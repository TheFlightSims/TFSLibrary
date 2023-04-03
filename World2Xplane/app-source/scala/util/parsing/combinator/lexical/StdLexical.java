/*    */ package scala.util.parsing.combinator.lexical;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.MatchError;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.HashSet;
/*    */ import scala.math.Ordering;
/*    */ import scala.math.Ordering$String$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.Sorting$;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.token.StdTokens;
/*    */ import scala.util.parsing.combinator.token.StdTokens$Identifier$;
/*    */ import scala.util.parsing.combinator.token.StdTokens$Keyword$;
/*    */ import scala.util.parsing.combinator.token.StdTokens$NumericLit$;
/*    */ import scala.util.parsing.combinator.token.StdTokens$StringLit$;
/*    */ import scala.util.parsing.combinator.token.Tokens;
/*    */ import scala.util.parsing.combinator.token.Tokens$EOF$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001!4A!\001\002\001\033\tQ1\013\0363MKbL7-\0317\013\005\r!\021a\0027fq&\034\027\r\034\006\003\013\031\t!bY8nE&t\027\r^8s\025\t9\001\"A\004qCJ\034\030N\\4\013\005%Q\021\001B;uS2T\021aC\001\006g\016\fG.Y\002\001'\r\001aB\005\t\003\037Ai\021AA\005\003#\t\021q\001T3yS\016\fG\016\005\002\024-5\tAC\003\002\026\t\005)Ao\\6f]&\021q\003\006\002\n'R$Gk\\6f]NDQ!\007\001\005\002i\ta\001P5oSRtD#A\016\021\005=\001\001\"B\013\001\t\003iR#\001\020\021\007}\001C%D\001\001\023\t\t#E\001\004QCJ\034XM]\005\003G\021\021q\001U1sg\026\0248\017\005\002 K%\021ae\n\002\006)>\\WM\\\005\003QQ\021a\001V8lK:\034\b\"\002\026\001\t\003Y\023!C5eK:$8\t[1s+\005a\003cA\020![A\021qDL\005\003_A\022A!\0227f[&\021\021G\001\002\t'\016\fgN\\3sg\")1\007\001C\001i\005Qq\017[5uKN\004\030mY3\026\003U\0022a\b\0217!\t9\004(D\001\013\023\tI$BA\002B]fDQa\017\001\005\022Q\nqaY8n[\026tG\017C\004>\001\t\007I\021\001 \002\021I,7/\032:wK\022,\022a\020\t\004\001\026;U\"A!\013\005\t\033\025aB7vi\006\024G.\032\006\003\t*\t!bY8mY\026\034G/[8o\023\t1\025IA\004ICND7+\032;\021\005![eBA\034J\023\tQ%\"\001\004Qe\026$WMZ\005\003\0316\023aa\025;sS:<'B\001&\013\021\031y\005\001)A\005\005I!/Z:feZ,G\r\t\005\b#\002\021\r\021\"\001?\003)!W\r\\5nSR,'o\035\005\007'\002\001\013\021B \002\027\021,G.[7ji\026\0248\017\t\005\006+\002!\tBV\001\raJ|7-Z:t\023\022,g\016\036\013\003/\002\024B\001\027.^I\031!\021\f\001\001X\0051a$/\0324j]\026lWM\034;?!\t94,\003\002]\025\ta1+\032:jC2L'0\0312mKB\021qGX\005\003?*\021q\001\025:pIV\034G\017C\003b)\002\007q)\001\003oC6,\007\002C2\001\021\013\007I\021B\017\002\r}#W\r\\5n\021!)\007\001#A!B\023q\022aB0eK2LW\016\t\005\006O\002!\t\"H\001\006I\026d\027.\034")
/*    */ public class StdLexical extends Lexical implements StdTokens {
/*    */   private final HashSet<String> reserved;
/*    */   
/*    */   private final HashSet<String> delimiters;
/*    */   
/*    */   private Parsers.Parser<Tokens.Token> _delim;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   private volatile StdTokens$Keyword$ Keyword$module;
/*    */   
/*    */   private volatile StdTokens$NumericLit$ NumericLit$module;
/*    */   
/*    */   private volatile StdTokens$StringLit$ StringLit$module;
/*    */   
/*    */   private volatile StdTokens$Identifier$ Identifier$module;
/*    */   
/*    */   private StdTokens$Keyword$ Keyword$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (this.Keyword$module == null)
/* 35 */         this.Keyword$module = new StdTokens$Keyword$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/StdLexical}} */
/* 35 */       return this.Keyword$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public StdTokens$Keyword$ Keyword() {
/* 35 */     return (this.Keyword$module == null) ? Keyword$lzycompute() : this.Keyword$module;
/*    */   }
/*    */   
/*    */   private StdTokens$NumericLit$ NumericLit$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (this.NumericLit$module == null)
/* 35 */         this.NumericLit$module = new StdTokens$NumericLit$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/StdLexical}} */
/* 35 */       return this.NumericLit$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public StdTokens$NumericLit$ NumericLit() {
/* 35 */     return (this.NumericLit$module == null) ? NumericLit$lzycompute() : this.NumericLit$module;
/*    */   }
/*    */   
/*    */   private StdTokens$StringLit$ StringLit$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (this.StringLit$module == null)
/* 35 */         this.StringLit$module = new StdTokens$StringLit$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/StdLexical}} */
/* 35 */       return this.StringLit$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public StdTokens$StringLit$ StringLit() {
/* 35 */     return (this.StringLit$module == null) ? StringLit$lzycompute() : this.StringLit$module;
/*    */   }
/*    */   
/*    */   private StdTokens$Identifier$ Identifier$lzycompute() {
/* 35 */     synchronized (this) {
/* 35 */       if (this.Identifier$module == null)
/* 35 */         this.Identifier$module = new StdTokens$Identifier$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/StdLexical}} */
/* 35 */       return this.Identifier$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public StdTokens$Identifier$ Identifier() {
/* 35 */     return (this.Identifier$module == null) ? Identifier$lzycompute() : this.Identifier$module;
/*    */   }
/*    */   
/*    */   public StdLexical() {
/* 35 */     StdTokens.class.$init$(this);
/* 66 */     this.reserved = new HashSet();
/* 69 */     this.delimiters = new HashSet();
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Tokens.Token> token() {
/*    */     return identChar().$tilde((Function0)new StdLexical$$anonfun$token$1(this)).$up$up((Function1)new StdLexical$$anonfun$token$2(this)).$bar((Function0)new StdLexical$$anonfun$token$3(this)).$bar((Function0)new StdLexical$$anonfun$token$4(this)).$bar((Function0)new StdLexical$$anonfun$token$5(this)).$bar((Function0)new StdLexical$$anonfun$token$6(this)).$bar((Function0)new StdLexical$$anonfun$token$7(this)).$bar((Function0)new StdLexical$$anonfun$token$8(this)).$bar((Function0)new StdLexical$$anonfun$token$9(this)).$bar((Function0)new StdLexical$$anonfun$token$10(this));
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$1 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<List<Object>> apply() {
/*    */       return this.$outer.rep((Function0<Parsers.Parser<Object>>)new StdLexical$$anonfun$token$1$$anonfun$apply$1(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$1(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$1$$anonfun$apply$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.identChar().$bar((Function0)new StdLexical$$anonfun$token$1$$anonfun$apply$1$$anonfun$apply$2(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$1$$anonfun$apply$1(StdLexical$$anonfun$token$1 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$token$1$$anonfun$apply$1$$anonfun$apply$2 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.digit();
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$token$1$$anonfun$apply$1$$anonfun$apply$2(StdLexical$$anonfun$token$1$$anonfun$apply$1 $outer) {}
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$2 extends AbstractFunction1<Parsers$.tilde<Object, List<Object>>, Serializable> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Serializable apply(Parsers$.tilde x0$1) {
/*    */       if (x0$1 != null) {
/*    */         char c = BoxesRunTime.unboxToChar(x0$1._1());
/*    */         return (Serializable)this.$outer.processIdent(((List)x0$1._2()).$colon$colon(BoxesRunTime.boxToCharacter(c)).mkString(""));
/*    */       } 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$2(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$3 extends AbstractFunction0<Parsers.Parser<StdTokens.NumericLit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<StdTokens.NumericLit> apply() {
/*    */       return this.$outer.digit().$tilde((Function0)new StdLexical$$anonfun$token$3$$anonfun$apply$3(this)).$up$up((Function1)new StdLexical$$anonfun$token$3$$anonfun$apply$5(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$3(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$3$$anonfun$apply$3 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<List<Object>> apply() {
/*    */         return this.$outer.$outer.rep((Function0<Parsers.Parser<Object>>)new StdLexical$$anonfun$token$3$$anonfun$apply$3$$anonfun$apply$4(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$3$$anonfun$apply$3(StdLexical$$anonfun$token$3 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$token$3$$anonfun$apply$3$$anonfun$apply$4 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.digit();
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$token$3$$anonfun$apply$3$$anonfun$apply$4(StdLexical$$anonfun$token$3$$anonfun$apply$3 $outer) {}
/*    */       }
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$token$3$$anonfun$apply$5 extends AbstractFunction1<Parsers$.tilde<Object, List<Object>>, StdTokens.NumericLit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final StdTokens.NumericLit apply(Parsers$.tilde x0$2) {
/*    */         if (x0$2 != null) {
/*    */           char c = BoxesRunTime.unboxToChar(x0$2._1());
/*    */           return new StdTokens.NumericLit(this.$outer.$outer, ((List)x0$2._2()).$colon$colon(BoxesRunTime.boxToCharacter(c)).mkString(""));
/*    */         } 
/*    */         throw new MatchError(x0$2);
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$3$$anonfun$apply$5(StdLexical$$anonfun$token$3 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$4 extends AbstractFunction0<Parsers.Parser<StdTokens.StringLit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<StdTokens.StringLit> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\'')).$tilde((Function0)new StdLexical$$anonfun$token$4$$anonfun$apply$6(this)).$tilde((Function0)new StdLexical$$anonfun$token$4$$anonfun$apply$8(this)).$up$up((Function1)new StdLexical$$anonfun$token$4$$anonfun$apply$9(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$4(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$4$$anonfun$apply$6 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<List<Object>> apply() {
/*    */         return this.$outer.$outer.rep((Function0<Parsers.Parser<Object>>)new StdLexical$$anonfun$token$4$$anonfun$apply$6$$anonfun$apply$7(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$4$$anonfun$apply$6(StdLexical$$anonfun$token$4 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$token$4$$anonfun$apply$6$$anonfun$apply$7 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.chrExcept((Seq<Object>)Predef$.MODULE$.wrapCharArray(new char[] { '\'', '\n', '\032' }));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$token$4$$anonfun$apply$6$$anonfun$apply$7(StdLexical$$anonfun$token$4$$anonfun$apply$6 $outer) {}
/*    */       }
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$token$4$$anonfun$apply$8 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('\''));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$4$$anonfun$apply$8(StdLexical$$anonfun$token$4 $outer) {}
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$token$4$$anonfun$apply$9 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<Object, List<Object>>, Object>, StdTokens.StringLit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final StdTokens.StringLit apply(Parsers$.tilde x0$3) {
/*    */         if (x0$3 != null && x0$3._1() != null && '\'' == BoxesRunTime.unboxToChar(((Parsers$.tilde)x0$3._1())._1()) && '\'' == BoxesRunTime.unboxToChar(x0$3._2()))
/*    */           return new StdTokens.StringLit(this.$outer.$outer, ((TraversableOnce)((Parsers$.tilde)x0$3._1())._2()).mkString("")); 
/*    */         throw new MatchError(x0$3);
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$4$$anonfun$apply$9(StdLexical$$anonfun$token$4 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$5 extends AbstractFunction0<Parsers.Parser<StdTokens.StringLit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<StdTokens.StringLit> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('"')).$tilde((Function0)new StdLexical$$anonfun$token$5$$anonfun$apply$10(this)).$tilde((Function0)new StdLexical$$anonfun$token$5$$anonfun$apply$12(this)).$up$up((Function1)new StdLexical$$anonfun$token$5$$anonfun$apply$13(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$5(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$5$$anonfun$apply$10 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<List<Object>> apply() {
/*    */         return this.$outer.$outer.rep((Function0<Parsers.Parser<Object>>)new StdLexical$$anonfun$token$5$$anonfun$apply$10$$anonfun$apply$11(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$5$$anonfun$apply$10(StdLexical$$anonfun$token$5 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$token$5$$anonfun$apply$10$$anonfun$apply$11 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.chrExcept((Seq<Object>)Predef$.MODULE$.wrapCharArray(new char[] { '"', '\n', '\032' }));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$token$5$$anonfun$apply$10$$anonfun$apply$11(StdLexical$$anonfun$token$5$$anonfun$apply$10 $outer) {}
/*    */       }
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$token$5$$anonfun$apply$12 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('"'));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$5$$anonfun$apply$12(StdLexical$$anonfun$token$5 $outer) {}
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$token$5$$anonfun$apply$13 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<Object, List<Object>>, Object>, StdTokens.StringLit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final StdTokens.StringLit apply(Parsers$.tilde x0$4) {
/*    */         if (x0$4 != null && x0$4._1() != null && '"' == BoxesRunTime.unboxToChar(((Parsers$.tilde)x0$4._1())._1()) && '"' == BoxesRunTime.unboxToChar(x0$4._2()))
/*    */           return new StdTokens.StringLit(this.$outer.$outer, ((TraversableOnce)((Parsers$.tilde)x0$4._1())._2()).mkString("")); 
/*    */         throw new MatchError(x0$4);
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$5$$anonfun$apply$13(StdLexical$$anonfun$token$5 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$6 extends AbstractFunction0<Parsers.Parser<Tokens$EOF$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens$EOF$> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\032')).$up$up$up((Function0)new StdLexical$$anonfun$token$6$$anonfun$apply$14(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$6(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$6$$anonfun$apply$14 extends AbstractFunction0<Tokens$EOF$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Tokens$EOF$ apply() {
/*    */         return this.$outer.$outer.EOF();
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$6$$anonfun$apply$14(StdLexical$$anonfun$token$6 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$7 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Nothing$> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\'')).$tilde$greater((Function0)new StdLexical$$anonfun$token$7$$anonfun$apply$15(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$7(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$7$$anonfun$apply$15 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Nothing$> apply() {
/*    */         return this.$outer.$outer.failure("unclosed string literal");
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$7$$anonfun$apply$15(StdLexical$$anonfun$token$7 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$8 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Nothing$> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('"')).$tilde$greater((Function0)new StdLexical$$anonfun$token$8$$anonfun$apply$16(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$8(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$token$8$$anonfun$apply$16 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Nothing$> apply() {
/*    */         return this.$outer.$outer.failure("unclosed string literal");
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$token$8$$anonfun$apply$16(StdLexical$$anonfun$token$8 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$9 extends AbstractFunction0<Parsers.Parser<Tokens.Token>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.Token> apply() {
/*    */       return this.$outer.delim();
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$9(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$token$10 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Nothing$> apply() {
/*    */       return this.$outer.failure("illegal character");
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$token$10(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> identChar() {
/*    */     return letter().$bar((Function0)new StdLexical$$anonfun$identChar$1(this));
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$identChar$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.elem(BoxesRunTime.boxToCharacter('_'));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$identChar$1(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> whitespace() {
/*    */     return (Parsers.Parser)rep((Function0<Parsers.Parser<?>>)new StdLexical$$anonfun$whitespace$1(this));
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$whitespace$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.whitespaceChar().$bar((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$17(this)).$bar((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$20(this)).$bar((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$24(this));
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$17 extends AbstractFunction0<Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, Object>> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('/')).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$18(this)).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$19(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$whitespace$1$$anonfun$apply$17(StdLexical$$anonfun$whitespace$1 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$18 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.accept(BoxesRunTime.boxToCharacter('*'));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$18(StdLexical$$anonfun$whitespace$1$$anonfun$apply$17 $outer) {}
/*    */       }
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$19 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.comment();
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$17$$anonfun$apply$19(StdLexical$$anonfun$whitespace$1$$anonfun$apply$17 $outer) {}
/*    */       }
/*    */     }
/*    */     
/*    */     public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$20 extends AbstractFunction0<Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, List<Object>>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, List<Object>>> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('/')).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$21(this)).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$whitespace$1$$anonfun$apply$20(StdLexical$$anonfun$whitespace$1 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$21 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.accept(BoxesRunTime.boxToCharacter('/'));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$21(StdLexical$$anonfun$whitespace$1$$anonfun$apply$20 $outer) {}
/*    */       }
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<List<Object>> apply() {
/*    */           return this.$outer.$outer.$outer.rep((Function0<Parsers.Parser<Object>>)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22$$anonfun$apply$23(this));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22(StdLexical$$anonfun$whitespace$1$$anonfun$apply$20 $outer) {}
/*    */         
/*    */         public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22$$anonfun$apply$23 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */           public static final long serialVersionUID = 0L;
/*    */           
/*    */           public final Parsers.Parser<Object> apply() {
/*    */             return this.$outer.$outer.$outer.$outer.chrExcept((Seq<Object>)Predef$.MODULE$.wrapCharArray(new char[] { '\032', '\n' }));
/*    */           }
/*    */           
/*    */           public StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22$$anonfun$apply$23(StdLexical$$anonfun$whitespace$1$$anonfun$apply$20$$anonfun$apply$22 $outer) {}
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$whitespace$1(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$24 extends AbstractFunction0<Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, Nothing$>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Parsers$.tilde<Parsers$.tilde<Object, Object>, Nothing$>> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('/')).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$25(this)).$tilde((Function0)new StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$26(this));
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$whitespace$1$$anonfun$apply$24(StdLexical$$anonfun$whitespace$1 $outer) {}
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$25 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.accept(BoxesRunTime.boxToCharacter('*'));
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$25(StdLexical$$anonfun$whitespace$1$$anonfun$apply$24 $outer) {}
/*    */       }
/*    */       
/*    */       public class StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$26 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Nothing$> apply() {
/*    */           return this.$outer.$outer.$outer.failure("unclosed comment");
/*    */         }
/*    */         
/*    */         public StdLexical$$anonfun$whitespace$1$$anonfun$apply$24$$anonfun$apply$26(StdLexical$$anonfun$whitespace$1$$anonfun$apply$24 $outer) {}
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> comment() {
/*    */     return accept(BoxesRunTime.boxToCharacter('*')).$tilde((Function0)new StdLexical$$anonfun$comment$1(this)).$up$up((Function1)new StdLexical$$anonfun$comment$2(this)).$bar((Function0)new StdLexical$$anonfun$comment$3(this));
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$comment$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('/'));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$comment$1(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$comment$2 extends AbstractFunction1<Parsers$.tilde<Object, Object>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final char apply(Parsers$.tilde x0$5) {
/*    */       return ' ';
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$comment$2(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$comment$3 extends AbstractFunction0<Parsers.Parser<Parsers$.tilde<Object, Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Parsers$.tilde<Object, Object>> apply() {
/*    */       return this.$outer.chrExcept((Seq<Object>)Predef$.MODULE$.wrapCharArray(new char[] { '\032' })).$tilde((Function0)new StdLexical$$anonfun$comment$3$$anonfun$apply$27(this));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$comment$3(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$comment$3$$anonfun$apply$27 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.comment();
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$comment$3$$anonfun$apply$27(StdLexical$$anonfun$comment$3 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public HashSet<String> reserved() {
/*    */     return this.reserved;
/*    */   }
/*    */   
/*    */   public HashSet<String> delimiters() {
/* 69 */     return this.delimiters;
/*    */   }
/*    */   
/*    */   public Tokens.Token processIdent(String name) {
/* 72 */     return reserved().contains(name) ? (Tokens.Token)new StdTokens.Keyword(this, name) : (Tokens.Token)new StdTokens.Identifier(this, name);
/*    */   }
/*    */   
/*    */   private Parsers.Parser _delim$lzycompute() {
/* 74 */     synchronized (this) {
/* 74 */       if (!this.bitmap$0) {
/* 80 */         String[] d = new String[delimiters().size()];
/* 81 */         delimiters().copyToArray(d, 0);
/* 82 */         Sorting$.MODULE$.quickSort(d, (Ordering)Ordering$String$.MODULE$);
/* 83 */         this._delim = (Parsers.Parser<Tokens.Token>)((List)Predef$.MODULE$.refArrayOps((Object[])d).toList().map((Function1)new StdLexical$$anonfun$_delim$1(this), List$.MODULE$.canBuildFrom())).foldRight(failure("no matching delimiter"), (Function2)new StdLexical$$anonfun$_delim$2(this));
/*    */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/StdLexical}} */
/*    */       return this._delim;
/*    */     } 
/*    */   }
/*    */   
/*    */   private Parsers.Parser<Tokens.Token> _delim() {
/*    */     return this.bitmap$0 ? this._delim : _delim$lzycompute();
/*    */   }
/*    */   
/*    */   public final Parsers.Parser scala$util$parsing$combinator$lexical$StdLexical$$parseDelim$1(String s) {
/*    */     Predef$ predef$ = Predef$.MODULE$;
/*    */     return accept((new StringOps(s)).toList(), (Function1<List, List<Object>>)Predef$.MODULE$.conforms()).$up$up((Function1)new StdLexical$$anonfun$scala$util$parsing$combinator$lexical$StdLexical$$parseDelim$1$1(this, s));
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$scala$util$parsing$combinator$lexical$StdLexical$$parseDelim$1$1 extends AbstractFunction1<List<Object>, StdTokens.Keyword> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String s$1;
/*    */     
/*    */     public final StdTokens.Keyword apply(List x) {
/*    */       return new StdTokens.Keyword(this.$outer, this.s$1);
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$scala$util$parsing$combinator$lexical$StdLexical$$parseDelim$1$1(StdLexical $outer, String s$1) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$_delim$1 extends AbstractFunction1<String, Parsers.Parser<Tokens.Token>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.Token> apply(String s) {
/* 83 */       return this.$outer.scala$util$parsing$combinator$lexical$StdLexical$$parseDelim$1(s);
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$_delim$1(StdLexical $outer) {}
/*    */   }
/*    */   
/*    */   public class StdLexical$$anonfun$_delim$2 extends AbstractFunction2<Parsers.Parser<Tokens.Token>, Parsers.Parser<Tokens.Token>, Parsers.Parser<Tokens.Token>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.Token> apply(Parsers.Parser x, Parsers.Parser y) {
/* 83 */       return y.$bar((Function0)new StdLexical$$anonfun$_delim$2$$anonfun$apply$28(this, x));
/*    */     }
/*    */     
/*    */     public StdLexical$$anonfun$_delim$2(StdLexical $outer) {}
/*    */     
/*    */     public class StdLexical$$anonfun$_delim$2$$anonfun$apply$28 extends AbstractFunction0<Parsers.Parser<Tokens.Token>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Parsers.Parser x$3;
/*    */       
/*    */       public final Parsers.Parser<Tokens.Token> apply() {
/* 83 */         return this.x$3;
/*    */       }
/*    */       
/*    */       public StdLexical$$anonfun$_delim$2$$anonfun$apply$28(StdLexical$$anonfun$_delim$2 $outer, Parsers.Parser x$3) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Tokens.Token> delim() {
/* 85 */     return _delim();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\lexical\StdLexical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */