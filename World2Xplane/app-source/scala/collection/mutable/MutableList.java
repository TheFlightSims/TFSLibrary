/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
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
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055g\001B\001\003\001%\0211\"T;uC\ndW\rT5ti*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQ\021cE\004\001\027mq2EK\027\021\0071iq\"D\001\003\023\tq!AA\006BEN$(/Y2u'\026\f\bC\001\t\022\031\001!QA\005\001C\002M\021\021!Q\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\raAdD\005\003;\t\021\021\002T5oK\006\0248+Z9\021\t}\001sBI\007\002\t%\021\021\005\002\002\023\031&tW-\031:TKF|\005\017^5nSj,G\rE\002\r\001=\001B\001J\024\020S5\tQE\003\002'\t\0059q-\0328fe&\034\027B\001\025&\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\ta\001\001\005\003\rW=\021\023B\001\027\003\005\035\021U/\0337eKJ\004\"!\006\030\n\005=2!\001D*fe&\fG.\033>bE2,\007\"B\031\001\t\003\021\024A\002\037j]&$h\bF\001#\021\025!\004\001\"\0216\003%\031w.\0349b]&|g.F\0017!\r!s'K\005\003q\025\022\001cR3oKJL7mQ8na\006t\027n\0348\t\ri\002\001\025\"\025<\003)qWm\036\"vS2$WM]\013\002U!9Q\b\001a\001\n#q\024A\0024jeN$\b'F\001@!\ra\001iD\005\003\003\n\021!\002T5oW\026$G*[:u\021\035\031\005\0011A\005\022\021\013!BZ5sgR\004t\fJ3r)\t)\005\n\005\002\026\r&\021qI\002\002\005+:LG\017C\004J\005\006\005\t\031A \002\007a$\023\007\003\004L\001\001\006KaP\001\bM&\0248\017\036\031!\021\035i\005\0011A\005\022y\nQ\001\\1tiBBqa\024\001A\002\023E\001+A\005mCN$\bg\030\023fcR\021Q)\025\005\b\023:\013\t\0211\001@\021\031\031\006\001)Q\005\0051A.Y:ua\001Bq!\026\001A\002\023Ea+A\002mK:,\022a\026\t\003+aK!!\027\004\003\007%sG\017C\004\\\001\001\007I\021\003/\002\0171,gn\030\023fcR\021Q)\030\005\b\023j\013\t\0211\001X\021\031y\006\001)Q\005/\006!A.\0328!\021\025\t\007\001\"\001c\003\035!x.U;fk\026,\022a\031\t\004\031\021|\021BA3\003\005\025\tV/Z;f\021\0259\007\001\"\021i\003\035I7/R7qif,\022!\033\t\003+)L!a\033\004\003\017\t{w\016\\3b]\")Q\016\001C!]\006!\001.Z1e+\005y\001\"\0029\001\t\003\n\030\001\002;bS2,\022A\t\005\006g\002!i\001^\001\ti\006LG.S7qYR\021Q)\036\005\006mJ\004\rAI\001\003i2DQ\001\037\001\005\002e\fa\002\n9mkN$S-\035\023d_2|g\016\006\002{w6\t\001\001C\003}o\002\007q\"\001\003fY\026l\007\"\002@\001\t\0032\026A\0027f]\036$\b\016C\004\002\002\001!\t%a\001\002\013\005\004\b\017\\=\025\007=\t)\001\003\004\002\b}\004\raV\001\002]\"9\0211\002\001\005\002\0055\021AB;qI\006$X\rF\003F\003\037\t\t\002C\004\002\b\005%\001\031A,\t\017\005M\021\021\002a\001\037\005\t\001\020C\004\002\030\001!\t!!\007\002\007\035,G\017\006\003\002\034\005\005\002\003B\013\002\036=I1!a\b\007\005\031y\005\017^5p]\"9\021qAA\013\001\0049\006bBA\023\001\021E\021qE\001\faJ,\007/\0328e\0132,W\016F\002F\003SAa\001`A\022\001\004y\001bBA\027\001\021E\021qF\001\013CB\004XM\0343FY\026lGcA#\0022!1A0a\013A\002=Aq!!\016\001\t\003\n9$\001\005ji\026\024\030\r^8s+\t\tI\004\005\003 \003wy\021bAA\037\t\tA\021\n^3sCR|'\017\003\004\002B\001!\tE\\\001\005Y\006\034H\017C\004\002F\001!\t%a\022\002\rQ|G*[:u+\t\tI\005E\003\002L\005Es\"\004\002\002N)\031\021q\n\003\002\023%lW.\036;bE2,\027\002BA*\003\033\022A\001T5ti\"9\021q\013\001\005\002\tq\024\001\004;p\031&t7.\0323MSN$\bbBA.\001\021\005\021QL\001\tIAdWo\035\023fcR\031!0a\030\t\rq\fI\0061\001\020\021\035\t\031\007\001C\001\003K\nQa\0317fCJ$\022!\022\005\007\003S\002A\021\001\032\002\rI,7/\0367u\021\031\ti\007\001C!e\005)1\r\\8oK\"*\001!!\035\002xA\031Q#a\035\n\007\005UdA\001\tTKJL\027\r\034,feNLwN\\+J\tzA!+\033P3}\0025\006yB\004\002|\tA\t!! \002\0275+H/\0312mK2K7\017\036\t\004\031\005}dAB\001\003\021\003\t\tiE\003\002\000\005\rU\006\005\003%\003\013K\023bAADK\tQ1+Z9GC\016$xN]=\t\017E\ny\b\"\001\002\fR\021\021Q\020\005\t\003\037\013y\bb\001\002\022\006a1-\0318Ck&dGM\022:p[V!\0211SAS+\t\t)\nE\005%\003/\013Y*a)\002(&\031\021\021T\023\003\031\r\013gNQ;jY\0224%o\\7\021\t\005u\025qT\007\003\003J1!!)8\005\021\031u\016\0347\021\007A\t)\013\002\004\023\003\033\023\ra\005\t\005\031\001\t\031\013C\004;\003\"\t!a+\026\t\0055\0261W\013\003\003_\003b\001D\026\0022\006U\006c\001\t\0024\0221!#!+C\002M\001B\001\004\001\0022\"Q\021\021XA@\003\003%I!a/\002\027I,\027\r\032*fg>dg/\032\013\003\003{\003B!a0\002J6\021\021\021\031\006\005\003\007\f)-\001\003mC:<'BAAd\003\021Q\027M^1\n\t\005-\027\021\031\002\007\037\nTWm\031;")
/*     */ public class MutableList<A> extends AbstractSeq<A> implements LinearSeq<A>, LinearSeqOptimized<A, MutableList<A>>, GenericTraversableTemplate<A, MutableList>, Builder<A, MutableList<A>>, Serializable {
/*     */   public static final long serialVersionUID = 5938451523372603072L;
/*     */   
/*     */   private LinkedList<A> first0;
/*     */   
/*     */   private LinkedList<A> last0;
/*     */   
/*     */   private int len;
/*     */   
/*     */   public void sizeHint(int size) {
/*  30 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  30 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  30 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  30 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  30 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  30 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/*  30 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
/*  30 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <B> void foreach(Function1 f) {
/*  30 */     LinearSeqOptimized.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  30 */     return LinearSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  30 */     return LinearSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  30 */     return LinearSeqOptimized.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  30 */     return LinearSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 f) {
/*  30 */     return (B)LinearSeqOptimized.class.foldLeft(this, z, f);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 f) {
/*  30 */     return (B)LinearSeqOptimized.class.foldRight(this, z, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 f) {
/*  30 */     return (B)LinearSeqOptimized.class.reduceLeft(this, f);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  30 */     return (B)LinearSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public MutableList<A> take(int n) {
/*  30 */     return (MutableList<A>)LinearSeqOptimized.class.take(this, n);
/*     */   }
/*     */   
/*     */   public MutableList<A> drop(int n) {
/*  30 */     return (MutableList<A>)LinearSeqOptimized.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public MutableList<A> dropRight(int n) {
/*  30 */     return (MutableList<A>)LinearSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public MutableList<A> slice(int from, int until) {
/*  30 */     return (MutableList<A>)LinearSeqOptimized.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public MutableList<A> takeWhile(Function1 p) {
/*  30 */     return (MutableList<A>)LinearSeqOptimized.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<MutableList<A>, MutableList<A>> span(Function1 p) {
/*  30 */     return LinearSeqOptimized.class.span(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  30 */     return LinearSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  30 */     return LinearSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int x) {
/*  30 */     return LinearSeqOptimized.class.isDefinedAt(this, x);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  30 */     return LinearSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  30 */     return LinearSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  30 */     return LinearSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> seq() {
/*  30 */     return LinearSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> thisCollection() {
/*  30 */     return LinearSeqLike.class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  30 */     return LinearSeqLike.class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  30 */     return LinearSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  30 */     return LinearSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public MutableList() {
/*  31 */     LinearSeqLike.class.$init$(this);
/*  31 */     LinearSeq.class.$init$(this);
/*  31 */     LinearSeq$class.$init$(this);
/*  31 */     LinearSeqOptimized.class.$init$(this);
/*  31 */     Growable.class.$init$(this);
/*  31 */     Builder$class.$init$(this);
/*  42 */     this.first0 = new LinkedList<A>();
/*  43 */     this.last0 = first0();
/*  44 */     this.len = 0;
/*     */   }
/*     */   
/*     */   public GenericCompanion<MutableList> companion() {
/*     */     return (GenericCompanion<MutableList>)MutableList$.MODULE$;
/*     */   }
/*     */   
/*     */   public Builder<A, MutableList<A>> newBuilder() {
/*     */     return new MutableList();
/*     */   }
/*     */   
/*     */   public LinkedList<A> first0() {
/*     */     return this.first0;
/*     */   }
/*     */   
/*     */   public void first0_$eq(LinkedList<A> x$1) {
/*     */     this.first0 = x$1;
/*     */   }
/*     */   
/*     */   public LinkedList<A> last0() {
/*     */     return this.last0;
/*     */   }
/*     */   
/*     */   public void last0_$eq(LinkedList<A> x$1) {
/*     */     this.last0 = x$1;
/*     */   }
/*     */   
/*     */   public int len() {
/*  44 */     return this.len;
/*     */   }
/*     */   
/*     */   public void len_$eq(int x$1) {
/*  44 */     this.len = x$1;
/*     */   }
/*     */   
/*     */   public Queue<A> toQueue() {
/*  46 */     return new Queue<A>(first0(), last0(), len());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  50 */     return (len() == 0);
/*     */   }
/*     */   
/*     */   public A head() {
/*  54 */     if (nonEmpty())
/*  54 */       return first0().head(); 
/*  54 */     throw new NoSuchElementException();
/*     */   }
/*     */   
/*     */   public MutableList<A> tail() {
/*  59 */     MutableList<A> tl = new MutableList();
/*  60 */     tailImpl(tl);
/*  61 */     return tl;
/*     */   }
/*     */   
/*     */   private final void tailImpl(MutableList<A> tl) {
/*  66 */     boolean bool = nonEmpty();
/*  66 */     Predef$ predef$ = Predef$.MODULE$;
/*  66 */     if (bool) {
/*  67 */       tl.first0_$eq(first0().tail());
/*  68 */       tl.len_$eq(len() - 1);
/*  69 */       tl.last0_$eq((tl.len() == 0) ? tl.first0() : last0());
/*     */       return;
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("tail of empty list").toString());
/*     */   }
/*     */   
/*     */   public MutableList<A> $plus$eq$colon(Object elem) {
/*  77 */     prependElem((A)elem);
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public int length() {
/*  81 */     return len();
/*     */   }
/*     */   
/*     */   public A apply(int n) {
/*  86 */     return first0().apply(n);
/*     */   }
/*     */   
/*     */   public void update(int n, Object x) {
/*  91 */     first0().update(n, (A)x);
/*     */   }
/*     */   
/*     */   public Option<A> get(int n) {
/*  96 */     return first0().get(n);
/*     */   }
/*     */   
/*     */   public void prependElem(Object elem) {
/*  99 */     first0_$eq(new LinkedList<A>((A)elem, first0()));
/* 100 */     if (len() == 0)
/* 100 */       last0_$eq(first0()); 
/* 101 */     len_$eq(len() + 1);
/*     */   }
/*     */   
/*     */   public void appendElem(Object elem) {
/* 105 */     if (len() == 0) {
/* 106 */       prependElem((A)elem);
/*     */     } else {
/* 108 */       last0().next_$eq(new LinkedList<A>());
/* 109 */       last0_$eq(last0().next());
/* 110 */       last0().elem_$eq((A)elem);
/* 111 */       last0().next_$eq(new LinkedList<A>());
/* 112 */       len_$eq(len() + 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 118 */     return first0().iterator();
/*     */   }
/*     */   
/*     */   public A last() {
/* 121 */     if (isEmpty())
/* 121 */       throw new NoSuchElementException("MutableList.empty.last"); 
/* 122 */     return last0().elem();
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 128 */     return first0().toList();
/*     */   }
/*     */   
/*     */   public LinkedList<A> toLinkedList() {
/* 133 */     return first0();
/*     */   }
/*     */   
/*     */   public MutableList<A> $plus$eq(Object elem) {
/* 139 */     appendElem((A)elem);
/* 139 */     return this;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 142 */     first0_$eq(new LinkedList<A>());
/* 143 */     last0_$eq(first0());
/* 144 */     len_$eq(0);
/*     */   }
/*     */   
/*     */   public MutableList<A> result() {
/* 147 */     return this;
/*     */   }
/*     */   
/*     */   public MutableList<A> clone() {
/* 150 */     Builder<A, MutableList<A>> bf = newBuilder();
/* 151 */     bf.$plus$plus$eq((TraversableOnce)seq());
/* 152 */     return bf.result();
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<MutableList<?>, A, MutableList<A>> canBuildFrom() {
/*     */     return MutableList$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<MutableList<A>> unapplySeq(MutableList<A> paramMutableList) {
/*     */     return MutableList$.MODULE$.unapplySeq(paramMutableList);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (MutableList<A>)MutableList$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> MutableList<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (MutableList<T>)MutableList$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> MutableList<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (MutableList<T>)MutableList$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<MutableList<MutableList<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (MutableList<MutableList<MutableList<MutableList<MutableList<A>>>>>)MutableList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<MutableList<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (MutableList<MutableList<MutableList<MutableList<A>>>>)MutableList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (MutableList<MutableList<MutableList<A>>>)MutableList$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (MutableList<MutableList<A>>)MutableList$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (MutableList<A>)MutableList$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<MutableList<MutableList<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (MutableList<MutableList<MutableList<MutableList<MutableList<A>>>>>)MutableList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<MutableList<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (MutableList<MutableList<MutableList<MutableList<A>>>>)MutableList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<MutableList<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (MutableList<MutableList<MutableList<A>>>)MutableList$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<MutableList<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (MutableList<MutableList<A>>)MutableList$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (MutableList<A>)MutableList$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> MutableList<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (MutableList<A>)MutableList$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<MutableList>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return MutableList$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> MutableList<A> empty() {
/*     */     return (MutableList<A>)MutableList$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MutableList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */