/*    */ package scala.util.control;
/*    */ 
/*    */ public abstract class NoStackTrace$class {
/*    */   public static void $init$(NoStackTrace $this) {}
/*    */   
/*    */   public static Throwable fillInStackTrace(NoStackTrace $this) {
/* 22 */     return NoStackTrace$.MODULE$.noSuppression() ? $this.scala$util$control$NoStackTrace$$super$fillInStackTrace() : 
/* 23 */       (Throwable)$this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\NoStackTrace$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */