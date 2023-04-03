/*     */ package org.geotools.feature.simple;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.type.FeatureTypeImpl;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class SimpleFeatureTypeImpl extends FeatureTypeImpl implements SimpleFeatureType {
/*  48 */   List<AttributeType> types = null;
/*     */   
/*     */   Map<String, Integer> index;
/*     */   
/*     */   public SimpleFeatureTypeImpl(Name name, List<AttributeDescriptor> schema, GeometryDescriptor defaultGeometry, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/*  59 */     super(name, schema, defaultGeometry, isAbstract, restrictions, superType, description);
/*  61 */     this.index = buildIndex(this);
/*     */   }
/*     */   
/*     */   public final List<AttributeDescriptor> getAttributeDescriptors() {
/*  72 */     return (List<AttributeDescriptor>)getDescriptors();
/*     */   }
/*     */   
/*     */   public List<AttributeType> getTypes() {
/*  76 */     if (this.types == null)
/*  77 */       synchronized (this) {
/*  78 */         if (this.types == null) {
/*  79 */           this.types = new ArrayList<AttributeType>();
/*  80 */           for (AttributeDescriptor ad : getAttributeDescriptors())
/*  81 */             this.types.add(ad.getType()); 
/*     */         } 
/*     */       }  
/*  86 */     return this.types;
/*     */   }
/*     */   
/*     */   public AttributeType getType(Name name) {
/*  90 */     AttributeDescriptor attribute = getDescriptor(name);
/*  91 */     if (attribute != null)
/*  92 */       return attribute.getType(); 
/*  94 */     return null;
/*     */   }
/*     */   
/*     */   public AttributeType getType(String name) {
/*  98 */     AttributeDescriptor attribute = getDescriptor(name);
/*  99 */     if (attribute != null)
/* 100 */       return attribute.getType(); 
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public AttributeType getType(int index) {
/* 106 */     return getTypes().get(index);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor(Name name) {
/* 110 */     return (AttributeDescriptor)super.getDescriptor(name);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor(String name) {
/* 114 */     return (AttributeDescriptor)super.getDescriptor(name);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor(int index) {
/* 118 */     return getAttributeDescriptors().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Name name) {
/* 122 */     if (name.getNamespaceURI() == null)
/* 123 */       return indexOf(name.getLocalPart()); 
/* 126 */     int index = 0;
/* 127 */     for (AttributeDescriptor descriptor : getAttributeDescriptors()) {
/* 128 */       if (descriptor.getName().equals(name))
/* 129 */         return index; 
/* 131 */       index++;
/*     */     } 
/* 133 */     return -1;
/*     */   }
/*     */   
/*     */   public int indexOf(String name) {
/* 137 */     Integer idx = this.index.get(name);
/* 138 */     if (idx != null)
/* 139 */       return idx.intValue(); 
/* 141 */     return -1;
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 146 */     return getAttributeDescriptors().size();
/*     */   }
/*     */   
/*     */   public String getTypeName() {
/* 150 */     return getName().getLocalPart();
/*     */   }
/*     */   
/*     */   static Map<String, Integer> buildIndex(SimpleFeatureType featureType) {
/* 160 */     Map<String, Integer> index = new HashMap<String, Integer>();
/* 161 */     int i = 0;
/* 162 */     for (AttributeDescriptor ad : featureType.getAttributeDescriptors())
/* 163 */       index.put(ad.getLocalName(), Integer.valueOf(i++)); 
/* 165 */     if (featureType.getGeometryDescriptor() != null)
/* 166 */       index.put(null, index.get(featureType.getGeometryDescriptor().getLocalName())); 
/* 169 */     return index;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\simple\SimpleFeatureTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */