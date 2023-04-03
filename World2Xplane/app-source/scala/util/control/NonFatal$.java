/*    */ package scala.util.control;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class NonFatal$ {
/*    */   public static final NonFatal$ MODULE$;
/*    */   
/*    */   private NonFatal$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public boolean apply(Throwable t) {
/*    */     boolean bool;
/* 35 */     if (t instanceof StackOverflowError) {
/* 35 */       bool = true;
/*    */     } else {
/*    */       boolean bool1;
/* 38 */       if (t instanceof VirtualMachineError) {
/* 38 */         bool1 = true;
/* 38 */       } else if (t instanceof ThreadDeath) {
/* 38 */         bool1 = true;
/* 38 */       } else if (t instanceof InterruptedException) {
/* 38 */         bool1 = true;
/* 38 */       } else if (t instanceof LinkageError) {
/* 38 */         bool1 = true;
/* 38 */       } else if (t instanceof ControlThrowable) {
/* 38 */         bool1 = true;
/* 38 */       } else if (t instanceof scala.NotImplementedError) {
/* 38 */         bool1 = true;
/*    */       } else {
/* 38 */         bool1 = false;
/*    */       } 
/* 38 */       if (bool1) {
/* 38 */         bool = false;
/*    */       } else {
/* 39 */         bool = true;
/*    */       } 
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public Option<Throwable> unapply(Throwable t) {
/* 44 */     return apply(t) ? (Option<Throwable>)new Some(t) : (Option<Throwable>)scala.None$.MODULE$;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\NonFatal$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */