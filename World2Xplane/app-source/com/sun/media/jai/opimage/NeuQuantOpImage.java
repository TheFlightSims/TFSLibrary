/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.iterator.RandomIter;
/*     */ import javax.media.jai.iterator.RandomIterFactory;
/*     */ 
/*     */ public class NeuQuantOpImage extends ColorQuantizerOpImage {
/*     */   protected static final int prime1 = 499;
/*     */   
/*     */   protected static final int prime2 = 491;
/*     */   
/*     */   protected static final int prime3 = 487;
/*     */   
/*     */   protected static final int prime4 = 503;
/*     */   
/*     */   protected static final int minpicturebytes = 1509;
/*     */   
/*     */   private int ncycles;
/*     */   
/*  90 */   private final int maxnetpos = this.maxColorNum - 1;
/*     */   
/*  91 */   private final int netbiasshift = 4;
/*     */   
/*  94 */   private final int intbiasshift = 16;
/*     */   
/*  95 */   private final int intbias = 65536;
/*     */   
/*  96 */   private final int gammashift = 10;
/*     */   
/*  97 */   private final int gamma = 1024;
/*     */   
/*  98 */   private final int betashift = 10;
/*     */   
/*  99 */   private final int beta = 64;
/*     */   
/* 100 */   private final int betagamma = 65536;
/*     */   
/* 103 */   private final int initrad = this.maxColorNum >> 3;
/*     */   
/* 104 */   private final int radiusbiasshift = 6;
/*     */   
/* 105 */   private final int radiusbias = 64;
/*     */   
/* 106 */   private final int initradius = this.initrad * 64;
/*     */   
/* 107 */   private final int radiusdec = 30;
/*     */   
/* 110 */   private final int alphabiasshift = 10;
/*     */   
/* 111 */   private final int initalpha = 1024;
/*     */   
/*     */   private int alphadec;
/*     */   
/* 116 */   private final int radbiasshift = 8;
/*     */   
/* 117 */   private final int radbias = 256;
/*     */   
/* 118 */   private final int alpharadbshift = 18;
/*     */   
/* 119 */   private final int alpharadbias = 262144;
/*     */   
/*     */   private int[][] network;
/*     */   
/* 124 */   private int[] netindex = new int[256];
/*     */   
/* 126 */   private int[] bias = new int[this.maxColorNum];
/*     */   
/* 127 */   private int[] freq = new int[this.maxColorNum];
/*     */   
/* 128 */   private int[] radpower = new int[this.initrad];
/*     */   
/*     */   public NeuQuantOpImage(RenderedImage source, Map config, ImageLayout layout, int maxColorNum, int upperBound, ROI roi, int xPeriod, int yPeriod) {
/* 143 */     super(source, config, layout, maxColorNum, roi, xPeriod, yPeriod);
/* 145 */     this.colorMap = null;
/* 146 */     this.ncycles = upperBound;
/*     */   }
/*     */   
/*     */   protected synchronized void train() {
/*     */     int step;
/* 152 */     this.network = new int[this.maxColorNum][];
/* 153 */     for (int i = 0; i < this.maxColorNum; i++) {
/* 154 */       this.network[i] = new int[4];
/* 155 */       int[] p = this.network[i];
/* 156 */       p[2] = (i << 12) / this.maxColorNum;
/* 156 */       p[1] = (i << 12) / this.maxColorNum;
/* 156 */       p[0] = (i << 12) / this.maxColorNum;
/* 157 */       this.freq[i] = 65536 / this.maxColorNum;
/* 158 */       this.bias[i] = 0;
/*     */     } 
/* 161 */     PlanarImage source = getSourceImage(0);
/* 162 */     Rectangle rect = source.getBounds();
/* 164 */     if (this.roi != null)
/* 165 */       rect = this.roi.getBounds(); 
/* 167 */     RandomIter iterator = RandomIterFactory.create((RenderedImage)source, rect);
/* 169 */     int samplefac = this.xPeriod * this.yPeriod;
/* 170 */     int startX = rect.x / this.xPeriod;
/* 171 */     int startY = rect.y / this.yPeriod;
/* 172 */     int offsetX = rect.x % this.xPeriod;
/* 173 */     int offsetY = rect.y % this.yPeriod;
/* 174 */     int pixelsPerLine = (rect.width - 1) / this.xPeriod + 1;
/* 175 */     int numSamples = pixelsPerLine * ((rect.height - 1) / this.yPeriod + 1);
/* 178 */     if (numSamples < 1509)
/* 179 */       samplefac = 1; 
/* 181 */     this.alphadec = 30 + (samplefac - 1) / 3;
/* 182 */     int pix = 0;
/* 184 */     int delta = numSamples / this.ncycles;
/* 185 */     int alpha = 1024;
/* 186 */     int radius = this.initradius;
/* 188 */     int rad = radius >> 6;
/* 189 */     if (rad <= 1)
/* 190 */       rad = 0; 
/* 191 */     for (int j = 0; j < rad; j++)
/* 192 */       this.radpower[j] = alpha * (rad * rad - j * j) * 256 / rad * rad; 
/* 195 */     if (numSamples < 1509) {
/* 196 */       step = 3;
/* 197 */     } else if (numSamples % 499 != 0) {
/* 198 */       step = 1497;
/* 200 */     } else if (numSamples % 491 != 0) {
/* 201 */       step = 1473;
/* 203 */     } else if (numSamples % 487 != 0) {
/* 204 */       step = 1461;
/*     */     } else {
/* 206 */       step = 1509;
/*     */     } 
/* 210 */     int[] pixel = new int[3];
/* 212 */     for (int k = 0; k < numSamples; ) {
/* 213 */       int y = (pix / pixelsPerLine + startY) * this.yPeriod + offsetY;
/* 214 */       int x = (pix % pixelsPerLine + startX) * this.xPeriod + offsetX;
/*     */       try {
/* 217 */         iterator.getPixel(x, y, pixel);
/* 218 */       } catch (Exception e) {
/*     */         continue;
/*     */       } 
/* 222 */       int b = pixel[2] << 4;
/* 223 */       int g = pixel[1] << 4;
/* 224 */       int r = pixel[0] << 4;
/* 226 */       int m = contest(b, g, r);
/* 228 */       altersingle(alpha, m, b, g, r);
/* 229 */       if (rad != 0)
/* 230 */         alterneigh(rad, m, b, g, r); 
/* 232 */       pix += step;
/* 233 */       if (pix >= numSamples)
/* 234 */         pix -= numSamples; 
/* 236 */       k++;
/* 237 */       if (k % delta == 0) {
/* 238 */         alpha -= alpha / this.alphadec;
/* 239 */         radius -= radius / 30;
/* 240 */         rad = radius >> 6;
/* 241 */         if (rad <= 1)
/* 242 */           rad = 0; 
/* 243 */         for (m = 0; m < rad; m++)
/* 244 */           this.radpower[m] = alpha * (rad * rad - m * m) * 256 / rad * rad; 
/*     */       } 
/*     */     } 
/* 248 */     unbiasnet();
/* 249 */     inxbuild();
/* 250 */     createLUT();
/* 251 */     setProperty("LUT", this.colorMap);
/* 252 */     setProperty("JAI.LookupTable", this.colorMap);
/*     */   }
/*     */   
/*     */   private void createLUT() {
/* 256 */     this.colorMap = new LookupTableJAI(new byte[3][this.maxColorNum]);
/* 257 */     byte[][] map = this.colorMap.getByteData();
/* 258 */     int[] index = new int[this.maxColorNum];
/*     */     int i;
/* 259 */     for (i = 0; i < this.maxColorNum; i++)
/* 260 */       index[this.network[i][3]] = i; 
/* 261 */     for (i = 0; i < this.maxColorNum; i++) {
/* 262 */       int j = index[i];
/* 263 */       map[2][i] = (byte)this.network[j][0];
/* 264 */       map[1][i] = (byte)this.network[j][1];
/* 265 */       map[0][i] = (byte)this.network[j][2];
/*     */     } 
/*     */   }
/*     */   
/*     */   private void inxbuild() {
/* 273 */     int previouscol = 0;
/* 274 */     int startpos = 0;
/* 275 */     for (int i = 0; i < this.maxColorNum; i++) {
/* 276 */       int[] p = this.network[i];
/* 277 */       int smallpos = i;
/* 278 */       int smallval = p[1];
/*     */       int k;
/* 281 */       for (k = i + 1; k < this.maxColorNum; k++) {
/* 282 */         int[] arrayOfInt = this.network[k];
/* 283 */         if (arrayOfInt[1] < smallval) {
/* 284 */           smallpos = k;
/* 285 */           smallval = arrayOfInt[1];
/*     */         } 
/*     */       } 
/* 288 */       int[] q = this.network[smallpos];
/* 290 */       if (i != smallpos) {
/* 291 */         k = q[0];
/* 291 */         q[0] = p[0];
/* 291 */         p[0] = k;
/* 292 */         k = q[1];
/* 292 */         q[1] = p[1];
/* 292 */         p[1] = k;
/* 293 */         k = q[2];
/* 293 */         q[2] = p[2];
/* 293 */         p[2] = k;
/* 294 */         k = q[3];
/* 294 */         q[3] = p[3];
/* 294 */         p[3] = k;
/*     */       } 
/* 297 */       if (smallval != previouscol) {
/* 298 */         this.netindex[previouscol] = startpos + i >> 1;
/* 299 */         for (k = previouscol + 1; k < smallval; k++)
/* 300 */           this.netindex[k] = i; 
/* 301 */         previouscol = smallval;
/* 302 */         startpos = i;
/*     */       } 
/*     */     } 
/* 305 */     this.netindex[previouscol] = startpos + this.maxnetpos >> 1;
/* 306 */     for (int j = previouscol + 1; j < 256; j++)
/* 307 */       this.netindex[j] = this.maxnetpos; 
/*     */   }
/*     */   
/*     */   protected byte findNearestEntry(int r, int g, int b) {
/* 314 */     int bestd = 1000;
/* 315 */     int best = -1;
/* 316 */     int i = this.netindex[g];
/* 317 */     int j = i - 1;
/* 319 */     while (i < this.maxColorNum || j >= 0) {
/* 320 */       if (i < this.maxColorNum) {
/* 321 */         int[] p = this.network[i];
/* 322 */         int dist = p[1] - g;
/* 323 */         if (dist >= bestd) {
/* 324 */           i = this.maxColorNum;
/*     */         } else {
/* 326 */           i++;
/* 327 */           if (dist < 0)
/* 328 */             dist = -dist; 
/* 329 */           int a = p[0] - b;
/* 330 */           if (a < 0)
/* 331 */             a = -a; 
/* 332 */           dist += a;
/* 333 */           if (dist < bestd) {
/* 334 */             a = p[2] - r;
/* 335 */             if (a < 0)
/* 336 */               a = -a; 
/* 337 */             dist += a;
/* 338 */             if (dist < bestd) {
/* 339 */               bestd = dist;
/* 340 */               best = p[3];
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 346 */       if (j >= 0) {
/* 347 */         int[] p = this.network[j];
/* 348 */         int dist = g - p[1];
/* 349 */         if (dist >= bestd) {
/* 350 */           j = -1;
/*     */           continue;
/*     */         } 
/* 352 */         j--;
/* 353 */         if (dist < 0)
/* 354 */           dist = -dist; 
/* 355 */         int a = p[0] - b;
/* 356 */         if (a < 0)
/* 357 */           a = -a; 
/* 358 */         dist += a;
/* 359 */         if (dist < bestd) {
/* 360 */           a = p[2] - r;
/* 361 */           if (a < 0)
/* 362 */             a = -a; 
/* 363 */           dist += a;
/* 364 */           if (dist < bestd) {
/* 365 */             bestd = dist;
/* 366 */             best = p[3];
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 372 */     return (byte)best;
/*     */   }
/*     */   
/*     */   private void unbiasnet() {
/* 379 */     for (int i = 0; i < this.maxColorNum; i++) {
/* 380 */       this.network[i][0] = this.network[i][0] >> 4;
/* 381 */       this.network[i][1] = this.network[i][1] >> 4;
/* 382 */       this.network[i][2] = this.network[i][2] >> 4;
/* 383 */       this.network[i][3] = i;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void alterneigh(int rad, int i, int b, int g, int r) {
/* 392 */     int lo = i - rad;
/* 393 */     if (lo < -1)
/* 394 */       lo = -1; 
/* 395 */     int hi = i + rad;
/* 396 */     if (hi > this.maxColorNum)
/* 397 */       hi = this.maxColorNum; 
/* 399 */     int j = i + 1;
/* 400 */     int k = i - 1;
/* 401 */     int m = 1;
/* 402 */     while (j < hi || k > lo) {
/* 403 */       int a = this.radpower[m++];
/* 404 */       if (j < hi) {
/* 405 */         int[] p = this.network[j++];
/* 407 */         p[0] = p[0] - a * (p[0] - b) / 262144;
/* 408 */         p[1] = p[1] - a * (p[1] - g) / 262144;
/* 409 */         p[2] = p[2] - a * (p[2] - r) / 262144;
/*     */       } 
/* 412 */       if (k > lo) {
/* 413 */         int[] p = this.network[k--];
/* 415 */         p[0] = p[0] - a * (p[0] - b) / 262144;
/* 416 */         p[1] = p[1] - a * (p[1] - g) / 262144;
/* 417 */         p[2] = p[2] - a * (p[2] - r) / 262144;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void altersingle(int alpha, int i, int b, int g, int r) {
/* 427 */     int[] n = this.network[i];
/* 428 */     n[0] = n[0] - alpha * (n[0] - b) / 1024;
/* 429 */     n[1] = n[1] - alpha * (n[1] - g) / 1024;
/* 430 */     n[2] = n[2] - alpha * (n[2] - r) / 1024;
/*     */   }
/*     */   
/*     */   private int contest(int b, int g, int r) {
/* 440 */     int bestd = Integer.MAX_VALUE;
/* 441 */     int bestbiasd = bestd;
/* 442 */     int bestpos = -1;
/* 443 */     int bestbiaspos = bestpos;
/* 445 */     for (int i = 0; i < this.maxColorNum; i++) {
/* 446 */       int[] n = this.network[i];
/* 447 */       int dist = n[0] - b;
/* 448 */       if (dist < 0)
/* 449 */         dist = -dist; 
/* 450 */       int a = n[1] - g;
/* 451 */       if (a < 0)
/* 452 */         a = -a; 
/* 453 */       dist += a;
/* 454 */       a = n[2] - r;
/* 455 */       if (a < 0)
/* 456 */         a = -a; 
/* 457 */       dist += a;
/* 458 */       if (dist < bestd) {
/* 459 */         bestd = dist;
/* 460 */         bestpos = i;
/*     */       } 
/* 462 */       int biasdist = dist - (this.bias[i] >> 12);
/* 463 */       if (biasdist < bestbiasd) {
/* 464 */         bestbiasd = biasdist;
/* 465 */         bestbiaspos = i;
/*     */       } 
/* 467 */       int betafreq = this.freq[i] >> 10;
/* 468 */       this.freq[i] = this.freq[i] - betafreq;
/* 469 */       this.bias[i] = this.bias[i] + (betafreq << 10);
/*     */     } 
/* 471 */     this.freq[bestpos] = this.freq[bestpos] + 64;
/* 472 */     this.bias[bestpos] = this.bias[bestpos] - 65536;
/* 473 */     return bestbiaspos;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\NeuQuantOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */