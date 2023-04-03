/*    */ package org.openstreetmap.osmosis.core.buffer.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
/*    */ import org.openstreetmap.osmosis.core.store.DataPostbox;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkRunnableSource;
/*    */ 
/*    */ public class EntityBuffer implements SinkRunnableSource {
/*    */   private Sink sink;
/*    */   
/*    */   private DataPostbox<EntityContainer> buffer;
/*    */   
/*    */   public EntityBuffer(int bufferCapacity) {
/* 32 */     this.buffer = new DataPostbox(bufferCapacity);
/*    */   }
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 40 */     this.buffer.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(EntityContainer entityContainer) {
/* 48 */     this.buffer.put(entityContainer);
/*    */   }
/*    */   
/*    */   public void complete() {
/* 56 */     this.buffer.complete();
/*    */   }
/*    */   
/*    */   public void release() {
/* 64 */     this.buffer.release();
/*    */   }
/*    */   
/*    */   public void setSink(Sink sink) {
/* 72 */     this.sink = sink;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 81 */       this.sink.initialize(this.buffer.outputInitialize());
/* 83 */       while (this.buffer.hasNext())
/* 84 */         this.sink.process((EntityContainer)this.buffer.getNext()); 
/* 87 */       this.sink.complete();
/* 88 */       this.buffer.outputComplete();
/*    */     } finally {
/* 91 */       this.sink.release();
/* 92 */       this.buffer.outputRelease();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\buffer\v0_6\EntityBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */