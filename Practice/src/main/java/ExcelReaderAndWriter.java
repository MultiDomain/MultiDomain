import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;

public class ExcelReaderAndWriter {

    public static void main(String[] args) throws Exception {

        File file = new File("Generic/ExcelFiles/execl2003.xls");
        FileInputStream fileInputStream = new FileInputStream(file);

        HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println(data);

        int colSize = sheet1.getRow(0).getLastCellNum();
        int rowSize = sheet1.getLastRowNum();
        System.out.println("Number of records: "+rowSize);

        for(int i = 0; i<rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                String dta = sheet1.getRow(i).getCell(j).getStringCellValue();



                if(j<colSize-1){
                    System.out.print(dta+" | ");
                }else{
                    System.out.println(dta);
                }

            }
        }

        wb.close();
        fileInputStream.close();

//        writing to excel



        File file1 = new File("Generic/ExcelFiles/execl2007.xlsx");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(0).createCell(0).setCellValue("This is entered by java");





    }

}
