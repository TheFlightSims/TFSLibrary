/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class SubtractFromConstCRIF extends CRIFImpl {
/*    */   public SubtractFromConstCRIF() {
/* 34 */     super("subtractfromconst");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 47 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 50 */     return (RenderedImage)new SubtractFromConstOpImage(args.getRenderedSource(0), renderHints, layout, (double[])args.getObjectParameter(0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\SubtractFromConstCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */