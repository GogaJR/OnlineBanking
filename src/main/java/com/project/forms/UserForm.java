package com.project.forms;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String surname;
    private String sex;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    protected String mail;
    protected String password;
    private String placeOfBirth;
    private String placeOfLiving;
    private String passportSerialNumber;
}
