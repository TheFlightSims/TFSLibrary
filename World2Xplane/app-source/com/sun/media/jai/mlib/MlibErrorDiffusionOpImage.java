/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import com.sun.medialib.mlib.mediaLibImageColormap;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ColorCube;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.UntiledOpImage;
/*     */ 
/*     */ final class MlibErrorDiffusionOpImage extends UntiledOpImage {
/*     */   private static final int KERNEL_SCALE_EXPONENT = 16;
/*     */   
/*     */   protected mediaLibImageColormap mlibColormap;
/*     */   
/*     */   protected int[] kernel;
/*     */   
/*     */   protected int kernelWidth;
/*     */   
/*     */   protected int kernelHeight;
/*     */   
/*     */   protected int kernelKeyX;
/*     */   
/*     */   protected int kernelKeyY;
/*     */   
/*     */   protected int kernelScale;
/*     */   
/*     */   static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, LookupTableJAI colormap) {
/*  88 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  92 */     il.setMinX(source.getMinX());
/*  93 */     il.setMinY(source.getMinY());
/*  94 */     il.setWidth(source.getWidth());
/*  95 */     il.setHeight(source.getHeight());
/*  98 */     SampleModel sm = il.getSampleModel(source);
/* 101 */     if (colormap.getNumBands() == 1 && colormap.getNumEntries() == 2 && !ImageUtil.isBinary(il.getSampleModel(source))) {
/* 104 */       sm = new MultiPixelPackedSampleModel(0, il.getTileWidth(source), il.getTileHeight(source), 1);
/* 108 */       il.setSampleModel(sm);
/*     */     } 
/* 112 */     if (sm.getNumBands() != 1) {
/* 113 */       sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), 1);
/* 119 */       il.setSampleModel(sm);
/* 122 */       ColorModel cm = il.getColorModel(null);
/* 123 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 126 */         il.unsetValid(512); 
/*     */     } 
/* 134 */     if ((layout == null || !il.isValid(512)) && source.getSampleModel().getDataType() == 0 && sm.getDataType() == 0 && colormap.getDataType() == 0 && colormap.getNumBands() == 3) {
/* 139 */       ColorModel cm = source.getColorModel();
/* 140 */       if (cm == null || (cm != null && cm.getColorSpace().isCS_sRGB())) {
/* 142 */         int size = colormap.getNumEntries();
/* 143 */         byte[][] cmap = new byte[3][256];
/* 144 */         for (int i = 0; i < 3; i++) {
/* 145 */           byte[] band = cmap[i];
/* 146 */           byte[] data = colormap.getByteData(i);
/* 147 */           int offset = colormap.getOffset(i);
/* 148 */           int end = offset + size;
/*     */           int j;
/* 149 */           for (j = 0; j < offset; j++)
/* 150 */             band[j] = 0; 
/* 152 */           for (j = offset; j < end; j++)
/* 153 */             band[j] = data[j - offset]; 
/* 155 */           for (j = end; j < 256; j++)
/* 156 */             band[j] = -1; 
/*     */         } 
/* 160 */         il.setColorModel(new IndexColorModel(8, 256, cmap[0], cmap[1], cmap[2]));
/*     */       } 
/*     */     } 
/* 166 */     return il;
/*     */   }
/*     */   
/*     */   public MlibErrorDiffusionOpImage(RenderedImage source, Map config, ImageLayout layout, LookupTableJAI colormap, KernelJAI errorKernel) {
/* 186 */     super(source, config, layout);
/* 190 */     this.mlibColormap = Image.ColorDitherInit((colormap instanceof ColorCube) ? ((ColorCube)colormap).getDimension() : null, 1, ImageUtil.isBinary(this.sampleModel) ? 0 : 1, colormap.getNumBands(), colormap.getNumEntries(), colormap.getOffset(), colormap.getByteData());
/* 202 */     this.kernelWidth = errorKernel.getWidth();
/* 203 */     this.kernelHeight = errorKernel.getHeight();
/* 204 */     this.kernelKeyX = errorKernel.getXOrigin();
/* 205 */     this.kernelKeyY = errorKernel.getYOrigin();
/* 206 */     this.kernelScale = 65536;
/* 209 */     float[] kernelData = errorKernel.getKernelData();
/* 210 */     int numElements = kernelData.length;
/* 211 */     this.kernel = new int[numElements];
/* 212 */     for (int i = 0; i < numElements; i++)
/* 213 */       this.kernel[i] = (int)(kernelData[i] * this.kernelScale); 
/*     */   }
/*     */   
/*     */   protected void computeImage(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int sourceFormatTag, destFormatTag;
/* 236 */     Raster source = sources[0];
/* 240 */     if (ImageUtil.isBinary(dest.getSampleModel())) {
/* 243 */       sourceFormatTag = MediaLibAccessor.findCompatibleTag(sources, source);
/* 248 */       destFormatTag = dest.getSampleModel().getDataType() | 0x100 | 0x0;
/*     */     } else {
/* 253 */       sourceFormatTag = destFormatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*     */     } 
/* 257 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(sources[0], destRect, sourceFormatTag, false);
/* 259 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, destFormatTag, true);
/* 262 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 263 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 265 */     Image.ColorErrorDiffusionMxN(dstML[0], srcML[0], this.kernel, this.kernelWidth, this.kernelHeight, this.kernelKeyX, this.kernelKeyY, 16, this.mlibColormap);
/* 275 */     if (dstAccessor.isDataCopy()) {
/* 276 */       dstAccessor.clampDataArrays();
/* 277 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibErrorDiffusionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */