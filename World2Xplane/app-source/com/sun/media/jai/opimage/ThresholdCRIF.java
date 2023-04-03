/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class ThresholdCRIF extends CRIFImpl {
/*    */   public ThresholdCRIF() {
/* 34 */     super("threshold");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 47 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 50 */     return (RenderedImage)new ThresholdOpImage(args.getRenderedSource(0), renderHints, layout, (double[])args.getObjectParameter(0), (double[])args.getObjectParameter(1), (double[])args.getObjectParameter(2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ThresholdCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */