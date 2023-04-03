/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class ComplexTypeImpl extends AttributeTypeImpl implements ComplexType {
/*     */   private final Collection<PropertyDescriptor> properties;
/*     */   
/*     */   private final Map<Name, PropertyDescriptor> propertyMap;
/*     */   
/*     */   public ComplexTypeImpl(Name name, Collection<PropertyDescriptor> properties, boolean identified, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/*  63 */     super(name, Collection.class, identified, isAbstract, restrictions, superType, description);
/*     */     List<PropertyDescriptor> localProperties;
/*     */     Map<Name, PropertyDescriptor> localPropertyMap;
/*  66 */     if (properties == null) {
/*  67 */       localProperties = Collections.emptyList();
/*  68 */       localPropertyMap = Collections.emptyMap();
/*     */     } else {
/*  70 */       localProperties = new ArrayList<PropertyDescriptor>(properties);
/*  71 */       localPropertyMap = new HashMap<Name, PropertyDescriptor>();
/*  72 */       for (PropertyDescriptor pd : properties) {
/*  73 */         if (pd == null)
/*  75 */           throw new NullPointerException("PropertyDescriptor is null - did you request a property that does not exist?"); 
/*  77 */         localPropertyMap.put(pd.getName(), pd);
/*     */       } 
/*     */     } 
/*  81 */     this.properties = Collections.unmodifiableList(localProperties);
/*  82 */     this.propertyMap = Collections.unmodifiableMap(localPropertyMap);
/*     */   }
/*     */   
/*     */   public Class<Collection<Property>> getBinding() {
/*  86 */     return (Class)super.getBinding();
/*     */   }
/*     */   
/*     */   public Collection<PropertyDescriptor> getDescriptors() {
/*  90 */     return this.properties;
/*     */   }
/*     */   
/*     */   public PropertyDescriptor getDescriptor(Name name) {
/*  94 */     return this.propertyMap.get(name);
/*     */   }
/*     */   
/*     */   public PropertyDescriptor getDescriptor(String name) {
/*  98 */     PropertyDescriptor result = getDescriptor((Name)new NameImpl(name));
/*  99 */     if (result == null) {
/* 101 */       result = getDescriptor((Name)new NameImpl(getName().getNamespaceURI(), name));
/* 102 */       if (result == null)
/* 104 */         for (PropertyDescriptor pd : this.properties) {
/* 105 */           if (pd.getName().getLocalPart().equals(name))
/* 106 */             return pd; 
/*     */         }  
/*     */     } 
/* 111 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isInline() {
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 121 */     if (this == o)
/* 122 */       return true; 
/* 124 */     if (!super.equals(o))
/* 125 */       return false; 
/* 127 */     if (getClass() != o.getClass())
/* 128 */       return false; 
/* 130 */     ComplexTypeImpl other = (ComplexTypeImpl)o;
/* 131 */     if (!this.properties.equals(other.properties))
/* 132 */       return false; 
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 138 */     StringBuffer sb = new StringBuffer(Classes.getShortClassName(this));
/* 139 */     sb.append(" ");
/* 140 */     sb.append(getName());
/* 141 */     if (isAbstract())
/* 142 */       sb.append(" abstract"); 
/* 144 */     if (isIdentified())
/* 145 */       sb.append(" identified"); 
/* 147 */     if (this.superType != null) {
/* 148 */       sb.append(" extends ");
/* 149 */       sb.append(this.superType.getName().getLocalPart());
/*     */     } 
/* 151 */     if (List.class.isAssignableFrom(this.binding)) {
/* 152 */       sb.append("[");
/*     */     } else {
/* 155 */       sb.append("(");
/*     */     } 
/* 157 */     boolean first = true;
/* 158 */     for (PropertyDescriptor property : getDescriptors()) {
/* 159 */       if (first) {
/* 160 */         first = false;
/*     */       } else {
/* 163 */         sb.append(",");
/*     */       } 
/* 165 */       sb.append(property.getName().getLocalPart());
/* 166 */       sb.append(":");
/* 167 */       sb.append(property.getType().getName().getLocalPart());
/*     */     } 
/* 169 */     if (List.class.isAssignableFrom(this.binding)) {
/* 170 */       sb.append("]");
/*     */     } else {
/* 173 */       sb.append(")");
/*     */     } 
/* 175 */     if (this.description != null) {
/* 176 */       sb.append("\n\tdescription=");
/* 177 */       sb.append((CharSequence)this.description);
/*     */     } 
/* 179 */     if (this.restrictions != null && !this.restrictions.isEmpty()) {
/* 180 */       sb.append("\nrestrictions=");
/* 181 */       first = true;
/* 182 */       for (Filter filter : this.restrictions) {
/* 183 */         if (first) {
/* 184 */           first = false;
/*     */         } else {
/* 187 */           sb.append(" AND ");
/*     */         } 
/* 189 */         sb.append(filter);
/*     */       } 
/*     */     } 
/* 192 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\ComplexTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */