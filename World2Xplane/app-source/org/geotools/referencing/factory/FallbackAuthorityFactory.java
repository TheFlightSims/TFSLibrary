/*      */ package org.geotools.referencing.factory;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.LogRecord;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.factory.FactoryNotFoundException;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Loggings;
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
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class FallbackAuthorityFactory extends AuthorityFactoryAdapter {
/*      */   private final AbstractAuthorityFactory fallback;
/*      */   
/*      */   private static int failureCount;
/*      */   
/*      */   static boolean chainable(AuthorityFactory primary, AuthorityFactory fallback) {
/*   78 */     return ((interfaceMask(primary) & interfaceMask(fallback)) != 0);
/*      */   }
/*      */   
/*      */   protected FallbackAuthorityFactory(AuthorityFactory primary, AuthorityFactory fallback) {
/*   96 */     super(primary, fallback);
/*   97 */     ensureNonNull("fallback", fallback);
/*   98 */     this.fallback = (fallback instanceof AbstractAuthorityFactory) ? (AbstractAuthorityFactory)fallback : new AuthorityFactoryAdapter(fallback);
/*      */   }
/*      */   
/*      */   public static <T extends AuthorityFactory> T create(Class<T> type, Collection<T> factories) throws FactoryNotFoundException, ClassCastException {
/*  118 */     ensureNonNull("type", type);
/*  119 */     ensureNonNull("factories", factories);
/*  120 */     if (factories.isEmpty())
/*  121 */       throw new FactoryNotFoundException(Errors.format(49, type)); 
/*  123 */     return type.cast(create(false, interfaceMask(type), factories.iterator()));
/*      */   }
/*      */   
/*      */   public static AuthorityFactory create(Collection<? extends AuthorityFactory> factories) throws FactoryNotFoundException {
/*  143 */     ensureNonNull("factories", factories);
/*  144 */     if (factories.isEmpty())
/*  145 */       throw new FactoryNotFoundException(Errors.format(49, AuthorityFactory.class)); 
/*  148 */     return create(false, interfaceMask(factories), factories.iterator());
/*      */   }
/*      */   
/*      */   private static AuthorityFactory create(boolean automatic, int interfaceMask, Iterator<? extends AuthorityFactory> factories) throws FactoryNotFoundException {
/*  165 */     AuthorityFactory primary = factories.next();
/*  166 */     if (factories.hasNext()) {
/*  167 */       AuthorityFactory fallback = create(true, interfaceMask, factories);
/*  168 */       while (fallback != primary) {
/*  169 */         if (!sameAuthorityCodes(fallback, primary)) {
/*  182 */           if (automatic)
/*  185 */             interfaceMask &= interfaceMask(primary) | interfaceMask(fallback); 
/*  187 */           primary = create(interfaceMask, primary, fallback);
/*      */           break;
/*      */         } 
/*  196 */         if (fallback instanceof FallbackAuthorityFactory)
/*  197 */           fallback = ((FallbackAuthorityFactory)fallback).fallback; 
/*      */       } 
/*      */     } 
/*  204 */     return primary;
/*      */   }
/*      */   
/*      */   Collection<? super AuthorityFactory> dependencies() {
/*  213 */     Collection<? super AuthorityFactory> dep = super.dependencies();
/*  214 */     dep.add(this.fallback);
/*  215 */     return dep;
/*      */   }
/*      */   
/*      */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  227 */     Set<String> codes = new LinkedHashSet<String>(super.getAuthorityCodes(type));
/*  228 */     codes.addAll(this.fallback.getAuthorityCodes(type));
/*  229 */     return codes;
/*      */   }
/*      */   
/*      */   public InternationalString getDescriptionText(String code) throws FactoryException {
/*      */     try {
/*  238 */       return super.getDescriptionText(code);
/*  239 */     } catch (FactoryException exception) {
/*  240 */       notifyFailure("getDescriptionText", exception);
/*      */       try {
/*  242 */         return this.fallback.getDescriptionText(code);
/*  243 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  244 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public IdentifiedObject createObject(String code) throws FactoryException {
/*      */     try {
/*  255 */       return super.createObject(code);
/*  256 */     } catch (FactoryException exception) {
/*  257 */       notifyFailure("createObject", exception);
/*      */       try {
/*  259 */         return this.fallback.createObject(code);
/*  260 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  261 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public org.opengis.referencing.datum.Datum createDatum(String code) throws FactoryException {
/*      */     try {
/*  274 */       return super.createDatum(code);
/*  275 */     } catch (FactoryException exception) {
/*  276 */       notifyFailure("createDatum", exception);
/*      */       try {
/*  278 */         return this.fallback.createDatum(code);
/*  279 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  280 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/*      */     try {
/*  293 */       return super.createEngineeringDatum(code);
/*  294 */     } catch (FactoryException exception) {
/*  295 */       notifyFailure("createEngineeringDatum", exception);
/*      */       try {
/*  297 */         return this.fallback.createEngineeringDatum(code);
/*  298 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  299 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ImageDatum createImageDatum(String code) throws FactoryException {
/*      */     try {
/*  312 */       return super.createImageDatum(code);
/*  313 */     } catch (FactoryException exception) {
/*  314 */       notifyFailure("createImageDatum", exception);
/*      */       try {
/*  316 */         return this.fallback.createImageDatum(code);
/*  317 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  318 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public VerticalDatum createVerticalDatum(String code) throws FactoryException {
/*      */     try {
/*  331 */       return super.createVerticalDatum(code);
/*  332 */     } catch (FactoryException exception) {
/*  333 */       notifyFailure("createVerticalDatum", exception);
/*      */       try {
/*  335 */         return this.fallback.createVerticalDatum(code);
/*  336 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  337 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public TemporalDatum createTemporalDatum(String code) throws FactoryException {
/*      */     try {
/*  350 */       return super.createTemporalDatum(code);
/*  351 */     } catch (FactoryException exception) {
/*  352 */       notifyFailure("createTemporalDatum", exception);
/*      */       try {
/*  354 */         return this.fallback.createTemporalDatum(code);
/*  355 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  356 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/*      */     try {
/*  369 */       return super.createGeodeticDatum(code);
/*  370 */     } catch (FactoryException exception) {
/*  371 */       notifyFailure("createGeodeticDatum", exception);
/*      */       try {
/*  373 */         return this.fallback.createGeodeticDatum(code);
/*  374 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  375 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Ellipsoid createEllipsoid(String code) throws FactoryException {
/*      */     try {
/*  388 */       return super.createEllipsoid(code);
/*  389 */     } catch (FactoryException exception) {
/*  390 */       notifyFailure("createEllipsoid", exception);
/*      */       try {
/*  392 */         return this.fallback.createEllipsoid(code);
/*  393 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  394 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/*      */     try {
/*  407 */       return super.createPrimeMeridian(code);
/*  408 */     } catch (FactoryException exception) {
/*  409 */       notifyFailure("createPrimeMeridian", exception);
/*      */       try {
/*  411 */         return this.fallback.createPrimeMeridian(code);
/*  412 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  413 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Extent createExtent(String code) throws FactoryException {
/*      */     try {
/*  426 */       return super.createExtent(code);
/*  427 */     } catch (FactoryException exception) {
/*  428 */       notifyFailure("createExtent", exception);
/*      */       try {
/*  430 */         return this.fallback.createExtent(code);
/*  431 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  432 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/*      */     try {
/*  445 */       return super.createCoordinateSystem(code);
/*  446 */     } catch (FactoryException exception) {
/*  447 */       notifyFailure("createCoordinateSystem", exception);
/*      */       try {
/*  449 */         return this.fallback.createCoordinateSystem(code);
/*  450 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  451 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CartesianCS createCartesianCS(String code) throws FactoryException {
/*      */     try {
/*  464 */       return super.createCartesianCS(code);
/*  465 */     } catch (FactoryException exception) {
/*  466 */       notifyFailure("createCartesianCS", exception);
/*      */       try {
/*  468 */         return this.fallback.createCartesianCS(code);
/*  469 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  470 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public PolarCS createPolarCS(String code) throws FactoryException {
/*      */     try {
/*  483 */       return super.createPolarCS(code);
/*  484 */     } catch (FactoryException exception) {
/*  485 */       notifyFailure("createPolarCS", exception);
/*      */       try {
/*  487 */         return this.fallback.createPolarCS(code);
/*  488 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  489 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CylindricalCS createCylindricalCS(String code) throws FactoryException {
/*      */     try {
/*  502 */       return super.createCylindricalCS(code);
/*  503 */     } catch (FactoryException exception) {
/*  504 */       notifyFailure("createCylindricalCS", exception);
/*      */       try {
/*  506 */         return this.fallback.createCylindricalCS(code);
/*  507 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  508 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public SphericalCS createSphericalCS(String code) throws FactoryException {
/*      */     try {
/*  521 */       return super.createSphericalCS(code);
/*  522 */     } catch (FactoryException exception) {
/*  523 */       notifyFailure("createSphericalCS", exception);
/*      */       try {
/*  525 */         return this.fallback.createSphericalCS(code);
/*  526 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  527 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/*      */     try {
/*  540 */       return super.createEllipsoidalCS(code);
/*  541 */     } catch (FactoryException exception) {
/*  542 */       notifyFailure("createEllipsoidalCS", exception);
/*      */       try {
/*  544 */         return this.fallback.createEllipsoidalCS(code);
/*  545 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  546 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public VerticalCS createVerticalCS(String code) throws FactoryException {
/*      */     try {
/*  559 */       return super.createVerticalCS(code);
/*  560 */     } catch (FactoryException exception) {
/*  561 */       notifyFailure("createVerticalCS", exception);
/*      */       try {
/*  563 */         return this.fallback.createVerticalCS(code);
/*  564 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  565 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public TimeCS createTimeCS(String code) throws FactoryException {
/*      */     try {
/*  578 */       return super.createTimeCS(code);
/*  579 */     } catch (FactoryException exception) {
/*  580 */       notifyFailure("createTimeCS", exception);
/*      */       try {
/*  582 */         return this.fallback.createTimeCS(code);
/*  583 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  584 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/*      */     try {
/*  599 */       return super.createCoordinateSystemAxis(code);
/*  600 */     } catch (FactoryException exception) {
/*  601 */       notifyFailure("createCoordinateSystemAxis", exception);
/*      */       try {
/*  603 */         return this.fallback.createCoordinateSystemAxis(code);
/*  604 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  605 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Unit<?> createUnit(String code) throws FactoryException {
/*      */     try {
/*  618 */       return super.createUnit(code);
/*  619 */     } catch (FactoryException exception) {
/*  620 */       notifyFailure("createUnit", exception);
/*      */       try {
/*  622 */         return this.fallback.createUnit(code);
/*  623 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  624 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/*      */     try {
/*  640 */       return super.createCoordinateReferenceSystem(code);
/*  641 */     } catch (FactoryException exception) {
/*  642 */       notifyFailure("createCoordinateReferenceSystem", exception);
/*      */       try {
/*  644 */         return this.fallback.createCoordinateReferenceSystem(code);
/*  645 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  646 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CompoundCRS createCompoundCRS(String code) throws FactoryException {
/*      */     try {
/*  659 */       return super.createCompoundCRS(code);
/*  660 */     } catch (FactoryException exception) {
/*  661 */       notifyFailure("createCompoundCRS", exception);
/*      */       try {
/*  663 */         return this.fallback.createCompoundCRS(code);
/*  664 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  665 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/*      */     try {
/*  678 */       return super.createDerivedCRS(code);
/*  679 */     } catch (FactoryException exception) {
/*  680 */       notifyFailure("createDerivedCRS", exception);
/*      */       try {
/*  682 */         return this.fallback.createDerivedCRS(code);
/*  683 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  684 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/*      */     try {
/*  697 */       return super.createEngineeringCRS(code);
/*  698 */     } catch (FactoryException exception) {
/*  699 */       notifyFailure("createEngineeringCRS", exception);
/*      */       try {
/*  701 */         return this.fallback.createEngineeringCRS(code);
/*  702 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  703 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/*      */     try {
/*  716 */       return super.createGeographicCRS(code);
/*  717 */     } catch (FactoryException exception) {
/*  718 */       notifyFailure("createGeographicCRS", exception);
/*      */       try {
/*  720 */         return this.fallback.createGeographicCRS(code);
/*  721 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  722 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/*      */     try {
/*  735 */       return super.createGeocentricCRS(code);
/*  736 */     } catch (FactoryException exception) {
/*  737 */       notifyFailure("createGeocentricCRS", exception);
/*      */       try {
/*  739 */         return this.fallback.createGeocentricCRS(code);
/*  740 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  741 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ImageCRS createImageCRS(String code) throws FactoryException {
/*      */     try {
/*  754 */       return super.createImageCRS(code);
/*  755 */     } catch (FactoryException exception) {
/*  756 */       notifyFailure("createImageCRS", exception);
/*      */       try {
/*  758 */         return this.fallback.createImageCRS(code);
/*  759 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  760 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/*      */     try {
/*  773 */       return super.createProjectedCRS(code);
/*  774 */     } catch (FactoryException exception) {
/*  775 */       notifyFailure("createProjectedCRS", exception);
/*      */       try {
/*  777 */         return this.fallback.createProjectedCRS(code);
/*  778 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  779 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/*      */     try {
/*  792 */       return super.createTemporalCRS(code);
/*  793 */     } catch (FactoryException exception) {
/*  794 */       notifyFailure("createTemporalCRS", exception);
/*      */       try {
/*  796 */         return this.fallback.createTemporalCRS(code);
/*  797 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  798 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/*      */     try {
/*  811 */       return super.createVerticalCRS(code);
/*  812 */     } catch (FactoryException exception) {
/*  813 */       notifyFailure("createVerticalCRS", exception);
/*      */       try {
/*  815 */         return this.fallback.createVerticalCRS(code);
/*  816 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  817 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public ParameterDescriptor createParameterDescriptor(String code) throws FactoryException {
/*      */     try {
/*  830 */       return super.createParameterDescriptor(code);
/*  831 */     } catch (FactoryException exception) {
/*  832 */       notifyFailure("createParameterDescriptor", exception);
/*      */       try {
/*  834 */         return this.fallback.createParameterDescriptor(code);
/*  835 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  836 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public OperationMethod createOperationMethod(String code) throws FactoryException {
/*      */     try {
/*  849 */       return super.createOperationMethod(code);
/*  850 */     } catch (FactoryException exception) {
/*  851 */       notifyFailure("createOperationMethod", exception);
/*      */       try {
/*  853 */         return this.fallback.createOperationMethod(code);
/*  854 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  855 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/*      */     try {
/*  868 */       return super.createCoordinateOperation(code);
/*  869 */     } catch (FactoryException exception) {
/*  870 */       notifyFailure("createCoordinateOperation", exception);
/*      */       try {
/*  872 */         return this.fallback.createCoordinateOperation(code);
/*  873 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  874 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws FactoryException {
/*      */     try {
/*  890 */       return super.createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS);
/*  891 */     } catch (FactoryException exception) {
/*  892 */       notifyFailure("createFromCoordinateReferenceSystemCodes", exception);
/*      */       try {
/*  894 */         return this.fallback.createFromCoordinateReferenceSystemCodes(sourceCRS, targetCRS);
/*  895 */       } catch (NoSuchAuthorityCodeException ignore) {
/*  896 */         throw exception;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  912 */     return new Finder(type);
/*      */   }
/*      */   
/*      */   private final class Finder extends AuthorityFactoryAdapter.Finder {
/*      */     private transient IdentifiedObjectFinder fallback;
/*      */     
/*      */     Finder(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  929 */       super(type);
/*      */     }
/*      */     
/*      */     private void ensureFallback() throws FactoryException {
/*  936 */       if (this.fallback == null)
/*  937 */         this.fallback = FallbackAuthorityFactory.this.fallback.getIdentifiedObjectFinder(getProxy().getType()); 
/*  939 */       this.fallback.setFullScanAllowed(isFullScanAllowed());
/*      */     }
/*      */     
/*      */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/*  947 */       IdentifiedObject candidate = this.finder.find(object);
/*  948 */       if (candidate != null)
/*  949 */         return candidate; 
/*  951 */       ensureFallback();
/*  952 */       candidate = this.fallback.find(object);
/*  953 */       return candidate;
/*      */     }
/*      */     
/*      */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/*  961 */       String candidate = this.finder.findIdentifier(object);
/*  962 */       if (candidate != null)
/*  963 */         return candidate; 
/*  965 */       ensureFallback();
/*  966 */       candidate = this.fallback.findIdentifier(object);
/*  967 */       return candidate;
/*      */     }
/*      */   }
/*      */   
/*      */   private static void notifyFailure(String method, FactoryException exception) {
/*  982 */     failureCount++;
/*  983 */     if (LOGGER.isLoggable(Level.FINE)) {
/*  984 */       LogRecord record = Loggings.format(Level.FINE, 22, exception);
/*  986 */       record.setSourceClassName(FallbackAuthorityFactory.class.getName());
/*  987 */       record.setSourceMethodName(method);
/*  988 */       record.setLoggerName(LOGGER.getName());
/*  989 */       LOGGER.log(record);
/*      */     } 
/*      */   }
/*      */   
/*      */   static int getFailureCount() {
/*  999 */     return failureCount;
/*      */   }
/*      */   
/*      */   private static int interfaceMask(Collection<? extends AuthorityFactory> factories) {
/* 1006 */     int mask = 0;
/* 1007 */     for (AuthorityFactory factory : factories)
/* 1008 */       mask |= interfaceMask(factory); 
/* 1010 */     return mask;
/*      */   }
/*      */   
/*      */   private static int interfaceMask(AuthorityFactory factory) {
/* 1017 */     return interfaceMask((Class)factory.getClass());
/*      */   }
/*      */   
/*      */   private static int interfaceMask(Class<? extends AuthorityFactory> type) {
/* 1024 */     int mask = 0;
/* 1025 */     if (CoordinateOperationAuthorityFactory.class.isAssignableFrom(type))
/* 1025 */       mask |= 0x1; 
/* 1026 */     if (CSAuthorityFactory.class.isAssignableFrom(type))
/* 1026 */       mask |= 0x2; 
/* 1027 */     if (DatumAuthorityFactory.class.isAssignableFrom(type))
/* 1027 */       mask |= 0x4; 
/* 1028 */     if (CRSAuthorityFactory.class.isAssignableFrom(type))
/* 1028 */       mask |= 0x8; 
/* 1029 */     return mask;
/*      */   }
/*      */   
/*      */   private static AuthorityFactory create(int mask, AuthorityFactory primary, AuthorityFactory fallback) {
/*      */     AuthorityFactory factory;
/* 1042 */     assert (mask & ((interfaceMask(primary) | interfaceMask(fallback)) ^ 0xFFFFFFFF)) == 0 : mask;
/* 1050 */     switch (mask) {
/*      */       case 15:
/* 1051 */         factory = new All(primary, fallback);
/*      */         break;
/*      */       case 14:
/* 1052 */         factory = new CRS_Datum_CS(primary, fallback);
/*      */         break;
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/* 1058 */         factory = new CRS(primary, fallback);
/*      */         break;
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*      */       case 7:
/* 1062 */         factory = new Datum(primary, fallback);
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/* 1064 */         factory = new CS(primary, fallback);
/*      */         break;
/*      */       case 1:
/* 1065 */         factory = new Operation(primary, fallback);
/*      */         break;
/*      */       case 0:
/* 1066 */         factory = new FallbackAuthorityFactory(primary, fallback);
/*      */         break;
/*      */       default:
/* 1067 */         throw new AssertionError(mask);
/*      */     } 
/* 1074 */     assert (interfaceMask(factory) & (mask ^ 0xFFFFFFFF)) == 0 : mask;
/* 1075 */     return factory;
/*      */   }
/*      */   
/*      */   private static final class CRS extends FallbackAuthorityFactory implements CRSAuthorityFactory {
/*      */     CRS(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1083 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class CS extends FallbackAuthorityFactory implements CSAuthorityFactory {
/*      */     CS(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1092 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Datum extends FallbackAuthorityFactory implements DatumAuthorityFactory {
/*      */     Datum(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1101 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class Operation extends FallbackAuthorityFactory implements CoordinateOperationAuthorityFactory {
/*      */     Operation(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1110 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class CRS_Datum_CS extends FallbackAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory {
/*      */     CRS_Datum_CS(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1119 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class All extends FallbackAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory {
/*      */     All(AuthorityFactory primary, AuthorityFactory fallback) {
/* 1128 */       super(primary, fallback);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\FallbackAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */