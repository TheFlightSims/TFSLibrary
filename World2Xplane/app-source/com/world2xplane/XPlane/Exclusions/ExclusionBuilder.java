/*     */ package com.world2xplane.XPlane.Exclusions;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ExclusionBuilder {
/*     */   private int numberOfColumns;
/*     */   
/*     */   private int numberOfRows;
/*     */   
/*     */   private double tx;
/*     */   
/*     */   private double ty;
/*     */   
/*  50 */   private double divx = 0.0D;
/*     */   
/*  51 */   private double divy = 0.0D;
/*     */   
/*     */   public void buildExclusions(List<Envelope> boxes, int distance, double tx, double ty, boolean forests, String path) {
/*  59 */     double distanceX = GeomUtils.distanceInMeters(new LineSegment2D(tx, ty, tx + 1.0D, ty));
/*  60 */     double distanceY = GeomUtils.distanceInMeters(new LineSegment2D(tx, ty, tx, ty + 1.0D));
/*  62 */     this.tx = tx;
/*  63 */     this.ty = ty;
/*  65 */     this.divx = 1.0D / distanceX / distance;
/*  66 */     this.divy = 1.0D / distanceY / distance;
/*  69 */     this.numberOfRows = (int)(1.0D / this.divx);
/*  70 */     this.numberOfColumns = (int)(1.0D / this.divy);
/*  72 */     byte[][] data = new byte[this.numberOfColumns + 1][this.numberOfRows + 1];
/*  74 */     for (int y = 0; y < this.numberOfColumns; y++) {
/*  75 */       for (int k = 0; k < this.numberOfRows; k++)
/*  76 */         data[y][k] = 0; 
/*     */     } 
/*  80 */     for (Envelope item : boxes) {
/*  82 */       if (item != null) {
/*  83 */         int k = getCoordX(item.getMinX());
/*  84 */         int m = getCoordY(item.getMinY());
/*  85 */         int n = getCoordX(item.getMaxX());
/*  86 */         int i1 = getCoordY(item.getMaxY());
/*  88 */         for (int i2 = k; i2 <= n; i2++) {
/*  89 */           for (int i3 = m; i3 <= i1; i3++) {
/*  90 */             byte val = data[i3][i2];
/*  91 */             if (val > 120)
/*  92 */               val = 120; 
/*  94 */             val = (byte)(val + 1);
/*  95 */             data[i3][i2] = val;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 100 */     boolean[][] grid = new boolean[this.numberOfColumns + 1][this.numberOfRows + 1];
/* 101 */     for (int i = 0; i < this.numberOfColumns; i++) {
/* 102 */       for (int k = 0; k < this.numberOfRows; k++) {
/* 103 */         byte val = data[i][k];
/* 104 */         if (val > 0 && forests) {
/* 105 */           grid[i][k] = true;
/* 106 */         } else if (val >= GeneratorStore.getGeneratorStore().getBuildingsPerGrid() && !forests) {
/* 108 */           grid[i][k] = true;
/*     */         } else {
/* 110 */           grid[i][k] = false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 116 */     int dWidth = 2048;
/* 117 */     int dHeight = 2048;
/* 118 */     BufferedImage bImage = new BufferedImage(dWidth, dHeight, 1);
/* 120 */     Graphics2D g2 = bImage.createGraphics();
/* 122 */     g2.setPaint(new Color(130, 139, 150));
/* 123 */     g2.fillRect(0, 0, dWidth, dHeight);
/* 124 */     g2.setPaint(new Color(111, 222, 73));
/* 126 */     double dx = dWidth / this.numberOfRows;
/* 127 */     double dy = dHeight / this.numberOfColumns;
/* 130 */     List<Box2D> exclusions = new ArrayList<>();
/* 131 */     int x1 = 0;
/* 132 */     int y1 = 0;
/* 133 */     int x2 = 0;
/* 134 */     int y2 = 0;
/* 275 */     int x = 0, j = 0;
/* 276 */     int box_n = 0, box_s = 0, box_w = 0, box_e = 0;
/* 277 */     boolean wrongline = false;
/* 278 */     boolean inside = false;
/* 280 */     for (int row = 0; row < this.numberOfRows; row++) {
/* 281 */       for (int col = 0; col < this.numberOfColumns; col++) {
/* 283 */         if (!inside && grid[col][row]) {
/* 285 */           box_n = row;
/* 286 */           box_w = col;
/* 287 */           inside = true;
/*     */         } 
/* 289 */         if (inside && (!grid[col][row] || col == this.numberOfColumns - 1)) {
/* 291 */           if (col == this.numberOfColumns - 1) {
/* 292 */             box_e = col;
/*     */           } else {
/* 294 */             box_e = col - 1;
/*     */           } 
/* 297 */           if (row < this.numberOfRows - 1) {
/* 298 */             wrongline = false;
/* 300 */             for (j = box_n + 1; j < this.numberOfRows; j++) {
/* 301 */               for (x = box_w; x <= box_e; x++) {
/* 302 */                 if (!grid[x][j]) {
/* 304 */                   wrongline = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 308 */               if (wrongline)
/*     */                 break; 
/* 313 */               for (x = box_w; x <= box_e; ) {
/* 313 */                 grid[x][j] = false;
/* 313 */                 x++;
/*     */               } 
/*     */             } 
/* 317 */             box_s = j - 1;
/*     */           } else {
/* 320 */             box_s = row;
/*     */           } 
/* 323 */           double xcord = 0.0D, ycord = 0.0D, x2cord = 0.0D, y2cord = 0.0D;
/* 324 */           exclusions.add(new Box2D(box_n, box_s, box_w, box_e));
/* 325 */           inside = false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 332 */     boxes.clear();
/* 334 */     for (Box2D item : exclusions) {
/* 336 */       if (GeneratorStore.getGeneratorStore().isEnableDebugImage()) {
/* 337 */         x1 = (int)(item.getMinX() * dx);
/* 338 */         x2 = (int)((item.getMaxX() + 1.0D) * dx);
/* 339 */         y1 = (int)(item.getMinY() * dy);
/* 340 */         y2 = (int)((item.getMaxY() + 1.0D) * dy);
/* 343 */         if (forests) {
/* 344 */           g2.setPaint(Color.green);
/*     */         } else {
/* 346 */           g2.setPaint(Color.red);
/*     */         } 
/* 348 */         g2.fillRect(x1, y1, x2 - x1, y2 - y1);
/*     */       } 
/* 352 */       double a1 = getX(item.getMinX() + 1.0D);
/* 353 */       double a2 = getX(item.getMaxX() + 2.0D);
/* 354 */       double a3 = getY(item.getMinY());
/* 355 */       double a4 = getY(item.getMaxY() + 1.0D);
/* 357 */       if (a1 < tx)
/* 358 */         a1 = tx; 
/* 360 */       if (a2 > tx + 1.0D)
/* 361 */         a2 = tx + 1.0D; 
/* 363 */       if (a3 < ty)
/* 364 */         a3 = ty; 
/* 366 */       if (a4 > ty + 1.0D)
/* 367 */         a4 = ty + 1.0D; 
/* 370 */       Envelope exclusion = new Envelope(a1, a2, a3, a4);
/* 371 */       boxes.add(exclusion);
/*     */     } 
/* 378 */     if (GeneratorStore.getGeneratorStore().isEnableDebugImage())
/*     */       try {
/* 384 */         File parent = new File(new File(path), forests ? ("forests_" + ty + "_" + tx + ".png") : ("buildings_" + ty + "_" + tx + ".png"));
/* 385 */         if (parent != null && !parent.exists() && 
/* 386 */           !parent.mkdirs())
/* 387 */           throw new IOException("File '" + parent + "' could not be created"); 
/* 391 */         if (ImageIO.write(bImage, "jpg", parent));
/* 394 */       } catch (Exception e) {
/* 396 */         e.printStackTrace();
/*     */       }  
/*     */   }
/*     */   
/*     */   private double getX(double minX) {
/* 403 */     double v = this.tx + minX * this.divx;
/* 404 */     return v;
/*     */   }
/*     */   
/*     */   private double getY(double minY) {
/* 408 */     double v = this.ty + minY * this.divy;
/* 409 */     return v;
/*     */   }
/*     */   
/*     */   private boolean lineEqual(int x1, int x2, int y1, int y2, int i, boolean[][] grid) {
/* 414 */     if (i > this.numberOfColumns)
/* 415 */       return false; 
/* 418 */     if (x1 > x2) {
/* 419 */       int swap = x1;
/* 420 */       x1 = x2;
/* 421 */       x2 = swap;
/*     */     } 
/*     */     int x;
/* 424 */     for (x = x1; x <= x2; x++) {
/* 425 */       if (!grid[i][x])
/* 426 */         return false; 
/*     */     } 
/* 431 */     for (x = x1; x <= x2; x++)
/* 432 */       grid[i][x] = false; 
/* 434 */     return true;
/*     */   }
/*     */   
/*     */   private int getCoordX(double value) {
/* 439 */     if (value < 0.0D)
/* 440 */       return (int)FastMath.abs(this.numberOfRows * (value - (int)value - -1.0D)); 
/* 442 */     return (int)FastMath.abs(this.numberOfRows * (value - (int)value));
/*     */   }
/*     */   
/*     */   private int getCoordY(double value) {
/* 446 */     if (value < 0.0D)
/* 447 */       return (int)FastMath.abs(this.numberOfColumns * (value - (int)value - -1.0D)); 
/* 449 */     return (int)FastMath.abs(this.numberOfColumns * (value - (int)value));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\Exclusions\ExclusionBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */