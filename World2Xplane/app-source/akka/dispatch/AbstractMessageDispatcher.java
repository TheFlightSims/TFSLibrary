/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ abstract class AbstractMessageDispatcher {
/*    */   static final long shutdownScheduleOffset;
/*    */   
/*    */   static final long inhabitantsOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 15 */       shutdownScheduleOffset = Unsafe.instance.objectFieldOffset(MessageDispatcher.class.getDeclaredField("_shutdownScheduleDoNotCallMeDirectly"));
/* 16 */       inhabitantsOffset = Unsafe.instance.objectFieldOffset(MessageDispatcher.class.getDeclaredField("_inhabitantsDoNotCallMeDirectly"));
/* 17 */     } catch (Throwable throwable) {
/* 18 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\AbstractMessageDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */