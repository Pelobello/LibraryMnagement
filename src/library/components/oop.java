/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class oop extends JPanel{

    public oop() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
         Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        
        GradientPaint gra = new GradientPaint(0, 0, new Color(33,105,249), getWidth(), 0, new Color(93,58,196));
        g2.setPaint(gra);
        super.paintComponent(g); 
    }

    
    
}
