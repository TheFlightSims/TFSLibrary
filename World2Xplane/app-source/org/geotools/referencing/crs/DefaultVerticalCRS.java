/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.cs.DefaultVerticalCS;
/*     */ import org.geotools.referencing.datum.DefaultVerticalDatum;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ 
/*     */ public class DefaultVerticalCRS extends AbstractSingleCRS implements VerticalCRS {
/*     */   private static final long serialVersionUID = 3565878468719941800L;
/*     */   
/*  75 */   public static final DefaultVerticalCRS ELLIPSOIDAL_HEIGHT = new DefaultVerticalCRS(getProperties((IdentifiedObject)DefaultVerticalCS.ELLIPSOIDAL_HEIGHT), (VerticalDatum)DefaultVerticalDatum.ELLIPSOIDAL, (VerticalCS)DefaultVerticalCS.ELLIPSOIDAL_HEIGHT);
/*     */   
/*  87 */   public static final DefaultVerticalCRS GEOIDAL_HEIGHT = new DefaultVerticalCRS(getProperties((IdentifiedObject)DefaultVerticalCS.GRAVITY_RELATED_HEIGHT), (VerticalDatum)DefaultVerticalDatum.GEOIDAL, (VerticalCS)DefaultVerticalCS.GRAVITY_RELATED_HEIGHT);
/*     */   
/*     */   public DefaultVerticalCRS(VerticalCRS crs) {
/* 101 */     super((SingleCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultVerticalCRS(VerticalDatum datum, VerticalCS cs) {
/* 114 */     this(getProperties((IdentifiedObject)datum), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultVerticalCRS(String name, VerticalDatum datum, VerticalCS cs) {
/* 128 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultVerticalCRS(Map<String, ?> properties, VerticalDatum datum, VerticalCS cs) {
/* 143 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public VerticalCS getCoordinateSystem() {
/* 151 */     return (VerticalCS)super.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public VerticalDatum getDatum() {
/* 159 */     return (VerticalDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 170 */     return 0x104418A8 ^ super.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 183 */     formatDefaultWKT(formatter);
/* 184 */     return "VERT_CS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultVerticalCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */