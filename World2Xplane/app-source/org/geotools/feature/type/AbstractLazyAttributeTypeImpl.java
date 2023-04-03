/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractLazyAttributeTypeImpl implements AttributeType {
/*     */   private final Name name;
/*     */   
/*     */   private final Class<?> binding;
/*     */   
/*     */   private final boolean identified;
/*     */   
/*     */   private final boolean isAbstract;
/*     */   
/*     */   private final List<Filter> restrictions;
/*     */   
/*     */   private final InternationalString description;
/*     */   
/*     */   private final Map<Object, Object> userData;
/*     */   
/*     */   private AttributeType superType;
/*     */   
/*     */   public AbstractLazyAttributeTypeImpl(Name name, Class<?> binding, boolean identified, boolean isAbstract, List<Filter> restrictions, InternationalString description) {
/*  76 */     if (name == null)
/*  77 */       throw new NullPointerException("Type has no name"); 
/*  79 */     if (binding == null)
/*  80 */       throw new NullPointerException("Type has no binding"); 
/*  82 */     this.name = name;
/*  83 */     this.binding = binding;
/*  84 */     this.identified = identified;
/*  85 */     this.isAbstract = isAbstract;
/*  86 */     if (restrictions == null) {
/*  87 */       this.restrictions = Collections.emptyList();
/*     */     } else {
/*  89 */       this.restrictions = Collections.unmodifiableList(new ArrayList<Filter>(restrictions));
/*     */     } 
/*  91 */     this.description = description;
/*  92 */     this.userData = new HashMap<Object, Object>();
/*     */   }
/*     */   
/*     */   public boolean isIdentified() {
/* 107 */     return this.identified;
/*     */   }
/*     */   
/*     */   public AttributeType getSuper() {
/* 114 */     if (this.superType == null)
/* 115 */       this.superType = buildSuper(); 
/* 117 */     return this.superType;
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 124 */     return this.name;
/*     */   }
/*     */   
/*     */   public Class<?> getBinding() {
/* 131 */     return this.binding;
/*     */   }
/*     */   
/*     */   public boolean isAbstract() {
/* 138 */     return this.isAbstract;
/*     */   }
/*     */   
/*     */   public List<Filter> getRestrictions() {
/* 145 */     return this.restrictions;
/*     */   }
/*     */   
/*     */   public InternationalString getDescription() {
/* 152 */     return this.description;
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/* 159 */     return this.userData;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 170 */     if (!(other instanceof AttributeType))
/* 171 */       return false; 
/* 173 */     return this.name.equals(((AttributeType)other).getName());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 181 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 189 */     return "LazyAttributeType: " + getName();
/*     */   }
/*     */   
/*     */   public abstract AttributeType buildSuper();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\AbstractLazyAttributeTypeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */