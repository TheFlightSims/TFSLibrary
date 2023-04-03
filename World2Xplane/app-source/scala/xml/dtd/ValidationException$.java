/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ValidationException$ extends AbstractFunction1<String, ValidationException> implements Serializable {
/*    */   public static final ValidationException$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 15 */     return "ValidationException";
/*    */   }
/*    */   
/*    */   public ValidationException apply(String e) {
/* 15 */     return new ValidationException(e);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(ValidationException x$0) {
/* 15 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.e());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 15 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ValidationException$() {
/* 15 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ValidationException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */