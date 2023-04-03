/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class CaselessStringKeyHashtable extends Hashtable implements Cloneable, Serializable {
/*     */   public CaselessStringKeyHashtable() {}
/*     */   
/*     */   public CaselessStringKeyHashtable(Map t) {
/*  38 */     super(t);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  46 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/*  61 */     return super.containsKey(new CaselessStringKey(key));
/*     */   }
/*     */   
/*     */   public boolean containsKey(CaselessStringKey key) {
/*  75 */     return super.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  87 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */   public Object get(String key) {
/* 103 */     return super.get(new CaselessStringKey(key));
/*     */   }
/*     */   
/*     */   public Object get(CaselessStringKey key) {
/* 118 */     return super.get(key);
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/* 131 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */   public Object put(String key, Object value) {
/* 154 */     if (key == null || value == null)
/* 155 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 158 */     return super.put(new CaselessStringKey(key), value);
/*     */   }
/*     */   
/*     */   public Object put(CaselessStringKey key, Object value) {
/* 179 */     if (key == null || value == null)
/* 180 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 183 */     return super.put(key, value);
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 197 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */   public Object remove(String key) {
/* 211 */     return super.remove(new CaselessStringKey(key));
/*     */   }
/*     */   
/*     */   public Object remove(CaselessStringKey key) {
/* 224 */     return super.remove(key);
/*     */   }
/*     */   
/*     */   public Object remove(Object key) {
/* 237 */     throw new IllegalArgumentException();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\CaselessStringKeyHashtable.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */