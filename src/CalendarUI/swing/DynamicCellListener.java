/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.swing;

import java.awt.event.MouseEvent;

public interface DynamicCellListener {

    public void scrollChanged(boolean scrollNext);

    public void mouseSelected(MouseEvent mouse);
}
