/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
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
/*     */ import javax.media.jai.TileCache;
/*     */ 
/*     */ public class AffineCRIF extends CRIFImpl {
/*     */   private static final float TOLERANCE = 0.01F;
/*     */   
/*     */   public AffineCRIF() {
/*  50 */     super("affine");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/*  59 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/*  62 */     TileCache cache = RIFUtil.getTileCacheHint(renderHints);
/*  65 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(renderHints);
/*  67 */     RenderedImage source = paramBlock.getRenderedSource(0);
/*  69 */     Object arg0 = paramBlock.getObjectParameter(0);
/*  70 */     AffineTransform transform = (AffineTransform)arg0;
/*  72 */     Object arg1 = paramBlock.getObjectParameter(1);
/*  73 */     Interpolation interp = (Interpolation)arg1;
/*  75 */     double[] backgroundValues = (double[])paramBlock.getObjectParameter(2);
/*  77 */     SampleModel sm = source.getSampleModel();
/*  78 */     boolean isBinary = (sm instanceof java.awt.image.MultiPixelPackedSampleModel && sm.getSampleSize(0) == 1 && (sm.getDataType() == 0 || sm.getDataType() == 1 || sm.getDataType() == 3));
/*  86 */     double[] tr = new double[6];
/*  87 */     transform.getMatrix(tr);
/*  93 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[4] == 0.0D && tr[5] == 0.0D)
/* 100 */       return (RenderedImage)new CopyOpImage(source, renderHints, layout); 
/* 111 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && Math.abs(tr[4] - (int)tr[4]) < 0.009999999776482582D && Math.abs(tr[5] - (int)tr[5]) < 0.009999999776482582D && layout == null)
/* 119 */       return (RenderedImage)new TranslateIntOpImage(source, renderHints, (int)tr[4], (int)tr[5]); 
/* 130 */     if (tr[0] > 0.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[3] > 0.0D) {
/* 135 */       if (interp instanceof javax.media.jai.InterpolationNearest) {
/* 136 */         if (isBinary)
/* 137 */           return (RenderedImage)new ScaleNearestBinaryOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 147 */         return (RenderedImage)new ScaleNearestOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp);
/*     */       } 
/* 157 */       if (interp instanceof javax.media.jai.InterpolationBilinear) {
/* 158 */         if (isBinary)
/* 159 */           return (RenderedImage)new ScaleBilinearBinaryOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 170 */         return (RenderedImage)new ScaleBilinearOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp);
/*     */       } 
/* 180 */       if (interp instanceof javax.media.jai.InterpolationBicubic || interp instanceof javax.media.jai.InterpolationBicubic2)
/* 182 */         return (RenderedImage)new ScaleBicubicOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp); 
/* 192 */       return (RenderedImage)new ScaleGeneralOpImage(source, extender, renderHints, layout, (float)tr[0], (float)tr[3], (float)tr[4], (float)tr[5], interp);
/*     */     } 
/* 205 */     if (interp instanceof javax.media.jai.InterpolationNearest) {
/* 206 */       if (isBinary)
/* 207 */         return (RenderedImage)new AffineNearestBinaryOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 215 */       return (RenderedImage)new AffineNearestOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues);
/*     */     } 
/* 223 */     if (interp instanceof javax.media.jai.InterpolationBilinear)
/* 224 */       return (RenderedImage)new AffineBilinearOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 231 */     if (interp instanceof javax.media.jai.InterpolationBicubic)
/* 232 */       return (RenderedImage)new AffineBicubicOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 239 */     if (interp instanceof javax.media.jai.InterpolationBicubic2)
/* 240 */       return (RenderedImage)new AffineBicubic2OpImage(source, extender, renderHints, layout, transform, interp, backgroundValues); 
/* 248 */     return (RenderedImage)new AffineGeneralOpImage(source, extender, renderHints, layout, transform, interp, backgroundValues);
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/* 266 */     return paramBlock.getRenderedSource(0);
/*     */   }
/*     */   
/*     */   public RenderContext mapRenderContext(int i, RenderContext renderContext, ParameterBlock paramBlock, RenderableImage image) {
/* 285 */     Object arg0 = paramBlock.getObjectParameter(0);
/* 286 */     AffineTransform affine = (AffineTransform)arg0;
/* 288 */     RenderContext RC = (RenderContext)renderContext.clone();
/* 289 */     AffineTransform usr2dev = RC.getTransform();
/* 290 */     usr2dev.concatenate(affine);
/* 291 */     RC.setTransform(usr2dev);
/* 292 */     return RC;
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D(ParameterBlock paramBlock) {
/* 300 */     RenderableImage source = paramBlock.getRenderableSource(0);
/* 301 */     Object arg0 = paramBlock.getObjectParameter(0);
/* 302 */     AffineTransform forward_tr = (AffineTransform)arg0;
/* 304 */     Object arg1 = paramBlock.getObjectParameter(1);
/* 305 */     Interpolation interp = (Interpolation)arg1;
/* 309 */     double[] tr = new double[6];
/* 310 */     forward_tr.getMatrix(tr);
/* 315 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[4] == 0.0D && tr[5] == 0.0D)
/* 321 */       return new Rectangle2D.Float(source.getMinX(), source.getMinY(), source.getWidth(), source.getHeight()); 
/* 331 */     if (tr[0] == 1.0D && tr[3] == 1.0D && tr[2] == 0.0D && tr[1] == 0.0D && Math.abs(tr[4] - (int)tr[4]) < 0.009999999776482582D && Math.abs(tr[5] - (int)tr[5]) < 0.009999999776482582D)
/* 337 */       return new Rectangle2D.Float(source.getMinX() + (float)tr[4], source.getMinY() + (float)tr[5], source.getWidth(), source.getHeight()); 
/* 347 */     if (tr[0] > 0.0D && tr[2] == 0.0D && tr[1] == 0.0D && tr[3] > 0.0D) {
/* 352 */       float x0 = source.getMinX();
/* 353 */       float y0 = source.getMinY();
/* 354 */       float w = source.getWidth();
/* 355 */       float h = source.getHeight();
/* 358 */       float d_x0 = x0 * (float)tr[0] + (float)tr[4];
/* 359 */       float d_y0 = y0 * (float)tr[3] + (float)tr[5];
/* 360 */       float d_w = w * (float)tr[0];
/* 361 */       float d_h = h * (float)tr[3];
/* 363 */       return new Rectangle2D.Float(d_x0, d_y0, d_w, d_h);
/*     */     } 
/* 374 */     float sx0 = source.getMinX();
/* 375 */     float sy0 = source.getMinY();
/* 376 */     float sw = source.getWidth();
/* 377 */     float sh = source.getHeight();
/* 384 */     Point2D[] pts = new Point2D[4];
/* 385 */     pts[0] = new Point2D.Float(sx0, sy0);
/* 386 */     pts[1] = new Point2D.Float(sx0 + sw, sy0);
/* 387 */     pts[2] = new Point2D.Float(sx0 + sw, sy0 + sh);
/* 388 */     pts[3] = new Point2D.Float(sx0, sy0 + sh);
/* 391 */     forward_tr.transform(pts, 0, pts, 0, 4);
/* 393 */     float dx0 = Float.MAX_VALUE;
/* 394 */     float dy0 = Float.MAX_VALUE;
/* 395 */     float dx1 = -3.4028235E38F;
/* 396 */     float dy1 = -3.4028235E38F;
/* 397 */     for (int i = 0; i < 4; i++) {
/* 398 */       float px = (float)pts[i].getX();
/* 399 */       float py = (float)pts[i].getY();
/* 401 */       dx0 = Math.min(dx0, px);
/* 402 */       dy0 = Math.min(dy0, py);
/* 403 */       dx1 = Math.max(dx1, px);
/* 404 */       dy1 = Math.max(dy1, py);
/*     */     } 
/* 411 */     float lw = dx1 - dx0;
/* 412 */     float lh = dy1 - dy0;
/* 414 */     return new Rectangle2D.Float(dx0, dy0, lw, lh);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AffineCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */