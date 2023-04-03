/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.DefaultKeyedValue;
/*     */ import org.jfree.data.KeyedValue;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class DefaultKeyedValueDataset extends AbstractDataset implements KeyedValueDataset, Serializable {
/*     */   private static final long serialVersionUID = -8149484339560406750L;
/*     */   
/*     */   private KeyedValue data;
/*     */   
/*     */   public DefaultKeyedValueDataset() {
/*  69 */     this(null);
/*     */   }
/*     */   
/*     */   public DefaultKeyedValueDataset(Comparable key, Number value) {
/*  79 */     this((KeyedValue)new DefaultKeyedValue(key, value));
/*     */   }
/*     */   
/*     */   public DefaultKeyedValueDataset(KeyedValue data) {
/*  89 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/*  99 */     Comparable result = null;
/* 100 */     if (this.data != null)
/* 101 */       result = this.data.getKey(); 
/* 103 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue() {
/* 112 */     Number result = null;
/* 113 */     if (this.data != null)
/* 114 */       result = this.data.getValue(); 
/* 116 */     return result;
/*     */   }
/*     */   
/*     */   public void updateValue(Number value) {
/* 125 */     if (this.data == null)
/* 126 */       throw new RuntimeException("updateValue: can't update null."); 
/* 128 */     setValue(this.data.getKey(), value);
/*     */   }
/*     */   
/*     */   public void setValue(Comparable key, Number value) {
/* 139 */     this.data = (KeyedValue)new DefaultKeyedValue(key, value);
/* 140 */     notifyListeners(new DatasetChangeEvent(this, this));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 152 */     if (obj == this)
/* 153 */       return true; 
/* 155 */     if (!(obj instanceof KeyedValueDataset))
/* 156 */       return false; 
/* 158 */     KeyedValueDataset that = (KeyedValueDataset)obj;
/* 159 */     if (this.data == null) {
/* 160 */       if (that.getKey() != null || that.getValue() != null)
/* 161 */         return false; 
/* 163 */       return true;
/*     */     } 
/* 165 */     if (!ObjectUtilities.equal(this.data.getKey(), that.getKey()))
/* 166 */       return false; 
/* 168 */     if (!ObjectUtilities.equal(this.data.getValue(), that.getValue()))
/* 169 */       return false; 
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 180 */     return (this.data != null) ? this.data.hashCode() : 0;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 192 */     DefaultKeyedValueDataset clone = (DefaultKeyedValueDataset)super.clone();
/* 194 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DefaultKeyedValueDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */