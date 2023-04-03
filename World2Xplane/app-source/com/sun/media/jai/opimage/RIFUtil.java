/*    */ package com.sun.media.jai.opimage;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import javax.media.jai.BorderExtender;
/*    */ import javax.media.jai.ImageLayout;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.TileCache;
/*    */ 
/*    */ public class RIFUtil {
/*    */   public static ImageLayout getImageLayoutHint(RenderingHints renderHints) {
/* 22 */     if (renderHints == null)
/* 23 */       return null; 
/* 25 */     return (ImageLayout)renderHints.get(JAI.KEY_IMAGE_LAYOUT);
/*    */   }
/*    */   
/*    */   public static TileCache getTileCacheHint(RenderingHints renderHints) {
/* 30 */     if (renderHints == null)
/* 31 */       return null; 
/* 33 */     return (TileCache)renderHints.get(JAI.KEY_TILE_CACHE);
/*    */   }
/*    */   
/*    */   public static BorderExtender getBorderExtenderHint(RenderingHints renderHints) {
/* 39 */     if (renderHints == null)
/* 40 */       return null; 
/* 42 */     return (BorderExtender)renderHints.get(JAI.KEY_BORDER_EXTENDER);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\RIFUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */