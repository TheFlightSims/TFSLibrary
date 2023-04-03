/*     */ package org.apache.commons.beanutils.converters;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.commons.beanutils.ConversionException;
/*     */ import org.apache.commons.beanutils.Converter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public abstract class AbstractConverter implements Converter {
/*     */   private static final String DEFAULT_CONFIG_MSG = "(N.B. Converters can be configured to use default values to avoid throwing exceptions)";
/*     */   
/*     */   private static final String PACKAGE = "org.apache.commons.beanutils.converters.";
/*     */   
/*     */   private transient Log log;
/*     */   
/*     */   private boolean useDefault = false;
/*     */   
/*  74 */   private Object defaultValue = null;
/*     */   
/*     */   public AbstractConverter(Object defaultValue) {
/*  94 */     setDefaultValue(defaultValue);
/*     */   }
/*     */   
/*     */   public boolean isUseDefault() {
/* 108 */     return this.useDefault;
/*     */   }
/*     */   
/*     */   public Object convert(Class type, Object value) {
/* 123 */     Class sourceType = (value == null) ? null : value.getClass();
/* 124 */     Class targetType = primitive((type == null) ? getDefaultType() : type);
/* 126 */     if (log().isDebugEnabled())
/* 127 */       log().debug("Converting" + ((value == null) ? "" : (" '" + toString(sourceType) + "'")) + " value '" + value + "' to type '" + toString(targetType) + "'"); 
/* 132 */     value = convertArray(value);
/* 135 */     if (value == null)
/* 136 */       return handleMissing(targetType); 
/* 139 */     sourceType = value.getClass();
/*     */     try {
/* 143 */       if (targetType.equals(String.class))
/* 144 */         return convertToString(value); 
/* 147 */       if (targetType.equals(sourceType)) {
/* 148 */         if (log().isDebugEnabled())
/* 149 */           log().debug("    No conversion required, value is already a " + toString(targetType)); 
/* 152 */         return value;
/*     */       } 
/* 156 */       Object result = convertToType(targetType, value);
/* 157 */       if (log().isDebugEnabled())
/* 158 */         log().debug("    Converted to " + toString(targetType) + " value '" + result + "'"); 
/* 161 */       return result;
/* 163 */     } catch (Throwable t) {
/* 164 */       return handleError(targetType, value, t);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String convertToString(Object value) throws Throwable {
/* 182 */     return value.toString();
/*     */   }
/*     */   
/*     */   protected Object convertArray(Object value) {
/* 210 */     if (value == null)
/* 211 */       return null; 
/* 213 */     if (value.getClass().isArray()) {
/* 214 */       if (Array.getLength(value) > 0)
/* 215 */         return Array.get(value, 0); 
/* 217 */       return null;
/*     */     } 
/* 220 */     if (value instanceof Collection) {
/* 221 */       Collection collection = (Collection)value;
/* 222 */       if (collection.size() > 0)
/* 223 */         return collection.iterator().next(); 
/* 225 */       return null;
/*     */     } 
/* 228 */     return value;
/*     */   }
/*     */   
/*     */   protected Object handleError(Class type, Object value, Throwable cause) {
/* 245 */     if (log().isDebugEnabled())
/* 246 */       if (cause instanceof ConversionException) {
/* 247 */         log().debug("    Conversion threw ConversionException: " + cause.getMessage());
/*     */       } else {
/* 249 */         log().debug("    Conversion threw " + cause);
/*     */       }  
/* 253 */     if (this.useDefault)
/* 254 */       return handleMissing(type); 
/* 257 */     ConversionException cex = null;
/* 258 */     if (cause instanceof ConversionException) {
/* 259 */       cex = (ConversionException)cause;
/* 260 */       if (log().isDebugEnabled()) {
/* 261 */         log().debug("    Re-throwing ConversionException: " + cex.getMessage());
/* 262 */         log().debug("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
/*     */       } 
/*     */     } else {
/* 265 */       String msg = "Error converting from '" + toString(value.getClass()) + "' to '" + toString(type) + "' " + cause.getMessage();
/* 267 */       cex = new ConversionException(msg, cause);
/* 268 */       if (log().isDebugEnabled()) {
/* 269 */         log().debug("    Throwing ConversionException: " + msg);
/* 270 */         log().debug("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
/*     */       } 
/* 272 */       BeanUtils.initCause((Throwable)cex, cause);
/*     */     } 
/* 275 */     throw cex;
/*     */   }
/*     */   
/*     */   protected Object handleMissing(Class type) {
/* 292 */     if (this.useDefault || type.equals(String.class)) {
/* 293 */       Object value = getDefault(type);
/* 294 */       if (this.useDefault && value != null && !type.equals(value.getClass()))
/*     */         try {
/* 296 */           value = convertToType(type, this.defaultValue);
/* 297 */         } catch (Throwable t) {
/* 298 */           log().error("    Default conversion to " + toString(type) + "failed: " + t);
/*     */         }  
/* 302 */       if (log().isDebugEnabled())
/* 303 */         log().debug("    Using default " + ((value == null) ? "" : (toString(value.getClass()) + " ")) + "value '" + this.defaultValue + "'"); 
/* 307 */       return value;
/*     */     } 
/* 310 */     ConversionException cex = new ConversionException("No value specified for '" + toString(type) + "'");
/* 312 */     if (log().isDebugEnabled()) {
/* 313 */       log().debug("    Throwing ConversionException: " + cex.getMessage());
/* 314 */       log().debug("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
/*     */     } 
/* 316 */     throw cex;
/*     */   }
/*     */   
/*     */   protected void setDefaultValue(Object defaultValue) {
/* 334 */     this.useDefault = false;
/* 335 */     if (log().isDebugEnabled())
/* 336 */       log().debug("Setting default value: " + defaultValue); 
/* 338 */     if (defaultValue == null) {
/* 339 */       this.defaultValue = null;
/*     */     } else {
/* 341 */       this.defaultValue = convert(getDefaultType(), defaultValue);
/*     */     } 
/* 343 */     this.useDefault = true;
/*     */   }
/*     */   
/*     */   protected Object getDefault(Class type) {
/* 360 */     if (type.equals(String.class))
/* 361 */       return null; 
/* 363 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 373 */     return toString(getClass()) + "[UseDefault=" + this.useDefault + "]";
/*     */   }
/*     */   
/*     */   Log log() {
/* 389 */     if (this.log == null)
/* 390 */       this.log = LogFactory.getLog(getClass()); 
/* 392 */     return this.log;
/*     */   }
/*     */   
/*     */   Class primitive(Class type) {
/* 401 */     if (type == null || !type.isPrimitive())
/* 402 */       return type; 
/* 405 */     if (type == int.class)
/* 406 */       return Integer.class; 
/* 407 */     if (type == double.class)
/* 408 */       return Double.class; 
/* 409 */     if (type == long.class)
/* 410 */       return Long.class; 
/* 411 */     if (type == boolean.class)
/* 412 */       return Boolean.class; 
/* 413 */     if (type == float.class)
/* 414 */       return Float.class; 
/* 415 */     if (type == short.class)
/* 416 */       return Short.class; 
/* 417 */     if (type == byte.class)
/* 418 */       return Byte.class; 
/* 419 */     if (type == char.class)
/* 420 */       return Character.class; 
/* 422 */     return type;
/*     */   }
/*     */   
/*     */   String toString(Class type) {
/* 432 */     String typeName = null;
/* 433 */     if (type == null) {
/* 434 */       typeName = "null";
/* 435 */     } else if (type.isArray()) {
/* 436 */       Class elementType = type.getComponentType();
/* 437 */       int count = 1;
/* 438 */       while (elementType.isArray()) {
/* 439 */         elementType = elementType.getComponentType();
/* 440 */         count++;
/*     */       } 
/* 442 */       typeName = elementType.getName();
/* 443 */       for (int i = 0; i < count; i++)
/* 444 */         typeName = typeName + "[]"; 
/*     */     } else {
/* 447 */       typeName = type.getName();
/*     */     } 
/* 449 */     if (typeName.startsWith("java.lang.") || typeName.startsWith("java.util.") || typeName.startsWith("java.math.")) {
/* 452 */       typeName = typeName.substring("java.lang.".length());
/* 453 */     } else if (typeName.startsWith("org.apache.commons.beanutils.converters.")) {
/* 454 */       typeName = typeName.substring("org.apache.commons.beanutils.converters.".length());
/*     */     } 
/* 456 */     return typeName;
/*     */   }
/*     */   
/*     */   public AbstractConverter() {}
/*     */   
/*     */   protected abstract Object convertToType(Class paramClass, Object paramObject) throws Throwable;
/*     */   
/*     */   protected abstract Class getDefaultType();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\AbstractConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */