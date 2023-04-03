/*      */ package com.sun.media.jai.opimage;
/*      */ 
/*      */ import com.sun.image.codec.jpeg.JPEGCodec;
/*      */ import com.sun.image.codec.jpeg.JPEGDecodeParam;
/*      */ import com.sun.image.codec.jpeg.JPEGImageDecoder;
/*      */ import com.sun.media.jai.util.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.Raster;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.InputStream;
/*      */ import java.net.URL;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.media.jai.ImageLayout;
/*      */ import javax.media.jai.JAI;
/*      */ import javax.media.jai.OpImage;
/*      */ import javax.media.jai.RasterFactory;
/*      */ import javax.media.jai.util.ImagingException;
/*      */ import javax.media.jai.util.ImagingListener;
/*      */ 
/*      */ public class IIPResolutionOpImage extends OpImage {
/*      */   private static final int TILE_SIZE = 64;
/*      */   
/*      */   private static final int TILE_BLOCK_WIDTH = 8;
/*      */   
/*      */   private static final int TILE_BLOCK_HEIGHT = 2;
/*      */   
/*      */   private static final char BLANK = ' ';
/*      */   
/*      */   private static final char COLON = ':';
/*      */   
/*      */   private static final char SLASH = '/';
/*      */   
/*      */   private static final char CR = '\r';
/*      */   
/*      */   private static final char LF = '\n';
/*      */   
/*      */   private static final int CS_COLORLESS = 0;
/*      */   
/*      */   private static final int CS_MONOCHROME = 1;
/*      */   
/*      */   private static final int CS_PHOTOYCC = 2;
/*      */   
/*      */   private static final int CS_NIFRGB = 3;
/*      */   
/*      */   private static final int CS_PLANE_ALPHA = 32766;
/*      */   
/*      */   private static final int TILE_UNCOMPRESSED = 0;
/*      */   
/*      */   private static final int TILE_SINGLE_COLOR = 1;
/*      */   
/*      */   private static final int TILE_JPEG = 2;
/*      */   
/*      */   private static final int TILE_INVALID = -1;
/*      */   
/*   80 */   private static ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/*      */   
/*      */   private String URLString;
/*      */   
/*      */   private int resolution;
/*      */   
/*      */   private int subImage;
/*      */   
/*      */   private int colorSpaceType;
/*      */   
/*      */   private boolean hasAlpha;
/*      */   
/*      */   private boolean isAlphaPremultilpied;
/*      */   
/*      */   private int minTileX;
/*      */   
/*      */   private int minTileY;
/*      */   
/*      */   private int numXTiles;
/*      */   
/*  111 */   private JPEGDecodeParam[] decodeParamCache = new JPEGDecodeParam[255];
/*      */   
/*      */   private boolean arePropertiesInitialized = false;
/*      */   
/*  117 */   private int tileBlockWidth = 8;
/*      */   
/*  118 */   private int tileBlockHeight = 2;
/*      */   
/*      */   private RenderingHints renderHints;
/*      */   
/*      */   private static final void YCbCrToNIFRGB(Raster raster) {
/*  127 */     byte[] data = ((DataBufferByte)raster.getDataBuffer()).getData();
/*  129 */     int offset = 0;
/*  130 */     int length = data.length;
/*  131 */     int MASK1 = 255;
/*  132 */     int MASK2 = 65280;
/*  133 */     if (raster.getSampleModel().getNumBands() == 3) {
/*  134 */       while (offset < length) {
/*  135 */         float Y = (data[offset] & 0xFF);
/*  136 */         float Cb = (data[offset + 1] & 0xFF);
/*  137 */         float Cr = (data[offset + 2] & 0xFF);
/*  139 */         int R = (int)(Y + 1.402F * Cr - 178.255F);
/*  140 */         int G = (int)(Y - 0.34414F * Cb - 0.71414F * Cr + 135.4307F);
/*  141 */         int B = (int)(Y + 1.772F * Cb - 225.43F);
/*  143 */         int imask = R >> 5 & 0x18;
/*  144 */         data[offset++] = (byte)((R & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*  147 */         imask = G >> 5 & 0x18;
/*  148 */         data[offset++] = (byte)((G & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*  151 */         imask = B >> 5 & 0x18;
/*  152 */         data[offset++] = (byte)((B & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*      */       } 
/*      */     } else {
/*  156 */       while (offset < length) {
/*  157 */         float Y = (data[offset] & 0xFF);
/*  158 */         float Cb = (data[offset + 1] & 0xFF);
/*  159 */         float Cr = (data[offset + 2] & 0xFF);
/*  161 */         int R = (int)(-Y - 1.402F * Cr - 433.255F);
/*  162 */         int G = (int)(-Y + 0.34414F * Cb + 0.71414F * Cr + 119.5693F);
/*  163 */         int B = (int)(-Y - 1.772F * Cb - 480.43F);
/*  165 */         int imask = R >> 5 & 0x18;
/*  166 */         data[offset++] = (byte)((R & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*  169 */         imask = G >> 5 & 0x18;
/*  170 */         data[offset++] = (byte)((G & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*  173 */         imask = B >> 5 & 0x18;
/*  174 */         data[offset++] = (byte)((B & MASK1 >> imask | MASK2 >> imask) & 0xFF);
/*  177 */         offset++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static InputStream postCommands(String URLSpec, String[] commands) {
/*  190 */     StringBuffer spec = new StringBuffer(URLSpec + "&OBJ=iip,1.0");
/*  192 */     if (commands != null)
/*  194 */       for (int i = 0; i < commands.length; i++)
/*  195 */         spec.append("&" + commands[i]);  
/*  200 */     InputStream stream = null;
/*      */     try {
/*  202 */       URL url = new URL(spec.toString());
/*  203 */       stream = url.openStream();
/*  204 */     } catch (Exception e) {
/*  205 */       String message = JaiI18N.getString("IIPResolution4") + spec.toString();
/*  207 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */     } 
/*  212 */     return stream;
/*      */   }
/*      */   
/*      */   private static String getLabel(InputStream stream) {
/*  223 */     boolean charsAppended = false;
/*  224 */     StringBuffer buf = new StringBuffer(16);
/*      */     try {
/*      */       int i;
/*  227 */       while ((i = stream.read()) != -1) {
/*  228 */         char c = (char)(0xFF & i);
/*  229 */         if (c == '/' || c == ':')
/*      */           break; 
/*  232 */         buf.append(c);
/*  233 */         charsAppended = true;
/*      */       } 
/*  235 */     } catch (Exception e) {
/*  236 */       String message = JaiI18N.getString("IIPResolution5");
/*  238 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */     } 
/*  243 */     return charsAppended ? buf.toString().toLowerCase() : null;
/*      */   }
/*      */   
/*      */   private static int getLength(InputStream stream) {
/*  252 */     return Integer.valueOf(getLabel(stream)).intValue();
/*      */   }
/*      */   
/*      */   private static InputStream checkError(String label, InputStream stream, boolean throwException) {
/*  263 */     if (label.equals("error")) {
/*  264 */       int length = Integer.valueOf(getLabel(stream)).intValue();
/*  265 */       byte[] b = new byte[length];
/*      */       try {
/*  267 */         stream.read(b);
/*  268 */       } catch (Exception e) {
/*  269 */         String message = JaiI18N.getString("IIPResolution6");
/*  271 */         listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */       } 
/*  275 */       String msg = new String(b);
/*  276 */       if (throwException) {
/*  277 */         throwIIPException(msg);
/*      */       } else {
/*  279 */         printIIPException(msg);
/*      */       } 
/*  281 */     } else if (label.startsWith("iip")) {
/*  283 */       String iipObjectResponse = getDataAsString(stream, false);
/*      */     } 
/*  286 */     return stream;
/*      */   }
/*      */   
/*      */   private static byte[] getDataAsByteArray(InputStream stream) {
/*  295 */     int length = getLength(stream);
/*  296 */     byte[] b = new byte[length];
/*      */     try {
/*  299 */       stream.read(b);
/*  300 */       stream.read();
/*  301 */       stream.read();
/*  302 */     } catch (Exception e) {
/*  303 */       String message = JaiI18N.getString("IIPResolution7");
/*  304 */       listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */     } 
/*  309 */     return b;
/*      */   }
/*      */   
/*      */   private static String getDataAsString(InputStream stream, boolean hasLength) {
/*  317 */     String str = null;
/*  318 */     if (hasLength) {
/*      */       try {
/*  320 */         int length = getLength(stream);
/*  321 */         byte[] b = new byte[length];
/*  322 */         stream.read(b);
/*  323 */         stream.read();
/*  324 */         stream.read();
/*  325 */         str = new String(b);
/*  326 */       } catch (Exception e) {
/*  327 */         String message = JaiI18N.getString("IIPResolution7");
/*  328 */         listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */       } 
/*      */     } else {
/*  333 */       StringBuffer buf = new StringBuffer(16);
/*      */       try {
/*      */         int i;
/*  336 */         while ((i = stream.read()) != -1) {
/*  337 */           char c = (char)(0xFF & i);
/*  338 */           if (c == '\r') {
/*  339 */             stream.read();
/*      */             break;
/*      */           } 
/*  342 */           buf.append(c);
/*      */         } 
/*  344 */         str = buf.toString();
/*  345 */       } catch (Exception e) {
/*  346 */         String message = JaiI18N.getString("IIPResolution7");
/*  347 */         listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */       } 
/*      */     } 
/*  353 */     return str;
/*      */   }
/*      */   
/*      */   private static void flushData(InputStream stream, boolean hasLength) {
/*  361 */     if (hasLength) {
/*      */       try {
/*  363 */         int length = getLength(stream);
/*  364 */         long numSkipped = stream.skip(length);
/*  365 */         if (numSkipped == length) {
/*  366 */           stream.read();
/*  367 */           stream.read();
/*      */         } 
/*  369 */       } catch (Exception e) {
/*  370 */         String message = JaiI18N.getString("IIPResolution8");
/*  371 */         listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */       } 
/*      */     } else {
/*      */       try {
/*      */         int i;
/*  378 */         while ((i = stream.read()) != -1) {
/*  379 */           if ((char)(0xFF & i) == '\r') {
/*  380 */             stream.read();
/*      */             break;
/*      */           } 
/*      */         } 
/*  384 */       } catch (Exception e) {
/*  385 */         String message = JaiI18N.getString("IIPResolution8");
/*  386 */         listener.errorOccurred(message, (Throwable)new ImagingException(message, e), IIPResolutionOpImage.class, false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int[] stringToIntArray(String s) {
/*  398 */     Vector v = new Vector();
/*  399 */     int lastBlank = 0;
/*  400 */     int nextBlank = s.indexOf(' ', 0);
/*      */     while (true) {
/*  402 */       v.add(Integer.valueOf(s.substring(lastBlank, nextBlank)));
/*  403 */       lastBlank = nextBlank + 1;
/*  404 */       nextBlank = s.indexOf(' ', lastBlank);
/*  405 */       if (nextBlank == -1) {
/*  406 */         v.add(Integer.valueOf(s.substring(lastBlank)));
/*  409 */         int length = v.size();
/*  410 */         int[] intArray = new int[length];
/*  411 */         for (int i = 0; i < length; i++)
/*  412 */           intArray[i] = ((Integer)v.get(i)).intValue(); 
/*  415 */         return intArray;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static float[] stringToFloatArray(String s) {
/*  423 */     Vector v = new Vector();
/*  424 */     int lastBlank = 0;
/*  425 */     int nextBlank = s.indexOf(' ', 0);
/*      */     while (true) {
/*  427 */       v.add(Float.valueOf(s.substring(lastBlank, nextBlank)));
/*  428 */       lastBlank = nextBlank + 1;
/*  429 */       nextBlank = s.indexOf(' ', lastBlank);
/*  430 */       if (nextBlank == -1) {
/*  431 */         v.add(Float.valueOf(s.substring(lastBlank)));
/*  434 */         int length = v.size();
/*  435 */         float[] floatArray = new float[length];
/*  436 */         for (int i = 0; i < length; i++)
/*  437 */           floatArray[i] = ((Float)v.get(i)).floatValue(); 
/*  440 */         return floatArray;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String formatIIPErrorMessage(String msg) {
/*  447 */     return new String(JaiI18N.getString("IIPResolutionOpImage0") + " " + msg);
/*      */   }
/*      */   
/*      */   private static void throwIIPException(String msg) {
/*  454 */     throw new RuntimeException(formatIIPErrorMessage(msg));
/*      */   }
/*      */   
/*      */   private static void printIIPException(String msg) {
/*  461 */     System.err.println(formatIIPErrorMessage(msg));
/*      */   }
/*      */   
/*      */   private static void closeStream(InputStream stream) {
/*      */     try {
/*  469 */       stream.close();
/*  470 */     } catch (Exception e) {}
/*      */   }
/*      */   
/*      */   private static ImageLayout layoutHelper(String URLSpec, int level, int subImage) {
/*  483 */     ImageLayout il = new ImageLayout();
/*  486 */     il.setTileGridXOffset(0);
/*  487 */     il.setTileGridYOffset(0);
/*  490 */     il.setTileWidth(64);
/*  491 */     il.setTileHeight(64);
/*  494 */     il.setMinX(0);
/*  495 */     il.setMinY(0);
/*  499 */     int maxWidth = -1;
/*  500 */     int maxHeight = -1;
/*  501 */     int numRes = -1;
/*  502 */     int resolution = -1;
/*  503 */     String[] cmd = { "OBJ=Max-size", "OBJ=Resolution-number" };
/*  504 */     InputStream stream = postCommands(URLSpec, cmd);
/*  505 */     String label = null;
/*  506 */     while ((label = getLabel(stream)) != null) {
/*  507 */       if (label.equals("max-size")) {
/*  508 */         String data = getDataAsString(stream, false);
/*  509 */         int[] wh = stringToIntArray(data);
/*  510 */         maxWidth = wh[0];
/*  511 */         maxHeight = wh[1];
/*      */         continue;
/*      */       } 
/*  512 */       if (label.equals("resolution-number")) {
/*  513 */         String data = getDataAsString(stream, false);
/*  514 */         numRes = Integer.valueOf(data).intValue();
/*  515 */         if (level < 0) {
/*  516 */           resolution = 0;
/*      */           continue;
/*      */         } 
/*  517 */         if (level >= numRes) {
/*  518 */           resolution = numRes - 1;
/*      */           continue;
/*      */         } 
/*  520 */         resolution = level;
/*      */         continue;
/*      */       } 
/*  523 */       checkError(label, stream, true);
/*      */     } 
/*  526 */     closeStream(stream);
/*  529 */     int w = maxWidth;
/*  530 */     int h = maxHeight;
/*  531 */     for (int i = numRes - 1; i > resolution; i--) {
/*  532 */       w = (w + 1) / 2;
/*  533 */       h = (h + 1) / 2;
/*      */     } 
/*  535 */     il.setWidth(w);
/*  536 */     il.setHeight(h);
/*  539 */     boolean hasAlpha = false;
/*  540 */     boolean isAlphaPremultiplied = false;
/*  541 */     cmd = new String[] { "OBJ=Colorspace," + resolution + "," + subImage };
/*  542 */     stream = postCommands(URLSpec, cmd);
/*  543 */     int colorSpaceIndex = 0;
/*  544 */     int numBands = 0;
/*  545 */     while ((label = getLabel(stream)) != null) {
/*  546 */       if (label.startsWith("colorspace")) {
/*  547 */         int[] ia = stringToIntArray(getDataAsString(stream, false));
/*  548 */         numBands = ia[3];
/*  549 */         switch (ia[2]) {
/*      */           case 1:
/*  551 */             colorSpaceIndex = 1003;
/*      */             break;
/*      */           case 2:
/*  554 */             colorSpaceIndex = 1002;
/*      */             break;
/*      */           case 3:
/*  557 */             colorSpaceIndex = 1000;
/*      */             break;
/*      */           default:
/*  560 */             colorSpaceIndex = (numBands < 3) ? 1003 : 1000;
/*      */             break;
/*      */         } 
/*  563 */         for (int m = 1; m <= numBands; m++) {
/*  564 */           if (ia[3 + m] == 32766)
/*  565 */             hasAlpha = true; 
/*      */         } 
/*  568 */         isAlphaPremultiplied = (ia[1] == 1);
/*      */         continue;
/*      */       } 
/*  570 */       checkError(label, stream, true);
/*      */     } 
/*  573 */     closeStream(stream);
/*  576 */     ColorSpace cs = ColorSpace.getInstance(colorSpaceIndex);
/*  577 */     int dtSize = DataBuffer.getDataTypeSize(0);
/*  578 */     int[] bits = new int[numBands];
/*  579 */     for (int j = 0; j < numBands; j++)
/*  580 */       bits[j] = dtSize; 
/*  582 */     int transparency = hasAlpha ? 3 : 1;
/*  584 */     ColorModel cm = new ComponentColorModel(cs, bits, hasAlpha, isAlphaPremultiplied, transparency, 0);
/*  588 */     il.setColorModel(cm);
/*  591 */     int[] bandOffsets = new int[numBands];
/*  592 */     for (int k = 0; k < numBands; k++)
/*  593 */       bandOffsets[k] = k; 
/*  595 */     il.setSampleModel(RasterFactory.createPixelInterleavedSampleModel(0, 64, 64, numBands, numBands * 64, bandOffsets));
/*  603 */     return il;
/*      */   }
/*      */   
/*      */   public IIPResolutionOpImage(Map config, String URLSpec, int level, int subImage) {
/*  621 */     super((Vector)null, layoutHelper(URLSpec, level, subImage), config, false);
/*  626 */     this.renderHints = (RenderingHints)config;
/*  629 */     this.URLString = URLSpec;
/*  630 */     this.subImage = subImage;
/*  633 */     String[] cmd = { "OBJ=Resolution-number" };
/*  634 */     InputStream stream = postCommands(cmd);
/*  635 */     String label = null;
/*  636 */     while ((label = getLabel(stream)) != null) {
/*  637 */       if (label.equals("resolution-number")) {
/*  638 */         String data = getDataAsString(stream, false);
/*  639 */         int numRes = Integer.valueOf(data).intValue();
/*  640 */         if (level < 0) {
/*  641 */           this.resolution = 0;
/*      */           continue;
/*      */         } 
/*  642 */         if (level >= numRes) {
/*  643 */           this.resolution = numRes - 1;
/*      */           continue;
/*      */         } 
/*  645 */         this.resolution = level;
/*      */         continue;
/*      */       } 
/*  648 */       checkError(label, stream, true);
/*      */     } 
/*  651 */     endResponse(stream);
/*  654 */     ColorSpace cs = this.colorModel.getColorSpace();
/*  655 */     if (cs.isCS_sRGB()) {
/*  656 */       this.colorSpaceType = 3;
/*  657 */     } else if (cs.equals(ColorSpace.getInstance(1003))) {
/*  658 */       this.colorSpaceType = 1;
/*      */     } else {
/*  660 */       this.colorSpaceType = 2;
/*      */     } 
/*  662 */     this.hasAlpha = this.colorModel.hasAlpha();
/*  663 */     this.isAlphaPremultilpied = this.colorModel.isAlphaPremultiplied();
/*  664 */     this.minTileX = getMinTileX();
/*  665 */     this.minTileY = getMinTileY();
/*  666 */     this.numXTiles = getNumXTiles();
/*      */   }
/*      */   
/*      */   private InputStream postCommands(String[] commands) {
/*  673 */     return postCommands(this.URLString, commands);
/*      */   }
/*      */   
/*      */   private void endResponse(InputStream stream) {
/*  681 */     closeStream(stream);
/*      */   }
/*      */   
/*      */   public Raster computeTile(int tileX, int tileY) {
/*  692 */     Raster raster = null;
/*  697 */     if ((tileX - this.minTileX) % this.tileBlockWidth == 0 && (tileY - this.minTileY) % this.tileBlockHeight == 0) {
/*  699 */       int endTileX = tileX + this.tileBlockWidth - 1;
/*  700 */       if (endTileX > getMaxTileX())
/*  701 */         endTileX = getMaxTileX(); 
/*  703 */       int endTileY = tileY + this.tileBlockHeight - 1;
/*  704 */       if (endTileY > getMaxTileY())
/*  705 */         endTileY = getMaxTileY(); 
/*  707 */       raster = getTileBlock(tileX, tileY, endTileX, endTileY);
/*  708 */     } else if ((raster = getTileFromCache(tileX, tileY)) == null) {
/*  709 */       raster = getTileBlock(tileX, tileY, tileX, tileY);
/*      */     } 
/*  712 */     return raster;
/*      */   }
/*      */   
/*      */   private Point getTileXY(String label, Point xy) {
/*  724 */     int beginIndex = label.indexOf(",", label.indexOf(",") + 1) + 1;
/*  725 */     int endIndex = label.lastIndexOf(",");
/*  726 */     int tile = Integer.valueOf(label.substring(beginIndex, endIndex)).intValue();
/*  730 */     int tileX = (tile + this.minTileX) % this.numXTiles;
/*  731 */     int tileY = (tile + this.minTileX - tileX) / this.numXTiles + this.minTileY;
/*  734 */     if (xy == null) {
/*  735 */       xy = new Point(tileX, tileY);
/*      */     } else {
/*  737 */       xy.setLocation(tileX, tileY);
/*      */     } 
/*  740 */     return xy;
/*      */   }
/*      */   
/*      */   private Raster getTileBlock(int upperLeftTileX, int upperLeftTileY, int lowerRightTileX, int lowerRightTileY) {
/*  750 */     int startTile = (upperLeftTileY - this.minTileY) * this.numXTiles + upperLeftTileX - this.minTileX;
/*  752 */     int endTile = (lowerRightTileY - this.minTileY) * this.numXTiles + lowerRightTileX - this.minTileX;
/*  755 */     String cmd = null;
/*  756 */     if (startTile == endTile) {
/*  757 */       cmd = new String("til=" + this.resolution + "," + startTile + "," + this.subImage);
/*      */     } else {
/*  759 */       cmd = new String("til=" + this.resolution + "," + startTile + "-" + endTile + "," + this.subImage);
/*      */     } 
/*  762 */     InputStream stream = postCommands(new String[] { cmd });
/*  763 */     int compressionType = -1;
/*  764 */     int compressionSubType = -1;
/*  765 */     byte[] data = null;
/*  766 */     String label = null;
/*  767 */     Raster upperLeftTile = null;
/*  768 */     Point tileXY = new Point();
/*  769 */     while ((label = getLabel(stream)) != null) {
/*  770 */       if (label.startsWith("tile")) {
/*  771 */         int length = getLength(stream);
/*  773 */         byte[] header = new byte[8];
/*      */         try {
/*  775 */           stream.read(header);
/*  776 */         } catch (Exception e) {
/*  777 */           throwIIPException(JaiI18N.getString("IIPResolutionOpImage1"));
/*      */         } 
/*  780 */         length -= 8;
/*  782 */         compressionType = header[3] << 24 | header[2] << 16 | header[1] << 8 | header[0];
/*  787 */         compressionSubType = header[7] << 24 | header[6] << 16 | header[5] << 8 | header[4];
/*  793 */         if (length != 0) {
/*  794 */           data = new byte[length];
/*      */           try {
/*  796 */             int numBytesRead = 0;
/*  797 */             int offset = 0;
/*      */             do {
/*  799 */               numBytesRead = stream.read(data, offset, length - offset);
/*  801 */               offset += numBytesRead;
/*  802 */             } while (offset < length && numBytesRead != -1);
/*  803 */             if (numBytesRead != -1) {
/*  804 */               stream.read();
/*  805 */               stream.read();
/*      */             } 
/*  807 */           } catch (Exception e) {
/*  808 */             throwIIPException(JaiI18N.getString("IIPResolutionOpImage2"));
/*      */           } 
/*      */         } 
/*  812 */         getTileXY(label, tileXY);
/*  813 */         int tileX = (int)tileXY.getX();
/*  814 */         int tileY = (int)tileXY.getY();
/*  815 */         int tx = tileXToX(tileX);
/*  816 */         int ty = tileYToY(tileY);
/*  818 */         Raster raster = null;
/*  820 */         switch (compressionType) {
/*      */           case 0:
/*  822 */             raster = getUncompressedTile(tx, ty, data);
/*      */             break;
/*      */           case 1:
/*  825 */             raster = getSingleColorTile(tx, ty, compressionSubType);
/*      */             break;
/*      */           case 2:
/*  828 */             raster = getJPEGTile(tx, ty, compressionSubType, data);
/*      */             break;
/*      */           default:
/*  832 */             raster = createWritableRaster(this.sampleModel, new Point(tx, ty));
/*      */             break;
/*      */         } 
/*  837 */         if (tileX == upperLeftTileX && tileY == upperLeftTileY) {
/*  838 */           upperLeftTile = raster;
/*      */           continue;
/*      */         } 
/*  841 */         addTileToCache(tileX, tileY, raster);
/*      */         continue;
/*      */       } 
/*  844 */       checkError(label, stream, true);
/*      */     } 
/*  848 */     endResponse(stream);
/*  850 */     return upperLeftTile;
/*      */   }
/*      */   
/*      */   private Raster getUncompressedTile(int tx, int ty, byte[] data) {
/*  857 */     DataBuffer dataBuffer = new DataBufferByte(data, data.length);
/*  859 */     return Raster.createRaster(this.sampleModel, dataBuffer, new Point(tx, ty));
/*      */   }
/*      */   
/*      */   private Raster getSingleColorTile(int tx, int ty, int color) {
/*  866 */     byte R = (byte)(color & 0xFF);
/*  867 */     byte G = (byte)(color >> 8 & 0xFF);
/*  868 */     byte B = (byte)(color >> 16 & 0xFF);
/*  869 */     byte A = (byte)(color >> 24 & 0xFF);
/*  871 */     int numBands = this.sampleModel.getNumBands();
/*  872 */     int length = this.tileWidth * this.tileHeight * numBands;
/*  873 */     byte[] data = new byte[length];
/*  874 */     int i = 0;
/*  875 */     switch (numBands) {
/*      */       case 1:
/*  877 */         while (i < length)
/*  878 */           data[i++] = R; 
/*  903 */         dataBuffer = new DataBufferByte(data, data.length);
/*  905 */         return Raster.createRaster(this.sampleModel, dataBuffer, new Point(tx, ty));
/*      */       case 2:
/*      */         while (i < length) {
/*      */           data[i++] = R;
/*      */           data[i++] = A;
/*      */         } 
/*      */         dataBuffer = new DataBufferByte(data, data.length);
/*  905 */         return Raster.createRaster(this.sampleModel, dataBuffer, new Point(tx, ty));
/*      */       case 3:
/*      */         while (i < length) {
/*      */           data[i++] = R;
/*      */           data[i++] = G;
/*      */           data[i++] = B;
/*      */         } 
/*      */         break;
/*      */     } 
/*      */     while (i < length) {
/*      */       data[i++] = R;
/*      */       data[i++] = G;
/*      */       data[i++] = B;
/*      */       data[i++] = A;
/*      */     } 
/*      */     DataBuffer dataBuffer = new DataBufferByte(data, data.length);
/*  905 */     return Raster.createRaster(this.sampleModel, dataBuffer, new Point(tx, ty));
/*      */   }
/*      */   
/*      */   private Raster getJPEGTile(int tx, int ty, int subType, byte[] data) {
/*  912 */     int tableIndex = subType >> 24 & 0xFF;
/*  913 */     boolean colorConversion = ((subType & 0xFF0000) != 0);
/*  914 */     JPEGDecodeParam decodeParam = null;
/*  915 */     if (tableIndex != 0)
/*  916 */       decodeParam = getJPEGDecodeParam(tableIndex); 
/*  919 */     ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
/*  920 */     JPEGImageDecoder decoder = (decodeParam == null) ? JPEGCodec.createJPEGDecoder(byteStream) : JPEGCodec.createJPEGDecoder(byteStream, decodeParam);
/*  924 */     Raster raster = null;
/*      */     try {
/*  926 */       raster = decoder.decodeAsRaster().createTranslatedChild(tx, ty);
/*  927 */     } catch (Exception e) {
/*  929 */       ImagingListener listener = ImageUtil.getImagingListener(this.renderHints);
/*  931 */       listener.errorOccurred(JaiI18N.getString("IIPResolutionOpImage3"), (Throwable)new ImagingException(e), this, false);
/*      */     } 
/*  940 */     closeStream(byteStream);
/*  942 */     if (this.colorSpaceType == 3 && colorConversion)
/*  943 */       YCbCrToNIFRGB(raster); 
/*  946 */     return raster;
/*      */   }
/*      */   
/*      */   private synchronized JPEGDecodeParam getJPEGDecodeParam(int tableIndex) {
/*  956 */     JPEGDecodeParam decodeParam = this.decodeParamCache[tableIndex - 1];
/*  958 */     if (decodeParam == null) {
/*  959 */       String cmd = new String("OBJ=Comp-group,2," + tableIndex);
/*  961 */       InputStream stream = postCommands(new String[] { cmd });
/*  962 */       String label = null;
/*  963 */       while ((label = getLabel(stream)) != null) {
/*  964 */         if (label.startsWith("comp-group")) {
/*  965 */           byte[] table = getDataAsByteArray(stream);
/*  966 */           ByteArrayInputStream tableStream = new ByteArrayInputStream(table);
/*  968 */           JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(tableStream);
/*      */           try {
/*  972 */             decoder.decodeAsRaster();
/*  973 */           } catch (Exception e) {}
/*  976 */           decodeParam = decoder.getJPEGDecodeParam();
/*      */           continue;
/*      */         } 
/*  978 */         checkError(label, stream, true);
/*      */       } 
/*  982 */       endResponse(stream);
/*  984 */       if (decodeParam != null)
/*  985 */         this.decodeParamCache[tableIndex - 1] = decodeParam; 
/*      */     } 
/*  989 */     return decodeParam;
/*      */   }
/*      */   
/*      */   private synchronized void initializeIIPProperties() {
/*  997 */     if (!this.arePropertiesInitialized) {
/*  998 */       String[] cmd = { "OBJ=IIP", "OBJ=Basic-info", "OBJ=View-info", "OBJ=Summary-info", "OBJ=Copyright" };
/* 1001 */       InputStream stream = postCommands(cmd);
/* 1002 */       String label = null;
/* 1003 */       while ((label = getLabel(stream)) != null) {
/* 1004 */         String name = label;
/* 1005 */         Object value = null;
/* 1006 */         if (label.equals("error")) {
/* 1007 */           flushData(stream, true);
/* 1008 */         } else if (label.startsWith("colorspace") || label.equals("max-size")) {
/* 1010 */           if (label.startsWith("colorspace"))
/* 1011 */             name = "colorspace"; 
/* 1013 */           value = stringToIntArray(getDataAsString(stream, false));
/* 1014 */         } else if (label.equals("resolution-number")) {
/* 1015 */           value = Integer.valueOf(getDataAsString(stream, false));
/* 1016 */         } else if (label.equals("aspect-ratio") || label.equals("contrast-adjust") || label.equals("filtering-value")) {
/* 1019 */           value = Float.valueOf(getDataAsString(stream, false));
/* 1020 */         } else if (label.equals("affine-transform")) {
/* 1021 */           float[] a = stringToFloatArray(getDataAsString(stream, false));
/* 1024 */           value = new AffineTransform(a[0], a[1], a[3], a[4], a[5], a[7]);
/* 1026 */         } else if (label.equals("color-twist")) {
/* 1027 */           value = stringToFloatArray(getDataAsString(stream, false));
/* 1028 */         } else if (label.equals("roi")) {
/* 1029 */           name = "roi-iip";
/* 1030 */           float[] rect = stringToFloatArray(getDataAsString(stream, false));
/* 1032 */           value = new Rectangle2D.Float(rect[0], rect[1], rect[2], rect[3]);
/* 1034 */         } else if (label.equals("copyright") || label.equals("title") || label.equals("subject") || label.equals("author") || label.equals("keywords") || label.equals("comment") || label.equals("last-author") || label.equals("rev-number") || label.equals("app-name")) {
/* 1043 */           value = getDataAsString(stream, true);
/* 1044 */         } else if (label.equals("iip") || label.equals("iip-server") || label.equals("edit-time") || label.equals("last-printed") || label.equals("create-dtm") || label.equals("last-save-dtm")) {
/* 1050 */           value = getDataAsString(stream, false);
/*      */         } else {
/* 1052 */           flushData(stream, false);
/*      */         } 
/* 1054 */         if (name != null && value != null)
/* 1055 */           setProperty(name, value); 
/*      */       } 
/* 1058 */       endResponse(stream);
/* 1059 */       this.arePropertiesInitialized = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/* 1067 */     initializeIIPProperties();
/* 1068 */     return super.getPropertyNames();
/*      */   }
/*      */   
/*      */   public Object getProperty(String name) {
/* 1075 */     initializeIIPProperties();
/* 1076 */     return super.getProperty(name);
/*      */   }
/*      */   
/*      */   public Rectangle mapSourceRect(Rectangle sourceRect, int sourceIndex) {
/* 1089 */     throw new IllegalArgumentException(JaiI18N.getString("AreaOpImage0"));
/*      */   }
/*      */   
/*      */   public Rectangle mapDestRect(Rectangle destRect, int sourceIndex) {
/* 1102 */     throw new IllegalArgumentException(JaiI18N.getString("AreaOpImage0"));
/*      */   }
/*      */   
/*      */   protected void finalize() throws Throwable {
/* 1109 */     super.finalize();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\IIPResolutionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */