/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.util.Vector;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class BandMergeCRIF extends CRIFImpl {
/*    */   public BandMergeCRIF() {
/* 36 */     super("bandmerge");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 50 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 54 */     Vector sources = paramBlock.getSources();
/* 57 */     return (RenderedImage)new BandMergeOpImage(sources, renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\BandMergeCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */