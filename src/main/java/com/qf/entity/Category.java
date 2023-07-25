package com.qf.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

//产品分类
public class Category {
   private int categoryId;
   private String categoryName;
   private String categoryIcon;
   private String categoryStatus;
   //实现分类启用和禁用

}