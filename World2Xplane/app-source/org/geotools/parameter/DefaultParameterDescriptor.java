/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ public class DefaultParameterDescriptor<T> extends AbstractParameterDescriptor implements ParameterDescriptor<T> {
/*     */   private static final long serialVersionUID = -295668622297737705L;
/*     */   
/*     */   private final Class<T> valueClass;
/*     */   
/*     */   private final Set<T> validValues;
/*     */   
/*     */   private final T defaultValue;
/*     */   
/*     */   private final Comparable<T> minimum;
/*     */   
/*     */   private final Comparable<T> maximum;
/*     */   
/*     */   private final Unit<?> unit;
/*     */   
/*     */   public DefaultParameterDescriptor(ParameterDescriptor<T> descriptor) {
/* 117 */     super((GeneralParameterDescriptor)descriptor);
/* 118 */     this.valueClass = descriptor.getValueClass();
/* 119 */     this.validValues = descriptor.getValidValues();
/* 120 */     this.defaultValue = (T)descriptor.getDefaultValue();
/* 121 */     this.minimum = descriptor.getMinimumValue();
/* 122 */     this.maximum = descriptor.getMaximumValue();
/* 123 */     this.unit = descriptor.getUnit();
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptor(String name, Class<T> valueClass, T[] validValues, T defaultValue) {
/* 142 */     this(Collections.singletonMap("name", name), valueClass, validValues, defaultValue, (Comparable<T>)null, (Comparable<T>)null, (Unit<?>)null, true);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptor(Citation authority, String name, Class<T> valueClass, T[] validValues, T defaultValue, Comparable<T> minimum, Comparable<T> maximum, Unit<?> unit, boolean required) {
/* 175 */     this(Collections.singletonMap("name", new NamedIdentifier(authority, name)), valueClass, validValues, defaultValue, minimum, maximum, unit, required);
/*     */   }
/*     */   
/*     */   public DefaultParameterDescriptor(Map<String, ?> properties, Class<T> valueClass, T[] validValues, T defaultValue, Comparable<T> minimum, Comparable<T> maximum, Unit<?> unit, boolean required) {
/* 205 */     this(properties, required, valueClass, validValues, defaultValue, minimum, maximum, unit);
/*     */   }
/*     */   
/*     */   private DefaultParameterDescriptor(Map<String, ?> properties, boolean required, Class<T> valueClass, T[] validValues, T defaultValue, Comparable<T> minimum, Comparable<T> maximum, Unit<?> unit) {
/* 236 */     super(properties, required ? 1 : 0, 1);
/* 237 */     this.valueClass = valueClass;
/* 238 */     this.defaultValue = defaultValue;
/* 239 */     this.minimum = minimum;
/* 240 */     this.maximum = maximum;
/* 241 */     this.unit = unit;
/* 242 */     ensureNonNull("valueClass", valueClass);
/* 243 */     AbstractParameter.ensureValidClass(valueClass, defaultValue);
/* 244 */     AbstractParameter.ensureValidClass(valueClass, minimum);
/* 245 */     AbstractParameter.ensureValidClass(valueClass, maximum);
/* 246 */     if (minimum != null && maximum != null && 
/* 247 */       minimum.compareTo(valueClass.cast(maximum)) > 0)
/* 248 */       throw new IllegalArgumentException(Errors.format(14, minimum, maximum)); 
/* 252 */     if (validValues != null) {
/* 253 */       Set<T> valids = new HashSet<T>(Math.max(validValues.length * 4 / 3 + 1, 8), 0.75F);
/* 254 */       for (int i = 0; i < validValues.length; i++) {
/* 255 */         T value = validValues[i];
/* 256 */         AbstractParameter.ensureValidClass(valueClass, value);
/* 257 */         valids.add(value);
/*     */       } 
/* 259 */       this.validValues = Collections.unmodifiableSet(valids);
/*     */     } else {
/* 261 */       this.validValues = null;
/*     */     } 
/* 263 */     if (defaultValue != null)
/* 264 */       Parameter.ensureValidValue(this, defaultValue); 
/*     */   }
/*     */   
/*     */   public static DefaultParameterDescriptor<Integer> create(String name, int defaultValue, int minimum, int maximum) {
/* 282 */     return create(Collections.singletonMap("name", name), defaultValue, minimum, maximum, true);
/*     */   }
/*     */   
/*     */   public static DefaultParameterDescriptor<Integer> create(Map<String, ?> properties, int defaultValue, int minimum, int maximum, boolean required) {
/* 301 */     return new DefaultParameterDescriptor<Integer>(properties, required, Integer.class, null, Integer.valueOf(defaultValue), (minimum == Integer.MIN_VALUE) ? null : Integer.valueOf(minimum), (maximum == Integer.MAX_VALUE) ? null : Integer.valueOf(maximum), null);
/*     */   }
/*     */   
/*     */   public static DefaultParameterDescriptor<Double> create(String name, double defaultValue, double minimum, double maximum, Unit<?> unit) {
/* 323 */     return create(Collections.singletonMap("name", name), defaultValue, minimum, maximum, unit, true);
/*     */   }
/*     */   
/*     */   public static DefaultParameterDescriptor<Double> create(Map<String, ?> properties, double defaultValue, double minimum, double maximum, Unit<?> unit, boolean required) {
/* 344 */     return new DefaultParameterDescriptor<Double>(properties, required, Double.class, null, Double.isNaN(defaultValue) ? null : Double.valueOf(defaultValue), (minimum == Double.NEGATIVE_INFINITY) ? null : Double.valueOf(minimum), (maximum == Double.POSITIVE_INFINITY) ? null : Double.valueOf(maximum), unit);
/*     */   }
/*     */   
/*     */   public static <T> DefaultParameterDescriptor<T> create(String name, CharSequence remarks, Class<T> valueClass, T defaultValue, boolean required) {
/*     */     Map<String, CharSequence> properties;
/* 367 */     T[] codeList = null;
/* 368 */     if (CodeList.class.isAssignableFrom(valueClass))
/*     */       try {
/* 371 */         T[] tmp = (T[])valueClass.getMethod("values", (Class[])null).invoke(null, (Object[])null);
/* 373 */         codeList = tmp;
/* 374 */       } catch (Exception exception) {} 
/* 380 */     if (remarks == null) {
/* 381 */       properties = Collections.singletonMap("name", name);
/*     */     } else {
/* 383 */       properties = new HashMap<String, CharSequence>(4);
/* 384 */       properties.put("name", name);
/* 385 */       properties.put("remarks", remarks);
/*     */     } 
/* 387 */     return new DefaultParameterDescriptor<T>(properties, valueClass, codeList, defaultValue, null, null, null, required);
/*     */   }
/*     */   
/*     */   public int getMaximumOccurs() {
/* 401 */     return 1;
/*     */   }
/*     */   
/*     */   public ParameterValue<T> createValue() {
/* 414 */     if (Double.class.equals(this.valueClass) && this.unit == null)
/* 415 */       return new FloatParameter((ParameterDescriptor)this); 
/* 417 */     return new Parameter<T>(this);
/*     */   }
/*     */   
/*     */   public Class<T> getValueClass() {
/* 426 */     return this.valueClass;
/*     */   }
/*     */   
/*     */   public Set<T> getValidValues() {
/* 440 */     return this.validValues;
/*     */   }
/*     */   
/*     */   public T getDefaultValue() {
/* 451 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public Comparable<T> getMinimumValue() {
/* 462 */     return this.minimum;
/*     */   }
/*     */   
/*     */   public Comparable<T> getMaximumValue() {
/* 473 */     return this.maximum;
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit() {
/* 488 */     return this.unit;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 501 */     if (object == this)
/* 502 */       return true; 
/* 504 */     if (super.equals(object, compareMetadata)) {
/* 505 */       if (!compareMetadata)
/* 514 */         if (!nameMatches(object.getName().getCode()) && !nameMatches((IdentifiedObject)object, getName().getCode()))
/* 517 */           return false;  
/* 520 */       DefaultParameterDescriptor that = (DefaultParameterDescriptor)object;
/* 521 */       return (Utilities.equals(this.validValues, that.validValues) && Utilities.equals(this.defaultValue, that.defaultValue) && Utilities.equals(this.minimum, that.minimum) && Utilities.equals(this.maximum, that.maximum) && Utilities.equals(this.unit, that.unit));
/*     */     } 
/* 527 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 538 */     int code = super.hashCode() * 37 + this.valueClass.hashCode();
/* 539 */     if (this.defaultValue != null)
/* 539 */       code += 37 * this.defaultValue.hashCode(); 
/* 540 */     if (this.minimum != null)
/* 540 */       code += 1369 * this.minimum.hashCode(); 
/* 541 */     if (this.maximum != null)
/* 541 */       code += 50653 * this.maximum.hashCode(); 
/* 542 */     if (this.unit != null)
/* 542 */       code += this.unit.hashCode(); 
/* 543 */     return code;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\DefaultParameterDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */