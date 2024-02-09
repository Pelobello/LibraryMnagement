/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.swing;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public interface DynamicCellRender<E> {

    public void paintBackground(Graphics2D g2, DynamicCell<E> dynamicCell, Rectangle rectangle);

    public void paintCell(Graphics2D g2, Rectangle2D rectangle, E e);

    public void paint(Graphics2D g2, Rectangle rectangle);

    public E next(E last);

    public E previous(E first);
}
