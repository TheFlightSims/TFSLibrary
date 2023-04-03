/*    */ package scala.text;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class DocGroup$ extends AbstractFunction1<Document, DocGroup> implements Serializable {
/*    */   public static final DocGroup$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 16 */     return "DocGroup";
/*    */   }
/*    */   
/*    */   public DocGroup apply(Document doc) {
/* 16 */     return new DocGroup(doc);
/*    */   }
/*    */   
/*    */   public Option<Document> unapply(DocGroup x$0) {
/* 16 */     return (x$0 == null) ? (Option<Document>)scala.None$.MODULE$ : (Option<Document>)new Some(x$0.doc());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocGroup$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocGroup$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */