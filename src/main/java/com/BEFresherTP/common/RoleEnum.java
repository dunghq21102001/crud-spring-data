package com.BEFresherTP.common;

public class RoleEnum {
    final private String ADMIN;
    final private String MANAGER;
    final private String STAFF;
    final private String COMMON_USER;

    public RoleEnum() {
        ADMIN = "ADMIN";
        MANAGER = "MANAGER";
        STAFF = "STAFF";
        COMMON_USER = "COMMON_USER";
    }

    public String getADMIN() {
        return ADMIN;
    }

    public String getMANAGER() {
        return MANAGER;
    }

    public String getSTAFF() {
        return STAFF;
    }

    public String getCOMMON_USER() {
        return COMMON_USER;
    }
}
