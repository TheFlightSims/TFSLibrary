/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.SubsampleBinaryToGrayOpImage;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ 
/*     */ class MlibSubsampleBinaryToGrayOpImage extends SubsampleBinaryToGrayOpImage {
/*     */   public MlibSubsampleBinaryToGrayOpImage(RenderedImage source, ImageLayout layout, Map config, float scaleX, float scaleY) {
/* 101 */     super(source, layout, config, scaleX, scaleY);
/*     */   }
/*     */   
/*     */   protected Rectangle backwardMapRect(Rectangle destRect, int sourceIndex) {
/* 122 */     Rectangle sourceRect = super.backwardMapRect(destRect, sourceIndex);
/* 125 */     sourceRect.width += (int)this.invScaleX;
/* 126 */     sourceRect.height += (int)this.invScaleY;
/* 129 */     return sourceRect.intersection(getSourceImage(0).getBounds());
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/* 145 */     Raster source = sources[0];
/* 146 */     Rectangle srcRect = source.getBounds();
/* 150 */     int sourceFormatTag = dest.getSampleModel().getDataType() | 0x100 | 0x0;
/* 157 */     int destFormatTag = MediaLibAccessor.findCompatibleTag(null, dest);
/* 159 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, sourceFormatTag, true);
/* 162 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, destFormatTag);
/* 166 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 171 */         srcML = srcAccessor.getMediaLibImages();
/* 172 */         dstML = dstAccessor.getMediaLibImages();
/* 173 */         for (i = 0; i < dstML.length; i++)
/* 174 */           Image.SubsampleBinaryToGray(dstML[i], srcML[i], this.scaleX, this.scaleY, this.lutGray); 
/*     */         break;
/*     */       default:
/* 182 */         className = getClass().getName();
/* 183 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 186 */     if (dstAccessor.isDataCopy()) {
/* 187 */       dstAccessor.clampDataArrays();
/* 188 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibSubsampleBinaryToGrayOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */