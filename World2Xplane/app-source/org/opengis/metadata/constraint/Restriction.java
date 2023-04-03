/*     */ package org.opengis.metadata.constraint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_RestrictionCode", specification = Specification.ISO_19115)
/*     */ public final class Restriction extends CodeList<Restriction> {
/*     */   private static final long serialVersionUID = 7949159742645339894L;
/*     */   
/*  43 */   private static final List<Restriction> VALUES = new ArrayList<Restriction>(8);
/*     */   
/*     */   @UML(identifier = "copyright", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  51 */   public static final Restriction COPYRIGHT = new Restriction("COPYRIGHT");
/*     */   
/*     */   @UML(identifier = "patent", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  58 */   public static final Restriction PATENT = new Restriction("PATENT");
/*     */   
/*     */   @UML(identifier = "patentPending", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  64 */   public static final Restriction PATENT_PENDING = new Restriction("PATENT_PENDING");
/*     */   
/*     */   @UML(identifier = "trademark", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  71 */   public static final Restriction TRADEMARK = new Restriction("TRADEMARK");
/*     */   
/*     */   @UML(identifier = "license", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  77 */   public static final Restriction LICENSE = new Restriction("LICENSE");
/*     */   
/*     */   @UML(identifier = "intellectualPropertyRights", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  84 */   public static final Restriction INTELLECTUAL_PROPERTY_RIGHTS = new Restriction("INTELLECTUAL_PROPERTY_RIGHTS");
/*     */   
/*     */   @UML(identifier = "restricted", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  90 */   public static final Restriction RESTRICTED = new Restriction("RESTRICTED");
/*     */   
/*     */   @UML(identifier = "otherRestictions", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  96 */   public static final Restriction OTHER_RESTRICTIONS = new Restriction("OTHER_RESTRICTIONS");
/*     */   
/*     */   private Restriction(String name) {
/* 105 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static Restriction[] values() {
/* 114 */     synchronized (VALUES) {
/* 115 */       return VALUES.<Restriction>toArray(new Restriction[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Restriction[] family() {
/* 123 */     return values();
/*     */   }
/*     */   
/*     */   public static Restriction valueOf(String code) {
/* 134 */     return (Restriction)valueOf(Restriction.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\constraint\Restriction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */