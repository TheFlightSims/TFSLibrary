/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.text.ParseException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.wkt.Symbols;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.DerivedSet;
/*     */ import org.geotools.util.NameFactory;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class PropertyAuthorityFactory extends DirectAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory {
/*     */   private final Citation authority;
/*     */   
/*     */   private final Citation[] authorities;
/*     */   
/*  95 */   private final Properties definitions = new Properties();
/*     */   
/* 101 */   private final Set<String> codes = Collections.unmodifiableSet(this.definitions.keySet());
/*     */   
/*     */   private transient Map<Class<? extends IdentifiedObject>, Set<String>> filteredCodes;
/*     */   
/*     */   private transient Parser parser;
/*     */   
/*     */   public PropertyAuthorityFactory(ReferencingFactoryContainer factories, Citation authority, URL definitions) throws IOException {
/* 130 */     this(factories, new Citation[] { authority }, definitions);
/*     */   }
/*     */   
/*     */   public PropertyAuthorityFactory(ReferencingFactoryContainer factories, Citation[] authorities, URL definitions) throws IOException {
/* 157 */     super(factories, 11);
/* 164 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/* 165 */     this.hints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/* 166 */     ensureNonNull("authorities", authorities);
/* 167 */     if (authorities.length == 0)
/* 168 */       throw new IllegalArgumentException(Errors.format(46)); 
/* 170 */     this.authorities = (Citation[])authorities.clone();
/* 171 */     this.authority = authorities[0];
/* 172 */     ensureNonNull("authority", this.authority);
/* 173 */     InputStream in = definitions.openStream();
/* 174 */     this.definitions.load(in);
/* 175 */     in.close();
/* 184 */     Symbols s = Symbols.DEFAULT;
/* 185 */     for (Object wkt : this.definitions.values()) {
/* 186 */       if (s.containsAxis((String)wkt)) {
/* 187 */         LOGGER.fine("Axis elements found in a wkt definition, the force longitude first axis order hint might not be respected:\n" + wkt);
/*     */         return;
/*     */       } 
/*     */     } 
/* 192 */     this.hints.remove(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER);
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 200 */     return this.authority;
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 231 */     if (type == null || type.isAssignableFrom(IdentifiedObject.class))
/* 232 */       return this.codes; 
/* 234 */     if (this.filteredCodes == null)
/* 235 */       this.filteredCodes = new HashMap<Class<? extends IdentifiedObject>, Set<String>>(); 
/* 237 */     synchronized (this.filteredCodes) {
/*     */       Codes codes;
/* 238 */       Set<String> filtered = this.filteredCodes.get(type);
/* 239 */       if (filtered == null) {
/* 241 */         Map<String, String> map = this.definitions;
/* 242 */         codes = new Codes(map, type);
/* 243 */         this.filteredCodes.put(type, codes);
/*     */       } 
/* 245 */       return (Set<String>)codes;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class Codes extends DerivedSet<String, String> {
/*     */     private static final long serialVersionUID = 2681905294171687900L;
/*     */     
/*     */     private final Class<? extends IdentifiedObject> type;
/*     */     
/*     */     private final Map<String, String> definitions;
/*     */     
/*     */     public Codes(Map<String, String> definitions, Class<? extends IdentifiedObject> type) {
/* 277 */       super(definitions.keySet(), String.class);
/* 278 */       this.definitions = definitions;
/* 279 */       this.type = type;
/*     */     }
/*     */     
/*     */     protected String baseToDerived(String key) {
/* 287 */       String wkt = this.definitions.get(key);
/* 288 */       int length = wkt.length();
/*     */       int i;
/* 289 */       for (i = 0; i < length && Character.isJavaIdentifierPart(wkt.charAt(i)); i++);
/* 290 */       Class<?> candidate = PropertyAuthorityFactory.Parser.getClassOf(wkt.substring(0, i));
/* 291 */       if (candidate == null)
/* 292 */         candidate = IdentifiedObject.class; 
/* 294 */       return this.type.isAssignableFrom(candidate) ? key : null;
/*     */     }
/*     */     
/*     */     protected String derivedToBase(String element) {
/* 301 */       return element;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getWKT(String code) throws NoSuchAuthorityCodeException {
/* 313 */     ensureNonNull("code", code);
/* 314 */     String wkt = this.definitions.getProperty(trimAuthority(code));
/* 315 */     if (wkt == null)
/* 316 */       throw noSuchAuthorityCode(IdentifiedObject.class, code); 
/* 318 */     return wkt.trim();
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 333 */     String wkt = getWKT(code);
/* 334 */     int start = wkt.indexOf('"');
/* 335 */     if (start >= 0) {
/* 336 */       int end = wkt.indexOf('"', ++start);
/* 337 */       if (end >= 0)
/* 338 */         return (InternationalString)new SimpleInternationalString(wkt.substring(start, end).trim()); 
/*     */     } 
/* 341 */     return null;
/*     */   }
/*     */   
/*     */   private Parser getParser() {
/* 348 */     if (this.parser == null)
/* 349 */       this.parser = new Parser(); 
/* 351 */     return this.parser;
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 366 */     String wkt = getWKT(code);
/* 367 */     Parser parser = getParser();
/*     */     try {
/* 369 */       synchronized (parser) {
/* 370 */         parser.code = code;
/* 371 */         return (IdentifiedObject)parser.parseObject(wkt);
/*     */       } 
/* 373 */     } catch (ParseException exception) {
/* 374 */       throw new FactoryException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 390 */     String wkt = getWKT(code);
/* 391 */     Parser parser = getParser();
/*     */     try {
/* 393 */       synchronized (parser) {
/* 394 */         parser.code = code;
/* 396 */         return parser.parseCoordinateReferenceSystem(wkt);
/*     */       } 
/* 398 */     } catch (ParseException exception) {
/* 399 */       throw new FactoryException(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String trimAuthority(String code) {
/* 413 */     code = code.trim();
/* 414 */     GenericName name = NameFactory.create(code);
/* 415 */     GenericName scope = name.scope().name();
/* 416 */     if (scope == null)
/* 417 */       return code; 
/* 419 */     String candidate = scope.toString();
/* 420 */     for (int i = 0; i < this.authorities.length; i++) {
/* 421 */       if (Citations.identifierMatches(this.authorities[i], candidate))
/* 422 */         return name.tip().toString().trim(); 
/*     */     } 
/* 425 */     return code;
/*     */   }
/*     */   
/*     */   private final class Parser extends org.geotools.referencing.wkt.Parser {
/*     */     private static final long serialVersionUID = -5910561042299146066L;
/*     */     
/*     */     String code;
/*     */     
/*     */     public Parser() {
/* 447 */       super(Symbols.DEFAULT, PropertyAuthorityFactory.this.factories);
/*     */     }
/*     */     
/*     */     protected Map<String, Object> alterProperties(Map<String, Object> properties) {
/* 455 */       Object candidate = properties.get("identifiers");
/* 456 */       if (candidate == null && this.code != null) {
/*     */         Object identifiers;
/* 457 */         properties = new HashMap<String, Object>(properties);
/* 458 */         this.code = PropertyAuthorityFactory.this.trimAuthority(this.code);
/* 460 */         if (PropertyAuthorityFactory.this.authorities.length <= 1) {
/* 461 */           identifiers = new NamedIdentifier(PropertyAuthorityFactory.this.authority, this.code);
/*     */         } else {
/* 463 */           NamedIdentifier[] ids = new NamedIdentifier[PropertyAuthorityFactory.this.authorities.length];
/* 464 */           for (int i = 0; i < ids.length; i++)
/* 465 */             ids[i] = new NamedIdentifier(PropertyAuthorityFactory.this.authorities[i], this.code); 
/* 467 */           identifiers = ids;
/*     */         } 
/* 469 */         properties.put("identifiers", identifiers);
/*     */       } 
/* 471 */       return super.alterProperties(properties);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\PropertyAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */