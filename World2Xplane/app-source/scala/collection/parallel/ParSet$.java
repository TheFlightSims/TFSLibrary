/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParSetFactory;
/*    */ import scala.collection.parallel.mutable.ParHashSetCombiner$;
/*    */ 
/*    */ public final class ParSet$ extends ParSetFactory<ParSet> {
/*    */   public static final ParSet$ MODULE$;
/*    */   
/*    */   private ParSet$() {
/* 55 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSet<T>> newCombiner() {
/* 56 */     return (Combiner<T, ParSet<T>>)ParHashSetCombiner$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSet<?>, T, ParSet<T>> canBuildFrom() {
/* 58 */     return (CanCombineFrom<ParSet<?>, T, ParSet<T>>)new ParSetFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */