/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class LRUMap extends SequencedHashMap implements Externalizable {
/*  55 */   private int maximumSize = 0;
/*     */   
/*     */   private static final long serialVersionUID = 2197433140769957051L;
/*     */   
/*     */   public LRUMap() {
/*  64 */     this(100);
/*     */   }
/*     */   
/*     */   public LRUMap(int i) {
/*  75 */     super(i);
/*  76 */     this.maximumSize = i;
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  92 */     if (!containsKey(key))
/*  92 */       return null; 
/*  94 */     Object value = remove(key);
/*  95 */     super.put(key, value);
/*  96 */     return value;
/*     */   }
/*     */   
/*     */   public Object put(Object key, Object value) {
/* 113 */     int mapSize = size();
/* 114 */     Object retval = null;
/* 116 */     if (mapSize >= this.maximumSize)
/* 120 */       if (!containsKey(key))
/* 122 */         removeLRU();  
/* 126 */     retval = super.put(key, value);
/* 128 */     return retval;
/*     */   }
/*     */   
/*     */   protected void removeLRU() {
/* 136 */     Object key = getFirstKey();
/* 139 */     Object value = super.get(key);
/* 141 */     remove(key);
/* 143 */     processRemovedLRU(key, value);
/*     */   }
/*     */   
/*     */   protected void processRemovedLRU(Object key, Object value) {}
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 161 */     this.maximumSize = in.readInt();
/* 162 */     int size = in.readInt();
/* 164 */     for (int i = 0; i < size; i++) {
/* 165 */       Object key = in.readObject();
/* 166 */       Object value = in.readObject();
/* 167 */       put(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 172 */     out.writeInt(this.maximumSize);
/* 173 */     out.writeInt(size());
/* 174 */     for (Iterator iterator = keySet().iterator(); iterator.hasNext(); ) {
/* 175 */       Object key = iterator.next();
/* 176 */       out.writeObject(key);
/* 179 */       Object value = super.get(key);
/* 180 */       out.writeObject(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMaximumSize() {
/* 191 */     return this.maximumSize;
/*     */   }
/*     */   
/*     */   public void setMaximumSize(int maximumSize) {
/* 197 */     this.maximumSize = maximumSize;
/* 198 */     while (size() > maximumSize)
/* 199 */       removeLRU(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\LRUMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */