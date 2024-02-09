
package library.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class TextPane extends JTextPane{

    public TextPane() {
        setOpaque(true);
        setBackground(new Color(15,4,76,255));
        Color colorborder = new Color(15,4,76,255);
       setBorder(new LineBorder(colorborder));
       
         Font poppinsFont = new Font("Poppins", Font.BOLD, 18); 
        setFont(poppinsFont);
         StyledDocument doc = getStyledDocument();
        SimpleAttributeSet centerAlign = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlign, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), centerAlign, false);
        
    }
 
}
