/*    */ package scala.util.grammar;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class LabelledRHS$ implements Serializable {
/*    */   public static final LabelledRHS$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 19 */     return "LabelledRHS";
/*    */   }
/*    */   
/*    */   public <A> LabelledRHS<A> apply(Object label, int hnt) {
/* 19 */     return new LabelledRHS<A>((A)label, hnt);
/*    */   }
/*    */   
/*    */   public <A> Option<Tuple2<A, Object>> unapply(LabelledRHS x$0) {
/* 19 */     return (x$0 == null) ? (Option<Tuple2<A, Object>>)scala.None$.MODULE$ : (Option<Tuple2<A, Object>>)new Some(new Tuple2(x$0.label(), BoxesRunTime.boxToInteger(x$0.hnt())));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 19 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private LabelledRHS$() {
/* 19 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\LabelledRHS$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */