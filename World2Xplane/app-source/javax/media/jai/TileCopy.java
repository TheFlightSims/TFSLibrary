/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ 
/*    */ final class TileCopy {
/*    */   Raster tile;
/*    */   
/*    */   int tileX;
/*    */   
/*    */   int tileY;
/*    */   
/*    */   TileCopy(Raster tile, int tileX, int tileY) {
/* 46 */     this.tile = tile;
/* 47 */     this.tileX = tileX;
/* 48 */     this.tileY = tileY;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\TileCopy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */