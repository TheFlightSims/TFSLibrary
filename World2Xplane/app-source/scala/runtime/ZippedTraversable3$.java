/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function3;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.AbstractTraversable;
/*    */ import scala.collection.Traversable;
/*    */ 
/*    */ public final class ZippedTraversable3$ {
/*    */   public static final ZippedTraversable3$ MODULE$;
/*    */   
/*    */   private ZippedTraversable3$() {
/* 19 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <El1, El2, El3> Traversable<Tuple3<El1, El2, El3>> zippedTraversable3ToTraversable(ZippedTraversable3 zz) {
/* 21 */     return (Traversable<Tuple3<El1, El2, El3>>)new ZippedTraversable3$$anon$1(zz);
/*    */   }
/*    */   
/*    */   public static class ZippedTraversable3$$anon$1 extends AbstractTraversable<Tuple3<El1, El2, El3>> {
/*    */     private final ZippedTraversable3 zz$1;
/*    */     
/*    */     public ZippedTraversable3$$anon$1(ZippedTraversable3 zz$1) {}
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 22 */       scala.Function$ function$ = scala.Function$.MODULE$;
/* 22 */       this.zz$1.foreach((Function3)new Object(f));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ZippedTraversable3$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */