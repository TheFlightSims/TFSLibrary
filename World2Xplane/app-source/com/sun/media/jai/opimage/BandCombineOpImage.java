/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ 
/*     */ final class BandCombineOpImage extends PointOpImage {
/*     */   private double[][] matrix;
/*     */   
/*     */   public BandCombineOpImage(RenderedImage source, Map config, ImageLayout layout, double[][] matrix) {
/*  67 */     super(source, layout, config, true);
/*  69 */     this.matrix = matrix;
/*  71 */     int numBands = matrix.length;
/*  72 */     if (getSampleModel().getNumBands() != numBands) {
/*  73 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, this.sampleModel.getDataType(), this.tileWidth, this.tileHeight, numBands);
/*  77 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  80 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*  98 */     RasterFormatTag[] formatTags = getFormatTags();
/* 100 */     RasterAccessor s = new RasterAccessor(sources[0], destRect, formatTags[0], getSourceImage(0).getColorModel());
/* 103 */     RasterAccessor d = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 106 */     switch (d.getDataType()) {
/*     */       case 0:
/* 108 */         computeRectByte(s, d);
/*     */         break;
/*     */       case 1:
/* 111 */         computeRectUShort(s, d);
/*     */         break;
/*     */       case 2:
/* 114 */         computeRectShort(s, d);
/*     */         break;
/*     */       case 3:
/* 117 */         computeRectInt(s, d);
/*     */         break;
/*     */       case 4:
/* 120 */         computeRectFloat(s, d);
/*     */         break;
/*     */       case 5:
/* 123 */         computeRectDouble(s, d);
/*     */         break;
/*     */     } 
/* 127 */     if (d.isDataCopy()) {
/* 128 */       d.clampDataArrays();
/* 129 */       d.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor src, RasterAccessor dst) {
/* 134 */     int sLineStride = src.getScanlineStride();
/* 135 */     int sPixelStride = src.getPixelStride();
/* 136 */     int sbands = src.getNumBands();
/* 137 */     int[] sBandOffsets = src.getBandOffsets();
/* 138 */     byte[][] sData = src.getByteDataArrays();
/* 140 */     int dwidth = dst.getWidth();
/* 141 */     int dheight = dst.getHeight();
/* 142 */     int dbands = dst.getNumBands();
/* 143 */     int dLineStride = dst.getScanlineStride();
/* 144 */     int dPixelStride = dst.getPixelStride();
/* 145 */     int[] dBandOffsets = dst.getBandOffsets();
/* 146 */     byte[][] dData = dst.getByteDataArrays();
/* 148 */     int sso = 0, dso = 0;
/* 150 */     for (int h = 0; h < dheight; h++) {
/* 151 */       int spo = sso;
/* 152 */       int dpo = dso;
/* 154 */       for (int w = 0; w < dwidth; w++) {
/* 155 */         for (int b = 0; b < dbands; b++) {
/* 156 */           float sum = 0.0F;
/* 157 */           double[] mat = this.matrix[b];
/* 159 */           for (int k = 0; k < sbands; k++)
/* 160 */             sum += (float)mat[k] * (sData[k][spo + sBandOffsets[k]] & 0xFF); 
/* 164 */           dData[b][dpo + dBandOffsets[b]] = ImageUtil.clampRoundByte(sum + (float)mat[sbands]);
/*     */         } 
/* 167 */         spo += sPixelStride;
/* 168 */         dpo += dPixelStride;
/*     */       } 
/* 171 */       sso += sLineStride;
/* 172 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor src, RasterAccessor dst) {
/* 177 */     int sLineStride = src.getScanlineStride();
/* 178 */     int sPixelStride = src.getPixelStride();
/* 179 */     int sbands = src.getNumBands();
/* 180 */     int[] sBandOffsets = src.getBandOffsets();
/* 181 */     short[][] sData = src.getShortDataArrays();
/* 183 */     int dwidth = dst.getWidth();
/* 184 */     int dheight = dst.getHeight();
/* 185 */     int dbands = dst.getNumBands();
/* 186 */     int dLineStride = dst.getScanlineStride();
/* 187 */     int dPixelStride = dst.getPixelStride();
/* 188 */     int[] dBandOffsets = dst.getBandOffsets();
/* 189 */     short[][] dData = dst.getShortDataArrays();
/* 191 */     int sso = 0, dso = 0;
/* 193 */     for (int h = 0; h < dheight; h++) {
/* 194 */       int spo = sso;
/* 195 */       int dpo = dso;
/* 197 */       for (int w = 0; w < dwidth; w++) {
/* 198 */         for (int b = 0; b < dbands; b++) {
/* 199 */           float sum = 0.0F;
/* 200 */           double[] mat = this.matrix[b];
/* 202 */           for (int k = 0; k < sbands; k++)
/* 203 */             sum += (float)mat[k] * (sData[k][spo + sBandOffsets[k]] & 0xFFFF); 
/* 207 */           dData[b][dpo + dBandOffsets[b]] = ImageUtil.clampRoundUShort(sum + (float)this.matrix[b][sbands]);
/*     */         } 
/* 210 */         spo += sPixelStride;
/* 211 */         dpo += dPixelStride;
/*     */       } 
/* 214 */       sso += sLineStride;
/* 215 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor src, RasterAccessor dst) {
/* 220 */     int sLineStride = src.getScanlineStride();
/* 221 */     int sPixelStride = src.getPixelStride();
/* 222 */     int sbands = src.getNumBands();
/* 223 */     int[] sBandOffsets = src.getBandOffsets();
/* 224 */     short[][] sData = src.getShortDataArrays();
/* 226 */     int dwidth = dst.getWidth();
/* 227 */     int dheight = dst.getHeight();
/* 228 */     int dbands = dst.getNumBands();
/* 229 */     int dLineStride = dst.getScanlineStride();
/* 230 */     int dPixelStride = dst.getPixelStride();
/* 231 */     int[] dBandOffsets = dst.getBandOffsets();
/* 232 */     short[][] dData = dst.getShortDataArrays();
/* 234 */     int sso = 0, dso = 0;
/* 236 */     for (int h = 0; h < dheight; h++) {
/* 237 */       int spo = sso;
/* 238 */       int dpo = dso;
/* 240 */       for (int w = 0; w < dwidth; w++) {
/* 241 */         for (int b = 0; b < dbands; b++) {
/* 242 */           float sum = 0.0F;
/* 243 */           double[] mat = this.matrix[b];
/* 245 */           for (int k = 0; k < sbands; k++)
/* 246 */             sum += (float)mat[k] * sData[k][spo + sBandOffsets[k]]; 
/* 250 */           dData[b][dpo + dBandOffsets[b]] = ImageUtil.clampRoundUShort(sum + (float)this.matrix[b][sbands]);
/*     */         } 
/* 253 */         spo += sPixelStride;
/* 254 */         dpo += dPixelStride;
/*     */       } 
/* 257 */       sso += sLineStride;
/* 258 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor src, RasterAccessor dst) {
/* 264 */     int sLineStride = src.getScanlineStride();
/* 265 */     int sPixelStride = src.getPixelStride();
/* 266 */     int sbands = src.getNumBands();
/* 267 */     int[] sBandOffsets = src.getBandOffsets();
/* 268 */     int[][] sData = src.getIntDataArrays();
/* 270 */     int dwidth = dst.getWidth();
/* 271 */     int dheight = dst.getHeight();
/* 272 */     int dbands = dst.getNumBands();
/* 273 */     int dLineStride = dst.getScanlineStride();
/* 274 */     int dPixelStride = dst.getPixelStride();
/* 275 */     int[] dBandOffsets = dst.getBandOffsets();
/* 276 */     int[][] dData = dst.getIntDataArrays();
/* 278 */     int sso = 0, dso = 0;
/* 280 */     for (int h = 0; h < dheight; h++) {
/* 281 */       int spo = sso;
/* 282 */       int dpo = dso;
/* 284 */       for (int w = 0; w < dwidth; w++) {
/* 285 */         for (int b = 0; b < dbands; b++) {
/* 286 */           float sum = 0.0F;
/* 287 */           double[] mat = this.matrix[b];
/* 289 */           for (int k = 0; k < sbands; k++)
/* 290 */             sum += (float)mat[k] * sData[k][spo + sBandOffsets[k]]; 
/* 294 */           dData[b][dpo + dBandOffsets[b]] = ImageUtil.clampRoundInt(sum + (float)this.matrix[b][sbands]);
/*     */         } 
/* 297 */         spo += sPixelStride;
/* 298 */         dpo += dPixelStride;
/*     */       } 
/* 301 */       sso += sLineStride;
/* 302 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor src, RasterAccessor dst) {
/* 307 */     int sLineStride = src.getScanlineStride();
/* 308 */     int sPixelStride = src.getPixelStride();
/* 309 */     int sbands = src.getNumBands();
/* 310 */     int[] sBandOffsets = src.getBandOffsets();
/* 311 */     float[][] sData = src.getFloatDataArrays();
/* 313 */     int dwidth = dst.getWidth();
/* 314 */     int dheight = dst.getHeight();
/* 315 */     int dbands = dst.getNumBands();
/* 316 */     int dLineStride = dst.getScanlineStride();
/* 317 */     int dPixelStride = dst.getPixelStride();
/* 318 */     int[] dBandOffsets = dst.getBandOffsets();
/* 319 */     float[][] dData = dst.getFloatDataArrays();
/* 321 */     int sso = 0, dso = 0;
/* 323 */     for (int h = 0; h < dheight; h++) {
/* 324 */       int spo = sso;
/* 325 */       int dpo = dso;
/* 327 */       for (int w = 0; w < dwidth; w++) {
/* 328 */         for (int b = 0; b < dbands; b++) {
/* 329 */           float sum = 0.0F;
/* 330 */           double[] mat = this.matrix[b];
/* 332 */           for (int k = 0; k < sbands; k++)
/* 333 */             sum += (float)mat[k] * sData[k][spo + sBandOffsets[k]]; 
/* 336 */           dData[b][dpo + dBandOffsets[b]] = sum + (float)this.matrix[b][sbands];
/*     */         } 
/* 339 */         spo += sPixelStride;
/* 340 */         dpo += dPixelStride;
/*     */       } 
/* 343 */       sso += sLineStride;
/* 344 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor src, RasterAccessor dst) {
/* 349 */     int sLineStride = src.getScanlineStride();
/* 350 */     int sPixelStride = src.getPixelStride();
/* 351 */     int sbands = src.getNumBands();
/* 352 */     int[] sBandOffsets = src.getBandOffsets();
/* 353 */     double[][] sData = src.getDoubleDataArrays();
/* 355 */     int dwidth = dst.getWidth();
/* 356 */     int dheight = dst.getHeight();
/* 357 */     int dbands = dst.getNumBands();
/* 358 */     int dLineStride = dst.getScanlineStride();
/* 359 */     int dPixelStride = dst.getPixelStride();
/* 360 */     int[] dBandOffsets = dst.getBandOffsets();
/* 361 */     double[][] dData = dst.getDoubleDataArrays();
/* 363 */     int sso = 0, dso = 0;
/* 365 */     for (int h = 0; h < dheight; h++) {
/* 366 */       int spo = sso;
/* 367 */       int dpo = dso;
/* 369 */       for (int w = 0; w < dwidth; w++) {
/* 370 */         for (int b = 0; b < dbands; b++) {
/* 371 */           double sum = 0.0D;
/* 372 */           double[] mat = this.matrix[b];
/* 374 */           for (int k = 0; k < sbands; k++)
/* 375 */             sum += mat[k] * sData[k][spo + sBandOffsets[k]]; 
/* 378 */           dData[b][dpo + dBandOffsets[b]] = sum + this.matrix[b][sbands];
/*     */         } 
/* 381 */         spo += sPixelStride;
/* 382 */         dpo += dPixelStride;
/*     */       } 
/* 385 */       sso += sLineStride;
/* 386 */       dso += dLineStride;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BandCombineOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */