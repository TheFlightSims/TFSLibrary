/*     */ package org.geotools.metadata.iso.lineage;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.lineage.Lineage;
/*     */ import org.opengis.metadata.lineage.ProcessStep;
/*     */ import org.opengis.metadata.lineage.Source;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class LineageImpl extends MetadataEntity implements Lineage {
/*     */   private static final long serialVersionUID = 3351230301999744987L;
/*     */   
/*     */   private InternationalString statement;
/*     */   
/*     */   private Collection<ProcessStep> processSteps;
/*     */   
/*     */   private Collection<Source> sources;
/*     */   
/*     */   public LineageImpl() {}
/*     */   
/*     */   public LineageImpl(Lineage source) {
/*  81 */     super(source);
/*     */   }
/*     */   
/*     */   public InternationalString getStatement() {
/*  90 */     return this.statement;
/*     */   }
/*     */   
/*     */   public synchronized void setStatement(InternationalString newValue) {
/*  98 */     checkWritePermission();
/*  99 */     this.statement = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ProcessStep> getProcessSteps() {
/* 107 */     return this.processSteps = nonNullCollection(this.processSteps, ProcessStep.class);
/*     */   }
/*     */   
/*     */   public synchronized void setProcessSteps(Collection<? extends ProcessStep> newValues) {
/* 115 */     this.processSteps = copyCollection(newValues, this.processSteps, ProcessStep.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Source> getSources() {
/* 122 */     return this.sources = nonNullCollection(this.sources, Source.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSources(Collection<? extends Source> newValues) {
/* 129 */     this.sources = copyCollection(newValues, this.sources, Source.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\lineage\LineageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */