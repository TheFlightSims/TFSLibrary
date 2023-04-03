/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ 
/*     */ public abstract class DirectAuthorityFactory extends AbstractAuthorityFactory {
/*     */   protected final ReferencingFactoryContainer factories;
/*     */   
/*     */   private boolean hintsInitialized;
/*     */   
/*     */   protected DirectAuthorityFactory(ReferencingFactoryContainer factories, int priority) {
/*  75 */     super(priority);
/*  76 */     this.factories = factories;
/*  77 */     ensureNonNull("factories", factories);
/*     */   }
/*     */   
/*     */   protected DirectAuthorityFactory(Hints hints, int priority) {
/*  91 */     super(priority);
/*  92 */     this.factories = ReferencingFactoryContainer.instance(hints);
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 105 */     synchronized (this.hints) {
/* 106 */       if (!this.hintsInitialized) {
/* 107 */         this.hintsInitialized = true;
/* 108 */         this.hints.putAll(this.factories.getImplementationHints());
/*     */       } 
/*     */     } 
/* 111 */     return super.getImplementationHints();
/*     */   }
/*     */   
/*     */   Collection<? super AuthorityFactory> dependencies() {
/* 119 */     if (this.factories != null) {
/* 120 */       Set<Object> dependencies = new LinkedHashSet(8);
/* 121 */       dependencies.add(this.factories.getCRSFactory());
/* 122 */       dependencies.add(this.factories.getCSFactory());
/* 123 */       dependencies.add(this.factories.getDatumFactory());
/* 124 */       return dependencies;
/*     */     } 
/* 126 */     return super.dependencies();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\DirectAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */