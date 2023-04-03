/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class Number$ extends AbstractFunction1<Object, Number> implements Serializable {
/*    */   public static final Number$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 11 */     return "Number";
/*    */   }
/*    */   
/*    */   public Number apply(int n) {
/* 11 */     return new Number(n);
/*    */   }
/*    */   
/*    */   public Option<Object> unapply(Number x$0) {
/* 11 */     return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(BoxesRunTime.boxToInteger(x$0.n()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 11 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Number$() {
/* 11 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Number$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */