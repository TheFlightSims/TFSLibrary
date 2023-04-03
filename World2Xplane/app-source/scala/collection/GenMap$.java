/*    */ package scala.collection;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenMapFactory;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Map$;
/*    */ 
/*    */ public final class GenMap$ extends GenMapFactory<GenMap> {
/*    */   public static final GenMap$ MODULE$;
/*    */   
/*    */   private GenMap$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A, B> Map<A, B> empty() {
/* 32 */     return Map$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public <A, B> CanBuildFrom<GenMap<?, ?>, Tuple2<A, B>, GenMap<A, B>> canBuildFrom() {
/* 35 */     return (CanBuildFrom<GenMap<?, ?>, Tuple2<A, B>, GenMap<A, B>>)new GenMapFactory.MapCanBuildFrom(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */