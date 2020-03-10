package com.sandu.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xiaobing
 * 2020-02-29 10:34
 */
public enum ExcelUtil {

    INSTANCE;

    public void export(ExcelParam excelParam, HttpServletResponse response) throws IOException {
        if (excelParam.getWidths() == null) {
            excelParam.widths = new int[excelParam.getHeaders().length];
            for (int i = 0; i < excelParam.getHeaders().length; i++) {
                excelParam.widths[i] = excelParam.getWidth();
            }
        }
        if (excelParam.getDs_format() == null) {
            excelParam.ds_format= new int[excelParam.getHeaders().length];
            for (int i = 0; i < excelParam.getHeaders().length; i++) {
                excelParam.getDs_format()[i] = 1;
            }
        }
        //创建一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet = wb.createSheet("excel");
        int rowCount = 0;
        if (excelParam.getHeaders() != null) {
            HSSFRow row = sheet.createRow(rowCount);
            //表头样式
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 11);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < excelParam.getHeaders().length; i++) {
                sheet.setColumnWidth(i, excelParam.widths[i]);
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelParam.getHeaders()[i]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        //表格主体  解析list
        for (int i = 0; i < excelParam.getData().size(); i++) {  //行数
            HSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.getHeaders().length; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.getData().get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //设置文件名
        String fileName = excelParam.getName() + ".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
