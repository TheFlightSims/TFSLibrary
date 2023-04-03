/*     */ package org.opengis.metadata.distribution;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_MediumFormatCode", specification = Specification.ISO_19115)
/*     */ public final class MediumFormat extends CodeList<MediumFormat> {
/*     */   private static final long serialVersionUID = 413822250362716958L;
/*     */   
/*  43 */   private static final List<MediumFormat> VALUES = new ArrayList<MediumFormat>(6);
/*     */   
/*     */   @UML(identifier = "cpio", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final MediumFormat CPIO = new MediumFormat("CPIO");
/*     */   
/*     */   @UML(identifier = "tar", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final MediumFormat TAR = new MediumFormat("TAR");
/*     */   
/*     */   @UML(identifier = "highSierra", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final MediumFormat HIGH_SIERRA = new MediumFormat("HIGH_SIERRA");
/*     */   
/*     */   @UML(identifier = "iso9660", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final MediumFormat ISO_9660 = new MediumFormat("ISO_9660");
/*     */   
/*     */   @UML(identifier = "iso9660RockRidge", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final MediumFormat ISO_9660_ROCK_RIDGE = new MediumFormat("ISO_9660_ROCK_RIDGE");
/*     */   
/*     */   @UML(identifier = "iso9660AppleHFS", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final MediumFormat ISO_9660_APPLE_HFS = new MediumFormat("ISO_9660_APPLE_HFS");
/*     */   
/*     */   private MediumFormat(String name) {
/*  88 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static MediumFormat[] values() {
/*  97 */     synchronized (VALUES) {
/*  98 */       return VALUES.<MediumFormat>toArray(new MediumFormat[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public MediumFormat[] family() {
/* 106 */     return values();
/*     */   }
/*     */   
/*     */   public static MediumFormat valueOf(String code) {
/* 117 */     return (MediumFormat)valueOf(MediumFormat.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\MediumFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */