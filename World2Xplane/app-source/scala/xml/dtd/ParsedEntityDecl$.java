/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ 
/*    */ public final class ParsedEntityDecl$ extends AbstractFunction2<String, EntityDef, ParsedEntityDecl> implements Serializable {
/*    */   public static final ParsedEntityDecl$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 57 */     return "ParsedEntityDecl";
/*    */   }
/*    */   
/*    */   public ParsedEntityDecl apply(String name, EntityDef entdef) {
/* 57 */     return new ParsedEntityDecl(name, entdef);
/*    */   }
/*    */   
/*    */   public Option<Tuple2<String, EntityDef>> unapply(ParsedEntityDecl x$0) {
/* 57 */     return (x$0 == null) ? (Option<Tuple2<String, EntityDef>>)scala.None$.MODULE$ : (Option<Tuple2<String, EntityDef>>)new Some(new Tuple2(x$0.name(), x$0.entdef()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 57 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ParsedEntityDecl$() {
/* 57 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ParsedEntityDecl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */