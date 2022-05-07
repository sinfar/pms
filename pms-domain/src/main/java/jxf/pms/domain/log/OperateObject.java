package jxf.pms.domain.log;

public enum OperateObject {
    project("项目"),
    requirement("需求"),
    plan("计划"),
    task("任务"),
    module("模块"),

    testCase("测试用例"),
    bug("bug"),

    user("用户"),
    role("角色");

    public String name;

    OperateObject(String name) {
        this.name = name;
    }
}
