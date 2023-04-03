/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ 
/*     */ public class DefaultEngineeringDatum extends AbstractDatum implements EngineeringDatum {
/*     */   private static final long serialVersionUID = 1498304918725248637L;
/*     */   
/*  59 */   public static final DefaultEngineeringDatum UNKNOW = new DefaultEngineeringDatum(name(230));
/*     */   
/*  69 */   public static final DefaultEngineeringDatum UNKNOWN = new DefaultEngineeringDatum(name(252));
/*     */   
/*     */   public DefaultEngineeringDatum(EngineeringDatum datum) {
/*  82 */     super((Datum)datum);
/*     */   }
/*     */   
/*     */   public DefaultEngineeringDatum(String name) {
/*  91 */     this(Collections.singletonMap("name", name));
/*     */   }
/*     */   
/*     */   public DefaultEngineeringDatum(Map<String, ?> properties) {
/* 101 */     super(properties);
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 114 */     if (object == this)
/* 115 */       return true; 
/* 117 */     return super.equals(object, compareMetadata);
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 130 */     super.formatWKT(formatter);
/* 131 */     return "LOCAL_DATUM";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultEngineeringDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */