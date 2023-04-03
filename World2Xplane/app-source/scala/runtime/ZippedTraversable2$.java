/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.AbstractTraversable;
/*    */ import scala.collection.Traversable;
/*    */ 
/*    */ public final class ZippedTraversable2$ {
/*    */   public static final ZippedTraversable2$ MODULE$;
/*    */   
/*    */   private ZippedTraversable2$() {
/* 22 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <El1, El2> Traversable<Tuple2<El1, El2>> zippedTraversable2ToTraversable(ZippedTraversable2 zz) {
/* 24 */     return (Traversable<Tuple2<El1, El2>>)new ZippedTraversable2$$anon$1(zz);
/*    */   }
/*    */   
/*    */   public static class ZippedTraversable2$$anon$1 extends AbstractTraversable<Tuple2<El1, El2>> {
/*    */     private final ZippedTraversable2 zz$1;
/*    */     
/*    */     public ZippedTraversable2$$anon$1(ZippedTraversable2 zz$1) {}
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 25 */       scala.Function$ function$ = scala.Function$.MODULE$;
/* 25 */       this.zz$1.foreach((Function2)new Object(f));
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\ZippedTraversable2$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */