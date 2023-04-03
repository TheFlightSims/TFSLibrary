/*     */ package org.opengis.metadata.spatial;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_PixelOrientationCode", specification = Specification.ISO_19115)
/*     */ public final class PixelOrientation extends CodeList<PixelOrientation> {
/*     */   private static final long serialVersionUID = 7885677198357949308L;
/*     */   
/*  47 */   private static final List<PixelOrientation> VALUES = new ArrayList<PixelOrientation>(5);
/*     */   
/*     */   @UML(identifier = "center", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final PixelOrientation CENTER = new PixelOrientation("CENTER");
/*     */   
/*     */   @UML(identifier = "lowerLeft", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  69 */   public static final PixelOrientation LOWER_LEFT = new PixelOrientation("LOWER_LEFT");
/*     */   
/*     */   @UML(identifier = "lowerRight", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  75 */   public static final PixelOrientation LOWER_RIGHT = new PixelOrientation("LOWER_RIGHT");
/*     */   
/*     */   @UML(identifier = "upperRight", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  81 */   public static final PixelOrientation UPPER_RIGHT = new PixelOrientation("UPPER_RIGHT");
/*     */   
/*     */   @UML(identifier = "upperLeft", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  87 */   public static final PixelOrientation UPPER_LEFT = new PixelOrientation("UPPER_LEFT");
/*     */   
/*     */   private PixelOrientation(String name) {
/*  96 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static PixelOrientation[] values() {
/* 105 */     synchronized (VALUES) {
/* 106 */       return VALUES.<PixelOrientation>toArray(new PixelOrientation[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PixelOrientation[] family() {
/* 114 */     return values();
/*     */   }
/*     */   
/*     */   public static PixelOrientation valueOf(String code) {
/* 125 */     return (PixelOrientation)valueOf(PixelOrientation.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\spatial\PixelOrientation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */