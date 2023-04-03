/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.StatisticsOpImage;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ public class ExtremaOpImage extends StatisticsOpImage {
/*     */   protected double[][] extrema;
/*     */   
/*     */   protected ArrayList[] minLocations;
/*     */   
/*     */   protected ArrayList[] maxLocations;
/*     */   
/*     */   protected int[] minCounts;
/*     */   
/*     */   protected int[] maxCounts;
/*     */   
/*     */   protected boolean saveLocations;
/*     */   
/*     */   protected int maxRuns;
/*     */   
/*  54 */   protected int numMinLocations = 0;
/*     */   
/*  56 */   protected int numMaxLocations = 0;
/*     */   
/*     */   private boolean isInitialized = false;
/*     */   
/*     */   private PixelAccessor srcPA;
/*     */   
/*     */   private int srcSampleType;
/*     */   
/*     */   private final boolean tileIntersectsROI(int tileX, int tileY) {
/*  65 */     if (this.roi == null)
/*  66 */       return true; 
/*  68 */     return this.roi.intersects(tileXToX(tileX), tileYToY(tileY), this.tileWidth, this.tileHeight);
/*     */   }
/*     */   
/*     */   public ExtremaOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod, boolean saveLocations, int maxRuns) {
/*  86 */     super(source, roi, xStart, yStart, xPeriod, yPeriod);
/*  88 */     this.extrema = (double[][])null;
/*  89 */     this.saveLocations = saveLocations;
/*  90 */     this.maxRuns = maxRuns;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  95 */     int numBands = this.sampleModel.getNumBands();
/*  97 */     if (this.extrema == null)
/* 100 */       return super.getProperty(name); 
/* 101 */     if (name.equalsIgnoreCase("extrema")) {
/* 102 */       double[][] stats = new double[2][numBands];
/* 103 */       for (int i = 0; i < numBands; i++) {
/* 104 */         stats[0][i] = this.extrema[0][i];
/* 105 */         stats[1][i] = this.extrema[1][i];
/*     */       } 
/* 107 */       return stats;
/*     */     } 
/* 108 */     if (name.equalsIgnoreCase("minimum")) {
/* 109 */       double[] stats = new double[numBands];
/* 110 */       for (int i = 0; i < numBands; i++)
/* 111 */         stats[i] = this.extrema[0][i]; 
/* 113 */       return stats;
/*     */     } 
/* 114 */     if (name.equalsIgnoreCase("maximum")) {
/* 115 */       double[] stats = new double[numBands];
/* 116 */       for (int i = 0; i < numBands; i++)
/* 117 */         stats[i] = this.extrema[1][i]; 
/* 119 */       return stats;
/*     */     } 
/* 120 */     if (this.saveLocations && name.equalsIgnoreCase("minLocations"))
/* 121 */       return this.minLocations; 
/* 122 */     if (this.saveLocations && name.equalsIgnoreCase("maxLocations"))
/* 123 */       return this.maxLocations; 
/* 126 */     return Image.UndefinedProperty;
/*     */   }
/*     */   
/*     */   protected String[] getStatisticsNames() {
/* 130 */     return new String[] { "extrema", "maximum", "minimum", "maxLocations", "minLocations" };
/*     */   }
/*     */   
/*     */   protected Object createStatistics(String name) {
/* 135 */     int numBands = this.sampleModel.getNumBands();
/* 136 */     Object stats = null;
/* 138 */     if (name.equalsIgnoreCase("extrema")) {
/* 139 */       stats = new double[2][numBands];
/* 140 */     } else if (name.equalsIgnoreCase("minimum") || name.equalsIgnoreCase("maximum")) {
/* 142 */       stats = new double[numBands];
/* 143 */     } else if (this.saveLocations && (name.equalsIgnoreCase("minLocations") || name.equalsIgnoreCase("maxLocations"))) {
/* 146 */       stats = new ArrayList[numBands];
/*     */     } else {
/* 148 */       stats = Image.UndefinedProperty;
/*     */     } 
/* 150 */     return stats;
/*     */   }
/*     */   
/*     */   private final int startPosition(int pos, int start, int period) {
/* 154 */     int t = (pos - start) % period;
/* 155 */     return (t == 0) ? pos : (pos + period - t);
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/*     */     LinkedList rectList;
/* 161 */     if (!this.isInitialized) {
/* 162 */       this.srcPA = new PixelAccessor((RenderedImage)getSourceImage(0));
/* 163 */       this.srcSampleType = (this.srcPA.sampleType == -1) ? 0 : this.srcPA.sampleType;
/* 165 */       this.isInitialized = true;
/*     */     } 
/* 168 */     Rectangle srcBounds = getSourceImage(0).getBounds().intersection(source.getBounds());
/* 172 */     if (this.roi == null) {
/* 173 */       rectList = new LinkedList();
/* 174 */       rectList.addLast(srcBounds);
/*     */     } else {
/* 176 */       rectList = this.roi.getAsRectangleList(srcBounds.x, srcBounds.y, srcBounds.width, srcBounds.height);
/* 180 */       if (rectList == null)
/*     */         return; 
/*     */     } 
/* 184 */     ListIterator iterator = rectList.listIterator(0);
/* 186 */     while (iterator.hasNext()) {
/* 187 */       Rectangle rect = srcBounds.intersection(iterator.next());
/* 188 */       int tx = rect.x;
/* 189 */       int ty = rect.y;
/* 192 */       rect.x = startPosition(tx, this.xStart, this.xPeriod);
/* 193 */       rect.y = startPosition(ty, this.yStart, this.yPeriod);
/* 194 */       rect.width = tx + rect.width - rect.x;
/* 195 */       rect.height = ty + rect.height - rect.y;
/* 197 */       if (rect.isEmpty())
/*     */         continue; 
/* 201 */       initializeState(source);
/* 203 */       UnpackedImageData uid = this.srcPA.getPixels(source, rect, this.srcSampleType, false);
/* 205 */       switch (uid.type) {
/*     */         case 0:
/* 207 */           accumulateStatisticsByte(uid);
/*     */         case 1:
/* 210 */           accumulateStatisticsUShort(uid);
/*     */         case 2:
/* 213 */           accumulateStatisticsShort(uid);
/*     */         case 3:
/* 216 */           accumulateStatisticsInt(uid);
/*     */         case 4:
/* 219 */           accumulateStatisticsFloat(uid);
/*     */         case 5:
/* 222 */           accumulateStatisticsDouble(uid);
/*     */       } 
/*     */     } 
/* 227 */     if (name.equalsIgnoreCase("extrema")) {
/* 228 */       double[][] ext = (double[][])stats;
/* 229 */       for (int i = 0; i < this.srcPA.numBands; i++) {
/* 230 */         ext[0][i] = this.extrema[0][i];
/* 231 */         ext[1][i] = this.extrema[1][i];
/*     */       } 
/* 233 */     } else if (name.equalsIgnoreCase("minimum")) {
/* 234 */       double[] min = (double[])stats;
/* 235 */       for (int i = 0; i < this.srcPA.numBands; i++)
/* 236 */         min[i] = this.extrema[0][i]; 
/* 238 */     } else if (name.equalsIgnoreCase("maximum")) {
/* 239 */       double[] max = (double[])stats;
/* 240 */       for (int i = 0; i < this.srcPA.numBands; i++)
/* 241 */         max[i] = this.extrema[1][i]; 
/* 243 */     } else if (name.equalsIgnoreCase("minLocations")) {
/* 244 */       ArrayList[] minLoc = (ArrayList[])stats;
/* 245 */       for (int i = 0; i < this.srcPA.numBands; i++)
/* 246 */         minLoc[i] = this.minLocations[i]; 
/* 248 */     } else if (name.equalsIgnoreCase("maxLocations")) {
/* 249 */       ArrayList[] maxLoc = (ArrayList[])stats;
/* 250 */       for (int i = 0; i < this.srcPA.numBands; i++)
/* 251 */         maxLoc[i] = this.maxLocations[i]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsByte(UnpackedImageData uid) {
/* 256 */     Rectangle rect = uid.rect;
/* 257 */     byte[][] data = uid.getByteData();
/* 258 */     int lineStride = uid.lineStride;
/* 259 */     int pixelStride = uid.pixelStride;
/* 261 */     int lineInc = lineStride * this.yPeriod;
/* 262 */     int pixelInc = pixelStride * this.xPeriod;
/* 264 */     if (!this.saveLocations) {
/* 265 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 266 */         int min = (int)this.extrema[0][b];
/* 267 */         int max = (int)this.extrema[1][b];
/* 269 */         byte[] d = data[b];
/* 270 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 272 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 273 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 275 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 276 */             int p = d[po] & 0xFF;
/* 278 */             if (p < min) {
/* 279 */               min = p;
/* 280 */             } else if (p > max) {
/* 281 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 285 */         this.extrema[0][b] = min;
/* 286 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 289 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 290 */         int min = (int)this.extrema[0][b];
/* 291 */         int max = (int)this.extrema[1][b];
/* 292 */         ArrayList minList = this.minLocations[b];
/* 293 */         ArrayList maxList = this.maxLocations[b];
/* 294 */         int minCount = this.minCounts[b];
/* 295 */         int maxCount = this.maxCounts[b];
/* 297 */         byte[] d = data[b];
/* 298 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 300 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 301 */           lo += lineInc, y += this.yPeriod) {
/* 303 */           int lastPixel = lo + rect.width * pixelStride;
/* 304 */           int minStart = 0;
/* 305 */           int maxStart = 0;
/* 306 */           int minLength = 0;
/* 307 */           int maxLength = 0;
/*     */           int x;
/* 309 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 310 */             x += this.xPeriod) {
/* 312 */             int p = d[po] & 0xFF;
/* 314 */             if (p < min) {
/* 315 */               min = p;
/* 316 */               minStart = x;
/* 317 */               minLength = 1;
/* 318 */               minList.clear();
/* 319 */               minCount = 0;
/* 320 */             } else if (p > max) {
/* 321 */               max = p;
/* 322 */               maxStart = x;
/* 323 */               maxLength = 1;
/* 324 */               maxList.clear();
/* 325 */               maxCount = 0;
/*     */             } else {
/* 327 */               if (p == min) {
/* 328 */                 if (minLength == 0)
/* 329 */                   minStart = x; 
/* 330 */                 minLength++;
/* 331 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 332 */                 minList.add(new int[] { minStart, y, minLength });
/* 333 */                 minCount++;
/* 334 */                 minLength = 0;
/*     */               } 
/* 337 */               if (p == max) {
/* 338 */                 if (maxLength == 0)
/* 339 */                   maxStart = x; 
/* 340 */                 maxLength++;
/* 341 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 342 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 343 */                 maxCount++;
/* 344 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 349 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 350 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 351 */             maxCount++;
/*     */           } 
/* 354 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 355 */             minList.add(new int[] { minStart, y, minLength });
/* 356 */             minCount++;
/*     */           } 
/*     */         } 
/* 360 */         this.extrema[0][b] = min;
/* 361 */         this.extrema[1][b] = max;
/* 362 */         this.minCounts[b] = minCount;
/* 363 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsUShort(UnpackedImageData uid) {
/* 369 */     Rectangle rect = uid.rect;
/* 370 */     short[][] data = uid.getShortData();
/* 371 */     int lineStride = uid.lineStride;
/* 372 */     int pixelStride = uid.pixelStride;
/* 374 */     int lineInc = lineStride * this.yPeriod;
/* 375 */     int pixelInc = pixelStride * this.xPeriod;
/* 377 */     if (!this.saveLocations) {
/* 378 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 379 */         int min = (int)this.extrema[0][b];
/* 380 */         int max = (int)this.extrema[1][b];
/* 382 */         short[] d = data[b];
/* 383 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 385 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 386 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 388 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 389 */             int p = d[po] & 0xFFFF;
/* 391 */             if (p < min) {
/* 392 */               min = p;
/* 393 */             } else if (p > max) {
/* 394 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 398 */         this.extrema[0][b] = min;
/* 399 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 402 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 403 */         int min = (int)this.extrema[0][b];
/* 404 */         int max = (int)this.extrema[1][b];
/* 405 */         ArrayList minList = this.minLocations[b];
/* 406 */         ArrayList maxList = this.maxLocations[b];
/* 407 */         int minCount = this.minCounts[b];
/* 408 */         int maxCount = this.maxCounts[b];
/* 410 */         short[] d = data[b];
/* 411 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 413 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 414 */           lo += lineInc, y += this.yPeriod) {
/* 416 */           int lastPixel = lo + rect.width * pixelStride;
/* 417 */           int minStart = 0;
/* 418 */           int maxStart = 0;
/* 419 */           int minLength = 0;
/* 420 */           int maxLength = 0;
/*     */           int x;
/* 422 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 423 */             x += this.xPeriod) {
/* 425 */             int p = d[po] & 0xFFFF;
/* 427 */             if (p < min) {
/* 428 */               min = p;
/* 429 */               minStart = x;
/* 430 */               minLength = 1;
/* 431 */               minList.clear();
/* 432 */               minCount = 0;
/* 433 */             } else if (p > max) {
/* 434 */               max = p;
/* 435 */               maxStart = x;
/* 436 */               maxLength = 1;
/* 437 */               maxList.clear();
/* 438 */               maxCount = 0;
/*     */             } else {
/* 440 */               if (p == min) {
/* 441 */                 if (minLength == 0)
/* 442 */                   minStart = x; 
/* 443 */                 minLength++;
/* 444 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 445 */                 minList.add(new int[] { minStart, y, minLength });
/* 446 */                 minCount++;
/* 447 */                 minLength = 0;
/*     */               } 
/* 450 */               if (p == max) {
/* 451 */                 if (maxLength == 0)
/* 452 */                   maxStart = x; 
/* 453 */                 maxLength++;
/* 454 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 455 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 456 */                 maxCount++;
/* 457 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 462 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 463 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 464 */             maxCount++;
/*     */           } 
/* 467 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 468 */             minList.add(new int[] { minStart, y, minLength });
/* 469 */             minCount++;
/*     */           } 
/*     */         } 
/* 473 */         this.extrema[0][b] = min;
/* 474 */         this.extrema[1][b] = max;
/* 475 */         this.minCounts[b] = minCount;
/* 476 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsShort(UnpackedImageData uid) {
/* 482 */     Rectangle rect = uid.rect;
/* 483 */     short[][] data = uid.getShortData();
/* 484 */     int lineStride = uid.lineStride;
/* 485 */     int pixelStride = uid.pixelStride;
/* 487 */     int lineInc = lineStride * this.yPeriod;
/* 488 */     int pixelInc = pixelStride * this.xPeriod;
/* 490 */     if (!this.saveLocations) {
/* 491 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 492 */         int min = (int)this.extrema[0][b];
/* 493 */         int max = (int)this.extrema[1][b];
/* 495 */         short[] d = data[b];
/* 496 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 498 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 499 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 501 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 502 */             int p = d[po];
/* 504 */             if (p < min) {
/* 505 */               min = p;
/* 506 */             } else if (p > max) {
/* 507 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 511 */         this.extrema[0][b] = min;
/* 512 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 515 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 516 */         int min = (int)this.extrema[0][b];
/* 517 */         int max = (int)this.extrema[1][b];
/* 518 */         ArrayList minList = this.minLocations[b];
/* 519 */         ArrayList maxList = this.maxLocations[b];
/* 520 */         int minCount = this.minCounts[b];
/* 521 */         int maxCount = this.maxCounts[b];
/* 523 */         short[] d = data[b];
/* 524 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 526 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 527 */           lo += lineInc, y += this.yPeriod) {
/* 529 */           int lastPixel = lo + rect.width * pixelStride;
/* 530 */           int minStart = 0;
/* 531 */           int maxStart = 0;
/* 532 */           int minLength = 0;
/* 533 */           int maxLength = 0;
/*     */           int x;
/* 535 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 536 */             x += this.xPeriod) {
/* 538 */             int p = d[po];
/* 540 */             if (p < min) {
/* 541 */               min = p;
/* 542 */               minStart = x;
/* 543 */               minLength = 1;
/* 544 */               minList.clear();
/* 545 */               minCount = 0;
/* 546 */             } else if (p > max) {
/* 547 */               max = p;
/* 548 */               maxStart = x;
/* 549 */               maxLength = 1;
/* 550 */               maxList.clear();
/* 551 */               maxCount = 0;
/*     */             } else {
/* 553 */               if (p == min) {
/* 554 */                 if (minLength == 0)
/* 555 */                   minStart = x; 
/* 556 */                 minLength++;
/* 557 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 558 */                 minList.add(new int[] { minStart, y, minLength });
/* 559 */                 minCount++;
/* 560 */                 minLength = 0;
/*     */               } 
/* 563 */               if (p == max) {
/* 564 */                 if (maxLength == 0)
/* 565 */                   maxStart = x; 
/* 566 */                 maxLength++;
/* 567 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 568 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 569 */                 maxCount++;
/* 570 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 575 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 576 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 577 */             maxCount++;
/*     */           } 
/* 580 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 581 */             minList.add(new int[] { minStart, y, minLength });
/* 582 */             minCount++;
/*     */           } 
/*     */         } 
/* 586 */         this.extrema[0][b] = min;
/* 587 */         this.extrema[1][b] = max;
/* 588 */         this.minCounts[b] = minCount;
/* 589 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsInt(UnpackedImageData uid) {
/* 595 */     Rectangle rect = uid.rect;
/* 596 */     int[][] data = uid.getIntData();
/* 597 */     int lineStride = uid.lineStride;
/* 598 */     int pixelStride = uid.pixelStride;
/* 600 */     int lineInc = lineStride * this.yPeriod;
/* 601 */     int pixelInc = pixelStride * this.xPeriod;
/* 603 */     if (!this.saveLocations) {
/* 604 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 605 */         int min = (int)this.extrema[0][b];
/* 606 */         int max = (int)this.extrema[1][b];
/* 608 */         int[] d = data[b];
/* 609 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 611 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 612 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 614 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 615 */             int p = d[po];
/* 617 */             if (p < min) {
/* 618 */               min = p;
/* 619 */             } else if (p > max) {
/* 620 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 624 */         this.extrema[0][b] = min;
/* 625 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 628 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 629 */         int min = (int)this.extrema[0][b];
/* 630 */         int max = (int)this.extrema[1][b];
/* 631 */         ArrayList minList = this.minLocations[b];
/* 632 */         ArrayList maxList = this.maxLocations[b];
/* 633 */         int minCount = this.minCounts[b];
/* 634 */         int maxCount = this.maxCounts[b];
/* 636 */         int[] d = data[b];
/* 637 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 639 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 640 */           lo += lineInc, y += this.yPeriod) {
/* 642 */           int lastPixel = lo + rect.width * pixelStride;
/* 643 */           int minStart = 0;
/* 644 */           int maxStart = 0;
/* 645 */           int minLength = 0;
/* 646 */           int maxLength = 0;
/*     */           int x;
/* 648 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 649 */             x += this.xPeriod) {
/* 651 */             int p = d[po];
/* 653 */             if (p < min) {
/* 654 */               min = p;
/* 655 */               minStart = x;
/* 656 */               minLength = 1;
/* 657 */               minList.clear();
/* 658 */               minCount = 0;
/* 659 */             } else if (p > max) {
/* 660 */               max = p;
/* 661 */               maxStart = x;
/* 662 */               maxLength = 1;
/* 663 */               maxList.clear();
/* 664 */               maxCount = 0;
/*     */             } else {
/* 666 */               if (p == min) {
/* 667 */                 if (minLength == 0)
/* 668 */                   minStart = x; 
/* 669 */                 minLength++;
/* 670 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 671 */                 minList.add(new int[] { minStart, y, minLength });
/* 672 */                 minCount++;
/* 673 */                 minLength = 0;
/*     */               } 
/* 676 */               if (p == max) {
/* 677 */                 if (maxLength == 0)
/* 678 */                   maxStart = x; 
/* 679 */                 maxLength++;
/* 680 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 681 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 682 */                 maxCount++;
/* 683 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 688 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 689 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 690 */             maxCount++;
/*     */           } 
/* 693 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 694 */             minList.add(new int[] { minStart, y, minLength });
/* 695 */             minCount++;
/*     */           } 
/*     */         } 
/* 699 */         this.extrema[0][b] = min;
/* 700 */         this.extrema[1][b] = max;
/* 701 */         this.minCounts[b] = minCount;
/* 702 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsFloat(UnpackedImageData uid) {
/* 708 */     Rectangle rect = uid.rect;
/* 709 */     float[][] data = uid.getFloatData();
/* 710 */     int lineStride = uid.lineStride;
/* 711 */     int pixelStride = uid.pixelStride;
/* 713 */     int lineInc = lineStride * this.yPeriod;
/* 714 */     int pixelInc = pixelStride * this.xPeriod;
/* 716 */     if (!this.saveLocations) {
/* 717 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 718 */         float min = (float)this.extrema[0][b];
/* 719 */         float max = (float)this.extrema[1][b];
/* 721 */         float[] d = data[b];
/* 722 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 724 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 725 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 727 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 728 */             float p = d[po];
/* 730 */             if (p < min) {
/* 731 */               min = p;
/* 732 */             } else if (p > max) {
/* 733 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 737 */         this.extrema[0][b] = min;
/* 738 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 741 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 742 */         float min = (float)this.extrema[0][b];
/* 743 */         float max = (float)this.extrema[1][b];
/* 744 */         ArrayList minList = this.minLocations[b];
/* 745 */         ArrayList maxList = this.maxLocations[b];
/* 746 */         int minCount = this.minCounts[b];
/* 747 */         int maxCount = this.maxCounts[b];
/* 749 */         float[] d = data[b];
/* 750 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 752 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 753 */           lo += lineInc, y += this.yPeriod) {
/* 755 */           int lastPixel = lo + rect.width * pixelStride;
/* 756 */           int minStart = 0;
/* 757 */           int maxStart = 0;
/* 758 */           int minLength = 0;
/* 759 */           int maxLength = 0;
/*     */           int x;
/* 761 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 762 */             x += this.xPeriod) {
/* 764 */             float p = d[po];
/* 766 */             if (p < min) {
/* 767 */               min = p;
/* 768 */               minStart = x;
/* 769 */               minLength = 1;
/* 770 */               minList.clear();
/* 771 */               minCount = 0;
/* 772 */             } else if (p > max) {
/* 773 */               max = p;
/* 774 */               maxStart = x;
/* 775 */               maxLength = 1;
/* 776 */               maxList.clear();
/* 777 */               maxCount = 0;
/*     */             } else {
/* 779 */               if (p == min) {
/* 780 */                 if (minLength == 0)
/* 781 */                   minStart = x; 
/* 782 */                 minLength++;
/* 783 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 784 */                 minList.add(new int[] { minStart, y, minLength });
/* 785 */                 minCount++;
/* 786 */                 minLength = 0;
/*     */               } 
/* 789 */               if (p == max) {
/* 790 */                 if (maxLength == 0)
/* 791 */                   maxStart = x; 
/* 792 */                 maxLength++;
/* 793 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 794 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 795 */                 maxCount++;
/* 796 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 801 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 802 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 803 */             maxCount++;
/*     */           } 
/* 806 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 807 */             minList.add(new int[] { minStart, y, minLength });
/* 808 */             minCount++;
/*     */           } 
/*     */         } 
/* 812 */         this.extrema[0][b] = min;
/* 813 */         this.extrema[1][b] = max;
/* 814 */         this.minCounts[b] = minCount;
/* 815 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsDouble(UnpackedImageData uid) {
/* 821 */     Rectangle rect = uid.rect;
/* 822 */     double[][] data = uid.getDoubleData();
/* 823 */     int lineStride = uid.lineStride;
/* 824 */     int pixelStride = uid.pixelStride;
/* 826 */     int lineInc = lineStride * this.yPeriod;
/* 827 */     int pixelInc = pixelStride * this.xPeriod;
/* 829 */     if (!this.saveLocations) {
/* 830 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 831 */         double min = this.extrema[0][b];
/* 832 */         double max = this.extrema[1][b];
/* 834 */         double[] d = data[b];
/* 835 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int lo;
/* 837 */         for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 838 */           int lastPixel = lo + rect.width * pixelStride;
/*     */           int po;
/* 840 */           for (po = lo; po < lastPixel; po += pixelInc) {
/* 841 */             double p = d[po];
/* 843 */             if (p < min) {
/* 844 */               min = p;
/* 845 */             } else if (p > max) {
/* 846 */               max = p;
/*     */             } 
/*     */           } 
/*     */         } 
/* 850 */         this.extrema[0][b] = min;
/* 851 */         this.extrema[1][b] = max;
/*     */       } 
/*     */     } else {
/* 854 */       for (int b = 0; b < this.srcPA.numBands; b++) {
/* 855 */         double min = this.extrema[0][b];
/* 856 */         double max = this.extrema[1][b];
/* 857 */         ArrayList minList = this.minLocations[b];
/* 858 */         ArrayList maxList = this.maxLocations[b];
/* 859 */         int minCount = this.minCounts[b];
/* 860 */         int maxCount = this.maxCounts[b];
/* 862 */         double[] d = data[b];
/* 863 */         int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */         int y;
/* 865 */         for (int lo = uid.bandOffsets[b]; lo < lastLine; 
/* 866 */           lo += lineInc, y += this.yPeriod) {
/* 868 */           int lastPixel = lo + rect.width * pixelStride;
/* 869 */           int minStart = 0;
/* 870 */           int maxStart = 0;
/* 871 */           int minLength = 0;
/* 872 */           int maxLength = 0;
/*     */           int x;
/* 874 */           for (int po = lo; po < lastPixel; po += pixelInc, 
/* 875 */             x += this.xPeriod) {
/* 877 */             double p = d[po];
/* 879 */             if (p < min) {
/* 880 */               min = p;
/* 881 */               minStart = x;
/* 882 */               minLength = 1;
/* 883 */               minList.clear();
/* 884 */               minCount = 0;
/* 885 */             } else if (p > max) {
/* 886 */               max = p;
/* 887 */               maxStart = x;
/* 888 */               maxLength = 1;
/* 889 */               maxList.clear();
/* 890 */               maxCount = 0;
/*     */             } else {
/* 892 */               if (p == min) {
/* 893 */                 if (minLength == 0)
/* 894 */                   minStart = x; 
/* 895 */                 minLength++;
/* 896 */               } else if (minLength > 0 && minCount < this.maxRuns) {
/* 897 */                 minList.add(new int[] { minStart, y, minLength });
/* 898 */                 minCount++;
/* 899 */                 minLength = 0;
/*     */               } 
/* 902 */               if (p == max) {
/* 903 */                 if (maxLength == 0)
/* 904 */                   maxStart = x; 
/* 905 */                 maxLength++;
/* 906 */               } else if (maxLength > 0 && maxCount < this.maxRuns) {
/* 907 */                 maxList.add(new int[] { maxStart, y, maxLength });
/* 908 */                 maxCount++;
/* 909 */                 maxLength = 0;
/*     */               } 
/*     */             } 
/*     */           } 
/* 914 */           if (maxLength > 0 && maxCount < this.maxRuns) {
/* 915 */             maxList.add(new int[] { maxStart, y, maxLength });
/* 916 */             maxCount++;
/*     */           } 
/* 919 */           if (minLength > 0 && minCount < this.maxRuns) {
/* 920 */             minList.add(new int[] { minStart, y, minLength });
/* 921 */             minCount++;
/*     */           } 
/*     */         } 
/* 925 */         this.extrema[0][b] = min;
/* 926 */         this.extrema[1][b] = max;
/* 927 */         this.minCounts[b] = minCount;
/* 928 */         this.maxCounts[b] = maxCount;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void initializeState(Raster source) {
/* 934 */     if (this.extrema == null) {
/* 935 */       int numBands = this.sampleModel.getNumBands();
/* 936 */       this.extrema = new double[2][numBands];
/* 938 */       Rectangle rect = source.getBounds();
/* 945 */       if (this.roi != null) {
/* 946 */         LinkedList rectList = this.roi.getAsRectangleList(rect.x, rect.y, rect.width, rect.height);
/* 950 */         if (rectList == null)
/*     */           return; 
/* 953 */         ListIterator iterator = rectList.listIterator(0);
/* 954 */         if (iterator.hasNext())
/* 955 */           rect = rect.intersection(iterator.next()); 
/*     */       } 
/* 959 */       rect.x = startPosition(rect.x, this.xStart, this.xPeriod);
/* 960 */       rect.y = startPosition(rect.y, this.yStart, this.yPeriod);
/* 961 */       source.getPixel(rect.x, rect.y, this.extrema[0]);
/*     */       int i;
/* 963 */       for (i = 0; i < numBands; i++)
/* 964 */         this.extrema[1][i] = this.extrema[0][i]; 
/* 967 */       if (this.saveLocations) {
/* 968 */         this.minLocations = new ArrayList[numBands];
/* 969 */         this.maxLocations = new ArrayList[numBands];
/* 970 */         this.minCounts = new int[numBands];
/* 971 */         this.maxCounts = new int[numBands];
/* 972 */         for (i = 0; i < numBands; i++) {
/* 973 */           this.minLocations[i] = new ArrayList();
/* 974 */           this.maxLocations[i] = new ArrayList();
/* 975 */           this.maxCounts[i] = 0;
/* 975 */           this.minCounts[i] = 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ExtremaOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */