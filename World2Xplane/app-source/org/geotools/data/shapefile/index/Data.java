/*     */ package org.geotools.data.shapefile.index;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Data {
/*     */   private DataDefinition def;
/*     */   
/*     */   private ArrayList values;
/*     */   
/*     */   public Data(DataDefinition def) {
/*  39 */     this.def = def;
/*  40 */     this.values = new ArrayList(def.getFieldsCount());
/*     */   }
/*     */   
/*     */   public final boolean isValid() {
/*  49 */     if (getValuesCount() != this.def.getFieldsCount())
/*  50 */       return false; 
/*  53 */     boolean ret = true;
/*  55 */     for (int i = 0; i < this.def.getFieldsCount(); i++) {
/*  56 */       if (!this.def.getField(i).getFieldClass().isInstance(getValue(i))) {
/*  58 */         ret = false;
/*     */         break;
/*     */       } 
/*     */     } 
/*  64 */     return ret;
/*     */   }
/*     */   
/*     */   public Data addValue(Object val) throws TreeException {
/*  77 */     if (this.values.size() == this.def.getFieldsCount())
/*  78 */       throw new TreeException("Max number of values reached!"); 
/*  81 */     int pos = this.values.size();
/*  83 */     if (!val.getClass().equals(this.def.getField(pos).getFieldClass()))
/*  84 */       throw new TreeException("Wrong class type, was expecting " + this.def.getField(pos).getFieldClass()); 
/*  88 */     this.values.add(val);
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public DataDefinition getDefinition() {
/*  98 */     return this.def;
/*     */   }
/*     */   
/*     */   public int getValuesCount() {
/* 106 */     return this.values.size();
/*     */   }
/*     */   
/*     */   public Object getValue(int i) {
/* 116 */     return this.values.get(i);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     StringBuffer ret = new StringBuffer();
/* 125 */     for (int i = 0; i < this.values.size(); i++) {
/* 126 */       if (i > 0)
/* 127 */         ret.append(" - "); 
/* 130 */       ret.append(this.def.getField(i).getFieldClass());
/* 131 */       ret.append(": ");
/* 132 */       ret.append(this.values.get(i));
/*     */     } 
/* 135 */     return ret.toString();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 139 */     this.values.clear();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\Data.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */