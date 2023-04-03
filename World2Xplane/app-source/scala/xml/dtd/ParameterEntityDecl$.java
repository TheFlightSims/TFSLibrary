/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class ParameterEntityDecl$ extends AbstractFunction2<String, EntityDef, ParameterEntityDecl> implements Serializable {
/*    */   public static final ParameterEntityDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 65 */     return "ParameterEntityDecl";
/*    */   }
/*    */   
/*    */   public ParameterEntityDecl apply(String name, EntityDef entdef) {
/* 65 */     return new ParameterEntityDecl(name, entdef);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, EntityDef>> unapply(ParameterEntityDecl x$0) {
/* 65 */     return (x$0 == null) ? (Option<Tuple2<String, EntityDef>>)scala.None$.MODULE$ : (Option<Tuple2<String, EntityDef>>)new Some(new Tuple2(x$0.name(), x$0.entdef()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 65 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ParameterEntityDecl$() {
/* 65 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ParameterEntityDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */