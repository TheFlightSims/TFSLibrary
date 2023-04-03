/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.DFTOpImage;
/*     */ import com.sun.media.jai.opimage.FFT;
/*     */ import com.sun.media.jai.opimage.RIFUtil;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderedImageFactory;
/*     */ import javax.media.jai.BorderExtender;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.operator.DFTDescriptor;
/*     */ 
/*     */ public class MlibDFTRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*     */     RenderedOp renderedOp;
/*  53 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  56 */     if (!MediaLibAccessor.isMediaLibCompatible(new ParameterBlock()))
/*  57 */       return null; 
/*  60 */     RenderedImage source = args.getRenderedSource(0);
/*  61 */     EnumeratedParameter scalingType = (EnumeratedParameter)args.getObjectParameter(0);
/*  63 */     EnumeratedParameter dataNature = (EnumeratedParameter)args.getObjectParameter(1);
/*  66 */     boolean isComplexSource = !dataNature.equals(DFTDescriptor.REAL_TO_COMPLEX);
/*  68 */     int numSourceBands = source.getSampleModel().getNumBands();
/*  74 */     if (((isComplexSource && numSourceBands == 2) || (!isComplexSource && numSourceBands == 1)) && MlibDFTOpImage.isAcceptableSampleModel(source.getSampleModel())) {
/*  79 */       int sourceWidth = source.getWidth();
/*  80 */       int sourceHeight = source.getHeight();
/*  81 */       if (!MathJAI.isPositivePowerOf2(sourceWidth) || !MathJAI.isPositivePowerOf2(sourceHeight)) {
/*  83 */         ParameterBlock pb = new ParameterBlock();
/*  84 */         pb.addSource(source);
/*  85 */         pb.add(0);
/*  86 */         pb.add(MathJAI.nextPositivePowerOf2(sourceWidth) - sourceWidth);
/*  88 */         pb.add(0);
/*  89 */         pb.add(MathJAI.nextPositivePowerOf2(sourceHeight) - sourceHeight);
/*  91 */         pb.add(BorderExtender.createInstance(0));
/*  92 */         renderedOp = JAI.create("border", pb, null);
/*     */       } 
/*  95 */       return (RenderedImage)new MlibDFTOpImage((RenderedImage)renderedOp, hints, layout, dataNature, true, scalingType);
/*     */     } 
/*  98 */     FFT fft = new FFTmediaLib(true, new Integer(scalingType.getValue()), 2);
/* 101 */     return (RenderedImage)new DFTOpImage((RenderedImage)renderedOp, hints, layout, dataNature, fft);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDFTRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */