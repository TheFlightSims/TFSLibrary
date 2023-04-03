/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class EvElemEnd$ extends AbstractFunction2<String, String, EvElemEnd> implements Serializable {
/*    */   public static final EvElemEnd$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 32 */     return "EvElemEnd";
/*    */   }
/*    */   
/*    */   public EvElemEnd apply(String pre, String label) {
/* 32 */     return new EvElemEnd(pre, label);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, String>> unapply(EvElemEnd x$0) {
/* 32 */     return (x$0 == null) ? (Option<Tuple2<String, String>>)scala.None$.MODULE$ : (Option<Tuple2<String, String>>)new Some(new Tuple2(x$0.pre(), x$0.label()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvElemEnd$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvElemEnd$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */