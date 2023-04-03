/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.SeqFactory;
/*    */ 
/*    */ public final class Buffer$ extends SeqFactory<Buffer> {
/*    */   public static final Buffer$ MODULE$;
/*    */   
/*    */   private Buffer$() {
/* 42 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <A> CanBuildFrom<Buffer<?>, A, Buffer<A>> canBuildFrom() {
/* 43 */     return (CanBuildFrom<Buffer<?>, A, Buffer<A>>)ReusableCBF();
/*    */   }
/*    */   
/*    */   public <A> Builder<A, Buffer<A>> newBuilder() {
/* 44 */     return (Builder)new ArrayBuffer<A>();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Buffer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */