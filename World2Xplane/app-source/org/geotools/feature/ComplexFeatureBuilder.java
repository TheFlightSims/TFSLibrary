/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ 
/*     */ public class ComplexFeatureBuilder extends FeatureBuilder<FeatureType, Feature> {
/*  45 */   Map<Name, ArrayList<Property>> values = new HashMap<Name, ArrayList<Property>>();
/*     */   
/*  47 */   AttributeDescriptor ad = null;
/*     */   
/*     */   public ComplexFeatureBuilder(FeatureType featureType) {
/*  50 */     this(featureType, CommonFactoryFinder.getFeatureFactory(null));
/*     */   }
/*     */   
/*     */   protected ComplexFeatureBuilder(FeatureType featureType, FeatureFactory factory) {
/*  55 */     super(featureType, factory);
/*     */   }
/*     */   
/*     */   public ComplexFeatureBuilder(AttributeDescriptor ad) {
/*  59 */     this(ad, CommonFactoryFinder.getFeatureFactory(null));
/*     */   }
/*     */   
/*     */   protected ComplexFeatureBuilder(AttributeDescriptor ad, FeatureFactory factory) {
/*  64 */     super((FeatureType)ad.getType(), factory);
/*  65 */     this.ad = ad;
/*     */   }
/*     */   
/*     */   public Feature buildFeature(String id) {
/*  75 */     id = (id == null) ? FeatureBuilder.createDefaultFeatureId() : id;
/*  79 */     label29: for (PropertyDescriptor propertyDescriptor : this.featureType.getDescriptors()) {
/*  81 */       Name name = propertyDescriptor.getName();
/*  85 */       if (!this.values.containsKey(name))
/*  86 */         this.values.put(name, new ArrayList<Property>()); 
/*  90 */       List<Property> list = this.values.get(name);
/*  94 */       int minOccurs = propertyDescriptor.getMinOccurs();
/*  95 */       int numberOfProperties = list.size();
/*  97 */       if (numberOfProperties < minOccurs) {
/*  99 */         if (propertyDescriptor.isNillable() && AttributeDescriptor.class.isAssignableFrom(propertyDescriptor.getClass()))
/*     */           while (true) {
/* 103 */             Property nullProperty = new AttributeImpl(propertyDescriptor.getType().getBinding().cast(null), (AttributeDescriptor)propertyDescriptor, null);
/* 108 */             list.add(nullProperty);
/* 109 */             if (++numberOfProperties >= minOccurs)
/*     */               continue label29; 
/*     */           }  
/* 116 */         throw new IllegalStateException(String.format("Failed to build feature '%s'; its property '%s' requires at least %s occurrence(s) but number of occurrences was %s.", new Object[] { this.featureType.getName(), name, Integer.valueOf(minOccurs), Integer.valueOf(numberOfProperties) }));
/*     */       } 
/*     */     } 
/* 127 */     Collection<Property> properties = new ArrayList<Property>();
/* 128 */     for (Name key : this.values.keySet())
/* 129 */       properties.addAll(this.values.get(key)); 
/* 132 */     this.values.clear();
/* 133 */     if (this.ad != null)
/* 134 */       return this.factory.createFeature(properties, this.ad, id); 
/* 137 */     return this.factory.createFeature(properties, this.featureType, id);
/*     */   }
/*     */   
/*     */   public void append(Name name, Property value) {
/*     */     ArrayList<Property> valueList;
/* 150 */     PropertyDescriptor propertyDescriptor = this.featureType.getDescriptor(name);
/* 153 */     if (propertyDescriptor == null)
/* 154 */       throw new IllegalArgumentException(String.format("The name '%s' is not a valid descriptor name for the type '%s'.", new Object[] { name, this.featureType.getName() })); 
/* 160 */     Class<?> expectedClass = propertyDescriptor.getType().getBinding();
/* 161 */     if (value != null) {
/* 162 */       Class<?> providedClass = value.getType().getBinding();
/* 166 */       if (!providedClass.equals(expectedClass) && !expectedClass.isAssignableFrom(providedClass))
/* 168 */         throw new IllegalArgumentException(String.format("The value provided contains an object of '%s' but the method expects an object of '%s'.", new Object[] { providedClass, expectedClass })); 
/* 174 */     } else if (propertyDescriptor.isNillable()) {
/* 175 */       value = (Property)expectedClass.cast(null);
/*     */     } else {
/* 179 */       value = (Property)expectedClass.cast(null);
/*     */     } 
/* 187 */     if (this.values.containsKey(name)) {
/* 188 */       valueList = this.values.get(name);
/* 191 */       int maxOccurs = propertyDescriptor.getMaxOccurs();
/* 192 */       if (valueList.size() == maxOccurs)
/* 193 */         throw new IndexOutOfBoundsException(String.format("You can't add another object with the name of '%s' because you already have the maximum number (%s) allowed by the property descriptor.", new Object[] { name, Integer.valueOf(maxOccurs) })); 
/*     */     } else {
/* 199 */       valueList = new ArrayList<Property>();
/* 200 */       this.values.put(name, valueList);
/*     */     } 
/* 203 */     valueList.add(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\ComplexFeatureBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */