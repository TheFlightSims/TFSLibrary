/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.GrowableInternationalString;
/*     */ import org.geotools.util.LocalName;
/*     */ import org.geotools.util.ScopedName;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.WeakValueHashMap;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.LocalName;
/*     */ import org.opengis.util.NameSpace;
/*     */ import org.opengis.util.ScopedName;
/*     */ 
/*     */ public class NamedIdentifier implements ReferenceIdentifier, GenericName, Comparable<GenericName>, Serializable {
/*     */   private static final long serialVersionUID = 8474731565582774497L;
/*     */   
/*     */   private static Map<CharSequence, GenericName> SCOPES;
/*     */   
/*     */   private final String code;
/*     */   
/*     */   private final String codespace;
/*     */   
/*     */   private final Citation authority;
/*     */   
/*     */   private final String version;
/*     */   
/*     */   private final InternationalString remarks;
/*     */   
/*     */   private GenericName name;
/*     */   
/*     */   public NamedIdentifier(Map<String, ?> properties) throws IllegalArgumentException {
/* 180 */     this(properties, true);
/*     */   }
/*     */   
/*     */   public NamedIdentifier(Citation authority, InternationalString code) {
/* 195 */     this(authority, code.toString(null));
/* 196 */     this.name = getName(authority, (CharSequence)code);
/*     */   }
/*     */   
/*     */   public NamedIdentifier(Citation authority, String code) {
/* 208 */     this(authority, code, null);
/*     */   }
/*     */   
/*     */   public NamedIdentifier(Citation authority, String code, String version) {
/* 221 */     this(toMap(authority, code, version));
/*     */   }
/*     */   
/*     */   private static Map<String, ?> toMap(Citation authority, String code, String version) {
/* 232 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 233 */     if (authority != null)
/* 233 */       properties.put("authority", authority); 
/* 234 */     if (code != null)
/* 234 */       properties.put("code", code); 
/* 235 */     if (version != null)
/* 235 */       properties.put("version", version); 
/* 236 */     return properties;
/*     */   }
/*     */   
/*     */   NamedIdentifier(Map<String, ?> properties, boolean standalone) throws IllegalArgumentException {
/* 255 */     ensureNonNull("properties", properties);
/* 256 */     Object code = null;
/* 257 */     Object codespace = null;
/* 258 */     Object version = null;
/* 259 */     Object authority = null;
/* 260 */     Object remarks = null;
/* 261 */     GrowableInternationalString growable = null;
/* 271 */     String key = null;
/* 272 */     Object value = null;
/* 273 */     for (Map.Entry<String, ?> entry : properties.entrySet()) {
/* 274 */       key = ((String)entry.getKey()).trim().toLowerCase();
/* 275 */       value = entry.getValue();
/* 280 */       switch (key.hashCode()) {
/*     */         case 3373707:
/* 282 */           if (!standalone && key.equals("name")) {
/* 283 */             code = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         case 3059181:
/* 289 */           if (key.equals("code")) {
/* 290 */             code = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         case -1108676807:
/* 296 */           if (key.equals("codespace")) {
/* 297 */             codespace = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         case 351608024:
/* 304 */           if (key.equals("version")) {
/* 305 */             version = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         case 1475610435:
/* 311 */           if (key.equals("authority")) {
/* 312 */             if (value instanceof String)
/* 313 */               value = Citations.fromName(value.toString()); 
/* 315 */             authority = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         case 1091415283:
/* 321 */           if (standalone && key.equals("remarks") && 
/* 322 */             value instanceof InternationalString) {
/* 323 */             remarks = value;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */       } 
/* 333 */       if (standalone && value instanceof String) {
/* 334 */         if (growable == null)
/* 335 */           if (remarks instanceof GrowableInternationalString) {
/* 336 */             growable = (GrowableInternationalString)remarks;
/*     */           } else {
/* 338 */             growable = new GrowableInternationalString();
/*     */           }  
/* 341 */         growable.add("remarks", key, value.toString());
/*     */       } 
/*     */     } 
/* 349 */     if (growable != null && !growable.getLocales().isEmpty())
/* 350 */       if (remarks == null) {
/* 351 */         remarks = growable;
/*     */       } else {
/* 353 */         Logger logger = Logging.getLogger(NamedIdentifier.class);
/* 354 */         LogRecord record = Loggings.format(Level.WARNING, 28);
/* 355 */         record.setLoggerName(logger.getName());
/* 356 */         logger.log(record);
/*     */       }  
/* 363 */     if (codespace == null && authority instanceof Citation)
/* 364 */       codespace = getCodeSpace((Citation)authority); 
/*     */     try {
/* 372 */       key = "code";
/* 372 */       this.code = (String)(value = code);
/* 373 */       key = "version";
/* 373 */       this.version = (String)(value = version);
/* 374 */       key = "codespace";
/* 374 */       this.codespace = (String)(value = codespace);
/* 375 */       key = "authority";
/* 375 */       this.authority = (Citation)(value = authority);
/* 376 */       key = "remarks";
/* 376 */       this.remarks = (InternationalString)(value = remarks);
/* 377 */     } catch (ClassCastException exception) {
/* 378 */       InvalidParameterValueException e = new InvalidParameterValueException(Errors.format(58, key, value), key, value);
/* 380 */       e.initCause(exception);
/* 381 */       throw e;
/*     */     } 
/* 383 */     ensureNonNull("code", code);
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/* 399 */     if (object == null)
/* 400 */       throw new InvalidParameterValueException(Errors.format(143, name), name, object); 
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 411 */     return this.code;
/*     */   }
/*     */   
/*     */   public String getCodeSpace() {
/* 420 */     return this.codespace;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 430 */     return this.authority;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 442 */     return this.version;
/*     */   }
/*     */   
/*     */   public InternationalString getRemarks() {
/* 451 */     return this.remarks;
/*     */   }
/*     */   
/*     */   private synchronized GenericName getName() {
/* 464 */     if (this.name == null)
/* 465 */       this.name = getName(this.authority, this.code); 
/* 467 */     return this.name;
/*     */   }
/*     */   
/*     */   private GenericName getName(Citation authority, CharSequence code) {
/*     */     InternationalString internationalString;
/*     */     LocalName localName;
/* 474 */     if (authority == null)
/* 475 */       return (GenericName)new LocalName(code); 
/* 478 */     if (this.codespace != null) {
/* 479 */       CharSequence title = this.codespace;
/*     */     } else {
/* 481 */       internationalString = getShortestTitle(authority);
/*     */     } 
/* 484 */     synchronized (NamedIdentifier.class) {
/* 485 */       if (SCOPES == null)
/* 486 */         SCOPES = (Map<CharSequence, GenericName>)new WeakValueHashMap(); 
/* 488 */       GenericName scope = SCOPES.get(internationalString);
/* 489 */       if (scope == null) {
/* 490 */         localName = new LocalName((CharSequence)internationalString);
/* 491 */         SCOPES.put(internationalString, localName);
/*     */       } 
/*     */     } 
/* 494 */     return (GenericName)new ScopedName((GenericName)localName, code);
/*     */   }
/*     */   
/*     */   private static InternationalString getShortestTitle(Citation authority) {
/* 501 */     InternationalString title = authority.getTitle();
/* 502 */     int length = title.length();
/* 503 */     Collection<? extends InternationalString> alt = authority.getAlternateTitles();
/* 504 */     if (alt != null)
/* 505 */       for (InternationalString candidate : alt) {
/* 506 */         int candidateLength = candidate.length();
/* 507 */         if (candidateLength > 0 && candidateLength < length) {
/* 508 */           title = candidate;
/* 509 */           length = candidateLength;
/*     */         } 
/*     */       }  
/* 513 */     return title;
/*     */   }
/*     */   
/*     */   private static String getCodeSpace(Citation authority) {
/* 521 */     Collection<? extends Identifier> identifiers = authority.getIdentifiers();
/* 522 */     if (identifiers != null)
/* 523 */       for (Identifier id : identifiers) {
/* 524 */         String identifier = id.getCode();
/* 525 */         if (isValidCodeSpace(identifier))
/* 526 */           return identifier; 
/*     */       }  
/* 531 */     String title = getShortestTitle(authority).toString(null);
/* 532 */     if (isValidCodeSpace(title))
/* 533 */       return title; 
/* 535 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean isValidCodeSpace(String codespace) {
/* 544 */     if (codespace == null)
/* 545 */       return false; 
/* 547 */     for (int i = codespace.length(); --i >= 0;) {
/* 548 */       if (!Character.isJavaIdentifierPart(codespace.charAt(i)))
/* 549 */         return false; 
/*     */     } 
/* 552 */     return true;
/*     */   }
/*     */   
/*     */   public LocalName head() {
/* 561 */     return getName().head();
/*     */   }
/*     */   
/*     */   public LocalName tip() {
/* 570 */     return getName().tip();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public LocalName name() {
/* 578 */     return tip();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public LocalName asLocalName() {
/* 589 */     return tip();
/*     */   }
/*     */   
/*     */   public NameSpace scope() {
/* 598 */     return getName().scope();
/*     */   }
/*     */   
/*     */   public GenericName getScope() {
/* 608 */     return getName().scope().name();
/*     */   }
/*     */   
/*     */   public int depth() {
/* 617 */     return getName().depth();
/*     */   }
/*     */   
/*     */   public List<LocalName> getParsedNames() {
/* 627 */     return getName().getParsedNames();
/*     */   }
/*     */   
/*     */   public ScopedName push(GenericName scope) {
/* 637 */     return getName().push(scope);
/*     */   }
/*     */   
/*     */   public GenericName toFullyQualifiedName() {
/* 646 */     return getName().toFullyQualifiedName();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ScopedName asScopedName() {
/* 657 */     GenericName name = toFullyQualifiedName();
/* 658 */     return (name instanceof ScopedName) ? (ScopedName)name : null;
/*     */   }
/*     */   
/*     */   public InternationalString toInternationalString() {
/* 669 */     return getName().toInternationalString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 679 */     return getName().toString();
/*     */   }
/*     */   
/*     */   public int compareTo(GenericName object) {
/* 691 */     return getName().compareTo(object);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 702 */     if (object != null && object.getClass().equals(getClass())) {
/* 703 */       NamedIdentifier that = (NamedIdentifier)object;
/* 704 */       return (Utilities.equals(this.code, that.code) && Utilities.equals(this.codespace, that.codespace) && Utilities.equals(this.version, that.version) && Utilities.equals(this.authority, that.authority) && Utilities.equals(this.remarks, that.remarks));
/*     */     } 
/* 710 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 718 */     int hash = 895086817;
/* 719 */     if (this.code != null)
/* 720 */       hash ^= this.code.hashCode(); 
/* 722 */     if (this.version != null)
/* 723 */       hash = hash * 37 + this.version.hashCode(); 
/* 725 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\NamedIdentifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */