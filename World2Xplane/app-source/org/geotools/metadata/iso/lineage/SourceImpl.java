/*     */ package org.geotools.metadata.iso.lineage;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.metadata.identification.RepresentativeFraction;
/*     */ import org.opengis.metadata.lineage.ProcessStep;
/*     */ import org.opengis.metadata.lineage.Source;
/*     */ import org.opengis.referencing.ReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class SourceImpl extends MetadataEntity implements Source {
/*     */   private static final long serialVersionUID = 2660914446466438044L;
/*     */   
/*     */   private InternationalString description;
/*     */   
/*     */   private RepresentativeFraction scaleDenominator;
/*     */   
/*     */   private ReferenceSystem sourceReferenceSystem;
/*     */   
/*     */   private Citation sourceCitation;
/*     */   
/*     */   private Collection<Extent> sourceExtents;
/*     */   
/*     */   private Collection<ProcessStep> sourceSteps;
/*     */   
/*     */   public SourceImpl() {}
/*     */   
/*     */   public SourceImpl(Source source) {
/*  93 */     super(source);
/*     */   }
/*     */   
/*     */   public SourceImpl(InternationalString description) {
/* 100 */     setDescription(description);
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 107 */     return this.description;
/*     */   }
/*     */   
/*     */   public synchronized void setDescription(InternationalString newValue) {
/* 114 */     checkWritePermission();
/* 115 */     this.description = newValue;
/*     */   }
/*     */   
/*     */   public synchronized RepresentativeFraction getScaleDenominator() {
/* 122 */     return this.scaleDenominator;
/*     */   }
/*     */   
/*     */   public synchronized void setScaleDenominator(RepresentativeFraction newValue) {
/* 131 */     checkWritePermission();
/* 132 */     this.scaleDenominator = newValue;
/*     */   }
/*     */   
/*     */   public ReferenceSystem getSourceReferenceSystem() {
/* 141 */     return this.sourceReferenceSystem;
/*     */   }
/*     */   
/*     */   public synchronized void setSourceReferenceSystem(ReferenceSystem newValue) {
/* 148 */     checkWritePermission();
/* 149 */     this.sourceReferenceSystem = newValue;
/*     */   }
/*     */   
/*     */   public Citation getSourceCitation() {
/* 156 */     return this.sourceCitation;
/*     */   }
/*     */   
/*     */   public synchronized void setSourceCitation(Citation newValue) {
/* 163 */     checkWritePermission();
/* 164 */     this.sourceCitation = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Extent> getSourceExtents() {
/* 172 */     return this.sourceExtents = nonNullCollection(this.sourceExtents, Extent.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSourceExtents(Collection<? extends Extent> newValues) {
/* 179 */     this.sourceExtents = copyCollection(newValues, this.sourceExtents, Extent.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ProcessStep> getSourceSteps() {
/* 186 */     return this.sourceSteps = nonNullCollection(this.sourceSteps, ProcessStep.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSourceSteps(Collection<? extends ProcessStep> newValues) {
/* 193 */     this.sourceSteps = copyCollection(newValues, this.sourceSteps, ProcessStep.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\lineage\SourceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */