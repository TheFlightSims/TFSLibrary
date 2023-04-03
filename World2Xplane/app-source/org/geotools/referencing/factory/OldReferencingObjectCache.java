/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class OldReferencingObjectCache {
/*  47 */   private final LinkedHashMap pool = new LinkedHashMap<Object, Object>(32, 0.75F, true);
/*     */   
/*     */   private final int maxStrongReferences;
/*     */   
/*     */   public OldReferencingObjectCache(int maxStrongReferences) {
/*  61 */     this.maxStrongReferences = maxStrongReferences;
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/*  68 */     if (this.pool != null)
/*  69 */       this.pool.clear(); 
/*     */   }
/*     */   
/*     */   public Object get(Object key) {
/*  83 */     Object object = this.pool.get(key);
/*  84 */     if (object instanceof Reference)
/*  85 */       object = ((Reference)object).get(); 
/*  87 */     return object;
/*     */   }
/*     */   
/*     */   public void put(Object key, Object object) {
/* 102 */     this.pool.put(key, object);
/* 103 */     int toReplace = this.pool.size() - this.maxStrongReferences;
/* 104 */     if (toReplace > 0)
/* 105 */       for (Iterator<Map.Entry> it = this.pool.entrySet().iterator(); it.hasNext(); ) {
/* 106 */         Map.Entry entry = it.next();
/* 107 */         Object value = entry.getValue();
/* 108 */         if (value instanceof Reference) {
/* 109 */           if (((Reference)value).get() == null)
/* 110 */             it.remove(); 
/*     */           continue;
/*     */         } 
/* 114 */         entry.setValue(new WeakReference(value));
/* 115 */         if (--toReplace == 0)
/*     */           break; 
/*     */       }  
/*     */   }
/*     */   
/*     */   public void writeLock(Object key) {}
/*     */   
/*     */   public void writeUnLock(Object key) {}
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   public Object test(Object key) {
/* 133 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\OldReferencingObjectCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */