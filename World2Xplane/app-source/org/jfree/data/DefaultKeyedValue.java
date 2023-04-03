/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class DefaultKeyedValue implements KeyedValue, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -7388924517460437712L;
/*     */   
/*     */   private Comparable key;
/*     */   
/*     */   private Number value;
/*     */   
/*     */   public DefaultKeyedValue(Comparable key, Number value) {
/*  77 */     this.key = key;
/*  78 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/*  87 */     return this.key;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/*  96 */     return this.value;
/*     */   }
/*     */   
/*     */   public synchronized void setValue(Number value) {
/* 105 */     this.value = value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 116 */     if (obj == this)
/* 117 */       return true; 
/* 119 */     if (!(obj instanceof DefaultKeyedValue))
/* 120 */       return false; 
/* 124 */     DefaultKeyedValue that = (DefaultKeyedValue)obj;
/* 128 */     if ((this.key != null) ? !this.key.equals(that.key) : (that.key != null))
/* 129 */       return false; 
/* 131 */     if ((this.value != null) ? !this.value.equals(that.value) : (that.value != null))
/* 133 */       return false; 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 145 */     int result = (this.key != null) ? this.key.hashCode() : 0;
/* 146 */     result = 29 * result + ((this.value != null) ? this.value.hashCode() : 0);
/* 147 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 161 */     DefaultKeyedValue clone = (DefaultKeyedValue)super.clone();
/* 162 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\DefaultKeyedValue.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */