/*     */ package org.opengis.metadata.maintenance;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_MaintenanceFrequencyCode", specification = Specification.ISO_19115)
/*     */ public final class MaintenanceFrequency extends CodeList<MaintenanceFrequency> {
/*     */   private static final long serialVersionUID = -6034786030982260550L;
/*     */   
/*  43 */   private static final List<MaintenanceFrequency> VALUES = new ArrayList<MaintenanceFrequency>(12);
/*     */   
/*     */   @UML(identifier = "continual", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final MaintenanceFrequency CONTINUAL = new MaintenanceFrequency("CONTINUAL");
/*     */   
/*     */   @UML(identifier = "daily", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final MaintenanceFrequency DAILY = new MaintenanceFrequency("DAILY");
/*     */   
/*     */   @UML(identifier = "weekly", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final MaintenanceFrequency WEEKLY = new MaintenanceFrequency("WEEKLY");
/*     */   
/*     */   @UML(identifier = "fortnightly", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final MaintenanceFrequency FORTNIGHTLY = new MaintenanceFrequency("FORTNIGHTLY");
/*     */   
/*     */   @UML(identifier = "monthly", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final MaintenanceFrequency MONTHLY = new MaintenanceFrequency("MONTHLY");
/*     */   
/*     */   @UML(identifier = "quarterly", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final MaintenanceFrequency QUARTERLY = new MaintenanceFrequency("QUARTERLY");
/*     */   
/*     */   @UML(identifier = "biannually", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final MaintenanceFrequency BIANNUALLY = new MaintenanceFrequency("BIANNUALLY");
/*     */   
/*     */   @UML(identifier = "annually", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  91 */   public static final MaintenanceFrequency ANNUALLY = new MaintenanceFrequency("ANNUALLY");
/*     */   
/*     */   @UML(identifier = "asNeeded", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  97 */   public static final MaintenanceFrequency AS_NEEDED = new MaintenanceFrequency("AS_NEEDED");
/*     */   
/*     */   @UML(identifier = "irregular", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 103 */   public static final MaintenanceFrequency IRREGULAR = new MaintenanceFrequency("IRREGULAR");
/*     */   
/*     */   @UML(identifier = "notPlanned", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 109 */   public static final MaintenanceFrequency NOT_PLANNED = new MaintenanceFrequency("NOT_PLANNED");
/*     */   
/*     */   @UML(identifier = "unknow", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 115 */   public static final MaintenanceFrequency UNKNOW = new MaintenanceFrequency("UNKNOW");
/*     */   
/*     */   private MaintenanceFrequency(String name) {
/* 124 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static MaintenanceFrequency[] values() {
/* 133 */     synchronized (VALUES) {
/* 134 */       return VALUES.<MaintenanceFrequency>toArray(new MaintenanceFrequency[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public MaintenanceFrequency[] family() {
/* 142 */     return values();
/*     */   }
/*     */   
/*     */   public static MaintenanceFrequency valueOf(String code) {
/* 153 */     return (MaintenanceFrequency)valueOf(MaintenanceFrequency.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\maintenance\MaintenanceFrequency.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */