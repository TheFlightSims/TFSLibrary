/*     */ package org.opengis.geometry.primitive;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "GM_CurveInterpolation", specification = Specification.ISO_19107)
/*     */ public final class CurveInterpolation extends CodeList<CurveInterpolation> {
/*     */   private static final long serialVersionUID = 170309206092641598L;
/*     */   
/*  47 */   private static final List<CurveInterpolation> VALUES = new ArrayList<CurveInterpolation>(10);
/*     */   
/*     */   @UML(identifier = "linear", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  54 */   public static final CurveInterpolation LINEAR = new CurveInterpolation("LINEAR");
/*     */   
/*     */   @UML(identifier = "geodesic", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  63 */   public static final CurveInterpolation GEODESIC = new CurveInterpolation("GEODESIC");
/*     */   
/*     */   @UML(identifier = "circularArc3Points", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  74 */   public static final CurveInterpolation CIRCULAR_ARC_3_POINTS = new CurveInterpolation("CIRCULAR_ARC_3_POINTS");
/*     */   
/*     */   @UML(identifier = "circularArc2PointWithBulge", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  86 */   public static final CurveInterpolation CIRCULAR_ARC_2_POINTS_WITH_BULGE = new CurveInterpolation("CIRCULAR_ARC_2_POINTS_WITH_BULGE");
/*     */   
/*     */   @UML(identifier = "elliptical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  97 */   public static final CurveInterpolation ELLIPTICAL = new CurveInterpolation("ELLIPTICAL");
/*     */   
/*     */   @UML(identifier = "clothoid", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 104 */   public static final CurveInterpolation CLOTHOID = new CurveInterpolation("CLOTHOID");
/*     */   
/*     */   @UML(identifier = "conic", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 112 */   public static final CurveInterpolation CONIC = new CurveInterpolation("CONIC");
/*     */   
/*     */   @UML(identifier = "polynomialSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 120 */   public static final CurveInterpolation POLYNOMIAL_SPLINE = new CurveInterpolation("POLYNOMIAL_SPLINE");
/*     */   
/*     */   @UML(identifier = "cubicSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 128 */   public static final CurveInterpolation CUBIC_SPLINE = new CurveInterpolation("CUBIC_SPLINE");
/*     */   
/*     */   @UML(identifier = "rationalSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 137 */   public static final CurveInterpolation RATIONAL_SPLINE = new CurveInterpolation("RATIONAL_SPLINE");
/*     */   
/*     */   private CurveInterpolation(String name) {
/* 147 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static CurveInterpolation[] values() {
/* 156 */     synchronized (VALUES) {
/* 157 */       return VALUES.<CurveInterpolation>toArray(new CurveInterpolation[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CurveInterpolation[] family() {
/* 165 */     return values();
/*     */   }
/*     */   
/*     */   public static CurveInterpolation valueOf(String code) {
/* 176 */     return (CurveInterpolation)valueOf(CurveInterpolation.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\CurveInterpolation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */