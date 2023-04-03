/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterCardinalityException;
/*     */ import org.opengis.parameter.InvalidParameterTypeException;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ public class ParameterGroup extends AbstractParameter implements ParameterValueGroup {
/*     */   private static final long serialVersionUID = -1985309386356545126L;
/*     */   
/*  74 */   public static ParameterValueGroup EMPTY = new ParameterGroup(Collections.singletonMap("name", "Void"), (GeneralParameterValue[])new ParameterValue[0]);
/*     */   
/*     */   private ArrayList<GeneralParameterValue> values;
/*     */   
/*     */   private transient List<GeneralParameterValue> asList;
/*     */   
/*     */   public ParameterGroup(ParameterDescriptorGroup descriptor) {
/* 103 */     super((GeneralParameterDescriptor)descriptor);
/* 104 */     List<GeneralParameterDescriptor> parameters = descriptor.descriptors();
/* 105 */     this.values = new ArrayList<GeneralParameterValue>(parameters.size());
/* 106 */     for (GeneralParameterDescriptor element : parameters) {
/* 107 */       for (int count = element.getMinimumOccurs(); --count >= 0; ) {
/* 108 */         GeneralParameterValue value = element.createValue();
/* 109 */         ensureNonNull("createValue", value);
/* 110 */         this.values.add(value);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterGroup(ParameterDescriptorGroup descriptor, GeneralParameterValue[] values) {
/* 128 */     super((GeneralParameterDescriptor)descriptor);
/* 129 */     ensureNonNull("values", values);
/* 130 */     this.values = new ArrayList<GeneralParameterValue>(values.length);
/* 131 */     for (int i = 0; i < values.length; i++)
/* 132 */       this.values.add(values[i]); 
/* 134 */     List<GeneralParameterDescriptor> parameters = descriptor.descriptors();
/* 135 */     Map<GeneralParameterDescriptor, int[]> occurences = (Map)new LinkedHashMap<GeneralParameterDescriptor, int>(Math.round(parameters.size() / 0.75F) + 1, 0.75F);
/* 137 */     for (GeneralParameterDescriptor param : parameters) {
/* 138 */       ensureNonNull("parameters", param);
/* 139 */       occurences.put(param, new int[1]);
/*     */     } 
/* 142 */     ensureValidOccurs(values, occurences);
/*     */   }
/*     */   
/*     */   public ParameterGroup(Map<String, ?> properties, GeneralParameterValue[] values) {
/* 158 */     super((GeneralParameterDescriptor)createDescriptor(properties, values));
/* 159 */     this.values = new ArrayList<GeneralParameterValue>(values.length);
/* 160 */     for (int i = 0; i < values.length; i++)
/* 161 */       this.values.add(values[i]); 
/*     */   }
/*     */   
/*     */   private static ParameterDescriptorGroup createDescriptor(Map<String, ?> properties, GeneralParameterValue[] values) {
/* 176 */     ensureNonNull("values", values);
/* 177 */     Map<GeneralParameterDescriptor, int[]> occurences = (Map)new LinkedHashMap<GeneralParameterDescriptor, int>(Math.round(values.length / 0.75F) + 1, 0.75F);
/* 179 */     for (int i = 0; i < values.length; i++) {
/* 180 */       ensureNonNull("values", (Object[])values, i);
/* 181 */       occurences.put(values[i].getDescriptor(), new int[1]);
/*     */     } 
/* 184 */     ensureValidOccurs(values, occurences);
/* 185 */     Set<GeneralParameterDescriptor> descriptors = occurences.keySet();
/* 186 */     return new DefaultParameterDescriptorGroup(properties, descriptors.<GeneralParameterDescriptor>toArray(new GeneralParameterDescriptor[descriptors.size()]));
/*     */   }
/*     */   
/*     */   private static void ensureValidOccurs(GeneralParameterValue[] values, Map<GeneralParameterDescriptor, int[]> occurences) {
/* 208 */     for (int i = 0; i < values.length; i++) {
/* 209 */       ensureNonNull("values", (Object[])values, i);
/* 210 */       GeneralParameterDescriptor descriptor = values[i].getDescriptor();
/* 211 */       int[] count = occurences.get(descriptor);
/* 212 */       if (count == null) {
/* 213 */         String name = getName(descriptor);
/* 214 */         throw new InvalidParameterTypeException(Errors.format(65, name), name);
/*     */       } 
/* 217 */       count[0] = count[0] + 1;
/*     */     } 
/* 222 */     for (Map.Entry<GeneralParameterDescriptor, int[]> entry : occurences.entrySet()) {
/* 223 */       GeneralParameterDescriptor descriptor = entry.getKey();
/* 224 */       int count = ((int[])entry.getValue())[0];
/* 225 */       int min = descriptor.getMinimumOccurs();
/* 226 */       int max = descriptor.getMaximumOccurs();
/* 227 */       if (count < min || count > max) {
/* 228 */         String name = getName(descriptor);
/* 229 */         throw new InvalidParameterCardinalityException(Errors.format(71, name, Integer.valueOf(count), Integer.valueOf(min), Integer.valueOf(max)), name);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getDescriptor() {
/* 240 */     return (ParameterDescriptorGroup)super.getDescriptor();
/*     */   }
/*     */   
/*     */   public List<GeneralParameterValue> values() {
/* 249 */     if (this.asList == null)
/* 250 */       this.asList = new ParameterValueList((ParameterDescriptorGroup)this.descriptor, this.values); 
/* 252 */     return this.asList;
/*     */   }
/*     */   
/*     */   final GeneralParameterValue parameter(int index) throws IndexOutOfBoundsException {
/* 263 */     return this.values.get(index);
/*     */   }
/*     */   
/*     */   public ParameterValue parameter(String name) throws ParameterNotFoundException {
/* 298 */     ensureNonNull("name", name);
/* 299 */     name = name.trim();
/* 300 */     for (GeneralParameterValue value : this.values) {
/* 301 */       if (value instanceof ParameterValue && 
/* 302 */         AbstractIdentifiedObject.nameMatches((IdentifiedObject)value.getDescriptor(), name))
/* 303 */         return (ParameterValue)value; 
/*     */     } 
/* 312 */     for (GeneralParameterDescriptor descriptor : getDescriptor().descriptors()) {
/* 313 */       if (descriptor instanceof ParameterDescriptor && 
/* 314 */         AbstractIdentifiedObject.nameMatches((IdentifiedObject)descriptor, name)) {
/* 315 */         ParameterValue value = ((ParameterDescriptor)descriptor).createValue();
/* 316 */         this.values.add(value);
/* 317 */         return value;
/*     */       } 
/*     */     } 
/* 321 */     throw new ParameterNotFoundException(Errors.format(99, name), name);
/*     */   }
/*     */   
/*     */   public List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException {
/* 338 */     ensureNonNull("name", name);
/* 339 */     name = name.trim();
/* 340 */     List<ParameterValueGroup> groups = new ArrayList<ParameterValueGroup>(Math.min(this.values.size(), 10));
/* 342 */     for (GeneralParameterValue value : this.values) {
/* 343 */       if (value instanceof ParameterValueGroup && 
/* 344 */         AbstractIdentifiedObject.nameMatches((IdentifiedObject)value.getDescriptor(), name))
/* 345 */         groups.add((ParameterValueGroup)value); 
/*     */     } 
/* 354 */     if (groups.isEmpty()) {
/* 355 */       GeneralParameterDescriptor check = ((ParameterDescriptorGroup)this.descriptor).descriptor(name);
/* 357 */       if (!(check instanceof ParameterDescriptorGroup))
/* 358 */         throw new ParameterNotFoundException(Errors.format(99, name), name); 
/*     */     } 
/* 362 */     return groups;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException, InvalidParameterCardinalityException {
/* 382 */     GeneralParameterDescriptor check = ((ParameterDescriptorGroup)this.descriptor).descriptor(name);
/* 384 */     if (!(check instanceof ParameterDescriptorGroup))
/* 385 */       throw new ParameterNotFoundException(Errors.format(99, name), name); 
/* 388 */     int count = 0;
/* 389 */     for (Iterator<GeneralParameterValue> it = this.values.iterator(); it.hasNext(); ) {
/* 390 */       GeneralParameterValue generalParameterValue = it.next();
/* 391 */       if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)generalParameterValue.getDescriptor(), name))
/* 392 */         count++; 
/*     */     } 
/* 395 */     if (count >= check.getMaximumOccurs())
/* 396 */       throw new InvalidParameterCardinalityException(Errors.format(169, name, Integer.valueOf(count)), name); 
/* 399 */     ParameterValueGroup value = ((ParameterDescriptorGroup)check).createValue();
/* 400 */     this.values.add(value);
/* 401 */     return value;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 412 */     if (object == this)
/* 413 */       return true; 
/* 415 */     if (super.equals(object)) {
/* 416 */       ParameterGroup that = (ParameterGroup)object;
/* 417 */       return Utilities.equals(this.values, that.values);
/*     */     } 
/* 419 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 430 */     return super.hashCode() ^ this.values.hashCode();
/*     */   }
/*     */   
/*     */   public ParameterGroup clone() {
/* 441 */     ParameterGroup copy = (ParameterGroup)super.clone();
/* 442 */     copy.values = (ArrayList<GeneralParameterValue>)copy.values.clone();
/* 443 */     for (int i = copy.values.size(); --i >= 0;)
/* 445 */       copy.values.set(i, ((GeneralParameterValue)copy.values.get(i)).clone()); 
/* 447 */     copy.asList = null;
/* 448 */     return copy;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ParameterGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */