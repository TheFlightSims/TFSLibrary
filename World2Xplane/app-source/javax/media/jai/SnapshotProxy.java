/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ 
/*    */ final class SnapshotProxy extends PlanarImage {
/*    */   Snapshot parent;
/*    */   
/*    */   SnapshotProxy(Snapshot parent) {
/* 77 */     super(new ImageLayout(parent), null, null);
/* 78 */     this.parent = parent;
/*    */   }
/*    */   
/*    */   public Raster getTile(int tileX, int tileY) {
/* 89 */     return this.parent.getTile(tileX, tileY);
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 94 */     this.parent.dispose();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\SnapshotProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */