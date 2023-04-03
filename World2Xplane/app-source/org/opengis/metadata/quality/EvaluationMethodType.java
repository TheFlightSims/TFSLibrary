/*     */ package org.opengis.metadata.quality;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "DQ_EvaluationMethodTypeCode", specification = Specification.ISO_19115)
/*     */ public final class EvaluationMethodType extends CodeList<EvaluationMethodType> {
/*     */   private static final long serialVersionUID = -2481257874205996202L;
/*     */   
/*  43 */   private static final List<EvaluationMethodType> VALUES = new ArrayList<EvaluationMethodType>(3);
/*     */   
/*     */   @UML(identifier = "directInternal", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  50 */   public static final EvaluationMethodType DIRECT_INTERNAL = new EvaluationMethodType("DIRECT_INTERNAL");
/*     */   
/*     */   @UML(identifier = "directExternal", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  57 */   public static final EvaluationMethodType DIRECT_EXTERNAL = new EvaluationMethodType("DIRECT_EXTERNAL");
/*     */   
/*     */   @UML(identifier = "indirect", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  63 */   public static final EvaluationMethodType INDIRECT = new EvaluationMethodType("INDIRECT");
/*     */   
/*     */   private EvaluationMethodType(String name) {
/*  72 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static EvaluationMethodType[] values() {
/*  81 */     synchronized (VALUES) {
/*  82 */       return VALUES.<EvaluationMethodType>toArray(new EvaluationMethodType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EvaluationMethodType[] family() {
/*  90 */     return values();
/*     */   }
/*     */   
/*     */   public static EvaluationMethodType valueOf(String code) {
/* 101 */     return (EvaluationMethodType)valueOf(EvaluationMethodType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\EvaluationMethodType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */