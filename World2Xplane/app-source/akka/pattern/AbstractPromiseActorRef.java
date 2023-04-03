/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ final class AbstractPromiseActorRef {
/*    */   static final long stateOffset;
/*    */   
/*    */   static final long watchedByOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 15 */       stateOffset = Unsafe.instance.objectFieldOffset(PromiseActorRef.class.getDeclaredField("_stateDoNotCallMeDirectly"));
/* 16 */       watchedByOffset = Unsafe.instance.objectFieldOffset(PromiseActorRef.class.getDeclaredField("_watchedByDoNotCallMeDirectly"));
/* 17 */     } catch (Throwable throwable) {
/* 18 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AbstractPromiseActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */