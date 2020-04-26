package sample;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImportData {


    public double [][] readXLS(String uri)throws IOException {

        double [][] data = new double[100][100];

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        while(itr.hasNext()){
            rowCount++;
            colCount=0;
            Iterator<Cell> cellIterator=row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();

               if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                   data[rowCount][colCount]=cell.getNumericCellValue();

                   colCount++;
               }

            }
            row=itr.next();

        }



        return data;


    }
    
}
