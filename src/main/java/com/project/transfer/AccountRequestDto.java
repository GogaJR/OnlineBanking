package com.project.transfer;

import com.project.models.AccountRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequestDto {
    private Long userId;
    private String userName;
    private String userSurname;
    private String cardId;

    public static AccountRequestDto from(AccountRequest request) {
        return AccountRequestDto.builder()
                .userId(request.getUser().getId())
                .userName(request.getUser().getName())
                .userSurname(request.getUser().getSurname())
                .cardId(request.getCardId())
                .build();
    }
}
