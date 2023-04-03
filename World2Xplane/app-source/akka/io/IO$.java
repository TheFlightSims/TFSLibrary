/*    */ package akka.io;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.ExtensionId;
/*    */ 
/*    */ public final class IO$ {
/*    */   public static final IO$ MODULE$;
/*    */   
/*    */   private IO$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T extends IO.Extension> ActorRef apply(ExtensionId key, ActorSystem system) {
/* 30 */     return ((IO.Extension)key.apply(system)).manager();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\IO$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */