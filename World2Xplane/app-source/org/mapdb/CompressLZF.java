/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public final class CompressLZF {
/*     */   private static final int HASH_SIZE = 16384;
/*     */   
/*     */   private static final int MAX_LITERAL = 32;
/*     */   
/*     */   private static final int MAX_OFF = 8192;
/*     */   
/*     */   private static final int MAX_REF = 264;
/*     */   
/*     */   private int[] cachedHashTable;
/*     */   
/*     */   private static int first(byte[] in, int inPos) {
/* 144 */     return in[inPos] << 8 | in[inPos + 1] & 0xFF;
/*     */   }
/*     */   
/*     */   private static int next(int v, byte[] in, int inPos) {
/* 151 */     return v << 8 | in[inPos + 2] & 0xFF;
/*     */   }
/*     */   
/*     */   private static int hash(int h) {
/* 158 */     return h * 2777 >> 9 & 0x3FFF;
/*     */   }
/*     */   
/*     */   public int compress(byte[] in, int inLen, byte[] out, int outPos) {
/* 162 */     int inPos = 0;
/* 163 */     if (this.cachedHashTable == null)
/* 164 */       this.cachedHashTable = new int[16384]; 
/* 166 */     int[] hashTab = this.cachedHashTable;
/* 167 */     int literals = 0;
/* 168 */     outPos++;
/* 169 */     int future = first(in, 0);
/* 170 */     while (inPos < inLen - 4) {
/* 171 */       byte p2 = in[inPos + 2];
/* 173 */       future = (future << 8) + (p2 & 0xFF);
/* 174 */       int off = hash(future);
/* 175 */       int ref = hashTab[off];
/* 176 */       hashTab[off] = inPos;
/* 183 */       if (ref < inPos && ref > 0 && (off = inPos - ref - 1) < 8192 && in[ref + 2] == p2 && in[ref + 1] == (byte)(future >> 8) && in[ref] == (byte)(future >> 16)) {
/* 190 */         int maxLen = inLen - inPos - 2;
/* 191 */         if (maxLen > 264)
/* 192 */           maxLen = 264; 
/* 194 */         if (literals == 0) {
/* 197 */           outPos--;
/*     */         } else {
/* 201 */           out[outPos - literals - 1] = (byte)(literals - 1);
/* 202 */           literals = 0;
/*     */         } 
/* 204 */         int len = 3;
/* 205 */         while (len < maxLen && in[ref + len] == in[inPos + len])
/* 206 */           len++; 
/* 208 */         len -= 2;
/* 209 */         if (len < 7) {
/* 210 */           out[outPos++] = (byte)((off >> 8) + (len << 5));
/*     */         } else {
/* 212 */           out[outPos++] = (byte)((off >> 8) + 224);
/* 213 */           out[outPos++] = (byte)(len - 7);
/*     */         } 
/* 215 */         out[outPos++] = (byte)off;
/* 217 */         outPos++;
/* 218 */         inPos += len;
/* 222 */         future = first(in, inPos);
/* 223 */         future = next(future, in, inPos);
/* 224 */         hashTab[hash(future)] = inPos++;
/* 225 */         future = next(future, in, inPos);
/* 226 */         hashTab[hash(future)] = inPos++;
/*     */         continue;
/*     */       } 
/* 229 */       out[outPos++] = in[inPos++];
/* 230 */       literals++;
/* 233 */       if (literals == 32) {
/* 234 */         out[outPos - literals - 1] = (byte)(literals - 1);
/* 235 */         literals = 0;
/* 238 */         outPos++;
/*     */       } 
/*     */     } 
/* 243 */     while (inPos < inLen) {
/* 244 */       out[outPos++] = in[inPos++];
/* 245 */       literals++;
/* 246 */       if (literals == 32) {
/* 247 */         out[outPos - literals - 1] = (byte)(literals - 1);
/* 248 */         literals = 0;
/* 249 */         outPos++;
/*     */       } 
/*     */     } 
/* 253 */     out[outPos - literals - 1] = (byte)(literals - 1);
/* 254 */     if (literals == 0)
/* 255 */       outPos--; 
/* 257 */     return outPos;
/*     */   }
/*     */   
/*     */   public void expand(DataInput in, byte[] out, int outPos, int outLen) throws IOException {
/* 262 */     assert outLen >= 0;
/*     */     do {
/* 264 */       int ctrl = in.readByte() & 0xFF;
/* 265 */       if (ctrl < 32) {
/* 267 */         ctrl++;
/* 269 */         in.readFully(out, outPos, ctrl);
/* 270 */         outPos += ctrl;
/*     */       } else {
/* 274 */         int len = ctrl >> 5;
/* 276 */         if (len == 7)
/* 277 */           len += in.readByte() & 0xFF; 
/* 281 */         len += 2;
/* 285 */         ctrl = -((ctrl & 0x1F) << 8) - 1;
/* 288 */         ctrl -= in.readByte() & 0xFF;
/* 292 */         ctrl += outPos;
/* 293 */         if (outPos + len >= out.length)
/* 295 */           throw new ArrayIndexOutOfBoundsException(); 
/* 297 */         for (int i = 0; i < len; i++)
/* 298 */           out[outPos++] = out[ctrl++]; 
/*     */       } 
/* 301 */     } while (outPos < outLen);
/*     */   }
/*     */   
/*     */   public void expand(ByteBuffer in, int inPos, byte[] out, int outPos, int outLen) {
/* 349 */     ByteBuffer in2 = null;
/* 350 */     assert outLen >= 0;
/*     */     do {
/* 352 */       int ctrl = in.get(inPos++) & 0xFF;
/* 353 */       if (ctrl < 32) {
/* 355 */         ctrl++;
/* 358 */         if (in2 == null)
/* 358 */           in2 = in.duplicate(); 
/* 359 */         in2.position(inPos);
/* 360 */         in2.get(out, outPos, ctrl);
/* 361 */         outPos += ctrl;
/* 362 */         inPos += ctrl;
/*     */       } else {
/* 366 */         int len = ctrl >> 5;
/* 368 */         if (len == 7)
/* 369 */           len += in.get(inPos++) & 0xFF; 
/* 373 */         len += 2;
/* 377 */         ctrl = -((ctrl & 0x1F) << 8) - 1;
/* 380 */         ctrl -= in.get(inPos++) & 0xFF;
/* 384 */         ctrl += outPos;
/* 385 */         if (outPos + len >= out.length)
/* 387 */           throw new ArrayIndexOutOfBoundsException(); 
/* 389 */         for (int i = 0; i < len; i++)
/* 390 */           out[outPos++] = out[ctrl++]; 
/*     */       } 
/* 393 */     } while (outPos < outLen);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\CompressLZF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */