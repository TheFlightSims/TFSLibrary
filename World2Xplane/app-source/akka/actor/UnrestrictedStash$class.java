/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ 
/*    */ public abstract class UnrestrictedStash$class {
/*    */   public static void $init$(UnrestrictedStash $this) {}
/*    */   
/*    */   public static void preRestart(UnrestrictedStash $this, Throwable reason, Option<Object> message) {
/*    */     try {
/* 70 */       $this.unstashAll();
/*    */       return;
/*    */     } finally {
/* 70 */       $this.akka$actor$UnrestrictedStash$$super$preRestart(reason, message);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void postStop(UnrestrictedStash $this) {
/*    */     try {
/* 78 */       $this.unstashAll();
/*    */       return;
/*    */     } finally {
/* 78 */       $this.akka$actor$UnrestrictedStash$$super$postStop();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnrestrictedStash$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */