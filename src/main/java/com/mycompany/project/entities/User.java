package com.mycompany.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

//Define Class attributes OR Table and Columns//
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, name = "userID")
    private int userID;
    @Column(length = 100, name = "userName")
    private String userName;


    @Column(length = 100, name = "userEmail", unique = true)
    private String userEmail;
    @Column(length = 100, name = "userPassword")
    private String userPassword;
    @Column(length = 10, name = "userPhone")
    private String userPhone;
    @Column(length = 1500, name = "userPic")
    private String userPic;
    @Column(length = 1500, name = "userAddress")
    private String userAddress;

    @Column(name = "user_type")
    private String userType;


    // Constructor with all fields
    public User(int userID, String userName, String userEmail, String userPassword, String userPhone, String userPic,
            String userAddress, String userType) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userPic = userPic;
        this.userAddress = userAddress;
        this.userType=userType;
    }

    // Constructor without UserId
    public User(String userName, String userEmail, String userPassword, String userPhone, String userPic,
            String userAddress, String userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userPic = userPic;
        this.userAddress = userAddress;
        this.userType=userType;
    }

    // Empty Constructor
    public User() {
    }

    // Getters and Setters
    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPic() {
        return this.userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // toString methods
    @Override
    public String toString() {
        return "{" +
                " userID='" + getUserID() + "'" +
                ", userName='" + getUserName() + "'" +
                ", userEmail='" + getUserEmail() + "'" +
                ", userPassword='" + getUserPassword() + "'" +
                ", userPhone='" + getUserPhone() + "'" +
                ", userPic='" + getUserPic() + "'" +
                ", userAddress='" + getUserAddress() + "'" +
                "}";
    }

}
