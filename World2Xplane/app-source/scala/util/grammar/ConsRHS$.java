/*    */ package scala.util.grammar;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ConsRHS$ extends AbstractFunction2<Object, Object, ConsRHS> implements Serializable {
/*    */   public static final ConsRHS$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 18 */     return "ConsRHS";
/*    */   }
/*    */   
/*    */   public ConsRHS apply(int tnt, int hnt) {
/* 18 */     return new ConsRHS(tnt, hnt);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Object>> unapply(ConsRHS x$0) {
/* 18 */     return (x$0 == null) ? (Option<Tuple2<Object, Object>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Object>>)new Some(new Tuple2.mcII.sp(x$0.tnt(), x$0.hnt()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 18 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ConsRHS$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\ConsRHS$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */