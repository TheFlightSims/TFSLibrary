/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.util.OpenIntToFieldHashMap;
/*     */ 
/*     */ public class SparseFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> {
/*     */   private final OpenIntToFieldHashMap<T> entries;
/*     */   
/*     */   private final int rows;
/*     */   
/*     */   private final int columns;
/*     */   
/*     */   public SparseFieldMatrix(Field<T> field) {
/*  45 */     super(field);
/*  46 */     this.rows = 0;
/*  47 */     this.columns = 0;
/*  48 */     this.entries = new OpenIntToFieldHashMap(field);
/*     */   }
/*     */   
/*     */   public SparseFieldMatrix(Field<T> field, int rowDimension, int columnDimension) {
/*  63 */     super(field, rowDimension, columnDimension);
/*  64 */     this.rows = rowDimension;
/*  65 */     this.columns = columnDimension;
/*  66 */     this.entries = new OpenIntToFieldHashMap(field);
/*     */   }
/*     */   
/*     */   public SparseFieldMatrix(SparseFieldMatrix<T> other) {
/*  75 */     super(other.getField(), other.getRowDimension(), other.getColumnDimension());
/*  76 */     this.rows = other.getRowDimension();
/*  77 */     this.columns = other.getColumnDimension();
/*  78 */     this.entries = new OpenIntToFieldHashMap(other.entries);
/*     */   }
/*     */   
/*     */   public SparseFieldMatrix(FieldMatrix<T> other) {
/*  87 */     super(other.getField(), other.getRowDimension(), other.getColumnDimension());
/*  88 */     this.rows = other.getRowDimension();
/*  89 */     this.columns = other.getColumnDimension();
/*  90 */     this.entries = new OpenIntToFieldHashMap(getField());
/*  91 */     for (int i = 0; i < this.rows; i++) {
/*  92 */       for (int j = 0; j < this.columns; j++)
/*  93 */         setEntry(i, j, other.getEntry(i, j)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addToEntry(int row, int column, T increment) {
/* 101 */     checkRowIndex(row);
/* 102 */     checkColumnIndex(column);
/* 103 */     int key = computeKey(row, column);
/* 104 */     FieldElement fieldElement = (FieldElement)this.entries.get(key).add(increment);
/* 105 */     if (((FieldElement)getField().getZero()).equals(fieldElement)) {
/* 106 */       this.entries.remove(key);
/*     */     } else {
/* 108 */       this.entries.put(key, fieldElement);
/*     */     } 
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> copy() {
/* 115 */     return new SparseFieldMatrix(this);
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> createMatrix(int rowDimension, int columnDimension) {
/* 121 */     return new SparseFieldMatrix(getField(), rowDimension, columnDimension);
/*     */   }
/*     */   
/*     */   public int getColumnDimension() {
/* 127 */     return this.columns;
/*     */   }
/*     */   
/*     */   public T getEntry(int row, int column) {
/* 133 */     checkRowIndex(row);
/* 134 */     checkColumnIndex(column);
/* 135 */     return (T)this.entries.get(computeKey(row, column));
/*     */   }
/*     */   
/*     */   public int getRowDimension() {
/* 141 */     return this.rows;
/*     */   }
/*     */   
/*     */   public void multiplyEntry(int row, int column, T factor) {
/* 147 */     checkRowIndex(row);
/* 148 */     checkColumnIndex(column);
/* 149 */     int key = computeKey(row, column);
/* 150 */     FieldElement fieldElement = (FieldElement)this.entries.get(key).multiply(factor);
/* 151 */     if (((FieldElement)getField().getZero()).equals(fieldElement)) {
/* 152 */       this.entries.remove(key);
/*     */     } else {
/* 154 */       this.entries.put(key, fieldElement);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setEntry(int row, int column, T value) {
/* 162 */     checkRowIndex(row);
/* 163 */     checkColumnIndex(column);
/* 164 */     if (((FieldElement)getField().getZero()).equals(value)) {
/* 165 */       this.entries.remove(computeKey(row, column));
/*     */     } else {
/* 167 */       this.entries.put(computeKey(row, column), (FieldElement)value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int computeKey(int row, int column) {
/* 179 */     return row * this.columns + column;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\SparseFieldMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */