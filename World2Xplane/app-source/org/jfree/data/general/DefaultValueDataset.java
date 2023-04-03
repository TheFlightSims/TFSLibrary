/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class DefaultValueDataset extends AbstractDataset implements ValueDataset, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 8137521217249294891L;
/*     */   
/*     */   private Number value;
/*     */   
/*     */   public DefaultValueDataset() {
/*  71 */     this((Number)null);
/*     */   }
/*     */   
/*     */   public DefaultValueDataset(double value) {
/*  80 */     this(new Double(value));
/*     */   }
/*     */   
/*     */   public DefaultValueDataset(Number value) {
/*  89 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/*  98 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(Number value) {
/* 108 */     this.value = value;
/* 109 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 121 */     if (obj == null)
/* 122 */       return false; 
/* 125 */     if (obj == this)
/* 126 */       return true; 
/* 129 */     if (obj instanceof ValueDataset) {
/* 130 */       ValueDataset vd = (ValueDataset)obj;
/* 131 */       return ObjectUtilities.equal(this.value, vd.getValue());
/*     */     } 
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 143 */     return (this.value != null) ? this.value.hashCode() : 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DefaultValueDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */