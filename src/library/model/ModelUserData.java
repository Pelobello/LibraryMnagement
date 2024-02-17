/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.model;

import java.util.Date;


public class ModelUserData {

    /**
     * @return the passWord
     */
    public char[] getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(char[] passWord) {
        this.passWord = passWord;
    }

   
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getLibraryName() {
        return libraryName;
    }

  
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

  
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ModelUserData() {
    }

    public ModelUserData(String userId, String libraryName, String userName, char[] passWord, Date birthDate) {
        this.userId = userId;
        this.libraryName = libraryName;
        this.userName = userName;
        this.passWord = passWord;
        this.birthDate = birthDate;
    }

  
   

    private String userId;
    private String libraryName;
    private String userName;
    private char [] passWord;
    private Date birthDate;
}
