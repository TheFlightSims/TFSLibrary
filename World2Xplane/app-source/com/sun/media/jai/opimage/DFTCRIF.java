/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.EnumeratedParameter;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class DFTCRIF extends CRIFImpl {
/*    */   public DFTCRIF() {
/* 35 */     super("dft");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 46 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 49 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 50 */     EnumeratedParameter scalingType = (EnumeratedParameter)paramBlock.getObjectParameter(0);
/* 52 */     EnumeratedParameter dataNature = (EnumeratedParameter)paramBlock.getObjectParameter(1);
/* 55 */     FFT fft = new FFT(true, new Integer(scalingType.getValue()), 2);
/* 57 */     return (RenderedImage)new DFTOpImage(source, renderHints, layout, dataNature, fft);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DFTCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */