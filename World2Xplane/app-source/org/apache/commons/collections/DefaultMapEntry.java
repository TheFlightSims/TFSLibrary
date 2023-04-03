/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DefaultMapEntry implements Map.Entry, KeyValue {
/*     */   private Object key;
/*     */   
/*     */   private Object value;
/*     */   
/*     */   public DefaultMapEntry() {}
/*     */   
/*     */   public DefaultMapEntry(Map.Entry entry) {
/*  57 */     this.key = entry.getKey();
/*  58 */     this.value = entry.getValue();
/*     */   }
/*     */   
/*     */   public DefaultMapEntry(Object key, Object value) {
/*  70 */     this.key = key;
/*  71 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Object getKey() {
/*  82 */     return this.key;
/*     */   }
/*     */   
/*     */   public void setKey(Object key) {
/*  93 */     this.key = key;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 102 */     return this.value;
/*     */   }
/*     */   
/*     */   public Object setValue(Object value) {
/* 114 */     Object answer = this.value;
/* 115 */     this.value = value;
/* 116 */     return answer;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (obj == this)
/* 131 */       return true; 
/* 133 */     if (!(obj instanceof Map.Entry))
/* 134 */       return false; 
/* 136 */     Map.Entry other = (Map.Entry)obj;
/* 137 */     return (((getKey() == null) ? (other.getKey() == null) : getKey().equals(other.getKey())) && ((getValue() == null) ? (other.getValue() == null) : getValue().equals(other.getValue())));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 150 */     return ((getKey() == null) ? 0 : getKey().hashCode()) ^ ((getValue() == null) ? 0 : getValue().hashCode());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 160 */     return "" + getKey() + "=" + getValue();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\DefaultMapEntry.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */