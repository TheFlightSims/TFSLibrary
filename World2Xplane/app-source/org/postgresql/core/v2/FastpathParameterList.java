/*     */ package org.postgresql.core.v2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.StreamWrapper;
/*     */ 
/*     */ class FastpathParameterList implements ParameterList {
/*     */   private final Object[] paramValues;
/*     */   
/*     */   FastpathParameterList(int paramCount) {
/*  34 */     this.paramValues = new Object[paramCount];
/*     */   }
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType) {}
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType, int precision) {}
/*     */   
/*     */   public int getInParameterCount() {
/*  41 */     return this.paramValues.length;
/*     */   }
/*     */   
/*     */   public int getOutParameterCount() {
/*  45 */     return 0;
/*     */   }
/*     */   
/*     */   public int getParameterCount() {
/*  49 */     return this.paramValues.length;
/*     */   }
/*     */   
/*     */   public int[] getTypeOIDs() {
/*  52 */     return null;
/*     */   }
/*     */   
/*     */   public void setIntParameter(int index, int value) throws SQLException {
/*  56 */     if (index < 1 || index > this.paramValues.length)
/*  57 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  59 */     byte[] data = new byte[4];
/*  60 */     data[3] = (byte)value;
/*  61 */     data[2] = (byte)(value >> 8);
/*  62 */     data[1] = (byte)(value >> 16);
/*  63 */     data[0] = (byte)(value >> 24);
/*  65 */     this.paramValues[index - 1] = data;
/*     */   }
/*     */   
/*     */   public void setLiteralParameter(int index, String value, int oid) throws SQLException {
/*  70 */     throw new IllegalArgumentException("can't setLiteralParameter() on a fastpath parameter");
/*     */   }
/*     */   
/*     */   public void setStringParameter(int index, String value, int oid) throws SQLException {
/*  74 */     this.paramValues[index - 1] = value;
/*     */   }
/*     */   
/*     */   public void setBytea(int index, byte[] data, int offset, int length) throws SQLException {
/*  78 */     if (index < 1 || index > this.paramValues.length)
/*  79 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  81 */     this.paramValues[index - 1] = new StreamWrapper(data, offset, length);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, InputStream stream, int length) throws SQLException {
/*  85 */     if (index < 1 || index > this.paramValues.length)
/*  86 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  88 */     this.paramValues[index - 1] = new StreamWrapper(stream, length);
/*     */   }
/*     */   
/*     */   public void setNull(int index, int oid) throws SQLException {
/*  92 */     throw new IllegalArgumentException("can't setNull() on a v2 fastpath parameter");
/*     */   }
/*     */   
/*     */   public String toString(int index) {
/*  96 */     if (index < 1 || index > this.paramValues.length)
/*  97 */       throw new IllegalArgumentException("parameter " + index + " out of range"); 
/*  99 */     return "<fastpath parameter>";
/*     */   }
/*     */   
/*     */   private void copyStream(PGStream pgStream, StreamWrapper wrapper) throws IOException {
/* 103 */     byte[] rawData = wrapper.getBytes();
/* 104 */     if (rawData != null) {
/* 106 */       pgStream.Send(rawData, wrapper.getOffset(), wrapper.getLength());
/*     */       return;
/*     */     } 
/* 110 */     pgStream.SendStream(wrapper.getStream(), wrapper.getLength());
/*     */   }
/*     */   
/*     */   void writeV2FastpathValue(int index, PGStream pgStream) throws IOException {
/* 114 */     index--;
/* 116 */     if (this.paramValues[index] instanceof StreamWrapper) {
/* 118 */       StreamWrapper wrapper = (StreamWrapper)this.paramValues[index];
/* 119 */       pgStream.SendInteger4(wrapper.getLength());
/* 120 */       copyStream(pgStream, wrapper);
/* 122 */     } else if (this.paramValues[index] instanceof byte[]) {
/* 124 */       byte[] data = (byte[])this.paramValues[index];
/* 125 */       pgStream.SendInteger4(data.length);
/* 126 */       pgStream.Send(data);
/* 128 */     } else if (this.paramValues[index] instanceof String) {
/* 130 */       byte[] data = pgStream.getEncoding().encode((String)this.paramValues[index]);
/* 131 */       pgStream.SendInteger4(data.length);
/* 132 */       pgStream.Send(data);
/*     */     } else {
/* 136 */       throw new IllegalArgumentException("don't know how to stream parameter " + index);
/*     */     } 
/*     */   }
/*     */   
/*     */   void checkAllParametersSet() throws SQLException {
/* 141 */     for (int i = 0; i < this.paramValues.length; i++) {
/* 143 */       if (this.paramValues[i] == null)
/* 144 */         throw new PSQLException(GT.tr("No value specified for parameter {0}.", new Integer(i + 1)), PSQLState.INVALID_PARAMETER_VALUE); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterList copy() {
/* 149 */     FastpathParameterList newCopy = new FastpathParameterList(this.paramValues.length);
/* 150 */     System.arraycopy(this.paramValues, 0, newCopy.paramValues, 0, this.paramValues.length);
/* 151 */     return newCopy;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 155 */     Arrays.fill(this.paramValues, (Object)null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v2\FastpathParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */