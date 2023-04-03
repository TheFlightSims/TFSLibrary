/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParMapFactory;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ParMap$ extends ParMapFactory<ParMap> {
/*    */   public static final ParMap$ MODULE$;
/*    */   
/*    */   public class ParMap$$anonfun$withDefaultValue$1 extends AbstractFunction1<K, V> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object d$2;
/*    */     
/*    */     public final V apply(Object x) {
/* 65 */       return (V)this.d$2;
/*    */     }
/*    */     
/*    */     public ParMap$$anonfun$withDefaultValue$1(ParMap $outer, Object d$2) {}
/*    */   }
/*    */   
/*    */   private ParMap$() {
/* 71 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <K, V> ParMap<K, V> empty() {
/* 72 */     return new ParHashMap<K, V>();
/*    */   }
/*    */   
/*    */   public <K, V> Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
/* 74 */     return (Combiner)ParHashMapCombiner$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public <K, V> CanCombineFrom<ParMap<?, ?>, Tuple2<K, V>, ParMap<K, V>> canBuildFrom() {
/* 76 */     return (CanCombineFrom<ParMap<?, ?>, Tuple2<K, V>, ParMap<K, V>>)new ParMapFactory.CanCombineFromMap(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */