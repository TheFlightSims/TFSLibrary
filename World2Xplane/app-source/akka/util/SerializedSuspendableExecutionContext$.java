/*    */ package akka.util;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.StringContext;
/*    */ import scala.collection.Seq;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class SerializedSuspendableExecutionContext$ implements Serializable {
/*    */   public static final SerializedSuspendableExecutionContext$ MODULE$;
/*    */   
/*    */   private final int Off;
/*    */   
/*    */   private final int On;
/*    */   
/*    */   private final int Suspended;
/*    */   
/*    */   private Object readResolve() {
/* 13 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private SerializedSuspendableExecutionContext$() {
/* 13 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public final int Off() {
/* 14 */     return 0;
/*    */   }
/*    */   
/*    */   public final int On() {
/* 15 */     return 1;
/*    */   }
/*    */   
/*    */   public final int Suspended() {
/* 16 */     return 2;
/*    */   }
/*    */   
/*    */   public SerializedSuspendableExecutionContext apply(int throughput, ExecutionContext context) {
/* 19 */     ExecutionContext executionContext2, executionContext1 = context;
/* 20 */     if (executionContext1 instanceof SerializedSuspendableExecutionContext) {
/* 20 */       SerializedSuspendableExecutionContext serializedSuspendableExecutionContext = (SerializedSuspendableExecutionContext)executionContext1;
/* 20 */       executionContext2 = serializedSuspendableExecutionContext.context();
/*    */     } else {
/* 21 */       executionContext2 = executionContext1;
/*    */     } 
/*    */     return new SerializedSuspendableExecutionContext(throughput, executionContext2);
/*    */   }
/*    */   
/*    */   public class SerializedSuspendableExecutionContext$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/* 37 */       (new String[2])[0] = "SerializedSuspendableExecutionContext.throughput must be greater than 0 but was ";
/* 37 */       (new String[2])[1] = "";
/* 37 */       return (new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.$outer.akka$util$SerializedSuspendableExecutionContext$$throughput) }));
/*    */     }
/*    */     
/*    */     public SerializedSuspendableExecutionContext$$anonfun$1(SerializedSuspendableExecutionContext $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\SerializedSuspendableExecutionContext$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */