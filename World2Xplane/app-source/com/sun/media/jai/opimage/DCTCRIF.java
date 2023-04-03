/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class DCTCRIF extends CRIFImpl {
/*    */   public DCTCRIF() {
/* 33 */     super("dct");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 49 */     return (RenderedImage)new DCTOpImage(source, renderHints, layout, new FCT(true, 2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DCTCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */