/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ReferenceMap extends AbstractReferenceMap implements Serializable {
/*     */   private static final long serialVersionUID = 1555089888138299607L;
/*     */   
/*     */   public ReferenceMap() {
/*  86 */     super(0, 1, 16, 0.75F, false);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType) {
/*  99 */     super(keyType, valueType, 16, 0.75F, false);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, boolean purgeValues) {
/* 114 */     super(keyType, valueType, 16, 0.75F, purgeValues);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor) {
/* 130 */     super(keyType, valueType, capacity, loadFactor, false);
/*     */   }
/*     */   
/*     */   public ReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 149 */     super(keyType, valueType, capacity, loadFactor, purgeValues);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 157 */     out.defaultWriteObject();
/* 158 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 165 */     in.defaultReadObject();
/* 166 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\ReferenceMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */