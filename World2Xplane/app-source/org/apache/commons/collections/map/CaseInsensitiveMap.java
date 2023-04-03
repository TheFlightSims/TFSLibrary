/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CaseInsensitiveMap extends AbstractHashedMap implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = -7074655917369299456L;
/*     */   
/*     */   public CaseInsensitiveMap() {
/*  69 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */   public CaseInsensitiveMap(int initialCapacity) {
/*  79 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public CaseInsensitiveMap(int initialCapacity, float loadFactor) {
/*  92 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public CaseInsensitiveMap(Map map) {
/* 106 */     super(map);
/*     */   }
/*     */   
/*     */   protected Object convertKey(Object key) {
/* 120 */     if (key != null)
/* 121 */       return key.toString().toLowerCase(); 
/* 123 */     return AbstractHashedMap.NULL;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 134 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 141 */     out.defaultWriteObject();
/* 142 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 149 */     in.defaultReadObject();
/* 150 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\CaseInsensitiveMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */