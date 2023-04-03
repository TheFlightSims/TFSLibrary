/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ShutdownHookThread$ {
/*    */   public static final ShutdownHookThread$ MODULE$;
/*    */   
/*    */   private int hookNameCount;
/*    */   
/*    */   private ShutdownHookThread$() {
/* 22 */     MODULE$ = this;
/* 23 */     this.hookNameCount = 0;
/*    */   }
/*    */   
/*    */   private int hookNameCount() {
/* 23 */     return this.hookNameCount;
/*    */   }
/*    */   
/*    */   private void hookNameCount_$eq(int x$1) {
/* 23 */     this.hookNameCount = x$1;
/*    */   }
/*    */   
/*    */   public synchronized String scala$sys$ShutdownHookThread$$hookName() {
/* 25 */     hookNameCount_$eq(hookNameCount() + 1);
/* 26 */     return (new StringBuilder()).append("shutdownHook").append(BoxesRunTime.boxToInteger(hookNameCount())).toString();
/*    */   }
/*    */   
/*    */   public ShutdownHookThread apply(Function0 body) {
/* 32 */     ShutdownHookThread t = new ShutdownHookThread$$anon$1(body);
/* 35 */     package$.MODULE$.runtime().addShutdownHook(t);
/* 36 */     return t;
/*    */   }
/*    */   
/*    */   public static class ShutdownHookThread$$anon$1 extends ShutdownHookThread {
/*    */     private final Function0 body$1;
/*    */     
/*    */     public ShutdownHookThread$$anon$1(Function0 body$1) {
/*    */       super(ShutdownHookThread$.MODULE$.scala$sys$ShutdownHookThread$$hookName());
/*    */     }
/*    */     
/*    */     public void run() {
/*    */       this.body$1.apply$mcV$sp();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\ShutdownHookThread$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */