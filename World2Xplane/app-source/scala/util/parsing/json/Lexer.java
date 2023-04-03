/*    */ package scala.util.parsing.json;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Function3;
/*    */ import scala.Function4;
/*    */ import scala.Function5;
/*    */ import scala.MatchError;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SetLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.List$;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ClassTag$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.RichChar$;
/*    */ import scala.util.parsing.combinator.ImplicitConversions;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.lexical.StdLexical;
/*    */ import scala.util.parsing.combinator.token.StdTokens;
/*    */ import scala.util.parsing.combinator.token.Tokens;
/*    */ import scala.util.parsing.combinator.token.Tokens$EOF$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005-c\001B\001\003\001-\021Q\001T3yKJT!a\001\003\002\t)\034xN\034\006\003\013\031\tq\001]1sg&twM\003\002\b\021\005!Q\017^5m\025\005I\021!B:dC2\f7\001A\n\004\0011!\002CA\007\023\033\005q!BA\b\021\003\035aW\r_5dC2T!!\005\003\002\025\r|WNY5oCR|'/\003\002\024\035\tQ1\013\0363MKbL7-\0317\021\005U1R\"\001\t\n\005]\001\"aE%na2L7-\033;D_:4XM]:j_:\034\b\"B\r\001\t\003Q\022A\002\037j]&$h\bF\001\034!\ta\002!D\001\003\021\025q\002\001\"\021 \003\025!xn[3o+\005\001\003cA\021#M5\t\001!\003\002$I\t1\001+\031:tKJL!!\n\t\003\017A\013'o]3sgB\021\021eJ\005\003Q%\022Q\001V8lK:L!AK\026\003\rQ{7.\0328t\025\tq\002\003C\003.\001\021\005a&\001\007dQ\026\0347nS3zo>\024H\r\006\0020sI!\001G\r\034'\r\021\t\004\001A\030\003\031q\022XMZ5oK6,g\016\036 \021\005M\"T\"\001\005\n\005UB!\001D*fe&\fG.\033>bE2,\007CA\0328\023\tA\004BA\004Qe>$Wo\031;\t\013ib\003\031A\036\002\005a\034\bc\001\037E\017:\021QH\021\b\003}\005k\021a\020\006\003\001*\ta\001\020:p_Rt\024\"A\005\n\005\rC\021a\0029bG.\fw-Z\005\003\013\032\023A\001T5ti*\0211\t\003\t\003g!K!!\023\005\003\007\005s\027\020C\003L\001\021\005A*\001\004tiJLgnZ\013\002\033B\031\021E\t(\021\005=\023fBA\032Q\023\t\t\006\"\001\004Qe\026$WMZ\005\003'R\023aa\025;sS:<'BA)\t\021\0251\006\001\"\021X\003)9\b.\033;fgB\f7-Z\013\0021B\031\021EI-\021\007q\"%\f\005\002\"7&\021A,\030\002\005\0132,W.\003\002_\035\tA1kY1o]\026\0248\017C\003a\001\021\005\021-\001\004ok6\024WM]\013\002EB\031\021EI2\021\005\021LW\"A3\013\005\031<\027\001\0027b]\036T\021\001[\001\005U\0064\030-\003\002TK\")1\016\001C\001\031\0069\021N\034;QCJ$\b\"B7\001\t\003a\025aB5oi2K7\017\036\005\006_\002!\t\001T\001\tMJ\f7\rU1si\")\021\017\001C\001C\0069Q\r\0379QCJ$\b\"B:\001\t\023!\030!C8qiN#(/\0338h+\t)x\020F\002dmbDQa\036:A\0029\0131\001\035:f\021\025I(\0171\001{\003\005\t\007cA\032|{&\021A\020\003\002\007\037B$\030n\0348\021\005y|H\002\001\003\b\003\003\021(\031AA\002\005\005\t\025cAA\003\017B\0311'a\002\n\007\005%\001BA\004O_RD\027N\\4\t\r\0055\001\001\"\001M\003\021QXM]8\t\017\005E\001\001\"\001\002\024\0059an\0348{KJ|WCAA\013!\r\t#E\027\005\b\0033\001A\021AA\n\003!)\007\020]8oK:$\bbBA\017\001\021\005\0211C\001\005g&<g\016\003\004\002\"\001!\t\001T\001\bG\"\f'oU3r\021%\t)\003\001b\001\n\003\t9#A\005iKb$\025nZ5ugV\021\021\021\006\t\007\003W\t)$!\017\016\005\0055\"\002BA\030\003c\t\021\"[7nkR\f'\r\\3\013\007\005M\002\"\001\006d_2dWm\031;j_:LA!a\016\002.\t\0311+\032;\021\007M\nY$C\002\002>!\021Aa\0215be\"A\021\021\t\001!\002\023\tI#\001\006iKb$\025nZ5ug\002Bq!!\022\001\t\003\t\031\"\001\005iKb$\025nZ5u\021\031\tI\005\001C\005C\006aQO\\5d_\022,'\t\\8dW\002")
/*    */ public class Lexer extends StdLexical implements ImplicitConversions {
/*    */   private final Set<Object> hexDigits;
/*    */   
/*    */   public <A, B, C> Function1<Parsers$.tilde<A, B>, C> flatten2(Function2 f) {
/* 21 */     return ImplicitConversions.class.flatten2(this, f);
/*    */   }
/*    */   
/*    */   public <A, B, C, D> Function1<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D> flatten3(Function3 f) {
/* 21 */     return ImplicitConversions.class.flatten3(this, f);
/*    */   }
/*    */   
/*    */   public <A, B, C, D, E> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E> flatten4(Function4 f) {
/* 21 */     return ImplicitConversions.class.flatten4(this, f);
/*    */   }
/*    */   
/*    */   public <A, B, C, D, E, F> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E>, F> flatten5(Function5 f) {
/* 21 */     return ImplicitConversions.class.flatten5(this, f);
/*    */   }
/*    */   
/*    */   public <A, T> Function1<Parsers$.tilde<A, Option<List<A>>>, T> headOptionTailToFunList(Function1 f) {
/* 21 */     return ImplicitConversions.class.headOptionTailToFunList(this, f);
/*    */   }
/*    */   
/*    */   public Lexer() {
/* 21 */     ImplicitConversions.class.$init$(this);
/* 80 */     Predef$ predef$ = Predef$.MODULE$;
/* 80 */     this.hexDigits = (Set<Object>)((SetLike)Predef$.MODULE$.Set().apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)Predef$.MODULE$.charArrayOps((char[])(new StringOps("0123456789abcdefABCDEF")).toArray(ClassTag$.MODULE$.Char())));
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Tokens.Token> token() {
/*    */     return string().$up$up((Function1)StringLit()).$bar((Function0)new Lexer$$anonfun$token$1(this)).$bar((Function0)new Lexer$$anonfun$token$2(this)).$bar((Function0)new Lexer$$anonfun$token$3(this)).$bar((Function0)new Lexer$$anonfun$token$4(this)).$bar((Function0)new Lexer$$anonfun$token$5(this)).$bar((Function0)new Lexer$$anonfun$token$6(this)).$bar((Function0)new Lexer$$anonfun$token$7(this)).$bar((Function0)new Lexer$$anonfun$token$8(this)).$bar((Function0)new Lexer$$anonfun$token$9(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$1 extends AbstractFunction0<Parsers.Parser<Tokens.ErrorToken>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.ErrorToken> apply() {
/*    */       return this.$outer.number().$tilde((Function0)new Lexer$$anonfun$token$1$$anonfun$apply$1(this)).$up$up((Function1)new Lexer$$anonfun$token$1$$anonfun$apply$2(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$1$$anonfun$apply$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.letter();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$1$$anonfun$apply$1(Lexer$$anonfun$token$1 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$1$$anonfun$apply$2 extends AbstractFunction1<Parsers$.tilde<String, Object>, Tokens.ErrorToken> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Tokens.ErrorToken apply(Parsers$.tilde x0$4) {
/*    */         if (x0$4 != null)
/*    */           return new Tokens.ErrorToken((Tokens)this.$outer.$outer, (new StringBuilder()).append("Invalid number format : ").append(x0$4._1()).append(x0$4._2()).toString()); 
/*    */         throw new MatchError(x0$4);
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$1$$anonfun$apply$2(Lexer$$anonfun$token$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$2 extends AbstractFunction0<Parsers.Parser<Tokens.ErrorToken>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.ErrorToken> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('-')).$tilde$greater((Function0)new Lexer$$anonfun$token$2$$anonfun$apply$3(this)).$tilde((Function0)new Lexer$$anonfun$token$2$$anonfun$apply$4(this)).$tilde((Function0)new Lexer$$anonfun$token$2$$anonfun$apply$5(this)).$up$up((Function1)new Lexer$$anonfun$token$2$$anonfun$apply$6(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$2(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$2$$anonfun$apply$3 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<List<Object>> apply() {
/*    */         return this.$outer.$outer.whitespace();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$2$$anonfun$apply$3(Lexer$$anonfun$token$2 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$2$$anonfun$apply$4 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<String> apply() {
/*    */         return this.$outer.$outer.number();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$2$$anonfun$apply$4(Lexer$$anonfun$token$2 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$2$$anonfun$apply$5 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.letter();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$2$$anonfun$apply$5(Lexer$$anonfun$token$2 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$2$$anonfun$apply$6 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<List<Object>, String>, Object>, Tokens.ErrorToken> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Tokens.ErrorToken apply(Parsers$.tilde x0$5) {
/*    */         if (x0$5 != null && x0$5._1() != null)
/*    */           return new Tokens.ErrorToken((Tokens)this.$outer.$outer, (new StringBuilder()).append("Invalid number format : -").append(((Parsers$.tilde)x0$5._1())._2()).append(x0$5._2()).toString()); 
/*    */         throw new MatchError(x0$5);
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$2$$anonfun$apply$6(Lexer$$anonfun$token$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$3 extends AbstractFunction0<Parsers.Parser<StdTokens.NumericLit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<StdTokens.NumericLit> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('-')).$tilde$greater((Function0)new Lexer$$anonfun$token$3$$anonfun$apply$7(this)).$tilde((Function0)new Lexer$$anonfun$token$3$$anonfun$apply$8(this)).$up$up((Function1)new Lexer$$anonfun$token$3$$anonfun$apply$9(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$3(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$3$$anonfun$apply$7 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<List<Object>> apply() {
/*    */         return this.$outer.$outer.whitespace();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$3$$anonfun$apply$7(Lexer$$anonfun$token$3 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$3$$anonfun$apply$8 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<String> apply() {
/*    */         return this.$outer.$outer.number();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$3$$anonfun$apply$8(Lexer$$anonfun$token$3 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$3$$anonfun$apply$9 extends AbstractFunction1<Parsers$.tilde<List<Object>, String>, StdTokens.NumericLit> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final StdTokens.NumericLit apply(Parsers$.tilde x0$6) {
/*    */         if (x0$6 != null)
/*    */           return new StdTokens.NumericLit((StdTokens)this.$outer.$outer, (new StringBuilder()).append("-").append(x0$6._2()).toString()); 
/*    */         throw new MatchError(x0$6);
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$3$$anonfun$apply$9(Lexer$$anonfun$token$3 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$4 extends AbstractFunction0<Parsers.Parser<StdTokens.NumericLit>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<StdTokens.NumericLit> apply() {
/*    */       return this.$outer.number().$up$up((Function1)this.$outer.NumericLit());
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$4(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$5 extends AbstractFunction0<Parsers.Parser<Tokens$EOF$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens$EOF$> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\032')).$up$up$up((Function0)new Lexer$$anonfun$token$5$$anonfun$apply$10(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$5(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$5$$anonfun$apply$10 extends AbstractFunction0<Tokens$EOF$> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Tokens$EOF$ apply() {
/*    */         return this.$outer.$outer.EOF();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$5$$anonfun$apply$10(Lexer$$anonfun$token$5 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$6 extends AbstractFunction0<Parsers.Parser<Tokens.Token>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Tokens.Token> apply() {
/*    */       return this.$outer.delim();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$6(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$7 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Nothing$> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('"')).$tilde$greater((Function0)new Lexer$$anonfun$token$7$$anonfun$apply$11(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$7(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$7$$anonfun$apply$11 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Nothing$> apply() {
/*    */         return this.$outer.$outer.failure("Unterminated string");
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$7$$anonfun$apply$11(Lexer$$anonfun$token$7 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$8 extends AbstractFunction0<Parsers.Parser<Serializable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Serializable> apply() {
/*    */       return this.$outer.rep((Function0)new Lexer$$anonfun$token$8$$anonfun$apply$12(this)).$up$up((Function1)new Lexer$$anonfun$token$8$$anonfun$apply$13(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$8(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$token$8$$anonfun$apply$12 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.letter();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$8$$anonfun$apply$12(Lexer$$anonfun$token$8 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$token$8$$anonfun$apply$13 extends AbstractFunction1<List<Object>, Serializable> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Serializable apply(List<Object> xs) {
/*    */         return this.$outer.$outer.checkKeyword(xs);
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$token$8$$anonfun$apply$13(Lexer$$anonfun$token$8 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$token$9 extends AbstractFunction0<Parsers.Parser<Nothing$>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Nothing$> apply() {
/*    */       return this.$outer.failure("Illegal character");
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$token$9(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Serializable checkKeyword(List xs) {
/*    */     String strRep = xs.mkString("");
/*    */     return reserved().contains(strRep) ? (Serializable)new StdTokens.Keyword((StdTokens)this, strRep) : (Serializable)new Tokens.ErrorToken((Tokens)this, (new StringBuilder()).append("Not a keyword: ").append(strRep).toString());
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> string() {
/*    */     return accept(BoxesRunTime.boxToCharacter('"')).$tilde$greater((Function0)new Lexer$$anonfun$string$1(this)).$less$tilde((Function0)new Lexer$$anonfun$string$2(this)).$up$up((Function1)new Lexer$$anonfun$string$3(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$string$1 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<List<Object>> apply() {
/*    */       return this.$outer.rep((Function0)new Lexer$$anonfun$string$1$$anonfun$apply$14(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$string$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$string$1$$anonfun$apply$14 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.charSeq().$bar((Function0)new Lexer$$anonfun$string$1$$anonfun$apply$14$$anonfun$apply$15(this));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$string$1$$anonfun$apply$14(Lexer$$anonfun$string$1 $outer) {}
/*    */       
/*    */       public class Lexer$$anonfun$string$1$$anonfun$apply$14$$anonfun$apply$15 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */         public static final long serialVersionUID = 0L;
/*    */         
/*    */         public final Parsers.Parser<Object> apply() {
/*    */           return this.$outer.$outer.$outer.chrExcept((Seq)Predef$.MODULE$.wrapCharArray(new char[] { '"', '\n', '\032' }));
/*    */         }
/*    */         
/*    */         public Lexer$$anonfun$string$1$$anonfun$apply$14$$anonfun$apply$15(Lexer$$anonfun$string$1$$anonfun$apply$14 $outer) {}
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$string$2 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('"'));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$string$2(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$string$3 extends AbstractFunction1<List<Object>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(List x$1) {
/*    */       return x$1.mkString("");
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$string$3(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<List<Object>> whitespace() {
/*    */     return rep((Function0)new Lexer$$anonfun$whitespace$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$whitespace$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.whitespaceChar();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$whitespace$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> number() {
/*    */     return intPart().$tilde((Function0)new Lexer$$anonfun$number$1(this)).$tilde((Function0)new Lexer$$anonfun$number$2(this)).$up$up((Function1)new Lexer$$anonfun$number$3(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$number$1 extends AbstractFunction0<Parsers.Parser<Option<String>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Option<String>> apply() {
/*    */       return this.$outer.opt((Function0)new Lexer$$anonfun$number$1$$anonfun$apply$16(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$number$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$number$1$$anonfun$apply$16 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<String> apply() {
/*    */         return this.$outer.$outer.fracPart();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$number$1$$anonfun$apply$16(Lexer$$anonfun$number$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$number$2 extends AbstractFunction0<Parsers.Parser<Option<String>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Option<String>> apply() {
/*    */       return this.$outer.opt((Function0)new Lexer$$anonfun$number$2$$anonfun$apply$17(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$number$2(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$number$2$$anonfun$apply$17 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<String> apply() {
/*    */         return this.$outer.$outer.expPart();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$number$2$$anonfun$apply$17(Lexer$$anonfun$number$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$number$3 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<String, Option<String>>, Option<String>>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Parsers$.tilde x0$3) {
/*    */       if (x0$3 != null && x0$3._1() != null)
/*    */         return (new StringBuilder()).append(((Parsers$.tilde)x0$3._1())._1()).append(this.$outer.scala$util$parsing$json$Lexer$$optString(".", (Option)((Parsers$.tilde)x0$3._1())._2())).append(this.$outer.scala$util$parsing$json$Lexer$$optString("", (Option)x0$3._2())).toString(); 
/*    */       throw new MatchError(x0$3);
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$number$3(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> intPart() {
/*    */     return zero().$bar((Function0)new Lexer$$anonfun$intPart$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$intPart$1 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.intList();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$intPart$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> intList() {
/*    */     return nonzero().$tilde((Function0)new Lexer$$anonfun$intList$1(this)).$up$up((Function1)new Lexer$$anonfun$intList$2(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$intList$1 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<List<Object>> apply() {
/*    */       return this.$outer.rep((Function0)new Lexer$$anonfun$intList$1$$anonfun$apply$18(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$intList$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$intList$1$$anonfun$apply$18 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.digit();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$intList$1$$anonfun$apply$18(Lexer$$anonfun$intList$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$intList$2 extends AbstractFunction1<Parsers$.tilde<Object, List<Object>>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Parsers$.tilde x0$1) {
/*    */       if (x0$1 != null) {
/*    */         char c = BoxesRunTime.unboxToChar(x0$1._1());
/*    */         return ((List)x0$1._2()).$colon$colon(BoxesRunTime.boxToCharacter(c)).mkString("");
/*    */       } 
/*    */       throw new MatchError(x0$1);
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$intList$2(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> fracPart() {
/*    */     return accept(BoxesRunTime.boxToCharacter('.')).$tilde$greater((Function0)new Lexer$$anonfun$fracPart$1(this)).$up$up((Function1)new Lexer$$anonfun$fracPart$2(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$fracPart$1 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<List<Object>> apply() {
/*    */       return this.$outer.rep((Function0)new Lexer$$anonfun$fracPart$1$$anonfun$apply$19(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$fracPart$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$fracPart$1$$anonfun$apply$19 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.digit();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$fracPart$1$$anonfun$apply$19(Lexer$$anonfun$fracPart$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$fracPart$2 extends AbstractFunction1<List<Object>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(List x$3) {
/*    */       return x$3.mkString("");
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$fracPart$2(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> expPart() {
/*    */     return exponent().$tilde((Function0)new Lexer$$anonfun$expPart$1(this)).$tilde((Function0)new Lexer$$anonfun$expPart$2(this)).$up$up((Function1)new Lexer$$anonfun$expPart$3(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$expPart$1 extends AbstractFunction0<Parsers.Parser<Option<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Option<Object>> apply() {
/*    */       return this.$outer.opt((Function0)new Lexer$$anonfun$expPart$1$$anonfun$apply$20(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$expPart$1(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$expPart$1$$anonfun$apply$20 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.sign();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$expPart$1$$anonfun$apply$20(Lexer$$anonfun$expPart$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$expPart$2 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<List<Object>> apply() {
/*    */       return this.$outer.rep1((Function0)new Lexer$$anonfun$expPart$2$$anonfun$apply$21(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$expPart$2(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$expPart$2$$anonfun$apply$21 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.digit();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$expPart$2$$anonfun$apply$21(Lexer$$anonfun$expPart$2 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$expPart$3 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<Object, Option<Object>>, List<Object>>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Parsers$.tilde x0$2) {
/*    */       if (x0$2 != null && x0$2._1() != null)
/*    */         return (new StringBuilder()).append(BoxesRunTime.unboxToChar(((Parsers$.tilde)x0$2._1())._1())).append(this.$outer.scala$util$parsing$json$Lexer$$optString("", (Option)((Parsers$.tilde)x0$2._1())._2())).append(((TraversableOnce)x0$2._2()).mkString("")).toString(); 
/*    */       throw new MatchError(x0$2);
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$expPart$3(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public <A> String scala$util$parsing$json$Lexer$$optString(String pre, Option a) {
/*    */     String str;
/*    */     if (a instanceof Some) {
/*    */       Some some = (Some)a;
/*    */       str = (new StringBuilder()).append(pre).append(some.x().toString()).toString();
/*    */     } else {
/*    */       if (None$.MODULE$ == null) {
/*    */         if (a != null)
/*    */           throw new MatchError(a); 
/*    */       } else if (!None$.MODULE$.equals(a)) {
/*    */         throw new MatchError(a);
/*    */       } 
/*    */       str = "";
/*    */     } 
/*    */     return str;
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> zero() {
/*    */     return accept(BoxesRunTime.boxToCharacter('0')).$up$up$up((Function0)new Lexer$$anonfun$zero$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$zero$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       return "0";
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$zero$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> nonzero() {
/*    */     return elem("nonzero digit", (Function1)new Lexer$$anonfun$nonzero$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$nonzero$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char d) {
/*    */       Predef$ predef$ = Predef$.MODULE$;
/*    */       return (RichChar$.MODULE$.isDigit$extension(d) && d != '0');
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$nonzero$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> exponent() {
/*    */     return elem("exponent character", (Function1)new Lexer$$anonfun$exponent$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$exponent$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char d) {
/*    */       return (d == 'e' || d == 'E');
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$exponent$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> sign() {
/*    */     return elem("sign character", (Function1)new Lexer$$anonfun$sign$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$sign$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char d) {
/*    */       return (d == '-' || d == '+');
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$sign$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> charSeq() {
/*    */     return accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$1(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$2(this)).$bar((Function0)new Lexer$$anonfun$charSeq$3(this)).$bar((Function0)new Lexer$$anonfun$charSeq$4(this)).$bar((Function0)new Lexer$$anonfun$charSeq$5(this)).$bar((Function0)new Lexer$$anonfun$charSeq$6(this)).$bar((Function0)new Lexer$$anonfun$charSeq$7(this)).$bar((Function0)new Lexer$$anonfun$charSeq$8(this)).$bar((Function0)new Lexer$$anonfun$charSeq$9(this)).$bar((Function0)new Lexer$$anonfun$charSeq$10(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('"'));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$2 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       return "\"";
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$2(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$3 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$3$$anonfun$apply$22(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$3$$anonfun$apply$23(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$3(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$3$$anonfun$apply$22 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('\\'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$3$$anonfun$apply$22(Lexer$$anonfun$charSeq$3 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$3$$anonfun$apply$23 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\\";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$3$$anonfun$apply$23(Lexer$$anonfun$charSeq$3 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$4 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$4$$anonfun$apply$24(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$4$$anonfun$apply$25(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$4(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$4$$anonfun$apply$24 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('/'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$4$$anonfun$apply$24(Lexer$$anonfun$charSeq$4 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$4$$anonfun$apply$25 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "/";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$4$$anonfun$apply$25(Lexer$$anonfun$charSeq$4 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$5 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$5$$anonfun$apply$26(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$5$$anonfun$apply$27(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$5(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$5$$anonfun$apply$26 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('b'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$5$$anonfun$apply$26(Lexer$$anonfun$charSeq$5 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$5$$anonfun$apply$27 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\b";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$5$$anonfun$apply$27(Lexer$$anonfun$charSeq$5 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$6 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$6$$anonfun$apply$28(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$6$$anonfun$apply$29(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$6(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$6$$anonfun$apply$28 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('f'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$6$$anonfun$apply$28(Lexer$$anonfun$charSeq$6 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$6$$anonfun$apply$29 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\f";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$6$$anonfun$apply$29(Lexer$$anonfun$charSeq$6 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$7 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$7$$anonfun$apply$30(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$7$$anonfun$apply$31(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$7(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$7$$anonfun$apply$30 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('n'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$7$$anonfun$apply$30(Lexer$$anonfun$charSeq$7 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$7$$anonfun$apply$31 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\n";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$7$$anonfun$apply$31(Lexer$$anonfun$charSeq$7 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$8 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$8$$anonfun$apply$32(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$8$$anonfun$apply$33(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$8(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$8$$anonfun$apply$32 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('r'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$8$$anonfun$apply$32(Lexer$$anonfun$charSeq$8 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$8$$anonfun$apply$33 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\r";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$8$$anonfun$apply$33(Lexer$$anonfun$charSeq$8 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$9 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde((Function0)new Lexer$$anonfun$charSeq$9$$anonfun$apply$34(this)).$up$up$up((Function0)new Lexer$$anonfun$charSeq$9$$anonfun$apply$35(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$9(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$9$$anonfun$apply$34 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('t'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$9$$anonfun$apply$34(Lexer$$anonfun$charSeq$9 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$9$$anonfun$apply$35 extends AbstractFunction0<String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply() {
/*    */         return "\t";
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$9$$anonfun$apply$35(Lexer$$anonfun$charSeq$9 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$charSeq$10 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/*    */       return this.$outer.accept(BoxesRunTime.boxToCharacter('\\')).$tilde$greater((Function0)new Lexer$$anonfun$charSeq$10$$anonfun$apply$36(this)).$tilde$greater((Function0)new Lexer$$anonfun$charSeq$10$$anonfun$apply$37(this));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$charSeq$10(Lexer $outer) {}
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$10$$anonfun$apply$36 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<Object> apply() {
/*    */         return this.$outer.$outer.accept(BoxesRunTime.boxToCharacter('u'));
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$10$$anonfun$apply$36(Lexer$$anonfun$charSeq$10 $outer) {}
/*    */     }
/*    */     
/*    */     public class Lexer$$anonfun$charSeq$10$$anonfun$apply$37 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Parsers.Parser<String> apply() {
/*    */         return this.$outer.$outer.scala$util$parsing$json$Lexer$$unicodeBlock();
/*    */       }
/*    */       
/*    */       public Lexer$$anonfun$charSeq$10$$anonfun$apply$37(Lexer$$anonfun$charSeq$10 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public Set<Object> hexDigits() {
/* 80 */     return this.hexDigits;
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> hexDigit() {
/* 81 */     return elem("hex digit", (Function1)new Lexer$$anonfun$hexDigit$1(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$hexDigit$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char x$4) {
/* 81 */       return this.$outer.hexDigits().contains(BoxesRunTime.boxToCharacter(x$4));
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$hexDigit$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> scala$util$parsing$json$Lexer$$unicodeBlock() {
/* 83 */     return hexDigit().$tilde((Function0)new Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$1(this)).$tilde((Function0)new Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$2(this)).$tilde((Function0)new Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$3(this)).$up$up((Function1)new Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$4(this));
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 83 */       return this.$outer.hexDigit();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$1(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$2 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 83 */       return this.$outer.hexDigit();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$2(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$3 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 83 */       return this.$outer.hexDigit();
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$3(Lexer $outer) {}
/*    */   }
/*    */   
/*    */   public class Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$4 extends AbstractFunction1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<Object, Object>, Object>, Object>, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Parsers$.tilde x0$7) {
/* 83 */       if (x0$7 != null && x0$7._1() != null && ((Parsers$.tilde)x0$7._1())._1() != null)
/* 83 */         return 
/*    */           
/* 85 */           new String(new int[] { Integer.parseInt(List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapCharArray(new char[] { BoxesRunTime.unboxToChar(((Parsers$.tilde)((Parsers$.tilde)x0$7._1())._1())._1()), BoxesRunTime.unboxToChar(((Parsers$.tilde)((Parsers$.tilde)x0$7._1())._1())._2()), BoxesRunTime.unboxToChar(((Parsers$.tilde)x0$7._1())._2()), BoxesRunTime.unboxToChar(x0$7._2()) }, )).mkString(""), 16) }0, 1); 
/*    */       throw new MatchError(x0$7);
/*    */     }
/*    */     
/*    */     public Lexer$$anonfun$scala$util$parsing$json$Lexer$$unicodeBlock$4(Lexer $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\Lexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */