package io.github.xinshepherd.springbootdemo;

import io.github.xinshepherd.excel.core.CellStyleProcessor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.Objects;

import static org.apache.poi.ss.usermodel.IndexedColors.ROSE;

/**
 * @author Fuxin
 * @since 2020/1/19 17:56
 */
public class CustomCellStyleProcessor implements CellStyleProcessor {
    private static final String NONE = "NONE";
    private static final String ADULT = "ADULT";
    private static final String CHILD = "CHILD";

    @Override
    public String getLabel(Object data) {
        if (Objects.isNull(data))
            return NONE;
        Integer age = (Integer) data;
        return age < 18 ? CHILD : ADULT;
    }

    @Override
    public CellStyle customize(CellStyle cellStyle, String label) {
        if (ADULT.equals(label)) {
            cellStyle.setFillForegroundColor(ROSE.index);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return cellStyle;
    }
}
