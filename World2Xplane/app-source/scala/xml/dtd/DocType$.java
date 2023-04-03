/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.Seq;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ 
/*    */ public final class DocType$ extends AbstractFunction3<String, ExternalID, Seq<Decl>, DocType> implements Serializable {
/*    */   public static final DocType$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 21 */     return "DocType";
/*    */   }
/*    */   
/*    */   public DocType apply(String name, ExternalID extID, Seq<Decl> intSubset) {
/* 21 */     return new DocType(name, extID, intSubset);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<String, ExternalID, Seq<Decl>>> unapply(DocType x$0) {
/* 21 */     return (x$0 == null) ? (Option<Tuple3<String, ExternalID, Seq<Decl>>>)scala.None$.MODULE$ : (Option<Tuple3<String, ExternalID, Seq<Decl>>>)new Some(new Tuple3(x$0.name(), x$0.extID(), x$0.intSubset()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 21 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocType$() {
/* 21 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DocType$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */