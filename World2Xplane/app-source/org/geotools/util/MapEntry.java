/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.Classes;
/*     */ 
/*     */ public class MapEntry<K, V> implements Map.Entry<K, V>, Serializable {
/*     */   private static final long serialVersionUID = 8627698052283756776L;
/*     */   
/*     */   private final K key;
/*     */   
/*     */   private final V value;
/*     */   
/*     */   public MapEntry(K key, V value) {
/*  65 */     this.key = key;
/*  66 */     this.value = value;
/*     */   }
/*     */   
/*     */   public K getKey() {
/*  73 */     return this.key;
/*     */   }
/*     */   
/*     */   public V getValue() {
/*  80 */     return this.value;
/*     */   }
/*     */   
/*     */   public V setValue(V value) {
/*  89 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  99 */     if (object instanceof Map.Entry) {
/* 100 */       Map.Entry that = (Map.Entry)object;
/* 101 */       return (Utilities.equals(getKey(), that.getKey()) && Utilities.equals(getValue(), that.getValue()));
/*     */     } 
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 112 */     int code = 0;
/* 113 */     if (this.key != null)
/* 113 */       code = this.key.hashCode(); 
/* 114 */     if (this.value != null)
/* 114 */       code ^= this.value.hashCode(); 
/* 115 */     return code;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     return Classes.getShortClassName(this) + "[key=" + this.key + ", value=" + this.value + ']';
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\MapEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */