/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*     */ import org.geotools.feature.type.AttributeTypeImpl;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ 
/*     */ public class Schema {
/*  58 */   private static Schema DEFAULT = new Schema();
/*     */   
/*     */   private FilterFactory ff;
/*     */   
/*     */   public Schema() {
/*  62 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public Schema(Hints hints) {
/*  65 */     this(CommonFactoryFinder.getFilterFactory(hints));
/*     */   }
/*     */   
/*     */   public Schema(FilterFactory filterFactory) {
/*  68 */     this.ff = filterFactory;
/*     */   }
/*     */   
/*     */   public int getAttributeCount(SimpleFeatureType featureType) {
/*  80 */     return getNames(featureType).size();
/*     */   }
/*     */   
/*     */   public List getNames(SimpleFeatureType featureType) {
/*  95 */     return getNames(featureType, new ArrayList());
/*     */   }
/*     */   
/*     */   public List getNames(SimpleFeatureType featureType, List<String> names) {
/* 107 */     if (featureType == null || featureType.getAttributeDescriptors() == null)
/* 108 */       return names; 
/* 110 */     List<FeatureType> ancestors = FeatureTypes.getAncestors((FeatureType)featureType);
/* 111 */     if (ancestors != null && !ancestors.isEmpty())
/* 112 */       for (int i = 0, length = ancestors.size(); i < length; i++) {
/* 113 */         SimpleFeatureType superType = (SimpleFeatureType)ancestors.get(i);
/* 114 */         getNames(superType, names);
/*     */       }  
/* 117 */     List<AttributeDescriptor> attributes = featureType.getAttributeDescriptors();
/* 118 */     if (attributes != null && !attributes.isEmpty())
/* 119 */       for (int i = 0, length = attributes.size(); i < length; i++) {
/* 120 */         AttributeDescriptor type = attributes.get(i);
/* 121 */         String name = type.getLocalName();
/* 122 */         if (!names.contains(name))
/* 123 */           names.add(name); 
/*     */       }  
/* 127 */     return names;
/*     */   }
/*     */   
/*     */   public List getAttributes(SimpleFeatureType featureType) {
/* 131 */     return getAttributes(featureType, new ArrayList());
/*     */   }
/*     */   
/*     */   public List getAttributes(SimpleFeatureType featureType, List<AttributeDescriptor> list) {
/* 143 */     if (featureType == null || featureType.getAttributeDescriptors() == null)
/* 144 */       return list; 
/* 147 */     List<FeatureType> ancestors = FeatureTypes.getAncestors((FeatureType)featureType);
/* 148 */     if (ancestors != null && !ancestors.isEmpty())
/* 149 */       for (int i = 0, length = ancestors.size(); i < length; i++)
/* 151 */         getAttributes((SimpleFeatureType)ancestors.get(i), list);  
/* 154 */     List<AttributeDescriptor> attributes = featureType.getAttributeDescriptors();
/* 155 */     if (attributes != null && !attributes.isEmpty())
/* 156 */       for (int i = 0, length = attributes.size(); i < length; i++) {
/* 157 */         AttributeDescriptor type = attributes.get(i);
/* 158 */         String name = type.getLocalName();
/* 159 */         int index = getIndexOf(list, name);
/* 160 */         if (index != -1) {
/* 161 */           AttributeDescriptor origional = list.get(index);
/* 162 */           list.remove(index);
/* 163 */           list.add(index, override(origional, type));
/*     */         } else {
/* 166 */           list.add(type);
/*     */         } 
/*     */       }  
/* 170 */     return list;
/*     */   }
/*     */   
/*     */   public Filter getRestrictions(SimpleFeatureType featureType, String name) {
/* 179 */     if (featureType == null || featureType.getAttributeDescriptors() == null)
/* 179 */       return (Filter)Filter.EXCLUDE; 
/* 181 */     List<Filter> restrictions = restriction(featureType, name, Collections.singletonList(Filter.INCLUDE));
/* 182 */     return restrictions.get(0);
/*     */   }
/*     */   
/*     */   public int getIndexOf(SimpleFeatureType type, String name) {
/* 191 */     List names = getNames(type);
/* 192 */     return names.indexOf(name);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getAttribute(SimpleFeatureType type, int index) {
/* 203 */     String name = getNames(type).get(index);
/* 204 */     return getXPath(type, name);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getAttribute(SimpleFeatureType type, String name) {
/* 208 */     List<AttributeDescriptor> list = getAttributes(type);
/* 209 */     int index = getIndexOf(list, name);
/* 210 */     if (index == -1)
/* 210 */       return null; 
/* 211 */     return list.get(index);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getXPath(SimpleFeatureType type, String xpath) {
/* 224 */     return getAttribute(type, xpath);
/*     */   }
/*     */   
/*     */   private int getIndexOf(List attributes, String name) {
/* 230 */     int index = 0;
/* 231 */     for (Iterator<AttributeDescriptor> i = attributes.iterator(); i.hasNext(); index++) {
/* 232 */       AttributeDescriptor type = i.next();
/* 233 */       if (name.equals(type.getLocalName()))
/* 233 */         return index; 
/*     */     } 
/* 235 */     return -1;
/*     */   }
/*     */   
/*     */   private AttributeDescriptor override(AttributeDescriptor type, AttributeDescriptor override) {
/* 239 */     int max = override.getMaxOccurs();
/* 240 */     if (max < 0)
/* 240 */       max = type.getMinOccurs(); 
/* 242 */     int min = override.getMinOccurs();
/* 243 */     if (min < 0)
/* 243 */       min = type.getMinOccurs(); 
/* 245 */     String name = override.getLocalName();
/* 246 */     if (name == null)
/* 246 */       name = type.getLocalName(); 
/* 248 */     List restrictions = override(type.getType().getRestrictions(), override.getType().getRestrictions());
/* 250 */     Class javaType = override.getType().getBinding();
/* 251 */     if (javaType == null)
/* 251 */       javaType = type.getType().getBinding(); 
/* 253 */     boolean isNilable = override.isNillable();
/* 255 */     Object defaultValue = override.getDefaultValue();
/* 256 */     if (defaultValue == null)
/* 256 */       defaultValue = type.getDefaultValue(); 
/* 259 */     return (AttributeDescriptor)new AttributeDescriptorImpl((AttributeType)new AttributeTypeImpl(new NameImpl(name), javaType, false, false, restrictions, null, null), new NameImpl(name), min, max, isNilable, defaultValue);
/*     */   }
/*     */   
/*     */   private List restriction(SimpleFeatureType featureType, String name, List filters) {
/* 266 */     List<FeatureType> ancestors = FeatureTypes.getAncestors((FeatureType)featureType);
/* 267 */     if (ancestors != null && !ancestors.isEmpty())
/* 268 */       for (int i = 0, length = ancestors.size(); i < length; i++) {
/* 269 */         SimpleFeatureType superType = (SimpleFeatureType)ancestors.get(i);
/* 270 */         filters = restriction(superType, name, filters);
/*     */       }  
/* 273 */     List<AttributeDescriptor> attributes = featureType.getAttributeDescriptors();
/* 274 */     if (attributes != null && !attributes.isEmpty())
/* 275 */       for (int i = 0, length = attributes.size(); i < length; i++) {
/* 276 */         AttributeDescriptor type = attributes.get(i);
/* 277 */         if (name.equals(type.getLocalName()))
/* 278 */           filters = override(filters, type.getType().getRestrictions()); 
/*     */       }  
/* 282 */     return filters;
/*     */   }
/*     */   
/*     */   private List override(List<Filter> filters, List<Filter> overrides) {
/* 286 */     if (filters.size() != overrides.size())
/* 287 */       throw new IllegalArgumentException("filters not same size"); 
/* 290 */     List result = new ArrayList();
/* 291 */     for (int i = 0; i < filters.size(); i++)
/* 292 */       Filter f = override(filters.get(i), overrides.get(i)); 
/* 295 */     return result;
/*     */   }
/*     */   
/*     */   private Filter override(Filter filter, Filter override) {
/* 299 */     if (isNOP(override))
/* 301 */       return filter; 
/* 303 */     if (isNOP(filter))
/* 304 */       return override; 
/* 307 */     return (Filter)this.ff.and(filter, override);
/*     */   }
/*     */   
/*     */   private boolean isNOP(Filter filter) {
/* 312 */     return (filter == null || filter instanceof org.opengis.filter.PropertyIsNull || filter == Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public static int attributeCount(SimpleFeatureType featureType) {
/* 328 */     return DEFAULT.getAttributeCount(featureType);
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor attribute(SimpleFeatureType type, int index) {
/* 335 */     return DEFAULT.getAttribute(type, index);
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor attribute(SimpleFeatureType type, String name) {
/* 339 */     return DEFAULT.getAttribute(type, name);
/*     */   }
/*     */   
/*     */   public static List attributes(SimpleFeatureType featureType) {
/* 343 */     return DEFAULT.getAttributes(featureType);
/*     */   }
/*     */   
/*     */   public static List attributes(SimpleFeatureType featureType, List list) {
/* 347 */     return DEFAULT.getAttributes(featureType, list);
/*     */   }
/*     */   
/*     */   public static int find(SimpleFeatureType type, String name) {
/* 354 */     return DEFAULT.getIndexOf(type, name);
/*     */   }
/*     */   
/*     */   public static List names(SimpleFeatureType featureType) {
/* 362 */     return DEFAULT.getNames(featureType);
/*     */   }
/*     */   
/*     */   public static List names(SimpleFeatureType featureType, List names) {
/* 369 */     return DEFAULT.getNames(featureType, names);
/*     */   }
/*     */   
/*     */   public static Filter restriction(SimpleFeatureType featureType, String name) {
/* 376 */     return DEFAULT.getRestrictions(featureType, name);
/*     */   }
/*     */   
/*     */   public static AttributeDescriptor xpath(SimpleFeatureType type, String xpath) {
/* 383 */     return DEFAULT.getAttribute(type, xpath);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */