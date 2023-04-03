/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterTypeException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ public final class Parameters {
/*     */   private static final double EPS = 1.0E-8D;
/*     */   
/*  94 */   public static ParameterDescriptorGroup EMPTY_GROUP = new DefaultParameterDescriptorGroup("empty", new org.opengis.parameter.GeneralParameterDescriptor[0]);
/*     */   
/*     */   public static <T> ParameterDescriptor<T> cast(ParameterDescriptor<?> descriptor, Class<T> type) throws ClassCastException {
/* 121 */     if (descriptor != null) {
/* 122 */       Class<?> actual = descriptor.getValueClass();
/* 125 */       if (!type.equals(actual))
/* 126 */         throw new ClassCastException(Errors.format(13, descriptor.getName().getCode(), actual)); 
/*     */     } 
/* 130 */     return (ParameterDescriptor)descriptor;
/*     */   }
/*     */   
/*     */   public static <T> ParameterValue<T> cast(ParameterValue<?> value, Class<T> type) throws ClassCastException {
/* 150 */     if (value != null) {
/* 151 */       ParameterDescriptor descriptor = value.getDescriptor();
/* 152 */       Class<?> actual = descriptor.getValueClass();
/* 153 */       if (!type.equals(actual))
/* 154 */         throw new ClassCastException(Errors.format(13, descriptor.getName().getCode(), actual)); 
/*     */     } 
/* 158 */     return (ParameterValue)value;
/*     */   }
/*     */   
/*     */   public static boolean isValid(ParameterValue<?> parameter) {
/* 180 */     ParameterDescriptor<?> descriptor = parameter.getDescriptor();
/* 181 */     Object value = parameter.getValue();
/* 182 */     if (value == null) {
/* 184 */       Set<?> validValues = descriptor.getValidValues();
/* 185 */       return (validValues != null && validValues.contains(value));
/*     */     } 
/* 187 */     Class<?> type = Classes.primitiveToWrapper(value.getClass());
/* 188 */     Class<?> expected = Classes.primitiveToWrapper(descriptor.getValueClass());
/* 189 */     if (expected.isAssignableFrom(type))
/* 190 */       return false; 
/* 192 */     if (type.isArray()) {
/* 194 */       int length = Array.getLength(value);
/* 195 */       for (int i = 0; i < length; i++) {
/* 196 */         if (!isValidValue(Array.get(value, i), descriptor))
/* 197 */           return false; 
/*     */       } 
/* 200 */     } else if (value instanceof Collection) {
/* 202 */       for (Object element : value) {
/* 203 */         if (!isValidValue(element, descriptor))
/* 204 */           return false; 
/*     */       } 
/* 208 */     } else if (!isValidValue(value, descriptor)) {
/* 209 */       return false;
/*     */     } 
/* 212 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean isValidValue(Object value, ParameterDescriptor<?> descriptor) {
/* 230 */     Set<?> validValues = descriptor.getValidValues();
/* 231 */     if (validValues != null && !validValues.contains(value))
/* 232 */       return false; 
/* 235 */     Comparable<Object> min = descriptor.getMinimumValue();
/* 236 */     if (min != null && min.compareTo(value) > 0)
/* 237 */       return false; 
/* 240 */     Comparable<Object> max = descriptor.getMaximumValue();
/* 241 */     if (max != null && max.compareTo(value) < 0)
/* 242 */       return false; 
/* 244 */     return true;
/*     */   }
/*     */   
/*     */   public static List<Object> search(GeneralParameterValue param, String name, int maxDepth) {
/* 274 */     List<Object> list = new ArrayList();
/* 275 */     search(param, name, maxDepth, list);
/* 276 */     return list;
/*     */   }
/*     */   
/*     */   private static void search(GeneralParameterValue param, String name, int maxDepth, Collection<Object> list) {
/* 285 */     if (maxDepth >= 0) {
/* 286 */       if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)param.getDescriptor(), name))
/* 287 */         list.add(param); 
/* 289 */       if (maxDepth != 0 && param instanceof ParameterValueGroup)
/* 290 */         for (GeneralParameterValue value : ((ParameterValueGroup)param).values())
/* 291 */           search(value, name, maxDepth - 1, list);  
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void copy(ParameterValueGroup source, ParameterValueGroup target) {
/* 309 */     for (GeneralParameterValue param : source.values()) {
/* 310 */       String name = param.getDescriptor().getName().getCode();
/* 311 */       if (param instanceof ParameterValueGroup) {
/* 312 */         copy((ParameterValueGroup)param, target.addGroup(name));
/*     */         continue;
/*     */       } 
/* 314 */       target.parameter(name).setValue(((ParameterValue)param).getValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Map<String, Object> toNameValueMap(GeneralParameterValue parameters, Map<String, Object> destination) {
/* 333 */     if (destination == null)
/* 334 */       destination = new LinkedHashMap<String, Object>(); 
/* 336 */     if (parameters instanceof ParameterValue) {
/* 337 */       ParameterValue param = (ParameterValue)parameters;
/* 338 */       Object value = param.getValue();
/* 339 */       Object old = destination.put(param.getDescriptor().getName().getCode(), value);
/* 340 */       if (old != null && !old.equals(value))
/* 343 */         throw new IllegalArgumentException("Inconsistent value"); 
/*     */     } 
/* 346 */     if (parameters instanceof ParameterValueGroup) {
/* 347 */       ParameterValueGroup group = (ParameterValueGroup)parameters;
/* 348 */       for (GeneralParameterValue value : group.values())
/* 349 */         destination = toNameValueMap(value, destination); 
/*     */     } 
/* 352 */     return destination;
/*     */   }
/*     */   
/*     */   public static boolean ensureSet(ParameterValueGroup parameters, String name, double value, Unit<?> unit, boolean force) {
/*     */     ParameterValue<?> parameter;
/*     */     try {
/* 380 */       parameter = parameters.parameter(name);
/* 381 */     } catch (ParameterNotFoundException ignore) {
/* 388 */       return false;
/*     */     } 
/*     */     try {
/* 391 */       if (Math.abs(parameter.doubleValue(unit) / value - 1.0D) <= 1.0E-8D)
/* 392 */         return false; 
/* 394 */     } catch (InvalidParameterTypeException exception) {
/* 399 */       return false;
/* 400 */     } catch (IllegalStateException exception) {
/* 404 */       parameter.setValue(value, unit);
/* 405 */       return true;
/*     */     } 
/* 410 */     if (force) {
/* 411 */       parameter.setValue(value, unit);
/*     */     } else {
/* 414 */       LogRecord record = new LogRecord(Level.FINE, "Axis length mismatch.");
/* 415 */       record.setSourceClassName(Parameters.class.getName());
/* 416 */       record.setSourceMethodName("ensureSet");
/* 417 */       Logger logger = Logging.getLogger(Parameters.class);
/* 418 */       record.setLoggerName(logger.getName());
/* 419 */       logger.log(record);
/*     */     } 
/* 421 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\Parameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */