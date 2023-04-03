/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ class AbstractCircuitBreaker {
/*    */   protected static final long stateOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 13 */       stateOffset = Unsafe.instance.objectFieldOffset(CircuitBreaker.class.getDeclaredField("_currentStateDoNotCallMeDirectly"));
/* 14 */     } catch (Throwable throwable) {
/* 15 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AbstractCircuitBreaker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */