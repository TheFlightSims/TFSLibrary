/*     */ package org.opengis.geometry.primitive;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "GM_SurfaceInterpolation", specification = Specification.ISO_19107)
/*     */ public final class SurfaceInterpolation extends CodeList<SurfaceInterpolation> {
/*     */   private static final long serialVersionUID = -8717227444427181227L;
/*     */   
/*  42 */   private static final List<SurfaceInterpolation> VALUES = new ArrayList<SurfaceInterpolation>(10);
/*     */   
/*     */   @UML(identifier = "none", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  49 */   public static final SurfaceInterpolation NONE = new SurfaceInterpolation("NONE");
/*     */   
/*     */   @UML(identifier = "planar", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  57 */   public static final SurfaceInterpolation PLANAR = new SurfaceInterpolation("PLANAR");
/*     */   
/*     */   @UML(identifier = "spherical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  64 */   public static final SurfaceInterpolation SPHERICAL = new SurfaceInterpolation("SPHERICAL");
/*     */   
/*     */   @UML(identifier = "elliptical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  71 */   public static final SurfaceInterpolation ELLIPTICAL = new SurfaceInterpolation("ELLIPTICAL");
/*     */   
/*     */   @UML(identifier = "conic", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  78 */   public static final SurfaceInterpolation CONIC = new SurfaceInterpolation("CONIC");
/*     */   
/*     */   @UML(identifier = "tin", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  85 */   public static final SurfaceInterpolation TIN = new SurfaceInterpolation("TIN");
/*     */   
/*     */   @UML(identifier = "parametricCurve", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  93 */   public static final SurfaceInterpolation PARAMETRIC_CURVE = new SurfaceInterpolation("PARAMETRIC_CURVE");
/*     */   
/*     */   @UML(identifier = "polynomialSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 101 */   public static final SurfaceInterpolation POLYNOMIAL_SPLINE = new SurfaceInterpolation("POLYNOMIAL_SPLINE");
/*     */   
/*     */   @UML(identifier = "rationalSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 109 */   public static final SurfaceInterpolation RATIONAL_SPLINE = new SurfaceInterpolation("RATIONAL_SPLINE");
/*     */   
/*     */   @UML(identifier = "triangulatedSpline", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/* 117 */   public static final SurfaceInterpolation TRIANGULATED_SPLINE = new SurfaceInterpolation("TRIANGULATED_SPLINE");
/*     */   
/*     */   private SurfaceInterpolation(String name) {
/* 127 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static SurfaceInterpolation[] values() {
/* 136 */     synchronized (VALUES) {
/* 137 */       return VALUES.<SurfaceInterpolation>toArray(new SurfaceInterpolation[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SurfaceInterpolation[] family() {
/* 145 */     return values();
/*     */   }
/*     */   
/*     */   public static SurfaceInterpolation valueOf(String code) {
/* 156 */     return (SurfaceInterpolation)valueOf(SurfaceInterpolation.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\SurfaceInterpolation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */