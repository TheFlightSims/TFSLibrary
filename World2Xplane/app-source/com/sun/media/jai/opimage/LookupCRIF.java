/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.LookupTableJAI;
/*    */ 
/*    */ public class LookupCRIF extends CRIFImpl {
/*    */   public LookupCRIF() {
/* 41 */     super("lookup");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 54 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 57 */     return (RenderedImage)new LookupOpImage(args.getRenderedSource(0), renderHints, layout, (LookupTableJAI)args.getObjectParameter(0));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\LookupCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */