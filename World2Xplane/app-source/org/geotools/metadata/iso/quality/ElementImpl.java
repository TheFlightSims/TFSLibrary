/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.quality.Element;
/*     */ import org.opengis.metadata.quality.EvaluationMethodType;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ElementImpl extends MetadataEntity implements Element {
/*     */   private static final long serialVersionUID = -3542504624077298894L;
/*     */   
/*     */   private Collection<InternationalString> namesOfMeasure;
/*     */   
/*     */   private Identifier measureIdentification;
/*     */   
/*     */   private InternationalString measureDescription;
/*     */   
/*     */   private EvaluationMethodType evaluationMethodType;
/*     */   
/*     */   private InternationalString evaluationMethodDescription;
/*     */   
/*     */   private Citation evaluationProcedure;
/*     */   
/*  93 */   private long date1 = Long.MIN_VALUE;
/*     */   
/*  93 */   private long date2 = Long.MIN_VALUE;
/*     */   
/*     */   private Collection<Result> results;
/*     */   
/*     */   public ElementImpl() {}
/*     */   
/*     */   public ElementImpl(Element source) {
/* 114 */     super(source);
/*     */   }
/*     */   
/*     */   public ElementImpl(Result result) {
/* 121 */     setResults(Collections.singleton(result));
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getNamesOfMeasure() {
/* 128 */     return this.namesOfMeasure = nonNullCollection(this.namesOfMeasure, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setNamesOfMeasure(Collection<? extends InternationalString> newValues) {
/* 137 */     this.namesOfMeasure = copyCollection(newValues, this.namesOfMeasure, InternationalString.class);
/*     */   }
/*     */   
/*     */   public Identifier getMeasureIdentification() {
/* 144 */     return this.measureIdentification;
/*     */   }
/*     */   
/*     */   public synchronized void setMeasureIdentification(Identifier newValue) {
/* 151 */     checkWritePermission();
/* 152 */     this.measureIdentification = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getMeasureDescription() {
/* 159 */     return this.measureDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setMeasureDescription(InternationalString newValue) {
/* 166 */     checkWritePermission();
/* 167 */     this.measureDescription = newValue;
/*     */   }
/*     */   
/*     */   public EvaluationMethodType getEvaluationMethodType() {
/* 175 */     return this.evaluationMethodType;
/*     */   }
/*     */   
/*     */   public synchronized void setEvaluationMethodType(EvaluationMethodType newValue) {
/* 182 */     checkWritePermission();
/* 183 */     this.evaluationMethodType = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getEvaluationMethodDescription() {
/* 190 */     return this.evaluationMethodDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setEvaluationMethodDescription(InternationalString newValue) {
/* 197 */     checkWritePermission();
/* 198 */     this.evaluationMethodDescription = newValue;
/*     */   }
/*     */   
/*     */   public Citation getEvaluationProcedure() {
/* 205 */     return this.evaluationProcedure;
/*     */   }
/*     */   
/*     */   public synchronized void setEvaluationProcedure(Citation newValue) {
/* 212 */     checkWritePermission();
/* 213 */     this.evaluationProcedure = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Date> getDates() {
/* 224 */     if (this.date1 == Long.MIN_VALUE)
/* 225 */       return Collections.emptyList(); 
/* 227 */     if (this.date2 == Long.MIN_VALUE)
/* 228 */       return Collections.singleton(new Date(this.date1)); 
/* 230 */     return Arrays.asList(new Date[] { new Date(this.date1), new Date(this.date2) });
/*     */   }
/*     */   
/*     */   public void setDates(Collection<Date> newValues) {
/* 242 */     checkWritePermission();
/* 243 */     this.date1 = this.date2 = Long.MIN_VALUE;
/* 244 */     Iterator<Date> it = newValues.iterator();
/* 245 */     if (it.hasNext()) {
/* 246 */       this.date1 = ((Date)it.next()).getTime();
/* 247 */       if (it.hasNext()) {
/* 248 */         this.date2 = ((Date)it.next()).getTime();
/* 249 */         if (it.hasNext())
/* 250 */           throw new IllegalArgumentException(Errors.format(91)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Collection<Result> getResults() {
/* 265 */     return this.results = nonNullCollection(this.results, Result.class);
/*     */   }
/*     */   
/*     */   public synchronized void setResults(Collection<? extends Result> newValues) {
/* 276 */     this.results = copyCollection(newValues, this.results, Result.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\ElementImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */