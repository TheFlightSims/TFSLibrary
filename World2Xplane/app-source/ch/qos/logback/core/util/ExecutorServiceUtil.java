/*    */ package ch.qos.logback.core.util;
/*    */ 
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.SynchronousQueue;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ public class ExecutorServiceUtil {
/*    */   public static ExecutorService newExecutorService() {
/* 36 */     return new ThreadPoolExecutor(CoreConstants.CORE_POOL_SIZE, 32, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
/*    */   }
/*    */   
/*    */   public static void shutdown(ExecutorService executorService) {
/* 48 */     executorService.shutdownNow();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\ExecutorServiceUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */