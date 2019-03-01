package com.project.services;

import com.project.forms.BankForm;
import com.project.forms.UserForm;

public interface SignUpService {
    boolean isMalePresent(String mail);
    void signUp(UserForm userForm);
    void signUp(BankForm bankForm);
}
