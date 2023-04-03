/*     */ package javax.measure;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import javax.measure.quantity.Quantity;
/*     */ import javax.measure.unit.CompoundUnit;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ 
/*     */ public abstract class MeasureFormat extends Format {
/*     */   public static MeasureFormat getInstance() {
/*  40 */     return DEFAULT;
/*     */   }
/*     */   
/*  43 */   static final NumberUnit DEFAULT = new NumberUnit(NumberFormat.getInstance(), UnitFormat.getInstance());
/*     */   
/*     */   public static MeasureFormat getInstance(NumberFormat numberFormat, UnitFormat unitFormat) {
/*  56 */     return new NumberUnit(numberFormat, unitFormat);
/*     */   }
/*     */   
/*     */   static final class NumberUnit extends MeasureFormat {
/*     */     private final NumberFormat _numberFormat;
/*     */     
/*     */     private final UnitFormat _unitFormat;
/*     */     
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private NumberUnit(NumberFormat numberFormat, UnitFormat unitFormat) {
/*  66 */       this._numberFormat = numberFormat;
/*  67 */       this._unitFormat = unitFormat;
/*     */     }
/*     */     
/*     */     public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
/*  73 */       Measure<?, ?> measure = (Measure<?, ?>)obj;
/*  74 */       Object value = measure.getValue();
/*  75 */       Unit<?> unit = measure.getUnit();
/*  76 */       if (value instanceof Number) {
/*  77 */         if (unit instanceof CompoundUnit)
/*  78 */           return formatCompound(((Number)value).doubleValue(), unit, toAppendTo, pos); 
/*  80 */         this._numberFormat.format(value, toAppendTo, pos);
/*     */       } else {
/*  82 */         toAppendTo.append(value);
/*     */       } 
/*  84 */       if (!measure.getUnit().equals(Unit.ONE)) {
/*  85 */         toAppendTo.append(' ');
/*  86 */         this._unitFormat.format(unit, toAppendTo, pos);
/*     */       } 
/*  88 */       return toAppendTo;
/*     */     }
/*     */     
/*     */     StringBuffer formatCompound(double value, Unit<?> unit, StringBuffer toAppendTo, FieldPosition pos) {
/*  94 */       if (!(unit instanceof CompoundUnit)) {
/*  95 */         toAppendTo.append((long)value);
/*  96 */         return this._unitFormat.format(unit, toAppendTo, pos);
/*     */       } 
/*  98 */       Unit<?> high = ((CompoundUnit)unit).getHigher();
/*  99 */       Unit<?> low = ((CompoundUnit)unit).getLower();
/* 100 */       long highValue = (long)low.getConverterTo(high).convert(value);
/* 101 */       double lowValue = value - high.getConverterTo(low).convert(highValue);
/* 103 */       formatCompound(highValue, high, toAppendTo, pos);
/* 104 */       formatCompound(lowValue, low, toAppendTo, pos);
/* 105 */       return toAppendTo;
/*     */     }
/*     */     
/*     */     public Object parseObject(String source, ParsePosition pos) {
/* 110 */       int start = pos.getIndex();
/*     */       try {
/* 112 */         int i = start;
/* 113 */         Number value = this._numberFormat.parse(source, pos);
/* 114 */         if (i == pos.getIndex())
/* 115 */           return null; 
/* 116 */         i = pos.getIndex();
/* 117 */         if (i >= source.length())
/* 118 */           return measureOf(value, Unit.ONE); 
/* 119 */         boolean isCompound = !Character.isWhitespace(source.charAt(i));
/* 120 */         if (isCompound)
/* 121 */           return parseCompound(value, source, pos); 
/* 122 */         if (++i >= source.length())
/* 123 */           return measureOf(value, Unit.ONE); 
/* 124 */         pos.setIndex(i);
/* 125 */         Unit<?> unit = this._unitFormat.parseProductUnit(source, pos);
/* 126 */         return measureOf(value, unit);
/* 127 */       } catch (ParseException e) {
/* 128 */         pos.setIndex(start);
/* 129 */         pos.setErrorIndex(e.getErrorOffset());
/* 130 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Object parseCompound(Number highValue, String source, ParsePosition pos) throws ParseException {
/* 137 */       Unit high = this._unitFormat.parseSingleUnit(source, pos);
/* 138 */       int i = pos.getIndex();
/* 139 */       if (i >= source.length() || Character.isWhitespace(source.charAt(i)))
/* 141 */         return measureOf(highValue, high); 
/* 142 */       Measure lowMeasure = (Measure)parseObject(source, pos);
/* 143 */       Unit<Quantity> unit = lowMeasure.getUnit();
/* 144 */       long l = lowMeasure.longValue(unit) + (long)high.getConverterTo(unit).convert(highValue.longValue());
/* 147 */       return Measure.valueOf(l, unit);
/*     */     }
/*     */     
/*     */     private static Measure measureOf(Number value, Unit<Quantity> unit) {
/* 152 */       if (value instanceof Double)
/* 153 */         return Measure.valueOf(value.doubleValue(), unit); 
/* 154 */       if (value instanceof Long)
/* 155 */         return Measure.valueOf(value.longValue(), unit); 
/* 156 */       if (value instanceof Float)
/* 157 */         return Measure.valueOf(value.floatValue(), unit); 
/* 158 */       if (value instanceof Integer)
/* 159 */         return Measure.valueOf(value.intValue(), unit); 
/* 160 */       if (value instanceof BigDecimal)
/* 161 */         return DecimalMeasure.valueOf((BigDecimal)value, unit); 
/* 163 */       return Measure.valueOf(value.doubleValue(), unit);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measure\MeasureFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */