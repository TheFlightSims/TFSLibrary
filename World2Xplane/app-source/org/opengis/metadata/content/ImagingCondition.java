/*     */ package org.opengis.metadata.content;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_ImagingConditionCode", specification = Specification.ISO_19115)
/*     */ public final class ImagingCondition extends CodeList<ImagingCondition> {
/*     */   private static final long serialVersionUID = -1948380148063658761L;
/*     */   
/*  43 */   private static final List<ImagingCondition> VALUES = new ArrayList<ImagingCondition>(11);
/*     */   
/*     */   @UML(identifier = "blurredImage", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final ImagingCondition BLURRED_IMAGE = new ImagingCondition("BLURRED_IMAGE");
/*     */   
/*     */   @UML(identifier = "cloud", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final ImagingCondition CLOUD = new ImagingCondition("CLOUD");
/*     */   
/*     */   @UML(identifier = "degradingObliquity", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  62 */   public static final ImagingCondition DEGRADING_OBLIQUITY = new ImagingCondition("DEGRADING_OBLIQUITY");
/*     */   
/*     */   @UML(identifier = "fog", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  68 */   public static final ImagingCondition FOG = new ImagingCondition("FOG");
/*     */   
/*     */   @UML(identifier = "heavySmokeOrDust", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  74 */   public static final ImagingCondition HEAVY_SMOKE_OR_DUST = new ImagingCondition("HEAVY_SMOKE_OR_DUST");
/*     */   
/*     */   @UML(identifier = "night", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  80 */   public static final ImagingCondition NIGHT = new ImagingCondition("NIGHT");
/*     */   
/*     */   @UML(identifier = "rain", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  86 */   public static final ImagingCondition RAIN = new ImagingCondition("RAIN");
/*     */   
/*     */   @UML(identifier = "semiDarkness", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  92 */   public static final ImagingCondition SEMI_DARKNESS = new ImagingCondition("SEMI_DARKNESS");
/*     */   
/*     */   @UML(identifier = "shadow", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  98 */   public static final ImagingCondition SHADOW = new ImagingCondition("SHADOW");
/*     */   
/*     */   @UML(identifier = "snow", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 104 */   public static final ImagingCondition SNOW = new ImagingCondition("SNOW");
/*     */   
/*     */   @UML(identifier = "terrainMasking", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 112 */   public static final ImagingCondition TERRAIN_MASKING = new ImagingCondition("TERRAIN_MASKING");
/*     */   
/*     */   private ImagingCondition(String name) {
/* 121 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static ImagingCondition[] values() {
/* 130 */     synchronized (VALUES) {
/* 131 */       return VALUES.<ImagingCondition>toArray(new ImagingCondition[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImagingCondition[] family() {
/* 139 */     return values();
/*     */   }
/*     */   
/*     */   public static ImagingCondition valueOf(String code) {
/* 150 */     return (ImagingCondition)valueOf(ImagingCondition.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\ImagingCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */