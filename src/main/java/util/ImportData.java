package util;



import model.DataSet;
import model.TableView;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ImportData {



    public TableView readExcel(String uri)throws IOException{
        String inputFileName=new File(uri).getName();

        switch (inputFileName.substring(inputFileName.lastIndexOf(".")+1,inputFileName.length())){

            case "xls":
                return readXLS(uri);
            case "xlsx":
                return readXLSX(uri);

        }


        return null;
    }


    public TableView readXLSX(String uri)throws IOException {

        TableView table=new TableView();
        ArrayList<String> etets=new ArrayList<>();

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        table.setDataSet(new DataSet(sheet.getPhysicalNumberOfRows(),row.getPhysicalNumberOfCells()));
        while(itr.hasNext()){
            rowCount++;
            colCount=0;
            Iterator<Cell> cellIterator=row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();

                if (rowCount==0)
                    etets.add(cell.getStringCellValue());

               if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                   table.getDataSet().getData()[rowCount][colCount]=cell.getNumericCellValue();

                   colCount++;
               }

            }
            row=itr.next();

        }
       table.setEntet(etets);


        return table;


    }


    public TableView readXLS(String uri) throws IOException{

        TableView table=new TableView();
        ArrayList<String> entets=new ArrayList<>(
        );

        int rowCount=-1;
        int colCount;

        File file=new File(uri);
        FileInputStream fis=new FileInputStream(file);
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet=wb.getSheetAt(0);

        Iterator<Row> itr=sheet.iterator();
        Row row=itr.next();
        table.setDataSet(new DataSet(sheet.getActiveCell().getRow(),row.getPhysicalNumberOfCells()));
        while(itr.hasNext()){
            rowCount++;
            colCount=0;
            Iterator<Cell> cellIterator=row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell=cellIterator.next();
                if (rowCount==0)
                    entets.add(cell.getStringCellValue());

                if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                    table.getDataSet().getData()[rowCount][colCount]=cell.getNumericCellValue();

                    colCount++;
                }

            }
            row=itr.next();

        }

       table.setEntet(entets);

        return table;
    }

    
}
