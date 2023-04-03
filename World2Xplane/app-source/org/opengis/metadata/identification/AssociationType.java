/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "DS_AssociationTypeCode", specification = Specification.ISO_19115)
/*     */ public final class AssociationType extends CodeList<AssociationType> {
/*     */   private static final long serialVersionUID = 6031427859661710114L;
/*     */   
/*  43 */   private static final List<AssociationType> VALUES = new ArrayList<AssociationType>(5);
/*     */   
/*     */   @UML(identifier = "crossReference", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final AssociationType CROSS_REFERENCE = new AssociationType("CROSS_REFERENCE");
/*     */   
/*     */   @UML(identifier = "largerWorkCitation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final AssociationType LARGER_WORD_CITATION = new AssociationType("LARGER_WORD_CITATION");
/*     */   
/*     */   @UML(identifier = "partOfSeamlessDatabase", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final AssociationType PART_OF_SEAMLESS_DATABASE = new AssociationType("PART_OF_SEAMLESS_DATABASE");
/*     */   
/*     */   @UML(identifier = "source", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final AssociationType SOURCE = new AssociationType("SOURCE");
/*     */   
/*     */   @UML(identifier = "stereoMate", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final AssociationType STEREO_MATE = new AssociationType("STEREO_MATE");
/*     */   
/*     */   private AssociationType(String name) {
/*  82 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static AssociationType[] values() {
/*  91 */     synchronized (VALUES) {
/*  92 */       return VALUES.<AssociationType>toArray(new AssociationType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public AssociationType[] family() {
/* 100 */     return values();
/*     */   }
/*     */   
/*     */   public static AssociationType valueOf(String code) {
/* 111 */     return (AssociationType)valueOf(AssociationType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\AssociationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */