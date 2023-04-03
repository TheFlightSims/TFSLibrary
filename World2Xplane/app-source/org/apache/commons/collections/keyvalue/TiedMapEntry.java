/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ 
/*     */ public class TiedMapEntry implements Map.Entry, KeyValue, Serializable {
/*     */   private static final long serialVersionUID = -8453869361373831205L;
/*     */   
/*     */   private final Map map;
/*     */   
/*     */   private final Object key;
/*     */   
/*     */   public TiedMapEntry(Map map, Object key) {
/*  53 */     this.map = map;
/*  54 */     this.key = key;
/*     */   }
/*     */   
/*     */   public Object getKey() {
/*  65 */     return this.key;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  74 */     return this.map.get(this.key);
/*     */   }
/*     */   
/*     */   public Object setValue(Object value) {
/*  85 */     if (value == this)
/*  86 */       throw new IllegalArgumentException("Cannot set value to this map entry"); 
/*  88 */     return this.map.put(this.key, value);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 100 */     if (obj == this)
/* 101 */       return true; 
/* 103 */     if (!(obj instanceof Map.Entry))
/* 104 */       return false; 
/* 106 */     Map.Entry other = (Map.Entry)obj;
/* 107 */     Object value = getValue();
/* 108 */     return (((this.key == null) ? (other.getKey() == null) : this.key.equals(other.getKey())) && ((value == null) ? (other.getValue() == null) : value.equals(other.getValue())));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 121 */     Object value = getValue();
/* 122 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((value == null) ? 0 : value.hashCode());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 132 */     return getKey() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\TiedMapEntry.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */