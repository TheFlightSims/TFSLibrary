/*     */ package org.opengis.metadata.identification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "MD_TopicCategoryCode", specification = Specification.ISO_19115)
/*     */ public final class TopicCategory extends CodeList<TopicCategory> {
/*     */   private static final long serialVersionUID = -4987523565852255081L;
/*     */   
/*  47 */   private static final List<TopicCategory> VALUES = new ArrayList<TopicCategory>(19);
/*     */   
/*     */   @UML(identifier = "farming", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  56 */   public static final TopicCategory FARMING = new TopicCategory("FARMING");
/*     */   
/*     */   @UML(identifier = "biota", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  65 */   public static final TopicCategory BIOTA = new TopicCategory("BIOTA");
/*     */   
/*     */   @UML(identifier = "boundaries", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  73 */   public static final TopicCategory BOUNDARIES = new TopicCategory("BOUNDARIES");
/*     */   
/*     */   @UML(identifier = "climatologyMeteorologyAtmosphere", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  82 */   public static final TopicCategory CLIMATOLOGY_METEOROLOGY_ATMOSPHERE = new TopicCategory("CLIMATOLOGY_METEOROLOGY_ATMOSPHERE");
/*     */   
/*     */   @UML(identifier = "economy", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/*  92 */   public static final TopicCategory ECONOMY = new TopicCategory("ECONOMY");
/*     */   
/*     */   @UML(identifier = "elevation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 100 */   public static final TopicCategory ELEVATION = new TopicCategory("ELEVATION");
/*     */   
/*     */   @UML(identifier = "environment", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 109 */   public static final TopicCategory ENVIRONMENT = new TopicCategory("ENVIRONMENT");
/*     */   
/*     */   @UML(identifier = "geoscientificInformation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 120 */   public static final TopicCategory GEOSCIENTIFIC_INFORMATION = new TopicCategory("GEOSCIENTIFIC_INFORMATION");
/*     */   
/*     */   @UML(identifier = "health", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 131 */   public static final TopicCategory HEALTH = new TopicCategory("HEALTH");
/*     */   
/*     */   @UML(identifier = "imageryBaseMapsEarthCover", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 140 */   public static final TopicCategory IMAGERY_BASE_MAPS_EARTH_COVER = new TopicCategory("IMAGERY_BASE_MAPS_EARTH_COVER");
/*     */   
/*     */   @UML(identifier = "intelligenceMilitary", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 148 */   public static final TopicCategory INTELLIGENCE_MILITARY = new TopicCategory("INTELLIGENCE_MILITARY");
/*     */   
/*     */   @UML(identifier = "inlandWaters", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 157 */   public static final TopicCategory INLAND_WATERS = new TopicCategory("INLAND_WATERS");
/*     */   
/*     */   @UML(identifier = "location", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 166 */   public static final TopicCategory LOCATION = new TopicCategory("LOCATION");
/*     */   
/*     */   @UML(identifier = "oceans", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 174 */   public static final TopicCategory OCEANS = new TopicCategory("OCEANS");
/*     */   
/*     */   @UML(identifier = "planningCadastre", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 182 */   public static final TopicCategory PLANNING_CADASTRE = new TopicCategory("PLANNING_CADASTRE");
/*     */   
/*     */   @UML(identifier = "society", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 192 */   public static final TopicCategory SOCIETY = new TopicCategory("SOCIETY");
/*     */   
/*     */   @UML(identifier = "structure", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 200 */   public static final TopicCategory STRUCTURE = new TopicCategory("STRUCTURE");
/*     */   
/*     */   @UML(identifier = "transportation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 209 */   public static final TopicCategory TRANSPORTATION = new TopicCategory("TRANSPORTATION");
/*     */   
/*     */   @UML(identifier = "utilitiesCommunication", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
/* 220 */   public static final TopicCategory UTILITIES_COMMUNICATION = new TopicCategory("UTILITIES_COMMUNICATION");
/*     */   
/*     */   private TopicCategory(String name) {
/* 229 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static TopicCategory[] values() {
/* 238 */     synchronized (VALUES) {
/* 239 */       return VALUES.<TopicCategory>toArray(new TopicCategory[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TopicCategory[] family() {
/* 247 */     return values();
/*     */   }
/*     */   
/*     */   public static TopicCategory valueOf(String code) {
/* 258 */     return (TopicCategory)valueOf(TopicCategory.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\TopicCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */