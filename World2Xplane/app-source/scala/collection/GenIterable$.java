/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class GenIterable$ extends GenTraversableFactory<GenIterable> {
/*    */   public static final GenIterable$ MODULE$;
/*    */   
/*    */   private GenIterable$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> GenTraversableFactory<GenIterable>.GenericCanBuildFrom<A> canBuildFrom() {
/* 33 */     return ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Iterable<A>> newBuilder() {
/* 34 */     return Iterable$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenIterable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */