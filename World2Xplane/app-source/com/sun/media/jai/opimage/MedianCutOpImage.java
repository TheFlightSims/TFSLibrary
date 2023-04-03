/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ public class MedianCutOpImage extends ColorQuantizerOpImage {
/*     */   private int histogramSize;
/*     */   
/*     */   private int[] counts;
/*     */   
/*     */   private int[] colors;
/*     */   
/*     */   private Cube[] partition;
/*     */   
/*  58 */   private int bits = 8;
/*     */   
/*     */   private int mask;
/*     */   
/*     */   HistogramHash histogram;
/*     */   
/*     */   public MedianCutOpImage(RenderedImage source, Map config, ImageLayout layout, int maxColorNum, int upperBound, ROI roi, int xPeriod, int yPeriod) {
/*  79 */     super(source, config, layout, maxColorNum, roi, xPeriod, yPeriod);
/*  81 */     this.colorMap = null;
/*  82 */     this.histogramSize = upperBound;
/*     */   }
/*     */   
/*     */   protected synchronized void train() {
/*  86 */     PlanarImage source = getSourceImage(0);
/*  87 */     if (this.roi == null)
/*  88 */       this.roi = (ROI)new ROIShape(source.getBounds()); 
/*  91 */     int minTileX = source.getMinTileX();
/*  92 */     int maxTileX = source.getMaxTileX();
/*  93 */     int minTileY = source.getMinTileY();
/*  94 */     int maxTileY = source.getMaxTileY();
/*  95 */     int xStart = source.getMinX();
/*  96 */     int yStart = source.getMinY();
/*  98 */     this.histogram = new HistogramHash(this.histogramSize);
/*     */     while (true) {
/* 101 */       this.histogram.init();
/* 102 */       int oldbits = this.bits;
/* 103 */       this.mask = 255 << 8 - this.bits & 0xFF;
/* 104 */       this.mask = this.mask | this.mask << 8 | this.mask << 16;
/* 106 */       for (int y = minTileY; y <= maxTileY; y++) {
/* 107 */         for (int x = minTileX; x <= maxTileX; x++) {
/* 111 */           Rectangle tileRect = source.getTileRect(x, y);
/* 114 */           if (this.roi.intersects(tileRect)) {
/* 118 */             if (this.checkForSkippedTiles && tileRect.x >= xStart && tileRect.y >= yStart) {
/* 122 */               int offsetX = (this.xPeriod - (tileRect.x - xStart) % this.xPeriod) % this.xPeriod;
/* 125 */               int offsetY = (this.yPeriod - (tileRect.y - yStart) % this.yPeriod) % this.yPeriod;
/* 131 */               if (offsetX >= tileRect.width || offsetY >= tileRect.height)
/*     */                 continue; 
/*     */             } 
/* 138 */             computeHistogram(source.getData(tileRect));
/* 139 */             if (this.histogram.isFull())
/*     */               break; 
/*     */           } 
/*     */           continue;
/*     */         } 
/* 144 */         if (this.histogram.isFull())
/*     */           break; 
/*     */       } 
/* 149 */       if (oldbits == this.bits) {
/* 150 */         this.counts = this.histogram.getCounts();
/* 151 */         this.colors = this.histogram.getColors();
/* 156 */         medianCut(this.maxColorNum);
/* 157 */         setProperty("LUT", this.colorMap);
/* 158 */         setProperty("JAI.LookupTable", this.colorMap);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeHistogram(Raster source) {
/*     */     LinkedList rectList;
/* 162 */     if (!this.isInitialized) {
/* 163 */       this.srcPA = new PixelAccessor((RenderedImage)getSourceImage(0));
/* 164 */       this.srcSampleType = (this.srcPA.sampleType == -1) ? 0 : this.srcPA.sampleType;
/* 166 */       this.isInitialized = true;
/*     */     } 
/* 169 */     Rectangle srcBounds = getSourceImage(0).getBounds().intersection(source.getBounds());
/* 173 */     if (this.roi == null) {
/* 174 */       rectList = new LinkedList();
/* 175 */       rectList.addLast(srcBounds);
/*     */     } else {
/* 177 */       rectList = this.roi.getAsRectangleList(srcBounds.x, srcBounds.y, srcBounds.width, srcBounds.height);
/* 181 */       if (rectList == null)
/*     */         return; 
/*     */     } 
/* 186 */     ListIterator iterator = rectList.listIterator(0);
/* 187 */     int xStart = source.getMinX();
/* 188 */     int yStart = source.getMinY();
/* 190 */     while (iterator.hasNext()) {
/* 191 */       Rectangle rect = srcBounds.intersection(iterator.next());
/* 192 */       int tx = rect.x;
/* 193 */       int ty = rect.y;
/* 196 */       rect.x = startPosition(tx, xStart, this.xPeriod);
/* 197 */       rect.y = startPosition(ty, yStart, this.yPeriod);
/* 198 */       rect.width = tx + rect.width - rect.x;
/* 199 */       rect.height = ty + rect.height - rect.y;
/* 201 */       if (rect.isEmpty())
/*     */         continue; 
/* 205 */       UnpackedImageData uid = this.srcPA.getPixels(source, rect, this.srcSampleType, false);
/* 207 */       switch (uid.type) {
/*     */         case 0:
/* 209 */           computeHistogramByte(uid);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeHistogramByte(UnpackedImageData uid) {
/* 216 */     Rectangle rect = uid.rect;
/* 217 */     byte[][] data = uid.getByteData();
/* 218 */     int lineStride = uid.lineStride;
/* 219 */     int pixelStride = uid.pixelStride;
/* 220 */     byte[] rBand = data[0];
/* 221 */     byte[] gBand = data[1];
/* 222 */     byte[] bBand = data[2];
/* 224 */     int lineInc = lineStride * this.yPeriod;
/* 225 */     int pixelInc = pixelStride * this.xPeriod;
/* 227 */     int lastLine = rect.height * lineStride;
/*     */     int lo;
/* 229 */     for (lo = 0; lo < lastLine; lo += lineInc) {
/* 230 */       int lastPixel = lo + rect.width * pixelStride;
/*     */       int po;
/* 232 */       for (po = lo; po < lastPixel; po += pixelInc) {
/* 233 */         int p = (rBand[po + uid.bandOffsets[0]] & 0xFF) << 16 | (gBand[po + uid.bandOffsets[1]] & 0xFF) << 8 | bBand[po + uid.bandOffsets[2]] & 0xFF;
/* 236 */         if (!this.histogram.insert(p & this.mask)) {
/* 237 */           this.bits--;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void medianCut(int expectedColorNum) {
/* 255 */     this.partition = new Cube[expectedColorNum];
/* 256 */     int numCubes = 0;
/* 257 */     Cube cube = new Cube();
/* 258 */     int numColors = 0;
/* 259 */     for (int i = 0; i < this.histogramSize; i++) {
/* 260 */       if (this.counts[i] != 0) {
/* 261 */         numColors++;
/* 262 */         cube.count += this.counts[i];
/*     */       } 
/*     */     } 
/* 266 */     cube.lower = 0;
/* 266 */     cube.upper = numColors - 1;
/* 267 */     cube.level = 0;
/* 268 */     shrinkCube(cube);
/* 269 */     this.partition[numCubes++] = cube;
/* 273 */     while (numCubes < expectedColorNum) {
/* 275 */       int level = 255;
/* 276 */       int splitableCube = -1;
/* 278 */       for (int k = 0; k < numCubes; k++) {
/* 279 */         if ((this.partition[k]).lower != (this.partition[k]).upper && (this.partition[k]).level < level) {
/* 281 */           level = (this.partition[k]).level;
/* 282 */           splitableCube = k;
/*     */         } 
/*     */       } 
/* 287 */       if (splitableCube == -1)
/*     */         break; 
/* 291 */       cube = this.partition[splitableCube];
/* 292 */       level = cube.level;
/* 295 */       int lr = 77 * (cube.rmax - cube.rmin);
/* 296 */       int lg = 150 * (cube.gmax - cube.gmin);
/* 297 */       int lb = 29 * (cube.bmax - cube.bmin);
/* 299 */       int longDimMask = 0;
/* 300 */       if (lr >= lg && lr >= lb)
/* 300 */         longDimMask = 16711680; 
/* 301 */       if (lg >= lr && lg >= lb)
/* 301 */         longDimMask = 65280; 
/* 302 */       if (lb >= lr && lb >= lg)
/* 302 */         longDimMask = 255; 
/* 305 */       quickSort(this.colors, cube.lower, cube.upper, longDimMask);
/* 308 */       int count = 0;
/* 309 */       int median = cube.lower;
/* 310 */       for (; median <= cube.upper - 1 && 
/* 311 */         count < cube.count / 2; median++)
/* 312 */         count += this.counts[median]; 
/* 317 */       Cube cubeA = new Cube();
/* 318 */       cubeA.lower = cube.lower;
/* 319 */       cubeA.upper = median - 1;
/* 320 */       cubeA.count = count;
/* 321 */       cube.level++;
/* 322 */       shrinkCube(cubeA);
/* 323 */       this.partition[splitableCube] = cubeA;
/* 325 */       Cube cubeB = new Cube();
/* 326 */       cubeB.lower = median;
/* 327 */       cubeB.upper = cube.upper;
/* 328 */       cube.count -= count;
/* 329 */       cube.level++;
/* 330 */       shrinkCube(cubeB);
/* 331 */       this.partition[numCubes++] = cubeB;
/*     */     } 
/* 335 */     createLUT(numCubes);
/*     */   }
/*     */   
/*     */   private void shrinkCube(Cube cube) {
/* 342 */     int rmin = 255;
/* 343 */     int rmax = 0;
/* 344 */     int gmin = 255;
/* 345 */     int gmax = 0;
/* 346 */     int bmin = 255;
/* 347 */     int bmax = 0;
/* 348 */     for (int i = cube.lower; i <= cube.upper; i++) {
/* 349 */       int color = this.colors[i];
/* 350 */       int r = color >> 16;
/* 351 */       int g = color >> 8 & 0xFF;
/* 352 */       int b = color & 0xFF;
/* 353 */       if (r > rmax) {
/* 353 */         rmax = r;
/* 354 */       } else if (r < rmin) {
/* 354 */         rmin = r;
/*     */       } 
/* 356 */       if (g > gmax) {
/* 356 */         gmax = g;
/* 357 */       } else if (g < gmin) {
/* 357 */         gmin = g;
/*     */       } 
/* 359 */       if (b > bmax) {
/* 359 */         bmax = b;
/* 360 */       } else if (b < bmin) {
/* 360 */         bmin = b;
/*     */       } 
/*     */     } 
/* 363 */     cube.rmin = rmin;
/* 363 */     cube.rmax = rmax;
/* 364 */     cube.gmin = gmin;
/* 364 */     cube.gmax = gmax;
/* 365 */     cube.bmin = bmin;
/* 365 */     cube.bmax = bmax;
/*     */   }
/*     */   
/*     */   private void createLUT(int ncubes) {
/* 371 */     if (this.colorMap == null)
/* 372 */       this.colorMap = new LookupTableJAI(new byte[3][ncubes]); 
/* 375 */     byte[] rLUT = this.colorMap.getByteData(0);
/* 376 */     byte[] gLUT = this.colorMap.getByteData(1);
/* 377 */     byte[] bLUT = this.colorMap.getByteData(2);
/* 379 */     float scale = 255.0F / (this.mask & 0xFF);
/* 381 */     for (int k = 0; k < ncubes; k++) {
/* 382 */       Cube cube = this.partition[k];
/* 383 */       float rsum = 0.0F, gsum = 0.0F, bsum = 0.0F;
/* 385 */       for (int i = cube.lower; i <= cube.upper; i++) {
/* 386 */         int color = this.colors[i];
/* 387 */         int r = color >> 16;
/* 388 */         rsum += r * this.counts[i];
/* 389 */         int g = color >> 8 & 0xFF;
/* 390 */         gsum += g * this.counts[i];
/* 391 */         int b = color & 0xFF;
/* 392 */         bsum += b * this.counts[i];
/*     */       } 
/* 396 */       rLUT[k] = (byte)(int)(rsum / cube.count * scale);
/* 397 */       gLUT[k] = (byte)(int)(gsum / cube.count * scale);
/* 398 */       bLUT[k] = (byte)(int)(bsum / cube.count * scale);
/*     */     } 
/*     */   }
/*     */   
/*     */   void quickSort(int[] a, int lo0, int hi0, int longDimMask) {
/* 405 */     int lo = lo0;
/* 406 */     int hi = hi0;
/* 409 */     if (hi0 > lo0) {
/* 410 */       int mid = a[(lo0 + hi0) / 2] & longDimMask;
/* 411 */       while (lo <= hi) {
/* 412 */         while (lo < hi0 && (a[lo] & longDimMask) < mid)
/* 413 */           lo++; 
/* 414 */         while (hi > lo0 && (a[hi] & longDimMask) > mid)
/* 415 */           hi--; 
/* 416 */         if (lo <= hi) {
/* 417 */           int t = a[lo];
/* 418 */           a[lo] = a[hi];
/* 419 */           a[hi] = t;
/* 421 */           t = this.counts[lo];
/* 422 */           this.counts[lo] = this.counts[hi];
/* 423 */           this.counts[hi] = t;
/* 425 */           lo++;
/* 426 */           hi--;
/*     */         } 
/*     */       } 
/* 429 */       if (lo0 < hi)
/* 430 */         quickSort(a, lo0, hi, longDimMask); 
/* 431 */       if (lo < hi0)
/* 432 */         quickSort(a, lo, hi0, longDimMask); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MedianCutOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */