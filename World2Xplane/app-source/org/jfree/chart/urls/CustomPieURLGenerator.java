/*     */ package org.jfree.chart.urls;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CustomPieURLGenerator implements PieURLGenerator, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 7100607670144900503L;
/*     */   
/*  74 */   private ArrayList urls = new ArrayList();
/*     */   
/*     */   public String generateURL(PieDataset dataset, Comparable key, int pieIndex) {
/*  88 */     return getURL(key, pieIndex);
/*     */   }
/*     */   
/*     */   public int getListCount() {
/*  97 */     return this.urls.size();
/*     */   }
/*     */   
/*     */   public int getURLCount(int list) {
/* 109 */     int result = 0;
/* 110 */     Map urlMap = this.urls.get(list);
/* 111 */     if (urlMap != null)
/* 112 */       result = urlMap.size(); 
/* 114 */     return result;
/*     */   }
/*     */   
/*     */   public String getURL(Comparable key, int pieItem) {
/* 127 */     String result = null;
/* 129 */     if (pieItem < getListCount()) {
/* 130 */       Map urlMap = this.urls.get(pieItem);
/* 131 */       if (urlMap != null)
/* 132 */         result = (String)urlMap.get(key); 
/*     */     } 
/* 136 */     return result;
/*     */   }
/*     */   
/*     */   public void addURLs(Map urlMap) {
/* 145 */     this.urls.add(urlMap);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 157 */     if (o == this)
/* 158 */       return true; 
/* 161 */     if (o instanceof CustomPieURLGenerator) {
/* 162 */       CustomPieURLGenerator generator = (CustomPieURLGenerator)o;
/* 163 */       if (getListCount() != generator.getListCount())
/* 164 */         return false; 
/* 167 */       for (int pieItem = 0; pieItem < getListCount(); pieItem++) {
/* 168 */         if (getURLCount(pieItem) != generator.getURLCount(pieItem))
/* 169 */           return false; 
/* 171 */         Set keySet = ((HashMap)this.urls.get(pieItem)).keySet();
/* 173 */         for (Iterator i = keySet.iterator(); i.hasNext(); ) {
/* 174 */           String key = i.next();
/* 175 */           if (!getURL(key, pieItem).equals(generator.getURL(key, pieItem)))
/* 177 */             return false; 
/*     */         } 
/*     */       } 
/* 181 */       return true;
/*     */     } 
/* 183 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 194 */     CustomPieURLGenerator urlGen = new CustomPieURLGenerator();
/* 199 */     for (Iterator i = this.urls.iterator(); i.hasNext(); ) {
/* 200 */       Map map = i.next();
/* 202 */       Map newMap = new HashMap();
/* 203 */       for (Iterator j = map.keySet().iterator(); j.hasNext(); ) {
/* 204 */         String key = j.next();
/* 205 */         newMap.put(key, map.get(key));
/*     */       } 
/* 208 */       urlGen.addURLs(newMap);
/* 209 */       newMap = null;
/*     */     } 
/* 212 */     return urlGen;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\char\\urls\CustomPieURLGenerator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */