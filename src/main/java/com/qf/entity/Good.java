package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//tb-good表【商品表】
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Good {
   private int goodsId;
   private String goodsName;
   private String goodsImg;
   private int goodsCost;
   private int goodsMinPrice;
}
