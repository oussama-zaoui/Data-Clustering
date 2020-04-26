package sample;



import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImportData {



    public double [][] readExcel(String uri)throws IOException{
        String inputFileName=new File(uri).getName();

        switch (inputFileName.substring(inputFileName.lastIndexOf(".")+1,inputFileName.length())){

            case "xls":
                return readXLS(uri);
            case "xlsx":
                return readXLSX(uri);

        }


        return null;
    }


    public double [][] readXLSX(String uri)throws IOException {

        double [][] data;

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        data=new double[sheet.getPhysicalNumberOfRows()][row.getPhysicalNumberOfCells()];
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


    public double [][] readXLS(String uri) throws IOException{

        double [][] data = new double[100][100];

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet=wb.getSheetAt(0);

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
