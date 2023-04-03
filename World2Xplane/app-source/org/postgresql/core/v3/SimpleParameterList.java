/*     */ package org.postgresql.core.v3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ import org.postgresql.core.PGStream;
/*     */ import org.postgresql.core.ParameterList;
/*     */ import org.postgresql.core.Utils;
/*     */ import org.postgresql.util.GT;
/*     */ import org.postgresql.util.PSQLException;
/*     */ import org.postgresql.util.PSQLState;
/*     */ import org.postgresql.util.StreamWrapper;
/*     */ 
/*     */ class SimpleParameterList implements V3ParameterList {
/*     */   private static final int IN = 1;
/*     */   
/*     */   private static final int OUT = 2;
/*     */   
/*     */   private static final int INOUT = 3;
/*     */   
/*     */   private final Object[] paramValues;
/*     */   
/*     */   private final int[] paramTypes;
/*     */   
/*     */   private final int[] direction;
/*     */   
/*     */   private final byte[][] encoded;
/*     */   
/*     */   private final ProtocolConnectionImpl protoConnection;
/*     */   
/*     */   SimpleParameterList(int paramCount, ProtocolConnectionImpl protoConnection) {
/*  36 */     this.paramValues = new Object[paramCount];
/*  37 */     this.paramTypes = new int[paramCount];
/*  38 */     this.encoded = new byte[paramCount][];
/*  39 */     this.direction = new int[paramCount];
/*  40 */     this.protoConnection = protoConnection;
/*     */   }
/*     */   
/*     */   public void registerOutParameter(int index, int sqlType) throws SQLException {
/*  45 */     if (index < 1 || index > this.paramValues.length)
/*  46 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  48 */     this.direction[index - 1] = this.direction[index - 1] | 0x2;
/*     */   }
/*     */   
/*     */   private void bind(int index, Object value, int oid) throws SQLException {
/*  52 */     if (index < 1 || index > this.paramValues.length)
/*  53 */       throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.", new Object[] { new Integer(index), new Integer(this.paramValues.length) }), PSQLState.INVALID_PARAMETER_VALUE); 
/*  55 */     index--;
/*  57 */     this.encoded[index] = null;
/*  58 */     this.paramValues[index] = value;
/*  59 */     this.direction[index] = this.direction[index] | 0x1;
/*  65 */     if (oid == 0 && this.paramTypes[index] != 0 && value == NULL_OBJECT)
/*     */       return; 
/*  68 */     this.paramTypes[index] = oid;
/*     */   }
/*     */   
/*     */   public int getParameterCount() {
/*  73 */     return this.paramValues.length;
/*     */   }
/*     */   
/*     */   public int getOutParameterCount() {
/*  77 */     int count = 0;
/*  78 */     for (int i = this.paramTypes.length; --i >= 0;) {
/*  80 */       if ((this.direction[i] & 0x2) == 2)
/*  82 */         count++; 
/*     */     } 
/*  86 */     if (count == 0)
/*  87 */       count = 1; 
/*  88 */     return count;
/*     */   }
/*     */   
/*     */   public int getInParameterCount() {
/*  93 */     int count = 0;
/*  94 */     for (int i = 0; i < this.paramTypes.length; i++) {
/*  96 */       if (this.direction[i] != 2)
/*  98 */         count++; 
/*     */     } 
/* 101 */     return count;
/*     */   }
/*     */   
/*     */   public void setIntParameter(int index, int value) throws SQLException {
/* 105 */     byte[] data = new byte[4];
/* 106 */     data[3] = (byte)value;
/* 107 */     data[2] = (byte)(value >> 8);
/* 108 */     data[1] = (byte)(value >> 16);
/* 109 */     data[0] = (byte)(value >> 24);
/* 110 */     bind(index, data, 23);
/*     */   }
/*     */   
/*     */   public void setLiteralParameter(int index, String value, int oid) throws SQLException {
/* 114 */     bind(index, value, oid);
/*     */   }
/*     */   
/*     */   public void setStringParameter(int index, String value, int oid) throws SQLException {
/* 118 */     bind(index, value, oid);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, byte[] data, int offset, int length) throws SQLException {
/* 122 */     bind(index, new StreamWrapper(data, offset, length), 17);
/*     */   }
/*     */   
/*     */   public void setBytea(int index, InputStream stream, int length) throws SQLException {
/* 126 */     bind(index, new StreamWrapper(stream, length), 17);
/*     */   }
/*     */   
/*     */   public void setNull(int index, int oid) throws SQLException {
/* 130 */     bind(index, NULL_OBJECT, oid);
/*     */   }
/*     */   
/*     */   public String toString(int index) {
/* 134 */     index--;
/* 135 */     if (this.paramValues[index] == null)
/* 136 */       return "?"; 
/* 137 */     if (this.paramValues[index] == NULL_OBJECT)
/* 138 */       return "NULL"; 
/* 140 */     String param = this.paramValues[index].toString();
/* 141 */     boolean hasBackslash = (param.indexOf('\\') != -1);
/* 144 */     StringBuffer p = new StringBuffer(3 + param.length() * 11 / 10);
/* 146 */     boolean standardConformingStrings = false;
/* 147 */     boolean supportsEStringSyntax = false;
/* 148 */     if (this.protoConnection != null) {
/* 149 */       standardConformingStrings = this.protoConnection.getStandardConformingStrings();
/* 150 */       supportsEStringSyntax = (this.protoConnection.getServerVersion().compareTo("8.1") >= 0);
/*     */     } 
/* 153 */     if (hasBackslash && !standardConformingStrings && supportsEStringSyntax)
/* 154 */       p.append('E'); 
/* 156 */     p.append('\'');
/*     */     try {
/* 158 */       p = Utils.appendEscapedLiteral(p, param, standardConformingStrings);
/* 159 */     } catch (SQLException sqle) {
/* 167 */       p.append(param);
/*     */     } 
/* 169 */     p.append('\'');
/* 170 */     return p.toString();
/*     */   }
/*     */   
/*     */   public void checkAllParametersSet() throws SQLException {
/* 175 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 177 */       if (this.direction[i] != 2 && this.paramValues[i] == null)
/* 178 */         throw new PSQLException(GT.tr("No value specified for parameter {0}.", new Integer(i + 1)), PSQLState.INVALID_PARAMETER_VALUE); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void convertFunctionOutParameters() {
/* 184 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 186 */       if (this.direction[i] == 2) {
/* 188 */         this.paramTypes[i] = 2278;
/* 189 */         this.paramValues[i] = "null";
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void streamBytea(PGStream pgStream, StreamWrapper wrapper) throws IOException {
/* 199 */     byte[] rawData = wrapper.getBytes();
/* 200 */     if (rawData != null) {
/* 202 */       pgStream.Send(rawData, wrapper.getOffset(), wrapper.getLength());
/*     */       return;
/*     */     } 
/* 206 */     pgStream.SendStream(wrapper.getStream(), wrapper.getLength());
/*     */   }
/*     */   
/*     */   public int[] getTypeOIDs() {
/* 210 */     return this.paramTypes;
/*     */   }
/*     */   
/*     */   int getTypeOID(int index) {
/* 218 */     return this.paramTypes[index - 1];
/*     */   }
/*     */   
/*     */   boolean hasUnresolvedTypes() {
/* 222 */     for (int i = 0; i < this.paramTypes.length; i++) {
/* 223 */       if (this.paramTypes[i] == 0)
/* 224 */         return true; 
/*     */     } 
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   void setResolvedType(int index, int oid) {
/* 231 */     if (this.paramTypes[index - 1] == 0) {
/* 232 */       this.paramTypes[index - 1] = oid;
/* 233 */     } else if (this.paramTypes[index - 1] != oid) {
/* 234 */       throw new IllegalArgumentException("Can't change resolved type for param: " + index + " from " + this.paramTypes[index - 1] + " to " + oid);
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean isNull(int index) {
/* 239 */     return (this.paramValues[index - 1] == NULL_OBJECT);
/*     */   }
/*     */   
/*     */   boolean isBinary(int index) {
/* 244 */     return this.paramValues[index - 1] instanceof StreamWrapper;
/*     */   }
/*     */   
/*     */   int getV3Length(int index) {
/* 248 */     index--;
/* 251 */     if (this.paramValues[index] == NULL_OBJECT)
/* 252 */       throw new IllegalArgumentException("can't getV3Length() on a null parameter"); 
/* 255 */     if (this.paramValues[index] instanceof byte[])
/* 256 */       return ((byte[])this.paramValues[index]).length; 
/* 259 */     if (this.paramValues[index] instanceof StreamWrapper)
/* 260 */       return ((StreamWrapper)this.paramValues[index]).getLength(); 
/* 263 */     if (this.encoded[index] == null)
/* 266 */       this.encoded[index] = Utils.encodeUTF8(this.paramValues[index].toString()); 
/* 269 */     return (this.encoded[index]).length;
/*     */   }
/*     */   
/*     */   void writeV3Value(int index, PGStream pgStream) throws IOException {
/* 273 */     index--;
/* 276 */     if (this.paramValues[index] == NULL_OBJECT)
/* 277 */       throw new IllegalArgumentException("can't writeV3Value() on a null parameter"); 
/* 280 */     if (this.paramValues[index] instanceof byte[]) {
/* 282 */       pgStream.Send((byte[])this.paramValues[index]);
/*     */       return;
/*     */     } 
/* 287 */     if (this.paramValues[index] instanceof StreamWrapper) {
/* 289 */       streamBytea(pgStream, (StreamWrapper)this.paramValues[index]);
/*     */       return;
/*     */     } 
/* 294 */     if (this.encoded[index] == null)
/* 295 */       this.encoded[index] = Utils.encodeUTF8((String)this.paramValues[index]); 
/* 296 */     pgStream.Send(this.encoded[index]);
/*     */   }
/*     */   
/*     */   public ParameterList copy() {
/* 302 */     SimpleParameterList newCopy = new SimpleParameterList(this.paramValues.length, this.protoConnection);
/* 303 */     System.arraycopy(this.paramValues, 0, newCopy.paramValues, 0, this.paramValues.length);
/* 304 */     System.arraycopy(this.paramTypes, 0, newCopy.paramTypes, 0, this.paramTypes.length);
/* 305 */     System.arraycopy(this.direction, 0, newCopy.direction, 0, this.direction.length);
/* 306 */     return newCopy;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 310 */     Arrays.fill(this.paramValues, (Object)null);
/* 311 */     Arrays.fill(this.paramTypes, 0);
/* 312 */     Arrays.fill((Object[])this.encoded, (Object)null);
/* 313 */     Arrays.fill(this.direction, 0);
/*     */   }
/*     */   
/*     */   public SimpleParameterList[] getSubparams() {
/* 316 */     return null;
/*     */   }
/*     */   
/* 329 */   private static final Object NULL_OBJECT = new Object();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\v3\SimpleParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */