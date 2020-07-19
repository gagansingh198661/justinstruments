package com.example.demo.utilities;




import com.example.demo.controllers.MainController;
import com.example.demo.entities.Client;
import com.example.demo.entities.ExcelResult;
import com.example.demo.entities.Instrument;
import com.example.demo.entities.MasterInstruments;
import com.example.demo.repositories.ExcelRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.ExcelService;

import com.example.demo.services.InstrumentService;
import com.example.demo.services.MasterInstrumentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class ExcelUtility {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MasterInstrumentsService masterInstrumentsService;

    private static final Logger LOGGER = LogManager.getLogger(ExcelUtility.class);

    public  void updateDatabase(File file)  {
        Set<Instrument> instrumentSet=null;
        List<Client> clientList=null;
        List<MasterInstruments> masterInstrumentList=null;
        try {
            Workbook wb = WorkbookFactory.create(file);
            int numOfSheets=  wb.getNumberOfSheets();
            for(int i=0;i<numOfSheets; i++) {
                Sheet mySheet = wb.getSheetAt(i);
                Collection collection=processResult(mySheet);
                if(collection!=null){
                    if(mySheet.getSheetName().equals(Constants.INSTRUMENT)){
                        instrumentSet=(Set<Instrument>)collection;
                    }else if(mySheet.getSheetName().equals(Constants.CLIENT)){
                        clientList=(List<Client>)collection;
                    }else {
                        masterInstrumentList=(List<MasterInstruments>) collection;
                    }
                }
            }



                instrumentService.saveAll(instrumentSet);
                clientService.saveAll(clientList);
                masterInstrumentsService.saveAll(masterInstrumentList);

        }catch (Exception e){
            LOGGER.error("Error : ",e);
            e.printStackTrace();
        }
    }

    private  Collection processResult(Sheet name) {
        ExcelRepository repository=new ExcelRepository();
        List<ExcelResult> resultDtoList=excelService.findAll();

        List objectList = new LinkedList();
        objectList=getObjectsFromSheet( resultDtoList, name);
        if(name.getSheetName().equals(Constants.INSTRUMENT)) {
            Set<Instrument> instrumentSet = new HashSet();
            instrumentSet = (Set<Instrument>) objectList.stream().map(o -> (Instrument) o).filter(o -> ((Instrument) o).getTagNo() != null && ((Instrument) o).getInstrumentSerialNo() != null).collect(Collectors.toSet());
            return instrumentSet;

        }else if(name.getSheetName().equals(Constants.CLIENT)){
            List<Client> clientList = new LinkedList<>();
            clientList= (List<Client>) objectList.stream().map(o->(Client)o).collect(Collectors.toList());
            return clientList;

        }else{
            List<MasterInstruments> masterInstrumentsList=new LinkedList<>();
            masterInstrumentsList=(List<MasterInstruments>) objectList.stream().map(o->(MasterInstruments)o).collect(Collectors.toList());
            return masterInstrumentsList;
        }

    }

    private static List getObjectsFromSheet( List<ExcelResult> resultDtoList, Sheet sheet) {
        ExcelResult currentSheetDto = getsheetDto(resultDtoList, sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        List objectList = new LinkedList();
        boolean indexOfParametersHaveBeenMade = false;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            if (!indexOfParametersHaveBeenMade) {
                indexOfParametersHaveBeenMade = makeIndexOfParameter(cellIterator, currentSheetDto);
            } else {
                Object populatedObject = getObjectFromCell(sheet.getSheetName(), currentSheetDto, cellIterator);
                if (populatedObject != null) {
                    objectList.add(populatedObject);
                }
            }
        }
        return objectList;
    }

    private static Object getObjectFromCell(String sheetName, ExcelResult currentSheetDto, Iterator<Cell> cellIterator) {
        Map<Integer, String> indexParameterMap = currentSheetDto.getHashmap();
        if (sheetName.equals(Constants.INSTRUMENT)) {
            Instrument instrument = new Instrument();
            int index = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String value = cell.toString();
                String param = indexParameterMap.get(index);
                if (param != null) {
                    index++;
                    try {
                        instrument = constructInstrumentObject(value, param, instrument);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            return instrument;
        } else if (sheetName.equals(Constants.CLIENT)) {
            Client client = new Client();
            int index = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String value = cell.toString();
                String param = indexParameterMap.get(index);
                if (param != null) {
                    index++;
                    client = constructClientObject(value, param, client);
                }
            }
            return client;
            }else if(sheetName.equals(Constants.MASTER)){
                MasterInstruments mInstrument=new MasterInstruments();
                int index = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String value = cell.toString();
                    String param = indexParameterMap.get(index);
                    if (param != null) {
                        index++;
                        mInstrument = constructMasterInstrumentObject(value, param, mInstrument);
                    }
                }
                return mInstrument;
        }
        return null;

    }

    private static MasterInstruments constructMasterInstrumentObject(String value, String param, MasterInstruments mInstrument) {
        try{
            if (param.equals(Constants.INSTRUMENT_SR_NO_MASTER)&&!value.isEmpty()) {
                mInstrument.setInstrumentSerialNo(value.substring(0,value.indexOf(".")));
            } else if (param.equals(Constants.ASSET_NO_MASTER)&&!value.isEmpty()) {
                mInstrument.setAsset_no(Long.valueOf(value.substring(0,value.indexOf("."))));
            } else if (param.equals(Constants.INSTRUMENT)) {
                mInstrument.setDescription(value);
            } else if (param.equals(Constants.MAKE)) {
                mInstrument.setMake(value);
            } else if (param.equals(Constants.MODEL)) {
                mInstrument.setModel(value);
            } else if (param.equals(Constants.DUE_DATE_MASTER)) {

                if(!value.isEmpty()){
                    SimpleDateFormat formatter4=new SimpleDateFormat("MMM dd,yyyy");
                    Date date1=formatter4.parse(value);
                    mInstrument.setDueDate(date1);
                }
            } else if (param.equals(Constants.CAL_DATE_MASTER)) {

                if(!value.isEmpty()){
                    SimpleDateFormat formatter4=new SimpleDateFormat("MMM dd,yyyy");
                    Date date1=formatter4.parse(value);
                    mInstrument.setCalDate(date1);
                }
            }
        }catch(Exception e){
            System.out.println("Error for value :"+value+"  param: "+param);
            e.printStackTrace();
        }
        return mInstrument;
    }

    private static Client constructClientObject(String value, String param, Client client) {
        try{
            if (param.equals(Constants.CLIENT_NAME)&&!value.isEmpty()) {
                client.setName(value);
            } else if (param.equals(Constants.CLIENT_ADDRESS)&&!value.isEmpty()) {
                client.setAddress(value);
            } else if (param.equals(Constants.CLIENT_EMAIL)) {
                client.setEmail(value);

            } else if (param.equals(Constants.CLIENT_PHONE)) {
                client.setPhone(value);
            } else if (param.equals(Constants.CLIENT_FAX)) {
                client.setFax(value);
            } else if (param.equals(Constants.CLIENT_DESCRIPTION)) {
                client.setDescription(value);
                //instrument.setD
            }
        }catch(Exception e){
            System.out.println("Error for value :"+value+"  param: "+param);
            e.printStackTrace();
        }
        return client;
    }

    private static Instrument constructInstrumentObject(String value, String param, Instrument instrument) throws ParseException {
        try{
            if (param.equals(Constants.TAG_NO)&&!value.isEmpty()) {
                instrument.setTagNo(value);
            } else if (param.equals(Constants.SR_NO)&&!value.isEmpty()) {

                instrument.setSerialNo(value.substring(0,value.indexOf(".")));
            } else if (param.equals(Constants.INSTRUMENT)) {
                instrument.setDescription(value);
            } else if (param.equals(Constants.MAKE)) {
                instrument.setMake(value);
            } else if (param.equals(Constants.MODEL)) {
                instrument.setModel(value);
            } else if (param.equals(Constants.CAL_REF_NO)) {
                instrument.setCal_ref_no(value);
            } else if (param.equals(Constants.DUE_DATE_HEADER)) {

                if(!value.isEmpty()){
                    SimpleDateFormat formatter4=new SimpleDateFormat("dd-MMM-yyyy");
                    Date date1=formatter4.parse(value);
                    instrument.setDate(date1);
                }
              } else if (param.equals(Constants.LOCATION)) {
                instrument.setLocation(value);
            } else if (param.equals(Constants.INSTRUMENT_SERIAL_NO)&&!value.isEmpty()) {
                instrument.setInstrumentSerialNo(value.substring(0,value.indexOf(".")));
            } else if(param.equals(Constants.RANGE)){
                instrument.setRanges(value);
            } else if(param.equals(Constants.REMARKS)){
                instrument.setRemarks(value);
            }
        }catch(Exception e){
            System.out.println("Error for value :"+value+"  param: "+param);
            e.printStackTrace();
        }


        return instrument;
    }

    private static String cleanLevel(String param) {
        if(param.indexOf("(")!=-1) {
            String[] values=param.split("/");
            if(values.length==2) {
                return values[1].substring(0,values[1].length()-2);
            }else{
             param=param.substring("LEVEL ".length(),param.length()-1);
             param=param=param.trim().substring(1);
             return param;
            }
        }
        return "";
    }

    private static boolean makeIndexOfParameter(Iterator<Cell> cellIterator, ExcelResult currentSheetDto) {
        Map<Integer, String> parameterMap=currentSheetDto.getHashmap();
        Map<Integer, String> generatedMap = new HashMap<>();
        boolean flag=false;
        int index=0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            if(!cell.toString().isEmpty()) {
                for(Map.Entry<Integer, String> entry:parameterMap.entrySet()) {
                    if (cell.getStringCellValue().toLowerCase().equals(entry.getValue().toLowerCase())) {
                           generatedMap.put(index,cell.getStringCellValue());
                           index++;
                           flag=true;
                           break;
                    }
                }
            }
        }
        if(flag){
            currentSheetDto.setHashmap(generatedMap);
        }
        return flag;
    }

    private static ExcelResult getsheetDto(List<ExcelResult> resultDtoList, String sheetName) {
        //ExcelResultDto result=new ExcelResultDto();
        for(ExcelResult resultDto:resultDtoList){
            if(resultDto.getExcelName().toLowerCase().equals(sheetName.trim().toLowerCase())){
                return resultDto;
            }
        }
        return null;

    }
}
