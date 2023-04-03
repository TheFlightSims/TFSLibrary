/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.parameter.DefaultParameterDescriptor;
/*     */ import org.geotools.parameter.DefaultParameterDescriptorGroup;
/*     */ import org.geotools.parameter.Parameters;
/*     */ import org.geotools.referencing.operation.transform.MathTransformProxy;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.InvalidParameterValueException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public abstract class MathTransformProvider extends DefaultOperationMethod {
/*     */   private static final long serialVersionUID = 7530475536803158473L;
/*     */   
/*     */   public MathTransformProvider(int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/*  91 */     this(toMap((IdentifiedObject)parameters), sourceDimensions, targetDimensions, parameters);
/*     */   }
/*     */   
/*     */   public MathTransformProvider(Map<String, ?> properties, int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 110 */     super(properties, sourceDimensions, targetDimensions, parameters);
/*     */   }
/*     */   
/*     */   private static Map<String, Object> toMap(IdentifiedObject parameters) {
/* 118 */     ensureNonNull("parameters", parameters);
/* 119 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 120 */     properties.put("name", parameters.getName());
/* 121 */     properties.put("identifiers", parameters.getIdentifiers().toArray((Object[])EMPTY_IDENTIFIER_ARRAY));
/* 122 */     properties.put("alias", parameters.getAlias().toArray((Object[])EMPTY_ALIAS_ARRAY));
/* 123 */     return properties;
/*     */   }
/*     */   
/*     */   public Class<? extends Operation> getOperationType() {
/* 141 */     return Operation.class;
/*     */   }
/*     */   
/*     */   protected static ParameterDescriptor<Double> createDescriptor(ReferenceIdentifier[] identifiers, double defaultValue, double minimum, double maximum, Unit<?> unit) {
/* 192 */     return (ParameterDescriptor<Double>)DefaultParameterDescriptor.create(toMap(identifiers), defaultValue, minimum, maximum, unit, true);
/*     */   }
/*     */   
/*     */   protected static ParameterDescriptor<Double> createOptionalDescriptor(ReferenceIdentifier[] identifiers, double minimum, double maximum, Unit<?> unit) {
/* 209 */     return (ParameterDescriptor<Double>)DefaultParameterDescriptor.create(toMap(identifiers), Double.NaN, minimum, maximum, unit, false);
/*     */   }
/*     */   
/*     */   protected static ParameterDescriptorGroup createDescriptorGroup(ReferenceIdentifier[] identifiers, GeneralParameterDescriptor[] parameters) {
/* 234 */     return (ParameterDescriptorGroup)new DefaultParameterDescriptorGroup(toMap(identifiers), parameters);
/*     */   }
/*     */   
/*     */   protected static Map<String, Object> toMap(ReferenceIdentifier[] identifiers) {
/* 242 */     ensureNonNull("identifiers", identifiers);
/* 243 */     if (identifiers.length == 0)
/* 244 */       throw new IllegalArgumentException(Errors.format(46)); 
/* 246 */     int idCount = 0;
/* 247 */     int aliasCount = 0;
/* 248 */     ReferenceIdentifier[] id = new ReferenceIdentifier[identifiers.length];
/* 249 */     GenericName[] alias = new GenericName[identifiers.length];
/* 250 */     for (int i = 0; i < identifiers.length; i++) {
/* 251 */       ReferenceIdentifier candidate = identifiers[i];
/* 252 */       if (candidate instanceof GenericName) {
/* 253 */         alias[aliasCount++] = (GenericName)candidate;
/*     */       } else {
/* 255 */         id[idCount++] = candidate;
/*     */       } 
/*     */     } 
/* 258 */     id = (ReferenceIdentifier[])XArray.resize((Object[])id, idCount);
/* 259 */     alias = (GenericName[])XArray.resize((Object[])alias, aliasCount);
/* 260 */     Map<String, Object> properties = new HashMap<String, Object>(4, 0.8F);
/* 261 */     properties.put("name", identifiers[0]);
/* 262 */     properties.put("identifiers", id);
/* 263 */     properties.put("alias", alias);
/* 264 */     return properties;
/*     */   }
/*     */   
/*     */   protected ParameterValueGroup ensureValidValues(ParameterValueGroup values) throws InvalidParameterNameException, InvalidParameterValueException {
/* 290 */     ParameterDescriptorGroup parameters = getParameters();
/* 291 */     ParameterDescriptorGroup parameterDescriptorGroup1 = values.getDescriptor();
/* 292 */     if (parameters.equals(parameterDescriptorGroup1))
/* 298 */       return values; 
/* 308 */     ParameterValueGroup copy = parameters.createValue();
/* 309 */     copy(values, copy);
/* 310 */     return copy;
/*     */   }
/*     */   
/*     */   private static void copy(ParameterValueGroup values, ParameterValueGroup copy) throws InvalidParameterNameException, InvalidParameterValueException {
/* 329 */     for (GeneralParameterValue value : values.values()) {
/*     */       ParameterValue<?> target;
/* 330 */       String name = value.getDescriptor().getName().getCode();
/* 331 */       if (value instanceof ParameterValueGroup) {
/* 336 */         GeneralParameterDescriptor descriptor = copy.getDescriptor().descriptor(name);
/* 337 */         if (descriptor instanceof ParameterDescriptorGroup) {
/* 338 */           ParameterValueGroup groups = (ParameterValueGroup)descriptor.createValue();
/* 339 */           copy((ParameterValueGroup)value, groups);
/* 340 */           values.groups(name).add(groups);
/*     */           continue;
/*     */         } 
/* 343 */         throw new InvalidParameterNameException(Errors.format(176, name), name);
/*     */       } 
/* 350 */       ParameterValue<?> source = (ParameterValue)value;
/*     */       try {
/* 353 */         target = copy.parameter(name);
/* 354 */       } catch (ParameterNotFoundException cause) {
/* 355 */         InvalidParameterNameException exception = new InvalidParameterNameException(Errors.format(176, name), name);
/* 358 */         exception.initCause((Throwable)cause);
/* 359 */         throw exception;
/*     */       } 
/* 361 */       Object v = source.getValue();
/* 362 */       Unit<?> unit = source.getUnit();
/* 363 */       if (unit == null) {
/* 364 */         target.setValue(v);
/*     */         continue;
/*     */       } 
/* 365 */       if (v instanceof Number) {
/* 366 */         target.setValue(((Number)v).doubleValue(), unit);
/*     */         continue;
/*     */       } 
/* 367 */       if (v instanceof double[]) {
/* 368 */         target.setValue((double[])v, unit);
/*     */         continue;
/*     */       } 
/* 370 */       throw new InvalidParameterValueException(Errors.format(58, name, v), name, v);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static <T> ParameterValue<T> getParameter(ParameterDescriptor<T> param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 396 */     String name = getName((IdentifiedObject)param, group.getDescriptor().getName().getAuthority());
/* 397 */     if (name == null)
/* 398 */       name = param.getName().getCode(); 
/* 400 */     if (param.getMinimumOccurs() != 0)
/* 401 */       return Parameters.cast(group.parameter(name), param.getValueClass()); 
/* 412 */     GeneralParameterDescriptor search = group.getDescriptor().descriptor(name);
/* 413 */     if (search instanceof ParameterDescriptor)
/* 414 */       for (GeneralParameterValue candidate : group.values()) {
/* 415 */         if (search.equals(candidate.getDescriptor()))
/* 416 */           return Parameters.cast((ParameterValue)candidate, param.getValueClass()); 
/*     */       }  
/* 420 */     return null;
/*     */   }
/*     */   
/*     */   protected static <T> T value(ParameterDescriptor<T> param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 442 */     ParameterValue<T> value = getParameter(param, group);
/* 443 */     return (value != null) ? (T)value.getValue() : null;
/*     */   }
/*     */   
/*     */   protected static String stringValue(ParameterDescriptor<?> param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 464 */     ParameterValue<?> value = getParameter(param, group);
/* 465 */     return (value != null) ? value.stringValue() : null;
/*     */   }
/*     */   
/*     */   protected static int intValue(ParameterDescriptor<?> param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 486 */     ParameterValue<?> value = getParameter(param, group);
/* 487 */     return (value != null) ? value.intValue() : 0;
/*     */   }
/*     */   
/*     */   protected static double doubleValue(ParameterDescriptor<?> param, ParameterValueGroup group) throws ParameterNotFoundException {
/* 510 */     Unit<?> unit = param.getUnit();
/* 511 */     ParameterValue<?> value = getParameter(param, group);
/* 512 */     return (value == null) ? Double.NaN : ((unit != null) ? value.doubleValue(unit) : value.doubleValue());
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 526 */     Class<? extends Operation> type = getOperationType();
/* 527 */     if (Projection.class.isAssignableFrom(type))
/* 528 */       return super.formatWKT(formatter); 
/* 530 */     formatter.setInvalidWKT(OperationMethod.class);
/* 531 */     return "OperationMethod";
/*     */   }
/*     */   
/*     */   protected abstract MathTransform createMathTransform(ParameterValueGroup paramParameterValueGroup) throws InvalidParameterNameException, ParameterNotFoundException, InvalidParameterValueException, FactoryException;
/*     */   
/*     */   protected static final class Delegate extends MathTransformProxy {
/*     */     private static final long serialVersionUID = -3942740060970730790L;
/*     */     
/*     */     public final OperationMethod method;
/*     */     
/*     */     public Delegate(MathTransform transform, OperationMethod method) {
/* 580 */       super(transform);
/* 581 */       this.method = method;
/* 582 */       MathTransformProvider.ensureNonNull("transform", transform);
/* 583 */       MathTransformProvider.ensureNonNull("method", method);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\MathTransformProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */