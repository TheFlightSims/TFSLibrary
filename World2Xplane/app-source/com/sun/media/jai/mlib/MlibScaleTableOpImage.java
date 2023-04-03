/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import com.sun.medialib.mlib.mediaLibImageInterpTable;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.InterpolationTable;
/*     */ 
/*     */ final class MlibScaleTableOpImage extends MlibScaleOpImage {
/*     */   public MlibScaleTableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, float xScale, float yScale, float xTrans, float yTrans, Interpolation interp) {
/*  57 */     super(source, extender, config, layout, xScale, yScale, xTrans, yTrans, interp, true);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImageInterpTable mlibInterpTable;
/*     */     mediaLibImage[] srcML, dstML;
/*     */     int i;
/*     */     String className;
/*  75 */     InterpolationTable jtable = (InterpolationTable)this.interp;
/*  80 */     Raster source = sources[0];
/*  81 */     Rectangle srcRect = source.getBounds();
/*  83 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  85 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  87 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/*  91 */     float mlibScaleX = (float)this.scaleXRationalNum / (float)this.scaleXRationalDenom;
/*  92 */     float mlibScaleY = (float)this.scaleYRationalNum / (float)this.scaleYRationalDenom;
/*  99 */     float tempDX = (float)(srcRect.x * this.scaleXRationalNum) / (float)this.scaleXRationalDenom;
/* 101 */     float tempDY = (float)(srcRect.y * this.scaleYRationalNum) / (float)this.scaleYRationalDenom;
/* 104 */     float tx = this.transX - destRect.x + tempDX;
/* 105 */     float ty = this.transY - destRect.y + tempDY;
/* 109 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 115 */         mlibInterpTable = new mediaLibImageInterpTable(3, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableData(), jtable.getVerticalTableData());
/* 127 */         srcML = srcAccessor.getMediaLibImages();
/* 128 */         dstML = dstAccessor.getMediaLibImages();
/* 129 */         for (i = 0; i < dstML.length; i++) {
/* 130 */           Image.ZoomTranslateTable(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, mlibInterpTable, 0);
/* 136 */           MlibUtils.clampImage(dstML[i], getColorModel());
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 142 */         mlibInterpTable = new mediaLibImageInterpTable(4, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataFloat(), jtable.getVerticalTableDataFloat());
/* 155 */         srcML = srcAccessor.getMediaLibImages();
/* 156 */         dstML = dstAccessor.getMediaLibImages();
/* 157 */         for (i = 0; i < dstML.length; i++)
/* 158 */           Image.ZoomTranslateTable_Fp(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, mlibInterpTable, 0); 
/*     */         break;
/*     */       case 5:
/* 170 */         mlibInterpTable = new mediaLibImageInterpTable(5, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataDouble(), jtable.getVerticalTableDataDouble());
/* 183 */         srcML = srcAccessor.getMediaLibImages();
/* 184 */         dstML = dstAccessor.getMediaLibImages();
/* 185 */         for (i = 0; i < dstML.length; i++)
/* 186 */           Image.ZoomTranslateTable_Fp(dstML[i], srcML[i], mlibScaleX, mlibScaleY, tx, ty, mlibInterpTable, 0); 
/*     */         break;
/*     */       default:
/* 197 */         className = getClass().getName();
/* 198 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 201 */     if (dstAccessor.isDataCopy()) {
/* 202 */       dstAccessor.clampDataArrays();
/* 203 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibScaleTableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */