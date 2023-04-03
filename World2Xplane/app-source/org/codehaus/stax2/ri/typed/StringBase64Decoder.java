/*     */ package org.codehaus.stax2.ri.typed;
/*     */ 
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ 
/*     */ public final class StringBase64Decoder extends Base64DecoderBase {
/*     */   String _currSegment;
/*     */   
/*     */   int _currSegmentPtr;
/*     */   
/*     */   int _currSegmentEnd;
/*     */   
/*     */   public void init(Base64Variant variant, boolean firstChunk, String segment) {
/*  42 */     this._variant = variant;
/*  43 */     if (firstChunk)
/*  44 */       this._state = 0; 
/*  46 */     this._currSegment = segment;
/*  47 */     this._currSegmentPtr = 0;
/*  48 */     this._currSegmentEnd = segment.length();
/*     */   }
/*     */   
/*     */   public int decode(byte[] resultBuffer, int resultOffset, int maxLength) throws IllegalArgumentException {
/*  62 */     int origResultOffset = resultOffset;
/*  63 */     int resultBufferEnd = resultOffset + maxLength;
/*     */     while (true) {
/*     */       char ch;
/*     */       int bits;
/*  67 */       switch (this._state) {
/*     */         case 0:
/*  73 */           label60: while (this._currSegmentPtr < this._currSegmentEnd) {
/*  76 */             char c = this._currSegment.charAt(this._currSegmentPtr++);
/*  77 */             if (c > ' ') {
/*  78 */               int i = this._variant.decodeBase64Char(c);
/*  79 */               if (i < 0)
/*  80 */                 throw reportInvalidChar(c, 0); 
/*  82 */               this._decodedData = i;
/*     */               break label60;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 1:
/*  89 */           if (this._currSegmentPtr >= this._currSegmentEnd) {
/*  90 */             this._state = 1;
/*     */             break;
/*     */           } 
/*  93 */           ch = this._currSegment.charAt(this._currSegmentPtr++);
/*  94 */           bits = this._variant.decodeBase64Char(ch);
/*  95 */           if (bits < 0)
/*  96 */             throw reportInvalidChar(ch, 1); 
/*  98 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 2:
/* 105 */           if (this._currSegmentPtr >= this._currSegmentEnd) {
/* 106 */             this._state = 2;
/*     */             break;
/*     */           } 
/* 109 */           ch = this._currSegment.charAt(this._currSegmentPtr++);
/* 110 */           bits = this._variant.decodeBase64Char(ch);
/* 111 */           if (bits < 0) {
/* 112 */             if (bits != -2)
/* 113 */               throw reportInvalidChar(ch, 2); 
/* 116 */             this._state = 7;
/*     */             continue;
/*     */           } 
/* 119 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 3:
/* 126 */           if (this._currSegmentPtr >= this._currSegmentEnd) {
/* 127 */             this._state = 3;
/*     */             break;
/*     */           } 
/* 130 */           ch = this._currSegment.charAt(this._currSegmentPtr++);
/* 131 */           bits = this._variant.decodeBase64Char(ch);
/* 132 */           if (bits < 0) {
/* 133 */             if (bits != -2)
/* 134 */               throw reportInvalidChar(ch, 3); 
/* 142 */             this._decodedData >>= 2;
/* 143 */             this._state = 5;
/*     */             continue;
/*     */           } 
/* 147 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 4:
/* 152 */           if (resultOffset >= resultBufferEnd) {
/* 153 */             this._state = 4;
/*     */             break;
/*     */           } 
/* 156 */           resultBuffer[resultOffset++] = (byte)(this._decodedData >> 16);
/*     */         case 5:
/* 160 */           if (resultOffset >= resultBufferEnd) {
/* 161 */             this._state = 5;
/*     */             break;
/*     */           } 
/* 164 */           resultBuffer[resultOffset++] = (byte)(this._decodedData >> 8);
/*     */         case 6:
/* 168 */           if (resultOffset >= resultBufferEnd) {
/* 169 */             this._state = 6;
/*     */             break;
/*     */           } 
/* 172 */           resultBuffer[resultOffset++] = (byte)this._decodedData;
/* 173 */           this._state = 0;
/*     */           continue;
/*     */         case 7:
/* 178 */           if (this._currSegmentPtr >= this._currSegmentEnd)
/*     */             break; 
/* 182 */           ch = this._currSegment.charAt(this._currSegmentPtr++);
/* 183 */           if (!this._variant.usesPaddingChar(ch))
/* 184 */             throw reportInvalidChar(ch, 3, "expected padding character '='"); 
/* 187 */           this._state = 6;
/* 188 */           this._decodedData >>= 4;
/*     */           continue;
/*     */       } 
/* 194 */       throw new IllegalStateException("Illegal internal state " + this._state);
/*     */     } 
/* 197 */     return resultOffset - origResultOffset;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\StringBase64Decoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */