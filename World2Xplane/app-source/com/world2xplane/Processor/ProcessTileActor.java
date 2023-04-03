/*    */ package com.world2xplane.Processor;
/*    */ 
/*    */ import akka.actor.Props;
/*    */ import akka.actor.UntypedActor;
/*    */ import akka.japi.Creator;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ProcessTileActor extends UntypedActor {
/* 33 */   private static Logger log = LoggerFactory.getLogger(ProcessTileActor.class);
/*    */   
/*    */   public static Props props() {
/* 37 */     return Props.create(new Creator<ProcessTileActor>() {
/*    */           private static final long serialVersionUID = 1L;
/*    */           
/*    */           public ProcessTileActor create() throws Exception {
/* 42 */             return new ProcessTileActor();
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public void onReceive(Object message) throws Exception {
/* 49 */     if (message instanceof ProcessTile) {
/* 50 */       log.info(((ProcessTile)message).getTileToProcess());
/* 51 */       sender().tell(new Finished(null), getSelf());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Processor\ProcessTileActor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */