/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class EvProcInstr$ extends AbstractFunction2<String, String, EvProcInstr> implements Serializable {
/*    */   public static final EvProcInstr$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 53 */     return "EvProcInstr";
/*    */   }
/*    */   
/*    */   public EvProcInstr apply(String target, String text) {
/* 53 */     return new EvProcInstr(target, text);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, String>> unapply(EvProcInstr x$0) {
/* 53 */     return (x$0 == null) ? (Option<Tuple2<String, String>>)scala.None$.MODULE$ : (Option<Tuple2<String, String>>)new Some(new Tuple2(x$0.target(), x$0.text()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 53 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvProcInstr$() {
/* 53 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvProcInstr$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */