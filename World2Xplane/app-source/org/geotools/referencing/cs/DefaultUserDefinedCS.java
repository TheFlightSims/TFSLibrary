/*     */ package org.geotools.referencing.cs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.UserDefinedCS;
/*     */ 
/*     */ public class DefaultUserDefinedCS extends AbstractCS implements UserDefinedCS {
/*     */   private static final long serialVersionUID = -4904091898305706316L;
/*     */   
/*     */   public DefaultUserDefinedCS(UserDefinedCS cs) {
/*  59 */     super((CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public DefaultUserDefinedCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/*  73 */     super(name, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultUserDefinedCS(String name, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/*  89 */     super(name, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */   
/*     */   public DefaultUserDefinedCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1) {
/* 105 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1 });
/*     */   }
/*     */   
/*     */   public DefaultUserDefinedCS(Map<String, ?> properties, CoordinateSystemAxis axis0, CoordinateSystemAxis axis1, CoordinateSystemAxis axis2) {
/* 123 */     super(properties, new CoordinateSystemAxis[] { axis0, axis1, axis2 });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\cs\DefaultUserDefinedCS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */