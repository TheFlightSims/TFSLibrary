/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class DefaultKeyedValues2D implements KeyedValues2D, PublicCloneable, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -5514169970951994748L;
/*     */   
/*     */   private List rowKeys;
/*     */   
/*     */   private List columnKeys;
/*     */   
/*     */   private List rows;
/*     */   
/*     */   private boolean sortRowKeys;
/*     */   
/*     */   public DefaultKeyedValues2D() {
/*  92 */     this(false);
/*     */   }
/*     */   
/*     */   public DefaultKeyedValues2D(boolean sortRowKeys) {
/* 101 */     this.rowKeys = new ArrayList();
/* 102 */     this.columnKeys = new ArrayList();
/* 103 */     this.rows = new ArrayList();
/* 104 */     this.sortRowKeys = sortRowKeys;
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 113 */     return this.rowKeys.size();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 122 */     return this.columnKeys.size();
/*     */   }
/*     */   
/*     */   public Number getValue(int row, int column) {
/* 134 */     Number result = null;
/* 135 */     DefaultKeyedValues rowData = this.rows.get(row);
/* 136 */     if (rowData != null) {
/* 137 */       Comparable columnKey = this.columnKeys.get(column);
/* 140 */       int index = rowData.getIndex(columnKey);
/* 141 */       if (index >= 0)
/* 142 */         result = rowData.getValue(index); 
/*     */     } 
/* 145 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getRowKey(int row) {
/* 156 */     return this.rowKeys.get(row);
/*     */   }
/*     */   
/*     */   public int getRowIndex(Comparable key) {
/* 167 */     if (key == null)
/* 168 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 170 */     if (this.sortRowKeys)
/* 171 */       return Collections.binarySearch(this.rowKeys, key); 
/* 174 */     return this.rowKeys.indexOf(key);
/*     */   }
/*     */   
/*     */   public List getRowKeys() {
/* 184 */     return Collections.unmodifiableList(this.rowKeys);
/*     */   }
/*     */   
/*     */   public Comparable getColumnKey(int column) {
/* 195 */     return this.columnKeys.get(column);
/*     */   }
/*     */   
/*     */   public int getColumnIndex(Comparable key) {
/* 206 */     if (key == null)
/* 207 */       throw new IllegalArgumentException("Null 'key' argument."); 
/* 209 */     return this.columnKeys.indexOf(key);
/*     */   }
/*     */   
/*     */   public List getColumnKeys() {
/* 218 */     return Collections.unmodifiableList(this.columnKeys);
/*     */   }
/*     */   
/*     */   public Number getValue(Comparable rowKey, Comparable columnKey) {
/* 232 */     if (rowKey == null)
/* 233 */       throw new IllegalArgumentException("Null 'rowKey' argument."); 
/* 235 */     if (columnKey == null)
/* 236 */       throw new IllegalArgumentException("Null 'columnKey' argument."); 
/* 238 */     int row = getRowIndex(rowKey);
/* 239 */     if (row >= 0) {
/* 240 */       DefaultKeyedValues rowData = this.rows.get(row);
/* 242 */       return rowData.getValue(columnKey);
/*     */     } 
/* 245 */     throw new UnknownKeyException("Unrecognised rowKey: " + rowKey);
/*     */   }
/*     */   
/*     */   public void addValue(Number value, Comparable rowKey, Comparable columnKey) {
/* 260 */     setValue(value, rowKey, columnKey);
/*     */   }
/*     */   
/*     */   public void setValue(Number value, Comparable rowKey, Comparable columnKey) {
/*     */     DefaultKeyedValues row;
/* 274 */     int rowIndex = getRowIndex(rowKey);
/* 276 */     if (rowIndex >= 0) {
/* 277 */       row = this.rows.get(rowIndex);
/*     */     } else {
/* 280 */       row = new DefaultKeyedValues();
/* 281 */       if (this.sortRowKeys) {
/* 282 */         rowIndex = -rowIndex - 1;
/* 283 */         this.rowKeys.add(rowIndex, rowKey);
/* 284 */         this.rows.add(rowIndex, row);
/*     */       } else {
/* 287 */         this.rowKeys.add(rowKey);
/* 288 */         this.rows.add(row);
/*     */       } 
/*     */     } 
/* 291 */     row.setValue(columnKey, value);
/* 293 */     int columnIndex = this.columnKeys.indexOf(columnKey);
/* 294 */     if (columnIndex < 0)
/* 295 */       this.columnKeys.add(columnKey); 
/*     */   }
/*     */   
/*     */   public void removeValue(Comparable rowKey, Comparable columnKey) {
/* 306 */     setValue(null, rowKey, columnKey);
/* 309 */     boolean allNull = true;
/* 310 */     int rowIndex = getRowIndex(rowKey);
/* 311 */     DefaultKeyedValues row = this.rows.get(rowIndex);
/* 313 */     for (int item = 0, itemCount = row.getItemCount(); item < itemCount; 
/* 314 */       item++) {
/* 315 */       if (row.getValue(item) != null) {
/* 316 */         allNull = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 321 */     if (allNull) {
/* 322 */       this.rowKeys.remove(rowIndex);
/* 323 */       this.rows.remove(rowIndex);
/*     */     } 
/* 327 */     allNull = true;
/* 328 */     int columnIndex = getColumnIndex(columnKey);
/*     */     int i, j;
/* 330 */     for (i = 0, j = this.rows.size(); i < j; 
/* 331 */       i++) {
/* 332 */       row = this.rows.get(i);
/* 333 */       if (row.getValue(columnIndex) != null) {
/* 334 */         allNull = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 339 */     if (allNull) {
/* 340 */       for (i = 0, j = this.rows.size(); i < j; 
/* 341 */         i++) {
/* 342 */         row = this.rows.get(i);
/* 343 */         row.removeValue(columnIndex);
/*     */       } 
/* 345 */       this.columnKeys.remove(columnIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeRow(int rowIndex) {
/* 355 */     this.rowKeys.remove(rowIndex);
/* 356 */     this.rows.remove(rowIndex);
/*     */   }
/*     */   
/*     */   public void removeRow(Comparable rowKey) {
/* 365 */     removeRow(getRowIndex(rowKey));
/*     */   }
/*     */   
/*     */   public void removeColumn(int columnIndex) {
/* 374 */     Comparable columnKey = getColumnKey(columnIndex);
/* 375 */     removeColumn(columnKey);
/*     */   }
/*     */   
/*     */   public void removeColumn(Comparable columnKey) {
/* 384 */     Iterator iterator = this.rows.iterator();
/* 385 */     while (iterator.hasNext()) {
/* 386 */       DefaultKeyedValues rowData = iterator.next();
/* 387 */       rowData.removeValue(columnKey);
/*     */     } 
/* 389 */     this.columnKeys.remove(columnKey);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 396 */     this.rowKeys.clear();
/* 397 */     this.columnKeys.clear();
/* 398 */     this.rows.clear();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 410 */     if (o == null)
/* 411 */       return false; 
/* 413 */     if (o == this)
/* 414 */       return true; 
/* 417 */     if (!(o instanceof KeyedValues2D))
/* 418 */       return false; 
/* 420 */     KeyedValues2D kv2D = (KeyedValues2D)o;
/* 421 */     if (!getRowKeys().equals(kv2D.getRowKeys()))
/* 422 */       return false; 
/* 424 */     if (!getColumnKeys().equals(kv2D.getColumnKeys()))
/* 425 */       return false; 
/* 427 */     int rowCount = getRowCount();
/* 428 */     if (rowCount != kv2D.getRowCount())
/* 429 */       return false; 
/* 432 */     int colCount = getColumnCount();
/* 433 */     if (colCount != kv2D.getColumnCount())
/* 434 */       return false; 
/* 437 */     for (int r = 0; r < rowCount; r++) {
/* 438 */       for (int c = 0; c < colCount; c++) {
/* 439 */         Number v1 = getValue(r, c);
/* 440 */         Number v2 = kv2D.getValue(r, c);
/* 441 */         if (v1 == null) {
/* 442 */           if (v2 != null)
/* 443 */             return false; 
/* 447 */         } else if (!v1.equals(v2)) {
/* 448 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 453 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 463 */     int result = this.rowKeys.hashCode();
/* 464 */     result = 29 * result + this.columnKeys.hashCode();
/* 465 */     result = 29 * result + this.rows.hashCode();
/* 466 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 478 */     DefaultKeyedValues2D clone = (DefaultKeyedValues2D)super.clone();
/* 481 */     clone.columnKeys = new ArrayList(this.columnKeys);
/* 482 */     clone.rowKeys = new ArrayList(this.rowKeys);
/* 485 */     clone.rows = (List)ObjectUtilities.deepClone(this.rows);
/* 486 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\DefaultKeyedValues2D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */