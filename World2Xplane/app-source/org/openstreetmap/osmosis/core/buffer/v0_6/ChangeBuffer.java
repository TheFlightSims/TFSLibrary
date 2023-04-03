/*    */ package org.openstreetmap.osmosis.core.buffer.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.container.v0_6.ChangeContainer;
/*    */ import org.openstreetmap.osmosis.core.store.DataPostbox;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkRunnableChangeSource;
/*    */ 
/*    */ public class ChangeBuffer implements ChangeSinkRunnableChangeSource {
/*    */   private ChangeSink changeSink;
/*    */   
/*    */   private DataPostbox<ChangeContainer> buffer;
/*    */   
/*    */   public ChangeBuffer(int bufferCapacity) {
/* 32 */     this.buffer = new DataPostbox(bufferCapacity);
/*    */   }
/*    */   
/*    */   public void initialize(Map<String, Object> metaData) {
/* 40 */     this.buffer.initialize(metaData);
/*    */   }
/*    */   
/*    */   public void process(ChangeContainer changeContainer) {
/* 48 */     this.buffer.put(changeContainer);
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
/*    */   public void setChangeSink(ChangeSink changeSink) {
/* 72 */     this.changeSink = changeSink;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 81 */       this.changeSink.initialize(this.buffer.outputInitialize());
/* 83 */       while (this.buffer.hasNext())
/* 84 */         this.changeSink.process((ChangeContainer)this.buffer.getNext()); 
/* 87 */       this.changeSink.complete();
/* 88 */       this.buffer.outputComplete();
/*    */     } finally {
/* 91 */       this.changeSink.release();
/* 92 */       this.buffer.outputRelease();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\buffer\v0_6\ChangeBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */