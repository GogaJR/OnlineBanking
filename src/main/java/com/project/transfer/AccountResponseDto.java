package com.project.transfer;

import com.project.models.AccountResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
    private String bankName;
    private String response;

    public static AccountResponseDto from(AccountResponse response) {
        return AccountResponseDto.builder()
                .bankName(response.getBank().getName())
                .response(response.getResponse().name().toLowerCase())
                .build();
    }
}
