package io.github.xinshepherd.springbootdemo;

import io.github.xinshepherd.excel.core.FontStyle;

/**
 * @author Fuxin
 * @since 2020/1/17 18:06
 */
public class CustomFontStyle implements FontStyle {

    @Override
    public boolean getItalic() {
        return true;
    }

    @Override
    public boolean getBold() {
        return true;
    }

    @Override
    public short getFontHeight() {
        return 256;
    }

    @Override
    public boolean getStrikeout() {
        return true;
    }
}
