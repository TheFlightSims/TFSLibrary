/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Str$ extends AbstractFunction1<String, Str> implements Serializable {
/*    */   public static final Str$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 13 */     return "Str";
/*    */   }
/*    */   
/*    */   public Str apply(String s) {
/* 13 */     return new Str(s);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(Str x$0) {
/* 13 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.s());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 13 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Str$() {
/* 13 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Str$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */