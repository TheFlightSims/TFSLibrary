/*      */ package org.geotools.referencing.factory;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.factory.AbstractFactory;
/*      */ import org.geotools.factory.Factory;
/*      */ import org.geotools.factory.FactoryRegistryException;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.factory.OptionalFactory;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.referencing.ReferencingFactoryFinder;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.metadata.extent.Extent;
/*      */ import org.opengis.parameter.ParameterDescriptor;
/*      */ import org.opengis.referencing.AuthorityFactory;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*      */ import org.opengis.referencing.crs.CRSAuthorityFactory;
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
/*      */ import org.opengis.referencing.cs.CSAuthorityFactory;
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
/*      */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.EngineeringDatum;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.ImageDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.TemporalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*      */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class AuthorityFactoryAdapter extends AbstractAuthorityFactory implements OptionalFactory {
/*   76 */   private static final Hints.Key[] TYPES = new Hints.Key[] { (Hints.Key)Hints.CRS_AUTHORITY_FACTORY, (Hints.Key)Hints.CS_AUTHORITY_FACTORY, (Hints.Key)Hints.DATUM_AUTHORITY_FACTORY, (Hints.Key)Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY };
/*      */   
/*      */   final DatumAuthorityFactory datumFactory;
/*      */   
/*      */   final CSAuthorityFactory csFactory;
/*      */   
/*      */   final CRSAuthorityFactory crsFactory;
/*      */   
/*      */   final CoordinateOperationAuthorityFactory operationFactory;
/*      */   
/*      */   private transient ReferencingFactoryContainer factories;
/*      */   
/*      */   AuthorityFactoryAdapter(int priority) {
/*  126 */     super(priority);
/*  127 */     this.datumFactory = null;
/*  128 */     this.csFactory = null;
/*  129 */     this.crsFactory = null;
/*  130 */     this.operationFactory = null;
/*      */   }
/*      */   
/*      */   protected AuthorityFactoryAdapter(AuthorityFactory factory) {
/*  142 */     this(factory, (AuthorityFactory)null);
/*      */   }
/*      */   
/*      */   AuthorityFactoryAdapter(AuthorityFactory factory, AuthorityFactory fallback) {
/*  149 */     this((factory instanceof CRSAuthorityFactory) ? (CRSAuthorityFactory)factory : ((fallback instanceof CRSAuthorityFactory) ? (CRSAuthorityFactory)fallback : null), (factory instanceof CSAuthorityFactory) ? (CSAuthorityFactory)factory : ((fallback instanceof CSAuthorityFactory) ? (CSAuthorityFactory)fallback : null), (factory instanceof DatumAuthorityFactory) ? (DatumAuthorityFactory)factory : ((fallback instanceof DatumAuthorityFactory) ? (DatumAuthorityFactory)fallback : null), (factory instanceof CoordinateOperationAuthorityFactory) ? (CoordinateOperationAuthorityFactory)factory : ((fallback instanceof CoordinateOperationAuthorityFactory) ? (CoordinateOperationAuthorityFactory)fallback : null));
/*      */   }
/*      */   
/*      */   protected AuthorityFactoryAdapter(CRSAuthorityFactory crsFactory, CSAuthorityFactory csFactory, DatumAuthorityFactory datumFactory, CoordinateOperationAuthorityFactory opFactory) {
/*  180 */     super(Math.max(getPriority((AuthorityFactory)datumFactory), Math.max(getPriority((AuthorityFactory)csFactory), Math.max(getPriority((AuthorityFactory)crsFactory), getPriority((AuthorityFactory)opFactory)))));
/*  185 */     if (this instanceof CRSAuthorityFactory)
/*  186 */       ensureNonNull("crsFactory", crsFactory); 
/*  188 */     if (this instanceof CSAuthorityFactory)
/*  189 */       ensureNonNull("csFactory", csFactory); 
/*  191 */     if (this instanceof DatumAuthorityFactory)
/*  192 */       ensureNonNull("datumFactory", datumFactory); 
/*  194 */     if (this instanceof CoordinateOperationAuthorityFactory)
/*  195 */       ensureNonNull("opFactory", opFactory); 
/*  197 */     store((Hints.Key)Hints.DATUM_AUTHORITY_FACTORY, (AuthorityFactory)(this.datumFactory = datumFactory));
/*  198 */     store((Hints.Key)Hints.CS_AUTHORITY_FACTORY, (AuthorityFactory)(this.csFactory = csFactory));
/*  199 */     store((Hints.Key)Hints.CRS_AUTHORITY_FACTORY, (AuthorityFactory)(this.crsFactory = crsFactory));
/*  200 */     store((Hints.Key)Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY, (AuthorityFactory)(this.operationFactory = opFactory));
/*      */   }
/*      */   
/*      */   private static int getPriority(AuthorityFactory factory) {
/*  207 */     return (factory instanceof AbstractFactory) ? ((AbstractFactory)factory).getPriority() : 50;
/*      */   }
/*      */   
/*      */   private void store(Hints.Key key, AuthorityFactory factory) {
/*  215 */     if (factory != null && 
/*  216 */       this.hints.put(key, factory) != null)
/*  218 */       throw new AssertionError(key); 
/*      */   }
/*      */   
/*      */   protected AuthorityFactoryAdapter(String authority, Hints userHints) throws FactoryRegistryException {
/*  238 */     this(ReferencingFactoryFinder.getCRSAuthorityFactory(authority, trim(userHints, (Hints.Key)Hints.CRS_AUTHORITY_FACTORY)), ReferencingFactoryFinder.getCSAuthorityFactory(authority, trim(userHints, (Hints.Key)Hints.CS_AUTHORITY_FACTORY)), ReferencingFactoryFinder.getDatumAuthorityFactory(authority, trim(userHints, (Hints.Key)Hints.DATUM_AUTHORITY_FACTORY)), ReferencingFactoryFinder.getCoordinateOperationAuthorityFactory(authority, trim(userHints, (Hints.Key)Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY)));
/*      */   }
/*      */   
/*      */   private static Hints trim(Hints userHints, Hints.Key keep) {
/*  274 */     Hints reduced = userHints;
/*  275 */     if (userHints != null)
/*  276 */       for (int i = 0; i < TYPES.length; i++) {
/*  277 */         Hints.Key key = TYPES[i];
/*  278 */         if (!keep.equals(key)) {
/*  279 */           if (reduced == userHints) {
/*  280 */             if (!userHints.containsKey(key))
/*      */               continue; 
/*  284 */             reduced = new Hints((RenderingHints)userHints);
/*      */           } 
/*  286 */           reduced.remove(key);
/*      */         } 
/*      */         continue;
/*      */       }  
/*  290 */     return reduced;
/*      */   }
/*      */   
/*      */   private Hints hints() {
/*  297 */     Hints extended = new Hints(this.hints);
/*  298 */     addAll((AuthorityFactory)this.operationFactory, extended);
/*  299 */     addAll((AuthorityFactory)this.datumFactory, extended);
/*  300 */     addAll((AuthorityFactory)this.csFactory, extended);
/*  301 */     addAll((AuthorityFactory)this.crsFactory, extended);
/*  302 */     extended.putAll(this.hints);
/*  303 */     return extended;
/*      */   }
/*      */   
/*      */   private static void addAll(AuthorityFactory factory, Hints hints) {
/*  310 */     if (factory instanceof Factory)
/*  311 */       hints.putAll(((Factory)factory).getImplementationHints()); 
/*      */   }
/*      */   
/*      */   Collection<? super AuthorityFactory> dependencies() {
/*      */     Object object;
/*  321 */     List<Object> dep = new ArrayList(4);
/*      */     try {
/*  324 */       object = getAuthorityFactory((String)null);
/*  325 */     } catch (FactoryException e) {
/*  326 */       object = e;
/*      */     } 
/*  328 */     dep.add(object);
/*  329 */     return dep;
/*      */   }
/*      */   
/*      */   boolean sameAuthorityCodes(AuthorityFactory factory) {
/*  341 */     if (!isCodeMethodOverriden())
/*  358 */       if (sameAuthorityCodes((AuthorityFactory)this.crsFactory, factory) && sameAuthorityCodes((AuthorityFactory)this.csFactory, factory) && sameAuthorityCodes((AuthorityFactory)this.datumFactory, factory) && sameAuthorityCodes((AuthorityFactory)this.operationFactory, factory))
/*  363 */         return true;  
/*  366 */     return super.sameAuthorityCodes(factory);
/*      */   }
/*      */   
/*      */   static boolean sameAuthorityCodes(AuthorityFactory backingStore, AuthorityFactory factory) {
/*  378 */     if (backingStore instanceof AbstractAuthorityFactory && (
/*  379 */       (AbstractAuthorityFactory)backingStore).sameAuthorityCodes(factory))
/*  380 */       return true; 
/*  383 */     return (factory == backingStore || backingStore == null);
/*      */   }
/*      */   
/*      */   public boolean isAvailable() {
/*  395 */     return (isAvailable((AuthorityFactory)this.crsFactory) && isAvailable((AuthorityFactory)this.csFactory) && isAvailable((AuthorityFactory)this.datumFactory) && isAvailable((AuthorityFactory)this.operationFactory));
/*      */   }
/*      */   
/*      */   private static boolean isAvailable(AuthorityFactory factory) {
/*  405 */     return (!(factory instanceof OptionalFactory) || ((OptionalFactory)factory).isAvailable());
/*      */   }
/*      */   
/*      */   Unit<?> replace(Unit<?> units) throws FactoryException {
/*  413 */     return units;
/*      */   }
/*      */   
/*      */   CoordinateSystemAxis replace(CoordinateSystemAxis axis) throws FactoryException {
/*  421 */     return axis;
/*      */   }
/*      */   
/*      */   CoordinateSystem replace(CoordinateSystem cs) throws FactoryException {
/*  429 */     return cs;
/*      */   }
/*      */   
/*      */   Datum replace(Datum datum) throws FactoryException {
/*  437 */     return datum;
/*      */   }
/*      */   
/*      */   CoordinateReferenceSystem replace(CoordinateReferenceSystem crs) throws FactoryException {
/*  445 */     return crs;
/*      */   }
/*      */   
/*      */   CoordinateOperation replace(CoordinateOperation operation) throws FactoryException {
/*  453 */     return operation;
/*      */   }
/*      */   
/*      */   private IdentifiedObject replaceObject(IdentifiedObject object) throws FactoryException {
/*  460 */     if (object instanceof CoordinateReferenceSystem)
/*  461 */       return (IdentifiedObject)replace((CoordinateReferenceSystem)object); 
/*  463 */     if (object instanceof CoordinateSystem)
/*  464 */       return (IdentifiedObject)replace((CoordinateSystem)object); 
/*  466 */     if (object instanceof CoordinateSystemAxis)
/*  467 */       return (IdentifiedObject)replace((CoordinateSystemAxis)object); 
/*  469 */     if (object instanceof Datum)
/*  470 */       return (IdentifiedObject)replace((Datum)object); 
/*  472 */     if (object instanceof CoordinateOperation)
/*  473 */       return (IdentifiedObject)replace((CoordinateOperation)object); 
/*  475 */     return object;
/*      */   }
/*      */   
/*      */   private AbstractAuthorityFactory getGeotoolsFactory(String caller, String code) throws FactoryException {
/*  486 */     AuthorityFactory candidate = getAuthorityFactory(code);
/*  487 */     if (candidate instanceof AbstractAuthorityFactory)
/*  488 */       return (AbstractAuthorityFactory)candidate; 
/*  490 */     if (caller == null)
/*  491 */       return null; 
/*  493 */     throw new FactoryException(Errors.format(52, caller));
/*      */   }
/*      */   
/*      */   public String getBackingStoreDescription() throws FactoryException {
/*  504 */     AbstractAuthorityFactory factory = getGeotoolsFactory((String)null, (String)null);
/*  505 */     return (factory != null) ? factory.getBackingStoreDescription() : null;
/*      */   }
/*      */   
/*      */   public Citation getVendor() {
/*  513 */     return getAuthorityFactory().getVendor();
/*      */   }
/*      */   
/*      */   public Citation getAuthority() {
/*  521 */     return getAuthorityFactory().getAuthority();
/*      */   }
/*      */   
/*      */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  532 */     return getAuthorityFactory((String)null).getAuthorityCodes(type);
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*  539 */     return getAuthorityFactory(code).getDescriptionText(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public IdentifiedObject createObject(String code) throws FactoryException {
/*  554 */     return replaceObject(getAuthorityFactory(code).createObject(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public Datum createDatum(String code) throws FactoryException {
/*  568 */     return replace(getDatumAuthorityFactory(code).createDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/*  580 */     return (EngineeringDatum)replace((Datum)getDatumAuthorityFactory(code).createEngineeringDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public ImageDatum createImageDatum(String code) throws FactoryException {
/*  593 */     return (ImageDatum)replace((Datum)getDatumAuthorityFactory(code).createImageDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public VerticalDatum createVerticalDatum(String code) throws FactoryException {
/*  606 */     return (VerticalDatum)replace((Datum)getDatumAuthorityFactory(code).createVerticalDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public TemporalDatum createTemporalDatum(String code) throws FactoryException {
/*  619 */     return (TemporalDatum)replace((Datum)getDatumAuthorityFactory(code).createTemporalDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/*  635 */     return (GeodeticDatum)replace((Datum)getDatumAuthorityFactory(code).createGeodeticDatum(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public Ellipsoid createEllipsoid(String code) throws FactoryException {
/*  648 */     return getDatumAuthorityFactory(code).createEllipsoid(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/*  660 */     return getDatumAuthorityFactory(code).createPrimeMeridian(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public Extent createExtent(String code) throws FactoryException {
/*  670 */     return getGeotoolsFactory("createExtent", code).createExtent(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/*  680 */     return replace(getCSAuthorityFactory(code).createCoordinateSystem(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public CartesianCS createCartesianCS(String code) throws FactoryException {
/*  691 */     return (CartesianCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createCartesianCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public PolarCS createPolarCS(String code) throws FactoryException {
/*  702 */     return (PolarCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createPolarCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public CylindricalCS createCylindricalCS(String code) throws FactoryException {
/*  713 */     return (CylindricalCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createCylindricalCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public SphericalCS createSphericalCS(String code) throws FactoryException {
/*  724 */     return (SphericalCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createSphericalCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/*  735 */     return (EllipsoidalCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createEllipsoidalCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public VerticalCS createVerticalCS(String code) throws FactoryException {
/*  746 */     return (VerticalCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createVerticalCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public TimeCS createTimeCS(String code) throws FactoryException {
/*  757 */     return (TimeCS)replace((CoordinateSystem)getCSAuthorityFactory(code).createTimeCS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/*  770 */     return replace(getCSAuthorityFactory(code).createCoordinateSystemAxis(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public Unit<?> createUnit(String code) throws FactoryException {
/*  781 */     return replace(getCSAuthorityFactory(code).createUnit(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/*  801 */     return replace(getCRSAuthorityFactory(code).createCoordinateReferenceSystem(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public CompoundCRS createCompoundCRS(String code) throws FactoryException {
/*  812 */     return (CompoundCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createCompoundCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/*  823 */     return (DerivedCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createDerivedCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/*  834 */     return (EngineeringCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createEngineeringCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/*  845 */     return (GeographicCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createGeographicCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/*  856 */     return (GeocentricCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createGeocentricCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public ImageCRS createImageCRS(String code) throws FactoryException {
/*  867 */     return (ImageCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createImageCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/*  878 */     return (ProjectedCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createProjectedCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/*  889 */     return (TemporalCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createTemporalCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/*  900 */     return (VerticalCRS)replace((CoordinateReferenceSystem)getCRSAuthorityFactory(code).createVerticalCRS(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public ParameterDescriptor createParameterDescriptor(String code) throws FactoryException {
/*  911 */     return getGeotoolsFactory("createParameterDescriptor", code).createParameterDescriptor(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public OperationMethod createOperationMethod(String code) throws FactoryException {
/*  922 */     return getGeotoolsFactory("createOperationMethod", code).createOperationMethod(toBackingFactoryCode(code));
/*      */   }
/*      */   
/*      */   public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/*  933 */     return replace(getCoordinateOperationAuthorityFactory(code).createCoordinateOperation(toBackingFactoryCode(code)));
/*      */   }
/*      */   
/*      */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws FactoryException {
/*  947 */     CoordinateOperationAuthorityFactory factory = getCoordinateOperationAuthorityFactory(sourceCRS);
/*  948 */     CoordinateOperationAuthorityFactory check = getCoordinateOperationAuthorityFactory(targetCRS);
/*  949 */     if (factory != check) {
/*  955 */       LogRecord record = Loggings.format(Level.WARNING, 29, sourceCRS, targetCRS);
/*  957 */       record.setSourceMethodName("createFromCoordinateReferenceSystemCodes");
/*  958 */       record.setSourceClassName(AuthorityFactoryAdapter.class.getName());
/*  959 */       record.setLoggerName(LOGGER.getName());
/*  960 */       LOGGER.log(record);
/*  961 */       return Collections.emptySet();
/*      */     } 
/*  963 */     return factory.createFromCoordinateReferenceSystemCodes(toBackingFactoryCode(sourceCRS), toBackingFactoryCode(targetCRS));
/*      */   }
/*      */   
/*      */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  979 */     return new Finder(type);
/*      */   }
/*      */   
/*      */   class Finder extends IdentifiedObjectFinder.Adapter {
/*      */     protected Finder(Class<? extends IdentifiedObject> type) throws FactoryException {}
/*      */     
/*      */     protected IdentifiedObject deriveEquivalent(IdentifiedObject candidate, IdentifiedObject model) throws FactoryException {
/* 1007 */       IdentifiedObject modified = AuthorityFactoryAdapter.this.replaceObject(candidate);
/* 1008 */       if (modified != candidate && 
/* 1009 */         CRS.equalsIgnoreMetadata(modified, model))
/* 1010 */         return modified; 
/* 1013 */       return super.deriveEquivalent(candidate, model);
/*      */     }
/*      */   }
/*      */   
/*      */   private FactoryException missingFactory(Class category, String code) {
/* 1023 */     return (FactoryException)new NoSuchAuthorityCodeException(Errors.format(49, category), Citations.getIdentifier(getAuthority()), trimAuthority(code));
/*      */   }
/*      */   
/*      */   private AuthorityFactory getAuthorityFactory() {
/*      */     try {
/* 1034 */       return getAuthorityFactory((String)null);
/* 1035 */     } catch (FactoryException cause) {
/* 1036 */       throw new IllegalStateException(Errors.format(170), cause);
/*      */     } 
/*      */   }
/*      */   
/*      */   <T extends AuthorityFactory> T getAuthorityFactory(Class<T> type, String code) throws FactoryException {
/*      */     CoordinateOperationAuthorityFactory coordinateOperationAuthorityFactory;
/* 1061 */     if (CRSAuthorityFactory.class.equals(type)) {
/* 1062 */       CRSAuthorityFactory cRSAuthorityFactory = getCRSAuthorityFactory(code);
/* 1063 */     } else if (CSAuthorityFactory.class.equals(type)) {
/* 1064 */       CSAuthorityFactory cSAuthorityFactory = getCSAuthorityFactory(code);
/* 1065 */     } else if (DatumAuthorityFactory.class.equals(type)) {
/* 1066 */       DatumAuthorityFactory datumAuthorityFactory = getDatumAuthorityFactory(code);
/* 1067 */     } else if (CoordinateOperationAuthorityFactory.class.equals(type)) {
/* 1068 */       coordinateOperationAuthorityFactory = getCoordinateOperationAuthorityFactory(code);
/*      */     } else {
/* 1070 */       throw new IllegalArgumentException(Errors.format(58, "type", type));
/*      */     } 
/* 1073 */     return type.cast(coordinateOperationAuthorityFactory);
/*      */   }
/*      */   
/*      */   protected AuthorityFactory getAuthorityFactory(String code) throws FactoryException {
/* 1094 */     if (this.crsFactory != null)
/* 1094 */       return (AuthorityFactory)this.crsFactory; 
/* 1095 */     if (this.csFactory != null)
/* 1095 */       return (AuthorityFactory)this.csFactory; 
/* 1096 */     if (this.datumFactory != null)
/* 1096 */       return (AuthorityFactory)this.datumFactory; 
/* 1097 */     if (this.operationFactory != null)
/* 1097 */       return (AuthorityFactory)this.operationFactory; 
/* 1098 */     throw missingFactory(AuthorityFactory.class, code);
/*      */   }
/*      */   
/*      */   protected DatumAuthorityFactory getDatumAuthorityFactory(String code) throws FactoryException {
/* 1117 */     if (this.datumFactory == null)
/* 1118 */       throw missingFactory(DatumAuthorityFactory.class, code); 
/* 1120 */     return this.datumFactory;
/*      */   }
/*      */   
/*      */   protected CSAuthorityFactory getCSAuthorityFactory(String code) throws FactoryException {
/* 1139 */     if (this.csFactory == null)
/* 1140 */       throw missingFactory(CSAuthorityFactory.class, code); 
/* 1142 */     return this.csFactory;
/*      */   }
/*      */   
/*      */   protected CRSAuthorityFactory getCRSAuthorityFactory(String code) throws FactoryException {
/* 1162 */     if (this.crsFactory == null)
/* 1163 */       throw missingFactory(CRSAuthorityFactory.class, code); 
/* 1165 */     return this.crsFactory;
/*      */   }
/*      */   
/*      */   protected CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory(String code) throws FactoryException {
/* 1185 */     if (this.operationFactory == null)
/* 1186 */       throw missingFactory(CoordinateOperationAuthorityFactory.class, code); 
/* 1188 */     return this.operationFactory;
/*      */   }
/*      */   
/*      */   final CoordinateOperationFactory getCoordinateOperationFactory() throws FactoryException {
/* 1197 */     if (this.operationFactory instanceof Factory) {
/* 1198 */       Factory factory = (Factory)this.operationFactory;
/* 1199 */       Map hints = factory.getImplementationHints();
/* 1200 */       Object candidate = hints.get(Hints.COORDINATE_OPERATION_FACTORY);
/* 1201 */       if (candidate instanceof CoordinateOperationFactory)
/* 1202 */         return (CoordinateOperationFactory)candidate; 
/*      */     } 
/* 1205 */     return ReferencingFactoryFinder.getCoordinateOperationFactory(hints());
/*      */   }
/*      */   
/*      */   final ReferencingFactoryContainer getFactoryContainer(boolean crs) {
/*      */     CSAuthorityFactory cSAuthorityFactory;
/* 1217 */     if (crs) {
/* 1218 */       CRSAuthorityFactory cRSAuthorityFactory = this.crsFactory;
/*      */     } else {
/* 1220 */       cSAuthorityFactory = this.csFactory;
/*      */     } 
/* 1222 */     if (cSAuthorityFactory instanceof DirectAuthorityFactory)
/* 1223 */       return ((DirectAuthorityFactory)cSAuthorityFactory).factories; 
/* 1226 */     if (this.factories == null)
/* 1227 */       this.factories = ReferencingFactoryContainer.instance(hints()); 
/* 1229 */     return this.factories;
/*      */   }
/*      */   
/*      */   protected String toBackingFactoryCode(String code) throws FactoryException {
/* 1246 */     return code;
/*      */   }
/*      */   
/*      */   final boolean isCodeMethodOverriden() {
/* 1253 */     Class<?>[] arguments = new Class[] { String.class };
/* 1254 */     for (Class<?> type = getClass(); !AuthorityFactoryAdapter.class.equals(type); ) {
/*      */       try {
/* 1256 */         type.getDeclaredMethod("toBackingFactoryCode", arguments);
/* 1257 */       } catch (NoSuchMethodException e) {
/*      */       
/* 1261 */       } catch (SecurityException e) {}
/* 1265 */       return true;
/*      */     } 
/* 1267 */     return false;
/*      */   }
/*      */   
/*      */   public void dispose() throws FactoryException {
/* 1272 */     super.dispose();
/* 1273 */     disposeAbstractAuthorityFactory(this.datumFactory);
/* 1274 */     disposeAbstractAuthorityFactory(this.csFactory);
/* 1275 */     disposeAbstractAuthorityFactory(this.crsFactory);
/* 1276 */     disposeAbstractAuthorityFactory(this.operationFactory);
/*      */   }
/*      */   
/*      */   private void disposeAbstractAuthorityFactory(Object factory) throws FactoryException {
/* 1280 */     if (factory instanceof AbstractAuthorityFactory)
/* 1281 */       ((AbstractAuthorityFactory)factory).dispose(); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AuthorityFactoryAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */