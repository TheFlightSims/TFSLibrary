/*     */ package org.apache.commons.lang.mutable;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class MutableObject implements Mutable, Serializable {
/*     */   private static final long serialVersionUID = 86241875189L;
/*     */   
/*     */   private Object value;
/*     */   
/*     */   public MutableObject() {}
/*     */   
/*     */   public MutableObject(Object value) {
/*  55 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Object getValue() {
/*  65 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Object value) {
/*  74 */     this.value = value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  87 */     if (obj instanceof MutableObject) {
/*  88 */       Object other = ((MutableObject)obj).value;
/*  89 */       return (this.value == other || (this.value != null && this.value.equals(other)));
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 100 */     return (this.value == null) ? 0 : this.value.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return (this.value == null) ? "null" : this.value.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\mutable\MutableObject.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */