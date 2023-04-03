/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultWindDataset extends AbstractXYDataset implements WindDataset {
/*     */   private List seriesKeys;
/*     */   
/*     */   private List allSeriesData;
/*     */   
/*     */   public DefaultWindDataset() {
/*  72 */     this.seriesKeys = new ArrayList();
/*  73 */     this.allSeriesData = new ArrayList();
/*     */   }
/*     */   
/*     */   public DefaultWindDataset(Object[][][] data) {
/*  82 */     this(seriesNameListFromDataArray((Object[][])data), data);
/*     */   }
/*     */   
/*     */   public DefaultWindDataset(String[] seriesNames, Object[][][] data) {
/*  92 */     this(Arrays.asList(seriesNames), data);
/*     */   }
/*     */   
/*     */   public DefaultWindDataset(List seriesKeys, Object[][][] data) {
/* 114 */     this.seriesKeys = seriesKeys;
/* 115 */     int seriesCount = data.length;
/* 116 */     this.allSeriesData = new ArrayList(seriesCount);
/* 118 */     for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
/* 119 */       List oneSeriesData = new ArrayList();
/* 120 */       int maxItemCount = (data[seriesIndex]).length;
/* 121 */       for (int itemIndex = 0; itemIndex < maxItemCount; itemIndex++) {
/* 122 */         Object xObject = data[seriesIndex][itemIndex][0];
/* 123 */         if (xObject != null) {
/*     */           Number xNumber;
/* 125 */           if (xObject instanceof Number) {
/* 126 */             xNumber = (Number)xObject;
/* 129 */           } else if (xObject instanceof Date) {
/* 130 */             Date xDate = (Date)xObject;
/* 131 */             xNumber = new Long(xDate.getTime());
/*     */           } else {
/* 134 */             xNumber = new Integer(0);
/*     */           } 
/* 137 */           Number windDir = (Number)data[seriesIndex][itemIndex][1];
/* 138 */           Number windForce = (Number)data[seriesIndex][itemIndex][2];
/* 139 */           oneSeriesData.add(new WindDataItem(xNumber, windDir, windForce));
/*     */         } 
/*     */       } 
/* 144 */       Collections.sort(oneSeriesData);
/* 145 */       this.allSeriesData.add(seriesIndex, oneSeriesData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 156 */     return this.allSeriesData.size();
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 167 */     List oneSeriesData = this.allSeriesData.get(series);
/* 168 */     return oneSeriesData.size();
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 179 */     return this.seriesKeys.get(series).toString();
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 193 */     List oneSeriesData = this.allSeriesData.get(series);
/* 194 */     WindDataItem windItem = oneSeriesData.get(item);
/* 195 */     return windItem.getX();
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 209 */     return getWindForce(series, item);
/*     */   }
/*     */   
/*     */   public Number getWindDirection(int series, int item) {
/* 222 */     List oneSeriesData = this.allSeriesData.get(series);
/* 223 */     WindDataItem windItem = oneSeriesData.get(item);
/* 224 */     return windItem.getWindDirection();
/*     */   }
/*     */   
/*     */   public Number getWindForce(int series, int item) {
/* 237 */     List oneSeriesData = this.allSeriesData.get(series);
/* 238 */     WindDataItem windItem = oneSeriesData.get(item);
/* 239 */     return windItem.getWindForce();
/*     */   }
/*     */   
/*     */   public static List seriesNameListFromDataArray(Object[][] data) {
/* 250 */     int seriesCount = data.length;
/* 251 */     List seriesNameList = new ArrayList(seriesCount);
/* 252 */     for (int i = 0; i < seriesCount; i++)
/* 253 */       seriesNameList.add("Series " + (i + 1)); 
/* 255 */     return seriesNameList;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\DefaultWindDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */