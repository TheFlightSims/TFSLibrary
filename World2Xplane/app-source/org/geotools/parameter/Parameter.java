/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Set;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.NonSI;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.measure.Units;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterTypeException;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ 
/*     */ public class Parameter<T> extends AbstractParameter implements ParameterValue<T> {
/*     */   private static final long serialVersionUID = -5837826787089486776L;
/*     */   
/*     */   private T value;
/*     */   
/*     */   private Unit<?> unit;
/*     */   
/*     */   public Parameter(ParameterDescriptor<T> descriptor) {
/* 147 */     super((GeneralParameterDescriptor)descriptor);
/* 148 */     this.value = (T)descriptor.getDefaultValue();
/* 149 */     this.unit = descriptor.getUnit();
/*     */   }
/*     */   
/*     */   public Parameter(ParameterDescriptor<T> descriptor, T value) throws InvalidParameterValueException {
/* 164 */     super((GeneralParameterDescriptor)descriptor);
/* 165 */     this.unit = descriptor.getUnit();
/* 166 */     setValue(value);
/*     */   }
/*     */   
/*     */   public static Parameter<Integer> create(String name, int value) {
/* 182 */     ParameterDescriptor<Integer> descriptor = DefaultParameterDescriptor.create(name, 0, -2147483648, 2147483647);
/* 184 */     Parameter<Integer> parameter = new Parameter<Integer>(descriptor);
/* 185 */     parameter.value = (T)Integer.valueOf(value);
/* 186 */     return parameter;
/*     */   }
/*     */   
/*     */   public static Parameter<Double> create(String name, double value, Unit<?> unit) {
/* 204 */     if (unit != null)
/* 205 */       if (SI.METER.isCompatible(unit)) {
/* 205 */         unit = SI.METER;
/* 206 */       } else if (NonSI.DAY.isCompatible(unit)) {
/* 206 */         unit = NonSI.DAY;
/* 207 */       } else if (NonSI.DEGREE_ANGLE.isCompatible(unit)) {
/* 207 */         unit = NonSI.DEGREE_ANGLE;
/*     */       }  
/* 209 */     ParameterDescriptor<Double> descriptor = DefaultParameterDescriptor.create(name, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, unit);
/* 211 */     Parameter<Double> parameter = new Parameter<Double>(descriptor);
/* 212 */     parameter.value = (T)Double.valueOf(value);
/* 213 */     parameter.unit = unit;
/* 214 */     return parameter;
/*     */   }
/*     */   
/*     */   public static <T extends org.opengis.util.CodeList> Parameter<T> create(String name, Class<T> type, T value) {
/* 234 */     ParameterDescriptor<T> descriptor = DefaultParameterDescriptor.create(name, (CharSequence)null, type, (T)null, true);
/* 236 */     Parameter<T> parameter = new Parameter<T>(descriptor);
/* 237 */     parameter.value = value;
/* 238 */     return parameter;
/*     */   }
/*     */   
/*     */   public static <T> T ensureValidValue(ParameterDescriptor<T> descriptor, Object value) throws InvalidParameterValueException {
/*     */     String error;
/* 265 */     if (value == null)
/* 266 */       return null; 
/* 269 */     Class<T> type = descriptor.getValueClass();
/* 270 */     if (!type.isInstance(value)) {
/* 271 */       error = Errors.format(72, Classes.getClass(value));
/*     */     } else {
/* 274 */       Comparable<Object> minimum = descriptor.getMinimumValue();
/* 276 */       Comparable<Object> maximum = descriptor.getMaximumValue();
/* 277 */       if ((minimum != null && minimum.compareTo(value) > 0) || (maximum != null && maximum.compareTo(value) < 0)) {
/* 280 */         error = Errors.format(201, value, minimum, maximum);
/*     */       } else {
/* 282 */         Set<?> validValues = descriptor.getValidValues();
/* 283 */         if (validValues != null && !validValues.contains(value)) {
/* 284 */           error = Errors.format(58, getName((GeneralParameterDescriptor)descriptor), value);
/*     */         } else {
/* 286 */           return type.cast(value);
/*     */         } 
/*     */       } 
/*     */     } 
/* 290 */     throw new InvalidParameterValueException(error, getName(descriptor), value);
/*     */   }
/*     */   
/*     */   private String getClassTypeError() {
/* 297 */     return Errors.format(72, ((ParameterDescriptor)this.descriptor).getValueClass());
/*     */   }
/*     */   
/*     */   public ParameterDescriptor<T> getDescriptor() {
/* 307 */     return (ParameterDescriptor<T>)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit() {
/* 323 */     return this.unit;
/*     */   }
/*     */   
/*     */   static int getUnitMessageID(Unit<?> unit) {
/* 340 */     if (Unit.ONE.equals(unit) || Units.PPM.equals(unit))
/* 341 */       return 116; 
/* 342 */     if (SI.METER.isCompatible(unit))
/* 342 */       return 113; 
/* 343 */     if (SI.SECOND.isCompatible(unit))
/* 343 */       return 117; 
/* 344 */     if (SI.RADIAN.isCompatible(unit))
/* 344 */       return 107; 
/* 345 */     return 76;
/*     */   }
/*     */   
/*     */   public double doubleValue(Unit<?> unit) throws InvalidParameterTypeException {
/* 363 */     if (this.unit == null)
/* 364 */       throw unitlessParameter(this.descriptor); 
/* 366 */     ensureNonNull("unit", unit);
/* 367 */     int expectedID = getUnitMessageID(this.unit);
/* 368 */     if (getUnitMessageID(unit) != expectedID)
/* 369 */       throw new IllegalArgumentException(Errors.format(expectedID, unit)); 
/* 371 */     return this.unit.getConverterTo(unit).convert(doubleValue());
/*     */   }
/*     */   
/*     */   public double doubleValue() throws InvalidParameterTypeException {
/* 386 */     if (this.value instanceof Number)
/* 387 */       return ((Number)this.value).doubleValue(); 
/* 389 */     String name = getName(this.descriptor);
/* 390 */     if (this.value == null)
/* 392 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 395 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public int intValue() throws InvalidParameterTypeException {
/* 409 */     if (this.value instanceof Number)
/* 410 */       return ((Number)this.value).intValue(); 
/* 412 */     String name = getName(this.descriptor);
/* 413 */     if (this.value == null)
/* 414 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 416 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public boolean booleanValue() throws InvalidParameterTypeException {
/* 429 */     if (this.value instanceof Boolean)
/* 430 */       return ((Boolean)this.value).booleanValue(); 
/* 432 */     String name = getName(this.descriptor);
/* 433 */     if (this.value == null)
/* 434 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 436 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public String stringValue() throws InvalidParameterTypeException {
/* 450 */     if (this.value instanceof CharSequence)
/* 451 */       return this.value.toString(); 
/* 453 */     String name = getName(this.descriptor);
/* 454 */     if (this.value == null)
/* 455 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 457 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public double[] doubleValueList(Unit<?> unit) throws InvalidParameterTypeException {
/* 475 */     if (this.unit == null)
/* 476 */       throw unitlessParameter(this.descriptor); 
/* 478 */     ensureNonNull("unit", unit);
/* 479 */     int expectedID = getUnitMessageID(this.unit);
/* 480 */     if (getUnitMessageID(unit) != expectedID)
/* 481 */       throw new IllegalArgumentException(Errors.format(expectedID, unit)); 
/* 483 */     UnitConverter converter = this.unit.getConverterTo(unit);
/* 484 */     double[] values = (double[])doubleValueList().clone();
/* 485 */     for (int i = 0; i < values.length; i++)
/* 486 */       values[i] = converter.convert(values[i]); 
/* 488 */     return values;
/*     */   }
/*     */   
/*     */   public double[] doubleValueList() throws InvalidParameterTypeException {
/* 503 */     if (this.value instanceof double[])
/* 504 */       return (double[])this.value; 
/* 506 */     String name = getName(this.descriptor);
/* 507 */     if (this.value == null)
/* 508 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 510 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public int[] intValueList() throws InvalidParameterTypeException {
/* 524 */     if (this.value instanceof int[])
/* 525 */       return (int[])this.value; 
/* 527 */     String name = getName(this.descriptor);
/* 528 */     if (this.value == null)
/* 529 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 531 */     throw new InvalidParameterTypeException(getClassTypeError(), name);
/*     */   }
/*     */   
/*     */   public URI valueFile() throws InvalidParameterTypeException {
/* 547 */     if (this.value instanceof URI)
/* 548 */       return (URI)this.value; 
/* 550 */     if (this.value instanceof File)
/* 551 */       return ((File)this.value).toURI(); 
/* 553 */     Exception cause = null;
/*     */     try {
/* 555 */       if (this.value instanceof URL)
/* 556 */         return ((URL)this.value).toURI(); 
/* 558 */       if (this.value instanceof String)
/* 559 */         return new URI((String)this.value); 
/* 561 */     } catch (URISyntaxException uRISyntaxException) {
/* 562 */       cause = uRISyntaxException;
/*     */     } 
/* 567 */     String name = getName(this.descriptor);
/* 568 */     if (this.value == null)
/* 569 */       throw new IllegalStateException(Errors.format(99, name)); 
/* 571 */     InvalidParameterTypeException exception = new InvalidParameterTypeException(getClassTypeError(), name);
/* 573 */     if (cause != null)
/* 574 */       exception.initCause(cause); 
/* 576 */     throw exception;
/*     */   }
/*     */   
/*     */   public T getValue() {
/* 589 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(double value, Unit<?> unit) throws InvalidParameterValueException {
/* 607 */     ensureNonNull("unit", unit);
/* 609 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 610 */     Unit<?> targetUnit = descriptor.getUnit();
/* 611 */     if (targetUnit == null)
/* 612 */       throw unitlessParameter(descriptor); 
/* 614 */     int expectedID = getUnitMessageID(targetUnit);
/* 615 */     if (getUnitMessageID(unit) != expectedID)
/* 616 */       throw new InvalidParameterValueException(Errors.format(expectedID, unit), descriptor.getName().getCode(), value); 
/* 619 */     Double converted = Double.valueOf(unit.getConverterTo(targetUnit).convert(value));
/* 620 */     ensureValidValue(descriptor, converted);
/* 623 */     this.value = descriptor.getValueClass().cast(Double.valueOf(value));
/* 624 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public void setValue(double value) throws InvalidParameterValueException {
/* 640 */     Double check = Double.valueOf(value);
/* 642 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 643 */     this.value = ensureValidValue(descriptor, check);
/*     */   }
/*     */   
/*     */   public void setValue(int value) throws InvalidParameterValueException {
/* 657 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 658 */     Class<T> type = descriptor.getValueClass();
/* 659 */     if (Double.class.equals(type) || double.class.equals(type)) {
/* 660 */       setValue(value);
/*     */       return;
/*     */     } 
/* 663 */     Integer check = Integer.valueOf(value);
/* 664 */     this.value = ensureValidValue(descriptor, check);
/*     */   }
/*     */   
/*     */   public void setValue(boolean value) throws InvalidParameterValueException {
/* 677 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 678 */     Boolean check = Boolean.valueOf(value);
/* 679 */     this.value = ensureValidValue(descriptor, check);
/*     */   }
/*     */   
/*     */   public void setValue(Object value) throws InvalidParameterValueException {
/* 696 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 697 */     this.value = ensureValidValue(descriptor, value);
/*     */   }
/*     */   
/*     */   public void setValue(double[] values, Unit<?> unit) throws InvalidParameterValueException {
/* 712 */     ensureNonNull("unit", unit);
/* 714 */     ParameterDescriptor<T> descriptor = (ParameterDescriptor<T>)this.descriptor;
/* 715 */     Unit<?> targetUnit = descriptor.getUnit();
/* 716 */     if (targetUnit == null)
/* 717 */       throw unitlessParameter(descriptor); 
/* 719 */     int expectedID = getUnitMessageID(targetUnit);
/* 720 */     if (getUnitMessageID(unit) != expectedID)
/* 721 */       throw new IllegalArgumentException(Errors.format(expectedID, unit)); 
/* 723 */     double[] converted = (double[])values.clone();
/* 724 */     UnitConverter converter = unit.getConverterTo(targetUnit);
/* 725 */     for (int i = 0; i < converted.length; i++)
/* 726 */       converted[i] = converter.convert(converted[i]); 
/* 728 */     this.value = ensureValidValue(descriptor, converted);
/* 729 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 740 */     if (object == this)
/* 742 */       return true; 
/* 744 */     if (super.equals(object)) {
/* 745 */       Parameter that = (Parameter)object;
/* 746 */       return (Utilities.equals(this.value, that.value) && Utilities.equals(this.unit, that.unit));
/*     */     } 
/* 749 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 760 */     int code = super.hashCode() * 37;
/* 761 */     if (this.value != null)
/* 761 */       code += this.value.hashCode(); 
/* 762 */     if (this.unit != null)
/* 762 */       code += 37 * this.unit.hashCode(); 
/* 763 */     return code ^ 0x9F456C48;
/*     */   }
/*     */   
/*     */   public Parameter clone() {
/* 771 */     return (Parameter)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */