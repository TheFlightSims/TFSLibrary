/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.jfree.data.DefaultKeyedValues;
/*     */ import org.jfree.data.KeyedValues;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class DefaultPieDataset extends AbstractDataset implements PieDataset, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 2904745139106540618L;
/*     */   
/*     */   private DefaultKeyedValues data;
/*     */   
/*     */   public DefaultPieDataset() {
/*  83 */     this.data = new DefaultKeyedValues();
/*     */   }
/*     */   
/*     */   public DefaultPieDataset(KeyedValues data) {
/*  93 */     if (data == null)
/*  94 */       throw new IllegalArgumentException("Null 'data' argument."); 
/*  96 */     this.data = new DefaultKeyedValues();
/*  97 */     for (int i = 0; i < data.getItemCount(); i++)
/*  98 */       this.data.addValue(data.getKey(i), data.getValue(i)); 
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 108 */     return this.data.getItemCount();
/*     */   }
/*     */   
/*     */   public List getKeys() {
/* 118 */     return Collections.unmodifiableList(this.data.getKeys());
/*     */   }
/*     */   
/*     */   public Comparable getKey(int item) {
/* 129 */     Comparable result = null;
/* 130 */     if (getItemCount() > item)
/* 131 */       result = this.data.getKey(item); 
/* 133 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(Comparable key) {
/* 144 */     return this.data.getIndex(key);
/*     */   }
/*     */   
/*     */   public Number getValue(int item) {
/* 156 */     Number result = null;
/* 157 */     if (getItemCount() > item)
/* 158 */       result = this.data.getValue(item); 
/* 160 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable key) {
/* 174 */     if (key == null)
/* 175 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 177 */     return this.data.getValue(key);
/*     */   }
/*     */   
/*     */   public void setValue(Comparable key, Number value) {
/* 187 */     this.data.setValue(key, value);
/* 188 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public void setValue(Comparable key, double value) {
/* 198 */     setValue(key, new Double(value));
/*     */   }
/*     */   
/*     */   public void remove(Comparable key) {
/* 208 */     this.data.removeValue(key);
/* 209 */     fireDatasetChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 220 */     if (obj == this)
/* 221 */       return true; 
/* 224 */     if (!(obj instanceof PieDataset))
/* 225 */       return false; 
/* 227 */     PieDataset that = (PieDataset)obj;
/* 228 */     int count = getItemCount();
/* 229 */     if (that.getItemCount() != count)
/* 230 */       return false; 
/* 233 */     for (int i = 0; i < count; i++) {
/* 234 */       Comparable k1 = getKey(i);
/* 235 */       Comparable k2 = that.getKey(i);
/* 236 */       if (!k1.equals(k2))
/* 237 */         return false; 
/* 240 */       Number v1 = getValue(i);
/* 241 */       Number v2 = that.getValue(i);
/* 242 */       if (v1 == null) {
/* 243 */         if (v2 != null)
/* 244 */           return false; 
/* 248 */       } else if (!v1.equals(v2)) {
/* 249 */         return false;
/*     */       } 
/*     */     } 
/* 253 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 263 */     return this.data.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 275 */     DefaultPieDataset clone = (DefaultPieDataset)super.clone();
/* 276 */     clone.data = (DefaultKeyedValues)this.data.clone();
/* 277 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\DefaultPieDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */