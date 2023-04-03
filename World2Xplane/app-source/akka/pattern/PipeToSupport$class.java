/*    */ package akka.pattern;
/*    */ 
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ 
/*    */ public abstract class PipeToSupport$class {
/*    */   public static void $init$(PipeToSupport $this) {}
/*    */   
/*    */   public static PipeToSupport.PipeableFuture pipe(PipeToSupport $this, Future<?> future, ExecutionContext executionContext) {
/* 58 */     return new PipeToSupport.PipeableFuture($this, future, executionContext);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\PipeToSupport$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */