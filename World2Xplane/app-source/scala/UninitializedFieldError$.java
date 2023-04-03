/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class UninitializedFieldError$ extends AbstractFunction1<String, UninitializedFieldError> implements Serializable {
/*    */   public static final UninitializedFieldError$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 21 */     return "UninitializedFieldError";
/*    */   }
/*    */   
/*    */   public UninitializedFieldError apply(String msg) {
/* 21 */     return new UninitializedFieldError(msg);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(UninitializedFieldError x$0) {
/* 21 */     return (x$0 == null) ? None$.MODULE$ : new Some<String>(x$0.msg());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private UninitializedFieldError$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\UninitializedFieldError$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */