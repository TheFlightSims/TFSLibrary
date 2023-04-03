/*    */ package scala.util.parsing.combinator.lexical;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.RichChar$;
/*    */ import scala.util.DynamicVariable;
/*    */ import scala.util.parsing.combinator.Parsers;
/*    */ import scala.util.parsing.combinator.Parsers$;
/*    */ import scala.util.parsing.combinator.Parsers$$tilde$;
/*    */ import scala.util.parsing.combinator.Parsers$Error$;
/*    */ import scala.util.parsing.combinator.Parsers$Failure$;
/*    */ import scala.util.parsing.combinator.Parsers$NoSuccess$;
/*    */ import scala.util.parsing.combinator.Parsers$Success$;
/*    */ import scala.util.parsing.combinator.token.Tokens;
/*    */ import scala.util.parsing.combinator.token.Tokens$EOF$;
/*    */ import scala.util.parsing.combinator.token.Tokens$ErrorToken$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2Q!\001\002\002\0025\021q\001T3yS\016\fGN\003\002\004\t\0059A.\032=jG\006d'BA\003\007\003)\031w.\0342j]\006$xN\035\006\003\017!\tq\001]1sg&twM\003\002\n\025\005!Q\017^5m\025\005Y\021!B:dC2\f7\001A\n\005\0019\021b\003\005\002\020!5\t!\"\003\002\022\025\t1\021I\\=SK\032\004\"a\005\013\016\003\tI!!\006\002\003\021M\033\027M\0348feN\004\"a\006\016\016\003aQ!!\007\003\002\013Q|7.\0328\n\005mA\"A\002+pW\026t7\017C\003\036\001\021\005a$\001\004=S:LGO\020\013\002?A\0211\003\001\005\006C\001!\tAI\001\007Y\026$H/\032:\026\003\r\0022\001J\023*\033\005\001\021B\001\024(\005\031\001\026M]:fe&\021\001\006\002\002\b!\006\0248/\032:t!\t!#&\003\002,)\t!Q\t\\3n\021\025i\003\001\"\001#\003\025!\027nZ5u\021\025y\003\001\"\0011\003%\031\007N]#yG\026\004H\017\006\002$c!)!G\fa\001g\005\0211m\035\t\004\037Q2\024BA\033\013\005)a$/\0329fCR,GM\020\t\003\037]J!\001\017\006\003\t\rC\027M\035\005\006u\001!\tAI\001\017o\"LG/Z:qC\016,7\t[1s\001")
/*    */ public abstract class Lexical implements Scanners, Tokens {
/*    */   private final DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */   
/*    */   private volatile Tokens$ErrorToken$ ErrorToken$module;
/*    */   
/*    */   private volatile Tokens$EOF$ EOF$module;
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
/*    */   private Tokens$ErrorToken$ ErrorToken$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.ErrorToken$module == null)
/* 26 */         this.ErrorToken$module = new Tokens$ErrorToken$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.ErrorToken$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Tokens$ErrorToken$ ErrorToken() {
/* 26 */     return (this.ErrorToken$module == null) ? ErrorToken$lzycompute() : this.ErrorToken$module;
/*    */   }
/*    */   
/*    */   private Tokens$EOF$ EOF$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.EOF$module == null)
/* 26 */         this.EOF$module = new Tokens$EOF$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.EOF$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Tokens$EOF$ EOF() {
/* 26 */     return (this.EOF$module == null) ? EOF$lzycompute() : this.EOF$module;
/*    */   }
/*    */   
/*    */   public Tokens.Token errorToken(String msg) {
/* 26 */     return Tokens.class.errorToken(this, msg);
/*    */   }
/*    */   
/*    */   private Parsers$Success$ Success$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.Success$module == null)
/* 26 */         this.Success$module = new Parsers$Success$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.Success$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Success$ Success() {
/* 26 */     return (this.Success$module == null) ? Success$lzycompute() : this.Success$module;
/*    */   }
/*    */   
/*    */   private DynamicVariable scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (!this.bitmap$0) {
/* 26 */         this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar = Parsers.class.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar(this);
/* 26 */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar;
/*    */     } 
/*    */   }
/*    */   
/*    */   public DynamicVariable<Option<Parsers.NoSuccess>> scala$util$parsing$combinator$Parsers$$lastNoSuccessVar() {
/* 26 */     return this.bitmap$0 ? this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar : scala$util$parsing$combinator$Parsers$$lastNoSuccessVar$lzycompute();
/*    */   }
/*    */   
/*    */   private Parsers$NoSuccess$ NoSuccess$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.NoSuccess$module == null)
/* 26 */         this.NoSuccess$module = new Parsers$NoSuccess$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.NoSuccess$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$NoSuccess$ NoSuccess() {
/* 26 */     return (this.NoSuccess$module == null) ? NoSuccess$lzycompute() : this.NoSuccess$module;
/*    */   }
/*    */   
/*    */   private Parsers$Failure$ Failure$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.Failure$module == null)
/* 26 */         this.Failure$module = new Parsers$Failure$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.Failure$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Failure$ Failure() {
/* 26 */     return (this.Failure$module == null) ? Failure$lzycompute() : this.Failure$module;
/*    */   }
/*    */   
/*    */   private Parsers$Error$ Error$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.Error$module == null)
/* 26 */         this.Error$module = new Parsers$Error$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.Error$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$Error$ Error() {
/* 26 */     return (this.Error$module == null) ? Error$lzycompute() : this.Error$module;
/*    */   }
/*    */   
/*    */   private Parsers$$tilde$ $tilde$lzycompute() {
/* 26 */     synchronized (this) {
/* 26 */       if (this.$tilde$module == null)
/* 26 */         this.$tilde$module = new Parsers$$tilde$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/parsing/combinator/lexical/Lexical}} */
/* 26 */       return this.$tilde$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Parsers$$tilde$ $tilde() {
/* 26 */     return (this.$tilde$module == null) ? $tilde$lzycompute() : this.$tilde$module;
/*    */   }
/*    */   
/*    */   public Parsers.NoSuccess lastNoSuccess() {
/* 26 */     return Parsers.class.lastNoSuccess(this);
/*    */   }
/*    */   
/*    */   public void lastNoSuccess_$eq(Parsers.NoSuccess x) {
/* 26 */     Parsers.class.lastNoSuccess_$eq(this, x);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> Parser(Function1 f) {
/* 26 */     return Parsers.class.Parser(this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> OnceParser(Function1 f) {
/* 26 */     return (Parsers.Parser<T>)Parsers.class.OnceParser(this, f);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> commit(Function0 p) {
/* 26 */     return Parsers.class.commit(this, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(String kind, Function1 p) {
/* 26 */     return Parsers.class.elem(this, kind, p);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> elem(Object e) {
/* 26 */     return Parsers.class.elem(this, e);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> accept(Object e) {
/* 26 */     return Parsers.class.accept(this, e);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> accept(Object es, Function1 evidence$1) {
/* 26 */     return Parsers.class.accept(this, es, evidence$1);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> accept(String expected, PartialFunction f) {
/* 26 */     return Parsers.class.accept(this, expected, f);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> acceptIf(Function1 p, Function1 err) {
/* 26 */     return Parsers.class.acceptIf(this, p, err);
/*    */   }
/*    */   
/*    */   public <U> Parsers.Parser<U> acceptMatch(String expected, PartialFunction f) {
/* 26 */     return Parsers.class.acceptMatch(this, expected, f);
/*    */   }
/*    */   
/*    */   public <ES> Parsers.Parser<List<Object>> acceptSeq(Object es, Function1 evidence$2) {
/* 26 */     return Parsers.class.acceptSeq(this, es, evidence$2);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Nothing$> failure(String msg) {
/* 26 */     return Parsers.class.failure(this, msg);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Nothing$> err(String msg) {
/* 26 */     return Parsers.class.err(this, msg);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> success(Object v) {
/* 26 */     return Parsers.class.success(this, v);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> log(Function0 p, String name) {
/* 26 */     return Parsers.class.log(this, p, name);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep(Function0 p) {
/* 26 */     return Parsers.class.rep(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repsep(Function0 p, Function0 q) {
/* 26 */     return Parsers.class.repsep(this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 p) {
/* 26 */     return Parsers.class.rep1(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1(Function0 first, Function0 p0) {
/* 26 */     return Parsers.class.rep1(this, first, p0);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> repN(int num, Function0 p) {
/* 26 */     return Parsers.class.repN(this, num, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<List<T>> rep1sep(Function0 p, Function0 q) {
/* 26 */     return Parsers.class.rep1sep(this, p, q);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> chainl1(Function0 p, Function0 q) {
/* 26 */     return Parsers.class.chainl1(this, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<T> chainl1(Function0 first, Function0 p, Function0 q) {
/* 26 */     return Parsers.class.chainl1(this, first, p, q);
/*    */   }
/*    */   
/*    */   public <T, U> Parsers.Parser<U> chainr1(Function0 p, Function0 q, Function2 combine, Object first) {
/* 26 */     return Parsers.class.chainr1(this, p, q, combine, first);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<Option<T>> opt(Function0 p) {
/* 26 */     return Parsers.class.opt(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<BoxedUnit> not(Function0 p) {
/* 26 */     return Parsers.class.not(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> guard(Function0 p) {
/* 26 */     return Parsers.class.guard(this, p);
/*    */   }
/*    */   
/*    */   public <T extends scala.util.parsing.input.Positional> Parsers.Parser<T> positioned(Function0 p) {
/* 26 */     return Parsers.class.positioned(this, p);
/*    */   }
/*    */   
/*    */   public <T> Parsers.Parser<T> phrase(Parsers.Parser p) {
/* 26 */     return Parsers.class.phrase(this, p);
/*    */   }
/*    */   
/*    */   public <T> Function1<Parsers$.tilde<T, List<T>>, List<T>> mkList() {
/* 26 */     return Parsers.class.mkList(this);
/*    */   }
/*    */   
/*    */   public Lexical() {
/* 26 */     Parsers.class.$init$(this);
/* 26 */     Scanners$class.$init$(this);
/* 26 */     Tokens.class.$init$(this);
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> letter() {
/* 29 */     return elem("letter", (Function1<Object, Object>)new Lexical$$anonfun$letter$1(this));
/*    */   }
/*    */   
/*    */   public class Lexical$$anonfun$letter$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char x$1) {
/* 29 */       Predef$ predef$ = Predef$.MODULE$;
/* 29 */       return RichChar$.MODULE$.isLetter$extension(x$1);
/*    */     }
/*    */     
/*    */     public Lexical$$anonfun$letter$1(Lexical $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> digit() {
/* 32 */     return elem("digit", (Function1<Object, Object>)new Lexical$$anonfun$digit$1(this));
/*    */   }
/*    */   
/*    */   public class Lexical$$anonfun$digit$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char x$2) {
/* 32 */       Predef$ predef$ = Predef$.MODULE$;
/* 32 */       return RichChar$.MODULE$.isDigit$extension(x$2);
/*    */     }
/*    */     
/*    */     public Lexical$$anonfun$digit$1(Lexical $outer) {}
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> chrExcept(Seq cs) {
/* 35 */     return elem("", (Function1<Object, Object>)new Lexical$$anonfun$chrExcept$1(this, cs));
/*    */   }
/*    */   
/*    */   public class Lexical$$anonfun$chrExcept$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Seq cs$1;
/*    */     
/*    */     public final boolean apply(char ch) {
/* 35 */       return this.cs$1.forall((Function1)new Lexical$$anonfun$chrExcept$1$$anonfun$apply$1(this, ch));
/*    */     }
/*    */     
/*    */     public Lexical$$anonfun$chrExcept$1(Lexical $outer, Seq cs$1) {}
/*    */     
/*    */     public class Lexical$$anonfun$chrExcept$1$$anonfun$apply$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final char ch$1;
/*    */       
/*    */       public final boolean apply(char x$3) {
/* 35 */         return (this.ch$1 != x$3);
/*    */       }
/*    */       
/*    */       public Lexical$$anonfun$chrExcept$1$$anonfun$apply$1(Lexical$$anonfun$chrExcept$1 $outer, char ch$1) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public Parsers.Parser<Object> whitespaceChar() {
/* 38 */     return elem("space char", (Function1<Object, Object>)new Lexical$$anonfun$whitespaceChar$1(this));
/*    */   }
/*    */   
/*    */   public class Lexical$$anonfun$whitespaceChar$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(char ch) {
/* 38 */       return (ch <= ' ' && ch != '\032');
/*    */     }
/*    */     
/*    */     public Lexical$$anonfun$whitespaceChar$1(Lexical $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\lexical\Lexical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */