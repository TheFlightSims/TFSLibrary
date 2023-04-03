/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ import scala.collection.immutable.Seq$;
/*    */ import scala.collection.mutable.Builder;
/*    */ 
/*    */ public final class Seq$ extends SeqFactory<Seq> {
/*    */   public static final Seq$ MODULE$;
/*    */   
/*    */   private Seq$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Seq<?>, A, Seq<A>> canBuildFrom() {
/* 34 */     return (CanBuildFrom<Seq<?>, A, Seq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Seq<A>> newBuilder() {
/* 36 */     return Seq$.MODULE$.newBuilder();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Seq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */