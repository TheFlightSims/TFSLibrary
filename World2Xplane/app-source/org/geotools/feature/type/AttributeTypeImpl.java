/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AttributeTypeImpl extends PropertyTypeImpl implements AttributeType {
/*     */   protected final boolean identified;
/*     */   
/*     */   public AttributeTypeImpl(Name name, Class<?> binding, boolean identified, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/*  45 */     super(name, binding, isAbstract, restrictions, (PropertyType)superType, description);
/*  47 */     this.identified = identified;
/*     */   }
/*     */   
/*     */   public boolean isIdentified() {
/*  51 */     return this.identified;
/*     */   }
/*     */   
/*     */   public Object parse(Object value) throws IllegalArgumentException {
/*  73 */     return value;
/*     */   }
/*     */   
/*     */   public Object createDefaultValue() {
/*  77 */     return null;
/*     */   }
/*     */   
/*     */   public AttributeType getSuper() {
/*  81 */     return (AttributeType)super.getSuper();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  88 */     return super.hashCode() ^ Boolean.valueOf(this.identified).hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 101 */     if (this == other)
/* 102 */       return true; 
/* 104 */     if (!(other instanceof AttributeType))
/* 105 */       return false; 
/* 108 */     if (!super.equals(other))
/* 109 */       return false; 
/* 111 */     AttributeType att = (AttributeType)other;
/* 113 */     if (this.identified != att.isIdentified())
/* 114 */       return false; 
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 121 */     StringBuffer sb = new StringBuffer(Classes.getShortClassName(this));
/* 122 */     sb.append(" ");
/* 123 */     sb.append(getName());
/* 124 */     if (isAbstract())
/* 125 */       sb.append(" abstract"); 
/* 127 */     if (isIdentified())
/* 128 */       sb.append(" identified"); 
/* 130 */     if (this.superType != null) {
/* 131 */       sb.append(" extends ");
/* 132 */       sb.append(this.superType.getName().getLocalPart());
/*     */     } 
/* 134 */     if (this.binding != null) {
/* 135 */       sb.append("<");
/* 136 */       sb.append(Classes.getShortName(this.binding));
/* 137 */       sb.append(">");
/*     */     } 
/* 139 */     if (this.description != null) {
/* 140 */       sb.append("\n\tdescription=");
/* 141 */       sb.append((CharSequence)this.description);
/*     */     } 
/* 143 */     if (this.restrictions != null && !this.restrictions.isEmpty()) {
/* 144 */       sb.append("\nrestrictions=");
/* 145 */       boolean first = true;
/* 146 */       for (Filter filter : this.restrictions) {
/* 147 */         if (first) {
/* 148 */           first = false;
/*     */         } else {
/* 151 */           sb.append(" AND ");
/*     */         } 
/* 153 */         sb.append(filter);
/*     */       } 
/*     */     } 
/* 156 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AttributeTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */