/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.media.jai.codec.ImageCodec;
/*      */ import com.sun.media.jai.codec.ImageDecoder;
/*      */ import com.sun.media.jai.codec.MemoryCacheSeekableStream;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Image;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*      */ import java.awt.image.renderable.ParameterBlock;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImageOp;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.CRIFImpl;
/*      */ import javax.media.jai.EnumeratedParameter;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.Interpolation;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.LookupTableJAI;
/*      */ import javax.media.jai.MultiResolutionRenderableImage;
/*      */ import javax.media.jai.ROI;
/*      */ import javax.media.jai.ROIShape;
/*      */ import javax.media.jai.RenderableOp;
/*      */ import javax.media.jai.RenderedOp;
/*      */ import javax.media.jai.TiledImage;
/*      */ import javax.media.jai.operator.TransposeDescriptor;
/*      */ import javax.media.jai.operator.TransposeType;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class IIPCRIF extends CRIFImpl {
/*      */   private static final int MASK_FILTER = 1;
/*      */   
/*      */   private static final int MASK_COLOR_TWIST = 2;
/*      */   
/*      */   private static final int MASK_CONTRAST = 4;
/*      */   
/*      */   private static final int MASK_ROI_SOURCE = 8;
/*      */   
/*      */   private static final int MASK_TRANSFORM = 16;
/*      */   
/*      */   private static final int MASK_ASPECT_RATIO = 32;
/*      */   
/*      */   private static final int MASK_ROI_DESTINATION = 64;
/*      */   
/*      */   private static final int MASK_ROTATION = 128;
/*      */   
/*      */   private static final int MASK_MIRROR_AXIS = 256;
/*      */   
/*      */   private static final int MASK_ICC_PROFILE = 512;
/*      */   
/*      */   private static final int MASK_JPEG_QUALITY = 1024;
/*      */   
/*      */   private static final int MASK_JPEG_TABLE = 2048;
/*      */   
/*      */   private static final int VENDOR_HP = 0;
/*      */   
/*      */   private static final int VENDOR_LIVE_PICTURE = 1;
/*      */   
/*      */   private static final int VENDOR_KODAK = 2;
/*      */   
/*      */   private static final int VENDOR_UNREGISTERED = 255;
/*      */   
/*      */   private static final int VENDOR_EXPERIMENTAL = 999;
/*      */   
/*      */   private static final int SERVER_CVT_JPEG = 1;
/*      */   
/*      */   private static final int SERVER_CVT_FPX = 2;
/*      */   
/*      */   private static final int SERVER_CVT_MJPEG = 4;
/*      */   
/*      */   private static final int SERVER_CVT_MFPX = 8;
/*      */   
/*      */   private static final int SERVER_CVT_M2JPEG = 16;
/*      */   
/*      */   private static final int SERVER_CVT_M2FPX = 32;
/*      */   
/*      */   private static final int SERVER_CVT_JTL = 64;
/*      */   
/*      */   private static final int SERVER_JPEG_PARTIAL = 5;
/*      */   
/*      */   private static final int SERVER_JPEG_FULL = 21;
/*      */   
/*      */   private static final int SERVER_FPX_PARTIAL = 10;
/*      */   
/*      */   private static final int SERVER_FPX_FULL = 42;
/*      */   
/*  131 */   private static final double[][] YCCA_TO_RGBA = new double[][] { { 1.3584D, 0.0D, 1.8215D, 0.0D }, { 1.3584D, -0.4303D, -0.9271D, 0.0D }, { 1.3584D, 2.2179D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*      */   
/*  139 */   private static final double[][] YCCA_TO_RGBA_CONST = new double[][] { { -249.55D }, { 194.14D }, { -345.99D }, { 0.0D } };
/*      */   
/*  143 */   private static final double[][] RGBA_TO_YCCA = new double[][] { { 0.220018D, 0.432276D, 0.083867D, 0.0D }, { -0.134755D, -0.264756D, 0.399511D, 0.0D }, { 0.384918D, -0.322373D, -0.062544D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*      */   
/*  151 */   private static final double[][] RGBA_TO_YCCA_CONST = new double[][] { { 5.726E-4D }, { 155.9984D }, { 137.0022D }, { 0.0D } };
/*      */   
/*  155 */   private static final double[][] YCC_TO_RGB = new double[][] { { 1.3584D, 0.0D, 1.8215D }, { 1.3584D, -0.4303D, -0.9271D }, { 1.3584D, 2.2179D, 0.0D } };
/*      */   
/*  162 */   private static final double[][] YCC_TO_RGB_CONST = new double[][] { { -249.55D }, { 194.14D }, { -345.99D } };
/*      */   
/*  166 */   private static final double[][] RGB_TO_YCC = new double[][] { { 0.220018D, 0.432276D, 0.083867D }, { -0.134755D, -0.264756D, 0.399511D }, { 0.384918D, -0.322373D, -0.062544D } };
/*      */   
/*  173 */   private static final double[][] RGB_TO_YCC_CONST = new double[][] { { 5.726E-4D }, { 155.9984D }, { 137.0022D } };
/*      */   
/*      */   private static final int getOperationMask(ParameterBlock pb) {
/*  180 */     int opMask = 0;
/*  184 */     if (pb.getFloatParameter(2) != 0.0F)
/*  185 */       opMask |= 0x1; 
/*  187 */     if (pb.getObjectParameter(3) != null)
/*  188 */       opMask |= 0x2; 
/*  190 */     if (Math.abs(pb.getFloatParameter(4) - 1.0F) > 0.01F)
/*  191 */       opMask |= 0x4; 
/*  193 */     if (pb.getObjectParameter(5) != null)
/*  194 */       opMask |= 0x8; 
/*  196 */     AffineTransform tf = (AffineTransform)pb.getObjectParameter(6);
/*  197 */     if (!tf.isIdentity())
/*  198 */       opMask |= 0x10; 
/*  200 */     if (pb.getObjectParameter(7) != null)
/*  201 */       opMask |= 0x20; 
/*  203 */     if (pb.getObjectParameter(8) != null)
/*  204 */       opMask |= 0x40; 
/*  206 */     if (pb.getIntParameter(9) != 0)
/*  207 */       opMask |= 0x80; 
/*  209 */     if (pb.getObjectParameter(10) != null)
/*  210 */       opMask |= 0x100; 
/*  212 */     if (pb.getObjectParameter(11) != null)
/*  213 */       opMask |= 0x200; 
/*  215 */     if (pb.getObjectParameter(12) != null)
/*  216 */       opMask |= 0x400; 
/*  218 */     if (pb.getObjectParameter(13) != null)
/*  219 */       opMask |= 0x800; 
/*  222 */     return opMask;
/*      */   }
/*      */   
/*      */   private static final int getServerCapabilityMask(String URLSpec, RenderedImage lowRes) {
/*  230 */     int vendorID = 255;
/*  231 */     int serverMask = 0;
/*  234 */     if (lowRes.getProperty("iip-server") != null && lowRes.getProperty("iip-server") != Image.UndefinedProperty) {
/*  236 */       String serverString = (String)lowRes.getProperty("iip-server");
/*  237 */       int dot = serverString.indexOf(".");
/*  238 */       vendorID = Integer.valueOf(serverString.substring(0, dot)).intValue();
/*  240 */       serverMask = Integer.valueOf(serverString.substring(dot + 1)).intValue();
/*      */     } 
/*  248 */     if (serverMask != 127 && vendorID != 0 && vendorID != 1 && vendorID != 2) {
/*  252 */       int[] maxSize = (int[])lowRes.getProperty("max-size");
/*  253 */       String rgn = "&RGN=0.0,0.0," + (64.0F / maxSize[0]) + "," + (64.0F / maxSize[1]);
/*  257 */       if (canDecode(URLSpec, "&CNT=0.9&WID=64&CVT=JPEG", "JPEG")) {
/*  259 */         serverMask = 21;
/*  260 */       } else if (canDecode(URLSpec, "&CNT=0.9&WID=64&CVT=FPX", "FPX")) {
/*  262 */         serverMask = 42;
/*  263 */       } else if (canDecode(URLSpec, rgn + "&CVT=JPEG", "JPEG")) {
/*  265 */         serverMask = 5;
/*  266 */       } else if (canDecode(URLSpec, rgn + "&CVT=FPX", "FPX")) {
/*  268 */         serverMask = 10;
/*      */       } 
/*      */     } 
/*  272 */     return serverMask;
/*      */   }
/*      */   
/*      */   private static boolean canDecode(String base, String suffix, String fmt) {
/*  284 */     StringBuffer buf = new StringBuffer(base);
/*  286 */     URL url = null;
/*  287 */     InputStream stream = null;
/*  288 */     RenderedImage rendering = null;
/*  290 */     boolean itWorks = false;
/*      */     try {
/*  293 */       buf.append(suffix);
/*  294 */       url = new URL(buf.toString());
/*  295 */       stream = url.openStream();
/*  296 */       ImageDecoder decoder = ImageCodec.createImageDecoder(fmt, stream, null);
/*  298 */       rendering = decoder.decodeAsRenderedImage();
/*  299 */       itWorks = true;
/*  300 */     } catch (Exception e) {
/*  301 */       itWorks = false;
/*      */     } 
/*  304 */     return itWorks;
/*      */   }
/*      */   
/*      */   private static final double[][] matrixMultiply(double[][] A, double[][] B) {
/*  316 */     if ((A[0]).length != B.length)
/*  317 */       throw new RuntimeException(JaiI18N.getString("IIPCRIF0")); 
/*  320 */     int nRows = A.length;
/*  321 */     int nCols = (B[0]).length;
/*  322 */     double[][] C = new double[nRows][nCols];
/*  324 */     int nSum = (A[0]).length;
/*  325 */     for (int r = 0; r < nRows; r++) {
/*  326 */       for (int c = 0; c < nCols; c++) {
/*  327 */         C[r][c] = 0.0D;
/*  328 */         for (int k = 0; k < nSum; k++)
/*  329 */           C[r][c] = C[r][c] + A[r][k] * B[k][c]; 
/*      */       } 
/*      */     } 
/*  334 */     return C;
/*      */   }
/*      */   
/*      */   private static final double[][] composeMatrices(double[][] A, double[][] b) {
/*  344 */     int nRows = A.length;
/*  345 */     if (nRows != b.length)
/*  346 */       throw new RuntimeException(JaiI18N.getString("IIPCRIF1")); 
/*  347 */     if ((b[0]).length != 1)
/*  348 */       throw new RuntimeException(JaiI18N.getString("IIPCRIF2")); 
/*  350 */     int nCols = (A[0]).length;
/*  352 */     double[][] bcMatrix = new double[nRows][nCols + 1];
/*  354 */     for (int r = 0; r < nRows; r++) {
/*  355 */       for (int c = 0; c < nCols; c++)
/*  356 */         bcMatrix[r][c] = A[r][c]; 
/*  358 */       bcMatrix[r][nCols] = b[r][0];
/*      */     } 
/*  361 */     return bcMatrix;
/*      */   }
/*      */   
/*      */   private static final double[][] getColorTwistMatrix(ColorModel colorModel, ParameterBlock pb) {
/*  374 */     float[] ctwParam = (float[])pb.getObjectParameter(3);
/*  375 */     double[][] ctw = new double[4][4];
/*  376 */     int k = 0;
/*  377 */     for (int r = 0; r < 4; r++) {
/*  378 */       for (int c = 0; c < 4; c++)
/*  379 */         ctw[r][c] = ctwParam[k++]; 
/*      */     } 
/*  385 */     double[][] H = (double[][])null;
/*  386 */     double[][] d = (double[][])null;
/*  387 */     int csType = colorModel.getColorSpace().getType();
/*  388 */     if (csType == 6 || csType == 5) {
/*  391 */       H = matrixMultiply(matrixMultiply(YCCA_TO_RGBA, ctw), RGBA_TO_YCCA);
/*  393 */       d = YCCA_TO_RGBA_CONST;
/*      */     } else {
/*  395 */       H = ctw;
/*  396 */       d = new double[][] { { 0.0D }, { 0.0D }, { 0.0D }, { 0.0D } };
/*      */     } 
/*  400 */     double[][] A = (double[][])null;
/*  401 */     double[][] b = (double[][])null;
/*  402 */     if (csType == 6) {
/*  403 */       if (colorModel.hasAlpha()) {
/*  404 */         A = new double[][] { { 1.0D, 0.0D }, { 1.0D, 0.0D }, { 1.0D, 0.0D }, { 0.0D, 1.0D } };
/*  406 */         b = new double[][] { { 0.0D }, { 0.0D }, { 0.0D }, { 0.0D } };
/*      */       } else {
/*  408 */         A = new double[][] { { 1.0D }, { 1.0D }, { 1.0D }, { 0.0D } };
/*  409 */         b = new double[][] { { 0.0D }, { 0.0D }, { 0.0D }, { 255.0D } };
/*      */       } 
/*  411 */     } else if (!colorModel.hasAlpha()) {
/*  412 */       A = new double[][] { { 1.0D, 0.0D, 0.0D }, { 0.0D, 1.0D, 0.0D }, { 0.0D, 0.0D, 1.0D }, { 0.0D, 0.0D, 0.0D } };
/*  417 */       b = new double[][] { { 0.0D }, { 0.0D }, { 0.0D }, { 255.0D } };
/*      */     } else {
/*  419 */       A = new double[][] { { 1.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 1.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 1.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*  424 */       b = new double[][] { { 0.0D }, { 0.0D }, { 0.0D }, { 0.0D } };
/*      */     } 
/*  428 */     boolean truncateChroma = false;
/*  429 */     if (csType == 6 && ctwParam[4] == 0.0F && ctwParam[7] == 0.0F && ctwParam[8] == 0.0F && ctwParam[11] == 0.0F)
/*  432 */       truncateChroma = true; 
/*  434 */     boolean truncateAlpha = false;
/*  435 */     if (!colorModel.hasAlpha() && ctwParam[15] == 1.0F)
/*  436 */       truncateAlpha = true; 
/*  441 */     double[][] T = (double[][])null;
/*  442 */     if (truncateAlpha && truncateChroma) {
/*  443 */       T = new double[][] { { 1.0D, 0.0D, 0.0D, 0.0D } };
/*  444 */     } else if (truncateChroma) {
/*  445 */       T = new double[][] { { 1.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*  448 */     } else if (truncateAlpha) {
/*  449 */       T = new double[][] { { 1.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 1.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 1.0D, 0.0D } };
/*      */     } else {
/*  454 */       T = new double[][] { { 1.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 1.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 1.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*      */     } 
/*  462 */     double[][] TH = matrixMultiply(T, H);
/*  463 */     double[][] THA = matrixMultiply(TH, A);
/*  464 */     double[][] THb = matrixMultiply(TH, b);
/*  465 */     double[][] THd = matrixMultiply(TH, d);
/*  466 */     double[][] Td = matrixMultiply(T, d);
/*  468 */     for (int i = 0; i < THb.length; i++) {
/*  469 */       for (int c = 0; c < (THb[i]).length; c++)
/*  470 */         THb[i][c] = THb[i][c] + Td[i][c] - THd[i][c]; 
/*      */     } 
/*  475 */     return composeMatrices(THA, THb);
/*      */   }
/*      */   
/*      */   private static final LookupTableJAI createContrastLUT(float K, int numBands) {
/*  484 */     byte[] contrastTable = new byte[256];
/*  486 */     double p = 0.4300000071525574D;
/*  489 */     for (int i = 0; i < 256; i++) {
/*  490 */       float j = (i - 127.5F) / 255.0F;
/*  491 */       float f = 0.0F;
/*  492 */       if (j < 0.0F) {
/*  493 */         f = (float)(-p * Math.pow(-j / p, K));
/*  494 */       } else if (j > 0.0F) {
/*  495 */         f = (float)(p * Math.pow(j / p, K));
/*      */       } 
/*  497 */       int val = (int)(f * 255.0F + 127.5F);
/*  498 */       if (val < 0) {
/*  499 */         contrastTable[i] = 0;
/*  500 */       } else if (val > 255) {
/*  501 */         contrastTable[i] = -1;
/*      */       } else {
/*  503 */         contrastTable[i] = (byte)(val & 0xFF);
/*      */       } 
/*      */     } 
/*  508 */     byte[][] data = new byte[numBands][];
/*  512 */     if (numBands % 2 == 1) {
/*  513 */       for (int j = 0; j < numBands; j++)
/*  514 */         data[j] = contrastTable; 
/*      */     } else {
/*  517 */       for (int j = 0; j < numBands - 1; j++)
/*  518 */         data[j] = contrastTable; 
/*  520 */       data[numBands - 1] = new byte[256];
/*  521 */       byte[] b = data[numBands - 1];
/*  522 */       for (int k = 0; k < 256; k++)
/*  523 */         b[k] = (byte)k; 
/*      */     } 
/*  527 */     return new LookupTableJAI(data);
/*      */   }
/*      */   
/*      */   public IIPCRIF() {
/*  532 */     super("IIP");
/*      */   }
/*      */   
/*      */   private RenderedImage serverProc(int serverMask, RenderContext renderContext, ParameterBlock paramBlock, int opMask, RenderedImage lowRes) {
/*      */     RenderedOp renderedOp;
/*  544 */     if ((serverMask & 0x15) != 21 && (serverMask & 0x2A) != 42 && (serverMask & 0x5) != 5 && (serverMask & 0xA) != 10)
/*  548 */       return null; 
/*  551 */     ImagingListener listener = ImageUtil.getImagingListener(renderContext);
/*  554 */     boolean isJPEG = false;
/*  555 */     boolean isFull = false;
/*  556 */     if ((serverMask & 0x15) == 21) {
/*  557 */       isJPEG = isFull = true;
/*  558 */     } else if ((serverMask & 0x2A) == 42) {
/*  559 */       isJPEG = false;
/*  560 */       isFull = true;
/*  561 */     } else if ((serverMask & 0x5) == 5) {
/*  562 */       isJPEG = true;
/*  563 */       isFull = false;
/*      */     } 
/*  567 */     StringBuffer buf = new StringBuffer((String)paramBlock.getObjectParameter(0));
/*  572 */     if ((opMask & 0x1) != 0)
/*  573 */       buf.append("&FTR=" + paramBlock.getFloatParameter(2)); 
/*  577 */     if ((opMask & 0x2) != 0) {
/*  578 */       buf.append("&CTW=");
/*  579 */       float[] ctw = (float[])paramBlock.getObjectParameter(3);
/*  580 */       for (int i = 0; i < ctw.length; i++) {
/*  581 */         buf.append(ctw[i]);
/*  582 */         if (i != ctw.length - 1)
/*  583 */           buf.append(","); 
/*      */       } 
/*      */     } 
/*  589 */     if ((opMask & 0x4) != 0)
/*  590 */       buf.append("&CNT=" + paramBlock.getFloatParameter(4)); 
/*  594 */     if ((opMask & 0x8) != 0) {
/*  595 */       Rectangle2D roi = (Rectangle2D)paramBlock.getObjectParameter(5);
/*  597 */       buf.append("&ROI=" + roi.getX() + "," + roi.getY() + "," + roi.getWidth() + "," + roi.getHeight());
/*      */     } 
/*  613 */     AffineTransform postTransform = new AffineTransform();
/*  616 */     AffineTransform at = (AffineTransform)renderContext.getTransform().clone();
/*  620 */     if (at.getTranslateX() != 0.0D || at.getTranslateY() != 0.0D) {
/*  621 */       postTransform.setToTranslation(at.getTranslateX(), at.getTranslateY());
/*  623 */       double[] m = new double[6];
/*  624 */       at.getMatrix(m);
/*  625 */       at.setTransform(m[0], m[1], m[2], m[3], 0.0D, 0.0D);
/*      */     } 
/*  629 */     Rectangle2D rgn = null;
/*  630 */     if ((opMask & 0x40) != 0) {
/*  631 */       rgn = (Rectangle2D)paramBlock.getObjectParameter(8);
/*      */     } else {
/*  633 */       float aspectRatio = 1.0F;
/*  634 */       if ((opMask & 0x20) != 0) {
/*  635 */         aspectRatio = paramBlock.getFloatParameter(7);
/*      */       } else {
/*  637 */         aspectRatio = ((Float)lowRes.getProperty("aspect-ratio")).floatValue();
/*      */       } 
/*  640 */       rgn = new Rectangle2D.Float(0.0F, 0.0F, aspectRatio, 1.0F);
/*      */     } 
/*  645 */     Rectangle dstROI = at.createTransformedShape(rgn).getBounds();
/*  649 */     AffineTransform scale = AffineTransform.getScaleInstance(dstROI.getWidth() / rgn.getWidth(), dstROI.getHeight() / rgn.getHeight());
/*      */     try {
/*  657 */       at.preConcatenate(scale.createInverse());
/*  658 */     } catch (Exception e) {
/*  659 */       String message = JaiI18N.getString("IIPCRIF6");
/*  660 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*      */     } 
/*  668 */     AffineTransform afn = (AffineTransform)paramBlock.getObjectParameter(6);
/*      */     try {
/*  671 */       afn.preConcatenate(at.createInverse());
/*  672 */     } catch (Exception e) {
/*  673 */       String message = JaiI18N.getString("IIPCRIF6");
/*  674 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*      */     } 
/*  680 */     if (isFull)
/*  683 */       buf.append("&WID=" + dstROI.width + "&HEI=" + dstROI.height); 
/*  720 */     double[] matrix = new double[6];
/*  721 */     afn.getMatrix(matrix);
/*  722 */     buf.append("&AFN=" + matrix[0] + "," + matrix[2] + ",0," + matrix[4] + "," + matrix[1] + "," + matrix[3] + ",0," + matrix[5] + ",0,0,1,0,0,0,0,1");
/*  728 */     if ((opMask & 0x20) != 0)
/*  729 */       buf.append("&RAR=" + paramBlock.getFloatParameter(7)); 
/*  733 */     if ((opMask & 0x40) != 0) {
/*  734 */       Rectangle2D dstRGN = (Rectangle2D)paramBlock.getObjectParameter(8);
/*  736 */       buf.append("&RGN=" + dstRGN.getX() + "," + dstRGN.getY() + "," + dstRGN.getWidth() + "," + dstRGN.getHeight());
/*      */     } 
/*  741 */     if (isFull && ((
/*  742 */       opMask & 0x80) != 0 || (opMask & 0x100) != 0)) {
/*  744 */       buf.append("&RFM=" + paramBlock.getIntParameter(9));
/*  745 */       if ((opMask & 0x100) != 0) {
/*  746 */         String axis = (String)paramBlock.getObjectParameter(10);
/*  747 */         if (axis.equalsIgnoreCase("x")) {
/*  748 */           buf.append(",0");
/*      */         } else {
/*  750 */           buf.append(",90");
/*      */         } 
/*      */       } 
/*      */     } 
/*  757 */     if ((opMask & 0x200) != 0);
/*  765 */     if (isJPEG) {
/*  766 */       if ((opMask & 0x400) != 0)
/*  767 */         buf.append("&QLT=" + paramBlock.getIntParameter(12)); 
/*  770 */       if ((opMask & 0x800) != 0)
/*  771 */         buf.append("&CIN=" + paramBlock.getIntParameter(13)); 
/*      */     } 
/*  776 */     String format = isJPEG ? "JPEG" : "FPX";
/*  779 */     buf.append("&CVT=" + format);
/*  783 */     InputStream stream = null;
/*  784 */     RenderedImage rendering = null;
/*      */     try {
/*  786 */       URL url = new URL(buf.toString());
/*  787 */       stream = url.openStream();
/*  788 */       MemoryCacheSeekableStream sStream = new MemoryCacheSeekableStream(stream);
/*  790 */       renderedOp = JAI.create(format, sStream);
/*  791 */     } catch (Exception e) {
/*  792 */       String message = JaiI18N.getString("IIPCRIF7") + " " + buf.toString();
/*  794 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*      */     } 
/*  801 */     if (!isFull)
/*  802 */       postTransform.scale(dstROI.getWidth() / renderedOp.getWidth(), dstROI.getHeight() / renderedOp.getHeight()); 
/*  807 */     if (!postTransform.isIdentity()) {
/*  808 */       Interpolation interp = Interpolation.getInstance(0);
/*  810 */       RenderingHints hints = renderContext.getRenderingHints();
/*  811 */       if (hints != null && hints.containsKey(JAI.KEY_INTERPOLATION))
/*  812 */         interp = (Interpolation)hints.get(JAI.KEY_INTERPOLATION); 
/*  814 */       renderedOp = JAI.create("affine", (RenderedImage)renderedOp, postTransform, interp);
/*      */     } 
/*  818 */     return (RenderedImage)renderedOp;
/*      */   }
/*      */   
/*      */   private RenderedImage clientProc(RenderContext renderContext, ParameterBlock paramBlock, int opMask, RenderedImage lowRes) {
/*      */     int height;
/*      */     RenderableImageOp renderableImageOp;
/*      */     RenderableOp renderableOp;
/*  829 */     AffineTransform at = renderContext.getTransform();
/*  830 */     RenderingHints hints = renderContext.getRenderingHints();
/*  832 */     ImagingListener listener = ImageUtil.getImagingListener(renderContext);
/*  835 */     int[] maxSize = (int[])lowRes.getProperty("max-size");
/*  836 */     int maxWidth = maxSize[0];
/*  837 */     int maxHeight = maxSize[1];
/*  838 */     int numLevels = ((Integer)lowRes.getProperty("resolution-number")).intValue();
/*  842 */     float aspectRatioSource = maxWidth / maxHeight;
/*  843 */     float aspectRatio = ((opMask & 0x20) != 0) ? paramBlock.getFloatParameter(7) : aspectRatioSource;
/*  847 */     Rectangle2D bounds2D = new Rectangle2D.Float(0.0F, 0.0F, aspectRatio, 1.0F);
/*  853 */     if (at.isIdentity()) {
/*  854 */       AffineTransform afn = (AffineTransform)paramBlock.getObjectParameter(6);
/*  856 */       Rectangle2D bounds = afn.createTransformedShape(bounds2D).getBounds2D();
/*  858 */       double H = maxHeight * bounds.getHeight();
/*  859 */       double W = maxHeight * bounds.getWidth();
/*  860 */       double m = Math.max(H, W / aspectRatioSource);
/*  861 */       height = (int)(m + 0.5D);
/*  862 */       int width = (int)(aspectRatioSource * m + 0.5D);
/*  863 */       at = AffineTransform.getScaleInstance(width, height);
/*  864 */       renderContext = (RenderContext)renderContext.clone();
/*  865 */       renderContext.setTransform(at);
/*      */     } else {
/*  867 */       Rectangle bounds = at.createTransformedShape(bounds2D).getBounds();
/*  868 */       int width = bounds.width;
/*  869 */       height = bounds.height;
/*      */     } 
/*  873 */     int res = numLevels - 1;
/*  874 */     int hRes = maxHeight;
/*  875 */     while (res > 0) {
/*  876 */       hRes = (int)((hRes + 1.0F) / 2.0F);
/*  877 */       if (hRes < height)
/*      */         break; 
/*  880 */       res--;
/*      */     } 
/*  884 */     int[] subImageArray = (int[])paramBlock.getObjectParameter(1);
/*  885 */     int subImage = (subImageArray.length < res + 1) ? 0 : subImageArray[res];
/*  886 */     if (subImage < 0)
/*  887 */       subImage = 0; 
/*  889 */     ParameterBlock pb = new ParameterBlock();
/*  890 */     pb.add(paramBlock.getObjectParameter(0)).add(res).add(subImage);
/*  891 */     RenderedOp renderedOp = JAI.create("iipresolution", pb);
/*  892 */     Vector sources = new Vector(1);
/*  893 */     sources.add(renderedOp);
/*  894 */     MultiResolutionRenderableImage multiResolutionRenderableImage = new MultiResolutionRenderableImage(sources, 0.0F, 0.0F, 1.0F);
/*  899 */     if ((opMask & 0x1) != 0) {
/*  900 */       float filter = paramBlock.getFloatParameter(2);
/*  901 */       pb = (new ParameterBlock()).addSource(multiResolutionRenderableImage).add(filter);
/*  902 */       renderableImageOp = new RenderableImageOp((ContextualRenderedImageFactory)new FilterCRIF(), pb);
/*      */     } 
/*  909 */     int nBands = renderedOp.getSampleModel().getNumBands();
/*  910 */     if ((opMask & 0x2) != 0) {
/*  911 */       double[][] ctw = getColorTwistMatrix(renderedOp.getColorModel(), paramBlock);
/*  913 */       pb = (new ParameterBlock()).addSource(renderableImageOp).add(ctw);
/*  914 */       renderableOp = JAI.createRenderable("bandcombine", pb);
/*  915 */       nBands = ctw.length;
/*      */     } 
/*  919 */     if ((opMask & 0x4) != 0) {
/*  920 */       int csType = renderedOp.getColorModel().getColorSpace().getType();
/*  921 */       boolean isPYCC = (csType != 6 && csType != 5);
/*  925 */       if (isPYCC) {
/*      */         double[][] matrix;
/*  927 */         if (nBands == 3) {
/*  928 */           matrix = composeMatrices(YCC_TO_RGB, YCC_TO_RGB_CONST);
/*      */         } else {
/*  930 */           matrix = composeMatrices(YCCA_TO_RGBA, YCCA_TO_RGBA_CONST);
/*      */         } 
/*  932 */         pb = (new ParameterBlock()).addSource(renderableOp).add(matrix);
/*  933 */         renderableOp = JAI.createRenderable("bandcombine", pb);
/*      */       } 
/*  936 */       float contrast = paramBlock.getFloatParameter(4);
/*  937 */       LookupTableJAI lut = createContrastLUT(contrast, nBands);
/*  939 */       pb = (new ParameterBlock()).addSource(renderableOp).add(lut);
/*  940 */       renderableOp = JAI.createRenderable("lookup", pb);
/*  942 */       if (isPYCC) {
/*      */         double[][] matrix;
/*  944 */         if (nBands == 3) {
/*  945 */           matrix = composeMatrices(RGB_TO_YCC, RGB_TO_YCC_CONST);
/*      */         } else {
/*  947 */           matrix = composeMatrices(RGBA_TO_YCCA, RGBA_TO_YCCA_CONST);
/*      */         } 
/*  949 */         pb = (new ParameterBlock()).addSource(renderableOp).add(matrix);
/*  950 */         renderableOp = JAI.createRenderable("bandcombine", pb);
/*      */       } 
/*      */     } 
/*  955 */     if ((opMask & 0x8) != 0) {
/*  957 */       Rectangle2D rect = (Rectangle2D)paramBlock.getObjectParameter(5);
/*  960 */       if (!rect.intersects(0.0D, 0.0D, aspectRatioSource, 1.0D))
/*  961 */         throw new RuntimeException(JaiI18N.getString("IIPCRIF5")); 
/*  965 */       Rectangle2D rectS = new Rectangle2D.Float(0.0F, 0.0F, aspectRatioSource, 1.0F);
/*  969 */       if (!rect.equals(rectS)) {
/*  971 */         rect = rect.createIntersection(rectS);
/*  974 */         pb = (new ParameterBlock()).addSource(renderableOp);
/*  975 */         pb.add((float)rect.getMinX()).add((float)rect.getMinY());
/*  976 */         pb.add((float)rect.getWidth()).add((float)rect.getHeight());
/*  977 */         renderableOp = JAI.createRenderable("crop", pb);
/*      */       } 
/*      */     } 
/*  990 */     if ((opMask & 0x10) != 0) {
/*  991 */       AffineTransform afn = (AffineTransform)paramBlock.getObjectParameter(6);
/*      */       try {
/*  995 */         afn = afn.createInverse();
/*  996 */       } catch (NoninvertibleTransformException e) {
/*  998 */         listener.errorOccurred(JaiI18N.getString("AffineNotInvertible"), e, this, false);
/*      */       } 
/* 1002 */       pb = (new ParameterBlock()).addSource(renderableOp).add(afn);
/* 1003 */       if (hints != null && hints.containsKey(JAI.KEY_INTERPOLATION))
/* 1004 */         pb.add(hints.get(JAI.KEY_INTERPOLATION)); 
/* 1006 */       renderableOp = JAI.createRenderable("affine", pb);
/*      */     } 
/* 1011 */     Rectangle2D rgn = ((opMask & 0x40) != 0) ? (Rectangle2D)paramBlock.getObjectParameter(8) : bounds2D;
/* 1015 */     if (rgn.isEmpty())
/* 1016 */       throw new RuntimeException(JaiI18N.getString("IIPCRIF3")); 
/* 1020 */     Rectangle2D riRect = new Rectangle2D.Float(renderableOp.getMinX(), renderableOp.getMinY(), renderableOp.getWidth(), renderableOp.getHeight());
/* 1027 */     if (!rgn.equals(riRect)) {
/* 1029 */       rgn = rgn.createIntersection(riRect);
/* 1032 */       pb = (new ParameterBlock()).addSource(renderableOp);
/* 1033 */       pb.add((float)rgn.getMinX()).add((float)rgn.getMinY());
/* 1034 */       pb.add((float)rgn.getWidth()).add((float)rgn.getHeight());
/* 1035 */       renderableOp = JAI.createRenderable("crop", pb);
/*      */     } 
/* 1039 */     return renderableOp.createRendering(renderContext);
/*      */   }
/*      */   
/*      */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 1048 */     RenderableOp renderableOp = JAI.createRenderable("iip", paramBlock);
/* 1050 */     return renderableOp.createDefaultRendering();
/*      */   }
/*      */   
/*      */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*      */     TiledImage tiledImage;
/*      */     RenderedOp renderedOp2;
/* 1061 */     int opMask = getOperationMask(paramBlock);
/* 1063 */     ImagingListener listener = ImageUtil.getImagingListener(renderContext);
/* 1066 */     ParameterBlock pb = new ParameterBlock();
/* 1067 */     int[] subImageArray = (int[])paramBlock.getObjectParameter(1);
/* 1068 */     pb.add(paramBlock.getObjectParameter(0)).add(0).add(subImageArray[0]);
/* 1069 */     RenderedOp renderedOp1 = JAI.create("iipresolution", pb);
/* 1072 */     int serverMask = getServerCapabilityMask((String)paramBlock.getObjectParameter(0), (RenderedImage)renderedOp1);
/* 1076 */     RenderedImage rendering = null;
/* 1079 */     if ((serverMask & 0x15) == 21 || (serverMask & 0x2A) == 42 || (serverMask & 0x5) == 5 || (serverMask & 0xA) == 10) {
/* 1084 */       rendering = serverProc(serverMask, renderContext, paramBlock, opMask, (RenderedImage)renderedOp1);
/*      */     } else {
/* 1088 */       rendering = clientProc(renderContext, paramBlock, opMask, (RenderedImage)renderedOp1);
/* 1092 */       if ((opMask & 0x8) != 0) {
/* 1094 */         Rectangle2D rgn = (Rectangle2D)paramBlock.getObjectParameter(5);
/* 1098 */         AffineTransform at = (AffineTransform)((AffineTransform)paramBlock.getObjectParameter(6)).clone();
/* 1102 */         if (!at.isIdentity())
/*      */           try {
/* 1104 */             at = at.createInverse();
/* 1105 */           } catch (Exception e) {
/* 1106 */             String message = JaiI18N.getString("IIPCRIF6");
/* 1107 */             listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*      */           }  
/* 1119 */         at.preConcatenate(renderContext.getTransform());
/* 1122 */         ROIShape roi = new ROIShape(at.createTransformedShape(rgn));
/* 1125 */         TiledImage ti = new TiledImage(rendering.getMinX(), rendering.getMinY(), rendering.getWidth(), rendering.getHeight(), rendering.getTileGridXOffset(), rendering.getTileGridYOffset(), rendering.getSampleModel(), rendering.getColorModel());
/* 1135 */         ti.set(rendering, (ROI)roi);
/* 1138 */         pb = new ParameterBlock();
/* 1139 */         pb.add(ti.getWidth());
/* 1140 */         pb.add(ti.getHeight());
/* 1141 */         Byte[] bandValues = new Byte[ti.getSampleModel().getNumBands()];
/* 1143 */         for (int b = 0; b < bandValues.length; b++)
/* 1144 */           bandValues[b] = new Byte((byte)-1); 
/* 1146 */         pb.add(bandValues);
/* 1148 */         ImageLayout il = new ImageLayout();
/* 1149 */         il.setSampleModel(ti.getSampleModel());
/* 1150 */         RenderingHints rh = new RenderingHints(JAI.KEY_IMAGE_LAYOUT, il);
/* 1153 */         RenderedOp renderedOp = JAI.create("constant", pb, rh);
/* 1156 */         ROI complementROI = (new ROIShape(ti.getBounds())).subtract((ROI)roi);
/* 1160 */         int maxTileY = ti.getMaxTileY();
/* 1161 */         int maxTileX = ti.getMaxTileX();
/* 1162 */         for (int j = ti.getMinTileY(); j <= maxTileY; j++) {
/* 1163 */           for (int i = ti.getMinTileX(); i <= maxTileX; i++) {
/* 1164 */             if (!roi.intersects(ti.getTileRect(i, j)))
/* 1165 */               ti.setData(renderedOp.getTile(i, j), complementROI); 
/*      */           } 
/*      */         } 
/* 1172 */         tiledImage = ti;
/*      */       } 
/*      */     } 
/* 1180 */     if ((serverMask & 0x15) != 21 && (serverMask & 0x2A) != 42) {
/* 1182 */       if ((opMask & 0x80) != 0) {
/*      */         TransposeType transposeType1;
/* 1185 */         EnumeratedParameter transposeType = null;
/* 1186 */         switch (paramBlock.getIntParameter(9)) {
/*      */           case 90:
/* 1188 */             transposeType1 = TransposeDescriptor.ROTATE_270;
/*      */             break;
/*      */           case 180:
/* 1191 */             transposeType1 = TransposeDescriptor.ROTATE_180;
/*      */             break;
/*      */           case 270:
/* 1194 */             transposeType1 = TransposeDescriptor.ROTATE_90;
/*      */             break;
/*      */         } 
/* 1197 */         if (transposeType1 != null)
/* 1198 */           renderedOp2 = JAI.create("transpose", (RenderedImage)tiledImage, transposeType1); 
/*      */       } 
/* 1203 */       if ((opMask & 0x100) != 0) {
/* 1204 */         String axis = (String)paramBlock.getObjectParameter(10);
/* 1205 */         TransposeType transposeType = axis.equalsIgnoreCase("x") ? TransposeDescriptor.FLIP_VERTICAL : TransposeDescriptor.FLIP_HORIZONTAL;
/* 1209 */         renderedOp2 = JAI.create("transpose", (RenderedImage)renderedOp2, transposeType);
/*      */       } 
/*      */     } 
/* 1213 */     return (RenderedImage)renderedOp2;
/*      */   }
/*      */   
/*      */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/*      */     float aspectRatioDestination;
/* 1222 */     int opMask = getOperationMask(paramBlock);
/* 1224 */     if ((opMask & 0x40) != 0)
/* 1225 */       return (Rectangle2D)paramBlock.getObjectParameter(8); 
/* 1229 */     if ((opMask & 0x20) != 0) {
/* 1230 */       aspectRatioDestination = paramBlock.getFloatParameter(7);
/*      */     } else {
/* 1233 */       ParameterBlock pb = new ParameterBlock();
/* 1234 */       int[] subImageArray = (int[])paramBlock.getObjectParameter(1);
/* 1235 */       pb.add(paramBlock.getObjectParameter(0));
/* 1236 */       pb.add(0).add(subImageArray[0]);
/* 1237 */       RenderedOp renderedOp = JAI.create("iipresolution", pb);
/* 1239 */       int[] maxSize = (int[])renderedOp.getProperty("max-size");
/* 1241 */       aspectRatioDestination = maxSize[0] / maxSize[1];
/*      */     } 
/* 1244 */     return new Rectangle2D.Float(0.0F, 0.0F, aspectRatioDestination, 1.0F);
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 1248 */     int nr = 0;
/* 1249 */     int nc = 0;
/* 1251 */     double[][] x = matrixMultiply(RGBA_TO_YCCA, YCCA_TO_RGBA);
/* 1252 */     nr = x.length;
/* 1253 */     nc = (x[0]).length;
/*      */     int r;
/* 1254 */     for (r = 0; r < nr; r++) {
/* 1255 */       for (int c = 0; c < nc; c++)
/* 1256 */         System.out.print(x[r][c] + " "); 
/* 1258 */       System.out.println("");
/*      */     } 
/* 1260 */     System.out.println("");
/* 1262 */     x = matrixMultiply(RGB_TO_YCC, YCC_TO_RGB);
/* 1263 */     nr = x.length;
/* 1264 */     nc = (x[0]).length;
/* 1265 */     for (r = 0; r < nr; r++) {
/* 1266 */       for (int c = 0; c < nc; c++)
/* 1267 */         System.out.print(x[r][c] + " "); 
/* 1269 */       System.out.println("");
/*      */     } 
/* 1271 */     System.out.println("");
/* 1273 */     double[][] b = { { 1.0D }, { 2.0D }, { 3.0D }, { 4.0D } };
/* 1274 */     double[][] A = composeMatrices(YCCA_TO_RGBA, b);
/* 1275 */     nr = A.length;
/* 1276 */     nc = (A[0]).length;
/* 1277 */     for (int i = 0; i < nr; i++) {
/* 1278 */       for (int c = 0; c < nc; c++)
/* 1279 */         System.out.print(A[i][c] + " "); 
/* 1281 */       System.out.println("");
/*      */     } 
/* 1283 */     System.out.println("");
/* 1285 */     double[][] d4 = matrixMultiply(RGBA_TO_YCCA, YCCA_TO_RGBA_CONST);
/* 1286 */     nr = d4.length;
/* 1287 */     nc = (d4[0]).length;
/* 1288 */     for (int j = 0; j < nr; j++) {
/* 1289 */       for (int c = 0; c < nc; c++)
/* 1290 */         System.out.print(-d4[j][c] + " "); 
/* 1292 */       System.out.println("");
/*      */     } 
/* 1294 */     System.out.println("");
/* 1296 */     double[][] d3 = matrixMultiply(RGB_TO_YCC, YCC_TO_RGB_CONST);
/* 1297 */     nr = d3.length;
/* 1298 */     nc = (d3[0]).length;
/* 1299 */     for (int k = 0; k < nr; k++) {
/* 1300 */       for (int c = 0; c < nc; c++)
/* 1301 */         System.out.print(-d3[k][c] + " "); 
/* 1303 */       System.out.println("");
/*      */     } 
/* 1305 */     System.out.println("");
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\IIPCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */