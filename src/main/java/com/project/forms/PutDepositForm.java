package com.project.forms;

import lombok.Data;

@Data
public class PutDepositForm {
    private String bankName;
    private String depositName;
    private String depositAmount;
    private String numberOfMonths;
}
