/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class MultiKey implements Serializable {
/*     */   private static final long serialVersionUID = 4465448607415788805L;
/*     */   
/*     */   private final Object[] keys;
/*     */   
/*     */   private final int hashCode;
/*     */   
/*     */   public MultiKey(Object key1, Object key2) {
/*  69 */     this(new Object[] { key1, key2 }, false);
/*     */   }
/*     */   
/*     */   public MultiKey(Object key1, Object key2, Object key3) {
/*  83 */     this(new Object[] { key1, key2, key3 }, false);
/*     */   }
/*     */   
/*     */   public MultiKey(Object key1, Object key2, Object key3, Object key4) {
/*  98 */     this(new Object[] { key1, key2, key3, key4 }, false);
/*     */   }
/*     */   
/*     */   public MultiKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
/* 114 */     this(new Object[] { key1, key2, key3, key4, key5 }, false);
/*     */   }
/*     */   
/*     */   public MultiKey(Object[] keys) {
/* 129 */     this(keys, true);
/*     */   }
/*     */   
/*     */   public MultiKey(Object[] keys, boolean makeClone) {
/* 158 */     if (keys == null)
/* 159 */       throw new IllegalArgumentException("The array of keys must not be null"); 
/* 161 */     if (makeClone) {
/* 162 */       this.keys = (Object[])keys.clone();
/*     */     } else {
/* 164 */       this.keys = keys;
/*     */     } 
/* 167 */     int total = 0;
/* 168 */     for (int i = 0; i < keys.length; i++) {
/* 169 */       if (keys[i] != null)
/* 170 */         total ^= keys[i].hashCode(); 
/*     */     } 
/* 173 */     this.hashCode = total;
/*     */   }
/*     */   
/*     */   public Object[] getKeys() {
/* 186 */     return (Object[])this.keys.clone();
/*     */   }
/*     */   
/*     */   public Object getKey(int index) {
/* 201 */     return this.keys[index];
/*     */   }
/*     */   
/*     */   public int size() {
/* 211 */     return this.keys.length;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 225 */     if (other == this)
/* 226 */       return true; 
/* 228 */     if (other instanceof MultiKey) {
/* 229 */       MultiKey otherMulti = (MultiKey)other;
/* 230 */       return Arrays.equals(this.keys, otherMulti.keys);
/*     */     } 
/* 232 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 246 */     return this.hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 255 */     return "MultiKey" + Arrays.<Object>asList(this.keys).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\MultiKey.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */