/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.quality.ConformanceResult;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ConformanceResultImpl extends ResultImpl implements ConformanceResult {
/*     */   private static final long serialVersionUID = 6429932577869033286L;
/*     */   
/*     */   private Citation specification;
/*     */   
/*     */   private InternationalString explanation;
/*     */   
/*     */   private boolean pass;
/*     */   
/*     */   public ConformanceResultImpl() {}
/*     */   
/*     */   public ConformanceResultImpl(ConformanceResult source) {
/*  74 */     super((Result)source);
/*     */   }
/*     */   
/*     */   public ConformanceResultImpl(Citation specification, InternationalString explanation, boolean pass) {
/*  84 */     setSpecification(specification);
/*  85 */     setExplanation(explanation);
/*  86 */     setPass(pass);
/*     */   }
/*     */   
/*     */   public Citation getSpecification() {
/*  93 */     return this.specification;
/*     */   }
/*     */   
/*     */   public synchronized void setSpecification(Citation newValue) {
/* 101 */     checkWritePermission();
/* 102 */     this.specification = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getExplanation() {
/* 109 */     return this.explanation;
/*     */   }
/*     */   
/*     */   public synchronized void setExplanation(InternationalString newValue) {
/* 116 */     checkWritePermission();
/* 117 */     this.explanation = newValue;
/*     */   }
/*     */   
/*     */   public boolean pass() {
/* 124 */     return this.pass;
/*     */   }
/*     */   
/*     */   public synchronized void setPass(boolean newValue) {
/* 131 */     checkWritePermission();
/* 132 */     this.pass = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\ConformanceResultImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */