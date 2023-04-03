/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.metadata.maintenance.ScopeCode;
/*     */ import org.opengis.metadata.maintenance.ScopeDescription;
/*     */ import org.opengis.metadata.quality.Scope;
/*     */ 
/*     */ public class ScopeImpl extends MetadataEntity implements Scope {
/*     */   private static final long serialVersionUID = -8021256328527422972L;
/*     */   
/*     */   private ScopeCode level;
/*     */   
/*     */   private Extent extent;
/*     */   
/*     */   private Collection<ScopeDescription> levelDescription;
/*     */   
/*     */   public ScopeImpl() {}
/*     */   
/*     */   public ScopeImpl(Scope source) {
/*  77 */     super(source);
/*     */   }
/*     */   
/*     */   public ScopeImpl(ScopeCode level) {
/*  84 */     setLevel(level);
/*     */   }
/*     */   
/*     */   public ScopeCode getLevel() {
/*  91 */     return this.level;
/*     */   }
/*     */   
/*     */   public synchronized void setLevel(ScopeCode newValue) {
/*  98 */     checkWritePermission();
/*  99 */     this.level = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ScopeDescription> getLevelDescription() {
/* 110 */     return this.levelDescription = nonNullCollection(this.levelDescription, ScopeDescription.class);
/*     */   }
/*     */   
/*     */   public synchronized void setLevelDescription(Collection<? extends ScopeDescription> newValues) {
/* 121 */     this.levelDescription = copyCollection(newValues, this.levelDescription, ScopeDescription.class);
/*     */   }
/*     */   
/*     */   public Extent getExtent() {
/* 129 */     return this.extent;
/*     */   }
/*     */   
/*     */   public synchronized void setExtent(Extent newValue) {
/* 137 */     checkWritePermission();
/* 138 */     this.extent = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\ScopeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */