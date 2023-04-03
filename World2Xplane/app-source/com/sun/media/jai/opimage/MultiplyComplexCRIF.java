/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MultiplyComplexCRIF extends CRIFImpl {
/*    */   public MultiplyComplexCRIF() {
/* 36 */     super("multiplycomplex");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 49 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 52 */     return (RenderedImage)new ComplexArithmeticOpImage(paramBlock.getRenderedSource(0), paramBlock.getRenderedSource(1), renderHints, layout, false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MultiplyComplexCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */