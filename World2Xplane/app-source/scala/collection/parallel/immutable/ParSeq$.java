/*    */ package scala.collection.parallel.immutable;
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
/* 46 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSeq<?>, T, ParSeq<T>> canBuildFrom() {
/* 47 */     return (CanCombineFrom<ParSeq<?>, T, ParSeq<T>>)new ParFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newBuilder() {
/* 49 */     return (Combiner)ParVector$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSeq<T>> newCombiner() {
/* 51 */     return (Combiner)ParVector$.MODULE$.newCombiner();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */