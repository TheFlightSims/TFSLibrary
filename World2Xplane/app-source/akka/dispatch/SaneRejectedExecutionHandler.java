/*     */ package akka.dispatch;
/*     */ 
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.RejectedExecutionHandler;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001A2A!\001\002\001\017\ta2+\0318f%\026TWm\031;fI\026CXmY;uS>t\007*\0318eY\026\024(BA\002\005\003!!\027n\0359bi\016D'\"A\003\002\t\005\\7.Y\002\001'\r\001\001\002\005\t\003\0239i\021A\003\006\003\0271\tA\001\\1oO*\tQ\"\001\003kCZ\f\027BA\b\013\005\031y%M[3diB\021\021CF\007\002%)\0211\003F\001\013G>t7-\036:sK:$(BA\013\r\003\021)H/\0337\n\005]\021\"\001\007*fU\026\034G/\0323Fq\026\034W\017^5p]\"\013g\016\0327fe\")\021\004\001C\0015\0051A(\0338jiz\"\022a\007\t\0039\001i\021A\001\005\006=\001!\taH\001\022e\026TWm\031;fI\026CXmY;uS>tGc\001\021'WA\021\021\005J\007\002E)\t1%A\003tG\006d\027-\003\002&E\t!QK\\5u\021\0259S\0041\001)\003!\021XO\0348bE2,\007CA\005*\023\tQ#B\001\005Sk:t\027M\0317f\021\025aS\0041\001.\003I!\bN]3bIB{w\016\\#yK\016,Ho\034:\021\005Eq\023BA\030\023\005I!\006N]3bIB{w\016\\#yK\016,Ho\034:")
/*     */ public class SaneRejectedExecutionHandler implements RejectedExecutionHandler {
/*     */   public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
/* 246 */     if (threadPoolExecutor.isShutdown())
/* 246 */       throw new RejectedExecutionException("Shutdown"); 
/* 247 */     runnable.run();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\SaneRejectedExecutionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */