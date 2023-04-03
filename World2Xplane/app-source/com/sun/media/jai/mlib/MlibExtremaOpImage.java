/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.ExtremaOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.ArrayList;
/*     */ import javax.media.jai.ROI;
/*     */ 
/*     */ final class MlibExtremaOpImage extends ExtremaOpImage {
/*     */   private int[] minCount;
/*     */   
/*     */   private int[] maxCount;
/*     */   
/*     */   private int[][] minLocs;
/*     */   
/*     */   private int[][] maxLocs;
/*     */   
/*     */   public MlibExtremaOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod, boolean saveLocations, int maxRuns) {
/*  62 */     super(source, roi, xStart, yStart, xPeriod, yPeriod, saveLocations, maxRuns);
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/*  69 */     int numBands = this.sampleModel.getNumBands();
/*  71 */     initializeState(source);
/*  75 */     Rectangle tileRect = source.getBounds();
/*  78 */     int offsetX = (this.xPeriod - (tileRect.x - this.xStart) % this.xPeriod) % this.xPeriod;
/*  81 */     int offsetY = (this.yPeriod - (tileRect.y - this.yStart) % this.yPeriod) % this.yPeriod;
/*  88 */     if (offsetX >= tileRect.width || offsetY >= tileRect.height)
/*     */       return; 
/*  93 */     int formatTag = MediaLibAccessor.findCompatibleTag(null, source);
/*  94 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, tileRect, formatTag);
/*  98 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 102 */     if (!this.saveLocations) {
/*     */       int[] imin;
/*     */       int[] imax;
/*     */       int i;
/*     */       double[] dmin;
/*     */       double[] dmax;
/*     */       int j;
/* 103 */       switch (srcAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 108 */           imin = new int[numBands];
/* 109 */           imax = new int[numBands];
/* 111 */           for (i = 0; i < srcML.length; i++)
/* 112 */             Image.Extrema2(imin, imax, srcML[i], offsetX, offsetY, this.xPeriod, this.yPeriod); 
/* 117 */           imin = srcAccessor.getIntParameters(0, imin);
/* 118 */           imax = srcAccessor.getIntParameters(0, imax);
/* 121 */           for (i = 0; i < numBands; i++) {
/* 122 */             this.extrema[0][i] = Math.min(imin[i], this.extrema[0][i]);
/* 124 */             this.extrema[1][i] = Math.max(imax[i], this.extrema[1][i]);
/*     */           } 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 131 */           dmin = new double[numBands];
/* 132 */           dmax = new double[numBands];
/* 133 */           for (j = 0; j < srcML.length; j++)
/* 134 */             Image.Extrema2_Fp(dmin, dmax, srcML[j], offsetX, offsetY, this.xPeriod, this.yPeriod); 
/* 139 */           dmin = srcAccessor.getDoubleParameters(0, dmin);
/* 140 */           dmax = srcAccessor.getDoubleParameters(0, dmax);
/* 143 */           for (j = 0; j < numBands; j++) {
/* 144 */             this.extrema[0][j] = Math.min(dmin[j], this.extrema[0][j]);
/* 146 */             this.extrema[1][j] = Math.max(dmax[j], this.extrema[1][j]);
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } else {
/*     */       int imin[], imax[], i;
/*     */       double[] dmin, dmax;
/*     */       int j;
/* 152 */       Rectangle loc = source.getBounds();
/* 153 */       int xOffset = loc.x;
/* 154 */       int yOffset = loc.y;
/* 156 */       switch (srcAccessor.getDataType()) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 161 */           imin = new int[numBands];
/* 162 */           imax = new int[numBands];
/* 164 */           for (i = 0; i < numBands; i++) {
/* 165 */             imin[i] = (int)this.extrema[0][i];
/* 166 */             imax[i] = (int)this.extrema[1][i];
/*     */           } 
/* 169 */           for (i = 0; i < srcML.length; i++)
/* 170 */             Image.ExtremaLocations(imin, imax, srcML[i], offsetX, offsetY, this.xPeriod, this.yPeriod, this.saveLocations, this.maxRuns, this.minCount, this.maxCount, this.minLocs, this.maxLocs); 
/* 178 */           imin = srcAccessor.getIntParameters(0, imin);
/* 179 */           imax = srcAccessor.getIntParameters(0, imax);
/* 180 */           this.minCount = srcAccessor.getIntParameters(0, this.minCount);
/* 181 */           this.maxCount = srcAccessor.getIntParameters(0, this.maxCount);
/* 182 */           this.minLocs = srcAccessor.getIntArrayParameters(0, this.minLocs);
/* 183 */           this.maxLocs = srcAccessor.getIntArrayParameters(0, this.maxLocs);
/* 186 */           for (i = 0; i < numBands; i++) {
/* 187 */             ArrayList minList = this.minLocations[i];
/* 188 */             ArrayList maxList = this.maxLocations[i];
/* 189 */             if (imin[i] < this.extrema[0][i]) {
/* 190 */               minList.clear();
/* 191 */               this.extrema[0][i] = imin[i];
/*     */             } 
/* 194 */             int[] minBuf = this.minLocs[i];
/* 195 */             int[] maxBuf = this.maxLocs[i];
/*     */             int m, k;
/* 197 */             for (m = 0, k = 0; m < this.minCount[i]; m++) {
/* 198 */               minList.add(new int[] { minBuf[k++] + xOffset, minBuf[k++] + yOffset, minBuf[k++] });
/*     */             } 
/* 200 */             if (imax[i] > this.extrema[1][i]) {
/* 201 */               maxList.clear();
/* 202 */               this.extrema[1][i] = imax[i];
/*     */             } 
/* 205 */             for (m = 0, k = 0; m < this.maxCount[i]; m++) {
/* 206 */               maxList.add(new int[] { maxBuf[k++] + xOffset, maxBuf[k++] + yOffset, maxBuf[k++] });
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 4:
/*     */         case 5:
/* 212 */           dmin = new double[numBands];
/* 213 */           dmax = new double[numBands];
/* 215 */           for (j = 0; j < numBands; j++) {
/* 216 */             dmin[j] = this.extrema[0][j];
/* 217 */             dmax[j] = this.extrema[1][j];
/*     */           } 
/* 220 */           for (j = 0; j < srcML.length; j++)
/* 221 */             Image.ExtremaLocations_Fp(dmin, dmax, srcML[j], offsetX, offsetY, this.xPeriod, this.yPeriod, this.saveLocations, this.maxRuns, this.minCount, this.maxCount, this.minLocs, this.maxLocs); 
/* 229 */           dmin = srcAccessor.getDoubleParameters(0, dmin);
/* 230 */           dmax = srcAccessor.getDoubleParameters(0, dmax);
/* 231 */           this.minCount = srcAccessor.getIntParameters(0, this.minCount);
/* 232 */           this.maxCount = srcAccessor.getIntParameters(0, this.maxCount);
/* 233 */           this.minLocs = srcAccessor.getIntArrayParameters(0, this.minLocs);
/* 234 */           this.maxLocs = srcAccessor.getIntArrayParameters(0, this.maxLocs);
/* 237 */           for (j = 0; j < numBands; j++) {
/* 238 */             ArrayList minList = this.minLocations[j];
/* 239 */             ArrayList maxList = this.maxLocations[j];
/* 240 */             if (dmin[j] < this.extrema[0][j]) {
/* 241 */               minList.clear();
/* 242 */               this.extrema[0][j] = dmin[j];
/*     */             } 
/* 245 */             int[] minBuf = this.minLocs[j];
/* 246 */             int[] maxBuf = this.maxLocs[j];
/*     */             int m, k;
/* 248 */             for (m = 0, k = 0; m < this.minCount[j]; m++) {
/* 249 */               minList.add(new int[] { minBuf[k++] + xOffset, minBuf[k++] + yOffset, minBuf[k++] });
/*     */             } 
/* 251 */             if (dmax[j] > this.extrema[1][j]) {
/* 252 */               maxList.clear();
/* 253 */               this.extrema[1][j] = dmax[j];
/*     */             } 
/* 256 */             for (m = 0, k = 0; m < this.maxCount[j]; m++) {
/* 257 */               maxList.add(new int[] { maxBuf[k++] + xOffset, maxBuf[k++] + yOffset, maxBuf[k++] });
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/* 263 */     if (name.equalsIgnoreCase("extrema")) {
/* 264 */       double[][] ext = (double[][])stats;
/* 265 */       for (int i = 0; i < numBands; i++) {
/* 266 */         ext[0][i] = this.extrema[0][i];
/* 267 */         ext[1][i] = this.extrema[1][i];
/*     */       } 
/* 269 */     } else if (name.equalsIgnoreCase("minimum")) {
/* 270 */       double[] min = (double[])stats;
/* 271 */       for (int i = 0; i < numBands; i++)
/* 272 */         min[i] = this.extrema[0][i]; 
/* 274 */     } else if (name.equalsIgnoreCase("maximum")) {
/* 275 */       double[] max = (double[])stats;
/* 276 */       for (int i = 0; i < numBands; i++)
/* 277 */         max[i] = this.extrema[1][i]; 
/* 279 */     } else if (name.equalsIgnoreCase("minLocations")) {
/* 280 */       ArrayList[] minLoc = (ArrayList[])stats;
/* 281 */       for (int i = 0; i < numBands; i++)
/* 282 */         minLoc[i] = this.minLocations[i]; 
/* 284 */     } else if (name.equalsIgnoreCase("maxLocations")) {
/* 285 */       ArrayList[] maxLoc = (ArrayList[])stats;
/* 286 */       for (int i = 0; i < numBands; i++)
/* 287 */         maxLoc[i] = this.maxLocations[i]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void initializeState(Raster source) {
/* 292 */     if (this.extrema == null) {
/* 293 */       int numBands = this.sampleModel.getNumBands();
/* 294 */       this.minCount = new int[numBands];
/* 295 */       this.maxCount = new int[numBands];
/* 297 */       this.minLocs = new int[numBands][];
/* 298 */       this.maxLocs = new int[numBands][];
/* 300 */       int size = (getTileWidth() + 1) / 2 * getTileHeight();
/* 302 */       for (int i = 0; i < numBands; i++) {
/* 303 */         this.minLocs[i] = new int[size];
/* 304 */         this.maxLocs[i] = new int[size];
/*     */       } 
/* 307 */       super.initializeState(source);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibExtremaOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */