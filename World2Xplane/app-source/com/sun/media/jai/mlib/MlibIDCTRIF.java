/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.DCTOpImage;
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ 
/*    */ public class MlibIDCTRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 48 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 51 */     if (!MediaLibAccessor.isMediaLibCompatible(new ParameterBlock()))
/* 52 */       return null; 
/* 55 */     return (RenderedImage)new DCTOpImage(args.getRenderedSource(0), hints, layout, new FCTmediaLib(false, 2));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibIDCTRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */