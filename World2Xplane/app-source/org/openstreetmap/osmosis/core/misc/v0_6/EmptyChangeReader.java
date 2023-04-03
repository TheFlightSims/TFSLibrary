/*    */ package org.openstreetmap.osmosis.core.misc.v0_6;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.RunnableChangeSource;
/*    */ 
/*    */ public class EmptyChangeReader implements RunnableChangeSource {
/*    */   private ChangeSink changeSink;
/*    */   
/*    */   public void setChangeSink(ChangeSink changeSink) {
/* 24 */     this.changeSink = changeSink;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/* 34 */       this.changeSink.initialize(Collections.emptyMap());
/* 35 */       this.changeSink.complete();
/*    */     } finally {
/* 37 */       this.changeSink.release();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\misc\v0_6\EmptyChangeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */