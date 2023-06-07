package api.utilities;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;


    String path;
    public XLUtility(String path)
    {
        this.path=path;
    }

    public int getRowCount(String SheetName) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(SheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String SheetName,int rownum ) throws IOException
    {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(SheetName);
        row=sheet.getRow(rownum);
        int cellcount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String SheetName,int rownum, int column) throws IOException
    {
        fi= new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(SheetName);
        row=sheet.getRow(rownum);
        cell=row.getCell(column);
        DataFormatter objDefaultFormat = new DataFormatter();

        String data;
        try
        {
            data=objDefaultFormat.formatCellValue(cell);//returns the formatted value of cell
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String SheetName,int rownum, int column,String Data) throws IOException {
        File xlfile = new File(path); //if file not exist create new file
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }

        fi = new FileInputStream(path);
           workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(SheetName) == 1)//if file not exist create new file
            workbook.createSheet(SheetName);
        sheet = workbook.getSheet(SheetName);

        if (sheet.getRow(rownum) == null)//if row not exist create new row
            sheet.createRow(rownum);
        row = sheet.getRow(rownum);

        cell = row.createCell(column);
        cell.setCellValue(Data);
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }




    }
