/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ 
/*     */ public class DefaultPolarCS extends AbstractCS implements PolarCS {
/*     */   private static final long serialVersionUID = 3960197260975470951L;
/*     */   
/*     */   public DefaultPolarCS(PolarCS cs) {
/*  64 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultPolarCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/*  78 */     super(name, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultPolarCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/*  94 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 104 */     return !AxisDirection.FUTURE.equals(direction.absolute());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultPolarCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */