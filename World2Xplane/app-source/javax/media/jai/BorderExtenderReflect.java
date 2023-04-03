/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public final class BorderExtenderReflect extends BorderExtender {
/*     */   private void flipX(WritableRaster raster) {
/*     */     int iData0[], iData1[], i;
/*     */     float[] fData0, fData1;
/*     */     int j;
/*     */     double[] dData0, dData1;
/*  49 */     int k, minX = raster.getMinX();
/*  50 */     int minY = raster.getMinY();
/*  51 */     int height = raster.getHeight();
/*  52 */     int width = raster.getWidth();
/*  53 */     int maxX = minX + width - 1;
/*  54 */     int numBands = raster.getNumBands();
/*  56 */     switch (raster.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  61 */         iData0 = new int[height * numBands];
/*  62 */         iData1 = new int[height * numBands];
/*  64 */         for (i = 0; i < width / 2; i++) {
/*  65 */           raster.getPixels(minX + i, minY, 1, height, iData0);
/*  66 */           raster.getPixels(maxX - i, minY, 1, height, iData1);
/*  68 */           raster.setPixels(minX + i, minY, 1, height, iData1);
/*  69 */           raster.setPixels(maxX - i, minY, 1, height, iData0);
/*     */         } 
/*     */         break;
/*     */       case 4:
/*  74 */         fData0 = new float[height * numBands];
/*  75 */         fData1 = new float[height * numBands];
/*  77 */         for (j = 0; j < width / 2; j++) {
/*  78 */           raster.getPixels(minX + j, minY, 1, height, fData0);
/*  79 */           raster.getPixels(maxX - j, minY, 1, height, fData1);
/*  81 */           raster.setPixels(minX + j, minY, 1, height, fData1);
/*  82 */           raster.setPixels(maxX - j, minY, 1, height, fData0);
/*     */         } 
/*     */         break;
/*     */       case 5:
/*  87 */         dData0 = new double[height * numBands];
/*  88 */         dData1 = new double[height * numBands];
/*  90 */         for (k = 0; k < width / 2; k++) {
/*  91 */           raster.getPixels(minX + k, minY, 1, height, dData0);
/*  92 */           raster.getPixels(maxX - k, minY, 1, height, dData1);
/*  94 */           raster.setPixels(minX + k, minY, 1, height, dData1);
/*  95 */           raster.setPixels(maxX - k, minY, 1, height, dData0);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void flipY(WritableRaster raster) {
/*     */     int iData0[], iData1[], i;
/*     */     float[] fData0, fData1;
/*     */     int j;
/*     */     double[] dData0, dData1;
/* 102 */     int k, minX = raster.getMinX();
/* 103 */     int minY = raster.getMinY();
/* 104 */     int height = raster.getHeight();
/* 105 */     int width = raster.getWidth();
/* 106 */     int maxY = minY + height - 1;
/* 107 */     int numBands = raster.getNumBands();
/* 109 */     switch (raster.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 114 */         iData0 = new int[width * numBands];
/* 115 */         iData1 = new int[width * numBands];
/* 117 */         for (i = 0; i < height / 2; i++) {
/* 118 */           raster.getPixels(minX, minY + i, width, 1, iData0);
/* 119 */           raster.getPixels(minX, maxY - i, width, 1, iData1);
/* 121 */           raster.setPixels(minX, minY + i, width, 1, iData1);
/* 122 */           raster.setPixels(minX, maxY - i, width, 1, iData0);
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 127 */         fData0 = new float[width * numBands];
/* 128 */         fData1 = new float[width * numBands];
/* 130 */         for (j = 0; j < height / 2; j++) {
/* 131 */           raster.getPixels(minX, minY + j, width, 1, fData0);
/* 132 */           raster.getPixels(minX, maxY - j, width, 1, fData1);
/* 134 */           raster.setPixels(minX, minY + j, width, 1, fData1);
/* 135 */           raster.setPixels(minX, maxY - j, width, 1, fData0);
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 140 */         dData0 = new double[width * numBands];
/* 141 */         dData1 = new double[width * numBands];
/* 143 */         for (k = 0; k < height / 2; k++) {
/* 144 */           raster.getPixels(minX, minY + k, width, 1, dData0);
/* 145 */           raster.getPixels(minX, maxY - k, width, 1, dData1);
/* 147 */           raster.setPixels(minX, minY + k, width, 1, dData1);
/* 148 */           raster.setPixels(minX, maxY - k, width, 1, dData0);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void extend(WritableRaster raster, PlanarImage im) {
/* 175 */     if (raster == null || im == null)
/* 176 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 179 */     int width = raster.getWidth();
/* 180 */     int height = raster.getHeight();
/* 182 */     int minX = raster.getMinX();
/* 183 */     int maxX = minX + width;
/* 184 */     int minY = raster.getMinY();
/* 185 */     int maxY = minY + height;
/* 187 */     int imMinX = im.getMinX();
/* 188 */     int imMinY = im.getMinY();
/* 189 */     int imWidth = im.getWidth();
/* 190 */     int imHeight = im.getHeight();
/* 192 */     int validMinX = Math.max(imMinX, minX);
/* 193 */     int validMaxX = Math.min(imMinX + imWidth, maxX);
/* 194 */     int validMinY = Math.max(imMinY, minY);
/* 195 */     int validMaxY = Math.min(imMinY + imHeight, maxY);
/* 197 */     if (validMinX > validMaxX || validMinY > validMaxY) {
/* 201 */       if (validMinX > validMaxX)
/* 202 */         if (minX == validMinX) {
/* 203 */           minX = im.getMaxX() - 1;
/*     */         } else {
/* 205 */           maxX = im.getMinX();
/*     */         }  
/* 208 */       if (validMinY > validMaxY)
/* 209 */         if (minY == validMinY) {
/* 210 */           minY = im.getMaxY() - 1;
/*     */         } else {
/* 212 */           maxY = im.getMinY();
/*     */         }  
/* 217 */       WritableRaster wr = raster.createCompatibleWritableRaster(minX, minY, maxX - minX, maxY - minY);
/* 223 */       extend(wr, im);
/* 226 */       Raster child = wr.createChild(raster.getMinX(), raster.getMinY(), raster.getWidth(), raster.getHeight(), raster.getMinX(), raster.getMinY(), null);
/* 232 */       JDKWorkarounds.setRect(raster, child, 0, 0);
/*     */       return;
/*     */     } 
/* 237 */     Rectangle rect = new Rectangle();
/* 245 */     int minTileX = PlanarImage.XToTileX(minX, imMinX, imWidth);
/* 246 */     int maxTileX = PlanarImage.XToTileX(maxX - 1, imMinX, imWidth);
/* 247 */     int minTileY = PlanarImage.YToTileY(minY, imMinY, imHeight);
/* 248 */     int maxTileY = PlanarImage.YToTileY(maxY - 1, imMinY, imHeight);
/* 251 */     for (int tileY = minTileY; tileY <= maxTileY; tileY++) {
/* 252 */       int ty = tileY * imHeight + imMinY;
/* 253 */       for (int tileX = minTileX; tileX <= maxTileX; tileX++) {
/* 254 */         int tx = tileX * imWidth + imMinX;
/* 257 */         if (tileX != 0 || tileY != 0) {
/*     */           int imX, imY;
/* 261 */           boolean flipX = (Math.abs(tileX) % 2 == 1);
/* 262 */           boolean flipY = (Math.abs(tileY) % 2 == 1);
/* 266 */           rect.x = tx;
/* 267 */           rect.y = ty;
/* 268 */           rect.width = imWidth;
/* 269 */           rect.height = imHeight;
/* 271 */           int xOffset = 0;
/* 272 */           if (rect.x < minX) {
/* 273 */             xOffset = minX - rect.x;
/* 274 */             rect.x = minX;
/* 275 */             rect.width -= xOffset;
/*     */           } 
/* 277 */           int yOffset = 0;
/* 278 */           if (rect.y < minY) {
/* 279 */             yOffset = minY - rect.y;
/* 280 */             rect.y = minY;
/* 281 */             rect.height -= yOffset;
/*     */           } 
/* 283 */           if (rect.x + rect.width > maxX)
/* 284 */             rect.width = maxX - rect.x; 
/* 286 */           if (rect.y + rect.height > maxY)
/* 287 */             rect.height = maxY - rect.y; 
/* 291 */           if (flipX) {
/* 292 */             if (xOffset == 0) {
/* 293 */               imX = imMinX + imWidth - rect.width;
/*     */             } else {
/* 295 */               imX = imMinX;
/*     */             } 
/*     */           } else {
/* 298 */             imX = imMinX + xOffset;
/*     */           } 
/* 302 */           if (flipY) {
/* 303 */             if (yOffset == 0) {
/* 304 */               imY = imMinY + imHeight - rect.height;
/*     */             } else {
/* 306 */               imY = imMinY;
/*     */             } 
/*     */           } else {
/* 309 */             imY = imMinY + yOffset;
/*     */           } 
/* 314 */           WritableRaster child = RasterFactory.createWritableChild(raster, rect.x, rect.y, rect.width, rect.height, imX, imY, null);
/* 323 */           im.copyData(child);
/* 325 */           if (flipX)
/* 326 */             flipX(child); 
/* 329 */           if (flipY)
/* 330 */             flipY(child); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtenderReflect.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */