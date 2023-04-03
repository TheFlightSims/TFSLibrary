/*     */ package ch.qos.logback.core;
/*     */ 
/*     */ import ch.qos.logback.core.spi.LifeCycle;
/*     */ import ch.qos.logback.core.spi.LogbackLock;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import ch.qos.logback.core.util.ExecutorServiceUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ 
/*     */ public class ContextBase implements Context, LifeCycle {
/*  29 */   private long birthTime = System.currentTimeMillis();
/*     */   
/*     */   private String name;
/*     */   
/*  32 */   private StatusManager sm = new BasicStatusManager();
/*     */   
/*  36 */   Map<String, String> propertyMap = new HashMap<String, String>();
/*     */   
/*  37 */   Map<String, Object> objectMap = new HashMap<String, Object>();
/*     */   
/*  39 */   LogbackLock configurationLock = new LogbackLock();
/*     */   
/*     */   private volatile ExecutorService executorService;
/*     */   
/*     */   private LifeCycleManager lifeCycleManager;
/*     */   
/*     */   private boolean started;
/*     */   
/*     */   public StatusManager getStatusManager() {
/*  46 */     return this.sm;
/*     */   }
/*     */   
/*     */   public void setStatusManager(StatusManager statusManager) {
/*  61 */     if (this.sm == null)
/*  62 */       throw new IllegalArgumentException("null StatusManager not allowed"); 
/*  64 */     this.sm = statusManager;
/*     */   }
/*     */   
/*     */   public Map<String, String> getCopyOfPropertyMap() {
/*  68 */     return new HashMap<String, String>(this.propertyMap);
/*     */   }
/*     */   
/*     */   public void putProperty(String key, String val) {
/*  72 */     this.propertyMap.put(key, val);
/*     */   }
/*     */   
/*     */   public String getProperty(String key) {
/*  83 */     if ("CONTEXT_NAME".equals(key))
/*  84 */       return getName(); 
/*  86 */     return this.propertyMap.get(key);
/*     */   }
/*     */   
/*     */   public Object getObject(String key) {
/*  90 */     return this.objectMap.get(key);
/*     */   }
/*     */   
/*     */   public void putObject(String key, Object value) {
/*  94 */     this.objectMap.put(key, value);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  98 */     return this.name;
/*     */   }
/*     */   
/*     */   public void start() {
/* 105 */     this.started = true;
/*     */   }
/*     */   
/*     */   public void stop() {
/* 111 */     stopExecutorService();
/* 112 */     this.started = false;
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/* 116 */     return this.started;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 123 */     getLifeCycleManager().reset();
/* 124 */     this.propertyMap.clear();
/* 125 */     this.objectMap.clear();
/*     */   }
/*     */   
/*     */   public void setName(String name) throws IllegalStateException {
/* 136 */     if (name != null && name.equals(this.name))
/*     */       return; 
/* 139 */     if (this.name == null || "default".equals(this.name)) {
/* 141 */       this.name = name;
/*     */     } else {
/* 143 */       throw new IllegalStateException("Context has been already given a name");
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getBirthTime() {
/* 148 */     return this.birthTime;
/*     */   }
/*     */   
/*     */   public Object getConfigurationLock() {
/* 152 */     return this.configurationLock;
/*     */   }
/*     */   
/*     */   public ExecutorService getExecutorService() {
/* 156 */     if (this.executorService == null)
/* 157 */       synchronized (this) {
/* 158 */         if (this.executorService == null)
/* 159 */           this.executorService = ExecutorServiceUtil.newExecutorService(); 
/*     */       }  
/* 163 */     return this.executorService;
/*     */   }
/*     */   
/*     */   private synchronized void stopExecutorService() {
/* 167 */     if (this.executorService != null) {
/* 168 */       ExecutorServiceUtil.shutdown(this.executorService);
/* 169 */       this.executorService = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void register(LifeCycle component) {
/* 174 */     getLifeCycleManager().register(component);
/*     */   }
/*     */   
/*     */   synchronized LifeCycleManager getLifeCycleManager() {
/* 190 */     if (this.lifeCycleManager == null)
/* 191 */       this.lifeCycleManager = new LifeCycleManager(); 
/* 193 */     return this.lifeCycleManager;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 198 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\ContextBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */