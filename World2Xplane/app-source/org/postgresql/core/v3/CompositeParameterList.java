/*     */ package org.postgresql.core.v3;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ 
/*     */ class CompositeParameterList implements V3ParameterList {
/*     */   private final int total;
/*     */   
/*     */   private final SimpleParameterList[] subparams;
/*     */   
/*     */   private final int[] offsets;
/*     */   
/*     */   CompositeParameterList(SimpleParameterList[] subparams, int[] offsets) {
/*  30 */     this.subparams = subparams;
/*  31 */     this.offsets = offsets;
/*  32 */     this.total = offsets[offsets.length - 1] + subparams[offsets.length - 1].getInParameterCount();
/*     */   }
/*     */   
/*     */   private final int findSubParam(int index) throws SQLException {
/*  36 */     if (index < 1 || index > this.total)
/*  37 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.total) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  39 */     for (int i = this.offsets.length - 1; i >= 0; i--) {
/*  40 */       if (this.offsets[i] < index)
/*  41 */         return i; 
/*     */     } 
/*  43 */     throw new IllegalArgumentException("I am confused; can't find a subparam for index " + index);
/*     */   }
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType) {}
/*     */   
/*     */   public int getDirection(int i) {
/*  50 */     return 0;
/*     */   }
/*     */   
/*     */   public int getParameterCount() {
/*  53 */     return this.total;
/*     */   }
/*     */   
/*     */   public int getInParameterCount() {
/*  56 */     return this.total;
/*     */   }
/*     */   
/*     */   public int getOutParameterCount() {
/*  60 */     return 0;
/*     */   }
/*     */   
/*     */   public int[] getTypeOIDs() {
/*  64 */     int[] oids = new int[this.total];
/*  65 */     for (int i = 0; i < this.offsets.length; i++) {
/*  66 */       int[] subOids = this.subparams[i].getTypeOIDs();
/*  67 */       System.arraycopy(subOids, 0, oids, this.offsets[i], subOids.length);
/*     */     } 
/*  69 */     return oids;
/*     */   }
/*     */   
/*     */   public void setIntParameter(int index, int value) throws SQLException {
/*  73 */     int sub = findSubParam(index);
/*  74 */     this.subparams[sub].setIntParameter(index - this.offsets[sub], value);
/*     */   }
/*     */   
/*     */   public void setLiteralParameter(int index, String value, int oid) throws SQLException {
/*  78 */     int sub = findSubParam(index);
/*  79 */     this.subparams[sub].setStringParameter(index - this.offsets[sub], value, oid);
/*     */   }
/*     */   
/*     */   public void setStringParameter(int index, String value, int oid) throws SQLException {
/*  83 */     int sub = findSubParam(index);
/*  84 */     this.subparams[sub].setStringParameter(index - this.offsets[sub], value, oid);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, byte[] data, int offset, int length) throws SQLException {
/*  88 */     int sub = findSubParam(index);
/*  89 */     this.subparams[sub].setBytea(index - this.offsets[sub], data, offset, length);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, InputStream stream, int length) throws SQLException {
/*  93 */     int sub = findSubParam(index);
/*  94 */     this.subparams[sub].setBytea(index - this.offsets[sub], stream, length);
/*     */   }
/*     */   
/*     */   public void setNull(int index, int oid) throws SQLException {
/*  98 */     int sub = findSubParam(index);
/*  99 */     this.subparams[sub].setNull(index - this.offsets[sub], oid);
/*     */   }
/*     */   
/*     */   public String toString(int index) {
/*     */     try {
/* 105 */       int sub = findSubParam(index);
/* 106 */       return this.subparams[sub].toString(index - this.offsets[sub]);
/* 108 */     } catch (SQLException e) {
/* 110 */       throw new IllegalStateException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterList copy() {
/* 115 */     SimpleParameterList[] copySub = new SimpleParameterList[this.subparams.length];
/* 116 */     for (int sub = 0; sub < this.subparams.length; sub++)
/* 117 */       copySub[sub] = (SimpleParameterList)this.subparams[sub].copy(); 
/* 119 */     return new CompositeParameterList(copySub, this.offsets);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 123 */     for (int sub = 0; sub < this.subparams.length; sub++)
/* 125 */       this.subparams[sub].clear(); 
/*     */   }
/*     */   
/*     */   public SimpleParameterList[] getSubparams() {
/* 130 */     return this.subparams;
/*     */   }
/*     */   
/*     */   public void checkAllParametersSet() throws SQLException {
/* 134 */     for (int sub = 0; sub < this.subparams.length; sub++)
/* 135 */       this.subparams[sub].checkAllParametersSet(); 
/*     */   }
/*     */   
/*     */   public void convertFunctionOutParameters() {
/* 140 */     for (int sub = 0; sub < this.subparams.length; sub++)
/* 141 */       this.subparams[sub].convertFunctionOutParameters(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\CompositeParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */