/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public final class WarpGrid extends Warp {
/*     */   private int xStart;
/*     */   
/*     */   private int yStart;
/*     */   
/*     */   private int xEnd;
/*     */   
/*     */   private int yEnd;
/*     */   
/*     */   private int xStep;
/*     */   
/*     */   private int yStep;
/*     */   
/*     */   private int xNumCells;
/*     */   
/*     */   private int yNumCells;
/*     */   
/*     */   private float[] xWarpPos;
/*     */   
/*     */   private float[] yWarpPos;
/*     */   
/*     */   private void initialize(int xStart, int xStep, int xNumCells, int yStart, int yStep, int yNumCells, float[] warpPositions) {
/* 104 */     this.xStart = xStart;
/* 105 */     this.yStart = yStart;
/* 107 */     this.xEnd = xStart + xStep * xNumCells;
/* 108 */     this.yEnd = yStart + yStep * yNumCells;
/* 110 */     this.xStep = xStep;
/* 111 */     this.yStep = yStep;
/* 113 */     this.xNumCells = xNumCells;
/* 114 */     this.yNumCells = yNumCells;
/* 116 */     int xNumGrids = xNumCells + 1;
/* 117 */     int yNumGrids = yNumCells + 1;
/* 119 */     int numNodes = yNumGrids * xNumGrids;
/* 121 */     this.xWarpPos = new float[numNodes];
/* 122 */     this.yWarpPos = new float[numNodes];
/* 124 */     int index = 0;
/* 125 */     for (int idx = 0; idx < numNodes; idx++) {
/* 126 */       this.xWarpPos[idx] = warpPositions[index++];
/* 127 */       this.yWarpPos[idx] = warpPositions[index++];
/*     */     } 
/*     */   }
/*     */   
/*     */   public WarpGrid(int xStart, int xStep, int xNumCells, int yStart, int yStep, int yNumCells, float[] warpPositions) {
/* 175 */     if (warpPositions.length != 2 * (xNumCells + 1) * (yNumCells + 1))
/* 176 */       throw new IllegalArgumentException(JaiI18N.getString("WarpGrid0")); 
/* 179 */     initialize(xStart, xStep, xNumCells, yStart, yStep, yNumCells, warpPositions);
/*     */   }
/*     */   
/*     */   public WarpGrid(Warp master, int xStart, int xStep, int xNumCells, int yStart, int yStep, int yNumCells) {
/* 205 */     int size = 2 * (xNumCells + 1) * (yNumCells + 1);
/* 207 */     float[] warpPositions = new float[size];
/* 208 */     warpPositions = master.warpSparseRect(xStart, yStart, xNumCells * xStep + 1, yNumCells * yStep + 1, xStep, yStep, warpPositions);
/* 214 */     initialize(xStart, xStep, xNumCells, yStart, yStep, yNumCells, warpPositions);
/*     */   }
/*     */   
/*     */   public int getXStart() {
/* 221 */     return this.xStart;
/*     */   }
/*     */   
/*     */   public int getYStart() {
/* 226 */     return this.yStart;
/*     */   }
/*     */   
/*     */   public int getXStep() {
/* 231 */     return this.xStep;
/*     */   }
/*     */   
/*     */   public int getYStep() {
/* 236 */     return this.yStep;
/*     */   }
/*     */   
/*     */   public int getXNumCells() {
/* 241 */     return this.xNumCells;
/*     */   }
/*     */   
/*     */   public int getYNumCells() {
/* 246 */     return this.yNumCells;
/*     */   }
/*     */   
/*     */   public float[] getXWarpPos() {
/* 251 */     return this.xWarpPos;
/*     */   }
/*     */   
/*     */   public float[] getYWarpPos() {
/* 256 */     return this.yWarpPos;
/*     */   }
/*     */   
/*     */   private float[] noWarpSparseRect(int x1, int x2, int y1, int y2, int periodX, int periodY, int offset, int stride, float[] destRect) {
/* 281 */     if (destRect == null)
/* 282 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*     */     int j;
/* 285 */     for (j = y1; j <= y2; j += periodY) {
/* 286 */       int index = offset;
/* 287 */       offset += stride;
/*     */       int i;
/* 289 */       for (i = x1; i <= x2; i += periodX) {
/* 290 */         destRect[index++] = i;
/* 291 */         destRect[index++] = j;
/*     */       } 
/*     */     } 
/* 295 */     return destRect;
/*     */   }
/*     */   
/*     */   public float[] warpSparseRect(int x, int y, int width, int height, int periodX, int periodY, float[] destRect) {
/* 326 */     int stride = 2 * (width + periodX - 1) / periodX;
/* 328 */     if (destRect == null)
/* 329 */       destRect = new float[stride * (height + periodY - 1) / periodY]; 
/* 332 */     int x1 = x;
/* 333 */     int x2 = x + width - 1;
/* 334 */     int y1 = y;
/* 335 */     int y2 = y + height - 1;
/* 337 */     if (y1 >= this.yEnd || y2 < this.yStart || x1 >= this.xEnd || x2 < this.xStart)
/* 339 */       return noWarpSparseRect(x1, x2, y1, y2, periodX, periodY, 0, stride, destRect); 
/* 343 */     if (y1 < this.yStart) {
/* 344 */       int periods = (this.yStart - y1 + periodY - 1) / periodY;
/* 345 */       noWarpSparseRect(x1, x2, y1, this.yStart - 1, periodX, periodY, 0, stride, destRect);
/* 347 */       y1 += periods * periodY;
/*     */     } 
/* 350 */     if (y2 >= this.yEnd) {
/* 351 */       int periods = (this.yEnd - y + periodY - 1) / periodY;
/* 352 */       noWarpSparseRect(x1, x2, y + periods * periodY, y2, periodX, periodY, periods * stride, stride, destRect);
/* 356 */       y2 = y + (periods - 1) * periodY;
/*     */     } 
/* 359 */     if (x1 < this.xStart) {
/* 360 */       int periods = (this.xStart - x1 + periodX - 1) / periodX;
/* 361 */       noWarpSparseRect(x1, this.xStart - 1, y1, y2, periodX, periodY, (y1 - y) / periodY * stride, stride, destRect);
/* 363 */       x1 += periods * periodX;
/*     */     } 
/* 366 */     if (x2 >= this.xEnd) {
/* 367 */       int periods = (this.xEnd - x + periodX - 1) / periodX;
/* 368 */       noWarpSparseRect(x + periods * periodX, x2, y1, y2, periodX, periodY, (y1 - y) / periodY * stride + periods * 2, stride, destRect);
/* 373 */       x2 = x + (periods - 1) * periodX;
/*     */     } 
/* 384 */     int[] cellPoints = new int[this.xNumCells];
/*     */     int i;
/* 385 */     for (i = x1; i <= x2; i += periodX)
/* 386 */       cellPoints[(i - this.xStart) / this.xStep] = cellPoints[(i - this.xStart) / this.xStep] + 1; 
/* 389 */     int offset = (y1 - y) / periodY * stride + (x1 - x) / periodX * 2;
/* 392 */     int xNumGrids = this.xNumCells + 1;
/* 395 */     float deltaX = periodX / this.xStep;
/*     */     int j;
/* 398 */     for (j = y1; j <= y2; j += periodY) {
/* 399 */       int index = offset;
/* 400 */       offset += stride;
/* 402 */       int yCell = (j - this.yStart) / this.yStep;
/* 403 */       int yGrid = this.yStart + yCell * this.yStep;
/* 404 */       float yFrac = (j + 0.5F - yGrid) / this.yStep;
/* 407 */       float deltaTop = (1.0F - yFrac) * deltaX;
/* 408 */       float deltaBottom = yFrac * deltaX;
/* 410 */       int k = x1;
/* 411 */       while (k <= x2) {
/* 413 */         int xCell = (k - this.xStart) / this.xStep;
/* 414 */         int xGrid = this.xStart + xCell * this.xStep;
/* 415 */         float xFrac = (k + 0.5F - xGrid) / this.xStep;
/* 417 */         int nodeOffset = yCell * xNumGrids + xCell;
/* 418 */         float wx0 = this.xWarpPos[nodeOffset];
/* 419 */         float wy0 = this.yWarpPos[nodeOffset];
/* 420 */         float wx1 = this.xWarpPos[++nodeOffset];
/* 421 */         float wy1 = this.yWarpPos[nodeOffset];
/* 422 */         nodeOffset += this.xNumCells;
/* 423 */         float wx2 = this.xWarpPos[nodeOffset];
/* 424 */         float wy2 = this.yWarpPos[nodeOffset];
/* 425 */         float wx3 = this.xWarpPos[++nodeOffset];
/* 426 */         float wy3 = this.yWarpPos[nodeOffset];
/* 428 */         float s = wx0 + (wx1 - wx0) * xFrac;
/* 429 */         float t = wy0 + (wy1 - wy0) * xFrac;
/* 430 */         float u = wx2 + (wx3 - wx2) * xFrac;
/* 431 */         float v = wy2 + (wy3 - wy2) * xFrac;
/* 433 */         float wx = s + (u - s) * yFrac;
/* 434 */         float wy = t + (v - t) * yFrac;
/* 437 */         float dx = (wx1 - wx0) * deltaTop + (wx3 - wx2) * deltaBottom;
/* 438 */         float dy = (wy1 - wy0) * deltaTop + (wy3 - wy2) * deltaBottom;
/* 441 */         int nPoints = cellPoints[xCell];
/* 442 */         for (int m = 0; m < nPoints; m++) {
/* 443 */           destRect[index++] = wx - 0.5F;
/* 444 */           destRect[index++] = wy - 0.5F;
/* 446 */           wx += dx;
/* 447 */           wy += dy;
/* 448 */           k += periodX;
/*     */         } 
/*     */       } 
/*     */     } 
/* 453 */     return destRect;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 491 */     if (destPt == null)
/* 492 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 495 */     float[] sxy = warpSparseRect((int)destPt.getX(), (int)destPt.getY(), 2, 2, 1, 1, null);
/* 498 */     double wtRight = destPt.getX() - (int)destPt.getX();
/* 499 */     double wtLeft = 1.0D - wtRight;
/* 500 */     double wtBottom = destPt.getY() - (int)destPt.getY();
/* 501 */     double wtTop = 1.0D - wtBottom;
/* 503 */     Point2D pt = (Point2D)destPt.clone();
/* 504 */     pt.setLocation((sxy[0] * wtLeft + sxy[2] * wtRight) * wtTop + (sxy[4] * wtLeft + sxy[6] * wtRight) * wtBottom, (sxy[1] * wtLeft + sxy[3] * wtRight) * wtTop + (sxy[5] * wtLeft + sxy[7] * wtRight) * wtBottom);
/* 509 */     return pt;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\WarpGrid.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */