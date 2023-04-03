/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultTimeCS extends AbstractCS implements TimeCS {
/*     */   private static final long serialVersionUID = 5222911412381303989L;
/*     */   
/*     */   public static final DefaultTimeCS DAYS;
/*     */   
/*     */   public static final DefaultTimeCS SECONDS;
/*     */   
/*     */   public static final DefaultTimeCS MILLISECONDS;
/*     */   
/*     */   static {
/*  98 */     Map<String, Object> properties = name(214);
/*  99 */     CoordinateSystemAxis axis = DefaultCoordinateSystemAxis.TIME;
/* 100 */     DAYS = new DefaultTimeCS(properties, axis);
/* 101 */     InternationalString name = ((GenericName)axis.getAlias().iterator().next()).toInternationalString();
/* 102 */     axis = new DefaultCoordinateSystemAxis(name, "t", AxisDirection.FUTURE, (Unit<?>)SI.SECOND);
/* 103 */     SECONDS = new DefaultTimeCS(properties, axis);
/* 104 */     axis = new DefaultCoordinateSystemAxis(name, "t", AxisDirection.FUTURE, SI.MILLI((Unit)SI.SECOND));
/* 105 */     MILLISECONDS = new DefaultTimeCS(properties, axis);
/*     */   }
/*     */   
/*     */   public DefaultTimeCS(TimeCS cs) {
/* 120 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultTimeCS(String name, CoordinateSystemAxis axis) {
/* 130 */     super(name, new CoordinateSystemAxis[] { axis });
/* 131 */     ensureTimeUnit(getAxis(0).getUnit());
/*     */   }
/*     */   
/*     */   public DefaultTimeCS(Map<String, ?> properties, CoordinateSystemAxis axis) {
/* 143 */     super(properties, new CoordinateSystemAxis[] { axis });
/* 144 */     ensureTimeUnit(getAxis(0).getUnit());
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 154 */     return AxisDirection.FUTURE.equals(direction.absolute());
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleUnit(AxisDirection direction, Unit<?> unit) {
/* 165 */     return SI.SECOND.isCompatible(unit);
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws MismatchedDimensionException {
/* 180 */     ensureDimensionMatch("coord1", coord1);
/* 181 */     ensureDimensionMatch("coord2", coord2);
/* 182 */     return new Measure(Math.abs(coord1[0] - coord2[0]), getDistanceUnit());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultTimeCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */