package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private String mgrId;
    private String loginName;
    private String loginPwd;
    private String mgrName;
    private String mgrGender;
    private String mgrTel;
    private String mgrEmail;
    private String mgrQQ;
    private Date createTime;
}
