/*     */ package org.opengis.metadata.spatial;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_GeometricObjectTypeCode", specification = Specification.ISO_19115)
/*     */ public final class GeometricObjectType extends CodeList<GeometricObjectType> {
/*     */   private static final long serialVersionUID = -8910485325021913980L;
/*     */   
/*  44 */   private static final List<GeometricObjectType> VALUES = new ArrayList<GeometricObjectType>(6);
/*     */   
/*     */   @UML(identifier = "complex", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  53 */   public static final GeometricObjectType COMPLEX = new GeometricObjectType("COMPLEX");
/*     */   
/*     */   @UML(identifier = "composite", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final GeometricObjectType COMPOSITE = new GeometricObjectType("COMPOSITE");
/*     */   
/*     */   @UML(identifier = "curve", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final GeometricObjectType CURVE = new GeometricObjectType("CURVE");
/*     */   
/*     */   @UML(identifier = "point", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final GeometricObjectType POINT = new GeometricObjectType("POINT");
/*     */   
/*     */   @UML(identifier = "solid", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  80 */   public static final GeometricObjectType SOLID = new GeometricObjectType("SOLID");
/*     */   
/*     */   @UML(identifier = "surface", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  87 */   public static final GeometricObjectType SURFACE = new GeometricObjectType("SURFACE");
/*     */   
/*     */   private GeometricObjectType(String name) {
/*  96 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static GeometricObjectType[] values() {
/* 105 */     synchronized (VALUES) {
/* 106 */       return VALUES.<GeometricObjectType>toArray(new GeometricObjectType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeometricObjectType[] family() {
/* 114 */     return values();
/*     */   }
/*     */   
/*     */   public static GeometricObjectType valueOf(String code) {
/* 125 */     return (GeometricObjectType)valueOf(GeometricObjectType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\GeometricObjectType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */