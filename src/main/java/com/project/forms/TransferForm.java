package com.project.forms;

import lombok.Data;

@Data
public class TransferForm {
    private String userBankName;
    private String recipientBankName;
    private String recipientName;
    private String accountTo;
    private String amount;
}
