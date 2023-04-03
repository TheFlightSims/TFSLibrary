/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.measure.Measure;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.LinearCS;
/*     */ 
/*     */ public class DefaultLinearCS extends AbstractCS implements LinearCS {
/*     */   private static final long serialVersionUID = -6890723478287625763L;
/*     */   
/*     */   public DefaultLinearCS(LinearCS cs) {
/*  65 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultLinearCS(String name, CoordinateSystemAxis axis) {
/*  75 */     super(name, new CoordinateSystemAxis[] { axis });
/*     */   }
/*     */   
/*     */   public DefaultLinearCS(Map<String, ?> properties, CoordinateSystemAxis axis) {
/*  87 */     super(properties, new CoordinateSystemAxis[] { axis });
/*     */   }
/*     */   
/*     */   public Measure distance(double[] coord1, double[] coord2) throws MismatchedDimensionException {
/* 102 */     ensureDimensionMatch("coord1", coord1);
/* 103 */     ensureDimensionMatch("coord2", coord2);
/* 104 */     return new Measure(Math.abs(coord1[0] - coord2[0]), getDistanceUnit());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultLinearCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */