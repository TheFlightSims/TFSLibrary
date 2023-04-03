/*     */ package org.codehaus.stax2.ri.typed;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.codehaus.stax2.typed.Base64Variant;
/*     */ 
/*     */ public final class CharArrayBase64Decoder extends Base64DecoderBase {
/*     */   char[] _currSegment;
/*     */   
/*     */   int _currSegmentPtr;
/*     */   
/*     */   int _currSegmentEnd;
/*     */   
/*  39 */   final ArrayList _nextSegments = new ArrayList();
/*     */   
/*     */   int _lastSegmentOffset;
/*     */   
/*     */   int _lastSegmentEnd;
/*     */   
/*     */   int _nextSegmentIndex;
/*     */   
/*     */   public void init(Base64Variant variant, boolean firstChunk, char[] lastSegment, int lastOffset, int lastLen, List segments) {
/*  56 */     this._variant = variant;
/*  60 */     if (firstChunk)
/*  61 */       this._state = 0; 
/*  63 */     this._nextSegments.clear();
/*  64 */     if (segments == null || segments.isEmpty()) {
/*  65 */       this._currSegment = lastSegment;
/*  66 */       this._currSegmentPtr = lastOffset;
/*  67 */       this._currSegmentEnd = lastOffset + lastLen;
/*     */     } else {
/*  69 */       if (lastSegment == null)
/*  70 */         throw new IllegalArgumentException(); 
/*  73 */       Iterator it = segments.iterator();
/*  74 */       this._currSegment = it.next();
/*  75 */       this._currSegmentPtr = 0;
/*  76 */       this._currSegmentEnd = this._currSegment.length;
/*  78 */       while (it.hasNext())
/*  79 */         this._nextSegments.add(it.next()); 
/*  81 */       this._nextSegmentIndex = 0;
/*  84 */       this._nextSegments.add(lastSegment);
/*  85 */       this._lastSegmentOffset = lastOffset;
/*  86 */       this._lastSegmentEnd = lastOffset + lastLen;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int decode(byte[] resultBuffer, int resultOffset, int maxLength) throws IllegalArgumentException {
/* 101 */     int origResultOffset = resultOffset;
/* 102 */     int resultBufferEnd = resultOffset + maxLength;
/*     */     while (true) {
/*     */       char ch;
/*     */       int bits;
/* 106 */       switch (this._state) {
/*     */         case 0:
/* 112 */           label65: while (this._currSegmentPtr < this._currSegmentEnd || 
/* 113 */             nextSegment()) {
/* 117 */             char c = this._currSegment[this._currSegmentPtr++];
/* 118 */             if (c > ' ') {
/* 119 */               int i = this._variant.decodeBase64Char(c);
/* 120 */               if (i < 0)
/* 121 */                 throw reportInvalidChar(c, 0); 
/* 123 */               this._decodedData = i;
/*     */               break label65;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 1:
/* 130 */           if (this._currSegmentPtr >= this._currSegmentEnd && 
/* 131 */             !nextSegment()) {
/* 132 */             this._state = 1;
/*     */             break;
/*     */           } 
/* 136 */           ch = this._currSegment[this._currSegmentPtr++];
/* 137 */           bits = this._variant.decodeBase64Char(ch);
/* 138 */           if (bits < 0)
/* 139 */             throw reportInvalidChar(ch, 1); 
/* 141 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 2:
/* 148 */           if (this._currSegmentPtr >= this._currSegmentEnd && 
/* 149 */             !nextSegment()) {
/* 150 */             this._state = 2;
/*     */             break;
/*     */           } 
/* 154 */           ch = this._currSegment[this._currSegmentPtr++];
/* 155 */           bits = this._variant.decodeBase64Char(ch);
/* 156 */           if (bits < 0) {
/* 157 */             if (bits != -2)
/* 158 */               throw reportInvalidChar(ch, 2); 
/* 161 */             this._state = 7;
/*     */             continue;
/*     */           } 
/* 164 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 3:
/* 171 */           if (this._currSegmentPtr >= this._currSegmentEnd && 
/* 172 */             !nextSegment()) {
/* 173 */             this._state = 3;
/*     */             break;
/*     */           } 
/* 177 */           ch = this._currSegment[this._currSegmentPtr++];
/* 178 */           bits = this._variant.decodeBase64Char(ch);
/* 179 */           if (bits < 0) {
/* 180 */             if (bits != -2)
/* 181 */               throw reportInvalidChar(ch, 3); 
/* 189 */             this._decodedData >>= 2;
/* 190 */             this._state = 5;
/*     */             continue;
/*     */           } 
/* 194 */           this._decodedData = this._decodedData << 6 | bits;
/*     */         case 4:
/* 199 */           if (resultOffset >= resultBufferEnd) {
/* 200 */             this._state = 4;
/*     */             break;
/*     */           } 
/* 203 */           resultBuffer[resultOffset++] = (byte)(this._decodedData >> 16);
/*     */         case 5:
/* 207 */           if (resultOffset >= resultBufferEnd) {
/* 208 */             this._state = 5;
/*     */             break;
/*     */           } 
/* 211 */           resultBuffer[resultOffset++] = (byte)(this._decodedData >> 8);
/*     */         case 6:
/* 215 */           if (resultOffset >= resultBufferEnd) {
/* 216 */             this._state = 6;
/*     */             break;
/*     */           } 
/* 219 */           resultBuffer[resultOffset++] = (byte)this._decodedData;
/* 220 */           this._state = 0;
/*     */           continue;
/*     */         case 7:
/* 225 */           if (this._currSegmentPtr >= this._currSegmentEnd && 
/* 226 */             !nextSegment())
/*     */             break; 
/* 231 */           ch = this._currSegment[this._currSegmentPtr++];
/* 232 */           if (!this._variant.usesPaddingChar(ch))
/* 233 */             throw reportInvalidChar(ch, 3, "expected padding character '" + this._variant.getPaddingChar() + "'"); 
/* 236 */           this._state = 6;
/* 237 */           this._decodedData >>= 4;
/*     */           continue;
/*     */       } 
/* 243 */       throw new IllegalStateException("Illegal internal state " + this._state);
/*     */     } 
/* 246 */     return resultOffset - origResultOffset;
/*     */   }
/*     */   
/*     */   private boolean nextSegment() {
/* 257 */     if (this._nextSegmentIndex < this._nextSegments.size()) {
/* 258 */       this._currSegment = this._nextSegments.get(this._nextSegmentIndex++);
/* 260 */       if (this._nextSegmentIndex == this._nextSegments.size()) {
/* 261 */         this._currSegmentPtr = this._lastSegmentOffset;
/* 262 */         this._currSegmentEnd = this._lastSegmentEnd;
/*     */       } else {
/* 264 */         this._currSegmentPtr = 0;
/* 265 */         this._currSegmentEnd = this._currSegment.length;
/*     */       } 
/* 267 */       return true;
/*     */     } 
/* 269 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\typed\CharArrayBase64Decoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */