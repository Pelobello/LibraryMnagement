/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.utils;

import CalendarUI.model.ModelDate;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface PanelDateListener {

    public boolean cellPaint(Graphics2D g2, Rectangle2D rectangle, ModelDate e);
}
