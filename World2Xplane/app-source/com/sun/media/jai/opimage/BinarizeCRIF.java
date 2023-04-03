/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class BinarizeCRIF extends CRIFImpl {
/*    */   public BinarizeCRIF() {
/* 32 */     super("binarize");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 45 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 47 */     return (RenderedImage)new BinarizeOpImage(args.getRenderedSource(0), renderHints, layout, args.getDoubleParameter(0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BinarizeCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */