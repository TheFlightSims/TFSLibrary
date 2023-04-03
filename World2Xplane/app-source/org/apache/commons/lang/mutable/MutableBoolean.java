/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.BooleanUtils;
/*     */ 
/*     */ public class MutableBoolean implements Mutable, Serializable, Comparable {
/*     */   private static final long serialVersionUID = -4830728138360036487L;
/*     */   
/*     */   private boolean value;
/*     */   
/*     */   public MutableBoolean() {}
/*     */   
/*     */   public MutableBoolean(boolean value) {
/*  58 */     this.value = value;
/*     */   }
/*     */   
/*     */   public MutableBoolean(Boolean value) {
/*  69 */     this.value = value.booleanValue();
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  79 */     return BooleanUtils.toBooleanObject(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(boolean value) {
/*  88 */     this.value = value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/*  98 */     setValue(((Boolean)value).booleanValue());
/*     */   }
/*     */   
/*     */   public boolean isTrue() {
/* 109 */     return (this.value == true);
/*     */   }
/*     */   
/*     */   public boolean isFalse() {
/* 119 */     return !this.value;
/*     */   }
/*     */   
/*     */   public boolean booleanValue() {
/* 129 */     return this.value;
/*     */   }
/*     */   
/*     */   public Boolean toBoolean() {
/* 140 */     return BooleanUtils.toBooleanObject(this.value);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 153 */     if (obj instanceof MutableBoolean)
/* 154 */       return (this.value == ((MutableBoolean)obj).booleanValue()); 
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 165 */     return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 177 */     MutableBoolean other = (MutableBoolean)obj;
/* 178 */     boolean anotherVal = other.value;
/* 179 */     return (this.value == anotherVal) ? 0 : (this.value ? 1 : -1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 189 */     return String.valueOf(this.value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableBoolean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */