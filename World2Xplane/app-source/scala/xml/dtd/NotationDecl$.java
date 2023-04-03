/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class NotationDecl$ extends AbstractFunction2<String, ExternalID, NotationDecl> implements Serializable {
/*    */   public static final NotationDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 80 */     return "NotationDecl";
/*    */   }
/*    */   
/*    */   public NotationDecl apply(String name, ExternalID extID) {
/* 80 */     return new NotationDecl(name, extID);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, ExternalID>> unapply(NotationDecl x$0) {
/* 80 */     return (x$0 == null) ? (Option<Tuple2<String, ExternalID>>)scala.None$.MODULE$ : (Option<Tuple2<String, ExternalID>>)new Some(new Tuple2(x$0.name(), x$0.extID()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 80 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private NotationDecl$() {
/* 80 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\NotationDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */