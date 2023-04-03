/*     */ package org.opengis.geometry.coordinate;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "GM_KnotType", specification = Specification.ISO_19107)
/*     */ public class KnotType extends CodeList<KnotType> {
/*     */   private static final long serialVersionUID = -431722533158166557L;
/*     */   
/*  49 */   private static final List<KnotType> VALUES = new ArrayList<KnotType>(3);
/*     */   
/*     */   @UML(identifier = "uniform", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  55 */   public static final KnotType UNIFORM = new KnotType("UNIFORM");
/*     */   
/*     */   @UML(identifier = "quasiUniform", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  61 */   public static final KnotType QUASI_UNIFORM = new KnotType("QUASI_UNIFORM");
/*     */   
/*     */   @UML(identifier = "piecewiseBezier", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  67 */   public static final KnotType PIECEWISE_BEZIER = new KnotType("PIECEWISE_BEZIER");
/*     */   
/*     */   private KnotType(String name) {
/*  76 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static KnotType[] values() {
/*  85 */     synchronized (VALUES) {
/*  86 */       return VALUES.<KnotType>toArray(new KnotType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public KnotType[] family() {
/*  94 */     return values();
/*     */   }
/*     */   
/*     */   public static KnotType valueOf(String code) {
/* 105 */     return (KnotType)valueOf(KnotType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\KnotType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */