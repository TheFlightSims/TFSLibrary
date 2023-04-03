/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "DS_InitiativeTypeCode", specification = Specification.ISO_19115)
/*     */ public final class InitiativeType extends CodeList<InitiativeType> {
/*     */   private static final long serialVersionUID = -6875282680499638030L;
/*     */   
/*  43 */   private static final List<InitiativeType> VALUES = new ArrayList<InitiativeType>(15);
/*     */   
/*     */   @UML(identifier = "campaign", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  49 */   public static final InitiativeType CAMPAIGN = new InitiativeType("CAMPAIGN");
/*     */   
/*     */   @UML(identifier = "collection", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  55 */   public static final InitiativeType COLLECTION = new InitiativeType("COLLECTION");
/*     */   
/*     */   @UML(identifier = "exercise", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  61 */   public static final InitiativeType EXERCISE = new InitiativeType("EXERCISE");
/*     */   
/*     */   @UML(identifier = "experiment", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  67 */   public static final InitiativeType EXPERIMENT = new InitiativeType("EXPERIMENT");
/*     */   
/*     */   @UML(identifier = "investigation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final InitiativeType INVESTIGATION = new InitiativeType("INVESTIGATION");
/*     */   
/*     */   @UML(identifier = "mission", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  79 */   public static final InitiativeType MISSION = new InitiativeType("MISSION");
/*     */   
/*     */   @UML(identifier = "sensor", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  85 */   public static final InitiativeType SENSOR = new InitiativeType("SENSOR");
/*     */   
/*     */   @UML(identifier = "oepration", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  91 */   public static final InitiativeType OPERATION = new InitiativeType("OPERATION");
/*     */   
/*     */   @UML(identifier = "platform", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  97 */   public static final InitiativeType PLATFORM = new InitiativeType("PLATFORM");
/*     */   
/*     */   @UML(identifier = "process", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 103 */   public static final InitiativeType PROCESS = new InitiativeType("PROCESS");
/*     */   
/*     */   @UML(identifier = "program", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 109 */   public static final InitiativeType PROGRAM = new InitiativeType("PROGRAM");
/*     */   
/*     */   @UML(identifier = "project", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 115 */   public static final InitiativeType PROJECT = new InitiativeType("PROJECT");
/*     */   
/*     */   @UML(identifier = "study", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 121 */   public static final InitiativeType STUDY = new InitiativeType("STUDY");
/*     */   
/*     */   @UML(identifier = "task", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 127 */   public static final InitiativeType TASK = new InitiativeType("TASK");
/*     */   
/*     */   @UML(identifier = "trial", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 133 */   public static final InitiativeType TRIAL = new InitiativeType("TRIAL");
/*     */   
/*     */   private InitiativeType(String name) {
/* 142 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static InitiativeType[] values() {
/* 151 */     synchronized (VALUES) {
/* 152 */       return VALUES.<InitiativeType>toArray(new InitiativeType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InitiativeType[] family() {
/* 160 */     return values();
/*     */   }
/*     */   
/*     */   public static InitiativeType valueOf(String code) {
/* 171 */     return (InitiativeType)valueOf(InitiativeType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\InitiativeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */