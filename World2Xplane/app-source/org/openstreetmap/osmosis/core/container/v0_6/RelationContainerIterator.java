/*    */ package org.openstreetmap.osmosis.core.container.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class RelationContainerIterator implements ReleasableIterator<RelationContainer> {
/*    */   private ReleasableIterator<Relation> source;
/*    */   
/*    */   public RelationContainerIterator(ReleasableIterator<Relation> source) {
/* 23 */     this.source = source;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 32 */     return this.source.hasNext();
/*    */   }
/*    */   
/*    */   public RelationContainer next() {
/* 40 */     return new RelationContainer((Relation)this.source.next());
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\container\v0_6\RelationContainerIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */