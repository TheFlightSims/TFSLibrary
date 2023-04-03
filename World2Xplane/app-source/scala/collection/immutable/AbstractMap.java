/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractMap;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0252a!\001\002\002\002\031A!aC!cgR\024\030m\031;NCBT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mCV\031\021bD\016\024\007\001QQ\004\005\003\f\0315QR\"\001\003\n\005\005!\001C\001\b\020\031\001!Q\001\005\001C\002I\021\021!Q\002\001#\t\031r\003\005\002\025+5\ta!\003\002\027\r\t9aj\034;iS:<\007C\001\013\031\023\tIbAA\002B]f\004\"AD\016\005\rq\001AQ1\001\023\005\005\021\005\003\002\020 \033ii\021AA\005\003A\t\0211!T1q\021\025\021\003\001\"\001$\003\031a\024N\\5u}Q\tA\005\005\003\037\0015Q\002")
/*     */ public abstract class AbstractMap<A, B> extends AbstractMap<A, B> implements Map<A, B> {
/*     */   public Map<A, B> empty() {
/* 187 */     return Map$class.empty(this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 187 */     return Map$class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Map<A, B> seq() {
/* 187 */     return Map$class.seq(this);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> withDefault(Function1 d) {
/* 187 */     return Map$class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> withDefaultValue(Object d) {
/* 187 */     return Map$class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 187 */     return MapLike$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> updated(Object key, Object value) {
/* 187 */     return MapLike$class.updated(this, key, value);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 187 */     return MapLike$class.$plus(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 187 */     return MapLike$class.$plus$plus(this, xs);
/*     */   }
/*     */   
/*     */   public Map<A, B> filterKeys(Function1 p) {
/* 187 */     return MapLike$class.filterKeys(this, p);
/*     */   }
/*     */   
/*     */   public <C> Map<A, C> mapValues(Function1 f) {
/* 187 */     return MapLike$class.mapValues(this, f);
/*     */   }
/*     */   
/*     */   public Set<A> keySet() {
/* 187 */     return MapLike$class.keySet(this);
/*     */   }
/*     */   
/*     */   public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/* 187 */     return (That)MapLike$class.transform(this, f, bf);
/*     */   }
/*     */   
/*     */   public GenericCompanion<Iterable> companion() {
/* 187 */     return Iterable$class.companion(this);
/*     */   }
/*     */   
/*     */   public AbstractMap() {
/* 187 */     Traversable$class.$init$(this);
/* 187 */     Iterable$class.$init$(this);
/* 187 */     MapLike$class.$init$(this);
/* 187 */     Map$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\AbstractMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */