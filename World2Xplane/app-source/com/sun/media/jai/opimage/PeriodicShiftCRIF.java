/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class PeriodicShiftCRIF extends CRIFImpl {
/*    */   public PeriodicShiftCRIF() {
/* 40 */     super("periodicshift");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 51 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 55 */     RenderedImage source = paramBlock.getRenderedSource(0);
/* 58 */     int shiftX = paramBlock.getIntParameter(0);
/* 59 */     int shiftY = paramBlock.getIntParameter(1);
/* 62 */     return (RenderedImage)new PeriodicShiftOpImage(source, renderHints, layout, shiftX, shiftY);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\PeriodicShiftCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */