package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

//品牌
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
   private int brandId;
   private String brandName;
   private String brandLogo;
   private String brandDesc;
   private Date createTime;
   private int brandStatus;
}
