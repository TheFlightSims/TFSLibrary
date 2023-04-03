/*     */ package org.opengis.coverage;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CV_ColorInterpretation", specification = Specification.OGC_01004)
/*     */ public final class ColorInterpretation extends CodeList<ColorInterpretation> {
/*     */   private static final long serialVersionUID = 6947933527594223350L;
/*     */   
/*  47 */   private static final List<ColorInterpretation> VALUES = new ArrayList<ColorInterpretation>(14);
/*     */   
/*     */   @UML(identifier = "CV_Undefined", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  53 */   public static final ColorInterpretation UNDEFINED = new ColorInterpretation("UNDEFINED");
/*     */   
/*     */   @UML(identifier = "CV_GrayIndex", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  62 */   public static final ColorInterpretation GRAY_INDEX = new ColorInterpretation("GRAY_INDEX");
/*     */   
/*     */   @UML(identifier = "CV_PaletteIndex", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  70 */   public static final ColorInterpretation PALETTE_INDEX = new ColorInterpretation("PALETTE_INDEX");
/*     */   
/*     */   @UML(identifier = "CV_RedBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  81 */   public static final ColorInterpretation RED_BAND = new ColorInterpretation("RED_BAND");
/*     */   
/*     */   @UML(identifier = "CV_GreenBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  92 */   public static final ColorInterpretation GREEN_BAND = new ColorInterpretation("GREEN_BAND");
/*     */   
/*     */   @UML(identifier = "CV_BlueBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 103 */   public static final ColorInterpretation BLUE_BAND = new ColorInterpretation("BLUE_BAND");
/*     */   
/*     */   @UML(identifier = "CV_AlphaBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 115 */   public static final ColorInterpretation ALPHA_BAND = new ColorInterpretation("ALPHA_BAND");
/*     */   
/*     */   @UML(identifier = "CV_HueBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 125 */   public static final ColorInterpretation HUE_BAND = new ColorInterpretation("HUE_BAND");
/*     */   
/*     */   @UML(identifier = "CV_SaturationBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 135 */   public static final ColorInterpretation SATURATION_BAND = new ColorInterpretation("SATURATION_BAND");
/*     */   
/*     */   @UML(identifier = "CV_LightnessBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 145 */   public static final ColorInterpretation LIGHTNESS_BAND = new ColorInterpretation("LIGHTNESS_BAND");
/*     */   
/*     */   @UML(identifier = "CV_CyanBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 156 */   public static final ColorInterpretation CYAN_BAND = new ColorInterpretation("CYAN_BAND");
/*     */   
/*     */   @UML(identifier = "CV_MagentaBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 167 */   public static final ColorInterpretation MAGENTA_BAND = new ColorInterpretation("MAGENTA_BAND");
/*     */   
/*     */   @UML(identifier = "CV_YellowBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 178 */   public static final ColorInterpretation YELLOW_BAND = new ColorInterpretation("YELLOW_BAND");
/*     */   
/*     */   @UML(identifier = "CV_BlackBand", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/* 189 */   public static final ColorInterpretation BLACK_BAND = new ColorInterpretation("BLACK_BAND");
/*     */   
/*     */   private ColorInterpretation(String name) {
/* 198 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static ColorInterpretation[] values() {
/* 207 */     synchronized (VALUES) {
/* 208 */       return VALUES.<ColorInterpretation>toArray(new ColorInterpretation[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ColorInterpretation[] family() {
/* 216 */     return values();
/*     */   }
/*     */   
/*     */   public static ColorInterpretation valueOf(String code) {
/* 227 */     return (ColorInterpretation)valueOf(ColorInterpretation.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\ColorInterpretation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */