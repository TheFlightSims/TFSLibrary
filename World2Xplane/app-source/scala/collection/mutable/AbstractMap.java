/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.AbstractMap;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0252a!\001\002\002\002\031A!aC!cgR\024\030m\031;NCBT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\007%y1dE\002\001\025u\001Ba\003\007\01655\tA!\003\002\002\tA\021ab\004\007\001\t\025\001\002A1\001\023\005\005\t5\001A\t\003']\001\"\001F\013\016\003\031I!A\006\004\003\0179{G\017[5oOB\021A\003G\005\0033\031\0211!\0218z!\tq1\004B\003\035\001\t\007!CA\001C!\021qr$\004\016\016\003\tI!\001\t\002\003\0075\013\007\017C\003#\001\021\0051%\001\004=S:LGO\020\013\002IA!a\004A\007\033\001")
/*    */ public abstract class AbstractMap<A, B> extends AbstractMap<A, B> implements Map<A, B> {
/*    */   public Map<A, B> empty() {
/* 91 */     return Map$class.empty(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> seq() {
/* 91 */     return Map$class.seq(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> withDefault(Function1 d) {
/* 91 */     return Map$class.withDefault(this, d);
/*    */   }
/*    */   
/*    */   public Map<A, B> withDefaultValue(Object d) {
/* 91 */     return Map$class.withDefaultValue(this, d);
/*    */   }
/*    */   
/*    */   public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
/* 91 */     return MapLike$class.newBuilder(this);
/*    */   }
/*    */   
/*    */   public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 91 */     return MapLike$class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public Option<B> put(Object key, Object value) {
/* 91 */     return MapLike$class.put(this, key, value);
/*    */   }
/*    */   
/*    */   public void update(Object key, Object value) {
/* 91 */     MapLike$class.update(this, key, value);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> updated(Object key, Object value) {
/* 91 */     return MapLike$class.updated(this, key, value);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 91 */     return MapLike$class.$plus(this, kv);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 91 */     return MapLike$class.$plus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 91 */     return MapLike$class.$plus$plus(this, xs);
/*    */   }
/*    */   
/*    */   public Option<B> remove(Object key) {
/* 91 */     return MapLike$class.remove(this, key);
/*    */   }
/*    */   
/*    */   public Map<A, B> $minus(Object key) {
/* 91 */     return MapLike$class.$minus(this, key);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 91 */     MapLike$class.clear(this);
/*    */   }
/*    */   
/*    */   public B getOrElseUpdate(Object key, Function0 op) {
/* 91 */     return (B)MapLike$class.getOrElseUpdate(this, key, op);
/*    */   }
/*    */   
/*    */   public MapLike<A, B, Map<A, B>> transform(Function2 f) {
/* 91 */     return MapLike$class.transform(this, f);
/*    */   }
/*    */   
/*    */   public MapLike<A, B, Map<A, B>> retain(Function2 p) {
/* 91 */     return MapLike$class.retain(this, p);
/*    */   }
/*    */   
/*    */   public Map<A, B> clone() {
/* 91 */     return MapLike$class.clone(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> result() {
/* 91 */     return MapLike$class.result(this);
/*    */   }
/*    */   
/*    */   public Map<A, B> $minus(Object elem1, Object elem2, Seq elems) {
/* 91 */     return MapLike$class.$minus(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Map<A, B> $minus$minus(GenTraversableOnce xs) {
/* 91 */     return MapLike$class.$minus$minus(this, xs);
/*    */   }
/*    */   
/*    */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 91 */     return super.clone();
/*    */   }
/*    */   
/*    */   public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 91 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 91 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public void sizeHint(int size) {
/* 91 */     Builder$class.sizeHint(this, size);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll) {
/* 91 */     Builder$class.sizeHint(this, coll);
/*    */   }
/*    */   
/*    */   public void sizeHint(TraversableLike coll, int delta) {
/* 91 */     Builder$class.sizeHint(this, coll, delta);
/*    */   }
/*    */   
/*    */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 91 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */   }
/*    */   
/*    */   public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1 f) {
/* 91 */     return Builder$class.mapResult(this, f);
/*    */   }
/*    */   
/*    */   public Growable<Tuple2<A, B>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 91 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */   }
/*    */   
/*    */   public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce xs) {
/* 91 */     return Growable.class.$plus$plus$eq(this, xs);
/*    */   }
/*    */   
/*    */   public GenericCompanion<Iterable> companion() {
/* 91 */     return Iterable$class.companion(this);
/*    */   }
/*    */   
/*    */   public AbstractMap() {
/* 91 */     Traversable$class.$init$(this);
/* 91 */     Iterable$class.$init$(this);
/* 91 */     Growable.class.$init$(this);
/* 91 */     Builder$class.$init$(this);
/* 91 */     Shrinkable.class.$init$(this);
/* 91 */     Cloneable$class.$init$(this);
/* 91 */     MapLike$class.$init$(this);
/* 91 */     Map$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\AbstractMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */