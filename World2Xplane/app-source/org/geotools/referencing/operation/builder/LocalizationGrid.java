/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.util.Arrays;
/*     */ import org.geotools.referencing.operation.transform.ProjectiveTransform;
/*     */ import org.geotools.referencing.operation.transform.WarpTransform2D;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ 
/*     */ public class LocalizationGrid {
/*     */   private static final int X_OFFSET = 0;
/*     */   
/*     */   private static final int Y_OFFSET = 1;
/*     */   
/*     */   private static final int CP_LENGTH = 2;
/*     */   
/*     */   private final int width;
/*     */   
/*     */   private final int height;
/*     */   
/*     */   private double[] grid;
/*     */   
/*     */   private transient AffineTransform global;
/*     */   
/*     */   private transient MathTransform2D[] transforms;
/*     */   
/*     */   private static final int INCREASING = 1;
/*     */   
/*     */   private static final int DECREASING = 2;
/*     */   
/*     */   private static final int EQUALS = 4;
/*     */   
/*     */   public LocalizationGrid(int width, int height) {
/* 194 */     if (width < 2)
/* 195 */       throw new IllegalArgumentException(String.valueOf(width)); 
/* 197 */     if (height < 2)
/* 198 */       throw new IllegalArgumentException(String.valueOf(height)); 
/* 200 */     this.width = width;
/* 201 */     this.height = height;
/* 202 */     this.grid = new double[width * height * 2];
/* 203 */     Arrays.fill(this.grid, Double.NaN);
/*     */   }
/*     */   
/*     */   private int computeOffset(int col, int row) {
/* 214 */     if (col < 0 || col >= this.width)
/* 215 */       throw new IndexOutOfBoundsException(String.valueOf(col)); 
/* 217 */     if (row < 0 || row >= this.height)
/* 218 */       throw new IndexOutOfBoundsException(String.valueOf(row)); 
/* 220 */     return (col + row * this.width) * 2;
/*     */   }
/*     */   
/*     */   public Dimension getSize() {
/* 229 */     return new Dimension(this.width, this.height);
/*     */   }
/*     */   
/*     */   public synchronized Point2D getLocalizationPoint(Point source) {
/* 243 */     int offset = computeOffset(source.x, source.y);
/* 244 */     return new Point2D.Double(this.grid[offset + 0], this.grid[offset + 1]);
/*     */   }
/*     */   
/*     */   public void setLocalizationPoint(Point source, Point2D target) {
/* 256 */     setLocalizationPoint(source.x, source.y, target.getX(), target.getY());
/*     */   }
/*     */   
/*     */   public synchronized void setLocalizationPoint(int sourceX, int sourceY, double targetX, double targetY) {
/* 273 */     int offset = computeOffset(sourceX, sourceY);
/* 274 */     notifyChange();
/* 275 */     this.global = null;
/* 276 */     this.grid[offset + 0] = targetX;
/* 277 */     this.grid[offset + 1] = targetY;
/*     */   }
/*     */   
/*     */   public synchronized void transform(AffineTransform transform, Rectangle region) {
/* 292 */     if (region == null) {
/* 293 */       transform.transform(this.grid, 0, this.grid, 0, this.width * this.height);
/*     */       return;
/*     */     } 
/* 296 */     computeOffset(region.x, region.y);
/* 297 */     int j = region.x + region.width;
/* 298 */     if (j > this.width)
/* 299 */       throw new IndexOutOfBoundsException(String.valueOf(j)); 
/* 301 */     j = region.y + region.height;
/* 302 */     while (--j >= region.y) {
/* 303 */       int offset = computeOffset(region.x, j);
/* 304 */       notifyChange();
/* 305 */       transform.transform(this.grid, offset, this.grid, offset, region.width);
/*     */     } 
/* 307 */     this.global = null;
/*     */   }
/*     */   
/*     */   public synchronized boolean isNaN() {
/* 315 */     for (int i = this.grid.length; --i >= 0;) {
/* 316 */       if (Double.isNaN(this.grid[i]))
/* 317 */         return true; 
/*     */     } 
/* 320 */     return false;
/*     */   }
/*     */   
/*     */   public synchronized boolean isMonotonic(boolean strict) {
/* 344 */     int orderX = 3;
/* 345 */     int orderY = 3;
/* 346 */     if (!strict) {
/* 347 */       orderX |= 0x4;
/* 348 */       orderY |= 0x4;
/*     */     } 
/* 350 */     for (int i = 0; i < this.width; i++) {
/* 351 */       int offset = computeOffset(i, 0);
/* 352 */       int s = 2 * this.width;
/* 353 */       if ((orderX = testOrder(this.grid, offset + 0, this.height, s, orderX)) == 0)
/* 353 */         return false; 
/* 354 */       if ((orderY = testOrder(this.grid, offset + 1, this.height, s, orderY)) == 0)
/* 354 */         return false; 
/*     */     } 
/* 356 */     orderX = 3;
/* 357 */     orderY = 3;
/* 358 */     if (!strict) {
/* 359 */       orderX |= 0x4;
/* 360 */       orderY |= 0x4;
/*     */     } 
/* 362 */     for (int j = 0; j < this.height; j++) {
/* 363 */       int offset = computeOffset(0, j);
/* 364 */       int s = 2;
/* 365 */       if ((orderX = testOrder(this.grid, offset + 0, this.width, 2, orderX)) == 0)
/* 365 */         return false; 
/* 366 */       if ((orderY = testOrder(this.grid, offset + 1, this.width, 2, orderY)) == 0)
/* 366 */         return false; 
/*     */     } 
/* 368 */     return true;
/*     */   }
/*     */   
/*     */   private static int testOrder(double[] grid, int offset, int num, int step, int flags) {
/* 389 */     num--;
/* 389 */     for (; --num >= 0; offset += step) {
/* 390 */       double v1 = grid[offset];
/* 391 */       if (!Double.isNaN(v1)) {
/*     */         int required, clear;
/*     */         while (true) {
/* 393 */           double v2 = grid[offset + step];
/* 395 */           if (v1 == v2) {
/* 396 */             required = 4;
/* 397 */             clear = -1;
/*     */             break;
/*     */           } 
/* 398 */           if (v2 > v1) {
/* 399 */             required = 1;
/* 400 */             clear = -3;
/*     */             break;
/*     */           } 
/* 401 */           if (v2 < v1) {
/* 402 */             required = 2;
/* 403 */             clear = -2;
/*     */             break;
/*     */           } 
/* 406 */           if (--num < 0)
/* 407 */             return flags; 
/* 409 */           offset += step;
/*     */         } 
/* 412 */         if ((flags & required) == 0)
/* 413 */           return 0; 
/* 415 */         flags &= clear;
/*     */       } 
/*     */     } 
/* 419 */     return flags;
/*     */   }
/*     */   
/*     */   public void removeSingularities() {
/* 428 */     removeSingularities(0, false);
/* 429 */     removeSingularities(0, true);
/* 430 */     removeSingularities(1, false);
/* 431 */     removeSingularities(1, true);
/*     */   }
/*     */   
/*     */   private void removeSingularities(int index, boolean vertical) {
/*     */     int step;
/*     */     int val1;
/*     */     int val2;
/* 444 */     if (vertical) {
/* 445 */       step = 2 * this.width;
/* 446 */       val1 = this.width;
/* 447 */       val2 = this.height;
/*     */     } else {
/* 449 */       step = 2;
/* 450 */       val1 = this.height;
/* 451 */       val2 = this.width;
/*     */     } 
/* 453 */     for (int i = 0; i < val1; i++) {
/*     */       int offset;
/* 455 */       if (vertical) {
/* 456 */         offset = computeOffset(i, 0) + index;
/*     */       } else {
/* 458 */         offset = computeOffset(0, i) + index;
/*     */       } 
/* 460 */       int singularityOffset = -1;
/* 461 */       for (int j = 1; j < val2; j++) {
/* 462 */         int previousOffset = offset + step * (j - 1);
/* 463 */         int currentOffset = previousOffset + step;
/* 464 */         if (this.grid[previousOffset] == this.grid[currentOffset]) {
/* 465 */           if (singularityOffset == -1)
/* 466 */             singularityOffset = (previousOffset == offset) ? previousOffset : (previousOffset - step); 
/* 469 */         } else if (singularityOffset != -1) {
/* 470 */           int num = (currentOffset - singularityOffset) / step + 1;
/* 471 */           replaceSingularity(this.grid, singularityOffset, num, step);
/* 472 */           singularityOffset = -1;
/*     */         } 
/*     */       } 
/* 475 */       if (singularityOffset != -1) {
/* 476 */         int currentOffset = offset + step * (val2 - 1);
/* 477 */         int num = (currentOffset - singularityOffset) / step + 1;
/* 478 */         replaceSingularity(this.grid, singularityOffset, num, step);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void replaceSingularity(double[] grid, int offset, int num, int step) {
/* 511 */     double increment = (grid[offset + (num - 1) * step] - grid[offset]) / (num - 1);
/* 512 */     double value = grid[offset];
/* 513 */     offset += step;
/* 514 */     for (int i = 0; i < num - 2; i++, offset += step)
/* 515 */       grid[offset] = value + increment * (i + 1); 
/*     */   }
/*     */   
/*     */   public synchronized AffineTransform getAffineTransform() {
/* 527 */     if (this.global == null) {
/* 528 */       double[] matrix = new double[6];
/* 529 */       fitPlane(0, matrix);
/* 530 */       fitPlane(1, matrix);
/* 531 */       this.global = new AffineTransform(matrix);
/*     */     } 
/* 533 */     return (AffineTransform)this.global.clone();
/*     */   }
/*     */   
/*     */   private void fitPlane(int offset, double[] coeff) {
/* 566 */     double zy = 0.0D, zx = zy, z = zx;
/* 567 */     int n = offset;
/* 568 */     for (int yi = 0; yi < this.height; yi++) {
/* 569 */       for (int xi = 0; xi < this.width; xi++) {
/* 570 */         assert computeOffset(xi, yi) + offset == n : n;
/* 571 */         double zi = this.grid[n];
/* 572 */         z += zi;
/* 573 */         zx += zi * xi;
/* 574 */         zy += zi * yi;
/* 575 */         n += 2;
/*     */       } 
/*     */     } 
/* 578 */     n = (n - offset) / 2;
/* 579 */     assert n == this.width * this.height : n;
/* 580 */     double x = n * (this.width - 1) / 2.0D;
/* 581 */     double y = n * (this.height - 1) / 2.0D;
/* 582 */     double xx = n * (this.width - 0.5D) * (this.width - 1) / 3.0D;
/* 583 */     double yy = n * (this.height - 0.5D) * (this.height - 1) / 3.0D;
/* 584 */     double xy = n * ((this.height - 1) * (this.width - 1)) / 4.0D;
/* 591 */     zx -= z * x / n;
/* 592 */     zy -= z * y / n;
/* 593 */     xx -= x * x / n;
/* 594 */     xy -= x * y / n;
/* 595 */     yy -= y * y / n;
/* 596 */     double den = xy * xy - xx * yy;
/* 597 */     double cy = (zx * xy - zy * xx) / den;
/* 598 */     double cx = (zy * xy - zx * yy) / den;
/* 599 */     double c = (z - cx * x + cy * y) / n;
/* 600 */     coeff[0 + offset] = cx;
/* 601 */     coeff[2 + offset] = cy;
/* 602 */     coeff[4 + offset] = c;
/*     */   }
/*     */   
/*     */   private MathTransform2D fitWarps(int degree) {
/* 611 */     float[] srcCoords = new float[this.width * this.height * 2];
/* 612 */     float[] dstCoords = new float[srcCoords.length];
/* 613 */     int gridOffset = 0;
/* 614 */     int warpOffset = 0;
/* 615 */     for (int yi = 0; yi < this.height; yi++) {
/* 616 */       for (int xi = 0; xi < this.width; xi++) {
/* 617 */         assert gridOffset == computeOffset(xi, yi);
/* 618 */         float x = (float)this.grid[gridOffset + 0];
/* 619 */         float y = (float)this.grid[gridOffset + 1];
/* 620 */         if (!Float.isNaN(x) && !Float.isNaN(y)) {
/* 621 */           srcCoords[warpOffset] = xi;
/* 622 */           srcCoords[warpOffset + 1] = yi;
/* 623 */           dstCoords[warpOffset] = x;
/* 624 */           dstCoords[warpOffset + 1] = y;
/* 625 */           warpOffset += 2;
/*     */         } 
/* 627 */         gridOffset += 2;
/*     */       } 
/*     */     } 
/* 630 */     return (MathTransform2D)new WarpTransform2D(null, srcCoords, 0, null, dstCoords, 0, warpOffset / 2, degree);
/*     */   }
/*     */   
/*     */   public synchronized MathTransform2D getPolynomialTransform(int degree) {
/* 644 */     if (degree < 0 || degree >= 8)
/* 646 */       throw new IllegalArgumentException(); 
/* 648 */     if (this.transforms == null)
/* 649 */       this.transforms = new MathTransform2D[8]; 
/* 651 */     if (this.transforms[degree] == null) {
/*     */       MathTransform2D tr;
/* 653 */       switch (degree) {
/*     */         case 0:
/* 657 */           tr = new LocalizationGridTransform2D(this.width, this.height, this.grid, getAffineTransform());
/*     */           break;
/*     */         case 1:
/* 661 */           tr = (MathTransform2D)ProjectiveTransform.create(getAffineTransform());
/*     */           break;
/*     */         default:
/* 665 */           tr = fitWarps(degree);
/*     */           break;
/*     */       } 
/* 669 */       this.transforms[degree] = tr;
/*     */     } 
/* 671 */     return this.transforms[degree];
/*     */   }
/*     */   
/*     */   public final MathTransform2D getMathTransform() {
/* 681 */     return getPolynomialTransform(0);
/*     */   }
/*     */   
/*     */   private void notifyChange() {
/* 689 */     if (this.transforms != null) {
/* 690 */       if (this.transforms[0] != null)
/* 692 */         this.grid = (double[])this.grid.clone(); 
/* 695 */       this.transforms = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\LocalizationGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */