/*    */ package scala.text;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class DocNest$ extends AbstractFunction2<Object, Document, DocNest> implements Serializable {
/*    */   public static final DocNest$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 17 */     return "DocNest";
/*    */   }
/*    */   
/*    */   public DocNest apply(int indent, Document doc) {
/* 17 */     return new DocNest(indent, doc);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<Object, Document>> unapply(DocNest x$0) {
/* 17 */     return (x$0 == null) ? (Option<Tuple2<Object, Document>>)scala.None$.MODULE$ : (Option<Tuple2<Object, Document>>)new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.indent()), x$0.doc()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 17 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocNest$() {
/* 17 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocNest$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */