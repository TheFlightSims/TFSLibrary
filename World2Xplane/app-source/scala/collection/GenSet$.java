/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class GenSet$ extends GenTraversableFactory<GenSet> {
/*    */   public static final GenSet$ MODULE$;
/*    */   
/*    */   private GenSet$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> GenTraversableFactory<GenSet>.GenericCanBuildFrom<A> canBuildFrom() {
/* 34 */     return ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Set<A>> newBuilder() {
/* 35 */     return Set$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */