/*    */ package scala.util.parsing.combinator.syntactical;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.collection.mutable.HashMap$;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ 
/*    */ public abstract class StdTokenParsers$class {
/*    */   public static void $init$(StdTokenParsers $this) {
/* 27 */     $this.scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq((HashMap)HashMap$.MODULE$.apply((Seq)Nil$.MODULE$));
/*    */   }
/*    */   
/*    */   public static Parsers.Parser keyword(StdTokenParsers $this, String chars) {
/* 36 */     return (Parsers.Parser)$this.keywordCache().getOrElseUpdate(chars, (Function0)new StdTokenParsers$$anonfun$keyword$1($this, chars));
/*    */   }
/*    */   
/*    */   public static Parsers.Parser numericLit(StdTokenParsers $this) {
/* 40 */     return $this.elem("number", (Function1)new StdTokenParsers$$anonfun$numericLit$1($this)).$up$up((Function1)new StdTokenParsers$$anonfun$numericLit$2($this));
/*    */   }
/*    */   
/*    */   public static Parsers.Parser stringLit(StdTokenParsers $this) {
/* 44 */     return $this.elem("string literal", (Function1)new StdTokenParsers$$anonfun$stringLit$1($this)).$up$up((Function1)new StdTokenParsers$$anonfun$stringLit$2($this));
/*    */   }
/*    */   
/*    */   public static Parsers.Parser ident(StdTokenParsers $this) {
/* 48 */     return $this.elem("identifier", (Function1)new StdTokenParsers$$anonfun$ident$1($this)).$up$up((Function1)new StdTokenParsers$$anonfun$ident$2($this));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\syntactical\StdTokenParsers$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */