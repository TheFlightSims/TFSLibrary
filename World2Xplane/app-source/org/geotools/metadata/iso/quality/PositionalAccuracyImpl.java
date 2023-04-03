/*     */ package org.geotools.metadata.iso.quality;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.quality.Element;
/*     */ import org.opengis.metadata.quality.EvaluationMethodType;
/*     */ import org.opengis.metadata.quality.PositionalAccuracy;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class PositionalAccuracyImpl extends ElementImpl implements PositionalAccuracy {
/*     */   private static final long serialVersionUID = 6043381860937480828L;
/*     */   
/*     */   public static final PositionalAccuracy DATUM_SHIFT_APPLIED;
/*     */   
/*     */   public static final PositionalAccuracy DATUM_SHIFT_OMITTED;
/*     */   
/*     */   static {
/*  75 */     SimpleInternationalString simpleInternationalString1 = new SimpleInternationalString("Transformation accuracy");
/*  76 */     SimpleInternationalString simpleInternationalString2 = new SimpleInternationalString("Is a datum shift method applied?");
/*  77 */     ConformanceResultImpl pass = new ConformanceResultImpl(Citations.GEOTOOLS, (InternationalString)simpleInternationalString2, true);
/*  78 */     ConformanceResultImpl fail = new ConformanceResultImpl(Citations.GEOTOOLS, (InternationalString)simpleInternationalString2, false);
/*  79 */     pass.freeze();
/*  80 */     fail.freeze();
/*  82 */     PositionalAccuracyImpl APPLIED = new AbsoluteExternalPositionalAccuracyImpl(pass);
/*  83 */     PositionalAccuracyImpl OMITTED = new AbsoluteExternalPositionalAccuracyImpl(fail);
/*  84 */     APPLIED.setMeasureDescription((InternationalString)simpleInternationalString1);
/*  85 */     OMITTED.setMeasureDescription((InternationalString)simpleInternationalString1);
/*  86 */     APPLIED.setEvaluationMethodDescription((InternationalString)simpleInternationalString2);
/*  87 */     OMITTED.setEvaluationMethodDescription((InternationalString)simpleInternationalString2);
/*  88 */     APPLIED.setEvaluationMethodType(EvaluationMethodType.DIRECT_INTERNAL);
/*  89 */     OMITTED.setEvaluationMethodType(EvaluationMethodType.DIRECT_INTERNAL);
/*  90 */     APPLIED.freeze();
/*  91 */     OMITTED.freeze();
/*     */   }
/*     */   
/*     */   public PositionalAccuracyImpl() {}
/*     */   
/*     */   public PositionalAccuracyImpl(PositionalAccuracy source) {
/* 106 */     super((Element)source);
/*     */   }
/*     */   
/*     */   public PositionalAccuracyImpl(Result result) {
/* 113 */     super(result);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\quality\PositionalAccuracyImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */