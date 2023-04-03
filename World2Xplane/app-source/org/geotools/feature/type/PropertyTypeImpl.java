/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class PropertyTypeImpl implements PropertyType {
/*  38 */   private static final List<Filter> NO_RESTRICTIONS = Collections.emptyList();
/*     */   
/*     */   protected final Name name;
/*     */   
/*     */   protected final Class<?> binding;
/*     */   
/*     */   protected final boolean isAbstract;
/*     */   
/*     */   protected final PropertyType superType;
/*     */   
/*     */   protected final List<Filter> restrictions;
/*     */   
/*     */   protected final InternationalString description;
/*     */   
/*     */   protected final Map<Object, Object> userData;
/*     */   
/*     */   public PropertyTypeImpl(Name name, Class<?> binding, boolean isAbstract, List<Filter> restrictions, PropertyType superType, InternationalString description) {
/*  52 */     if (name == null)
/*  53 */       throw new NullPointerException("Name is required for PropertyType"); 
/*  55 */     if (binding == null) {
/*  56 */       if (superType != null && superType.getBinding() != null)
/*  58 */         throw new NullPointerException("Binding to a Java class, did you mean to bind to " + superType.getBinding()); 
/*  60 */       throw new NullPointerException("Binding to a Java class is required");
/*     */     } 
/*  62 */     this.name = name;
/*  63 */     this.binding = binding;
/*  64 */     this.isAbstract = isAbstract;
/*  66 */     if (restrictions == null) {
/*  67 */       this.restrictions = NO_RESTRICTIONS;
/*     */     } else {
/*  69 */       this.restrictions = Collections.unmodifiableList(restrictions);
/*     */     } 
/*  72 */     this.superType = superType;
/*  73 */     this.description = description;
/*  74 */     this.userData = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   public Name getName() {
/*  78 */     return this.name;
/*     */   }
/*     */   
/*     */   public Class<?> getBinding() {
/*  82 */     return this.binding;
/*     */   }
/*     */   
/*     */   public boolean isAbstract() {
/*  86 */     return this.isAbstract;
/*     */   }
/*     */   
/*     */   public List<Filter> getRestrictions() {
/*  90 */     return this.restrictions;
/*     */   }
/*     */   
/*     */   public PropertyType getSuper() {
/*  94 */     return this.superType;
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/*  98 */     return this.description;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 102 */     return getName().hashCode() ^ getBinding().hashCode() ^ ((getDescription() != null) ? getDescription().hashCode() : 17);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 108 */     if (this == other)
/* 109 */       return true; 
/* 111 */     if (!(other instanceof PropertyType))
/* 112 */       return false; 
/* 115 */     PropertyType prop = (PropertyType)other;
/* 117 */     if (!Utilities.equals(this.name, prop.getName()))
/* 118 */       return false; 
/* 121 */     if (!Utilities.equals(this.binding, prop.getBinding()))
/* 122 */       return false; 
/* 125 */     if (this.isAbstract != prop.isAbstract())
/* 126 */       return false; 
/* 129 */     if (!equals(getRestrictions(), prop.getRestrictions()))
/* 130 */       return false; 
/* 133 */     if (!Utilities.equals(this.superType, prop.getSuper()))
/* 134 */       return false; 
/* 137 */     if (!Utilities.equals(this.description, prop.getDescription()))
/* 138 */       return false; 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   private boolean equals(List object1, List object2) {
/* 149 */     if (object1 == object2 || (object1 != null && object1.equals(object2)))
/* 150 */       return true; 
/* 151 */     if (object1 == null && object2.size() == 0)
/* 152 */       return true; 
/* 153 */     if (object2 == null && object1.size() == 0)
/* 154 */       return true; 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/* 159 */     return this.userData;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 163 */     StringBuffer sb = new StringBuffer(Classes.getShortClassName(this));
/* 164 */     sb.append(" ");
/* 165 */     sb.append(getName());
/* 166 */     if (isAbstract())
/* 167 */       sb.append(" abstract"); 
/* 169 */     if (this.superType != null) {
/* 170 */       sb.append(" extends ");
/* 171 */       sb.append(this.superType.getName().getLocalPart());
/*     */     } 
/* 173 */     if (this.binding != null) {
/* 174 */       sb.append("<");
/* 175 */       sb.append(Classes.getShortName(this.binding));
/* 176 */       sb.append(">");
/*     */     } 
/* 178 */     if (this.description != null) {
/* 179 */       sb.append("\n\tdescription=");
/* 180 */       sb.append((CharSequence)this.description);
/*     */     } 
/* 182 */     if (this.restrictions != null && !this.restrictions.isEmpty()) {
/* 183 */       sb.append("\nrestrictions=");
/* 184 */       boolean first = true;
/* 185 */       for (Filter filter : this.restrictions) {
/* 186 */         if (first) {
/* 187 */           first = false;
/*     */         } else {
/* 190 */           sb.append(" AND ");
/*     */         } 
/* 192 */         sb.append(filter);
/*     */       } 
/*     */     } 
/* 195 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\PropertyTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */