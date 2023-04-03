/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Duration;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.cs.DefaultTimeCS;
/*     */ import org.geotools.referencing.datum.DefaultTemporalDatum;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.crs.TemporalCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ 
/*     */ public class DefaultTemporalCRS extends AbstractSingleCRS implements TemporalCRS {
/*     */   private static final long serialVersionUID = 3000119849197222007L;
/*     */   
/*  69 */   public static final DefaultTemporalCRS JULIAN = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.JULIAN, (TimeCS)DefaultTimeCS.DAYS);
/*     */   
/*  82 */   public static final DefaultTemporalCRS MODIFIED_JULIAN = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.MODIFIED_JULIAN, (TimeCS)DefaultTimeCS.DAYS);
/*     */   
/*  95 */   public static final DefaultTemporalCRS TRUNCATED_JULIAN = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.TRUNCATED_JULIAN, (TimeCS)DefaultTimeCS.DAYS);
/*     */   
/* 108 */   public static final DefaultTemporalCRS DUBLIN_JULIAN = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.DUBLIN_JULIAN, (TimeCS)DefaultTimeCS.DAYS);
/*     */   
/* 119 */   public static final DefaultTemporalCRS UNIX = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.UNIX, (TimeCS)DefaultTimeCS.SECONDS);
/*     */   
/* 130 */   public static final DefaultTemporalCRS JAVA = new DefaultTemporalCRS((TemporalDatum)DefaultTemporalDatum.UNIX, (TimeCS)DefaultTimeCS.MILLISECONDS);
/*     */   
/* 138 */   public static Unit<Duration> MILLISECOND = SI.MILLI((Unit)SI.SECOND);
/*     */   
/*     */   private transient UnitConverter toMillis;
/*     */   
/*     */   private transient long origin;
/*     */   
/*     */   public DefaultTemporalCRS(TemporalCRS crs) {
/* 167 */     super((SingleCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultTemporalCRS(TemporalDatum datum, TimeCS cs) {
/* 180 */     this(getProperties((IdentifiedObject)datum), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultTemporalCRS(String name, TemporalDatum datum, TimeCS cs) {
/* 194 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultTemporalCRS(Map<String, ?> properties, TemporalDatum datum, TimeCS cs) {
/* 209 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public static DefaultTemporalCRS wrap(TemporalCRS crs) {
/* 222 */     if (crs == null || crs instanceof DefaultTemporalCRS)
/* 223 */       return (DefaultTemporalCRS)crs; 
/* 225 */     return new DefaultTemporalCRS(crs);
/*     */   }
/*     */   
/*     */   private void initializeConverter() {
/* 232 */     this.origin = ((TemporalDatum)this.datum).getOrigin().getTime();
/* 233 */     this.toMillis = this.coordinateSystem.getAxis(0).getUnit().getConverterTo(MILLISECOND);
/*     */   }
/*     */   
/*     */   public TimeCS getCoordinateSystem() {
/* 241 */     return (TimeCS)super.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public TemporalDatum getDatum() {
/* 249 */     return (TemporalDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public Date toDate(double value) {
/* 260 */     if (this.toMillis == null)
/* 261 */       initializeConverter(); 
/* 263 */     return new Date(Math.round(this.toMillis.convert(value)) + this.origin);
/*     */   }
/*     */   
/*     */   public double toValue(Date time) {
/* 274 */     if (this.toMillis == null)
/* 275 */       initializeConverter(); 
/* 277 */     return this.toMillis.inverse().convert((time.getTime() - this.origin));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 288 */     return 0x86FFB877 ^ super.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultTemporalCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */