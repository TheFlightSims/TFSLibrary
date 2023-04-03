/*     */ package org.geotools.metadata.iso.lineage;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.lineage.ProcessStep;
/*     */ import org.opengis.metadata.lineage.Source;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ProcessStepImpl extends MetadataEntity implements ProcessStep {
/*     */   private static final long serialVersionUID = 4629429337326490722L;
/*     */   
/*     */   private InternationalString description;
/*     */   
/*     */   private InternationalString rationale;
/*     */   
/*     */   private long date;
/*     */   
/*     */   private Collection<ResponsibleParty> processors;
/*     */   
/*     */   private Collection<Source> sources;
/*     */   
/*     */   public ProcessStepImpl() {}
/*     */   
/*     */   public ProcessStepImpl(ProcessStep source) {
/*  91 */     super(source);
/*     */   }
/*     */   
/*     */   public ProcessStepImpl(InternationalString description) {
/*  98 */     setDescription(description);
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 105 */     return this.description;
/*     */   }
/*     */   
/*     */   public synchronized void setDescription(InternationalString newValue) {
/* 112 */     checkWritePermission();
/* 113 */     this.description = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getRationale() {
/* 120 */     return this.rationale;
/*     */   }
/*     */   
/*     */   public synchronized void setRationale(InternationalString newValue) {
/* 127 */     checkWritePermission();
/* 128 */     this.rationale = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Date getDate() {
/* 136 */     return (this.date != Long.MIN_VALUE) ? new Date(this.date) : null;
/*     */   }
/*     */   
/*     */   public synchronized void setDate(Date newValue) {
/* 144 */     checkWritePermission();
/* 145 */     this.date = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getProcessors() {
/* 153 */     return this.processors = nonNullCollection(this.processors, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setProcessors(Collection<? extends ResponsibleParty> newValues) {
/* 161 */     this.processors = copyCollection(newValues, this.processors, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Source> getSources() {
/* 169 */     return this.sources = nonNullCollection(this.sources, Source.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSources(Collection<? extends Source> newValues) {
/* 176 */     this.sources = copyCollection(newValues, this.sources, Source.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\lineage\ProcessStepImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */