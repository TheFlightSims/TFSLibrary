/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.net.URI;
/*     */ import javax.measure.unit.Unit;
/*     */ import javax.media.jai.ParameterList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterTypeException;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ 
/*     */ final class ImagingParameter<T> extends AbstractParameter implements ParameterValue<T> {
/*     */   private static final long serialVersionUID = -170895429717041733L;
/*     */   
/*     */   private final ParameterList parameters;
/*     */   
/*     */   public ImagingParameter(ParameterDescriptor descriptor, ParameterList parameters) {
/*  56 */     super((GeneralParameterDescriptor)descriptor);
/*  57 */     this.parameters = parameters;
/*     */   }
/*     */   
/*     */   public ParameterDescriptor<T> getDescriptor() {
/*  66 */     return (ParameterDescriptor<T>)super.getDescriptor();
/*     */   }
/*     */   
/*     */   private InvalidParameterTypeException invalidType(ClassCastException cause) {
/*  73 */     InvalidParameterTypeException exception = new InvalidParameterTypeException(Errors.format(72, getType()), getName(this.descriptor));
/*  76 */     exception.initCause(cause);
/*  77 */     return exception;
/*     */   }
/*     */   
/*     */   private String getName() {
/*  85 */     return this.descriptor.getName().getCode();
/*     */   }
/*     */   
/*     */   private Class<T> getType() {
/*  92 */     return getDescriptor().getValueClass();
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit() {
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public double doubleValue(Unit<?> unit) throws InvalidParameterTypeException {
/* 106 */     throw unitlessParameter(this.descriptor);
/*     */   }
/*     */   
/*     */   public double doubleValue() throws InvalidParameterTypeException {
/* 113 */     String name = getName();
/* 114 */     Class<T> type = getType();
/*     */     try {
/* 116 */       if (type.equals(Float.class))
/* 116 */         this.parameters.getFloatParameter(name); 
/* 117 */       if (type.equals(Long.class))
/* 117 */         this.parameters.getLongParameter(name); 
/* 118 */       if (type.equals(Integer.class))
/* 118 */         this.parameters.getIntParameter(name); 
/* 119 */       if (type.equals(Short.class))
/* 119 */         this.parameters.getShortParameter(name); 
/* 120 */       if (type.equals(Byte.class))
/* 120 */         this.parameters.getByteParameter(name); 
/* 121 */       return this.parameters.getDoubleParameter(name);
/* 122 */     } catch (ClassCastException exception) {
/* 123 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int intValue() throws InvalidParameterTypeException {
/* 131 */     String name = getName();
/* 132 */     Class<T> type = getType();
/*     */     try {
/* 134 */       if (type.equals(Short.class))
/* 134 */         this.parameters.getShortParameter(name); 
/* 135 */       if (type.equals(Byte.class))
/* 135 */         this.parameters.getByteParameter(name); 
/* 136 */       return this.parameters.getIntParameter(name);
/* 137 */     } catch (ClassCastException exception) {
/* 138 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean booleanValue() throws InvalidParameterTypeException {
/* 146 */     String name = getName();
/*     */     try {
/* 148 */       return this.parameters.getBooleanParameter(name);
/* 149 */     } catch (ClassCastException exception) {
/* 150 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String stringValue() throws InvalidParameterTypeException {
/* 158 */     String name = getName();
/*     */     try {
/* 162 */       return ((CharSequence)this.parameters.getObjectParameter(name)).toString();
/* 163 */     } catch (ClassCastException exception) {
/* 164 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] doubleValueList(Unit<?> unit) throws InvalidParameterTypeException {
/* 172 */     throw unitlessParameter(this.descriptor);
/*     */   }
/*     */   
/*     */   public double[] doubleValueList() throws InvalidParameterTypeException {
/* 179 */     String name = getName();
/*     */     try {
/* 181 */       return (double[])this.parameters.getObjectParameter(name);
/* 182 */     } catch (ClassCastException exception) {
/* 183 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] intValueList() throws InvalidParameterTypeException {
/* 191 */     String name = getName();
/*     */     try {
/* 193 */       return (int[])this.parameters.getObjectParameter(name);
/* 194 */     } catch (ClassCastException exception) {
/* 195 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public URI valueFile() throws InvalidParameterTypeException {
/* 205 */     String name = getName();
/*     */     try {
/* 207 */       return (URI)this.parameters.getObjectParameter(name);
/* 208 */     } catch (ClassCastException exception) {
/* 209 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public T getValue() {
/*     */     Object value;
/* 219 */     String name = getName();
/*     */     try {
/* 222 */       value = this.parameters.getObjectParameter(name);
/* 223 */     } catch (IllegalStateException ignore) {
/* 228 */       return null;
/*     */     } 
/* 230 */     return getType().cast(value);
/*     */   }
/*     */   
/*     */   public void setValue(double value, Unit<?> unit) throws InvalidParameterValueException {
/* 237 */     throw unitlessParameter(this.descriptor);
/*     */   }
/*     */   
/*     */   public void setValue(double value) throws InvalidParameterValueException {
/* 244 */     String name = getName();
/* 245 */     Class<T> type = getType();
/*     */     try {
/* 247 */       if (type.equals(Float.class)) {
/* 247 */         this.parameters.setParameter(name, (float)value);
/*     */         return;
/*     */       } 
/* 248 */       if (type.equals(Long.class)) {
/* 248 */         this.parameters.setParameter(name, (long)value);
/*     */         return;
/*     */       } 
/* 249 */       if (type.equals(Integer.class)) {
/* 249 */         this.parameters.setParameter(name, (int)value);
/*     */         return;
/*     */       } 
/* 250 */       if (type.equals(Short.class)) {
/* 250 */         this.parameters.setParameter(name, (short)(int)value);
/*     */         return;
/*     */       } 
/* 251 */       if (type.equals(Byte.class)) {
/* 251 */         this.parameters.setParameter(name, (byte)(int)value);
/*     */         return;
/*     */       } 
/* 252 */       this.parameters.setParameter(name, value);
/* 253 */     } catch (ClassCastException exception) {
/* 254 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setValue(int value) throws InvalidParameterValueException {
/* 262 */     String name = getName();
/* 263 */     Class<T> type = getType();
/*     */     try {
/* 265 */       if (type.equals(Short.class)) {
/* 265 */         this.parameters.setParameter(name, (short)value);
/*     */         return;
/*     */       } 
/* 266 */       if (type.equals(Byte.class)) {
/* 266 */         this.parameters.setParameter(name, (byte)value);
/*     */         return;
/*     */       } 
/* 267 */       this.parameters.setParameter(name, value);
/* 268 */     } catch (ClassCastException exception) {
/* 269 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setValue(boolean value) throws InvalidParameterValueException {
/* 277 */     String name = getName();
/*     */     try {
/* 279 */       this.parameters.setParameter(name, value);
/* 280 */     } catch (ClassCastException exception) {
/* 281 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setValue(Object value) throws InvalidParameterValueException {
/* 291 */     String name = getName();
/*     */     try {
/* 293 */       this.parameters.setParameter(name, value);
/* 294 */     } catch (ClassCastException exception) {
/* 295 */       throw invalidType(exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setValue(double[] values, Unit<?> unit) throws InvalidParameterValueException {
/* 303 */     throw unitlessParameter(this.descriptor);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 311 */     if (object == this)
/* 313 */       return true; 
/* 315 */     if (super.equals(object)) {
/* 316 */       ImagingParameter that = (ImagingParameter)object;
/* 317 */       return Utilities.equals(getValue(), that.getValue());
/*     */     } 
/* 319 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 327 */     int code = super.hashCode() * 37;
/* 328 */     Object value = getValue();
/* 329 */     if (value != null)
/* 330 */       code += value.hashCode(); 
/* 332 */     return code ^ 0x19FA5DBB;
/*     */   }
/*     */   
/*     */   public Parameter<T> clone() {
/* 341 */     Parameter<T> parameter = new Parameter<T>(getDescriptor());
/* 342 */     parameter.setValue(getValue());
/* 343 */     return parameter;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ImagingParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */