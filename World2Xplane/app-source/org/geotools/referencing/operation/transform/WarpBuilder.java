/*     */ package org.geotools.referencing.operation.transform;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.media.jai.Warp;
/*     */ import javax.media.jai.WarpAffine;
/*     */ import javax.media.jai.WarpGrid;
/*     */ import org.geotools.referencing.operation.matrix.XAffineTransform;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class WarpBuilder {
/*  50 */   static final Logger LOGGER = Logging.getLogger(WarpBuilder.class);
/*     */   
/*  55 */   static final boolean DUMP_GRIDS = Boolean.getBoolean("org.geotools.dump.warp.grid");
/*     */   
/*     */   static final double EPS = 1.0E-6D;
/*     */   
/*     */   final double maxDistanceSquared;
/*     */   
/*  71 */   final double[] ordinates = new double[10];
/*     */   
/*  77 */   int maxPositions = -1;
/*     */   
/*     */   public WarpBuilder(double tolerance) {
/*  83 */     if (tolerance >= 0.0D) {
/*  84 */       this.maxDistanceSquared = tolerance * tolerance;
/*     */     } else {
/*  86 */       this.maxDistanceSquared = 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMaxPositions(int maxPositions) {
/*  97 */     this.maxPositions = maxPositions;
/*     */   }
/*     */   
/*     */   public Warp buildWarp(MathTransform2D mt, Rectangle domain) throws TransformException {
/*     */     int[] rowCols;
/* 109 */     if (mt instanceof AffineTransform2D)
/* 110 */       return (Warp)new WarpAffine((AffineTransform)mt); 
/* 114 */     if (this.maxDistanceSquared == 0.0D)
/* 115 */       return new WarpAdapter(null, mt); 
/* 119 */     double minx = domain.getMinX();
/* 120 */     double maxx = domain.getMaxX();
/* 121 */     double miny = domain.getMinY();
/* 122 */     double maxy = domain.getMaxY();
/* 123 */     int width = (int)(maxx - minx);
/* 124 */     int heigth = (int)(maxy - miny);
/* 125 */     if (Math.abs(width) == 0 || heigth == 0)
/* 126 */       throw new IllegalArgumentException("The domain is empty!"); 
/*     */     try {
/* 137 */       rowCols = computeOptimalDepths(mt, minx, maxx, miny, maxy, 0, 0);
/* 138 */     } catch (ExcessiveDepthException e) {
/* 139 */       return new WarpAdapter(null, mt);
/*     */     } 
/* 142 */     if (rowCols[0] == 0 && rowCols[1] == 0) {
/* 149 */       this.ordinates[0] = minx;
/* 150 */       this.ordinates[1] = miny;
/* 151 */       this.ordinates[2] = minx;
/* 152 */       this.ordinates[3] = maxy;
/* 153 */       this.ordinates[4] = maxx;
/* 154 */       this.ordinates[5] = miny;
/* 155 */       mt.transform(this.ordinates, 0, this.ordinates, 0, 3);
/* 159 */       double m00 = (this.ordinates[4] - this.ordinates[0]) / width;
/* 160 */       double m10 = (this.ordinates[5] - this.ordinates[1]) / width;
/* 161 */       double m01 = (this.ordinates[2] - this.ordinates[0]) / heigth;
/* 162 */       double m11 = (this.ordinates[3] - this.ordinates[1]) / heigth;
/* 163 */       double m02 = this.ordinates[0];
/* 164 */       double m12 = this.ordinates[1];
/* 166 */       AffineTransform at = new AffineTransform(m00, m10, m01, m11, m02, m12);
/* 167 */       at.translate(-minx, -miny);
/* 168 */       XAffineTransform.round(at, 1.0E-6D);
/* 169 */       LOGGER.log(Level.FINE, "Optimizing the warp into an affine transformation: {0}", at);
/* 170 */       return (Warp)new WarpAffine(at);
/*     */     } 
/* 173 */     int stepx = (int)(width / Math.pow(2.0D, rowCols[1]));
/* 174 */     int stepy = (int)(heigth / Math.pow(2.0D, rowCols[0]));
/* 177 */     int cols = width / stepx;
/* 178 */     int rows = heigth / stepy;
/* 179 */     int cmax = (int)(minx + (cols * stepx));
/* 180 */     int rmax = (int)(miny + (rows * stepy));
/* 184 */     if (this.maxPositions > 0 && cols * rows > this.maxPositions) {
/* 185 */       LOGGER.log(Level.FINE, "Bailing out to WarpAdapter, the number of rows and col grew too much, rows: " + rows + " and cols: " + cols);
/* 187 */       return new WarpAdapter(null, mt);
/*     */     } 
/* 193 */     if (cmax < maxx)
/* 195 */       if (cmax + stepx < cols * (stepx + 1)) {
/* 196 */         cmax += stepx;
/* 197 */         cols++;
/*     */       } else {
/* 199 */         stepx++;
/* 200 */         cmax = (int)(minx + (cols * stepx));
/*     */       }  
/* 203 */     if (rmax < maxy)
/* 205 */       if (rmax + stepy < rows * (stepy + 1)) {
/* 206 */         rmax += stepy;
/* 207 */         rows++;
/*     */       } else {
/* 209 */         stepy++;
/* 210 */         rmax = (int)(miny + (rows * stepy));
/*     */       }  
/* 215 */     float[] warpPositions = new float[(rows + 1) * (cols + 1) * 2];
/* 216 */     int r = (int)miny;
/* 217 */     int idx = 0;
/* 218 */     while (r <= rmax) {
/* 219 */       int c = (int)minx;
/* 220 */       while (c <= cmax) {
/* 221 */         warpPositions[idx++] = c;
/* 222 */         warpPositions[idx++] = r;
/* 223 */         c += stepx;
/*     */       } 
/* 225 */       r += stepy;
/*     */     } 
/* 227 */     if (DUMP_GRIDS)
/* 228 */       dumpPropertyFile(warpPositions, "original"); 
/* 232 */     mt.transform(warpPositions, 0, warpPositions, 0, warpPositions.length / 2);
/* 233 */     if (DUMP_GRIDS)
/* 234 */       dumpPropertyFile(warpPositions, "transformed"); 
/* 237 */     LOGGER.log(Level.FINE, "Optimizing the warp into an grid warp {0} x {1}", new Object[] { Integer.valueOf(rows), Integer.valueOf(cols) });
/* 239 */     return (Warp)new WarpGrid((int)minx, stepx, cols, (int)miny, stepy, rows, warpPositions);
/*     */   }
/*     */   
/*     */   int[] computeOptimalDepths(MathTransform2D mt, double minx, double maxx, double miny, double maxy, int rowDepth, int colDepth) throws TransformException {
/* 255 */     if (maxx - minx < 4.0D || maxy - miny < 4.0D)
/* 256 */       throw new ExcessiveDepthException("Warp grid getting as dense as the original data"); 
/* 257 */     if (rowDepth + colDepth > 20)
/* 259 */       throw new ExcessiveDepthException("Warp grid getting too large to fit in memory, bailing out"); 
/* 263 */     double midx = (minx + maxx) / 2.0D;
/* 264 */     double midy = (miny + maxy) / 2.0D;
/* 267 */     boolean withinTolVertical = (isWithinTolerance(mt, minx, miny, minx, midy, minx, maxy) && isWithinTolerance(mt, maxx, miny, maxx, midy, maxx, maxy));
/* 270 */     boolean withinTolHorizontal = (isWithinTolerance(mt, minx, miny, midx, miny, maxx, miny) && isWithinTolerance(mt, minx, maxy, midx, maxy, maxx, maxy));
/* 273 */     if (withinTolVertical && withinTolHorizontal && (
/* 274 */       !isWithinTolerance(mt, minx, miny, midx, midy, maxx, maxy) || !isWithinTolerance(mt, minx, maxy, midx, midy, maxx, miny))) {
/* 276 */       withinTolVertical = false;
/* 277 */       withinTolHorizontal = false;
/*     */     } 
/* 283 */     if (!withinTolHorizontal && !withinTolVertical) {
/* 285 */       rowDepth++;
/* 286 */       colDepth++;
/* 287 */       int[] d1 = computeOptimalDepths(mt, minx, midx, miny, midy, rowDepth, colDepth);
/* 288 */       int[] d2 = computeOptimalDepths(mt, minx, midx, midy, maxy, rowDepth, colDepth);
/* 289 */       int[] d3 = computeOptimalDepths(mt, midx, maxx, miny, midy, rowDepth, colDepth);
/* 290 */       int[] d4 = computeOptimalDepths(mt, midx, maxx, midy, maxy, rowDepth, colDepth);
/* 291 */       return new int[] { Math.max(Math.max(d1[0], d2[0]), Math.max(d3[0], d4[0])), Math.max(Math.max(d1[1], d2[1]), Math.max(d3[1], d4[1])) };
/*     */     } 
/* 293 */     if (!withinTolHorizontal) {
/* 295 */       colDepth++;
/* 296 */       int[] d1 = computeOptimalDepths(mt, minx, midx, miny, maxy, rowDepth, colDepth);
/* 297 */       int[] d2 = computeOptimalDepths(mt, midx, maxx, miny, maxy, rowDepth, colDepth);
/* 298 */       return new int[] { Math.max(d1[0], d2[0]), Math.max(d1[1], d2[1]) };
/*     */     } 
/* 299 */     if (!withinTolVertical) {
/* 301 */       rowDepth++;
/* 302 */       int[] d1 = computeOptimalDepths(mt, minx, maxx, miny, midy, rowDepth, colDepth);
/* 303 */       int[] d2 = computeOptimalDepths(mt, minx, maxx, midy, maxy, rowDepth, colDepth);
/* 304 */       return new int[] { Math.max(d1[0], d2[0]), Math.max(d1[1], d2[1]) };
/*     */     } 
/* 307 */     return new int[] { rowDepth, colDepth };
/*     */   }
/*     */   
/*     */   boolean isWithinTolerance(MathTransform2D mt, double x1, double y1, double x2, double y2, double x3, double y3) throws TransformException {
/*     */     int j;
/* 325 */     this.ordinates[0] = x1;
/* 326 */     this.ordinates[1] = y1;
/* 327 */     this.ordinates[2] = (x1 + x2) / 2.0D;
/* 328 */     this.ordinates[3] = (y1 + y2) / 2.0D;
/* 329 */     this.ordinates[4] = x2;
/* 330 */     this.ordinates[5] = y2;
/* 331 */     this.ordinates[6] = (x2 + x3) / 2.0D;
/* 332 */     this.ordinates[7] = (y2 + y3) / 2.0D;
/* 333 */     this.ordinates[8] = x3;
/* 334 */     this.ordinates[9] = y3;
/* 335 */     mt.transform(this.ordinates, 0, this.ordinates, 0, 5);
/* 337 */     boolean withinTolerance = true;
/* 338 */     for (int i = 1; i < 4 && withinTolerance; i++) {
/* 340 */       double tx1 = this.ordinates[0];
/* 341 */       double ty1 = this.ordinates[1];
/* 342 */       double tx2 = this.ordinates[i * 2];
/* 343 */       double ty2 = this.ordinates[i * 2 + 1];
/* 344 */       double tx3 = this.ordinates[8];
/* 345 */       double ty3 = this.ordinates[9];
/* 348 */       double dx = 0.0D;
/* 349 */       if (Math.abs(x3 - x1) > 1.0E-6D) {
/*     */         double xmid;
/* 351 */         if (i == 1) {
/* 352 */           xmid = (x1 + x2) / 2.0D;
/* 353 */         } else if (i == 2) {
/* 354 */           xmid = x2;
/*     */         } else {
/* 356 */           xmid = (x2 + x3) / 2.0D;
/*     */         } 
/* 359 */         dx = tx2 - (tx3 - tx1) / (x3 - x1) * (xmid - x1) - tx1;
/*     */       } 
/* 361 */       double dy = 0.0D;
/* 362 */       if (Math.abs(y3 - y1) > 1.0E-6D) {
/*     */         double ymid;
/* 364 */         if (i == 1) {
/* 365 */           ymid = (y1 + y2) / 2.0D;
/* 366 */         } else if (i == 2) {
/* 367 */           ymid = y2;
/*     */         } else {
/* 369 */           ymid = (y2 + y3) / 2.0D;
/*     */         } 
/* 371 */         dy = ty2 - (ty3 - ty1) / (y3 - y1) * (ymid - y1) - ty1;
/*     */       } 
/* 375 */       double distance = dx * dx + dy * dy;
/* 376 */       j = withinTolerance & ((distance < this.maxDistanceSquared) ? 1 : 0);
/*     */     } 
/* 379 */     return j;
/*     */   }
/*     */   
/*     */   class ExcessiveDepthException extends RuntimeException {
/*     */     private static final long serialVersionUID = -3533898904532522502L;
/*     */     
/*     */     public ExcessiveDepthException() {}
/*     */     
/*     */     public ExcessiveDepthException(String message, Throwable cause) {
/* 394 */       super(message, cause);
/*     */     }
/*     */     
/*     */     public ExcessiveDepthException(String message) {
/* 398 */       super(message);
/*     */     }
/*     */     
/*     */     public ExcessiveDepthException(Throwable cause) {
/* 402 */       super(cause);
/*     */     }
/*     */   }
/*     */   
/*     */   void dumpPropertyFile(float[] points, String name) {
/* 413 */     long start = System.currentTimeMillis();
/* 415 */     BufferedWriter writer = null;
/*     */     try {
/* 417 */       File output = File.createTempFile(start + name, ".properties");
/* 418 */       writer = new BufferedWriter(new FileWriter(output));
/* 419 */       writer.write("_=geom:Point:srid=32632");
/* 419 */       writer.newLine();
/* 420 */       for (int i = 0; i < points.length; i += 2) {
/* 421 */         writer.write("p." + (i / 2) + "=POINT(" + points[i] + " " + points[i + 1] + ")");
/* 422 */         writer.newLine();
/*     */       } 
/* 424 */       LOGGER.info(name + " dumped as " + output.getName());
/* 425 */     } catch (IOException e) {
/* 426 */       LOGGER.log(Level.SEVERE, "Failed to dump points: " + e.getMessage(), e);
/*     */     } finally {
/* 428 */       if (writer != null)
/*     */         try {
/* 430 */           writer.close();
/* 431 */         } catch (IOException e) {} 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\transform\WarpBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */