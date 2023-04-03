/*      */ package org.geotools.referencing;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import org.geotools.factory.Factory;
/*      */ import org.geotools.factory.FactoryNotFoundException;
/*      */ import org.geotools.factory.FactoryRegistryException;
/*      */ import org.geotools.factory.GeoTools;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.geometry.DirectPosition2D;
/*      */ import org.geotools.geometry.Envelope2D;
/*      */ import org.geotools.geometry.GeneralDirectPosition;
/*      */ import org.geotools.geometry.GeneralEnvelope;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.metadata.iso.extent.GeographicBoundingBoxImpl;
/*      */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*      */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*      */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*      */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*      */ import org.geotools.referencing.factory.IdentifiedObjectFinder;
/*      */ import org.geotools.referencing.operation.DefaultMathTransformFactory;
/*      */ import org.geotools.referencing.operation.projection.MapProjection;
/*      */ import org.geotools.referencing.operation.projection.PolarStereographic;
/*      */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*      */ import org.geotools.referencing.operation.transform.IdentityTransform;
/*      */ import org.geotools.referencing.wkt.Formattable;
/*      */ import org.geotools.resources.CRSUtilities;
/*      */ import org.geotools.resources.geometry.XRectangle2D;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.UnsupportedImplementationException;
/*      */ import org.geotools.util.Version;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.geometry.DirectPosition;
/*      */ import org.opengis.geometry.Envelope;
/*      */ import org.opengis.geometry.Geometry;
/*      */ import org.opengis.geometry.MismatchedDimensionException;
/*      */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.metadata.extent.BoundingPolygon;
/*      */ import org.opengis.metadata.extent.Extent;
/*      */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*      */ import org.opengis.metadata.extent.GeographicExtent;
/*      */ import org.opengis.parameter.ParameterValue;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.crs.ProjectedCRS;
/*      */ import org.opengis.referencing.crs.SingleCRS;
/*      */ import org.opengis.referencing.crs.TemporalCRS;
/*      */ import org.opengis.referencing.crs.VerticalCRS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.datum.Datum;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.MathTransform2D;
/*      */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*      */ import org.opengis.referencing.operation.Projection;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ 
/*      */ public final class CRS {
/*  144 */   static final Logger LOGGER = Logging.getLogger(CRS.class);
/*      */   
/*  146 */   static volatile AtomicBoolean FORCED_LON_LAT = null;
/*      */   
/*      */   public enum AxisOrder {
/*  155 */     EAST_NORTH, NORTH_EAST, INAPPLICABLE;
/*      */     
/*  168 */     public static AxisOrder LON_LAT = EAST_NORTH;
/*      */     
/*  174 */     public static AxisOrder LAT_LON = NORTH_EAST;
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */   
/*  180 */   private static final Hints FORCE_LONGITUDE_FIRST_AXIS_ORDER = new Hints((RenderingHints.Key)Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
/*      */   
/*      */   private static CRSAuthorityFactory defaultFactory;
/*      */   
/*      */   private static CRSAuthorityFactory xyFactory;
/*      */   
/*      */   private static CoordinateOperationFactory strictFactory;
/*      */   
/*      */   private static CoordinateOperationFactory lenientFactory;
/*      */   
/*      */   static {
/*  209 */     GeoTools.addChangeListener(new ChangeListener() {
/*      */           public void stateChanged(ChangeEvent e) {
/*  211 */             synchronized (CRS.class) {
/*  212 */               CRS.defaultFactory = null;
/*  213 */               CRS.xyFactory = null;
/*  214 */               CRS.strictFactory = null;
/*  215 */               CRS.lenientFactory = null;
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public static synchronized CRSAuthorityFactory getAuthorityFactory(boolean longitudeFirst) throws FactoryRegistryException {
/*  257 */     CRSAuthorityFactory factory = longitudeFirst ? xyFactory : defaultFactory;
/*  258 */     if (factory == null)
/*      */       try {
/*  261 */         updateForcedLonLat();
/*  262 */         factory = new DefaultAuthorityFactory(longitudeFirst);
/*  263 */         if (longitudeFirst) {
/*  264 */           xyFactory = factory;
/*      */         } else {
/*  266 */           defaultFactory = factory;
/*      */         } 
/*  268 */       } catch (NoSuchElementException exception) {
/*  270 */         throw new FactoryNotFoundException(null, exception);
/*      */       }  
/*  272 */     return factory;
/*      */   }
/*      */   
/*      */   private static void updateForcedLonLat() {
/*  276 */     boolean forcedLonLat = false;
/*      */     try {
/*  278 */       forcedLonLat = (Boolean.getBoolean("org.geotools.referencing.forceXY") || Boolean.TRUE.equals(Hints.getSystemDefault((RenderingHints.Key)Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER)));
/*  280 */     } catch (Exception e) {
/*  282 */       LOGGER.log(Level.FINE, "Failed to determine if we are in forced lon/lat mode", e);
/*      */     } 
/*  284 */     FORCED_LON_LAT = new AtomicBoolean(forcedLonLat);
/*      */   }
/*      */   
/*      */   private static boolean isForcedLonLat() {
/*  288 */     if (FORCED_LON_LAT == null)
/*  289 */       updateForcedLonLat(); 
/*  291 */     return FORCED_LON_LAT.get();
/*      */   }
/*      */   
/*      */   public static synchronized CoordinateOperationFactory getCoordinateOperationFactory(boolean lenient) {
/*  305 */     CoordinateOperationFactory factory = lenient ? lenientFactory : strictFactory;
/*  306 */     if (factory == null) {
/*  307 */       Hints hints = GeoTools.getDefaultHints();
/*  308 */       if (lenient)
/*  309 */         hints.put(Hints.LENIENT_DATUM_SHIFT, Boolean.TRUE); 
/*  311 */       factory = ReferencingFactoryFinder.getCoordinateOperationFactory(hints);
/*  312 */       if (lenient) {
/*  313 */         lenientFactory = factory;
/*      */       } else {
/*  315 */         strictFactory = factory;
/*      */       } 
/*      */     } 
/*  318 */     return factory;
/*      */   }
/*      */   
/*      */   public static Version getVersion(String authority) throws FactoryRegistryException {
/*  333 */     Object candidate = ReferencingFactoryFinder.getCRSAuthorityFactory(authority, null);
/*  334 */     Set<Factory> guard = new HashSet<Factory>();
/*  335 */     while (candidate instanceof Factory) {
/*  336 */       Factory factory = (Factory)candidate;
/*  337 */       if (!guard.add(factory))
/*      */         break; 
/*  340 */       Map hints = factory.getImplementationHints();
/*  341 */       Object version = hints.get(Hints.VERSION);
/*  342 */       if (version instanceof Version)
/*  343 */         return (Version)version; 
/*  345 */       candidate = hints.get(Hints.CRS_AUTHORITY_FACTORY);
/*      */     } 
/*  347 */     return null;
/*      */   }
/*      */   
/*      */   public static Set<String> getSupportedCodes(String authority) {
/*  395 */     return DefaultAuthorityFactory.getSupportedCodes(authority);
/*      */   }
/*      */   
/*      */   public static Set<String> getSupportedAuthorities(boolean returnAliases) {
/*  409 */     return DefaultAuthorityFactory.getSupportedAuthorities(returnAliases);
/*      */   }
/*      */   
/*      */   public static CoordinateReferenceSystem decode(String code) throws NoSuchAuthorityCodeException, FactoryException {
/*  447 */     return decode(code, false);
/*      */   }
/*      */   
/*      */   public static CoordinateReferenceSystem decode(String code, boolean longitudeFirst) throws NoSuchAuthorityCodeException, FactoryException {
/*  518 */     code = code.trim().toUpperCase();
/*  519 */     return getAuthorityFactory(longitudeFirst).createCoordinateReferenceSystem(code);
/*      */   }
/*      */   
/*      */   public static CoordinateReferenceSystem parseWKT(String wkt) throws FactoryException {
/*  534 */     return ReferencingFactoryFinder.getCRSFactory(null).createFromWKT(wkt);
/*      */   }
/*      */   
/*      */   public static Envelope getEnvelope(CoordinateReferenceSystem crs) {
/*      */     GeneralEnvelope generalEnvelope1;
/*  558 */     Envelope envelope = null;
/*  559 */     GeneralEnvelope merged = null;
/*  560 */     if (crs != null) {
/*  561 */       Extent domainOfValidity = crs.getDomainOfValidity();
/*  562 */       if (domainOfValidity != null)
/*  563 */         for (GeographicExtent extent : domainOfValidity.getGeographicElements()) {
/*  564 */           if (Boolean.FALSE.equals(extent.getInclusion()))
/*      */             continue; 
/*  567 */           if (extent instanceof BoundingPolygon)
/*  568 */             for (Geometry geometry : ((BoundingPolygon)extent).getPolygons()) {
/*  569 */               Envelope candidate = geometry.getEnvelope();
/*  570 */               if (candidate != null) {
/*  571 */                 CoordinateReferenceSystem sourceCRS = candidate.getCoordinateReferenceSystem();
/*  573 */                 if (sourceCRS == null || equalsIgnoreMetadata(sourceCRS, crs)) {
/*  574 */                   if (envelope == null) {
/*  575 */                     envelope = candidate;
/*      */                     continue;
/*      */                   } 
/*  577 */                   if (merged == null)
/*  578 */                     generalEnvelope1 = merged = new GeneralEnvelope(envelope); 
/*  580 */                   merged.add((Envelope)generalEnvelope1);
/*      */                 } 
/*      */               } 
/*      */             }  
/*      */         }  
/*      */     } 
/*  594 */     if (generalEnvelope1 == null) {
/*  595 */       GeographicBoundingBox bounds = getGeographicBoundingBox(crs);
/*  596 */       if (bounds != null && !Boolean.FALSE.equals(bounds.getInclusion())) {
/*  597 */         generalEnvelope1 = merged = new GeneralEnvelope(new double[] { bounds.getWestBoundLongitude(), bounds.getSouthBoundLatitude() }, new double[] { bounds.getEastBoundLongitude(), bounds.getNorthBoundLatitude() });
/*  606 */         SingleCRS targetCRS = getHorizontalCRS(crs);
/*  607 */         GeographicCRS sourceCRS = CRSUtilities.getStandardGeographicCRS2D((CoordinateReferenceSystem)targetCRS);
/*  608 */         merged.setCoordinateReferenceSystem((CoordinateReferenceSystem)sourceCRS);
/*      */         try {
/*  610 */           Envelope envelope1 = transform((Envelope)generalEnvelope1, (CoordinateReferenceSystem)targetCRS);
/*  611 */         } catch (TransformException exception) {
/*  618 */           generalEnvelope1 = null;
/*  619 */           unexpectedException("getEnvelope", (Exception)exception);
/*      */         } 
/*  627 */         merged.setCoordinateReferenceSystem((CoordinateReferenceSystem)targetCRS);
/*      */       } 
/*      */     } 
/*  630 */     return (Envelope)generalEnvelope1;
/*      */   }
/*      */   
/*      */   public static GeographicBoundingBox getGeographicBoundingBox(CoordinateReferenceSystem crs) {
/*      */     GeographicBoundingBoxImpl geographicBoundingBoxImpl1;
/*  650 */     GeographicBoundingBox bounds = null;
/*  651 */     GeographicBoundingBoxImpl merged = null;
/*  652 */     if (crs != null) {
/*  653 */       Extent domainOfValidity = crs.getDomainOfValidity();
/*  654 */       if (domainOfValidity != null)
/*  655 */         for (GeographicExtent extent : domainOfValidity.getGeographicElements()) {
/*  656 */           if (extent instanceof GeographicBoundingBox) {
/*  657 */             GeographicBoundingBox candidate = (GeographicBoundingBox)extent;
/*  658 */             if (bounds == null) {
/*  659 */               bounds = candidate;
/*      */               continue;
/*      */             } 
/*  661 */             if (merged == null)
/*  662 */               geographicBoundingBoxImpl1 = merged = new GeographicBoundingBoxImpl(bounds); 
/*  664 */             merged.add(candidate);
/*      */           } 
/*      */         }  
/*      */     } 
/*  670 */     return (GeographicBoundingBox)geographicBoundingBoxImpl1;
/*      */   }
/*      */   
/*      */   public static SingleCRS getHorizontalCRS(CoordinateReferenceSystem crs) {
/*  684 */     if (crs instanceof SingleCRS) {
/*  685 */       CoordinateSystem cs = crs.getCoordinateSystem();
/*  686 */       int dimension = cs.getDimension();
/*  687 */       if (dimension == 2) {
/*  693 */         CoordinateReferenceSystem base = crs;
/*  694 */         while (base instanceof GeneralDerivedCRS)
/*  695 */           base = ((GeneralDerivedCRS)base).getBaseCRS(); 
/*  698 */         if (base instanceof GeographicCRS)
/*  699 */           return (SingleCRS)crs; 
/*  702 */         if (base.getCoordinateSystem() instanceof org.opengis.referencing.cs.CartesianCS)
/*  703 */           return (SingleCRS)crs; 
/*  705 */       } else if (dimension >= 3 && crs instanceof GeographicCRS) {
/*  711 */         CoordinateSystemAxis axis0 = null, axis1 = null;
/*  712 */         int count = 0;
/*  713 */         for (int i = 0; i < dimension; i++) {
/*  714 */           CoordinateSystemAxis axis = cs.getAxis(i);
/*  715 */           if (DefaultCoordinateSystemAxis.isCompassDirection(axis.getDirection()))
/*  716 */             switch (count++) {
/*      */               case 0:
/*  717 */                 axis0 = axis;
/*      */                 break;
/*      */               case 1:
/*  718 */                 axis1 = axis;
/*      */                 break;
/*      */             }  
/*      */         } 
/*  723 */         if (count == 2) {
/*      */           DefaultEllipsoidalCS defaultEllipsoidalCS;
/*      */           DefaultGeographicCRS defaultGeographicCRS;
/*  724 */           GeodeticDatum datum = ((GeographicCRS)crs).getDatum();
/*  725 */           Map<String, ?> properties = CRSUtilities.changeDimensionInName((IdentifiedObject)cs, "3D", "2D");
/*      */           try {
/*  728 */             EllipsoidalCS horizontalCS = ReferencingFactoryFinder.getCSFactory(null).createEllipsoidalCS(properties, axis0, axis1);
/*  730 */           } catch (FactoryException e) {
/*  731 */             Logging.recoverableException(CRS.class, "getHorizontalCRS", (Throwable)e);
/*  732 */             defaultEllipsoidalCS = new DefaultEllipsoidalCS(properties, axis0, axis1);
/*      */           } 
/*  734 */           properties = CRSUtilities.changeDimensionInName((IdentifiedObject)crs, "3D", "2D");
/*      */           try {
/*  737 */             GeographicCRS horizontalCRS = ReferencingFactoryFinder.getCRSFactory(null).createGeographicCRS(properties, datum, (EllipsoidalCS)defaultEllipsoidalCS);
/*  739 */           } catch (FactoryException e) {
/*  740 */             Logging.recoverableException(CRS.class, "getHorizontalCRS", (Throwable)e);
/*  741 */             defaultGeographicCRS = new DefaultGeographicCRS(properties, datum, (EllipsoidalCS)defaultEllipsoidalCS);
/*      */           } 
/*  743 */           return (SingleCRS)defaultGeographicCRS;
/*      */         } 
/*      */       } 
/*      */     } 
/*  747 */     if (crs instanceof CompoundCRS) {
/*  748 */       CompoundCRS cp = (CompoundCRS)crs;
/*  749 */       for (CoordinateReferenceSystem c : cp.getCoordinateReferenceSystems()) {
/*  750 */         SingleCRS candidate = getHorizontalCRS(c);
/*  751 */         if (candidate != null)
/*  752 */           return candidate; 
/*      */       } 
/*      */     } 
/*  756 */     return null;
/*      */   }
/*      */   
/*      */   public static ProjectedCRS getProjectedCRS(CoordinateReferenceSystem crs) {
/*  769 */     if (crs instanceof ProjectedCRS)
/*  770 */       return (ProjectedCRS)crs; 
/*  772 */     if (crs instanceof CompoundCRS) {
/*  773 */       CompoundCRS cp = (CompoundCRS)crs;
/*  774 */       for (CoordinateReferenceSystem c : cp.getCoordinateReferenceSystems()) {
/*  775 */         ProjectedCRS candidate = getProjectedCRS(c);
/*  776 */         if (candidate != null)
/*  777 */           return candidate; 
/*      */       } 
/*      */     } 
/*  781 */     return null;
/*      */   }
/*      */   
/*      */   public static MapProjection getMapProjection(CoordinateReferenceSystem crs) {
/*  791 */     ProjectedCRS projectedCRS = getProjectedCRS(crs);
/*  792 */     if (projectedCRS == null)
/*  793 */       return null; 
/*  795 */     Projection conversion = projectedCRS.getConversionFromBase();
/*  796 */     MathTransform mt = conversion.getMathTransform();
/*  797 */     return unrollProjection(mt);
/*      */   }
/*      */   
/*      */   private static MapProjection unrollProjection(MathTransform mt) {
/*  801 */     if (mt instanceof MapProjection)
/*  802 */       return (MapProjection)mt; 
/*  803 */     if (mt instanceof ConcatenatedTransform) {
/*  804 */       ConcatenatedTransform ct = (ConcatenatedTransform)mt;
/*  805 */       MapProjection result = unrollProjection(ct.transform1);
/*  806 */       if (result == null)
/*  807 */         result = unrollProjection(ct.transform2); 
/*  809 */       return result;
/*      */     } 
/*  811 */     return null;
/*      */   }
/*      */   
/*      */   public static VerticalCRS getVerticalCRS(CoordinateReferenceSystem crs) {
/*  826 */     if (crs instanceof VerticalCRS)
/*  827 */       return (VerticalCRS)crs; 
/*  829 */     if (crs instanceof CompoundCRS) {
/*  830 */       CompoundCRS cp = (CompoundCRS)crs;
/*  831 */       for (CoordinateReferenceSystem c : cp.getCoordinateReferenceSystems()) {
/*  832 */         VerticalCRS candidate = getVerticalCRS(c);
/*  833 */         if (candidate != null)
/*  834 */           return candidate; 
/*      */       } 
/*      */     } 
/*  838 */     return null;
/*      */   }
/*      */   
/*      */   public static TemporalCRS getTemporalCRS(CoordinateReferenceSystem crs) {
/*  851 */     if (crs instanceof TemporalCRS)
/*  852 */       return (TemporalCRS)crs; 
/*  854 */     if (crs instanceof CompoundCRS) {
/*  855 */       CompoundCRS cp = (CompoundCRS)crs;
/*  856 */       for (CoordinateReferenceSystem c : cp.getCoordinateReferenceSystems()) {
/*  857 */         TemporalCRS candidate = getTemporalCRS(c);
/*  858 */         if (candidate != null)
/*  859 */           return candidate; 
/*      */       } 
/*      */     } 
/*  863 */     return null;
/*      */   }
/*      */   
/*      */   public static Ellipsoid getEllipsoid(CoordinateReferenceSystem crs) {
/*  876 */     Datum datum = CRSUtilities.getDatum(crs);
/*  877 */     if (datum instanceof GeodeticDatum)
/*  878 */       return ((GeodeticDatum)datum).getEllipsoid(); 
/*  880 */     if (crs instanceof CompoundCRS) {
/*  881 */       CompoundCRS cp = (CompoundCRS)crs;
/*  882 */       for (CoordinateReferenceSystem c : cp.getCoordinateReferenceSystems()) {
/*  883 */         Ellipsoid candidate = getEllipsoid(c);
/*  884 */         if (candidate != null)
/*  885 */           return candidate; 
/*      */       } 
/*      */     } 
/*  889 */     return null;
/*      */   }
/*      */   
/*      */   public static boolean equalsIgnoreMetadata(Object object1, Object object2) {
/*  904 */     if (object1 == object2)
/*  905 */       return true; 
/*  907 */     if (object1 instanceof AbstractIdentifiedObject && object2 instanceof AbstractIdentifiedObject)
/*  910 */       return ((AbstractIdentifiedObject)object1).equals((AbstractIdentifiedObject)object2, false); 
/*  913 */     return (object1 != null && object1.equals(object2));
/*      */   }
/*      */   
/*      */   public static String toSRS(CoordinateReferenceSystem crs) {
/*  938 */     if (crs == null)
/*  939 */       return null; 
/*  941 */     if (isForcedLonLat() && getAxisOrder(crs, false) == AxisOrder.NORTH_EAST)
/*      */       try {
/*  944 */         Integer code = lookupEpsgCode(crs, false);
/*  945 */         if (code != null)
/*  946 */           return "urn:ogc:def:crs:EPSG::" + code; 
/*  948 */       } catch (Exception e) {
/*  950 */         LOGGER.log(Level.FINE, "Failed to determine EPSG code", e);
/*      */       }  
/*  954 */     if (crs == DefaultGeographicCRS.WGS84)
/*  956 */       return "CRS:84"; 
/*  958 */     Set<ReferenceIdentifier> identifiers = crs.getIdentifiers();
/*  959 */     if (identifiers.isEmpty()) {
/*  961 */       ReferenceIdentifier referenceIdentifier = crs.getName();
/*  962 */       if (referenceIdentifier != null)
/*  963 */         return referenceIdentifier.toString(); 
/*  965 */       return null;
/*      */     } 
/*  968 */     for (ReferenceIdentifier identifier : crs.getIdentifiers()) {
/*  969 */       String srs = identifier.toString();
/*  970 */       if (srs.contains("EPSG:") || srs.contains("CRS:"))
/*  971 */         return srs; 
/*      */     } 
/*  975 */     ReferenceIdentifier name = crs.getName();
/*  976 */     if (name != null && (name.toString().contains("EPSG:") || name.toString().contains("CRS:")))
/*  978 */       return name.toString(); 
/*  981 */     return ((ReferenceIdentifier)identifiers.iterator().next()).toString();
/*      */   }
/*      */   
/*      */   public static String toSRS(CoordinateReferenceSystem crs, boolean codeOnly) {
/*  995 */     if (crs == null)
/*  995 */       return null; 
/*  996 */     String srsName = toSRS(crs);
/*  997 */     if (codeOnly && srsName != null) {
/* 1003 */       int index = srsName.lastIndexOf(':');
/* 1004 */       if (index > 0)
/* 1006 */         srsName = srsName.substring(index + 1).trim(); 
/* 1008 */       return srsName;
/*      */     } 
/* 1011 */     return srsName;
/*      */   }
/*      */   
/*      */   public static String lookupIdentifier(IdentifiedObject object, boolean fullScan) throws FactoryException {
/* 1047 */     AbstractAuthorityFactory xyFactory = (AbstractAuthorityFactory)getAuthorityFactory(true);
/* 1048 */     IdentifiedObjectFinder finder = xyFactory.getIdentifiedObjectFinder(object.getClass());
/* 1049 */     finder.setFullScanAllowed(fullScan);
/* 1050 */     return finder.findIdentifier(object);
/*      */   }
/*      */   
/*      */   public static String lookupIdentifier(Citation authority, CoordinateReferenceSystem crs, boolean fullScan) throws FactoryException {
/* 1081 */     ReferenceIdentifier id = AbstractIdentifiedObject.getIdentifier((IdentifiedObject)crs, authority);
/* 1082 */     if (id != null)
/* 1083 */       return id.getCode(); 
/* 1085 */     for (CRSAuthorityFactory factory : ReferencingFactoryFinder.getCRSAuthorityFactories(FORCE_LONGITUDE_FIRST_AXIS_ORDER)) {
/* 1088 */       if (!Citations.identifierMatches(factory.getAuthority(), authority))
/*      */         continue; 
/* 1091 */       if (factory == null || !(factory instanceof AbstractAuthorityFactory))
/*      */         continue; 
/* 1094 */       AbstractAuthorityFactory f = (AbstractAuthorityFactory)factory;
/* 1095 */       IdentifiedObjectFinder finder = f.getIdentifiedObjectFinder(crs.getClass());
/* 1096 */       finder.setFullScanAllowed(fullScan);
/* 1097 */       String code = finder.findIdentifier((IdentifiedObject)crs);
/* 1098 */       if (code != null)
/* 1099 */         return code; 
/*      */     } 
/* 1102 */     return null;
/*      */   }
/*      */   
/*      */   public static Integer lookupEpsgCode(CoordinateReferenceSystem crs, boolean fullScan) throws FactoryException {
/* 1120 */     String identifier = lookupIdentifier(Citations.EPSG, crs, fullScan);
/* 1121 */     if (identifier != null) {
/* 1122 */       int split = identifier.lastIndexOf(':');
/* 1123 */       String code = identifier.substring(split + 1);
/*      */       try {
/* 1127 */         return Integer.valueOf(Integer.parseInt(code));
/* 1128 */       } catch (NumberFormatException e) {
/* 1129 */         throw new FactoryException(Errors.format(67, identifier), e);
/*      */       } 
/*      */     } 
/* 1132 */     return null;
/*      */   }
/*      */   
/*      */   public static MathTransform findMathTransform(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws FactoryException {
/* 1172 */     return findMathTransform(sourceCRS, targetCRS, false);
/*      */   }
/*      */   
/*      */   public static MathTransform findMathTransform(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, boolean lenient) throws FactoryException {
/* 1199 */     if (equalsIgnoreMetadata(sourceCRS, targetCRS))
/* 1201 */       return (MathTransform)IdentityTransform.create(sourceCRS.getCoordinateSystem().getDimension()); 
/* 1203 */     CoordinateOperationFactory operationFactory = getCoordinateOperationFactory(lenient);
/* 1204 */     return operationFactory.createOperation(sourceCRS, targetCRS).getMathTransform();
/*      */   }
/*      */   
/*      */   public static Envelope transform(Envelope envelope, CoordinateReferenceSystem targetCRS) throws TransformException {
/*      */     GeneralEnvelope generalEnvelope;
/* 1231 */     if (envelope != null && targetCRS != null) {
/* 1232 */       CoordinateReferenceSystem sourceCRS = envelope.getCoordinateReferenceSystem();
/* 1233 */       if (sourceCRS != null) {
/* 1234 */         if (!equalsIgnoreMetadata(sourceCRS, targetCRS)) {
/*      */           CoordinateOperation operation;
/* 1235 */           CoordinateOperationFactory factory = getCoordinateOperationFactory(true);
/*      */           try {
/* 1238 */             operation = factory.createOperation(sourceCRS, targetCRS);
/* 1239 */           } catch (FactoryException exception) {
/* 1240 */             throw new TransformException(Errors.format(33), exception);
/*      */           } 
/* 1243 */           if (!operation.getMathTransform().isIdentity()) {
/* 1244 */             generalEnvelope = transform(operation, envelope);
/* 1245 */           } else if (!equalsIgnoreMetadata(generalEnvelope.getCoordinateReferenceSystem(), targetCRS)) {
/* 1246 */             GeneralEnvelope tx = new GeneralEnvelope((Envelope)generalEnvelope);
/* 1247 */             tx.setCoordinateReferenceSystem(targetCRS);
/* 1248 */             generalEnvelope = tx;
/*      */           } 
/*      */         } 
/* 1251 */         assert equalsIgnoreMetadata(generalEnvelope.getCoordinateReferenceSystem(), targetCRS);
/*      */       } 
/*      */     } 
/* 1254 */     return (Envelope)generalEnvelope;
/*      */   }
/*      */   
/*      */   public static GeneralEnvelope transform(MathTransform transform, Envelope envelope) throws TransformException {
/* 1279 */     return transform(transform, envelope, (GeneralDirectPosition)null);
/*      */   }
/*      */   
/*      */   private static GeneralEnvelope transform(MathTransform transform, Envelope envelope, GeneralDirectPosition targetPt) throws TransformException {
/* 1292 */     if (envelope == null)
/* 1293 */       return null; 
/* 1295 */     if (transform.isIdentity()) {
/* 1303 */       GeneralEnvelope e = new GeneralEnvelope(envelope);
/* 1304 */       e.setCoordinateReferenceSystem(null);
/* 1305 */       if (targetPt != null)
/* 1306 */         for (int j = envelope.getDimension(); --j >= 0;)
/* 1307 */           targetPt.setOrdinate(j, e.getMedian(j));  
/* 1310 */       return e;
/*      */     } 
/* 1315 */     int sourceDim = transform.getSourceDimensions();
/* 1316 */     if (envelope.getDimension() != sourceDim)
/* 1317 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(sourceDim), Integer.valueOf(envelope.getDimension()))); 
/* 1320 */     int coordinateNumber = 0;
/* 1321 */     GeneralEnvelope transformed = null;
/* 1322 */     if (targetPt == null)
/* 1323 */       targetPt = new GeneralDirectPosition(transform.getTargetDimensions()); 
/* 1329 */     GeneralDirectPosition sourcePt = new GeneralDirectPosition(sourceDim);
/* 1330 */     for (int i = sourceDim; --i >= 0;)
/* 1331 */       sourcePt.setOrdinate(i, envelope.getMinimum(i)); 
/*      */     while (true) {
/* 1338 */       if (targetPt != transform.transform((DirectPosition)sourcePt, (DirectPosition)targetPt))
/* 1339 */         throw new UnsupportedImplementationException(transform.getClass()); 
/* 1341 */       if (transformed != null) {
/* 1342 */         transformed.add((DirectPosition)targetPt);
/*      */       } else {
/* 1344 */         transformed = new GeneralEnvelope(targetPt, targetPt);
/*      */       } 
/* 1356 */       int n = ++coordinateNumber;
/*      */       int j;
/* 1357 */       for (j = sourceDim; --j >= 0; ) {
/* 1358 */         switch (n % 5) {
/*      */           case 0:
/* 1359 */             sourcePt.setOrdinate(j, envelope.getMinimum(j));
/* 1359 */             n /= 5;
/*      */             continue;
/*      */           case 1:
/* 1360 */             sourcePt.setOrdinate(j, envelope.getMaximum(j));
/*      */             continue;
/*      */           case 2:
/* 1361 */             sourcePt.setOrdinate(j, (envelope.getMinimum(j) + envelope.getMedian(j)) / 2.0D);
/*      */             continue;
/*      */           case 3:
/* 1362 */             sourcePt.setOrdinate(j, (envelope.getMedian(j) + envelope.getMaximum(j)) / 2.0D);
/*      */             continue;
/*      */           case 4:
/* 1363 */             sourcePt.setOrdinate(j, envelope.getMedian(j));
/*      */             continue;
/*      */         } 
/* 1364 */         throw new AssertionError(n);
/*      */       } 
/*      */       break;
/*      */     } 
/* 1369 */     return transformed;
/*      */   }
/*      */   
/*      */   public static GeneralEnvelope transform(CoordinateOperation operation, Envelope envelope) throws TransformException {
/* 1393 */     if (envelope == null)
/* 1394 */       return null; 
/* 1396 */     CoordinateReferenceSystem sourceCRS = operation.getSourceCRS();
/* 1397 */     if (sourceCRS != null) {
/* 1398 */       CoordinateReferenceSystem crs = envelope.getCoordinateReferenceSystem();
/* 1399 */       if (crs != null && !equalsIgnoreMetadata(crs, sourceCRS))
/* 1400 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*      */     } 
/* 1404 */     MathTransform mt = operation.getMathTransform();
/* 1405 */     GeneralDirectPosition centerPt = new GeneralDirectPosition(mt.getTargetDimensions());
/* 1406 */     GeneralEnvelope transformed = transform(mt, envelope, centerPt);
/* 1415 */     if (sourceCRS != null) {
/* 1416 */       CoordinateSystem cs = sourceCRS.getCoordinateSystem();
/* 1417 */       if (cs != null) {
/* 1418 */         DirectPosition directPosition1 = null;
/* 1419 */         DirectPosition directPosition2 = null;
/* 1420 */         int j = cs.getDimension();
/* 1421 */         for (int k = 0; k < j; k++) {
/* 1422 */           CoordinateSystemAxis axis = cs.getAxis(k);
/* 1423 */           if (axis != null) {
/* 1426 */             double min = envelope.getMinimum(k);
/* 1427 */             double max = envelope.getMaximum(k);
/* 1428 */             double v1 = axis.getMinimumValue();
/* 1429 */             double v2 = axis.getMaximumValue();
/* 1430 */             boolean b1 = (v1 > min && v1 < max);
/* 1431 */             boolean b2 = (v2 > min && v2 < max);
/* 1432 */             if (b1 || b2) {
/*      */               GeneralDirectPosition generalDirectPosition;
/* 1435 */               if (directPosition1 == null) {
/* 1436 */                 generalDirectPosition = new GeneralDirectPosition(j);
/* 1437 */                 for (int m = 0; m < j; m++)
/* 1438 */                   generalDirectPosition.setOrdinate(m, envelope.getMedian(m)); 
/*      */               } 
/* 1441 */               if (b1) {
/* 1442 */                 generalDirectPosition.setOrdinate(k, v1);
/* 1443 */                 transformed.add(directPosition2 = mt.transform((DirectPosition)generalDirectPosition, directPosition2));
/*      */               } 
/* 1445 */               if (b2) {
/* 1446 */                 generalDirectPosition.setOrdinate(k, v2);
/* 1447 */                 transformed.add(directPosition2 = mt.transform((DirectPosition)generalDirectPosition, directPosition2));
/*      */               } 
/* 1449 */               generalDirectPosition.setOrdinate(k, envelope.getMedian(k));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1459 */     CoordinateReferenceSystem targetCRS = operation.getTargetCRS();
/* 1460 */     if (targetCRS == null)
/* 1461 */       return transformed; 
/* 1463 */     GeneralEnvelope generalEnvelope = toGeneralEnvelope(envelope);
/* 1464 */     MapProjection sourceProjection = getMapProjection(sourceCRS);
/* 1465 */     if (sourceProjection instanceof PolarStereographic) {
/* 1466 */       PolarStereographic ps = (PolarStereographic)sourceProjection;
/* 1467 */       ParameterValue<?> fe = ps.getParameterValues().parameter(MapProjection.AbstractProvider.FALSE_EASTING.getName().getCode());
/* 1469 */       double originX = fe.doubleValue();
/* 1470 */       ParameterValue<?> fn = ps.getParameterValues().parameter(MapProjection.AbstractProvider.FALSE_NORTHING.getName().getCode());
/* 1472 */       double originY = fn.doubleValue();
/* 1473 */       DirectPosition2D origin = new DirectPosition2D(originX, originY);
/* 1474 */       if (generalEnvelope.contains((DirectPosition)origin)) {
/* 1475 */         if (targetCRS instanceof GeographicCRS) {
/* 1476 */           DirectPosition lowerCorner = transformed.getLowerCorner();
/* 1477 */           if (getAxisOrder(targetCRS) == AxisOrder.NORTH_EAST) {
/* 1478 */             lowerCorner.setOrdinate(1, -180.0D);
/* 1479 */             transformed.add(lowerCorner);
/* 1480 */             lowerCorner.setOrdinate(1, 180.0D);
/* 1481 */             transformed.add(lowerCorner);
/*      */           } else {
/* 1483 */             lowerCorner.setOrdinate(0, -180.0D);
/* 1484 */             transformed.add(lowerCorner);
/* 1485 */             lowerCorner.setOrdinate(0, 180.0D);
/* 1486 */             transformed.add(lowerCorner);
/*      */           } 
/*      */         } else {
/* 1493 */           DirectPosition lc = transformed.getLowerCorner();
/* 1494 */           DirectPosition uc = transformed.getUpperCorner();
/* 1495 */           for (int j = -180; j < 180; j++) {
/* 1496 */             expandEnvelopeByLongitude(j, lc, transformed, targetCRS);
/* 1497 */             expandEnvelopeByLongitude(j, uc, transformed, targetCRS);
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1504 */         if (generalEnvelope.getMinimum(0) < originX && generalEnvelope.getMaximum(0) > originX) {
/* 1506 */           DirectPosition lc = generalEnvelope.getLowerCorner();
/* 1507 */           lc.setOrdinate(0, originX);
/* 1508 */           mt.transform(lc, lc);
/* 1509 */           transformed.add(lc);
/* 1510 */           DirectPosition uc = generalEnvelope.getUpperCorner();
/* 1511 */           uc.setOrdinate(0, originX);
/* 1512 */           mt.transform(uc, uc);
/* 1513 */           transformed.add(uc);
/*      */         } 
/* 1515 */         if (generalEnvelope.getMinimum(1) < originY && generalEnvelope.getMaximum(1) > originY) {
/* 1517 */           DirectPosition lc = generalEnvelope.getLowerCorner();
/* 1518 */           lc.setOrdinate(1, originY);
/* 1519 */           mt.transform(lc, lc);
/* 1520 */           transformed.add(lc);
/* 1521 */           DirectPosition uc = generalEnvelope.getUpperCorner();
/* 1522 */           uc.setOrdinate(1, originY);
/* 1523 */           mt.transform(uc, uc);
/* 1524 */           transformed.add(uc);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1533 */     transformed.setCoordinateReferenceSystem(targetCRS);
/* 1534 */     CoordinateSystem targetCS = targetCRS.getCoordinateSystem();
/* 1535 */     if (targetCS == null)
/* 1537 */       return transformed; 
/* 1571 */     DirectPosition sourcePt = null;
/* 1572 */     DirectPosition targetPt = null;
/* 1573 */     int dimension = targetCS.getDimension();
/* 1574 */     for (int i = 0; i < dimension; i++) {
/* 1575 */       CoordinateSystemAxis axis = targetCS.getAxis(i);
/* 1576 */       if (axis != null) {
/* 1579 */         boolean testMax = false;
/*      */         while (true) {
/*      */           GeneralDirectPosition generalDirectPosition;
/* 1581 */           double extremum = testMax ? axis.getMaximumValue() : axis.getMinimumValue();
/* 1582 */           if (!Double.isInfinite(extremum) && !Double.isNaN(extremum)) {
/* 1590 */             if (targetPt == null) {
/*      */               try {
/* 1592 */                 mt = mt.inverse();
/* 1593 */               } catch (NoninvertibleTransformException exception) {
/* 1605 */                 if (dimension >= mt.getSourceDimensions())
/* 1606 */                   unexpectedException("transform", (Exception)exception); 
/* 1608 */                 return transformed;
/*      */               } 
/* 1610 */               generalDirectPosition = new GeneralDirectPosition(mt.getSourceDimensions());
/* 1611 */               for (int j = 0; j < dimension; j++)
/* 1612 */                 generalDirectPosition.setOrdinate(j, centerPt.getOrdinate(j)); 
/*      */             } 
/* 1615 */             generalDirectPosition.setOrdinate(i, extremum);
/*      */             try {
/* 1617 */               sourcePt = mt.transform((DirectPosition)generalDirectPosition, sourcePt);
/* 1618 */             } catch (TransformException e) {}
/* 1626 */             if (generalEnvelope.contains(sourcePt))
/* 1627 */               transformed.add((DirectPosition)generalDirectPosition); 
/*      */           } 
/* 1629 */           if ((testMax = !testMax) != true) {
/* 1630 */             if (generalDirectPosition != null)
/* 1631 */               generalDirectPosition.setOrdinate(i, centerPt.getOrdinate(i)); 
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1634 */     return transformed;
/*      */   }
/*      */   
/*      */   private static void expandEnvelopeByLongitude(double longitude, DirectPosition input, GeneralEnvelope transformed, CoordinateReferenceSystem sourceCRS) {
/*      */     try {
/* 1640 */       MathTransform mt = findMathTransform(sourceCRS, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/* 1642 */       DirectPosition2D pos = new DirectPosition2D(sourceCRS);
/* 1643 */       mt.transform(input, (DirectPosition)pos);
/* 1644 */       pos.setOrdinate(0, longitude);
/* 1645 */       mt.inverse().transform((DirectPosition)pos, (DirectPosition)pos);
/* 1646 */       transformed.add((DirectPosition)pos);
/* 1647 */     } catch (Exception e) {
/* 1648 */       LOGGER.log(Level.FINER, "Tried to expand target envelope to include longitude " + longitude + " but failed. This is not necesseraly and issue, " + "this is a best effort attempt to handle the polar stereographic " + "pole singularity during reprojection", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static GeneralEnvelope toGeneralEnvelope(Envelope envelope) {
/*      */     GeneralEnvelope generalEnvelope;
/* 1659 */     if (envelope instanceof GeneralEnvelope) {
/* 1660 */       generalEnvelope = (GeneralEnvelope)envelope;
/*      */     } else {
/* 1662 */       generalEnvelope = new GeneralEnvelope(envelope);
/*      */     } 
/* 1664 */     return generalEnvelope;
/*      */   }
/*      */   
/*      */   public static Rectangle2D transform(MathTransform2D transform, Rectangle2D envelope, Rectangle2D destination) throws TransformException {
/* 1699 */     return transform(transform, envelope, destination, new Point2D.Double());
/*      */   }
/*      */   
/*      */   private static Rectangle2D transform(MathTransform2D transform, Rectangle2D envelope, Rectangle2D destination, Point2D.Double point) throws TransformException {
/*      */     XRectangle2D xRectangle2D;
/* 1714 */     if (envelope == null)
/* 1715 */       return null; 
/* 1717 */     double xmin = Double.POSITIVE_INFINITY;
/* 1718 */     double ymin = Double.POSITIVE_INFINITY;
/* 1719 */     double xmax = Double.NEGATIVE_INFINITY;
/* 1720 */     double ymax = Double.NEGATIVE_INFINITY;
/* 1721 */     for (int i = 0; i <= 8; i++) {
/* 1731 */       point.x = ((i & 0x1) == 0) ? envelope.getMinX() : envelope.getMaxX();
/* 1732 */       point.y = ((i & 0x2) == 0) ? envelope.getMinY() : envelope.getMaxY();
/* 1733 */       switch (i) {
/*      */         case 5:
/*      */         case 6:
/* 1735 */           point.x = envelope.getCenterX();
/*      */           break;
/*      */         case 8:
/* 1736 */           point.x = envelope.getCenterX();
/*      */         case 4:
/*      */         case 7:
/* 1738 */           point.y = envelope.getCenterY();
/*      */           break;
/*      */       } 
/* 1740 */       if (point != transform.transform(point, point))
/* 1741 */         throw new UnsupportedImplementationException(transform.getClass()); 
/* 1743 */       if (point.x < xmin)
/* 1743 */         xmin = point.x; 
/* 1744 */       if (point.x > xmax)
/* 1744 */         xmax = point.x; 
/* 1745 */       if (point.y < ymin)
/* 1745 */         ymin = point.y; 
/* 1746 */       if (point.y > ymax)
/* 1746 */         ymax = point.y; 
/*      */     } 
/* 1748 */     if (destination != null) {
/* 1749 */       destination.setRect(xmin, ymin, xmax - xmin, ymax - ymin);
/*      */     } else {
/* 1751 */       xRectangle2D = XRectangle2D.createFromExtremums(xmin, ymin, xmax, ymax);
/*      */     } 
/* 1757 */     assert XRectangle2D.equalsEpsilon((Rectangle2D)xRectangle2D, transform((MathTransform)transform, (Envelope)new Envelope2D(null, envelope)).toRectangle2D()) : xRectangle2D;
/* 1758 */     return (Rectangle2D)xRectangle2D;
/*      */   }
/*      */   
/*      */   public static Rectangle2D transform(CoordinateOperation operation, Rectangle2D envelope, Rectangle2D destination) throws TransformException {
/* 1790 */     if (envelope == null)
/* 1791 */       return null; 
/* 1794 */     GeneralEnvelope result = transform(operation, (Envelope)new GeneralEnvelope(envelope));
/* 1795 */     if (destination == null)
/* 1796 */       return result.toRectangle2D(); 
/* 1798 */     destination.setFrame(result.getMinimum(0), result.getMinimum(1), result.getSpan(0), result.getSpan(1));
/* 1800 */     return destination;
/*      */   }
/*      */   
/*      */   static void unexpectedException(String methodName, Exception exception) {
/* 1810 */     Logging.unexpectedException(CRS.class, methodName, exception);
/*      */   }
/*      */   
/*      */   public static void reset(String aspects) {
/* 1830 */     StringTokenizer tokens = new StringTokenizer(aspects, ", \t\n\r\f");
/* 1831 */     while (tokens.hasMoreTokens()) {
/* 1832 */       String aspect = tokens.nextToken().trim();
/* 1833 */       boolean all = aspect.equalsIgnoreCase("all");
/* 1834 */       if (all || aspect.equalsIgnoreCase("plugins")) {
/* 1835 */         ReferencingFactoryFinder.reset();
/* 1836 */         ReferencingFactoryFinder.scanForPlugins();
/*      */       } 
/* 1838 */       if (all || aspect.equalsIgnoreCase("warnings"))
/* 1839 */         MapProjection.resetWarnings(); 
/*      */     } 
/* 1842 */     FORCED_LON_LAT = null;
/* 1843 */     defaultFactory = null;
/* 1844 */     xyFactory = null;
/* 1845 */     strictFactory = null;
/* 1846 */     lenientFactory = null;
/*      */   }
/*      */   
/*      */   public static void cleanupThreadLocals() {
/* 1854 */     DefaultMathTransformFactory.cleanupThreadLocals();
/* 1855 */     Formattable.cleanupThreadLocals();
/*      */   }
/*      */   
/*      */   public static AxisOrder getAxisOrder(CoordinateReferenceSystem crs) {
/* 1870 */     return getAxisOrder(crs, false);
/*      */   }
/*      */   
/*      */   public static AxisOrder getAxisOrder(CoordinateReferenceSystem crs, boolean useBaseGeoCRS) {
/* 1890 */     CoordinateSystem cs = null;
/* 1892 */     if (crs instanceof ProjectedCRS) {
/* 1893 */       cs = !useBaseGeoCRS ? crs.getCoordinateSystem() : (CoordinateSystem)((ProjectedCRS)crs).getBaseCRS().getCoordinateSystem();
/* 1896 */     } else if (crs instanceof GeographicCRS) {
/* 1897 */       cs = crs.getCoordinateSystem();
/*      */     } else {
/* 1900 */       return AxisOrder.INAPPLICABLE;
/*      */     } 
/* 1903 */     int dimension = cs.getDimension();
/* 1904 */     int longitudeDim = -1;
/* 1905 */     int latitudeDim = -1;
/* 1907 */     for (int i = 0; i < dimension; i++) {
/* 1908 */       AxisDirection dir = cs.getAxis(i).getDirection().absolute();
/* 1910 */       if (dir.equals(AxisDirection.EAST))
/* 1911 */         longitudeDim = i; 
/* 1914 */       if (dir.equals(AxisDirection.NORTH))
/* 1915 */         latitudeDim = i; 
/*      */     } 
/* 1919 */     if (longitudeDim >= 0 && latitudeDim >= 0) {
/* 1920 */       if (longitudeDim < latitudeDim)
/* 1921 */         return AxisOrder.EAST_NORTH; 
/* 1923 */       return AxisOrder.NORTH_EAST;
/*      */     } 
/* 1927 */     return AxisOrder.INAPPLICABLE;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/* 2036 */     Command.execute(args);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\CRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */