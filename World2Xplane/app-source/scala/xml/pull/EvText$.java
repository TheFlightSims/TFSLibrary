/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class EvText$ extends AbstractFunction1<String, EvText> implements Serializable {
/*    */   public static final EvText$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 38 */     return "EvText";
/*    */   }
/*    */   
/*    */   public EvText apply(String text) {
/* 38 */     return new EvText(text);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(EvText x$0) {
/* 38 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.text());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 38 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvText$() {
/* 38 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvText$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */