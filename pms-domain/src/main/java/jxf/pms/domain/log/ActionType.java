package jxf.pms.domain.log;

public enum ActionType {
    start("开始"),
    hang("挂起"),
    close("关闭"),
    active("激活"),
    pause("暂停"),
    block("阻塞"),
    finish("完成"),
    cancel("取消"),
    resolve("解决"),

    ref_bug("关联bug"),
    ref_testcase("关联bug"),
    ref_req("关联需求"),

    review("评审"),

    create("创建"),
    update("修改"),
    delete("删除"),
    disabled("禁用"),
    enabled("启用"),
    resetpassword("重置密码"),
    addExec("添加执行结果"),

    rename("重命名");

    public String name;

    ActionType(String name) {
        this.name = name;
    }
}
