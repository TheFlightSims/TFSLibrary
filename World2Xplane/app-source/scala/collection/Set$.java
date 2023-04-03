/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SetFactory;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Set$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class Set$ extends SetFactory<Set> {
/*    */   public static final Set$ MODULE$;
/*    */   
/*    */   private Set$() {
/* 40 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Set<A>> newBuilder() {
/* 41 */     return Set$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public <A> Set<A> empty() {
/* 42 */     return (Set<A>)Set$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
/* 43 */     return setCanBuildFrom();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Set$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */