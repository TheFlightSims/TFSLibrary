/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class MalformedAttributeException$ extends AbstractFunction1<String, MalformedAttributeException> implements Serializable {
/*    */   public static final MalformedAttributeException$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 14 */     return "MalformedAttributeException";
/*    */   }
/*    */   
/*    */   public MalformedAttributeException apply(String msg) {
/* 14 */     return new MalformedAttributeException(msg);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(MalformedAttributeException x$0) {
/* 14 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.msg());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 14 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private MalformedAttributeException$() {
/* 14 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\MalformedAttributeException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */