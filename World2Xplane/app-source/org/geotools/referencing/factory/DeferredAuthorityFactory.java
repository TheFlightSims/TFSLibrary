/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.factory.OptionalFactory;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ public abstract class DeferredAuthorityFactory extends BufferedAuthorityFactory implements OptionalFactory {
/*  58 */   private static Timer TIMER = new Timer("GT authority factory disposer", true);
/*     */   
/*     */   private TimerTask disposer;
/*     */   
/*     */   private boolean used;
/*     */   
/*     */   protected DeferredAuthorityFactory(Hints userHints, int priority) {
/*  88 */     super(priority, 20);
/*     */   }
/*     */   
/*     */   protected DeferredAuthorityFactory(Hints userHints, int priority, int maxStrongReferences) {
/* 110 */     super(priority, maxStrongReferences);
/*     */   }
/*     */   
/*     */   public boolean isAvailable() {
/* 119 */     return super.isAvailable();
/*     */   }
/*     */   
/*     */   protected final AbstractAuthorityFactory getBackingStore() throws FactoryException {
/* 130 */     if (this.backingStore == null)
/* 131 */       synchronized (this) {
/* 132 */         if (this.backingStore == null) {
/* 133 */           this.backingStore = createBackingStore();
/* 134 */           if (this.backingStore == null)
/* 135 */             throw new FactoryNotFoundException(Errors.format(131)); 
/* 137 */           completeHints();
/*     */         } 
/*     */       }  
/* 141 */     this.used = true;
/* 142 */     return this.backingStore;
/*     */   }
/*     */   
/*     */   public synchronized boolean isConnected() {
/* 162 */     return (this.backingStore != null);
/*     */   }
/*     */   
/*     */   public synchronized void setTimeout(long delay) {
/* 177 */     if (TIMER == null)
/*     */       return; 
/* 180 */     if (this.disposer != null)
/* 181 */       this.disposer.cancel(); 
/* 183 */     this.disposer = new Disposer();
/* 184 */     TIMER.schedule(this.disposer, delay, delay);
/*     */   }
/*     */   
/*     */   protected boolean canDisposeBackingStore(AbstractAuthorityFactory backingStore) {
/* 197 */     return true;
/*     */   }
/*     */   
/*     */   public synchronized void dispose() throws FactoryException {
/* 206 */     if (this.disposer != null) {
/* 207 */       this.disposer.cancel();
/* 208 */       this.disposer = null;
/*     */     } 
/* 210 */     super.dispose();
/*     */   }
/*     */   
/*     */   public static void exit() {
/* 217 */     if (TIMER != null) {
/* 218 */       TIMER.cancel();
/* 219 */       TIMER = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected synchronized void disposeBackingStore() {
/*     */     try {
/* 229 */       if (this.backingStore != null) {
/* 230 */         LOGGER.log(Level.INFO, "Disposing " + getClass() + " backing store");
/* 231 */         this.backingStore.dispose();
/* 232 */         this.backingStore = null;
/*     */       } 
/* 234 */     } catch (FactoryException exception) {
/* 235 */       this.backingStore = null;
/* 236 */       LogRecord record = Loggings.format(Level.WARNING, 7);
/* 238 */       record.setSourceMethodName("run");
/* 239 */       record.setSourceClassName(Disposer.class.getName());
/* 240 */       record.setThrown((Throwable)exception);
/* 241 */       record.setLoggerName(LOGGER.getName());
/* 242 */       LOGGER.log(record);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract AbstractAuthorityFactory createBackingStore() throws FactoryException;
/*     */   
/*     */   private final class Disposer extends TimerTask {
/*     */     private Disposer() {}
/*     */     
/*     */     public void run() {
/* 251 */       synchronized (DeferredAuthorityFactory.this) {
/* 252 */         if (DeferredAuthorityFactory.this.used || !DeferredAuthorityFactory.this.canDisposeBackingStore(DeferredAuthorityFactory.this.backingStore)) {
/* 253 */           DeferredAuthorityFactory.this.used = false;
/*     */           return;
/*     */         } 
/* 256 */         if (cancel()) {
/* 257 */           DeferredAuthorityFactory.this.disposer = null;
/* 258 */           if (DeferredAuthorityFactory.this.backingStore != null)
/* 259 */             DeferredAuthorityFactory.this.disposeBackingStore(); 
/* 262 */           DeferredAuthorityFactory.this.hints.remove(Hints.DATUM_AUTHORITY_FACTORY);
/* 263 */           DeferredAuthorityFactory.this.hints.remove(Hints.CS_AUTHORITY_FACTORY);
/* 264 */           DeferredAuthorityFactory.this.hints.remove(Hints.CRS_AUTHORITY_FACTORY);
/* 265 */           DeferredAuthorityFactory.this.hints.remove(Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\DeferredAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */