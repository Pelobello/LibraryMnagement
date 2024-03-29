/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.calendar;

import CalendarUI.swing.DynamicCell;
import CalendarUI.swing.DynamicCellListener;
import CalendarUI.utils.CalendarCellListener;
import CalendarUI.utils.CellRenderCustom;
import com.formdev.flatlaf.FlatClientProperties;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.SwingUtilities;

public class PanelYear extends DynamicCell<Integer> {

    public void setYear(int year) {
        init(year - 4);
    }

    private final Point mouse = new Point();
    private CalendarCellListener calendarCellListener;

    public PanelYear() {
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:if($Calendar.background,$Calendar.background,$Panel.background)");
        setColumn(4);
        setRow(5);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouse.setLocation(e.getPoint());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouse.setLocation(e.getPoint());
                repaint();
            }
        };
        DynamicCellListener dynamicCellListener = new DynamicCellListener() {
            @Override
            public void scrollChanged(boolean scrollNext) {
                calendarCellListener.scrollChanged();
            }

            @Override
            public void mouseSelected(MouseEvent mouse) {
                if (SwingUtilities.isLeftMouseButton(mouse)) {
                    calendarCellListener.cellSelected(mouse, getSelectedIndex());
                }
            }
        };
        addEventDynamicCellListenter(dynamicCellListener);
        addMouseMotionListener(mouseAdapter);
        setDynamicCellRender(new CellRenderCustom<Integer>(mouse) {
            @Override
            public void paintCell(Graphics2D g2, Rectangle2D rectangle, Integer e) {
                FontMetrics fm = g2.getFontMetrics();
                String text = e + "";
                Rectangle2D fr = fm.getStringBounds(text, g2);
                float x = (float) ((rectangle.getWidth() - fr.getWidth()) / 2f);
                float y = (float) (((rectangle.getHeight() - fr.getHeight()) / 2) + fm.getAscent());
                g2.setColor(getForeground());
                g2.drawString(text, x, y);
            }

            @Override
            public Integer next(Integer last) {
                return last + 1;
            }

            @Override
            public Integer previous(Integer first) {
                return first - 1;
            }
        });
    }

    public CalendarCellListener getCalendarCellListener() {
        return calendarCellListener;
    }

    public void setCalendarCellListener(CalendarCellListener calendarCellListener) {
        this.calendarCellListener = calendarCellListener;
    }

    public String getYear() {
        int start = getModels().get(0);
        int end = getModels().get(getModels().size() - 1);
        return start + " - " + end;
    }
}
