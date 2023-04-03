/*     */ package org.java.plugin;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ 
/*     */ public abstract class Plugin {
/*  43 */   protected final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private PluginManager manager;
/*     */   
/*     */   private PluginDescriptor descriptor;
/*     */   
/*     */   private boolean started;
/*     */   
/*     */   public final PluginDescriptor getDescriptor() {
/*  53 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   final void setDescriptor(PluginDescriptor descr) {
/*  60 */     this.descriptor = descr;
/*     */   }
/*     */   
/*     */   public final PluginManager getManager() {
/*  67 */     return this.manager;
/*     */   }
/*     */   
/*     */   final void setManager(PluginManager aManager) {
/*  74 */     this.manager = aManager;
/*     */   }
/*     */   
/*     */   final void start() throws Exception {
/*  81 */     if (!this.started) {
/*  82 */       doStart();
/*  83 */       this.started = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   final void stop() throws Exception {
/*  91 */     if (this.started) {
/*  92 */       doStop();
/*  93 */       this.started = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final boolean isActive() {
/* 101 */     return this.started;
/*     */   }
/*     */   
/*     */   protected abstract void doStart() throws Exception;
/*     */   
/*     */   protected abstract void doStop() throws Exception;
/*     */   
/*     */   public String toString() {
/* 125 */     return "{" + getClass().getName() + ": manager=" + this.manager + ", descriptor=" + this.descriptor + "}";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\Plugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */