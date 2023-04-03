/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.IndexedSeqOptimized;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParArray;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-d\001B\001\003\001%\021\001\"\021:sCf\034V-\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M9\001aC\016\037K%\"\004c\001\007\016\0375\t!!\003\002\017\005\tY\021IY:ue\006\034GoU3r!\t\001\022\003\004\001\005\013I\001!\031A\n\003\003\005\013\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020E\002\r9=I!!\b\002\003\025%sG-\032=fIN+\027\017\005\003 E=!S\"\001\021\013\005\005\"\021aB4f]\026\024\030nY\005\003G\001\022!dR3oKJL7\r\026:bm\026\0248/\0312mKR+W\016\0357bi\026\004\"\001\004\001\021\t11s\002K\005\003O\t\0211#\0238eKb,GmU3r\037B$\030.\\5{K\022\0042\001\004\001\020!\021Q3fD\027\016\003\021I!\001\f\003\003)\r+8\017^8n!\006\024\030\r\0347fY&T\030M\0317f!\rq#gD\007\002_)\0211\001\r\006\003c\021\t\001\002]1sC2dW\r\\\005\003g=\022\001\002U1s\003J\024\030-\037\t\003+UJ!A\016\004\003\031M+'/[1mSj\f'\r\\3\t\021a\002!Q1A\005Be\na\001\\3oORDW#\001\036\021\005UY\024B\001\037\007\005\rIe\016\036\005\t}\001\021\t\021)A\005u\0059A.\0328hi\"\004\003\"\002!\001\t\003\t\025A\002\037j]&$h\b\006\002)\005\")\001h\020a\001u!)A\t\001C!\013\006I1m\\7qC:LwN\\\013\002\rB\031qd\022\023\n\005!\003#\001E$f]\026\024\030nY\"p[B\fg.[8o\021\035Q\005A1A\005\002-\013Q!\031:sCf,\022\001\024\t\004+5{\025B\001(\007\005\025\t%O]1z!\t)\002+\003\002R\r\t1\021I\\=SK\032Daa\025\001!\002\023a\025AB1se\006L\b\005C\003V\001\021\005c+A\002qCJ,\022!\f\005\0061\002!\t!W\001\006CB\004H.\037\013\003\037iCQaW,A\002i\n1!\0333y\021\025i\006\001\"\001_\003\031)\b\017Z1uKR\031qLY2\021\005U\001\027BA1\007\005\021)f.\033;\t\013mc\006\031\001\036\t\013\021d\006\031A\b\002\t\025dW-\034\005\006M\002!\teZ\001\bM>\024X-Y2i+\tAw\016\006\002`S\")!.\032a\001W\006\ta\r\005\003\026Y>q\027BA7\007\005%1UO\\2uS>t\027\007\005\002\021_\022)\001/\032b\001'\t\tQ\013C\003s\001\021\0053/A\006d_BLHk\\!se\006LXC\001;z)\021yV\017 @\t\013Y\f\b\031A<\002\005a\034\bcA\013NqB\021\001#\037\003\006uF\024\ra\037\002\002\005F\021q\002\007\005\006{F\004\rAO\001\006gR\f'\017\036\005\006F\004\rAO\001\004Y\026t\007bBA\002\001\021\005\023QA\001\006G2|g.\032\013\002Q!*\001!!\003\002\020A\031Q#a\003\n\007\0055aA\001\tTKJL\027\r\034,feNLwN\\+J\tzAQ\003P\037SR%s1oB\004\002\024\tA\t!!\006\002\021\005\023(/Y=TKF\0042\001DA\f\r\031\t!\001#\001\002\032M)\021qCA\016iA!q$!\b%\023\r\ty\002\t\002\013'\026\fh)Y2u_JL\bb\002!\002\030\021\005\0211\005\013\003\003+A\001\"a\n\002\030\021\r\021\021F\001\rG\006t')^5mI\032\023x.\\\013\005\003W\ti$\006\002\002.AIq$a\f\0024\005m\022qH\005\004\003c\001#\001D\"b]\n+\030\016\0343Ge>l\007\003BA\033\003oi!!a\006\n\007\005erI\001\003D_2d\007c\001\t\002>\0211!#!\nC\002M\001B\001\004\001\002<!A\0211IA\f\t\003\t)%\001\006oK^\024U/\0337eKJ,B!a\022\002RU\021\021\021\n\t\b\031\005-\023qJA*\023\r\tiE\001\002\b\005VLG\016Z3s!\r\001\022\021\013\003\007%\005\005#\031A\n\021\t1\001\021q\n\005\013\003/\n9\"!A\005\n\005e\023a\003:fC\022\024Vm]8mm\026$\"!a\027\021\t\005u\023qM\007\003\003?RA!!\031\002d\005!A.\0318h\025\t\t)'\001\003kCZ\f\027\002BA5\003?\022aa\0242kK\016$\b")
/*     */ public class ArraySeq<A> extends AbstractSeq<A> implements IndexedSeq<A>, GenericTraversableTemplate<A, ArraySeq>, IndexedSeqOptimized<A, ArraySeq<A>>, CustomParallelizable<A, ParArray<A>>, Serializable {
/*     */   public static final long serialVersionUID = 1530165946227428979L;
/*     */   
/*     */   private final int length;
/*     */   
/*     */   private final Object[] array;
/*     */   
/*     */   public Combiner<A, ParArray<A>> parCombiner() {
/*  45 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*  45 */     return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*  45 */     return IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  45 */     return IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*  45 */     return IterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*  45 */     return TraversableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*  45 */     return TraversableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*  45 */     return TraversableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*  45 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*  45 */     return SeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  45 */     return IndexedSeqOptimized.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  45 */     return IndexedSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  45 */     return IndexedSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  45 */     return IndexedSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  45 */     return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  45 */     return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  45 */     return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  45 */     return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  45 */     return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  45 */     return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> slice(int from, int until) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public A head() {
/*  45 */     return (A)IndexedSeqOptimized.class.head(this);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> tail() {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.tail(this);
/*     */   }
/*     */   
/*     */   public A last() {
/*  45 */     return (A)IndexedSeqOptimized.class.last(this);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> init() {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.init(this);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> take(int n) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> drop(int n) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> takeRight(int n) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.takeRight(this, n);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> dropRight(int n) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public Tuple2<ArraySeq<A>, ArraySeq<A>> splitAt(int n) {
/*  45 */     return IndexedSeqOptimized.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> takeWhile(Function1 p) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> dropWhile(Function1 p) {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<ArraySeq<A>, ArraySeq<A>> span(Function1 p) {
/*  45 */     return IndexedSeqOptimized.class.span(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  45 */     return IndexedSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  45 */     return IndexedSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  45 */     return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  45 */     return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  45 */     return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> reverse() {
/*  45 */     return (ArraySeq<A>)IndexedSeqOptimized.class.reverse(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> reverseIterator() {
/*  45 */     return IndexedSeqOptimized.class.reverseIterator(this);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that, int offset) {
/*  45 */     return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <B> boolean endsWith(GenSeq that) {
/*  45 */     return IndexedSeqOptimized.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> seq() {
/*  45 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> thisCollection() {
/*  45 */     return IndexedSeqLike$class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toCollection(Object repr) {
/*  45 */     return IndexedSeqLike$class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  45 */     return IndexedSeqLike$class.view(this);
/*     */   }
/*     */   
/*     */   public IndexedSeqView<A, ArraySeq<A>> view(int from, int until) {
/*  45 */     return IndexedSeqLike$class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  45 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  45 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  45 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public int length() {
/*  45 */     return this.length;
/*     */   }
/*     */   
/*     */   public ArraySeq(int length) {
/*  45 */     IndexedSeqLike.class.$init$(this);
/*  45 */     IndexedSeq.class.$init$(this);
/*  45 */     IndexedSeqLike$class.$init$(this);
/*  45 */     IndexedSeq$class.$init$(this);
/*  45 */     IndexedSeqOptimized.class.$init$(this);
/*  45 */     CustomParallelizable.class.$init$(this);
/*  56 */     this.array = new Object[length];
/*     */   }
/*     */   
/*     */   public GenericCompanion<ArraySeq> companion() {
/*     */     return (GenericCompanion<ArraySeq>)ArraySeq$.MODULE$;
/*     */   }
/*     */   
/*     */   public Object[] array() {
/*  56 */     return this.array;
/*     */   }
/*     */   
/*     */   public ParArray<A> par() {
/*  58 */     return ParArray$.MODULE$.handoff(array(), length());
/*     */   }
/*     */   
/*     */   public A apply(int idx) {
/*  61 */     if (idx >= length())
/*  61 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/*  62 */     return (A)array()[idx];
/*     */   }
/*     */   
/*     */   public void update(int idx, Object elem) {
/*  66 */     if (idx >= length())
/*  66 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx).toString()); 
/*  67 */     array()[idx] = elem;
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  71 */     int i = 0;
/*  72 */     while (i < length()) {
/*  73 */       f.apply(array()[i]);
/*  74 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  88 */     Predef$ predef$1 = Predef$.MODULE$;
/*  88 */     int i = RichInt$.MODULE$.min$extension(len, ScalaRunTime$.MODULE$.array_length(xs) - start);
/*  88 */     Predef$ predef$2 = Predef$.MODULE$;
/*  88 */     int len1 = RichInt$.MODULE$.min$extension(i, length());
/*  89 */     Array$.MODULE$.copy(array(), 0, xs, start, len1);
/*     */   }
/*     */   
/*     */   public ArraySeq<A> clone() {
/*  93 */     Object[] cloned = (Object[])array().clone();
/*  94 */     return new ArraySeq$$anon$1(this, (ArraySeq<A>)cloned);
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<ArraySeq<?>, A, ArraySeq<A>> canBuildFrom() {
/*     */     return ArraySeq$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<ArraySeq<A>> unapplySeq(ArraySeq<A> paramArraySeq) {
/*     */     return ArraySeq$.MODULE$.unapplySeq(paramArraySeq);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (ArraySeq<A>)ArraySeq$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> ArraySeq<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (ArraySeq<T>)ArraySeq$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> ArraySeq<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (ArraySeq<T>)ArraySeq$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>>)ArraySeq$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>)ArraySeq$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<A>>>)ArraySeq$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (ArraySeq<ArraySeq<A>>)ArraySeq$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (ArraySeq<A>)ArraySeq$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>>)ArraySeq$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<ArraySeq<A>>>>)ArraySeq$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<ArraySeq<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (ArraySeq<ArraySeq<ArraySeq<A>>>)ArraySeq$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<ArraySeq<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (ArraySeq<ArraySeq<A>>)ArraySeq$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (ArraySeq<A>)ArraySeq$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (ArraySeq<A>)ArraySeq$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<ArraySeq>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return ArraySeq$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> ArraySeq<A> empty() {
/*     */     return (ArraySeq<A>)ArraySeq$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public class ArraySeq$$anon$1 extends ArraySeq<A> {
/*     */     private final Object[] array;
/*     */     
/*     */     public ArraySeq$$anon$1(ArraySeq $outer, Object[] cloned$1) {
/*  94 */       super($outer.length());
/*  95 */       this.array = cloned$1;
/*     */     }
/*     */     
/*     */     public Object[] array() {
/*  95 */       return this.array;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ArraySeq$$anonfun$newBuilder$1 extends AbstractFunction1<ArrayBuffer<A>, ArraySeq<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArraySeq<A> apply(ArrayBuffer buf) {
/* 110 */       ArraySeq<A> result = new ArraySeq(buf.length());
/* 111 */       buf.copyToArray(result.array(), 0);
/* 112 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArraySeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */