/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ 
/*     */ public class DefaultSphericalCS extends AbstractCS implements SphericalCS {
/*     */   private static final long serialVersionUID = 196295996465774477L;
/*     */   
/*  64 */   public static DefaultSphericalCS GEOCENTRIC = new DefaultSphericalCS(name(77), DefaultCoordinateSystemAxis.SPHERICAL_LONGITUDE, DefaultCoordinateSystemAxis.SPHERICAL_LATITUDE, DefaultCoordinateSystemAxis.GEOCENTRIC_RADIUS);
/*     */   
/*     */   public DefaultSphericalCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/*  83 */     super(name, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   public DefaultSphericalCS(SphericalCS cs) {
/*  96 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultSphericalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 114 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 124 */     return !AxisDirection.FUTURE.equals(direction.absolute());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultSphericalCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */