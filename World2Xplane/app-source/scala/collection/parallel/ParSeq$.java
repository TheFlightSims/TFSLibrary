/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.mutable.package$;
/*    */ 
/*    */ public final class ParSeq$ extends ParFactory<ParSeq> {
/*    */   public static final ParSeq$ MODULE$;
/*    */   
/*    */   private ParSeq$() {
/* 51 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSeq<?>, T, ParSeq<T>> canBuildFrom() {
/* 52 */     return (CanCombineFrom<ParSeq<?>, T, ParSeq<T>>)new ParFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newBuilder() {
/* 54 */     return (Combiner<T, ParSeq<T>>)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newCombiner() {
/* 56 */     return (Combiner<T, ParSeq<T>>)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */