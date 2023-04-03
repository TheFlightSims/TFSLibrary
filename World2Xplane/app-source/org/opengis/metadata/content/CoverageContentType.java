/*    */ package org.opengis.metadata.content;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.opengis.annotation.Obligation;
/*    */ import org.opengis.annotation.Specification;
/*    */ import org.opengis.annotation.UML;
/*    */ import org.opengis.util.CodeList;
/*    */ 
/*    */ @UML(identifier = "MD_CoverageContentTypeCode", specification = Specification.ISO_19115)
/*    */ public final class CoverageContentType extends CodeList<CoverageContentType> {
/*    */   private static final long serialVersionUID = -346887088822021485L;
/*    */   
/* 42 */   private static final List<CoverageContentType> VALUES = new ArrayList<CoverageContentType>(3);
/*    */   
/*    */   @UML(identifier = "image", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 49 */   public static final CoverageContentType IMAGE = new CoverageContentType("IMAGE");
/*    */   
/*    */   @UML(identifier = "thematicClassification", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 55 */   public static final CoverageContentType THEMATIC_CLASSIFICATION = new CoverageContentType("THEMATIC_CLASSIFICATION");
/*    */   
/*    */   @UML(identifier = "physicalMeasurement", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 61 */   public static final CoverageContentType PHYSICAL_MEASUREMENT = new CoverageContentType("PHYSICAL_MEASUREMENT");
/*    */   
/*    */   private CoverageContentType(String name) {
/* 70 */     super(name, VALUES);
/*    */   }
/*    */   
/*    */   public static CoverageContentType[] values() {
/* 79 */     synchronized (VALUES) {
/* 80 */       return VALUES.<CoverageContentType>toArray(new CoverageContentType[VALUES.size()]);
/*    */     } 
/*    */   }
/*    */   
/*    */   public CoverageContentType[] family() {
/* 88 */     return values();
/*    */   }
/*    */   
/*    */   public static CoverageContentType valueOf(String code) {
/* 99 */     return (CoverageContentType)valueOf(CoverageContentType.class, code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\CoverageContentType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */