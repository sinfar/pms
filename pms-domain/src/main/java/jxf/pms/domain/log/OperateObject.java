package jxf.pms.domain.log;

public enum OperateObject {
    user("用户"),
    role("角色");

    public String name;

    OperateObject(String name) {
        this.name = name;
    }
}
