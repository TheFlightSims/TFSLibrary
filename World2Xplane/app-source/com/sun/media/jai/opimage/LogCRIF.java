/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import javax.media.jai.CRIFImpl;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class LogCRIF extends CRIFImpl {
/*    */   public LogCRIF() {
/* 39 */     super("log");
/*    */   }
/*    */   
/*    */   public RenderedImage create(ParameterBlock pb, RenderingHints renderHints) {
/* 52 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 55 */     return (RenderedImage)new LogOpImage(pb.getRenderedSource(0), renderHints, layout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\LogCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */