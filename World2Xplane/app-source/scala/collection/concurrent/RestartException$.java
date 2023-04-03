/*      */ package scala.collection.concurrent;
/*      */ 
/*      */ import scala.util.control.ControlThrowable;
/*      */ import scala.util.control.NoStackTrace;
/*      */ 
/*      */ public final class RestartException$ extends Throwable implements ControlThrowable {
/*      */   public static final RestartException$ MODULE$;
/*      */   
/*      */   public Throwable scala$util$control$NoStackTrace$$super$fillInStackTrace() {
/* 1058 */     return super.fillInStackTrace();
/*      */   }
/*      */   
/*      */   public Throwable fillInStackTrace() {
/* 1058 */     return NoStackTrace.class.fillInStackTrace((NoStackTrace)this);
/*      */   }
/*      */   
/*      */   private Object readResolve() {
/* 1058 */     return MODULE$;
/*      */   }
/*      */   
/*      */   private RestartException$() {
/* 1058 */     MODULE$ = this;
/* 1058 */     NoStackTrace.class.$init$((NoStackTrace)this);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\RestartException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */