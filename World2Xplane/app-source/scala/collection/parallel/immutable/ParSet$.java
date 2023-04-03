/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.ParSetFactory;
/*    */ import scala.collection.parallel.Combiner;
/*    */ 
/*    */ public final class ParSet$ extends ParSetFactory<ParSet> {
/*    */   public static final ParSet$ MODULE$;
/*    */   
/*    */   private ParSet$() {
/* 44 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Combiner<T, ParSet<T>> newCombiner() {
/* 45 */     return (Combiner)HashSetCombiner$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public <T> CanCombineFrom<ParSet<?>, T, ParSet<T>> canBuildFrom() {
/* 47 */     return (CanCombineFrom<ParSet<?>, T, ParSet<T>>)new ParSetFactory.GenericCanCombineFrom(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */