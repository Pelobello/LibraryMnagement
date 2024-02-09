/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalendarUI.utils;

import CalendarUI.model.ModelDate;
import java.awt.event.MouseEvent;


public interface CalendarSelectedListener {

    public void selected(MouseEvent evt, ModelDate date);
}
