/*      */ package org.geotools.referencing;
/*      */ 
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.text.Format;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import javax.measure.unit.NonSI;
/*      */ import org.geotools.geometry.DirectPosition2D;
/*      */ import org.geotools.geometry.GeneralDirectPosition;
/*      */ import org.geotools.geometry.TransformedDirectPosition;
/*      */ import org.geotools.io.TableWriter;
/*      */ import org.geotools.measure.Angle;
/*      */ import org.geotools.measure.CoordinateFormat;
/*      */ import org.geotools.measure.Latitude;
/*      */ import org.geotools.measure.Longitude;
/*      */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*      */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*      */ import org.geotools.referencing.datum.DefaultEllipsoid;
/*      */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*      */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*      */ import org.geotools.resources.CRSUtilities;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.resources.i18n.Vocabulary;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.geometry.DirectPosition;
/*      */ import org.opengis.geometry.coordinate.Position;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.datum.Datum;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ 
/*      */ public class GeodeticCalculator {
/*      */   private static final double TOLERANCE_0 = 5.0E-15D;
/*      */   
/*      */   private static final double TOLERANCE_1 = 5.0E-14D;
/*      */   
/*      */   private static final double TOLERANCE_2 = 5.0E-13D;
/*      */   
/*      */   private static final double TOLERANCE_3 = 0.007D;
/*      */   
/*      */   private static final double TOLERANCE_CHECK = 1.0E-8D;
/*      */   
/*      */   private final TransformedDirectPosition userToGeodetic;
/*      */   
/*      */   private CoordinateReferenceSystem coordinateReferenceSystem;
/*      */   
/*      */   private GeographicCRS geographicCRS;
/*      */   
/*      */   private final Ellipsoid ellipsoid;
/*      */   
/*      */   private final double semiMajorAxis;
/*      */   
/*      */   private final double semiMinorAxis;
/*      */   
/*      */   private final double eccentricitySquared;
/*      */   
/*      */   private final double maxOrthodromicDistance;
/*      */   
/*      */   private final double A;
/*      */   
/*      */   private final double B;
/*      */   
/*      */   private final double C;
/*      */   
/*      */   private final double D;
/*      */   
/*      */   private final double E;
/*      */   
/*      */   private final double F;
/*      */   
/*      */   private final double fo;
/*      */   
/*      */   private final double f;
/*      */   
/*      */   private final double f2;
/*      */   
/*      */   private final double f3;
/*      */   
/*      */   private final double f4;
/*      */   
/*      */   private final double T1;
/*      */   
/*      */   private final double T2;
/*      */   
/*      */   private final double T4;
/*      */   
/*      */   private final double T6;
/*      */   
/*      */   private final double a01;
/*      */   
/*      */   private final double a02;
/*      */   
/*      */   private final double a03;
/*      */   
/*      */   private final double a21;
/*      */   
/*      */   private final double a22;
/*      */   
/*      */   private final double a23;
/*      */   
/*      */   private final double a42;
/*      */   
/*      */   private final double a43;
/*      */   
/*      */   private final double a63;
/*      */   
/*      */   private double lat1;
/*      */   
/*      */   private double long1;
/*      */   
/*      */   private double lat2;
/*      */   
/*      */   private double long2;
/*      */   
/*      */   private double distance;
/*      */   
/*      */   private double azimuth;
/*      */   
/*      */   private boolean destinationValid;
/*      */   
/*      */   private boolean directionValid;
/*      */   
/*      */   private boolean antipodal;
/*      */   
/*      */   public GeodeticCalculator() {
/*  227 */     this((Ellipsoid)DefaultEllipsoid.WGS84);
/*      */   }
/*      */   
/*      */   public GeodeticCalculator(Ellipsoid ellipsoid) {
/*  237 */     this(ellipsoid, null);
/*      */   }
/*      */   
/*      */   public GeodeticCalculator(CoordinateReferenceSystem crs) {
/*  249 */     this(CRS.getEllipsoid(crs), crs);
/*      */   }
/*      */   
/*      */   private GeodeticCalculator(Ellipsoid ellipsoid, CoordinateReferenceSystem crs) {
/*  256 */     if (ellipsoid == null)
/*  257 */       throw new IllegalArgumentException(Errors.format(143, "ellipsoid")); 
/*  259 */     this.ellipsoid = ellipsoid;
/*  260 */     this.semiMajorAxis = ellipsoid.getSemiMajorAxis();
/*  261 */     this.semiMinorAxis = ellipsoid.getSemiMinorAxis();
/*  262 */     if (crs != null) {
/*  263 */       this.coordinateReferenceSystem = crs;
/*  264 */       this.geographicCRS = getGeographicCRS(crs);
/*  272 */       this.userToGeodetic = new TransformedDirectPosition(crs, (CoordinateReferenceSystem)this.geographicCRS, null);
/*      */     } else {
/*  274 */       this.userToGeodetic = null;
/*      */     } 
/*  278 */     this.f = (this.semiMajorAxis - this.semiMinorAxis) / this.semiMajorAxis;
/*  279 */     this.fo = 1.0D - this.f;
/*  280 */     this.f2 = this.f * this.f;
/*  281 */     this.f3 = this.f * this.f2;
/*  282 */     this.f4 = this.f * this.f3;
/*  283 */     this.eccentricitySquared = this.f * (2.0D - this.f);
/*  286 */     double E2 = this.eccentricitySquared;
/*  287 */     double E4 = E2 * E2;
/*  288 */     double E6 = E4 * E2;
/*  289 */     double E8 = E6 * E2;
/*  290 */     double EX = E8 * E2;
/*  292 */     this.A = 1.0D + 0.75D * E2 + 0.703125D * E4 + 0.68359375D * E6 + 0.67291259765625D * E8 + 0.6661834716796875D * EX;
/*  293 */     this.B = 0.75D * E2 + 0.9375D * E4 + 1.025390625D * E6 + 1.07666015625D * E8 + 1.1103057861328125D * EX;
/*  294 */     this.C = 0.234375D * E4 + 0.41015625D * E6 + 0.538330078125D * E8 + 0.63446044921875D * EX;
/*  295 */     this.D = 0.068359375D * E6 + 0.15380859375D * E8 + 0.23792266845703125D * EX;
/*  296 */     this.E = 0.01922607421875D * E8 + 0.0528717041015625D * EX;
/*  297 */     this.F = 0.00528717041015625D * EX;
/*  299 */     this.maxOrthodromicDistance = this.semiMajorAxis * (1.0D - E2) * Math.PI * this.A - 1.0D;
/*  301 */     this.T1 = 1.0D;
/*  302 */     this.T2 = -0.25D * this.f * (1.0D + this.f + this.f2);
/*  303 */     this.T4 = 0.1875D * this.f2 * (1.0D + 2.25D * this.f);
/*  304 */     this.T6 = 0.1953125D * this.f3;
/*  306 */     double a = this.f3 * (1.0D + 2.25D * this.f);
/*  307 */     this.a01 = -this.f2 * (1.0D + this.f + this.f2) / 4.0D;
/*  308 */     this.a02 = 0.1875D * a;
/*  309 */     this.a03 = -0.1953125D * this.f4;
/*  310 */     this.a21 = -this.a01;
/*  311 */     this.a22 = -0.25D * a;
/*  312 */     this.a23 = 0.29296875D * this.f4;
/*  313 */     this.a42 = 0.03125D * a;
/*  314 */     this.a43 = 0.05859375D * this.f4;
/*  315 */     this.a63 = 5.0D * this.f4 / 768.0D;
/*      */   }
/*      */   
/*      */   private static GeographicCRS getGeographicCRS(CoordinateReferenceSystem crs) {
/*  332 */     if (crs instanceof GeographicCRS) {
/*  333 */       CoordinateSystem cs = crs.getCoordinateSystem();
/*  334 */       if (cs.getDimension() == 2 && isStandard(cs.getAxis(0), AxisDirection.EAST) && isStandard(cs.getAxis(1), AxisDirection.NORTH))
/*  338 */         return (GeographicCRS)crs; 
/*      */     } 
/*  341 */     Datum datum = CRSUtilities.getDatum(crs);
/*  342 */     if (datum instanceof GeodeticDatum)
/*  343 */       return (GeographicCRS)new DefaultGeographicCRS("Geodetic", (GeodeticDatum)datum, (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_2D); 
/*  346 */     if (crs instanceof CompoundCRS)
/*  347 */       for (CoordinateReferenceSystem component : ((CompoundCRS)crs).getCoordinateReferenceSystems()) {
/*  348 */         GeographicCRS candidate = getGeographicCRS(component);
/*  349 */         if (candidate != null)
/*  350 */           return candidate; 
/*      */       }  
/*  354 */     throw new IllegalArgumentException(Errors.format(62));
/*      */   }
/*      */   
/*      */   private static boolean isStandard(CoordinateSystemAxis axis, AxisDirection direction) {
/*  362 */     return (direction.equals(axis.getDirection()) && NonSI.DEGREE_ANGLE.equals(axis.getUnit()));
/*      */   }
/*      */   
/*      */   private static double castToAngleRange(double alpha) {
/*  373 */     return alpha - 6.283185307179586D * Math.floor(alpha / 6.283185307179586D + 0.5D);
/*      */   }
/*      */   
/*      */   private static double checkLatitude(double latitude) throws IllegalArgumentException {
/*  386 */     if (latitude >= -90.0D && latitude <= 90.0D)
/*  387 */       return Math.toRadians(latitude); 
/*  389 */     throw new IllegalArgumentException(Errors.format(85, new Latitude(latitude)));
/*      */   }
/*      */   
/*      */   private static double checkLongitude(double longitude) throws IllegalArgumentException {
/*  403 */     if (longitude >= -180.0D && longitude <= 180.0D)
/*  404 */       return Math.toRadians(longitude); 
/*  406 */     throw new IllegalArgumentException(Errors.format(88, new Longitude(longitude)));
/*      */   }
/*      */   
/*      */   private static double checkAzimuth(double azimuth) throws IllegalArgumentException {
/*  420 */     if (azimuth >= -180.0D && azimuth <= 180.0D)
/*  421 */       return Math.toRadians(azimuth); 
/*  423 */     throw new IllegalArgumentException(Errors.format(6, new Longitude(azimuth)));
/*      */   }
/*      */   
/*      */   private void checkOrthodromicDistance(double distance) throws IllegalArgumentException {
/*  438 */     if (distance < 0.0D || distance > this.maxOrthodromicDistance)
/*  439 */       throw new IllegalArgumentException(Errors.format(43, Double.valueOf(distance), Double.valueOf(0.0D), Double.valueOf(this.maxOrthodromicDistance), this.ellipsoid.getAxisUnit())); 
/*      */   }
/*      */   
/*      */   private static void checkNumberOfPoints(int numberOfPoints) throws IllegalArgumentException {
/*  454 */     if (numberOfPoints < 0)
/*  455 */       throw new IllegalArgumentException(Errors.format(58, "numberOfPoints", Integer.valueOf(numberOfPoints))); 
/*      */   }
/*      */   
/*      */   private String getNoConvergenceErrorMessage() {
/*  465 */     CoordinateFormat cf = new CoordinateFormat();
/*  466 */     return Errors.format(130, format((Format)cf, this.long1, this.lat1), format((Format)cf, this.long2, this.lat2));
/*      */   }
/*      */   
/*      */   private static String format(Format cf, double longitude, double latitude) {
/*  475 */     return cf.format(new GeneralDirectPosition(Math.toDegrees(longitude), Math.toDegrees(latitude)));
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/*  497 */     if (this.coordinateReferenceSystem == null)
/*  498 */       this.coordinateReferenceSystem = (CoordinateReferenceSystem)getGeographicCRS(); 
/*  500 */     return this.coordinateReferenceSystem;
/*      */   }
/*      */   
/*      */   public GeographicCRS getGeographicCRS() {
/*  513 */     if (this.geographicCRS == null) {
/*  514 */       String name = Vocabulary.format(83);
/*  515 */       this.geographicCRS = (GeographicCRS)new DefaultGeographicCRS(name, (GeodeticDatum)new DefaultGeodeticDatum(name, getEllipsoid(), (PrimeMeridian)DefaultPrimeMeridian.GREENWICH), (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_2D);
/*      */     } 
/*  519 */     return this.geographicCRS;
/*      */   }
/*      */   
/*      */   public Ellipsoid getEllipsoid() {
/*  528 */     return this.ellipsoid;
/*      */   }
/*      */   
/*      */   public void setStartingGeographicPoint(double longitude, double latitude) throws IllegalArgumentException {
/*  549 */     longitude = checkLongitude(longitude);
/*  550 */     latitude = checkLatitude(latitude);
/*  552 */     this.long1 = longitude;
/*  553 */     this.lat1 = latitude;
/*  554 */     this.destinationValid = false;
/*  555 */     this.directionValid = false;
/*      */   }
/*      */   
/*      */   public void setStartingGeographicPoint(Point2D point) throws IllegalArgumentException {
/*  572 */     setStartingGeographicPoint(point.getX(), point.getY());
/*      */   }
/*      */   
/*      */   public void setStartingPosition(Position position) throws TransformException {
/*      */     TransformedDirectPosition transformedDirectPosition;
/*  586 */     DirectPosition p = position.getDirectPosition();
/*  587 */     if (this.userToGeodetic != null) {
/*  588 */       this.userToGeodetic.transform(p);
/*  589 */       transformedDirectPosition = this.userToGeodetic;
/*      */     } 
/*  591 */     setStartingGeographicPoint(transformedDirectPosition.getOrdinate(0), transformedDirectPosition.getOrdinate(1));
/*      */   }
/*      */   
/*      */   public Point2D getStartingGeographicPoint() {
/*  604 */     return new Point2D.Double(Math.toDegrees(this.long1), Math.toDegrees(this.lat1));
/*      */   }
/*      */   
/*      */   public DirectPosition getStartingPosition() throws TransformException {
/*      */     DirectPosition2D directPosition2D;
/*      */     DirectPosition directPosition;
/*  618 */     TransformedDirectPosition transformedDirectPosition = this.userToGeodetic;
/*  619 */     if (transformedDirectPosition == null)
/*  620 */       directPosition2D = new DirectPosition2D(); 
/*  622 */     directPosition2D.setOrdinate(0, Math.toDegrees(this.long1));
/*  623 */     directPosition2D.setOrdinate(1, Math.toDegrees(this.lat1));
/*  624 */     if (this.userToGeodetic != null)
/*  625 */       directPosition = this.userToGeodetic.inverseTransform(); 
/*  627 */     return directPosition;
/*      */   }
/*      */   
/*      */   public void setDestinationGeographicPoint(double longitude, double latitude) throws IllegalArgumentException {
/*  646 */     longitude = checkLongitude(longitude);
/*  647 */     latitude = checkLatitude(latitude);
/*  649 */     this.long2 = longitude;
/*  650 */     this.lat2 = latitude;
/*  651 */     this.destinationValid = true;
/*  652 */     this.directionValid = false;
/*      */   }
/*      */   
/*      */   public void setDestinationGeographicPoint(Point2D point) throws IllegalArgumentException {
/*  671 */     setDestinationGeographicPoint(point.getX(), point.getY());
/*      */   }
/*      */   
/*      */   public void setDestinationPosition(Position position) throws TransformException {
/*      */     TransformedDirectPosition transformedDirectPosition;
/*  685 */     DirectPosition p = position.getDirectPosition();
/*  686 */     if (this.userToGeodetic != null) {
/*  687 */       this.userToGeodetic.transform(p);
/*  688 */       transformedDirectPosition = this.userToGeodetic;
/*      */     } 
/*  690 */     setDestinationGeographicPoint(transformedDirectPosition.getOrdinate(0), transformedDirectPosition.getOrdinate(1));
/*      */   }
/*      */   
/*      */   public Point2D getDestinationGeographicPoint() throws IllegalStateException {
/*  710 */     if (!this.destinationValid)
/*  711 */       computeDestinationPoint(); 
/*  713 */     return new Point2D.Double(Math.toDegrees(this.long2), Math.toDegrees(this.lat2));
/*      */   }
/*      */   
/*      */   public DirectPosition getDestinationPosition() throws TransformException {
/*      */     DirectPosition2D directPosition2D;
/*      */     DirectPosition directPosition;
/*  727 */     if (!this.destinationValid)
/*  728 */       computeDestinationPoint(); 
/*  730 */     TransformedDirectPosition transformedDirectPosition = this.userToGeodetic;
/*  731 */     if (transformedDirectPosition == null)
/*  732 */       directPosition2D = new DirectPosition2D(); 
/*  734 */     directPosition2D.setOrdinate(0, Math.toDegrees(this.long2));
/*  735 */     directPosition2D.setOrdinate(1, Math.toDegrees(this.lat2));
/*  736 */     if (this.userToGeodetic != null)
/*  737 */       directPosition = this.userToGeodetic.inverseTransform(); 
/*  739 */     return directPosition;
/*      */   }
/*      */   
/*      */   public void setDirection(double azimuth, double distance) throws IllegalArgumentException {
/*  758 */     azimuth = checkAzimuth(azimuth);
/*  759 */     checkOrthodromicDistance(distance);
/*  761 */     this.azimuth = azimuth;
/*  762 */     this.distance = distance;
/*  763 */     this.destinationValid = false;
/*  764 */     this.directionValid = true;
/*      */   }
/*      */   
/*      */   public double getAzimuth() throws IllegalStateException {
/*  784 */     if (!this.directionValid) {
/*  785 */       computeDirection();
/*  786 */       if (this.antipodal)
/*  787 */         Logging.getLogger(GeodeticCalculator.class).warning("Azimuth is innacurate for antipodal points."); 
/*      */     } 
/*  791 */     return Math.toDegrees(this.azimuth);
/*      */   }
/*      */   
/*      */   public double getOrthodromicDistance() throws IllegalStateException {
/*  807 */     if (!this.directionValid) {
/*  808 */       computeDirection();
/*  809 */       if (this.antipodal) {
/*  811 */         if (this.ellipsoid instanceof DefaultEllipsoid)
/*  812 */           return ((DefaultEllipsoid)this.ellipsoid).orthodromicDistance(Math.toDegrees(this.long1), Math.toDegrees(this.lat1), Math.toDegrees(this.long2), Math.toDegrees(this.lat2)); 
/*      */       } else {
/*  816 */         assert checkOrthodromicDistance() : this;
/*      */       } 
/*      */     } 
/*  819 */     return this.distance;
/*      */   }
/*      */   
/*      */   private boolean checkOrthodromicDistance() {
/*  828 */     if (this.ellipsoid instanceof DefaultEllipsoid) {
/*  830 */       DefaultEllipsoid ellipsoid = (DefaultEllipsoid)this.ellipsoid;
/*  831 */       double check = ellipsoid.orthodromicDistance(Math.toDegrees(this.long1), Math.toDegrees(this.lat1), Math.toDegrees(this.long2), Math.toDegrees(this.lat2));
/*  833 */       check = Math.abs(this.distance - check);
/*  834 */       return (check <= (this.distance + 1.0D) * 1.0E-8D);
/*      */     } 
/*  836 */     return true;
/*      */   }
/*      */   
/*      */   private void computeDestinationPoint() throws IllegalStateException {
/*  849 */     if (!this.directionValid)
/*  850 */       throw new IllegalStateException(Errors.format(41)); 
/*  853 */     double lat1 = this.lat1;
/*  854 */     double long1 = this.long1;
/*  855 */     double azimuth = this.azimuth;
/*  856 */     double distance = this.distance;
/*  872 */     double TU = this.fo * Math.sin(lat1) / Math.cos(lat1);
/*  873 */     double SF = Math.sin(azimuth);
/*  874 */     double CF = Math.cos(azimuth);
/*  875 */     double BAZ = (CF != 0.0D) ? (Math.atan2(TU, CF) * 2.0D) : 0.0D;
/*  876 */     double CU = 1.0D / Math.sqrt(TU * TU + 1.0D);
/*  877 */     double SU = TU * CU;
/*  878 */     double SA = CU * SF;
/*  879 */     double C2A = 1.0D - SA * SA;
/*  880 */     double X = Math.sqrt((1.0D / this.fo / this.fo - 1.0D) * C2A + 1.0D) + 1.0D;
/*  881 */     X = (X - 2.0D) / X;
/*  882 */     double C = 1.0D - X;
/*  883 */     C = (X * X / 4.0D + 1.0D) / C;
/*  884 */     double D = (0.375D * X * X - 1.0D) * X;
/*  885 */     TU = distance / this.fo / this.semiMajorAxis / C;
/*  886 */     double Y = TU;
/*      */     while (true) {
/*  889 */       double SY = Math.sin(Y);
/*  890 */       double CY = Math.cos(Y);
/*  891 */       double CZ = Math.cos(BAZ + Y);
/*  892 */       double E = CZ * CZ * 2.0D - 1.0D;
/*  893 */       C = Y;
/*  894 */       X = E * CY;
/*  895 */       Y = E + E - 1.0D;
/*  896 */       Y = (((SY * SY * 4.0D - 3.0D) * Y * CZ * D / 6.0D + X) * D / 4.0D - CZ) * SY * D + TU;
/*  897 */       if (Math.abs(Y - C) <= 5.0E-14D) {
/*  898 */         BAZ = CU * CY * CF - SU * SY;
/*  899 */         C = this.fo * Math.hypot(SA, BAZ);
/*  900 */         D = SU * CY + CU * SY * CF;
/*  901 */         this.lat2 = Math.atan2(D, C);
/*  902 */         C = CU * CY - SU * SY * CF;
/*  903 */         X = Math.atan2(SY * SF, C);
/*  904 */         C = ((-3.0D * C2A + 4.0D) * this.f + 4.0D) * C2A * this.f / 16.0D;
/*  905 */         D = ((E * CY * C + CZ) * SY * C + Y) * SA;
/*  906 */         this.long2 = long1 + X - (1.0D - C) * D * this.f;
/*  907 */         this.long2 = castToAngleRange(this.long2);
/*  908 */         this.destinationValid = true;
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getMeridianArcLength(double latitude1, double latitude2) {
/*  920 */     return getMeridianArcLengthRadians(checkLatitude(latitude1), checkLatitude(latitude2));
/*      */   }
/*      */   
/*      */   private double getMeridianArcLengthRadians(double P1, double P2) {
/*  943 */     double S1 = Math.abs(P1);
/*  944 */     double S2 = Math.abs(P2);
/*  945 */     double DA = P2 - P1;
/*  947 */     if (S1 > 5.0E-15D || S2 <= 1.5707963267948915D || S2 >= 1.5707963267949017D) {
/*  948 */       double DB = Math.sin(P2 * 2.0D) - Math.sin(P1 * 2.0D);
/*  949 */       double DC = Math.sin(P2 * 4.0D) - Math.sin(P1 * 4.0D);
/*  950 */       double DD = Math.sin(P2 * 6.0D) - Math.sin(P1 * 6.0D);
/*  951 */       double DE = Math.sin(P2 * 8.0D) - Math.sin(P1 * 8.0D);
/*  952 */       double DF = Math.sin(P2 * 10.0D) - Math.sin(P1 * 10.0D);
/*  954 */       S2 = -DB * this.B / 2.0D + DC * this.C / 4.0D - DD * this.D / 6.0D + DE * this.E / 8.0D - DF * this.F / 10.0D;
/*      */     } else {
/*  956 */       S2 = 0.0D;
/*      */     } 
/*  959 */     S1 = DA * this.A;
/*  961 */     return Math.abs(this.semiMajorAxis * (1.0D - this.eccentricitySquared) * (S1 + S2));
/*      */   }
/*      */   
/*      */   private void computeDirection() throws IllegalStateException {
/*      */     double xy, w, q2, q4, q6, r2, r3, sig, ssig, slon, clon, sinalf;
/*  975 */     if (!this.destinationValid)
/*  976 */       throw new IllegalStateException(Errors.format(40)); 
/*  979 */     double long1 = this.long1;
/*  980 */     double lat1 = this.lat1;
/*  981 */     double long2 = this.long2;
/*  982 */     double lat2 = this.lat2;
/* 1000 */     double dlon = castToAngleRange(long2 - long1);
/* 1001 */     double ss = Math.abs(dlon);
/* 1002 */     if (ss < 5.0E-14D) {
/* 1003 */       this.distance = getMeridianArcLengthRadians(lat1, lat2);
/* 1004 */       this.azimuth = (lat2 > lat1) ? 0.0D : Math.PI;
/* 1005 */       this.directionValid = true;
/* 1006 */       this.antipodal = false;
/*      */       return;
/*      */     } 
/* 1009 */     this.antipodal = (Math.PI - ss < 0.014D && Math.abs(lat1 + lat2) < 0.014D);
/* 1016 */     double ESQP = this.eccentricitySquared / (1.0D - this.eccentricitySquared);
/* 1017 */     double alimit = Math.PI * this.fo;
/* 1018 */     if (ss >= alimit && lat1 < 0.007D && lat1 > -0.007D && lat2 < 0.007D && lat2 > -0.007D) {
/* 1023 */       double AZ_TEMP, AO, CONS = (Math.PI - ss) / Math.PI * this.f;
/* 1024 */       double AZ = Math.asin(CONS);
/* 1026 */       int iter = 0;
/*      */       do {
/* 1028 */         if (++iter > 8)
/* 1029 */           throw new ArithmeticException(getNoConvergenceErrorMessage()); 
/* 1031 */         S = Math.cos(AZ);
/* 1032 */         double C2 = S * S;
/* 1034 */         AO = this.T1 + this.T2 * C2 + this.T4 * C2 * C2 + this.T6 * C2 * C2 * C2;
/* 1035 */         double CS = CONS / AO;
/* 1036 */         S = Math.asin(CS);
/* 1037 */         AZ_TEMP = AZ;
/* 1038 */         AZ = S;
/* 1039 */       } while (Math.abs(S - AZ_TEMP) >= 5.0E-13D);
/* 1041 */       double AZ1 = (dlon < 0.0D) ? (6.283185307179586D - S) : S;
/* 1042 */       this.azimuth = castToAngleRange(AZ1);
/* 1043 */       double S = Math.cos(AZ1);
/* 1046 */       double U2 = ESQP * S * S;
/* 1047 */       double U4 = U2 * U2;
/* 1048 */       double U6 = U4 * U2;
/* 1049 */       double U8 = U6 * U2;
/* 1050 */       double BO = 1.0D + 0.25D * U2 + 0.046875D * U4 + 0.01953125D * U6 + -0.01068115234375D * U8;
/* 1055 */       S = Math.sin(AZ1);
/* 1056 */       double SMS = this.semiMajorAxis * Math.PI * (1.0D - this.f * Math.abs(S) * AO - BO * this.fo);
/* 1057 */       this.distance = this.semiMajorAxis * ss - SMS;
/* 1058 */       this.directionValid = true;
/*      */       return;
/*      */     } 
/* 1063 */     double u1 = Math.atan(this.fo * Math.sin(lat1) / Math.cos(lat1));
/* 1064 */     double u2 = Math.atan(this.fo * Math.sin(lat2) / Math.cos(lat2));
/* 1065 */     double su1 = Math.sin(u1);
/* 1066 */     double cu1 = Math.cos(u1);
/* 1067 */     double su2 = Math.sin(u2);
/* 1068 */     double cu2 = Math.cos(u2);
/* 1069 */     double ab = dlon;
/* 1070 */     int kcount = 0;
/*      */     do {
/* 1072 */       if (++kcount > 12)
/* 1073 */         throw new ArithmeticException(getNoConvergenceErrorMessage()); 
/* 1075 */       clon = Math.cos(ab);
/* 1076 */       slon = Math.sin(ab);
/* 1077 */       double csig = su1 * su2 + cu1 * cu2 * clon;
/* 1078 */       ssig = Math.hypot(slon * cu2, su2 * cu1 - su1 * cu2 * clon);
/* 1079 */       sig = Math.atan2(ssig, csig);
/* 1080 */       sinalf = cu1 * cu2 * slon / ssig;
/* 1081 */       w = 1.0D - sinalf * sinalf;
/* 1082 */       double t4 = w * w;
/* 1083 */       double t6 = w * t4;
/* 1086 */       double ao = this.f + this.a01 * w + this.a02 * t4 + this.a03 * t6;
/* 1087 */       double a2 = this.a21 * w + this.a22 * t4 + this.a23 * t6;
/* 1088 */       double a4 = this.a42 * t4 + this.a43 * t6;
/* 1089 */       double a6 = this.a63 * t6;
/* 1092 */       double qo = 0.0D;
/* 1093 */       if (w > 5.0E-15D)
/* 1094 */         qo = -2.0D * su1 * su2 / w; 
/* 1096 */       q2 = csig + qo;
/* 1097 */       q4 = 2.0D * q2 * q2 - 1.0D;
/* 1098 */       q6 = q2 * (4.0D * q2 * q2 - 3.0D);
/* 1099 */       r2 = 2.0D * ssig * csig;
/* 1100 */       r3 = ssig * (3.0D - 4.0D * ssig * ssig);
/* 1103 */       double s = sinalf * (ao * sig + a2 * ssig * q2 + a4 * r2 * q4 + a6 * r3 * q6);
/* 1104 */       double xz = dlon + s;
/* 1105 */       xy = Math.abs(xz - ab);
/* 1106 */       ab = dlon + s;
/* 1107 */     } while (xy >= 5.0E-14D);
/* 1109 */     double z = ESQP * w;
/* 1110 */     double bo = 1.0D + z * (0.25D + z * (-0.046875D + z * (0.01953125D - z * 0.01068115234375D)));
/* 1111 */     double b2 = z * (-0.25D + z * (0.0625D + z * (-0.029296875D + z * 0.01708984375D)));
/* 1112 */     double b4 = z * z * (-0.0078125D + z * (0.005859375D - z * 0.0042724609375D));
/* 1113 */     double b6 = z * z * z * (-6.510416666666666E-4D + z * 8.138020833333334E-4D);
/* 1116 */     this.distance = this.semiMinorAxis * (bo * sig + b2 * ssig * q2 + b4 * r2 * q4 + b6 * r3 * q6);
/* 1117 */     double az1 = (dlon < 0.0D) ? 4.71238898038469D : 1.5707963267948966D;
/* 1120 */     if (Math.abs(su1) >= 5.0E-15D || Math.abs(su2) >= 5.0E-15D) {
/* 1121 */       double tana1 = slon * cu2 / (su2 * cu1 - clon * su1 * cu2);
/* 1122 */       double sina1 = sinalf / cu1;
/* 1125 */       az1 = Math.atan2(sina1, sina1 / tana1);
/*      */     } 
/* 1127 */     this.azimuth = castToAngleRange(az1);
/* 1128 */     this.directionValid = true;
/*      */   }
/*      */   
/*      */   public Shape getGeodeticCurve(int numberOfPoints) {
/* 1150 */     List<Point2D> points = getGeodeticPath(numberOfPoints);
/* 1151 */     GeneralPath path = new GeneralPath(0, numberOfPoints + 1);
/* 1152 */     Point2D start = points.get(0);
/* 1153 */     path.moveTo(start.getX(), start.getY());
/* 1154 */     for (int i = 1; i < points.size(); i++) {
/* 1155 */       Point2D p = points.get(i);
/* 1156 */       path.lineTo(p.getX(), p.getY());
/*      */     } 
/* 1159 */     return path;
/*      */   }
/*      */   
/*      */   public Shape getGeodeticCurve() {
/* 1174 */     return getGeodeticCurve(10);
/*      */   }
/*      */   
/*      */   public List<Point2D> getGeodeticPath(int numPoints) {
/* 1192 */     if (numPoints < 0)
/* 1193 */       throw new IllegalArgumentException(Errors.format(58, "numPoints", Integer.valueOf(numPoints))); 
/* 1197 */     List<Point2D> points = new ArrayList<Point2D>(numPoints + 2);
/* 1199 */     if (!this.directionValid)
/* 1200 */       computeDirection(); 
/* 1203 */     if (!this.destinationValid)
/* 1204 */       computeDestinationPoint(); 
/* 1207 */     double long2 = this.long2;
/* 1208 */     double lat2 = this.lat2;
/* 1209 */     double distance = this.distance;
/* 1210 */     double delta = distance / (numPoints + 1);
/* 1212 */     points.add(new Point2D.Double(Math.toDegrees(this.long1), Math.toDegrees(this.lat1)));
/* 1214 */     for (int i = 1; i <= numPoints; i++) {
/* 1215 */       this.distance = i * delta;
/* 1216 */       computeDestinationPoint();
/* 1217 */       points.add(new Point2D.Double(Math.toDegrees(this.long2), Math.toDegrees(this.lat2)));
/*      */     } 
/* 1219 */     points.add(new Point2D.Double(Math.toDegrees(long2), Math.toDegrees(lat2)));
/* 1220 */     this.long2 = long2;
/* 1221 */     this.lat2 = lat2;
/* 1222 */     this.distance = distance;
/* 1224 */     return points;
/*      */   }
/*      */   
/*      */   private Shape getLoxodromicCurve() {
/* 1240 */     throw new UnsupportedOperationException();
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1359 */     Vocabulary resources = Vocabulary.getResources(null);
/* 1360 */     TableWriter buffer = new TableWriter(null, " ");
/* 1361 */     if (this.coordinateReferenceSystem != null) {
/* 1362 */       buffer.write(resources.getLabel(32));
/* 1363 */       buffer.nextColumn();
/* 1364 */       buffer.write(this.coordinateReferenceSystem.getName().getCode());
/* 1365 */       buffer.nextLine();
/*      */     } 
/* 1367 */     if (this.ellipsoid != null) {
/* 1368 */       buffer.write(resources.getLabel(56));
/* 1369 */       buffer.nextColumn();
/* 1370 */       buffer.write(this.ellipsoid.getName().getCode());
/* 1371 */       buffer.nextLine();
/*      */     } 
/* 1373 */     CoordinateFormat cf = new CoordinateFormat();
/* 1374 */     Format nf = cf.getFormat(0);
/* 1376 */     buffer.write(resources.getLabel(201));
/* 1377 */     buffer.nextColumn();
/* 1378 */     buffer.write(format((Format)cf, this.long1, this.lat1));
/* 1379 */     buffer.nextLine();
/* 1381 */     if (this.destinationValid) {
/* 1382 */       buffer.write(resources.getLabel(212));
/* 1383 */       buffer.nextColumn();
/* 1384 */       buffer.write(format((Format)cf, this.long2, this.lat2));
/* 1385 */       buffer.nextLine();
/*      */     } 
/* 1387 */     if (this.directionValid) {
/* 1388 */       buffer.write(resources.getLabel(8));
/* 1389 */       buffer.nextColumn();
/* 1390 */       buffer.write(nf.format(new Angle(Math.toDegrees(this.azimuth))));
/* 1391 */       buffer.nextLine();
/*      */     } 
/* 1393 */     if (this.directionValid) {
/* 1394 */       buffer.write(resources.getLabel(159));
/* 1395 */       buffer.nextColumn();
/* 1396 */       buffer.write(nf.format(Double.valueOf(this.distance)));
/* 1397 */       if (this.ellipsoid != null) {
/* 1398 */         buffer.write(32);
/* 1399 */         buffer.write(this.ellipsoid.getAxisUnit().toString());
/*      */       } 
/* 1401 */       buffer.nextLine();
/*      */     } 
/* 1403 */     return buffer.toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\GeodeticCalculator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */