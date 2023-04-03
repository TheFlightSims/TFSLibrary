/*     */ package org.codehaus.stax2.typed;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public final class Base64Variant {
/*     */   static final char PADDING_CHAR_NONE = '\000';
/*     */   
/*     */   public static final int BASE64_VALUE_INVALID = -1;
/*     */   
/*     */   public static final int BASE64_VALUE_PADDING = -2;
/*     */   
/*  69 */   private final int[] _asciiToBase64 = new int[128];
/*     */   
/*  75 */   private final char[] _base64ToAsciiC = new char[64];
/*     */   
/*  81 */   private final byte[] _base64ToAsciiB = new byte[64];
/*     */   
/*     */   final String _name;
/*     */   
/*     */   final boolean _usesPadding;
/*     */   
/*     */   final char _paddingChar;
/*     */   
/*     */   final int _maxLineLength;
/*     */   
/*     */   public Base64Variant(String name, String base64Alphabet, boolean usesPadding, char paddingChar, int maxLineLength) {
/* 122 */     this._name = name;
/* 123 */     this._usesPadding = usesPadding;
/* 124 */     this._paddingChar = paddingChar;
/* 125 */     this._maxLineLength = maxLineLength;
/* 130 */     int alphaLen = base64Alphabet.length();
/* 131 */     if (alphaLen != 64)
/* 132 */       throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + alphaLen + ")"); 
/* 136 */     base64Alphabet.getChars(0, alphaLen, this._base64ToAsciiC, 0);
/* 137 */     Arrays.fill(this._asciiToBase64, -1);
/* 138 */     for (int i = 0; i < alphaLen; i++) {
/* 139 */       char alpha = this._base64ToAsciiC[i];
/* 140 */       this._base64ToAsciiB[i] = (byte)alpha;
/* 141 */       this._asciiToBase64[alpha] = i;
/*     */     } 
/* 145 */     if (usesPadding)
/* 146 */       this._asciiToBase64[paddingChar] = -2; 
/*     */   }
/*     */   
/*     */   public Base64Variant(Base64Variant base, String name, int maxLineLength) {
/* 157 */     this(base, name, base._usesPadding, base._paddingChar, maxLineLength);
/*     */   }
/*     */   
/*     */   public Base64Variant(Base64Variant base, String name, boolean usesPadding, char paddingChar, int maxLineLength) {
/* 167 */     this._name = name;
/* 168 */     byte[] srcB = base._base64ToAsciiB;
/* 169 */     System.arraycopy(srcB, 0, this._base64ToAsciiB, 0, srcB.length);
/* 170 */     char[] srcC = base._base64ToAsciiC;
/* 171 */     System.arraycopy(srcC, 0, this._base64ToAsciiC, 0, srcC.length);
/* 172 */     int[] srcV = base._asciiToBase64;
/* 173 */     System.arraycopy(srcV, 0, this._asciiToBase64, 0, srcV.length);
/* 175 */     this._usesPadding = usesPadding;
/* 176 */     this._paddingChar = paddingChar;
/* 177 */     this._maxLineLength = maxLineLength;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 186 */     return this._name;
/*     */   }
/*     */   
/*     */   public boolean usesPadding() {
/* 188 */     return this._usesPadding;
/*     */   }
/*     */   
/*     */   public boolean usesPaddingChar(char c) {
/* 189 */     return (c == this._paddingChar);
/*     */   }
/*     */   
/*     */   public char getPaddingChar() {
/* 190 */     return this._paddingChar;
/*     */   }
/*     */   
/*     */   public byte getPaddingByte() {
/* 191 */     return (byte)this._paddingChar;
/*     */   }
/*     */   
/*     */   public int getMaxLineLength() {
/* 193 */     return this._maxLineLength;
/*     */   }
/*     */   
/*     */   public int decodeBase64Char(char c) {
/* 206 */     int ch = c;
/* 207 */     return (ch <= 127) ? this._asciiToBase64[ch] : -1;
/*     */   }
/*     */   
/*     */   public int decodeBase64Byte(byte b) {
/* 212 */     int ch = b;
/* 213 */     return (ch <= 127) ? this._asciiToBase64[ch] : -1;
/*     */   }
/*     */   
/*     */   public char encodeBase64BitsAsChar(int value) {
/* 227 */     return this._base64ToAsciiC[value];
/*     */   }
/*     */   
/*     */   public int encodeBase64Chunk(int b24, char[] buffer, int ptr) {
/* 236 */     buffer[ptr++] = this._base64ToAsciiC[b24 >> 18 & 0x3F];
/* 237 */     buffer[ptr++] = this._base64ToAsciiC[b24 >> 12 & 0x3F];
/* 238 */     buffer[ptr++] = this._base64ToAsciiC[b24 >> 6 & 0x3F];
/* 239 */     buffer[ptr++] = this._base64ToAsciiC[b24 & 0x3F];
/* 240 */     return ptr;
/*     */   }
/*     */   
/*     */   public int encodeBase64Partial(int bits, int outputBytes, char[] buffer, int outPtr) {
/* 253 */     buffer[outPtr++] = this._base64ToAsciiC[bits >> 18 & 0x3F];
/* 254 */     buffer[outPtr++] = this._base64ToAsciiC[bits >> 12 & 0x3F];
/* 255 */     if (this._usesPadding) {
/* 256 */       buffer[outPtr++] = (outputBytes == 2) ? this._base64ToAsciiC[bits >> 6 & 0x3F] : this._paddingChar;
/* 258 */       buffer[outPtr++] = this._paddingChar;
/* 260 */     } else if (outputBytes == 2) {
/* 261 */       buffer[outPtr++] = this._base64ToAsciiC[bits >> 6 & 0x3F];
/*     */     } 
/* 264 */     return outPtr;
/*     */   }
/*     */   
/*     */   public byte encodeBase64BitsAsByte(int value) {
/* 270 */     return this._base64ToAsciiB[value];
/*     */   }
/*     */   
/*     */   public int encodeBase64Chunk(int b24, byte[] buffer, int ptr) {
/* 279 */     buffer[ptr++] = this._base64ToAsciiB[b24 >> 18 & 0x3F];
/* 280 */     buffer[ptr++] = this._base64ToAsciiB[b24 >> 12 & 0x3F];
/* 281 */     buffer[ptr++] = this._base64ToAsciiB[b24 >> 6 & 0x3F];
/* 282 */     buffer[ptr++] = this._base64ToAsciiB[b24 & 0x3F];
/* 283 */     return ptr;
/*     */   }
/*     */   
/*     */   public int encodeBase64Partial(int bits, int outputBytes, byte[] buffer, int outPtr) {
/* 296 */     buffer[outPtr++] = this._base64ToAsciiB[bits >> 18 & 0x3F];
/* 297 */     buffer[outPtr++] = this._base64ToAsciiB[bits >> 12 & 0x3F];
/* 298 */     if (this._usesPadding) {
/* 299 */       byte pb = (byte)this._paddingChar;
/* 300 */       buffer[outPtr++] = (outputBytes == 2) ? this._base64ToAsciiB[bits >> 6 & 0x3F] : pb;
/* 302 */       buffer[outPtr++] = pb;
/* 304 */     } else if (outputBytes == 2) {
/* 305 */       buffer[outPtr++] = this._base64ToAsciiB[bits >> 6 & 0x3F];
/*     */     } 
/* 308 */     return outPtr;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 318 */     return this._name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\typed\Base64Variant.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */