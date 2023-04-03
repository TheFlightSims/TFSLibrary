/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Cloneable;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
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
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.SeqView;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericOrderedCompanion;
/*     */ import scala.collection.generic.GenericOrderedTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.PartialOrdering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t]d\001B\001\003\001%\021Q\002\025:j_JLG/_)vKV,'BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\t\024\023\001Y1DH\023+[A\032\004c\001\007\016\0375\t!!\003\002\017\005\t\001\022IY:ue\006\034G/\023;fe\006\024G.\032\t\003!Ea\001\001B\003\023\001\t\0071CA\001B#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007CA\013\032\023\tQbAA\002B]f\0042\001\004\017\020\023\ti\"A\001\005Ji\026\024\030M\0317f!\021y\"e\004\023\016\003\001R!!\t\003\002\017\035,g.\032:jG&\0211\005\t\002\"\017\026tWM]5d\037J$WM]3e)J\fg/\032:tC\ndW\rV3na2\fG/\032\t\003\031\001\001BAJ\024\020S5\tA!\003\002)\t\ta\021\n^3sC\ndW\rT5lKB\031A\002A\b\021\007}Ys\"\003\002-A\tAqI]8xC\ndW\r\005\003\r]=I\023BA\030\003\005\035\021U/\0337eKJ\004\"!F\031\n\005I2!\001D*fe&\fG.\033>bE2,\007CA\0135\023\t)dAA\005DY>tW-\0312mK\"Aq\007\001BC\002\023\r\001(A\002pe\022,\022!\017\t\004u\t{aBA\036A\035\tat(D\001>\025\tq\004\"\001\004=e>|GOP\005\002\017%\021\021IB\001\ba\006\0347.Y4f\023\t\031EI\001\005Pe\022,'/\0338h\025\t\te\001\003\005G\001\t\005\t\025!\003:\003\021y'\017\032\021\t\013!\003A\021A%\002\rqJg.\033;?)\005QECA\025L\021\0259t\tq\001:\r\021i\005\001\002(\003)I+7/\033>bE2,\027I\035:bs\006\0337-Z:t+\tyEkE\002M!V\0032\001D)T\023\t\021&AA\006BEN$(/Y2u'\026\f\bC\001\tU\t\025\021BJ1\001\024!\raakU\005\003/\n\021aBU3tSj\f'\r\\3BeJ\f\027\020C\003I\031\022\005\021\fF\001[!\rYFjU\007\002\001!)Q\f\024C\001=\0069\001oX:ju\026\004T#A0\021\005U\001\027BA1\007\005\rIe\016\036\005\006G2#\t\001Z\001\fa~\033\030N_31?\022*\027\017\006\002fQB\021QCZ\005\003O\032\021A!\0268ji\")\021N\031a\001?\006\t1\017C\003l\031\022\005A.A\004q?\006\024(/Y=\026\0035\0042!\0068q\023\tygAA\003BeJ\f\027\020\005\002\026c&\021!O\002\002\007\003:L(+\0324\t\013QdE\021A;\002\031A|VM\\:ve\026\034\026N_3\025\005\0254\b\"B<t\001\004y\026!\0018\t\013edE\021\001>\002\rA|6o^1q)\r)70 \005\006yb\004\raX\001\002C\")a\020\037a\001?\006\t!\r\003\005\002\002\001\001K\021KA\002\003)qWm\036\"vS2$WM]\013\002S!I\021q\001\001C\002\023%\021\021B\001\007e\026\034\030M\035:\026\005\005-\001cA.M\037!A\021q\002\001!\002\023\tY!A\004sKN\f'O\035\021\t\r\005M\001\001\"\001_\003\031aWM\\4uQ\"1\021q\003\001\005By\013Aa]5{K\"9\0211\004\001\005B\005u\021aB5t\0136\004H/_\013\003\003?\0012!FA\021\023\r\t\031C\002\002\b\005>|G.Z1o\021\035\t9\003\001C!\003\007\tAA]3qe\"9\0211\006\001\005\002\0055\022A\002:fgVdG\017F\001*\021\035\t\t\004\001C!\003g\t\001c\034:eKJ,GmQ8na\006t\027n\0348\026\005\005Ubb\001\007\0028\0359\021\021\b\002\t\002\005m\022!\004)sS>\024\030\016^=Rk\026,X\rE\002\r\003{1a!\001\002\t\002\005}2#BA\037\003\003\002\004\003B\020\002D\021J1!!\022!\005ey%\017Z3sK\022$&/\031<feN\f'\r\\3GC\016$xN]=\t\017!\013i\004\"\001\002JQ\021\0211\b\005\t\003\003\ti\004\"\001\002NU!\021qJA+)\021\t\t&a\026\021\t1\001\0211\013\t\004!\005UCA\002\n\002L\t\0071\003C\0048\003\027\002\035!!\027\021\ti\022\0251\013\005\t\003;\ni\004b\001\002`\005a1-\0318Ck&dGM\022:p[V!\021\021MA<)\021\t\031'a\037\021\023}\t)'!\033\002v\005e\024bAA4A\ta1)\0318Ck&dGM\022:p[B!\0211NA7\033\t\ti$\003\003\002p\005E$\001B\"pY2L1!a\035!\005]9UM\\3sS\016|%\017Z3sK\022\034u.\0349b]&|g\016E\002\021\003o\"aAEA.\005\004\031\002\003\002\007\001\003kBqaNA.\001\b\ti\b\005\003;\005\006U\004BCAA\003{\t\t\021\"\003\002\004\006Y!/Z1e%\026\034x\016\034<f)\t\t)\t\005\003\002\b\006EUBAAE\025\021\tY)!$\002\t1\fgn\032\006\003\003\037\013AA[1wC&!\0211SAE\005\031y%M[3di\"9\021q\023\001\005\n\005e\025a\001;p\003R\031q\"a'\t\017\005u\025Q\023a\001a\006\t\001\020C\004\002\"\002!\t\"a)\002\013\031L\0070\0269\025\013\025\f)+!+\t\017\005\035\026q\024a\001[\006\021\021m\035\005\b\003W\013y\n1\001`\003\005i\007bBAX\001\021E\021\021W\001\bM&DHi\\<o)\035)\0271WA[\003oCq!a*\002.\002\007Q\016C\004\002,\0065\006\031A0\t\r]\fi\0131\001`\021\035\tY\f\001C\001\003{\013\001\002\n9mkN$S-\035\013\0047\006}\006bBAa\003s\003\raD\001\005K2,W\016C\004\002F\002!\t!a2\002\025\021\002H.^:%a2,8\017F\002*\003\023D\001\"a3\002D\002\007\021QZ\001\003qN\004BAJAh\037%\031\021\021\033\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\005\b\003+\004A\021AAl\003\035)g.];fk\026$2!ZAm\021!\tY.a5A\002\005u\027!B3mK6\034\b\003B\013\002`>I1!!9\007\005)a$/\0329fCR,GM\020\005\b\003K\004A\021AAt\003\035!W-];fk\026$\022a\004\005\b\003W\004A\021AAw\003)!W-];fk\026\fE\016\\\013\007\003_\0249!a=\025\t\005E\030q\037\t\004!\005MHaBA{\003S\024\ra\005\002\005)\"\fG\017\003\005\002z\006%\b9AA~\003\t\021g\r\r\003\002~\n\005\001#C\020\002f\005}(QAAy!\r\001\"\021\001\003\f\005\007\t90!A\001\002\013\0051CA\002`IE\0022\001\005B\004\t!\021I!!;C\002\t-!AA!2#\ty\001\004C\004\003\020\001!\tA!\005\002\0075\f\0070F\001\020Q!\021iA!\006\003\034\t}\001cA\013\003\030%\031!\021\004\004\003\025\021,\007O]3dCR,G-\t\002\003\036\005\031Rk]3!A\",\027\r\0321!S:\034H/Z1e]\005\022!\021E\001\006e9Jd\006\r\005\b\005K\001A\021\tB\t\003\021AW-\0313\t\017\t%\002\001\"\001\003,\005)1\r\\3beR\tQ\rC\004\0030\001!\tE!\r\002\021%$XM]1u_J,\"Aa\r\021\t\031\022)dD\005\004\005o!!\001C%uKJ\fGo\034:\t\017\tm\002\001\"\001\002\004\0059!/\032<feN,\007b\002B \001\021\005!\021G\001\020e\0264XM]:f\023R,'/\031;pe\"9!1\t\001\005B\t\025\023\001\0035bg\"\034u\016Z3\025\003}CqA!\023\001\t\003\021Y%A\004u_F+X-^3\026\005\t5\003\003\002\007\003P=I1A!\025\003\005\025\tV/Z;f\021\035\021)\006\001C!\005/\n\001\002^8TiJLgn\032\013\003\0053\002BAa\027\003b9\031QC!\030\n\007\t}c!\001\004Qe\026$WMZ\005\005\005G\022)G\001\004TiJLgn\032\006\004\005?2\001b\002B5\001\021\005#1N\001\007i>d\025n\035;\026\005\t5\004\003\002\036\003p=I1A!\035E\005\021a\025n\035;\t\017\tU\004\001\"\021\002.\005)1\r\\8oK\002")
/*     */ public class PriorityQueue<A> extends AbstractIterable<A> implements Iterable<A>, GenericOrderedTraversableTemplate<A, PriorityQueue>, IterableLike<A, PriorityQueue<A>>, Growable<A>, Builder<A, PriorityQueue<A>>, Serializable, Cloneable {
/*     */   private final Ordering<A> ord;
/*     */   
/*     */   private final ResizableArrayAccess<A> scala$collection$mutable$PriorityQueue$$resarr;
/*     */   
/*     */   public void sizeHint(int size) {
/*  34 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  34 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  34 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  34 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<A, NewTo> mapResult(Function1 f) {
/*  34 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  34 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<A> $plus$plus$eq(TraversableOnce xs) {
/*  34 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, PriorityQueue<B>> genericOrderedBuilder(Ordering ord) {
/*  34 */     return GenericOrderedTraversableTemplate.class.genericOrderedBuilder(this, ord);
/*     */   }
/*     */   
/*     */   public Ordering<A> ord() {
/*  34 */     return this.ord;
/*     */   }
/*     */   
/*     */   public PriorityQueue(Ordering<A> ord) {
/*  34 */     GenericOrderedTraversableTemplate.class.$init$(this);
/*  34 */     Growable.class.$init$(this);
/*  34 */     Builder$class.$init$(this);
/*  56 */     this.scala$collection$mutable$PriorityQueue$$resarr = new ResizableArrayAccess<A>(this);
/*  58 */     scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
/*     */   }
/*     */   
/*     */   public class ResizableArrayAccess<A> extends AbstractSeq<A> implements ResizableArray<A> {
/*     */     private Object[] array;
/*     */     
/*     */     private int size0;
/*     */     
/*     */     public Object[] array() {
/*     */       return this.array;
/*     */     }
/*     */     
/*     */     public void array_$eq(Object[] x$1) {
/*     */       this.array = x$1;
/*     */     }
/*     */     
/*     */     public int size0() {
/*     */       return this.size0;
/*     */     }
/*     */     
/*     */     public void size0_$eq(int x$1) {
/*     */       this.size0 = x$1;
/*     */     }
/*     */     
/*     */     public GenericCompanion<ResizableArray> companion() {
/*     */       return ResizableArray$class.companion(this);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/*     */       return ResizableArray$class.initialSize(this);
/*     */     }
/*     */     
/*     */     public int length() {
/*     */       return ResizableArray$class.length(this);
/*     */     }
/*     */     
/*     */     public A apply(int idx) {
/*     */       return (A)ResizableArray$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public void update(int idx, Object elem) {
/*     */       ResizableArray$class.update(this, idx, elem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*     */       ResizableArray$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*     */       ResizableArray$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public void reduceToSize(int sz) {
/*     */       ResizableArray$class.reduceToSize(this, sz);
/*     */     }
/*     */     
/*     */     public void ensureSize(int n) {
/*     */       ResizableArray$class.ensureSize(this, n);
/*     */     }
/*     */     
/*     */     public void swap(int a, int b) {
/*     */       ResizableArray$class.swap(this, a, b);
/*     */     }
/*     */     
/*     */     public void copy(int m, int n, int len) {
/*     */       ResizableArray$class.copy(this, m, n, len);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/*     */       return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/*     */       return IterableLike.class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/*     */       return IterableLike.class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$head() {
/*     */       return IterableLike.class.head(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/*     */       return TraversableLike.class.tail(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$last() {
/*     */       return TraversableLike.class.last(this);
/*     */     }
/*     */     
/*     */     public Object scala$collection$IndexedSeqOptimized$$super$init() {
/*     */       return TraversableLike.class.init(this);
/*     */     }
/*     */     
/*     */     public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/*     */       return IterableLike.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/*     */       return SeqLike.class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*     */       return IndexedSeqOptimized.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*     */       return IndexedSeqOptimized.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*     */       return IndexedSeqOptimized.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/*     */       return IndexedSeqOptimized.class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*     */       return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*     */       return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*     */       return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*     */       return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*     */       return (That)IndexedSeqOptimized.class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*     */       return (That)IndexedSeqOptimized.class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> slice(int from, int until) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public A head() {
/*     */       return (A)IndexedSeqOptimized.class.head(this);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> tail() {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/*     */       return (A)IndexedSeqOptimized.class.last(this);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> init() {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.init(this);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> take(int n) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.take(this, n);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> drop(int n) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> takeRight(int n) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> dropRight(int n) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public Tuple2<ResizableArray<A>, ResizableArray<A>> splitAt(int n) {
/*     */       return IndexedSeqOptimized.class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> takeWhile(Function1 p) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> dropWhile(Function1 p) {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<ResizableArray<A>, ResizableArray<A>> span(Function1 p) {
/*     */       return IndexedSeqOptimized.class.span(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*     */       return IndexedSeqOptimized.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/*     */       return IndexedSeqOptimized.class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/*     */       return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/*     */       return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/*     */       return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public ResizableArray<A> reverse() {
/*     */       return (ResizableArray<A>)IndexedSeqOptimized.class.reverse(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> reverseIterator() {
/*     */       return IndexedSeqOptimized.class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/*     */       return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/*     */       return IndexedSeqOptimized.class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> seq() {
/*     */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> thisCollection() {
/*     */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toCollection(Object repr) {
/*     */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Object view() {
/*     */       return IndexedSeqLike$class.view(this);
/*     */     }
/*     */     
/*     */     public IndexedSeqView<A, ResizableArray<A>> view(int from, int until) {
/*     */       return IndexedSeqLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       return IndexedSeqLike.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/*     */       return IndexedSeqLike.class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/*     */       return IndexedSeqLike.class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public ResizableArrayAccess(PriorityQueue $outer) {
/*     */       IndexedSeqLike.class.$init$(this);
/*     */       IndexedSeq.class.$init$(this);
/*     */       IndexedSeqLike$class.$init$(this);
/*     */       IndexedSeq$class.$init$(this);
/*     */       IndexedSeqOptimized.class.$init$(this);
/*     */       ResizableArray$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int p_size0() {
/*     */       return size0();
/*     */     }
/*     */     
/*     */     public void p_size0_$eq(int s) {
/*     */       size0_$eq(s);
/*     */     }
/*     */     
/*     */     public Object[] p_array() {
/*     */       return array();
/*     */     }
/*     */     
/*     */     public void p_ensureSize(int n) {
/*     */       ResizableArray$class.ensureSize(this, n);
/*     */     }
/*     */     
/*     */     public void p_swap(int a, int b) {
/*     */       ResizableArray$class.swap(this, a, b);
/*     */     }
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> newBuilder() {
/*     */     return new PriorityQueue(ord());
/*     */   }
/*     */   
/*     */   public ResizableArrayAccess<A> scala$collection$mutable$PriorityQueue$$resarr() {
/*     */     return this.scala$collection$mutable$PriorityQueue$$resarr;
/*     */   }
/*     */   
/*     */   public int length() {
/*  59 */     return scala$collection$mutable$PriorityQueue$$resarr().length() - 1;
/*     */   }
/*     */   
/*     */   public int size() {
/*  60 */     return length();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  61 */     return (scala$collection$mutable$PriorityQueue$$resarr().p_size0() < 2);
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> repr() {
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> result() {
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public PriorityQueue$ orderedCompanion() {
/*  66 */     return PriorityQueue$.MODULE$;
/*     */   }
/*     */   
/*     */   public A scala$collection$mutable$PriorityQueue$$toA(Object x) {
/*  68 */     return (A)x;
/*     */   }
/*     */   
/*     */   public void fixUp(Object[] as, int m) {
/*  70 */     int k = m;
/*  71 */     while (k > 1 && ord().mkOrderingOps(scala$collection$mutable$PriorityQueue$$toA(as[k / 2])).$less(scala$collection$mutable$PriorityQueue$$toA(as[k]))) {
/*  72 */       scala$collection$mutable$PriorityQueue$$resarr().p_swap(k, k / 2);
/*  73 */       k /= 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fixDown(Object[] as, int m, int n) {
/*  78 */     int k = m;
/*  79 */     while (n >= 2 * k) {
/*  80 */       int j = 2 * k;
/*  81 */       if (j < n && ord().mkOrderingOps(scala$collection$mutable$PriorityQueue$$toA(as[j])).$less(scala$collection$mutable$PriorityQueue$$toA(as[j + 1])))
/*  82 */         j++; 
/*  83 */       if (ord().mkOrderingOps(scala$collection$mutable$PriorityQueue$$toA(as[k])).$greater$eq(scala$collection$mutable$PriorityQueue$$toA(as[j])))
/*     */         return; 
/*  86 */       Object h = as[k];
/*  87 */       as[k] = as[j];
/*  88 */       as[j] = h;
/*  89 */       k = j;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> $plus$eq(Object elem) {
/* 100 */     scala$collection$mutable$PriorityQueue$$resarr().p_ensureSize(scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
/* 101 */     scala$collection$mutable$PriorityQueue$$resarr().p_array()[scala$collection$mutable$PriorityQueue$$resarr().p_size0()] = elem;
/* 102 */     fixUp(scala$collection$mutable$PriorityQueue$$resarr().p_array(), scala$collection$mutable$PriorityQueue$$resarr().p_size0());
/* 103 */     scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(scala$collection$mutable$PriorityQueue$$resarr().p_size0() + 1);
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> $plus$plus(GenTraversableOnce xs) {
/* 113 */     return (PriorityQueue<A>)clone().$plus$plus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public void enqueue(Seq elems) {
/* 119 */     $plus$plus$eq((TraversableOnce<A>)elems);
/*     */   }
/*     */   
/*     */   public A dequeue() {
/* 128 */     if (scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1) {
/* 129 */       scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
/* 130 */       scala$collection$mutable$PriorityQueue$$resarr().p_swap(1, scala$collection$mutable$PriorityQueue$$resarr().p_size0());
/* 131 */       fixDown(scala$collection$mutable$PriorityQueue$$resarr().p_array(), 1, scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1);
/* 132 */       return scala$collection$mutable$PriorityQueue$$toA(scala$collection$mutable$PriorityQueue$$resarr().p_array()[scala$collection$mutable$PriorityQueue$$resarr().p_size0()]);
/*     */     } 
/* 134 */     throw new NoSuchElementException("no element to remove from heap");
/*     */   }
/*     */   
/*     */   public <A1, That> That dequeueAll(CanBuildFrom bf) {
/* 137 */     Builder b = bf.apply();
/* 138 */     while (nonEmpty())
/* 139 */       b.$plus$eq(dequeue()); 
/* 141 */     return (That)b.result();
/*     */   }
/*     */   
/*     */   public A max() {
/* 150 */     if (scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1)
/* 150 */       return scala$collection$mutable$PriorityQueue$$toA(scala$collection$mutable$PriorityQueue$$resarr().p_array()[1]); 
/* 150 */     throw new NoSuchElementException("queue is empty");
/*     */   }
/*     */   
/*     */   public A head() {
/* 157 */     if (scala$collection$mutable$PriorityQueue$$resarr().p_size0() > 1)
/* 157 */       return scala$collection$mutable$PriorityQueue$$toA(scala$collection$mutable$PriorityQueue$$resarr().p_array()[1]); 
/* 157 */     throw new NoSuchElementException("queue is empty");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 162 */     scala$collection$mutable$PriorityQueue$$resarr().p_size0_$eq(1);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/* 172 */     return (Iterator<A>)new PriorityQueue$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anon$2 extends AbstractIterator<A> {
/* 173 */     private int i = 1;
/*     */     
/*     */     private int i() {
/* 173 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 173 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 174 */       return (i() < this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0());
/*     */     }
/*     */     
/*     */     public A next() {
/* 176 */       Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[i()];
/* 177 */       i_$eq(i() + 1);
/* 178 */       return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anon$2(PriorityQueue $outer) {}
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> reverse() {
/* 196 */     PriorityQueue<A> revq = new PriorityQueue(new PriorityQueue$$anon$1(this));
/* 199 */     Predef$ predef$ = Predef$.MODULE$;
/* 199 */     int i = scala$collection$mutable$PriorityQueue$$resarr().length();
/* 199 */     Range$ range$ = Range$.MODULE$;
/* 199 */     PriorityQueue$$anonfun$reverse$1 priorityQueue$$anonfun$reverse$1 = new PriorityQueue$$anonfun$reverse$1(this, revq);
/*     */     Range range;
/* 199 */     if ((range = new Range(1, i, 1)).validateRangeBoundaries((Function1)priorityQueue$$anonfun$reverse$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 199 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 199 */         int j = i1;
/* 199 */         revq.$plus$eq(scala$collection$mutable$PriorityQueue$$resarr().apply(j));
/* 199 */         i1 += step1;
/*     */       } 
/*     */     } 
/* 200 */     return revq;
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anon$1 implements Ordering<A> {
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*     */       return Ordering.class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*     */       return Ordering.class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*     */       return Ordering.class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*     */       return Ordering.class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*     */       return Ordering.class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*     */       return Ordering.class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public A max(Object x, Object y) {
/*     */       return (A)Ordering.class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public A min(Object x, Object y) {
/*     */       return (A)Ordering.class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<A> reverse() {
/*     */       return Ordering.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*     */       return Ordering.class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<A>.Ops mkOrderingOps(Object lhs) {
/*     */       return Ordering.class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anon$1(PriorityQueue $outer) {
/*     */       PartialOrdering.class.$init$((PartialOrdering)this);
/*     */       Ordering.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/*     */       return this.$outer.ord().compare(y, x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anonfun$reverse$1 extends AbstractFunction1<Object, PriorityQueue<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final PriorityQueue revq$1;
/*     */     
/*     */     public final PriorityQueue<A> apply(int i) {
/*     */       return this.revq$1.$plus$eq(this.$outer.scala$collection$mutable$PriorityQueue$$resarr().apply(i));
/*     */     }
/*     */     
/*     */     public PriorityQueue$$anonfun$reverse$1(PriorityQueue $outer, PriorityQueue revq$1) {}
/*     */   }
/*     */   
/*     */   public Iterator<A> reverseIterator() {
/* 210 */     return (Iterator<A>)new PriorityQueue$$anon$3(this);
/*     */   }
/*     */   
/*     */   public class PriorityQueue$$anon$3 extends AbstractIterator<A> {
/*     */     private int i;
/*     */     
/*     */     public PriorityQueue$$anon$3(PriorityQueue $outer) {
/* 211 */       this.i = $outer.scala$collection$mutable$PriorityQueue$$resarr().p_size0() - 1;
/*     */     }
/*     */     
/*     */     private int i() {
/* 211 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 211 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 212 */       return (i() >= 1);
/*     */     }
/*     */     
/*     */     public A next() {
/* 214 */       Object n = this.$outer.scala$collection$mutable$PriorityQueue$$resarr().p_array()[i()];
/* 215 */       i_$eq(i() - 1);
/* 216 */       return this.$outer.scala$collection$mutable$PriorityQueue$$toA(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 226 */     throw new UnsupportedOperationException("unsuitable as hash key");
/*     */   }
/*     */   
/*     */   public Queue<A> toQueue() {
/* 232 */     return (Queue<A>)(new Queue<A>()).$plus$plus$eq((TraversableOnce<A>)iterator());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 238 */     return toList().mkString("PriorityQueue(", ", ", ")");
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 246 */     return iterator().toList();
/*     */   }
/*     */   
/*     */   public PriorityQueue<A> clone() {
/* 252 */     return (PriorityQueue<A>)(new PriorityQueue(ord())).$plus$plus$eq((TraversableOnce<A>)iterator());
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<PriorityQueue<?>, A, PriorityQueue<A>> canBuildFrom(Ordering<A> paramOrdering) {
/*     */     return PriorityQueue$.MODULE$.canBuildFrom(paramOrdering);
/*     */   }
/*     */   
/*     */   public static <A> PriorityQueue<A> apply(Seq<A> paramSeq, Ordering<A> paramOrdering) {
/*     */     return (PriorityQueue<A>)PriorityQueue$.MODULE$.apply(paramSeq, paramOrdering);
/*     */   }
/*     */   
/*     */   public static <A> PriorityQueue<A> empty(Ordering<A> paramOrdering) {
/*     */     return (PriorityQueue<A>)PriorityQueue$.MODULE$.empty(paramOrdering);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\PriorityQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */