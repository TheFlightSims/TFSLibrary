/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.Factory;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ 
/*     */ public abstract class FeatureLockFactory implements Factory {
/*  46 */   private static FeatureLockFactory factory = null;
/*     */   
/*     */   public static FeatureLockFactory getInstance() throws FactoryRegistryException {
/*  56 */     if (factory == null)
/*  57 */       factory = CommonFactoryFinder.getFeatureLockFactory(null); 
/*  59 */     return factory;
/*     */   }
/*     */   
/*     */   public static FeatureLock generate(long duration) {
/*  81 */     return generate("LockID", duration);
/*     */   }
/*     */   
/*     */   public static FeatureLock generate(String name, long duration) {
/* 101 */     return getInstance().createLock(name, duration);
/*     */   }
/*     */   
/*     */   protected abstract FeatureLock createLock(String paramString, long paramLong);
/*     */   
/*     */   public Map<RenderingHints.Key, Object> getImplementationHints() {
/* 110 */     return Collections.emptyMap();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureLockFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */