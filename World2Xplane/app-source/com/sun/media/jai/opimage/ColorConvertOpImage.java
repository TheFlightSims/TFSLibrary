/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColorSpaceJAI;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ final class ColorConvertOpImage extends PointOpImage {
/*  51 */   private static final ColorSpace rgbColorSpace = ColorSpace.getInstance(1000);
/*     */   
/*  54 */   private static SoftReference softRef = null;
/*     */   
/*  57 */   private ImageParameters srcParam = null;
/*     */   
/*  60 */   private ImageParameters dstParam = null;
/*     */   
/*  63 */   private ImageParameters tempParam = null;
/*     */   
/*  66 */   private ColorConvertOp colorConvertOp = null;
/*     */   
/*     */   private int caseNumber;
/*     */   
/*     */   private static synchronized ColorConvertOp getColorConvertOp(ColorSpace src, ColorSpace dst) {
/*  83 */     HashMap colorConvertOpBuf = null;
/*  85 */     if (softRef == null || (colorConvertOpBuf = softRef.get()) == null) {
/*  88 */       colorConvertOpBuf = new HashMap();
/*  89 */       softRef = new SoftReference(colorConvertOpBuf);
/*     */     } 
/*  92 */     ArrayList hashcode = new ArrayList(2);
/*  93 */     hashcode.add(0, src);
/*  94 */     hashcode.add(1, dst);
/*  95 */     ColorConvertOp op = (ColorConvertOp)colorConvertOpBuf.get(hashcode);
/*  97 */     if (op == null) {
/*  98 */       op = new ColorConvertOp(src, dst, null);
/*  99 */       colorConvertOpBuf.put(hashcode, op);
/*     */     } 
/* 102 */     return op;
/*     */   }
/*     */   
/*     */   private static float getMinValue(int dataType) {
/* 112 */     float minValue = 0.0F;
/* 113 */     switch (dataType) {
/*     */       case 0:
/* 115 */         minValue = 0.0F;
/* 130 */         return minValue;
/*     */       case 2:
/*     */         minValue = -32768.0F;
/* 130 */         return minValue;
/*     */       case 1:
/*     */         minValue = 0.0F;
/* 130 */         return minValue;
/*     */       case 3:
/*     */         minValue = -2.1474836E9F;
/* 130 */         return minValue;
/*     */     } 
/*     */     minValue = 0.0F;
/* 130 */     return minValue;
/*     */   }
/*     */   
/*     */   private static float getRange(int dataType) {
/* 140 */     float range = 1.0F;
/* 141 */     switch (dataType) {
/*     */       case 0:
/* 143 */         range = 255.0F;
/* 158 */         return range;
/*     */       case 2:
/*     */         range = 65535.0F;
/* 158 */         return range;
/*     */       case 1:
/*     */         range = 65535.0F;
/* 158 */         return range;
/*     */       case 3:
/*     */         range = 4.2949673E9F;
/* 158 */         return range;
/*     */     } 
/*     */     range = 1.0F;
/* 158 */     return range;
/*     */   }
/*     */   
/*     */   public ColorConvertOpImage(RenderedImage source, Map config, ImageLayout layout, ColorModel colorModel) {
/* 177 */     super(source, layout, config, true);
/* 178 */     this.colorModel = colorModel;
/* 181 */     this.srcParam = new ImageParameters(this, source.getColorModel(), source.getSampleModel());
/* 183 */     this.dstParam = new ImageParameters(this, colorModel, this.sampleModel);
/* 185 */     ColorSpace srcColorSpace = this.srcParam.getColorModel().getColorSpace();
/* 186 */     ColorSpace dstColorSpace = this.dstParam.getColorModel().getColorSpace();
/* 190 */     if (srcColorSpace instanceof ColorSpaceJAI && dstColorSpace instanceof ColorSpaceJAI) {
/* 194 */       this.caseNumber = 1;
/* 195 */       this.tempParam = createTempParam();
/* 196 */     } else if (srcColorSpace instanceof ColorSpaceJAI) {
/* 200 */       if (dstColorSpace != rgbColorSpace) {
/* 201 */         this.caseNumber = 2;
/* 202 */         this.tempParam = createTempParam();
/* 203 */         this.colorConvertOp = getColorConvertOp(rgbColorSpace, dstColorSpace);
/*     */       } else {
/* 206 */         this.caseNumber = 3;
/*     */       } 
/* 207 */     } else if (dstColorSpace instanceof ColorSpaceJAI) {
/* 211 */       if (srcColorSpace != rgbColorSpace) {
/* 212 */         this.caseNumber = 4;
/* 213 */         this.tempParam = createTempParam();
/* 214 */         this.colorConvertOp = getColorConvertOp(srcColorSpace, rgbColorSpace);
/*     */       } else {
/* 216 */         this.caseNumber = 5;
/*     */       } 
/*     */     } else {
/* 220 */       this.caseNumber = 6;
/* 221 */       this.colorConvertOp = getColorConvertOp(srcColorSpace, dstColorSpace);
/*     */     } 
/* 225 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 239 */     WritableRaster tempRas = null;
/* 242 */     Raster source = sources[0];
/* 245 */     if (!destRect.equals(source.getBounds()))
/* 246 */       source = source.createChild(destRect.x, destRect.y, destRect.width, destRect.height, destRect.x, destRect.y, null); 
/* 252 */     switch (this.caseNumber) {
/*     */       case 1:
/* 256 */         tempRas = computeRectColorSpaceJAIToRGB(source, this.srcParam, (WritableRaster)null, this.tempParam);
/* 258 */         computeRectColorSpaceJAIFromRGB(tempRas, this.tempParam, dest, this.dstParam);
/*     */         break;
/*     */       case 2:
/* 266 */         tempRas = computeRectColorSpaceJAIToRGB(source, this.srcParam, (WritableRaster)null, this.tempParam);
/* 268 */         computeRectNonColorSpaceJAI(tempRas, this.tempParam, dest, this.dstParam, destRect);
/*     */         break;
/*     */       case 3:
/* 272 */         computeRectColorSpaceJAIToRGB(source, this.srcParam, dest, this.dstParam);
/*     */         break;
/*     */       case 4:
/* 278 */         tempRas = createTempWritableRaster(source);
/* 279 */         computeRectNonColorSpaceJAI(source, this.srcParam, tempRas, this.tempParam, destRect);
/* 281 */         computeRectColorSpaceJAIFromRGB(tempRas, this.tempParam, dest, this.dstParam);
/*     */         break;
/*     */       case 5:
/* 285 */         computeRectColorSpaceJAIFromRGB(source, this.srcParam, dest, this.dstParam);
/*     */         break;
/*     */       case 6:
/* 290 */         computeRectNonColorSpaceJAI(source, this.srcParam, dest, this.dstParam, destRect);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private WritableRaster computeRectColorSpaceJAIToRGB(Raster src, ImageParameters srcParam, WritableRaster dest, ImageParameters dstParam) {
/* 306 */     src = convertRasterToUnsigned(src);
/* 308 */     ColorSpaceJAI colorSpaceJAI = (ColorSpaceJAI)srcParam.getColorModel().getColorSpace();
/* 310 */     dest = colorSpaceJAI.toRGB(src, srcParam.getComponentSize(), dest, dstParam.getComponentSize());
/* 313 */     dest = convertRasterToSigned(dest);
/* 314 */     return dest;
/*     */   }
/*     */   
/*     */   private WritableRaster computeRectColorSpaceJAIFromRGB(Raster src, ImageParameters srcParam, WritableRaster dest, ImageParameters dstParam) {
/* 326 */     src = convertRasterToUnsigned(src);
/* 327 */     ColorSpaceJAI colorSpaceJAI = (ColorSpaceJAI)dstParam.getColorModel().getColorSpace();
/* 329 */     dest = colorSpaceJAI.fromRGB(src, srcParam.getComponentSize(), dest, dstParam.getComponentSize());
/* 332 */     dest = convertRasterToSigned(dest);
/* 333 */     return dest;
/*     */   }
/*     */   
/*     */   private void computeRectNonColorSpaceJAI(Raster src, ImageParameters srcParam, WritableRaster dest, ImageParameters dstParam, Rectangle destRect) {
/* 344 */     if (!srcParam.isFloat() && !dstParam.isFloat()) {
/* 350 */       Raster s = src;
/* 351 */       if (s.getMinX() != destRect.x || s.getMinY() != destRect.y || s.getWidth() != destRect.width || s.getHeight() != destRect.height)
/* 355 */         s = s.createChild(destRect.x, destRect.y, destRect.width, destRect.height, destRect.x, destRect.y, null); 
/* 359 */       WritableRaster d = dest;
/* 360 */       if (d.getMinX() != destRect.x || d.getMinY() != destRect.y || d.getWidth() != destRect.width || d.getHeight() != destRect.height)
/* 364 */         d = d.createWritableChild(destRect.x, destRect.y, destRect.width, destRect.height, destRect.x, destRect.y, (int[])null); 
/* 370 */       synchronized (this.colorConvertOp.getClass()) {
/* 373 */         this.colorConvertOp.filter(s, d);
/*     */       } 
/*     */     } else {
/* 378 */       ColorSpace srcColorSpace = srcParam.getColorModel().getColorSpace();
/* 379 */       ColorSpace dstColorSpace = dstParam.getColorModel().getColorSpace();
/* 380 */       boolean srcFloat = srcParam.isFloat();
/* 381 */       float srcMinValue = srcParam.getMinValue();
/* 382 */       float srcRange = srcParam.getRange();
/* 384 */       boolean dstFloat = dstParam.isFloat();
/* 385 */       float dstMinValue = dstParam.getMinValue();
/* 386 */       float dstRange = dstParam.getRange();
/* 388 */       int rectYMax = destRect.y + destRect.height;
/* 389 */       int rectXMax = destRect.x + destRect.width;
/* 390 */       int numComponents = srcColorSpace.getNumComponents();
/* 391 */       float[] srcPixel = new float[numComponents];
/* 394 */       for (int y = destRect.y; y < rectYMax; y++) {
/* 395 */         for (int x = destRect.x; x < rectXMax; x++) {
/* 396 */           srcPixel = src.getPixel(x, y, srcPixel);
/* 397 */           if (!srcFloat)
/* 399 */             for (int i = 0; i < numComponents; i++)
/* 400 */               srcPixel[i] = (srcPixel[i] - srcMinValue) / srcRange;  
/* 405 */           float[] xyzPixel = srcColorSpace.toCIEXYZ(srcPixel);
/* 406 */           float[] dstPixel = dstColorSpace.fromCIEXYZ(xyzPixel);
/* 408 */           if (!dstFloat)
/* 410 */             for (int i = 0; i < numComponents; i++)
/* 411 */               dstPixel[i] = dstPixel[i] * dstRange + dstMinValue;  
/* 414 */           dest.setPixel(x, y, dstPixel);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private ImageParameters createTempParam() {
/* 423 */     ColorModel cm = null;
/* 424 */     SampleModel sm = null;
/* 426 */     if (this.srcParam.getDataType() > this.dstParam.getDataType()) {
/* 427 */       cm = this.srcParam.getColorModel();
/* 428 */       sm = this.srcParam.getSampleModel();
/*     */     } else {
/* 430 */       cm = this.dstParam.getColorModel();
/* 431 */       sm = this.dstParam.getSampleModel();
/*     */     } 
/* 434 */     cm = new ComponentColorModel(rgbColorSpace, cm.getComponentSize(), cm.hasAlpha(), cm.isAlphaPremultiplied(), cm.getTransparency(), sm.getDataType());
/* 440 */     return new ImageParameters(this, cm, sm);
/*     */   }
/*     */   
/*     */   private WritableRaster createTempWritableRaster(Raster src) {
/* 446 */     Point origin = new Point(src.getMinX(), src.getMinY());
/* 447 */     return RasterFactory.createWritableRaster(src.getSampleModel(), origin);
/*     */   }
/*     */   
/*     */   private Raster convertRasterToUnsigned(Raster ras) {
/* 453 */     int type = ras.getSampleModel().getDataType();
/* 454 */     WritableRaster tempRas = null;
/* 456 */     if (type == 3 || type == 2) {
/* 458 */       int minX = ras.getMinX(), minY = ras.getMinY();
/* 459 */       int w = ras.getWidth(), h = ras.getHeight();
/* 461 */       int[] buf = ras.getPixels(minX, minY, w, h, (int[])null);
/* 462 */       convertBufferToUnsigned(buf, type);
/* 464 */       tempRas = createTempWritableRaster(ras);
/* 465 */       tempRas.setPixels(minX, minY, w, h, buf);
/* 466 */       return tempRas;
/*     */     } 
/* 468 */     return ras;
/*     */   }
/*     */   
/*     */   private WritableRaster convertRasterToSigned(WritableRaster ras) {
/* 473 */     int type = ras.getSampleModel().getDataType();
/* 474 */     WritableRaster tempRas = null;
/* 476 */     if (type == 3 || type == 2) {
/* 478 */       int minX = ras.getMinX(), minY = ras.getMinY();
/* 479 */       int w = ras.getWidth(), h = ras.getHeight();
/* 481 */       int[] buf = ras.getPixels(minX, minY, w, h, (int[])null);
/* 482 */       convertBufferToSigned(buf, type);
/* 484 */       if (ras instanceof WritableRaster) {
/* 485 */         tempRas = ras;
/*     */       } else {
/* 487 */         tempRas = createTempWritableRaster(ras);
/*     */       } 
/* 488 */       tempRas.setPixels(minX, minY, w, h, buf);
/* 489 */       return tempRas;
/*     */     } 
/* 491 */     return ras;
/*     */   }
/*     */   
/*     */   private void convertBufferToSigned(int[] buf, int type) {
/* 496 */     if (buf == null)
/*     */       return; 
/* 498 */     if (type == 2) {
/* 499 */       for (int i = 0; i < buf.length; i++)
/* 500 */         buf[i] = buf[i] + -32768; 
/* 502 */     } else if (type == 3) {
/* 503 */       for (int i = 0; i < buf.length; i++)
/* 504 */         buf[i] = (int)((buf[i] & 0xFFFFFFFFL) + -2147483648L); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void convertBufferToUnsigned(int[] buf, int type) {
/* 511 */     if (buf == null)
/*     */       return; 
/* 513 */     if (type == 2) {
/* 514 */       for (int i = 0; i < buf.length; i++)
/* 515 */         buf[i] = buf[i] - -32768; 
/* 517 */     } else if (type == 3) {
/* 518 */       for (int i = 0; i < buf.length; i++)
/* 519 */         buf[i] = (int)((buf[i] & 0xFFFFFFFFL) - -2147483648L); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private final class ImageParameters {
/*     */     private boolean isFloat;
/*     */     
/*     */     private ColorModel colorModel;
/*     */     
/*     */     private SampleModel sampleModel;
/*     */     
/*     */     private float minValue;
/*     */     
/*     */     private float range;
/*     */     
/*     */     private int[] componentSize;
/*     */     
/*     */     private int dataType;
/*     */     
/*     */     private final ColorConvertOpImage this$0;
/*     */     
/*     */     ImageParameters(ColorConvertOpImage this$0, ColorModel cm, SampleModel sm) {
/* 535 */       this.this$0 = this$0;
/* 536 */       this.colorModel = cm;
/* 537 */       this.sampleModel = sm;
/* 538 */       this.dataType = sm.getDataType();
/* 539 */       this.isFloat = (this.dataType == 4 || this.dataType == 5);
/* 541 */       this.minValue = ColorConvertOpImage.getMinValue(this.dataType);
/* 542 */       this.range = ColorConvertOpImage.getRange(this.dataType);
/* 543 */       this.componentSize = cm.getComponentSize();
/*     */     }
/*     */     
/*     */     public boolean isFloat() {
/* 547 */       return this.isFloat;
/*     */     }
/*     */     
/*     */     public ColorModel getColorModel() {
/* 551 */       return this.colorModel;
/*     */     }
/*     */     
/*     */     public SampleModel getSampleModel() {
/* 555 */       return this.sampleModel;
/*     */     }
/*     */     
/*     */     public float getMinValue() {
/* 559 */       return this.minValue;
/*     */     }
/*     */     
/*     */     public float getRange() {
/* 563 */       return this.range;
/*     */     }
/*     */     
/*     */     public int[] getComponentSize() {
/* 567 */       return this.componentSize;
/*     */     }
/*     */     
/*     */     public int getDataType() {
/* 571 */       return this.dataType;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ColorConvertOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */