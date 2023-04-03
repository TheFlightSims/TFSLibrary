/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.Sorted;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ObjectRef;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005udaB\001\003!\003\r\ta\002\002\016'>\024H/\0323NCBd\025n[3\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\026\t!)\002fH\n\005\001%iQ\006\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032\004BAD\t\024=5\tqB\003\002\021\005\0059q-\0328fe&\034\027B\001\n\020\005\031\031vN\035;fIB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\tQ\021$\003\002\033\t\t9aj\034;iS:<\007C\001\006\035\023\tiBAA\002B]f\004\"\001F\020\005\r\001\002AQ1\001\"\005\021!\006.[:\022\005a\021#cA\022&U\031!A\005\001\001#\0051a$/\0324j]\026lWM\034;?!\0251\003aE\024\037\033\005\021\001C\001\013)\t\031I\003\001\"b\001/\t\t!\t\005\003'WM9\023B\001\027\003\005%\031vN\035;fI6\013\007\017E\003']M9c$\003\0020\005\t9Q*\0319MS.,\007\"B\031\001\t\003\021\024A\002\023j]&$H\005F\0014!\tQA'\003\0026\t\t!QK\\5u\021\0259\004\001\"\0019\003!1\027N]:u\027\026LX#A\n\t\013i\002A\021\001\035\002\0171\f7\017^&fs\")A\b\001D\002{\005AqN\0353fe&tw-F\001?!\ryti\005\b\003\001\026s!!\021#\016\003\tS!a\021\004\002\rq\022xn\034;?\023\005)\021B\001$\005\003\035\001\030mY6bO\026L!\001S%\003\021=\023H-\032:j]\036T!A\022\003\t\013-\003a\021\001'\002\023I\fgnZ3J[BdGc\001\020N%\")aJ\023a\001\037\006!aM]8n!\rQ\001kE\005\003#\022\021aa\0249uS>t\007\"B*K\001\004y\025!B;oi&d\007\"B+\001\t\0032\026AB6fsN+G/F\001X!\r1\003lE\005\0033\n\021\021bU8si\026$7+\032;\007\tm\003\001\002\030\002\024\t\0264\027-\0367u\027\026L8k\034:uK\022\034V\r^\n\0045v;\006C\0010`\033\005\001\021B\0011/\0055!UMZ1vYR\\U-_*fi\")!M\027C\001G\0061A(\0338jiz\"\022\001\032\t\003=jCQ\001\020.\005\004uBQa\032.\005B!\fQ\001\n9mkN$\"aV5\t\013)4\007\031A\n\002\t\025dW-\034\005\006Yj#\t%\\\001\007I5Lg.^:\025\005]s\007\"\0026l\001\004\031\002\"B&[\t\003\002HcA,re\")aj\034a\001\037\")1k\034a\001\037\")A\017\001C!k\0069Q\017\0353bi\026$WC\001<z)\r9HP \t\005M-\032\002\020\005\002\025s\022)!p\035b\001w\n\021!)M\t\003OmAQ!`:A\002M\t1a[3z\021\025y8\0171\001y\003\0251\030\r\\;f\021\0319\007A\"\001\002\004U!\021QAA\006)\021\t9!!\004\021\013\031Z3#!\003\021\007Q\tY\001\002\004{\003\003\021\ra\037\005\t\003\037\t\t\0011\001\002\022\005\0211N\036\t\007\025\005M1#!\003\n\007\005UAA\001\004UkBdWM\r\005\007O\002!\t%!\007\026\t\005m\021\021\005\013\t\003;\t\031#!\013\002.A)aeK\n\002 A\031A#!\t\005\ri\f9B1\001|\021!\t)#a\006A\002\005\035\022!B3mK6\f\004C\002\006\002\024M\ty\002\003\005\002,\005]\001\031AA\024\003\025)G.Z73\021!\ty#a\006A\002\005E\022!B3mK6\034\b#\002\006\0024\005\035\022bAA\033\t\tQAH]3qK\006$X\r\032 \t\017\005e\002\001\"\021\002<\005Qa-\0337uKJ\\U-_:\025\007)\ni\004\003\005\002@\005]\002\031AA!\003\005\001\bC\002\006\002DM\t9%C\002\002F\021\021\021BR;oGRLwN\\\031\021\007)\tI%C\002\002L\021\021qAQ8pY\026\fg\016C\004\002P\001!\t%!\025\002\0235\f\007OV1mk\026\034X\003BA*\0033\"B!!\026\002^A)aeK\n\002XA\031A#!\027\005\017\005m\023Q\nb\001/\t\t1\t\003\005\002`\0055\003\031AA1\003\0051\007C\002\006\002D\035\n9\006C\004\002f\001!\t%a\032\002\025\021\002H.^:%a2,8/\006\003\002j\005=D\003BA6\003c\002RAJ\026\024\003[\0022\001FA8\t\031Q\0301\rb\001w\"A\0211OA2\001\004\t)(\001\002ygB)a%a\036\002|%\031\021\021\020\002\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\t\007\025\005M1#!\034")
/*    */ public interface SortedMapLike<A, B, This extends SortedMapLike<A, B, This> & SortedMap<A, B>> extends Sorted<A, This>, MapLike<A, B, This> {
/*    */   A firstKey();
/*    */   
/*    */   A lastKey();
/*    */   
/*    */   Ordering<A> ordering();
/*    */   
/*    */   This rangeImpl(Option<A> paramOption1, Option<A> paramOption2);
/*    */   
/*    */   SortedSet<A> keySet();
/*    */   
/*    */   <B1> SortedMap<A, B1> updated(A paramA, B1 paramB1);
/*    */   
/*    */   <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*    */   
/*    */   <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*    */   
/*    */   SortedMap<A, B> filterKeys(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <C> SortedMap<A, C> mapValues(Function1<B, C> paramFunction1);
/*    */   
/*    */   <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*    */   
/*    */   public class DefaultKeySortedSet extends MapLike<A, B, This>.DefaultKeySet implements SortedSet<A> {
/*    */     public SortedSet<A> empty() {
/* 37 */       return SortedSet$class.empty(this);
/*    */     }
/*    */     
/*    */     public boolean scala$collection$SortedSetLike$$super$subsetOf(GenSet that) {
/* 37 */       return GenSetLike$class.subsetOf(this, that);
/*    */     }
/*    */     
/*    */     public SortedSet<A> keySet() {
/* 37 */       return SortedSetLike$class.keySet(this);
/*    */     }
/*    */     
/*    */     public A firstKey() {
/* 37 */       return (A)SortedSetLike$class.firstKey(this);
/*    */     }
/*    */     
/*    */     public A lastKey() {
/* 37 */       return (A)SortedSetLike$class.lastKey(this);
/*    */     }
/*    */     
/*    */     public SortedSet<A> from(Object from) {
/* 37 */       return SortedSetLike$class.from(this, from);
/*    */     }
/*    */     
/*    */     public SortedSet<A> until(Object until) {
/* 37 */       return SortedSetLike$class.until(this, until);
/*    */     }
/*    */     
/*    */     public SortedSet<A> range(Object from, Object until) {
/* 37 */       return SortedSetLike$class.range(this, from, until);
/*    */     }
/*    */     
/*    */     public boolean subsetOf(GenSet that) {
/* 37 */       return SortedSetLike$class.subsetOf(this, that);
/*    */     }
/*    */     
/*    */     public int compare(Object k0, Object k1) {
/* 37 */       return Sorted.class.compare(this, k0, k1);
/*    */     }
/*    */     
/*    */     public SortedSet<A> to(Object to) {
/* 37 */       return (SortedSet<A>)Sorted.class.to(this, to);
/*    */     }
/*    */     
/*    */     public boolean hasAll(Iterator j) {
/* 37 */       return Sorted.class.hasAll(this, j);
/*    */     }
/*    */     
/*    */     public DefaultKeySortedSet(SortedMapLike<A, B, This> $outer) {
/* 37 */       super($outer);
/* 37 */       Sorted.class.$init$(this);
/* 37 */       SortedSetLike$class.$init$(this);
/* 37 */       SortedSet$class.$init$(this);
/*    */     }
/*    */     
/*    */     public Ordering<A> ordering() {
/* 38 */       return scala$collection$SortedMapLike$DefaultKeySortedSet$$$outer().ordering();
/*    */     }
/*    */     
/*    */     public SortedSet<A> $plus(Object elem) {
/* 39 */       return (SortedSet<A>)SortedSet$.MODULE$.apply((Seq)Nil$.MODULE$, ordering()).$plus$plus(this).$plus(elem);
/*    */     }
/*    */     
/*    */     public SortedSet<A> $minus(Object elem) {
/* 40 */       return (SortedSet<A>)SortedSet$.MODULE$.apply((Seq)Nil$.MODULE$, ordering()).$plus$plus(this).$minus(elem);
/*    */     }
/*    */     
/*    */     public SortedSet<A> rangeImpl(Option from, Option until) {
/* 42 */       SortedMap<A, B, This> map = (SortedMap<A, B, This>)scala$collection$SortedMapLike$DefaultKeySortedSet$$$outer().rangeImpl(from, until);
/* 43 */       return new DefaultKeySortedSet((SortedMapLike<A, B, This>)map);
/*    */     }
/*    */   }
/*    */   
/*    */   public class SortedMapLike$$anonfun$$plus$1 extends AbstractFunction1<Tuple2<A, B1>, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final ObjectRef m$1;
/*    */     
/*    */     public final void apply(Tuple2 e) {
/* 72 */       this.m$1.elem = ((SortedMap)this.m$1.elem).$plus(e);
/*    */     }
/*    */     
/*    */     public SortedMapLike$$anonfun$$plus$1(SortedMapLike $outer, ObjectRef m$1) {}
/*    */   }
/*    */   
/*    */   public class SortedMapLike$$anon$1 extends MapLike<A, B, This>.FilteredKeys implements SortedMap.Default<A, B> {
/*    */     private final Function1 p$1;
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus(Tuple2 kv) {
/* 76 */       return SortedMap.Default$class.$plus(this, kv);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> $minus(Object key) {
/* 76 */       return SortedMap.Default$class.$minus(this, key);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> empty() {
/* 76 */       return SortedMap$class.empty(this);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder() {
/* 76 */       return SortedMap$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public A firstKey() {
/* 76 */       return (A)SortedMapLike$class.firstKey(this);
/*    */     }
/*    */     
/*    */     public A lastKey() {
/* 76 */       return (A)SortedMapLike$class.lastKey(this);
/*    */     }
/*    */     
/*    */     public SortedSet<A> keySet() {
/* 76 */       return SortedMapLike$class.keySet(this);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> updated(Object key, Object value) {
/* 76 */       return SortedMapLike$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 76 */       return SortedMapLike$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> filterKeys(Function1 p) {
/* 76 */       return SortedMapLike$class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <C> SortedMap<A, C> mapValues(Function1 f) {
/* 76 */       return SortedMapLike$class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 76 */       return SortedMapLike$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public int compare(Object k0, Object k1) {
/* 76 */       return Sorted.class.compare(this, k0, k1);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> from(Object from) {
/* 76 */       return (SortedMap<A, B>)Sorted.class.from(this, from);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> until(Object until) {
/* 76 */       return (SortedMap<A, B>)Sorted.class.until(this, until);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> range(Object from, Object until) {
/* 76 */       return (SortedMap<A, B>)Sorted.class.range(this, from, until);
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> to(Object to) {
/* 76 */       return (SortedMap<A, B>)Sorted.class.to(this, to);
/*    */     }
/*    */     
/*    */     public boolean hasAll(Iterator j) {
/* 76 */       return Sorted.class.hasAll(this, j);
/*    */     }
/*    */     
/*    */     public SortedMapLike$$anon$1(SortedMapLike<A, B, This> $outer, Function1<A, Object> p$1) {
/* 76 */       super($outer, p$1);
/* 76 */       Sorted.class.$init$(this);
/* 76 */       SortedMapLike$class.$init$(this);
/* 76 */       SortedMap$class.$init$(this);
/* 76 */       SortedMap.Default$class.$init$(this);
/*    */     }
/*    */     
/*    */     public Ordering<A> ordering() {
/* 77 */       return this.$outer.ordering();
/*    */     }
/*    */     
/*    */     public SortedMap<A, B> rangeImpl(Option from, Option until) {
/* 78 */       return this.$outer.rangeImpl(from, until).filterKeys(this.p$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class SortedMapLike$$anon$2 extends MapLike<A, B, This>.MappedValues<C> implements SortedMap.Default<A, C> {
/*    */     private final Function1 f$1;
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus(Tuple2 kv) {
/* 81 */       return SortedMap.Default$class.$plus(this, kv);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> $minus(Object key) {
/* 81 */       return SortedMap.Default$class.$minus(this, key);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> empty() {
/* 81 */       return SortedMap$class.empty(this);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, C>, SortedMap<A, C>> newBuilder() {
/* 81 */       return SortedMap$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public A firstKey() {
/* 81 */       return (A)SortedMapLike$class.firstKey(this);
/*    */     }
/*    */     
/*    */     public A lastKey() {
/* 81 */       return (A)SortedMapLike$class.lastKey(this);
/*    */     }
/*    */     
/*    */     public SortedSet<A> keySet() {
/* 81 */       return SortedMapLike$class.keySet(this);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> updated(Object key, Object value) {
/* 81 */       return SortedMapLike$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 81 */       return SortedMapLike$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> filterKeys(Function1 p) {
/* 81 */       return SortedMapLike$class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <C> SortedMap<A, C> mapValues(Function1 f) {
/* 81 */       return SortedMapLike$class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 81 */       return SortedMapLike$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public int compare(Object k0, Object k1) {
/* 81 */       return Sorted.class.compare(this, k0, k1);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> from(Object from) {
/* 81 */       return (SortedMap<A, C>)Sorted.class.from(this, from);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> until(Object until) {
/* 81 */       return (SortedMap<A, C>)Sorted.class.until(this, until);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> range(Object from, Object until) {
/* 81 */       return (SortedMap<A, C>)Sorted.class.range(this, from, until);
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> to(Object to) {
/* 81 */       return (SortedMap<A, C>)Sorted.class.to(this, to);
/*    */     }
/*    */     
/*    */     public boolean hasAll(Iterator j) {
/* 81 */       return Sorted.class.hasAll(this, j);
/*    */     }
/*    */     
/*    */     public SortedMapLike$$anon$2(SortedMapLike<A, B, This> $outer, Function1<B, C> f$1) {
/* 81 */       super($outer, f$1);
/* 81 */       Sorted.class.$init$(this);
/* 81 */       SortedMapLike$class.$init$(this);
/* 81 */       SortedMap$class.$init$(this);
/* 81 */       SortedMap.Default$class.$init$(this);
/*    */     }
/*    */     
/*    */     public Ordering<A> ordering() {
/* 82 */       return this.$outer.ordering();
/*    */     }
/*    */     
/*    */     public SortedMap<A, C> rangeImpl(Option from, Option until) {
/* 83 */       return this.$outer.rangeImpl(from, until).mapValues(this.f$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class SortedMapLike$$anonfun$$plus$plus$1 extends AbstractFunction2<SortedMap<A, B1>, Tuple2<A, B1>, SortedMap<A, B1>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final SortedMap<A, B1> apply(SortedMap x$2, Tuple2 x$3) {
/* 92 */       return x$2.$plus(x$3);
/*    */     }
/*    */     
/*    */     public SortedMapLike$$anonfun$$plus$plus$1(SortedMapLike $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedMapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */