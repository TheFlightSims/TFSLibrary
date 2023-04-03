/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.MutableSetFactory;
/*    */ 
/*    */ public final class Set$ extends MutableSetFactory<Set> {
/*    */   public static final Set$ MODULE$;
/*    */   
/*    */   private Set$() {
/* 39 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Set<?>, A, Set<A>> canBuildFrom() {
/* 40 */     return setCanBuildFrom();
/*    */   }
/*    */   
/*    */   public <A> Set<A> empty() {
/* 41 */     return HashSet$.MODULE$.empty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Set$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */