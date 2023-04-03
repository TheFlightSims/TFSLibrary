/*    */ package scala.util.parsing.combinator;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ 
/*    */ public abstract class JavaTokenParsers$class {
/*    */   public static void $init$(JavaTokenParsers $this) {}
/*    */   
/*    */   public static Parsers.Parser ident(JavaTokenParsers $this) {
/* 29 */     Predef$ predef$ = Predef$.MODULE$;
/* 29 */     return $this.regex((new StringOps("\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*")).r());
/*    */   }
/*    */   
/*    */   public static Parsers.Parser wholeNumber(JavaTokenParsers $this) {
/* 32 */     Predef$ predef$ = Predef$.MODULE$;
/* 32 */     return $this.regex((new StringOps("-?\\d+")).r());
/*    */   }
/*    */   
/*    */   public static Parsers.Parser decimalNumber(JavaTokenParsers $this) {
/* 41 */     Predef$ predef$ = Predef$.MODULE$;
/* 41 */     return $this.regex((new StringOps("(\\d+(\\.\\d*)?|\\d*\\.\\d+)")).r());
/*    */   }
/*    */   
/*    */   public static Parsers.Parser stringLiteral(JavaTokenParsers $this) {
/* 51 */     Predef$ predef$ = Predef$.MODULE$;
/* 51 */     return $this.regex((new StringOps("\"([^\"\\p{Cntrl}\\\\]|\\\\[\\\\'\"bfnrt]|\\\\u[a-fA-F0-9]{4})*\"")).r());
/*    */   }
/*    */   
/*    */   public static Parsers.Parser floatingPointNumber(JavaTokenParsers $this) {
/* 60 */     Predef$ predef$ = Predef$.MODULE$;
/* 60 */     return $this.regex((new StringOps("-?(\\d+(\\.\\d*)?|\\d*\\.\\d+)([eE][+-]?\\d+)?[fFdD]?")).r());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\JavaTokenParsers$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */