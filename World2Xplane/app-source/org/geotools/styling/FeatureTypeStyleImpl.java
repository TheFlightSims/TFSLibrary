/*     */ package org.geotools.styling;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Id;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.metadata.citation.OnLineResource;
/*     */ import org.opengis.style.Description;
/*     */ import org.opengis.style.FeatureTypeStyle;
/*     */ import org.opengis.style.Rule;
/*     */ import org.opengis.style.SemanticType;
/*     */ import org.opengis.style.StyleVisitor;
/*     */ import org.opengis.util.Cloneable;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class FeatureTypeStyleImpl implements FeatureTypeStyle, Cloneable {
/*  53 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.styling");
/*     */   
/*  55 */   private List<Rule> rules = new ArrayList<Rule>();
/*     */   
/*  56 */   private Set<SemanticType> semantics = new LinkedHashSet<SemanticType>();
/*     */   
/*  57 */   private Id featureInstances = null;
/*     */   
/*  58 */   private Set<Name> featureTypeNames = new LinkedHashSet<Name>();
/*     */   
/*  60 */   private DescriptionImpl description = new DescriptionImpl();
/*     */   
/*  61 */   private String name = "name";
/*     */   
/*  62 */   private OnLineResource online = null;
/*     */   
/*     */   private Expression transformation;
/*     */   
/*     */   protected FeatureTypeStyleImpl(Rule[] rules) {
/*  71 */     this(Arrays.asList(rules));
/*     */   }
/*     */   
/*     */   protected FeatureTypeStyleImpl(List<Rule> arules) {
/*  75 */     this.rules = new ArrayList<Rule>();
/*  76 */     this.rules.addAll(arules);
/*     */   }
/*     */   
/*     */   protected FeatureTypeStyleImpl() {
/*  83 */     this.rules = new ArrayList<Rule>();
/*     */   }
/*     */   
/*     */   public FeatureTypeStyleImpl(FeatureTypeStyle fts) {
/*  87 */     this.description = new DescriptionImpl(fts.getDescription());
/*  88 */     this.featureInstances = fts.getFeatureInstanceIDs();
/*  89 */     this.featureTypeNames = new LinkedHashSet<Name>(fts.featureTypeNames());
/*  90 */     this.name = fts.getName();
/*  91 */     this.rules = new ArrayList<Rule>();
/*  92 */     if (fts.rules() != null)
/*  93 */       for (Rule rule : fts.rules())
/*  94 */         this.rules.add(RuleImpl.cast(rule));  
/*  97 */     this.semantics = new LinkedHashSet<SemanticType>(fts.semanticTypeIdentifiers());
/*     */   }
/*     */   
/*     */   public List<Rule> rules() {
/* 101 */     return this.rules;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Rule[] getRules() {
/* 108 */     Rule[] ret = new Rule[this.rules.size()];
/* 109 */     for (int i = 0, n = this.rules.size(); i < n; i++)
/* 110 */       ret[i] = this.rules.get(i); 
/* 113 */     return ret;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setRules(Rule[] newRules) {
/* 118 */     this.rules = new ArrayList<Rule>();
/* 119 */     this.rules.addAll(Arrays.asList(newRules));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addRule(Rule rule) {
/* 126 */     this.rules.add(rule);
/*     */   }
/*     */   
/*     */   public Set<SemanticType> semanticTypeIdentifiers() {
/* 132 */     return this.semantics;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String[] getSemanticTypeIdentifiers() {
/* 137 */     String[] ids = new String[this.semantics.size()];
/* 139 */     Iterator<SemanticType> types = this.semantics.iterator();
/* 140 */     int i = 0;
/* 141 */     while (types.hasNext()) {
/* 142 */       ids[i] = ((SemanticType)types.next()).name();
/* 143 */       i++;
/*     */     } 
/* 146 */     if (ids.length == 0)
/* 147 */       ids = new String[] { SemanticType.ANY.toString() }; 
/* 150 */     return ids;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSemanticTypeIdentifiers(String[] types) {
/* 155 */     this.semantics.clear();
/* 157 */     for (String id : types) {
/* 159 */       SemanticType st = SemanticType.valueOf(id);
/* 161 */       if (st != null)
/* 161 */         this.semantics.add(st); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<Name> featureTypeNames() {
/* 167 */     return this.featureTypeNames;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getFeatureTypeName() {
/* 172 */     if (!this.featureTypeNames.isEmpty())
/* 173 */       return ((Name)this.featureTypeNames.iterator().next()).getLocalPart(); 
/* 175 */     return "Feature";
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setFeatureTypeName(String name) {
/* 181 */     this.featureTypeNames.clear();
/* 183 */     if (name.equals("feature"))
/* 184 */       LOGGER.warning("FeatureTypeStyle with typename 'feature' - did you mean to say 'Feature' (with a capital F) for the 'generic' FeatureType"); 
/* 188 */     NameImpl nameImpl = new NameImpl(name);
/* 190 */     this.featureTypeNames.add(nameImpl);
/*     */   }
/*     */   
/*     */   public Id getFeatureInstanceIDs() {
/* 194 */     return this.featureInstances;
/*     */   }
/*     */   
/*     */   public Description getDescription() {
/* 198 */     return this.description;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 202 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 206 */     this.name = name;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getAbstract() {
/* 211 */     if (this.description == null || this.description.getAbstract() == null)
/* 212 */       return null; 
/* 214 */     return this.description.getAbstract().toString();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setAbstract(String abstractStr) {
/* 219 */     this.description.setAbstract((InternationalString)new SimpleInternationalString(abstractStr));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getTitle() {
/* 224 */     if (this.description == null || this.description.getTitle() == null)
/* 225 */       return null; 
/* 227 */     return this.description.getTitle().toString();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setTitle(String title) {
/* 232 */     this.description.setTitle((InternationalString)new SimpleInternationalString(title));
/*     */   }
/*     */   
/*     */   public Object accept(StyleVisitor visitor, Object data) {
/* 236 */     return visitor.visit(this, data);
/*     */   }
/*     */   
/*     */   public void accept(StyleVisitor visitor) {
/* 240 */     visitor.visit(this);
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     FeatureTypeStyle clone;
/*     */     try {
/* 252 */       clone = (FeatureTypeStyle)super.clone();
/* 253 */     } catch (CloneNotSupportedException e) {
/* 254 */       throw new AssertionError(e);
/*     */     } 
/* 257 */     List<Rule> rulesCopy = new ArrayList<Rule>();
/* 259 */     for (Rule rl : this.rules)
/* 260 */       rulesCopy.add((Rule)((Cloneable)rl).clone()); 
/* 263 */     clone.rules().clear();
/* 264 */     clone.rules().addAll(rulesCopy);
/* 265 */     clone.featureTypeNames().clear();
/* 266 */     clone.featureTypeNames().addAll(this.featureTypeNames);
/* 267 */     clone.semanticTypeIdentifiers().clear();
/* 268 */     clone.semanticTypeIdentifiers().addAll(this.semantics);
/* 270 */     return clone;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 279 */     int PRIME = 1000003;
/* 280 */     int result = 0;
/* 282 */     if (this.rules != null)
/* 283 */       result = 1000003 * result + this.rules.hashCode(); 
/* 286 */     if (this.featureInstances != null)
/* 287 */       result = 1000003 * result + this.featureInstances.hashCode(); 
/* 290 */     if (this.semantics != null)
/* 291 */       result = 1000003 * result + this.semantics.hashCode(); 
/* 294 */     if (this.featureTypeNames != null)
/* 295 */       result = 1000003 * result + this.featureTypeNames.hashCode(); 
/* 298 */     if (this.name != null)
/* 299 */       result = 1000003 * result + this.name.hashCode(); 
/* 302 */     if (this.description != null)
/* 303 */       result = 1000003 * result + this.description.hashCode(); 
/* 306 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object oth) {
/* 323 */     if (this == oth)
/* 324 */       return true; 
/* 327 */     if (oth instanceof FeatureTypeStyleImpl) {
/* 328 */       FeatureTypeStyleImpl other = (FeatureTypeStyleImpl)oth;
/* 330 */       return (Utilities.equals(this.name, other.name) && Utilities.equals(this.description, other.description) && Utilities.equals(this.rules, other.rules) && Utilities.equals(this.featureTypeNames, other.featureTypeNames) && Utilities.equals(this.semantics, other.semantics));
/*     */     } 
/* 337 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 341 */     StringBuffer buf = new StringBuffer();
/* 342 */     buf.append("FeatureTypeStyleImpl");
/* 343 */     buf.append("[");
/* 344 */     if (this.name != null) {
/* 345 */       buf.append(" name=");
/* 346 */       buf.append(this.name);
/*     */     } else {
/* 349 */       buf.append(" UNNAMED");
/*     */     } 
/* 351 */     buf.append(", ");
/* 352 */     buf.append(this.featureTypeNames);
/* 353 */     buf.append(", rules=<");
/* 354 */     buf.append(this.rules.size());
/* 355 */     buf.append(">");
/* 356 */     if (this.rules.size() > 0) {
/* 357 */       buf.append("(");
/* 358 */       buf.append(this.rules.get(0));
/* 359 */       if (this.rules.size() > 1)
/* 360 */         buf.append(",..."); 
/* 362 */       buf.append(")");
/*     */     } 
/* 364 */     buf.append("]");
/* 365 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public void setOnlineResource(OnLineResource online) {
/* 369 */     this.online = online;
/*     */   }
/*     */   
/*     */   public OnLineResource getOnlineResource() {
/* 373 */     return this.online;
/*     */   }
/*     */   
/*     */   static FeatureTypeStyleImpl cast(FeatureTypeStyle featureTypeStyle) {
/* 377 */     if (featureTypeStyle == null)
/* 378 */       return null; 
/* 380 */     if (featureTypeStyle instanceof FeatureTypeStyleImpl)
/* 381 */       return (FeatureTypeStyleImpl)featureTypeStyle; 
/* 384 */     FeatureTypeStyleImpl copy = new FeatureTypeStyleImpl();
/* 386 */     return copy;
/*     */   }
/*     */   
/*     */   public Expression getTransformation() {
/* 392 */     return this.transformation;
/*     */   }
/*     */   
/*     */   public void setTransformation(Expression transformation) {
/* 396 */     this.transformation = transformation;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\FeatureTypeStyleImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */