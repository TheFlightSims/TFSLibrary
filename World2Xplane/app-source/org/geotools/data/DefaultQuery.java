/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.expression.PropertyName;
/*     */ 
/*     */ public class DefaultQuery extends Query {
/*     */   public DefaultQuery() {}
/*     */   
/*     */   public DefaultQuery(String typeName) {
/*  52 */     this(typeName, (Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public DefaultQuery(String typeName, Filter filter) {
/*  64 */     this(typeName, filter, Query.ALL_NAMES);
/*     */   }
/*     */   
/*     */   public DefaultQuery(String typeName, Filter filter, String[] properties) {
/*  75 */     this(typeName, (URI)null, filter, 2147483647, properties, (String)null);
/*     */   }
/*     */   
/*     */   public DefaultQuery(String typeName, Filter filter, int maxFeatures, String[] propNames, String handle) {
/*  88 */     this(typeName, (URI)null, filter, maxFeatures, propNames, handle);
/*     */   }
/*     */   
/*     */   public DefaultQuery(String typeName, URI namespace, Filter filter, int maxFeatures, String[] propNames, String handle) {
/* 103 */     this.typeName = typeName;
/* 104 */     this.filter = filter;
/* 105 */     this.namespace = namespace;
/* 106 */     this.maxFeatures = maxFeatures;
/* 107 */     this.handle = handle;
/* 108 */     setPropertyNames(propNames);
/*     */   }
/*     */   
/*     */   public DefaultQuery(String typeName, URI namespace, Filter filter, int maxFeatures, List<PropertyName> propNames, String handle) {
/* 123 */     this.typeName = typeName;
/* 124 */     this.filter = filter;
/* 125 */     this.namespace = namespace;
/* 126 */     this.properties = (propNames == null) ? null : new ArrayList<PropertyName>(propNames);
/* 127 */     this.maxFeatures = maxFeatures;
/* 128 */     this.handle = handle;
/*     */   }
/*     */   
/*     */   public DefaultQuery(Query query) {
/* 136 */     this(query.getTypeName(), query.getNamespace(), query.getFilter(), query.getMaxFeatures(), query.getProperties(), query.getHandle());
/* 138 */     this.sortBy = query.getSortBy();
/* 139 */     this.coordinateSystem = query.getCoordinateSystem();
/* 140 */     this.coordinateSystemReproject = query.getCoordinateSystemReproject();
/* 141 */     this.version = query.getVersion();
/* 142 */     this.hints = query.getHints();
/* 143 */     this.startIndex = query.getStartIndex();
/* 144 */     this.alias = query.getAlias();
/* 145 */     this.joins = query.getJoins();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */