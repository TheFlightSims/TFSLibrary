/*     */ package org.opengis.coverage;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CV_PaletteInterpretation", specification = Specification.OGC_01004)
/*     */ public final class PaletteInterpretation extends CodeList<PaletteInterpretation> {
/*     */   private static final long serialVersionUID = -7387623392932592485L;
/*     */   
/*  47 */   private static final List<PaletteInterpretation> VALUES = new ArrayList<PaletteInterpretation>(4);
/*     */   
/*     */   @UML(identifier = "CV_Gray", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  55 */   public static final PaletteInterpretation GRAY = new PaletteInterpretation("GRAY");
/*     */   
/*     */   @UML(identifier = "CV_RGB", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  63 */   public static final PaletteInterpretation RGB = new PaletteInterpretation("RGB");
/*     */   
/*     */   @UML(identifier = "CV_CMYK", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  71 */   public static final PaletteInterpretation CMYK = new PaletteInterpretation("CMYK");
/*     */   
/*     */   @UML(identifier = "CV_HLS", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_01004)
/*  79 */   public static final PaletteInterpretation HLS = new PaletteInterpretation("HLS");
/*     */   
/*     */   private PaletteInterpretation(String name) {
/*  88 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static PaletteInterpretation[] values() {
/*  97 */     synchronized (VALUES) {
/*  98 */       return VALUES.<PaletteInterpretation>toArray(new PaletteInterpretation[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PaletteInterpretation[] family() {
/* 106 */     return values();
/*     */   }
/*     */   
/*     */   public static PaletteInterpretation valueOf(String code) {
/* 117 */     return (PaletteInterpretation)valueOf(PaletteInterpretation.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\PaletteInterpretation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */