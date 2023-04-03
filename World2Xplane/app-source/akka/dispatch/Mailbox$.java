/*    */ package akka.dispatch;
/*    */ 
/*    */ public final class Mailbox$ {
/*    */   public static final Mailbox$ MODULE$;
/*    */   
/*    */   private final int Open;
/*    */   
/*    */   private final int Closed;
/*    */   
/*    */   private final int Scheduled;
/*    */   
/*    */   private final int shouldScheduleMask;
/*    */   
/*    */   private final int shouldNotProcessMask;
/*    */   
/*    */   private final int suspendMask;
/*    */   
/*    */   private final int suspendUnit;
/*    */   
/*    */   private final boolean debug;
/*    */   
/*    */   private Mailbox$() {
/* 22 */     MODULE$ = this;
/* 37 */     this.shouldNotProcessMask = 0x2 ^ 0xFFFFFFFF;
/* 38 */     this.suspendMask = 0x3 ^ 0xFFFFFFFF;
/*    */   }
/*    */   
/*    */   public final int Open() {
/*    */     return 0;
/*    */   }
/*    */   
/*    */   public final int Closed() {
/*    */     return 1;
/*    */   }
/*    */   
/*    */   public final int Scheduled() {
/*    */     return 2;
/*    */   }
/*    */   
/*    */   public final int shouldScheduleMask() {
/*    */     return 3;
/*    */   }
/*    */   
/*    */   public final int shouldNotProcessMask() {
/*    */     return this.shouldNotProcessMask;
/*    */   }
/*    */   
/*    */   public final int suspendMask() {
/* 38 */     return this.suspendMask;
/*    */   }
/*    */   
/*    */   public final int suspendUnit() {
/* 39 */     return 4;
/*    */   }
/*    */   
/*    */   public final boolean debug() {
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Mailbox$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */