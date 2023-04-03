/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class ElemDecl$ extends AbstractFunction2<String, ContentModel, ElemDecl> implements Serializable {
/*    */   public static final ElemDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 22 */     return "ElemDecl";
/*    */   }
/*    */   
/*    */   public ElemDecl apply(String name, ContentModel contentModel) {
/* 22 */     return new ElemDecl(name, contentModel);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, ContentModel>> unapply(ElemDecl x$0) {
/* 22 */     return (x$0 == null) ? (Option<Tuple2<String, ContentModel>>)scala.None$.MODULE$ : (Option<Tuple2<String, ContentModel>>)new Some(new Tuple2(x$0.name(), x$0.contentModel()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 22 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ElemDecl$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ElemDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */