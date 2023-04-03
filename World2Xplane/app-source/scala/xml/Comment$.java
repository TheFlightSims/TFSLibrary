/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Comment$ extends AbstractFunction1<String, Comment> implements Serializable {
/*    */   public static final Comment$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 16 */     return "Comment";
/*    */   }
/*    */   
/*    */   public Comment apply(String commentText) {
/* 16 */     return new Comment(commentText);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(Comment x$0) {
/* 16 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.commentText());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 16 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Comment$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Comment$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */