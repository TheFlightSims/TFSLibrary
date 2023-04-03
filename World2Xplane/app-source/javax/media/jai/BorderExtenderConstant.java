/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public final class BorderExtenderConstant extends BorderExtender {
/*     */   private double[] constants;
/*     */   
/*     */   public BorderExtenderConstant(double[] constants) {
/*  66 */     this.constants = constants;
/*     */   }
/*     */   
/*     */   private int clamp(int band, int min, int max) {
/*     */     double c;
/*  70 */     int length = this.constants.length;
/*  72 */     if (length == 1) {
/*  73 */       c = this.constants[0];
/*  74 */     } else if (band < length) {
/*  75 */       c = this.constants[band];
/*     */     } else {
/*  77 */       throw new UnsupportedOperationException(JaiI18N.getString("BorderExtenderConstant0"));
/*     */     } 
/*  80 */     return (c > min) ? ((c > max) ? max : (int)c) : min;
/*     */   }
/*     */   
/*     */   public final double[] getConstants() {
/*  90 */     return this.constants;
/*     */   }
/*     */   
/*     */   public final void extend(WritableRaster raster, PlanarImage im) {
/* 116 */     if (raster == null || im == null)
/* 117 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 120 */     int width = raster.getWidth();
/* 121 */     int height = raster.getHeight();
/* 122 */     int numBands = raster.getNumBands();
/* 124 */     int minX = raster.getMinX();
/* 125 */     int maxX = minX + width;
/* 126 */     int minY = raster.getMinY();
/* 127 */     int maxY = minY + height;
/* 129 */     int validMinX = Math.max(im.getMinX(), minX);
/* 130 */     int validMaxX = Math.min(im.getMaxX(), maxX);
/* 131 */     int validMinY = Math.max(im.getMinY(), minY);
/* 132 */     int validMaxY = Math.min(im.getMaxY(), maxY);
/* 136 */     int dataType = raster.getSampleModel().getDataType();
/* 137 */     if (dataType == 4) {
/* 138 */       float[] fBandData = new float[numBands];
/* 139 */       for (int b = 0; b < numBands; b++)
/* 140 */         fBandData[b] = (b < this.constants.length) ? (float)this.constants[b] : 0.0F; 
/* 143 */       float[] fData = new float[width * numBands];
/* 144 */       int index = 0;
/* 145 */       for (int i = 0; i < width; i++) {
/* 146 */         for (int j = 0; j < numBands; j++)
/* 147 */           fData[index++] = fBandData[j]; 
/*     */       } 
/* 151 */       if (validMinX > validMaxX || validMinY > validMaxY) {
/* 153 */         for (int row = minY; row < maxY; row++)
/* 154 */           raster.setPixels(minX, row, width, 1, fData); 
/*     */       } else {
/*     */         int row;
/* 157 */         for (row = minY; row < validMinY; row++)
/* 158 */           raster.setPixels(minX, row, width, 1, fData); 
/* 160 */         for (row = validMinY; row < validMaxY; row++) {
/* 161 */           if (minX < validMinX)
/* 162 */             raster.setPixels(minX, row, validMinX - minX, 1, fData); 
/* 165 */           if (validMaxX < maxX)
/* 166 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, fData); 
/*     */         } 
/* 170 */         for (row = validMaxY; row < maxY; row++)
/* 171 */           raster.setPixels(minX, row, width, 1, fData); 
/*     */       } 
/* 174 */     } else if (dataType == 5) {
/* 175 */       double[] dBandData = new double[numBands];
/* 176 */       for (int b = 0; b < numBands; b++)
/* 177 */         dBandData[b] = (b < this.constants.length) ? this.constants[b] : 0.0D; 
/* 180 */       double[] dData = new double[width * numBands];
/* 181 */       int index = 0;
/* 182 */       for (int i = 0; i < width; i++) {
/* 183 */         for (int j = 0; j < numBands; j++)
/* 184 */           dData[index++] = dBandData[j]; 
/*     */       } 
/* 188 */       if (validMinX > validMaxX || validMinY > validMaxY) {
/* 190 */         for (int row = minY; row < maxY; row++)
/* 191 */           raster.setPixels(minX, row, width, 1, dData); 
/*     */       } else {
/*     */         int row;
/* 194 */         for (row = minY; row < validMinY; row++)
/* 195 */           raster.setPixels(minX, row, width, 1, dData); 
/* 197 */         for (row = validMinY; row < validMaxY; row++) {
/* 198 */           if (minX < validMinX)
/* 199 */             raster.setPixels(minX, row, validMinX - minX, 1, dData); 
/* 202 */           if (validMaxX < maxX)
/* 203 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, dData); 
/*     */         } 
/* 207 */         for (row = validMaxY; row < maxY; row++)
/* 208 */           raster.setPixels(minX, row, width, 1, dData); 
/*     */       } 
/*     */     } else {
/* 212 */       int b, iBandData[] = new int[numBands];
/* 213 */       switch (dataType) {
/*     */         case 0:
/* 215 */           for (b = 0; b < numBands; b++)
/* 216 */             iBandData[b] = clamp(b, 0, 255); 
/*     */           break;
/*     */         case 2:
/* 220 */           for (b = 0; b < numBands; b++)
/* 221 */             iBandData[b] = clamp(b, -32768, 32767); 
/*     */           break;
/*     */         case 1:
/* 225 */           for (b = 0; b < numBands; b++)
/* 226 */             iBandData[b] = clamp(b, 0, 65535); 
/*     */           break;
/*     */         case 3:
/* 230 */           for (b = 0; b < numBands; b++)
/* 231 */             iBandData[b] = clamp(b, -2147483648, 2147483647); 
/*     */           break;
/*     */         default:
/* 236 */           throw new IllegalArgumentException(JaiI18N.getString("Generic3"));
/*     */       } 
/* 239 */       int[] iData = new int[width * numBands];
/* 240 */       int index = 0;
/* 241 */       for (int i = 0; i < width; i++) {
/* 242 */         for (int j = 0; j < numBands; j++)
/* 243 */           iData[index++] = iBandData[j]; 
/*     */       } 
/* 247 */       if (validMinX > validMaxX || validMinY > validMaxY) {
/* 249 */         for (int row = minY; row < maxY; row++)
/* 250 */           raster.setPixels(minX, row, width, 1, iData); 
/*     */       } else {
/*     */         int row;
/* 253 */         for (row = minY; row < validMinY; row++)
/* 254 */           raster.setPixels(minX, row, width, 1, iData); 
/* 256 */         for (row = validMinY; row < validMaxY; row++) {
/* 257 */           if (minX < validMinX)
/* 258 */             raster.setPixels(minX, row, validMinX - minX, 1, iData); 
/* 261 */           if (validMaxX < maxX)
/* 262 */             raster.setPixels(validMaxX, row, maxX - validMaxX, 1, iData); 
/*     */         } 
/* 266 */         for (row = validMaxY; row < maxY; row++)
/* 267 */           raster.setPixels(minX, row, width, 1, iData); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\BorderExtenderConstant.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */