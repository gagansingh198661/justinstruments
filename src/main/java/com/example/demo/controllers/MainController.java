package com.example.demo.controllers;


import com.example.demo.dtos.*;
import com.example.demo.entities.*;
import com.example.demo.exceptions.ReportNotMadeException;
import com.example.demo.repositories.*;
import com.example.demo.services.*;
import com.example.demo.utilities.*;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import org.controlsfx.control.textfield.TextFields;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component
public class MainController  {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @FXML
    private Tab settingsTab;

    @FXML
    private TextField client_name_t,date_heading_text,emailTextfiled;

    @FXML
    private Button updateDatabase;






    @FXML
    private Label customer_label,address_label,fax_label,settingsAtTestLabel;

    @FXML
    private Label phone_label;

    @FXML
    private TextField asset_no_1;

    @FXML
    private Label serial_no_1,model_no_1,description_1,e_date_1,manufacturer_1;

    @FXML
    private TextField asset_no_2,tag_no_text,ref_no,toleranceText,settingsAtTestText;

    @FXML
    private Label model_no_2,description_2,serial_no_2,e_date_2,manufacturer_2;

    @FXML
    private ComboBox frequency ;

    @FXML
    private Label modelInstrument,locationInstrument,rangeInstrument,manufacturerInstrument
            ,due_date,descriptionInstrument,serialNoInstrument;

    @FXML
    private DatePicker cal_date_dp;

    @FXML
    private Label input_4_found,input_3_found,input_2_found,input_1_found,input_0_found,
            input_0_left,input_1_left,input_2_left,input_3_left,input_4_left;

    @FXML
    private TextField error_0_found,error_1_found,error_2_found,error_3_found,error_4_found,
            error_0_last,error_1_last,error_2_last,error_3_last,error_4_last;

    @FXML
    private TextField output_0_found,output_1_found,output_2_found,output_3_found,output_4_found;

    @FXML
    private TextField  output_0_last,output_1_last,output_2_last,output_3_last,output_4_last;

    private Map<Object,String> parameterMap;

    @FXML
    private TextField db_name_txt,username_txt,password_txt,port_txt,reportText;

    @FXML
    private Label passed_label,failed_label,comments_label,filePath;

    @FXML
    private StackPane root;

    @Autowired
    private ApplicationPropertyService applicationPropertyService;

    @Autowired
    private OutputService outputService;

    @Autowired
    private ExcelUtility excelUtility;

    @Autowired
    private ClientService clientService;

    @Autowired
    private InstrumentService instrumentService;


    @FXML
    private ComboBox rangeCombo;

    @Autowired
    private MasterInstrumentsService masterInstrumentsService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ThreadUtility threadUtility;

    @FXML
    private CheckBox validationCheck;

    @FXML
    private ScrollPane reportScrollPane;

    @FXML
    private AnchorPane anchorReportPane;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public void initialize() {
        try {
            initializeDBConnectionProperties();
            threadUtility.checkForExpiryOfReports();
            List<Client> clients = getClients();
            List suggestion = new LinkedList<String>();
            for (Client client : clients) {
                suggestion.add(client.getName());
            }

            TextFields.bindAutoCompletion(client_name_t, suggestion);
            client_name_t.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    doSomething(clients);
                }
            });
            asset_no_1.textProperty().addListener(
                    (observableValue, s, t1) -> {
                        MasterInstruments masterInstrument=null;
                        if(!asset_no_1.getText().isEmpty()) {
                            masterInstrument = masterInstrumentsService.getMasterInstrumentbyAssetNo(asset_no_1.getText());
                        }
                        if(masterInstrument!=null) {
                            serial_no_1.setText(masterInstrument.getInstrumentSerialNo());
                            description_1.setText(masterInstrument.getDescription());
                            model_no_1.setText(masterInstrument.getModel());
                            manufacturer_1.setText(masterInstrument.getMake());
                            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                            String strDate = dateFormat.format(masterInstrument.getDueDate());
                            e_date_1.setText(strDate);
                        }
                    }
            );
            asset_no_2.textProperty().addListener(
                    (observableValue, s, t1) -> {
                        MasterInstruments masterInstrument=null;
                        if(!asset_no_2.getText().isEmpty()) {
                            masterInstrument = masterInstrumentsService.getMasterInstrumentbyAssetNo(asset_no_2.getText());
                        }
                        if(masterInstrument!=null) {
                            serial_no_2.setText(masterInstrument.getInstrumentSerialNo());
                            description_2.setText(masterInstrument.getDescription());
                            model_no_2.setText(masterInstrument.getModel());
                            manufacturer_2.setText(masterInstrument.getMake());
                            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                            String strDate = dateFormat.format(masterInstrument.getDueDate());
                            e_date_2.setText(strDate);
                        }
                    }
            );
            setFrequency();
            setInstrumentFieldSettings();
            rangeCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
                String rangeString = (String) rangeCombo.getValue();
                String[] rangeArray = null;
                String rangeNumStr="";
                if(rangeString.indexOf("(")!=-1&&rangeString.indexOf(")")!=-1){
                    rangeString=rangeString.replaceAll("\\(","");
                    rangeString=rangeString.replaceAll("\\)","");
                }
                if(rangeString.lastIndexOf("-")!=-1){
                    rangeArray = rangeString.split("-");
                   if(rangeArray.length==3){
                       rangeNumStr=rangeArray[2];
                   }else {
                       rangeNumStr = rangeArray[1];
                   }
                }else{
                    rangeNumStr=rangeString;
                }
                rangeArray=rangeNumStr.split(" ");
                if(rangeArray.length==2) {
                    rangeNumStr = rangeArray[0];
                }else{
                    rangeNumStr = rangeArray[0];
                }
                parameterMap.put(Constants.UNIT,rangeArray[1]);
                Double range = Double.valueOf(rangeNumStr);
                input_4_found.setText(String.format("%.2f", range));
                input_3_found.setText(String.format("%.2f", range * .75));
                input_2_found.setText(String.format("%.2f", range * .5));
                input_1_found.setText(String.format("%.2f", range * .25));
                input_0_found.setText("0.00");
                input_4_left.setText(String.format("%.2f", range));
                input_3_left.setText(String.format("%.2f", range * .75));
                input_2_left.setText(String.format("%.2f", range * .5));
                input_1_left.setText(String.format("%.2f", range * .25));
                input_0_left.setText("0.00");


            });
            addListenersToOutputColumns();
        }catch(Exception e){
            LOGGER.error("Error :  ",e);
        }
        createFieldsMap();
    }



    private void initializeDBConnectionProperties() {
        Map<String,String> propertiesMap=applicationPropertyService.getApplicationProperties();
        //Properties properties= ConnectionUtility.getconnectionProperties();
        db_name_txt.setText(propertiesMap.get("database_name"));
        username_txt.setText(propertiesMap.get("username"));
        password_txt.setText(propertiesMap.get("password"));
        port_txt.setText(propertiesMap.get("port"));
        reportText.setText(propertiesMap.get("report_path"));

    }


    private void setInstrumentFieldSettings() {
        List<String> instrumentStringList=new LinkedList<>();
        List<Instrument> instrumentListAll=getInstruments();
        instrumentListAll.stream().map(t->t.getTagNo()).forEach(
                t->instrumentStringList.add(t)
        );
        TextFields.bindAutoCompletion(tag_no_text,instrumentStringList);
        tag_no_text.textProperty().addListener(
                (observableValue, s, t1) -> {
                    List<Instrument> instrumentList=instrumentService.getInstruments(tag_no_text.getText()) ;
                    if(instrumentList.size()!=0) {
                        Instrument instrument = instrumentList.get(0);
                        manufacturerInstrument.setText(instrument.getMake());
                        modelInstrument.setText(instrument.getModel());
                        locationInstrument.setText(instrument.getLocation());
                        // rangeInstrument.setText(instrument.getLevel() + "");
                        ref_no.setText(instrument.getCal_ref_no());
                        serialNoInstrument.setText(instrument.getInstrumentSerialNo());
                        descriptionInstrument.setText(instrument.getDescription());
                        Date due_date_1=instrument.getDate();
                        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        String strDate = dateFormat.format(due_date_1);
                        due_date.setText(strDate);
                        String[] comboArray=instrument.getRanges().split(",");
                        List<String> comboList=Arrays.asList(comboArray).stream().map(input->{
                            String param=input;
                            if(input.indexOf("?")!=-1)
                                input=input.substring(0,input.indexOf("?"))+"\u00B0"+ input.substring(input.indexOf("?")+1);
                            return input;
                        }).collect(Collectors.toList());
                        int i=0;
                        for(String com: comboList){
                            comboArray[i]=com;
                            i++;
                        }

                        if(comboArray.length!=0) {
                            rangeCombo.setItems(FXCollections.observableList(Arrays.asList(comboArray)));
                            if(comboArray.length==1)
                            rangeCombo.setValue(comboArray[0]);
                        }
                    }
                }
        );
        List<String> instrumentSerialStringList=new LinkedList<>();
        instrumentListAll.stream().map(t->t.getInstrumentSerialNo()).forEach(
                t->instrumentSerialStringList.add(t)
        );
       /* TextFields.bindAutoCompletion(instrument_serial_no_textfield,instrumentSerialStringList);
        instrument_serial_no_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Instrument> instrumentList=instrumentService.getInstrumentsBySerialNo(instrument_serial_no_textfield.getText()) ;
            if(instrumentList.size()!=0) {
                Instrument instrument = instrumentList.get(0);
                manufacturerInstrument.setText(instrument.getMake());
                modelInstrument.setText(instrument.getModel());
                locationInstrument.setText(instrument.getLocation());
                tag_no_text.setText(instrument.getTagNo());
                ref_no.setText(instrument.getCal_ref_no());
                serialNoInstrument.setText(instrument.getInstrumentSerialNo());
                descriptionInstrument.setText(instrument.getDescription());
                String[] comboArray=instrument.getRanges().split(",");

                if(comboArray.length!=0) {
                    rangeCombo.setItems(FXCollections.observableList(Arrays.asList(comboArray)));
                }
            }
        });*/
        due_date.textProperty().addListener((observable, oldValue, newValue) -> {
            String due_Date1=due_date.getText();
            try {
                String format="MM-dd-yyyy";
                Date date1=new SimpleDateFormat(format).parse(due_Date1);
                LocalDate localDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localDate=localDate.minusMonths(Long.valueOf((String) frequency.getValue()));
                cal_date_dp.setValue(localDate);
                date_heading_text.setText(convertDate(localDate,format));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void setFrequency() {
        List<String> frequencyList=new LinkedList<>();
        for(int i=1;i<25;i++){
            if(i%3==0){
                frequencyList.add(i+"");
            }
        }
        frequency.getItems().addAll(frequencyList);
        frequency.setValue(frequencyList.get(3));
    }

    private List<Instrument> getInstruments(){
        return instrumentService.findAll();
    }



    private void doSomething(List<Client> clients) {

        String text=client_name_t.getText();
        for(Client client: clients){
            if(client.getName().equals(text)){
                customer_label.setText(text);
                address_label.setText(client.getAddress());
                phone_label.setText(client.getPhone());
                fax_label.setText(client.getFax());
                emailTextfiled.setText(client.getEmail());
            }
        }

    }

    private List<Client> getClients() {
        return clientService.getAll();
    }


    public void initializeDueDate(ActionEvent actionEvent) {

        LocalDate date=cal_date_dp.getValue();
        ZoneId defaultZoneId = ZoneId.systemDefault();

        if(date!=null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

            LocalDate duedate = date.plusMonths(Long.valueOf(frequency.getValue().toString()));
            Date dateDate=Date.from(duedate.atStartOfDay(defaultZoneId).toInstant());
            String formattedDate = formatter.format(dateDate);
            due_date.setText(formattedDate);
            Date currentDate=Date.from(date.atStartOfDay(defaultZoneId).toInstant());
            formattedDate=formatter.format(currentDate);
            date_heading_text.setText(formattedDate);
        }
    }

    public String convertDate(LocalDate localDate,String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date dateDate=Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        String formattedDate = formatter.format(dateDate);
        return formattedDate;
    }


    public void createReport(ActionEvent actionEvent) {
        try {

            if (validateForm()) {
                ProgressIndicator pi = new ProgressIndicator();
                VBox box = new VBox(pi);
                 box.setAlignment(Pos.CENTER);
                root.getChildren().add(box);
                Map<String, Map<String, String>> parameterMap = createParameterMap();
                try {
                    ReportDetails reportDetails = PDFReportGenerator.generatePDF(parameterMap);
                    Report reportDTO = createReportDTO(reportDetails);
                    reportService.save(reportDTO);
                    Utility.showPopup(Alert.AlertType.CONFIRMATION, "Report Created In Directory",reportScrollPane);
                }catch(Exception e){
                    if(e instanceof ReportNotMadeException) {

                        Utility.showPopup(Alert.AlertType.ERROR,"Report Not Getting Created!!",reportScrollPane);//,stage);
                    }
                    LOGGER.error("The Report was not saved in Database",e);
                }
                root.getChildren().remove(box);
                // resetForm();
            }
        }catch(Exception e){
            LOGGER.error("Error While Creating Report : ");
            e.printStackTrace();
        }

    }

    private void resetForm() {
        for(Map.Entry<Object,String> entry:parameterMap.entrySet()){
            Object object=entry.getKey();
            if(object instanceof Label){
                Label label=(Label) object;
                if(entry.getValue()!=null) {
                    label.setText(entry.getValue());
                }else {
                    label.setText("");
                }
            }else if(object instanceof TextField){
                TextField textField=(TextField) object;
                if(entry.getValue()!=null) {

                    textField.setText(entry.getValue());
                }else{
                    textField.setText("");
                }
            }else if(object instanceof ComboBox){
                ComboBox textField=(ComboBox) object;
                if(entry.getValue()!=null) {
                    textField.setValue(entry.getValue());
                }else{
                    textField.setValue(textField.getPromptText());
                }
            }
           // instrument_serial_no_textfield.setText(instrument_serial_no_textfield.getPromptText());
            cal_date_dp.setValue(null);
        }
    }

    private Report createReportDTO(ReportDetails details) {

        Report report=new Report();
        try{

            File file=new File(details.getPath());
            byte[] bytesArray=new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            report.setReportFile(bytesArray);
            String customer=client_name_t.getText();

            report.setReportCreationDate(new Date());

            String sDate1=due_date.getText();
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            report.setExpiryDate(date1);

            report.setClientName(customer);
            report.setReportName(details.getReportName());

        }catch(Exception e){
            LOGGER.error("Error :",e);
        }
        return report;
    }

    private Map<String, Map<String, String>>  createParameterMap() {
        Map<String, Map<String, String>> parameterReportMap=null;
        try {
            parameterReportMap = new HashMap<>();
            Map<String, String> tableMap = new HashMap<>();
            tableMap.put(Constants.JUST_FILE_NO, client_name_t.getText());
            tableMap.put(Constants.JUST_REF_NO, ref_no.getText());
            tableMap.put(Constants.DATE, date_heading_text.getText());

            parameterReportMap.put(Constants.CUSTOMER_BASIC, tableMap);

            Map<String, String> customerCompleteMap = new HashMap<>();
            customerCompleteMap.put(Constants.CUSTOMER, customer_label.getText());
            customerCompleteMap.put(Constants.ADDRESS, address_label.getText());
            customerCompleteMap.put(Constants.FAX, fax_label.getText());
            customerCompleteMap.put(Constants.EMAIL, emailTextfiled.getText());
            customerCompleteMap.put(Constants.PHONE, phone_label.getText());

            parameterReportMap.put(Constants.CUSTOMER_COMPLETE, customerCompleteMap);

            Map<String, String> equipmentMap1 = new LinkedHashMap<>();
            equipmentMap1.put(Constants.DESCRIPTION, description_1.getText());
            equipmentMap1.put(Constants.MANUFACTURER, manufacturer_1.getText());
            equipmentMap1.put(Constants.MODEL_NO, model_no_1.getText());
            equipmentMap1.put(Constants.SERIAL_NO, serial_no_1.getText());
            equipmentMap1.put(Constants.EXPIRY_DATE, e_date_1.getText());
            parameterReportMap.put(Constants.EQUIPMENT_1, equipmentMap1);

            Map<String, String> equipmentMap2 = new LinkedHashMap<>();
            equipmentMap2.put(Constants.DESCRIPTION, description_2.getText());
            equipmentMap2.put(Constants.MANUFACTURER, manufacturer_2.getText());
            equipmentMap2.put(Constants.MODEL_NO, model_no_2.getText());
            equipmentMap2.put(Constants.SERIAL_NO, serial_no_2.getText());
            equipmentMap2.put(Constants.EXPIRY_DATE, e_date_2.getText());
            parameterReportMap.put(Constants.EQUIPMENT_2, equipmentMap2);

            Map<String, String> instrumentMap = new LinkedHashMap<>();
            instrumentMap.put(Constants.DESCRIPTION, descriptionInstrument.getText());
            instrumentMap.put(Constants.TAG_NO, tag_no_text.getText());
            instrumentMap.put(Constants.MANUFACTURER, manufacturerInstrument.getText());
            instrumentMap.put(Constants.LOCATION, locationInstrument.getText());
            instrumentMap.put(Constants.MODEL_NO, modelInstrument.getText());
            instrumentMap.put(Constants.TOLERANCE, toleranceText.getText()+" %");
            instrumentMap.put(Constants.SERIAL_NO, serialNoInstrument.getText());
            if(cal_date_dp.getValue()!=null)
                instrumentMap.put(Constants.CAL_DATE, convertDate(cal_date_dp.getValue(),"MM-dd-yyyy"));

            String rangeString=(String)rangeCombo.getValue();

            instrumentMap.put(Constants.RANGE, rangeString);
            instrumentMap.put(Constants.DUE_DATE, due_date.getText());

            parameterReportMap.put(Constants.INSTRUMENT, instrumentMap);

            Map<String, String> calibrationResultsMap = new LinkedHashMap<>();
            calibrationResultsMap.put(Constants.FOUND_INPUT_0, input_0_found.getText());
            calibrationResultsMap.put(Constants.FOUND_INPUT_25, input_1_found.getText());
            calibrationResultsMap.put(Constants.FOUND_INPUT_50, input_2_found.getText());
            calibrationResultsMap.put(Constants.FOUND_INPUT_75, input_3_found.getText());
            calibrationResultsMap.put(Constants.FOUND_INPUT_100, input_4_found.getText());

            calibrationResultsMap.put(Constants.FOUND_OUTPUT_0, output_0_found.getText());
            calibrationResultsMap.put(Constants.FOUND_OUTPUT_25, output_1_found.getText());
            calibrationResultsMap.put(Constants.FOUND_OUTPUT_50, output_2_found.getText());
            calibrationResultsMap.put(Constants.FOUND_OUTPUT_75, output_3_found.getText());
            calibrationResultsMap.put(Constants.FOUND_OUTPUT_100, output_4_found.getText());

            calibrationResultsMap.put(Constants.FOUND_ERROR_0, error_0_found.getText());
            calibrationResultsMap.put(Constants.FOUND_ERROR_25, error_1_found.getText());
            calibrationResultsMap.put(Constants.FOUND_ERROR_50, error_2_found.getText());
            calibrationResultsMap.put(Constants.FOUND_ERROR_75, error_3_found.getText());
            calibrationResultsMap.put(Constants.FOUND_ERROR_100, error_4_found.getText());

            calibrationResultsMap.put(Constants.LEFT_OUTPUT_0, output_0_last.getText());
            calibrationResultsMap.put(Constants.LEFT_OUTPUT_25, output_1_last.getText());
            calibrationResultsMap.put(Constants.LEFT_OUTPUT_50, output_2_last.getText());
            calibrationResultsMap.put(Constants.LEFT_OUTPUT_75, output_3_last.getText());
            calibrationResultsMap.put(Constants.LEFT_OUTPUT_100, output_4_last.getText());

            calibrationResultsMap.put(Constants.LAST_ERROR_0,error_0_last.getText());
            calibrationResultsMap.put(Constants.LAST_ERROR_25,error_1_last.getText());
            calibrationResultsMap.put(Constants.LAST_ERROR_50,error_2_last.getText());
            calibrationResultsMap.put(Constants.LAST_ERROR_75,error_3_last.getText());
            calibrationResultsMap.put(Constants.LAST_ERROR_100,error_4_last.getText());
            parameterReportMap.put(Constants.CALIBRATION_RESULTS, calibrationResultsMap);

            Map<String, String> calibrationReportMap1 = new HashMap<>();
            calibrationReportMap1.put(Constants.PASSED,passed_label.getText());
            calibrationReportMap1.put(Constants.FAILED,failed_label.getText());
            calibrationReportMap1.put(Constants.COMMENTS,comments_label.getText());
            parameterReportMap.put(Constants.CALIBRATION_REPORT1,calibrationReportMap1);

            Map<String, String> directoryMap = new HashMap<>();
            directoryMap.put(Constants.DIRECTORY,reportText.getText());
            directoryMap.put(Constants.CUSTOMER,customer_label.getText());
            parameterReportMap.put(Constants.DIRECTORY,directoryMap);

            /*gfdsfgdsfgdsgf*/
            Map<String,String> calibrationSettingsMap=new HashMap<>();
            calibrationSettingsMap.put(Constants.TEMPERATUREANDSETTINGSLABEL,settingsAtTestLabel.getText());
            calibrationSettingsMap.put(Constants.TEMPERATUREANDSETTINGSTEXT,settingsAtTestText.getText());
            parameterReportMap.put(Constants.TEMPERATUREANDSETTINGSMAP,calibrationSettingsMap);

            Map<String,String> unitMap=new HashMap<>();
            unitMap.put(Constants.UNIT,parameterMap.get(Constants.UNIT));
            parameterReportMap.put(Constants.UNIT,unitMap);

            Map<String,String> validationMap=new HashMap<>();
            validationMap.put(Constants.ISVALIDATION,parameterMap.get(Constants.ISVALIDATION)==null?"false":parameterMap.get(Constants.ISVALIDATION));
            parameterReportMap.put(Constants.ISVALIDATION,validationMap);

            Map<String,String> refNoMap=new HashMap<>();
            refNoMap.put(Constants.REPORT_NAME,ref_no.getText());

            parameterReportMap.put(Constants.REPORT_NAME,refNoMap);
        }catch (Exception e){

            LOGGER.error("Error : ",e);
        }
        return parameterReportMap;


    }

    private void createFieldsMap() {
        parameterMap= new HashMap<Object, String>();
        parameterMap.put(client_name_t,client_name_t.getText());
        parameterMap.put(date_heading_text,date_heading_text.getText());
        parameterMap.put(customer_label,customer_label.getText());
        parameterMap.put(address_label,address_label.getText());
        parameterMap.put(phone_label,phone_label.getText());
        parameterMap.put(serial_no_1,serial_no_1.getText());
        parameterMap.put(model_no_1,model_no_1.getText());
        parameterMap.put(description_1,description_1.getText());
        parameterMap.put(e_date_1,e_date_1.getText());
        parameterMap.put(manufacturer_1,manufacturer_1.getText());
        parameterMap.put(asset_no_1,asset_no_1.getText());
        parameterMap.put(asset_no_2,asset_no_2.getText());
        parameterMap.put(model_no_2,model_no_2.getText());
        parameterMap.put(description_2,description_2.getText());
        parameterMap.put(serial_no_2,serial_no_2.getText());
        parameterMap.put(e_date_2,e_date_2.getText());
        parameterMap.put(manufacturer_2,manufacturer_2.getText());
        parameterMap.put(tag_no_text,tag_no_text.getText());
        parameterMap.put(ref_no,ref_no.getText());
        if(cal_date_dp.getValue()==null)
            parameterMap.put(cal_date_dp,null);
        parameterMap.put(modelInstrument,modelInstrument.getText());
        parameterMap.put(locationInstrument,locationInstrument.getText());
        if(rangeCombo.getValue()==null) {
            parameterMap.put(rangeCombo, null);
        }else{
            parameterMap.put(rangeCombo, rangeCombo.getValue().toString());
        }
        parameterMap.put(due_date,due_date.getText());
        parameterMap.put(input_4_found,input_4_found.getText());
        parameterMap.put(input_3_found,input_3_found.getText());
        parameterMap.put(input_2_found,input_2_found.getText());
        parameterMap.put(input_1_found,input_1_found.getText());
        parameterMap.put(input_0_found,input_0_found.getText());
        parameterMap.put(input_0_left,input_0_left.getText());
        parameterMap.put(input_1_left,input_1_left.getText());
        parameterMap.put(input_2_left,input_2_left.getText());
        parameterMap.put(input_3_left,input_3_left.getText());
        parameterMap.put(input_4_left,input_4_left.getText());
        parameterMap.put(error_0_found,error_0_found.getText());
        parameterMap.put(error_1_found,error_1_found.getText());
        parameterMap.put(error_2_found,error_2_found.getText());
        parameterMap.put(error_3_found,error_3_found.getText());
        parameterMap.put(error_4_found,error_4_found.getText());
        parameterMap.put(error_0_last,error_0_last.getText());
        parameterMap.put(error_1_last,error_1_last.getText());
        parameterMap.put(error_2_last,error_2_last.getText());
        parameterMap.put(error_3_last,error_3_last.getText());
        parameterMap.put(error_4_last,error_4_last.getText());
        parameterMap.put(output_0_found,output_0_found.getText());
        parameterMap.put(output_1_found,output_1_found.getText());
        parameterMap.put(output_2_found,output_2_found.getText());
        parameterMap.put(output_3_found,output_3_found.getText());
        parameterMap.put(output_4_found,output_4_found.getText());
        parameterMap.put(output_0_last,output_0_last.getText());
        parameterMap.put(output_1_last,output_1_last.getText());
        parameterMap.put(output_2_last,output_2_last.getText());
        parameterMap.put(output_3_last,output_3_last.getText());
        parameterMap.put(output_4_last,output_4_last.getText());
        parameterMap.put(emailTextfiled,emailTextfiled.getPromptText());
        parameterMap.put(fax_label,fax_label.getText());
        parameterMap.put(description_1,description_1.getText());
        parameterMap.put(model_no_1,model_no_1.getText());
        parameterMap.put(serial_no_1,serial_no_1.getText());
        parameterMap.put(serialNoInstrument,serialNoInstrument.getText());
        parameterMap.put(manufacturerInstrument,manufacturerInstrument.getText());
        parameterMap.put(descriptionInstrument,descriptionInstrument.getText());
        parameterMap.put(modelInstrument,modelInstrument.getText());
        parameterMap.put(settingsAtTestText,settingsAtTestText.getText());


    }

    private boolean validateForm(){
        return Utility.validateMap(parameterMap);
    }



    private void addListenersToOutputColumns(){
        TextField[] outputFound=new TextField[5];
        outputFound[0]=output_0_found;
        outputFound[1]=output_1_found;
        outputFound[2]=output_2_found;
        outputFound[3]=output_3_found;
        outputFound[4]=output_4_found;

        TextField[] outputLast=new TextField[5];
        outputLast[0]=output_0_last;
        outputLast[1]=output_1_last;
        outputLast[2]=output_2_last;
        outputLast[3]=output_3_last;
        outputLast[4]=output_4_last;

        TextField[] labelfoundArr=new TextField[5];
        labelfoundArr[0]=error_0_found;
        labelfoundArr[1]=error_1_found;
        labelfoundArr[2]=error_2_found;
        labelfoundArr[3]=error_3_found;
        labelfoundArr[4]=error_4_found;
        TextField[] errorlabel=new TextField[5];
        errorlabel[0]=error_0_last;
        errorlabel[1]=error_1_last;
        errorlabel[2]=error_2_last;
        errorlabel[3]=error_3_last;
        errorlabel[4]=error_4_last;

        Label[] inputFoundlabel=new Label[5];
        inputFoundlabel[0]=input_0_found;
        inputFoundlabel[1]=input_1_found;
        inputFoundlabel[2]=input_2_found;
        inputFoundlabel[3]=input_3_found;
        inputFoundlabel[4]=input_4_found;

        Label[] inputLastlabel=new Label[5];
        inputLastlabel[0]=input_0_left;
        inputLastlabel[1]=input_1_left;
        inputLastlabel[2]=input_2_left;
        inputLastlabel[3]=input_3_left;
        inputLastlabel[4]=input_4_left;


        addListenersToAllText(outputFound, labelfoundArr, inputFoundlabel);
        addListenersToAllText(outputLast, errorlabel, inputLastlabel);
        updateListenersToFillValueInOutPutFound(outputLast,outputFound);
        addListenersToErrorText(outputFound,labelfoundArr,inputFoundlabel);
        addListenersToErrorText(outputLast,errorlabel,inputLastlabel);
    }

    private void addListenersToErrorText(TextField[] outputFound, TextField[] textErr, Label[] inputlabel) {
        int i=0;
        for(TextField t:textErr){
            final int  temp=i;
            t.textProperty().addListener((observable -> {

                if(!t.getText().isEmpty()&&Utility.isInputANumber(t.getText())&&
                        !toleranceText.getText().isEmpty()&&Utility.isInputANumber(toleranceText.getText())&&
                !inputlabel[temp].getText().isEmpty()){
                    outputFound[temp].setText(getOutputOfTextField(t.getText(),inputlabel[temp].getText()));
                }
            }));
            i++;
        }
    }

    private String getOutputOfTextField(String error,String input){
        double errD=Double.valueOf(error);
        double inputD=Double.valueOf(input);
        double result=(inputD+errD);
        return String.format("%.2f", result);
    }
    private void addListenersToAllText(TextField[] outputFound, TextField[] labelArr, Label[] inputLabel) {
        int i=0;
        for(TextField tField:outputFound){
            Label output1=inputLabel[i];
            TextField errorText=labelArr[i];
            tField.textProperty().addListener((observableValue, s, t1) -> {
                if(Utility.isInputANumber(tField.getText())){
                    double value=Double.valueOf(tField.getText());
                    if(!output1.getText().isEmpty()) {
                        //String output = String.format("%.2f", value - Double.valueOf(output1.getText()));
                       // errorText.setText(output);
                        checkIfAllLabelsHaveFilled(inputLabel);
                    }
                }else{
                    errorText.setText("");
                }
            });
            i++;
        }
    }

    private void updateListenersToFillValueInOutPutFound(TextField[] outputLast,  TextField[] outputFound) {
        int i=0;
        for(;i<outputLast.length;i++){
            TextField tField=outputLast[i];
            TextField tFoundField=outputFound[i];
            tField.textProperty().addListener((observableValue, s, t1) -> {
                if(Utility.isInputANumber(tField.getText())){
                    tFoundField.setText(tField.getText());

                }
            });

        }
    }

    private void checkIfAllLabelsHaveFilled(Label[] inputLabel) {
        try {
            double[] error=new double[5];

            error[0] = Double.valueOf(error_0_last.getText());
            error[1] = Double.valueOf(error_1_last.getText());
            error[2] = Double.valueOf(error_2_last.getText());
            error[3] = Double.valueOf(error_3_last.getText());
            error[4] = Double.valueOf(error_4_last.getText());
            int i=0;
            boolean passed =true;
            for(double err:error){
                double per=getPercentage(Math.abs(err),Double.valueOf(inputLabel[i].getText()));
                if(per>=Double.valueOf(toleranceText.getText())){
                    passed=false;
                }
                i++;
            }
            if(passed){
                passed_label.setText("YES");
                failed_label.setText("NA");
                comments_label.setText("Unit Working Within Tolerance");
            }else{
                failed_label.setText("YES");
                passed_label.setText("NA");
                comments_label.setText("Unit Not Within Tolerance");
            }

        }catch(Exception e){

            if(e instanceof  NumberFormatException) {
                LOGGER.debug("Error : ", e);
            }else{
                LOGGER.error("Error : ", e);
            }
        }
    }

    private Double getPercentage(double error,double value){
        double percentage= (error/value)*100;
        return percentage;
    }


    public void findExcelFile(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            filePath.setText(file.getPath());
        }

    }

    public void updateDatabase(ActionEvent actionEvent) {
        if(!filePath.getText().equalsIgnoreCase(Constants.FILEPATH)) {
            try {
                File file = new File(filePath.getText());
                excelUtility.updateDatabase(file);
                initialize();
                Utility.showPopup(Alert.AlertType.CONFIRMATION,"Success!! Database Updated. Please Restart The Application To See the Changes!!",anchorReportPane);
            }catch(Exception e){
                LOGGER.error("Error Occured",e);
                Utility.showPopup(Alert.AlertType.ERROR,"Oops Something Went Wrong, maybe the file is open in Background ??",anchorReportPane);
            }
        }
        //
    }

    public void isAValidationReport(ActionEvent actionEvent) {
        Boolean isValidation=validationCheck.isSelected();
        parameterMap.put(Constants.ISVALIDATION,isValidation.toString());
    }

    public void clearForm(ActionEvent actionEvent) {
        resetForm();
    }
}
