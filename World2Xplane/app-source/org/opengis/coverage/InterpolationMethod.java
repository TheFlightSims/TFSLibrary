/*     */ package org.opengis.coverage;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CV_InterpolationMethod", specification = Specification.ISO_19123)
/*     */ public class InterpolationMethod extends CodeList<InterpolationMethod> {
/*     */   private static final long serialVersionUID = -4289541167757079847L;
/*     */   
/*  63 */   private static final List<InterpolationMethod> VALUES = new ArrayList<InterpolationMethod>(9);
/*     */   
/*     */   @UML(identifier = "Nearest neighbour", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/*  77 */   public static final InterpolationMethod NEAREST_NEIGHBOUR = new InterpolationMethod("NEAREST_NEIGHBOUR");
/*     */   
/*     */   @UML(identifier = "Linear interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 105 */   public static final InterpolationMethod LINEAR = new InterpolationMethod("LINEAR");
/*     */   
/*     */   @UML(identifier = "Quadratic interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 123 */   public static final InterpolationMethod QUADRATIC = new InterpolationMethod("QUADRATIC");
/*     */   
/*     */   @UML(identifier = "Cubic interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 142 */   public static final InterpolationMethod CUBIC = new InterpolationMethod("CUBIC");
/*     */   
/*     */   @UML(identifier = "Bilinear interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 149 */   public static final InterpolationMethod BILINEAR = new InterpolationMethod("BILINEAR");
/*     */   
/*     */   @UML(identifier = "Biquadratic interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 156 */   public static final InterpolationMethod BIQUADRATIC = new InterpolationMethod("BIQUADRATIC");
/*     */   
/*     */   @UML(identifier = "Bicubic interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 163 */   public static final InterpolationMethod BICUBIC = new InterpolationMethod("BICUBIC");
/*     */   
/*     */   @UML(identifier = "Lost area interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 169 */   public static final InterpolationMethod LOST_AREA = new InterpolationMethod("LOST_AREA");
/*     */   
/*     */   @UML(identifier = "Barycentric interpolation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19123)
/* 175 */   public static final InterpolationMethod BARYCENTRIC = new InterpolationMethod("BARYCENTRIC");
/*     */   
/*     */   private InterpolationMethod(String name) {
/* 184 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static InterpolationMethod[] values() {
/* 193 */     synchronized (VALUES) {
/* 194 */       return VALUES.<InterpolationMethod>toArray(new InterpolationMethod[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InterpolationMethod[] family() {
/* 202 */     return values();
/*     */   }
/*     */   
/*     */   public static InterpolationMethod valueOf(String code) {
/* 213 */     return (InterpolationMethod)valueOf(InterpolationMethod.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\InterpolationMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */