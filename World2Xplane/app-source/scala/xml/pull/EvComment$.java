/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class EvComment$ extends AbstractFunction1<String, EvComment> implements Serializable {
/*    */   public static final EvComment$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 59 */     return "EvComment";
/*    */   }
/*    */   
/*    */   public EvComment apply(String text) {
/* 59 */     return new EvComment(text);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(EvComment x$0) {
/* 59 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.text());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 59 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvComment$() {
/* 59 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvComment$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */