/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class AttListDecl$ extends AbstractFunction2<String, List<AttrDecl>, AttListDecl> implements Serializable {
/*    */   public static final AttListDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 32 */     return "AttListDecl";
/*    */   }
/*    */   
/*    */   public AttListDecl apply(String name, List<AttrDecl> attrs) {
/* 32 */     return new AttListDecl(name, attrs);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, List<AttrDecl>>> unapply(AttListDecl x$0) {
/* 32 */     return (x$0 == null) ? (Option<Tuple2<String, List<AttrDecl>>>)scala.None$.MODULE$ : (Option<Tuple2<String, List<AttrDecl>>>)new Some(new Tuple2(x$0.name(), x$0.attrs()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private AttListDecl$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\AttListDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */