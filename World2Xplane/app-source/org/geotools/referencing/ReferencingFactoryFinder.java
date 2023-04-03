/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryFinder;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.factory.gridshift.GridShiftLocator;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.LazySet;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.Factory;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ 
/*     */ public final class ReferencingFactoryFinder extends FactoryFinder {
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   private static Set<String> authorityNames;
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/* 113 */     assert Thread.holdsLock(ReferencingFactoryFinder.class);
/* 114 */     if (registry == null)
/* 115 */       registry = (FactoryRegistry)new FactoryCreator(new Class[] { DatumFactory.class, CSFactory.class, CRSFactory.class, DatumAuthorityFactory.class, CSAuthorityFactory.class, CRSAuthorityFactory.class, MathTransformFactory.class, CoordinateOperationFactory.class, CoordinateOperationAuthorityFactory.class, GridShiftLocator.class }); 
/* 127 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized Set<String> getAuthorityNames() {
/* 142 */     if (authorityNames == null) {
/* 143 */       authorityNames = new LinkedHashSet<String>();
/* 144 */       Hints hints = EMPTY_HINTS;
/* 145 */       for (int i = 0;; i++) {
/*     */         Set<? extends AuthorityFactory> factories;
/* 147 */         switch (i) {
/*     */           case 0:
/* 148 */             factories = (Set)getCRSAuthorityFactories(hints);
/*     */             break;
/*     */           case 1:
/* 149 */             factories = (Set)getCSAuthorityFactories(hints);
/*     */             break;
/*     */           case 2:
/* 150 */             factories = (Set)getDatumAuthorityFactories(hints);
/*     */             break;
/*     */           case 3:
/* 151 */             factories = (Set)getCoordinateOperationAuthorityFactories(hints);
/*     */             break;
/*     */           default:
/*     */             break;
/*     */         } 
/* 154 */         for (AuthorityFactory factory : factories) {
/* 155 */           Citation authority = factory.getAuthority();
/* 156 */           if (authority != null) {
/* 157 */             authorityNames.add(Citations.getIdentifier(authority));
/* 158 */             for (Identifier id : authority.getIdentifiers())
/* 159 */               authorityNames.add(id.getCode()); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 164 */       authorityNames = Collections.unmodifiableSet(authorityNames);
/*     */     } 
/* 166 */     return authorityNames;
/*     */   }
/*     */   
/*     */   private static synchronized <T extends Factory> Set<T> getFactories(Class<T> category, Hints hints) {
/* 179 */     hints = mergeSystemHints(hints);
/* 180 */     return (Set<T>)new LazySet(getServiceRegistry().getServiceProviders(category, null, hints));
/*     */   }
/*     */   
/*     */   private static synchronized <T extends Factory> T getFactory(Class<T> category, Hints hints, Hints.Key key) throws FactoryRegistryException {
/* 196 */     hints = mergeSystemHints(hints);
/* 197 */     return (T)getServiceRegistry().getServiceProvider(category, null, hints, key);
/*     */   }
/*     */   
/*     */   private static synchronized <T extends AuthorityFactory> T getAuthorityFactory(Class<T> category, String authority, Hints hints, Hints.Key key) throws FactoryRegistryException {
/* 219 */     hints = mergeSystemHints(hints);
/* 220 */     return (T)getServiceRegistry().getServiceProvider(category, new AuthorityFilter(authority), hints, key);
/*     */   }
/*     */   
/*     */   public static DatumFactory getDatumFactory(Hints hints) throws FactoryRegistryException {
/* 236 */     return getFactory(DatumFactory.class, hints, (Hints.Key)Hints.DATUM_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<DatumFactory> getDatumFactories(Hints hints) {
/* 246 */     return getFactories(DatumFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CSFactory getCSFactory(Hints hints) throws FactoryRegistryException {
/* 262 */     return getFactory(CSFactory.class, hints, (Hints.Key)Hints.CS_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CSFactory> getCSFactories(Hints hints) {
/* 272 */     return getFactories(CSFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CRSFactory getCRSFactory(Hints hints) throws FactoryRegistryException {
/* 288 */     return getFactory(CRSFactory.class, hints, (Hints.Key)Hints.CRS_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CRSFactory> getCRSFactories(Hints hints) {
/* 298 */     return getFactories(CRSFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CoordinateOperationFactory getCoordinateOperationFactory(Hints hints) throws FactoryRegistryException {
/* 322 */     return getFactory(CoordinateOperationFactory.class, hints, (Hints.Key)Hints.COORDINATE_OPERATION_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CoordinateOperationFactory> getCoordinateOperationFactories(Hints hints) {
/* 334 */     return getFactories(CoordinateOperationFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static DatumAuthorityFactory getDatumAuthorityFactory(String authority, Hints hints) throws FactoryRegistryException {
/* 354 */     return getAuthorityFactory(DatumAuthorityFactory.class, authority, hints, (Hints.Key)Hints.DATUM_AUTHORITY_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<DatumAuthorityFactory> getDatumAuthorityFactories(Hints hints) {
/* 366 */     return getFactories(DatumAuthorityFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CSAuthorityFactory getCSAuthorityFactory(String authority, Hints hints) throws FactoryRegistryException {
/* 392 */     return getAuthorityFactory(CSAuthorityFactory.class, authority, hints, (Hints.Key)Hints.CS_AUTHORITY_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CSAuthorityFactory> getCSAuthorityFactories(Hints hints) {
/* 403 */     return getFactories(CSAuthorityFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CRSAuthorityFactory getCRSAuthorityFactory(String authority, Hints hints) throws FactoryRegistryException {
/* 440 */     return getAuthorityFactory(CRSAuthorityFactory.class, authority, hints, (Hints.Key)Hints.CRS_AUTHORITY_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CRSAuthorityFactory> getCRSAuthorityFactories(Hints hints) {
/* 455 */     return getFactories(CRSAuthorityFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory(String authority, Hints hints) throws FactoryRegistryException {
/* 475 */     return getAuthorityFactory(CoordinateOperationAuthorityFactory.class, authority, hints, (Hints.Key)Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<CoordinateOperationAuthorityFactory> getCoordinateOperationAuthorityFactories(Hints hints) {
/* 489 */     return getFactories(CoordinateOperationAuthorityFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static Set<GridShiftLocator> getGridShiftLocators(Hints hints) {
/* 502 */     return getFactories(GridShiftLocator.class, hints);
/*     */   }
/*     */   
/*     */   public static MathTransformFactory getMathTransformFactory(Hints hints) throws FactoryRegistryException {
/* 520 */     return getFactory(MathTransformFactory.class, hints, (Hints.Key)Hints.MATH_TRANSFORM_FACTORY);
/*     */   }
/*     */   
/*     */   public static Set<MathTransformFactory> getMathTransformFactories(Hints hints) {
/* 531 */     return getFactories(MathTransformFactory.class, hints);
/*     */   }
/*     */   
/*     */   public static synchronized boolean setVendorOrdering(String vendor1, String vendor2) {
/* 551 */     return getServiceRegistry().setOrdering(Factory.class, true, new VendorFilter(vendor1), new VendorFilter(vendor2));
/*     */   }
/*     */   
/*     */   public static synchronized boolean unsetVendorOrdering(String vendor1, String vendor2) {
/* 568 */     return getServiceRegistry().setOrdering(Factory.class, false, new VendorFilter(vendor1), new VendorFilter(vendor2));
/*     */   }
/*     */   
/*     */   private static final class VendorFilter implements ServiceRegistry.Filter {
/*     */     private final String vendor;
/*     */     
/*     */     public VendorFilter(String vendor) {
/* 582 */       this.vendor = vendor;
/*     */     }
/*     */     
/*     */     public boolean filter(Object provider) {
/* 587 */       return Citations.titleMatches(((Factory)provider).getVendor(), this.vendor);
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized boolean setAuthorityOrdering(String authority1, String authority2) {
/* 608 */     return getServiceRegistry().setOrdering(AuthorityFactory.class, true, new AuthorityFilter(authority1), new AuthorityFilter(authority2));
/*     */   }
/*     */   
/*     */   public static synchronized boolean unsetAuthorityOrdering(String authority1, String authority2) {
/* 625 */     return getServiceRegistry().setOrdering(AuthorityFactory.class, false, new AuthorityFilter(authority1), new AuthorityFilter(authority2));
/*     */   }
/*     */   
/*     */   private static final class AuthorityFilter implements ServiceRegistry.Filter {
/*     */     private final String authority;
/*     */     
/*     */     public AuthorityFilter(String authority) {
/* 639 */       this.authority = authority;
/*     */     }
/*     */     
/*     */     public boolean filter(Object provider) {
/* 644 */       if (this.authority == null)
/* 647 */         return false; 
/* 649 */       return Citations.identifierMatches(((AuthorityFactory)provider).getAuthority(), this.authority);
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized void addAuthorityFactory(AuthorityFactory authority) {
/* 661 */     if (registry == null)
/* 662 */       scanForPlugins(); 
/* 664 */     getServiceRegistry().registerServiceProvider(authority);
/* 665 */     authorityNames = null;
/*     */   }
/*     */   
/*     */   public static synchronized void removeAuthorityFactory(AuthorityFactory authority) {
/* 676 */     getServiceRegistry().deregisterServiceProvider(authority);
/* 677 */     authorityNames = null;
/*     */   }
/*     */   
/*     */   public static synchronized boolean isRegistered(Factory factory) {
/* 688 */     return factory.equals(getServiceRegistry().getServiceProviderByClass(factory.getClass()));
/*     */   }
/*     */   
/*     */   public static void scanForPlugins() {
/* 700 */     synchronized (ReferencingFactoryFinder.class) {
/* 701 */       authorityNames = null;
/* 702 */       if (registry != null)
/* 703 */         registry.scanForPlugins(); 
/*     */     } 
/* 706 */     GeoTools.fireConfigurationChanged();
/*     */   }
/*     */   
/*     */   public static synchronized void listProviders(Writer out, Locale locale) throws IOException {
/* 721 */     FactoryRegistry registry = getServiceRegistry();
/* 722 */     (new FactoryPrinter()).list(registry, out, locale);
/*     */   }
/*     */   
/*     */   public static void reset() {
/* 729 */     FactoryRegistry copy = registry;
/* 730 */     registry = null;
/* 731 */     if (copy != null)
/* 732 */       copy.deregisterAll(); 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 767 */     Arguments arguments = new Arguments(args);
/* 768 */     args = arguments.getRemainingArguments(0);
/*     */     try {
/* 770 */       listProviders(arguments.out, arguments.locale);
/* 771 */     } catch (Exception exception) {
/* 772 */       exception.printStackTrace(arguments.err);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\ReferencingFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */