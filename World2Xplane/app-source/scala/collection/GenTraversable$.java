/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class GenTraversable$ extends GenTraversableFactory<GenTraversable> {
/*    */   public static final GenTraversable$ MODULE$;
/*    */   
/*    */   private GenTraversable$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> GenTraversableFactory<GenTraversable>.GenericCanBuildFrom<A> canBuildFrom() {
/* 35 */     return ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Traversable<A>> newBuilder() {
/* 36 */     return Traversable$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenTraversable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */