/*     */ package org.opengis.metadata.citation;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CI_RoleCode", specification = Specification.ISO_19115)
/*     */ public final class Role extends CodeList<Role> {
/*     */   private static final long serialVersionUID = -7763516018565534103L;
/*     */   
/*  44 */   private static final List<Role> VALUES = new ArrayList<Role>(11);
/*     */   
/*     */   @UML(identifier = "resourceProvider", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  50 */   public static final Role RESOURCE_PROVIDER = new Role("RESOURCE_PROVIDER");
/*     */   
/*     */   @UML(identifier = "custodian", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  57 */   public static final Role CUSTODIAN = new Role("CUSTODIAN");
/*     */   
/*     */   @UML(identifier = "owner", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  63 */   public static final Role OWNER = new Role("OWNER");
/*     */   
/*     */   @UML(identifier = "user", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  69 */   public static final Role USER = new Role("USER");
/*     */   
/*     */   @UML(identifier = "distributor", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  75 */   public static final Role DISTRIBUTOR = new Role("DISTRIBUTOR");
/*     */   
/*     */   @UML(identifier = "originator", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  81 */   public static final Role ORIGINATOR = new Role("ORIGINATOR");
/*     */   
/*     */   @UML(identifier = "pointOfContact", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  87 */   public static final Role POINT_OF_CONTACT = new Role("POINT_OF_CONTACT");
/*     */   
/*     */   @UML(identifier = "principalInvestigator", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  93 */   public static final Role PRINCIPAL_INVESTIGATOR = new Role("PRINCIPAL_INVESTIGATOR");
/*     */   
/*     */   @UML(identifier = "processor", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  99 */   public static final Role PROCESSOR = new Role("PROCESSOR");
/*     */   
/*     */   @UML(identifier = "publisher", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 105 */   public static final Role PUBLISHER = new Role("PUBLISHER");
/*     */   
/*     */   @UML(identifier = "author", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 113 */   public static final Role AUTHOR = new Role("AUTHOR");
/*     */   
/*     */   private Role(String name) {
/* 122 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static Role[] values() {
/* 131 */     synchronized (VALUES) {
/* 132 */       return VALUES.<Role>toArray(new Role[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Role[] family() {
/* 140 */     return values();
/*     */   }
/*     */   
/*     */   public static Role valueOf(String code) {
/* 151 */     return (Role)valueOf(Role.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Role.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */