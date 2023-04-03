/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultTemporalDatum extends AbstractDatum implements TemporalDatum {
/*     */   private static final long serialVersionUID = 3357241732140076884L;
/*     */   
/*  56 */   public static final DefaultTemporalDatum JULIAN = new DefaultTemporalDatum(name(117), new Date(-210866760000000L));
/*     */   
/*  68 */   public static final DefaultTemporalDatum MODIFIED_JULIAN = new DefaultTemporalDatum(name(142), new Date(-3506716800000L));
/*     */   
/*  80 */   public static final DefaultTemporalDatum TRUNCATED_JULIAN = new DefaultTemporalDatum(name(227), new Date(-50716800000L));
/*     */   
/*  92 */   public static final DefaultTemporalDatum DUBLIN_JULIAN = new DefaultTemporalDatum(name(51), new Date(-2209032000000L));
/*     */   
/* 101 */   public static final DefaultTemporalDatum UNIX = new DefaultTemporalDatum("UNIX", new Date(0L));
/*     */   
/*     */   private final long origin;
/*     */   
/*     */   public DefaultTemporalDatum(TemporalDatum datum) {
/* 119 */     super((Datum)datum);
/* 120 */     this.origin = datum.getOrigin().getTime();
/*     */   }
/*     */   
/*     */   public DefaultTemporalDatum(String name, Date origin) {
/* 130 */     this(Collections.singletonMap("name", name), origin);
/*     */   }
/*     */   
/*     */   public DefaultTemporalDatum(Map<String, ?> properties, Date origin) {
/* 141 */     super(properties);
/* 142 */     ensureNonNull("origin", origin);
/* 143 */     this.origin = origin.getTime();
/*     */   }
/*     */   
/*     */   public Date getOrigin() {
/* 152 */     return new Date(this.origin);
/*     */   }
/*     */   
/*     */   public InternationalString getAnchorPoint() {
/* 160 */     return super.getAnchorPoint();
/*     */   }
/*     */   
/*     */   public Date getRealizationEpoch() {
/* 168 */     return super.getRealizationEpoch();
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 181 */     if (object == this)
/* 182 */       return true; 
/* 184 */     if (super.equals(object, compareMetadata)) {
/* 185 */       DefaultTemporalDatum that = (DefaultTemporalDatum)object;
/* 186 */       return (this.origin == that.origin);
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 203 */     return super.hashCode() ^ (int)this.origin ^ (int)(this.origin >>> 32L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultTemporalDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */