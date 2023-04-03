/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.KernelJAI;
/*     */ 
/*     */ public class MlibDilateRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*  45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  48 */     boolean isBinary = false;
/*  49 */     if (!MediaLibAccessor.isMediaLibCompatible(args, layout) || !MediaLibAccessor.hasSameNumBands(args, layout)) {
/*  51 */       if (!MediaLibAccessor.isMediaLibBinaryCompatible(args, layout))
/*  52 */         return null; 
/*  54 */       isBinary = true;
/*     */     } 
/*  58 */     BorderExtender extender = RIFUtil.getBorderExtenderHint(hints);
/*  60 */     RenderedImage source = args.getRenderedSource(0);
/*  62 */     KernelJAI unRotatedKernel = (KernelJAI)args.getObjectParameter(0);
/*  63 */     KernelJAI kJAI = unRotatedKernel.getRotatedKernel();
/*  65 */     int kWidth = kJAI.getWidth();
/*  66 */     int kHeight = kJAI.getHeight();
/*  67 */     int xOri = kJAI.getXOrigin();
/*  68 */     int yOri = kJAI.getYOrigin();
/*  69 */     int numB = source.getSampleModel().getNumBands();
/*  73 */     if (xOri != 1 || yOri != 1 || kWidth != 3 || kHeight != 3 || numB != 1)
/*  74 */       return null; 
/*  79 */     float[] kdata = kJAI.getKernelData();
/*  81 */     if ((isBinary && isKernel3Square1(kdata)) || (!isBinary && isKernel3Square0(kdata)))
/*  83 */       return (RenderedImage)new MlibDilate3SquareOpImage(source, extender, hints, layout); 
/*  88 */     if (isBinary && isKernel3Plus1(kdata))
/*  91 */       return (RenderedImage)new MlibDilate3PlusOpImage(source, extender, hints, layout); 
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isKernel3Plus1(float[] kdata) {
/* 103 */     return (kdata[0] == 0.0F && kdata[1] == 1.0F && kdata[2] == 0.0F && kdata[3] == 1.0F && kdata[4] == 1.0F && kdata[5] == 1.0F && kdata[6] == 0.0F && kdata[7] == 1.0F && kdata[8] == 0.0F);
/*     */   }
/*     */   
/*     */   private boolean isKernel3Square0(float[] kdata) {
/* 111 */     return (kdata[0] == 0.0F && kdata[1] == 0.0F && kdata[2] == 0.0F && kdata[3] == 0.0F && kdata[4] == 0.0F && kdata[5] == 0.0F && kdata[6] == 0.0F && kdata[7] == 0.0F && kdata[8] == 0.0F);
/*     */   }
/*     */   
/*     */   private boolean isKernel3Square1(float[] kdata) {
/* 119 */     return (kdata[0] == 1.0F && kdata[1] == 1.0F && kdata[2] == 1.0F && kdata[3] == 1.0F && kdata[4] == 1.0F && kdata[5] == 1.0F && kdata[6] == 1.0F && kdata[7] == 1.0F && kdata[8] == 1.0F);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDilateRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */