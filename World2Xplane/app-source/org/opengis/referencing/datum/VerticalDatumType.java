/*     */ package org.opengis.referencing.datum;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CD_VerticalDatumType", specification = Specification.ISO_19111)
/*     */ public final class VerticalDatumType extends CodeList<VerticalDatumType> {
/*     */   private static final long serialVersionUID = -8161084528823937553L;
/*     */   
/*  42 */   private static final List<VerticalDatumType> VALUES = new ArrayList<VerticalDatumType>(6);
/*     */   
/*     */   @UML(identifier = "other surface", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  51 */   public static final VerticalDatumType OTHER_SURFACE = new VerticalDatumType("OTHER_SURFACE");
/*     */   
/*     */   @UML(identifier = "geoidal", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  59 */   public static final VerticalDatumType GEOIDAL = new VerticalDatumType("GEOIDAL");
/*     */   
/*     */   @UML(identifier = "CS_DatumType.CS_VD_Ellipsoidal", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01009)
/*  86 */   public static final VerticalDatumType ELLIPSOIDAL = new VerticalDatumType("ELLIPSOIDAL");
/*     */   
/*     */   @UML(identifier = "depth", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  97 */   public static final VerticalDatumType DEPTH = new VerticalDatumType("DEPTH");
/*     */   
/*     */   @UML(identifier = "barometric", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/* 108 */   public static final VerticalDatumType BAROMETRIC = new VerticalDatumType("BAROMETRIC");
/*     */   
/*     */   @UML(identifier = "CS_DatumType.CS_VD_Orthometric", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01009)
/* 114 */   public static final VerticalDatumType ORTHOMETRIC = new VerticalDatumType("ORTHOMETRIC");
/*     */   
/*     */   private VerticalDatumType(String name) {
/* 123 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static VerticalDatumType[] values() {
/* 132 */     synchronized (VALUES) {
/* 133 */       return VALUES.<VerticalDatumType>toArray(new VerticalDatumType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalDatumType[] family() {
/* 141 */     return values();
/*     */   }
/*     */   
/*     */   public static VerticalDatumType valueOf(String code) {
/* 152 */     return (VerticalDatumType)valueOf(VerticalDatumType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\VerticalDatumType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */