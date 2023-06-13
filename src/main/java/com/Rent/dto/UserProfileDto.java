package com.Rent.dto;

public class UserProfileDto {

    private String name;
    private String email;
    private byte[] picture;

    public UserProfileDto() {
    }

    public UserProfileDto(String name, String email, byte[] picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
