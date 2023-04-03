/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.text.FieldPosition;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Collection;
/*     */ import javax.measure.quantity.Angle;
/*     */ import javax.measure.quantity.Length;
/*     */ import javax.measure.unit.AlternateUnit;
/*     */ import javax.measure.unit.BaseUnit;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.measure.unit.UnitFormat;
/*     */ import org.geotools.math.XMath;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.util.CodeList;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class Formatter {
/*  84 */   private static final Class<? extends IdentifiedObject>[] AUTHORITY_EXCLUDE = new Class[] { CoordinateSystemAxis.class };
/*     */   
/*     */   private static final String NUMBER_COLOR = "\033[33m";
/*     */   
/*     */   private static final String INTEGER_COLOR = "\033[33m";
/*     */   
/*     */   private static final String UNIT_COLOR = "\033[33m";
/*     */   
/*     */   private static final String AXIS_COLOR = "\033[36m";
/*     */   
/*     */   private static final String CODELIST_COLOR = "\033[36m";
/*     */   
/*     */   private static final String PARAMETER_COLOR = "\033[32m";
/*     */   
/*     */   private static final String METHOD_COLOR = "\033[32m";
/*     */   
/*     */   private static final String DATUM_COLOR = "\033[32m";
/*     */   
/*     */   private static final String ERROR_COLOR = "\033[41m";
/*     */   
/*     */   private final Symbols symbols;
/*     */   
/* 114 */   private Citation authority = Citations.OGC;
/*     */   
/*     */   boolean colorEnabled = false;
/*     */   
/*     */   private Unit<Length> linearUnit;
/*     */   
/*     */   private Unit<Angle> angularUnit;
/*     */   
/*     */   private final NumberFormat numberFormat;
/*     */   
/*     */   public Citation getAuthority() {
/* 138 */     return this.authority;
/*     */   }
/*     */   
/*     */   public void setAuthority(Citation authority) {
/* 142 */     this.authority = authority;
/* 143 */     this.unitFormat = GeoToolsUnitFormat.getInstance(authority);
/*     */   }
/*     */   
/* 155 */   private UnitFormat unitFormat = GeoToolsUnitFormat.getInstance(Citations.EPSG);
/*     */   
/* 160 */   private final FieldPosition dummy = new FieldPosition(0);
/*     */   
/*     */   StringBuffer buffer;
/*     */   
/*     */   int bufferBase;
/*     */   
/*     */   final int indentation;
/*     */   
/*     */   private int margin;
/*     */   
/*     */   private boolean lineChanged;
/*     */   
/*     */   private boolean invalidWKT;
/*     */   
/*     */   private Class<?> unformattable;
/*     */   
/*     */   String warning;
/*     */   
/*     */   public Formatter() {
/* 216 */     this(Symbols.DEFAULT, 0);
/*     */   }
/*     */   
/*     */   public Formatter(Symbols symbols) {
/* 226 */     this(symbols, 0);
/*     */   }
/*     */   
/*     */   public Formatter(Symbols symbols, int indentation) {
/* 240 */     this.symbols = symbols;
/* 241 */     this.indentation = indentation;
/* 242 */     if (symbols == null)
/* 243 */       throw new IllegalArgumentException(Errors.format(143, "symbols")); 
/* 245 */     if (indentation < 0)
/* 246 */       throw new IllegalArgumentException(Errors.format(58, "indentation", Integer.valueOf(indentation))); 
/* 249 */     this.numberFormat = (NumberFormat)symbols.numberFormat.clone();
/* 250 */     this.buffer = new StringBuffer();
/*     */   }
/*     */   
/*     */   Formatter(Symbols symbols, NumberFormat numberFormat) {
/* 258 */     this.symbols = symbols;
/* 259 */     this.indentation = Formattable.getIndentation();
/* 260 */     this.numberFormat = numberFormat;
/*     */   }
/*     */   
/*     */   private void setColor(String color) {
/* 270 */     if (this.colorEnabled)
/* 271 */       this.buffer.append(color); 
/*     */   }
/*     */   
/*     */   private void resetColor() {
/* 280 */     if (this.colorEnabled)
/* 281 */       this.buffer.append("\033[39m"); 
/*     */   }
/*     */   
/*     */   private static String getNameColor(IdentifiedObject object) {
/* 289 */     if (object instanceof org.opengis.referencing.datum.Datum)
/* 290 */       return "\033[32m"; 
/* 292 */     if (object instanceof org.opengis.referencing.operation.OperationMethod)
/* 293 */       return "\033[32m"; 
/* 295 */     if (object instanceof CoordinateSystemAxis)
/* 296 */       return "\033[36m"; 
/* 301 */     return null;
/*     */   }
/*     */   
/*     */   private void appendSeparator(boolean newLine) {
/* 310 */     int length = this.buffer.length();
/*     */     while (true) {
/* 313 */       if (length == this.bufferBase)
/*     */         return; 
/* 316 */       char c = this.buffer.charAt(--length);
/* 317 */       this.symbols.getClass();
/* 317 */       if (c == this.symbols.open || c == '{')
/*     */         return; 
/* 320 */       this.symbols.getClass();
/* 320 */       if (!Character.isWhitespace(c) && c != ' ') {
/* 321 */         this.symbols.getClass();
/* 321 */         this.buffer.append(this.symbols.separator).append(' ');
/* 322 */         if (newLine && this.indentation != 0) {
/* 323 */           this.buffer.append(System.getProperty("line.separator", "\n")).append(Utilities.spaces(this.margin));
/* 325 */           this.lineChanged = true;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(Formattable formattable) {
/* 346 */     appendSeparator(true);
/* 347 */     int base = this.buffer.length();
/* 348 */     this.buffer.append(this.symbols.open);
/* 349 */     IdentifiedObject info = (formattable instanceof IdentifiedObject) ? (IdentifiedObject)formattable : null;
/* 351 */     if (info != null) {
/* 352 */       String c = getNameColor(info);
/* 353 */       if (c != null)
/* 354 */         setColor(c); 
/* 356 */       this.symbols.getClass();
/* 356 */       this.symbols.getClass();
/* 356 */       this.buffer.append('"').append(getName(info)).append('"');
/* 357 */       if (c != null)
/* 358 */         resetColor(); 
/*     */     } 
/* 370 */     indent(1);
/* 371 */     this.lineChanged = false;
/* 372 */     String keyword = formattable.formatWKT(this);
/* 373 */     if (this.colorEnabled && this.invalidWKT) {
/* 374 */       this.invalidWKT = false;
/* 375 */       this.buffer.insert(base, "\033[41m\033[49m");
/* 376 */       base += "\033[41m".length();
/*     */     } 
/* 378 */     this.buffer.insert(base, keyword);
/* 391 */     Identifier identifier = getIdentifier(info);
/* 392 */     if (identifier != null && authorityAllowed(info)) {
/* 393 */       Citation authority = identifier.getAuthority();
/* 394 */       if (authority != null) {
/* 400 */         InternationalString inter = authority.getTitle();
/* 401 */         String title = (inter != null) ? inter.toString(this.symbols.locale) : null;
/* 402 */         for (InternationalString alt : authority.getAlternateTitles()) {
/* 403 */           if (alt != null) {
/* 404 */             String candidate = alt.toString(this.symbols.locale);
/* 405 */             if (candidate != null && (
/* 406 */               title == null || candidate.length() < title.length()))
/* 407 */               title = candidate; 
/*     */           } 
/*     */         } 
/* 412 */         if (title != null) {
/* 413 */           appendSeparator(this.lineChanged);
/* 414 */           this.symbols.getClass();
/* 414 */           this.symbols.getClass();
/* 414 */           this.buffer.append("AUTHORITY").append(this.symbols.open).append('"').append(title).append('"');
/* 419 */           String code = identifier.getCode();
/* 420 */           if (code != null) {
/* 421 */             this.symbols.getClass();
/* 421 */             this.symbols.getClass();
/* 421 */             this.buffer.append(this.symbols.separator).append('"').append(code).append('"');
/*     */           } 
/* 426 */           this.buffer.append(this.symbols.close);
/*     */         } 
/*     */       } 
/*     */     } 
/* 430 */     this.buffer.append(this.symbols.close);
/* 431 */     this.lineChanged = true;
/* 432 */     indent(-1);
/*     */   }
/*     */   
/*     */   public void append(IdentifiedObject info) {
/* 441 */     if (info instanceof Formattable) {
/* 442 */       append((Formattable)info);
/*     */     } else {
/* 444 */       append(new Adapter(info));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(MathTransform transform) {
/* 454 */     if (transform instanceof Formattable) {
/* 455 */       append((Formattable)transform);
/*     */     } else {
/* 457 */       append(new Adapter(transform));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(CodeList code) {
/* 467 */     if (code != null) {
/* 468 */       appendSeparator(false);
/* 469 */       setColor("\033[36m");
/* 470 */       String name = code.name();
/* 471 */       boolean needQuotes = (name.indexOf(' ') >= 0);
/* 472 */       if (needQuotes) {
/* 473 */         this.symbols.getClass();
/* 473 */         this.buffer.append('"');
/*     */       } 
/* 475 */       this.buffer.append(name);
/* 476 */       if (needQuotes) {
/* 477 */         this.symbols.getClass();
/* 477 */         this.buffer.append('"');
/* 478 */         setInvalidWKT(code.getClass());
/*     */       } 
/* 480 */       resetColor();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(GeneralParameterValue parameter) {
/* 492 */     if (parameter instanceof ParameterValueGroup)
/* 493 */       for (GeneralParameterValue param : ((ParameterValueGroup)parameter).values())
/* 494 */         append(param);  
/* 497 */     if (parameter instanceof ParameterValue) {
/* 498 */       ParameterValue<?> param = (ParameterValue)parameter;
/* 499 */       ParameterDescriptor<?> descriptor = param.getDescriptor();
/* 500 */       Unit<?> valueUnit = descriptor.getUnit();
/* 501 */       Unit<?> unit = valueUnit;
/* 502 */       if (unit != null && !Unit.ONE.equals(unit))
/* 503 */         if (this.linearUnit != null && unit.isCompatible(this.linearUnit)) {
/* 504 */           unit = this.linearUnit;
/* 505 */         } else if (this.angularUnit != null && unit.isCompatible(this.angularUnit)) {
/* 506 */           unit = this.angularUnit;
/*     */         }  
/* 509 */       appendSeparator(true);
/* 510 */       int start = this.buffer.length();
/* 511 */       this.buffer.append("PARAMETER");
/* 512 */       int stop = this.buffer.length();
/* 513 */       this.buffer.append(this.symbols.open);
/* 514 */       setColor("\033[32m");
/* 515 */       this.symbols.getClass();
/* 515 */       this.symbols.getClass();
/* 515 */       this.buffer.append('"').append(getName((IdentifiedObject)descriptor)).append('"');
/* 516 */       resetColor();
/* 517 */       this.symbols.getClass();
/* 517 */       this.buffer.append(this.symbols.separator).append(' ');
/* 518 */       if (unit != null) {
/*     */         double d;
/*     */         try {
/* 521 */           d = param.doubleValue(unit);
/* 522 */         } catch (IllegalStateException exception) {
/* 525 */           if (this.colorEnabled)
/* 526 */             this.buffer.insert(stop, "\033[49m").insert(start, "\033[41m"); 
/* 528 */           this.warning = exception.getLocalizedMessage();
/* 529 */           d = Double.NaN;
/*     */         } 
/* 531 */         if (!unit.equals(valueUnit))
/* 532 */           d = XMath.trimDecimalFractionDigits(d, 4, 9); 
/* 534 */         format(d);
/*     */       } else {
/* 536 */         appendObject(param.getValue());
/*     */       } 
/* 538 */       this.buffer.append(this.symbols.close);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendObject(Object value) {
/* 547 */     if (value == null) {
/* 548 */       this.buffer.append("null");
/*     */       return;
/*     */     } 
/* 551 */     if (value.getClass().isArray()) {
/* 552 */       this.symbols.getClass();
/* 552 */       this.buffer.append('{');
/* 553 */       int length = Array.getLength(value);
/* 554 */       for (int i = 0; i < length; i++) {
/* 555 */         if (i != 0) {
/* 556 */           this.symbols.getClass();
/* 556 */           this.buffer.append(this.symbols.separator).append(' ');
/*     */         } 
/* 558 */         appendObject(Array.get(value, i));
/*     */       } 
/* 560 */       this.symbols.getClass();
/* 560 */       this.buffer.append('}');
/*     */       return;
/*     */     } 
/* 563 */     if (value instanceof Number) {
/* 564 */       format((Number)value);
/*     */     } else {
/* 566 */       this.symbols.getClass();
/* 566 */       this.symbols.getClass();
/* 566 */       this.buffer.append('"').append(value).append('"');
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(int number) {
/* 577 */     appendSeparator(false);
/* 578 */     format(number);
/*     */   }
/*     */   
/*     */   public void append(double number) {
/* 588 */     appendSeparator(false);
/* 589 */     format(number);
/*     */   }
/*     */   
/*     */   public void append(Unit<?> unit) {
/* 599 */     if (unit != null) {
/*     */       AlternateUnit alternateUnit;
/* 600 */       appendSeparator(this.lineChanged);
/* 601 */       this.buffer.append("UNIT").append(this.symbols.open);
/* 602 */       setColor("\033[33m");
/* 603 */       this.symbols.getClass();
/* 603 */       this.buffer.append('"');
/* 604 */       this.unitFormat.format(unit, this.buffer, this.dummy);
/* 605 */       this.symbols.getClass();
/* 605 */       this.buffer.append('"');
/* 606 */       resetColor();
/* 607 */       Unit<?> base = null;
/* 608 */       if (SI.METER.isCompatible(unit)) {
/* 609 */         base = SI.METER;
/* 610 */       } else if (SI.SECOND.isCompatible(unit)) {
/* 611 */         BaseUnit baseUnit = SI.SECOND;
/* 612 */       } else if (SI.RADIAN.isCompatible(unit) && 
/* 613 */         !Unit.ONE.equals(unit)) {
/* 614 */         alternateUnit = SI.RADIAN;
/*     */       } 
/* 617 */       if (alternateUnit != null)
/* 618 */         append(unit.getConverterTo((Unit)alternateUnit).convert(1.0D)); 
/* 620 */       this.buffer.append(this.symbols.close);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void append(String text) {
/* 631 */     appendSeparator(false);
/* 632 */     this.symbols.getClass();
/* 632 */     this.symbols.getClass();
/* 632 */     this.buffer.append('"').append(text).append('"');
/*     */   }
/*     */   
/*     */   private void format(Number number) {
/* 639 */     if (number instanceof Byte || number instanceof Short || number instanceof Integer) {
/* 643 */       format(number.intValue());
/*     */     } else {
/* 645 */       format(number.doubleValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void format(int number) {
/* 653 */     setColor("\033[33m");
/* 654 */     int fraction = this.numberFormat.getMinimumFractionDigits();
/* 655 */     this.numberFormat.setMinimumFractionDigits(0);
/* 656 */     this.numberFormat.format(number, this.buffer, this.dummy);
/* 657 */     this.numberFormat.setMinimumFractionDigits(fraction);
/* 658 */     resetColor();
/*     */   }
/*     */   
/*     */   private void format(double number) {
/* 665 */     setColor("\033[33m");
/* 666 */     this.numberFormat.format(number, this.buffer, this.dummy);
/* 667 */     resetColor();
/*     */   }
/*     */   
/*     */   private void indent(int amount) {
/* 676 */     this.margin = Math.max(0, this.margin + this.indentation * amount);
/*     */   }
/*     */   
/*     */   private static boolean authorityAllowed(IdentifiedObject info) {
/* 683 */     for (int i = 0; i < AUTHORITY_EXCLUDE.length; i++) {
/* 684 */       if (AUTHORITY_EXCLUDE[i].isInstance(info))
/* 685 */         return false; 
/*     */     } 
/* 688 */     return true;
/*     */   }
/*     */   
/*     */   public Identifier getIdentifier(IdentifiedObject info) {
/* 704 */     Identifier first = null;
/* 705 */     if (info != null) {
/* 706 */       Collection<? extends Identifier> identifiers = info.getIdentifiers();
/* 707 */       if (identifiers != null)
/* 708 */         for (Identifier id : identifiers) {
/* 709 */           if (authorityMatches(id.getAuthority()))
/* 710 */             return id; 
/* 712 */           if (first == null)
/* 713 */             first = id; 
/*     */         }  
/*     */     } 
/* 718 */     return first;
/*     */   }
/*     */   
/*     */   private boolean authorityMatches(Citation citation) {
/* 726 */     if (this.authority == citation)
/* 727 */       return true; 
/* 730 */     return (citation != null && this.authority.getTitle().toString(null).equalsIgnoreCase(citation.getTitle().toString(null)));
/*     */   }
/*     */   
/*     */   public String getName(IdentifiedObject info) {
/* 745 */     ReferenceIdentifier referenceIdentifier = info.getName();
/* 746 */     if (!authorityMatches(referenceIdentifier.getAuthority())) {
/* 747 */       Collection<GenericName> aliases = info.getAlias();
/* 748 */       if (aliases != null) {
/* 755 */         for (GenericName alias : aliases) {
/* 756 */           if (alias instanceof Identifier) {
/* 757 */             Identifier candidate = (Identifier)alias;
/* 758 */             if (authorityMatches(candidate.getAuthority()))
/* 759 */               return candidate.getCode(); 
/*     */           } 
/*     */         } 
/* 764 */         String title = this.authority.getTitle().toString(null);
/* 765 */         for (GenericName alias : aliases) {
/* 766 */           GenericName scope = alias.scope().name();
/* 767 */           if (scope != null && 
/* 768 */             title.equalsIgnoreCase(scope.toString()))
/* 769 */             return alias.tip().toString(); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 775 */     return referenceIdentifier.getCode();
/*     */   }
/*     */   
/*     */   public Unit<Length> getLinearUnit() {
/* 785 */     return this.linearUnit;
/*     */   }
/*     */   
/*     */   public void setLinearUnit(Unit<Length> unit) {
/* 794 */     if (unit != null && !SI.METER.isCompatible(unit))
/* 795 */       throw new IllegalArgumentException(Errors.format(113, unit)); 
/* 797 */     this.linearUnit = unit;
/*     */   }
/*     */   
/*     */   public Unit<Angle> getAngularUnit() {
/* 808 */     return this.angularUnit;
/*     */   }
/*     */   
/*     */   public void setAngularUnit(Unit<Angle> unit) {
/* 817 */     if (unit != null && (!SI.RADIAN.isCompatible(unit) || Unit.ONE.equals(unit)))
/* 818 */       throw new IllegalArgumentException(Errors.format(107, unit)); 
/* 820 */     this.angularUnit = unit;
/*     */   }
/*     */   
/*     */   public boolean isInvalidWKT() {
/* 835 */     return (this.unformattable != null || (this.buffer != null && this.buffer.length() == 0));
/*     */   }
/*     */   
/*     */   final Class getUnformattableClass() {
/* 846 */     return this.unformattable;
/*     */   }
/*     */   
/*     */   public void setInvalidWKT(Class<?> unformattable) {
/* 866 */     this.unformattable = unformattable;
/* 867 */     this.invalidWKT = true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 875 */     return this.buffer.toString();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 885 */     if (this.buffer != null)
/* 886 */       this.buffer.setLength(0); 
/* 888 */     this.linearUnit = null;
/* 889 */     this.angularUnit = null;
/* 890 */     this.unformattable = null;
/* 891 */     this.warning = null;
/* 892 */     this.invalidWKT = false;
/* 893 */     this.lineChanged = false;
/* 894 */     this.margin = 0;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 911 */     Arguments arguments = new Arguments(args);
/* 912 */     int indentation = arguments.getRequiredInteger("Indentation");
/* 913 */     args = arguments.getRemainingArguments(0);
/* 914 */     Formattable.setIndentation(indentation);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Formatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */