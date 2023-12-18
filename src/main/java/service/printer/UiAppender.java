package service.printer;

import ui.LichKingUi;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;

public class UiAppender {

    public void appendToPane(LichKingUi ui, String msg, Color c) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = ui.getConsoleTextPane().getDocument().getLength();
        ui.getConsoleTextPane().setCaretPosition(len);
        ui.getConsoleTextPane().setCharacterAttributes(attributeSet, false);
        ui.getConsoleTextPane().replaceSelection(msg);
    }
}
