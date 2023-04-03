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
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ final class MlibOrderedDitherOpImage extends PointOpImage {
/*     */   private static final int DMASK_SCALE_EXPONENT = 16;
/*     */   
/*     */   protected mediaLibImageColormap mlibColormap;
/*     */   
/*     */   protected int[][] dmask;
/*     */   
/*     */   protected int dmaskWidth;
/*     */   
/*     */   protected int dmaskHeight;
/*     */   
/*     */   protected int dmaskScale;
/*     */   
/*     */   static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, ColorCube colormap) {
/*     */     ImageLayout il;
/*  83 */     if (layout == null) {
/*  84 */       il = new ImageLayout(source);
/*     */     } else {
/*  86 */       il = (ImageLayout)layout.clone();
/*     */     } 
/*  90 */     SampleModel sm = il.getSampleModel(source);
/*  93 */     if (colormap.getNumBands() == 1 && colormap.getNumEntries() == 2 && !ImageUtil.isBinary(il.getSampleModel(source))) {
/*  96 */       sm = new MultiPixelPackedSampleModel(0, il.getTileWidth(source), il.getTileHeight(source), 1);
/* 100 */       il.setSampleModel(sm);
/*     */     } 
/* 104 */     if (sm.getNumBands() != 1) {
/* 106 */       sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), 1);
/* 111 */       il.setSampleModel(sm);
/* 114 */       ColorModel cm = il.getColorModel(null);
/* 115 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 118 */         il.unsetValid(512); 
/*     */     } 
/* 128 */     if ((layout == null || !il.isValid(512)) && source.getSampleModel().getDataType() == 0 && il.getSampleModel(null).getDataType() == 0 && colormap.getDataType() == 0 && colormap.getNumBands() == 3) {
/* 133 */       ColorModel cm = source.getColorModel();
/* 134 */       if (cm == null || (cm != null && cm.getColorSpace().isCS_sRGB())) {
/* 136 */         int size = colormap.getNumEntries();
/* 137 */         byte[][] cmap = new byte[3][256];
/* 138 */         for (int i = 0; i < 3; i++) {
/* 139 */           byte[] band = cmap[i];
/* 140 */           byte[] data = colormap.getByteData(i);
/* 141 */           int offset = colormap.getOffset(i);
/* 142 */           int end = offset + size;
/*     */           int j;
/* 143 */           for (j = 0; j < offset; j++)
/* 144 */             band[j] = 0; 
/* 146 */           for (j = offset; j < end; j++)
/* 147 */             band[j] = data[j - offset]; 
/* 149 */           for (j = end; j < 256; j++)
/* 150 */             band[j] = -1; 
/*     */         } 
/* 154 */         il.setColorModel(new IndexColorModel(8, 256, cmap[0], cmap[1], cmap[2]));
/*     */       } 
/*     */     } 
/* 160 */     return il;
/*     */   }
/*     */   
/*     */   public MlibOrderedDitherOpImage(RenderedImage source, Map config, ImageLayout layout, ColorCube colormap, KernelJAI[] ditherMask) {
/* 178 */     super(source, layoutHelper(layout, source, colormap), config, true);
/* 183 */     this.mlibColormap = Image.ColorDitherInit(colormap.getDimension(), 1, ImageUtil.isBinary(this.sampleModel) ? 0 : 1, colormap.getNumBands(), colormap.getNumEntries(), colormap.getOffset(), colormap.getByteData());
/* 194 */     this.dmaskWidth = ditherMask[0].getWidth();
/* 195 */     this.dmaskHeight = ditherMask[0].getHeight();
/* 196 */     this.dmaskScale = 65536;
/* 198 */     int numMasks = ditherMask.length;
/* 199 */     this.dmask = new int[numMasks][];
/* 201 */     for (int k = 0; k < numMasks; k++) {
/* 202 */       KernelJAI mask = ditherMask[k];
/* 204 */       if (mask.getWidth() != this.dmaskWidth || mask.getHeight() != this.dmaskHeight)
/* 206 */         throw new IllegalArgumentException(JaiI18N.getString("MlibOrderedDitherOpImage0")); 
/* 211 */       float[] dmaskData = ditherMask[k].getKernelData();
/* 212 */       int numElements = dmaskData.length;
/* 213 */       this.dmask[k] = new int[numElements];
/* 214 */       int[] dm = this.dmask[k];
/* 215 */       for (int i = 0; i < numElements; i++)
/* 216 */         dm[i] = (int)(dmaskData[i] * this.dmaskScale); 
/*     */     } 
/* 221 */     permitInPlaceOperation();
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     int sourceFormatTag, destFormatTag;
/* 237 */     Raster source = sources[0];
/* 241 */     if (ImageUtil.isBinary(dest.getSampleModel())) {
/* 244 */       sourceFormatTag = MediaLibAccessor.findCompatibleTag(sources, source);
/* 249 */       destFormatTag = dest.getSampleModel().getDataType() | 0x100 | 0x0;
/*     */     } else {
/* 254 */       sourceFormatTag = destFormatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*     */     } 
/* 258 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(sources[0], destRect, sourceFormatTag, false);
/* 260 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, destFormatTag, true);
/* 263 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 264 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 266 */     Image.ColorOrderedDitherMxN(dstML[0], srcML[0], this.dmask, this.dmaskWidth, this.dmaskHeight, 16, this.mlibColormap);
/* 274 */     if (dstAccessor.isDataCopy()) {
/* 275 */       dstAccessor.clampDataArrays();
/* 276 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibOrderedDitherOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */