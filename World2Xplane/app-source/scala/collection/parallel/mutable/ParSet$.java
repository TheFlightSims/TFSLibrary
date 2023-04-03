/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParSetFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public final class ParSet$ extends ParSetFactory<ParSet> {
/*    */   public static final ParSet$ MODULE$;
/*    */   
/*    */   private ParSet$() {
/* 47 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSet<?>, T, ParSet<T>> canBuildFrom() {
/* 48 */     return (CanCombineFrom<ParSet<?>, T, ParSet<T>>)new ParSetFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSet<T>> newBuilder() {
/* 50 */     return (Combiner)ParHashSet$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSet<T>> newCombiner() {
/* 52 */     return (Combiner)ParHashSet$.MODULE$.newCombiner();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */