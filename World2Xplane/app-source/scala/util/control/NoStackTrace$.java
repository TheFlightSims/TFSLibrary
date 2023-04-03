/*    */ package scala.util.control;
/*    */ 
/*    */ import scala.Serializable;
/*    */ 
/*    */ public final class NoStackTrace$ implements Serializable {
/*    */   public static final NoStackTrace$ MODULE$;
/*    */   
/*    */   private boolean _noSuppression;
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private NoStackTrace$() {
/* 26 */     MODULE$ = this;
/* 30 */     this._noSuppression = false;
/* 31 */     _noSuppression_$eq(scala.sys.SystemProperties$.MODULE$.noTraceSupression().value());
/*    */   }
/*    */   
/*    */   public final boolean noSuppression() {
/*    */     return _noSuppression();
/*    */   }
/*    */   
/*    */   private final boolean _noSuppression() {
/*    */     return this._noSuppression;
/*    */   }
/*    */   
/*    */   private final void _noSuppression_$eq(boolean x$1) {
/*    */     this._noSuppression = x$1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\NoStackTrace$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */