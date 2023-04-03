/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public final class BorderExtenderCopy extends BorderExtender {
/*     */   public final void extend(WritableRaster raster, PlanarImage im) {
/*     */     int[] iData;
/*     */     float[] fData;
/*     */     double[] dData;
/*  78 */     if (raster == null || im == null)
/*  79 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  82 */     int width = raster.getWidth();
/*  83 */     int height = raster.getHeight();
/*  84 */     int numBands = raster.getNumBands();
/*  86 */     int minX = raster.getMinX();
/*  87 */     int maxX = minX + width;
/*  88 */     int minY = raster.getMinY();
/*  89 */     int maxY = minY + height;
/*  91 */     int validMinX = Math.max(im.getMinX(), minX);
/*  92 */     int validMaxX = Math.min(im.getMaxX(), maxX);
/*  93 */     int validMinY = Math.max(im.getMinY(), minY);
/*  94 */     int validMaxY = Math.min(im.getMaxY(), maxY);
/*  96 */     if (validMinX > validMaxX || validMinY > validMaxY) {
/* 100 */       if (validMinX > validMaxX)
/* 101 */         if (minX == validMinX) {
/* 102 */           minX = im.getMaxX() - 1;
/*     */         } else {
/* 104 */           maxX = im.getMinX();
/*     */         }  
/* 107 */       if (validMinY > validMaxY)
/* 108 */         if (minY == validMinY) {
/* 109 */           minY = im.getMaxY() - 1;
/*     */         } else {
/* 111 */           maxY = im.getMinY();
/*     */         }  
/* 116 */       WritableRaster wr = raster.createCompatibleWritableRaster(minX, minY, maxX - minX, maxY - minY);
/* 122 */       extend(wr, im);
/* 125 */       Raster child = wr.createChild(raster.getMinX(), raster.getMinY(), raster.getWidth(), raster.getHeight(), raster.getMinX(), raster.getMinY(), null);
/* 131 */       JDKWorkarounds.setRect(raster, child, 0, 0);
/*     */       return;
/*     */     } 
/* 136 */     Rectangle rect = new Rectangle();
/* 137 */     int size = Math.max(width, height);
/* 140 */     switch (raster.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 145 */         iData = new int[size * numBands];
/* 147 */         if (minX < validMinX) {
/* 148 */           rect.x = validMinX;
/* 149 */           rect.y = validMinY;
/* 150 */           rect.width = 1;
/* 151 */           rect.height = validMaxY - validMinY;
/* 153 */           if (rect.height > 0) {
/* 154 */             Raster leftEdge = im.getData(rect);
/* 155 */             leftEdge.getPixels(validMinX, validMinY, 1, rect.height, iData);
/* 158 */             for (int col = minX; col < validMinX; col++)
/* 159 */               raster.setPixels(col, validMinY, 1, rect.height, iData); 
/*     */           } 
/*     */         } 
/* 165 */         if (validMaxX < maxX) {
/* 166 */           rect.x = validMaxX - 1;
/* 167 */           rect.y = validMinY;
/* 168 */           rect.width = 1;
/* 169 */           rect.height = validMaxY - validMinY;
/* 171 */           if (rect.height > 0) {
/* 172 */             Raster rightEdge = im.getData(rect);
/* 173 */             rightEdge.getPixels(validMaxX - 1, validMinY, 1, rect.height, iData);
/* 176 */             for (int col = validMaxX; col < maxX; col++)
/* 177 */               raster.setPixels(col, validMinY, 1, rect.height, iData); 
/*     */           } 
/*     */         } 
/* 183 */         if (minY < validMinY) {
/* 184 */           rect.x = minX;
/* 185 */           rect.y = validMinY;
/* 186 */           rect.width = width;
/* 187 */           rect.height = 1;
/* 189 */           Raster topRow = im.getExtendedData(rect, this);
/* 190 */           topRow.getPixels(minX, validMinY, width, 1, iData);
/* 191 */           for (int row = minY; row < validMinY; row++)
/* 192 */             raster.setPixels(minX, row, width, 1, iData); 
/*     */         } 
/* 196 */         if (validMaxY < maxY) {
/* 197 */           rect.x = minX;
/* 198 */           rect.y = validMaxY - 1;
/* 199 */           rect.width = width;
/* 200 */           rect.height = 1;
/* 202 */           Raster bottomRow = im.getExtendedData(rect, this);
/* 203 */           bottomRow.getPixels(minX, validMaxY - 1, width, 1, iData);
/* 205 */           for (int row = validMaxY; row < maxY; row++)
/* 206 */             raster.setPixels(minX, row, width, 1, iData); 
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 212 */         fData = new float[size * numBands];
/* 214 */         if (minX < validMinX) {
/* 215 */           rect.x = validMinX;
/* 216 */           rect.y = validMinY;
/* 217 */           rect.width = 1;
/* 218 */           rect.height = validMaxY - validMinY;
/* 220 */           if (rect.height > 0) {
/* 221 */             Raster leftEdge = im.getData(rect);
/* 222 */             leftEdge.getPixels(validMinX, validMinY, 1, rect.height, fData);
/* 225 */             for (int col = minX; col < validMinX; col++)
/* 226 */               raster.setPixels(col, validMinY, 1, rect.height, fData); 
/*     */           } 
/*     */         } 
/* 232 */         if (validMaxX < maxX) {
/* 233 */           rect.x = validMaxX - 1;
/* 234 */           rect.y = validMinY;
/* 235 */           rect.width = 1;
/* 236 */           rect.height = validMaxY - validMinY;
/* 238 */           if (rect.height > 0) {
/* 239 */             Raster rightEdge = im.getData(rect);
/* 240 */             rightEdge.getPixels(validMaxX - 1, validMinY, 1, rect.height, fData);
/* 243 */             for (int col = validMaxX; col < maxX; col++)
/* 244 */               raster.setPixels(col, validMinY, 1, rect.height, fData); 
/*     */           } 
/*     */         } 
/* 250 */         if (minY < validMinY) {
/* 251 */           rect.x = minX;
/* 252 */           rect.y = validMinY;
/* 253 */           rect.width = width;
/* 254 */           rect.height = 1;
/* 256 */           Raster topRow = im.getExtendedData(rect, this);
/* 257 */           topRow.getPixels(minX, validMinY, width, 1, fData);
/* 258 */           for (int row = minY; row < validMinY; row++)
/* 259 */             raster.setPixels(minX, row, width, 1, fData); 
/*     */         } 
/* 263 */         if (validMaxY < maxY) {
/* 264 */           rect.x = minX;
/* 265 */           rect.y = validMaxY - 1;
/* 266 */           rect.width = width;
/* 267 */           rect.height = 1;
/* 269 */           Raster bottomRow = im.getExtendedData(rect, this);
/* 270 */           bottomRow.getPixels(minX, validMaxY - 1, width, 1, fData);
/* 272 */           for (int row = validMaxY; row < maxY; row++)
/* 273 */             raster.setPixels(minX, row, width, 1, fData); 
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 279 */         dData = new double[size * numBands];
/* 281 */         if (minX < validMinX) {
/* 282 */           rect.x = validMinX;
/* 283 */           rect.y = validMinY;
/* 284 */           rect.width = 1;
/* 285 */           rect.height = validMaxY - validMinY;
/* 287 */           if (rect.height > 0) {
/* 288 */             Raster leftEdge = im.getData(rect);
/* 289 */             leftEdge.getPixels(validMinX, validMinY, 1, rect.height, dData);
/* 292 */             for (int col = minX; col < validMinX; col++)
/* 293 */               raster.setPixels(col, validMinY, 1, rect.height, dData); 
/*     */           } 
/*     */         } 
/* 299 */         if (validMaxX < maxX) {
/* 300 */           rect.x = validMaxX - 1;
/* 301 */           rect.y = validMinY;
/* 302 */           rect.width = 1;
/* 303 */           rect.height = validMaxY - validMinY;
/* 305 */           if (rect.height > 0) {
/* 306 */             Raster rightEdge = im.getData(rect);
/* 307 */             rightEdge.getPixels(validMaxX - 1, validMinY, 1, rect.height, dData);
/* 310 */             for (int col = validMaxX; col < maxX; col++)
/* 311 */               raster.setPixels(col, validMinY, 1, rect.height, dData); 
/*     */           } 
/*     */         } 
/* 317 */         if (minY < validMinY) {
/* 318 */           rect.x = minX;
/* 319 */           rect.y = validMinY;
/* 320 */           rect.width = width;
/* 321 */           rect.height = 1;
/* 323 */           Raster topRow = im.getExtendedData(rect, this);
/* 324 */           topRow.getPixels(minX, validMinY, width, 1, dData);
/* 325 */           for (int row = minY; row < validMinY; row++)
/* 326 */             raster.setPixels(minX, row, width, 1, dData); 
/*     */         } 
/* 330 */         if (validMaxY < maxY) {
/* 331 */           rect.x = minX;
/* 332 */           rect.y = validMaxY - 1;
/* 333 */           rect.width = width;
/* 334 */           rect.height = 1;
/* 336 */           Raster bottomRow = im.getExtendedData(rect, this);
/* 337 */           bottomRow.getPixels(minX, validMaxY - 1, width, 1, dData);
/* 339 */           for (int row = validMaxY; row < maxY; row++)
/* 340 */             raster.setPixels(minX, row, width, 1, dData); 
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtenderCopy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */