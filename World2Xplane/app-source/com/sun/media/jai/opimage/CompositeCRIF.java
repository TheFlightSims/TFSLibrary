/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.operator.CompositeDescriptor;
/*     */ 
/*     */ public class CompositeCRIF extends CRIFImpl {
/*     */   public CompositeCRIF() {
/*  38 */     super("composite");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  49 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  51 */     RenderedImage source1 = args.getRenderedSource(0);
/*  52 */     RenderedImage source2 = args.getRenderedSource(1);
/*  54 */     RenderedImage alpha1 = (RenderedImage)args.getObjectParameter(0);
/*  55 */     RenderedImage alpha2 = null;
/*  56 */     if (args.getObjectParameter(1) != null)
/*  57 */       alpha2 = (RenderedImage)args.getObjectParameter(1); 
/*  60 */     boolean premultiplied = ((Boolean)args.getObjectParameter(2)).booleanValue();
/*  62 */     EnumeratedParameter destAlpha = (EnumeratedParameter)args.getObjectParameter(3);
/*  65 */     if (destAlpha.equals(CompositeDescriptor.NO_DESTINATION_ALPHA))
/*  66 */       return (RenderedImage)new CompositeNoDestAlphaOpImage(source1, source2, hints, layout, alpha1, alpha2, premultiplied); 
/*  71 */     return (RenderedImage)new CompositeOpImage(source1, source2, hints, layout, alpha1, alpha2, premultiplied, destAlpha.equals(CompositeDescriptor.DESTINATION_ALPHA_FIRST));
/*     */   }
/*     */   
/*     */   public RenderedImage create(RenderContext renderContext, ParameterBlock paramBlock) {
/*  91 */     RenderableImage alphaImage1 = (RenderableImage)paramBlock.getObjectParameter(0);
/*  93 */     RenderableImage alphaImage2 = (RenderableImage)paramBlock.getObjectParameter(1);
/*  97 */     RenderedImage rAlphaImage1 = alphaImage1.createRendering(renderContext);
/*  99 */     RenderedImage rAlphaImage2 = alphaImage2.createRendering(renderContext);
/* 102 */     ParameterBlock newPB = (ParameterBlock)paramBlock.clone();
/* 106 */     newPB.set(rAlphaImage1, 0);
/* 107 */     newPB.set(rAlphaImage2, 1);
/* 110 */     return (RenderedImage)JAI.create("composite", newPB, renderContext.getRenderingHints());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\CompositeCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */