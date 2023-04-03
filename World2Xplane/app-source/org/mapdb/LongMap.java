/*     */ package org.mapdb;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class LongMap<V> {
/*     */   public abstract void clear();
/*     */   
/*     */   public abstract V get(long paramLong);
/*     */   
/*     */   public abstract boolean isEmpty();
/*     */   
/*     */   public abstract V put(long paramLong, V paramV);
/*     */   
/*     */   public abstract V remove(long paramLong);
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public abstract Iterator<V> valuesIterator();
/*     */   
/*     */   public abstract LongMapIterator<V> longMapIterator();
/*     */   
/*     */   public String toString() {
/* 100 */     StringBuilder b = new StringBuilder();
/* 101 */     b.append(getClass().getSimpleName());
/* 102 */     b.append('[');
/* 103 */     boolean first = true;
/* 104 */     LongMapIterator<V> iter = longMapIterator();
/* 105 */     while (iter.moveToNext()) {
/* 106 */       if (first) {
/* 107 */         first = false;
/*     */       } else {
/* 109 */         b.append(", ");
/*     */       } 
/* 111 */       b.append(iter.key());
/* 112 */       b.append(" => ");
/* 113 */       b.append(iter.value());
/*     */     } 
/* 115 */     b.append(']');
/* 116 */     return b.toString();
/*     */   }
/*     */   
/*     */   public static interface LongMapIterator<V> {
/*     */     boolean moveToNext();
/*     */     
/*     */     long key();
/*     */     
/*     */     V value();
/*     */     
/*     */     void remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\LongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */