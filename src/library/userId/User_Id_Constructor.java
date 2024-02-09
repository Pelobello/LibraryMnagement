/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.userId;


public class User_Id_Constructor {

    
    public String getUserId() {
        return UserId;
    }

   
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public User_Id_Constructor(String UserId) {
        this.UserId = UserId;
    }

    public User_Id_Constructor() {
        this.UserId ="142";
    }
    private String UserId;
}
