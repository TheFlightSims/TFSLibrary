/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.measure.converter.ConversionException;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Duration;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class DateRange extends Range<Date> {
/*     */   private static final long serialVersionUID = -6400011350250757942L;
/*     */   
/*  49 */   private static final Unit<Duration> MILLISECOND = SI.MILLI((Unit)SI.SECOND);
/*     */   
/*     */   public DateRange(Date startTime, Date endTime) {
/*  58 */     super(Date.class, clone(startTime), clone(endTime));
/*     */   }
/*     */   
/*     */   public DateRange(Date startTime, boolean isMinIncluded, Date endTime, boolean isMaxIncluded) {
/*  72 */     super(Date.class, clone(startTime), isMinIncluded, clone(endTime), isMaxIncluded);
/*     */   }
/*     */   
/*     */   public DateRange(MeasurementRange<?> range, Date origin) throws ConversionException {
/*  85 */     this(range, getConverter(range.getUnits()), origin.getTime());
/*     */   }
/*     */   
/*     */   private DateRange(MeasurementRange<?> range, UnitConverter converter, long origin) throws ConversionException {
/*  95 */     super(Date.class, new Date(origin + Math.round(converter.convert(range.getMinimum()))), range.isMinIncluded(), new Date(origin + Math.round(converter.convert(range.getMaximum()))), range.isMaxIncluded());
/*     */   }
/*     */   
/*     */   private static Date clone(Date date) {
/* 104 */     return (date != null) ? (Date)date.clone() : null;
/*     */   }
/*     */   
/*     */   private static UnitConverter getConverter(Unit<?> source) throws ConversionException {
/* 112 */     if (source == null)
/* 113 */       throw new ConversionException(Errors.format(142)); 
/* 115 */     return source.getConverterTo(MILLISECOND);
/*     */   }
/*     */   
/*     */   public Date getMinValue() {
/* 123 */     return clone(super.getMinValue());
/*     */   }
/*     */   
/*     */   public Date getMaxValue() {
/* 131 */     return clone(super.getMaxValue());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\DateRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */