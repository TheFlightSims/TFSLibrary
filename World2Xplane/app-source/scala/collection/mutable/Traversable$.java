/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.TraversableFactory;
/*    */ 
/*    */ public final class Traversable$ extends GenTraversableFactory<Traversable> implements TraversableFactory<Traversable> {
/*    */   public static final Traversable$ MODULE$;
/*    */   
/*    */   private Traversable$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Traversable<?>, A, Traversable<A>> canBuildFrom() {
/* 35 */     return (CanBuildFrom<Traversable<?>, A, Traversable<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Traversable<A>> newBuilder() {
/* 36 */     return (Builder)new ArrayBuffer<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Traversable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */