/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.ImmutableSetFactory;
/*    */ 
/*    */ public final class Set$ extends ImmutableSetFactory<Set> {
/*    */   public static final Set$ MODULE$;
/*    */   
/*    */   private Set$() {
/* 44 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
/* 46 */     return setCanBuildFrom();
/*    */   }
/*    */   
/*    */   public <A> Set<A> empty() {
/* 47 */     return Set.EmptySet$.MODULE$;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Set$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */