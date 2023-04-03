/*     */ package org.geotools.temporal.reference;
/*     */ 
/*     */ import java.util.Date;
/*     */ import org.geotools.temporal.object.DefaultTemporalCoordinate;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.temporal.TemporalCoordinate;
/*     */ import org.opengis.temporal.TemporalCoordinateSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultTemporalCoordinateSystem extends DefaultTemporalReferenceSystem implements TemporalCoordinateSystem {
/*     */   private Date origin;
/*     */   
/*     */   private InternationalString interval;
/*     */   
/*     */   public DefaultTemporalCoordinateSystem(ReferenceIdentifier name, Extent domainOfValidity, Date origin, InternationalString interval) {
/*  49 */     super(name, domainOfValidity);
/*  50 */     this.origin = origin;
/*  51 */     this.interval = interval;
/*     */   }
/*     */   
/*     */   public void setOrigin(Date origin) {
/*  55 */     this.origin = origin;
/*     */   }
/*     */   
/*     */   public void setInterval(InternationalString interval) {
/*  59 */     this.interval = interval;
/*     */   }
/*     */   
/*     */   public Date getOrigin() {
/*  63 */     return this.origin;
/*     */   }
/*     */   
/*     */   public InternationalString getInterval() {
/*  67 */     return this.interval;
/*     */   }
/*     */   
/*     */   public Date transformCoord(TemporalCoordinate c_value) {
/*  77 */     long yearMS = 31536000000L;
/*  78 */     long monthMS = 2628000000L;
/*  79 */     long weekMS = 604800000L;
/*  80 */     long dayMS = 86400000L;
/*  81 */     long hourMS = 3600000L;
/*  82 */     long minMS = 60000L;
/*  83 */     long secondMS = 1000L;
/*  84 */     DefaultTemporalCoordinate value = (DefaultTemporalCoordinate)c_value;
/*  85 */     Number f = Integer.valueOf(0);
/*  86 */     if (value.getFrame() != null && value.getFrame() instanceof TemporalCoordinateSystem) {
/*  87 */       if (value.getCoordinateValue() != null) {
/*  88 */         float n = value.getCoordinateValue().floatValue();
/*  89 */         if (this.interval.toString().equals("year")) {
/*  90 */           f = Float.valueOf(n * 3.1536E10F);
/*  91 */         } else if (this.interval.toString().equals("month")) {
/*  92 */           f = Float.valueOf(n * 2.628E9F);
/*  93 */         } else if (this.interval.toString().equals("week")) {
/*  94 */           f = Float.valueOf(n * 6.048E8F);
/*  95 */         } else if (this.interval.toString().equals("day")) {
/*  96 */           f = Float.valueOf(n * 8.64E7F);
/*  97 */         } else if (this.interval.toString().equals("hour")) {
/*  98 */           f = Float.valueOf(n * 3600000.0F);
/*  99 */         } else if (this.interval.toString().equals("minute")) {
/* 100 */           f = Float.valueOf(n * 60000.0F);
/* 101 */         } else if (this.interval.toString().equals("second")) {
/* 102 */           f = Float.valueOf(n * 1000.0F);
/* 103 */         } else if (this.interval.toString().equals("millisecond")) {
/* 104 */           f = Float.valueOf(n);
/*     */         } else {
/* 106 */           throw new IllegalArgumentException("The name of a single unit of measure used as the base interval for the scale in this current TemporalCoordinateSystem is not supported !");
/*     */         } 
/* 108 */         Date response = new Date(this.origin.getTime() + f.longValue());
/* 109 */         return response;
/*     */       } 
/* 111 */       return null;
/*     */     } 
/* 114 */     throw new IllegalArgumentException("The TemporalCoordinate argument must be a TemporalCoordinate ! ");
/*     */   }
/*     */   
/*     */   public TemporalCoordinate transformDateTime(Date dateTime) {
/* 126 */     long yearMS = 31536000000L;
/* 127 */     long monthMS = 2628000000L;
/* 128 */     long weekMS = 604800000L;
/* 129 */     long dayMS = 86400000L;
/* 130 */     long hourMS = 3600000L;
/* 131 */     long minMS = 60000L;
/* 132 */     long secondMS = 1000L;
/* 134 */     Number coordinateValue = Long.valueOf(Math.abs(dateTime.getTime() - this.origin.getTime()));
/* 135 */     if (this.interval.toString().equals("year")) {
/* 136 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 3.1536E10F);
/* 137 */     } else if (this.interval.toString().equals("month")) {
/* 138 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 2.628E9F);
/* 139 */     } else if (this.interval.toString().equals("week")) {
/* 140 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 6.048E8F);
/* 141 */     } else if (this.interval.toString().equals("day")) {
/* 142 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 8.64E7F);
/* 143 */     } else if (this.interval.toString().equals("hour")) {
/* 144 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 3600000.0F);
/* 145 */     } else if (this.interval.toString().equals("minute")) {
/* 146 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 60000.0F);
/* 147 */     } else if (this.interval.toString().equals("second")) {
/* 148 */       coordinateValue = Float.valueOf((float)coordinateValue.longValue() / 1000.0F);
/*     */     } 
/* 150 */     return (TemporalCoordinate)new DefaultTemporalCoordinate(this, null, coordinateValue);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 156 */     if (object == this)
/* 157 */       return true; 
/* 159 */     if (object instanceof DefaultTemporalCoordinateSystem && super.equals(object) && 
/* 160 */       object instanceof DefaultTemporalCoordinateSystem) {
/* 161 */       DefaultTemporalCoordinateSystem that = (DefaultTemporalCoordinateSystem)object;
/* 163 */       return (Utilities.equals(this.interval, that.interval) && Utilities.equals(this.origin, that.origin));
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 172 */     int hash = 5;
/* 173 */     hash = 37 * hash + ((this.interval != null) ? this.interval.hashCode() : 0);
/* 174 */     hash = 37 * hash + ((this.origin != null) ? this.origin.hashCode() : 0);
/* 175 */     return hash;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 180 */     StringBuilder s = (new StringBuilder("TemporalCoordinateSystem:")).append('\n');
/* 181 */     if (this.interval != null)
/* 182 */       s.append("interval:").append((CharSequence)this.interval).append('\n'); 
/* 184 */     if (this.origin != null)
/* 185 */       s.append("origin:").append(this.origin).append('\n'); 
/* 187 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\reference\DefaultTemporalCoordinateSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */