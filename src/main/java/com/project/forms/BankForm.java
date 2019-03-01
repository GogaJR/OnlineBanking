package com.project.forms;

import lombok.Data;

@Data
public class BankForm {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String fax;
    private String workingHours;
    protected String mail;
    protected String password;
    private String daysOff;
    private String account;
}
