/*    */ package com.sun.media.jai.mlib;
/*    */ 
/*    */ import com.sun.media.jai.opimage.RIFUtil;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderedImageFactory;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.LookupTableJAI;
/*    */ 
/*    */ public class MlibLookupRIF implements RenderedImageFactory {
/*    */   public RenderedImage create(ParameterBlock args, RenderingHints hints) {
/* 46 */     ImageLayout layout = RIFUtil.getImageLayoutHint(hints);
/* 49 */     if (!MediaLibAccessor.isMediaLibCompatible(args))
/* 50 */       return null; 
/* 54 */     LookupTableJAI table = (LookupTableJAI)args.getObjectParameter(0);
/* 55 */     if (table.getNumBands() > 4 || table.getDataType() == 1)
/* 57 */       return null; 
/* 60 */     return (RenderedImage)new MlibLookupOpImage(args.getRenderedSource(0), hints, layout, table);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibLookupRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */