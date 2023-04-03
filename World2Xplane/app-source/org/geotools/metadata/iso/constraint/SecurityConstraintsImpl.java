/*     */ package org.geotools.metadata.iso.constraint;
/*     */ 
/*     */ import org.opengis.metadata.constraint.Classification;
/*     */ import org.opengis.metadata.constraint.Constraints;
/*     */ import org.opengis.metadata.constraint.SecurityConstraints;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class SecurityConstraintsImpl extends ConstraintsImpl implements SecurityConstraints {
/*     */   private static final long serialVersionUID = 6412833018607679734L;
/*     */   
/*     */   private Classification classification;
/*     */   
/*     */   private InternationalString userNote;
/*     */   
/*     */   private InternationalString classificationSystem;
/*     */   
/*     */   private InternationalString handlingDescription;
/*     */   
/*     */   public SecurityConstraintsImpl() {}
/*     */   
/*     */   public SecurityConstraintsImpl(SecurityConstraints source) {
/*  78 */     super((Constraints)source);
/*     */   }
/*     */   
/*     */   public SecurityConstraintsImpl(Classification classification) {
/*  85 */     setClassification(classification);
/*     */   }
/*     */   
/*     */   public Classification getClassification() {
/*  92 */     return this.classification;
/*     */   }
/*     */   
/*     */   public synchronized void setClassification(Classification newValue) {
/*  99 */     checkWritePermission();
/* 100 */     this.classification = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getUserNote() {
/* 108 */     return this.userNote;
/*     */   }
/*     */   
/*     */   public synchronized void setUserNote(InternationalString newValue) {
/* 116 */     checkWritePermission();
/* 117 */     this.userNote = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getClassificationSystem() {
/* 124 */     return this.classificationSystem;
/*     */   }
/*     */   
/*     */   public synchronized void setClassificationSystem(InternationalString newValue) {
/* 131 */     checkWritePermission();
/* 132 */     this.classificationSystem = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getHandlingDescription() {
/* 139 */     return this.handlingDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setHandlingDescription(InternationalString newValue) {
/* 146 */     checkWritePermission();
/* 147 */     this.handlingDescription = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\constraint\SecurityConstraintsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */