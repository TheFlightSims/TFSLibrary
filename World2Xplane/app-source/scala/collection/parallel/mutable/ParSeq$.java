/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public final class ParSeq$ extends ParFactory<ParSeq> {
/*    */   public static final ParSeq$ MODULE$;
/*    */   
/*    */   private ParSeq$() {
/* 53 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSeq<?>, T, ParSeq<T>> canBuildFrom() {
/* 54 */     return (CanCombineFrom<ParSeq<?>, T, ParSeq<T>>)new ParFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newBuilder() {
/* 56 */     return (Combiner)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newCombiner() {
/* 58 */     return (Combiner)package$.MODULE$.ParArrayCombiner().apply();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */