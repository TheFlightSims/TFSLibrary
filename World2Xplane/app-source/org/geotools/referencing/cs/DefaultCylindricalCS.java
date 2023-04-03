/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ 
/*     */ public class DefaultCylindricalCS extends AbstractCS implements CylindricalCS {
/*     */   private static final long serialVersionUID = -8290402732390917907L;
/*     */   
/*     */   public DefaultCylindricalCS(CylindricalCS cs) {
/*  65 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultCylindricalCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/*  81 */     super(name, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   public DefaultCylindricalCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/*  99 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 109 */     return !AxisDirection.FUTURE.equals(direction.absolute());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultCylindricalCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */