/*     */ package org.opengis.metadata.distribution;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_MediumNameCode", specification = Specification.ISO_19115)
/*     */ public final class MediumName extends CodeList<MediumName> {
/*     */   private static final long serialVersionUID = 2634504971646621701L;
/*     */   
/*  42 */   private static final List<MediumName> VALUES = new ArrayList<MediumName>(18);
/*     */   
/*     */   @UML(identifier = "cdRom", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  48 */   public static final MediumName CD_ROM = new MediumName("CD_ROM");
/*     */   
/*     */   @UML(identifier = "dvd", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  54 */   public static final MediumName DVD = new MediumName("DVD");
/*     */   
/*     */   @UML(identifier = "dvdRom", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  60 */   public static final MediumName DVD_ROM = new MediumName("DVD_ROM");
/*     */   
/*     */   @UML(identifier = "3halfInchFloppy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  66 */   public static final MediumName FLOPPY_3_HALF_INCH = new MediumName("FLOPPY_3_HALF_INCH");
/*     */   
/*     */   @UML(identifier = "5quarterInchFloppy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  72 */   public static final MediumName FLOPPY_5_QUARTER_INCH = new MediumName("FLOPPY_5_QUARTER_INCH");
/*     */   
/*     */   @UML(identifier = "7trackTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  78 */   public static final MediumName TAPE_7_TRACK = new MediumName("TAPE_7_TRACK");
/*     */   
/*     */   @UML(identifier = "9trackTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  84 */   public static final MediumName TAPE_9_TRACK = new MediumName("TAPE_9_TRACK");
/*     */   
/*     */   @UML(identifier = "3480Cartridge", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  90 */   public static final MediumName CARTRIDGE_3480 = new MediumName("CARTRIDGE_3480");
/*     */   
/*     */   @UML(identifier = "3490Cartridge", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  96 */   public static final MediumName CARTRIDGE_3490 = new MediumName("CARTRIDGE_3490");
/*     */   
/*     */   @UML(identifier = "3580Cartridge", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 102 */   public static final MediumName CARTRIDGE_3580 = new MediumName("CARTRIDGE_3580");
/*     */   
/*     */   @UML(identifier = "4mmCartridgeTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 108 */   public static final MediumName CARTRIDGE_TAPE_4mm = new MediumName("CARTRIDGE_TAPE_4mm");
/*     */   
/*     */   @UML(identifier = "8mmCartridgeTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 114 */   public static final MediumName CARTRIDGE_TAPE_8mm = new MediumName("CARTRIDGE_TAPE_8mm");
/*     */   
/*     */   @UML(identifier = "1quarterInchCartridgeTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 120 */   public static final MediumName CARTRIDGE_TAPE_1_QUARTER_INCH = new MediumName("CARTRIDGE_TAPE_1_QUARTER_INCH");
/*     */   
/*     */   @UML(identifier = "digitalLinearTape", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 126 */   public static final MediumName DIGITAL_LINEAR_TAPE = new MediumName("DIGITAL_LINEAR_TAPE");
/*     */   
/*     */   @UML(identifier = "onLine", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 132 */   public static final MediumName ON_LINE = new MediumName("ON_LINE");
/*     */   
/*     */   @UML(identifier = "satellite", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 138 */   public static final MediumName SATELLITE = new MediumName("SATELLITE");
/*     */   
/*     */   @UML(identifier = "telephoneLink", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 144 */   public static final MediumName TELEPHONE_LINK = new MediumName("TELEPHONE_LINK");
/*     */   
/*     */   @UML(identifier = "hardCopy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 150 */   public static final MediumName HARD_COPY = new MediumName("HARD_COPY");
/*     */   
/*     */   private MediumName(String name) {
/* 159 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static MediumName[] values() {
/* 168 */     synchronized (VALUES) {
/* 169 */       return VALUES.<MediumName>toArray(new MediumName[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public MediumName[] family() {
/* 177 */     return values();
/*     */   }
/*     */   
/*     */   public static MediumName valueOf(String code) {
/* 188 */     return (MediumName)valueOf(MediumName.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\MediumName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */