/*    */ package scala.text;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class DocText$ extends AbstractFunction1<String, DocText> implements Serializable {
/*    */   public static final DocText$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 15 */     return "DocText";
/*    */   }
/*    */   
/*    */   public DocText apply(String txt) {
/* 15 */     return new DocText(txt);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(DocText x$0) {
/* 15 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.txt());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 15 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DocText$() {
/* 15 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\text\DocText$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */