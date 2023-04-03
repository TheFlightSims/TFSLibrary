/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.TraversableFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ 
/*    */ public final class Traversable$ extends GenTraversableFactory<Traversable> implements TraversableFactory<Traversable> {
/*    */   public static final Traversable$ MODULE$;
/*    */   
/*    */   private Traversable$() {
/* 35 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Traversable<?>, A, Traversable<A>> canBuildFrom() {
/* 36 */     return (CanBuildFrom<Traversable<?>, A, Traversable<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Traversable<A>> newBuilder() {
/* 37 */     return (Builder<A, Traversable<A>>)new ListBuffer();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Traversable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */