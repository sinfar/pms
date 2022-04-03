package jxf.pms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResultDTO {
    private  LoginUserDTO user;
    private String token;
}
