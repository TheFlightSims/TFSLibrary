/*     */ package org.geotools.measure;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.Format;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*     */ import org.geotools.referencing.crs.DefaultTemporalCRS;
/*     */ import org.geotools.resources.CRSUtilities;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ 
/*     */ public class CoordinateFormat extends Format {
/*     */   private static final long serialVersionUID = 8235685097881260737L;
/*     */   
/*     */   private CoordinateReferenceSystem crs;
/*     */   
/*     */   private String separator;
/*     */   
/*     */   private Format[] formats;
/*     */   
/*     */   private transient UnitFormat unitFormat;
/*     */   
/*     */   private byte[] types;
/*     */   
/*     */   private static final byte LONGITUDE = 1;
/*     */   
/*     */   private static final byte LATITUDE = 2;
/*     */   
/*     */   private static final byte ANGLE = 3;
/*     */   
/*     */   private static final byte DATE = 4;
/*     */   
/*     */   private static final byte TIME = 5;
/*     */   
/*     */   private long[] epochs;
/*     */   
/*     */   private UnitConverter[] toMillis;
/*     */   
/* 129 */   private final FieldPosition dummy = new FieldPosition(0);
/*     */   
/*     */   private final Locale locale;
/*     */   
/*     */   public CoordinateFormat() {
/* 141 */     this(Locale.getDefault());
/*     */   }
/*     */   
/*     */   public CoordinateFormat(Locale locale) {
/* 151 */     this(locale, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/*     */   }
/*     */   
/*     */   public CoordinateFormat(Locale locale, CoordinateReferenceSystem crs) {
/* 161 */     this.locale = locale;
/* 162 */     this.separator = " ";
/* 163 */     setCoordinateReferenceSystem(crs);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/* 172 */     return this.crs;
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) {
/* 182 */     if (CRS.equalsIgnoreMetadata(this.crs, this.crs = crs))
/*     */       return; 
/* 185 */     Format numberFormat = null;
/* 186 */     Format angleFormat = null;
/* 187 */     Format dateFormat = null;
/* 193 */     if (this.formats != null)
/* 194 */       for (int j = this.formats.length; --j >= 0; ) {
/* 195 */         Format format = this.formats[j];
/* 196 */         if (format instanceof NumberFormat) {
/* 197 */           numberFormat = format;
/*     */           continue;
/*     */         } 
/* 198 */         if (format instanceof AngleFormat) {
/* 199 */           angleFormat = format;
/*     */           continue;
/*     */         } 
/* 200 */         if (format instanceof DateFormat)
/* 201 */           dateFormat = format; 
/*     */       }  
/* 209 */     if (crs == null) {
/* 210 */       if (numberFormat == null)
/* 211 */         numberFormat = NumberFormat.getNumberInstance(this.locale); 
/* 213 */       this.types = new byte[1];
/* 214 */       this.formats = new Format[] { numberFormat };
/*     */       return;
/*     */     } 
/* 221 */     CoordinateSystem cs = crs.getCoordinateSystem();
/* 222 */     this.epochs = null;
/* 223 */     this.toMillis = null;
/* 224 */     this.formats = new Format[cs.getDimension()];
/* 225 */     this.types = new byte[this.formats.length];
/* 226 */     int i = 0;
/*     */     while (true) {
/* 226 */       if (i < this.formats.length) {
/* 227 */         Unit<?> unit = cs.getAxis(i).getUnit();
/* 231 */         if (NonSI.DEGREE_ANGLE.equals(unit)) {
/* 232 */           if (angleFormat == null)
/* 233 */             angleFormat = new AngleFormat("DD°MM.m'", this.locale); 
/* 235 */           this.formats[i] = angleFormat;
/* 236 */           AxisDirection axis = cs.getAxis(i).getDirection().absolute();
/* 237 */           if (AxisDirection.EAST.equals(axis)) {
/* 238 */             this.types[i] = 1;
/* 239 */           } else if (AxisDirection.NORTH.equals(axis)) {
/* 240 */             this.types[i] = 2;
/*     */           } else {
/* 242 */             this.types[i] = 3;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 249 */         if (SI.SECOND.isCompatible(unit)) {
/* 250 */           Datum datum = CRSUtilities.getDatum(CRSUtilities.getSubCRS(crs, i, i + 1));
/* 251 */           if (datum instanceof TemporalDatum) {
/* 252 */             if (this.toMillis == null) {
/* 253 */               this.toMillis = new UnitConverter[this.formats.length];
/* 254 */               this.epochs = new long[this.formats.length];
/*     */             } 
/* 256 */             this.toMillis[i] = unit.getConverterTo(DefaultTemporalCRS.MILLISECOND);
/* 257 */             this.epochs[i] = ((TemporalDatum)datum).getOrigin().getTime();
/* 258 */             if (dateFormat == null)
/* 259 */               dateFormat = DateFormat.getDateInstance(2, this.locale); 
/* 261 */             this.formats[i] = dateFormat;
/* 262 */             this.types[i] = 4;
/*     */           } else {
/* 265 */             this.types[i] = 5;
/* 272 */             if (numberFormat == null)
/* 273 */               numberFormat = NumberFormat.getNumberInstance(this.locale); 
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } else {
/*     */         break;
/*     */       } 
/*     */       if (numberFormat == null)
/* 273 */         numberFormat = NumberFormat.getNumberInstance(this.locale); 
/*     */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 288 */     return this.separator;
/*     */   }
/*     */   
/*     */   public void setSeparator(String separator) {
/* 299 */     this.separator = separator;
/*     */   }
/*     */   
/*     */   public void setNumberPattern(String pattern) {
/* 310 */     Format lastFormat = null;
/* 311 */     for (int i = 0; i < this.formats.length; i++) {
/* 312 */       Format format = this.formats[i];
/* 313 */       if (format != lastFormat && format instanceof DecimalFormat) {
/* 314 */         ((DecimalFormat)format).applyPattern(pattern);
/* 315 */         lastFormat = format;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAnglePattern(String pattern) {
/* 329 */     Format lastFormat = null;
/* 330 */     for (int i = 0; i < this.formats.length; i++) {
/* 331 */       Format format = this.formats[i];
/* 332 */       if (format != lastFormat && format instanceof AngleFormat) {
/* 333 */         ((AngleFormat)format).applyPattern(pattern);
/* 334 */         lastFormat = format;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDatePattern(String pattern) {
/* 347 */     Format lastFormat = null;
/* 348 */     for (int i = 0; i < this.formats.length; i++) {
/* 349 */       Format format = this.formats[i];
/* 350 */       if (format != lastFormat && format instanceof SimpleDateFormat) {
/* 351 */         ((SimpleDateFormat)format).applyPattern(pattern);
/* 352 */         lastFormat = format;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTimeZone(TimeZone timezone) {
/* 365 */     Format lastFormat = null;
/* 366 */     for (int i = 0; i < this.formats.length; i++) {
/* 367 */       Format format = this.formats[i];
/* 368 */       if (format != lastFormat && format instanceof DateFormat) {
/* 369 */         ((DateFormat)format).setTimeZone(timezone);
/* 370 */         lastFormat = format;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Format getFormat(int dimension) throws IndexOutOfBoundsException {
/* 388 */     return this.formats[dimension];
/*     */   }
/*     */   
/*     */   public String format(DirectPosition point) {
/* 401 */     return format(point, new StringBuffer(), (FieldPosition)null).toString();
/*     */   }
/*     */   
/*     */   public StringBuffer format(DirectPosition point, StringBuffer toAppendTo, FieldPosition position) throws IllegalArgumentException {
/*     */     CoordinateSystem cs;
/* 422 */     int dimension = point.getDimension();
/* 424 */     if (this.crs != null) {
/* 425 */       if (dimension != this.formats.length)
/* 426 */         throw new MismatchedDimensionException(Errors.format(94, "point", Integer.valueOf(dimension), Integer.valueOf(this.formats.length))); 
/* 429 */       cs = this.crs.getCoordinateSystem();
/*     */     } else {
/* 431 */       cs = null;
/*     */     } 
/* 433 */     for (int i = 0; i < dimension; i++) {
/*     */       Object object;
/*     */       CoordinateSystemAxis axis;
/*     */       long offset;
/* 434 */       double value = point.getOrdinate(i);
/* 435 */       int fi = Math.min(i, this.formats.length - 1);
/* 437 */       byte type = this.types[fi];
/* 438 */       switch (type) {
/*     */         default:
/* 439 */           object = Double.valueOf(value);
/*     */           break;
/*     */         case 1:
/* 440 */           object = new Longitude(value);
/*     */           break;
/*     */         case 2:
/* 441 */           object = new Latitude(value);
/*     */           break;
/*     */         case 3:
/* 442 */           object = new Angle(value);
/*     */           break;
/*     */         case 4:
/* 444 */           axis = cs.getAxis(i);
/* 445 */           offset = Math.round(this.toMillis[fi].convert(value));
/* 446 */           if (AxisDirection.PAST.equals(axis.getDirection()))
/* 447 */             offset = -offset; 
/* 449 */           object = new Date(this.epochs[fi] + offset);
/*     */           break;
/*     */       } 
/* 453 */       if (i != 0)
/* 454 */         toAppendTo.append(this.separator); 
/* 456 */       this.formats[fi].format(object, toAppendTo, this.dummy);
/* 460 */       if (type == 0 && cs != null) {
/* 461 */         Unit<?> unit = cs.getAxis(i).getUnit();
/* 462 */         if (unit != null) {
/* 463 */           if (this.unitFormat == null)
/* 464 */             this.unitFormat = UnitFormat.getInstance(); 
/* 466 */           String asText = this.unitFormat.format(unit);
/* 467 */           if (asText.length() != 0) {
/* 468 */             toAppendTo.append(' ');
/* 469 */             toAppendTo.append(unit);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 474 */     return toAppendTo;
/*     */   }
/*     */   
/*     */   public StringBuffer format(Object object, StringBuffer toAppendTo, FieldPosition position) throws IllegalArgumentException {
/* 496 */     if (object instanceof DirectPosition)
/* 497 */       return format((DirectPosition)object, toAppendTo, position); 
/* 499 */     throw new IllegalArgumentException(String.valueOf(object));
/*     */   }
/*     */   
/*     */   public DirectPosition parseObject(String source, ParsePosition position) {
/* 510 */     throw new UnsupportedOperationException("DirectPosition parsing not yet implemented.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\CoordinateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */