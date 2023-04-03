/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class IDCTCRIF extends CRIFImpl {
/*    */   public IDCTCRIF() {
/* 33 */     super("idct");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 44 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 49 */     return (RenderedImage)new DCTOpImage(source, renderHints, layout, new FCT(false, 2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\IDCTCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */