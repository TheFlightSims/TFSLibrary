/*     */ package com.world2xplane.XPlane.Exclusions;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.world2xplane.Geom.GeomUtils;
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
/*     */ public class PolyExclusionBuilder {
/*     */   private int numberOfColumns;
/*     */   
/*     */   private int numberOfRows;
/*     */   
/*     */   private double tx;
/*     */   
/*     */   private double ty;
/*     */   
/*  52 */   private double divx = 0.0D;
/*     */   
/*  53 */   private double divy = 0.0D;
/*     */   
/*  54 */   private List<Envelope> boxes = new ArrayList<>();
/*     */   
/*     */   private File image;
/*     */   
/*     */   public List<Envelope> getExclusionZones(String path, Geometry regionPoly, double tx, double ty) {
/*  60 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  63 */     double distanceX = GeomUtils.distanceInMeters(new LineSegment2D(tx, ty, tx + 1.0D, ty));
/*  64 */     double distanceY = GeomUtils.distanceInMeters(new LineSegment2D(tx, ty, tx, ty + 1.0D));
/*  66 */     this.tx = tx;
/*  67 */     this.ty = ty;
/*  69 */     this.divx = 1.0D / distanceX / 500.0D;
/*  70 */     this.divy = 1.0D / distanceY / 500.0D;
/*  73 */     this.numberOfRows = (int)(1.0D / this.divx);
/*  74 */     this.numberOfColumns = (int)(1.0D / this.divy);
/*  76 */     byte[][] data = new byte[this.numberOfColumns + 1][this.numberOfRows + 1];
/*  78 */     for (int y = 0; y < this.numberOfColumns; y++) {
/*  79 */       for (int k = 0; k < this.numberOfRows; k++)
/*  80 */         data[y][k] = 0; 
/*     */     } 
/*  85 */     boolean[][] grid = new boolean[this.numberOfColumns + 1][this.numberOfRows + 1];
/*  86 */     for (int i = 0; i < this.numberOfColumns; i++) {
/*  87 */       for (int k = 0; k < this.numberOfRows; k++) {
/*  90 */         double a1 = getX((k + 1));
/*  91 */         double a2 = getX((k + 2));
/*  92 */         double a3 = getY(i);
/*  93 */         double a4 = getY((i + 1));
/*  95 */         if (a1 < tx)
/*  96 */           a1 = tx; 
/*  98 */         if (a2 > tx + 1.0D)
/*  99 */           a2 = tx + 1.0D; 
/* 101 */         if (a3 < ty)
/* 102 */           a3 = ty; 
/* 104 */         if (a4 > ty + 1.0D)
/* 105 */           a4 = ty + 1.0D; 
/* 108 */         Envelope exclusion = new Envelope(a1, a2, a3, a4);
/* 110 */         Geometry box = geometryFactory.toGeometry(exclusion);
/* 111 */         if (regionPoly.intersects(box))
/* 112 */           grid[i][k] = true; 
/*     */       } 
/*     */     } 
/* 118 */     int dWidth = 2048;
/* 119 */     int dHeight = 2048;
/* 120 */     BufferedImage bImage = new BufferedImage(dWidth, dHeight, 1);
/* 122 */     Graphics2D g2 = bImage.createGraphics();
/* 124 */     g2.setPaint(new Color(130, 139, 150));
/* 125 */     g2.fillRect(0, 0, dWidth, dHeight);
/* 126 */     g2.setPaint(new Color(111, 222, 73));
/* 128 */     double dx = dWidth / this.numberOfRows;
/* 129 */     double dy = dHeight / this.numberOfColumns;
/* 132 */     List<Box2D> exclusions = new ArrayList<>();
/* 133 */     int x1 = 0;
/* 134 */     int y1 = 0;
/* 135 */     int x2 = 0;
/* 136 */     int y2 = 0;
/* 139 */     boolean inside = false;
/* 142 */     int x = 0, j = 0;
/* 143 */     int box_n = 0, box_s = 0, box_w = 0, box_e = 0;
/* 144 */     boolean wrongline = false;
/* 146 */     for (int row = 0; row < this.numberOfRows; row++) {
/* 147 */       for (int col = 0; col < this.numberOfColumns; col++) {
/* 149 */         if (!inside && grid[col][row]) {
/* 151 */           box_n = row;
/* 152 */           box_w = col;
/* 153 */           inside = true;
/*     */         } 
/* 155 */         if (inside && (!grid[col][row] || col == this.numberOfColumns - 1)) {
/* 157 */           if (col == this.numberOfColumns - 1) {
/* 158 */             box_e = col;
/*     */           } else {
/* 160 */             box_e = col - 1;
/*     */           } 
/* 163 */           if (row < this.numberOfRows - 1) {
/* 164 */             wrongline = false;
/* 166 */             for (j = box_n + 1; j < this.numberOfRows; j++) {
/* 167 */               for (x = box_w; x <= box_e; x++) {
/* 168 */                 if (!grid[x][j]) {
/* 170 */                   wrongline = true;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 174 */               if (wrongline)
/*     */                 break; 
/* 179 */               for (x = box_w; x <= box_e; ) {
/* 179 */                 grid[x][j] = false;
/* 179 */                 x++;
/*     */               } 
/*     */             } 
/* 183 */             box_s = j - 1;
/*     */           } else {
/* 186 */             box_s = row;
/*     */           } 
/* 189 */           double xcord = 0.0D, ycord = 0.0D, x2cord = 0.0D, y2cord = 0.0D;
/* 190 */           exclusions.add(new Box2D(box_n, box_s, box_w, box_e));
/* 191 */           inside = false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 240 */     for (Box2D item : exclusions) {
/* 242 */       x1 = (int)(item.getMinX() * dx);
/* 243 */       x2 = (int)((item.getMaxX() + 1.0D) * dx);
/* 244 */       y1 = (int)(item.getMinY() * dy);
/* 245 */       y2 = (int)((item.getMaxY() + 1.0D) * dy);
/* 246 */       g2.setPaint(new Color(111, 222, 73));
/* 247 */       g2.fillRect(x1, dHeight - y1 - y2 - y1, x2 - x1, y2 - y1);
/* 249 */       double a1 = getX(item.getMinX() + 1.0D);
/* 250 */       double a2 = getX(item.getMaxX() + 2.0D);
/* 251 */       double a3 = getY(item.getMinY());
/* 252 */       double a4 = getY(item.getMaxY() + 1.0D);
/* 254 */       if (a1 < tx)
/* 255 */         a1 = tx; 
/* 257 */       if (a2 > tx + 1.0D)
/* 258 */         a2 = tx + 1.0D; 
/* 260 */       if (a3 < ty)
/* 261 */         a3 = ty; 
/* 263 */       if (a4 > ty + 1.0D)
/* 264 */         a4 = ty + 1.0D; 
/* 267 */       Envelope exclusion = new Envelope(a1, a2, a3, a4);
/* 268 */       this.boxes.add(exclusion);
/*     */     } 
/*     */     try {
/* 276 */       File parent = new File(new File(path), "exclusion_" + ty + "_" + tx + ".png");
/* 277 */       if (parent != null && !parent.exists() && 
/* 278 */         !parent.mkdirs())
/* 279 */         throw new IOException("File '" + parent + "' could not be created"); 
/* 283 */       if (ImageIO.write(bImage, "jpg", parent));
/* 287 */       this.image = parent;
/* 289 */     } catch (Exception e) {
/* 291 */       e.printStackTrace();
/*     */     } 
/* 294 */     return this.boxes;
/*     */   }
/*     */   
/*     */   private double getX(double minX) {
/* 299 */     double v = this.tx + minX * this.divx;
/* 300 */     return v;
/*     */   }
/*     */   
/*     */   private double getY(double minY) {
/* 304 */     double v = this.ty + minY * this.divy;
/* 305 */     return v;
/*     */   }
/*     */   
/*     */   private boolean lineEqual(int x1, int x2, int y1, int y2, int i, boolean[][] grid) {
/* 310 */     if (i > this.numberOfColumns)
/* 311 */       return false; 
/* 314 */     if (x1 > x2) {
/* 315 */       int swap = x1;
/* 316 */       x1 = x2;
/* 317 */       x2 = swap;
/*     */     } 
/*     */     int x;
/* 320 */     for (x = x1; x <= x2; x++) {
/* 321 */       if (!grid[i][x])
/* 322 */         return false; 
/*     */     } 
/* 327 */     for (x = x1; x <= x2; x++)
/* 328 */       grid[i][x] = false; 
/* 330 */     return true;
/*     */   }
/*     */   
/*     */   private int getCoordX(double value) {
/* 335 */     if (value < 0.0D)
/* 336 */       return (int)FastMath.abs(this.numberOfRows * (value - (int)value - -1.0D)); 
/* 338 */     return (int)FastMath.abs(this.numberOfRows * (value - (int)value));
/*     */   }
/*     */   
/*     */   private int getCoordY(double value) {
/* 342 */     if (value < 0.0D)
/* 343 */       return (int)FastMath.abs(this.numberOfColumns * (value - (int)value - -1.0D)); 
/* 345 */     return (int)FastMath.abs(this.numberOfColumns * (value - (int)value));
/*     */   }
/*     */   
/*     */   public File getImage() {
/* 349 */     return this.image;
/*     */   }
/*     */   
/*     */   public void setImage(File image) {
/* 353 */     this.image = image;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\Exclusions\PolyExclusionBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */