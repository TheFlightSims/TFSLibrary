/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class ObjectTable implements Serializable {
/*     */   private static final long serialVersionUID = -3968322452944912066L;
/*     */   
/*     */   private int rows;
/*     */   
/*     */   private int columns;
/*     */   
/*     */   private transient Object[][] data;
/*     */   
/*     */   private int rowIncrement;
/*     */   
/*     */   private int columnIncrement;
/*     */   
/*     */   public ObjectTable() {
/*  88 */     this(5, 5);
/*     */   }
/*     */   
/*     */   public ObjectTable(int increment) {
/*  97 */     this(increment, increment);
/*     */   }
/*     */   
/*     */   public ObjectTable(int rowIncrement, int colIncrement) {
/* 106 */     if (rowIncrement < 1)
/* 107 */       throw new IllegalArgumentException("Increment must be positive."); 
/* 110 */     if (colIncrement < 1)
/* 111 */       throw new IllegalArgumentException("Increment must be positive."); 
/* 114 */     this.rows = 0;
/* 115 */     this.columns = 0;
/* 116 */     this.rowIncrement = rowIncrement;
/* 117 */     this.columnIncrement = colIncrement;
/* 119 */     this.data = new Object[rowIncrement][];
/*     */   }
/*     */   
/*     */   public int getColumnIncrement() {
/* 128 */     return this.columnIncrement;
/*     */   }
/*     */   
/*     */   public int getRowIncrement() {
/* 137 */     return this.rowIncrement;
/*     */   }
/*     */   
/*     */   protected void ensureRowCapacity(int row) {
/* 149 */     if (row >= this.data.length) {
/* 151 */       Object[][] enlarged = new Object[row + this.rowIncrement][];
/* 152 */       System.arraycopy(this.data, 0, enlarged, 0, this.data.length);
/* 155 */       this.data = enlarged;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void ensureCapacity(int row, int column) {
/* 167 */     if (row < 0)
/* 168 */       throw new IndexOutOfBoundsException("Row is invalid. " + row); 
/* 170 */     if (column < 0)
/* 171 */       throw new IndexOutOfBoundsException("Column is invalid. " + column); 
/* 174 */     ensureRowCapacity(row);
/* 176 */     Object[] current = this.data[row];
/* 177 */     if (current == null) {
/* 178 */       Object[] enlarged = new Object[Math.max(column + 1, this.columnIncrement)];
/* 180 */       this.data[row] = enlarged;
/* 182 */     } else if (column >= current.length) {
/* 183 */       Object[] enlarged = new Object[column + this.columnIncrement];
/* 184 */       System.arraycopy(current, 0, enlarged, 0, current.length);
/* 185 */       this.data[row] = enlarged;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 195 */     return this.rows;
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 204 */     return this.columns;
/*     */   }
/*     */   
/*     */   protected Object getObject(int row, int column) {
/* 220 */     if (row < this.data.length) {
/* 221 */       Object[] current = this.data[row];
/* 222 */       if (current == null)
/* 223 */         return null; 
/* 225 */       if (column < current.length)
/* 226 */         return current[column]; 
/*     */     } 
/* 229 */     return null;
/*     */   }
/*     */   
/*     */   protected void setObject(int row, int column, Object object) {
/* 244 */     ensureCapacity(row, column);
/* 246 */     this.data[row][column] = object;
/* 247 */     this.rows = Math.max(this.rows, row + 1);
/* 248 */     this.columns = Math.max(this.columns, column + 1);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 261 */     if (o == null)
/* 262 */       return false; 
/* 265 */     if (this == o)
/* 266 */       return true; 
/* 269 */     if (!(o instanceof ObjectTable))
/* 270 */       return false; 
/* 273 */     ObjectTable ot = (ObjectTable)o;
/* 274 */     if (getRowCount() != ot.getRowCount())
/* 275 */       return false; 
/* 278 */     if (getColumnCount() != ot.getColumnCount())
/* 279 */       return false; 
/* 282 */     for (int r = 0; r < getRowCount(); r++) {
/* 283 */       for (int c = 0; c < getColumnCount(); c++) {
/* 284 */         if (!ObjectUtilities.equal(getObject(r, c), ot.getObject(r, c)))
/* 286 */           return false; 
/*     */       } 
/*     */     } 
/* 290 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 300 */     int result = this.rows;
/* 301 */     result = 29 * result + this.columns;
/* 302 */     return result;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 314 */     stream.defaultWriteObject();
/* 315 */     int rowCount = this.data.length;
/* 316 */     stream.writeInt(rowCount);
/* 317 */     for (int r = 0; r < rowCount; r++) {
/* 318 */       Object[] column = this.data[r];
/* 319 */       stream.writeBoolean((column != null));
/* 320 */       if (column != null) {
/* 321 */         int columnCount = column.length;
/* 322 */         stream.writeInt(columnCount);
/* 323 */         for (int c = 0; c < columnCount; c++)
/* 324 */           writeSerializedData(stream, column[c]); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void writeSerializedData(ObjectOutputStream stream, Object o) throws IOException {
/* 340 */     stream.writeObject(o);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 353 */     stream.defaultReadObject();
/* 354 */     int rowCount = stream.readInt();
/* 355 */     this.data = new Object[rowCount][];
/* 356 */     for (int r = 0; r < rowCount; r++) {
/* 357 */       boolean isNotNull = stream.readBoolean();
/* 358 */       if (isNotNull) {
/* 359 */         int columnCount = stream.readInt();
/* 360 */         Object[] column = new Object[columnCount];
/* 361 */         this.data[r] = column;
/* 362 */         for (int c = 0; c < columnCount; c++)
/* 363 */           column[c] = readSerializedData(stream); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object readSerializedData(ObjectInputStream stream) throws ClassNotFoundException, IOException {
/* 381 */     return stream.readObject();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 388 */     this.rows = 0;
/* 389 */     this.columns = 0;
/* 390 */     for (int i = 0; i < this.data.length; i++) {
/* 391 */       if (this.data[i] != null)
/* 392 */         Arrays.fill(this.data[i], (Object)null); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void copyColumn(int oldColumn, int newColumn) {
/* 405 */     for (int i = 0; i < getRowCount(); i++)
/* 407 */       setObject(i, newColumn, getObject(i, oldColumn)); 
/*     */   }
/*     */   
/*     */   protected void copyRow(int oldRow, int newRow) {
/* 420 */     ensureCapacity(newRow, getColumnCount());
/* 421 */     Object[] oldRowStorage = this.data[oldRow];
/* 422 */     if (oldRowStorage == null) {
/* 424 */       Object[] newRowStorage = this.data[newRow];
/* 425 */       if (newRowStorage != null)
/* 427 */         Arrays.fill(newRowStorage, (Object)null); 
/*     */     } else {
/* 432 */       this.data[newRow] = (Object[])oldRowStorage.clone();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ObjectTable.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */