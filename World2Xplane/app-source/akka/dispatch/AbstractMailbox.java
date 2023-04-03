/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.util.Unsafe;
/*    */ 
/*    */ final class AbstractMailbox {
/*    */   static final long mailboxStatusOffset;
/*    */   
/*    */   static final long systemMessageOffset;
/*    */   
/*    */   static {
/*    */     try {
/* 15 */       mailboxStatusOffset = Unsafe.instance.objectFieldOffset(Mailbox.class.getDeclaredField("_statusDoNotCallMeDirectly"));
/* 16 */       systemMessageOffset = Unsafe.instance.objectFieldOffset(Mailbox.class.getDeclaredField("_systemQueueDoNotCallMeDirectly"));
/* 17 */     } catch (Throwable throwable) {
/* 18 */       throw new ExceptionInInitializerError(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\AbstractMailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */