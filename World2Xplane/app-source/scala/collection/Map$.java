/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenMapFactory;
/*    */ import scala.collection.generic.MapFactory;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Map$;
/*    */ 
/*    */ public final class Map$ extends MapFactory<Map> {
/*    */   public static final Map$ MODULE$;
/*    */   
/*    */   private Map$() {
/* 39 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> Map<A, B> empty() {
/* 40 */     return Map$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>> canBuildFrom() {
/* 43 */     return (CanBuildFrom<Map<?, ?>, Tuple2<A, B>, Map<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Map$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */