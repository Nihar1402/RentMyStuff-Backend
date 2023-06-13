package com.Rent.dto;

public class UserProfile {
    private int useid;
    private int adressid;
    private String userName;
    private String email;
    private String userPhone;
    private String password;
    private byte[]  userPic;

    private String city="Indore";
    private String state;
    private String pincode;
    private String userAddress;

    public UserProfile() {
    }

    public UserProfile(int useid, int adressid, String userName, String email, String userPhone, String password, byte[] userPic, String city, String state, String pincode, String userAddress) {
        this.useid = useid;
        this.adressid = adressid;
        this.userName = userName;
        this.email = email;
        this.userPhone = userPhone;
        this.password = password;
        this.userPic = userPic;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.userAddress = userAddress;
    }

    public UserProfile(String userName, String email, String userPhone, String password, byte[] userPic, String city, String state, String pincode, String userAddress) {
        this.userName = userName;
        this.email = email;
        this.userPhone = userPhone;
        this.password = password;
        this.userPic = userPic;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.userAddress = userAddress;
    }

    public int getUseid() {
        return useid;
    }

    public void setUseid(int useid) {
        this.useid = useid;
    }

    public int getAdressid() {
        return adressid;
    }

    public void setAdressid(int adressid) {
        this.adressid = adressid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getUserPic() {
        return userPic;
    }

    public void setUserPic(byte[] userPic) {
        this.userPic = userPic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
