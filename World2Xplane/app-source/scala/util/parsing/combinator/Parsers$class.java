/*     */ package scala.util.parsing.combinator;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Option$;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.util.DynamicVariable;
/*     */ import scala.util.parsing.input.Reader;
/*     */ 
/*     */ public abstract class Parsers$class {
/*     */   public static void $init$(Parsers $this) {}
/*     */   
/*     */   public static DynamicVariable scala$util$parsing$combinator$Parsers$$lastNoSuccessVar(Parsers $this) {
/* 158 */     return new DynamicVariable(None$.MODULE$);
/*     */   }
/*     */   
/*     */   public static Parsers.NoSuccess lastNoSuccess(Parsers $this) {
/* 161 */     Predef$.less.colon.less less = Predef$.MODULE$.conforms();
/*     */     Option option;
/* 161 */     return (option = (Option)$this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar().value()).isEmpty() ? (Parsers.NoSuccess)less.apply(null) : (Parsers.NoSuccess)option.get();
/*     */   }
/*     */   
/*     */   public static void lastNoSuccess_$eq(Parsers $this, Parsers.NoSuccess x) {
/* 164 */     $this.scala$util$parsing$combinator$Parsers$$lastNoSuccessVar().value_$eq(Option$.MODULE$.apply(x));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser Parser(Parsers $this, Function1 f) {
/* 222 */     return new Parsers$$anon$3($this, f);
/*     */   }
/*     */   
/*     */   public static Parsers.OnceParser OnceParser(Parsers $this, Function1 f) {
/* 225 */     return new Parsers$$anon$1($this, f);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser commit(Parsers $this, Function0 p) {
/* 531 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$commit$1($this, p));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser elem(Parsers $this, String kind, Function1<Object, Object> p) {
/* 551 */     return $this.acceptIf(p, (Function1<Object, String>)new Parsers$$anonfun$elem$1($this, kind));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser elem(Parsers $this, Object e) {
/* 560 */     return $this.accept(e);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser accept(Parsers $this, Object e) {
/* 572 */     return $this.acceptIf((Function1<Object, Object>)new Parsers$$anonfun$accept$1($this, e), (Function1<Object, String>)new Parsers$$anonfun$accept$2($this, e));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser accept(Parsers $this, Object es, Function1<Object, Iterable<Object>> evidence$1) {
/* 581 */     return $this.acceptSeq(es, evidence$1);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser accept(Parsers $this, String expected, PartialFunction<Object, ?> f) {
/* 596 */     return $this.acceptMatch(expected, f);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser acceptIf(Parsers $this, Function1 p, Function1 err) {
/* 606 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$acceptIf$1($this, p, err));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser acceptMatch(Parsers $this, String expected, PartialFunction f) {
/* 625 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$acceptMatch$1($this, expected, f));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser acceptSeq(Parsers $this, Object es, Function1 evidence$2) {
/* 639 */     return (Parsers.Parser)((IterableLike)evidence$2.apply(es)).foldRight($this.success(Nil$.MODULE$), (Function2)new Parsers$$anonfun$acceptSeq$1($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser failure(Parsers $this, String msg) {
/* 646 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$failure$1($this, msg));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser err(Parsers $this, String msg) {
/* 653 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$err$1($this, msg));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser success(Parsers $this, Object v) {
/* 660 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$success$1($this, v));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser log(Parsers $this, Function0 p, String name) {
/* 666 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$log$1($this, p, name));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser rep(Parsers $this, Function0<Parsers.Parser<?>> p) {
/* 681 */     return $this.rep1(p).$bar((Function0)new Parsers$$anonfun$rep$1($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser repsep(Parsers $this, Function0<Parsers.Parser<?>> p, Function0<Parsers.Parser<Object>> q) {
/* 696 */     return $this.rep1sep(p, q).$bar((Function0)new Parsers$$anonfun$repsep$1($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser rep1(Parsers $this, Function0<Parsers.Parser<?>> p) {
/* 707 */     return $this.rep1(p, p);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser rep1(Parsers $this, Function0 first, Function0 p0) {
/* 721 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$rep1$1($this, first, p0));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser repN(Parsers $this, int num, Function0 p) {
/* 753 */     return (num == 0) ? $this.<Nil$>success(Nil$.MODULE$) : $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$repN$1($this, num, p));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser rep1sep(Parsers $this, Function0 p, Function0 q) {
/* 780 */     return ((Parsers.Parser)p.apply()).$tilde((Function0)new Parsers$$anonfun$rep1sep$1($this, p, q)).$up$up((Function1)new Parsers$$anonfun$rep1sep$2($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser chainl1(Parsers $this, Function0<Parsers.Parser<?>> p, Function0<Parsers.Parser<Function2<?, ?, ?>>> q) {
/* 794 */     return $this.chainl1(p, p, q);
/*     */   }
/*     */   
/*     */   public static Parsers.Parser chainl1(Parsers $this, Function0 first, Function0 p, Function0 q) {
/* 807 */     return ((Parsers.Parser)first.apply()).$tilde((Function0)new Parsers$$anonfun$chainl1$1($this, p, q)).$up$up((Function1)new Parsers$$anonfun$chainl1$2($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser chainr1(Parsers $this, Function0 p, Function0 q, Function2 combine, Object first) {
/* 825 */     return ((Parsers.Parser)p.apply()).$tilde((Function0)new Parsers$$anonfun$chainr1$1($this, p, q)).$up$up((Function1)new Parsers$$anonfun$chainr1$2($this, combine, first));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser opt(Parsers $this, Function0 p) {
/* 838 */     return ((Parsers.Parser)p.apply()).$up$up((Function1)new Parsers$$anonfun$opt$1($this)).$bar((Function0)new Parsers$$anonfun$opt$2($this));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser not(Parsers $this, Function0 p) {
/* 843 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$not$1($this, p));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser guard(Parsers $this, Function0 p) {
/* 858 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$guard$1($this, p));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser positioned(Parsers $this, Function0 p) {
/* 873 */     return $this.Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new Parsers$$anonfun$positioned$1($this, p));
/*     */   }
/*     */   
/*     */   public static Parsers.Parser phrase(Parsers $this, Parsers.Parser p) {
/* 889 */     return new Parsers$$anon$2($this, p);
/*     */   }
/*     */   
/*     */   public static Function1 mkList(Parsers $this) {
/* 903 */     return (Function1)new Parsers$$anonfun$mkList$1($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\Parsers$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */