/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class GenSeq$ extends GenTraversableFactory<GenSeq> {
/*    */   public static final GenSeq$ MODULE$;
/*    */   
/*    */   private GenSeq$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> GenTraversableFactory<GenSeq>.GenericCanBuildFrom<A> canBuildFrom() {
/* 34 */     return ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Seq<A>> newBuilder() {
/* 35 */     return Seq$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */