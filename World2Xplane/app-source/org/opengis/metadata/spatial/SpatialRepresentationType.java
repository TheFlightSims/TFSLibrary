/*     */ package org.opengis.metadata.spatial;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.ComplianceLevel;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Profile;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_SpatialRepresentationTypeCode", specification = Specification.ISO_19115)
/*     */ @Profile(level = ComplianceLevel.CORE)
/*     */ public final class SpatialRepresentationType extends CodeList<SpatialRepresentationType> {
/*     */   private static final long serialVersionUID = 4790487150664264363L;
/*     */   
/*  45 */   private static final List<SpatialRepresentationType> VALUES = new ArrayList<SpatialRepresentationType>(6);
/*     */   
/*     */   @UML(identifier = "vector", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  51 */   public static final SpatialRepresentationType VECTOR = new SpatialRepresentationType("VECTOR");
/*     */   
/*     */   @UML(identifier = "grid", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  57 */   public static final SpatialRepresentationType GRID = new SpatialRepresentationType("GRID");
/*     */   
/*     */   @UML(identifier = "textTable", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  63 */   public static final SpatialRepresentationType TEXT_TABLE = new SpatialRepresentationType("TEXT_TABLE");
/*     */   
/*     */   @UML(identifier = "tin", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  69 */   public static final SpatialRepresentationType TIN = new SpatialRepresentationType("TIN");
/*     */   
/*     */   @UML(identifier = "stereoModel", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  76 */   public static final SpatialRepresentationType STEREO_MODEL = new SpatialRepresentationType("STEREO_MODEL");
/*     */   
/*     */   @UML(identifier = "video", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  82 */   public static final SpatialRepresentationType VIDEO = new SpatialRepresentationType("VIDEO");
/*     */   
/*     */   private SpatialRepresentationType(String name) {
/*  91 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static SpatialRepresentationType[] values() {
/* 100 */     synchronized (VALUES) {
/* 101 */       return VALUES.<SpatialRepresentationType>toArray(new SpatialRepresentationType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SpatialRepresentationType[] family() {
/* 109 */     return values();
/*     */   }
/*     */   
/*     */   public static SpatialRepresentationType valueOf(String code) {
/* 120 */     return (SpatialRepresentationType)valueOf(SpatialRepresentationType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\SpatialRepresentationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */