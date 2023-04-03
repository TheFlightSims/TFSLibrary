/*      */ package org.geotools.measure;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.text.FieldPosition;
/*      */ import java.text.Format;
/*      */ import java.text.ParseException;
/*      */ import java.text.ParsePosition;
/*      */ import java.util.Locale;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.math.XMath;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.geotools.util.logging.LoggedFormat;
/*      */ 
/*      */ public class AngleFormat extends Format {
/*      */   private static final long serialVersionUID = 4320403817210439764L;
/*      */   
/*      */   private static final char NORTH = 'N';
/*      */   
/*      */   private static final char SOUTH = 'S';
/*      */   
/*      */   private static final char EAST = 'E';
/*      */   
/*      */   private static final char WEST = 'W';
/*      */   
/*      */   static final int LONGITUDE = 0;
/*      */   
/*      */   static final int LATITUDE = 1;
/*      */   
/*      */   static final int ALTITUDE = 2;
/*      */   
/*      */   private static final int PREFIX_FIELD = -1;
/*      */   
/*      */   public static final int DEGREES_FIELD = 0;
/*      */   
/*      */   public static final int MINUTES_FIELD = 1;
/*      */   
/*      */   public static final int SECONDS_FIELD = 2;
/*      */   
/*      */   public static final int HEMISPHERE_FIELD = 3;
/*      */   
/*  181 */   private static final char[] SYMBOLS = new char[] { 'D', 'M', 'S' };
/*      */   
/*  190 */   private int width0 = 1;
/*      */   
/*  190 */   private int width1 = 2;
/*      */   
/*  190 */   private int width2 = 0;
/*      */   
/*  190 */   private int widthDecimal = 0;
/*      */   
/*  197 */   private String prefix = null;
/*      */   
/*  197 */   private String suffix0 = "°";
/*      */   
/*  197 */   private String suffix1 = "'";
/*      */   
/*  197 */   private String suffix2 = "\"";
/*      */   
/*      */   private boolean decimalSeparator = true;
/*      */   
/*      */   private final DecimalFormat numberFormat;
/*      */   
/*  219 */   private transient FieldPosition dummy = new FieldPosition(0);
/*      */   
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  227 */     in.defaultReadObject();
/*  228 */     this.dummy = new FieldPosition(0);
/*      */   }
/*      */   
/*      */   private int getWidth(int index) {
/*  235 */     switch (index) {
/*      */       case 0:
/*  236 */         return this.width0;
/*      */       case 1:
/*  237 */         return this.width1;
/*      */       case 2:
/*  238 */         return this.width2;
/*      */     } 
/*  239 */     return 0;
/*      */   }
/*      */   
/*      */   private void setWidth(int index, int width) {
/*  249 */     switch (index) {
/*      */       case 0:
/*  250 */         this.width0 = width;
/*  250 */         width = 0;
/*      */       case 1:
/*  251 */         this.width1 = width;
/*  251 */         width = 0;
/*      */       case 2:
/*  252 */         this.width2 = width;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private String getSuffix(int index) {
/*  260 */     switch (index) {
/*      */       case -1:
/*  261 */         return this.prefix;
/*      */       case 0:
/*  262 */         return this.suffix0;
/*      */       case 1:
/*  263 */         return this.suffix1;
/*      */       case 2:
/*  264 */         return this.suffix2;
/*      */     } 
/*  265 */     return null;
/*      */   }
/*      */   
/*      */   private void setSuffix(int index, String s) {
/*  276 */     switch (index) {
/*      */       case -1:
/*  277 */         this.prefix = s;
/*  277 */         s = "°";
/*      */       case 0:
/*  278 */         this.suffix0 = s;
/*  278 */         s = "'";
/*      */       case 1:
/*  279 */         this.suffix1 = s;
/*  279 */         s = "\"";
/*      */       case 2:
/*  280 */         this.suffix2 = s;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static AngleFormat getInstance(Locale locale) {
/*  291 */     return new AngleFormat("D°MM.m'", locale);
/*      */   }
/*      */   
/*      */   public enum RoundingMethod {
/*  310 */     ROUND_HALF_EVEN, ROUND_HALF_UP, ROUND_HALF_DOWN;
/*      */   }
/*      */   
/*  326 */   public static final RoundingMethod DEFAULT_ROUNDING_METHOD = RoundingMethod.ROUND_HALF_EVEN;
/*      */   
/*  331 */   private static RoundingMethod defaultRoundingMethod = DEFAULT_ROUNDING_METHOD;
/*      */   
/*  337 */   private RoundingMethod instanceRoundingMethod = defaultRoundingMethod;
/*      */   
/*      */   public static synchronized void setDefaultRoundingMethod(RoundingMethod method) {
/*  352 */     defaultRoundingMethod = method;
/*      */   }
/*      */   
/*      */   public static synchronized RoundingMethod getDefaultRoundingMethod() {
/*  361 */     return defaultRoundingMethod;
/*      */   }
/*      */   
/*      */   public synchronized void setRoundingMethod(RoundingMethod method) {
/*  376 */     this.instanceRoundingMethod = method;
/*      */   }
/*      */   
/*      */   public static synchronized RoundingMethod getRoundingMethod() {
/*  385 */     return defaultRoundingMethod;
/*      */   }
/*      */   
/*      */   private synchronized double doRounding(double value, int precision) {
/*  398 */     double scale = XMath.pow10(precision);
/*  399 */     double eps = XMath.pow10(-precision - 2);
/*  400 */     double scaledValue = scale * (value + eps);
/*  403 */     if (Double.compare(scaledValue, 9.223372036854776E18D) < 0) {
/*      */       long rounded;
/*  404 */       RoundingMethod rm = this.instanceRoundingMethod;
/*  406 */       if (rm == RoundingMethod.ROUND_HALF_EVEN) {
/*  407 */         double d = scaledValue / 10.0D;
/*  408 */         boolean even = ((int)((d - (int)d) * 10.0D) % 2 == 0);
/*  409 */         rm = even ? RoundingMethod.ROUND_HALF_DOWN : RoundingMethod.ROUND_HALF_UP;
/*      */       } 
/*  412 */       switch (rm) {
/*      */         case ROUND_HALF_DOWN:
/*  414 */           rounded = Math.round(scaledValue - 0.5D);
/*  415 */           return rounded / scale;
/*      */         case ROUND_HALF_UP:
/*  418 */           rounded = Math.round(scaledValue);
/*  419 */           return rounded / scale;
/*      */       } 
/*      */     } 
/*  423 */     Logger logger = Logger.getLogger(AngleFormat.class.getName());
/*  424 */     logger.log(Level.WARNING, String.format("Can't round the value %s to the given precision %d", new Object[] { String.valueOf(value), Integer.valueOf(precision) }));
/*  428 */     return value;
/*      */   }
/*      */   
/*      */   private boolean isOverflow(double value, int field, boolean last) {
/*  451 */     boolean overflow = false;
/*  452 */     if (last)
/*  453 */       value = doRounding(value, this.widthDecimal); 
/*  455 */     if (field != 0 && value == 60.0D)
/*  456 */       overflow = true; 
/*  458 */     if (field == 0 && value >= 360.0D)
/*  459 */       overflow = true; 
/*  462 */     return overflow;
/*      */   }
/*      */   
/*      */   public AngleFormat() {
/*  470 */     this("D°MM.m'");
/*      */   }
/*      */   
/*      */   public AngleFormat(String pattern) throws IllegalArgumentException {
/*  482 */     this(pattern, new DecimalFormatSymbols());
/*      */   }
/*      */   
/*      */   public AngleFormat(String pattern, Locale locale) throws IllegalArgumentException {
/*  495 */     this(pattern, new DecimalFormatSymbols(locale));
/*      */   }
/*      */   
/*      */   public AngleFormat(String pattern, DecimalFormatSymbols symbols) {
/*  511 */     this.numberFormat = new DecimalFormat("#0", symbols);
/*  512 */     applyPattern(pattern);
/*      */   }
/*      */   
/*      */   public synchronized void applyPattern(String pattern) throws IllegalArgumentException {
/*  524 */     this.widthDecimal = 0;
/*  525 */     this.decimalSeparator = true;
/*  526 */     int startPrefix = 0;
/*  527 */     int symbolIndex = 0;
/*  528 */     boolean parseFinished = false;
/*  529 */     int length = pattern.length();
/*  530 */     for (int i = 0; i < length; i++) {
/*  537 */       char c = pattern.charAt(i);
/*  538 */       char upperCaseC = Character.toUpperCase(c);
/*  539 */       for (int field = 0; field < SYMBOLS.length; field++) {
/*  540 */         if (upperCaseC == SYMBOLS[field]) {
/*  549 */           if (c == upperCaseC)
/*  550 */             symbolIndex++; 
/*  552 */           if (field != symbolIndex - 1 || parseFinished) {
/*  553 */             setWidth(0, 1);
/*  554 */             setSuffix(-1, null);
/*  555 */             this.widthDecimal = 0;
/*  556 */             this.decimalSeparator = true;
/*  557 */             throw new IllegalArgumentException(Errors.format(56, pattern));
/*      */           } 
/*  560 */           if (c == upperCaseC) {
/*  567 */             setSuffix(field - 1, (i > startPrefix) ? pattern.substring(startPrefix, i) : null);
/*      */             int w;
/*  568 */             for (w = 1; ++i < length && pattern.charAt(i) == c; w++);
/*  569 */             setWidth(field, w);
/*      */           } else {
/*  576 */             switch (i - startPrefix) {
/*      */               case 0:
/*  578 */                 this.decimalSeparator = false;
/*      */                 break;
/*      */               case 1:
/*  582 */                 if (pattern.charAt(startPrefix) == '.') {
/*  583 */                   this.decimalSeparator = true;
/*      */                   break;
/*      */                 } 
/*      */               default:
/*  589 */                 throw new IllegalArgumentException(Errors.format(56, pattern));
/*      */             } 
/*      */             int w;
/*  593 */             for (w = 1; ++i < length && pattern.charAt(i) == c; w++);
/*  594 */             this.widthDecimal = w;
/*  595 */             parseFinished = true;
/*      */           } 
/*  597 */           startPrefix = i--;
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  602 */     setSuffix(symbolIndex - 1, (startPrefix < length) ? pattern.substring(startPrefix) : null);
/*      */   }
/*      */   
/*      */   public synchronized String toPattern() {
/*  612 */     char symbol = '#';
/*  613 */     StringBuilder buffer = new StringBuilder();
/*  614 */     for (int field = 0; field <= SYMBOLS.length; ) {
/*  615 */       String previousSuffix = getSuffix(field - 1);
/*  616 */       int w = getWidth(field);
/*  617 */       if (w > 0) {
/*  623 */         if (previousSuffix != null)
/*  624 */           buffer.append(previousSuffix); 
/*  626 */         symbol = SYMBOLS[field];
/*      */         while (true) {
/*  627 */           buffer.append(symbol);
/*  628 */           if (--w <= 0)
/*      */             field++; 
/*      */         } 
/*      */       } 
/*  635 */       w = this.widthDecimal;
/*  636 */       if (w > 0) {
/*  637 */         if (this.decimalSeparator)
/*  637 */           buffer.append('.'); 
/*  638 */         symbol = Character.toLowerCase(symbol);
/*      */         do {
/*  639 */           buffer.append(symbol);
/*  640 */         } while (--w > 0);
/*      */       } 
/*  642 */       if (previousSuffix != null)
/*  643 */         buffer.append(previousSuffix); 
/*      */     } 
/*  648 */     return buffer.toString();
/*      */   }
/*      */   
/*      */   public final String format(double angle) {
/*  659 */     return format(angle, new StringBuffer(), (FieldPosition)null).toString();
/*      */   }
/*      */   
/*      */   public synchronized StringBuffer format(double angle, StringBuffer toAppendTo, FieldPosition pos) {
/*      */     int field;
/*  683 */     if (Double.isNaN(angle) || Double.isInfinite(angle))
/*  684 */       return this.numberFormat.format(angle, toAppendTo, (pos != null) ? pos : new FieldPosition(0)); 
/*  687 */     double degrees = angle;
/*  695 */     double minutes = Double.NaN;
/*  696 */     double secondes = Double.NaN;
/*  697 */     if (this.width1 != 0 && !Double.isNaN(angle)) {
/*  698 */       int tmp = (int)degrees;
/*  699 */       minutes = Math.abs(degrees - tmp) * 60.0D;
/*  700 */       degrees = tmp;
/*  701 */       if (minutes < 0.0D || minutes > 60.0D)
/*  703 */         throw new IllegalArgumentException(Errors.format(4, Double.valueOf(angle))); 
/*  705 */       if (this.width2 != 0) {
/*  706 */         tmp = (int)minutes;
/*  707 */         secondes = (minutes - tmp) * 60.0D;
/*  708 */         minutes = tmp;
/*  709 */         if (secondes < 0.0D || secondes > 60.0D)
/*  711 */           throw new IllegalArgumentException(Errors.format(4, Double.valueOf(angle))); 
/*  713 */         tmp = (int)(secondes / 60.0D);
/*  714 */         secondes -= (60 * tmp);
/*  715 */         minutes += tmp;
/*      */       } 
/*  717 */       tmp = (int)(minutes / 60.0D);
/*  718 */       minutes -= (60 * tmp);
/*  719 */       degrees += tmp;
/*      */     } 
/*  727 */     if (this.prefix != null)
/*  728 */       toAppendTo.append(this.prefix); 
/*  731 */     if (pos != null) {
/*  732 */       field = pos.getField();
/*  733 */       pos.setBeginIndex(0);
/*  734 */       pos.setEndIndex(0);
/*      */     } else {
/*  736 */       field = -1;
/*      */     } 
/*  740 */     if (!Double.isNaN(secondes) && isOverflow(secondes, 2, true)) {
/*  742 */       secondes = 0.0D;
/*  744 */       minutes++;
/*      */     } 
/*  747 */     if (!Double.isNaN(minutes) && isOverflow(minutes, 1, (this.width2 == 0))) {
/*  749 */       minutes = 0.0D;
/*  751 */       degrees++;
/*      */     } 
/*  754 */     if (isOverflow(degrees, 0, (this.width1 == 0)))
/*  756 */       degrees -= 360.0D; 
/*  759 */     toAppendTo = formatField(degrees, toAppendTo, (field == 0) ? pos : null, this.width0, (this.width1 == 0), this.suffix0);
/*  762 */     if (!Double.isNaN(minutes))
/*  763 */       toAppendTo = formatField(minutes, toAppendTo, (field == 1) ? pos : null, this.width1, (this.width2 == 0), this.suffix1); 
/*  767 */     if (!Double.isNaN(secondes))
/*  768 */       toAppendTo = formatField(secondes, toAppendTo, (field == 2) ? pos : null, this.width2, true, this.suffix2); 
/*  772 */     return toAppendTo;
/*      */   }
/*      */   
/*      */   private StringBuffer formatField(double value, StringBuffer toAppendTo, FieldPosition pos, int w, boolean last, String s) {
/*  792 */     int startPosition = toAppendTo.length();
/*  793 */     if (!last) {
/*  794 */       this.numberFormat.setMinimumIntegerDigits(w);
/*  795 */       this.numberFormat.setMaximumFractionDigits(0);
/*  796 */       toAppendTo = this.numberFormat.format(value, toAppendTo, this.dummy);
/*      */     } else {
/*  799 */       value = doRounding(value, this.widthDecimal);
/*  801 */       if (this.decimalSeparator) {
/*  802 */         this.numberFormat.setMinimumIntegerDigits(w);
/*  803 */         this.numberFormat.setMinimumFractionDigits(this.widthDecimal);
/*  804 */         this.numberFormat.setMaximumFractionDigits(this.widthDecimal);
/*  805 */         toAppendTo = this.numberFormat.format(value, toAppendTo, this.dummy);
/*      */       } else {
/*  807 */         value *= XMath.pow10(this.widthDecimal);
/*  808 */         this.numberFormat.setMaximumFractionDigits(0);
/*  809 */         this.numberFormat.setMinimumIntegerDigits(w + this.widthDecimal);
/*  810 */         toAppendTo = this.numberFormat.format(value, toAppendTo, this.dummy);
/*      */       } 
/*      */     } 
/*  813 */     if (s != null)
/*  814 */       toAppendTo.append(s); 
/*  816 */     if (pos != null) {
/*  817 */       pos.setBeginIndex(startPosition);
/*  818 */       pos.setEndIndex(toAppendTo.length() - 1);
/*      */     } 
/*  820 */     return toAppendTo;
/*      */   }
/*      */   
/*      */   public synchronized StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) throws IllegalArgumentException {
/*  861 */     if (obj instanceof Latitude)
/*  862 */       return format(((Latitude)obj).degrees(), toAppendTo, pos, 'N', 'S'); 
/*  864 */     if (obj instanceof Longitude)
/*  865 */       return format(((Longitude)obj).degrees(), toAppendTo, pos, 'E', 'W'); 
/*  867 */     if (obj instanceof Angle)
/*  868 */       return format(((Angle)obj).degrees(), toAppendTo, pos); 
/*  870 */     if (obj instanceof Number) {
/*  871 */       this.numberFormat.setMinimumIntegerDigits(1);
/*  872 */       this.numberFormat.setMinimumFractionDigits(0);
/*  873 */       this.numberFormat.setMaximumFractionDigits(2);
/*  874 */       return this.numberFormat.format(obj, toAppendTo, (pos != null) ? pos : this.dummy);
/*      */     } 
/*  876 */     throw new IllegalArgumentException(Errors.format(119, Classes.getClass(obj)));
/*      */   }
/*      */   
/*      */   synchronized StringBuffer format(double number, int type, StringBuffer toAppendTo, FieldPosition pos) {
/*  903 */     switch (type) {
/*      */       default:
/*  904 */         throw new IllegalArgumentException(Integer.toString(type));
/*      */       case 1:
/*  905 */         return format(number, toAppendTo, pos, 'N', 'S');
/*      */       case 0:
/*  906 */         return format(number, toAppendTo, pos, 'E', 'W');
/*      */       case 2:
/*      */         break;
/*      */     } 
/*  908 */     this.numberFormat.setMinimumIntegerDigits(1);
/*  909 */     this.numberFormat.setMinimumFractionDigits(0);
/*  910 */     this.numberFormat.setMaximumFractionDigits(2);
/*  911 */     return this.numberFormat.format(number, toAppendTo, (pos != null) ? pos : this.dummy);
/*      */   }
/*      */   
/*      */   private StringBuffer format(double angle, StringBuffer toAppendTo, FieldPosition pos, char north, char south) {
/*  940 */     toAppendTo = format(Math.abs(angle), toAppendTo, pos);
/*  941 */     int start = toAppendTo.length();
/*  942 */     toAppendTo.append((angle < 0.0D) ? south : north);
/*  943 */     if (pos != null && pos.getField() == 3) {
/*  944 */       pos.setBeginIndex(start);
/*  945 */       pos.setEndIndex(toAppendTo.length() - 1);
/*      */     } 
/*  947 */     return toAppendTo;
/*      */   }
/*      */   
/*      */   private int skipSuffix(String source, ParsePosition pos, int field) {
/*  980 */     int length = source.length();
/*  981 */     int start = pos.getIndex();
/*  982 */     for (int j = SYMBOLS.length; j >= 0; j--) {
/*  983 */       int index = start;
/*  984 */       String toSkip = getSuffix(field);
/*  985 */       if (toSkip != null) {
/*  986 */         int toSkipLength = toSkip.length();
/*      */         do {
/*  988 */           if (source.regionMatches(index, toSkip, 0, toSkipLength)) {
/*  989 */             pos.setIndex(index + toSkipLength);
/*  990 */             return field;
/*      */           } 
/*  993 */         } while (index < length && Character.isSpaceChar(source.charAt(index++)));
/*      */       } 
/*  995 */       if (++field >= SYMBOLS.length)
/*  995 */         field = -1; 
/*      */     } 
/*      */     char c;
/*      */     do {
/* 1004 */       if (start >= length)
/* 1005 */         return SYMBOLS.length; 
/* 1008 */     } while (Character.isSpaceChar(c = source.charAt(start++)));
/* 1009 */     switch (c) {
/*      */       case '°':
/* 1010 */         pos.setIndex(start);
/* 1010 */         return 0;
/*      */       case '\'':
/* 1011 */         pos.setIndex(start);
/* 1011 */         return 1;
/*      */       case '"':
/* 1012 */         pos.setIndex(start);
/* 1012 */         return 2;
/*      */     } 
/* 1013 */     return SYMBOLS.length;
/*      */   }
/*      */   
/*      */   public Angle parse(String source, ParsePosition pos) {
/* 1035 */     return parse(source, pos, false);
/*      */   }
/*      */   
/*      */   private synchronized Angle parse(String source, ParsePosition pos, boolean spaceAsSeparator) {
/*      */     int indexStartField;
/* 1061 */     double degrees = Double.NaN;
/* 1062 */     double minutes = Double.NaN;
/* 1063 */     double secondes = Double.NaN;
/* 1064 */     int length = source.length();
/* 1077 */     int indexStart = pos.getIndex();
/* 1078 */     int i = skipSuffix(source, pos, -1);
/* 1079 */     if (i >= 0 && i < SYMBOLS.length) {
/* 1080 */       pos.setErrorIndex(indexStart);
/* 1081 */       pos.setIndex(indexStart);
/* 1082 */       return null;
/*      */     } 
/* 1088 */     i = pos.getIndex();
/* 1089 */     for (; i < length && Character.isSpaceChar(source.charAt(i)); i++);
/* 1090 */     pos.setIndex(i);
/* 1096 */     Number fieldObject = this.numberFormat.parse(source, pos);
/* 1097 */     if (fieldObject == null) {
/* 1098 */       pos.setIndex(indexStart);
/* 1099 */       if (pos.getErrorIndex() < indexStart)
/* 1100 */         pos.setErrorIndex(i); 
/* 1102 */       return null;
/*      */     } 
/* 1104 */     degrees = fieldObject.doubleValue();
/* 1105 */     int indexEndField = pos.getIndex();
/* 1106 */     boolean swapDM = true;
/* 1107 */     switch (skipSuffix(source, pos, 0)) {
/*      */       case -1:
/* 1116 */         pos.setIndex(indexEndField);
/*      */         break;
/*      */       case 2:
/* 1127 */         secondes = degrees;
/* 1128 */         degrees = Double.NaN;
/*      */         break;
/*      */       default:
/* 1139 */         if (this.width1 == 0 || 
/* 1140 */           !spaceAsSeparator)
/*      */           break; 
/*      */       case 0:
/* 1151 */         indexStartField = i = pos.getIndex();
/* 1152 */         while (i < length && Character.isSpaceChar(source.charAt(i)))
/* 1153 */           i++; 
/* 1155 */         if (!spaceAsSeparator && i != indexStartField)
/*      */           break; 
/* 1158 */         pos.setIndex(i);
/* 1159 */         fieldObject = this.numberFormat.parse(source, pos);
/* 1160 */         if (fieldObject == null) {
/* 1161 */           pos.setIndex(indexStartField);
/*      */           break;
/*      */         } 
/* 1164 */         indexEndField = pos.getIndex();
/* 1165 */         minutes = fieldObject.doubleValue();
/* 1166 */         switch (skipSuffix(source, pos, (this.width1 != 0) ? 1 : -1)) {
/*      */           case 1:
/*      */             break;
/*      */           case 2:
/* 1184 */             secondes = minutes;
/* 1185 */             minutes = Double.NaN;
/*      */             break;
/*      */           default:
/* 1196 */             if (this.width1 != 0)
/*      */               break; 
/*      */           case 0:
/* 1207 */             pos.setIndex(indexStartField);
/* 1208 */             minutes = Double.NaN;
/*      */             break;
/*      */           case -1:
/* 1219 */             pos.setIndex(indexEndField);
/*      */             break;
/*      */         } 
/* 1223 */         swapDM = false;
/*      */       case 1:
/* 1235 */         if (swapDM) {
/* 1236 */           minutes = degrees;
/* 1237 */           degrees = Double.NaN;
/*      */         } 
/* 1239 */         indexStartField = i = pos.getIndex();
/* 1240 */         while (i < length && Character.isSpaceChar(source.charAt(i)))
/* 1241 */           i++; 
/* 1243 */         if (!spaceAsSeparator && i != indexStartField)
/*      */           break; 
/* 1246 */         pos.setIndex(i);
/* 1247 */         fieldObject = this.numberFormat.parse(source, pos);
/* 1248 */         if (fieldObject == null) {
/* 1249 */           pos.setIndex(indexStartField);
/*      */           break;
/*      */         } 
/* 1252 */         indexEndField = pos.getIndex();
/* 1253 */         secondes = fieldObject.doubleValue();
/* 1254 */         switch (skipSuffix(source, pos, (this.width2 != 0) ? 1 : -1)) {
/*      */           case 2:
/*      */             break;
/*      */           default:
/* 1271 */             if (this.width2 != 0)
/*      */               break; 
/*      */           case 0:
/*      */           case 1:
/* 1282 */             pos.setIndex(indexStartField);
/* 1283 */             secondes = Double.NaN;
/*      */             break;
/*      */           case -1:
/*      */             break;
/*      */         } 
/* 1294 */         pos.setIndex(indexEndField);
/*      */         break;
/*      */     } 
/* 1306 */     if (minutes < 0.0D)
/* 1307 */       secondes = -secondes; 
/* 1309 */     if (degrees < 0.0D) {
/* 1310 */       minutes = -minutes;
/* 1311 */       secondes = -secondes;
/*      */     } 
/* 1313 */     if (!this.decimalSeparator) {
/* 1314 */       double facteur = XMath.pow10(this.widthDecimal);
/* 1315 */       if (this.width2 != 0) {
/* 1316 */         if (this.suffix1 == null && Double.isNaN(secondes)) {
/* 1317 */           if (this.suffix0 == null && Double.isNaN(minutes)) {
/* 1318 */             degrees /= facteur;
/*      */           } else {
/* 1320 */             minutes /= facteur;
/*      */           } 
/*      */         } else {
/* 1323 */           secondes /= facteur;
/*      */         } 
/* 1325 */       } else if (Double.isNaN(secondes)) {
/* 1326 */         if (this.width1 != 0) {
/* 1327 */           if (this.suffix0 == null && Double.isNaN(minutes)) {
/* 1328 */             degrees /= facteur;
/*      */           } else {
/* 1330 */             minutes /= facteur;
/*      */           } 
/* 1332 */         } else if (Double.isNaN(minutes)) {
/* 1333 */           degrees /= facteur;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1342 */     if (this.suffix1 == null && this.width2 != 0 && Double.isNaN(secondes)) {
/* 1343 */       double facteur = XMath.pow10(this.width2);
/* 1344 */       if (this.suffix0 == null && this.width1 != 0 && Double.isNaN(minutes)) {
/* 1348 */         secondes = degrees;
/* 1349 */         minutes = (int)(degrees / facteur);
/* 1350 */         secondes -= minutes * facteur;
/* 1351 */         facteur = XMath.pow10(this.width1);
/* 1352 */         degrees = (int)(minutes / facteur);
/* 1353 */         minutes -= degrees * facteur;
/*      */       } else {
/* 1358 */         secondes = minutes;
/* 1359 */         minutes = (int)(minutes / facteur);
/* 1360 */         secondes -= minutes * facteur;
/*      */       } 
/* 1362 */     } else if (this.suffix0 == null && this.width1 != 0 && Double.isNaN(minutes)) {
/* 1366 */       double facteur = XMath.pow10(this.width1);
/* 1367 */       minutes = degrees;
/* 1368 */       degrees = (int)(degrees / facteur);
/* 1369 */       minutes -= degrees * facteur;
/*      */     } 
/* 1371 */     pos.setErrorIndex(-1);
/* 1372 */     if (Double.isNaN(degrees))
/* 1372 */       degrees = 0.0D; 
/* 1373 */     if (!Double.isNaN(minutes))
/* 1373 */       degrees += minutes / 60.0D; 
/* 1374 */     if (!Double.isNaN(secondes))
/* 1374 */       degrees += secondes / 3600.0D; 
/* 1379 */     for (int index = pos.getIndex(); index < length; index++) {
/* 1380 */       char c = source.charAt(index);
/* 1381 */       switch (Character.toUpperCase(c)) {
/*      */         case 'N':
/* 1382 */           pos.setIndex(index + 1);
/* 1382 */           return new Latitude(degrees);
/*      */         case 'S':
/* 1383 */           pos.setIndex(index + 1);
/* 1383 */           return new Latitude(-degrees);
/*      */         case 'E':
/* 1384 */           pos.setIndex(index + 1);
/* 1384 */           return new Longitude(degrees);
/*      */         case 'W':
/* 1385 */           pos.setIndex(index + 1);
/* 1385 */           return new Longitude(-degrees);
/*      */       } 
/* 1387 */       if (!Character.isSpaceChar(c))
/*      */         break; 
/*      */     } 
/* 1391 */     return new Angle(degrees);
/*      */   }
/*      */   
/*      */   public Angle parse(String source) throws ParseException {
/* 1403 */     ParsePosition pos = new ParsePosition(0);
/* 1404 */     Angle angle = parse(source, pos, true);
/* 1405 */     int length = source.length();
/* 1406 */     int origin = pos.getIndex();
/* 1407 */     for (int index = origin; index < length; index++) {
/* 1408 */       if (!Character.isWhitespace(source.charAt(index))) {
/* 1409 */         index = Math.max(origin, pos.getErrorIndex());
/* 1410 */         throw new ParseException(LoggedFormat.formatUnparsable(source, 0, index, null), index);
/*      */       } 
/*      */     } 
/* 1413 */     return angle;
/*      */   }
/*      */   
/*      */   public Angle parseObject(String source, ParsePosition pos) {
/* 1426 */     return parse(source, pos);
/*      */   }
/*      */   
/*      */   public Angle parseObject(String source) throws ParseException {
/* 1440 */     return parse(source);
/*      */   }
/*      */   
/*      */   final Number parseNumber(String source, ParsePosition pos) {
/* 1453 */     return this.numberFormat.parse(source, pos);
/*      */   }
/*      */   
/*      */   public synchronized int hashCode() {
/* 1461 */     int c = 78236951;
/* 1462 */     if (this.decimalSeparator)
/* 1462 */       c ^= 0xFF; 
/* 1463 */     if (this.prefix != null)
/* 1463 */       c ^= this.prefix.hashCode(); 
/* 1464 */     if (this.suffix0 != null)
/* 1464 */       c = c * 37 + this.suffix0.hashCode(); 
/* 1465 */     if (this.suffix1 != null)
/* 1465 */       c ^= c * 37 + this.suffix1.hashCode(); 
/* 1466 */     if (this.suffix2 != null)
/* 1466 */       c ^= c * 37 + this.suffix2.hashCode(); 
/* 1467 */     return c ^ ((this.width0 << 8 ^ this.width1) << 8 ^ this.width2) << 8 ^ this.widthDecimal;
/*      */   }
/*      */   
/*      */   public synchronized boolean equals(Object obj) {
/* 1477 */     if (obj == this)
/* 1478 */       return true; 
/* 1480 */     if (obj != null && getClass().equals(obj.getClass())) {
/* 1481 */       AngleFormat cast = (AngleFormat)obj;
/* 1482 */       return (this.width0 == cast.width0 && this.width1 == cast.width1 && this.width2 == cast.width2 && this.widthDecimal == cast.widthDecimal && this.decimalSeparator == cast.decimalSeparator && Utilities.equals(this.prefix, cast.prefix) && Utilities.equals(this.suffix0, cast.suffix0) && Utilities.equals(this.suffix1, cast.suffix1) && Utilities.equals(this.suffix2, cast.suffix2) && Utilities.equals(this.numberFormat.getDecimalFormatSymbols(), cast.numberFormat.getDecimalFormatSymbols()));
/*      */     } 
/* 1494 */     return false;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1503 */     return Classes.getShortClassName(this) + '[' + toPattern() + ']';
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\AngleFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */