package sample;



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImportData {


    public double [][] readXLS(String uri)throws IOException {

        double [][] data = new double[0][];

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();





        return data;


    }
    
}
