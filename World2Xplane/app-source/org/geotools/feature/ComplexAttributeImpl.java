/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*     */ import org.opengis.feature.ComplexAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ 
/*     */ public class ComplexAttributeImpl extends AttributeImpl implements ComplexAttribute {
/*     */   public ComplexAttributeImpl(Collection<Property> properties, AttributeDescriptor descriptor, Identifier id) {
/*  40 */     super(cloneProperties(properties), descriptor, id);
/*     */   }
/*     */   
/*     */   public ComplexAttributeImpl(Collection<Property> properties, ComplexType type, Identifier id) {
/*  44 */     this(properties, (AttributeDescriptor)new AttributeDescriptorImpl((AttributeType)type, type.getName(), 1, 1, true, null), id);
/*     */   }
/*     */   
/*     */   public ComplexType getType() {
/*  48 */     return (ComplexType)super.getType();
/*     */   }
/*     */   
/*     */   public Collection<? extends Property> getValue() {
/*  52 */     return FeatureImplUtils.unmodifiable((Collection)super.getValue());
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties() {
/*  56 */     return FeatureImplUtils.unmodifiable((Collection)super.getValue());
/*     */   }
/*     */   
/*     */   protected Collection properties() {
/*  64 */     return (Collection)super.getValue();
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(Name name) {
/*  68 */     List<Property> matches = new ArrayList<Property>();
/*  69 */     for (Iterator<? extends Property> p = getValue().iterator(); p.hasNext(); ) {
/*  70 */       Property property = p.next();
/*  71 */       if (property.getName().equals(name))
/*  72 */         matches.add(property); 
/*     */     } 
/*  76 */     return matches;
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(String name) {
/*  80 */     List<Property> matches = new ArrayList<Property>();
/*  81 */     for (Iterator<Property> p = properties().iterator(); p.hasNext(); ) {
/*  82 */       Property property = p.next();
/*  83 */       if (property.getName().getLocalPart().equals(name))
/*  84 */         matches.add(property); 
/*     */     } 
/*  88 */     return matches;
/*     */   }
/*     */   
/*     */   public Property getProperty(Name name) {
/*  92 */     for (Iterator<Property> p = properties().iterator(); p.hasNext(); ) {
/*  93 */       Property property = p.next();
/*  94 */       if (property.getName().equals(name))
/*  95 */         return property; 
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public Property getProperty(String name) {
/* 103 */     for (Iterator<? extends Property> p = getValue().iterator(); p.hasNext(); ) {
/* 104 */       Property property = p.next();
/* 105 */       if (property.getName().getLocalPart().equals(name))
/* 106 */         return property; 
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public void setValue(Object newValue) throws IllegalArgumentException, IllegalStateException {
/* 115 */     setValue((Collection<Property>)newValue);
/*     */   }
/*     */   
/*     */   public void setValue(Collection<Property> newValue) {
/* 119 */     super.setValue(cloneProperties(newValue));
/*     */   }
/*     */   
/*     */   private static Collection cloneProperties(Collection original) {
/* 126 */     if (original == null)
/* 127 */       return null; 
/* 130 */     Collection clone = null;
/*     */     try {
/* 132 */       clone = (Collection)original.getClass().newInstance();
/* 134 */     } catch (Exception e) {
/* 135 */       clone = new ArrayList();
/*     */     } 
/* 138 */     clone.addAll(original);
/* 139 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\ComplexAttributeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */