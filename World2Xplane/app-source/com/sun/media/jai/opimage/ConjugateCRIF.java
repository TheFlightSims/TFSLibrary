/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class ConjugateCRIF extends CRIFImpl {
/*    */   public ConjugateCRIF() {
/* 33 */     super("conjugate");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 48 */     return (RenderedImage)new ConjugateOpImage(paramBlock.getRenderedSource(0), renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConjugateCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */