/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class PolarToComplexCRIF extends CRIFImpl {
/*    */   public PolarToComplexCRIF() {
/* 37 */     super("polartocomplex");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 50 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 53 */     return (RenderedImage)new PolarToComplexOpImage(paramBlock.getRenderedSource(0), paramBlock.getRenderedSource(1), renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PolarToComplexCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */