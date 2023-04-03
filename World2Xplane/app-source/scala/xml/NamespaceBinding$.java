/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class NamespaceBinding$ extends AbstractFunction3<String, String, NamespaceBinding, NamespaceBinding> implements Serializable {
/*    */   public static final NamespaceBinding$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 22 */     return "NamespaceBinding";
/*    */   }
/*    */   
/*    */   public NamespaceBinding apply(String prefix, String uri, NamespaceBinding parent) {
/* 22 */     return new NamespaceBinding(prefix, uri, parent);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<String, String, NamespaceBinding>> unapply(NamespaceBinding x$0) {
/* 22 */     return (x$0 == null) ? (Option<Tuple3<String, String, NamespaceBinding>>)scala.None$.MODULE$ : (Option<Tuple3<String, String, NamespaceBinding>>)new Some(new Tuple3(x$0.prefix(), x$0.uri(), x$0.parent()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private NamespaceBinding$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class NamespaceBinding$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder x$1) {
/* 39 */       this.$outer.buildString(x$1, TopScope$.MODULE$);
/*    */     }
/*    */     
/*    */     public NamespaceBinding$$anonfun$toString$1(NamespaceBinding $outer) {}
/*    */   }
/*    */   
/*    */   public class NamespaceBinding$$anonfun$buildString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final NamespaceBinding stop$1;
/*    */     
/*    */     public final void apply(StringBuilder x$2) {
/* 53 */       this.$outer.buildString(x$2, this.stop$1);
/*    */     }
/*    */     
/*    */     public NamespaceBinding$$anonfun$buildString$1(NamespaceBinding $outer, NamespaceBinding stop$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\NamespaceBinding$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */