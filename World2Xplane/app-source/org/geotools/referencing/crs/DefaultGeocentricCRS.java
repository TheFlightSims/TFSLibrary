/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*     */ import org.geotools.referencing.cs.DefaultSphericalCS;
/*     */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.GeocentricCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ 
/*     */ public class DefaultGeocentricCRS extends AbstractSingleCRS implements GeocentricCRS {
/*     */   private static final long serialVersionUID = 6784642848287659827L;
/*     */   
/*  72 */   public static final DefaultGeocentricCRS CARTESIAN = new DefaultGeocentricCRS(name(14), (GeodeticDatum)DefaultGeodeticDatum.WGS84, (CartesianCS)DefaultCartesianCS.GEOCENTRIC);
/*     */   
/*  81 */   public static final DefaultGeocentricCRS SPHERICAL = new DefaultGeocentricCRS(name(204), (GeodeticDatum)DefaultGeodeticDatum.WGS84, (SphericalCS)DefaultSphericalCS.GEOCENTRIC);
/*     */   
/*     */   public DefaultGeocentricCRS(GeocentricCRS crs) {
/*  97 */     super((SingleCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultGeocentricCRS(String name, GeodeticDatum datum, CartesianCS cs) {
/* 111 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultGeocentricCRS(String name, GeodeticDatum datum, SphericalCS cs) {
/* 125 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, CartesianCS cs) {
/* 140 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, SphericalCS cs) {
/* 156 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public GeodeticDatum getDatum() {
/* 164 */     return (GeodeticDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 175 */     return 0x54149733 ^ super.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 188 */     Unit<?> unit = getUnit();
/* 189 */     formatter.append((IdentifiedObject)this.datum);
/* 190 */     formatter.append((IdentifiedObject)((GeodeticDatum)this.datum).getPrimeMeridian());
/* 191 */     formatter.append(unit);
/* 192 */     int dimension = this.coordinateSystem.getDimension();
/* 193 */     for (int i = 0; i < dimension; i++)
/* 194 */       formatter.append((IdentifiedObject)this.coordinateSystem.getAxis(i)); 
/* 196 */     if (unit == null)
/* 197 */       formatter.setInvalidWKT(GeocentricCRS.class); 
/* 199 */     return "GEOCCS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultGeocentricCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */