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
/*     */ final class PolarToComplexOpImage extends PointOpImage {
/*  56 */   private double phaseGain = 1.0D;
/*     */   
/*  59 */   private double phaseBias = 0.0D;
/*     */   
/*     */   public PolarToComplexOpImage(RenderedImage magnitude, RenderedImage phase, Map config, ImageLayout layout) {
/*  76 */     super(magnitude, phase, layout, config, true);
/*  79 */     int numBands = 2 * Math.min(magnitude.getSampleModel().getNumBands(), phase.getSampleModel().getNumBands());
/*  82 */     if (this.sampleModel.getNumBands() != numBands) {
/*  84 */       this.sampleModel = RasterFactory.createComponentSampleModel(this.sampleModel, this.sampleModel.getTransferType(), this.sampleModel.getWidth(), this.sampleModel.getHeight(), numBands);
/*  91 */       if (this.colorModel != null && !JDKWorkarounds.areCompatibleDataModels(this.sampleModel, this.colorModel))
/*  94 */         this.colorModel = ImageUtil.getCompatibleColorModel(this.sampleModel, config); 
/*     */     } 
/* 100 */     switch (phase.getSampleModel().getTransferType()) {
/*     */       case 0:
/* 102 */         this.phaseGain = 0.024639942381096416D;
/* 103 */         this.phaseBias = -3.141592653589793D;
/*     */         break;
/*     */       case 2:
/* 106 */         this.phaseGain = 1.9175345033660654E-4D;
/* 107 */         this.phaseBias = -3.141592653589793D;
/*     */         break;
/*     */       case 1:
/* 110 */         this.phaseGain = 9.587526218325454E-5D;
/* 111 */         this.phaseBias = -3.141592653589793D;
/*     */         break;
/*     */       case 3:
/* 114 */         this.phaseGain = 2.925836159896768E-9D;
/* 115 */         this.phaseBias = -3.141592653589793D;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 136 */     RasterFormatTag[] formatTags = getFormatTags();
/* 139 */     RasterAccessor magAccessor = new RasterAccessor(sources[0], destRect, formatTags[0], getSource(0).getColorModel());
/* 142 */     RasterAccessor phsAccessor = new RasterAccessor(sources[1], destRect, formatTags[1], getSource(1).getColorModel());
/* 145 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[2], getColorModel());
/* 149 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/* 151 */         computeRectByte(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 2:
/* 155 */         computeRectShort(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 1:
/* 159 */         computeRectUShort(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 3:
/* 163 */         computeRectInt(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 4:
/* 167 */         computeRectFloat(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       case 5:
/* 171 */         computeRectDouble(magAccessor, phsAccessor, dstAccessor, destRect.height, destRect.width);
/*     */         break;
/*     */       default:
/* 176 */         throw new RuntimeException(JaiI18N.getString("PolarToComplexOpImage0"));
/*     */     } 
/* 179 */     if (dstAccessor.needsClamping())
/* 180 */       dstAccessor.clampDataArrays(); 
/* 184 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */   
/*     */   private void computeRectDouble(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 193 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 194 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 195 */     int magPixelStride = magAccessor.getPixelStride();
/* 196 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 197 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 198 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 201 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 202 */     for (int component = 0; component < numComponents; component++) {
/* 204 */       int dstBandReal = 2 * component;
/* 205 */       int dstBandImag = dstBandReal + 1;
/* 208 */       double[] dstReal = dstAccessor.getDoubleDataArray(dstBandReal);
/* 209 */       double[] dstImag = dstAccessor.getDoubleDataArray(dstBandImag);
/* 210 */       double[] magData = magAccessor.getDoubleDataArray(component);
/* 211 */       double[] phsData = phsAccessor.getDoubleDataArray(component);
/* 214 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 215 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 216 */       int magOffset = magAccessor.getBandOffset(component);
/* 217 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 220 */       int dstLineReal = dstOffsetReal;
/* 221 */       int dstLineImag = dstOffsetImag;
/* 222 */       int magLine = magOffset;
/* 223 */       int phsLine = phsOffset;
/* 225 */       for (int row = 0; row < numRows; row++) {
/* 227 */         int dstPixelReal = dstLineReal;
/* 228 */         int dstPixelImag = dstLineImag;
/* 229 */         int magPixel = magLine;
/* 230 */         int phsPixel = phsLine;
/* 232 */         for (int col = 0; col < numCols; col++) {
/* 233 */           double mag = magData[magPixel];
/* 234 */           double phs = phsData[phsPixel] * this.phaseGain + this.phaseBias;
/* 236 */           dstReal[dstPixelReal] = mag * Math.cos(phs);
/* 237 */           dstImag[dstPixelImag] = mag * Math.sin(phs);
/* 239 */           dstPixelReal += dstPixelStride;
/* 240 */           dstPixelImag += dstPixelStride;
/* 241 */           magPixel += magPixelStride;
/* 242 */           phsPixel += phsPixelStride;
/*     */         } 
/* 246 */         dstLineReal += dstScanlineStride;
/* 247 */         dstLineImag += dstScanlineStride;
/* 248 */         magLine += magScanlineStride;
/* 249 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectFloat(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 260 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 261 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 262 */     int magPixelStride = magAccessor.getPixelStride();
/* 263 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 264 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 265 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 268 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 269 */     for (int component = 0; component < numComponents; component++) {
/* 271 */       int dstBandReal = 2 * component;
/* 272 */       int dstBandImag = dstBandReal + 1;
/* 275 */       float[] dstReal = dstAccessor.getFloatDataArray(dstBandReal);
/* 276 */       float[] dstImag = dstAccessor.getFloatDataArray(dstBandImag);
/* 277 */       float[] magData = magAccessor.getFloatDataArray(component);
/* 278 */       float[] phsData = phsAccessor.getFloatDataArray(component);
/* 281 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 282 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 283 */       int magOffset = magAccessor.getBandOffset(component);
/* 284 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 287 */       int dstLineReal = dstOffsetReal;
/* 288 */       int dstLineImag = dstOffsetImag;
/* 289 */       int magLine = magOffset;
/* 290 */       int phsLine = phsOffset;
/* 292 */       for (int row = 0; row < numRows; row++) {
/* 294 */         int dstPixelReal = dstLineReal;
/* 295 */         int dstPixelImag = dstLineImag;
/* 296 */         int magPixel = magLine;
/* 297 */         int phsPixel = phsLine;
/* 299 */         for (int col = 0; col < numCols; col++) {
/* 300 */           double mag = magData[magPixel];
/* 301 */           double phs = phsData[phsPixel] * this.phaseGain + this.phaseBias;
/* 303 */           dstReal[dstPixelReal] = ImageUtil.clampFloat(mag * Math.cos(phs));
/* 304 */           dstImag[dstPixelImag] = ImageUtil.clampFloat(mag * Math.sin(phs));
/* 306 */           dstPixelReal += dstPixelStride;
/* 307 */           dstPixelImag += dstPixelStride;
/* 308 */           magPixel += magPixelStride;
/* 309 */           phsPixel += phsPixelStride;
/*     */         } 
/* 313 */         dstLineReal += dstScanlineStride;
/* 314 */         dstLineImag += dstScanlineStride;
/* 315 */         magLine += magScanlineStride;
/* 316 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectInt(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 327 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 328 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 329 */     int magPixelStride = magAccessor.getPixelStride();
/* 330 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 331 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 332 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 335 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 336 */     for (int component = 0; component < numComponents; component++) {
/* 338 */       int dstBandReal = 2 * component;
/* 339 */       int dstBandImag = dstBandReal + 1;
/* 342 */       int[] dstReal = dstAccessor.getIntDataArray(dstBandReal);
/* 343 */       int[] dstImag = dstAccessor.getIntDataArray(dstBandImag);
/* 344 */       int[] magData = magAccessor.getIntDataArray(component);
/* 345 */       int[] phsData = phsAccessor.getIntDataArray(component);
/* 348 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 349 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 350 */       int magOffset = magAccessor.getBandOffset(component);
/* 351 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 354 */       int dstLineReal = dstOffsetReal;
/* 355 */       int dstLineImag = dstOffsetImag;
/* 356 */       int magLine = magOffset;
/* 357 */       int phsLine = phsOffset;
/* 359 */       for (int row = 0; row < numRows; row++) {
/* 361 */         int dstPixelReal = dstLineReal;
/* 362 */         int dstPixelImag = dstLineImag;
/* 363 */         int magPixel = magLine;
/* 364 */         int phsPixel = phsLine;
/* 366 */         for (int col = 0; col < numCols; col++) {
/* 367 */           double mag = magData[magPixel];
/* 368 */           double phs = phsData[phsPixel] * this.phaseGain + this.phaseBias;
/* 370 */           dstReal[dstPixelReal] = ImageUtil.clampRoundInt(mag * Math.cos(phs));
/* 371 */           dstImag[dstPixelImag] = ImageUtil.clampRoundInt(mag * Math.sin(phs));
/* 373 */           dstPixelReal += dstPixelStride;
/* 374 */           dstPixelImag += dstPixelStride;
/* 375 */           magPixel += magPixelStride;
/* 376 */           phsPixel += phsPixelStride;
/*     */         } 
/* 380 */         dstLineReal += dstScanlineStride;
/* 381 */         dstLineImag += dstScanlineStride;
/* 382 */         magLine += magScanlineStride;
/* 383 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectUShort(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 394 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 395 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 396 */     int magPixelStride = magAccessor.getPixelStride();
/* 397 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 398 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 399 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 402 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 403 */     for (int component = 0; component < numComponents; component++) {
/* 405 */       int dstBandReal = 2 * component;
/* 406 */       int dstBandImag = dstBandReal + 1;
/* 409 */       short[] dstReal = dstAccessor.getShortDataArray(dstBandReal);
/* 410 */       short[] dstImag = dstAccessor.getShortDataArray(dstBandImag);
/* 411 */       short[] magData = magAccessor.getShortDataArray(component);
/* 412 */       short[] phsData = phsAccessor.getShortDataArray(component);
/* 415 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 416 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 417 */       int magOffset = magAccessor.getBandOffset(component);
/* 418 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 421 */       int dstLineReal = dstOffsetReal;
/* 422 */       int dstLineImag = dstOffsetImag;
/* 423 */       int magLine = magOffset;
/* 424 */       int phsLine = phsOffset;
/* 426 */       for (int row = 0; row < numRows; row++) {
/* 428 */         int dstPixelReal = dstLineReal;
/* 429 */         int dstPixelImag = dstLineImag;
/* 430 */         int magPixel = magLine;
/* 431 */         int phsPixel = phsLine;
/* 433 */         for (int col = 0; col < numCols; col++) {
/* 434 */           double mag = (magData[magPixel] & 0xFFFF);
/* 435 */           double phs = (phsData[phsPixel] & 0xFFFF) * this.phaseGain + this.phaseBias;
/* 438 */           dstReal[dstPixelReal] = ImageUtil.clampRoundUShort(mag * Math.cos(phs));
/* 440 */           dstImag[dstPixelImag] = ImageUtil.clampRoundUShort(mag * Math.sin(phs));
/* 443 */           dstPixelReal += dstPixelStride;
/* 444 */           dstPixelImag += dstPixelStride;
/* 445 */           magPixel += magPixelStride;
/* 446 */           phsPixel += phsPixelStride;
/*     */         } 
/* 450 */         dstLineReal += dstScanlineStride;
/* 451 */         dstLineImag += dstScanlineStride;
/* 452 */         magLine += magScanlineStride;
/* 453 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectShort(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 464 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 465 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 466 */     int magPixelStride = magAccessor.getPixelStride();
/* 467 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 468 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 469 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 472 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 473 */     for (int component = 0; component < numComponents; component++) {
/* 475 */       int dstBandReal = 2 * component;
/* 476 */       int dstBandImag = dstBandReal + 1;
/* 479 */       short[] dstReal = dstAccessor.getShortDataArray(dstBandReal);
/* 480 */       short[] dstImag = dstAccessor.getShortDataArray(dstBandImag);
/* 481 */       short[] magData = magAccessor.getShortDataArray(component);
/* 482 */       short[] phsData = phsAccessor.getShortDataArray(component);
/* 485 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 486 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 487 */       int magOffset = magAccessor.getBandOffset(component);
/* 488 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 491 */       int dstLineReal = dstOffsetReal;
/* 492 */       int dstLineImag = dstOffsetImag;
/* 493 */       int magLine = magOffset;
/* 494 */       int phsLine = phsOffset;
/* 496 */       for (int row = 0; row < numRows; row++) {
/* 498 */         int dstPixelReal = dstLineReal;
/* 499 */         int dstPixelImag = dstLineImag;
/* 500 */         int magPixel = magLine;
/* 501 */         int phsPixel = phsLine;
/* 503 */         for (int col = 0; col < numCols; col++) {
/* 504 */           double mag = magData[magPixel];
/* 505 */           double phs = phsData[phsPixel] * this.phaseGain + this.phaseBias;
/* 507 */           dstReal[dstPixelReal] = ImageUtil.clampRoundShort(mag * Math.cos(phs));
/* 508 */           dstImag[dstPixelImag] = ImageUtil.clampRoundShort(mag * Math.sin(phs));
/* 510 */           dstPixelReal += dstPixelStride;
/* 511 */           dstPixelImag += dstPixelStride;
/* 512 */           magPixel += magPixelStride;
/* 513 */           phsPixel += phsPixelStride;
/*     */         } 
/* 517 */         dstLineReal += dstScanlineStride;
/* 518 */         dstLineImag += dstScanlineStride;
/* 519 */         magLine += magScanlineStride;
/* 520 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeRectByte(RasterAccessor magAccessor, RasterAccessor phsAccessor, RasterAccessor dstAccessor, int numRows, int numCols) {
/* 531 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 532 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 533 */     int magPixelStride = magAccessor.getPixelStride();
/* 534 */     int magScanlineStride = magAccessor.getScanlineStride();
/* 535 */     int phsPixelStride = phsAccessor.getPixelStride();
/* 536 */     int phsScanlineStride = phsAccessor.getScanlineStride();
/* 539 */     int numComponents = this.sampleModel.getNumBands() / 2;
/* 540 */     for (int component = 0; component < numComponents; component++) {
/* 542 */       int dstBandReal = 2 * component;
/* 543 */       int dstBandImag = dstBandReal + 1;
/* 546 */       byte[] dstReal = dstAccessor.getByteDataArray(dstBandReal);
/* 547 */       byte[] dstImag = dstAccessor.getByteDataArray(dstBandImag);
/* 548 */       byte[] magData = magAccessor.getByteDataArray(component);
/* 549 */       byte[] phsData = phsAccessor.getByteDataArray(component);
/* 552 */       int dstOffsetReal = dstAccessor.getBandOffset(dstBandReal);
/* 553 */       int dstOffsetImag = dstAccessor.getBandOffset(dstBandImag);
/* 554 */       int magOffset = magAccessor.getBandOffset(component);
/* 555 */       int phsOffset = phsAccessor.getBandOffset(component);
/* 558 */       int dstLineReal = dstOffsetReal;
/* 559 */       int dstLineImag = dstOffsetImag;
/* 560 */       int magLine = magOffset;
/* 561 */       int phsLine = phsOffset;
/* 563 */       for (int row = 0; row < numRows; row++) {
/* 565 */         int dstPixelReal = dstLineReal;
/* 566 */         int dstPixelImag = dstLineImag;
/* 567 */         int magPixel = magLine;
/* 568 */         int phsPixel = phsLine;
/* 570 */         for (int col = 0; col < numCols; col++) {
/* 571 */           double mag = (magData[magPixel] & 0xFF);
/* 572 */           double phs = (phsData[phsPixel] & 0xFF) * this.phaseGain + this.phaseBias;
/* 575 */           dstReal[dstPixelReal] = ImageUtil.clampRoundByte(mag * Math.cos(phs));
/* 576 */           dstImag[dstPixelImag] = ImageUtil.clampRoundByte(mag * Math.sin(phs));
/* 578 */           dstPixelReal += dstPixelStride;
/* 579 */           dstPixelImag += dstPixelStride;
/* 580 */           magPixel += magPixelStride;
/* 581 */           phsPixel += phsPixelStride;
/*     */         } 
/* 585 */         dstLineReal += dstScanlineStride;
/* 586 */         dstLineImag += dstScanlineStride;
/* 587 */         magLine += magScanlineStride;
/* 588 */         phsLine += phsScanlineStride;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PolarToComplexOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */