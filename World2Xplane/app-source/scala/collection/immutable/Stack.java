/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeq;
/*     */ import scala.collection.LinearSeqLike;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParSeq;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035u!B\001\003\021\003I\021!B*uC\016\\'BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001\001\005\002\013\0275\t!AB\003\r\005!\005QBA\003Ti\006\0347nE\002\f\035I\0022a\004\n\025\033\005\001\"BA\t\005\003\0359WM\\3sS\016L!a\005\t\003\025M+\027OR1di>\024\030\020\005\002\013+\031!AB\001\001\027+\t9bd\005\004\0261!ZcF\r\t\0043iaR\"\001\003\n\005m!!aC!cgR\024\030m\031;TKF\004\"!\b\020\r\001\0211q$\006CC\002\001\022\021!Q\t\003C\025\002\"AI\022\016\003\031I!\001\n\004\003\0179{G\017[5oOB\021!EJ\005\003O\031\0211!\0218z!\rQ\021\006H\005\003U\t\021\021\002T5oK\006\0248+Z9\021\t=aC\004F\005\003[A\021!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004B!G\030\035c%\021\001\007\002\002\023\031&tW-\031:TKF|\005\017^5nSj,G\rE\002\013+q\001\"AI\032\n\005Q2!\001D*fe&\fG.\033>bE2,\007\002\003\034\026\005\013\007I\021C\034\002\013\025dW-\\:\026\003a\0022AC\035\035\023\tQ$A\001\003MSN$\b\002\003\037\026\005\003\005\013\021\002\035\002\r\025dW-\\:!\021\025qT\003\"\005@\003\031a\024N\\5u}Q\021\021\007\021\005\006mu\002\r\001\017\005\006\005V!\teQ\001\nG>l\007/\0318j_:,\022\001\022\t\004\037\025#\022B\001$\021\005A9UM\\3sS\016\034u.\0349b]&|g\016C\003?+\021\005\001\nF\0012\021\025QU\003\"\021L\003\035I7/R7qif,\022\001\024\t\003E5K!A\024\004\003\017\t{w\016\\3b]\")\001+\006C!#\006!\001.Z1e+\005a\002\"B*\026\t\003\"\026\001\002;bS2,\022!\r\005\006-V!\taV\001\005aV\034\b.\006\002Y7R\021\021L\030\t\004\025UQ\006CA\017\\\t\025aVK1\001^\005\005\021\025C\001\017&\021\025yV\0131\001[\003\021)G.Z7\t\013Y+B\021A1\026\005\t,G\003B2gQ*\0042AC\013e!\tiR\rB\003]A\n\007Q\fC\003hA\002\007A-A\003fY\026l\027\007C\003jA\002\007A-A\003fY\026l'\007C\0037A\002\0071\016E\002#Y\022L!!\034\004\003\025q\022X\r]3bi\026$g\bC\003p+\021\005\001/A\004qkND\027\t\0347\026\005E$HC\001:v!\rQQc\035\t\003;Q$Q\001\0308C\002uCQA\0368A\002]\f!\001_:\021\007eA8/\003\002z\t\tyAK]1wKJ\034\030M\0317f\037:\034W\rC\003|+\021\005\021+A\002u_BDQ!`\013\005\002Q\0131\001]8q\021\031yX\003\"\001\002\002\005!\001o\03493+\t\t\031\001E\003#\003\013a\022'C\002\002\b\031\021a\001V;qY\026\024\004BBA\006+\021\005C+A\004sKZ,'o]3\t\017\005=Q\003\"\021\002\022\005A\021\016^3sCR|'/\006\002\002\024A!\021$!\006\035\023\r\t9\002\002\002\t\023R,'/\031;pe\"9\0211D\013\005B\005u\021\001\003;p'R\024\030N\\4\025\005\005}\001\003BA\021\003Oq1AIA\022\023\r\t)CB\001\007!J,G-\0324\n\t\005%\0221\006\002\007'R\024\030N\\4\013\007\005\025b\001K\003\026\003_\t)\004E\002#\003cI1!a\r\007\005A\031VM]5bYZ+'o]5p]VKEI\b\005\034[z\017;D\r~?\022\031q4\002\"\001\002:Q\t\021\002C\004\002>-!\031!a\020\002\031\r\fgNQ;jY\0224%o\\7\026\t\005\005\0231K\013\003\003\007\002\022bDA#\003\023\n\t&!\026\n\007\005\035\003C\001\007DC:\024U/\0337e\rJ|W\016\005\003\002L\0055S\"A\006\n\007\005=SI\001\003D_2d\007cA\017\002T\0211q$a\017C\002\001\002BAC\013\002R!9\021\021L\006\005\002\005m\023A\0038fo\n+\030\016\0343feV!\021QLA7+\t\ty\006\005\005\002b\005\035\0241NA8\033\t\t\031GC\002\002f\021\tq!\\;uC\ndW-\003\003\002j\005\r$a\002\"vS2$WM\035\t\004;\0055DAB\020\002X\t\007\001\005\005\003\013+\005-\004\"CA:\027\005\005I\021BA;\003-\021X-\0313SKN|GN^3\025\005\005]\004\003BA=\003\007k!!a\037\013\t\005u\024qP\001\005Y\006twM\003\002\002\002\006!!.\031<b\023\021\t))a\037\003\r=\023'.Z2u\001")
/*     */ public class Stack<A> extends AbstractSeq<A> implements LinearSeq<A>, GenericTraversableTemplate<A, Stack>, LinearSeqOptimized<A, Stack<A>>, Serializable {
/*     */   public static final long serialVersionUID = 1976480595012942526L;
/*     */   
/*     */   private final List<A> elems;
/*     */   
/*     */   public static class Stack$$anonfun$newBuilder$1 extends AbstractFunction1<ArrayBuffer<A>, Stack<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stack<A> apply(ArrayBuffer buf) {
/*  22 */       return new Stack<A>(buf.toList());
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
/*  48 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int length() {
/*  48 */     return LinearSeqOptimized.class.length(this);
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/*  48 */     return (A)LinearSeqOptimized.class.apply(this, n);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  48 */     LinearSeqOptimized.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  48 */     return LinearSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  48 */     return LinearSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  48 */     return LinearSeqOptimized.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  48 */     return LinearSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 f) {
/*  48 */     return (B)LinearSeqOptimized.class.foldLeft(this, z, f);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 f) {
/*  48 */     return (B)LinearSeqOptimized.class.foldRight(this, z, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 f) {
/*  48 */     return (B)LinearSeqOptimized.class.reduceLeft(this, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  48 */     return (B)LinearSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public A last() {
/*  48 */     return (A)LinearSeqOptimized.class.last(this);
/*     */   }
/*     */   
/*     */   public Stack<A> take(int n) {
/*  48 */     return (Stack<A>)LinearSeqOptimized.class.take(this, n);
/*     */   }
/*     */   
/*     */   public Stack<A> drop(int n) {
/*  48 */     return (Stack<A>)LinearSeqOptimized.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public Stack<A> dropRight(int n) {
/*  48 */     return (Stack<A>)LinearSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public Stack<A> slice(int from, int until) {
/*  48 */     return (Stack<A>)LinearSeqOptimized.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public Stack<A> takeWhile(Function1 p) {
/*  48 */     return (Stack<A>)LinearSeqOptimized.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Stack<A>, Stack<A>> span(Function1 p) {
/*  48 */     return LinearSeqOptimized.class.span(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  48 */     return LinearSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  48 */     return LinearSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int x) {
/*  48 */     return LinearSeqOptimized.class.isDefinedAt(this, x);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  48 */     return LinearSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  48 */     return LinearSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  48 */     return LinearSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> seq() {
/*  48 */     return LinearSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> thisCollection() {
/*  48 */     return LinearSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  48 */     return LinearSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  48 */     return LinearSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  48 */     return LinearSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/*  48 */     return Seq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParSeq<A>> parCombiner() {
/*  48 */     return Seq$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public List<A> elems() {
/*  48 */     return this.elems;
/*     */   }
/*     */   
/*     */   public Stack(List<A> elems) {
/*  48 */     Traversable$class.$init$(this);
/*  48 */     Iterable$class.$init$(this);
/*  48 */     Seq$class.$init$(this);
/*  48 */     LinearSeqLike.class.$init$(this);
/*  48 */     LinearSeq.class.$init$(this);
/*  48 */     LinearSeq$class.$init$(this);
/*  48 */     LinearSeqOptimized.class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Stack> companion() {
/*  54 */     return (GenericCompanion<Stack>)Stack$.MODULE$;
/*     */   }
/*     */   
/*     */   public Stack() {
/*  56 */     this(Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  62 */     return elems().isEmpty();
/*     */   }
/*     */   
/*     */   public A head() {
/*  64 */     return elems().head();
/*     */   }
/*     */   
/*     */   public Stack<A> tail() {
/*  65 */     return new Stack((List<A>)elems().tail());
/*     */   }
/*     */   
/*     */   public <B> Stack<B> push(Object elem) {
/*  72 */     return new Stack(elems().$colon$colon((A)elem));
/*     */   }
/*     */   
/*     */   public <B> Stack<B> push(Object elem1, Object elem2, Seq elems) {
/*  81 */     return push(elem1).push(elem2).pushAll((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public <B> Stack<B> pushAll(TraversableOnce xs) {
/*  91 */     return (Stack<B>)xs.toIterator().$div$colon(this, (Function2)new Stack$$anonfun$pushAll$1(this));
/*     */   }
/*     */   
/*     */   public class Stack$$anonfun$pushAll$1 extends AbstractFunction2<Stack<B>, B, Stack<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stack<B> apply(Stack x$3, Object x$4) {
/*  91 */       return x$3.push(x$4);
/*     */     }
/*     */     
/*     */     public Stack$$anonfun$pushAll$1(Stack $outer) {}
/*     */   }
/*     */   
/*     */   public A top() {
/* 100 */     if (isEmpty())
/* 101 */       throw new NoSuchElementException("top of empty stack"); 
/*     */     return elems().head();
/*     */   }
/*     */   
/*     */   public Stack<A> pop() {
/* 110 */     if (isEmpty())
/* 111 */       throw new NoSuchElementException("pop of empty stack"); 
/*     */     return new Stack((List<A>)elems().tail());
/*     */   }
/*     */   
/*     */   public Tuple2<A, Stack<A>> pop2() {
/* 114 */     if (isEmpty())
/* 115 */       throw new NoSuchElementException("pop of empty stack"); 
/*     */     return new Tuple2(elems().head(), new Stack((List<A>)elems().tail()));
/*     */   }
/*     */   
/*     */   public Stack<A> reverse() {
/* 117 */     return new Stack(elems().reverse());
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 125 */     return elems().iterator();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 129 */     return elems().mkString("Stack(", ", ", ")");
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<Stack<?>, A, Stack<A>> canBuildFrom() {
/*     */     return Stack$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<Stack<A>> unapplySeq(Stack<A> paramStack) {
/*     */     return Stack$.MODULE$.unapplySeq(paramStack);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (Stack<A>)Stack$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> Stack<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (Stack<T>)Stack$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> Stack<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (Stack<T>)Stack$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<Stack<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (Stack<Stack<Stack<Stack<Stack<A>>>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (Stack<Stack<Stack<Stack<A>>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (Stack<Stack<Stack<A>>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (Stack<Stack<A>>)Stack$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (Stack<A>)Stack$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<Stack<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<Stack<Stack<A>>>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<Stack<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<Stack<A>>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<Stack<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<Stack<A>>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<Stack<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (Stack<Stack<A>>)Stack$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (Stack<A>)Stack$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (Stack<A>)Stack$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<Stack>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return Stack$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> Stack<A> empty() {
/*     */     return (Stack<A>)Stack$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Stack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */