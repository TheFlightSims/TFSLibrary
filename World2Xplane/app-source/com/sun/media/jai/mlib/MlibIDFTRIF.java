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
/*     */ public class MlibIDFTRIF implements RenderedImageFactory {
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/*     */     RenderedOp renderedOp;
/*  54 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/*  57 */     if (!MediaLibAccessor.isMediaLibCompatible(new ParameterBlock()))
/*  58 */       return null; 
/*  61 */     RenderedImage source = args.getRenderedSource(0);
/*  62 */     EnumeratedParameter scalingType = (EnumeratedParameter)args.getObjectParameter(0);
/*  64 */     EnumeratedParameter dataNature = (EnumeratedParameter)args.getObjectParameter(1);
/*  67 */     boolean isComplexSource = !dataNature.equals(DFTDescriptor.REAL_TO_COMPLEX);
/*  69 */     int numSourceBands = source.getSampleModel().getNumBands();
/*  75 */     if (((isComplexSource && numSourceBands == 2) || (!isComplexSource && numSourceBands == 1)) && MlibDFTOpImage.isAcceptableSampleModel(source.getSampleModel())) {
/*  80 */       int sourceWidth = source.getWidth();
/*  81 */       int sourceHeight = source.getHeight();
/*  82 */       if (!MathJAI.isPositivePowerOf2(sourceWidth) || !MathJAI.isPositivePowerOf2(sourceHeight)) {
/*  84 */         ParameterBlock pb = new ParameterBlock();
/*  85 */         pb.addSource(source);
/*  86 */         pb.add(0);
/*  87 */         pb.add(MathJAI.nextPositivePowerOf2(sourceWidth) - sourceWidth);
/*  89 */         pb.add(0);
/*  90 */         pb.add(MathJAI.nextPositivePowerOf2(sourceHeight) - sourceHeight);
/*  92 */         pb.add(BorderExtender.createInstance(0));
/*  93 */         renderedOp = JAI.create("border", pb);
/*     */       } 
/*  96 */       return (RenderedImage)new MlibDFTOpImage((RenderedImage)renderedOp, hints, layout, dataNature, false, scalingType);
/*     */     } 
/*  99 */     FFT fft = new FFTmediaLib(false, new Integer(scalingType.getValue()), 2);
/* 102 */     return (RenderedImage)new DFTOpImage((RenderedImage)renderedOp, hints, layout, dataNature, fft);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibIDFTRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */