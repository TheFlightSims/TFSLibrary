/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.XArray;
/*     */ 
/*     */ final class FactoryIteratorProviders {
/*  39 */   private static final FactoryIteratorProviders GLOBAL = new FactoryIteratorProviders();
/*     */   
/*  44 */   private int modifications = 0;
/*     */   
/*     */   private Set<FactoryIteratorProvider> iteratorProviders;
/*     */   
/*     */   final FactoryIteratorProvider[] synchronizeIteratorProviders() {
/*  68 */     FactoryIteratorProvider[] newProviders = null;
/*  69 */     int count = 0;
/*  70 */     synchronized (GLOBAL) {
/*  71 */       if (this.modifications == GLOBAL.modifications)
/*  72 */         return null; 
/*  74 */       this.modifications = GLOBAL.modifications;
/*  75 */       if (GLOBAL.iteratorProviders == null)
/*  80 */         throw new AssertionError(this.modifications); 
/*  87 */       if (this.iteratorProviders != null) {
/*  88 */         this.iteratorProviders.retainAll(GLOBAL.iteratorProviders);
/*  89 */       } else if (!GLOBAL.iteratorProviders.isEmpty()) {
/*  90 */         this.iteratorProviders = new LinkedHashSet<FactoryIteratorProvider>();
/*     */       } 
/*  98 */       int remaining = GLOBAL.iteratorProviders.size();
/*  99 */       for (Iterator<FactoryIteratorProvider> it = GLOBAL.iteratorProviders.iterator(); it.hasNext(); ) {
/* 100 */         FactoryIteratorProvider candidate = it.next();
/* 101 */         if (this.iteratorProviders.add(candidate)) {
/* 102 */           if (newProviders == null)
/* 103 */             newProviders = new FactoryIteratorProvider[remaining]; 
/* 105 */           newProviders[count++] = candidate;
/*     */         } 
/* 107 */         remaining--;
/*     */       } 
/*     */     } 
/* 111 */     return (FactoryIteratorProvider[])XArray.resize((Object[])newProviders, count);
/*     */   }
/*     */   
/*     */   public static void addFactoryIteratorProvider(FactoryIteratorProvider provider) {
/* 123 */     synchronized (GLOBAL) {
/* 124 */       if (GLOBAL.iteratorProviders == null)
/* 125 */         GLOBAL.iteratorProviders = new LinkedHashSet<FactoryIteratorProvider>(); 
/* 127 */       if (GLOBAL.iteratorProviders.add(provider))
/* 128 */         GLOBAL.modifications++; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void removeFactoryIteratorProvider(FactoryIteratorProvider provider) {
/* 139 */     synchronized (GLOBAL) {
/* 140 */       if (GLOBAL.iteratorProviders != null && 
/* 141 */         GLOBAL.iteratorProviders.remove(provider))
/* 142 */         GLOBAL.modifications++; 
/*     */     } 
/*     */   }
/*     */   
/*     */   static FactoryIteratorProvider[] getIteratorProviders() {
/* 153 */     synchronized (GLOBAL) {
/* 154 */       if (GLOBAL.iteratorProviders == null)
/* 155 */         return new FactoryIteratorProvider[0]; 
/* 157 */       return GLOBAL.iteratorProviders.<FactoryIteratorProvider>toArray(new FactoryIteratorProvider[GLOBAL.iteratorProviders.size()]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\FactoryIteratorProviders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */