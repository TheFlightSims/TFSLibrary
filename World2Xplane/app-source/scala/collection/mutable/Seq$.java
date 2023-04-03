/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ 
/*    */ public final class Seq$ extends SeqFactory<Seq> {
/*    */   public static final Seq$ MODULE$;
/*    */   
/*    */   private Seq$() {
/* 41 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Seq<?>, A, Seq<A>> canBuildFrom() {
/* 42 */     return (CanBuildFrom<Seq<?>, A, Seq<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Seq<A>> newBuilder() {
/* 43 */     return (Builder)new ArrayBuffer<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Seq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */