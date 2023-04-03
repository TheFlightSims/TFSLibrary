/*    */ package org.openstreetmap.osmosis.core.bound.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.BoundContainer;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
/*    */ import org.openstreetmap.osmosis.core.domain.v0_6.EntityType;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;
/*    */ 
/*    */ public class BoundSetter implements SinkSource {
/*    */   private Sink sink;
/*    */   
/*    */   private boolean boundProcessed;
/*    */   
/*    */   private Bound newBound;
/*    */   
/*    */   public BoundSetter(Bound newBound) {
/* 34 */     this.newBound = newBound;
/* 35 */     this.boundProcessed = false;
/*    */   }
/*    */   
/*    */   public void initialize(Map<String, Object> metaTags) {
/* 41 */     this.sink.initialize(metaTags);
/*    */   }
/*    */   
/*    */   public void process(EntityContainer entityContainer) {
/* 47 */     if (this.boundProcessed) {
/* 48 */       this.sink.process(entityContainer);
/*    */     } else {
/* 51 */       processFirstEntity(entityContainer);
/* 52 */       this.boundProcessed = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void processFirstEntity(EntityContainer entityContainer) {
/* 58 */     if (entityContainer.getEntity().getType() == EntityType.Bound) {
/* 59 */       if (this.newBound == null)
/*    */         return; 
/* 63 */       this.sink.process((EntityContainer)new BoundContainer(this.newBound));
/*    */     } else {
/* 66 */       if (this.newBound != null)
/* 67 */         this.sink.process((EntityContainer)new BoundContainer(this.newBound)); 
/* 69 */       this.sink.process(entityContainer);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void complete() {
/* 76 */     this.sink.complete();
/*    */   }
/*    */   
/*    */   public void release() {
/* 82 */     this.sink.release();
/*    */   }
/*    */   
/*    */   public void setSink(Sink sink) {
/* 88 */     this.sink = sink;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\bound\v0_6\BoundSetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */