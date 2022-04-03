package jxf.pms.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleDTO {
    private Integer id;
    private String name;
}
