package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 10:22:38
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private int roleId;
    private String roleName;
    private String roleDesc;
}