/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import com.sun.medialib.mlib.mediaLibImageInterpTable;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.InterpolationTable;
/*     */ 
/*     */ public class MlibAffineTableOpImage extends MlibAffineOpImage {
/*     */   public MlibAffineTableOpImage(RenderedImage source, BorderExtender extender, Map config, ImageLayout layout, AffineTransform tr, Interpolation interp, double[] backgroundValues) {
/*  62 */     super(source, layout, config, extender, tr, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     mediaLibImageInterpTable mlibInterpTable;
/*     */     mediaLibImage[] srcML, dstML;
/*     */     String className;
/*  85 */     InterpolationTable jtable = (InterpolationTable)this.interp;
/*  90 */     Raster source = sources[0];
/*  91 */     Rectangle srcRect = source.getBounds();
/*  93 */     int formatTag = MediaLibAccessor.findCompatibleTag(sources, dest);
/*  95 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, srcRect, formatTag);
/*  97 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 107 */     double[] medialib_tr = (double[])this.medialib_tr.clone();
/* 109 */     medialib_tr[2] = this.m_transform[0] * srcRect.x + this.m_transform[1] * srcRect.y + this.m_transform[2] - destRect.x;
/* 113 */     medialib_tr[5] = this.m_transform[3] * srcRect.x + this.m_transform[4] * srcRect.y + this.m_transform[5] - destRect.y;
/* 120 */     switch (dstAccessor.getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 125 */         mlibInterpTable = new mediaLibImageInterpTable(3, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableData(), jtable.getVerticalTableData());
/* 137 */         srcML = srcAccessor.getMediaLibImages();
/* 138 */         dstML = dstAccessor.getMediaLibImages();
/* 140 */         if (this.setBackground) {
/* 141 */           Image.AffineTable2(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0, this.intBackgroundValues);
/*     */         } else {
/* 148 */           Image.AffineTable(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0);
/*     */         } 
/* 153 */         MlibUtils.clampImage(dstML[0], getColorModel());
/*     */         break;
/*     */       case 4:
/* 157 */         mlibInterpTable = new mediaLibImageInterpTable(4, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataFloat(), jtable.getVerticalTableDataFloat());
/* 169 */         srcML = srcAccessor.getMediaLibImages();
/* 170 */         dstML = dstAccessor.getMediaLibImages();
/* 172 */         if (this.setBackground) {
/* 173 */           Image.AffineTable2_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0, this.backgroundValues);
/*     */           break;
/*     */         } 
/* 180 */         Image.AffineTable_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0);
/*     */         break;
/*     */       case 5:
/* 188 */         mlibInterpTable = new mediaLibImageInterpTable(5, jtable.getWidth(), jtable.getHeight(), jtable.getLeftPadding(), jtable.getTopPadding(), jtable.getSubsampleBitsH(), jtable.getSubsampleBitsV(), jtable.getPrecisionBits(), jtable.getHorizontalTableDataDouble(), jtable.getVerticalTableDataDouble());
/* 200 */         srcML = srcAccessor.getMediaLibImages();
/* 201 */         dstML = dstAccessor.getMediaLibImages();
/* 203 */         if (this.setBackground) {
/* 204 */           Image.AffineTable2_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0, this.backgroundValues);
/*     */           break;
/*     */         } 
/* 211 */         Image.AffineTable_Fp(dstML[0], srcML[0], medialib_tr, mlibInterpTable, 0);
/*     */         break;
/*     */       default:
/* 219 */         className = getClass().getName();
/* 220 */         throw new RuntimeException(JaiI18N.getString("Generic2"));
/*     */     } 
/* 223 */     if (dstAccessor.isDataCopy())
/* 224 */       dstAccessor.copyDataToRaster(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibAffineTableOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */