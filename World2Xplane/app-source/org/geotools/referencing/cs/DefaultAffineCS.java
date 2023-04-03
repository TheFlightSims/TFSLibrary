/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.opengis.referencing.cs.AffineCS;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ 
/*     */ public class DefaultAffineCS extends AbstractCS implements AffineCS {
/*     */   private static final long serialVersionUID = 7977674229369042440L;
/*     */   
/*     */   public DefaultAffineCS(AffineCS cs) {
/*  69 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultAffineCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/*  83 */     super(name, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultAffineCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/*  99 */     super(name, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   public DefaultAffineCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 115 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultAffineCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 132 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   DefaultAffineCS(Map<String, ?> properties, CoordinateSystemAxis[] axis) {
/* 139 */     super(properties, axis);
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleDirection(AxisDirection direction) {
/* 149 */     return !AxisDirection.FUTURE.equals(direction.absolute());
/*     */   }
/*     */   
/*     */   protected boolean isCompatibleUnit(AxisDirection direction, Unit<?> unit) {
/* 161 */     return (SI.METER.isCompatible(unit) || Unit.ONE.equals(unit));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultAffineCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */