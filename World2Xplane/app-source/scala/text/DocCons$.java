/*    */ package scala.text;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class DocCons$ extends AbstractFunction2<Document, Document, DocCons> implements Serializable {
/*    */   public static final DocCons$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 18 */     return "DocCons";
/*    */   }
/*    */   
/*    */   public DocCons apply(Document hd, Document tl) {
/* 18 */     return new DocCons(hd, tl);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Document, Document>> unapply(DocCons x$0) {
/* 18 */     return (x$0 == null) ? (Option<Tuple2<Document, Document>>)scala.None$.MODULE$ : (Option<Tuple2<Document, Document>>)new Some(new Tuple2(x$0.hd(), x$0.tl()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 18 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocCons$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocCons$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */