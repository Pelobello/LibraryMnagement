/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.utils;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Raven
 * @param <E>
 */
public interface CalendarEventCellRender<E> {

    public void paint(Graphics2D g2, Rectangle2D rectangle2D, boolean isSelected, E value);
}
