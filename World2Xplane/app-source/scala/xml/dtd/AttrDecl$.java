/*    */ package scala.xml.dtd;
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
/*    */ public final class AttrDecl$ extends AbstractFunction3<String, String, DefaultDecl, AttrDecl> implements Serializable {
/*    */   public static final AttrDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 43 */     return "AttrDecl";
/*    */   }
/*    */   
/*    */   public AttrDecl apply(String name, String tpe, DefaultDecl default) {
/* 43 */     return new AttrDecl(name, tpe, default);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<String, String, DefaultDecl>> unapply(AttrDecl x$0) {
/* 43 */     return (x$0 == null) ? (Option<Tuple3<String, String, DefaultDecl>>)scala.None$.MODULE$ : (Option<Tuple3<String, String, DefaultDecl>>)new Some(new Tuple3(x$0.name(), x$0.tpe(), x$0.default()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 43 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private AttrDecl$() {
/* 43 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public class AttrDecl$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 44 */       this.$outer.buildString(sb);
/*    */     }
/*    */     
/*    */     public AttrDecl$$anonfun$toString$1(AttrDecl $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\AttrDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */