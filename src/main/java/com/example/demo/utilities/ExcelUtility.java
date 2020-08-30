package com.example.demo.utilities;

import com.example.demo.dtos.ResponseDTO;
import com.example.demo.entities.Client;
import com.example.demo.entities.ExcelResult;
import com.example.demo.entities.Instrument;
import com.example.demo.entities.MasterInstruments;
import com.example.demo.services.*;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.io.File;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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




    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtility.class);

    public ResponseDTO updateDatabase(File file) {
        ResponseDTO responseDTO=new ResponseDTO();
        List<Instrument> instrumentSet=new LinkedList<>();
        List<Client> clientList=null;
        List<MasterInstruments> masterInstrumentList=null;

        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numOfSheets=  wb.getNumberOfSheets();
        for(int i=0;i<numOfSheets; i++) {
            Sheet mySheet = wb.getSheetAt(i);
            if(mySheet!=null) {
                Collection collection = processResult(mySheet);
                if (collection != null) {
                    if (mySheet.getSheetName().equals(Constants.CLIENT)) {
                        clientList = (List<Client>) collection;

                    } else if(mySheet.getSheetName().equalsIgnoreCase(Constants.MASTER)){
                        masterInstrumentList = (List<MasterInstruments>) collection;
                    } else  {
                        List<Instrument> localInstrumentSet = (List<Instrument>) collection;
                        instrumentSet.addAll(localInstrumentSet);
                    }
                }
            }
        }


            clientService.saveAll(clientList);
            //clientList=clientService.getAll();
            instrumentService.saveAll(instrumentSet);

            masterInstrumentsService.saveAll(masterInstrumentList);


        responseDTO.setReturned(true);
        return responseDTO;
    }

    private  Collection processResult(Sheet name) {
        try {
            List<ExcelResult> resultDtoList = excelService.findAll();

            List objectList = new LinkedList();
            objectList = getObjectsFromSheet(resultDtoList, name);
            if (objectList != null) {
                if (name.getSheetName().equals(Constants.CLIENT)) {
                    List<Client> clientList = new LinkedList<>();
                    clientList = (List<Client>) objectList.stream().map(o -> (Client) o).collect(Collectors.toList());
                    return clientList;


                } else if (name.getSheetName().equalsIgnoreCase(Constants.MASTER)) {
                    List<MasterInstruments> masterInstrumentsList = new LinkedList<>();
                    masterInstrumentsList = (List<MasterInstruments>) objectList.stream().map(o -> (MasterInstruments) o).collect(Collectors.toList());
                    return masterInstrumentsList;
                } else {
                    List<Instrument> instrumentList = new LinkedList<>();

                    instrumentList = (List<Instrument>)
                            objectList.stream().map(o -> (Instrument) o).filter(o->((Instrument) o).getCalRefNo()!=null).collect(Collectors.toList());
                    String clientName=null;
                    for(Instrument instrument: instrumentList){
                        if(instrument.getClientId()!=null&&!instrument.getClientId().isEmpty()){
                            clientName=instrument.getClientId();break;
                        }
                    }
                    final String clNameFinal=clientName;
                    instrumentList=instrumentList.stream().map((instrument -> {instrument.setClientId(clNameFinal);
                    return instrument;
                    })).collect(Collectors.toList());
                    return instrumentList;
                }
            }
        }catch(Exception e){
            LOGGER.error("Error Occured at Sheet : "+name,e);
        }
        return null;
    }

    private static List getObjectsFromSheet( List<ExcelResult> resultDtoList, Sheet sheet) {

        ExcelResult currentSheetDto = getsheetDto(resultDtoList, sheet.getSheetName());
        if(currentSheetDto==null){
            return null;
        }
        int rowStart=sheet.getFirstRowNum();
        int rowLast=sheet.getLastRowNum();
        List objectList = new LinkedList();

        boolean indexOfParametersHaveBeenMade = false;
        for(int i=rowStart;i<rowLast;i++){
            Row row=sheet.getRow(i);
            if(row==null){
                continue;
            }
            if (!indexOfParametersHaveBeenMade) {
                try{
                    indexOfParametersHaveBeenMade = makeIndexOfParameter(row, currentSheetDto);
                }catch(Exception e){
                    LOGGER.error("Error occured",e);
                }
            } else {
                Object populatedObject = getObjectFromCell(sheet, currentSheetDto, row);
                if (populatedObject != null) {
                    objectList.add(populatedObject);
                }

            }
        }


        return objectList;
    }

    private static Object getObjectFromCell(Sheet sheet, ExcelResult currentSheetDto, Row row) {
        Map<Integer, String> indexParameterMap = currentSheetDto.getHashmap();
         if (sheet.getSheetName().equals(Constants.MASTER)) {
            MasterInstruments mInstrument = new MasterInstruments();
            for (int index = 0;index<row.getLastCellNum();index++) {
                Cell cell = row.getCell(index);
                String value = null;
                String param = indexParameterMap.get(index);
                    if (cell.getCellType() == CellType.NUMERIC) {
                        int value1 = (int) cell.getNumericCellValue();

                        value = value1 + "";
                    } else {

                        value = cell.toString();
                    }
                    if (param.equalsIgnoreCase(Constants.DUE_DATE_MASTER)) {
                        value = cell.toString();
                    }
                    if(value.equalsIgnoreCase(Constants.IGNORE_LINE)){
                        break;
                    }
                    if (param != null) {
                        mInstrument = constructMasterInstrumentObject(value, param, mInstrument);
                    }

            }
            return mInstrument;
        } else if(sheet.getSheetName().equals(Constants.CLIENT)){
            Client client = new Client();
            for (int index = 0;index<row.getLastCellNum();index++) {
                Cell cell = row.getCell(index);
                String value = cell.toString();
                String param = indexParameterMap.get(index);
                if (param != null) {
                    client = constructClientObject(value, param, client);
                }
            }
            return client;
        }else  {
            Instrument instrument = new Instrument();
            for (int index = 0;index<row.getLastCellNum();index++) {
                 if (row == null) {
                     continue;
                 }
                 Cell cell = row.getCell(index);
                 String value = "";
                 String param = indexParameterMap.get(index);
                 if (cell != null) {
                     value = cell.toString();
                 }
                 if(value.equalsIgnoreCase(Constants.IGNORE_LINE)){
                     return null;
                 }
                 if (param != null) {

                     try {
                         if(param.equalsIgnoreCase(Constants.TAG_NO)){
                             if(Utility.isInputANumber(value)){
                                 DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
                                 df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
                                 value=new BigDecimal(value).toPlainString(); // Output: 0.00000021
                             }
                         }
                         instrument = constructInstrumentObject(value, param, instrument);
                     } catch (ParseException e) {
                         e.printStackTrace();
                     }
                 }

             }
            return instrument;
        }
    }

    private static MasterInstruments constructMasterInstrumentObject(String value, String param, MasterInstruments mInstrument) {
        try{
            if (param.equals(Constants.INSTRUMENT_SR_NO_MASTER)) {
                mInstrument.setInstrumentSerialNo(value);
            }
            else if (param.equals(Constants.ASSET_NO_MASTER)&&!value.isEmpty()) {
                mInstrument.setAsset_no(Long.valueOf(value));
            } else if (param.equals(Constants.INSTRUMENT)) {
                mInstrument.setDescription(value);
            } else if (param.equals(Constants.MAKE)) {
                mInstrument.setMake(value);
            } else if (param.equals(Constants.MODEL)) {
                mInstrument.setModel(value);
            } else if (param.equals(Constants.DUE_DATE_MASTER)) {
                if(!value.isEmpty()){
                    SimpleDateFormat formatter4=new SimpleDateFormat("dd-MMM-yyyy");
                    try {
                        Date date1 = formatter4.parse(value);
                        mInstrument.setDueDate(date1);
                    }catch(Exception e){
                        Utility.showPopup(Alert.AlertType.ERROR,"Incorrect Date in Due Date Column Format : Enter Date With Correct Format Like : \"April 3,2021");
                    }
                }
            }
        }catch(Exception e){
            LOGGER.error("Error in Master Instruments Sheet for value :"+value+"  param: "+param);
            Utility.showPopup(Alert.AlertType.ERROR,"");
            System.out.println("Error for value :"+value+"  param: "+param);
            e.printStackTrace();
        }
        return mInstrument;
    }

    private static Client constructClientObject(String value, String param, Client client) {
        try{
            if (value != null) {
                value=value.trim();
            }
            if(param!=null){
                param=param.trim();
            }
            if (param.equals(Constants.CLIENT_NAME)&&!value.isEmpty()) {
                client.setName(value.trim());
            } else if (param.equals(Constants.CLIENT_ADDRESS)&&!value.isEmpty()) {
                client.setAddress(value.trim());
            } else if (param.equals(Constants.CLIENT_EMAIL)) {
                client.setEmail(value.trim());
            } else if (param.equals(Constants.CLIENT_PHONE)) {
                client.setPhone(value.trim());
            } else if (param.equals(Constants.CLIENT_FAX)) {
                client.setFax(value.trim());
            } else if (param.equals(Constants.CLIENT_DESCRIPTION)) {
                client.setDescription(value.trim());
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
            if (value != null) {
                value=value.trim();
            }
            if(param!=null){
                param=param.trim();
            }
            if (param.equals(Constants.TAG_NO)&&!value.isEmpty()) {
                instrument.setTagNo(value);
            } else if (param.equals(Constants.SR_NO)&&!value.isEmpty()) {
                instrument.setSerialNo(Integer.valueOf(value).intValue()+"");
            } else if (param.equals(Constants.INSTRUMENT)) {
                instrument.setDescription(value);
            } else if (param.equals(Constants.MAKE)) {
                instrument.setMake(value);
            } else if (param.equals(Constants.MODEL)) {
                instrument.setModel(value);
            } else if (param.equals(Constants.CAL_REF_NO)) {
                instrument.setCalRefNo(value);
            } else if (param.equals(Constants.DUE_DATE_HEADER)) {

                if(!value.isEmpty()){
                    String dateString=checkDateFormatString(value);

                    SimpleDateFormat formatter4=new SimpleDateFormat(dateString);
                    Date date1=formatter4.parse(value);
                    instrument.setDate(date1);
                }
              } else if (param.equals(Constants.LOCATION)) {
                instrument.setLocation(value);
            } else if (param.equals(Constants.INSTRUMENT_SERIAL_NO)&&!value.isEmpty()) {
                if(isANumber(value)){
                    value=(Double.valueOf(value).longValue())+"";
                }
                instrument.setInstrumentSerialNo(value);
            } else if(param.equals(Constants.RANGE)){
                instrument.setRanges(value);
            } else if(param.equals(Constants.REMARKS)){
                instrument.setRemarks(value);
            }else if(param.equalsIgnoreCase(Constants.CLIENT)){
                instrument.setClientId(value);
            }
        }catch(Exception e) {
            LOGGER.error("Errror",e);

        }

        return instrument;
    }

    private static boolean isANumber(String value) {
        try {
            double value1 = Double.valueOf(value);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    private static String checkDateFormatString(String value) {
        String formatString=null;
        if(value.indexOf("-")!=-1){
            formatString="dd-MMM-yyyy";
        }else if(value.indexOf(",")!=-1){
            formatString="MMMM dd, yyyy";
        }
        return formatString;

    }


    private static boolean makeIndexOfParameter(Row row, ExcelResult currentSheetDto) {
        Map<Integer, String> parameterMap=currentSheetDto.getHashmap();
        Map<Integer, String> generatedMap = new HashMap<>();
        boolean flag=false;
        for(int index=0;index<row.getLastCellNum();index++){
            Cell cell=row.getCell(index);
            if(!cell.toString().isEmpty()) {
                for(Map.Entry<Integer, String> entry:parameterMap.entrySet()) {
                    String param= cell.getStringCellValue().trim();
                    if (param.toLowerCase().equals(entry.getValue().toLowerCase())) {
                        generatedMap.put(index,cell.getStringCellValue());
                        flag=true;
                        break;
                    }
                }
            }
        }
        for (int i=0;i<row.getLastCellNum();i++) {
            Cell cell = row.getCell(i);

            if(!cell.toString().isEmpty()) {
                for(Map.Entry<Integer, String> entry:parameterMap.entrySet()) {
                    String param= cell.getStringCellValue().trim();
                    if (param.toLowerCase().equals(entry.getValue().toLowerCase())) {
                           generatedMap.put(i,cell.getStringCellValue());
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

        for(ExcelResult resultDto:resultDtoList){
            String nameOfSheetProcessed=sheetName.trim();
            String excelFromDTO=resultDto.getExcelName();
            if(excelFromDTO.equalsIgnoreCase(nameOfSheetProcessed)){
                return resultDto;
            }else if(!nameOfSheetProcessed.equalsIgnoreCase(Constants.MASTER)&&!nameOfSheetProcessed.equalsIgnoreCase(Constants.CLIENT)
                    &&!nameOfSheetProcessed.equalsIgnoreCase(Constants.INSTRUMENT)){
                return resultDto;
            }
        }
        return null;

    }
}
