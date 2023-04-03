/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class XorCRIF extends CRIFImpl {
/*    */   public XorCRIF() {
/* 36 */     super("xor");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 49 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 52 */     return (RenderedImage)new XorOpImage(paramBlock.getRenderedSource(0), paramBlock.getRenderedSource(1), renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\XorCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */