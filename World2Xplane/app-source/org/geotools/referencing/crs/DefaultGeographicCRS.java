/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.geotools.metadata.iso.extent.ExtentImpl;
/*     */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*     */ import org.geotools.referencing.datum.DefaultEllipsoid;
/*     */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.UnsupportedImplementationException;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ 
/*     */ public class DefaultGeographicCRS extends AbstractSingleCRS implements GeographicCRS {
/*     */   private static final long serialVersionUID = 861224913438092335L;
/*     */   
/*     */   public static final DefaultGeographicCRS WGS84;
/*     */   
/*     */   public static final DefaultGeographicCRS WGS84_3D;
/*     */   
/*     */   static {
/*  89 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/*  90 */     properties.put("name", "WGS84(DD)");
/*  91 */     String[] alias = { "WGS84", "WGS 84" };
/*  95 */     properties.put("alias", alias);
/*  96 */     properties.put("domainOfValidity", ExtentImpl.WORLD);
/*  97 */     WGS84 = new DefaultGeographicCRS(properties, (GeodeticDatum)DefaultGeodeticDatum.WGS84, (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_2D);
/*  99 */     alias[1] = "WGS 84 (geographic 3D)";
/* 100 */     WGS84_3D = new DefaultGeographicCRS(properties, (GeodeticDatum)DefaultGeodeticDatum.WGS84, (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_3D);
/*     */   }
/*     */   
/*     */   public DefaultGeographicCRS(GeographicCRS crs) {
/* 116 */     super((SingleCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultGeographicCRS(GeodeticDatum datum, EllipsoidalCS cs) {
/* 129 */     this(getProperties((IdentifiedObject)datum), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultGeographicCRS(String name, GeodeticDatum datum, EllipsoidalCS cs) {
/* 143 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultGeographicCRS(Map<String, ?> properties, GeodeticDatum datum, EllipsoidalCS cs) {
/* 158 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public EllipsoidalCS getCoordinateSystem() {
/* 166 */     return (EllipsoidalCS)super.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public GeodeticDatum getDatum() {
/* 174 */     return (GeodeticDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws UnsupportedOperationException, MismatchedDimensionException {
/* 194 */     if (!(this.coordinateSystem instanceof DefaultEllipsoidalCS))
/* 195 */       throw new UnsupportedImplementationException(this.coordinateSystem.getClass()); 
/* 197 */     Ellipsoid ellipsoid = ((GeodeticDatum)this.datum).getEllipsoid();
/* 198 */     if (!(ellipsoid instanceof DefaultEllipsoid))
/* 199 */       throw new UnsupportedImplementationException(ellipsoid.getClass()); 
/* 201 */     DefaultEllipsoidalCS cs = (DefaultEllipsoidalCS)this.coordinateSystem;
/* 202 */     DefaultEllipsoid e = (DefaultEllipsoid)ellipsoid;
/* 203 */     if (coord1.length != 2 || coord2.length != 2 || cs.getDimension() != 2)
/* 208 */       return super.distance(coord1, coord2); 
/* 210 */     return new Measure(e.orthodromicDistance(cs.getLongitude(coord1), cs.getLatitude(coord1), cs.getLongitude(coord2), cs.getLatitude(coord2)), e.getAxisUnit());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 224 */     return 0xED31AC2F ^ super.hashCode();
/*     */   }
/*     */   
/*     */   static Unit<Angle> getAngularUnit(CoordinateSystem coordinateSystem) {
/* 232 */     Unit<Angle> unit = NonSI.DEGREE_ANGLE;
/* 233 */     for (int i = coordinateSystem.getDimension(); --i >= 0; ) {
/* 234 */       CoordinateSystemAxis axis = coordinateSystem.getAxis(i);
/* 235 */       Unit<?> candidate = axis.getUnit();
/* 236 */       if (NonSI.DEGREE_ANGLE.isCompatible(candidate)) {
/* 237 */         unit = candidate.asType(Angle.class);
/* 238 */         if (AxisDirection.EAST.equals(axis.getDirection().absolute()))
/*     */           break; 
/*     */       } 
/*     */     } 
/* 243 */     return unit;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 256 */     Unit<Angle> oldUnit = formatter.getAngularUnit();
/* 257 */     Unit<Angle> unit = getAngularUnit(this.coordinateSystem);
/* 258 */     formatter.setAngularUnit(unit);
/* 259 */     formatter.append((IdentifiedObject)this.datum);
/* 260 */     formatter.append((IdentifiedObject)((GeodeticDatum)this.datum).getPrimeMeridian());
/* 261 */     formatter.append(unit);
/* 262 */     int dimension = this.coordinateSystem.getDimension();
/* 263 */     for (int i = 0; i < dimension; i++)
/* 264 */       formatter.append((IdentifiedObject)this.coordinateSystem.getAxis(i)); 
/* 266 */     if (!unit.equals(getUnit()))
/* 267 */       formatter.setInvalidWKT(GeographicCRS.class); 
/* 269 */     formatter.setAngularUnit(oldUnit);
/* 270 */     return "GEOGCS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultGeographicCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */