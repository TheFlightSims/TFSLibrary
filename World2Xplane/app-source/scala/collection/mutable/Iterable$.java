/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.TraversableFactory;
/*    */ 
/*    */ public final class Iterable$ extends GenTraversableFactory<Iterable> implements TraversableFactory<Iterable> {
/*    */   public static final Iterable$ MODULE$;
/*    */   
/*    */   private Iterable$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Iterable<?>, A, Iterable<A>> canBuildFrom() {
/* 35 */     return (CanBuildFrom<Iterable<?>, A, Iterable<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Iterable<A>> newBuilder() {
/* 36 */     return (Builder)new ArrayBuffer<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Iterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */