/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.TraversableFactory;
/*    */ import scala.collection.immutable.Iterable$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class Iterable$ extends GenTraversableFactory<Iterable> implements TraversableFactory<Iterable> {
/*    */   public static final Iterable$ MODULE$;
/*    */   
/*    */   private Iterable$() {
/* 45 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Iterable<?>, A, Iterable<A>> canBuildFrom() {
/* 48 */     return (CanBuildFrom<Iterable<?>, A, Iterable<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Iterable<A>> newBuilder() {
/* 50 */     return Iterable$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Iterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */