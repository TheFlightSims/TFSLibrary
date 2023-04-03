/*    */ package scala.util.parsing.combinator.syntactical;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.util.DynamicVariable;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.Parsers$$tilde$;
/*    */ import scala.util.parsing.combinator.Parsers$Error$;
/*    */ import scala.util.parsing.combinator.Parsers$Failure$;
/*    */ import scala.util.parsing.combinator.Parsers$NoSuccess$;
/*    */ import scala.util.parsing.combinator.Parsers$Success$;
/*    */ import scala.util.parsing.combinator.lexical.StdLexical;
/*    */ import scala.util.parsing.combinator.token.Tokens;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001y2A!\001\002\001\033\t!2\013^1oI\006\024H\rV8lK:\004\026M]:feNT!a\001\003\002\027MLh\016^1di&\034\027\r\034\006\003\013\031\t!bY8nE&t\027\r^8s\025\t9\001\"A\004qCJ\034\030N\\4\013\005%Q\021\001B;uS2T\021aC\001\006g\016\fG.Y\002\001'\r\001aB\005\t\003\037Ai\021AC\005\003#)\021a!\0218z%\0264\007CA\n\025\033\005\021\021BA\013\003\005=\031F\017\032+pW\026t\007+\031:tKJ\034\b\"B\f\001\t\003A\022A\002\037j]&$h\bF\001\032!\t\031\002!\002\003\034\001\001a\"A\002+pW\026t7\017\005\002\036A5\taD\003\002 \t\005)Ao\\6f]&\021\021E\b\002\n'R$Gk\\6f]NDqa\t\001C\002\023\005A%A\004mKbL7-\0317\026\003\025\002\"A\n\025\016\003\035R!a\t\003\n\005%:#AC*uI2+\0070[2bY\"11\006\001Q\001\n\025\n\001\002\\3yS\016\fG\016\t\005\006[\001!\031EL\001\bW\026Lxo\034:e)\tyC\bE\0021cUj\021\001A\005\003eM\022a\001U1sg\026\024\030B\001\033\005\005\035\001\026M]:feN\004\"AN\035\017\005=9\024B\001\035\013\003\031\001&/\0323fM&\021!h\017\002\007'R\024\030N\\4\013\005aR\001\"B\037-\001\004)\024!B2iCJ\034\b")
/*    */ public class StandardTokenParsers implements StdTokenParsers {
/*    */   private final StdLexical lexical;
/*    */   
/*    */   private final HashMap<String, Parsers.Parser<String>> keywordCache;
/*    */   
/*    */   private final DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */   
/*    */   private volatile Parsers$Success$ Success$module;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   private volatile Parsers$NoSuccess$ NoSuccess$module;
/*    */   
/*    */   private volatile Parsers$Failure$ Failure$module;
/*    */   
/*    */   private volatile Parsers$Error$ Error$module;
/*    */   
/*    */   private volatile Parsers$$tilde$ $tilde$module;
/*    */   
/*    */   public HashMap<String, Parsers.Parser<String>> keywordCache() {
/* 22 */     return this.keywordCache;
/*    */   }
/*    */   
/*    */   public void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(HashMap<String, Parsers.Parser<String>> x$1) {
/* 22 */     this.keywordCache = x$1;
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> numericLit() {
/* 22 */     return StdTokenParsers$class.numericLit(this);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> stringLit() {
/* 22 */     return StdTokenParsers$class.stringLit(this);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> ident() {
/* 22 */     return StdTokenParsers$class.ident(this);
/*    */   }
/*    */   
/*    */   private Parsers$Success$ Success$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (this.Success$module == null)
/* 22 */         this.Success$module = new Parsers$Success$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.Success$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Success$ Success() {
/* 22 */     return (this.Success$module == null) ? Success$lzycompute() : this.Success$module;
/*    */   }
/*    */   
/*    */   private DynamicVariable scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (!this.bitmap$0) {
/* 22 */         this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar = Parsers.class.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar(this);
/* 22 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */     } 
/*    */   }
/*    */   
/*    */   public DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar() {
/* 22 */     return this.bitmap$0 ? this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar : scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute();
/*    */   }
/*    */   
/*    */   private Parsers$NoSuccess$ NoSuccess$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (this.NoSuccess$module == null)
/* 22 */         this.NoSuccess$module = new Parsers$NoSuccess$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.NoSuccess$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$NoSuccess$ NoSuccess() {
/* 22 */     return (this.NoSuccess$module == null) ? NoSuccess$lzycompute() : this.NoSuccess$module;
/*    */   }
/*    */   
/*    */   private Parsers$Failure$ Failure$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (this.Failure$module == null)
/* 22 */         this.Failure$module = new Parsers$Failure$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.Failure$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Failure$ Failure() {
/* 22 */     return (this.Failure$module == null) ? Failure$lzycompute() : this.Failure$module;
/*    */   }
/*    */   
/*    */   private Parsers$Error$ Error$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (this.Error$module == null)
/* 22 */         this.Error$module = new Parsers$Error$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.Error$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Error$ Error() {
/* 22 */     return (this.Error$module == null) ? Error$lzycompute() : this.Error$module;
/*    */   }
/*    */   
/*    */   private Parsers$$tilde$ $tilde$lzycompute() {
/* 22 */     synchronized (this) {
/* 22 */       if (this.$tilde$module == null)
/* 22 */         this.$tilde$module = new Parsers$$tilde$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/syntactical/StandardTokenParsers}} */
/* 22 */       return this.$tilde$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$$tilde$ $tilde() {
/* 22 */     return (this.$tilde$module == null) ? $tilde$lzycompute() : this.$tilde$module;
/*    */   }
/*    */   
/*    */   public Parsers.NoSuccess lastNoSuccess() {
/* 22 */     return Parsers.class.lastNoSuccess(this);
/*    */   }
/*    */   
/*    */   public void lastNoSuccess_$eq(Parsers.NoSuccess x) {
/* 22 */     Parsers.class.lastNoSuccess_$eq(this, x);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> Parser(Function1 f) {
/* 22 */     return Parsers.class.Parser(this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> OnceParser(Function1 f) {
/* 22 */     return (Parsers.Parser<T>)Parsers.class.OnceParser(this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> commit(Function0 p) {
/* 22 */     return Parsers.class.commit(this, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(String kind, Function1 p) {
/* 22 */     return Parsers.class.elem(this, kind, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(Object e) {
/* 22 */     return Parsers.class.elem(this, e);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> accept(Object e) {
/* 22 */     return Parsers.class.accept(this, e);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> accept(Object es, Function1 evidence$1) {
/* 22 */     return Parsers.class.accept(this, es, evidence$1);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> accept(String expected, PartialFunction f) {
/* 22 */     return Parsers.class.accept(this, expected, f);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> acceptIf(Function1 p, Function1 err) {
/* 22 */     return Parsers.class.acceptIf(this, p, err);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> acceptMatch(String expected, PartialFunction f) {
/* 22 */     return Parsers.class.acceptMatch(this, expected, f);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> acceptSeq(Object es, Function1 evidence$2) {
/* 22 */     return Parsers.class.acceptSeq(this, es, evidence$2);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Nothing$> failure(String msg) {
/* 22 */     return Parsers.class.failure(this, msg);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Nothing$> err(String msg) {
/* 22 */     return Parsers.class.err(this, msg);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> success(Object v) {
/* 22 */     return Parsers.class.success(this, v);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> log(Function0 p, String name) {
/* 22 */     return Parsers.class.log(this, p, name);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep(Function0 p) {
/* 22 */     return Parsers.class.rep(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repsep(Function0 p, Function0 q) {
/* 22 */     return Parsers.class.repsep(this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 p) {
/* 22 */     return Parsers.class.rep1(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 first, Function0 p0) {
/* 22 */     return Parsers.class.rep1(this, first, p0);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repN(int num, Function0 p) {
/* 22 */     return Parsers.class.repN(this, num, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1sep(Function0 p, Function0 q) {
/* 22 */     return Parsers.class.rep1sep(this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> chainl1(Function0 p, Function0 q) {
/* 22 */     return Parsers.class.chainl1(this, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<T> chainl1(Function0 first, Function0 p, Function0 q) {
/* 22 */     return Parsers.class.chainl1(this, first, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<U> chainr1(Function0 p, Function0 q, Function2 combine, Object first) {
/* 22 */     return Parsers.class.chainr1(this, p, q, combine, first);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<Option<T>> opt(Function0 p) {
/* 22 */     return Parsers.class.opt(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<BoxedUnit> not(Function0 p) {
/* 22 */     return Parsers.class.not(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> guard(Function0 p) {
/* 22 */     return Parsers.class.guard(this, p);
/*    */   }
/*    */   
/*    */   public <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0 p) {
/* 22 */     return Parsers.class.positioned(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> phrase(Parsers.Parser p) {
/* 22 */     return Parsers.class.phrase(this, p);
/*    */   }
/*    */   
/*    */   public <T> Function1<Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/* 22 */     return Parsers.class.mkList(this);
/*    */   }
/*    */   
/*    */   public StandardTokenParsers() {
/* 22 */     Parsers.class.$init$(this);
/* 22 */     StdTokenParsers$class.$init$(this);
/* 24 */     this.lexical = new StdLexical();
/*    */   }
/*    */   
/*    */   public StdLexical lexical() {
/* 24 */     return this.lexical;
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> keyword(String chars) {
/* 28 */     return (lexical().reserved().contains(chars) || lexical().delimiters().contains(chars)) ? StdTokenParsers$class.keyword(this, chars) : 
/* 29 */       (Parsers.Parser)failure((new StringBuilder()).append("You are trying to parse \"").append(chars).append("\", but it is neither contained in the delimiters list, nor in the reserved keyword list of your lexical object").toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\syntactical\StandardTokenParsers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */