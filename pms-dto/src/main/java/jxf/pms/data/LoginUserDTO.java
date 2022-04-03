package jxf.pms.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginUserDTO implements Serializable {
    private Integer id;
    private String name;
}
