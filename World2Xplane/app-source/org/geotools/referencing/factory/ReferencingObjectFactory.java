/*      */ package org.geotools.referencing.factory;
/*      */ 
/*      */ import java.text.ParseException;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.measure.quantity.Angle;
/*      */ import javax.measure.quantity.Length;
/*      */ import javax.measure.unit.Unit;
/*      */ import org.geotools.factory.BufferedFactory;
/*      */ import org.geotools.factory.Factory;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.referencing.ReferencingFactoryFinder;
/*      */ import org.geotools.referencing.crs.DefaultCompoundCRS;
/*      */ import org.geotools.referencing.crs.DefaultDerivedCRS;
/*      */ import org.geotools.referencing.crs.DefaultEngineeringCRS;
/*      */ import org.geotools.referencing.crs.DefaultGeocentricCRS;
/*      */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*      */ import org.geotools.referencing.crs.DefaultImageCRS;
/*      */ import org.geotools.referencing.crs.DefaultProjectedCRS;
/*      */ import org.geotools.referencing.crs.DefaultTemporalCRS;
/*      */ import org.geotools.referencing.crs.DefaultVerticalCRS;
/*      */ import org.geotools.referencing.cs.DefaultAffineCS;
/*      */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*      */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*      */ import org.geotools.referencing.cs.DefaultCylindricalCS;
/*      */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*      */ import org.geotools.referencing.cs.DefaultLinearCS;
/*      */ import org.geotools.referencing.cs.DefaultPolarCS;
/*      */ import org.geotools.referencing.cs.DefaultSphericalCS;
/*      */ import org.geotools.referencing.cs.DefaultTimeCS;
/*      */ import org.geotools.referencing.cs.DefaultUserDefinedCS;
/*      */ import org.geotools.referencing.cs.DefaultVerticalCS;
/*      */ import org.geotools.referencing.datum.DefaultEllipsoid;
/*      */ import org.geotools.referencing.datum.DefaultEngineeringDatum;
/*      */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*      */ import org.geotools.referencing.datum.DefaultImageDatum;
/*      */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*      */ import org.geotools.referencing.datum.DefaultTemporalDatum;
/*      */ import org.geotools.referencing.datum.DefaultVerticalDatum;
/*      */ import org.geotools.referencing.operation.DefaultMathTransformFactory;
/*      */ import org.geotools.referencing.operation.MathTransformProvider;
/*      */ import org.geotools.referencing.wkt.Parser;
/*      */ import org.geotools.referencing.wkt.Symbols;
/*      */ import org.geotools.util.CanonicalSet;
/*      */ import org.opengis.parameter.ParameterValueGroup;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.crs.CRSFactory;
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
/*      */ import org.opengis.referencing.cs.AffineCS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CSFactory;
/*      */ import org.opengis.referencing.cs.CartesianCS;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.CylindricalCS;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.cs.LinearCS;
/*      */ import org.opengis.referencing.cs.PolarCS;
/*      */ import org.opengis.referencing.cs.SphericalCS;
/*      */ import org.opengis.referencing.cs.TimeCS;
/*      */ import org.opengis.referencing.cs.UserDefinedCS;
/*      */ import org.opengis.referencing.cs.VerticalCS;
/*      */ import org.opengis.referencing.datum.DatumFactory;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.EngineeringDatum;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.ImageDatum;
/*      */ import org.opengis.referencing.datum.PixelInCell;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.TemporalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatumType;
/*      */ import org.opengis.referencing.operation.Conversion;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.MathTransformFactory;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ 
/*      */ public class ReferencingObjectFactory extends ReferencingFactory implements CSFactory, DatumFactory, CRSFactory, BufferedFactory {
/*      */   private MathTransformFactory mtFactory;
/*      */   
/*      */   private Parser parser;
/*      */   
/*      */   public ReferencingObjectFactory() {
/*   97 */     this(null);
/*      */   }
/*      */   
/*  115 */   private final CanonicalSet<IdentifiedObject> pool = CanonicalSet.newInstance(IdentifiedObject.class);
/*      */   
/*      */   public ReferencingObjectFactory(Hints hints) {
/*  116 */     if (hints != null && !hints.isEmpty()) {
/*  121 */       this.mtFactory = ReferencingFactoryFinder.getMathTransformFactory(hints);
/*  122 */       DatumFactory datumFactory = ReferencingFactoryFinder.getDatumFactory(hints);
/*  123 */       createParser(datumFactory, this.mtFactory);
/*  124 */       addHints(datumFactory);
/*  125 */       addHints(this.mtFactory);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void addHints(Object factory) {
/*  140 */     if (factory instanceof Factory)
/*  141 */       this.hints.putAll(((Factory)factory).getImplementationHints()); 
/*      */   }
/*      */   
/*      */   private synchronized MathTransformFactory getMathTransformFactory() {
/*  153 */     if (this.mtFactory == null)
/*  154 */       this.mtFactory = ReferencingFactoryFinder.getMathTransformFactory(null); 
/*  156 */     return this.mtFactory;
/*      */   }
/*      */   
/*      */   public Ellipsoid createEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) throws FactoryException {
/*      */     DefaultEllipsoid defaultEllipsoid;
/*      */     try {
/*  181 */       defaultEllipsoid = DefaultEllipsoid.createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit);
/*  183 */     } catch (IllegalArgumentException exception) {
/*  184 */       throw new FactoryException(exception);
/*      */     } 
/*  186 */     return (Ellipsoid)this.pool.unique(defaultEllipsoid);
/*      */   }
/*      */   
/*      */   public Ellipsoid createFlattenedSphere(Map<String, ?> properties, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) throws FactoryException {
/*      */     DefaultEllipsoid defaultEllipsoid;
/*      */     try {
/*  204 */       defaultEllipsoid = DefaultEllipsoid.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit);
/*  206 */     } catch (IllegalArgumentException exception) {
/*  207 */       throw new FactoryException(exception);
/*      */     } 
/*  209 */     return (Ellipsoid)this.pool.unique(defaultEllipsoid);
/*      */   }
/*      */   
/*      */   public PrimeMeridian createPrimeMeridian(Map<String, ?> properties, double longitude, Unit<Angle> angularUnit) throws FactoryException {
/*      */     DefaultPrimeMeridian defaultPrimeMeridian;
/*      */     try {
/*  226 */       defaultPrimeMeridian = new DefaultPrimeMeridian(properties, longitude, angularUnit);
/*  227 */     } catch (IllegalArgumentException exception) {
/*  228 */       throw new FactoryException(exception);
/*      */     } 
/*  230 */     return (PrimeMeridian)this.pool.unique(defaultPrimeMeridian);
/*      */   }
/*      */   
/*      */   public GeodeticDatum createGeodeticDatum(Map<String, ?> properties, Ellipsoid ellipsoid, PrimeMeridian primeMeridian) throws FactoryException {
/*      */     DefaultGeodeticDatum defaultGeodeticDatum;
/*      */     try {
/*  247 */       defaultGeodeticDatum = new DefaultGeodeticDatum(properties, ellipsoid, primeMeridian);
/*  248 */     } catch (IllegalArgumentException exception) {
/*  249 */       throw new FactoryException(exception);
/*      */     } 
/*  251 */     return (GeodeticDatum)this.pool.unique(defaultGeodeticDatum);
/*      */   }
/*      */   
/*      */   public VerticalDatum createVerticalDatum(Map<String, ?> properties, VerticalDatumType type) throws FactoryException {
/*      */     DefaultVerticalDatum defaultVerticalDatum;
/*      */     try {
/*  267 */       defaultVerticalDatum = new DefaultVerticalDatum(properties, type);
/*  268 */     } catch (IllegalArgumentException exception) {
/*  269 */       throw new FactoryException(exception);
/*      */     } 
/*  271 */     return (VerticalDatum)this.pool.unique(defaultVerticalDatum);
/*      */   }
/*      */   
/*      */   public TemporalDatum createTemporalDatum(Map<String, ?> properties, Date origin) throws FactoryException {
/*      */     DefaultTemporalDatum defaultTemporalDatum;
/*      */     try {
/*  287 */       defaultTemporalDatum = new DefaultTemporalDatum(properties, origin);
/*  288 */     } catch (IllegalArgumentException exception) {
/*  289 */       throw new FactoryException(exception);
/*      */     } 
/*  291 */     return (TemporalDatum)this.pool.unique(defaultTemporalDatum);
/*      */   }
/*      */   
/*      */   public EngineeringDatum createEngineeringDatum(Map<String, ?> properties) throws FactoryException {
/*      */     DefaultEngineeringDatum defaultEngineeringDatum;
/*      */     try {
/*  306 */       defaultEngineeringDatum = new DefaultEngineeringDatum(properties);
/*  307 */     } catch (IllegalArgumentException exception) {
/*  308 */       throw new FactoryException(exception);
/*      */     } 
/*  310 */     return (EngineeringDatum)this.pool.unique(defaultEngineeringDatum);
/*      */   }
/*      */   
/*      */   public ImageDatum createImageDatum(Map<String, ?> properties, PixelInCell pixelInCell) throws FactoryException {
/*      */     DefaultImageDatum defaultImageDatum;
/*      */     try {
/*  327 */       defaultImageDatum = new DefaultImageDatum(properties, pixelInCell);
/*  328 */     } catch (IllegalArgumentException exception) {
/*  329 */       throw new FactoryException(exception);
/*      */     } 
/*  331 */     return (ImageDatum)this.pool.unique(defaultImageDatum);
/*      */   }
/*      */   
/*      */   public CoordinateSystemAxis createCoordinateSystemAxis(Map<String, ?> properties, String abbreviation, AxisDirection direction, Unit<?> unit) throws FactoryException {
/*      */     DefaultCoordinateSystemAxis defaultCoordinateSystemAxis;
/*      */     try {
/*  357 */       defaultCoordinateSystemAxis = new DefaultCoordinateSystemAxis(properties, abbreviation, direction, unit);
/*  358 */     } catch (IllegalArgumentException exception) {
/*  359 */       throw new FactoryException(exception);
/*      */     } 
/*  361 */     return (CoordinateSystemAxis)this.pool.unique(defaultCoordinateSystemAxis);
/*      */   }
/*      */   
/*      */   public CartesianCS createCartesianCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) throws FactoryException {
/*      */     DefaultCartesianCS defaultCartesianCS;
/*      */     try {
/*  379 */       defaultCartesianCS = new DefaultCartesianCS(properties, axis0, axis1);
/*  380 */     } catch (IllegalArgumentException exception) {
/*  381 */       throw new FactoryException(exception);
/*      */     } 
/*  383 */     return (CartesianCS)this.pool.unique(defaultCartesianCS);
/*      */   }
/*      */   
/*      */   public CartesianCS createCartesianCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultCartesianCS defaultCartesianCS;
/*      */     try {
/*  403 */       defaultCartesianCS = new DefaultCartesianCS(properties, axis0, axis1, axis2);
/*  404 */     } catch (IllegalArgumentException exception) {
/*  405 */       throw new FactoryException(exception);
/*      */     } 
/*  407 */     return (CartesianCS)this.pool.unique(defaultCartesianCS);
/*      */   }
/*      */   
/*      */   public AffineCS createAffineCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) throws FactoryException {
/*      */     DefaultAffineCS defaultAffineCS;
/*      */     try {
/*  425 */       defaultAffineCS = new DefaultAffineCS(properties, axis0, axis1);
/*  426 */     } catch (IllegalArgumentException exception) {
/*  427 */       throw new FactoryException(exception);
/*      */     } 
/*  429 */     return (AffineCS)this.pool.unique(defaultAffineCS);
/*      */   }
/*      */   
/*      */   public AffineCS createAffineCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultAffineCS defaultAffineCS;
/*      */     try {
/*  449 */       defaultAffineCS = new DefaultAffineCS(properties, axis0, axis1, axis2);
/*  450 */     } catch (IllegalArgumentException exception) {
/*  451 */       throw new FactoryException(exception);
/*      */     } 
/*  453 */     return (AffineCS)this.pool.unique(defaultAffineCS);
/*      */   }
/*      */   
/*      */   public PolarCS createPolarCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) throws FactoryException {
/*      */     DefaultPolarCS defaultPolarCS;
/*      */     try {
/*  471 */       defaultPolarCS = new DefaultPolarCS(properties, axis0, axis1);
/*  472 */     } catch (IllegalArgumentException exception) {
/*  473 */       throw new FactoryException(exception);
/*      */     } 
/*  475 */     return (PolarCS)this.pool.unique(defaultPolarCS);
/*      */   }
/*      */   
/*      */   public CylindricalCS createCylindricalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultCylindricalCS defaultCylindricalCS;
/*      */     try {
/*  495 */       defaultCylindricalCS = new DefaultCylindricalCS(properties, axis0, axis1, axis2);
/*  496 */     } catch (IllegalArgumentException exception) {
/*  497 */       throw new FactoryException(exception);
/*      */     } 
/*  499 */     return (CylindricalCS)this.pool.unique(defaultCylindricalCS);
/*      */   }
/*      */   
/*      */   public SphericalCS createSphericalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultSphericalCS defaultSphericalCS;
/*      */     try {
/*  519 */       defaultSphericalCS = new DefaultSphericalCS(properties, axis0, axis1, axis2);
/*  520 */     } catch (IllegalArgumentException exception) {
/*  521 */       throw new FactoryException(exception);
/*      */     } 
/*  523 */     return (SphericalCS)this.pool.unique(defaultSphericalCS);
/*      */   }
/*      */   
/*      */   public EllipsoidalCS createEllipsoidalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) throws FactoryException {
/*      */     DefaultEllipsoidalCS defaultEllipsoidalCS;
/*      */     try {
/*  541 */       defaultEllipsoidalCS = new DefaultEllipsoidalCS(properties, axis0, axis1);
/*  542 */     } catch (IllegalArgumentException exception) {
/*  543 */       throw new FactoryException(exception);
/*      */     } 
/*  545 */     return (EllipsoidalCS)this.pool.unique(defaultEllipsoidalCS);
/*      */   }
/*      */   
/*      */   public EllipsoidalCS createEllipsoidalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultEllipsoidalCS defaultEllipsoidalCS;
/*      */     try {
/*  565 */       defaultEllipsoidalCS = new DefaultEllipsoidalCS(properties, axis0, axis1, axis2);
/*  566 */     } catch (IllegalArgumentException exception) {
/*  567 */       throw new FactoryException(exception);
/*      */     } 
/*  569 */     return (EllipsoidalCS)this.pool.unique(defaultEllipsoidalCS);
/*      */   }
/*      */   
/*      */   public VerticalCS createVerticalCS(Map<String, ?> properties, CoordinateSystemAxis axis) throws FactoryException {
/*      */     DefaultVerticalCS defaultVerticalCS;
/*      */     try {
/*  585 */       defaultVerticalCS = new DefaultVerticalCS(properties, axis);
/*  586 */     } catch (IllegalArgumentException exception) {
/*  587 */       throw new FactoryException(exception);
/*      */     } 
/*  589 */     return (VerticalCS)this.pool.unique(defaultVerticalCS);
/*      */   }
/*      */   
/*      */   public TimeCS createTimeCS(Map<String, ?> properties, CoordinateSystemAxis axis) throws FactoryException {
/*      */     DefaultTimeCS defaultTimeCS;
/*      */     try {
/*  605 */       defaultTimeCS = new DefaultTimeCS(properties, axis);
/*  606 */     } catch (IllegalArgumentException exception) {
/*  607 */       throw new FactoryException(exception);
/*      */     } 
/*  609 */     return (TimeCS)this.pool.unique(defaultTimeCS);
/*      */   }
/*      */   
/*      */   public LinearCS createLinearCS(Map<String, ?> properties, CoordinateSystemAxis axis) throws FactoryException {
/*      */     DefaultLinearCS defaultLinearCS;
/*      */     try {
/*  625 */       defaultLinearCS = new DefaultLinearCS(properties, axis);
/*  626 */     } catch (IllegalArgumentException exception) {
/*  627 */       throw new FactoryException(exception);
/*      */     } 
/*  629 */     return (LinearCS)this.pool.unique(defaultLinearCS);
/*      */   }
/*      */   
/*      */   public UserDefinedCS createUserDefinedCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) throws FactoryException {
/*      */     DefaultUserDefinedCS defaultUserDefinedCS;
/*      */     try {
/*  647 */       defaultUserDefinedCS = new DefaultUserDefinedCS(properties, axis0, axis1);
/*  648 */     } catch (IllegalArgumentException exception) {
/*  649 */       throw new FactoryException(exception);
/*      */     } 
/*  651 */     return (UserDefinedCS)this.pool.unique(defaultUserDefinedCS);
/*      */   }
/*      */   
/*      */   public UserDefinedCS createUserDefinedCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) throws FactoryException {
/*      */     DefaultUserDefinedCS defaultUserDefinedCS;
/*      */     try {
/*  671 */       defaultUserDefinedCS = new DefaultUserDefinedCS(properties, axis0, axis1, axis2);
/*  672 */     } catch (IllegalArgumentException exception) {
/*  673 */       throw new FactoryException(exception);
/*      */     } 
/*  675 */     return (UserDefinedCS)this.pool.unique(defaultUserDefinedCS);
/*      */   }
/*      */   
/*      */   public CompoundCRS createCompoundCRS(Map<String, ?> properties, CoordinateReferenceSystem[] elements) throws FactoryException {
/*      */     DefaultCompoundCRS defaultCompoundCRS;
/*      */     try {
/*  701 */       defaultCompoundCRS = new DefaultCompoundCRS(properties, elements);
/*  702 */     } catch (IllegalArgumentException exception) {
/*  703 */       throw new FactoryException(exception);
/*      */     } 
/*  705 */     return (CompoundCRS)this.pool.unique(defaultCompoundCRS);
/*      */   }
/*      */   
/*      */   public EngineeringCRS createEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs) throws FactoryException {
/*      */     DefaultEngineeringCRS defaultEngineeringCRS;
/*      */     try {
/*  723 */       defaultEngineeringCRS = new DefaultEngineeringCRS(properties, datum, cs);
/*  724 */     } catch (IllegalArgumentException exception) {
/*  725 */       throw new FactoryException(exception);
/*      */     } 
/*  727 */     return (EngineeringCRS)this.pool.unique(defaultEngineeringCRS);
/*      */   }
/*      */   
/*      */   public ImageCRS createImageCRS(Map<String, ?> properties, ImageDatum datum, AffineCS cs) throws FactoryException {
/*      */     DefaultImageCRS defaultImageCRS;
/*      */     try {
/*  745 */       defaultImageCRS = new DefaultImageCRS(properties, datum, cs);
/*  746 */     } catch (IllegalArgumentException exception) {
/*  747 */       throw new FactoryException(exception);
/*      */     } 
/*  749 */     return (ImageCRS)this.pool.unique(defaultImageCRS);
/*      */   }
/*      */   
/*      */   public TemporalCRS createTemporalCRS(Map<String, ?> properties, TemporalDatum datum, TimeCS cs) throws FactoryException {
/*      */     DefaultTemporalCRS defaultTemporalCRS;
/*      */     try {
/*  767 */       defaultTemporalCRS = new DefaultTemporalCRS(properties, datum, cs);
/*  768 */     } catch (IllegalArgumentException exception) {
/*  769 */       throw new FactoryException(exception);
/*      */     } 
/*  771 */     return (TemporalCRS)this.pool.unique(defaultTemporalCRS);
/*      */   }
/*      */   
/*      */   public VerticalCRS createVerticalCRS(Map<String, ?> properties, VerticalDatum datum, VerticalCS cs) throws FactoryException {
/*      */     DefaultVerticalCRS defaultVerticalCRS;
/*      */     try {
/*  789 */       defaultVerticalCRS = new DefaultVerticalCRS(properties, datum, cs);
/*  790 */     } catch (IllegalArgumentException exception) {
/*  791 */       throw new FactoryException(exception);
/*      */     } 
/*  793 */     return (VerticalCRS)this.pool.unique(defaultVerticalCRS);
/*      */   }
/*      */   
/*      */   public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, CartesianCS cs) throws FactoryException {
/*      */     DefaultGeocentricCRS defaultGeocentricCRS;
/*      */     try {
/*  812 */       defaultGeocentricCRS = new DefaultGeocentricCRS(properties, datum, cs);
/*  813 */     } catch (IllegalArgumentException exception) {
/*  814 */       throw new FactoryException(exception);
/*      */     } 
/*  816 */     return (GeocentricCRS)this.pool.unique(defaultGeocentricCRS);
/*      */   }
/*      */   
/*      */   public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, SphericalCS cs) throws FactoryException {
/*      */     DefaultGeocentricCRS defaultGeocentricCRS;
/*      */     try {
/*  835 */       defaultGeocentricCRS = new DefaultGeocentricCRS(properties, datum, cs);
/*  836 */     } catch (IllegalArgumentException exception) {
/*  837 */       throw new FactoryException(exception);
/*      */     } 
/*  839 */     return (GeocentricCRS)this.pool.unique(defaultGeocentricCRS);
/*      */   }
/*      */   
/*      */   public GeographicCRS createGeographicCRS(Map<String, ?> properties, GeodeticDatum datum, EllipsoidalCS cs) throws FactoryException {
/*      */     DefaultGeographicCRS defaultGeographicCRS;
/*      */     try {
/*  859 */       defaultGeographicCRS = new DefaultGeographicCRS(properties, datum, cs);
/*  860 */     } catch (IllegalArgumentException exception) {
/*  861 */       throw new FactoryException(exception);
/*      */     } 
/*  863 */     return (GeographicCRS)this.pool.unique(defaultGeographicCRS);
/*      */   }
/*      */   
/*      */   public DerivedCRS createDerivedCRS(Map<String, ?> properties, OperationMethod method, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws FactoryException {
/*      */     DefaultDerivedCRS defaultDerivedCRS;
/*      */     try {
/*  900 */       defaultDerivedCRS = new DefaultDerivedCRS(properties, method, base, baseToDerived, derivedCS);
/*  901 */     } catch (IllegalArgumentException exception) {
/*  902 */       throw new FactoryException(exception);
/*      */     } 
/*  904 */     return (DerivedCRS)this.pool.unique(defaultDerivedCRS);
/*      */   }
/*      */   
/*      */   public DerivedCRS createDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem baseCRS, Conversion conversionFromBase, CoordinateSystem derivedCS) throws FactoryException {
/*      */     DefaultDerivedCRS defaultDerivedCRS;
/*  927 */     MathTransform mt = conversionFromBase.getMathTransform();
/*  928 */     if (mt == null) {
/*  929 */       ParameterValueGroup parameters = conversionFromBase.getParameterValues();
/*  930 */       MathTransformFactory mtFactory = getMathTransformFactory();
/*  931 */       mt = mtFactory.createParameterizedTransform(parameters);
/*      */     } 
/*      */     try {
/*  935 */       defaultDerivedCRS = new DefaultDerivedCRS(properties, conversionFromBase, baseCRS, mt, derivedCS);
/*  936 */     } catch (IllegalArgumentException exception) {
/*  937 */       throw new FactoryException(exception);
/*      */     } 
/*  939 */     return (DerivedCRS)this.pool.unique(defaultDerivedCRS);
/*      */   }
/*      */   
/*      */   public ProjectedCRS createProjectedCRS(Map<String, ?> properties, OperationMethod method, GeographicCRS base, MathTransform baseToDerived, CartesianCS derivedCS) throws FactoryException {
/*      */     DefaultProjectedCRS defaultProjectedCRS;
/*      */     try {
/*  971 */       defaultProjectedCRS = new DefaultProjectedCRS(properties, method, base, baseToDerived, derivedCS);
/*  972 */     } catch (IllegalArgumentException exception) {
/*  973 */       throw new FactoryException(exception);
/*      */     } 
/*  975 */     return (ProjectedCRS)this.pool.unique(defaultProjectedCRS);
/*      */   }
/*      */   
/*      */   public ProjectedCRS createProjectedCRS(Map<String, ?> properties, GeographicCRS baseCRS, Conversion conversionFromBase, CartesianCS derivedCS) throws FactoryException {
/*      */     MathTransform mt;
/*      */     DefaultProjectedCRS defaultProjectedCRS;
/*  999 */     MathTransform existing = conversionFromBase.getMathTransform();
/* 1000 */     MathTransformFactory mtFactory = getMathTransformFactory();
/* 1001 */     if (existing != null && mtFactory instanceof DefaultMathTransformFactory) {
/* 1014 */       mt = ((DefaultMathTransformFactory)mtFactory).createBaseToDerived((CoordinateReferenceSystem)baseCRS, existing, (CoordinateSystem)derivedCS);
/*      */     } else {
/* 1020 */       ParameterValueGroup parameters = conversionFromBase.getParameterValues();
/* 1021 */       mt = mtFactory.createBaseToDerived((CoordinateReferenceSystem)baseCRS, parameters, (CoordinateSystem)derivedCS);
/* 1022 */       OperationMethod method = conversionFromBase.getMethod();
/* 1023 */       if (!(method instanceof MathTransformProvider))
/* 1031 */         if (!properties.containsKey("conversionType")) {
/* 1032 */           method = mtFactory.getLastMethodUsed();
/* 1033 */           if (method instanceof MathTransformProvider) {
/* 1034 */             Map<String, Object> copy = new HashMap<String, Object>(properties);
/* 1035 */             copy.put("conversionType", ((MathTransformProvider)method).getOperationType());
/* 1037 */             properties = copy;
/*      */           } 
/*      */         }  
/* 1046 */       if (existing != null && existing.equals(mt))
/* 1047 */         mt = existing; 
/*      */     } 
/*      */     try {
/* 1052 */       defaultProjectedCRS = new DefaultProjectedCRS(properties, conversionFromBase, baseCRS, mt, derivedCS);
/* 1053 */     } catch (IllegalArgumentException exception) {
/* 1054 */       throw new FactoryException(exception);
/*      */     } 
/* 1056 */     return (ProjectedCRS)this.pool.unique(defaultProjectedCRS);
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem createFromXML(String xml) throws FactoryException {
/* 1069 */     throw new FactoryException("Not yet implemented");
/*      */   }
/*      */   
/*      */   public synchronized CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException {
/* 1085 */     if (this.parser == null)
/* 1086 */       createParser(ReferencingFactoryFinder.getDatumFactory(null), getMathTransformFactory()); 
/*      */     try {
/* 1090 */       return this.parser.parseCoordinateReferenceSystem(wkt);
/* 1091 */     } catch (ParseException exception) {
/* 1092 */       Throwable cause = exception.getCause();
/* 1093 */       if (cause instanceof FactoryException)
/* 1094 */         throw (FactoryException)cause; 
/* 1096 */       throw new FactoryException(exception);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void createParser(DatumFactory datumFactory, MathTransformFactory mtFactory) {
/* 1106 */     this.parser = new Parser(Symbols.DEFAULT, datumFactory, this, this, mtFactory);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\ReferencingObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */