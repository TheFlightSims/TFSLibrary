/*    */ package org.openstreetmap.osmosis.core.util;
/*    */ 
/*    */ public class TileCalculator {
/*    */   public long calculateTile(double latitude, double longitude) {
/* 34 */     int x = (int)Math.round((longitude + 180.0D) * 65535.0D / 360.0D);
/* 35 */     int y = (int)Math.round((latitude + 90.0D) * 65535.0D / 180.0D);
/* 37 */     long tile = 0L;
/* 39 */     for (int i = 15; i >= 0; i--) {
/* 40 */       tile = tile << 1L | (x >> i & 0x1);
/* 41 */       tile = tile << 1L | (y >> i & 0x1);
/*    */     } 
/* 44 */     return tile;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\TileCalculator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */