/*     */ package org.apache.commons.collections.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class IdentityMap extends AbstractHashedMap implements Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 2028493495224302329L;
/*     */   
/*     */   public IdentityMap() {
/*  54 */     super(16, 0.75F, 12);
/*     */   }
/*     */   
/*     */   public IdentityMap(int initialCapacity) {
/*  64 */     super(initialCapacity);
/*     */   }
/*     */   
/*     */   public IdentityMap(int initialCapacity, float loadFactor) {
/*  77 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */   public IdentityMap(Map map) {
/*  87 */     super(map);
/*     */   }
/*     */   
/*     */   protected int hash(Object key) {
/*  99 */     return System.identityHashCode(key);
/*     */   }
/*     */   
/*     */   protected boolean isEqualKey(Object key1, Object key2) {
/* 111 */     return (key1 == key2);
/*     */   }
/*     */   
/*     */   protected boolean isEqualValue(Object value1, Object value2) {
/* 123 */     return (value1 == value2);
/*     */   }
/*     */   
/*     */   protected AbstractHashedMap.HashEntry createEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 137 */     return new IdentityEntry(next, hashCode, key, value);
/*     */   }
/*     */   
/*     */   protected static class IdentityEntry extends AbstractHashedMap.HashEntry {
/*     */     protected IdentityEntry(AbstractHashedMap.HashEntry next, int hashCode, Object key, Object value) {
/* 147 */       super(next, hashCode, key, value);
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 151 */       if (obj == this)
/* 152 */         return true; 
/* 154 */       if (!(obj instanceof Map.Entry))
/* 155 */         return false; 
/* 157 */       Map.Entry other = (Map.Entry)obj;
/* 158 */       return (getKey() == other.getKey() && getValue() == other.getValue());
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 164 */       return System.identityHashCode(getKey()) ^ System.identityHashCode(getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 176 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 183 */     out.defaultWriteObject();
/* 184 */     doWriteObject(out);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 191 */     in.defaultReadObject();
/* 192 */     doReadObject(in);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\map\IdentityMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */