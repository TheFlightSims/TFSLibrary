/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.AffineCS;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ public class DefaultCartesianCS extends DefaultAffineCS implements CartesianCS {
/*     */   private static final long serialVersionUID = -6182037957705712945L;
/*     */   
/*  74 */   public static DefaultCartesianCS PROJECTED = new DefaultCartesianCS(name(177), DefaultCoordinateSystemAxis.EASTING, DefaultCoordinateSystemAxis.NORTHING);
/*     */   
/*  88 */   public static DefaultCartesianCS GEOCENTRIC = new DefaultCartesianCS(name(77), DefaultCoordinateSystemAxis.GEOCENTRIC_X, DefaultCoordinateSystemAxis.GEOCENTRIC_Y, DefaultCoordinateSystemAxis.GEOCENTRIC_Z);
/*     */   
/* 100 */   public static DefaultCartesianCS GENERIC_2D = new DefaultCartesianCS(name(15), DefaultCoordinateSystemAxis.X, DefaultCoordinateSystemAxis.Y);
/*     */   
/* 112 */   public static DefaultCartesianCS GENERIC_3D = new DefaultCartesianCS(name(16), DefaultCoordinateSystemAxis.X, DefaultCoordinateSystemAxis.Y, DefaultCoordinateSystemAxis.Z);
/*     */   
/* 124 */   public static DefaultCartesianCS GRID = new DefaultCartesianCS(name(96), DefaultCoordinateSystemAxis.COLUMN, DefaultCoordinateSystemAxis.ROW);
/*     */   
/* 137 */   public static DefaultCartesianCS DISPLAY = new DefaultCartesianCS(name(48), DefaultCoordinateSystemAxis.DISPLAY_X, DefaultCoordinateSystemAxis.DISPLAY_Y);
/*     */   
/*     */   private transient UnitConverter[] converters;
/*     */   
/*     */   public DefaultCartesianCS(CartesianCS cs) {
/* 161 */     super((AffineCS)cs);
/* 162 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   public DefaultCartesianCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 176 */     super(name, axis0, axis1);
/* 177 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   public DefaultCartesianCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 193 */     super(name, axis0, axis1, axis2);
/* 194 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   public DefaultCartesianCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 210 */     super(properties, axis0, axis1);
/* 211 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   public DefaultCartesianCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 229 */     super(properties, axis0, axis1, axis2);
/* 230 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   DefaultCartesianCS(Map<String, ?> properties, CoordinateSystemAxis[] axis) {
/* 237 */     super(properties, axis);
/* 238 */     ensurePerpendicularAxis();
/*     */   }
/*     */   
/*     */   private void ensurePerpendicularAxis() throws IllegalArgumentException {
/* 245 */     int dimension = getDimension();
/* 246 */     for (int i = 0; i < dimension; i++) {
/* 247 */       AxisDirection axis0 = getAxis(i).getDirection();
/* 248 */       for (int j = i; ++j < dimension; ) {
/* 249 */         AxisDirection axis1 = getAxis(j).getDirection();
/* 250 */         double angle = DefaultCoordinateSystemAxis.getAngle(axis0, axis1);
/* 251 */         if (Math.abs(Math.abs(angle) - 90.0D) > 1.0E-10D)
/* 252 */           throw new IllegalArgumentException(Errors.format(115, axis0.name(), axis1.name())); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws MismatchedDimensionException {
/* 271 */     ensureDimensionMatch("coord1", coord1);
/* 272 */     ensureDimensionMatch("coord2", coord2);
/* 273 */     Unit<?> unit = getDistanceUnit();
/* 274 */     UnitConverter[] converters = this.converters;
/* 275 */     if (converters == null) {
/* 276 */       converters = new UnitConverter[getDimension()];
/* 277 */       for (int j = 0; j < converters.length; j++)
/* 278 */         converters[j] = getAxis(j).getUnit().getConverterTo(unit); 
/* 280 */       this.converters = converters;
/*     */     } 
/* 282 */     double sum = 0.0D;
/* 283 */     for (int i = 0; i < converters.length; i++) {
/* 284 */       UnitConverter c = converters[i];
/* 285 */       double delta = c.convert(coord1[i]) - c.convert(coord2[i]);
/* 286 */       sum += delta * delta;
/*     */     } 
/* 288 */     return new Measure(Math.sqrt(sum), unit);
/*     */   }
/*     */   
/*     */   public DefaultCartesianCS usingUnit(Unit<?> unit) throws IllegalArgumentException {
/* 302 */     CoordinateSystemAxis[] axis = axisUsingUnit(unit);
/* 303 */     if (axis == null)
/* 304 */       return this; 
/* 306 */     return new DefaultCartesianCS(getProperties((IdentifiedObject)this, null), axis);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultCartesianCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */