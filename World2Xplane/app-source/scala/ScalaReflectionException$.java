/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ScalaReflectionException$ extends AbstractFunction1<String, ScalaReflectionException> implements Serializable {
/*    */   public static final ScalaReflectionException$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 68 */     return "ScalaReflectionException";
/*    */   }
/*    */   
/*    */   public ScalaReflectionException apply(String msg) {
/* 68 */     return new ScalaReflectionException(msg);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(ScalaReflectionException x$0) {
/* 68 */     return (x$0 == null) ? None$.MODULE$ : new Some<String>(x$0.msg());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 68 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ScalaReflectionException$() {
/* 68 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\ScalaReflectionException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */