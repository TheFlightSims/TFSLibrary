/*     */ package org.opengis.geometry.coordinate;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "GM_SplineCurveForm", specification = Specification.ISO_19107)
/*     */ public final class SplineCurveForm extends CodeList<SplineCurveForm> {
/*     */   private static final long serialVersionUID = 7692137703533158212L;
/*     */   
/*  43 */   private static final List<SplineCurveForm> VALUES = new ArrayList<SplineCurveForm>(5);
/*     */   
/*     */   @UML(identifier = "polylineForm", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  49 */   public static final SplineCurveForm POLYLINE_FORM = new SplineCurveForm("POLYLINE_FORM");
/*     */   
/*     */   @UML(identifier = "circularArc", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  56 */   public static final SplineCurveForm CIRCULAR_ARC = new SplineCurveForm("CIRCULAR_ARC");
/*     */   
/*     */   @UML(identifier = "ellipticalArc", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  63 */   public static final SplineCurveForm ELLIPTICAL_ARC = new SplineCurveForm("ELLIPTICAL_ARC");
/*     */   
/*     */   @UML(identifier = "parabolicArc", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  70 */   public static final SplineCurveForm PARABOLIC_ARC = new SplineCurveForm("PARABOLIC_ARC");
/*     */   
/*     */   @UML(identifier = "hyperbolicArc", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  77 */   public static final SplineCurveForm HYPERBOLIC_ARC = new SplineCurveForm("HYPERBOLIC_ARC");
/*     */   
/*     */   private SplineCurveForm(String name) {
/*  87 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static SplineCurveForm[] values() {
/*  96 */     synchronized (VALUES) {
/*  97 */       return VALUES.<SplineCurveForm>toArray(new SplineCurveForm[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SplineCurveForm[] family() {
/* 105 */     return values();
/*     */   }
/*     */   
/*     */   public static SplineCurveForm valueOf(String code) {
/* 116 */     return (SplineCurveForm)valueOf(SplineCurveForm.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\SplineCurveForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */