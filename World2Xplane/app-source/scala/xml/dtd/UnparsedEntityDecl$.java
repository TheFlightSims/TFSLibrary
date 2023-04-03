/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ 
/*    */ public final class UnparsedEntityDecl$ extends AbstractFunction3<String, ExternalID, String, UnparsedEntityDecl> implements Serializable {
/*    */   public static final UnparsedEntityDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 73 */     return "UnparsedEntityDecl";
/*    */   }
/*    */   
/*    */   public UnparsedEntityDecl apply(String name, ExternalID extID, String notation) {
/* 73 */     return new UnparsedEntityDecl(name, extID, notation);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<String, ExternalID, String>> unapply(UnparsedEntityDecl x$0) {
/* 73 */     return (x$0 == null) ? (Option<Tuple3<String, ExternalID, String>>)scala.None$.MODULE$ : (Option<Tuple3<String, ExternalID, String>>)new Some(new Tuple3(x$0.name(), x$0.extID(), x$0.notation()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 73 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private UnparsedEntityDecl$() {
/* 73 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\UnparsedEntityDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */