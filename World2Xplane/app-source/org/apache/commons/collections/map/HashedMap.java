/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class HashedMap extends AbstractHashedMap implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = -1788199231038721040L;
/*     */   
/*     */   public HashedMap() {
/*  54 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */   public HashedMap(int initialCapacity) {
/*  64 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public HashedMap(int initialCapacity, float loadFactor) {
/*  77 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public HashedMap(Map map) {
/*  87 */     super(map);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*  97 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 104 */     out.defaultWriteObject();
/* 105 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 112 */     in.defaultReadObject();
/* 113 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\HashedMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */