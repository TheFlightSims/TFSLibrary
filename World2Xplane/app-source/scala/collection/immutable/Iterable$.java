/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.TraversableFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.ListBuffer;
/*    */ 
/*    */ public final class Iterable$ extends GenTraversableFactory<Iterable> implements TraversableFactory<Iterable> {
/*    */   public static final Iterable$ MODULE$;
/*    */   
/*    */   private Iterable$() {
/* 40 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Iterable<?>, A, Iterable<A>> canBuildFrom() {
/* 41 */     return (CanBuildFrom<Iterable<?>, A, Iterable<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Iterable<A>> newBuilder() {
/* 42 */     return (Builder<A, Iterable<A>>)new ListBuffer();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Iterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */