/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.OperationDescriptor;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.ParameterList;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListImpl;
/*     */ import javax.media.jai.util.Range;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ public class ImagingParameters extends AbstractParameter implements ParameterValueGroup {
/*     */   private static final long serialVersionUID = 1378692626023992530L;
/*     */   
/*     */   public final ParameterList parameters;
/*     */   
/*     */   private List<GeneralParameterValue> values;
/*     */   
/*     */   public ImagingParameters(ImagingParameterDescriptors descriptor) {
/* 106 */     super(descriptor);
/* 107 */     if (descriptor.operation instanceof OperationDescriptor) {
/* 109 */       this.parameters = (ParameterList)new ParameterBlockJAI((OperationDescriptor)descriptor.operation, descriptor.registryMode);
/*     */     } else {
/* 113 */       this.parameters = (ParameterList)new ParameterListImpl(descriptor.descriptor);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImagingParameters(Map<String, ?> properties, ParameterList parameters) {
/* 125 */     super(new ImagingParameterDescriptors(properties, parameters.getParameterListDescriptor()));
/* 126 */     this.parameters = parameters;
/* 127 */     ensureNonNull("parameters", parameters);
/*     */   }
/*     */   
/*     */   private static boolean compatible(ParameterDescriptor descriptor, ParameterListDescriptor listDescriptor, String[] names, Class<?>[] types, String[] enumerated) {
/* 155 */     String name = descriptor.getName().getCode().trim();
/* 156 */     Class<?> type = null;
/* 157 */     if (names != null)
/* 158 */       for (int i = 0; i < names.length; i++) {
/* 159 */         if (name.equalsIgnoreCase(names[i])) {
/* 160 */           type = types[i];
/*     */           break;
/*     */         } 
/*     */       }  
/* 165 */     if (type == null || !type.isAssignableFrom(descriptor.getValueClass()))
/* 166 */       return false; 
/* 168 */     Range range = listDescriptor.getParamValueRange(name);
/* 169 */     if (range != null) {
/* 171 */       Comparable c = descriptor.getMinimumValue();
/* 172 */       if (c != null && !range.contains(c))
/* 173 */         return false; 
/* 175 */       c = descriptor.getMaximumValue();
/* 176 */       if (c != null && !range.contains(c))
/* 177 */         return false; 
/*     */     } 
/* 180 */     if (enumerated != null)
/* 181 */       for (int i = 0; i < enumerated.length; i++) {
/* 182 */         if (name.equalsIgnoreCase(enumerated[i])) {
/* 184 */           EnumeratedParameter[] restrictions = listDescriptor.getEnumeratedParameterValues(name);
/* 185 */           Set<?> valids = descriptor.getValidValues();
/* 186 */           if (valids == null || !Arrays.<EnumeratedParameter>asList(restrictions).containsAll(valids))
/* 187 */             return false; 
/*     */         } 
/*     */       }  
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   private GeneralParameterValue[] createElements() {
/* 205 */     ImagingParameterDescriptors descriptor = (ImagingParameterDescriptors)this.descriptor;
/* 206 */     ParameterListDescriptor listDescriptor = this.parameters.getParameterListDescriptor();
/* 207 */     String[] names = listDescriptor.getParamNames();
/* 208 */     Class[] types = listDescriptor.getParamClasses();
/* 209 */     String[] enumerated = listDescriptor.getEnumeratedParameterNames();
/* 210 */     List<GeneralParameterDescriptor> descriptors = descriptor.descriptors();
/* 211 */     GeneralParameterValue[] values = new GeneralParameterValue[descriptors.size()];
/* 212 */     for (int i = 0; i < values.length; i++) {
/*     */       ParameterValue value;
/* 213 */       ParameterDescriptor d = (ParameterDescriptor)descriptors.get(i);
/* 215 */       if (compatible(d, listDescriptor, names, types, enumerated)) {
/* 220 */         value = new ImagingParameter(d, this.parameters);
/*     */       } else {
/* 229 */         value = d.createValue();
/*     */       } 
/* 231 */       values[i] = (GeneralParameterValue)value;
/*     */     } 
/* 236 */     for (int j = 0; j < values.length; j++) {
/* 237 */       String name = values[j].getDescriptor().getName().getCode().trim();
/* 238 */       for (int k = 0; k < values.length; k++) {
/* 239 */         if (k != j) {
/* 240 */           ParameterDescriptor d = (ParameterDescriptor)values[k].getDescriptor();
/* 241 */           if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)d, name))
/* 242 */             throw new InvalidParameterNameException(Errors.format(154, d.getName().getCode(), Integer.valueOf(j), name, Integer.valueOf(k)), name); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 249 */     this.values = (List<GeneralParameterValue>)UnmodifiableArrayList.wrap((Object[])values);
/* 250 */     return values;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getDescriptor() {
/* 258 */     return (ParameterDescriptorGroup)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public synchronized List<GeneralParameterValue> values() {
/* 267 */     if (this.values == null)
/* 268 */       createElements(); 
/* 270 */     assert ((ParameterDescriptorGroup)this.descriptor).descriptors().size() == this.values.size() : this.values;
/* 271 */     return this.values;
/*     */   }
/*     */   
/*     */   public synchronized ParameterValue parameter(String name) throws ParameterNotFoundException {
/* 284 */     ensureNonNull("name", name);
/* 285 */     name = name.trim();
/* 286 */     List<GeneralParameterValue> values = values();
/* 287 */     int size = values.size();
/* 288 */     for (int i = 0; i < size; i++) {
/* 289 */       ParameterValue value = (ParameterValue)values.get(i);
/* 290 */       if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)value.getDescriptor(), name))
/* 291 */         return value; 
/*     */     } 
/* 294 */     throw new ParameterNotFoundException(Errors.format(99, name), name);
/*     */   }
/*     */   
/*     */   public List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException {
/* 303 */     throw new ParameterNotFoundException(Errors.format(99, name), name);
/*     */   }
/*     */   
/*     */   public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
/* 312 */     throw new ParameterNotFoundException(Errors.format(99, name), name);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 321 */     if (object == this)
/* 323 */       return true; 
/* 325 */     if (super.equals(object)) {
/* 326 */       ImagingParameters that = (ImagingParameters)object;
/* 327 */       return Utilities.equals(this.parameters, that.parameters);
/*     */     } 
/* 329 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 338 */     return super.hashCode() * 37 + this.parameters.hashCode();
/*     */   }
/*     */   
/*     */   public synchronized ImagingParameters clone() {
/* 346 */     ImagingParameters copy = (ImagingParameters)super.clone();
/*     */     try {
/* 348 */       Method cloneMethod = this.parameters.getClass().getMethod("clone", (Class[])null);
/* 349 */       Field paramField = ImagingParameters.class.getField("parameters");
/* 350 */       paramField.setAccessible(true);
/* 351 */       paramField.set(copy, cloneMethod.invoke(this.parameters, (Object[])null));
/* 352 */     } catch (Exception exception) {
/* 354 */       throw new UnsupportedOperationException("Clone not supported.", exception);
/*     */     } 
/* 366 */     if (copy.values != null) {
/* 367 */       GeneralParameterValue[] cloned = copy.createElements();
/* 368 */       assert this.values.size() == cloned.length : this.values;
/* 369 */       for (int i = 0; i < cloned.length; i++) {
/* 370 */         if (!(cloned[i] instanceof ImagingParameter))
/* 371 */           cloned[i] = (GeneralParameterValue)((ParameterValue)this.values.get(i)).clone(); 
/*     */       } 
/*     */     } 
/* 375 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ImagingParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */