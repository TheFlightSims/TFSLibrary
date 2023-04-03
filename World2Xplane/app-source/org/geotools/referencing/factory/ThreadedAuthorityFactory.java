/*      */ package org.geotools.referencing.factory;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.factory.BufferedFactory;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.metadata.extent.Extent;
/*      */ import org.opengis.parameter.ParameterDescriptor;
/*      */ import org.opengis.referencing.AuthorityFactory;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.DerivedCRS;
/*      */ import org.opengis.referencing.crs.EngineeringCRS;
/*      */ import org.opengis.referencing.crs.GeocentricCRS;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.crs.ImageCRS;
/*      */ import org.opengis.referencing.crs.ProjectedCRS;
/*      */ import org.opengis.referencing.crs.TemporalCRS;
/*      */ import org.opengis.referencing.crs.VerticalCRS;
/*      */ import org.opengis.referencing.cs.CartesianCS;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.CylindricalCS;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.cs.PolarCS;
/*      */ import org.opengis.referencing.cs.SphericalCS;
/*      */ import org.opengis.referencing.cs.TimeCS;
/*      */ import org.opengis.referencing.cs.VerticalCS;
/*      */ import org.opengis.referencing.datum.Datum;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.EngineeringDatum;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.ImageDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.TemporalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class ThreadedAuthorityFactory extends AbstractAuthorityFactory implements BufferedFactory {
/*      */   static final int DEFAULT_MAX = 20;
/*      */   
/*      */   AbstractAuthorityFactory backingStore;
/*      */   
/*      */   private final OldReferencingObjectCache objectCache;
/*      */   
/*   99 */   private final Map<IdentifiedObject, IdentifiedObject> findPool = new WeakHashMap<IdentifiedObject, IdentifiedObject>();
/*      */   
/*      */   protected ThreadedAuthorityFactory(AbstractAuthorityFactory factory) {
/*  114 */     this(factory, 20);
/*      */   }
/*      */   
/*      */   protected ThreadedAuthorityFactory(AbstractAuthorityFactory factory, int maxStrongReferences) {
/*  133 */     super(factory.getPriority());
/*  134 */     while (factory instanceof ThreadedAuthorityFactory)
/*  135 */       factory = ((ThreadedAuthorityFactory)factory).backingStore; 
/*  137 */     this.backingStore = factory;
/*  138 */     this.objectCache = new OldReferencingObjectCache(maxStrongReferences);
/*  139 */     completeHints();
/*      */   }
/*      */   
/*      */   ThreadedAuthorityFactory(int priority, int maxStrongReferences) {
/*  155 */     super(priority);
/*  156 */     this.objectCache = new OldReferencingObjectCache(maxStrongReferences);
/*      */   }
/*      */   
/*      */   final void completeHints() {
/*  174 */     if (this.backingStore instanceof org.opengis.referencing.datum.DatumAuthorityFactory)
/*  175 */       this.hints.put(Hints.DATUM_AUTHORITY_FACTORY, this.backingStore); 
/*  177 */     if (this.backingStore instanceof org.opengis.referencing.cs.CSAuthorityFactory)
/*  178 */       this.hints.put(Hints.CS_AUTHORITY_FACTORY, this.backingStore); 
/*  180 */     if (this.backingStore instanceof org.opengis.referencing.crs.CRSAuthorityFactory)
/*  181 */       this.hints.put(Hints.CRS_AUTHORITY_FACTORY, this.backingStore); 
/*  183 */     if (this.backingStore instanceof org.opengis.referencing.operation.CoordinateOperationAuthorityFactory)
/*  184 */       this.hints.put(Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY, this.backingStore); 
/*      */   }
/*      */   
/*      */   Collection<? super AuthorityFactory> dependencies() {
/*      */     Object object;
/*      */     try {
/*  196 */       object = getBackingStore();
/*  197 */     } catch (FactoryException e) {
/*  198 */       object = e;
/*      */     } 
/*  200 */     return Collections.singleton(object);
/*      */   }
/*      */   
/*      */   AbstractAuthorityFactory getBackingStore() throws FactoryException {
/*  210 */     if (this.backingStore == null)
/*  211 */       throw new FactoryException(Errors.format(42)); 
/*  213 */     return this.backingStore;
/*      */   }
/*      */   
/*      */   boolean isAvailable() {
/*      */     try {
/*  224 */       return getBackingStore().isAvailable();
/*  225 */     } catch (FactoryNotFoundException exception) {
/*      */     
/*  233 */     } catch (FactoryException exception) {
/*  238 */       Citation citation = getAuthority();
/*  239 */       Collection titles = citation.getAlternateTitles();
/*  240 */       InternationalString title = citation.getTitle();
/*  241 */       if (titles != null)
/*  242 */         for (Iterator<InternationalString> it = titles.iterator(); it.hasNext(); ) {
/*  248 */           InternationalString candidate = it.next();
/*  249 */           if (candidate.length() > title.length())
/*  250 */             title = candidate; 
/*      */         }  
/*  254 */       LogRecord record = Loggings.format(Level.WARNING, 42, title);
/*  256 */       record.setSourceClassName(getClass().getName());
/*  257 */       record.setSourceMethodName("isAvailable");
/*  258 */       record.setThrown((Throwable)exception);
/*  259 */       record.setLoggerName(LOGGER.getName());
/*  260 */       LOGGER.log(record);
/*      */     } 
/*  262 */     return false;
/*      */   }
/*      */   
/*      */   boolean sameAuthorityCodes(AuthorityFactory factory) {
/*  273 */     AbstractAuthorityFactory backingStore = this.backingStore;
/*  274 */     if (backingStore != null && backingStore.sameAuthorityCodes(factory))
/*  275 */       return true; 
/*  277 */     return super.sameAuthorityCodes(factory);
/*      */   }
/*      */   
/*      */   public Citation getVendor() {
/*  285 */     return (this.backingStore != null) ? this.backingStore.getVendor() : super.getVendor();
/*      */   }
/*      */   
/*      */   public Citation getAuthority() {
/*  293 */     return (this.backingStore != null) ? this.backingStore.getAuthority() : null;
/*      */   }
/*      */   
/*      */   public String getBackingStoreDescription() throws FactoryException {
/*  304 */     return getBackingStore().getBackingStoreDescription();
/*      */   }
/*      */   
/*      */   public Set<String> getAuthorityCodes(Class type) throws FactoryException {
/*  320 */     return getBackingStore().getAuthorityCodes(type);
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*  335 */     return getBackingStore().getDescriptionText(code);
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObject createObject(String code) throws FactoryException {
/*      */     IdentifiedObject object;
/*  346 */     String key = trimAuthority(code);
/*  347 */     Object cached = this.objectCache.get(key);
/*  348 */     if (cached instanceof IdentifiedObject) {
/*  349 */       object = (IdentifiedObject)cached;
/*      */     } else {
/*  351 */       object = getBackingStore().createObject(code);
/*      */     } 
/*  353 */     this.objectCache.put(key, object);
/*  354 */     return object;
/*      */   }
/*      */   
/*      */   public synchronized Datum createDatum(String code) throws FactoryException {
/*      */     Datum datum;
/*  365 */     String key = trimAuthority(code);
/*  366 */     Object cached = this.objectCache.get(key);
/*  367 */     if (cached instanceof Datum) {
/*  368 */       datum = (Datum)cached;
/*      */     } else {
/*  370 */       datum = getBackingStore().createDatum(code);
/*      */     } 
/*  372 */     this.objectCache.put(key, datum);
/*  373 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/*      */     EngineeringDatum datum;
/*  384 */     String key = trimAuthority(code);
/*  385 */     Object cached = this.objectCache.get(key);
/*  386 */     if (cached instanceof EngineeringDatum) {
/*  387 */       datum = (EngineeringDatum)cached;
/*      */     } else {
/*  389 */       datum = getBackingStore().createEngineeringDatum(code);
/*      */     } 
/*  391 */     this.objectCache.put(key, datum);
/*  392 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized ImageDatum createImageDatum(String code) throws FactoryException {
/*      */     ImageDatum datum;
/*  403 */     String key = trimAuthority(code);
/*  404 */     Object cached = this.objectCache.get(key);
/*  405 */     if (cached instanceof ImageDatum) {
/*  406 */       datum = (ImageDatum)cached;
/*      */     } else {
/*  408 */       datum = getBackingStore().createImageDatum(code);
/*      */     } 
/*  410 */     this.objectCache.put(key, datum);
/*  411 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized VerticalDatum createVerticalDatum(String code) throws FactoryException {
/*      */     VerticalDatum datum;
/*  422 */     String key = trimAuthority(code);
/*  423 */     Object cached = this.objectCache.get(key);
/*  424 */     if (cached instanceof VerticalDatum) {
/*  425 */       datum = (VerticalDatum)cached;
/*      */     } else {
/*  427 */       datum = getBackingStore().createVerticalDatum(code);
/*      */     } 
/*  429 */     this.objectCache.put(key, datum);
/*  430 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized TemporalDatum createTemporalDatum(String code) throws FactoryException {
/*      */     TemporalDatum datum;
/*  441 */     String key = trimAuthority(code);
/*  442 */     Object cached = this.objectCache.get(key);
/*  443 */     if (cached instanceof TemporalDatum) {
/*  444 */       datum = (TemporalDatum)cached;
/*      */     } else {
/*  446 */       datum = getBackingStore().createTemporalDatum(code);
/*      */     } 
/*  448 */     this.objectCache.put(key, datum);
/*  449 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/*      */     GeodeticDatum datum;
/*  460 */     String key = trimAuthority(code);
/*  461 */     Object cached = this.objectCache.get(key);
/*  462 */     if (cached instanceof GeodeticDatum) {
/*  463 */       datum = (GeodeticDatum)cached;
/*      */     } else {
/*  465 */       datum = getBackingStore().createGeodeticDatum(code);
/*      */     } 
/*  467 */     this.objectCache.put(key, datum);
/*  468 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized Ellipsoid createEllipsoid(String code) throws FactoryException {
/*      */     Ellipsoid ellipsoid;
/*  479 */     String key = trimAuthority(code);
/*  480 */     Object cached = this.objectCache.get(key);
/*  481 */     if (cached instanceof Ellipsoid) {
/*  482 */       ellipsoid = (Ellipsoid)cached;
/*      */     } else {
/*  484 */       ellipsoid = getBackingStore().createEllipsoid(code);
/*      */     } 
/*  486 */     this.objectCache.put(key, ellipsoid);
/*  487 */     return ellipsoid;
/*      */   }
/*      */   
/*      */   public synchronized PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/*      */     PrimeMeridian meridian;
/*  498 */     String key = trimAuthority(code);
/*  499 */     Object cached = this.objectCache.get(key);
/*  500 */     if (cached instanceof PrimeMeridian) {
/*  501 */       meridian = (PrimeMeridian)cached;
/*      */     } else {
/*  503 */       meridian = getBackingStore().createPrimeMeridian(code);
/*      */     } 
/*  505 */     this.objectCache.put(key, meridian);
/*  506 */     return meridian;
/*      */   }
/*      */   
/*      */   public synchronized Extent createExtent(String code) throws FactoryException {
/*      */     Extent extent;
/*  517 */     String key = trimAuthority(code);
/*  518 */     Object cached = this.objectCache.get(key);
/*  519 */     if (cached instanceof Extent) {
/*  520 */       extent = (Extent)cached;
/*      */     } else {
/*  522 */       extent = getBackingStore().createExtent(code);
/*      */     } 
/*  524 */     this.objectCache.put(key, extent);
/*  525 */     return extent;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/*      */     CoordinateSystem cs;
/*  536 */     String key = trimAuthority(code);
/*  537 */     Object cached = this.objectCache.get(key);
/*  538 */     if (cached instanceof CoordinateSystem) {
/*  539 */       cs = (CoordinateSystem)cached;
/*      */     } else {
/*  541 */       cs = getBackingStore().createCoordinateSystem(code);
/*      */     } 
/*  543 */     this.objectCache.put(key, cs);
/*  544 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CartesianCS createCartesianCS(String code) throws FactoryException {
/*      */     CartesianCS cs;
/*  555 */     String key = trimAuthority(code);
/*  556 */     Object cached = this.objectCache.get(key);
/*  557 */     if (cached instanceof CartesianCS) {
/*  558 */       cs = (CartesianCS)cached;
/*      */     } else {
/*  560 */       cs = getBackingStore().createCartesianCS(code);
/*      */     } 
/*  562 */     this.objectCache.put(key, cs);
/*  563 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized PolarCS createPolarCS(String code) throws FactoryException {
/*      */     PolarCS cs;
/*  574 */     String key = trimAuthority(code);
/*  575 */     Object cached = this.objectCache.get(key);
/*  576 */     if (cached instanceof PolarCS) {
/*  577 */       cs = (PolarCS)cached;
/*      */     } else {
/*  579 */       cs = getBackingStore().createPolarCS(code);
/*      */     } 
/*  581 */     this.objectCache.put(key, cs);
/*  582 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CylindricalCS createCylindricalCS(String code) throws FactoryException {
/*      */     CylindricalCS cs;
/*  593 */     String key = trimAuthority(code);
/*  594 */     Object cached = this.objectCache.get(key);
/*  595 */     if (cached instanceof CylindricalCS) {
/*  596 */       cs = (CylindricalCS)cached;
/*      */     } else {
/*  598 */       cs = getBackingStore().createCylindricalCS(code);
/*      */     } 
/*  600 */     this.objectCache.put(key, cs);
/*  601 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized SphericalCS createSphericalCS(String code) throws FactoryException {
/*      */     SphericalCS cs;
/*  612 */     String key = trimAuthority(code);
/*  613 */     Object cached = this.objectCache.get(key);
/*  614 */     if (cached instanceof SphericalCS) {
/*  615 */       cs = (SphericalCS)cached;
/*      */     } else {
/*  617 */       cs = getBackingStore().createSphericalCS(code);
/*      */     } 
/*  619 */     this.objectCache.put(key, cs);
/*  620 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/*      */     EllipsoidalCS cs;
/*  631 */     String key = trimAuthority(code);
/*  632 */     Object cached = this.objectCache.get(key);
/*  633 */     if (cached instanceof EllipsoidalCS) {
/*  634 */       cs = (EllipsoidalCS)cached;
/*      */     } else {
/*  636 */       cs = getBackingStore().createEllipsoidalCS(code);
/*      */     } 
/*  638 */     this.objectCache.put(key, cs);
/*  639 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized VerticalCS createVerticalCS(String code) throws FactoryException {
/*      */     VerticalCS cs;
/*  650 */     String key = trimAuthority(code);
/*  651 */     Object cached = this.objectCache.get(key);
/*  652 */     if (cached instanceof VerticalCS) {
/*  653 */       cs = (VerticalCS)cached;
/*      */     } else {
/*  655 */       cs = getBackingStore().createVerticalCS(code);
/*      */     } 
/*  657 */     this.objectCache.put(key, cs);
/*  658 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized TimeCS createTimeCS(String code) throws FactoryException {
/*      */     TimeCS cs;
/*  669 */     String key = trimAuthority(code);
/*  670 */     Object cached = this.objectCache.get(key);
/*  671 */     if (cached instanceof TimeCS) {
/*  672 */       cs = (TimeCS)cached;
/*      */     } else {
/*  674 */       cs = getBackingStore().createTimeCS(code);
/*      */     } 
/*  676 */     this.objectCache.put(key, cs);
/*  677 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/*      */     CoordinateSystemAxis axis;
/*  688 */     String key = trimAuthority(code);
/*  689 */     Object cached = this.objectCache.get(key);
/*  690 */     if (cached instanceof CoordinateSystemAxis) {
/*  691 */       axis = (CoordinateSystemAxis)cached;
/*      */     } else {
/*  693 */       axis = getBackingStore().createCoordinateSystemAxis(code);
/*      */     } 
/*  695 */     this.objectCache.put(key, axis);
/*  696 */     return axis;
/*      */   }
/*      */   
/*      */   public synchronized Unit<?> createUnit(String code) throws FactoryException {
/*      */     Unit<?> unit;
/*  707 */     String key = trimAuthority(code);
/*  708 */     Object cached = this.objectCache.get(key);
/*  709 */     if (cached instanceof Unit) {
/*  710 */       unit = (Unit)cached;
/*      */     } else {
/*  712 */       unit = getBackingStore().createUnit(code);
/*      */     } 
/*  714 */     this.objectCache.put(key, unit);
/*  715 */     return unit;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/*      */     CoordinateReferenceSystem crs;
/*  726 */     String key = trimAuthority(code);
/*  727 */     Object cached = this.objectCache.get(key);
/*  728 */     if (cached instanceof CoordinateReferenceSystem) {
/*  729 */       crs = (CoordinateReferenceSystem)cached;
/*      */     } else {
/*  731 */       crs = getBackingStore().createCoordinateReferenceSystem(code);
/*      */     } 
/*  733 */     this.objectCache.put(key, crs);
/*  734 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized CompoundCRS createCompoundCRS(String code) throws FactoryException {
/*      */     CompoundCRS crs;
/*  745 */     String key = trimAuthority(code);
/*  746 */     Object cached = this.objectCache.get(key);
/*  747 */     if (cached instanceof CompoundCRS) {
/*  748 */       crs = (CompoundCRS)cached;
/*      */     } else {
/*  750 */       crs = getBackingStore().createCompoundCRS(code);
/*      */     } 
/*  752 */     this.objectCache.put(key, crs);
/*  753 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized DerivedCRS createDerivedCRS(String code) throws FactoryException {
/*      */     DerivedCRS crs;
/*  764 */     String key = trimAuthority(code);
/*  765 */     Object cached = this.objectCache.get(key);
/*  766 */     if (cached instanceof DerivedCRS) {
/*  767 */       crs = (DerivedCRS)cached;
/*      */     } else {
/*  769 */       crs = getBackingStore().createDerivedCRS(code);
/*      */     } 
/*  771 */     this.objectCache.put(key, crs);
/*  772 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/*      */     EngineeringCRS crs;
/*  783 */     String key = trimAuthority(code);
/*  784 */     Object cached = this.objectCache.get(key);
/*  785 */     if (cached instanceof EngineeringCRS) {
/*  786 */       crs = (EngineeringCRS)cached;
/*      */     } else {
/*  788 */       crs = getBackingStore().createEngineeringCRS(code);
/*      */     } 
/*  790 */     this.objectCache.put(key, crs);
/*  791 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized GeographicCRS createGeographicCRS(String code) throws FactoryException {
/*      */     GeographicCRS crs;
/*  802 */     String key = trimAuthority(code);
/*  803 */     Object cached = this.objectCache.get(key);
/*  804 */     if (cached instanceof GeographicCRS) {
/*  805 */       crs = (GeographicCRS)cached;
/*      */     } else {
/*  807 */       crs = getBackingStore().createGeographicCRS(code);
/*      */     } 
/*  809 */     this.objectCache.put(key, crs);
/*  810 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/*      */     GeocentricCRS crs;
/*  821 */     String key = trimAuthority(code);
/*  822 */     Object cached = this.objectCache.get(key);
/*  823 */     if (cached instanceof GeocentricCRS) {
/*  824 */       crs = (GeocentricCRS)cached;
/*      */     } else {
/*  826 */       crs = getBackingStore().createGeocentricCRS(code);
/*      */     } 
/*  828 */     this.objectCache.put(key, crs);
/*  829 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ImageCRS createImageCRS(String code) throws FactoryException {
/*      */     ImageCRS crs;
/*  840 */     String key = trimAuthority(code);
/*  841 */     Object cached = this.objectCache.get(key);
/*  842 */     if (cached instanceof ImageCRS) {
/*  843 */       crs = (ImageCRS)cached;
/*      */     } else {
/*  845 */       crs = getBackingStore().createImageCRS(code);
/*      */     } 
/*  847 */     this.objectCache.put(key, crs);
/*  848 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/*      */     ProjectedCRS crs;
/*  859 */     String key = trimAuthority(code);
/*  860 */     Object cached = this.objectCache.get(key);
/*  861 */     if (cached instanceof ProjectedCRS) {
/*  862 */       crs = (ProjectedCRS)cached;
/*      */     } else {
/*  864 */       crs = getBackingStore().createProjectedCRS(code);
/*      */     } 
/*  866 */     this.objectCache.put(key, crs);
/*  867 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized TemporalCRS createTemporalCRS(String code) throws FactoryException {
/*      */     TemporalCRS crs;
/*  878 */     String key = trimAuthority(code);
/*  879 */     Object cached = this.objectCache.get(key);
/*  880 */     if (cached instanceof TemporalCRS) {
/*  881 */       crs = (TemporalCRS)cached;
/*      */     } else {
/*  883 */       crs = getBackingStore().createTemporalCRS(code);
/*      */     } 
/*  885 */     this.objectCache.put(key, crs);
/*  886 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized VerticalCRS createVerticalCRS(String code) throws FactoryException {
/*      */     VerticalCRS crs;
/*  897 */     String key = trimAuthority(code);
/*  898 */     Object cached = this.objectCache.get(key);
/*  899 */     if (cached instanceof VerticalCRS) {
/*  900 */       crs = (VerticalCRS)cached;
/*      */     } else {
/*  902 */       crs = getBackingStore().createVerticalCRS(code);
/*      */     } 
/*  904 */     this.objectCache.put(key, crs);
/*  905 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ParameterDescriptor createParameterDescriptor(String code) throws FactoryException {
/*      */     ParameterDescriptor parameter;
/*  916 */     String key = trimAuthority(code);
/*  917 */     Object cached = this.objectCache.get(key);
/*  918 */     if (cached instanceof ParameterDescriptor) {
/*  919 */       parameter = (ParameterDescriptor)cached;
/*      */     } else {
/*  921 */       parameter = getBackingStore().createParameterDescriptor(code);
/*      */     } 
/*  923 */     this.objectCache.put(key, parameter);
/*  924 */     return parameter;
/*      */   }
/*      */   
/*      */   public synchronized OperationMethod createOperationMethod(String code) throws FactoryException {
/*      */     OperationMethod method;
/*  935 */     String key = trimAuthority(code);
/*  936 */     Object cached = this.objectCache.get(key);
/*  937 */     if (cached instanceof OperationMethod) {
/*  938 */       method = (OperationMethod)cached;
/*      */     } else {
/*  940 */       method = getBackingStore().createOperationMethod(code);
/*      */     } 
/*  942 */     this.objectCache.put(key, method);
/*  943 */     return method;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/*      */     CoordinateOperation operation;
/*  954 */     String key = trimAuthority(code);
/*  955 */     Object cached = this.objectCache.get(key);
/*  956 */     if (cached instanceof CoordinateOperation) {
/*  957 */       operation = (CoordinateOperation)cached;
/*      */     } else {
/*  959 */       operation = getBackingStore().createCoordinateOperation(code);
/*      */     } 
/*  961 */     this.objectCache.put(key, operation);
/*  962 */     return operation;
/*      */   }
/*      */   
/*      */   public synchronized Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/*      */     Set<CoordinateOperation> operations;
/*  974 */     CodePair key = new CodePair(trimAuthority(sourceCode), trimAuthority(targetCode));
/*  975 */     Object cached = this.objectCache.get(key);
/*  976 */     if (cached instanceof CoordinateOperation) {
/*  977 */       operations = (Set<CoordinateOperation>)cached;
/*      */     } else {
/*  979 */       operations = Collections.unmodifiableSet(getBackingStore().createFromCoordinateReferenceSystemCodes(sourceCode, targetCode));
/*      */     } 
/*  982 */     this.objectCache.put(key, operations);
/*  983 */     return operations;
/*      */   }
/*      */   
/*      */   private static final class CodePair {
/*      */     private final String source;
/*      */     
/*      */     private final String target;
/*      */     
/*      */     public CodePair(String source, String target) {
/*  994 */       this.source = source;
/*  995 */       this.target = target;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1000 */       int code = 0;
/* 1001 */       if (this.source != null)
/* 1001 */         code = this.source.hashCode(); 
/* 1002 */       if (this.target != null)
/* 1002 */         code += this.target.hashCode() * 37; 
/* 1003 */       return code;
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/* 1008 */       if (other instanceof CodePair) {
/* 1009 */         CodePair that = (CodePair)other;
/* 1010 */         return (Utilities.equals(this.source, that.source) && Utilities.equals(this.target, that.target));
/*      */       } 
/* 1013 */       return false;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1018 */       return this.source + " ⇨ " + this.target;
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 1031 */     return new Finder(getBackingStore().getIdentifiedObjectFinder(type));
/*      */   }
/*      */   
/*      */   private final class Finder extends IdentifiedObjectFinder.Adapter {
/*      */     Finder(IdentifiedObjectFinder finder) {}
/*      */     
/*      */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/*      */       IdentifiedObject candidate;
/* 1068 */       synchronized (ThreadedAuthorityFactory.this.findPool) {
/* 1069 */         candidate = (IdentifiedObject)ThreadedAuthorityFactory.this.findPool.get(object);
/*      */       } 
/* 1071 */       if (candidate == null) {
/* 1074 */         candidate = this.finder.find(object);
/* 1075 */         if (candidate != null)
/* 1076 */           synchronized (ThreadedAuthorityFactory.this.findPool) {
/* 1077 */             ThreadedAuthorityFactory.this.findPool.put(object, candidate);
/*      */           }  
/*      */       } 
/* 1081 */       return candidate;
/*      */     }
/*      */     
/*      */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/*      */       IdentifiedObject candidate;
/* 1090 */       synchronized (ThreadedAuthorityFactory.this.findPool) {
/* 1091 */         candidate = (IdentifiedObject)ThreadedAuthorityFactory.this.findPool.get(object);
/*      */       } 
/* 1093 */       if (candidate != null)
/* 1094 */         return getIdentifier(candidate); 
/* 1098 */       return this.finder.findIdentifier(object);
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void dispose() throws FactoryException {
/* 1107 */     if (this.backingStore != null) {
/* 1108 */       this.backingStore.dispose();
/* 1109 */       this.backingStore = null;
/*      */     } 
/* 1111 */     this.objectCache.clear();
/* 1112 */     super.dispose();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\ThreadedAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */