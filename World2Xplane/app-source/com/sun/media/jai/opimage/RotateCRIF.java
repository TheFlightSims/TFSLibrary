/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OpImage;
/*     */ import javax.media.jai.PlanarImage;
/*     */ 
/*     */ public class RotateCRIF extends CRIFImpl {
/*     */   public RotateCRIF() {
/*  51 */     super("rotate");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  60 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  64 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  66 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  68 */     float x_center = paramBlock.getFloatParameter(0);
/*  69 */     float y_center = paramBlock.getFloatParameter(1);
/*  70 */     float angle = paramBlock.getFloatParameter(2);
/*  72 */     Object arg1 = paramBlock.getObjectParameter(3);
/*  73 */     Interpolation interp = (Interpolation)arg1;
/*  75 */     double[] backgroundValues = (double[])paramBlock.getObjectParameter(4);
/*  77 */     SampleModel sm = source.getSampleModel();
/*  78 */     boolean isBinary = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  90 */     double tmp_angle = 57.29577951308232D * angle;
/*  91 */     double rnd_angle = Math.round(tmp_angle);
/*  96 */     AffineTransform transform = AffineTransform.getRotateInstance(angle, x_center, y_center);
/* 100 */     if (Math.abs(rnd_angle - tmp_angle) < 1.0E-4D) {
/* 101 */       int dangle = (int)rnd_angle % 360;
/* 104 */       if (dangle < 0)
/* 105 */         dangle += 360; 
/* 112 */       if (dangle == 0)
/* 113 */         return (RenderedImage)new CopyOpImage(source, renderHints, layout); 
/* 116 */       int ix_center = Math.round(x_center);
/* 117 */       int iy_center = Math.round(y_center);
/* 121 */       if (dangle % 90 == 0 && Math.abs(x_center - ix_center) < 1.0E-4D && Math.abs(y_center - iy_center) < 1.0E-4D) {
/*     */         TransposeOpImage transposeOpImage;
/* 125 */         int transType = -1;
/* 126 */         int rotMinX = 0;
/* 127 */         int rotMinY = 0;
/* 129 */         int sourceMinX = source.getMinX();
/* 130 */         int sourceMinY = source.getMinY();
/* 131 */         int sourceMaxX = sourceMinX + source.getWidth();
/* 132 */         int sourceMaxY = sourceMinY + source.getHeight();
/* 134 */         if (dangle == 90) {
/* 135 */           transType = 4;
/* 136 */           rotMinX = ix_center - sourceMaxY - iy_center;
/* 137 */           rotMinY = iy_center - ix_center - sourceMinX;
/* 138 */         } else if (dangle == 180) {
/* 139 */           transType = 5;
/* 140 */           rotMinX = 2 * ix_center - sourceMaxX;
/* 141 */           rotMinY = 2 * iy_center - sourceMaxY;
/*     */         } else {
/* 143 */           transType = 6;
/* 144 */           rotMinX = ix_center - iy_center - sourceMinY;
/* 145 */           rotMinY = iy_center - sourceMaxX - ix_center;
/*     */         } 
/* 149 */         if (isBinary) {
/* 150 */           transposeOpImage = new TransposeBinaryOpImage(source, renderHints, layout, transType);
/*     */         } else {
/* 153 */           transposeOpImage = new TransposeOpImage(source, renderHints, layout, transType);
/*     */         } 
/* 157 */         int imMinX = transposeOpImage.getMinX();
/* 158 */         int imMinY = transposeOpImage.getMinY();
/* 161 */         if (layout == null) {
/* 163 */           OpImage opImage = new TranslateIntOpImage((RenderedImage)transposeOpImage, renderHints, rotMinX - imMinX, rotMinY - imMinY);
/*     */           try {
/* 169 */             return (RenderedImage)new PointMapperOpImage((PlanarImage)opImage, renderHints, transform);
/* 172 */           } catch (NoninvertibleTransformException nite) {
/* 173 */             return (RenderedImage)opImage;
/*     */           } 
/*     */         } 
/* 176 */         ParameterBlock pbScale = new ParameterBlock();
/* 177 */         pbScale.addSource(transposeOpImage);
/* 178 */         pbScale.add(0.0F);
/* 179 */         pbScale.add(0.0F);
/* 180 */         pbScale.add(rotMinX - imMinX);
/* 181 */         pbScale.add(rotMinY - imMinY);
/* 182 */         pbScale.add(interp);
/* 183 */         PlanarImage intermediateImage = JAI.create("scale", pbScale, renderHints).getRendering();
/*     */         try {
/* 187 */           return (RenderedImage)new PointMapperOpImage(intermediateImage, renderHints, transform);
/* 190 */         } catch (NoninvertibleTransformException nite) {
/* 191 */           return (RenderedImage)intermediateImage;
/*     */         } 
/*     */       } 
/*     */     } 
/* 205 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/* 206 */       if (isBinary)
/* 207 */         return (RenderedImage)new AffineNearestBinaryOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 214 */       return (RenderedImage)new AffineNearestOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues);
/*     */     } 
/* 221 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 222 */       return (RenderedImage)new AffineBilinearOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 229 */     if (interp instanceof javax.media.jai.InterpolationBicubic)
/* 230 */       return (RenderedImage)new AffineBicubicOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 237 */     if (interp instanceof javax.media.jai.InterpolationBicubic2)
/* 238 */       return (RenderedImage)new AffineBicubic2OpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 246 */     return (RenderedImage)new AffineGeneralOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 263 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 282 */     float x_center = paramBlock.getFloatParameter(0);
/* 283 */     float y_center = paramBlock.getFloatParameter(1);
/* 284 */     float angle = paramBlock.getFloatParameter(2);
/* 286 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, x_center, y_center);
/* 289 */     RenderContext RC = (RenderContext)renderContext.clone();
/* 290 */     AffineTransform usr2dev = RC.getTransform();
/* 291 */     usr2dev.concatenate(rotate);
/* 292 */     RC.setTransform(usr2dev);
/* 293 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 301 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 303 */     float x_center = paramBlock.getFloatParameter(0);
/* 304 */     float y_center = paramBlock.getFloatParameter(1);
/* 305 */     float angle = paramBlock.getFloatParameter(2);
/* 306 */     Interpolation interp = (Interpolation)paramBlock.getObjectParameter(3);
/* 314 */     int dangle = 0;
/* 315 */     double tmp_angle = (180.0F * angle) / Math.PI;
/* 316 */     double rnd_angle = Math.round(tmp_angle);
/* 318 */     if (Math.abs(rnd_angle - tmp_angle) < 1.0E-4D) {
/* 319 */       dangle = (int)rnd_angle;
/*     */     } else {
/* 321 */       dangle = (int)tmp_angle;
/*     */     } 
/* 327 */     if (dangle % 360 == 0)
/* 328 */       return new Rectangle2D.Float(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight()); 
/* 337 */     float x0 = source.getMinX();
/* 338 */     float y0 = source.getMinY();
/* 339 */     float s_width = source.getWidth();
/* 340 */     float s_height = source.getHeight();
/* 341 */     float x1 = x0 + s_width - 1.0F;
/* 342 */     float y1 = y0 + s_height - 1.0F;
/* 344 */     float tx0 = 0.0F;
/* 345 */     float ty0 = 0.0F;
/* 346 */     float tx1 = 0.0F;
/* 347 */     float ty1 = 0.0F;
/* 349 */     if (dangle % 270 == 0) {
/* 350 */       if (dangle < 0) {
/* 352 */         tx0 = s_height - y1 - 1.0F;
/* 353 */         ty0 = x0;
/* 354 */         tx1 = s_height - y0 - 1.0F;
/* 355 */         ty1 = x1;
/* 356 */         return new Rectangle2D.Float(tx0, ty0, tx1 - tx0 + 1.0F, ty1 - ty0 + 1.0F);
/*     */       } 
/* 362 */       tx0 = y0;
/* 363 */       ty0 = s_width - x1 - 1.0F;
/* 364 */       tx1 = y1;
/* 365 */       ty1 = s_width - x0 - 1.0F;
/* 366 */       return new Rectangle2D.Float(tx0, ty0, tx1 - tx0 + 1.0F, ty1 - ty0 + 1.0F);
/*     */     } 
/* 373 */     if (dangle % 180 == 0) {
/* 374 */       tx0 = s_width - x1 - 1.0F;
/* 375 */       ty0 = s_height - y1 - 1.0F;
/* 376 */       tx1 = s_width - x0 - 1.0F;
/* 377 */       ty1 = s_height - y0 - 1.0F;
/* 379 */       return new Rectangle2D.Float(tx0, ty0, tx1 - tx0 + 1.0F, ty1 - ty0 + 1.0F);
/*     */     } 
/* 385 */     if (dangle % 90 == 0) {
/* 386 */       if (dangle < 0) {
/* 388 */         tx0 = y0;
/* 389 */         ty0 = s_width - x1 - 1.0F;
/* 390 */         tx1 = y1;
/* 391 */         ty1 = s_width - x0 - 1.0F;
/* 392 */         return new Rectangle2D.Float(tx0, ty0, tx1 - tx0 + 1.0F, ty1 - ty0 + 1.0F);
/*     */       } 
/* 398 */       tx0 = s_height - y1 - 1.0F;
/* 399 */       ty0 = x0;
/* 400 */       tx1 = s_height - y0 - 1.0F;
/* 401 */       ty1 = x1;
/* 402 */       return new Rectangle2D.Float(tx0, ty0, tx1 - tx0 + 1.0F, ty1 - ty0 + 1.0F);
/*     */     } 
/* 412 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, x_center, y_center);
/* 418 */     float sx0 = source.getMinX();
/* 419 */     float sy0 = source.getMinY();
/* 420 */     float sw = source.getWidth();
/* 421 */     float sh = source.getHeight();
/* 428 */     Point2D[] pts = new Point2D[4];
/* 429 */     pts[0] = new Point2D.Float(sx0, sy0);
/* 430 */     pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 431 */     pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 432 */     pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 435 */     rotate.transform(pts, 0, pts, 0, 4);
/* 437 */     float dx0 = Float.MAX_VALUE;
/* 438 */     float dy0 = Float.MAX_VALUE;
/* 439 */     float dx1 = -3.4028235E38F;
/* 440 */     float dy1 = -3.4028235E38F;
/* 441 */     for (int i = 0; i < 4; i++) {
/* 442 */       float px = (float)pts[i].getX();
/* 443 */       float py = (float)pts[i].getY();
/* 445 */       dx0 = Math.min(dx0, px);
/* 446 */       dy0 = Math.min(dy0, py);
/* 447 */       dx1 = Math.max(dx1, px);
/* 448 */       dy1 = Math.max(dy1, py);
/*     */     } 
/* 455 */     float lw = dx1 - dx0;
/* 456 */     float lh = dy1 - dy0;
/* 458 */     return new Rectangle2D.Float(dx0, dy0, lw, lh);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\RotateCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */