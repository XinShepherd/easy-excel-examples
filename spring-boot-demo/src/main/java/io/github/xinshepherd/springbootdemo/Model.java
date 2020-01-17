package io.github.xinshepherd.springbootdemo;

import io.github.xinshepherd.excel.annotation.Excel;
import io.github.xinshepherd.excel.annotation.ExcelBigHead;
import io.github.xinshepherd.excel.annotation.ExcelField;
import lombok.Data;

import java.util.Date;

/**
 * @author Fuxin
 * @since 2020/1/17 18:05
 */
@Data
@ExcelBigHead(value = "Just an example", fontStyle = CustomFontStyle.class)
@Excel(value = "Summary",
        headerColor = 0x0D,
        herderHigh = 1024,
        rowHigh = 512,
        fontStyle = CustomFontStyle.class)
public class Model {

    @ExcelField("Name")
    private String name;

    @ExcelField(value = "Birthday", type = ExcelField.CellType.DATE, width = 50, headerColor = 0x0C)
    private Date birthDate;

    @ExcelField(value = "Age", type = ExcelField.CellType.NUMERIC)
    private Integer age;

    @ExcelField(value = "Time", type = ExcelField.CellType.DATE, datePattern = "h:mm:ss")
    private Date time;

    @ExcelField(value = "Excel Time", type = ExcelField.CellType.DATE, datePattern = "h:mm:ss")
    private double excelTime;

}
