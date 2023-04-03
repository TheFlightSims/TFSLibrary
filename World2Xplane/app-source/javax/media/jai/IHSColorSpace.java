/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.lang.ref.SoftReference;
/*      */ 
/*      */ public final class IHSColorSpace extends ColorSpaceJAI {
/*      */   private static final double PI2 = 6.283185307179586D;
/*      */   
/*      */   private static final double PI23 = 2.0943951023931953D;
/*      */   
/*      */   private static final double PI43 = 4.1887902047863905D;
/*      */   
/*  112 */   private static final double SQRT3 = Math.sqrt(3.0D);
/*      */   
/*      */   private static final double BYTESCALE = 40.58451048843331D;
/*      */   
/*  116 */   private static SoftReference reference = new SoftReference(null);
/*      */   
/*  119 */   private static byte[] acosTable = null;
/*      */   
/*  120 */   private static double[] sqrtTable = null;
/*      */   
/*  121 */   private static double[] tanTable = null;
/*      */   
/*      */   private static SoftReference acosSoftRef;
/*      */   
/*      */   private static SoftReference sqrtSoftRef;
/*      */   
/*      */   private static SoftReference tanSoftRef;
/*      */   
/*      */   public static IHSColorSpace getInstance() {
/*  131 */     synchronized (reference) {
/*      */       IHSColorSpace cs;
/*  132 */       Object referent = reference.get();
/*  134 */       if (referent == null) {
/*  136 */         reference = new SoftReference(cs = new IHSColorSpace());
/*      */       } else {
/*  139 */         cs = (IHSColorSpace)referent;
/*      */       } 
/*  142 */       return cs;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected IHSColorSpace() {
/*  152 */     super(7, 3, true);
/*      */   }
/*      */   
/*      */   private synchronized void generateACosTable() {
/*  158 */     if (acosSoftRef == null || acosSoftRef.get() == null) {
/*  159 */       acosTable = new byte[1001];
/*  160 */       acosSoftRef = new SoftReference(acosTable);
/*  162 */       for (int i = 0; i <= 1000; i++)
/*  163 */         acosTable[i] = (byte)(int)(40.58451048843331D * Math.acos((i - 500) * 0.002D) + 0.5D); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void generateSqrtTable() {
/*  170 */     if (sqrtSoftRef == null || sqrtSoftRef.get() == null) {
/*  171 */       sqrtTable = new double[1001];
/*  172 */       sqrtSoftRef = new SoftReference(sqrtTable);
/*  174 */       for (int i = 0; i <= 1000; i++)
/*  175 */         sqrtTable[i] = Math.sqrt(i / 1000.0D); 
/*      */     } 
/*      */   }
/*      */   
/*      */   private synchronized void generateTanTable() {
/*  181 */     if (tanSoftRef == null || tanSoftRef.get() == null) {
/*  182 */       tanTable = new double[256];
/*  183 */       tanSoftRef = new SoftReference(tanTable);
/*  185 */       for (int i = 0; i < 256; i++)
/*  186 */         tanTable[i] = Math.tan(i * 6.283185307179586D / 255.0D); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public float[] fromCIEXYZ(float[] colorValue) {
/*  194 */     float[] rgb = new float[3];
/*  195 */     XYZ2RGB(colorValue, rgb);
/*  197 */     float r = rgb[0];
/*  198 */     float g = rgb[1];
/*  199 */     float b = rgb[2];
/*  201 */     float[] ihs = new float[3];
/*  203 */     ihs[0] = (r + g + b) / 3.0F;
/*  204 */     float drg = r - g;
/*  205 */     float drb = r - b;
/*  206 */     float temp = (float)Math.sqrt(drg * drg + drb * (drb - drg));
/*  211 */     if (temp != 0.0F) {
/*  212 */       temp = (float)Math.acos((drg + drb) / temp / 2.0D);
/*  213 */       if (g < b) {
/*  214 */         ihs[1] = (float)(6.283185307179586D - temp);
/*      */       } else {
/*  215 */         ihs[1] = temp;
/*      */       } 
/*      */     } else {
/*  217 */       ihs[1] = 6.2831855F;
/*      */     } 
/*  219 */     float min = (r < g) ? r : g;
/*  220 */     min = (min < b) ? min : b;
/*  224 */     if (ihs[0] == 0.0F) {
/*  225 */       ihs[2] = 0.0F;
/*      */     } else {
/*  227 */       ihs[2] = 1.0F - min / ihs[0];
/*      */     } 
/*  229 */     return ihs;
/*      */   }
/*      */   
/*      */   public float[] fromRGB(float[] rgbValue) {
/*  236 */     float r = rgbValue[0];
/*  237 */     float g = rgbValue[1];
/*  238 */     float b = rgbValue[2];
/*  240 */     r = (r < 0.0F) ? 0.0F : ((r > 1.0F) ? 1.0F : r);
/*  241 */     g = (g < 0.0F) ? 0.0F : ((g > 1.0F) ? 1.0F : g);
/*  242 */     b = (b < 0.0F) ? 0.0F : ((b > 1.0F) ? 1.0F : b);
/*  244 */     float[] ihs = new float[3];
/*  246 */     ihs[0] = (r + g + b) / 3.0F;
/*  247 */     float drg = r - g;
/*  248 */     float drb = r - b;
/*  249 */     float temp = (float)Math.sqrt(drg * drg + drb * (drb - drg));
/*  254 */     if (temp != 0.0F) {
/*  255 */       temp = (float)Math.acos((drg + drb) / temp / 2.0D);
/*  256 */       if (g < b) {
/*  257 */         ihs[1] = (float)(6.283185307179586D - temp);
/*      */       } else {
/*  258 */         ihs[1] = temp;
/*      */       } 
/*      */     } else {
/*  259 */       ihs[1] = 6.2831855F;
/*      */     } 
/*  261 */     float min = (r < g) ? r : g;
/*  262 */     min = (min < b) ? min : b;
/*  266 */     if (ihs[0] == 0.0F) {
/*  267 */       ihs[2] = 0.0F;
/*      */     } else {
/*  269 */       ihs[2] = 1.0F - min / ihs[0];
/*      */     } 
/*  271 */     return ihs;
/*      */   }
/*      */   
/*      */   public float[] toCIEXYZ(float[] colorValue) {
/*  278 */     float i = colorValue[0];
/*  279 */     float h = colorValue[1];
/*  280 */     float s = colorValue[2];
/*  282 */     i = (i < 0.0F) ? 0.0F : ((i > 1.0F) ? 1.0F : i);
/*  283 */     h = (h < 0.0F) ? 0.0F : ((h > 6.2831855F) ? 6.2831855F : h);
/*  284 */     s = (s < 0.0F) ? 0.0F : ((s > 1.0F) ? 1.0F : s);
/*  286 */     float r = 0.0F, g = 0.0F, b = 0.0F;
/*  290 */     if (s == 0.0F) {
/*  291 */       r = g = b = i;
/*  294 */     } else if (h >= 2.0943951023931953D && h < 4.1887902047863905D) {
/*  295 */       r = (1.0F - s) * i;
/*  296 */       float c1 = 3.0F * i - r;
/*  297 */       float c2 = (float)(SQRT3 * (r - i) * Math.tan(h));
/*  298 */       g = (c1 + c2) / 2.0F;
/*  299 */       b = (c1 - c2) / 2.0F;
/*  301 */     } else if (h > 4.1887902047863905D) {
/*  302 */       g = (1.0F - s) * i;
/*  303 */       float c1 = 3.0F * i - g;
/*  304 */       float c2 = (float)(SQRT3 * (g - i) * Math.tan(h - 2.0943951023931953D));
/*  305 */       b = (c1 + c2) / 2.0F;
/*  306 */       r = (c1 - c2) / 2.0F;
/*  308 */     } else if (h < 2.0943951023931953D) {
/*  309 */       b = (1.0F - s) * i;
/*  310 */       float c1 = 3.0F * i - b;
/*  311 */       float c2 = (float)(SQRT3 * (b - i) * Math.tan(h - 4.1887902047863905D));
/*  312 */       r = (c1 + c2) / 2.0F;
/*  313 */       g = (c1 - c2) / 2.0F;
/*      */     } 
/*  317 */     float[] xyz = new float[3];
/*  318 */     float[] rgb = new float[3];
/*  319 */     rgb[0] = r;
/*  320 */     rgb[1] = g;
/*  321 */     rgb[2] = b;
/*  323 */     RGB2XYZ(rgb, xyz);
/*  325 */     return xyz;
/*      */   }
/*      */   
/*      */   public float[] toRGB(float[] colorValue) {
/*  332 */     float i = colorValue[0];
/*  333 */     float h = colorValue[1];
/*  334 */     float s = colorValue[2];
/*  336 */     i = (i < 0.0F) ? 0.0F : ((i > 1.0F) ? 1.0F : i);
/*  337 */     h = (h < 0.0F) ? 0.0F : ((h > 6.2831855F) ? 6.2831855F : h);
/*  338 */     s = (s < 0.0F) ? 0.0F : ((s > 1.0F) ? 1.0F : s);
/*  340 */     float[] rgb = new float[3];
/*  343 */     if (s == 0.0F) {
/*  344 */       rgb[2] = i;
/*  344 */       rgb[1] = i;
/*  344 */       rgb[0] = i;
/*  347 */     } else if (h >= 2.0943951023931953D && h <= 4.1887902047863905D) {
/*  348 */       float r = (1.0F - s) * i;
/*  349 */       float c1 = 3.0F * i - r;
/*  350 */       float c2 = (float)(SQRT3 * (r - i) * Math.tan(h));
/*  351 */       rgb[0] = r;
/*  352 */       rgb[1] = (c1 + c2) / 2.0F;
/*  353 */       rgb[2] = (c1 - c2) / 2.0F;
/*  355 */     } else if (h > 4.1887902047863905D) {
/*  356 */       float g = (1.0F - s) * i;
/*  357 */       float c1 = 3.0F * i - g;
/*  358 */       float c2 = (float)(SQRT3 * (g - i) * Math.tan(h - 2.0943951023931953D));
/*  359 */       rgb[0] = (c1 - c2) / 2.0F;
/*  360 */       rgb[1] = g;
/*  361 */       rgb[2] = (c1 + c2) / 2.0F;
/*  363 */     } else if (h < 2.0943951023931953D) {
/*  364 */       float b = (1.0F - s) * i;
/*  365 */       float c1 = 3.0F * i - b;
/*  366 */       float c2 = (float)(SQRT3 * (b - i) * Math.tan(h - 4.1887902047863905D));
/*  367 */       rgb[0] = (c1 + c2) / 2.0F;
/*  368 */       rgb[1] = (c1 - c2) / 2.0F;
/*  369 */       rgb[2] = b;
/*      */     } 
/*  373 */     return rgb;
/*      */   }
/*      */   
/*      */   public WritableRaster fromCIEXYZ(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  384 */     WritableRaster tempRas = CIEXYZToRGB(src, srcComponentSize, null, null);
/*  386 */     return fromRGB(tempRas, tempRas.getSampleModel().getSampleSize(), dest, destComponentSize);
/*      */   }
/*      */   
/*      */   public WritableRaster fromRGB(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  399 */     checkParameters(src, srcComponentSize, dest, destComponentSize);
/*  401 */     SampleModel srcSampleModel = src.getSampleModel();
/*  405 */     if (srcComponentSize == null)
/*  406 */       srcComponentSize = srcSampleModel.getSampleSize(); 
/*  409 */     if (dest == null) {
/*  410 */       Point origin = new Point(src.getMinX(), src.getMinY());
/*  411 */       dest = RasterFactory.createWritableRaster(srcSampleModel, origin);
/*      */     } 
/*  415 */     SampleModel dstSampleModel = dest.getSampleModel();
/*  416 */     if (destComponentSize == null)
/*  417 */       destComponentSize = dstSampleModel.getSampleSize(); 
/*  419 */     PixelAccessor srcAcc = new PixelAccessor(srcSampleModel, null);
/*  420 */     UnpackedImageData srcUid = srcAcc.getPixels(src, src.getBounds(), srcSampleModel.getDataType(), false);
/*  424 */     switch (srcSampleModel.getDataType()) {
/*      */       case 0:
/*  427 */         fromRGBByte(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  431 */         fromRGBShort(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 3:
/*  434 */         fromRGBInt(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 4:
/*  437 */         fromRGBFloat(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 5:
/*  440 */         fromRGBDouble(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */     } 
/*  443 */     return dest;
/*      */   }
/*      */   
/*      */   private void fromRGBByte(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  449 */     byte[] rBuf = src.getByteData(0);
/*  450 */     byte[] gBuf = src.getByteData(1);
/*  451 */     byte[] bBuf = src.getByteData(2);
/*  453 */     int normr = 8 - srcComponentSize[0];
/*  454 */     int normg = 8 - srcComponentSize[1];
/*  455 */     int normb = 8 - srcComponentSize[2];
/*  457 */     double normi = 0.00392156862745098D, normh = 1.0D, norms = 1.0D;
/*  459 */     int bnormi = 0, bnormh = 0, bnorms = 0;
/*  461 */     int dstType = dest.getSampleModel().getDataType();
/*  462 */     boolean isByte = (dstType == 0);
/*  464 */     if (isByte) {
/*  465 */       bnormi = 8 - destComponentSize[0];
/*  466 */       bnormh = 8 - destComponentSize[1];
/*  467 */       bnorms = 8 - destComponentSize[2];
/*  468 */       generateACosTable();
/*  469 */       generateSqrtTable();
/*  471 */     } else if (dstType < 4) {
/*  472 */       normi = ((1L << destComponentSize[0]) - 1L) / 255.0D;
/*  473 */       normh = ((1L << destComponentSize[1]) - 1L) / 6.283185307179586D;
/*  474 */       norms = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  477 */     int height = dest.getHeight();
/*  478 */     int width = dest.getWidth();
/*  480 */     double[] dstPixels = null;
/*  481 */     int[] dstIntPixels = null;
/*  483 */     if (isByte) {
/*  484 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/*  486 */       dstPixels = new double[3 * height * width];
/*      */     } 
/*  488 */     int rStart = src.bandOffsets[0];
/*  489 */     int gStart = src.bandOffsets[1];
/*  490 */     int bStart = src.bandOffsets[2];
/*  491 */     int srcPixelStride = src.pixelStride;
/*  492 */     int srcLineStride = src.lineStride;
/*  494 */     int dIndex = 0;
/*  495 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  496 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  497 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  498 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  499 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  500 */         short R = (short)((rBuf[rIndex] & 0xFF) << normr);
/*  501 */         short G = (short)((gBuf[gIndex] & 0xFF) << normg);
/*  502 */         short B = (short)((bBuf[bIndex] & 0xFF) << normb);
/*  504 */         if (isByte) {
/*      */           double temp;
/*      */           int hue;
/*  505 */           float intensity = (R + G + B) / 3.0F;
/*  506 */           dstIntPixels[dIndex++] = (short)(int)(intensity + 0.5F) >> bnormi;
/*  509 */           short drg = (short)(R - G);
/*  510 */           short drb = (short)(R - B);
/*  512 */           int tint = drg * drg + drb * (drb - drg);
/*  514 */           short sum = (short)(drg + drb);
/*  516 */           if (tint != 0) {
/*  517 */             temp = sqrtTable[(int)(250.0D * sum * sum / tint + 0.5D)];
/*      */           } else {
/*  518 */             temp = -1.0D;
/*      */           } 
/*  521 */           if (sum > 0) {
/*  522 */             hue = acosTable[(int)(500.0D * temp + 0.5D) + 500];
/*      */           } else {
/*  524 */             hue = acosTable[(int)(-500.0D * temp - 0.5D) + 500];
/*      */           } 
/*  526 */           if (B >= G) {
/*  527 */             dstIntPixels[dIndex++] = 255 - hue >> bnormh;
/*      */           } else {
/*  529 */             dstIntPixels[dIndex++] = hue >> bnormh;
/*      */           } 
/*  531 */           short min = (G > B) ? B : G;
/*  532 */           min = (R > min) ? min : R;
/*  533 */           dstIntPixels[dIndex++] = 255 - (int)((255 * min) / intensity + 0.5F) >> bnorms;
/*      */         } else {
/*  537 */           float intensity = (R + G + B) / 3.0F;
/*  538 */           dstPixels[dIndex++] = normi * intensity;
/*  540 */           double drg = (R - G);
/*  541 */           double drb = (R - B);
/*  542 */           double temp = Math.sqrt(drg * drg + drb * (drb - drg));
/*  544 */           if (temp != 0.0D) {
/*  545 */             temp = Math.acos((drg + drb) / temp / 2.0D);
/*  546 */             if (B >= G)
/*  547 */               temp = 6.283185307179586D - temp; 
/*      */           } else {
/*  548 */             temp = 6.283185307179586D;
/*      */           } 
/*  550 */           dstPixels[dIndex++] = normh * temp;
/*  552 */           double min = (G > B) ? B : G;
/*  553 */           min = (R > min) ? min : R;
/*  554 */           dstPixels[dIndex++] = norms * (1.0D - min / intensity);
/*      */         } 
/*      */       } 
/*      */     } 
/*  558 */     if (isByte) {
/*  559 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/*  562 */       convertToSigned(dstPixels, dstType);
/*  563 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fromRGBShort(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  571 */     short[] rBuf = src.getShortData(0);
/*  572 */     short[] gBuf = src.getShortData(1);
/*  573 */     short[] bBuf = src.getShortData(2);
/*  576 */     int normr = 16 - srcComponentSize[0];
/*  577 */     int normg = 16 - srcComponentSize[1];
/*  578 */     int normb = 16 - srcComponentSize[2];
/*  581 */     double normi = 1.5259021896696422E-5D, normh = 1.0D, norms = 1.0D;
/*  584 */     int bnormi = 0, bnormh = 0, bnorms = 0;
/*  586 */     int dstType = dest.getSampleModel().getDataType();
/*  587 */     boolean isByte = (dstType == 0);
/*  589 */     if (isByte) {
/*  590 */       bnormi = 16 - destComponentSize[0];
/*  591 */       bnormh = 8 - destComponentSize[1];
/*  592 */       bnorms = 8 - destComponentSize[2];
/*  593 */       generateACosTable();
/*  594 */       generateSqrtTable();
/*  596 */     } else if (dstType < 4) {
/*  597 */       normi = ((1L << destComponentSize[0]) - 1L) / 65535.0D;
/*  598 */       normh = ((1L << destComponentSize[1]) - 1L) / 6.283185307179586D;
/*  599 */       norms = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  602 */     int height = dest.getHeight();
/*  603 */     int width = dest.getWidth();
/*  605 */     double[] dstPixels = null;
/*  606 */     int[] dstIntPixels = null;
/*  608 */     if (isByte) {
/*  609 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/*  611 */       dstPixels = new double[3 * height * width];
/*      */     } 
/*  613 */     int rStart = src.bandOffsets[0];
/*  614 */     int gStart = src.bandOffsets[1];
/*  615 */     int bStart = src.bandOffsets[2];
/*  616 */     int srcPixelStride = src.pixelStride;
/*  617 */     int srcLineStride = src.lineStride;
/*  619 */     int dIndex = 0;
/*  620 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  621 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  622 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  623 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  624 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  625 */         int R = (rBuf[rIndex] & 0xFFFF) << normr;
/*  626 */         int G = (gBuf[gIndex] & 0xFFFF) << normg;
/*  627 */         int B = (bBuf[bIndex] & 0xFFFF) << normb;
/*  629 */         if (isByte) {
/*      */           double temp;
/*      */           int hue;
/*  630 */           float intensity = (R + G + B) / 3.0F;
/*  631 */           dstIntPixels[dIndex++] = (int)(intensity + 0.5F) >> bnormi;
/*  634 */           int drg = R - G;
/*  635 */           int drb = R - B;
/*  637 */           double tint = drg * drg + drb * (drb - drg);
/*  639 */           double sum = (drg + drb);
/*  641 */           if (tint != 0.0D) {
/*  642 */             temp = sqrtTable[(int)(250.0D * sum * sum / tint + 0.5D)];
/*      */           } else {
/*  643 */             temp = -1.0D;
/*      */           } 
/*  646 */           if (sum > 0.0D) {
/*  647 */             hue = acosTable[(int)(500.0D * temp + 0.5D) + 500];
/*      */           } else {
/*  649 */             hue = acosTable[(int)(-500.0D * temp - 0.5D) + 500];
/*      */           } 
/*  651 */           if (B >= G) {
/*  652 */             dstIntPixels[dIndex++] = 255 - hue >> bnormh;
/*      */           } else {
/*  654 */             dstIntPixels[dIndex++] = hue >> bnormh;
/*      */           } 
/*  656 */           int min = (G > B) ? B : G;
/*  657 */           min = (R > min) ? min : R;
/*  658 */           dstIntPixels[dIndex++] = 255 - (int)((255 * min) / intensity + 0.5F) >> bnorms;
/*      */         } else {
/*  663 */           float intensity = (R + G + B) / 3.0F;
/*  664 */           dstPixels[dIndex++] = normi * intensity;
/*  666 */           double drg = (R - G);
/*  667 */           double drb = (R - B);
/*  668 */           double temp = Math.sqrt(drg * drg + drb * (drb - drg));
/*  670 */           if (temp != 0.0D) {
/*  671 */             temp = Math.acos((drg + drb) / temp / 2.0D);
/*  672 */             if (B >= G)
/*  673 */               temp = 6.283185307179586D - temp; 
/*      */           } else {
/*  674 */             temp = 6.283185307179586D;
/*      */           } 
/*  676 */           dstPixels[dIndex++] = normh * temp;
/*  678 */           double min = (G > B) ? B : G;
/*  679 */           min = (R > min) ? min : R;
/*  680 */           dstPixels[dIndex++] = norms * (1.0D - min / intensity);
/*      */         } 
/*      */       } 
/*      */     } 
/*  685 */     if (isByte) {
/*  686 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/*  689 */       convertToSigned(dstPixels, dstType);
/*  690 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fromRGBInt(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  698 */     int[] rBuf = src.getIntData(0);
/*  699 */     int[] gBuf = src.getIntData(1);
/*  700 */     int[] bBuf = src.getIntData(2);
/*  702 */     int normr = 32 - srcComponentSize[0];
/*  703 */     int normg = 32 - srcComponentSize[1];
/*  704 */     int normb = 32 - srcComponentSize[2];
/*  706 */     double range = 4.294967295E9D;
/*  707 */     double normi = 1.0D / range, normh = 1.0D, norms = 1.0D;
/*  709 */     int bnormi = 0, bnormh = 0, bnorms = 0;
/*  711 */     int dstType = dest.getSampleModel().getDataType();
/*  712 */     boolean isByte = (dstType == 0);
/*  714 */     if (isByte) {
/*  715 */       bnormi = 32 - destComponentSize[0];
/*  716 */       bnormh = 8 - destComponentSize[1];
/*  717 */       bnorms = 8 - destComponentSize[2];
/*  718 */       generateACosTable();
/*  719 */       generateSqrtTable();
/*  721 */     } else if (dstType < 4) {
/*  722 */       normi = ((1L << destComponentSize[0]) - 1L) / range;
/*  723 */       normh = ((1L << destComponentSize[1]) - 1L) / 6.283185307179586D;
/*  724 */       norms = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  727 */     int height = dest.getHeight();
/*  728 */     int width = dest.getWidth();
/*  730 */     double[] dstPixels = null;
/*  731 */     int[] dstIntPixels = null;
/*  733 */     if (isByte) {
/*  734 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/*  736 */       dstPixels = new double[3 * height * width];
/*      */     } 
/*  738 */     int rStart = src.bandOffsets[0];
/*  739 */     int gStart = src.bandOffsets[1];
/*  740 */     int bStart = src.bandOffsets[2];
/*  741 */     int srcPixelStride = src.pixelStride;
/*  742 */     int srcLineStride = src.lineStride;
/*  744 */     int dIndex = 0;
/*  745 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  746 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  747 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  748 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  749 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  750 */         long R = (rBuf[rIndex] & 0xFFFFFFFFL) << normr;
/*  751 */         long G = (gBuf[gIndex] & 0xFFFFFFFFL) << normg;
/*  752 */         long B = (bBuf[bIndex] & 0xFFFFFFFFL) << normb;
/*  754 */         if (isByte) {
/*      */           double temp;
/*      */           int hue;
/*  755 */           float intensity = (float)(R + G + B) / 3.0F;
/*  756 */           dstIntPixels[dIndex++] = (int)((long)(intensity + 0.5F) >> bnormi);
/*  759 */           long drg = R - G;
/*  760 */           long drb = R - B;
/*  762 */           double tint = drg * drg + drb * (drb - drg);
/*  764 */           double sum = (drg + drb);
/*  766 */           if (tint != 0.0D) {
/*  767 */             temp = sqrtTable[(int)(250.0D * sum * sum / tint + 0.5D)];
/*      */           } else {
/*  768 */             temp = -1.0D;
/*      */           } 
/*  771 */           if (sum > 0.0D) {
/*  772 */             hue = acosTable[(int)(500.0D * temp + 0.5D) + 500];
/*      */           } else {
/*  774 */             hue = acosTable[(int)(-500.0D * temp - 0.5D) + 500];
/*      */           } 
/*  776 */           if (B >= G) {
/*  777 */             dstIntPixels[dIndex++] = 255 - hue >> bnormh;
/*      */           } else {
/*  779 */             dstIntPixels[dIndex++] = hue >> bnormh;
/*      */           } 
/*  781 */           long min = (G > B) ? B : G;
/*  782 */           min = (R > min) ? min : R;
/*  783 */           dstIntPixels[dIndex++] = 255 - (int)((float)(255L * min) / intensity + 0.5F) >> bnorms;
/*      */         } else {
/*  787 */           float intensity = (float)(R + G + B) / 3.0F;
/*  788 */           dstPixels[dIndex++] = normi * intensity;
/*  790 */           double drg = (R - G);
/*  791 */           double drb = (R - B);
/*  792 */           double temp = Math.sqrt(drg * drg + drb * (drb - drg));
/*  794 */           if (temp != 0.0D) {
/*  795 */             temp = Math.acos((drg + drb) / temp / 2.0D);
/*  796 */             if (B >= G)
/*  797 */               temp = 6.283185307179586D - temp; 
/*      */           } else {
/*  798 */             temp = 6.283185307179586D;
/*      */           } 
/*  800 */           dstPixels[dIndex++] = normh * temp;
/*  802 */           double min = (G > B) ? B : G;
/*  803 */           min = (R > min) ? min : R;
/*  804 */           dstPixels[dIndex++] = norms * (1.0D - min / intensity);
/*      */         } 
/*      */       } 
/*      */     } 
/*  809 */     if (isByte) {
/*  810 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/*  813 */       convertToSigned(dstPixels, dstType);
/*  814 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fromRGBFloat(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  822 */     float[] rBuf = src.getFloatData(0);
/*  823 */     float[] gBuf = src.getFloatData(1);
/*  824 */     float[] bBuf = src.getFloatData(2);
/*  826 */     double normi = 1.0D, normh = 1.0D, norms = 1.0D;
/*  828 */     int bnormi = 0, bnormh = 0, bnorms = 0;
/*  830 */     int dstType = dest.getSampleModel().getDataType();
/*  831 */     boolean isByte = (dstType == 0);
/*  833 */     if (isByte) {
/*  834 */       bnormi = (1 << destComponentSize[0]) - 1;
/*  835 */       bnormh = 8 - destComponentSize[1];
/*  836 */       bnorms = 8 - destComponentSize[2];
/*  837 */       generateACosTable();
/*  838 */       generateSqrtTable();
/*  840 */     } else if (dstType < 4) {
/*  841 */       normi = ((1L << destComponentSize[0]) - 1L);
/*  842 */       normh = ((1L << destComponentSize[1]) - 1L) / 6.283185307179586D;
/*  843 */       norms = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  846 */     int height = dest.getHeight();
/*  847 */     int width = dest.getWidth();
/*  849 */     double[] dstPixels = null;
/*  850 */     int[] dstIntPixels = null;
/*  852 */     if (isByte) {
/*  853 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/*  855 */       dstPixels = new double[3 * height * width];
/*      */     } 
/*  857 */     int rStart = src.bandOffsets[0];
/*  858 */     int gStart = src.bandOffsets[1];
/*  859 */     int bStart = src.bandOffsets[2];
/*  860 */     int srcPixelStride = src.pixelStride;
/*  861 */     int srcLineStride = src.lineStride;
/*  863 */     int dIndex = 0;
/*  864 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  865 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  866 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  867 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  868 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  869 */         float R = rBuf[rIndex];
/*  870 */         float G = gBuf[gIndex];
/*  871 */         float B = bBuf[bIndex];
/*  873 */         if (isByte) {
/*      */           double temp;
/*      */           int hue;
/*  874 */           float intensity = (R + G + B) / 3.0F;
/*  875 */           dstIntPixels[dIndex++] = (int)(intensity * bnormi + 0.5F);
/*  877 */           float drg = R - G;
/*  878 */           float drb = R - B;
/*  880 */           double tint = drg * drg + drb * (drb - drg);
/*  882 */           double sum = (drg + drb);
/*  884 */           if (tint != 0.0D) {
/*  885 */             temp = sqrtTable[(int)(250.0D * sum * sum / tint + 0.5D)];
/*      */           } else {
/*  886 */             temp = -1.0D;
/*      */           } 
/*  889 */           if (sum > 0.0D) {
/*  890 */             hue = acosTable[(int)(500.0D * temp + 0.5D) + 500];
/*      */           } else {
/*  892 */             hue = acosTable[(int)(-500.0D * temp - 0.5D) + 500];
/*      */           } 
/*  894 */           if (B >= G) {
/*  895 */             dstIntPixels[dIndex++] = 255 - hue >> bnormh;
/*      */           } else {
/*  897 */             dstIntPixels[dIndex++] = hue >> bnormh;
/*      */           } 
/*  899 */           float min = (G > B) ? B : G;
/*  900 */           min = (R > min) ? min : R;
/*  901 */           dstIntPixels[dIndex++] = 255 - (int)(255.0F * min / intensity + 0.5F) >> bnorms;
/*      */         } else {
/*  905 */           float intensity = (R + G + B) / 3.0F;
/*  906 */           dstPixels[dIndex++] = normi * intensity;
/*  908 */           double drg = (R - G);
/*  909 */           double drb = (R - B);
/*  910 */           double temp = Math.sqrt(drg * drg + drb * (drb - drg));
/*  912 */           if (temp != 0.0D) {
/*  913 */             temp = Math.acos((drg + drb) / temp / 2.0D);
/*  914 */             if (B >= G)
/*  915 */               temp = 6.283185307179586D - temp; 
/*      */           } else {
/*  916 */             temp = 6.283185307179586D;
/*      */           } 
/*  918 */           dstPixels[dIndex++] = normh * temp;
/*  920 */           double min = (G > B) ? B : G;
/*  921 */           min = (R > min) ? min : R;
/*  922 */           dstPixels[dIndex++] = norms * (1.0D - min / intensity);
/*      */         } 
/*      */       } 
/*      */     } 
/*  928 */     if (isByte) {
/*  929 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/*  932 */       convertToSigned(dstPixels, dstType);
/*  933 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void fromRGBDouble(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/*  941 */     double[] rBuf = src.getDoubleData(0);
/*  942 */     double[] gBuf = src.getDoubleData(1);
/*  943 */     double[] bBuf = src.getDoubleData(2);
/*  945 */     double normi = 1.0D, normh = 1.0D, norms = 1.0D;
/*  947 */     int bnormi = 0, bnormh = 0, bnorms = 0;
/*  949 */     int dstType = dest.getSampleModel().getDataType();
/*  950 */     boolean isByte = (dstType == 0);
/*  952 */     if (isByte) {
/*  953 */       bnormi = (1 << destComponentSize[0]) - 1;
/*  954 */       bnormh = 8 - destComponentSize[1];
/*  955 */       bnorms = 8 - destComponentSize[2];
/*  956 */       generateACosTable();
/*  957 */       generateSqrtTable();
/*  959 */     } else if (dstType < 4) {
/*  960 */       normi = ((1L << destComponentSize[0]) - 1L);
/*  961 */       normh = ((1L << destComponentSize[1]) - 1L) / 6.283185307179586D;
/*  962 */       norms = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/*  965 */     int height = dest.getHeight();
/*  966 */     int width = dest.getWidth();
/*  968 */     double[] dstPixels = null;
/*  969 */     int[] dstIntPixels = null;
/*  971 */     if (isByte) {
/*  972 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/*  974 */       dstPixels = new double[3 * height * width];
/*      */     } 
/*  976 */     int rStart = src.bandOffsets[0];
/*  977 */     int gStart = src.bandOffsets[1];
/*  978 */     int bStart = src.bandOffsets[2];
/*  979 */     int srcPixelStride = src.pixelStride;
/*  980 */     int srcLineStride = src.lineStride;
/*  982 */     int dIndex = 0;
/*  983 */     for (int j = 0; j < height; j++, rStart += srcLineStride, 
/*  984 */       gStart += srcLineStride, bStart += srcLineStride) {
/*  985 */       int i = 0, rIndex = rStart, gIndex = gStart, bIndex = bStart;
/*  986 */       for (; i < width; i++, rIndex += srcPixelStride, 
/*  987 */         gIndex += srcPixelStride, bIndex += srcPixelStride) {
/*  988 */         double R = rBuf[rIndex];
/*  989 */         double G = gBuf[gIndex];
/*  990 */         double B = bBuf[bIndex];
/*  992 */         if (isByte) {
/*      */           double temp;
/*      */           int hue;
/*  993 */           double intensity = (R + G + B) / 3.0D;
/*  994 */           dstIntPixels[dIndex++] = (int)(intensity * bnormi + 0.5D);
/*  996 */           double drg = R - G;
/*  997 */           double drb = R - B;
/*  999 */           double tint = drg * drg + drb * (drb - drg);
/* 1001 */           double sum = drg + drb;
/* 1003 */           if (tint != 0.0D) {
/* 1004 */             temp = sqrtTable[(int)(250.0D * sum * sum / tint + 0.5D)];
/*      */           } else {
/* 1005 */             temp = -1.0D;
/*      */           } 
/* 1008 */           if (sum > 0.0D) {
/* 1009 */             hue = acosTable[(int)(500.0D * temp + 0.5D) + 500];
/*      */           } else {
/* 1011 */             hue = acosTable[(int)(-500.0D * temp - 0.5D) + 500];
/*      */           } 
/* 1013 */           if (B >= G) {
/* 1014 */             dstIntPixels[dIndex++] = 255 - hue >> bnormh;
/*      */           } else {
/* 1016 */             dstIntPixels[dIndex++] = hue >> bnormh;
/*      */           } 
/* 1018 */           double min = (G > B) ? B : G;
/* 1019 */           min = (R > min) ? min : R;
/* 1020 */           dstIntPixels[dIndex++] = 255 - (int)(255.0D * min / intensity + 0.5D) >> bnorms;
/*      */         } else {
/* 1024 */           double intensity = (R + G + B) / 3.0D;
/* 1025 */           dstPixels[dIndex++] = normi * intensity;
/* 1027 */           double drg = R - G;
/* 1028 */           double drb = R - B;
/* 1029 */           double temp = Math.sqrt(drg * drg + drb * (drb - drg));
/* 1031 */           if (temp != 0.0D) {
/* 1032 */             temp = Math.acos((drg + drb) / temp / 2.0D);
/* 1033 */             if (B >= G)
/* 1034 */               temp = 6.283185307179586D - temp; 
/*      */           } else {
/* 1035 */             temp = 6.283185307179586D;
/*      */           } 
/* 1037 */           dstPixels[dIndex++] = normh * temp;
/* 1039 */           double min = (G > B) ? B : G;
/* 1040 */           min = (R > min) ? min : R;
/* 1041 */           dstPixels[dIndex++] = norms * (1.0D - min / intensity);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1045 */     if (isByte) {
/* 1046 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/* 1049 */       convertToSigned(dstPixels, dstType);
/* 1050 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   public WritableRaster toCIEXYZ(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1063 */     WritableRaster tempRas = toRGB(src, srcComponentSize, (WritableRaster)null, (int[])null);
/* 1064 */     return RGBToCIEXYZ(tempRas, tempRas.getSampleModel().getSampleSize(), dest, destComponentSize);
/*      */   }
/*      */   
/*      */   public WritableRaster toRGB(Raster src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1077 */     checkParameters(src, srcComponentSize, dest, destComponentSize);
/* 1079 */     SampleModel srcSampleModel = src.getSampleModel();
/* 1081 */     if (srcComponentSize == null)
/* 1082 */       srcComponentSize = srcSampleModel.getSampleSize(); 
/* 1084 */     if (dest == null) {
/* 1085 */       Point origin = new Point(src.getMinX(), src.getMinY());
/* 1086 */       dest = RasterFactory.createWritableRaster(srcSampleModel, origin);
/*      */     } 
/* 1090 */     SampleModel dstSampleModel = dest.getSampleModel();
/* 1091 */     if (destComponentSize == null)
/* 1092 */       destComponentSize = dstSampleModel.getSampleSize(); 
/* 1094 */     PixelAccessor srcAcc = new PixelAccessor(srcSampleModel, null);
/* 1095 */     UnpackedImageData srcUid = srcAcc.getPixels(src, src.getBounds(), srcSampleModel.getDataType(), false);
/* 1099 */     switch (srcSampleModel.getDataType()) {
/*      */       case 0:
/* 1102 */         toRGBByte(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/* 1106 */         toRGBShort(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 3:
/* 1109 */         toRGBInt(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 4:
/* 1112 */         toRGBFloat(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */       case 5:
/* 1115 */         toRGBDouble(srcUid, srcComponentSize, dest, destComponentSize);
/*      */         break;
/*      */     } 
/* 1118 */     return dest;
/*      */   }
/*      */   
/*      */   private void toRGBByte(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1124 */     byte[] iBuf = src.getByteData(0);
/* 1125 */     byte[] hBuf = src.getByteData(1);
/* 1126 */     byte[] sBuf = src.getByteData(2);
/* 1128 */     double normi = 1.0D / ((1 << srcComponentSize[0]) - 1);
/* 1129 */     double normh = 1.0D / ((1 << srcComponentSize[1]) - 1) * 6.283185307179586D;
/* 1130 */     double norms = 1.0D / ((1 << srcComponentSize[2]) - 1);
/* 1132 */     double normr = 1.0D, normg = 1.0D, normb = 1.0D;
/* 1134 */     int dstType = dest.getSampleModel().getDataType();
/* 1135 */     boolean isByte = (dstType == 0);
/* 1137 */     if (isByte)
/* 1138 */       generateTanTable(); 
/* 1140 */     if (dstType < 4) {
/* 1141 */       normr = ((1L << destComponentSize[0]) - 1L);
/* 1142 */       normg = ((1L << destComponentSize[1]) - 1L);
/* 1143 */       normb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/* 1146 */     int height = dest.getHeight();
/* 1147 */     int width = dest.getWidth();
/* 1149 */     double[] dstPixels = null;
/* 1150 */     int[] dstIntPixels = null;
/* 1152 */     if (isByte) {
/* 1153 */       dstIntPixels = new int[3 * height * width];
/*      */     } else {
/* 1155 */       dstPixels = new double[3 * height * width];
/*      */     } 
/* 1157 */     int iStart = src.bandOffsets[0];
/* 1158 */     int hStart = src.bandOffsets[1];
/* 1159 */     int sStart = src.bandOffsets[2];
/* 1160 */     int srcPixelStride = src.pixelStride;
/* 1161 */     int srcLineStride = src.lineStride;
/* 1163 */     int dIndex = 0;
/* 1164 */     for (int j = 0; j < height; j++, iStart += srcLineStride, 
/* 1165 */       hStart += srcLineStride, sStart += srcLineStride) {
/* 1166 */       int i = 0, iIndex = iStart, hIndex = hStart, sIndex = sStart;
/* 1167 */       for (; i < width; i++, iIndex += srcPixelStride, 
/* 1168 */         hIndex += srcPixelStride, sIndex += srcPixelStride) {
/* 1169 */         double I = (iBuf[iIndex] & 0xFF) * normi;
/* 1170 */         int h = hBuf[hIndex] & 0xFF;
/* 1171 */         double S = (sBuf[sIndex] & 0xFF) * norms;
/* 1173 */         if (isByte) {
/* 1176 */           float b = (float)I, g = b, r = g;
/* 1178 */           if (S != 0.0D)
/* 1179 */             if (h >= 85 && h <= 170) {
/* 1180 */               r = (float)((1.0D - S) * I);
/* 1181 */               float c1 = (float)(3.0D * I - r);
/* 1182 */               float c2 = (float)(SQRT3 * (r - I) * tanTable[h]);
/* 1183 */               g = (c1 + c2) / 2.0F;
/* 1184 */               b = (c1 - c2) / 2.0F;
/* 1186 */             } else if (h > 170) {
/* 1187 */               g = (float)((1.0D - S) * I);
/* 1188 */               float c1 = (float)(3.0D * I - g);
/* 1189 */               float c2 = (float)(SQRT3 * (g - I) * tanTable[h - 85]);
/* 1190 */               b = (c1 + c2) / 2.0F;
/* 1191 */               r = (c1 - c2) / 2.0F;
/* 1193 */             } else if (h < 85) {
/* 1194 */               b = (float)((1.0D - S) * I);
/* 1195 */               float c1 = (float)(3.0D * I - b);
/* 1196 */               float c2 = (float)(SQRT3 * (b - I) * tanTable[h + 85]);
/* 1197 */               r = (c1 + c2) / 2.0F;
/* 1198 */               g = (c1 - c2) / 2.0F;
/*      */             }  
/* 1201 */           dstIntPixels[dIndex++] = (int)(((r < 0.0F) ? 0.0F : ((r > 1.0F) ? 1.0F : r)) * normr + 0.5D);
/* 1203 */           dstIntPixels[dIndex++] = (int)(((g < 0.0F) ? 0.0F : ((g > 1.0F) ? 1.0F : g)) * normg + 0.5D);
/* 1205 */           dstIntPixels[dIndex++] = (int)(((b < 0.0F) ? 0.0F : ((b > 1.0F) ? 1.0F : b)) * normb + 0.5D);
/*      */         } else {
/* 1211 */           double B = I, G = B, R = G;
/* 1212 */           if (S != 0.0D) {
/* 1213 */             double H = h * normh;
/* 1214 */             if (H >= 2.0943951023931953D && H <= 4.1887902047863905D) {
/* 1215 */               R = (1.0D - S) * I;
/* 1216 */               double c1 = 3.0D * I - R;
/* 1217 */               double c2 = SQRT3 * (R - I) * Math.tan(H);
/* 1218 */               G = (c1 + c2) / 2.0D;
/* 1219 */               B = (c1 - c2) / 2.0D;
/* 1221 */             } else if (H > 4.1887902047863905D) {
/* 1222 */               G = (1.0D - S) * I;
/* 1223 */               double c1 = 3.0D * I - G;
/* 1224 */               double c2 = SQRT3 * (G - I) * Math.tan(H - 2.0943951023931953D);
/* 1225 */               B = (c1 + c2) / 2.0D;
/* 1226 */               R = (c1 - c2) / 2.0D;
/* 1228 */             } else if (H < 2.0943951023931953D) {
/* 1229 */               B = (1.0D - S) * I;
/* 1230 */               double c1 = 3.0D * I - B;
/* 1231 */               double c2 = SQRT3 * (B - I) * Math.tan(H - 4.1887902047863905D);
/* 1232 */               R = (c1 + c2) / 2.0D;
/* 1233 */               G = (c1 - c2) / 2.0D;
/*      */             } 
/*      */           } 
/* 1236 */           dstPixels[dIndex++] = ((R < 0.0D) ? 0.0D : ((R > 1.0D) ? 1.0D : R)) * normr;
/* 1237 */           dstPixels[dIndex++] = ((G < 0.0D) ? 0.0D : ((G > 1.0D) ? 1.0D : G)) * normg;
/* 1238 */           dstPixels[dIndex++] = ((B < 0.0D) ? 0.0D : ((B > 1.0D) ? 1.0D : B)) * normb;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1243 */     if (isByte) {
/* 1244 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstIntPixels);
/*      */     } else {
/* 1247 */       convertToSigned(dstPixels, dstType);
/* 1248 */       dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void toRGBShort(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1257 */     short[] iBuf = src.getShortData(0);
/* 1258 */     short[] hBuf = src.getShortData(1);
/* 1259 */     short[] sBuf = src.getShortData(2);
/* 1261 */     double normi = 1.0D / ((1 << srcComponentSize[0]) - 1);
/* 1262 */     double normh = 1.0D / ((1 << srcComponentSize[1]) - 1) * 6.283185307179586D;
/* 1263 */     double norms = 1.0D / ((1 << srcComponentSize[2]) - 1);
/* 1265 */     double normr = 1.0D, normg = 1.0D, normb = 1.0D;
/* 1267 */     int dstType = dest.getSampleModel().getDataType();
/* 1269 */     if (dstType < 4) {
/* 1270 */       normr = ((1L << destComponentSize[0]) - 1L);
/* 1271 */       normg = ((1L << destComponentSize[1]) - 1L);
/* 1272 */       normb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/* 1275 */     int height = dest.getHeight();
/* 1276 */     int width = dest.getWidth();
/* 1278 */     double[] dstPixels = new double[3 * height * width];
/* 1280 */     int iStart = src.bandOffsets[0];
/* 1281 */     int hStart = src.bandOffsets[1];
/* 1282 */     int sStart = src.bandOffsets[2];
/* 1283 */     int srcPixelStride = src.pixelStride;
/* 1284 */     int srcLineStride = src.lineStride;
/* 1286 */     int dIndex = 0;
/* 1287 */     for (int j = 0; j < height; j++, iStart += srcLineStride, 
/* 1288 */       hStart += srcLineStride, sStart += srcLineStride) {
/* 1289 */       int i = 0, iIndex = iStart, hIndex = hStart, sIndex = sStart;
/* 1290 */       for (; i < width; i++, iIndex += srcPixelStride, 
/* 1291 */         hIndex += srcPixelStride, sIndex += srcPixelStride) {
/* 1292 */         double I = (iBuf[iIndex] & 0xFFFF) * normi;
/* 1293 */         double H = (hBuf[hIndex] & 0xFFFF) * normh;
/* 1294 */         double S = (sBuf[sIndex] & 0xFFFF) * norms;
/* 1297 */         double B = I, G = B, R = G;
/* 1298 */         if (S != 0.0D)
/* 1299 */           if (H >= 2.0943951023931953D && H <= 4.1887902047863905D) {
/* 1300 */             R = (1.0D - S) * I;
/* 1301 */             double c1 = 3.0D * I - R;
/* 1302 */             double c2 = SQRT3 * (R - I) * Math.tan(H);
/* 1303 */             G = (c1 + c2) / 2.0D;
/* 1304 */             B = (c1 - c2) / 2.0D;
/* 1306 */           } else if (H > 4.1887902047863905D) {
/* 1307 */             G = (1.0D - S) * I;
/* 1308 */             double c1 = 3.0D * I - G;
/* 1309 */             double c2 = SQRT3 * (G - I) * Math.tan(H - 2.0943951023931953D);
/* 1310 */             B = (c1 + c2) / 2.0D;
/* 1311 */             R = (c1 - c2) / 2.0D;
/* 1313 */           } else if (H < 2.0943951023931953D) {
/* 1314 */             B = (1.0D - S) * I;
/* 1315 */             double c1 = 3.0D * I - B;
/* 1316 */             double c2 = SQRT3 * (B - I) * Math.tan(H - 4.1887902047863905D);
/* 1317 */             R = (c1 + c2) / 2.0D;
/* 1318 */             G = (c1 - c2) / 2.0D;
/*      */           }  
/* 1322 */         dstPixels[dIndex++] = ((R < 0.0D) ? 0.0D : ((R > 1.0D) ? 1.0D : R)) * normr;
/* 1323 */         dstPixels[dIndex++] = ((G < 0.0D) ? 0.0D : ((G > 1.0D) ? 1.0D : G)) * normg;
/* 1324 */         dstPixels[dIndex++] = ((B < 0.0D) ? 0.0D : ((B > 1.0D) ? 1.0D : B)) * normb;
/*      */       } 
/*      */     } 
/* 1328 */     convertToSigned(dstPixels, dstType);
/* 1329 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private void toRGBInt(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1334 */     int[] iBuf = src.getIntData(0);
/* 1335 */     int[] hBuf = src.getIntData(1);
/* 1336 */     int[] sBuf = src.getIntData(2);
/* 1338 */     double normi = 1.0D / ((1L << srcComponentSize[0]) - 1L);
/* 1339 */     double normh = 1.0D / ((1L << srcComponentSize[1]) - 1L) * 6.283185307179586D;
/* 1340 */     double norms = 1.0D / ((1L << srcComponentSize[2]) - 1L);
/* 1342 */     double normr = 1.0D, normg = 1.0D, normb = 1.0D;
/* 1344 */     int dstType = dest.getSampleModel().getDataType();
/* 1346 */     if (dstType < 4) {
/* 1347 */       normr = ((1L << destComponentSize[0]) - 1L);
/* 1348 */       normg = ((1L << destComponentSize[1]) - 1L);
/* 1349 */       normb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/* 1352 */     int height = dest.getHeight();
/* 1353 */     int width = dest.getWidth();
/* 1355 */     double[] dstPixels = new double[3 * height * width];
/* 1357 */     int iStart = src.bandOffsets[0];
/* 1358 */     int hStart = src.bandOffsets[1];
/* 1359 */     int sStart = src.bandOffsets[2];
/* 1360 */     int srcPixelStride = src.pixelStride;
/* 1361 */     int srcLineStride = src.lineStride;
/* 1363 */     int dIndex = 0;
/* 1364 */     for (int j = 0; j < height; j++, iStart += srcLineStride, 
/* 1365 */       hStart += srcLineStride, sStart += srcLineStride) {
/* 1366 */       int i = 0, iIndex = iStart, hIndex = hStart, sIndex = sStart;
/* 1367 */       for (; i < width; i++, iIndex += srcPixelStride, 
/* 1368 */         hIndex += srcPixelStride, sIndex += srcPixelStride) {
/* 1369 */         double I = (iBuf[iIndex] & 0xFFFFFFFFL) * normi;
/* 1370 */         double H = (hBuf[hIndex] & 0xFFFFFFFFL) * normh;
/* 1371 */         double S = (sBuf[sIndex] & 0xFFFFFFFFL) * norms;
/* 1375 */         double B = I, G = B, R = G;
/* 1376 */         if (S != 0.0D)
/* 1377 */           if (H >= 2.0943951023931953D && H <= 4.1887902047863905D) {
/* 1378 */             R = (1.0D - S) * I;
/* 1379 */             double c1 = 3.0D * I - R;
/* 1380 */             double c2 = SQRT3 * (R - I) * Math.tan(H);
/* 1381 */             G = (c1 + c2) / 2.0D;
/* 1382 */             B = (c1 - c2) / 2.0D;
/* 1384 */           } else if (H > 4.1887902047863905D) {
/* 1385 */             G = (1.0D - S) * I;
/* 1386 */             double c1 = 3.0D * I - G;
/* 1387 */             double c2 = SQRT3 * (G - I) * Math.tan(H - 2.0943951023931953D);
/* 1388 */             B = (c1 + c2) / 2.0D;
/* 1389 */             R = (c1 - c2) / 2.0D;
/* 1391 */           } else if (H < 2.0943951023931953D) {
/* 1392 */             B = (1.0D - S) * I;
/* 1393 */             double c1 = 3.0D * I - B;
/* 1394 */             double c2 = SQRT3 * (B - I) * Math.tan(H - 4.1887902047863905D);
/* 1395 */             R = (c1 + c2) / 2.0D;
/* 1396 */             G = (c1 - c2) / 2.0D;
/*      */           }  
/* 1400 */         dstPixels[dIndex++] = ((R < 0.0D) ? 0.0D : ((R > 1.0D) ? 1.0D : R)) * normr;
/* 1401 */         dstPixels[dIndex++] = ((G < 0.0D) ? 0.0D : ((G > 1.0D) ? 1.0D : G)) * normg;
/* 1402 */         dstPixels[dIndex++] = ((B < 0.0D) ? 0.0D : ((B > 1.0D) ? 1.0D : B)) * normb;
/*      */       } 
/*      */     } 
/* 1405 */     convertToSigned(dstPixels, dstType);
/* 1406 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private void toRGBFloat(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1411 */     float[] iBuf = src.getFloatData(0);
/* 1412 */     float[] hBuf = src.getFloatData(1);
/* 1413 */     float[] sBuf = src.getFloatData(2);
/* 1415 */     double normr = 1.0D, normg = 1.0D, normb = 1.0D;
/* 1417 */     int dstType = dest.getSampleModel().getDataType();
/* 1419 */     if (dstType < 4) {
/* 1420 */       normr = ((1L << destComponentSize[0]) - 1L);
/* 1421 */       normg = ((1L << destComponentSize[1]) - 1L);
/* 1422 */       normb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/* 1425 */     int height = dest.getHeight();
/* 1426 */     int width = dest.getWidth();
/* 1428 */     double[] dstPixels = new double[3 * height * width];
/* 1430 */     int iStart = src.bandOffsets[0];
/* 1431 */     int hStart = src.bandOffsets[1];
/* 1432 */     int sStart = src.bandOffsets[2];
/* 1433 */     int srcPixelStride = src.pixelStride;
/* 1434 */     int srcLineStride = src.lineStride;
/* 1436 */     int dIndex = 0;
/* 1437 */     for (int j = 0; j < height; j++, iStart += srcLineStride, 
/* 1438 */       hStart += srcLineStride, sStart += srcLineStride) {
/* 1439 */       int i = 0, iIndex = iStart, hIndex = hStart, sIndex = sStart;
/* 1440 */       for (; i < width; i++, iIndex += srcPixelStride, 
/* 1441 */         hIndex += srcPixelStride, sIndex += srcPixelStride) {
/* 1442 */         double I = iBuf[iIndex];
/* 1443 */         double H = hBuf[hIndex];
/* 1444 */         double S = sBuf[sIndex];
/* 1447 */         double B = I, G = B, R = G;
/* 1448 */         if (S != 0.0D)
/* 1449 */           if (H >= 2.0943951023931953D && H <= 4.1887902047863905D) {
/* 1450 */             R = (1.0D - S) * I;
/* 1451 */             double c1 = 3.0D * I - R;
/* 1452 */             double c2 = SQRT3 * (R - I) * Math.tan(H);
/* 1453 */             G = (c1 + c2) / 2.0D;
/* 1454 */             B = (c1 - c2) / 2.0D;
/* 1456 */           } else if (H > 4.1887902047863905D) {
/* 1457 */             G = (1.0D - S) * I;
/* 1458 */             double c1 = 3.0D * I - G;
/* 1459 */             double c2 = SQRT3 * (G - I) * Math.tan(H - 2.0943951023931953D);
/* 1460 */             B = (c1 + c2) / 2.0D;
/* 1461 */             R = (c1 - c2) / 2.0D;
/* 1463 */           } else if (H < 2.0943951023931953D) {
/* 1464 */             B = (1.0D - S) * I;
/* 1465 */             double c1 = 3.0D * I - B;
/* 1466 */             double c2 = SQRT3 * (B - I) * Math.tan(H - 4.1887902047863905D);
/* 1467 */             R = (c1 + c2) / 2.0D;
/* 1468 */             G = (c1 - c2) / 2.0D;
/*      */           }  
/* 1472 */         dstPixels[dIndex++] = ((R < 0.0D) ? 0.0D : ((R > 1.0D) ? 1.0D : R)) * normr;
/* 1473 */         dstPixels[dIndex++] = ((G < 0.0D) ? 0.0D : ((G > 1.0D) ? 1.0D : G)) * normg;
/* 1474 */         dstPixels[dIndex++] = ((B < 0.0D) ? 0.0D : ((B > 1.0D) ? 1.0D : B)) * normb;
/*      */       } 
/*      */     } 
/* 1477 */     convertToSigned(dstPixels, dstType);
/* 1478 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */   
/*      */   private void toRGBDouble(UnpackedImageData src, int[] srcComponentSize, WritableRaster dest, int[] destComponentSize) {
/* 1483 */     double[] iBuf = src.getDoubleData(0);
/* 1484 */     double[] hBuf = src.getDoubleData(1);
/* 1485 */     double[] sBuf = src.getDoubleData(2);
/* 1487 */     double normr = 1.0D, normg = 1.0D, normb = 1.0D;
/* 1489 */     int dstType = dest.getSampleModel().getDataType();
/* 1491 */     if (dstType < 4) {
/* 1492 */       normr = ((1L << destComponentSize[0]) - 1L);
/* 1493 */       normg = ((1L << destComponentSize[1]) - 1L);
/* 1494 */       normb = ((1L << destComponentSize[2]) - 1L);
/*      */     } 
/* 1497 */     int height = dest.getHeight();
/* 1498 */     int width = dest.getWidth();
/* 1500 */     double[] dstPixels = new double[3 * height * width];
/* 1502 */     int iStart = src.bandOffsets[0];
/* 1503 */     int hStart = src.bandOffsets[1];
/* 1504 */     int sStart = src.bandOffsets[2];
/* 1505 */     int srcPixelStride = src.pixelStride;
/* 1506 */     int srcLineStride = src.lineStride;
/* 1508 */     int dIndex = 0;
/* 1509 */     for (int j = 0; j < height; j++, iStart += srcLineStride, 
/* 1510 */       hStart += srcLineStride, sStart += srcLineStride) {
/* 1511 */       int i = 0, iIndex = iStart, hIndex = hStart, sIndex = sStart;
/* 1512 */       for (; i < width; i++, iIndex += srcPixelStride, 
/* 1513 */         hIndex += srcPixelStride, sIndex += srcPixelStride) {
/* 1514 */         double I = iBuf[iIndex];
/* 1515 */         double H = hBuf[hIndex];
/* 1516 */         double S = sBuf[sIndex];
/* 1520 */         double B = I, G = B, R = G;
/* 1521 */         if (S != 0.0D)
/* 1522 */           if (H >= 2.0943951023931953D && H <= 4.1887902047863905D) {
/* 1523 */             R = (1.0D - S) * I;
/* 1524 */             double c1 = 3.0D * I - R;
/* 1525 */             double c2 = SQRT3 * (R - I) * Math.tan(H);
/* 1526 */             G = (c1 + c2) / 2.0D;
/* 1527 */             B = (c1 - c2) / 2.0D;
/* 1529 */           } else if (H > 4.1887902047863905D) {
/* 1530 */             G = (1.0D - S) * I;
/* 1531 */             double c1 = 3.0D * I - G;
/* 1532 */             double c2 = SQRT3 * (G - I) * Math.tan(H - 2.0943951023931953D);
/* 1533 */             B = (c1 + c2) / 2.0D;
/* 1534 */             R = (c1 - c2) / 2.0D;
/* 1536 */           } else if (H < 2.0943951023931953D) {
/* 1537 */             B = (1.0D - S) * I;
/* 1538 */             double c1 = 3.0D * I - B;
/* 1539 */             double c2 = SQRT3 * (B - I) * Math.tan(H - 4.1887902047863905D);
/* 1540 */             R = (c1 + c2) / 2.0D;
/* 1541 */             G = (c1 - c2) / 2.0D;
/*      */           }  
/* 1545 */         dstPixels[dIndex++] = ((R < 0.0D) ? 0.0D : ((R > 1.0D) ? 1.0D : R)) * normr;
/* 1546 */         dstPixels[dIndex++] = ((G < 0.0D) ? 0.0D : ((G > 1.0D) ? 1.0D : G)) * normg;
/* 1547 */         dstPixels[dIndex++] = ((B < 0.0D) ? 0.0D : ((B > 1.0D) ? 1.0D : B)) * normb;
/*      */       } 
/*      */     } 
/* 1550 */     convertToSigned(dstPixels, dstType);
/* 1551 */     dest.setPixels(dest.getMinX(), dest.getMinY(), width, height, dstPixels);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\IHSColorSpace.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */