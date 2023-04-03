/*     */ package org.geotools.parameter;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.List;
/*     */ import java.util.RandomAccess;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.InvalidParameterCardinalityException;
/*     */ import org.opengis.parameter.InvalidParameterNameException;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ 
/*     */ final class ParameterValueList extends AbstractList<GeneralParameterValue> implements RandomAccess, Serializable {
/*     */   private static final long serialVersionUID = -7446077551686135264L;
/*     */   
/*     */   private final ParameterDescriptorGroup descriptor;
/*     */   
/*     */   private final List<GeneralParameterValue> values;
/*     */   
/*     */   public ParameterValueList(ParameterDescriptorGroup descriptor, List<GeneralParameterValue> values) {
/*  73 */     this.descriptor = descriptor;
/*  74 */     this.values = values;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  82 */     return this.values.isEmpty();
/*     */   }
/*     */   
/*     */   public int size() {
/*  83 */     return this.values.size();
/*     */   }
/*     */   
/*     */   public GeneralParameterValue get(int i) {
/*  84 */     return this.values.get(i);
/*     */   }
/*     */   
/*     */   public int indexOf(Object o) {
/*  85 */     return this.values.indexOf(o);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object o) {
/*  86 */     return this.values.lastIndexOf(o);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  87 */     return this.values.equals(o);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  88 */     return this.values.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  89 */     return this.values.toString();
/*     */   }
/*     */   
/*     */   public boolean add(GeneralParameterValue parameter) {
/* 114 */     this.modCount++;
/* 115 */     GeneralParameterDescriptor type = parameter.getDescriptor();
/* 116 */     List<GeneralParameterDescriptor> descriptors = this.descriptor.descriptors();
/* 117 */     String name = type.getName().getCode();
/* 118 */     if (!descriptors.contains(type)) {
/*     */       Object value;
/* 123 */       for (GeneralParameterDescriptor descriptor : descriptors) {
/* 124 */         if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)descriptor, name))
/* 129 */           throw new IllegalArgumentException(Errors.format(65, name)); 
/*     */       } 
/* 138 */       if (parameter instanceof ParameterValue) {
/* 139 */         value = ((ParameterValue)parameter).getValue();
/*     */       } else {
/* 141 */         value = "(group)";
/*     */       } 
/* 143 */       throw new InvalidParameterNameException(Errors.format(58, name, value), name);
/*     */     } 
/* 146 */     int max = type.getMaximumOccurs();
/* 147 */     if (max == 1) {
/* 151 */       for (int i = this.values.size(); --i >= 0; ) {
/* 152 */         GeneralParameterValue oldValue = this.values.get(i);
/* 153 */         GeneralParameterDescriptor oldDescriptor = oldValue.getDescriptor();
/* 154 */         if (type.equals(oldDescriptor)) {
/* 155 */           assert AbstractIdentifiedObject.nameMatches((IdentifiedObject)oldDescriptor, name) : parameter;
/* 156 */           boolean same = parameter.equals(oldValue);
/* 157 */           this.values.set(i, parameter);
/* 158 */           return !same;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 166 */       int count = 0;
/* 167 */       for (GeneralParameterValue value : this.values) {
/* 168 */         if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)value.getDescriptor(), name))
/* 169 */           count++; 
/*     */       } 
/* 172 */       if (count >= max)
/* 173 */         throw new InvalidParameterCardinalityException(Errors.format(169, name, Integer.valueOf(count)), name); 
/*     */     } 
/* 177 */     this.values.add(parameter);
/* 178 */     return true;
/*     */   }
/*     */   
/*     */   public GeneralParameterValue remove(int index) {
/* 188 */     return remove(((GeneralParameterValue)this.values.get(index)).getDescriptor(), index);
/*     */   }
/*     */   
/*     */   private GeneralParameterValue remove(GeneralParameterDescriptor type, int index) {
/* 198 */     this.modCount++;
/* 199 */     int count = 0;
/* 200 */     String name = type.getName().getCode();
/* 201 */     for (GeneralParameterValue generalParameterValue : this.values) {
/* 202 */       if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)generalParameterValue.getDescriptor(), name))
/* 203 */         count++; 
/*     */     } 
/* 206 */     int min = type.getMinimumOccurs();
/* 207 */     if (count <= min) {
/* 208 */       int max = type.getMaximumOccurs();
/* 209 */       throw new InvalidParameterCardinalityException(Errors.format(71, name, Integer.valueOf(count - 1), Integer.valueOf(min), Integer.valueOf(max)), name);
/*     */     } 
/* 212 */     GeneralParameterValue value = this.values.remove(index);
/* 213 */     assert value != null && type.equals(value.getDescriptor()) : value;
/* 214 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\parameter\ParameterValueList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */