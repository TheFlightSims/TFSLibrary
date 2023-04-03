/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public class CustomXYURLGenerator implements XYURLGenerator, Serializable {
/*     */   private static final long serialVersionUID = -8565933356596551832L;
/*     */   
/*  65 */   private ArrayList urlSeries = new ArrayList();
/*     */   
/*     */   public int getListCount() {
/*  80 */     return this.urlSeries.size();
/*     */   }
/*     */   
/*     */   public int getURLCount(int list) {
/*  91 */     int result = 0;
/*  92 */     List urls = this.urlSeries.get(list);
/*  93 */     if (urls != null)
/*  94 */       result = urls.size(); 
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public String getURL(int series, int item) {
/* 108 */     String result = null;
/* 109 */     if (series < getListCount()) {
/* 110 */       List urls = this.urlSeries.get(series);
/* 111 */       if (urls != null && 
/* 112 */         item < urls.size())
/* 113 */         result = urls.get(item); 
/*     */     } 
/* 117 */     return result;
/*     */   }
/*     */   
/*     */   public String generateURL(XYDataset dataset, int series, int item) {
/* 130 */     return getURL(series, item);
/*     */   }
/*     */   
/*     */   public void addURLSeries(List urls) {
/* 139 */     this.urlSeries.add(urls);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 151 */     if (o == null)
/* 152 */       return false; 
/* 154 */     if (o == this)
/* 155 */       return true; 
/* 158 */     if (!(o instanceof CustomXYURLGenerator))
/* 159 */       return false; 
/* 161 */     CustomXYURLGenerator generator = (CustomXYURLGenerator)o;
/* 162 */     int listCount = getListCount();
/* 163 */     if (listCount != generator.getListCount())
/* 164 */       return false; 
/* 167 */     for (int series = 0; series < listCount; series++) {
/* 168 */       int urlCount = getURLCount(series);
/* 169 */       if (urlCount != generator.getURLCount(series))
/* 170 */         return false; 
/* 173 */       for (int item = 0; item < urlCount; item++) {
/* 174 */         String u1 = getURL(series, item);
/* 175 */         String u2 = generator.getURL(series, item);
/* 176 */         if (u1 != null) {
/* 177 */           if (!u1.equals(u2))
/* 178 */             return false; 
/* 182 */         } else if (u2 != null) {
/* 183 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 188 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\CustomXYURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */