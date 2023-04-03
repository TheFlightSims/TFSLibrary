/*     */ package org.opengis.metadata.spatial;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_DimensionNameTypeCode", specification = Specification.ISO_19115)
/*     */ public final class DimensionNameType extends CodeList<DimensionNameType> {
/*     */   private static final long serialVersionUID = -8534729639298737965L;
/*     */   
/*  43 */   private static final List<DimensionNameType> VALUES = new ArrayList<DimensionNameType>(8);
/*     */   
/*     */   @UML(identifier = "row", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final DimensionNameType ROW = new DimensionNameType("ROW");
/*     */   
/*     */   @UML(identifier = "column", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final DimensionNameType COLUMN = new DimensionNameType("COLUMN");
/*     */   
/*     */   @UML(identifier = "vertical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final DimensionNameType VERTICAL = new DimensionNameType("VERTICAL");
/*     */   
/*     */   @UML(identifier = "track", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final DimensionNameType TRACK = new DimensionNameType("TRACK");
/*     */   
/*     */   @UML(identifier = "crossTrack", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final DimensionNameType CROSS_TRACK = new DimensionNameType("CROSS_TRACK");
/*     */   
/*     */   @UML(identifier = "line", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final DimensionNameType LINE = new DimensionNameType("LINE");
/*     */   
/*     */   @UML(identifier = "sample", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final DimensionNameType SAMPLE = new DimensionNameType("SAMPLE");
/*     */   
/*     */   @UML(identifier = "time", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  91 */   public static final DimensionNameType TIME = new DimensionNameType("TIME");
/*     */   
/*     */   private DimensionNameType(String name) {
/* 100 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static DimensionNameType[] values() {
/* 109 */     synchronized (VALUES) {
/* 110 */       return VALUES.<DimensionNameType>toArray(new DimensionNameType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public DimensionNameType[] family() {
/* 118 */     return values();
/*     */   }
/*     */   
/*     */   public static DimensionNameType valueOf(String code) {
/* 129 */     return (DimensionNameType)valueOf(DimensionNameType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\DimensionNameType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */