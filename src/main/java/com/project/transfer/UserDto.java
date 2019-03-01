package com.project.transfer;

import com.project.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String surname;

    public static UserDto from(User user) {
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
