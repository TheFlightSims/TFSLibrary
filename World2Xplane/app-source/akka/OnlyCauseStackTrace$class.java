/*    */ package akka;
/*    */ 
/*    */ import scala.Array$;
/*    */ import scala.reflect.ClassTag$;
/*    */ 
/*    */ public abstract class OnlyCauseStackTrace$class {
/*    */   public static void $init$(Throwable $this) {}
/*    */   
/*    */   public static Throwable fillInStackTrace(Throwable $this) {
/*    */     StackTraceElement[] arrayOfStackTraceElement;
/* 25 */     Throwable throwable = $this.getCause();
/* 26 */     if (throwable == null) {
/* 26 */       arrayOfStackTraceElement = (StackTraceElement[])Array$.MODULE$.empty(ClassTag$.MODULE$.apply(StackTraceElement.class));
/*    */     } else {
/* 27 */       arrayOfStackTraceElement = throwable.getStackTrace();
/*    */     } 
/*    */     $this.setStackTrace(arrayOfStackTraceElement);
/* 29 */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\OnlyCauseStackTrace$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */