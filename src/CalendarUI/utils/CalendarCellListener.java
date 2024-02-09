/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.utils;

import java.awt.event.MouseEvent;

/**
 *
 * @author Raven
 */
public interface CalendarCellListener {

    public void cellSelected(MouseEvent evet, int index);

    public void scrollChanged();
}
