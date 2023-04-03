/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*     */ import org.geotools.referencing.datum.DefaultEngineeringDatum;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ 
/*     */ public class DefaultEngineeringCRS extends AbstractSingleCRS implements EngineeringCRS {
/*     */   private static final long serialVersionUID = 6695541732063382701L;
/*     */   
/*     */   protected boolean wildcard;
/*     */   
/*  88 */   public static final DefaultEngineeringCRS CARTESIAN_2D = new DefaultEngineeringCRS(15, (CoordinateSystem)DefaultCartesianCS.GENERIC_2D, false);
/*     */   
/* 100 */   public static final DefaultEngineeringCRS CARTESIAN_3D = new DefaultEngineeringCRS(16, (CoordinateSystem)DefaultCartesianCS.GENERIC_3D, false);
/*     */   
/* 115 */   public static final DefaultEngineeringCRS GENERIC_2D = new DefaultEngineeringCRS(75, (CoordinateSystem)DefaultCartesianCS.GENERIC_2D, true);
/*     */   
/* 131 */   public static final DefaultEngineeringCRS GENERIC_3D = new DefaultEngineeringCRS(76, (CoordinateSystem)DefaultCartesianCS.GENERIC_3D, true);
/*     */   
/*     */   DefaultEngineeringCRS(int key, CoordinateSystem cs, boolean wildcard) {
/* 136 */     this(name(key), (EngineeringDatum)DefaultEngineeringDatum.UNKNOWN, cs);
/* 137 */     this.wildcard = wildcard;
/*     */   }
/*     */   
/*     */   public DefaultEngineeringCRS(EngineeringCRS crs) {
/* 152 */     super((SingleCRS)crs);
/* 153 */     if (crs instanceof DefaultEngineeringCRS)
/* 154 */       this.wildcard = ((DefaultEngineeringCRS)crs).wildcard; 
/*     */   }
/*     */   
/*     */   public DefaultEngineeringCRS(String name, EngineeringDatum datum, CoordinateSystem cs) {
/* 169 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs) {
/* 184 */     super(properties, (Datum)datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs, boolean wildcard) {
/* 201 */     super(properties, (Datum)datum, cs);
/* 202 */     this.wildcard = wildcard;
/*     */   }
/*     */   
/*     */   public EngineeringDatum getDatum() {
/* 210 */     return (EngineeringDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 221 */     return 0x16710CAD ^ super.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 234 */     formatDefaultWKT(formatter);
/* 235 */     return "LOCAL_CS";
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 245 */     if (super.equals(object, compareMetadata)) {
/* 246 */       if (compareMetadata)
/* 248 */         return true; 
/* 250 */       DefaultEngineeringCRS that = (DefaultEngineeringCRS)object;
/* 251 */       return (this.wildcard == that.wildcard);
/*     */     } 
/* 253 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWildcard() {
/* 262 */     return this.wildcard;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultEngineeringCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */