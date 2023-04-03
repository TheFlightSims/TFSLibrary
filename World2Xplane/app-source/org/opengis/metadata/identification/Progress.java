/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_ProgressCode", specification = Specification.ISO_19115)
/*     */ public final class Progress extends CodeList<Progress> {
/*     */   private static final long serialVersionUID = 7521085150853319219L;
/*     */   
/*  43 */   private static final List<Progress> VALUES = new ArrayList<Progress>(7);
/*     */   
/*     */   @UML(identifier = "completed", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final Progress COMPLETED = new Progress("COMPLETED");
/*     */   
/*     */   @UML(identifier = "historicalArchive", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final Progress HISTORICAL_ARCHIVE = new Progress("HISTORICAL_ARCHIVE");
/*     */   
/*     */   @UML(identifier = "obsolete", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final Progress OBSOLETE = new Progress("OBSOLETE");
/*     */   
/*     */   @UML(identifier = "onGoing", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final Progress ON_GOING = new Progress("ON_GOING");
/*     */   
/*     */   @UML(identifier = "planned", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final Progress PLANNED = new Progress("PLANNED");
/*     */   
/*     */   @UML(identifier = "required", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final Progress REQUIRED = new Progress("REQUIRED");
/*     */   
/*     */   @UML(identifier = "underDevelopment", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final Progress UNDER_DEVELOPMENT = new Progress("UNDER_DEVELOPMENT");
/*     */   
/*     */   private Progress(String name) {
/*  94 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static Progress[] values() {
/* 103 */     synchronized (VALUES) {
/* 104 */       return VALUES.<Progress>toArray(new Progress[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Progress[] family() {
/* 112 */     return values();
/*     */   }
/*     */   
/*     */   public static Progress valueOf(String code) {
/* 123 */     return (Progress)valueOf(Progress.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\Progress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */