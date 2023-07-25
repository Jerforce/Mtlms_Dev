package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
public class InfoDetail {
   private int infoDetailId;
   private String infoDetailName;
   private String infoDetailDesc;
   private int fkBasicInfoId;
   public InfoDetail() {
   }

   public InfoDetail(int infoDetailId, String infoDetailName, String infoDetailDesc) {
      this.infoDetailId = infoDetailId;
      this.infoDetailName = infoDetailName;
      this.infoDetailDesc = infoDetailDesc;
   }

   /**
    * 获取
    * @return infoDetailId
    */
   public int getInfoDetailId() {
      return infoDetailId;
   }

   /**
    * 设置
    * @param infoDetailId
    */
   public void setInfoDetailId(int infoDetailId) {
      this.infoDetailId = infoDetailId;
   }

   /**
    * 获取
    * @return infoDetailName
    */
   public String getInfoDetailName() {
      return infoDetailName;
   }

   /**
    * 设置
    * @param infoDetailName
    */
   public void setInfoDetailName(String infoDetailName) {
      this.infoDetailName = infoDetailName;
   }

   /**
    * 获取
    * @return infoDetailDesc
    */
   public String getInfoDetailDesc() {
      return infoDetailDesc;
   }

   /**
    * 设置
    * @param infoDetailDesc
    */
   public void setInfoDetailDesc(String infoDetailDesc) {
      this.infoDetailDesc = infoDetailDesc;
   }

   public String toString() {
      return "InfoDetail{infoDetailId = " + infoDetailId + ", infoDetailName = " + infoDetailName + ", infoDetailDesc = " + infoDetailDesc + "}";
   }



}
