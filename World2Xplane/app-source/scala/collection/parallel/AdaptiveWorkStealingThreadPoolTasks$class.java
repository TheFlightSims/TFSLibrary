/*     */ package scala.collection.parallel;
/*     */ 
/*     */ public abstract class AdaptiveWorkStealingThreadPoolTasks$class {
/*     */   public static void $init$(AdaptiveWorkStealingThreadPoolTasks $this) {}
/*     */   
/*     */   public static AdaptiveWorkStealingThreadPoolTasks.WrappedTask newWrappedTask(AdaptiveWorkStealingThreadPoolTasks $this, Task<?, ?> b) {
/* 531 */     return new AdaptiveWorkStealingThreadPoolTasks.WrappedTask<Object, Object>($this, b);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AdaptiveWorkStealingThreadPoolTasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */