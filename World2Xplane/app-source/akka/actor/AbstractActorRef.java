/*    */ package akka.actor;
/*    */ 
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ final class AbstractActorRef {
/*    */   static final long cellOffset;
/*    */   
/*    */   static final long lookupOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 15 */       cellOffset = Unsafe.instance.objectFieldOffset(RepointableActorRef.class.getDeclaredField("_cellDoNotCallMeDirectly"));
/* 16 */       lookupOffset = Unsafe.instance.objectFieldOffset(RepointableActorRef.class.getDeclaredField("_lookupDoNotCallMeDirectly"));
/* 17 */     } catch (Throwable throwable) {
/* 18 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */