/*     */ package org.apache.commons.collections.keyvalue;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.KeyValue;
/*     */ 
/*     */ public class DefaultKeyValue extends AbstractKeyValue {
/*     */   public DefaultKeyValue() {
/*  44 */     super(null, null);
/*     */   }
/*     */   
/*     */   public DefaultKeyValue(Object key, Object value) {
/*  54 */     super(key, value);
/*     */   }
/*     */   
/*     */   public DefaultKeyValue(KeyValue pair) {
/*  64 */     super(pair.getKey(), pair.getValue());
/*     */   }
/*     */   
/*     */   public DefaultKeyValue(Map.Entry entry) {
/*  74 */     super(entry.getKey(), entry.getValue());
/*     */   }
/*     */   
/*     */   public Object setKey(Object key) {
/*  86 */     if (key == this)
/*  87 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a key."); 
/*  90 */     Object old = this.key;
/*  91 */     this.key = key;
/*  92 */     return old;
/*     */   }
/*     */   
/*     */   public Object setValue(Object value) {
/* 103 */     if (value == this)
/* 104 */       throw new IllegalArgumentException("DefaultKeyValue may not contain itself as a value."); 
/* 107 */     Object old = this.value;
/* 108 */     this.value = value;
/* 109 */     return old;
/*     */   }
/*     */   
/*     */   public Map.Entry toMapEntry() {
/* 119 */     return new DefaultMapEntry(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 133 */     if (obj == this)
/* 134 */       return true; 
/* 136 */     if (!(obj instanceof DefaultKeyValue))
/* 137 */       return false; 
/* 140 */     DefaultKeyValue other = (DefaultKeyValue)obj;
/* 141 */     return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 155 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\keyvalue\DefaultKeyValue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */