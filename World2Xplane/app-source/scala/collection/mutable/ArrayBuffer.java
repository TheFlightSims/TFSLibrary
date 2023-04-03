/*     */ package scala.collection.mutable;
/*     */ 
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
/*     */ import scala.collection.GenTraversableOnce;
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
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParArray;
/*     */ import scala.collection.parallel.mutable.ParArray$;
/*     */ import scala.compat.Platform$;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=e\001B\001\003\001%\0211\"\021:sCf\024UO\0324fe*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQ\021c\005\006\001\027mqR%\013\0270eu\0022\001D\007\020\033\005\021\021B\001\b\003\0059\t%m\035;sC\016$()\0364gKJ\004\"\001E\t\r\001\021)!\003\001b\001'\t\t\021)\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\0071ar\"\003\002\036\005\t1!)\0364gKJ\004Ba\b\022\020I5\t\001E\003\002\"\t\0059q-\0328fe&\034\027BA\022!\005i9UM\\3sS\016$&/\031<feN\f'\r\\3UK6\004H.\031;f!\ta\001\001\005\003\rM=A\023BA\024\003\005)\021UO\0324fe2K7.\032\t\004\031\001y\001\003\002\007+\037!J!a\013\002\003'%sG-\032=fIN+\027o\0249uS6L'0\0323\021\t1is\002K\005\003]\t\021qAQ;jY\022,'\017E\002\ra=I!!\r\002\003\035I+7/\033>bE2,\027I\035:bsB!1\007N\b7\033\005!\021BA\033\005\005Q\031Uo\035;p[B\013'/\0317mK2L'0\0312mKB\031qgO\b\016\003aR!aA\035\013\005i\"\021\001\0039be\006dG.\0327\n\005qB$\001\003)be\006\023(/Y=\021\005Uq\024BA \007\0051\031VM]5bY&T\030M\0317f\021!\t\005A!b\001\n#\022\025aC5oSRL\027\r\\*ju\026,\022a\021\t\003+\021K!!\022\004\003\007%sG\017\003\005H\001\t\005\t\025!\003D\0031Ig.\033;jC2\034\026N_3!\021\025I\005\001\"\001K\003\031a\024N\\5u}Q\021\001f\023\005\006\003\"\003\ra\021\005\006\033\002!\tET\001\nG>l\007/\0318j_:,\022a\024\t\004?A#\023BA)!\005A9UM\\3sS\016\034u.\0349b]&|g\016C\003J\001\021\0051\013F\001)\021\025)\006\001\"\001W\003\025\031G.Z1s)\0059\006CA\013Y\023\tIfA\001\003V]&$\b\"B.\001\t\003b\026\001C:ju\026D\025N\034;\025\005]k\006\"\0020[\001\004\031\025a\0017f]\")\001\r\001C!C\006\031\001/\031:\026\003YBQa\031\001\005\002\021\f\001\002\n9mkN$S-\035\013\003K\032l\021\001\001\005\006O\n\004\raD\001\005K2,W\016C\003j\001\021\005#.A\007%a2,8\017\n9mkN$S-\035\013\003K.DQ\001\0345A\0025\f!\001_:\021\007Mrw\"\003\002p\t\tyAK]1wKJ\034\030M\0317f\037:\034W\rC\003r\001\021\005!/\001\b%a2,8\017J3rI\r|Gn\0348\025\005\025\034\b\"B4q\001\004y\001\"B;\001\t\0032\030a\005\023qYV\034H\005\0357vg\022*\027\017J2pY>tGCA3x\021\025aG\0171\001n\021\025I\b\001\"\001{\003%Ign]3si\006cG\016F\002XwvDQ\001 =A\002\r\013\021A\034\005\006}b\004\ra`\001\004g\026\f\b\003B\032\002\002=I1!a\001\005\005-!&/\031<feN\f'\r\\3\t\017\005\035\001\001\"\021\002\n\0051!/Z7pm\026$RaVA\006\003\033Aa\001`A\003\001\004\031\005bBA\b\003\013\001\raQ\001\006G>,h\016\036\005\b\003\017\001A\021AA\n)\ry\021Q\003\005\007y\006E\001\031A\"\t\r\005e\001\001\"\001T\003\031\021Xm];mi\"9\021Q\004\001\005B\005}\021\001D:ue&tw\r\025:fM&DXCAA\021!\021\t\031#!\013\017\007U\t)#C\002\002(\031\ta\001\025:fI\0264\027\002BA\026\003[\021aa\025;sS:<'bAA\024\r!*\001!!\r\0028A\031Q#a\r\n\007\005UbA\001\tTKJL\027\r\034,feNLwN\\+J\tzAQ\003\017YT\007\013u9oB\004\002<\tA\t!!\020\002\027\005\023(/Y=Ck\0324WM\035\t\004\031\005}bAB\001\003\021\003\t\teE\003\002@\005\rS\b\005\003 \003\013\"\023bAA$A\tQ1+Z9GC\016$xN]=\t\017%\013y\004\"\001\002LQ\021\021Q\b\005\t\003\037\ny\004b\001\002R\005a1-\0318Ck&dGM\022:p[V!\0211KA3+\t\t)\006E\005 \003/\nY&a\031\002h%\031\021\021\f\021\003\031\r\013gNQ;jY\0224%o\\7\021\t\005u\023qL\007\003\003I1!!\031Q\005\021\031u\016\0347\021\007A\t)\007\002\004\023\003\033\022\ra\005\t\005\031\001\t\031\007\003\005\002l\005}B\021AA7\003)qWm\036\"vS2$WM]\013\005\003_\n)(\006\002\002rA1A\"LA:\003o\0022\001EA;\t\031\021\022\021\016b\001'A!A\002AA:\021)\tY(a\020\002\002\023%\021QP\001\fe\026\fGMU3t_24X\r\006\002\002\000A!\021\021QAF\033\t\t\031I\003\003\002\006\006\035\025\001\0027b]\036T!!!#\002\t)\fg/Y\005\005\003\033\013\031I\001\004PE*,7\r\036")
/*     */ public class ArrayBuffer<A> extends AbstractBuffer<A> implements Buffer<A>, GenericTraversableTemplate<A, ArrayBuffer>, BufferLike<A, ArrayBuffer<A>>, IndexedSeqOptimized<A, ArrayBuffer<A>>, Builder<A, ArrayBuffer<A>>, ResizableArray<A>, CustomParallelizable<A, ParArray<A>>, Serializable {
/*     */   public static final long serialVersionUID = 1529165946227428979L;
/*     */   
/*     */   private final int initialSize;
/*     */   
/*     */   private Object[] array;
/*     */   
/*     */   private int size0;
/*     */   
/*     */   public Combiner<A, ParArray<A>> parCombiner() {
/*  47 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public Object[] array() {
/*  47 */     return this.array;
/*     */   }
/*     */   
/*     */   public void array_$eq(Object[] x$1) {
/*  47 */     this.array = x$1;
/*     */   }
/*     */   
/*     */   public int size0() {
/*  47 */     return this.size0;
/*     */   }
/*     */   
/*     */   public void size0_$eq(int x$1) {
/*  47 */     this.size0 = x$1;
/*     */   }
/*     */   
/*     */   public int length() {
/*  47 */     return ResizableArray$class.length(this);
/*     */   }
/*     */   
/*     */   public A apply(int idx) {
/*  47 */     return (A)ResizableArray$class.apply(this, idx);
/*     */   }
/*     */   
/*     */   public void update(int idx, Object elem) {
/*  47 */     ResizableArray$class.update(this, idx, elem);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  47 */     ResizableArray$class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/*  47 */     ResizableArray$class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public void reduceToSize(int sz) {
/*  47 */     ResizableArray$class.reduceToSize(this, sz);
/*     */   }
/*     */   
/*     */   public void ensureSize(int n) {
/*  47 */     ResizableArray$class.ensureSize(this, n);
/*     */   }
/*     */   
/*     */   public void swap(int a, int b) {
/*  47 */     ResizableArray$class.swap(this, a, b);
/*     */   }
/*     */   
/*     */   public void copy(int m, int n, int len) {
/*  47 */     ResizableArray$class.copy(this, m, n, len);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> seq() {
/*  47 */     return IndexedSeq$class.seq(this);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  47 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  47 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  47 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  47 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*  47 */     return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*  47 */     return IterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  47 */     return IterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*  47 */     return IterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*  47 */     return TraversableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*  47 */     return TraversableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*  47 */     return TraversableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*  47 */     return IterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*  47 */     return SeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  47 */     return IndexedSeqOptimized.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/*  47 */     return IndexedSeqOptimized.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/*  47 */     return IndexedSeqOptimized.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/*  47 */     return IndexedSeqOptimized.class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/*  47 */     return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/*  47 */     return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/*  47 */     return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/*  47 */     return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  47 */     return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  47 */     return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> slice(int from, int until) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public A head() {
/*  47 */     return (A)IndexedSeqOptimized.class.head(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> tail() {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.tail(this);
/*     */   }
/*     */   
/*     */   public A last() {
/*  47 */     return (A)IndexedSeqOptimized.class.last(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> init() {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.init(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> take(int n) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> drop(int n) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> takeRight(int n) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.takeRight(this, n);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> dropRight(int n) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.dropRight(this, n);
/*     */   }
/*     */   
/*     */   public Tuple2<ArrayBuffer<A>, ArrayBuffer<A>> splitAt(int n) {
/*  47 */     return IndexedSeqOptimized.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> takeWhile(Function1 p) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> dropWhile(Function1 p) {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<ArrayBuffer<A>, ArrayBuffer<A>> span(Function1 p) {
/*  47 */     return IndexedSeqOptimized.class.span(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean sameElements(GenIterable that) {
/*  47 */     return IndexedSeqOptimized.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public int lengthCompare(int len) {
/*  47 */     return IndexedSeqOptimized.class.lengthCompare(this, len);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  47 */     return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  47 */     return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  47 */     return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> reverse() {
/*  47 */     return (ArrayBuffer<A>)IndexedSeqOptimized.class.reverse(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> reverseIterator() {
/*  47 */     return IndexedSeqOptimized.class.reverseIterator(this);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that, int offset) {
/*  47 */     return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <B> boolean endsWith(GenSeq that) {
/*  47 */     return IndexedSeqOptimized.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> thisCollection() {
/*  47 */     return IndexedSeqLike$class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toCollection(Object repr) {
/*  47 */     return IndexedSeqLike$class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  47 */     return IndexedSeqLike$class.view(this);
/*     */   }
/*     */   
/*     */   public IndexedSeqView<A, ArrayBuffer<A>> view(int from, int until) {
/*  47 */     return IndexedSeqLike$class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  47 */     return IndexedSeqLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  47 */     return IndexedSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/*  47 */     return IndexedSeqLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  47 */     return this.initialSize;
/*     */   }
/*     */   
/*     */   public ArrayBuffer(int initialSize) {
/*  47 */     IndexedSeqLike.class.$init$(this);
/*  47 */     IndexedSeqLike$class.$init$(this);
/*  47 */     IndexedSeqOptimized.class.$init$(this);
/*  47 */     Builder$class.$init$(this);
/*  47 */     IndexedSeq.class.$init$(this);
/*  47 */     IndexedSeq$class.$init$(this);
/*  47 */     ResizableArray$class.$init$(this);
/*  47 */     CustomParallelizable.class.$init$(this);
/*     */   }
/*     */   
/*     */   public GenericCompanion<ArrayBuffer> companion() {
/*  58 */     return (GenericCompanion<ArrayBuffer>)ArrayBuffer$.MODULE$;
/*     */   }
/*     */   
/*     */   public ArrayBuffer() {
/*  62 */     this(16);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  64 */     reduceToSize(0);
/*     */   }
/*     */   
/*     */   public void sizeHint(int len) {
/*  67 */     if (len > size() && len >= 1) {
/*  68 */       Object[] newarray = new Object[len];
/*  69 */       int i = size0();
/*  69 */       Object[] arrayOfObject1 = array();
/*  69 */       Platform$ platform$ = Platform$.MODULE$;
/*  69 */       System.arraycopy(arrayOfObject1, 0, newarray, 0, i);
/*  70 */       array_$eq(newarray);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParArray<A> par() {
/*  74 */     return ParArray$.MODULE$.handoff(array(), size());
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> $plus$eq(Object elem) {
/*  83 */     ensureSize(size0() + 1);
/*  84 */     array()[size0()] = elem;
/*  85 */     size0_$eq(size0() + 1);
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> $plus$plus$eq(TraversableOnce xs) {
/*     */     ArrayBuffer<A> arrayBuffer;
/*  95 */     if (xs instanceof IndexedSeqLike) {
/*  95 */       IndexedSeqLike indexedSeqLike = (IndexedSeqLike)xs;
/*  97 */       int n = indexedSeqLike.length();
/*  98 */       ensureSize(size0() + n);
/*  99 */       indexedSeqLike.copyToArray(array(), size0(), n);
/* 100 */       size0_$eq(size0() + n);
/* 101 */       arrayBuffer = this;
/*     */     } else {
/* 103 */       arrayBuffer = (ArrayBuffer)Growable.class.$plus$plus$eq(this, xs);
/*     */     } 
/*     */     return arrayBuffer;
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> $plus$eq$colon(Object elem) {
/* 114 */     ensureSize(size0() + 1);
/* 115 */     copy(0, 1, size0());
/* 116 */     array()[0] = elem;
/* 117 */     size0_$eq(size0() + 1);
/* 118 */     return this;
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> $plus$plus$eq$colon(TraversableOnce xs) {
/* 127 */     insertAll(0, xs.toTraversable());
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public void insertAll(int n, Traversable seq) {
/* 138 */     if (n < 0 || n > size0())
/* 138 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 139 */     List xs = seq.toList();
/* 140 */     int len = xs.length();
/* 141 */     ensureSize(size0() + len);
/* 142 */     copy(n, n + len, size0() - n);
/* 143 */     xs.copyToArray(array(), n);
/* 144 */     size0_$eq(size0() + len);
/*     */   }
/*     */   
/*     */   public void remove(int n, int count) {
/* 155 */     boolean bool = (count >= 0) ? true : false;
/* 155 */     Predef$ predef$ = Predef$.MODULE$;
/* 155 */     if (bool) {
/* 156 */       if (n < 0 || n > size0() - count)
/* 156 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(n).toString()); 
/* 157 */       copy(n + count, n, size0() - n + count);
/* 158 */       reduceToSize(size0() - count);
/*     */       return;
/*     */     } 
/*     */     throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append("removing negative number of elements").toString());
/*     */   }
/*     */   
/*     */   public A remove(int n) {
/* 167 */     Object result = apply(n);
/* 168 */     remove(n, 1);
/* 169 */     return (A)result;
/*     */   }
/*     */   
/*     */   public ArrayBuffer<A> result() {
/* 172 */     return this;
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 176 */     return "ArrayBuffer";
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<ArrayBuffer<?>, A, ArrayBuffer<A>> canBuildFrom() {
/*     */     return ArrayBuffer$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Some<ArrayBuffer<A>> unapplySeq(ArrayBuffer<A> paramArrayBuffer) {
/*     */     return ArrayBuffer$.MODULE$.unapplySeq(paramArrayBuffer);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (ArrayBuffer<A>)ArrayBuffer$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> ArrayBuffer<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (ArrayBuffer<T>)ArrayBuffer$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> ArrayBuffer<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (ArrayBuffer<T>)ArrayBuffer$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>>)ArrayBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>)ArrayBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>)ArrayBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (ArrayBuffer<ArrayBuffer<A>>)ArrayBuffer$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (ArrayBuffer<A>)ArrayBuffer$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>>)ArrayBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>>)ArrayBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (ArrayBuffer<ArrayBuffer<ArrayBuffer<A>>>)ArrayBuffer$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<ArrayBuffer<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (ArrayBuffer<ArrayBuffer<A>>)ArrayBuffer$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (ArrayBuffer<A>)ArrayBuffer$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (ArrayBuffer<A>)ArrayBuffer$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<ArrayBuffer>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*     */     return ArrayBuffer$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> ArrayBuffer<A> empty() {
/*     */     return (ArrayBuffer<A>)ArrayBuffer$.MODULE$.empty();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */