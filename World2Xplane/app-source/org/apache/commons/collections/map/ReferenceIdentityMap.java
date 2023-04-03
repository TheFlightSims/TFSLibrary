/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.ref.Reference;
/*     */ 
/*     */ public class ReferenceIdentityMap extends AbstractReferenceMap implements Serializable {
/*     */   private static final long serialVersionUID = -1266190134568365852L;
/*     */   
/*     */   public ReferenceIdentityMap() {
/*  83 */     super(0, 1, 16, 0.75F, false);
/*     */   }
/*     */   
/*     */   public ReferenceIdentityMap(int keyType, int valueType) {
/*  96 */     super(keyType, valueType, 16, 0.75F, false);
/*     */   }
/*     */   
/*     */   public ReferenceIdentityMap(int keyType, int valueType, boolean purgeValues) {
/* 111 */     super(keyType, valueType, 16, 0.75F, purgeValues);
/*     */   }
/*     */   
/*     */   public ReferenceIdentityMap(int keyType, int valueType, int capacity, float loadFactor) {
/* 126 */     super(keyType, valueType, capacity, loadFactor, false);
/*     */   }
/*     */   
/*     */   public ReferenceIdentityMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues) {
/* 144 */     super(keyType, valueType, capacity, loadFactor, purgeValues);
/*     */   }
/*     */   
/*     */   protected int hash(Object key) {
/* 157 */     return System.identityHashCode(key);
/*     */   }
/*     */   
/*     */   protected int hashEntry(Object key, Object value) {
/* 170 */     return System.identityHashCode(key) ^ System.identityHashCode(value);
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(Object key1, Object key2) {
/* 185 */     key2 = (this.keyType > 0) ? ((Reference)key2).get() : key2;
/* 186 */     return (key1 == key2);
/*     */   }
/*     */   
/*     */   protected boolean isEqualValue(Object value1, Object value2) {
/* 199 */     return (value1 == value2);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 207 */     out.defaultWriteObject();
/* 208 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 215 */     in.defaultReadObject();
/* 216 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\ReferenceIdentityMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */