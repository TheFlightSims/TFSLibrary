/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractLazyComplexTypeImpl extends AbstractLazyAttributeTypeImpl implements ComplexType {
/*     */   private Collection<PropertyDescriptor> descriptors;
/*     */   
/*     */   private Map<Name, PropertyDescriptor> descriptorMap;
/*     */   
/*     */   public AbstractLazyComplexTypeImpl(Name name, boolean identified, boolean isAbstract, List<Filter> restrictions, InternationalString description) {
/*  67 */     super(name, Collection.class, identified, isAbstract, restrictions, description);
/*     */   }
/*     */   
/*     */   public abstract Collection<PropertyDescriptor> buildDescriptors();
/*     */   
/*     */   private void requireDescriptors() {
/*  86 */     if (this.descriptors == null) {
/*  87 */       Collection<PropertyDescriptor> builtDescriptors = buildDescriptors();
/*  88 */       if (builtDescriptors == null) {
/*  89 */         this.descriptors = Collections.emptyList();
/*  90 */         this.descriptorMap = Collections.emptyMap();
/*     */       } else {
/*  92 */         Collection<PropertyDescriptor> localDescriptors = new ArrayList<PropertyDescriptor>(builtDescriptors);
/*  94 */         Map<Name, PropertyDescriptor> localDescriptorMap = new HashMap<Name, PropertyDescriptor>();
/*  95 */         for (PropertyDescriptor descriptor : localDescriptors)
/*  96 */           localDescriptorMap.put(descriptor.getName(), descriptor); 
/*  98 */         this.descriptors = Collections.unmodifiableCollection(localDescriptors);
/*  99 */         this.descriptorMap = Collections.unmodifiableMap(localDescriptorMap);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Class<Collection<Property>> getBinding() {
/* 110 */     return (Class)super.getBinding();
/*     */   }
/*     */   
/*     */   public Collection<PropertyDescriptor> getDescriptors() {
/* 117 */     requireDescriptors();
/* 118 */     return this.descriptors;
/*     */   }
/*     */   
/*     */   public PropertyDescriptor getDescriptor(Name name) {
/* 125 */     requireDescriptors();
/* 126 */     return this.descriptorMap.get(name);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public PropertyDescriptor getDescriptor(String name) {
/* 140 */     requireDescriptors();
/* 141 */     PropertyDescriptor result = getDescriptor((Name)new NameImpl(name));
/* 142 */     if (result == null) {
/* 143 */       result = getDescriptor((Name)new NameImpl(getName().getNamespaceURI(), name));
/* 144 */       if (result == null)
/* 145 */         for (PropertyDescriptor pd : this.descriptors) {
/* 146 */           if (pd.getName().getLocalPart().equals(name))
/* 147 */             return pd; 
/*     */         }  
/*     */     } 
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isInline() {
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 167 */     return "LazyComplexType: " + getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AbstractLazyComplexTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */