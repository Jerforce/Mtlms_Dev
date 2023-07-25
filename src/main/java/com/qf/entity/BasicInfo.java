package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfo {
   private int basicInfoId;
   private String basicInfoName;
   private int basicInfoStatus;
   private List<InfoDetail> infoDetailList;


    public BasicInfo(int i, String basicInfoName, int i1) {
        this.basicInfoId = basicInfoId;
        this.basicInfoName = basicInfoName;
        this.basicInfoStatus = basicInfoStatus;
    }
}
