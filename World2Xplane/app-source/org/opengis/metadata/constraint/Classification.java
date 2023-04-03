/*     */ package org.opengis.metadata.constraint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_ClassificationCode", specification = Specification.ISO_19115)
/*     */ public final class Classification extends CodeList<Classification> {
/*     */   private static final long serialVersionUID = -549174931332214797L;
/*     */   
/*  43 */   private static final List<Classification> VALUES = new ArrayList<Classification>(5);
/*     */   
/*     */   @UML(identifier = "unclassified", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final Classification UNCLASSIFIED = new Classification("UNCLASSIFIED");
/*     */   
/*     */   @UML(identifier = "restricted", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final Classification RESTRICTED = new Classification("RESTRICTED");
/*     */   
/*     */   @UML(identifier = "confidential", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final Classification CONFIDENTIAL = new Classification("CONFIDENTIAL");
/*     */   
/*     */   @UML(identifier = "secret", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final Classification SECRET = new Classification("SECRET");
/*     */   
/*     */   @UML(identifier = "topsecret", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final Classification TOP_SECRET = new Classification("TOP_SECRET");
/*     */   
/*     */   private Classification(String name) {
/*  82 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static Classification[] values() {
/*  91 */     synchronized (VALUES) {
/*  92 */       return VALUES.<Classification>toArray(new Classification[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Classification[] family() {
/* 100 */     return values();
/*     */   }
/*     */   
/*     */   public static Classification valueOf(String code) {
/* 111 */     return (Classification)valueOf(Classification.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\constraint\Classification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */