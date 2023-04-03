/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.opengis.metadata.Datatype;
/*     */ import org.opengis.metadata.ExtendedElementInformation;
/*     */ import org.opengis.metadata.Obligation;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ExtendedElementInformationImpl extends MetadataEntity implements ExtendedElementInformation {
/*     */   private static final long serialVersionUID = -935396252908733907L;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private String shortName;
/*     */   
/*     */   private Integer domainCode;
/*     */   
/*     */   private InternationalString definition;
/*     */   
/*     */   private Obligation obligation;
/*     */   
/*     */   private InternationalString condition;
/*     */   
/*     */   private Datatype dataType;
/*     */   
/*     */   private Integer maximumOccurrence;
/*     */   
/*     */   private InternationalString domainValue;
/*     */   
/*     */   private Collection<String> parentEntity;
/*     */   
/*     */   private InternationalString rule;
/*     */   
/*     */   private Collection<InternationalString> rationales;
/*     */   
/*     */   private Collection<ResponsibleParty> sources;
/*     */   
/*     */   public ExtendedElementInformationImpl() {}
/*     */   
/*     */   public ExtendedElementInformationImpl(ExtendedElementInformation source) {
/* 141 */     super(source);
/*     */   }
/*     */   
/*     */   public ExtendedElementInformationImpl(String name, InternationalString definition, InternationalString condition, Datatype dataType, Collection<String> parentEntity, InternationalString rule, Collection<? extends ResponsibleParty> sources) {
/* 155 */     setName(name);
/* 156 */     setDefinition(definition);
/* 157 */     setCondition(condition);
/* 158 */     setDataType(dataType);
/* 159 */     setParentEntity(parentEntity);
/* 160 */     setRule(rule);
/* 161 */     setSources(sources);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 168 */     return this.name;
/*     */   }
/*     */   
/*     */   public synchronized void setName(String newValue) {
/* 175 */     checkWritePermission();
/* 176 */     this.name = newValue;
/*     */   }
/*     */   
/*     */   public String getShortName() {
/* 186 */     return this.shortName;
/*     */   }
/*     */   
/*     */   public synchronized void setShortName(String newValue) {
/* 193 */     checkWritePermission();
/* 194 */     this.shortName = newValue;
/*     */   }
/*     */   
/*     */   public Integer getDomainCode() {
/* 203 */     return this.domainCode;
/*     */   }
/*     */   
/*     */   public synchronized void setDomainCode(Integer newValue) {
/* 210 */     checkWritePermission();
/* 211 */     this.domainCode = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getDefinition() {
/* 218 */     return this.definition;
/*     */   }
/*     */   
/*     */   public synchronized void setDefinition(InternationalString newValue) {
/* 225 */     checkWritePermission();
/* 226 */     this.definition = newValue;
/*     */   }
/*     */   
/*     */   public Obligation getObligation() {
/* 233 */     return this.obligation;
/*     */   }
/*     */   
/*     */   public synchronized void setObligation(Obligation newValue) {
/* 240 */     checkWritePermission();
/* 241 */     this.obligation = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getCondition() {
/* 250 */     return this.condition;
/*     */   }
/*     */   
/*     */   public synchronized void setCondition(InternationalString newValue) {
/* 257 */     checkWritePermission();
/* 258 */     this.condition = newValue;
/*     */   }
/*     */   
/*     */   public Datatype getDataType() {
/* 265 */     return this.dataType;
/*     */   }
/*     */   
/*     */   public synchronized void setDataType(Datatype newValue) {
/* 272 */     checkWritePermission();
/* 273 */     this.dataType = newValue;
/*     */   }
/*     */   
/*     */   public Integer getMaximumOccurrence() {
/* 284 */     return this.maximumOccurrence;
/*     */   }
/*     */   
/*     */   public synchronized void setMaximumOccurrence(Integer newValue) {
/* 291 */     checkWritePermission();
/* 292 */     this.maximumOccurrence = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getDomainValue() {
/* 303 */     return this.domainValue;
/*     */   }
/*     */   
/*     */   public synchronized void setDomainValue(InternationalString newValue) {
/* 310 */     checkWritePermission();
/* 311 */     this.domainValue = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getParentEntity() {
/* 319 */     return this.parentEntity = nonNullCollection(this.parentEntity, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setParentEntity(Collection<? extends String> newValues) {
/* 328 */     this.parentEntity = copyCollection(newValues, this.parentEntity, String.class);
/*     */   }
/*     */   
/*     */   public InternationalString getRule() {
/* 335 */     return this.rule;
/*     */   }
/*     */   
/*     */   public synchronized void setRule(InternationalString newValue) {
/* 342 */     checkWritePermission();
/* 343 */     this.rule = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<InternationalString> getRationales() {
/* 350 */     return this.rationales = nonNullCollection(this.rationales, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized void setRationales(Collection<? extends InternationalString> newValues) {
/* 359 */     this.rationales = copyCollection(newValues, this.rationales, InternationalString.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getSources() {
/* 366 */     return this.sources = nonNullCollection(this.sources, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSources(Collection<? extends ResponsibleParty> newValues) {
/* 375 */     this.sources = copyCollection(newValues, this.sources, ResponsibleParty.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\ExtendedElementInformationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */