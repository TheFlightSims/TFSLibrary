/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MagnitudeSquaredCRIF extends CRIFImpl {
/*    */   public MagnitudeSquaredCRIF() {
/* 34 */     super("magnitudesquared");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 48 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 50 */     return (RenderedImage)new MagnitudePhaseOpImage(source, renderHints, layout, 2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MagnitudeSquaredCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */