/*     */ package scala.util.parsing.json;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.Null$;
/*     */ import scala.util.DynamicVariable;
/*     */ import scala.util.parsing.combinator.ImplicitConversions;
/*     */ import scala.util.parsing.combinator.Parsers;
/*     */ import scala.util.parsing.combinator.Parsers$;
/*     */ import scala.util.parsing.combinator.Parsers$$tilde$;
/*     */ import scala.util.parsing.combinator.Parsers$Error$;
/*     */ import scala.util.parsing.combinator.Parsers$Failure$;
/*     */ import scala.util.parsing.combinator.Parsers$NoSuccess$;
/*     */ import scala.util.parsing.combinator.Parsers$Success$;
/*     */ import scala.util.parsing.combinator.syntactical.StdTokenParsers;
/*     */ import scala.util.parsing.combinator.token.StdTokens;
/*     */ import scala.util.parsing.combinator.token.Tokens;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ea\001B\001\003\001-\021a\001U1sg\026\024(BA\002\005\003\021Q7o\0348\013\005\0251\021a\0029beNLgn\032\006\003\017!\tA!\036;jY*\t\021\"A\003tG\006d\027m\001\001\024\t\001a\001\003\007\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007CA\t\027\033\005\021\"BA\n\025\003-\031\030P\034;bGRL7-\0317\013\005U!\021AC2p[\nLg.\031;pe&\021qC\005\002\020'R$Gk\\6f]B\013'o]3sgB\021\021DG\007\002)%\0211\004\006\002\024\0236\004H.[2ji\016{gN^3sg&|gn\035\005\006;\001!\tAH\001\007y%t\027\016\036 \025\003}\001\"\001\t\001\016\003\t)AA\t\001\001G\t1Ak\\6f]N\004\"\001\t\023\n\005\025\022!!\002'fq\026\024\bbB\024\001\005\004%\t\001K\001\bY\026D\030nY1m+\005\031\003B\002\026\001A\003%1%\001\005mKbL7-\0317!\013\021a\003\001A\027\003\0339+X.\032:jGB\013'o]3s!\021ia\006M\034\n\005=B!!\003$v]\016$\030n\03482!\t\tDG\004\002\016e%\0211\007C\001\007!J,G-\0324\n\005U2$AB*ue&twM\003\0024\021A\021Q\002O\005\003s!\0211!\0218z\021\035Y\004\0011A\005\022q\n1\003Z3gCVdGOT;nE\026\024\b+\031:tKJ,\022!\020\t\003}-j\021\001\001\005\b\001\002\001\r\021\"\005B\003]!WMZ1vYRtU/\0342feB\013'o]3s?\022*\027\017\006\002C\013B\021QbQ\005\003\t\"\021A!\0268ji\"9aiPA\001\002\004i\024a\001=%c!1\001\n\001Q!\nu\nA\003Z3gCVdGOT;nE\026\024\b+\031:tKJ\004\003b\002&\001\005\004%\tbS\001\r]Vl'-\032:QCJ\034XM]\013\002\031B\031QJU\037\016\0039S!a\024)\002\t1\fgn\032\006\002#\006!!.\031<b\023\t\031fJA\006UQJ,\027\r\032'pG\006d\007BB+\001A\003%A*A\007ok6\024WM\035)beN,'\017\t\005\006/\002!\t\001W\001\005e>|G/F\001Z!\rq$,X\005\003\003mK!\001\030\013\003\017A\013'o]3sgJ!a\fY2g\r\021y\006\001A/\003\031q\022XMZ5oK6,g\016\036 \021\0055\t\027B\0012\t\0051\031VM]5bY&T\030M\0317f!\tiA-\003\002f\021\t9\001K]8ek\016$\bC\001\021h\023\tA'A\001\005K'>sE+\0379f\021\025Q\007\001\"\001l\003\035Q7o\0348PE*,\022\001\034\t\004}ik\007C\001\021o\023\ty'A\001\006K'>suJ\0316fGRDQ!\035\001\005\002I\f\021B[:p]\006\023(/Y=\026\003M\0042A\020.u!\t\001S/\003\002w\005\tI!jU(O\003J\024\030-\037\005\006q\002!\t!_\001\t_\nTWI\034;ssV\t!\020E\002?5n\004B!\004?1o%\021Q\020\003\002\007)V\004H.\032\032\t\r}\004A\021AA\001\003\0251\030\r\\;f+\t\t\031\001E\002?5^Bq!a\002\001\t\003\tI!A\005tiJLgn\032,bYV\021\0211\002\t\004}i\003\004bBA\b\001\021\005\021\021A\001\007]Vl'-\032:")
/*     */ public class Parser implements StdTokenParsers, ImplicitConversions {
/*     */   private final Lexer lexical;
/*     */   
/*     */   private Function1<String, Object> defaultNumberParser;
/*     */   
/*     */   private final ThreadLocal<Function1<String, Object>> numberParser;
/*     */   
/*     */   private final HashMap<String, Parsers.Parser<String>> keywordCache;
/*     */   
/*     */   private final DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*     */   
/*     */   private volatile Parsers$Success$ Success$module;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   private volatile Parsers$NoSuccess$ NoSuccess$module;
/*     */   
/*     */   private volatile Parsers$Failure$ Failure$module;
/*     */   
/*     */   private volatile Parsers$Error$ Error$module;
/*     */   
/*     */   private volatile Parsers$$tilde$ $tilde$module;
/*     */   
/*     */   public <A, B, C> Function1<Parsers$.tilde<A, B>, C> flatten2(Function2 f) {
/* 113 */     return ImplicitConversions.class.flatten2(this, f);
/*     */   }
/*     */   
/*     */   public <A, B, C, D> Function1<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D> flatten3(Function3 f) {
/* 113 */     return ImplicitConversions.class.flatten3(this, f);
/*     */   }
/*     */   
/*     */   public <A, B, C, D, E> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E> flatten4(Function4 f) {
/* 113 */     return ImplicitConversions.class.flatten4(this, f);
/*     */   }
/*     */   
/*     */   public <A, B, C, D, E, F> Function1<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<Parsers$.tilde<A, B>, C>, D>, E>, F> flatten5(Function5 f) {
/* 113 */     return ImplicitConversions.class.flatten5(this, f);
/*     */   }
/*     */   
/*     */   public <A, T> Function1<Parsers$.tilde<A, Option<List<A>>>, T> headOptionTailToFunList(Function1 f) {
/* 113 */     return ImplicitConversions.class.headOptionTailToFunList(this, f);
/*     */   }
/*     */   
/*     */   public HashMap<String, Parsers.Parser<String>> keywordCache() {
/* 113 */     return this.keywordCache;
/*     */   }
/*     */   
/*     */   public void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(HashMap<String, Parsers.Parser<String>> x$1) {
/* 113 */     this.keywordCache = x$1;
/*     */   }
/*     */   
/*     */   public Parsers.Parser<String> keyword(String chars) {
/* 113 */     return StdTokenParsers.class.keyword(this, chars);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<String> numericLit() {
/* 113 */     return StdTokenParsers.class.numericLit(this);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<String> stringLit() {
/* 113 */     return StdTokenParsers.class.stringLit(this);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<String> ident() {
/* 113 */     return StdTokenParsers.class.ident(this);
/*     */   }
/*     */   
/*     */   private Parsers$Success$ Success$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (this.Success$module == null)
/* 113 */         this.Success$module = new Parsers$Success$((Parsers)this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.Success$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Parsers$Success$ Success() {
/* 113 */     return (this.Success$module == null) ? Success$lzycompute() : this.Success$module;
/*     */   }
/*     */   
/*     */   private DynamicVariable scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (!this.bitmap$0) {
/* 113 */         this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar = Parsers.class.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar((Parsers)this);
/* 113 */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar() {
/* 113 */     return this.bitmap$0 ? this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar : scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute();
/*     */   }
/*     */   
/*     */   private Parsers$NoSuccess$ NoSuccess$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (this.NoSuccess$module == null)
/* 113 */         this.NoSuccess$module = new Parsers$NoSuccess$((Parsers)this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.NoSuccess$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Parsers$NoSuccess$ NoSuccess() {
/* 113 */     return (this.NoSuccess$module == null) ? NoSuccess$lzycompute() : this.NoSuccess$module;
/*     */   }
/*     */   
/*     */   private Parsers$Failure$ Failure$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (this.Failure$module == null)
/* 113 */         this.Failure$module = new Parsers$Failure$((Parsers)this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.Failure$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Parsers$Failure$ Failure() {
/* 113 */     return (this.Failure$module == null) ? Failure$lzycompute() : this.Failure$module;
/*     */   }
/*     */   
/*     */   private Parsers$Error$ Error$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (this.Error$module == null)
/* 113 */         this.Error$module = new Parsers$Error$((Parsers)this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.Error$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Parsers$Error$ Error() {
/* 113 */     return (this.Error$module == null) ? Error$lzycompute() : this.Error$module;
/*     */   }
/*     */   
/*     */   private Parsers$$tilde$ $tilde$lzycompute() {
/* 113 */     synchronized (this) {
/* 113 */       if (this.$tilde$module == null)
/* 113 */         this.$tilde$module = new Parsers$$tilde$((Parsers)this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/json/Parser}} */
/* 113 */       return this.$tilde$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Parsers$$tilde$ $tilde() {
/* 113 */     return (this.$tilde$module == null) ? $tilde$lzycompute() : this.$tilde$module;
/*     */   }
/*     */   
/*     */   public Parsers.NoSuccess lastNoSuccess() {
/* 113 */     return Parsers.class.lastNoSuccess((Parsers)this);
/*     */   }
/*     */   
/*     */   public void lastNoSuccess_$eq(Parsers.NoSuccess x) {
/* 113 */     Parsers.class.lastNoSuccess_$eq((Parsers)this, x);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> Parser(Function1 f) {
/* 113 */     return Parsers.class.Parser((Parsers)this, f);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> OnceParser(Function1 f) {
/* 113 */     return (Parsers.Parser<T>)Parsers.class.OnceParser((Parsers)this, f);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> commit(Function0 p) {
/* 113 */     return Parsers.class.commit((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> elem(String kind, Function1 p) {
/* 113 */     return Parsers.class.elem((Parsers)this, kind, p);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> elem(Object e) {
/* 113 */     return Parsers.class.elem((Parsers)this, e);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> accept(Object e) {
/* 113 */     return Parsers.class.accept((Parsers)this, e);
/*     */   }
/*     */   
/*     */   public <ES> Parsers.Parser<List<Object>> accept(Object es, Function1 evidence$1) {
/* 113 */     return Parsers.class.accept((Parsers)this, es, evidence$1);
/*     */   }
/*     */   
/*     */   public <U> Parsers.Parser<U> accept(String expected, PartialFunction f) {
/* 113 */     return Parsers.class.accept((Parsers)this, expected, f);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> acceptIf(Function1 p, Function1 err) {
/* 113 */     return Parsers.class.acceptIf((Parsers)this, p, err);
/*     */   }
/*     */   
/*     */   public <U> Parsers.Parser<U> acceptMatch(String expected, PartialFunction f) {
/* 113 */     return Parsers.class.acceptMatch((Parsers)this, expected, f);
/*     */   }
/*     */   
/*     */   public <ES> Parsers.Parser<List<Object>> acceptSeq(Object es, Function1 evidence$2) {
/* 113 */     return Parsers.class.acceptSeq((Parsers)this, es, evidence$2);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Nothing$> failure(String msg) {
/* 113 */     return Parsers.class.failure((Parsers)this, msg);
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Nothing$> err(String msg) {
/* 113 */     return Parsers.class.err((Parsers)this, msg);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> success(Object v) {
/* 113 */     return Parsers.class.success((Parsers)this, v);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> log(Function0 p, String name) {
/* 113 */     return Parsers.class.log((Parsers)this, p, name);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> rep(Function0 p) {
/* 113 */     return Parsers.class.rep((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> repsep(Function0 p, Function0 q) {
/* 113 */     return Parsers.class.repsep((Parsers)this, p, q);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> rep1(Function0 p) {
/* 113 */     return Parsers.class.rep1((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> rep1(Function0 first, Function0 p0) {
/* 113 */     return Parsers.class.rep1((Parsers)this, first, p0);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> repN(int num, Function0 p) {
/* 113 */     return Parsers.class.repN((Parsers)this, num, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<List<T>> rep1sep(Function0 p, Function0 q) {
/* 113 */     return Parsers.class.rep1sep((Parsers)this, p, q);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> chainl1(Function0 p, Function0 q) {
/* 113 */     return Parsers.class.chainl1((Parsers)this, p, q);
/*     */   }
/*     */   
/*     */   public <T, U> Parsers.Parser<T> chainl1(Function0 first, Function0 p, Function0 q) {
/* 113 */     return Parsers.class.chainl1((Parsers)this, first, p, q);
/*     */   }
/*     */   
/*     */   public <T, U> Parsers.Parser<U> chainr1(Function0 p, Function0 q, Function2 combine, Object first) {
/* 113 */     return Parsers.class.chainr1((Parsers)this, p, q, combine, first);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<Option<T>> opt(Function0 p) {
/* 113 */     return Parsers.class.opt((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<BoxedUnit> not(Function0 p) {
/* 113 */     return Parsers.class.not((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> guard(Function0 p) {
/* 113 */     return Parsers.class.guard((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0 p) {
/* 113 */     return Parsers.class.positioned((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Parsers.Parser<T> phrase(Parsers.Parser p) {
/* 113 */     return Parsers.class.phrase((Parsers)this, p);
/*     */   }
/*     */   
/*     */   public <T> Function1<Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/* 113 */     return Parsers.class.mkList((Parsers)this);
/*     */   }
/*     */   
/*     */   public Parser() {
/* 113 */     Parsers.class.$init$((Parsers)this);
/* 113 */     StdTokenParsers.class.$init$(this);
/* 113 */     ImplicitConversions.class.$init$(this);
/* 116 */     this.lexical = new Lexer();
/* 119 */     (new String[3])[0] = "true";
/* 119 */     (new String[3])[1] = "false";
/* 119 */     (new String[3])[2] = "null";
/* 119 */     lexical().reserved().$plus$plus$eq((TraversableOnce)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[3])));
/* 120 */     (new String[6])[0] = "{";
/* 120 */     (new String[6])[1] = "}";
/* 120 */     (new String[6])[2] = "[";
/* 120 */     (new String[6])[3] = "]";
/* 120 */     (new String[6])[4] = ":";
/* 120 */     (new String[6])[5] = ",";
/* 120 */     lexical().delimiters().$plus$plus$eq((TraversableOnce)List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[6])));
/* 126 */     this.defaultNumberParser = (Function1<String, Object>)new $anonfun$2(this);
/* 129 */     this.numberParser = new $anon$1(this);
/*     */   }
/*     */   
/*     */   public Lexer lexical() {
/*     */     return this.lexical;
/*     */   }
/*     */   
/*     */   public Function1<String, Object> defaultNumberParser() {
/*     */     return this.defaultNumberParser;
/*     */   }
/*     */   
/*     */   public void defaultNumberParser_$eq(Function1<String, Object> x$1) {
/*     */     this.defaultNumberParser = x$1;
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final double apply(String x$1) {
/*     */       Predef$ predef$ = Predef$.MODULE$;
/*     */       return (new StringOps(x$1)).toDouble();
/*     */     }
/*     */     
/*     */     public $anonfun$2(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public ThreadLocal<Function1<String, Object>> numberParser() {
/* 129 */     return this.numberParser;
/*     */   }
/*     */   
/*     */   public class $anon$1 extends ThreadLocal<Function1<String, Object>> {
/*     */     public $anon$1(Parser $outer) {}
/*     */     
/*     */     public Function1<String, Object> initialValue() {
/* 130 */       return this.$outer.defaultNumberParser();
/*     */     }
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Serializable> root() {
/* 134 */     return jsonObj().$bar((Function0)new Parser$$anonfun$root$1(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$root$1 extends AbstractFunction0<Parsers.Parser<JSONArray>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<JSONArray> apply() {
/* 134 */       return this.$outer.jsonArray();
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$root$1(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<JSONObject> jsonObj() {
/* 135 */     return keyword("{").$tilde$greater((Function0)new Parser$$anonfun$jsonObj$1(this)).$less$tilde((Function0)new Parser$$anonfun$jsonObj$2(this)).$up$up((Function1)new Parser$$anonfun$jsonObj$3(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonObj$1 extends AbstractFunction0<Parsers.Parser<List<Tuple2<String, Object>>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<List<Tuple2<String, Object>>> apply() {
/* 135 */       return this.$outer.repsep((Function0<Parsers.Parser<Tuple2<String, Object>>>)new Parser$$anonfun$jsonObj$1$$anonfun$apply$3(this), (Function0<Parsers.Parser<Object>>)new Parser$$anonfun$jsonObj$1$$anonfun$apply$4(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonObj$1(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$jsonObj$1$$anonfun$apply$3 extends AbstractFunction0<Parsers.Parser<Tuple2<String, Object>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<Tuple2<String, Object>> apply() {
/* 135 */         return this.$outer.$outer.objEntry();
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$jsonObj$1$$anonfun$apply$3(Parser$$anonfun$jsonObj$1 $outer) {}
/*     */     }
/*     */     
/*     */     public class Parser$$anonfun$jsonObj$1$$anonfun$apply$4 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<String> apply() {
/* 135 */         return this.$outer.$outer.keyword(",");
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$jsonObj$1$$anonfun$apply$4(Parser$$anonfun$jsonObj$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonObj$2 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<String> apply() {
/* 135 */       return this.$outer.keyword("}");
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonObj$2(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonObj$3 extends AbstractFunction1<List<Tuple2<String, Object>>, JSONObject> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final JSONObject apply(List x0$2) {
/* 135 */       if (x0$2 instanceof List)
/* 135 */         return new JSONObject((Map<String, Object>)Predef$.MODULE$.Map().apply((Seq)x0$2)); 
/* 135 */       throw new MatchError(x0$2);
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonObj$3(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<JSONArray> jsonArray() {
/* 136 */     return keyword("[").$tilde$greater((Function0)new Parser$$anonfun$jsonArray$1(this)).$less$tilde((Function0)new Parser$$anonfun$jsonArray$2(this)).$up$up((Function1)new Parser$$anonfun$jsonArray$3(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonArray$1 extends AbstractFunction0<Parsers.Parser<List<Object>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<List<Object>> apply() {
/* 136 */       return this.$outer.repsep((Function0<Parsers.Parser<Object>>)new Parser$$anonfun$jsonArray$1$$anonfun$apply$5(this), (Function0<Parsers.Parser<Object>>)new Parser$$anonfun$jsonArray$1$$anonfun$apply$6(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonArray$1(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$jsonArray$1$$anonfun$apply$5 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<Object> apply() {
/* 136 */         return this.$outer.$outer.value();
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$jsonArray$1$$anonfun$apply$5(Parser$$anonfun$jsonArray$1 $outer) {}
/*     */     }
/*     */     
/*     */     public class Parser$$anonfun$jsonArray$1$$anonfun$apply$6 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<String> apply() {
/* 136 */         return this.$outer.$outer.keyword(",");
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$jsonArray$1$$anonfun$apply$6(Parser$$anonfun$jsonArray$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonArray$2 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<String> apply() {
/* 136 */       return this.$outer.keyword("]");
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonArray$2(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$jsonArray$3 extends AbstractFunction1<List<Object>, JSONArray> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final JSONArray apply(List<Object> x0$3) {
/* 136 */       if (x0$3 instanceof List)
/* 136 */         return new JSONArray(x0$3); 
/* 136 */       throw new MatchError(x0$3);
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$jsonArray$3(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Tuple2<String, Object>> objEntry() {
/* 137 */     return stringVal().$tilde((Function0)new Parser$$anonfun$objEntry$1(this)).$up$up((Function1)new Parser$$anonfun$objEntry$2(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$objEntry$1 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Object> apply() {
/* 137 */       return this.$outer.keyword(":").$tilde$greater((Function0)new Parser$$anonfun$objEntry$1$$anonfun$apply$7(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$objEntry$1(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$objEntry$1$$anonfun$apply$7 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Parsers.Parser<Object> apply() {
/* 137 */         return this.$outer.$outer.value();
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$objEntry$1$$anonfun$apply$7(Parser$$anonfun$objEntry$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$objEntry$2 extends AbstractFunction1<Parsers$.tilde<String, Object>, Tuple2<String, Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<String, Object> apply(Parsers$.tilde x0$1) {
/* 137 */       if (x0$1 != null)
/* 137 */         return new Tuple2(x0$1._1(), x0$1._2()); 
/* 137 */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$objEntry$2(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> value() {
/* 138 */     return jsonObj().$bar((Function0)new Parser$$anonfun$value$1(this)).$bar((Function0)new Parser$$anonfun$value$2(this)).$bar((Function0)new Parser$$anonfun$value$3(this)).$bar((Function0)new Parser$$anonfun$value$4(this)).$bar((Function0)new Parser$$anonfun$value$5(this)).$bar((Function0)new Parser$$anonfun$value$6(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$1 extends AbstractFunction0<Parsers.Parser<JSONArray>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<JSONArray> apply() {
/* 138 */       return this.$outer.jsonArray();
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$1(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$2 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Object> apply() {
/* 138 */       return this.$outer.number();
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$2(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$3 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Object> apply() {
/* 138 */       return this.$outer.keyword("true").$up$up$up((Function0)new Parser$$anonfun$value$3$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$3(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$value$3$$anonfun$apply$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply() {
/* 138 */         return true;
/*     */       }
/*     */       
/*     */       public boolean apply$mcZ$sp() {
/* 138 */         return true;
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$value$3$$anonfun$apply$1(Parser$$anonfun$value$3 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$4 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Object> apply() {
/* 138 */       return this.$outer.keyword("false").$up$up$up((Function0)new Parser$$anonfun$value$4$$anonfun$apply$2(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$4(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$value$4$$anonfun$apply$2 extends AbstractFunction0.mcZ.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply() {
/* 138 */         return false;
/*     */       }
/*     */       
/*     */       public boolean apply$mcZ$sp() {
/* 138 */         return false;
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$value$4$$anonfun$apply$2(Parser$$anonfun$value$4 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$5 extends AbstractFunction0<Parsers.Parser<Null$>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<Null$> apply() {
/* 138 */       return this.$outer.keyword("null").$up$up$up((Function0)new Parser$$anonfun$value$5$$anonfun$apply$8(this));
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$5(Parser $outer) {}
/*     */     
/*     */     public class Parser$$anonfun$value$5$$anonfun$apply$8 extends AbstractFunction0<Null$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Null$ apply() {
/* 138 */         return null;
/*     */       }
/*     */       
/*     */       public Parser$$anonfun$value$5$$anonfun$apply$8(Parser$$anonfun$value$5 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$value$6 extends AbstractFunction0<Parsers.Parser<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Parsers.Parser<String> apply() {
/* 138 */       return this.$outer.stringVal();
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$value$6(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<String> stringVal() {
/* 139 */     return accept("string", (PartialFunction<Object, String>)new Parser$$anonfun$stringVal$1(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$stringVal$1 extends AbstractPartialFunction<Tokens.Token, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Tokens.Token, B1> B1 applyOrElse(Tokens.Token x1, Function1 default) {
/*     */       Object object;
/* 139 */       if (x1 instanceof StdTokens.StringLit) {
/* 139 */         StdTokens.StringLit stringLit = (StdTokens.StringLit)x1;
/* 139 */         object = stringLit.chars();
/*     */       } else {
/* 139 */         object = default.apply(x1);
/*     */       } 
/* 139 */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Tokens.Token x1) {
/*     */       boolean bool;
/* 139 */       if (x1 instanceof StdTokens.StringLit) {
/* 139 */         bool = true;
/*     */       } else {
/* 139 */         bool = false;
/*     */       } 
/* 139 */       return bool;
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$stringVal$1(Parser $outer) {}
/*     */   }
/*     */   
/*     */   public Parsers.Parser<Object> number() {
/* 140 */     return accept("number", (PartialFunction<Object, Object>)new Parser$$anonfun$number$1(this));
/*     */   }
/*     */   
/*     */   public class Parser$$anonfun$number$1 extends AbstractPartialFunction<Tokens.Token, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final <A1 extends Tokens.Token, B1> B1 applyOrElse(Tokens.Token x2, Function1 default) {
/*     */       Object object;
/* 140 */       if (x2 instanceof StdTokens.NumericLit) {
/* 140 */         StdTokens.NumericLit numericLit = (StdTokens.NumericLit)x2;
/* 140 */         object = ((Function1)this.$outer.numberParser().get()).apply(numericLit.chars());
/*     */       } else {
/* 140 */         object = default.apply(x2);
/*     */       } 
/* 140 */       return (B1)object;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Tokens.Token x2) {
/*     */       boolean bool;
/* 140 */       if (x2 instanceof StdTokens.NumericLit) {
/* 140 */         bool = true;
/*     */       } else {
/* 140 */         bool = false;
/*     */       } 
/* 140 */       return bool;
/*     */     }
/*     */     
/*     */     public Parser$$anonfun$number$1(Parser $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\json\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */