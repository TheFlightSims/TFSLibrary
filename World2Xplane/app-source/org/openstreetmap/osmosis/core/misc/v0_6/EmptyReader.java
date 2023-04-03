/*    */ package org.openstreetmap.osmosis.core.misc.v0_6;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.RunnableSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ 
/*    */ public class EmptyReader implements RunnableSource {
/*    */   private Sink sink;
/*    */   
/*    */   public void setSink(Sink sink) {
/* 24 */     this.sink = sink;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 34 */       this.sink.initialize(Collections.emptyMap());
/* 35 */       this.sink.complete();
/*    */     } finally {
/* 37 */       this.sink.release();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\misc\v0_6\EmptyReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */