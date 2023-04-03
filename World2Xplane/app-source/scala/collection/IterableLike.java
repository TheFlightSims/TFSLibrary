/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Equals;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tUbaB\001\003!\003\r\ta\002\002\r\023R,'/\0312mK2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\004\021Yi2#\002\001\n\033Ay\002C\001\006\f\033\005!\021B\001\007\005\005\r\te.\037\t\003\0259I!a\004\003\003\r\025\013X/\0317t!\021\t\"\003\006\017\016\003\tI!a\005\002\003\037Q\023\030M^3sg\006\024G.\032'jW\026\004\"!\006\f\r\001\0211q\003\001CC\002a\021\021!Q\t\0033%\001\"A\003\016\n\005m!!a\002(pi\"Lgn\032\t\003+u!aA\b\001\005\006\004A\"\001\002*faJ\004B!\005\021\0259%\021\021E\001\002\020\017\026t\027\n^3sC\ndW\rT5lK\")1\005\001C\001I\0051A%\0338ji\022\"\022!\n\t\003\025\031J!a\n\003\003\tUs\027\016\036\005\007S\001\001K\021\013\026\002\035QD\027n]\"pY2,7\r^5p]V\t1\006E\002\022YQI!!\f\002\003\021%#XM]1cY\026Daa\f\001!\n#\002\024\001\004;p\007>dG.Z2uS>tGCA\0262\021\025\021d\0061\001\035\003\021\021X\r\035:\t\013Q\002a\021A\033\002\021%$XM]1u_J,\022A\016\t\004#]\"\022B\001\035\003\005!IE/\032:bi>\024\b\"\002\036\001\t\003Y\024a\0024pe\026\f7\r[\013\003y\r#\"!J\037\t\013yJ\004\031A \002\003\031\004BA\003!\025\005&\021\021\t\002\002\n\rVt7\r^5p]F\002\"!F\"\005\013\021K$\031\001\r\003\003UCQA\022\001\005B\035\013aAZ8sC2dGC\001%L!\tQ\021*\003\002K\t\t9!i\\8mK\006t\007\"\002'F\001\004i\025!\0019\021\t)\001E\003\023\005\006\037\002!\t\005U\001\007KbL7\017^:\025\005!\013\006\"\002'O\001\004i\005\"B*\001\t\003\"\026\001\0024j]\022$\"!\026-\021\007)1F#\003\002X\t\t1q\n\035;j_:DQ\001\024*A\0025CQA\027\001\005Bm\013q![:F[B$\0300F\001I\021\025i\006\001\"\021_\003%1w\016\0343SS\036DG/\006\002`ER\021\001-\033\013\003C\022\004\"!\0062\005\013\rd&\031\001\r\003\003\tCQ!\032/A\002\031\f!a\0349\021\013)9G#Y1\n\005!$!!\003$v]\016$\030n\03483\021\025QG\f1\001b\003\005Q\b\"\0027\001\t\003j\027a\003:fIV\034WMU5hQR,\"A\0349\025\005=\024\bCA\013q\t\025\0317N1\001r#\t!\022\002C\003fW\002\0071\017E\003\013ORyw\016C\003v\001\021\005#&\001\006u_&#XM]1cY\026DQa\036\001\005BU\n!\002^8Ji\026\024\030\r^8s\021\025I\b\001\"\021{\003\021AW-\0313\026\003QAQ\001 \001\005Bu\fQa\0357jG\026$B\001\b@\002\b!1qp\037a\001\003\003\tAA\032:p[B\031!\"a\001\n\007\005\025AAA\002J]RDq!!\003|\001\004\t\t!A\003v]RLG\016C\004\002\016\001!\t%a\004\002\tQ\f7.\032\013\0049\005E\001\002CA\n\003\027\001\r!!\001\002\0039Dq!a\006\001\t\003\nI\"\001\003ee>\004Hc\001\017\002\034!A\0211CA\013\001\004\t\t\001C\004\002 \001!\t%!\t\002\023Q\f7.Z,iS2,Gc\001\017\002$!1A*!\bA\0025Cq!a\n\001\t\003\tI#A\004he>,\b/\0323\025\t\005-\022Q\006\t\004#]b\002\002CA\030\003K\001\r!!\001\002\tML'0\032\005\b\003g\001A\021AA\033\003\035\031H.\0333j]\036$B!a\013\0028!A\021qFA\031\001\004\t\t\001C\004\0024\001!\t!a\017\025\r\005-\022QHA \021!\ty#!\017A\002\005\005\001\002CA!\003s\001\r!!\001\002\tM$X\r\035\005\b\003\013\002A\021AA$\003%!\030m[3SS\036DG\017F\002\035\003\023B\001\"a\005\002D\001\007\021\021\001\005\b\003\033\002A\021AA(\003%!'o\0349SS\036DG\017F\002\035\003#B\001\"a\005\002L\001\007\021\021\001\005\b\003+\002A\021IA,\003-\031w\016]=U_\006\023(/Y=\026\t\005e\023q\r\013\bK\005m\023\021NA7\021!\ti&a\025A\002\005}\023A\001=t!\025Q\021\021MA3\023\r\t\031\007\002\002\006\003J\024\030-\037\t\004+\005\035DAB2\002T\t\007\021\017\003\005\002l\005M\003\031AA\001\003\025\031H/\031:u\021!\ty'a\025A\002\005\005\021a\0017f]\"9\0211\017\001\005\002\005U\024a\001>jaVA\021qOAM\003?\013i\b\006\003\002z\005\005F\003BA>\003\003\0032!FA?\t\035\ty(!\035C\002a\021A\001\0265bi\"A\0211QA9\001\b\t))\001\002cMBI\021qQAG9\005E\0251P\007\003\003\023S1!a#\003\003\0359WM\\3sS\016LA!a$\002\n\na1)\0318Ck&dGM\022:p[B9!\"a%\002\030\006u\025bAAK\t\t1A+\0369mKJ\0022!FAM\t\035\tY*!\035C\002E\024!!Q\031\021\007U\ty\n\002\004d\003c\022\r\001\007\005\t\003G\013\t\b1\001\002&\006!A\017[1u!\025\t\022qUAO\023\r\tIK\001\002\f\017\026t\027\n^3sC\ndW\rC\004\002.\002!\t!a,\002\riL\007/\0217m+!\t\t,!2\002B\006]F\003CAZ\003\017\fY-a4\025\t\005U\026\021\030\t\004+\005]FaBA@\003W\023\r\001\007\005\t\003\007\013Y\013q\001\002<BI\021qQAG9\005u\026Q\027\t\b\025\005M\025qXAb!\r)\022\021\031\003\b\0037\013YK1\001r!\r)\022Q\031\003\007G\006-&\031\001\r\t\021\005\r\0261\026a\001\003\023\004R!EAT\003\007D\001\"!4\002,\002\007\021qX\001\ti\"L7/\0227f[\"A\021\021[AV\001\004\t\031-\001\005uQ\006$X\t\\3n\021\035\t)\016\001C\001\003/\fAB_5q/&$\b.\0238eKb,b!!7\002h\006uG\003BAn\003?\0042!FAo\t\035\ty(a5C\002aA\001\"a!\002T\002\017\021\021\035\t\n\003\017\013i\tHAr\0037\004rACAJ\003K\f\t\001E\002\026\003O$q!a'\002T\n\007\021\017C\004\002l\002!\t!!<\002\031M\fW.Z#mK6,g\016^:\026\t\005=\030q\037\013\004\021\006E\b\002CAR\003S\004\r!a=\021\013E\t9+!>\021\007U\t9\020\002\004d\003S\024\r!\035\005\b\003w\004A\021IA\003!!xn\025;sK\006lWCAA\000!\025\021\tAa\002\025\033\t\021\031AC\002\003\006\t\t\021\"[7nkR\f'\r\\3\n\t\t%!1\001\002\007'R\024X-Y7\t\017\t5\001\001\"\021\003\020\005A1-\0318FcV\fG\016F\002I\005#Aq!a)\003\f\001\007\021\002C\004\003\026\001!\tEa\006\002\tYLWm^\013\003\0053\021bAa\007\003 \t\025ba\002B\017\005'\001!\021\004\002\ryI,g-\0338f[\026tGO\020\t\004\025\t\005\022b\001B\022\t\t1\021I\\=SK\032\004R!\005B\024)qI1A!\013\003\0051IE/\032:bE2,g+[3x\021\035\021)\002\001C!\005[!bA!\n\0030\tE\002bB@\003,\001\007\021\021\001\005\t\003\023\021Y\0031\001\002\002A!\021\003\001\013\035\001")
/*     */ public interface IterableLike<A, Repr> extends Equals, TraversableLike<A, Repr>, GenIterableLike<A, Repr> {
/*     */   Iterable<A> thisCollection();
/*     */   
/*     */   Iterable<A> toCollection(Repr paramRepr);
/*     */   
/*     */   Iterator<A> iterator();
/*     */   
/*     */   <U> void foreach(Function1<A, U> paramFunction1);
/*     */   
/*     */   boolean forall(Function1<A, Object> paramFunction1);
/*     */   
/*     */   boolean exists(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Option<A> find(Function1<A, Object> paramFunction1);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   <B> B foldRight(B paramB, Function2<A, B, B> paramFunction2);
/*     */   
/*     */   <B> B reduceRight(Function2<A, B, B> paramFunction2);
/*     */   
/*     */   Iterable<A> toIterable();
/*     */   
/*     */   Iterator<A> toIterator();
/*     */   
/*     */   A head();
/*     */   
/*     */   Repr slice(int paramInt1, int paramInt2);
/*     */   
/*     */   Repr take(int paramInt);
/*     */   
/*     */   Repr drop(int paramInt);
/*     */   
/*     */   Repr takeWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Iterator<Repr> grouped(int paramInt);
/*     */   
/*     */   Iterator<Repr> sliding(int paramInt);
/*     */   
/*     */   Iterator<Repr> sliding(int paramInt1, int paramInt2);
/*     */   
/*     */   Repr takeRight(int paramInt);
/*     */   
/*     */   Repr dropRight(int paramInt);
/*     */   
/*     */   <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
/*     */   
/*     */   <A1, B, That> That zip(GenIterable<B> paramGenIterable, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
/*     */   
/*     */   <B, A1, That> That zipAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB, CanBuildFrom<Repr, Tuple2<A1, B>, That> paramCanBuildFrom);
/*     */   
/*     */   <A1, That> That zipWithIndex(CanBuildFrom<Repr, Tuple2<A1, Object>, That> paramCanBuildFrom);
/*     */   
/*     */   <B> boolean sameElements(GenIterable<B> paramGenIterable);
/*     */   
/*     */   Stream<A> toStream();
/*     */   
/*     */   boolean canEqual(Object paramObject);
/*     */   
/*     */   Object view();
/*     */   
/*     */   IterableView<A, Repr> view(int paramInt1, int paramInt2);
/*     */   
/*     */   public class IterableLike$$anonfun$grouped$1 extends AbstractFunction1<Seq<A>, Repr> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public IterableLike$$anonfun$grouped$1(IterableLike $outer) {}
/*     */     
/*     */     public final Repr apply(Seq xs) {
/* 159 */       Builder b = this.$outer.newBuilder();
/* 160 */       b.$plus$plus$eq(xs);
/* 161 */       return (Repr)b.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableLike$$anonfun$sliding$1 extends AbstractFunction1<Seq<A>, Repr> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public IterableLike$$anonfun$sliding$1(IterableLike $outer) {}
/*     */     
/*     */     public final Repr apply(Seq xs) {
/* 188 */       Builder b = this.$outer.newBuilder();
/* 189 */       b.$plus$plus$eq(xs);
/* 190 */       return (Repr)b.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableLike$$anonfun$takeRight$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Iterator lead$1;
/*     */     
/*     */     private final BooleanRef go$1;
/*     */     
/*     */     public IterableLike$$anonfun$takeRight$1(IterableLike $outer, Builder b$1, Iterator lead$1, BooleanRef go$1) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 207 */       this.go$1.elem = true;
/* 207 */       this.lead$1.hasNext() ? this.lead$1.next() : BoxedUnit.UNIT;
/* 208 */       return this.go$1.elem ? this.b$1.$plus$eq(x) : BoxedUnit.UNIT;
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableLike$$anonfun$zipWithIndex$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$2;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public IterableLike$$anonfun$zipWithIndex$1(IterableLike $outer, Builder b$2, IntRef i$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 268 */       this.b$2.$plus$eq(new Tuple2(x, BoxesRunTime.boxToInteger(this.i$1.elem)));
/* 269 */       this.i$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public class IterableLike$$anon$1 implements IterableView<A, Repr> {
/*     */     private Repr underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public <B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/* 294 */       return IterableViewLike$class.newZipped(this, that);
/*     */     }
/*     */     
/*     */     public <A1, B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 294 */       return IterableViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<B> newForced(Function0 xs) {
/* 294 */       return IterableViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<B> newAppended(GenTraversable that) {
/* 294 */       return IterableViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<B> newMapped(Function1 f) {
/* 294 */       return IterableViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<B> newFlatMapped(Function1 f) {
/* 294 */       return IterableViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newFiltered(Function1 p) {
/* 294 */       return IterableViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 294 */       return IterableViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newDroppedWhile(Function1 p) {
/* 294 */       return IterableViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newTakenWhile(Function1 p) {
/* 294 */       return IterableViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newTaken(int n) {
/* 294 */       return IterableViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public IterableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A> newDropped(int n) {
/* 294 */       return IterableViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> drop(int n) {
/* 294 */       return IterableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> take(int n) {
/* 294 */       return IterableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 294 */       return (That)IterableViewLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 294 */       return (That)IterableViewLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 294 */       return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<A, Repr>> grouped(int size) {
/* 294 */       return IterableViewLike$class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<A, Repr>> sliding(int size, int step) {
/* 294 */       return IterableViewLike$class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 294 */       return IterableViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public Builder<A, IterableView<A, Repr>> newBuilder() {
/* 294 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/* 294 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public String viewIdString() {
/* 294 */       return TraversableViewLike$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<B> flatten(Function1 asTraversable) {
/* 294 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> filter(Function1 p) {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> withFilter(Function1 p) {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> partition(Function1 p) {
/* 294 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> init() {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> slice(int from, int until) {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> dropWhile(Function1 p) {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> takeWhile(Function1 p) {
/* 294 */       return (IterableView<A, Repr>)TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> span(Function1 p) {
/* 294 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<IterableView<A, Repr>, IterableView<A, Repr>> splitAt(int n) {
/* 294 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 294 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, IterableView<A, Repr>> groupBy(Function1 f) {
/* 294 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A2>> unzip(Function1 asPair) {
/* 294 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A2>, TraversableViewLike<A, Repr, IterableView<A, Repr>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 294 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 294 */       return TraversableViewLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/* 294 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisSeq() {
/* 294 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 294 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 294 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 294 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 294 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/* 294 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public Iterable<A> seq() {
/* 294 */       return Iterable$class.seq(this);
/*     */     }
/*     */     
/*     */     public Iterable<A> thisCollection() {
/* 294 */       return IterableLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Iterable<A> toCollection(Object repr) {
/* 294 */       return IterableLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 294 */       IterableLike$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 294 */       return IterableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 294 */       return IterableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/* 294 */       return IterableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 294 */       return IterableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 294 */       return (B)IterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 294 */       return (B)IterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<A> toIterable() {
/* 294 */       return IterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> toIterator() {
/* 294 */       return IterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public A head() {
/* 294 */       return (A)IterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<A, Repr>> sliding(int size) {
/* 294 */       return IterableLike$class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> takeRight(int n) {
/* 294 */       return (IterableView<A, Repr>)IterableLike$class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> dropRight(int n) {
/* 294 */       return (IterableView<A, Repr>)IterableLike$class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 294 */       IterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/* 294 */       return IterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<A> toStream() {
/* 294 */       return IterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 294 */       return IterableLike$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 294 */       return IterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public IterableView<A, IterableView<A, Repr>> view(int from, int until) {
/* 294 */       return IterableLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Iterable<B>> genericBuilder() {
/* 294 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Iterable<Iterable<B>> transpose(Function1 asTraversable) {
/* 294 */       return (Iterable<Iterable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> repr() {
/* 294 */       return (IterableView<A, Repr>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 294 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParIterable<A>> parCombiner() {
/* 294 */       return TraversableLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 294 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 294 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 294 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> filterNot(Function1 p) {
/* 294 */       return (IterableView<A, Repr>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 294 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public Option<A> headOption() {
/* 294 */       return TraversableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> tail() {
/* 294 */       return (IterableView<A, Repr>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/* 294 */       return (A)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<A> lastOption() {
/* 294 */       return TraversableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> sliceWithKnownDelta(int from, int until, int delta) {
/* 294 */       return (IterableView<A, Repr>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public IterableView<A, Repr> sliceWithKnownBound(int from, int until) {
/* 294 */       return (IterableView<A, Repr>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<A, Repr>> tails() {
/* 294 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<IterableView<A, Repr>> inits() {
/* 294 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> toTraversable() {
/* 294 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 294 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public ParIterable<A> par() {
/* 294 */       return (ParIterable<A>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<A> reversed() {
/* 294 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 294 */       return TraversableOnce$class.size(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 294 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 294 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 294 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 294 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 294 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 294 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 294 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 294 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 294 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 294 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 294 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 294 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 294 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 294 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 294 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> A min(Ordering cmp) {
/* 294 */       return (A)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A max(Ordering cmp) {
/* 294 */       return (A)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 294 */       return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 294 */       return (A)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 294 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 294 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 294 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 294 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 294 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 294 */       return TraversableOnce$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toIndexedSeq() {
/* 294 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 294 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 294 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<A> toVector() {
/* 294 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 294 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 294 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 294 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 294 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public IterableLike$$anon$1(IterableLike $outer) {
/* 294 */       GenTraversableOnce$class.$init$(this);
/* 294 */       TraversableOnce$class.$init$(this);
/* 294 */       Parallelizable$class.$init$(this);
/* 294 */       TraversableLike$class.$init$(this);
/* 294 */       GenericTraversableTemplate.class.$init$(this);
/* 294 */       GenTraversable$class.$init$(this);
/* 294 */       Traversable$class.$init$(this);
/* 294 */       GenIterable$class.$init$(this);
/* 294 */       IterableLike$class.$init$(this);
/* 294 */       Iterable$class.$init$(this);
/* 294 */       ViewMkString$class.$init$(this);
/* 294 */       GenTraversableViewLike$class.$init$(this);
/* 294 */       TraversableViewLike$class.$init$(this);
/* 294 */       GenIterableViewLike$class.$init$(this);
/* 294 */       IterableViewLike$class.$init$(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/* 295 */       synchronized (this) {
/* 295 */         if (!this.bitmap$0) {
/* 295 */           this.underlying = (Repr)this.$outer.repr();
/* 295 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/IterableLike$$anon$1}} */
/* 295 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Repr underlying() {
/* 295 */       return this.bitmap$0 ? this.underlying : (Repr)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 296 */       return this.$outer.iterator();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\IterableLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */