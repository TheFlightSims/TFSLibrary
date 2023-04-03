/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AbstractDatum extends AbstractIdentifiedObject implements Datum {
/*     */   private static final long serialVersionUID = -4894180465652474930L;
/*     */   
/*  74 */   private static final String[] LOCALIZABLES = new String[] { "anchorPoint", "scope" };
/*     */   
/*     */   private final InternationalString anchorPoint;
/*     */   
/*     */   private final long realizationEpoch;
/*     */   
/*     */   private final Extent domainOfValidity;
/*     */   
/*     */   private final InternationalString scope;
/*     */   
/*     */   public AbstractDatum(Datum datum) {
/* 112 */     super((IdentifiedObject)datum);
/* 113 */     Date epoch = datum.getRealizationEpoch();
/* 114 */     this.realizationEpoch = (epoch != null) ? epoch.getTime() : Long.MIN_VALUE;
/* 115 */     this.domainOfValidity = datum.getDomainOfValidity();
/* 116 */     this.scope = datum.getScope();
/* 117 */     this.anchorPoint = datum.getAnchorPoint();
/*     */   }
/*     */   
/*     */   public AbstractDatum(Map<String, ?> properties) {
/* 157 */     this(properties, new HashMap<String, Object>());
/*     */   }
/*     */   
/*     */   private AbstractDatum(Map<String, ?> properties, Map<String, Object> subProperties) {
/* 165 */     super(properties, subProperties, LOCALIZABLES);
/* 167 */     this.anchorPoint = (InternationalString)subProperties.get("anchorPoint");
/* 168 */     Date realizationEpoch = (Date)subProperties.get("realizationEpoch");
/* 169 */     this.domainOfValidity = (Extent)subProperties.get("domainOfValidity");
/* 170 */     this.scope = (InternationalString)subProperties.get("scope");
/* 171 */     this.realizationEpoch = (realizationEpoch != null) ? realizationEpoch.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   static Map<String, Object> name(int key) {
/* 181 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 182 */     InternationalString name = Vocabulary.formatInternational(key);
/* 183 */     properties.put("name", name.toString(null));
/* 184 */     properties.put("alias", name);
/* 185 */     return properties;
/*     */   }
/*     */   
/*     */   public InternationalString getAnchorPoint() {
/* 210 */     return this.anchorPoint;
/*     */   }
/*     */   
/*     */   public Date getRealizationEpoch() {
/* 223 */     return (this.realizationEpoch != Long.MIN_VALUE) ? new Date(this.realizationEpoch) : null;
/*     */   }
/*     */   
/*     */   public Extent getDomainOfValidity() {
/* 232 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public Extent getValidArea() {
/* 241 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public InternationalString getScope() {
/* 249 */     return this.scope;
/*     */   }
/*     */   
/*     */   int getLegacyDatumType() {
/* 261 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 274 */     if (super.equals(object, compareMetadata)) {
/* 275 */       if (!compareMetadata)
/* 282 */         return (nameMatches(object.getName().getCode()) || object.nameMatches(getName().getCode())); 
/* 285 */       AbstractDatum that = (AbstractDatum)object;
/* 286 */       return (this.realizationEpoch == that.realizationEpoch && Utilities.equals(this.domainOfValidity, that.domainOfValidity) && Utilities.equals(this.anchorPoint, that.anchorPoint) && Utilities.equals(this.scope, that.scope));
/*     */     } 
/* 291 */     return false;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 308 */     formatter.append(getLegacyDatumType());
/* 309 */     return Classes.getShortClassName(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\AbstractDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */