package util;



import model.DataSet;
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



    public DataSet readExcel(String uri)throws IOException{
        String inputFileName=new File(uri).getName();

        switch (inputFileName.substring(inputFileName.lastIndexOf(".")+1,inputFileName.length())){

            case "xls":
                return readXLS(uri);
            case "xlsx":
                return readXLSX(uri);

        }


        return null;
    }


    public DataSet readXLSX(String uri)throws IOException {

        DataSet data;

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        row=itr.next();
        data=new DataSet(sheet.getPhysicalNumberOfRows(),row.getPhysicalNumberOfCells());
        while(itr.hasNext()){
            rowCount++;
            colCount=0;
            Iterator<Cell> cellIterator=row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();

               if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                   data.getData()[rowCount][colCount]=cell.getNumericCellValue();

                   colCount++;
               }

            }
            row=itr.next();

        }



        return data;


    }


    public DataSet readXLS(String uri) throws IOException{

        DataSet data;

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        data=new DataSet(sheet.getActiveCell().getRow(),row.getPhysicalNumberOfCells());
        while(itr.hasNext()){
            rowCount++;
            colCount=0;
            Iterator<Cell> cellIterator=row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();

                if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    data.getData()[rowCount][colCount]=cell.getNumericCellValue();

                    colCount++;
                }

            }
            row=itr.next();

        }



        return data;
    }

    
}
