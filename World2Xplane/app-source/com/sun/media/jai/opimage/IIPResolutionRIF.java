/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ 
/*    */ public class IIPResolutionRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 42 */     return (RenderedImage)new IIPResolutionOpImage(hints, (String)args.getObjectParameter(0), args.getIntParameter(1), args.getIntParameter(2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\IIPResolutionRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */