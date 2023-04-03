/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.MosaicOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.operator.MosaicDescriptor;
/*     */ import javax.media.jai.operator.MosaicType;
/*     */ 
/*     */ final class MlibMosaicOpImage extends MosaicOpImage {
/*     */   private int[] glow;
/*     */   
/*     */   private int[] ghigh;
/*     */   
/*     */   private int shift;
/*     */   
/*     */   public MlibMosaicOpImage(Vector sources, ImageLayout layout, Map config, MosaicType mosaicType, PlanarImage[] sourceAlpha, ROI[] sourceROI, double[][] sourceThreshold, double[] backgroundValues) {
/*  49 */     super(sources, layout, config, mosaicType, sourceAlpha, sourceROI, sourceThreshold, backgroundValues);
/*  53 */     int numSources = sources.size();
/*  55 */     int dataType = this.sampleModel.getDataType();
/*  56 */     if (dataType == 4 || dataType == 5)
/*  58 */       throw new UnsupportedOperationException(JaiI18N.getString("MlibMosaicOpImage0")); 
/*  62 */     for (int i = 0; i < numSources; i++) {
/*  63 */       for (int j = 0; j < this.numBands; j++)
/*  64 */         this.threshold[i][j] = this.threshold[i][j] - 1; 
/*     */     } 
/*  69 */     int minValue = -2147483647;
/*  70 */     int maxValue = Integer.MAX_VALUE;
/*  71 */     switch (dataType) {
/*     */       case 0:
/*  73 */         minValue = 0;
/*  74 */         maxValue = 255;
/*  75 */         this.shift = 8;
/*     */         break;
/*     */       case 1:
/*  78 */         minValue = 0;
/*  79 */         maxValue = 65535;
/*  80 */         this.shift = 16;
/*     */         break;
/*     */       case 2:
/*  83 */         minValue = -32768;
/*  84 */         maxValue = 32767;
/*  85 */         this.shift = 16;
/*     */         break;
/*     */       case 3:
/*  88 */         minValue = Integer.MIN_VALUE;
/*  89 */         maxValue = Integer.MAX_VALUE;
/*  90 */         this.shift = 32;
/*     */         break;
/*     */     } 
/*  96 */     this.glow = new int[this.numBands];
/*  97 */     Arrays.fill(this.glow, minValue);
/*  98 */     this.ghigh = new int[this.numBands];
/*  99 */     Arrays.fill(this.ghigh, maxValue);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect, Raster[] alphaRaster, Raster[] roiRaster) {
/* 110 */     int numSources = sources.length;
/* 113 */     ArrayList sourceList = new ArrayList(numSources);
/* 114 */     for (int i = 0; i < numSources; i++) {
/* 115 */       if (sources[i] != null)
/* 116 */         sourceList.add(sources[i]); 
/*     */     } 
/* 121 */     int numNonNullSources = sourceList.size();
/* 122 */     Raster[] nonNullSources = null;
/* 123 */     if (numNonNullSources != 0) {
/* 124 */       nonNullSources = new Raster[numNonNullSources];
/* 125 */       sourceList.toArray(nonNullSources);
/*     */     } 
/* 129 */     int formatTag = MediaLibAccessor.findCompatibleTag(nonNullSources, dest);
/* 133 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 135 */     mediaLibImage[] dst = dstAccessor.getMediaLibImages();
/* 138 */     int[] mlibBackground = dstAccessor.getIntParameters(0, this.background);
/* 140 */     if (numNonNullSources == 0) {
/* 142 */       Image.Clear(dst[0], mlibBackground);
/*     */       return;
/*     */     } 
/* 147 */     MediaLibAccessor[] srcAccessor = new MediaLibAccessor[numSources];
/* 148 */     for (int j = 0; j < numSources; j++) {
/* 149 */       if (sources[j] != null)
/* 150 */         srcAccessor[j] = new MediaLibAccessor(sources[j], destRect, formatTag); 
/*     */     } 
/* 156 */     int[][] mlibThreshold = new int[numSources][];
/* 157 */     mediaLibImage[][] src = new mediaLibImage[numSources][];
/* 158 */     for (int k = 0; k < numSources; k++) {
/* 159 */       if (srcAccessor[k] != null) {
/* 160 */         src[k] = srcAccessor[k].getMediaLibImages();
/* 161 */         mlibThreshold[k] = srcAccessor[k].getIntParameters(0, this.threshold[k]);
/*     */       } 
/*     */     } 
/* 167 */     mediaLibImage tmpIm1 = null;
/* 168 */     mediaLibImage tmpImN = null;
/* 169 */     mediaLibImage[] tmpIm1Array = { tmpIm1 };
/* 170 */     mediaLibImage[] tmpImNArray = { tmpImN };
/* 172 */     if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_OVERLAY) {
/* 174 */       Image.Clear(dst[0], mlibBackground);
/* 176 */       for (int m = numSources - 1; m >= 0; m--) {
/* 177 */         if (src[m] != null) {
/* 181 */           mediaLibImage weight = getWeightImage(destRect, formatTag, dst[0], src[m][0], (this.sourceAlpha != null && this.sourceAlpha[m] != null) ? alphaRaster[m] : null, (this.sourceROI != null && this.sourceROI[m] != null) ? roiRaster[m] : null, mlibThreshold[m], tmpIm1Array, tmpImNArray);
/* 194 */           Image.Blend2(dst[0], src[m][0], weight);
/*     */         } 
/*     */       } 
/* 196 */     } else if (this.mosaicType == MosaicDescriptor.MOSAIC_TYPE_BLEND) {
/* 197 */       tmpIm1 = new mediaLibImage(dst[0].getType(), 1, dst[0].getWidth(), dst[0].getHeight());
/* 201 */       tmpImN = new mediaLibImage(dst[0].getType(), dst[0].getChannels(), dst[0].getWidth(), dst[0].getHeight());
/* 206 */       mediaLibImage[] alphas = new mediaLibImage[numNonNullSources];
/* 207 */       mediaLibImage[] srcs = new mediaLibImage[numNonNullSources];
/* 209 */       int sourceCount = 0;
/* 211 */       for (int m = 0; m < numSources; m++) {
/* 212 */         if (src[m] != null) {
/* 216 */           srcs[sourceCount] = src[m][0];
/* 217 */           alphas[sourceCount] = getWeightImage(destRect, formatTag, dst[0], src[m][0], (this.sourceAlpha != null && this.sourceAlpha[m] != null) ? alphaRaster[m] : null, (this.sourceROI != null && this.sourceROI[m] != null) ? roiRaster[m] : null, mlibThreshold[m], (mediaLibImage[])null, (mediaLibImage[])null);
/* 229 */           sourceCount++;
/*     */         } 
/*     */       } 
/* 232 */       if (sourceCount != numNonNullSources) {
/* 233 */         mediaLibImage[] srcsNew = new mediaLibImage[sourceCount];
/* 234 */         System.arraycopy(srcs, 0, srcsNew, 0, sourceCount);
/* 235 */         srcs = srcsNew;
/* 236 */         mediaLibImage[] alphasNew = new mediaLibImage[sourceCount];
/* 237 */         System.arraycopy(alphas, 0, alphasNew, 0, sourceCount);
/* 238 */         alphas = alphasNew;
/*     */       } 
/* 241 */       Image.BlendMulti(dst[0], srcs, alphas, mlibBackground);
/*     */     } 
/* 244 */     if (dstAccessor.isDataCopy()) {
/* 245 */       dstAccessor.clampDataArrays();
/* 246 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */   
/*     */   private mediaLibImage getWeightImage(Rectangle destRect, int formatTag, mediaLibImage dst, mediaLibImage src, Raster alphaRaster, Raster roiRaster, int[] thresh, mediaLibImage[] tmpIm1, mediaLibImage[] tmpImN) {
/* 262 */     mediaLibImage weight = null;
/* 264 */     if (alphaRaster != null) {
/* 265 */       MediaLibAccessor alphaAccessor = new MediaLibAccessor(alphaRaster, destRect, formatTag);
/* 268 */       mediaLibImage[] alphaML = alphaAccessor.getMediaLibImages();
/* 270 */       if (this.isAlphaBitmask) {
/* 271 */         if (tmpIm1 == null)
/* 272 */           tmpIm1 = new mediaLibImage[] { null }; 
/* 274 */         if (tmpIm1[0] == null)
/* 275 */           tmpIm1[0] = new mediaLibImage(src.getType(), 1, src.getWidth(), src.getHeight()); 
/* 281 */         Image.Thresh1(tmpIm1[0], alphaML[0], new int[] { 0 }, new int[] { this.ghigh[0] }, new int[] { this.glow[0] });
/* 283 */         weight = tmpIm1[0];
/*     */       } else {
/* 285 */         weight = alphaML[0];
/*     */       } 
/* 287 */     } else if (roiRaster != null) {
/* 288 */       int roiFmtTag = MediaLibAccessor.findCompatibleTag(null, roiRaster);
/* 291 */       MediaLibAccessor roiAccessor = new MediaLibAccessor(roiRaster, destRect, roiFmtTag, true);
/* 294 */       mediaLibImage[] roi = roiAccessor.getMediaLibImages();
/* 296 */       if (tmpIm1 == null)
/* 297 */         tmpIm1 = new mediaLibImage[] { null }; 
/* 299 */       if (tmpIm1[0] == null)
/* 300 */         tmpIm1[0] = new mediaLibImage(src.getType(), 1, src.getWidth(), src.getHeight()); 
/* 306 */       if (tmpIm1[0].getType() != roi[0].getType()) {
/* 307 */         if (tmpIm1[0] == null)
/* 308 */           tmpIm1[0] = new mediaLibImage(src.getType(), 1, src.getWidth(), src.getHeight()); 
/* 313 */         Image.DataTypeConvert(tmpIm1[0], roi[0]);
/*     */       } else {
/* 317 */         tmpIm1[0] = roi[0];
/*     */       } 
/* 320 */       Image.Thresh1(tmpIm1[0], new int[] { 0 }, new int[] { this.ghigh[0] }, new int[] { this.glow[0] });
/* 323 */       weight = tmpIm1[0];
/*     */     } else {
/* 325 */       if (tmpImN == null)
/* 326 */         tmpImN = new mediaLibImage[] { null }; 
/* 328 */       if (tmpImN[0] == null)
/* 329 */         tmpImN[0] = dst.createCompatibleImage(); 
/* 331 */       weight = tmpImN[0];
/* 332 */       Image.Thresh1(weight, src, thresh, this.ghigh, this.glow);
/*     */     } 
/* 335 */     return weight;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibMosaicOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */