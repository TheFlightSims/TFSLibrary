/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class ProcInstr$ extends AbstractFunction2<String, String, ProcInstr> implements Serializable {
/*    */   public static final ProcInstr$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 18 */     return "ProcInstr";
/*    */   }
/*    */   
/*    */   public ProcInstr apply(String target, String proctext) {
/* 18 */     return new ProcInstr(target, proctext);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, String>> unapply(ProcInstr x$0) {
/* 18 */     return (x$0 == null) ? (Option<Tuple2<String, String>>)scala.None$.MODULE$ : (Option<Tuple2<String, String>>)new Some(new Tuple2(x$0.target(), x$0.proctext()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 18 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ProcInstr$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\ProcInstr$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */