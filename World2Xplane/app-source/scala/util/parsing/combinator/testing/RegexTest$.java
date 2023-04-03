/*    */ package scala.util.parsing.combinator.testing;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.util.DynamicVariable;
/*    */ import scala.util.matching.Regex;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.RegexParsers;
/*    */ import scala.util.parsing.input.CharSequenceReader;
/*    */ import scala.util.parsing.input.Reader;
/*    */ 
/*    */ public final class RegexTest$ implements RegexParsers {
/*    */   public static final RegexTest$ MODULE$;
/*    */   
/*    */   private final Parsers.Parser<Object> ident;
/*    */   
/*    */   private final Parsers.Parser<Object> number;
/*    */   
/*    */   private final Parsers.Parser<Object> string;
/*    */   
/*    */   private final Parsers.Parser<List<Object>> parser;
/*    */   
/*    */   private final Regex whiteSpace;
/*    */   
/*    */   private final DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */   
/*    */   private volatile scala.util.parsing.combinator.Parsers$Success$ Success$module;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   private volatile scala.util.parsing.combinator.Parsers$NoSuccess$ NoSuccess$module;
/*    */   
/*    */   private volatile scala.util.parsing.combinator.Parsers$Failure$ Failure$module;
/*    */   
/*    */   private volatile scala.util.parsing.combinator.Parsers$Error$ Error$module;
/*    */   
/*    */   private volatile scala.util.parsing.combinator.Parsers$$tilde$ $tilde$module;
/*    */   
/*    */   public Regex whiteSpace() {
/* 16 */     return this.whiteSpace;
/*    */   }
/*    */   
/*    */   public Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$positioned(Function0 p) {
/* 16 */     return Parsers.class.positioned((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser scala$util$parsing$combinator$RegexParsers$$super$phrase(Parsers.Parser p) {
/* 16 */     return Parsers.class.phrase((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public void scala$util$parsing$combinator$RegexParsers$_setter_$whiteSpace_$eq(Regex x$1) {
/* 16 */     this.whiteSpace = x$1;
/*    */   }
/*    */   
/*    */   public boolean skipWhitespace() {
/* 16 */     return RegexParsers.class.skipWhitespace(this);
/*    */   }
/*    */   
/*    */   public int handleWhiteSpace(CharSequence source, int offset) {
/* 16 */     return RegexParsers.class.handleWhiteSpace(this, source, offset);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> literal(String s) {
/* 16 */     return RegexParsers.class.literal(this, s);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<String> regex(Regex r) {
/* 16 */     return RegexParsers.class.regex(this, r);
/*    */   }
/*    */   
/*    */   public <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0 p) {
/* 16 */     return RegexParsers.class.positioned(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> phrase(Parsers.Parser p) {
/* 16 */     return RegexParsers.class.phrase(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parse(Parsers.Parser p, Reader in) {
/* 16 */     return RegexParsers.class.parse(this, p, in);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parse(Parsers.Parser p, CharSequence in) {
/* 16 */     return RegexParsers.class.parse(this, p, in);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parse(Parsers.Parser p, Reader in) {
/* 16 */     return RegexParsers.class.parse(this, p, in);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parseAll(Parsers.Parser p, Reader in) {
/* 16 */     return RegexParsers.class.parseAll(this, p, in);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parseAll(Parsers.Parser p, Reader in) {
/* 16 */     return RegexParsers.class.parseAll(this, p, in);
/*    */   }
/*    */   
/*    */   public <T> Parsers.ParseResult<T> parseAll(Parsers.Parser p, CharSequence in) {
/* 16 */     return RegexParsers.class.parseAll(this, p, in);
/*    */   }
/*    */   
/*    */   private scala.util.parsing.combinator.Parsers$Success$ Success$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (this.Success$module == null)
/* 16 */         this.Success$module = new scala.util.parsing.combinator.Parsers$Success$((Parsers)this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.Success$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.util.parsing.combinator.Parsers$Success$ Success() {
/* 16 */     return (this.Success$module == null) ? Success$lzycompute() : this.Success$module;
/*    */   }
/*    */   
/*    */   private DynamicVariable scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (!this.bitmap$0) {
/* 16 */         this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar = Parsers.class.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar((Parsers)this);
/* 16 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */     } 
/*    */   }
/*    */   
/*    */   public DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar() {
/* 16 */     return this.bitmap$0 ? this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar : scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute();
/*    */   }
/*    */   
/*    */   private scala.util.parsing.combinator.Parsers$NoSuccess$ NoSuccess$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (this.NoSuccess$module == null)
/* 16 */         this.NoSuccess$module = new scala.util.parsing.combinator.Parsers$NoSuccess$((Parsers)this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.NoSuccess$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.util.parsing.combinator.Parsers$NoSuccess$ NoSuccess() {
/* 16 */     return (this.NoSuccess$module == null) ? NoSuccess$lzycompute() : this.NoSuccess$module;
/*    */   }
/*    */   
/*    */   private scala.util.parsing.combinator.Parsers$Failure$ Failure$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (this.Failure$module == null)
/* 16 */         this.Failure$module = new scala.util.parsing.combinator.Parsers$Failure$((Parsers)this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.Failure$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.util.parsing.combinator.Parsers$Failure$ Failure() {
/* 16 */     return (this.Failure$module == null) ? Failure$lzycompute() : this.Failure$module;
/*    */   }
/*    */   
/*    */   private scala.util.parsing.combinator.Parsers$Error$ Error$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (this.Error$module == null)
/* 16 */         this.Error$module = new scala.util.parsing.combinator.Parsers$Error$((Parsers)this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.Error$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.util.parsing.combinator.Parsers$Error$ Error() {
/* 16 */     return (this.Error$module == null) ? Error$lzycompute() : this.Error$module;
/*    */   }
/*    */   
/*    */   private scala.util.parsing.combinator.Parsers$$tilde$ $tilde$lzycompute() {
/* 16 */     synchronized (this) {
/* 16 */       if (this.$tilde$module == null)
/* 16 */         this.$tilde$module = new scala.util.parsing.combinator.Parsers$$tilde$((Parsers)this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/testing/RegexTest$}} */
/* 16 */       return this.$tilde$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.util.parsing.combinator.Parsers$$tilde$ $tilde() {
/* 16 */     return (this.$tilde$module == null) ? $tilde$lzycompute() : this.$tilde$module;
/*    */   }
/*    */   
/*    */   public Parsers.NoSuccess lastNoSuccess() {
/* 16 */     return Parsers.class.lastNoSuccess((Parsers)this);
/*    */   }
/*    */   
/*    */   public void lastNoSuccess_$eq(Parsers.NoSuccess x) {
/* 16 */     Parsers.class.lastNoSuccess_$eq((Parsers)this, x);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> Parser(Function1 f) {
/* 16 */     return Parsers.class.Parser((Parsers)this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> OnceParser(Function1 f) {
/* 16 */     return (Parsers.Parser<T>)Parsers.class.OnceParser((Parsers)this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> commit(Function0 p) {
/* 16 */     return Parsers.class.commit((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(String kind, Function1 p) {
/* 16 */     return Parsers.class.elem((Parsers)this, kind, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(Object e) {
/* 16 */     return Parsers.class.elem((Parsers)this, e);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> accept(Object e) {
/* 16 */     return Parsers.class.accept((Parsers)this, e);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> accept(Object es, Function1 evidence$1) {
/* 16 */     return Parsers.class.accept((Parsers)this, es, evidence$1);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> accept(String expected, PartialFunction f) {
/* 16 */     return Parsers.class.accept((Parsers)this, expected, f);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> acceptIf(Function1 p, Function1 err) {
/* 16 */     return Parsers.class.acceptIf((Parsers)this, p, err);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> acceptMatch(String expected, PartialFunction f) {
/* 16 */     return Parsers.class.acceptMatch((Parsers)this, expected, f);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> acceptSeq(Object es, Function1 evidence$2) {
/* 16 */     return Parsers.class.acceptSeq((Parsers)this, es, evidence$2);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<scala.runtime.Nothing$> failure(String msg) {
/* 16 */     return Parsers.class.failure((Parsers)this, msg);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<scala.runtime.Nothing$> err(String msg) {
/* 16 */     return Parsers.class.err((Parsers)this, msg);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> success(Object v) {
/* 16 */     return Parsers.class.success((Parsers)this, v);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> log(Function0 p, String name) {
/* 16 */     return Parsers.class.log((Parsers)this, p, name);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep(Function0 p) {
/* 16 */     return Parsers.class.rep((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repsep(Function0 p, Function0 q) {
/* 16 */     return Parsers.class.repsep((Parsers)this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 p) {
/* 16 */     return Parsers.class.rep1((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 first, Function0 p0) {
/* 16 */     return Parsers.class.rep1((Parsers)this, first, p0);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repN(int num, Function0 p) {
/* 16 */     return Parsers.class.repN((Parsers)this, num, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1sep(Function0 p, Function0 q) {
/* 16 */     return Parsers.class.rep1sep((Parsers)this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> chainl1(Function0 p, Function0 q) {
/* 16 */     return Parsers.class.chainl1((Parsers)this, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<T> chainl1(Function0 first, Function0 p, Function0 q) {
/* 16 */     return Parsers.class.chainl1((Parsers)this, first, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<U> chainr1(Function0 p, Function0 q, Function2 combine, Object first) {
/* 16 */     return Parsers.class.chainr1((Parsers)this, p, q, combine, first);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<Option<T>> opt(Function0 p) {
/* 16 */     return Parsers.class.opt((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<BoxedUnit> not(Function0 p) {
/* 16 */     return Parsers.class.not((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> guard(Function0 p) {
/* 16 */     return Parsers.class.guard((Parsers)this, p);
/*    */   }
/*    */   
/*    */   public <T> Function1<scala.util.parsing.combinator.Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/* 16 */     return Parsers.class.mkList((Parsers)this);
/*    */   }
/*    */   
/*    */   private RegexTest$() {
/* 16 */     MODULE$ = this;
/* 16 */     Parsers.class.$init$((Parsers)this);
/* 16 */     RegexParsers.class.$init$(this);
/* 17 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 17 */     this.ident = regex((new StringOps("[a-zA-Z_]\\w*")).r()).$up$up((Function1)new RegexTest.$anonfun$1());
/* 18 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 18 */     this.number = regex((new StringOps("\\d\\d*")).r()).$up$up((Function1)new RegexTest.$anonfun$2());
/* 19 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 19 */     this.string = regex((new StringOps("\".*\"")).r()).$up$up((Function1)new RegexTest.$anonfun$3());
/* 20 */     this.parser = ident().$bar((Function0)new RegexTest.$anonfun$4()).$bar((Function0)new RegexTest.$anonfun$5()).$times();
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> ident() {
/*    */     return this.ident;
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction1<String, Ident> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Ident apply(String s) {
/*    */       return new Ident(s);
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> number() {
/*    */     return this.number;
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction1<String, Number> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Number apply(String s) {
/*    */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*    */       return new Number((new StringOps(s)).toInt());
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> string() {
/*    */     return this.string;
/*    */   }
/*    */   
/*    */   public static class $anonfun$3 extends AbstractFunction1<String, Str> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Str apply(String s) {
/*    */       return new Str(s.substring(1, s.length() - 1));
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<List<Object>> parser() {
/* 20 */     return this.parser;
/*    */   }
/*    */   
/*    */   public static class $anonfun$4 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 20 */       return RegexTest$.MODULE$.number();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class $anonfun$5 extends AbstractFunction0<Parsers.Parser<Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Parsers.Parser<Object> apply() {
/* 20 */       return RegexTest$.MODULE$.string();
/*    */     }
/*    */   }
/*    */   
/*    */   public void main(String[] args) {
/* 23 */     String in = scala.Predef$.MODULE$.refArrayOps((Object[])args).mkString(" ");
/* 24 */     scala.Predef$.MODULE$.println((new StringBuilder()).append("\nin : ").append(in).toString());
/* 25 */     scala.Predef$.MODULE$.println(phrase((Parsers.Parser)parser()).apply((Reader)new CharSequenceReader(in)));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\RegexTest$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */