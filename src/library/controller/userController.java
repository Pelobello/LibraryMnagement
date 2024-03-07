/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.controller;

import library.model.ModelUserData;


public class userController {
    AddUserController DAO;
    public userController() {
        this.DAO = new AddUserController();
    }
    public boolean registerUser(ModelUserData data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassWord()));
        data.setPassWord(encryptedPassword.toCharArray());
        return DAO.addUserToDatabase(data);
    }
    public boolean changePasswordUser(ModelUserData data){
        String encryptPassword = DAO.encryptPass(new String(data.getNewPassword()));
        data.setNewPassword(encryptPassword.toCharArray());
        String encryptPasswordv2 = DAO.encryptPass(new String(data.getPassWord()));
        data.setPassWord(encryptPasswordv2.toCharArray());       
        return DAO.updatePassword(data);
    }
    public ModelUserData LogIn(ModelUserData data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassWord()));
        data.setPassWord(encryptedPassword.toCharArray());
        return this.DAO.Sign_In(data);
    }
}
