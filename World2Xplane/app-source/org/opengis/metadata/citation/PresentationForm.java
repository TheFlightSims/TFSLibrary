/*     */ package org.opengis.metadata.citation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CI_PresentationFormCode", specification = Specification.ISO_19115)
/*     */ public final class PresentationForm extends CodeList<PresentationForm> {
/*     */   private static final long serialVersionUID = 5668779490885399888L;
/*     */   
/*  43 */   private static final List<PresentationForm> VALUES = new ArrayList<PresentationForm>(14);
/*     */   
/*     */   @UML(identifier = "documentDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final PresentationForm DOCUMENT_DIGITAL = new PresentationForm("DOCUMENT_DIGITAL");
/*     */   
/*     */   @UML(identifier = "documentHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  56 */   public static final PresentationForm DOCUMENT_HARDCOPY = new PresentationForm("DOCUMENT_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "imageDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  64 */   public static final PresentationForm IMAGE_DIGITAL = new PresentationForm("IMAGE_DIGITAL");
/*     */   
/*     */   @UML(identifier = "imageHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final PresentationForm IMAGE_HARDCOPY = new PresentationForm("IMAGE_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "mapDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final PresentationForm MAP_DIGITAL = new PresentationForm("MAP_DIGITAL");
/*     */   
/*     */   @UML(identifier = "mapHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  86 */   public static final PresentationForm MAP_HARDCOPY = new PresentationForm("MAP_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "modelDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  92 */   public static final PresentationForm MODEL_DIGITAL = new PresentationForm("MODEL_DIGITAL");
/*     */   
/*     */   @UML(identifier = "modelHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  98 */   public static final PresentationForm MODEL_HARDCOPY = new PresentationForm("MODEL_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "profileDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 104 */   public static final PresentationForm PROFILE_DIGITAL = new PresentationForm("PROFILE_DIGITAL");
/*     */   
/*     */   @UML(identifier = "profileHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 110 */   public static final PresentationForm PROFILE_HARDCOPY = new PresentationForm("PROFILE_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "tableDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 116 */   public static final PresentationForm TABLE_DIGITAL = new PresentationForm("TABLE_DIGITAL");
/*     */   
/*     */   @UML(identifier = "tableHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 123 */   public static final PresentationForm TABLE_HARDCOPY = new PresentationForm("TABLE_HARDCOPY");
/*     */   
/*     */   @UML(identifier = "videoDigital", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 129 */   public static final PresentationForm VIDEO_DIGITAL = new PresentationForm("VIDEO_DIGITAL");
/*     */   
/*     */   @UML(identifier = "videoHardcopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 135 */   public static final PresentationForm VIDEO_HARDCOPY = new PresentationForm("VIDEO_HARDCOPY");
/*     */   
/*     */   private PresentationForm(String name) {
/* 144 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static PresentationForm[] values() {
/* 153 */     synchronized (VALUES) {
/* 154 */       return VALUES.<PresentationForm>toArray(new PresentationForm[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PresentationForm[] family() {
/* 162 */     return values();
/*     */   }
/*     */   
/*     */   public static PresentationForm valueOf(String code) {
/* 173 */     return (PresentationForm)valueOf(PresentationForm.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\PresentationForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */