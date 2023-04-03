/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public final class BorderExtenderZero extends BorderExtender {
/*     */   public final void extend(WritableRaster raster, PlanarImage im) {
/*     */     int row, iData[];
/*     */     float[] fData;
/*     */     double[] dData;
/*  71 */     if (raster == null || im == null)
/*  72 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  75 */     int width = raster.getWidth();
/*  76 */     int height = raster.getHeight();
/*  77 */     int numBands = raster.getNumBands();
/*  79 */     int minX = raster.getMinX();
/*  80 */     int maxX = minX + width;
/*  81 */     int minY = raster.getMinY();
/*  82 */     int maxY = minY + height;
/*  84 */     int validMinX = Math.max(im.getMinX(), minX);
/*  85 */     int validMaxX = Math.min(im.getMaxX(), maxX);
/*  86 */     int validMinY = Math.max(im.getMinY(), minY);
/*  87 */     int validMaxY = Math.min(im.getMaxY(), maxY);
/*  90 */     switch (raster.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  95 */         iData = new int[width * numBands];
/*  96 */         if (validMinX > validMaxX || validMinY > validMaxY) {
/*  98 */           for (int i = minY; i < maxY; i++)
/*  99 */             raster.setPixels(minX, i, width, 1, iData); 
/*     */           break;
/*     */         } 
/* 102 */         for (row = minY; row < validMinY; row++)
/* 103 */           raster.setPixels(minX, row, width, 1, iData); 
/* 105 */         for (row = validMinY; row < validMaxY; row++) {
/* 106 */           if (minX < validMinX)
/* 107 */             raster.setPixels(minX, row, validMinX - minX, 1, iData); 
/* 110 */           if (validMaxX < maxX)
/* 111 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, iData); 
/*     */         } 
/* 115 */         for (row = validMaxY; row < maxY; row++)
/* 116 */           raster.setPixels(minX, row, width, 1, iData); 
/*     */         break;
/*     */       case 4:
/* 122 */         fData = new float[width * numBands];
/* 123 */         if (validMinX > validMaxX || validMinY > validMaxY) {
/* 125 */           for (row = minY; row < maxY; row++)
/* 126 */             raster.setPixels(minX, row, width, 1, fData); 
/*     */           break;
/*     */         } 
/* 129 */         for (row = minY; row < validMinY; row++)
/* 130 */           raster.setPixels(minX, row, width, 1, fData); 
/* 132 */         for (row = validMinY; row < validMaxY; row++) {
/* 133 */           if (minX < validMinX)
/* 134 */             raster.setPixels(minX, row, validMinX - minX, 1, fData); 
/* 137 */           if (validMaxX < maxX)
/* 138 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, fData); 
/*     */         } 
/* 142 */         for (row = validMaxY; row < maxY; row++)
/* 143 */           raster.setPixels(minX, row, width, 1, fData); 
/*     */         break;
/*     */       case 5:
/* 149 */         dData = new double[width * numBands];
/* 150 */         if (validMinX > validMaxX || validMinY > validMaxY) {
/* 152 */           for (row = minY; row < maxY; row++)
/* 153 */             raster.setPixels(minX, row, width, 1, dData); 
/*     */           break;
/*     */         } 
/* 156 */         for (row = minY; row < validMinY; row++)
/* 157 */           raster.setPixels(minX, row, width, 1, dData); 
/* 159 */         for (row = validMinY; row < validMaxY; row++) {
/* 160 */           if (minX < validMinX)
/* 161 */             raster.setPixels(minX, row, validMinX - minX, 1, dData); 
/* 164 */           if (validMaxX < maxX)
/* 165 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, dData); 
/*     */         } 
/* 169 */         for (row = validMaxY; row < maxY; row++)
/* 170 */           raster.setPixels(minX, row, width, 1, dData); 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtenderZero.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */