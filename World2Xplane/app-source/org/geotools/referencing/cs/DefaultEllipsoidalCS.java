/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ 
/*     */ public class DefaultEllipsoidalCS extends AbstractCS implements EllipsoidalCS {
/*     */   private static final long serialVersionUID = -1452492488902329211L;
/*     */   
/*  69 */   public static DefaultEllipsoidalCS GEODETIC_2D = new DefaultEllipsoidalCS(name(83), DefaultCoordinateSystemAxis.GEODETIC_LONGITUDE, DefaultCoordinateSystemAxis.GEODETIC_LATITUDE);
/*     */   
/*  81 */   public static DefaultEllipsoidalCS GEODETIC_3D = new DefaultEllipsoidalCS(name(84), DefaultCoordinateSystemAxis.GEODETIC_LONGITUDE, DefaultCoordinateSystemAxis.GEODETIC_LATITUDE, DefaultCoordinateSystemAxis.ELLIPSOIDAL_HEIGHT);
/*     */   
/*     */   private transient int longitudeAxis;
/*     */   
/*     */   private transient int latitudeAxis;
/*     */   
/*     */   private transient int heightAxis;
/*     */   
/*     */   private transient UnitConverter longitudeConverter;
/*     */   
/*     */   private transient UnitConverter latitudeConverter;
/*     */   
/*     */   private transient UnitConverter heightConverter;
/*     */   
/*     */   public DefaultEllipsoidalCS(EllipsoidalCS cs) {
/* 111 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultEllipsoidalCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 125 */     super(name, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultEllipsoidalCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 141 */     super(name, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   public DefaultEllipsoidalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 157 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultEllipsoidalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 175 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   private DefaultEllipsoidalCS(Map<String, ?> properties, CoordinateSystemAxis[] axis) {
/* 182 */     super(properties, axis);
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 194 */     direction = direction.absolute();
/* 195 */     return (AxisDirection.NORTH.equals(direction) || AxisDirection.EAST.equals(direction) || AxisDirection.UP.equals(direction));
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleUnit(AxisDirection direction, Unit<?> unit) {
/* 210 */     direction = direction.absolute();
/* 211 */     Unit<?> expected = AxisDirection.UP.equals(direction) ? SI.METER : NonSI.DEGREE_ANGLE;
/* 212 */     return expected.isCompatible(unit);
/*     */   }
/*     */   
/*     */   private void update() {
/* 219 */     for (int i = getDimension(); --i >= 0; ) {
/* 220 */       CoordinateSystemAxis axis = getAxis(i);
/* 221 */       AxisDirection direction = axis.getDirection().absolute();
/* 222 */       Unit<?> unit = axis.getUnit();
/* 223 */       if (AxisDirection.EAST.equals(direction)) {
/* 224 */         this.longitudeAxis = i;
/* 225 */         this.longitudeConverter = unit.getConverterTo(NonSI.DEGREE_ANGLE);
/*     */         continue;
/*     */       } 
/* 228 */       if (AxisDirection.NORTH.equals(direction)) {
/* 229 */         this.latitudeAxis = i;
/* 230 */         this.latitudeConverter = unit.getConverterTo(NonSI.DEGREE_ANGLE);
/*     */         continue;
/*     */       } 
/* 233 */       if (AxisDirection.UP.equals(direction)) {
/* 234 */         this.heightAxis = i;
/* 235 */         this.heightConverter = unit.getConverterTo(SI.METER);
/*     */         continue;
/*     */       } 
/* 240 */       throw new AssertionError(direction);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getLongitude(double[] coordinates) throws MismatchedDimensionException {
/* 255 */     ensureDimensionMatch("coordinates", coordinates);
/* 256 */     if (this.longitudeConverter == null)
/* 257 */       update(); 
/* 259 */     return this.longitudeConverter.convert(coordinates[this.longitudeAxis]);
/*     */   }
/*     */   
/*     */   public double getLatitude(double[] coordinates) throws MismatchedDimensionException {
/* 273 */     ensureDimensionMatch("coordinates", coordinates);
/* 274 */     if (this.latitudeConverter == null)
/* 275 */       update(); 
/* 277 */     return this.latitudeConverter.convert(coordinates[this.latitudeAxis]);
/*     */   }
/*     */   
/*     */   public double getHeight(double[] coordinates) throws MismatchedDimensionException {
/* 292 */     ensureDimensionMatch("coordinates", coordinates);
/* 293 */     if (this.heightConverter == null) {
/* 294 */       update();
/* 295 */       if (this.heightConverter == null)
/* 296 */         throw new IllegalStateException(Errors.format(126)); 
/*     */     } 
/* 299 */     return this.heightConverter.convert(coordinates[this.heightAxis]);
/*     */   }
/*     */   
/*     */   public DefaultEllipsoidalCS usingUnit(Unit<?> unit) throws IllegalArgumentException {
/* 315 */     CoordinateSystemAxis[] axis = axisUsingUnit(unit);
/* 316 */     if (axis == null)
/* 317 */       return this; 
/* 319 */     return new DefaultEllipsoidalCS(getProperties((IdentifiedObject)this, null), axis);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultEllipsoidalCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */