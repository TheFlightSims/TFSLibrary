/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.SortOrder;
/*     */ 
/*     */ public class DefaultKeyedValues implements KeyedValues, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 8468154364608194797L;
/*     */   
/*  85 */   private List data = new ArrayList();
/*     */   
/*     */   public int getItemCount() {
/*  94 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public Number getValue(int item) {
/* 107 */     Number result = null;
/* 108 */     KeyedValue kval = this.data.get(item);
/* 109 */     if (kval != null)
/* 110 */       result = kval.getValue(); 
/* 112 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getKey(int index) {
/* 125 */     Comparable result = null;
/* 126 */     KeyedValue item = this.data.get(index);
/* 127 */     if (item != null)
/* 128 */       result = item.getKey(); 
/* 130 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(Comparable key) {
/* 141 */     int i = 0;
/* 142 */     Iterator iterator = this.data.iterator();
/* 143 */     while (iterator.hasNext()) {
/* 144 */       KeyedValue kv = iterator.next();
/* 145 */       if (kv.getKey().equals(key))
/* 146 */         return i; 
/* 148 */       i++;
/*     */     } 
/* 150 */     return -1;
/*     */   }
/*     */   
/*     */   public List getKeys() {
/* 159 */     List result = new ArrayList();
/* 160 */     Iterator iterator = this.data.iterator();
/* 161 */     while (iterator.hasNext()) {
/* 162 */       KeyedValue kv = iterator.next();
/* 163 */       result.add(kv.getKey());
/*     */     } 
/* 165 */     return result;
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable key) {
/* 178 */     int index = getIndex(key);
/* 179 */     if (index < 0)
/* 180 */       throw new UnknownKeyException("Key not found: " + key); 
/* 182 */     return getValue(index);
/*     */   }
/*     */   
/*     */   public void addValue(Comparable key, double value) {
/* 192 */     addValue(key, new Double(value));
/*     */   }
/*     */   
/*     */   public void addValue(Comparable key, Number value) {
/* 204 */     setValue(key, value);
/*     */   }
/*     */   
/*     */   public void setValue(Comparable key, double value) {
/* 214 */     setValue(key, new Double(value));
/*     */   }
/*     */   
/*     */   public void setValue(Comparable key, Number value) {
/* 224 */     if (key == null)
/* 225 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 227 */     int keyIndex = getIndex(key);
/* 228 */     if (keyIndex >= 0) {
/* 229 */       DefaultKeyedValue kv = this.data.get(keyIndex);
/* 230 */       kv.setValue(value);
/*     */     } else {
/* 233 */       KeyedValue kv = new DefaultKeyedValue(key, value);
/* 234 */       this.data.add(kv);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeValue(int index) {
/* 244 */     this.data.remove(index);
/*     */   }
/*     */   
/*     */   public void removeValue(Comparable key) {
/* 254 */     int index = getIndex(key);
/* 255 */     if (index >= 0)
/* 256 */       removeValue(index); 
/*     */   }
/*     */   
/*     */   public void sortByKeys(SortOrder order) {
/* 266 */     Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_KEY, order);
/* 269 */     Collections.sort(this.data, comparator);
/*     */   }
/*     */   
/*     */   public void sortByValues(SortOrder order) {
/* 280 */     Comparator comparator = new KeyedValueComparator(KeyedValueComparatorType.BY_VALUE, order);
/* 283 */     Collections.sort(this.data, comparator);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 294 */     if (obj == this)
/* 295 */       return true; 
/* 298 */     if (!(obj instanceof KeyedValues))
/* 299 */       return false; 
/* 302 */     KeyedValues that = (KeyedValues)obj;
/* 303 */     int count = getItemCount();
/* 304 */     if (count != that.getItemCount())
/* 305 */       return false; 
/* 308 */     for (int i = 0; i < count; i++) {
/* 309 */       Comparable k1 = getKey(i);
/* 310 */       Comparable k2 = that.getKey(i);
/* 311 */       if (!k1.equals(k2))
/* 312 */         return false; 
/* 314 */       Number v1 = getValue(i);
/* 315 */       Number v2 = that.getValue(i);
/* 316 */       if (v1 == null) {
/* 317 */         if (v2 != null)
/* 318 */           return false; 
/* 322 */       } else if (!v1.equals(v2)) {
/* 323 */         return false;
/*     */       } 
/*     */     } 
/* 327 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 336 */     return (this.data != null) ? this.data.hashCode() : 0;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 348 */     DefaultKeyedValues clone = (DefaultKeyedValues)super.clone();
/* 349 */     clone.data = (List)ObjectUtilities.deepClone(this.data);
/* 350 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\DefaultKeyedValues.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */