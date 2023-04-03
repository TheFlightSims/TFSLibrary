/*     */ package org.opengis.coverage;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CV_SampleDimensionType", specification = Specification.OGC_01004)
/*     */ public final class SampleDimensionType extends CodeList<SampleDimensionType> {
/*     */   private static final long serialVersionUID = -4153433145134818506L;
/*     */   
/*  48 */   private static final List<SampleDimensionType> VALUES = new ArrayList<SampleDimensionType>(11);
/*     */   
/*     */   @UML(identifier = "CV_1BIT", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  57 */   public static final SampleDimensionType UNSIGNED_1BIT = new SampleDimensionType("UNSIGNED_1BIT");
/*     */   
/*     */   @UML(identifier = "CV_2BIT", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  66 */   public static final SampleDimensionType UNSIGNED_2BITS = new SampleDimensionType("UNSIGNED_2BITS");
/*     */   
/*     */   @UML(identifier = "CV_4BIT", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  75 */   public static final SampleDimensionType UNSIGNED_4BITS = new SampleDimensionType("UNSIGNED_4BITS");
/*     */   
/*     */   @UML(identifier = "CV_8BIT_U", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  87 */   public static final SampleDimensionType UNSIGNED_8BITS = new SampleDimensionType("UNSIGNED_8BITS");
/*     */   
/*     */   @UML(identifier = "CV_8BIT_S", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  98 */   public static final SampleDimensionType SIGNED_8BITS = new SampleDimensionType("SIGNED_8BITS");
/*     */   
/*     */   @UML(identifier = "CV_16BIT_U", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 110 */   public static final SampleDimensionType UNSIGNED_16BITS = new SampleDimensionType("UNSIGNED_16BITS");
/*     */   
/*     */   @UML(identifier = "CV_16BIT_S", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 122 */   public static final SampleDimensionType SIGNED_16BITS = new SampleDimensionType("SIGNED_16BITS");
/*     */   
/*     */   @UML(identifier = "CV_32BIT_U", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 133 */   public static final SampleDimensionType UNSIGNED_32BITS = new SampleDimensionType("UNSIGNED_32BITS");
/*     */   
/*     */   @UML(identifier = "CV_32BIT_S", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 145 */   public static final SampleDimensionType SIGNED_32BITS = new SampleDimensionType("SIGNED_32BITS");
/*     */   
/*     */   @UML(identifier = "CV_32BIT_REAL", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 157 */   public static final SampleDimensionType REAL_32BITS = new SampleDimensionType("REAL_32BITS");
/*     */   
/*     */   @UML(identifier = "CV_64BIT_REAL", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 169 */   public static final SampleDimensionType REAL_64BITS = new SampleDimensionType("REAL_64BITS");
/*     */   
/*     */   private SampleDimensionType(String name) {
/* 178 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static SampleDimensionType[] values() {
/* 187 */     synchronized (VALUES) {
/* 188 */       return VALUES.<SampleDimensionType>toArray(new SampleDimensionType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SampleDimensionType[] family() {
/* 196 */     return values();
/*     */   }
/*     */   
/*     */   public static SampleDimensionType valueOf(String code) {
/* 207 */     return (SampleDimensionType)valueOf(SampleDimensionType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\SampleDimensionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */