/*     */ package org.opengis.geometry.coordinate;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "GM_BSplineSurfaceForm", specification = Specification.ISO_19107)
/*     */ public class BSplineSurfaceForm extends CodeList<BSplineSurfaceForm> {
/*     */   private static final long serialVersionUID = -5066463171878030795L;
/*     */   
/*  43 */   private static final List<BSplineSurfaceForm> VALUES = new ArrayList<BSplineSurfaceForm>(6);
/*     */   
/*     */   @UML(identifier = "planar", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  49 */   public static final BSplineSurfaceForm PLANAR = new BSplineSurfaceForm("PLANAR");
/*     */   
/*     */   @UML(identifier = "cylindrical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  55 */   public static final BSplineSurfaceForm CYLINDRICAL = new BSplineSurfaceForm("CYLINDRICAL");
/*     */   
/*     */   @UML(identifier = "conical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  61 */   public static final BSplineSurfaceForm CONICAL = new BSplineSurfaceForm("CONICAL");
/*     */   
/*     */   @UML(identifier = "spherical", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  67 */   public static final BSplineSurfaceForm SPHERICAL = new BSplineSurfaceForm("SPHERICAL");
/*     */   
/*     */   @UML(identifier = "toroidal", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  73 */   public static final BSplineSurfaceForm TOROIDAL = new BSplineSurfaceForm("TOROIDAL");
/*     */   
/*     */   @UML(identifier = "unspecified", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
/*  79 */   public static final BSplineSurfaceForm UNSPECIFIED = new BSplineSurfaceForm("UNSPECIFIED");
/*     */   
/*     */   private BSplineSurfaceForm(String name) {
/*  88 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static BSplineSurfaceForm[] values() {
/*  97 */     synchronized (VALUES) {
/*  98 */       return VALUES.<BSplineSurfaceForm>toArray(new BSplineSurfaceForm[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public BSplineSurfaceForm[] family() {
/* 106 */     return values();
/*     */   }
/*     */   
/*     */   public static BSplineSurfaceForm valueOf(String code) {
/* 117 */     return (BSplineSurfaceForm)valueOf(BSplineSurfaceForm.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\BSplineSurfaceForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */