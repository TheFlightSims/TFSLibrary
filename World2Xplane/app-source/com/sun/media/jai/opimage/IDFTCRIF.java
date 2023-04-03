/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.EnumeratedParameter;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class IDFTCRIF extends CRIFImpl {
/*    */   public IDFTCRIF() {
/* 36 */     super("idft");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 52 */     EnumeratedParameter scalingType = (EnumeratedParameter)paramBlock.getObjectParameter(0);
/* 54 */     EnumeratedParameter dataNature = (EnumeratedParameter)paramBlock.getObjectParameter(1);
/* 57 */     FFT fft = new FFT(false, new Integer(scalingType.getValue()), 2);
/* 59 */     return (RenderedImage)new DFTOpImage(source, renderHints, layout, dataNature, fft);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\IDFTCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */