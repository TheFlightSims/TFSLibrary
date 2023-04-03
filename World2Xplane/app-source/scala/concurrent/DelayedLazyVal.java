/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0353A!\001\002\001\017\tqA)\0327bs\026$G*\031>z-\006d'BA\002\005\003)\031wN\\2veJ,g\016\036\006\002\013\005)1oY1mC\016\001QC\001\005\025'\t\001\021\002\005\002\013\0275\tA!\003\002\r\t\t1\021I\\=SK\032D\001B\004\001\003\002\003\006IaD\001\002MB\031!\002\005\n\n\005E!!!\003$v]\016$\030n\03481!\t\031B\003\004\001\005\013U\001!\031\001\f\003\003Q\013\"a\006\016\021\005)A\022BA\r\005\005\035qu\016\0365j]\036\004\"AC\016\n\005q!!aA!os\"Aa\004\001B\001J\003%q$\001\003c_\022L\bc\001\006!E%\021\021\005\002\002\ty\tLh.Y7f}A\021!bI\005\003I\021\021A!\0268ji\"Aa\005\001B\001B\003-q%\001\003fq\026\034\007C\001\025*\033\005\021\021B\001\026\003\005A)\0050Z2vi&|gnQ8oi\026DH\017C\003-\001\021\005Q&\001\004=S:LGO\020\013\004]E\022DCA\0301!\rA\003A\005\005\006M-\002\035a\n\005\006\035-\002\ra\004\005\007=-\"\t\031A\020\t\rQ\002\001\025)\0036\003\035y\026n\035#p]\026\004\"A\003\034\n\005]\"!a\002\"p_2,\027M\034\025\003ge\002\"A\003\036\n\005m\"!\001\003<pY\006$\030\016\\3\t\021u\002\001R1Q\005\ny\n\001bY8na2,G/Z\013\002%!A\001\t\001E\001B\003&!#A\005d_6\004H.\032;fA!)!\t\001C\001\007\0061\021n\035#p]\026,\022!\016\005\006\013\002!\tAR\001\006CB\004H.\037\013\002%\001")
/*    */ public class DelayedLazyVal<T> {
/*    */   private final Function0<T> f;
/*    */   
/*    */   public final Function0<BoxedUnit> scala$concurrent$DelayedLazyVal$$body;
/*    */   
/*    */   public volatile boolean scala$concurrent$DelayedLazyVal$$_isDone;
/*    */   
/*    */   private T complete;
/*    */   
/*    */   private volatile boolean bitmap$0;
/*    */   
/*    */   public DelayedLazyVal(Function0<T> f, Function0<BoxedUnit> body, ExecutionContext exec) {
/* 27 */     this.scala$concurrent$DelayedLazyVal$$_isDone = false;
/* 42 */     exec.execute(new DelayedLazyVal$$anon$1(this));
/*    */   }
/*    */   
/*    */   private Object complete$lzycompute() {
/*    */     synchronized (this) {
/*    */       if (!this.bitmap$0) {
/*    */         this.complete = (T)this.f.apply();
/*    */         this.bitmap$0 = true;
/*    */       } 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/concurrent/DelayedLazyVal}} */
/*    */       return this.complete;
/*    */     } 
/*    */   }
/*    */   
/*    */   private T complete() {
/*    */     return this.bitmap$0 ? this.complete : (T)complete$lzycompute();
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/*    */     return this.scala$concurrent$DelayedLazyVal$$_isDone;
/*    */   }
/*    */   
/*    */   public T apply() {
/*    */     return isDone() ? complete() : (T)this.f.apply();
/*    */   }
/*    */   
/*    */   public class DelayedLazyVal$$anon$1 implements Runnable {
/*    */     public void run() {
/* 42 */       this.$outer.scala$concurrent$DelayedLazyVal$$body.apply$mcV$sp();
/* 42 */       this.$outer.scala$concurrent$DelayedLazyVal$$_isDone = true;
/*    */     }
/*    */     
/*    */     public DelayedLazyVal$$anon$1(DelayedLazyVal $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\DelayedLazyVal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */