/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ISAACRandom extends BitsStreamGenerator implements Serializable {
/*     */   private static final long serialVersionUID = 7288197941165002400L;
/*     */   
/*     */   private static final int SIZE_L = 8;
/*     */   
/*     */   private static final int SIZE = 256;
/*     */   
/*     */   private static final int H_SIZE = 128;
/*     */   
/*     */   private static final int MASK = 1020;
/*     */   
/*     */   private static final int GLD_RATIO = -1640531527;
/*     */   
/*  56 */   private final int[] rsl = new int[256];
/*     */   
/*  58 */   private final int[] mem = new int[256];
/*     */   
/*     */   private int count;
/*     */   
/*     */   private int isaacA;
/*     */   
/*     */   private int isaacB;
/*     */   
/*     */   private int isaacC;
/*     */   
/*  68 */   private final int[] arr = new int[8];
/*     */   
/*     */   private int isaacX;
/*     */   
/*     */   private int isaacI;
/*     */   
/*     */   private int isaacJ;
/*     */   
/*     */   public ISAACRandom() {
/*  84 */     setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*     */   }
/*     */   
/*     */   public ISAACRandom(long seed) {
/*  93 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public ISAACRandom(int[] seed) {
/* 103 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public void setSeed(int seed) {
/* 109 */     setSeed(new int[] { seed });
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 115 */     setSeed(new int[] { (int)(seed >>> 32L), (int)(seed & 0xFFFFFFFFL) });
/*     */   }
/*     */   
/*     */   public void setSeed(int[] seed) {
/* 121 */     if (seed == null) {
/* 122 */       setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*     */       return;
/*     */     } 
/* 125 */     int seedLen = seed.length;
/* 126 */     int rslLen = this.rsl.length;
/* 127 */     System.arraycopy(seed, 0, this.rsl, 0, Math.min(seedLen, rslLen));
/* 128 */     if (seedLen < rslLen)
/* 129 */       for (int j = seedLen; j < rslLen; j++) {
/* 130 */         long k = this.rsl[j - seedLen];
/* 131 */         this.rsl[j] = (int)(1812433253L * (k ^ k >> 30L) + j & 0xFFFFFFFFL);
/*     */       }  
/* 134 */     initState();
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/* 140 */     if (this.count < 0) {
/* 141 */       isaac();
/* 142 */       this.count = 255;
/*     */     } 
/* 144 */     return this.rsl[this.count--] >>> 32 - bits;
/*     */   }
/*     */   
/*     */   private void isaac() {
/* 149 */     this.isaacI = 0;
/* 150 */     this.isaacJ = 128;
/* 151 */     this.isaacB += ++this.isaacC;
/* 152 */     while (this.isaacI < 128)
/* 153 */       isaac2(); 
/* 155 */     this.isaacJ = 0;
/* 156 */     while (this.isaacJ < 128)
/* 157 */       isaac2(); 
/*     */   }
/*     */   
/*     */   private void isaac2() {
/* 163 */     this.isaacX = this.mem[this.isaacI];
/* 164 */     this.isaacA ^= this.isaacA << 13;
/* 165 */     this.isaacA += this.mem[this.isaacJ++];
/* 166 */     isaac3();
/* 167 */     this.isaacX = this.mem[this.isaacI];
/* 168 */     this.isaacA ^= this.isaacA >>> 6;
/* 169 */     this.isaacA += this.mem[this.isaacJ++];
/* 170 */     isaac3();
/* 171 */     this.isaacX = this.mem[this.isaacI];
/* 172 */     this.isaacA ^= this.isaacA << 2;
/* 173 */     this.isaacA += this.mem[this.isaacJ++];
/* 174 */     isaac3();
/* 175 */     this.isaacX = this.mem[this.isaacI];
/* 176 */     this.isaacA ^= this.isaacA >>> 16;
/* 177 */     this.isaacA += this.mem[this.isaacJ++];
/* 178 */     isaac3();
/*     */   }
/*     */   
/*     */   private void isaac3() {
/* 183 */     this.mem[this.isaacI] = this.mem[(this.isaacX & 0x3FC) >> 2] + this.isaacA + this.isaacB;
/* 184 */     this.isaacB = this.mem[(this.mem[this.isaacI] >> 8 & 0x3FC) >> 2] + this.isaacX;
/* 185 */     this.rsl[this.isaacI++] = this.isaacB;
/*     */   }
/*     */   
/*     */   private void initState() {
/* 190 */     this.isaacA = 0;
/* 191 */     this.isaacB = 0;
/* 192 */     this.isaacC = 0;
/*     */     int j;
/* 193 */     for (j = 0; j < this.arr.length; j++)
/* 194 */       this.arr[j] = -1640531527; 
/* 196 */     for (j = 0; j < 4; j++)
/* 197 */       shuffle(); 
/* 200 */     for (j = 0; j < 256; j += 8) {
/* 201 */       this.arr[0] = this.arr[0] + this.rsl[j];
/* 202 */       this.arr[1] = this.arr[1] + this.rsl[j + 1];
/* 203 */       this.arr[2] = this.arr[2] + this.rsl[j + 2];
/* 204 */       this.arr[3] = this.arr[3] + this.rsl[j + 3];
/* 205 */       this.arr[4] = this.arr[4] + this.rsl[j + 4];
/* 206 */       this.arr[5] = this.arr[5] + this.rsl[j + 5];
/* 207 */       this.arr[6] = this.arr[6] + this.rsl[j + 6];
/* 208 */       this.arr[7] = this.arr[7] + this.rsl[j + 7];
/* 209 */       shuffle();
/* 210 */       setState(j);
/*     */     } 
/* 213 */     for (j = 0; j < 256; j += 8) {
/* 214 */       this.arr[0] = this.arr[0] + this.mem[j];
/* 215 */       this.arr[1] = this.arr[1] + this.mem[j + 1];
/* 216 */       this.arr[2] = this.arr[2] + this.mem[j + 2];
/* 217 */       this.arr[3] = this.arr[3] + this.mem[j + 3];
/* 218 */       this.arr[4] = this.arr[4] + this.mem[j + 4];
/* 219 */       this.arr[5] = this.arr[5] + this.mem[j + 5];
/* 220 */       this.arr[6] = this.arr[6] + this.mem[j + 6];
/* 221 */       this.arr[7] = this.arr[7] + this.mem[j + 7];
/* 222 */       shuffle();
/* 223 */       setState(j);
/*     */     } 
/* 225 */     isaac();
/* 226 */     this.count = 255;
/* 227 */     clear();
/*     */   }
/*     */   
/*     */   private void shuffle() {
/* 232 */     this.arr[0] = this.arr[0] ^ this.arr[1] << 11;
/* 233 */     this.arr[3] = this.arr[3] + this.arr[0];
/* 234 */     this.arr[1] = this.arr[1] + this.arr[2];
/* 235 */     this.arr[1] = this.arr[1] ^ this.arr[2] >>> 2;
/* 236 */     this.arr[4] = this.arr[4] + this.arr[1];
/* 237 */     this.arr[2] = this.arr[2] + this.arr[3];
/* 238 */     this.arr[2] = this.arr[2] ^ this.arr[3] << 8;
/* 239 */     this.arr[5] = this.arr[5] + this.arr[2];
/* 240 */     this.arr[3] = this.arr[3] + this.arr[4];
/* 241 */     this.arr[3] = this.arr[3] ^ this.arr[4] >>> 16;
/* 242 */     this.arr[6] = this.arr[6] + this.arr[3];
/* 243 */     this.arr[4] = this.arr[4] + this.arr[5];
/* 244 */     this.arr[4] = this.arr[4] ^ this.arr[5] << 10;
/* 245 */     this.arr[7] = this.arr[7] + this.arr[4];
/* 246 */     this.arr[5] = this.arr[5] + this.arr[6];
/* 247 */     this.arr[5] = this.arr[5] ^ this.arr[6] >>> 4;
/* 248 */     this.arr[0] = this.arr[0] + this.arr[5];
/* 249 */     this.arr[6] = this.arr[6] + this.arr[7];
/* 250 */     this.arr[6] = this.arr[6] ^ this.arr[7] << 8;
/* 251 */     this.arr[1] = this.arr[1] + this.arr[6];
/* 252 */     this.arr[7] = this.arr[7] + this.arr[0];
/* 253 */     this.arr[7] = this.arr[7] ^ this.arr[0] >>> 9;
/* 254 */     this.arr[2] = this.arr[2] + this.arr[7];
/* 255 */     this.arr[0] = this.arr[0] + this.arr[1];
/*     */   }
/*     */   
/*     */   private void setState(int start) {
/* 263 */     this.mem[start] = this.arr[0];
/* 264 */     this.mem[start + 1] = this.arr[1];
/* 265 */     this.mem[start + 2] = this.arr[2];
/* 266 */     this.mem[start + 3] = this.arr[3];
/* 267 */     this.mem[start + 4] = this.arr[4];
/* 268 */     this.mem[start + 5] = this.arr[5];
/* 269 */     this.mem[start + 6] = this.arr[6];
/* 270 */     this.mem[start + 7] = this.arr[7];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\ISAACRandom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */