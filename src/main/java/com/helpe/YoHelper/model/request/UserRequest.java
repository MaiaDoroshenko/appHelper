package com.helpe.YoHelper.model.request;

import com.helpe.YoHelper.util.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Rol rol;
}

