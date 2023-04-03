/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Factory;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ManyAuthoritiesFactory extends AuthorityFactoryAdapter implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*  72 */   private static final Class<? extends AuthorityFactory>[] FACTORY_TYPES = new Class[] { CRSAuthorityFactory.class, DatumAuthorityFactory.class, CSAuthorityFactory.class, CoordinateOperationAuthorityFactory.class };
/*     */   
/*  84 */   private static final Class<? extends IdentifiedObject>[] OBJECT_TYPES = new Class[] { CoordinateReferenceSystem.class, Datum.class, CoordinateSystem.class, CoordinateOperation.class };
/*     */   
/*     */   private Collection<AuthorityFactory> factories;
/*     */   
/*     */   private final ThreadLocal<Boolean> inProgress;
/*     */   
/*     */   public ManyAuthoritiesFactory(Collection<? extends AuthorityFactory> factories) {
/* 117 */     super(50);
/* 118 */     this.inProgress = new ThreadLocal<Boolean>();
/* 119 */     if (factories != null && !factories.isEmpty()) {
/* 120 */       for (AuthorityFactory factory : factories) {
/* 121 */         if (factory instanceof Factory)
/* 122 */           this.hints.putAll(((Factory)factory).getImplementationHints()); 
/*     */       } 
/* 125 */       this.factories = createFallbacks(factories);
/*     */     } 
/*     */   }
/*     */   
/*     */   Collection<AuthorityFactory> getFactories() {
/* 134 */     return this.factories;
/*     */   }
/*     */   
/*     */   final synchronized void setFactories(Collection<AuthorityFactory> factories) {
/* 142 */     this.factories = createFallbacks(factories);
/*     */   }
/*     */   
/*     */   private static Collection<AuthorityFactory> createFallbacks(Collection<? extends AuthorityFactory> factories) {
/* 157 */     int authorityCount = 0;
/* 158 */     Citation[] authorities = new Citation[factories.size()];
/* 160 */     List[] arrayOfList = new List[authorities.length];
/* 161 */     for (AuthorityFactory factory : factories) {
/*     */       List<AuthorityFactory> list;
/* 167 */       Citation authority = factory.getAuthority();
/*     */       int authorityIndex;
/* 169 */       for (authorityIndex = 0; authorityIndex < authorityCount; authorityIndex++) {
/* 170 */         Citation candidate = authorities[authorityIndex];
/* 171 */         if (Citations.identifierMatches(candidate, authority)) {
/* 172 */           authority = candidate;
/*     */           break;
/*     */         } 
/*     */       } 
/* 177 */       if (authorityIndex == authorityCount) {
/* 178 */         authorities[authorityCount++] = authority;
/* 179 */         arrayOfList[authorityIndex] = list = new ArrayList<AuthorityFactory>(4);
/*     */       } else {
/* 181 */         list = arrayOfList[authorityIndex];
/*     */       } 
/* 183 */       if (!list.contains(factory))
/* 184 */         list.add(factory); 
/*     */     } 
/* 190 */     ArrayList<AuthorityFactory> result = new ArrayList<AuthorityFactory>();
/* 191 */     List<AuthorityFactory> buffer = new ArrayList<AuthorityFactory>(4);
/* 192 */     for (int i = 0; i < authorityCount; i++) {
/* 193 */       Collection<AuthorityFactory> list = arrayOfList[i];
/* 194 */       while (!list.isEmpty()) {
/* 195 */         AuthorityFactory primary = null;
/* 196 */         boolean needOtherChains = false;
/* 197 */         for (Iterator<AuthorityFactory> it = list.iterator(); it.hasNext(); ) {
/* 198 */           AuthorityFactory fallback = it.next();
/* 199 */           if (primary == null) {
/* 200 */             primary = fallback;
/* 201 */           } else if (!FallbackAuthorityFactory.chainable(primary, fallback)) {
/* 202 */             needOtherChains = true;
/*     */             continue;
/*     */           } 
/* 205 */           buffer.add(fallback);
/* 206 */           if (!needOtherChains)
/* 207 */             it.remove(); 
/*     */         } 
/* 210 */         result.add(FallbackAuthorityFactory.create(buffer));
/* 211 */         buffer.clear();
/*     */       } 
/*     */     } 
/* 214 */     result.trimToSize();
/* 215 */     return result;
/*     */   }
/*     */   
/*     */   boolean sameAuthorityCodes(AuthorityFactory factory) {
/* 226 */     return (factory == this);
/*     */   }
/*     */   
/*     */   protected char getSeparator(String code) {
/* 240 */     code = code.trim();
/* 241 */     int length = code.length();
/* 242 */     for (int i = 0; i < length; i++) {
/* 243 */       if (!Character.isLetterOrDigit(code.charAt(i))) {
/* 244 */         if (code.regionMatches(i, "://", 0, 3))
/* 245 */           return '/'; 
/*     */         break;
/*     */       } 
/*     */     } 
/* 250 */     return ':';
/*     */   }
/*     */   
/*     */   private static boolean canSeparateAt(String code, int index) {
/* 269 */     int i = index;
/*     */     while (true) {
/* 271 */       if (--i < 0)
/* 272 */         return false; 
/* 274 */       char c = code.charAt(i);
/* 275 */       if (!Character.isWhitespace(c)) {
/* 276 */         if (!Character.isJavaIdentifierPart(c))
/* 277 */           return false; 
/* 279 */         int length = code.length();
/* 280 */         i = index;
/*     */         while (true) {
/* 282 */           if (++i >= length)
/* 283 */             return false; 
/* 285 */           c = code.charAt(i);
/* 286 */           if (!Character.isWhitespace(c))
/* 287 */             return (Character.isJavaIdentifierPart(c) || c == '-'); 
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Citation getVendor() {
/* 296 */     return Citations.GEOTOOLS;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 305 */     return ALL;
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityNames() {
/* 312 */     Set<String> names = new HashSet<String>();
/* 313 */     Collection<AuthorityFactory> factories = getFactories();
/* 314 */     if (factories != null)
/* 315 */       for (AuthorityFactory factory : factories)
/* 316 */         names.add(Citations.getIdentifier(factory.getAuthority()));  
/* 319 */     return names;
/*     */   }
/*     */   
/*     */   public String getBackingStoreDescription() throws FactoryException {
/* 330 */     return null;
/*     */   }
/*     */   
/*     */   Collection<? super AuthorityFactory> dependencies() {
/* 340 */     return getFactories();
/*     */   }
/*     */   
/*     */   private static boolean exclude(AuthorityFactory factory) {
/* 351 */     if (ManyAuthoritiesFactory.class.isInstance(factory))
/* 352 */       return true; 
/* 354 */     if (factory instanceof AuthorityFactoryAdapter) {
/* 355 */       AuthorityFactoryAdapter adapter = (AuthorityFactoryAdapter)factory;
/* 356 */       return (exclude((AuthorityFactory)adapter.crsFactory) || exclude((AuthorityFactory)adapter.csFactory) || exclude((AuthorityFactory)adapter.datumFactory) || exclude((AuthorityFactory)adapter.operationFactory));
/*     */     } 
/* 361 */     return false;
/*     */   }
/*     */   
/*     */   final void fromFactoryRegistry(String authority, Class<? extends AuthorityFactory> type, Set<AuthorityFactory> addTo) {
/* 372 */     for (int i = 0; i < OBJECT_TYPES.length; i++) {
/* 373 */       if (OBJECT_TYPES[i].isAssignableFrom(type)) {
/*     */         AuthorityFactory factory;
/*     */         try {
/* 376 */           factory = fromFactoryRegistry(authority, (Class)FACTORY_TYPES[i]);
/* 377 */         } catch (FactoryRegistryException e) {}
/* 381 */         if (!exclude(factory))
/* 382 */           addTo.add(factory); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   <T extends AuthorityFactory> T fromFactoryRegistry(String authority, Class<T> type) throws FactoryRegistryException {
/* 402 */     return null;
/*     */   }
/*     */   
/*     */   final <T extends AuthorityFactory> T getAuthorityFactory(Class<T> type, String code) throws NoSuchAuthorityCodeException {
/* 419 */     ensureNonNull("code", code);
/* 420 */     String authority = null;
/* 421 */     FactoryRegistryException cause = null;
/* 422 */     Collection<AuthorityFactory> factories = getFactories();
/* 423 */     char separator = getSeparator(code);
/*     */     int split;
/* 424 */     for (split = code.lastIndexOf(separator); split >= 0; 
/* 425 */       split = code.lastIndexOf(separator, split - 1)) {
/* 427 */       if (canSeparateAt(code, split)) {
/*     */         AuthorityFactory factory;
/* 437 */         authority = code.substring(0, split).trim();
/* 438 */         if (factories != null)
/* 439 */           for (AuthorityFactory authorityFactory : factories) {
/* 440 */             if (type.isAssignableFrom(authorityFactory.getClass()) && 
/* 441 */               Citations.identifierMatches(authorityFactory.getAuthority(), authority))
/* 442 */               return type.cast(authorityFactory); 
/*     */           }  
/*     */         try {
/* 452 */           factory = fromFactoryRegistry(authority, type);
/* 453 */         } catch (FactoryRegistryException exception) {
/* 454 */           cause = exception;
/*     */         } 
/* 457 */         if (factory != null)
/* 458 */           return type.cast(factory); 
/*     */       } 
/*     */     } 
/* 467 */     throw noSuchAuthority(code, authority, cause);
/*     */   }
/*     */   
/*     */   private NoSuchAuthorityCodeException noSuchAuthority(String code, String authority, FactoryRegistryException cause) {
/*     */     String message;
/* 482 */     if (authority == null) {
/* 483 */       authority = Vocabulary.format(252);
/* 484 */       message = Errors.format(96, code);
/*     */     } else {
/* 486 */       message = Errors.format(180, authority);
/*     */     } 
/* 489 */     NoSuchAuthorityCodeException exception = new NoSuchAuthorityCodeException(message, authority, code);
/* 490 */     exception.initCause((Throwable)cause);
/* 491 */     return exception;
/*     */   }
/*     */   
/*     */   protected AuthorityFactory getAuthorityFactory(String code) throws NoSuchAuthorityCodeException {
/* 506 */     return getAuthorityFactory(AuthorityFactory.class, code);
/*     */   }
/*     */   
/*     */   protected DatumAuthorityFactory getDatumAuthorityFactory(String code) throws NoSuchAuthorityCodeException {
/* 520 */     return getAuthorityFactory(DatumAuthorityFactory.class, code);
/*     */   }
/*     */   
/*     */   protected CSAuthorityFactory getCSAuthorityFactory(String code) throws NoSuchAuthorityCodeException {
/* 534 */     return getAuthorityFactory(CSAuthorityFactory.class, code);
/*     */   }
/*     */   
/*     */   protected CRSAuthorityFactory getCRSAuthorityFactory(String code) throws NoSuchAuthorityCodeException {
/* 548 */     return getAuthorityFactory(CRSAuthorityFactory.class, code);
/*     */   }
/*     */   
/*     */   protected CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory(String code) throws NoSuchAuthorityCodeException {
/* 562 */     return getAuthorityFactory(CoordinateOperationAuthorityFactory.class, code);
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 578 */     if (Boolean.TRUE.equals(this.inProgress.get()))
/* 584 */       return Collections.emptySet(); 
/* 586 */     Set<String> codes = new LinkedHashSet<String>();
/* 587 */     Set<AuthorityFactory> done = new HashSet<AuthorityFactory>();
/* 588 */     done.add(this);
/*     */     try {
/* 590 */       this.inProgress.set(Boolean.TRUE);
/* 591 */       for (String authority : getAuthorityNames()) {
/* 592 */         authority = authority.trim();
/* 593 */         char separator = getSeparator(authority);
/* 599 */         StringBuilder code = new StringBuilder(authority);
/* 600 */         int codeBase = code.length();
/* 601 */         if (codeBase != 0 && code.charAt(codeBase - 1) != separator) {
/* 602 */           code.append(separator);
/* 603 */           codeBase = code.length();
/*     */         } 
/* 605 */         code.append("all");
/* 606 */         String dummyCode = code.toString();
/*     */         int i;
/* 612 */         label54: for (i = 0; i < FACTORY_TYPES.length; i++) {
/* 613 */           if (OBJECT_TYPES[i].isAssignableFrom(type)) {
/*     */             AuthorityFactory factory;
/* 616 */             Class<? extends AuthorityFactory> factoryType = FACTORY_TYPES[i];
/*     */             try {
/* 619 */               factory = getAuthorityFactory((Class)factoryType, dummyCode);
/* 620 */             } catch (NoSuchAuthorityCodeException e) {}
/* 623 */             if (done.add(factory)) {
/* 626 */               AuthorityFactory wrapped = factory;
/* 627 */               while (wrapped instanceof AuthorityFactoryAdapter) {
/* 628 */                 AuthorityFactoryAdapter adapter = (AuthorityFactoryAdapter)wrapped;
/*     */                 try {
/* 630 */                   wrapped = adapter.getAuthorityFactory((Class)factoryType, dummyCode);
/* 631 */                 } catch (NoSuchAuthorityCodeException exception) {
/*     */                   continue label54;
/*     */                 } 
/* 638 */                 if (!done.add(wrapped))
/*     */                   continue label54; 
/*     */               } 
/* 648 */               for (String candidate : factory.getAuthorityCodes(type)) {
/* 649 */                 candidate = candidate.trim();
/* 650 */                 if (candidate.length() < codeBase || Character.isLetterOrDigit(candidate.charAt(codeBase - 1)) || !authority.equalsIgnoreCase(candidate.substring(0, codeBase - 1))) {
/* 655 */                   code.setLength(codeBase);
/* 656 */                   code.append(candidate);
/* 657 */                   candidate = code.toString();
/*     */                 } 
/* 659 */                 codes.add(candidate);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 664 */       this.inProgress.remove();
/*     */     } 
/* 666 */     return codes;
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*     */     NoSuchAuthorityCodeException noSuchAuthorityCodeException;
/* 680 */     Set<AuthorityFactory> done = new HashSet<AuthorityFactory>();
/* 681 */     done.add(this);
/* 682 */     FactoryException factoryException = null;
/* 683 */     for (int type = 0; type < FACTORY_TYPES.length; type++) {
/*     */       AuthorityFactory factory;
/*     */       try {
/* 691 */         factory = getAuthorityFactory((Class)FACTORY_TYPES[type], code);
/* 692 */       } catch (NoSuchAuthorityCodeException exception) {
/* 693 */         if (factoryException == null)
/* 694 */           noSuchAuthorityCodeException = exception; 
/*     */       } 
/* 698 */       if (done.add(factory))
/*     */         try {
/* 699 */           return factory.getDescriptionText(code);
/* 700 */         } catch (FactoryException exception) {
/* 708 */           if (noSuchAuthorityCodeException == null || noSuchAuthorityCodeException.getCause() instanceof FactoryRegistryException)
/* 709 */             factoryException = exception; 
/*     */         }  
/*     */     } 
/* 713 */     if (factoryException == null)
/* 714 */       noSuchAuthorityCodeException = noSuchAuthorityCode(IdentifiedObject.class, code); 
/* 716 */     throw noSuchAuthorityCodeException;
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/*     */     NoSuchAuthorityCodeException noSuchAuthorityCodeException;
/* 729 */     Set<AuthorityFactory> done = new HashSet<AuthorityFactory>();
/* 730 */     done.add(this);
/* 731 */     FactoryException factoryException = null;
/* 732 */     for (int type = 0; type < FACTORY_TYPES.length; type++) {
/*     */       AuthorityFactory factory;
/*     */       try {
/* 740 */         factory = getAuthorityFactory((Class)FACTORY_TYPES[type], code);
/* 741 */       } catch (NoSuchAuthorityCodeException exception) {
/* 742 */         if (factoryException == null)
/* 743 */           noSuchAuthorityCodeException = exception; 
/*     */       } 
/* 747 */       if (done.add(factory))
/*     */         try {
/* 748 */           return factory.createObject(code);
/* 749 */         } catch (FactoryException exception) {
/* 757 */           if (noSuchAuthorityCodeException == null || noSuchAuthorityCodeException.getCause() instanceof FactoryRegistryException)
/* 758 */             factoryException = exception; 
/*     */         }  
/*     */     } 
/* 762 */     if (factoryException == null)
/* 763 */       noSuchAuthorityCodeException = noSuchAuthorityCode(IdentifiedObject.class, code); 
/* 765 */     throw noSuchAuthorityCodeException;
/*     */   }
/*     */   
/*     */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 776 */     return new Finder(this, type);
/*     */   }
/*     */   
/*     */   static class Finder extends IdentifiedObjectFinder {
/*     */     protected Finder(ManyAuthoritiesFactory factory, Class<? extends IdentifiedObject> type) {
/* 789 */       super(factory, type);
/*     */     }
/*     */     
/*     */     final Collection<AuthorityFactory> getFactories() {
/* 796 */       return ((ManyAuthoritiesFactory)getProxy().getAuthorityFactory()).getFactories();
/*     */     }
/*     */     
/*     */     final IdentifiedObjectFinder next(Iterator<AuthorityFactory> it) throws FactoryException {
/* 805 */       while (it.hasNext()) {
/* 806 */         AuthorityFactory factory = it.next();
/* 807 */         if (ManyAuthoritiesFactory.exclude(factory))
/*     */           continue; 
/* 810 */         if (factory instanceof AbstractAuthorityFactory) {
/* 811 */           IdentifiedObjectFinder finder = ((AbstractAuthorityFactory)factory).getIdentifiedObjectFinder(getProxy().getType());
/* 813 */           if (finder != null) {
/* 814 */             finder.setFullScanAllowed(isFullScanAllowed());
/* 815 */             return finder;
/*     */           } 
/*     */         } 
/*     */       } 
/* 819 */       return null;
/*     */     }
/*     */     
/*     */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 832 */       IdentifiedObject candidate = createFromIdentifiers(object);
/* 833 */       if (candidate != null)
/* 834 */         return candidate; 
/* 836 */       Collection<AuthorityFactory> factories = getFactories();
/* 837 */       if (factories != null) {
/* 839 */         Iterator<AuthorityFactory> it = factories.iterator();
/*     */         IdentifiedObjectFinder finder;
/* 840 */         while ((finder = next(it)) != null) {
/* 841 */           candidate = finder.find(object);
/* 842 */           if (candidate != null)
/*     */             break; 
/*     */         } 
/*     */       } 
/* 847 */       return candidate;
/*     */     }
/*     */     
/*     */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 860 */       IdentifiedObject candidate = createFromIdentifiers(object);
/* 861 */       if (candidate != null)
/* 862 */         return candidate.getName().toString(); 
/* 864 */       Collection<AuthorityFactory> factories = getFactories();
/* 865 */       if (factories != null) {
/* 867 */         Iterator<AuthorityFactory> it = factories.iterator();
/*     */         IdentifiedObjectFinder finder;
/* 868 */         while ((finder = next(it)) != null) {
/* 869 */           String id = finder.findIdentifier(object);
/* 870 */           if (id != null)
/* 871 */             return id; 
/*     */         } 
/*     */       } 
/* 875 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\ManyAuthoritiesFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */