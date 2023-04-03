/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.net.URI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterTypeException;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ 
/*     */ public class FloatParameter extends AbstractParameter implements ParameterValue<Double> {
/*     */   private static final long serialVersionUID = 9027797654033417816L;
/*     */   
/*     */   private double value;
/*     */   
/*     */   public FloatParameter(ParameterDescriptor<Double> descriptor) {
/*  73 */     super((GeneralParameterDescriptor)descriptor);
/*  74 */     Class type = descriptor.getValueClass();
/*  75 */     Class<Double> expected = Double.class;
/*  76 */     if (!expected.equals(type) && !double.class.equals(type))
/*  77 */       throw new IllegalArgumentException(Errors.format(61, type, expected)); 
/*  80 */     Number value = (Number)descriptor.getDefaultValue();
/*  81 */     this.value = (value != null) ? value.doubleValue() : Double.NaN;
/*     */   }
/*     */   
/*     */   public FloatParameter(ParameterDescriptor<Double> descriptor, double value) {
/*  94 */     this(descriptor);
/*  95 */     setValue(value);
/*     */   }
/*     */   
/*     */   public ParameterDescriptor<Double> getDescriptor() {
/* 104 */     return (ParameterDescriptor<Double>)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit() {
/* 114 */     return ((ParameterDescriptor)this.descriptor).getUnit();
/*     */   }
/*     */   
/*     */   public double doubleValue(Unit<?> unit) throws IllegalArgumentException {
/* 127 */     ensureNonNull("unit", unit);
/* 128 */     Unit<?> thisUnit = getUnit();
/* 129 */     if (thisUnit == null)
/* 130 */       throw unitlessParameter(this.descriptor); 
/* 132 */     int expectedID = Parameter.getUnitMessageID(thisUnit);
/* 133 */     if (Parameter.getUnitMessageID(unit) != expectedID)
/* 134 */       throw new IllegalArgumentException(Errors.format(expectedID, unit)); 
/* 136 */     return thisUnit.getConverterTo(unit).convert(this.value);
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 146 */     return this.value;
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 155 */     return (int)Math.round(this.value);
/*     */   }
/*     */   
/*     */   public boolean booleanValue() {
/* 164 */     return (this.value != 0.0D && !Double.isNaN(this.value));
/*     */   }
/*     */   
/*     */   public String stringValue() {
/* 173 */     return String.valueOf(this.value);
/*     */   }
/*     */   
/*     */   public double[] doubleValueList(Unit<?> unit) throws IllegalArgumentException {
/* 185 */     return new double[] { doubleValue(unit) };
/*     */   }
/*     */   
/*     */   public double[] doubleValueList() {
/* 194 */     return new double[] { doubleValue() };
/*     */   }
/*     */   
/*     */   public int[] intValueList() {
/* 203 */     return new int[] { intValue() };
/*     */   }
/*     */   
/*     */   public URI valueFile() throws InvalidParameterTypeException {
/* 213 */     throw new InvalidParameterTypeException(getClassTypeError(), Parameter.getName(this.descriptor));
/*     */   }
/*     */   
/*     */   private static String getClassTypeError() {
/* 220 */     return Errors.format(72, Double.class);
/*     */   }
/*     */   
/*     */   public Double getValue() {
/* 229 */     return Double.valueOf(this.value);
/*     */   }
/*     */   
/*     */   public void setValue(double value, Unit<?> unit) throws InvalidParameterValueException {
/* 241 */     ensureNonNull("unit", unit);
/* 243 */     ParameterDescriptor<Double> descriptor = (ParameterDescriptor<Double>)this.descriptor;
/* 244 */     Unit<?> thisUnit = descriptor.getUnit();
/* 245 */     if (thisUnit == null)
/* 246 */       throw unitlessParameter(descriptor); 
/* 248 */     int expectedID = Parameter.getUnitMessageID(thisUnit);
/* 249 */     if (Parameter.getUnitMessageID(unit) != expectedID)
/* 250 */       throw new IllegalArgumentException(Errors.format(expectedID, unit)); 
/* 252 */     value = unit.getConverterTo(thisUnit).convert(value);
/* 253 */     this.value = ((Double)Parameter.<Double>ensureValidValue(descriptor, Double.valueOf(value))).doubleValue();
/*     */   }
/*     */   
/*     */   public void setValue(double value) throws InvalidParameterValueException {
/* 265 */     ParameterDescriptor<Double> descriptor = (ParameterDescriptor<Double>)this.descriptor;
/* 266 */     this.value = ((Double)Parameter.<Double>ensureValidValue(descriptor, Double.valueOf(value))).doubleValue();
/*     */   }
/*     */   
/*     */   public void setValue(int value) throws InvalidParameterValueException {
/* 277 */     setValue(value);
/*     */   }
/*     */   
/*     */   public void setValue(boolean value) throws InvalidParameterValueException {
/* 287 */     setValue(value ? 1.0D : 0.0D);
/*     */   }
/*     */   
/*     */   public void setValue(Object value) throws InvalidParameterValueException {
/* 300 */     ParameterDescriptor<Double> descriptor = (ParameterDescriptor<Double>)this.descriptor;
/* 301 */     this.value = ((Double)Parameter.<Double>ensureValidValue(descriptor, value)).doubleValue();
/*     */   }
/*     */   
/*     */   public void setValue(double[] values, Unit<?> unit) throws InvalidParameterValueException {
/* 310 */     throw new InvalidParameterTypeException(getClassTypeError(), Parameter.getName(this.descriptor));
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 322 */     if (super.equals(object)) {
/* 323 */       FloatParameter that = (FloatParameter)object;
/* 324 */       return (Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.value));
/*     */     } 
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 338 */     long code = Double.doubleToLongBits(this.value);
/* 339 */     return (int)code ^ (int)(code >>> 32L) + super.hashCode() * 37;
/*     */   }
/*     */   
/*     */   public FloatParameter clone() {
/* 347 */     return (FloatParameter)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\FloatParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */