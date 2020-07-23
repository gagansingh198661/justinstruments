package com.example.demo.utilities;

import com.example.demo.dtos.ReportDTO;
import com.example.demo.dtos.ReportDetails;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Alert;
import javafx.scene.text.TextAlignment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;


import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

public class PDFReportGenerator {


    public static ReportDetails generatePDF(Map<String, Map<String, String>> parameterMap) throws IOException {
        Document document = new Document();
        FileOutputStream directory=null;
        ReportDetails details=null;
        try {
            details=getFileStream(parameterMap);
            directory=details.getFileOutputStream();

            PdfWriter.getInstance(document, directory);
            document.open();

            PdfPTable headerOfReport=createHeaderOfReportTable();
            PdfPTable customerBasicTable =createCustomerBasic(parameterMap.get(Constants.CUSTOMER_BASIC),Constants.CUSTOMER_BASIC);
            customerBasicTable.setSpacingAfter(15);
            customerBasicTable.setSpacingBefore(10);
            PdfPTable customerCompleteTable =createCustomerComplete(parameterMap.get(Constants.CUSTOMER_COMPLETE));
            customerCompleteTable.setSpacingAfter(10);

            Paragraph certificate_title_table = createHeaderTable(Constants.CERTIFICATE_OF_CALIBRATION,false);
            Paragraph test_equipment_title_table = createHeaderTable(Constants.TEST_EQUIPMENT_USED_FOR_CALIBRATION, true);
            Paragraph instrument_used_title_table = createHeaderTable(Constants.INSTRUMENT_IDENTIFICATION, true);
            Paragraph calibration_results_title_table = createHeaderTable(Constants.CALIBRATION_RESULTS, true);
            Paragraph calibration_report_title_table = createHeaderTable(Constants.CALIBRATION_REPORT, true);

            PdfPTable equipmentTable=createEquipmentTable(parameterMap);
            equipmentTable.setSpacingBefore(15);
            equipmentTable.setSpacingAfter(10);

            PdfPTable instrumentTable=createInstrumentTable(parameterMap.get(Constants.INSTRUMENT));
            instrumentTable.setSpacingBefore(10);
            instrumentTable.setSpacingAfter(10);

            PdfPTable calibrationResultsTable=createCalibrationResultsTable(parameterMap.get(Constants.CALIBRATION_RESULTS));
            calibrationResultsTable.setSpacingBefore(15);
            calibrationResultsTable.setSpacingAfter(10);

            PdfPTable calibrationReportTable1=createCalibrationReportTable1(parameterMap.get(Constants.CALIBRATION_REPORT1));
            calibrationReportTable1.setSpacingBefore(10);
            calibrationReportTable1.setSpacingAfter(20);

            PdfPTable calibrationReportTable2=createCalibrationReportTable2(null);

            document.add(headerOfReport);
            document.add(certificate_title_table);
            document.add(customerBasicTable);
            document.add(customerCompleteTable);
            document.add(test_equipment_title_table);
            document.add(equipmentTable);
            document.add(instrument_used_title_table);
            document.add(instrumentTable);
            document.add(calibration_results_title_table);
            document.add(calibrationResultsTable);
            document.add(calibration_report_title_table);
            document.add(calibrationReportTable1);
            document.add(calibrationReportTable2);
            document.close();

            Utility.showPopup(Alert.AlertType.CONFIRMATION, "Report Created In Directory");

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return details;
    }
    public static void main(String... args) throws IOException, BadElementException {
       // createHeaderOfReportTable();
    }
    private static PdfPTable createHeaderOfReportTable() throws IOException, DocumentException {

        PdfPTable headerTable=new PdfPTable(3);
        headerTable.setWidths(new float[]{2,4,2.5f});

        String imagePath=new ClassPathResource(
                "/static/justinstruments.png").getPath();
        Image image1 = Image.getInstance(getByte("/static/justinstruments.png"));
        image1.setAlignment(Element.ALIGN_CENTER);
        image1.scaleAbsolute(75, 75);
        headerTable.addCell(getCell(image1));

        PdfPTable addressTable=new PdfPTable(1);

        addressTable.addCell(getCell(new Phrase(Constants.REPORT_TITLE_COMPANY_NAME, FontFactory.getFont(FontFactory.TIMES_ROMAN, 15))));
        addressTable.addCell(getCell(Constants.REPORT_TITLE_COMPANY_ADDRESS,TextAlignment.CENTER,10));
        headerTable.addCell(getCell(addressTable));

        PdfPTable contactsTable=new PdfPTable(1);

        contactsTable.addCell(getCell("  Tel: +1 289.632.3134", TextAlignment.LEFT,8));
        contactsTable.addCell(getCell("       +1 647.834.7829", TextAlignment.LEFT,8));
        contactsTable.addCell(getCell("       +1 877.525.7829", TextAlignment.LEFT,8));
        contactsTable.addCell(getCell("  Fax: +1 905.216.7829", TextAlignment.LEFT,8));
        contactsTable.addCell(getCell("kd@justinstruments.net", TextAlignment.LEFT,8));
        headerTable.addCell(getCell(contactsTable));
        headerTable.setSpacingAfter(25);
        return headerTable;
    }

    private static ReportDetails getFileStream(Map<String, Map<String, String>> parameterMap) throws FileNotFoundException {
        Map<String, String> directoryMap=parameterMap.get(Constants.DIRECTORY);
        String directoryOfPDF=directoryMap.get(Constants.DIRECTORY);
        directoryOfPDF+="/"+directoryMap.get(Constants.CUSTOMER);
        directoryOfPDF=directoryOfPDF.trim();
        File file=new File(directoryOfPDF);
        File generatedDirectory=null;
        String finalPath="";
        generatedDirectory=generateFile(file);
        File[] files=generatedDirectory.listFiles();
        String fileName="";
        if(files!=null&&files.length!=0){
            int latest=getIndexOfLatestFile(files);
            fileName= directoryMap.get(Constants.CUSTOMER)+"_"+latest+".pdf";
            finalPath=generatedDirectory.getPath()+"//"+fileName;
        }else{
            fileName= directoryMap.get(Constants.CUSTOMER)+"_"+1+".pdf";
            finalPath=generatedDirectory.getPath()+"//"+fileName;
        }
        ReportDetails reportDetails=new ReportDetails();
        reportDetails.setFileOutputStream(new FileOutputStream(new File(finalPath)));
        reportDetails.setReportName(fileName);
        reportDetails.setPath(finalPath);
        return reportDetails;
    }

    private static int getIndexOfLatestFile(File[] files) {
        int[] indexes=new int[files.length];
        int i=0;
        for(File fName:files){
            String[] fileIndexes=fName.getName().split("_");
            String strIndex="200";
            if(fileIndexes.length>=2){
                String lastString=fileIndexes[fileIndexes.length-1];
                String[] prefixes=lastString.split("\\.");
                if(prefixes.length==2){
                    strIndex=prefixes[0];
                }
            }
            //fName.getName().substring(fName.getName().length()-5,fName.getName().length()-4);
            indexes[i]= Integer.valueOf(strIndex);
            i++;
        }
        Arrays.sort(indexes);
        int fileNo=indexes[indexes.length-1]+1;
        return fileNo;
    }

    private static File generateFile(File file) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate= formatter.format(date);
        File newFile=new File(file.getPath()+"//"+strDate);
        newFile.mkdirs();
        return newFile;
    }

    private static Paragraph createHeaderTable(String text, boolean b) {
        Paragraph preface=null;
        if(b) {
            preface = new Paragraph(text, new Font(Font.FontFamily.COURIER, 11, Font.BOLD | Font.UNDERLINE));
        }else{
            preface = new Paragraph(text, new Font(Font.FontFamily.COURIER, 11, Font.BOLD ));
        }
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.setSpacingAfter(7);
        return preface;

    }

    private static PdfPCell getCell(String text_in_the_middle, TextAlignment center) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph(text_in_the_middle));
        cell.setPadding(0);

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;

    }

    private static PdfPCell getCell(Phrase element) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(element);
        cell.setPadding(0);

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;

    }

    private static PdfPCell getCell(String text_in_the_middle, TextAlignment center,int size) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph(text_in_the_middle,new Font(Font.FontFamily.COURIER, size, Font.BOLD )));
        cell.setPadding(0);

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;

    }
    private static PdfPCell getCell(Image text_in_the_middle) {
        PdfPCell cell = new PdfPCell();
        cell.addElement(text_in_the_middle);
        cell.setPadding(2);

        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;

    }

    private static PdfPTable createEquipmentTable(Map<String, Map<String, String>> parameterMap) {
        PdfPTable mainTable=new PdfPTable(2);
        PdfPTable firstTable=useGenralLogicToCreateTable(2,parameterMap.get(Constants.EQUIPMENT_1),null);
        PdfPTable secondTable=useGenralLogicToCreateTable(2,parameterMap.get(Constants.EQUIPMENT_2),null);

        mainTable.addCell(getCell(firstTable));
        mainTable.addCell(getCell(secondTable));

        Map<String,String> settingMap=parameterMap.get(Constants.TEMPERATUREANDSETTINGSMAP);
        mainTable.addCell(getTableCell(settingMap.get(Constants.TEMPERATUREANDSETTINGSLABEL)));
        mainTable.addCell(getTableCell(settingMap.get(Constants.TEMPERATUREANDSETTINGSTEXT)));
        return mainTable;
    }

    private static PdfPCell getCell(PdfPTable table) {
        PdfPCell cell =new PdfPCell(table);
        cell.setPadding(0);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static PdfPTable createCalibrationReportTable1(Map<String, String> stringStringMap) {
        PdfPTable passedTable=new PdfPTable(3);
        passedTable.addCell(getTableCell(Constants.PASSED));
        passedTable.addCell(getTableCell(Constants.FAILED));
        passedTable.addCell(getTableCell(Constants.COMMENTS));
        passedTable.addCell(getTableCell(stringStringMap.get(Constants.PASSED)));
        passedTable.addCell(getTableCell(stringStringMap.get(Constants.FAILED)));
        passedTable.addCell(getTableCell(stringStringMap.get(Constants.COMMENTS)));
        return passedTable;
     }
    private static PdfPTable createCalibrationReportTable2(Map<String, String> stringStringMap) throws DocumentException {
        PdfPTable failTable=new PdfPTable(8);
        failTable.setWidths(new float[]{2.5f,2,1,1,2,1.5f,1,1});

        failTable.addCell(getTableCell(Constants.CALIBRATION_BY));
        failTable.addCell(getTableCell(Constants.SIGNEE));
        failTable.addCell(getTableCell(Constants.SIGN));

        Image imageSignee2=null;
        try {
            String imagePathSign1 =new ClassPathResource("/static/SIM.png").getPath();
            Image imageSignee1 = Image.getInstance(getByte("/static/KD.png"));
            //Image imageSignee1 = Image.getInstance(imagePathSign1);
            imageSignee1.setAlignment(Element.ALIGN_CENTER);
            imageSignee1.setScaleToFitHeight(true);
            failTable.addCell(imageSignee1);

            String imagePathSign2 = new ClassPathResource("/static/SIM.png").getPath();
            imageSignee2 = Image.getInstance(getByte("/static/SIM.png"));
            imageSignee2.setAlignment(Element.ALIGN_CENTER);
            imageSignee2.setScaleToFitHeight(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        failTable.addCell(getTableCell(Constants.CHECKED_BY_LABEL));
        failTable.addCell(getTableCell(Constants.SIGNEE_2));
        failTable.addCell(getTableCell(Constants.SIGN));
        failTable.addCell(imageSignee2);
        return failTable;
    }



    private static PdfPTable createCalibrationResultsTable(Map<String, String> stringStringMap) {
        PdfPTable maintable=new PdfPTable(3);
        try {
            maintable.setWidths(new float[]{0.8f,2.6f,2.6f});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        maintable.addCell(getTableCell("% FULL SCALE"));

        PdfPTable headerAsFoundTable=generateCalibrationResultsHeader("AS FOUND");
        maintable.addCell(getCell(headerAsFoundTable));

        PdfPTable headerAsLeftTable=generateCalibrationResultsHeader("AS LEFT");
        maintable.addCell(getCell(headerAsLeftTable));


        PdfPTable firstColumn = new PdfPTable(1);

        for(int i=0;i<101;i=i+25){
            if(i%25==0) {
                firstColumn.addCell(getTableCell(i+""));
            }
        }


        PdfPTable inputColumn=new PdfPTable(1);

        inputColumn.addCell(getTableCell(stringStringMap.get(Constants.FOUND_INPUT_0)));
        inputColumn.addCell(getTableCell(stringStringMap.get(Constants.FOUND_INPUT_25)));
        inputColumn.addCell(getTableCell(stringStringMap.get(Constants.FOUND_INPUT_50)));
        inputColumn.addCell(getTableCell(stringStringMap.get(Constants.FOUND_INPUT_75)));
        inputColumn.addCell(getTableCell(stringStringMap.get(Constants.FOUND_INPUT_100)));

        PdfPTable outputColumnFound=new PdfPTable(1);

        outputColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_OUTPUT_0)));
        outputColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_OUTPUT_25)));
        outputColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_OUTPUT_50)));
        outputColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_OUTPUT_75)));
        outputColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_OUTPUT_100)));


        PdfPTable errorColumnFound=new PdfPTable(1);
        errorColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_ERROR_0)));
        errorColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_ERROR_25)));
        errorColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_ERROR_50)));
        errorColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_ERROR_75)));
        errorColumnFound.addCell(getTableCell(stringStringMap.get(Constants.FOUND_ERROR_100)));

        PdfPTable outputColumnLeft=new PdfPTable(1);
        outputColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LEFT_OUTPUT_0)));
        outputColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LEFT_OUTPUT_25)));
        outputColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LEFT_OUTPUT_50)));
        outputColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LEFT_OUTPUT_75)));
        outputColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LEFT_OUTPUT_100)));

        PdfPTable errorColumnLeft=new PdfPTable(1);
        errorColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LAST_ERROR_0)));
        errorColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LAST_ERROR_25)));
        errorColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LAST_ERROR_50)));
        errorColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LAST_ERROR_75)));
        errorColumnLeft.addCell(getTableCell(stringStringMap.get(Constants.LAST_ERROR_100)));

        PdfPTable foundTable=new PdfPTable(1);
        PdfPTable tempFoundTable=new PdfPTable(3);
        tempFoundTable.addCell(getCell(inputColumn));
        tempFoundTable.addCell(getCell(outputColumnFound));
        tempFoundTable.addCell(getCell(errorColumnFound));


        foundTable.addCell(getCell(tempFoundTable));

        PdfPTable lastTable=new PdfPTable(1);
        PdfPTable tempLastTable=new PdfPTable(3);
        tempLastTable.addCell(getCell(inputColumn));
        tempLastTable.addCell(getCell(outputColumnLeft));
        tempLastTable.addCell(getCell(errorColumnLeft));


        lastTable.addCell(getCell(tempLastTable));

        maintable.addCell(getCell(firstColumn));
        maintable.addCell(getCell(foundTable));
        maintable.addCell(getCell(lastTable));
        return maintable;
    }

    private static PdfPTable createInstrumentTable(Map<String, String> stringStringMap) {
        return useGenralLogicToCreateTable(4,stringStringMap,null);
    }

    private static PdfPTable generateCalibrationResultsHeader(String heading){
        PdfPTable headerAsLeftTable=new PdfPTable(1);

        PdfPCell headingCell=new PdfPCell();
        headingCell.setPhrase(getTableCell(heading));
        headingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerAsLeftTable.addCell(headingCell);
        PdfPTable subHeaderAsLeftTable=new PdfPTable(3);
        subHeaderAsLeftTable.addCell(getTableCell(Constants.INPUT));
        subHeaderAsLeftTable.addCell(getTableCell(Constants.OUTPUT));
        subHeaderAsLeftTable.addCell(getTableCell(Constants.ERROR));
        headerAsLeftTable.addCell(getCell(subHeaderAsLeftTable));

        return headerAsLeftTable;
    }

    private static PdfPTable createCustomerComplete(Map<String, String> parameterMap) throws DocumentException {
        PdfPTable pdfPTable=new PdfPTable(2);
        pdfPTable.setWidths(new float[]{0.6f,3.4f});
        pdfPTable.addCell(getTableCell(Constants.CUSTOMER));
        pdfPTable.addCell(getTableCell(parameterMap.get(Constants.CUSTOMER)));
        pdfPTable.addCell(getTableCell(Constants.ADDRESS));
        pdfPTable.addCell(getTableCell(parameterMap.get(Constants.ADDRESS)));
        pdfPTable.addCell(getTableCell(Constants.PHONE));

        PdfPTable cell6Table=new PdfPTable(5);
        cell6Table.setWidths(new float[]{1,0.6f,1,0.6f,2});
        cell6Table.addCell(getTableCell(parameterMap.get(Constants.PHONE)));
        cell6Table.addCell(getTableCell(Constants.FAX));
        cell6Table.addCell(getTableCell(parameterMap.get(Constants.FAX)));
        cell6Table.addCell(getTableCell(Constants.EMAIL));
        cell6Table.addCell(getTableCell(parameterMap.get(Constants.EMAIL)));
        pdfPTable.addCell(getCell(cell6Table));
        return pdfPTable;
    }

    private static PdfPTable createCustomerBasic(Map<String, String> parameterMap,String tableName) {
        return useGenralLogicToCreateTable(6,parameterMap,tableName);
    }

    private static PdfPTable useGeneralLogicCreateTableWithOnlyValues(Map<String, String> parameterMap){
        PdfPTable pdfPTable=new PdfPTable(1);
        for(Map.Entry<String, String> entry: parameterMap.entrySet()){
            pdfPTable.addCell(getTableCell(entry.getValue()));
        }
        return pdfPTable;
    }
    private static PdfPTable useGenralLogicToCreateTable(int columns, Map<String, String> parameterMap,String tableName){
        PdfPTable pdfTable=new PdfPTable(columns);
        if(tableName!=null&&tableName.equalsIgnoreCase(Constants.CUSTOMER_BASIC)){
            try {
                pdfTable.setWidths(new float[]{1.2f,3f,1,1.2f,1.5f,1.5f});
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        for(Map.Entry<String, String> entry:parameterMap.entrySet()){
            pdfTable.addCell(getTableCell(entry.getKey()));
            pdfTable.addCell(getTableCell(entry.getValue()));
        }
        return pdfTable;
    }

    private static  Phrase getTableCell(String text){
        return new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 8));
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("JUST FILE #", "column  2", "column  3","4","5","6")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(.5f);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static byte[] getByte(String path){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        InputStream is=new PDFReportGenerator().getClass().getResourceAsStream(path);
        int nRead;
        byte[] data = new byte[1638];
        while (true) {
               try {
                       if (!((nRead = is.read(data, 0, data.length)) != -1)) break;
                           buffer.write(data, 0, nRead);
                    } catch (IOException e) {
                          e.printStackTrace();
               }
        }

        return buffer.toByteArray();
    }



}
