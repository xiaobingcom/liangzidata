package com.sandu.common.util;

import java.util.List;

/**
 * @author xiaobing
 * 2020-02-29 10:34
 */
public class ExcelParam {
    String name;
    int width;
    String font;
    String[] headers;
    /**
     * 导出数据的样式
     * 1:String left;
     * 2:String center
     * 3:String right
     * 4 int  right
     * 5:float ###,###.## right
     * 6:number: #.00% 百分比 right
     */
    public int[] ds_format;
    /**
     * 每列表格的宽度,默认为256 * 14
     */
    public int[] widths;
    List<String[]> data;

    private ExcelParam() {

    }
    //使用Builder模式，设置默认参数和必填参数
    public static class Builder {
        String name;
        int width = 256 * 14;
        String font = "微软雅黑";
        String[] headers;
        int[] ds_format;
        int[] widths;
        List<String[]> data;

        public Builder(String name) {
            this.name = name;
        }

        public Builder font(String font) {
            this.font = font;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder headers(String[] headers) {
            this.headers = headers;
            return this;
        }

        public Builder ds_format(int[] ds_format) {
            this.ds_format = ds_format;
            return this;
        }

        public Builder widths(int[] widths) {
            this.widths = widths;
            return this;
        }

        public Builder data(List<String[]> data) {
            this.data = data;
            return this;
        }

        public ExcelParam build() {
            ExcelParam excelParam = new ExcelParam();
            excelParam.name = this.name;
            excelParam.data = this.data;
            excelParam.widths = this.widths;
            excelParam.ds_format = this.ds_format;
            excelParam.headers = this.headers;
            excelParam.font = this.font;
            excelParam.width = this.width;
            return excelParam;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public int[] getDs_format() {
        return ds_format;
    }

    public void setDs_format(int[] ds_format) {
        this.ds_format = ds_format;
    }

    public int[] getWidths() {
        return widths;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
