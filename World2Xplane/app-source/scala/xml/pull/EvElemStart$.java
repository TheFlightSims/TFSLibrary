/*    */ package scala.xml.pull;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple4;
/*    */ import scala.runtime.AbstractFunction4;
/*    */ import scala.xml.MetaData;
/*    */ import scala.xml.NamespaceBinding;
/*    */ 
/*    */ public final class EvElemStart$ extends AbstractFunction4<String, String, MetaData, NamespaceBinding, EvElemStart> implements Serializable {
/*    */   public static final EvElemStart$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 25 */     return "EvElemStart";
/*    */   }
/*    */   
/*    */   public EvElemStart apply(String pre, String label, MetaData attrs, NamespaceBinding scope) {
/* 25 */     return new EvElemStart(pre, label, attrs, scope);
/*    */   }
/*    */   
/*    */   public Option<Tuple4<String, String, MetaData, NamespaceBinding>> unapply(EvElemStart x$0) {
/* 25 */     return (x$0 == null) ? (Option<Tuple4<String, String, MetaData, NamespaceBinding>>)scala.None$.MODULE$ : (Option<Tuple4<String, String, MetaData, NamespaceBinding>>)new Some(new Tuple4(x$0.pre(), x$0.label(), x$0.attrs(), x$0.scope()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 25 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private EvElemStart$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\pull\EvElemStart$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */