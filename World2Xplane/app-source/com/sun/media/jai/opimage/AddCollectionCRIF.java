/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.util.Collection;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class AddCollectionCRIF extends CRIFImpl {
/*    */   public AddCollectionCRIF() {
/* 35 */     super("addcollection");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 51 */     return (RenderedImage)new AddCollectionOpImage((Collection)args.getSource(0), renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\AddCollectionCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */