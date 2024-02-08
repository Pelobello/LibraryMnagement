/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;


public class ModelDashboardData {

    public String getMonth() {
        return month;
    }

    
    public void setMonth(String month) {
        this.month = month;
    }

    
    public double getTotalAmount() {
        return totalAmount;
    }

   
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    
    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public ModelDashboardData(String month, double totalAmount, double totalQuantity) {
        this.month = month;
        this.totalAmount = totalAmount;
        this.totalQuantity = totalQuantity;
    }

    public ModelDashboardData() {
    }
    private String month;
    private double totalAmount;
    private double totalQuantity;
}
