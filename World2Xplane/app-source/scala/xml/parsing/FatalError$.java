/*    */ package scala.xml.parsing;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class FatalError$ extends AbstractFunction1<String, FatalError> implements Serializable {
/*    */   public static final FatalError$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 16 */     return "FatalError";
/*    */   }
/*    */   
/*    */   public FatalError apply(String msg) {
/* 16 */     return new FatalError(msg);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(FatalError x$0) {
/* 16 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.msg());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private FatalError$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\FatalError$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */