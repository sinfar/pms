package jxf.pms.domain.log;

public enum ActionType {
    create("创建"),
    update("修改"),
    delete("删除"),
    disabled("禁用"),
    enabled("启用"),
    resetpassword("重置密码");

    public String name;

    ActionType(String name) {
        this.name = name;
    }
}
