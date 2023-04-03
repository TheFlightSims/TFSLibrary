/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.PointMapperOpImage;
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import com.sun.media.jai.opimage.TranslateIntOpImage;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ public class MlibRotateRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  57 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  59 */     Interpolation interp = (Interpolation)args.getObjectParameter(3);
/*  60 */     double[] backgroundValues = (double[])args.getObjectParameter(4);
/*  62 */     RenderedImage source = args.getRenderedSource(0);
/*  64 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout) || source.getTileWidth() >= 32768 || source.getTileHeight() >= 32768)
/*  70 */       return null; 
/*  74 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  76 */     float x_center = args.getFloatParameter(0);
/*  77 */     float y_center = args.getFloatParameter(1);
/*  78 */     float angle = args.getFloatParameter(2);
/*  86 */     double tmp_angle = (180.0F * angle) / Math.PI;
/*  87 */     double rnd_angle = Math.round(tmp_angle);
/*  90 */     AffineTransform transform = AffineTransform.getRotateInstance(angle, x_center, y_center);
/*  94 */     if (Math.abs(rnd_angle - tmp_angle) < 1.0E-4D) {
/*  95 */       int dangle = (int)rnd_angle % 360;
/*  98 */       if (dangle < 0)
/*  99 */         dangle += 360; 
/* 106 */       if (dangle == 0)
/* 107 */         return (RenderedImage)new MlibCopyOpImage(source, hints, layout); 
/* 110 */       int ix_center = Math.round(x_center);
/* 111 */       int iy_center = Math.round(y_center);
/* 115 */       if (dangle % 90 == 0 && Math.abs(x_center - ix_center) < 1.0E-4D && Math.abs(y_center - iy_center) < 1.0E-4D) {
/* 119 */         int transType = -1;
/* 120 */         int rotMinX = 0;
/* 121 */         int rotMinY = 0;
/* 123 */         int sourceMinX = source.getMinX();
/* 124 */         int sourceMinY = source.getMinY();
/* 125 */         int sourceMaxX = sourceMinX + source.getWidth();
/* 126 */         int sourceMaxY = sourceMinY + source.getHeight();
/* 128 */         if (dangle == 90) {
/* 129 */           transType = 4;
/* 130 */           rotMinX = ix_center - sourceMaxY - iy_center;
/* 131 */           rotMinY = iy_center - ix_center - sourceMinX;
/* 132 */         } else if (dangle == 180) {
/* 133 */           transType = 5;
/* 134 */           rotMinX = 2 * ix_center - sourceMaxX;
/* 135 */           rotMinY = 2 * iy_center - sourceMaxY;
/*     */         } else {
/* 137 */           transType = 6;
/* 138 */           rotMinX = ix_center - iy_center - sourceMinY;
/* 139 */           rotMinY = iy_center - sourceMaxX - ix_center;
/*     */         } 
/* 142 */         MlibTransposeOpImage mlibTransposeOpImage = new MlibTransposeOpImage(source, hints, layout, transType);
/* 146 */         int imMinX = mlibTransposeOpImage.getMinX();
/* 147 */         int imMinY = mlibTransposeOpImage.getMinY();
/* 151 */         if (layout == null) {
/* 152 */           TranslateIntOpImage translateIntOpImage = new TranslateIntOpImage((RenderedImage)mlibTransposeOpImage, hints, rotMinX - imMinX, rotMinY - imMinY);
/*     */           try {
/* 158 */             return (RenderedImage)new PointMapperOpImage((PlanarImage)translateIntOpImage, hints, transform);
/* 161 */           } catch (NoninvertibleTransformException nite) {
/* 162 */             return (RenderedImage)translateIntOpImage;
/*     */           } 
/*     */         } 
/* 165 */         ParameterBlock pbScale = new ParameterBlock();
/* 166 */         pbScale.addSource(mlibTransposeOpImage);
/* 167 */         pbScale.add(0.0F);
/* 168 */         pbScale.add(0.0F);
/* 169 */         pbScale.add(rotMinX - imMinX);
/* 170 */         pbScale.add(rotMinY - imMinY);
/* 171 */         pbScale.add(interp);
/* 172 */         PlanarImage intermediateImage = JAI.create("scale", pbScale, hints).getRendering();
/*     */         try {
/* 175 */           return (RenderedImage)new PointMapperOpImage(intermediateImage, hints, transform);
/* 178 */         } catch (NoninvertibleTransformException nite) {
/* 179 */           return (RenderedImage)intermediateImage;
/*     */         } 
/*     */       } 
/*     */     } 
/* 191 */     if (interp instanceof javax.media.jai.InterpolationNearest)
/* 192 */       return (RenderedImage)new MlibAffineNearestOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 197 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 198 */       return (RenderedImage)new MlibAffineBilinearOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 203 */     if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 205 */       return (RenderedImage)new MlibAffineBicubicOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 210 */     if (interp instanceof javax.media.jai.InterpolationTable)
/* 211 */       return (RenderedImage)new MlibAffineTableOpImage(source, extender, hints, layout, transform, interp, backgroundValues); 
/* 217 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibRotateRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */