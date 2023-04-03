/*     */ package org.postgresql.core.v2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Writer;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.Utils;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.StreamWrapper;
/*     */ 
/*     */ class SimpleParameterList implements ParameterList {
/*     */   private final Object[] paramValues;
/*     */   
/*     */   private final boolean useEStringSyntax;
/*     */   
/*     */   SimpleParameterList(int paramCount, boolean useEStringSyntax) {
/*  32 */     this.paramValues = new Object[paramCount];
/*  33 */     this.useEStringSyntax = useEStringSyntax;
/*     */   }
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType) {}
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType, int precision) {}
/*     */   
/*     */   public int getInParameterCount() {
/*  39 */     return this.paramValues.length;
/*     */   }
/*     */   
/*     */   public int getParameterCount() {
/*  43 */     return this.paramValues.length;
/*     */   }
/*     */   
/*     */   public int getOutParameterCount() {
/*  47 */     return 1;
/*     */   }
/*     */   
/*     */   public int[] getTypeOIDs() {
/*  50 */     return null;
/*     */   }
/*     */   
/*     */   public void setIntParameter(int index, int value) throws SQLException {
/*  54 */     setLiteralParameter(index, "" + value, 23);
/*     */   }
/*     */   
/*     */   public void setLiteralParameter(int index, String value, int oid) throws SQLException {
/*  58 */     if (index < 1 || index > this.paramValues.length)
/*  59 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  61 */     this.paramValues[index - 1] = value;
/*     */   }
/*     */   
/*     */   public void setStringParameter(int index, String value, int oid) throws SQLException {
/*  65 */     StringBuffer sbuf = new StringBuffer(2 + value.length() * 11 / 10);
/*  67 */     if (this.useEStringSyntax)
/*  68 */       sbuf.append(' ').append('E'); 
/*  69 */     sbuf.append('\'');
/*  70 */     Utils.appendEscapedLiteral(sbuf, value, false);
/*  71 */     sbuf.append('\'');
/*  73 */     setLiteralParameter(index, sbuf.toString(), oid);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, byte[] data, int offset, int length) throws SQLException {
/*  77 */     if (index < 1 || index > this.paramValues.length)
/*  78 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  80 */     this.paramValues[index - 1] = new StreamWrapper(data, offset, length);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, InputStream stream, int length) throws SQLException {
/*  84 */     if (index < 1 || index > this.paramValues.length)
/*  85 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  87 */     this.paramValues[index - 1] = new StreamWrapper(stream, length);
/*     */   }
/*     */   
/*     */   public void setNull(int index, int oid) throws SQLException {
/*  91 */     if (index < 1 || index > this.paramValues.length)
/*  92 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  94 */     this.paramValues[index - 1] = NULL_OBJECT;
/*     */   }
/*     */   
/*     */   public String toString(int index) {
/*  98 */     if (index < 1 || index > this.paramValues.length)
/*  99 */       throw new IllegalArgumentException("Parameter index " + index + " out of range"); 
/* 101 */     if (this.paramValues[index - 1] == null)
/* 102 */       return "?"; 
/* 103 */     if (this.paramValues[index - 1] == NULL_OBJECT)
/* 104 */       return "NULL"; 
/* 106 */     return this.paramValues[index - 1].toString();
/*     */   }
/*     */   
/*     */   private void streamBytea(StreamWrapper param, Writer encodingWriter) throws IOException {
/* 117 */     InputStream stream = param.getStream();
/* 118 */     char[] buffer = { '\\', '\\', Character.MIN_VALUE, Character.MIN_VALUE, Character.MIN_VALUE };
/* 120 */     if (this.useEStringSyntax) {
/* 122 */       encodingWriter.write(32);
/* 123 */       encodingWriter.write(69);
/*     */     } 
/* 126 */     encodingWriter.write(39);
/* 127 */     for (int remaining = param.getLength(); remaining > 0; remaining--) {
/* 129 */       int nextByte = stream.read();
/* 131 */       buffer[2] = (char)(48 + (nextByte >> 6 & 0x3));
/* 132 */       buffer[3] = (char)(48 + (nextByte >> 3 & 0x7));
/* 133 */       buffer[4] = (char)(48 + (nextByte & 0x7));
/* 135 */       encodingWriter.write(buffer, 0, 5);
/*     */     } 
/* 138 */     encodingWriter.write(39);
/*     */   }
/*     */   
/*     */   void writeV2Value(int index, Writer encodingWriter) throws IOException {
/* 143 */     if (this.paramValues[index - 1] instanceof StreamWrapper) {
/* 145 */       streamBytea((StreamWrapper)this.paramValues[index - 1], encodingWriter);
/*     */     } else {
/* 149 */       encodingWriter.write((String)this.paramValues[index - 1]);
/*     */     } 
/*     */   }
/*     */   
/*     */   void checkAllParametersSet() throws SQLException {
/* 154 */     for (int i = 0; i < this.paramValues.length; i++) {
/* 156 */       if (this.paramValues[i] == null)
/* 157 */         throw new PSQLException(GT.tr("No value specified for parameter {0}.", new Integer(i + 1)), PSQLState.INVALID_PARAMETER_VALUE); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterList copy() {
/* 162 */     SimpleParameterList newCopy = new SimpleParameterList(this.paramValues.length, this.useEStringSyntax);
/* 163 */     System.arraycopy(this.paramValues, 0, newCopy.paramValues, 0, this.paramValues.length);
/* 164 */     return newCopy;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 168 */     Arrays.fill(this.paramValues, (Object)null);
/*     */   }
/*     */   
/* 179 */   private static final String NULL_OBJECT = new String("NULL");
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v2\SimpleParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */