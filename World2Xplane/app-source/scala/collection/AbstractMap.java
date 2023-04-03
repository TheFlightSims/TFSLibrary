/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0312a!\001\002\002\002\0211!aC!cgR\024\030m\031;NCBT!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b+\r9!#H\n\004\001!y\002cA\005\013\0315\t!!\003\002\f\005\t\001\022IY:ue\006\034G/\023;fe\006\024G.\032\t\005\0339\001B$D\001\005\023\tyAA\001\004UkBdWM\r\t\003#Ia\001\001B\003\024\001\t\007QCA\001B\007\001\t\"AF\r\021\00559\022B\001\r\005\005\035qu\016\0365j]\036\004\"!\004\016\n\005m!!aA!osB\021\021#\b\003\007=\001!)\031A\013\003\003\t\003B!\003\021\0219%\021\021E\001\002\004\033\006\004\b\"B\022\001\t\003!\023A\002\037j]&$h\bF\001&!\021I\001\001\005\017")
/*    */ public abstract class AbstractMap<A, B> extends AbstractIterable<Tuple2<A, B>> implements Map<A, B> {
/*    */   public Map<A, B> empty() {
/* 58 */     return Map$class.empty(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> seq() {
/* 58 */     return Map$class.seq(this);
/*    */   }
/*    */   
/*    */   public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
/* 58 */     return MapLike$class.newBuilder(this);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 58 */     return MapLike$class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public <B1> B1 getOrElse(Object key, Function0 default) {
/* 58 */     return (B1)MapLike$class.getOrElse(this, key, default);
/*    */   }
/*    */   
/*    */   public B apply(Object key) {
/* 58 */     return (B)MapLike$class.apply(this, key);
/*    */   }
/*    */   
/*    */   public boolean contains(Object key) {
/* 58 */     return MapLike$class.contains(this, key);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(Object key) {
/* 58 */     return MapLike$class.isDefinedAt(this, key);
/*    */   }
/*    */   
/*    */   public Set<A> keySet() {
/* 58 */     return MapLike$class.keySet(this);
/*    */   }
/*    */   
/*    */   public Iterator<A> keysIterator() {
/* 58 */     return MapLike$class.keysIterator(this);
/*    */   }
/*    */   
/*    */   public Iterable<A> keys() {
/* 58 */     return MapLike$class.keys(this);
/*    */   }
/*    */   
/*    */   public Iterable<B> values() {
/* 58 */     return MapLike$class.values(this);
/*    */   }
/*    */   
/*    */   public Iterator<B> valuesIterator() {
/* 58 */     return MapLike$class.valuesIterator(this);
/*    */   }
/*    */   
/*    */   public B default(Object key) {
/* 58 */     return (B)MapLike$class.default(this, key);
/*    */   }
/*    */   
/*    */   public Map<A, B> filterKeys(Function1 p) {
/* 58 */     return MapLike$class.filterKeys(this, p);
/*    */   }
/*    */   
/*    */   public <C> Map<A, C> mapValues(Function1 f) {
/* 58 */     return MapLike$class.mapValues(this, f);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> updated(Object key, Object value) {
/* 58 */     return MapLike$class.updated(this, key, value);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> $plus(Tuple2 kv1, Tuple2 kv2, Seq kvs) {
/* 58 */     return MapLike$class.$plus(this, kv1, kv2, kvs);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 58 */     return MapLike$class.$plus$plus(this, xs);
/*    */   }
/*    */   
/*    */   public Map<A, B> filterNot(Function1 p) {
/* 58 */     return MapLike$class.filterNot(this, p);
/*    */   }
/*    */   
/*    */   public Seq<Tuple2<A, B>> toSeq() {
/* 58 */     return MapLike$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public <C> Buffer<C> toBuffer() {
/* 58 */     return MapLike$class.toBuffer(this);
/*    */   }
/*    */   
/*    */   public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 58 */     return MapLike$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 58 */     return MapLike$class.addString(this, b, start, sep, end);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 58 */     return MapLike$class.stringPrefix(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return MapLike$class.toString(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> $minus(Object elem1, Object elem2, Seq elems) {
/* 58 */     return (Map<A, B>)Subtractable.class.$minus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Map<A, B> $minus$minus(GenTraversableOnce xs) {
/* 58 */     return (Map<A, B>)Subtractable.class.$minus$minus(this, xs);
/*    */   }
/*    */   
/*    */   public <A1 extends A, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 58 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<A, C> andThen(Function1 k) {
/* 58 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<A, Option<B>> lift() {
/* 58 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1 extends A, B1> B1 applyOrElse(Object x, Function1 default) {
/* 58 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<A, Object> runWith(Function1 action) {
/* 58 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 58 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 58 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 58 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 58 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 58 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 58 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 58 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 58 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 58 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 58 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 58 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 58 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 58 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 58 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 58 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 58 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 58 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 58 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 58 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 58 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 58 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 58 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 58 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 58 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, B> compose(Function1 g) {
/* 58 */     return Function1.class.compose((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 58 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 58 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 58 */     return GenMapLike$class.hashCode(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 58 */     return GenMapLike$class.equals(this, that);
/*    */   }
/*    */   
/*    */   public AbstractMap() {
/* 58 */     GenMapLike$class.$init$(this);
/* 58 */     Function1.class.$init$((Function1)this);
/* 58 */     PartialFunction.class.$init$(this);
/* 58 */     Subtractable.class.$init$(this);
/* 58 */     MapLike$class.$init$(this);
/* 58 */     Map$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */