package com.helpe.YoHelper.model.response;

import com.helpe.YoHelper.util.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private String name;
    private String lastName;
    private String email;
    private Rol rol;
}
