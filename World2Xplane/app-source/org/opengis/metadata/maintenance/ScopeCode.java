/*     */ package org.opengis.metadata.maintenance;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_ScopeCode", specification = Specification.ISO_19115)
/*     */ public final class ScopeCode extends CodeList<ScopeCode> {
/*     */   private static final long serialVersionUID = -4542429199783894255L;
/*     */   
/*  45 */   private static final List<ScopeCode> VALUES = new ArrayList<ScopeCode>(16);
/*     */   
/*     */   @UML(identifier = "attribute", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  51 */   public static final ScopeCode ATTRIBUTE = new ScopeCode("ATTRIBUTE");
/*     */   
/*     */   @UML(identifier = "attributeType", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  57 */   public static final ScopeCode ATTRIBUTE_TYPE = new ScopeCode("ATTRIBUTE_TYPE");
/*     */   
/*     */   @UML(identifier = "collectionHardware", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  63 */   public static final ScopeCode COLLECTION_HARDWARE = new ScopeCode("COLLECTION_HARDWARE");
/*     */   
/*     */   @UML(identifier = "collectionSession", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  69 */   public static final ScopeCode COLLECTION_SESSION = new ScopeCode("COLLECTION_SESSION");
/*     */   
/*     */   @UML(identifier = "dataset", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  75 */   public static final ScopeCode DATASET = new ScopeCode("DATASET");
/*     */   
/*     */   @UML(identifier = "series", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  81 */   public static final ScopeCode SERIES = new ScopeCode("SERIES");
/*     */   
/*     */   @UML(identifier = "nonGeographicDataset", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  87 */   public static final ScopeCode NON_GEOGRAPHIC_DATASET = new ScopeCode("NON_GEOGRAPHIC_DATASET");
/*     */   
/*     */   @UML(identifier = "dimensionGroup", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  93 */   public static final ScopeCode DIMENSION_GROUP = new ScopeCode("DIMENSION_GROUP");
/*     */   
/*     */   @UML(identifier = "feature", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  99 */   public static final ScopeCode FEATURE = new ScopeCode("FEATURE");
/*     */   
/*     */   @UML(identifier = "featureType", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 105 */   public static final ScopeCode FEATURE_TYPE = new ScopeCode("FEATURE_TYPE");
/*     */   
/*     */   @UML(identifier = "propertyType", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 111 */   public static final ScopeCode PROPERTY_TYPE = new ScopeCode("PROPERTY_TYPE");
/*     */   
/*     */   @UML(identifier = "fieldSession", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 117 */   public static final ScopeCode FIELD_SESSION = new ScopeCode("FIELD_SESSION");
/*     */   
/*     */   @UML(identifier = "software", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 123 */   public static final ScopeCode SOFTWARE = new ScopeCode("SOFTWARE");
/*     */   
/*     */   @UML(identifier = "service", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 131 */   public static final ScopeCode SERVICE = new ScopeCode("SERVICE");
/*     */   
/*     */   @UML(identifier = "model", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 137 */   public static final ScopeCode MODEL = new ScopeCode("MODEL");
/*     */   
/*     */   @UML(identifier = "tile", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 145 */   public static final ScopeCode TILE = new ScopeCode("TILE");
/*     */   
/*     */   private ScopeCode(String name) {
/* 154 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static ScopeCode[] values() {
/* 163 */     synchronized (VALUES) {
/* 164 */       return VALUES.<ScopeCode>toArray(new ScopeCode[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ScopeCode[] family() {
/* 172 */     return values();
/*     */   }
/*     */   
/*     */   public static ScopeCode valueOf(String code) {
/* 183 */     return (ScopeCode)valueOf(ScopeCode.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\maintenance\ScopeCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */