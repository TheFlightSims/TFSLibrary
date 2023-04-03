/*     */ package akka.routing;
/*     */ 
/*     */ public abstract class PoolOverrideUnsetConfig$class {
/*     */   public static void $init$(PoolOverrideUnsetConfig $this) {}
/*     */   
/*     */   public static final RouterConfig overrideUnsetConfig(PoolOverrideUnsetConfig $this, RouterConfig other) {
/* 103 */     NoRouter$ noRouter$ = NoRouter$.MODULE$;
/* 103 */     if (other == null) {
/* 103 */       if (noRouter$ != null)
/* 106 */         RouterConfig routerConfig = other; 
/*     */     } else {
/*     */       if (other.equals(noRouter$));
/* 106 */       RouterConfig routerConfig = other;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\PoolOverrideUnsetConfig$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */