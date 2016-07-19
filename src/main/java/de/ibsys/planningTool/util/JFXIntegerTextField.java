package de.ibsys.planningTool.util;

import com.jfoenix.controls.JFXTextField;

/**
 *
 * Created by minhnguyen on 18.07.16.
 */
public class JFXIntegerTextField extends JFXTextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (validate(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*");
    }
}
