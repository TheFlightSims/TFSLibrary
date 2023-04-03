/*    */ package com.world2xplane.Processor;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.Props;
/*    */ import akka.actor.UntypedActor;
/*    */ import akka.japi.Creator;
/*    */ import java.util.List;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ProcessGroupActor extends UntypedActor {
/* 36 */   private static Logger log = LoggerFactory.getLogger(ProcessGroupActor.class);
/*    */   
/*    */   private final List<String> tiles;
/*    */   
/* 38 */   private int count = 0;
/*    */   
/*    */   public ProcessGroupActor(List<String> tiles) {
/* 41 */     this.tiles = tiles;
/*    */   }
/*    */   
/*    */   public static Props props(final List<String> tiles) {
/* 45 */     return Props.create(new Creator<ProcessGroupActor>() {
/*    */           public ProcessGroupActor create() throws Exception {
/* 49 */             return new ProcessGroupActor(tiles);
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public void onReceive(Object message) throws Exception {
/* 57 */     if (message instanceof ProcessGroup)
/* 58 */       for (String item : this.tiles) {
/* 59 */         ActorRef ref = getContext().actorOf(ProcessTileActor.props(), "P:" + item);
/* 60 */         ref.tell(new ProcessTile(item), getSelf());
/*    */       }  
/* 63 */     if (message instanceof Finished) {
/* 64 */       this.count++;
/* 65 */       if (this.count == this.tiles.size())
/* 66 */         getSender().tell(new Finished(null), getSelf()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Processor\ProcessGroupActor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */