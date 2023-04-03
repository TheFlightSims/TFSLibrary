/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Way;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class WayContainerIterator implements ReleasableIterator<WayContainer> {
/*    */   private ReleasableIterator<Way> source;
/*    */   
/*    */   public WayContainerIterator(ReleasableIterator<Way> source) {
/* 23 */     this.source = source;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 32 */     return this.source.hasNext();
/*    */   }
/*    */   
/*    */   public WayContainer next() {
/* 40 */     return new WayContainer((Way)this.source.next());
/*    */   }
/*    */   
/*    */   public void remove() {
/* 48 */     this.source.remove();
/*    */   }
/*    */   
/*    */   public void release() {
/* 57 */     this.source.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\WayContainerIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */