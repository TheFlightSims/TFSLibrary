/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SortedMapFactory;
/*    */ import scala.collection.immutable.SortedMap$;
/*    */ import scala.math.Ordering;
/*    */ 
/*    */ public final class SortedMap$ extends SortedMapFactory<SortedMap> {
/*    */   public static final SortedMap$ MODULE$;
/*    */   
/*    */   private SortedMap$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> SortedMap<A, B> empty(Ordering ord) {
/* 33 */     return (SortedMap<A, B>)SortedMap$.MODULE$.empty(ord);
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>> canBuildFrom(Ordering ord) {
/* 35 */     return (CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>>)new SortedMapFactory.SortedMapCanBuildFrom(this, ord);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SortedMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */