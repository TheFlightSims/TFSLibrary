/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ 
/*     */ public class DefaultVerticalCS extends AbstractCS implements VerticalCS {
/*     */   private static final long serialVersionUID = 1201155778896630499L;
/*     */   
/*  64 */   public static DefaultVerticalCS ELLIPSOIDAL_HEIGHT = new DefaultVerticalCS(DefaultCoordinateSystemAxis.ELLIPSOIDAL_HEIGHT);
/*     */   
/*  74 */   public static DefaultVerticalCS GRAVITY_RELATED_HEIGHT = new DefaultVerticalCS(DefaultCoordinateSystemAxis.GRAVITY_RELATED_HEIGHT);
/*     */   
/*     */   @Deprecated
/*  81 */   public static DefaultVerticalCS GRAVITY_RELATED = GRAVITY_RELATED_HEIGHT;
/*     */   
/*  88 */   public static DefaultVerticalCS DEPTH = new DefaultVerticalCS(DefaultCoordinateSystemAxis.DEPTH);
/*     */   
/*     */   public DefaultVerticalCS(VerticalCS cs) {
/* 101 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultVerticalCS(CoordinateSystemAxis axis) {
/* 113 */     super(getProperties((IdentifiedObject)axis), new CoordinateSystemAxis[] { axis });
/*     */   }
/*     */   
/*     */   public DefaultVerticalCS(String name, CoordinateSystemAxis axis) {
/* 123 */     super(name, new CoordinateSystemAxis[] { axis });
/*     */   }
/*     */   
/*     */   public DefaultVerticalCS(Map<String, ?> properties, CoordinateSystemAxis axis) {
/* 134 */     super(properties, new CoordinateSystemAxis[] { axis });
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 144 */     return AxisDirection.UP.equals(direction.absolute());
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws MismatchedDimensionException {
/* 159 */     ensureDimensionMatch("coord1", coord1);
/* 160 */     ensureDimensionMatch("coord2", coord2);
/* 161 */     return new Measure(Math.abs(coord1[0] - coord2[0]), getDistanceUnit());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultVerticalCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */