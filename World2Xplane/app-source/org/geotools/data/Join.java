/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ 
/*     */ public class Join {
/*  56 */   static final FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*     */   
/*     */   Type type;
/*     */   
/*     */   String typeName;
/*     */   
/*     */   public enum Type {
/*  62 */     INNER, OUTER;
/*     */   }
/*     */   
/*  76 */   List<PropertyName> properties = Query.ALL_PROPERTIES;
/*     */   
/*     */   Filter join;
/*     */   
/*     */   Filter filter;
/*     */   
/*     */   String alias;
/*     */   
/*     */   public Join(String typeName, Filter join) {
/* 101 */     this.typeName = typeName;
/* 102 */     this.join = join;
/* 103 */     this.type = Type.INNER;
/* 104 */     this.properties = Query.ALL_PROPERTIES;
/* 105 */     this.filter = (Filter)Filter.INCLUDE;
/* 106 */     this.alias = null;
/*     */   }
/*     */   
/*     */   public Join(Join other) {
/* 113 */     this.typeName = other.getTypeName();
/* 114 */     this.join = other.getJoinFilter();
/* 115 */     this.filter = other.getFilter();
/* 116 */     this.type = other.getType();
/* 117 */     this.properties = other.getProperties();
/* 118 */     this.filter = other.getFilter();
/* 119 */     this.alias = other.getAlias();
/*     */   }
/*     */   
/*     */   public String getTypeName() {
/* 130 */     return this.typeName;
/*     */   }
/*     */   
/*     */   public Filter getJoinFilter() {
/* 151 */     return this.join;
/*     */   }
/*     */   
/*     */   public void setType(Type type) {
/* 159 */     this.type = type;
/*     */   }
/*     */   
/*     */   public Type getType() {
/* 170 */     return this.type;
/*     */   }
/*     */   
/*     */   public List<PropertyName> getProperties() {
/* 180 */     if (this.properties == Query.ALL_PROPERTIES)
/* 181 */       return this.properties; 
/* 183 */     return Collections.unmodifiableList(this.properties);
/*     */   }
/*     */   
/*     */   public void setProperties(List<PropertyName> properties) {
/* 193 */     this.properties = properties;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 203 */     if (this.properties == Query.ALL_PROPERTIES)
/* 204 */       return Query.ALL_NAMES; 
/* 207 */     String[] names = new String[this.properties.size()];
/* 208 */     for (int i = 0; i < names.length; i++)
/* 209 */       names[i] = ((PropertyName)this.properties.get(i)).getPropertyName(); 
/* 211 */     return names;
/*     */   }
/*     */   
/*     */   public void setFilter(Filter filter) {
/* 220 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public Filter getFilter() {
/* 231 */     return this.filter;
/*     */   }
/*     */   
/*     */   public void setAlias(String alias) {
/* 239 */     this.alias = alias;
/*     */   }
/*     */   
/*     */   public String getAlias() {
/* 256 */     return this.alias;
/*     */   }
/*     */   
/*     */   public String attributeName() {
/* 271 */     return (getAlias() != null) ? getAlias() : getTypeName();
/*     */   }
/*     */   
/*     */   public Join properties(String... properties) {
/* 278 */     this.properties = new ArrayList<PropertyName>();
/* 279 */     for (String p : properties)
/* 280 */       this.properties.add(ff.property(p)); 
/* 282 */     return this;
/*     */   }
/*     */   
/*     */   public Join filter(Filter filter) {
/* 289 */     setFilter(filter);
/* 290 */     return this;
/*     */   }
/*     */   
/*     */   public Join alias(String alias) {
/* 297 */     setAlias(alias);
/* 298 */     return this;
/*     */   }
/*     */   
/*     */   public Join type(Type type) {
/* 305 */     setType(type);
/* 306 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Join.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */