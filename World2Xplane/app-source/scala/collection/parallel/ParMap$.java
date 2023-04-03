/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParMapFactory;
/*    */ import scala.collection.parallel.mutable.ParHashMap;
/*    */ import scala.collection.parallel.mutable.ParHashMapCombiner$;
/*    */ 
/*    */ public final class ParMap$ extends ParMapFactory<ParMap> {
/*    */   public static final ParMap$ MODULE$;
/*    */   
/*    */   private ParMap$() {
/* 52 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <K, V> ParMap<K, V> empty() {
/* 53 */     return (ParMap<K, V>)new ParHashMap();
/*    */   }
/*    */   
/*    */   public <K, V> Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
/* 55 */     return (Combiner<Tuple2<K, V>, ParMap<K, V>>)ParHashMapCombiner$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public <K, V> CanCombineFrom<ParMap<?, ?>, Tuple2<K, V>, ParMap<K, V>> canBuildFrom() {
/* 57 */     return (CanCombineFrom<ParMap<?, ?>, Tuple2<K, V>, ParMap<K, V>>)new ParMapFactory.CanCombineFromMap(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */