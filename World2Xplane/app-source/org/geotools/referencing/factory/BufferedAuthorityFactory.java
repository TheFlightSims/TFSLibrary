/*      */ package org.geotools.referencing.factory;
/*      */ 
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
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
/*      */ import org.opengis.referencing.NoSuchAuthorityCodeException;
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
/*      */ public class BufferedAuthorityFactory extends AbstractAuthorityFactory implements BufferedFactory {
/*      */   static final int DEFAULT_MAX = 20;
/*      */   
/*      */   volatile AbstractAuthorityFactory backingStore;
/*      */   
/*   98 */   private final LinkedHashMap<Object, Object> pool = new LinkedHashMap<Object, Object>(32, 0.75F, true);
/*      */   
/*      */   private final int maxStrongReferences;
/*      */   
/*  111 */   private final Map<IdentifiedObject, IdentifiedObject> findPool = new WeakHashMap<IdentifiedObject, IdentifiedObject>();
/*      */   
/*      */   protected BufferedAuthorityFactory(AbstractAuthorityFactory factory) {
/*  125 */     this(factory, 20);
/*      */   }
/*      */   
/*      */   protected BufferedAuthorityFactory(AbstractAuthorityFactory factory, int maxStrongReferences) {
/*  144 */     super(factory.getPriority());
/*  145 */     while (factory instanceof BufferedAuthorityFactory)
/*  146 */       factory = ((BufferedAuthorityFactory)factory).backingStore; 
/*  148 */     this.backingStore = factory;
/*  149 */     this.maxStrongReferences = maxStrongReferences;
/*  150 */     completeHints();
/*      */   }
/*      */   
/*      */   BufferedAuthorityFactory(int priority, int maxStrongReferences) {
/*  166 */     super(priority);
/*  167 */     this.maxStrongReferences = maxStrongReferences;
/*      */   }
/*      */   
/*      */   final void completeHints() {
/*  185 */     if (this.backingStore instanceof org.opengis.referencing.datum.DatumAuthorityFactory)
/*  186 */       this.hints.put(Hints.DATUM_AUTHORITY_FACTORY, this.backingStore); 
/*  188 */     if (this.backingStore instanceof org.opengis.referencing.cs.CSAuthorityFactory)
/*  189 */       this.hints.put(Hints.CS_AUTHORITY_FACTORY, this.backingStore); 
/*  191 */     if (this.backingStore instanceof org.opengis.referencing.crs.CRSAuthorityFactory)
/*  192 */       this.hints.put(Hints.CRS_AUTHORITY_FACTORY, this.backingStore); 
/*  194 */     if (this.backingStore instanceof org.opengis.referencing.operation.CoordinateOperationAuthorityFactory)
/*  195 */       this.hints.put(Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY, this.backingStore); 
/*      */   }
/*      */   
/*      */   Collection<? super AuthorityFactory> dependencies() {
/*      */     Object object;
/*      */     try {
/*  207 */       object = getBackingStore();
/*  208 */     } catch (FactoryException e) {
/*  209 */       object = e;
/*      */     } 
/*  211 */     return Collections.singleton(object);
/*      */   }
/*      */   
/*      */   AbstractAuthorityFactory getBackingStore() throws FactoryException {
/*  221 */     if (this.backingStore == null)
/*  222 */       throw new FactoryException(Errors.format(42)); 
/*  224 */     return this.backingStore;
/*      */   }
/*      */   
/*      */   boolean isAvailable() {
/*      */     try {
/*  235 */       return getBackingStore().isAvailable();
/*  236 */     } catch (FactoryNotFoundException exception) {
/*      */     
/*  244 */     } catch (FactoryException exception) {
/*  249 */       Citation citation = getAuthority();
/*  250 */       Collection<? extends InternationalString> titles = citation.getAlternateTitles();
/*  251 */       InternationalString title = citation.getTitle();
/*  252 */       if (titles != null)
/*  253 */         for (InternationalString candidate : titles) {
/*  259 */           if (candidate.length() > title.length())
/*  260 */             title = candidate; 
/*      */         }  
/*  264 */       LogRecord record = Loggings.format(Level.WARNING, 42, title);
/*  266 */       record.setSourceClassName(getClass().getName());
/*  267 */       record.setSourceMethodName("isAvailable");
/*  268 */       record.setThrown((Throwable)exception);
/*  269 */       record.setLoggerName(LOGGER.getName());
/*  270 */       LOGGER.log(record);
/*      */     } 
/*  272 */     return false;
/*      */   }
/*      */   
/*      */   boolean sameAuthorityCodes(AuthorityFactory factory) {
/*  283 */     AbstractAuthorityFactory backingStore = this.backingStore;
/*  284 */     if (backingStore != null && backingStore.sameAuthorityCodes(factory))
/*  285 */       return true; 
/*  287 */     return super.sameAuthorityCodes(factory);
/*      */   }
/*      */   
/*      */   public Citation getVendor() {
/*  295 */     return (this.backingStore != null) ? this.backingStore.getVendor() : super.getVendor();
/*      */   }
/*      */   
/*      */   public Citation getAuthority() {
/*  303 */     return (this.backingStore != null) ? this.backingStore.getAuthority() : null;
/*      */   }
/*      */   
/*      */   public String getBackingStoreDescription() throws FactoryException {
/*  314 */     return getBackingStore().getBackingStoreDescription();
/*      */   }
/*      */   
/*      */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  330 */     return getBackingStore().getAuthorityCodes(type);
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws NoSuchAuthorityCodeException, FactoryException {
/*  345 */     return getBackingStore().getDescriptionText(code);
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObject createObject(String code) throws FactoryException {
/*      */     IdentifiedObject object;
/*  358 */     String key = trimAuthority(code);
/*  359 */     Object cached = get(key);
/*  360 */     if (cached instanceof IdentifiedObject) {
/*  361 */       object = (IdentifiedObject)cached;
/*      */     } else {
/*  363 */       object = getBackingStore().createObject(code);
/*      */     } 
/*  365 */     put(key, object);
/*  366 */     return object;
/*      */   }
/*      */   
/*      */   public synchronized Datum createDatum(String code) throws FactoryException {
/*      */     Datum datum;
/*  379 */     String key = trimAuthority(code);
/*  380 */     Object cached = get(key);
/*  381 */     if (cached instanceof Datum) {
/*  382 */       datum = (Datum)cached;
/*      */     } else {
/*  384 */       datum = getBackingStore().createDatum(code);
/*      */     } 
/*  386 */     put(key, datum);
/*  387 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/*      */     EngineeringDatum datum;
/*  400 */     String key = trimAuthority(code);
/*  401 */     Object cached = get(key);
/*  402 */     if (cached instanceof EngineeringDatum) {
/*  403 */       datum = (EngineeringDatum)cached;
/*      */     } else {
/*  405 */       datum = getBackingStore().createEngineeringDatum(code);
/*      */     } 
/*  407 */     put(key, datum);
/*  408 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized ImageDatum createImageDatum(String code) throws FactoryException {
/*      */     ImageDatum datum;
/*  421 */     String key = trimAuthority(code);
/*  422 */     Object cached = get(key);
/*  423 */     if (cached instanceof ImageDatum) {
/*  424 */       datum = (ImageDatum)cached;
/*      */     } else {
/*  426 */       datum = getBackingStore().createImageDatum(code);
/*      */     } 
/*  428 */     put(key, datum);
/*  429 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized VerticalDatum createVerticalDatum(String code) throws FactoryException {
/*      */     VerticalDatum datum;
/*  442 */     String key = trimAuthority(code);
/*  443 */     Object cached = get(key);
/*  444 */     if (cached instanceof VerticalDatum) {
/*  445 */       datum = (VerticalDatum)cached;
/*      */     } else {
/*  447 */       datum = getBackingStore().createVerticalDatum(code);
/*      */     } 
/*  449 */     put(key, datum);
/*  450 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized TemporalDatum createTemporalDatum(String code) throws FactoryException {
/*      */     TemporalDatum datum;
/*  463 */     String key = trimAuthority(code);
/*  464 */     Object cached = get(key);
/*  465 */     if (cached instanceof TemporalDatum) {
/*  466 */       datum = (TemporalDatum)cached;
/*      */     } else {
/*  468 */       datum = getBackingStore().createTemporalDatum(code);
/*      */     } 
/*  470 */     put(key, datum);
/*  471 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/*      */     GeodeticDatum datum;
/*  484 */     String key = trimAuthority(code);
/*  485 */     Object cached = get(key);
/*  486 */     if (cached instanceof GeodeticDatum) {
/*  487 */       datum = (GeodeticDatum)cached;
/*      */     } else {
/*  489 */       datum = getBackingStore().createGeodeticDatum(code);
/*      */     } 
/*  491 */     put(key, datum);
/*  492 */     return datum;
/*      */   }
/*      */   
/*      */   public synchronized Ellipsoid createEllipsoid(String code) throws FactoryException {
/*      */     Ellipsoid ellipsoid;
/*  505 */     String key = trimAuthority(code);
/*  506 */     Object cached = get(key);
/*  507 */     if (cached instanceof Ellipsoid) {
/*  508 */       ellipsoid = (Ellipsoid)cached;
/*      */     } else {
/*  510 */       ellipsoid = getBackingStore().createEllipsoid(code);
/*      */     } 
/*  512 */     put(key, ellipsoid);
/*  513 */     return ellipsoid;
/*      */   }
/*      */   
/*      */   public synchronized PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/*      */     PrimeMeridian meridian;
/*  526 */     String key = trimAuthority(code);
/*  527 */     Object cached = get(key);
/*  528 */     if (cached instanceof PrimeMeridian) {
/*  529 */       meridian = (PrimeMeridian)cached;
/*      */     } else {
/*  531 */       meridian = getBackingStore().createPrimeMeridian(code);
/*      */     } 
/*  533 */     put(key, meridian);
/*  534 */     return meridian;
/*      */   }
/*      */   
/*      */   public synchronized Extent createExtent(String code) throws FactoryException {
/*      */     Extent extent;
/*  547 */     String key = trimAuthority(code);
/*  548 */     Object cached = get(key);
/*  549 */     if (cached instanceof Extent) {
/*  550 */       extent = (Extent)cached;
/*      */     } else {
/*  552 */       extent = getBackingStore().createExtent(code);
/*      */     } 
/*  554 */     put(key, extent);
/*  555 */     return extent;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/*      */     CoordinateSystem cs;
/*  568 */     String key = trimAuthority(code);
/*  569 */     Object cached = get(key);
/*  570 */     if (cached instanceof CoordinateSystem) {
/*  571 */       cs = (CoordinateSystem)cached;
/*      */     } else {
/*  573 */       cs = getBackingStore().createCoordinateSystem(code);
/*      */     } 
/*  575 */     put(key, cs);
/*  576 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CartesianCS createCartesianCS(String code) throws FactoryException {
/*      */     CartesianCS cs;
/*  589 */     String key = trimAuthority(code);
/*  590 */     Object cached = get(key);
/*  591 */     if (cached instanceof CartesianCS) {
/*  592 */       cs = (CartesianCS)cached;
/*      */     } else {
/*  594 */       cs = getBackingStore().createCartesianCS(code);
/*      */     } 
/*  596 */     put(key, cs);
/*  597 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized PolarCS createPolarCS(String code) throws FactoryException {
/*      */     PolarCS cs;
/*  610 */     String key = trimAuthority(code);
/*  611 */     Object cached = get(key);
/*  612 */     if (cached instanceof PolarCS) {
/*  613 */       cs = (PolarCS)cached;
/*      */     } else {
/*  615 */       cs = getBackingStore().createPolarCS(code);
/*      */     } 
/*  617 */     put(key, cs);
/*  618 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CylindricalCS createCylindricalCS(String code) throws FactoryException {
/*      */     CylindricalCS cs;
/*  631 */     String key = trimAuthority(code);
/*  632 */     Object cached = get(key);
/*  633 */     if (cached instanceof CylindricalCS) {
/*  634 */       cs = (CylindricalCS)cached;
/*      */     } else {
/*  636 */       cs = getBackingStore().createCylindricalCS(code);
/*      */     } 
/*  638 */     put(key, cs);
/*  639 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized SphericalCS createSphericalCS(String code) throws FactoryException {
/*      */     SphericalCS cs;
/*  652 */     String key = trimAuthority(code);
/*  653 */     Object cached = get(key);
/*  654 */     if (cached instanceof SphericalCS) {
/*  655 */       cs = (SphericalCS)cached;
/*      */     } else {
/*  657 */       cs = getBackingStore().createSphericalCS(code);
/*      */     } 
/*  659 */     put(key, cs);
/*  660 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/*      */     EllipsoidalCS cs;
/*  673 */     String key = trimAuthority(code);
/*  674 */     Object cached = get(key);
/*  675 */     if (cached instanceof EllipsoidalCS) {
/*  676 */       cs = (EllipsoidalCS)cached;
/*      */     } else {
/*  678 */       cs = getBackingStore().createEllipsoidalCS(code);
/*      */     } 
/*  680 */     put(key, cs);
/*  681 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized VerticalCS createVerticalCS(String code) throws FactoryException {
/*      */     VerticalCS cs;
/*  694 */     String key = trimAuthority(code);
/*  695 */     Object cached = get(key);
/*  696 */     if (cached instanceof VerticalCS) {
/*  697 */       cs = (VerticalCS)cached;
/*      */     } else {
/*  699 */       cs = getBackingStore().createVerticalCS(code);
/*      */     } 
/*  701 */     put(key, cs);
/*  702 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized TimeCS createTimeCS(String code) throws FactoryException {
/*      */     TimeCS cs;
/*  715 */     String key = trimAuthority(code);
/*  716 */     Object cached = get(key);
/*  717 */     if (cached instanceof TimeCS) {
/*  718 */       cs = (TimeCS)cached;
/*      */     } else {
/*  720 */       cs = getBackingStore().createTimeCS(code);
/*      */     } 
/*  722 */     put(key, cs);
/*  723 */     return cs;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/*      */     CoordinateSystemAxis axis;
/*  736 */     String key = trimAuthority(code);
/*  737 */     Object cached = get(key);
/*  738 */     if (cached instanceof CoordinateSystemAxis) {
/*  739 */       axis = (CoordinateSystemAxis)cached;
/*      */     } else {
/*  741 */       axis = getBackingStore().createCoordinateSystemAxis(code);
/*      */     } 
/*  743 */     put(key, axis);
/*  744 */     return axis;
/*      */   }
/*      */   
/*      */   public synchronized Unit<?> createUnit(String code) throws FactoryException {
/*      */     Unit<?> unit;
/*  757 */     String key = trimAuthority(code);
/*  758 */     Object cached = get(key);
/*  759 */     if (cached instanceof Unit) {
/*  760 */       unit = (Unit)cached;
/*      */     } else {
/*  762 */       unit = getBackingStore().createUnit(code);
/*      */     } 
/*  764 */     put(key, unit);
/*  765 */     return unit;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/*      */     CoordinateReferenceSystem crs;
/*  778 */     String key = trimAuthority(code);
/*  779 */     Object cached = get(key);
/*  780 */     if (cached instanceof CoordinateReferenceSystem) {
/*  781 */       crs = (CoordinateReferenceSystem)cached;
/*      */     } else {
/*  783 */       crs = getBackingStore().createCoordinateReferenceSystem(code);
/*      */     } 
/*  785 */     put(key, crs);
/*  786 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized CompoundCRS createCompoundCRS(String code) throws FactoryException {
/*      */     CompoundCRS crs;
/*  799 */     String key = trimAuthority(code);
/*  800 */     Object cached = get(key);
/*  801 */     if (cached instanceof CompoundCRS) {
/*  802 */       crs = (CompoundCRS)cached;
/*      */     } else {
/*  804 */       crs = getBackingStore().createCompoundCRS(code);
/*      */     } 
/*  806 */     put(key, crs);
/*  807 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized DerivedCRS createDerivedCRS(String code) throws FactoryException {
/*      */     DerivedCRS crs;
/*  820 */     String key = trimAuthority(code);
/*  821 */     Object cached = get(key);
/*  822 */     if (cached instanceof DerivedCRS) {
/*  823 */       crs = (DerivedCRS)cached;
/*      */     } else {
/*  825 */       crs = getBackingStore().createDerivedCRS(code);
/*      */     } 
/*  827 */     put(key, crs);
/*  828 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/*      */     EngineeringCRS crs;
/*  841 */     String key = trimAuthority(code);
/*  842 */     Object cached = get(key);
/*  843 */     if (cached instanceof EngineeringCRS) {
/*  844 */       crs = (EngineeringCRS)cached;
/*      */     } else {
/*  846 */       crs = getBackingStore().createEngineeringCRS(code);
/*      */     } 
/*  848 */     put(key, crs);
/*  849 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized GeographicCRS createGeographicCRS(String code) throws FactoryException {
/*      */     GeographicCRS crs;
/*  862 */     String key = trimAuthority(code);
/*  863 */     Object cached = get(key);
/*  864 */     if (cached instanceof GeographicCRS) {
/*  865 */       crs = (GeographicCRS)cached;
/*      */     } else {
/*  867 */       crs = getBackingStore().createGeographicCRS(code);
/*      */     } 
/*  869 */     put(key, crs);
/*  870 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/*      */     GeocentricCRS crs;
/*  883 */     String key = trimAuthority(code);
/*  884 */     Object cached = get(key);
/*  885 */     if (cached instanceof GeocentricCRS) {
/*  886 */       crs = (GeocentricCRS)cached;
/*      */     } else {
/*  888 */       crs = getBackingStore().createGeocentricCRS(code);
/*      */     } 
/*  890 */     put(key, crs);
/*  891 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ImageCRS createImageCRS(String code) throws FactoryException {
/*      */     ImageCRS crs;
/*  904 */     String key = trimAuthority(code);
/*  905 */     Object cached = get(key);
/*  906 */     if (cached instanceof ImageCRS) {
/*  907 */       crs = (ImageCRS)cached;
/*      */     } else {
/*  909 */       crs = getBackingStore().createImageCRS(code);
/*      */     } 
/*  911 */     put(key, crs);
/*  912 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/*      */     ProjectedCRS crs;
/*  925 */     String key = trimAuthority(code);
/*  926 */     Object cached = get(key);
/*  927 */     if (cached instanceof ProjectedCRS) {
/*  928 */       crs = (ProjectedCRS)cached;
/*      */     } else {
/*  930 */       crs = getBackingStore().createProjectedCRS(code);
/*      */     } 
/*  932 */     put(key, crs);
/*  933 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized TemporalCRS createTemporalCRS(String code) throws FactoryException {
/*      */     TemporalCRS crs;
/*  946 */     String key = trimAuthority(code);
/*  947 */     Object cached = get(key);
/*  948 */     if (cached instanceof TemporalCRS) {
/*  949 */       crs = (TemporalCRS)cached;
/*      */     } else {
/*  951 */       crs = getBackingStore().createTemporalCRS(code);
/*      */     } 
/*  953 */     put(key, crs);
/*  954 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized VerticalCRS createVerticalCRS(String code) throws FactoryException {
/*      */     VerticalCRS crs;
/*  967 */     String key = trimAuthority(code);
/*  968 */     Object cached = get(key);
/*  969 */     if (cached instanceof VerticalCRS) {
/*  970 */       crs = (VerticalCRS)cached;
/*      */     } else {
/*  972 */       crs = getBackingStore().createVerticalCRS(code);
/*      */     } 
/*  974 */     put(key, crs);
/*  975 */     return crs;
/*      */   }
/*      */   
/*      */   public synchronized ParameterDescriptor createParameterDescriptor(String code) throws FactoryException {
/*      */     ParameterDescriptor parameter;
/*  990 */     String key = trimAuthority(code);
/*  991 */     Object cached = get(key);
/*  992 */     if (cached instanceof ParameterDescriptor) {
/*  993 */       parameter = (ParameterDescriptor)cached;
/*      */     } else {
/*  995 */       parameter = getBackingStore().createParameterDescriptor(code);
/*      */     } 
/*  997 */     put(key, parameter);
/*  998 */     return parameter;
/*      */   }
/*      */   
/*      */   public synchronized OperationMethod createOperationMethod(String code) throws FactoryException {
/*      */     OperationMethod method;
/* 1013 */     String key = trimAuthority(code);
/* 1014 */     Object cached = get(key);
/* 1015 */     if (cached instanceof OperationMethod) {
/* 1016 */       method = (OperationMethod)cached;
/*      */     } else {
/* 1018 */       method = getBackingStore().createOperationMethod(code);
/*      */     } 
/* 1020 */     put(key, method);
/* 1021 */     return method;
/*      */   }
/*      */   
/*      */   public synchronized CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/*      */     CoordinateOperation operation;
/* 1036 */     String key = trimAuthority(code);
/* 1037 */     Object cached = get(key);
/* 1038 */     if (cached instanceof CoordinateOperation) {
/* 1039 */       operation = (CoordinateOperation)cached;
/*      */     } else {
/* 1041 */       operation = getBackingStore().createCoordinateOperation(code);
/*      */     } 
/* 1043 */     put(key, operation);
/* 1044 */     return operation;
/*      */   }
/*      */   
/*      */   public synchronized Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws FactoryException {
/*      */     Set<CoordinateOperation> operations;
/* 1060 */     CodePair key = new CodePair(trimAuthority(sourceCRS), trimAuthority(targetCRS));
/* 1061 */     Object cached = get(key);
/* 1062 */     if (cached instanceof Set) {
/* 1063 */       operations = (Set<CoordinateOperation>)cached;
/*      */     } else {
/* 1065 */       operations = Collections.unmodifiableSet(getBackingStore().createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS));
/*      */     } 
/* 1068 */     put(key, operations);
/* 1069 */     return operations;
/*      */   }
/*      */   
/*      */   private static final class CodePair {
/*      */     private final String source;
/*      */     
/*      */     private final String target;
/*      */     
/*      */     public CodePair(String source, String target) {
/* 1080 */       this.source = source;
/* 1081 */       this.target = target;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1086 */       int code = 0;
/* 1087 */       if (this.source != null)
/* 1087 */         code = this.source.hashCode(); 
/* 1088 */       if (this.target != null)
/* 1088 */         code += this.target.hashCode() * 37; 
/* 1089 */       return code;
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/* 1094 */       if (other instanceof CodePair) {
/* 1095 */         CodePair that = (CodePair)other;
/* 1096 */         return (Utilities.equals(this.source, that.source) && Utilities.equals(this.target, that.target));
/*      */       } 
/* 1099 */       return false;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1104 */       return this.source + " ⇨ " + this.target;
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 1121 */     return new Finder(getBackingStore().getIdentifiedObjectFinder(type));
/*      */   }
/*      */   
/*      */   private final class Finder extends IdentifiedObjectFinder.Adapter {
/*      */     Finder(IdentifiedObjectFinder finder) {}
/*      */     
/*      */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/*      */       IdentifiedObject candidate;
/* 1158 */       synchronized (BufferedAuthorityFactory.this.findPool) {
/* 1159 */         candidate = (IdentifiedObject)BufferedAuthorityFactory.this.findPool.get(object);
/*      */       } 
/* 1161 */       if (candidate == null) {
/* 1164 */         candidate = this.finder.find(object);
/* 1165 */         if (candidate != null)
/* 1166 */           synchronized (BufferedAuthorityFactory.this.findPool) {
/* 1167 */             BufferedAuthorityFactory.this.findPool.put(object, candidate);
/*      */           }  
/*      */       } 
/* 1171 */       return candidate;
/*      */     }
/*      */     
/*      */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/*      */       IdentifiedObject candidate;
/* 1180 */       synchronized (BufferedAuthorityFactory.this.findPool) {
/* 1181 */         candidate = (IdentifiedObject)BufferedAuthorityFactory.this.findPool.get(object);
/*      */       } 
/* 1183 */       if (candidate != null)
/* 1184 */         return getIdentifier(candidate); 
/* 1188 */       return this.finder.findIdentifier(object);
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void dispose() throws FactoryException {
/* 1199 */     if (this.backingStore != null) {
/* 1200 */       this.backingStore.dispose();
/* 1201 */       this.backingStore = null;
/*      */     } 
/* 1203 */     this.pool.clear();
/* 1204 */     this.findPool.clear();
/* 1205 */     super.dispose();
/*      */   }
/*      */   
/*      */   private Object get(Object key) {
/* 1215 */     assert Thread.holdsLock(this);
/* 1216 */     Object object = this.pool.get(key);
/* 1217 */     if (object instanceof Reference)
/* 1218 */       object = ((Reference)object).get(); 
/* 1220 */     return object;
/*      */   }
/*      */   
/*      */   private void put(Object key, Object object) {
/* 1231 */     assert Thread.holdsLock(this);
/* 1232 */     this.pool.put(key, object);
/* 1233 */     int toReplace = this.pool.size() - this.maxStrongReferences;
/* 1234 */     if (toReplace > 0)
/* 1235 */       for (Iterator<Map.Entry<Object, Object>> it = this.pool.entrySet().iterator(); it.hasNext(); ) {
/* 1236 */         Map.Entry<Object, Object> entry = it.next();
/* 1237 */         Object value = entry.getValue();
/* 1238 */         if (value instanceof Reference) {
/* 1239 */           if (((Reference)value).get() == null)
/* 1240 */             it.remove(); 
/*      */           continue;
/*      */         } 
/* 1244 */         entry.setValue(new WeakReference(value));
/* 1245 */         if (--toReplace == 0)
/*      */           break; 
/*      */       }  
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\BufferedAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */