/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.NameFactory;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.DerivedCRS;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.crs.GeocentricCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ImageCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.crs.TemporalCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractAuthorityFactory extends ReferencingFactory implements AuthorityFactory {
/*     */   protected AbstractAuthorityFactory(int priority) {
/*  80 */     super(priority);
/*     */   }
/*     */   
/*     */   boolean isAvailable() {
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   boolean sameAuthorityCodes(AuthorityFactory factory) {
/* 103 */     return (factory == this);
/*     */   }
/*     */   
/*     */   public abstract Citation getAuthority();
/*     */   
/*     */   public String getBackingStoreDescription() throws FactoryException {
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 143 */     ensureNonNull("code", code);
/* 144 */     throw noSuchAuthorityCode(IdentifiedObject.class, code);
/*     */   }
/*     */   
/*     */   public Datum createDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 163 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 165 */       return (Datum)object;
/* 166 */     } catch (ClassCastException exception) {
/* 167 */       throw noSuchAuthorityCode(Datum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EngineeringDatum createEngineeringDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 185 */     Datum datum = createDatum(code);
/*     */     try {
/* 187 */       return (EngineeringDatum)datum;
/* 188 */     } catch (ClassCastException exception) {
/* 189 */       throw noSuchAuthorityCode(EngineeringDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImageDatum createImageDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 207 */     Datum datum = createDatum(code);
/*     */     try {
/* 209 */       return (ImageDatum)datum;
/* 210 */     } catch (ClassCastException exception) {
/* 211 */       throw noSuchAuthorityCode(ImageDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalDatum createVerticalDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 229 */     Datum datum = createDatum(code);
/*     */     try {
/* 231 */       return (VerticalDatum)datum;
/* 232 */     } catch (ClassCastException exception) {
/* 233 */       throw noSuchAuthorityCode(VerticalDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TemporalDatum createTemporalDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 251 */     Datum datum = createDatum(code);
/*     */     try {
/* 253 */       return (TemporalDatum)datum;
/* 254 */     } catch (ClassCastException exception) {
/* 255 */       throw noSuchAuthorityCode(TemporalDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeodeticDatum createGeodeticDatum(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 276 */     Datum datum = createDatum(code);
/*     */     try {
/* 278 */       return (GeodeticDatum)datum;
/* 279 */     } catch (ClassCastException exception) {
/* 280 */       throw noSuchAuthorityCode(GeodeticDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Ellipsoid createEllipsoid(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 298 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 300 */       return (Ellipsoid)object;
/* 301 */     } catch (ClassCastException exception) {
/* 302 */       throw noSuchAuthorityCode(Ellipsoid.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PrimeMeridian createPrimeMeridian(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 320 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 322 */       return (PrimeMeridian)object;
/* 323 */     } catch (ClassCastException exception) {
/* 324 */       throw noSuchAuthorityCode(PrimeMeridian.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Extent createExtent(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 340 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 342 */       return (Extent)object;
/* 343 */     } catch (ClassCastException exception) {
/* 344 */       throw noSuchAuthorityCode(Extent.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateSystem createCoordinateSystem(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 360 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 362 */       return (CoordinateSystem)object;
/* 363 */     } catch (ClassCastException exception) {
/* 364 */       throw noSuchAuthorityCode(CoordinateSystem.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CartesianCS createCartesianCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 381 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 383 */       return (CartesianCS)cs;
/* 384 */     } catch (ClassCastException exception) {
/* 385 */       throw noSuchAuthorityCode(CartesianCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PolarCS createPolarCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 402 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 404 */       return (PolarCS)cs;
/* 405 */     } catch (ClassCastException exception) {
/* 406 */       throw noSuchAuthorityCode(PolarCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CylindricalCS createCylindricalCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 423 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 425 */       return (CylindricalCS)cs;
/* 426 */     } catch (ClassCastException exception) {
/* 427 */       throw noSuchAuthorityCode(CylindricalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SphericalCS createSphericalCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 444 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 446 */       return (SphericalCS)cs;
/* 447 */     } catch (ClassCastException exception) {
/* 448 */       throw noSuchAuthorityCode(SphericalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EllipsoidalCS createEllipsoidalCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 465 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 467 */       return (EllipsoidalCS)cs;
/* 468 */     } catch (ClassCastException exception) {
/* 469 */       throw noSuchAuthorityCode(EllipsoidalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalCS createVerticalCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 486 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 488 */       return (VerticalCS)cs;
/* 489 */     } catch (ClassCastException exception) {
/* 490 */       throw noSuchAuthorityCode(VerticalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TimeCS createTimeCS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 507 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 509 */       return (TimeCS)cs;
/* 510 */     } catch (ClassCastException exception) {
/* 511 */       throw noSuchAuthorityCode(TimeCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 527 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 529 */       return (CoordinateSystemAxis)object;
/* 530 */     } catch (ClassCastException exception) {
/* 531 */       throw noSuchAuthorityCode(CoordinateSystemAxis.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Unit<?> createUnit(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 547 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 549 */       return (Unit)object;
/* 550 */     } catch (ClassCastException exception) {
/* 551 */       throw noSuchAuthorityCode(Unit.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 577 */     IdentifiedObject object = createObject(code);
/*     */     try {
/* 579 */       return (CoordinateReferenceSystem)object;
/* 580 */     } catch (ClassCastException exception) {
/* 581 */       throw noSuchAuthorityCode(CoordinateReferenceSystem.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CompoundCRS createCompoundCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 596 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 598 */       return (CompoundCRS)crs;
/* 599 */     } catch (ClassCastException exception) {
/* 600 */       throw noSuchAuthorityCode(CompoundCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public DerivedCRS createDerivedCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 615 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 617 */       return (DerivedCRS)crs;
/* 618 */     } catch (ClassCastException exception) {
/* 619 */       throw noSuchAuthorityCode(DerivedCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EngineeringCRS createEngineeringCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 634 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 636 */       return (EngineeringCRS)crs;
/* 637 */     } catch (ClassCastException exception) {
/* 638 */       throw noSuchAuthorityCode(EngineeringCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeographicCRS createGeographicCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 655 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 657 */       return (GeographicCRS)crs;
/* 658 */     } catch (ClassCastException exception) {
/* 659 */       throw noSuchAuthorityCode(GeographicCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeocentricCRS createGeocentricCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 676 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 678 */       return (GeocentricCRS)crs;
/* 679 */     } catch (ClassCastException exception) {
/* 680 */       throw noSuchAuthorityCode(GeocentricCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImageCRS createImageCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 695 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 697 */       return (ImageCRS)crs;
/* 698 */     } catch (ClassCastException exception) {
/* 699 */       throw noSuchAuthorityCode(ImageCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 716 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 718 */       return (ProjectedCRS)crs;
/* 719 */     } catch (ClassCastException exception) {
/* 720 */       throw noSuchAuthorityCode(ProjectedCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TemporalCRS createTemporalCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 737 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 739 */       return (TemporalCRS)crs;
/* 740 */     } catch (ClassCastException exception) {
/* 741 */       throw noSuchAuthorityCode(TemporalCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalCRS createVerticalCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 758 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 760 */       return (VerticalCRS)crs;
/* 761 */     } catch (ClassCastException exception) {
/* 762 */       throw noSuchAuthorityCode(VerticalCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterDescriptor createParameterDescriptor(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 779 */     IdentifiedObject operation = createObject(code);
/*     */     try {
/* 781 */       return (ParameterDescriptor)operation;
/* 782 */     } catch (ClassCastException exception) {
/* 783 */       throw noSuchAuthorityCode(ParameterDescriptor.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OperationMethod createOperationMethod(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 800 */     IdentifiedObject operation = createObject(code);
/*     */     try {
/* 802 */       return (OperationMethod)operation;
/* 803 */     } catch (ClassCastException exception) {
/* 804 */       throw noSuchAuthorityCode(OperationMethod.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 821 */     IdentifiedObject operation = createObject(code);
/*     */     try {
/* 823 */       return (CoordinateOperation)operation;
/* 824 */     } catch (ClassCastException exception) {
/* 825 */       throw noSuchAuthorityCode(CoordinateOperation.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCRS, String targetCRS) throws NoSuchAuthorityCodeException, FactoryException {
/* 853 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 876 */     return new IdentifiedObjectFinder(this, type);
/*     */   }
/*     */   
/*     */   public void dispose() throws FactoryException {}
/*     */   
/*     */   private NoSuchAuthorityCodeException noSuchAuthorityCode(Class type, String code, ClassCastException cause) {
/* 906 */     NoSuchAuthorityCodeException exception = noSuchAuthorityCode(type, code);
/* 907 */     exception.initCause(cause);
/* 908 */     return exception;
/*     */   }
/*     */   
/*     */   protected String trimAuthority(String code) {
/* 925 */     code = code.trim();
/* 926 */     GenericName name = NameFactory.create(code);
/* 927 */     GenericName scope = name.scope().name();
/* 928 */     if (scope == null)
/* 929 */       return code; 
/* 931 */     if (Citations.identifierMatches(getAuthority(), scope.toString()))
/* 932 */       return name.tip().toString().trim(); 
/* 934 */     return code;
/*     */   }
/*     */   
/*     */   protected final NoSuchAuthorityCodeException noSuchAuthorityCode(Class type, String code) {
/* 950 */     InternationalString authority = getAuthority().getTitle();
/* 951 */     return new NoSuchAuthorityCodeException(Errors.format(138, code, authority, type), authority.toString(), code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AbstractAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */