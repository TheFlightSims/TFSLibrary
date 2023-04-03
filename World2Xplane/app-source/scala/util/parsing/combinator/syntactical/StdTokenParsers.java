/*    */ package scala.util.parsing.combinator.syntactical;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.token.StdTokens;
/*    */ import scala.util.parsing.combinator.token.Tokens;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Y3q!\001\002\021\002\007\005QBA\bTi\022$vn[3o!\006\0248/\032:t\025\t\031A!A\006ts:$\030m\031;jG\006d'BA\003\007\003)\031w.\0342j]\006$xN\035\006\003\017!\tq\001]1sg&twM\003\002\n\025\005!Q\017^5m\025\005Y\021!B:dC2\f7\001A\n\004\0019\021\002CA\b\021\033\005Q\021BA\t\013\005\031\te.\037*fMB\0211\003F\007\002\005%\021QC\001\002\r)>\\WM\034)beN,'o\035\005\006/\001!\t\001G\001\007I%t\027\016\036\023\025\003e\001\"a\004\016\n\005mQ!\001B+oSR$Q!\b\001\003\002y\021a\001V8lK:\034\030CA\020#!\ty\001%\003\002\"\025\t9aj\034;iS:<\007CA\022'\033\005!#BA\023\005\003\025!xn[3o\023\t9CEA\005Ti\022$vn[3og\"9\021\006\001b\001\n#Q\023\001D6fs^|'\017Z\"bG\",W#A\026\021\t1\n4gO\007\002[)\021afL\001\b[V$\030M\0317f\025\t\001$\"\001\006d_2dWm\031;j_:L!AM\027\003\017!\0137\017['baB\021A'O\007\002k)\021agN\001\005Y\006twMC\0019\003\021Q\027M^1\n\005i*$AB*ue&tw\rE\002={Mj\021\001A\005\003}}\022a\001U1sg\026\024\030B\001!\005\005\035\001\026M]:feNDaA\021\001!\002\023Y\023!D6fs^|'\017Z\"bG\",\007\005C\003E\001\021\rQ)A\004lKf<xN\0353\025\005\031k\005c\001\037>\017B\021\001j\023\b\003\037%K!A\023\006\002\rA\023X\rZ3g\023\tQDJ\003\002K\025!)aj\021a\001\017\006)1\r[1sg\")\001\013\001C\001#\006Qa.^7fe&\034G*\033;\026\003\031CQa\025\001\005\002E\013\021b\035;sS:<G*\033;\t\013U\003A\021A)\002\013%$WM\034;")
/*    */ public interface StdTokenParsers extends TokenParsers {
/*    */   void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(HashMap paramHashMap);
/*    */   
/*    */   HashMap<String, Parsers.Parser<String>> keywordCache();
/*    */   
/*    */   Parsers.Parser<String> keyword(String paramString);
/*    */   
/*    */   Parsers.Parser<String> numericLit();
/*    */   
/*    */   Parsers.Parser<String> stringLit();
/*    */   
/*    */   Parsers.Parser<String> ident();
/*    */   
/*    */   public class StdTokenParsers$$anonfun$keyword$1 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final String chars$1;
/*    */     
/*    */     public final Parsers.Parser<String> apply() {
/* 36 */       return this.$outer.accept(new StdTokens.Keyword((StdTokens)this.$outer.lexical(), this.chars$1)).$up$up((Function1)new StdTokenParsers$$anonfun$keyword$1$$anonfun$apply$1(this));
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$keyword$1(StdTokenParsers $outer, String chars$1) {}
/*    */     
/*    */     public class StdTokenParsers$$anonfun$keyword$1$$anonfun$apply$1 extends AbstractFunction1<Tokens.Token, String> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final String apply(Tokens.Token x$1) {
/* 36 */         return x$1.chars();
/*    */       }
/*    */       
/*    */       public StdTokenParsers$$anonfun$keyword$1$$anonfun$apply$1(StdTokenParsers$$anonfun$keyword$1 $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$numericLit$1 extends AbstractFunction1<Tokens.Token, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Tokens.Token x$2) {
/* 40 */       return x$2 instanceof StdTokens.NumericLit;
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$numericLit$1(StdTokenParsers $outer) {}
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$numericLit$2 extends AbstractFunction1<Tokens.Token, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Tokens.Token x$3) {
/* 40 */       return x$3.chars();
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$numericLit$2(StdTokenParsers $outer) {}
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$stringLit$1 extends AbstractFunction1<Tokens.Token, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Tokens.Token x$4) {
/* 44 */       return x$4 instanceof StdTokens.StringLit;
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$stringLit$1(StdTokenParsers $outer) {}
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$stringLit$2 extends AbstractFunction1<Tokens.Token, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Tokens.Token x$5) {
/* 44 */       return x$5.chars();
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$stringLit$2(StdTokenParsers $outer) {}
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$ident$1 extends AbstractFunction1<Tokens.Token, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(Tokens.Token x$6) {
/* 48 */       return x$6 instanceof StdTokens.Identifier;
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$ident$1(StdTokenParsers $outer) {}
/*    */   }
/*    */   
/*    */   public class StdTokenParsers$$anonfun$ident$2 extends AbstractFunction1<Tokens.Token, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(Tokens.Token x$7) {
/* 48 */       return x$7.chars();
/*    */     }
/*    */     
/*    */     public StdTokenParsers$$anonfun$ident$2(StdTokenParsers $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\syntactical\StdTokenParsers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */