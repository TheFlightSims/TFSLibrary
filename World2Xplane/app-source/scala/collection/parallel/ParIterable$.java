/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.mutable.package$;
/*    */ 
/*    */ public final class ParIterable$ extends ParFactory<ParIterable> {
/*    */   public static final ParIterable$ MODULE$;
/*    */   
/*    */   private ParIterable$() {
/* 42 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParIterable<?>, T, ParIterable<T>> canBuildFrom() {
/* 43 */     return (CanCombineFrom<ParIterable<?>, T, ParIterable<T>>)new ParFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParIterable<T>> newBuilder() {
/* 45 */     return (Combiner<T, ParIterable<T>>)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParIterable<T>> newCombiner() {
/* 47 */     return (Combiner<T, ParIterable<T>>)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParIterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */