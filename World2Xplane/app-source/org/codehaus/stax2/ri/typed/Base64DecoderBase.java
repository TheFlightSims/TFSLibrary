/*     */ package org.codehaus.stax2.ri.typed;
/*     */ 
/*     */ import org.codehaus.stax2.ri.Stax2Util;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ 
/*     */ abstract class Base64DecoderBase {
/*     */   static final int STATE_INITIAL = 0;
/*     */   
/*     */   static final int STATE_VALID_1 = 1;
/*     */   
/*     */   static final int STATE_VALID_2 = 2;
/*     */   
/*     */   static final int STATE_VALID_3 = 3;
/*     */   
/*     */   static final int STATE_OUTPUT_3 = 4;
/*     */   
/*     */   static final int STATE_OUTPUT_2 = 5;
/*     */   
/*     */   static final int STATE_OUTPUT_1 = 6;
/*     */   
/*     */   static final int STATE_VALID_2_AND_PADDING = 7;
/*     */   
/*     */   static final int INT_SPACE = 32;
/*     */   
/*     */   Base64Variant _variant;
/*     */   
/* 101 */   int _state = 0;
/*     */   
/*     */   int _decodedData;
/*     */   
/* 113 */   Stax2Util.ByteAggregator _byteAggr = null;
/*     */   
/*     */   public abstract int decode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IllegalArgumentException;
/*     */   
/*     */   public final boolean hasData() {
/* 141 */     return (this._state >= 4 && this._state <= 6);
/*     */   }
/*     */   
/*     */   public final int endOfContent() {
/* 165 */     if (this._state == 0 || this._state == 4 || this._state == 5 || this._state == 6)
/* 167 */       return 0; 
/* 170 */     if (this._variant.usesPadding())
/* 171 */       return -1; 
/* 175 */     if (this._state == 2) {
/* 177 */       this._state = 6;
/* 178 */       this._decodedData >>= 4;
/* 179 */       return 1;
/*     */     } 
/* 180 */     if (this._state == 3) {
/* 182 */       this._decodedData >>= 2;
/* 183 */       this._state = 5;
/* 184 */       return 2;
/*     */     } 
/* 186 */     return -1;
/*     */   }
/*     */   
/*     */   public byte[] decodeCompletely() {
/* 202 */     Stax2Util.ByteAggregator aggr = getByteAggregator();
/* 203 */     byte[] buffer = aggr.startAggregation();
/*     */     while (true) {
/* 206 */       int offset = 0;
/* 207 */       int len = buffer.length;
/*     */       do {
/* 210 */         int readCount = decode(buffer, offset, len);
/* 212 */         if (readCount < 1) {
/* 220 */           int left = endOfContent();
/* 221 */           if (left < 0)
/* 222 */             throw new IllegalArgumentException("Incomplete base64 triplet at the end of decoded content"); 
/* 223 */           if (left <= 0)
/* 226 */             return aggr.aggregateAll(buffer, offset); 
/*     */         } else {
/* 228 */           offset += readCount;
/* 229 */           len -= readCount;
/*     */         } 
/* 230 */       } while (len > 0);
/* 233 */       buffer = aggr.addFullBlock(buffer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Stax2Util.ByteAggregator getByteAggregator() {
/* 239 */     if (this._byteAggr == null)
/* 240 */       this._byteAggr = new Stax2Util.ByteAggregator(); 
/* 242 */     return this._byteAggr;
/*     */   }
/*     */   
/*     */   protected IllegalArgumentException reportInvalidChar(char ch, int bindex) throws IllegalArgumentException {
/* 254 */     return reportInvalidChar(ch, bindex, null);
/*     */   }
/*     */   
/*     */   protected IllegalArgumentException reportInvalidChar(char ch, int bindex, String msg) throws IllegalArgumentException {
/*     */     String base;
/* 265 */     if (ch <= ' ') {
/* 266 */       base = "Illegal white space character (code 0x" + Integer.toHexString(ch) + ") as character #" + (bindex + 1) + " of 4-char base64 unit: can only used between units";
/* 267 */     } else if (this._variant.usesPaddingChar(ch)) {
/* 268 */       base = "Unexpected padding character ('" + this._variant.getPaddingChar() + "') as character #" + (bindex + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
/* 269 */     } else if (!Character.isDefined(ch) || Character.isISOControl(ch)) {
/* 271 */       base = "Illegal character (code 0x" + Integer.toHexString(ch) + ") in base64 content";
/*     */     } else {
/* 273 */       base = "Illegal character '" + ch + "' (code 0x" + Integer.toHexString(ch) + ") in base64 content";
/*     */     } 
/* 275 */     if (msg != null)
/* 276 */       base = base + ": " + msg; 
/* 278 */     return new IllegalArgumentException(base);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\Base64DecoderBase.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */