package Test;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.Test;

import java.io.FileOutputStream;

public class GenerateExcelTest {
    @Test
    public void generateExcel() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Add feed");
        String[][] data = {
            {"username", "password", "expectedMessage"},
            {"standard_user", "secret_sauce", "success"},
            {"locked_out_user", "secret_sauce", "locked out"},
            {"standard_user", "wrong_password", "do not match"},
            {"EMPTY", "secret_sauce", "Username is required"},
            {"standard_user", "EMPTY", "Password is required"}
        };
        for (int i = 0; i < data.length; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < data[i].length; j++) {
                row.createCell(j).setCellValue(data[i][j]);
            }
        }
        java.io.File file = new java.io.File("src/test/resources/TestData");
        file.mkdirs();
        FileOutputStream fos = new FileOutputStream("src/test/resources/TestData/onsite.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();
        System.out.println("Excel generated successfully!");
    }
}
