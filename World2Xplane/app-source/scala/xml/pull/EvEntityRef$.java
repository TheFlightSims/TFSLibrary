/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class EvEntityRef$ extends AbstractFunction1<String, EvEntityRef> implements Serializable {
/*    */   public static final EvEntityRef$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 43 */     return "EvEntityRef";
/*    */   }
/*    */   
/*    */   public EvEntityRef apply(String entity) {
/* 43 */     return new EvEntityRef(entity);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(EvEntityRef x$0) {
/* 43 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.entity());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 43 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvEntityRef$() {
/* 43 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvEntityRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */