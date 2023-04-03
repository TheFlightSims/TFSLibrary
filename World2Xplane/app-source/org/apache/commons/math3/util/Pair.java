/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ public class Pair<K, V> {
/*     */   private final K key;
/*     */   
/*     */   private final V value;
/*     */   
/*     */   public Pair(K k, V v) {
/*  43 */     this.key = k;
/*  44 */     this.value = v;
/*     */   }
/*     */   
/*     */   public Pair(Pair<? extends K, ? extends V> entry) {
/*  53 */     this.key = entry.getKey();
/*  54 */     this.value = entry.getValue();
/*     */   }
/*     */   
/*     */   public K getKey() {
/*  63 */     return this.key;
/*     */   }
/*     */   
/*     */   public V getValue() {
/*  72 */     return this.value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  84 */     if (o == null)
/*  85 */       return false; 
/*  87 */     if (!(o instanceof Pair))
/*  88 */       return false; 
/*  90 */     Pair<?, ?> oP = (Pair<?, ?>)o;
/*  91 */     return (((this.key == null) ? (oP.getKey() == null) : this.key.equals(oP.getKey())) && ((this.value == null) ? (oP.getValue() == null) : this.value.equals(oP.getValue())));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 107 */     return ((this.key == null) ? 0 : this.key.hashCode()) ^ ((this.value == null) ? 0 : this.value.hashCode());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\Pair.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */