/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ class FIDSQuery extends Query {
/*  55 */   public static final String[] NO_PROPERTIES = new String[0];
/*     */   
/*     */   public static final String FIDS_HANDLE = "Request Feature IDs";
/*     */   
/*     */   public static final String FIDS_NAME = "Query.FIDS";
/*     */   
/*     */   public String[] getPropertyNames() {
/*  72 */     return NO_PROPERTIES;
/*     */   }
/*     */   
/*     */   public boolean retrieveAllProperties() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public int getMaxFeatures() {
/*  96 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public Integer getStartIndex() {
/* 108 */     return null;
/*     */   }
/*     */   
/*     */   public Filter getFilter() {
/* 121 */     return (Filter)Filter.INCLUDE;
/*     */   }
/*     */   
/*     */   public String getTypeName() {
/* 133 */     return null;
/*     */   }
/*     */   
/*     */   public URI getNamespace() {
/* 145 */     return NO_NAMESPACE;
/*     */   }
/*     */   
/*     */   public String getHandle() {
/* 157 */     return "Request Feature IDs";
/*     */   }
/*     */   
/*     */   public String getVersion() {
/* 169 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 179 */     String[] n = getPropertyNames();
/* 181 */     return ((n == null) ? -1 : ((n.length == 0) ? 0 : (n.length | n[0].hashCode()))) | getMaxFeatures() | ((getFilter() == null) ? 0 : getFilter().hashCode()) | ((getTypeName() == null) ? 0 : getTypeName().hashCode()) | ((getVersion() == null) ? 0 : getVersion().hashCode()) | ((getCoordinateSystem() == null) ? 0 : getCoordinateSystem().hashCode()) | ((getCoordinateSystemReproject() == null) ? 0 : getCoordinateSystemReproject().hashCode());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 203 */     if (obj == null || !(obj instanceof Query))
/* 204 */       return false; 
/* 207 */     if (this == obj)
/* 208 */       return true; 
/* 211 */     Query other = (Query)obj;
/* 213 */     return (Arrays.equals((Object[])getPropertyNames(), (Object[])other.getPropertyNames()) && retrieveAllProperties() == other.retrieveAllProperties() && getMaxFeatures() == other.getMaxFeatures() && ((getFilter() == null) ? (other.getFilter() == null) : getFilter().equals(other.getFilter())) && ((getTypeName() == null) ? (other.getTypeName() == null) : getTypeName().equals(other.getTypeName())) && ((getVersion() == null) ? (other.getVersion() == null) : getVersion().equals(other.getVersion())) && ((getCoordinateSystem() == null) ? (other.getCoordinateSystem() == null) : getCoordinateSystem().equals(other.getCoordinateSystem())) && ((getCoordinateSystemReproject() == null) ? (other.getCoordinateSystemReproject() == null) : getCoordinateSystemReproject().equals(other.getCoordinateSystemReproject())));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 238 */     return "Query.FIDS";
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateSystem() {
/* 250 */     return null;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateSystemReproject() {
/* 262 */     return null;
/*     */   }
/*     */   
/*     */   public SortBy[] getSortBy() {
/* 274 */     return SortBy.UNSORTED;
/*     */   }
/*     */   
/*     */   public Hints getHints() {
/* 285 */     return GeoTools.getDefaultHints();
/*     */   }
/*     */   
/*     */   public void setCoordinateSystem(CoordinateReferenceSystem system) {
/* 297 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setCoordinateSystemReproject(CoordinateReferenceSystem system) {
/* 305 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setFilter(Filter filter) {
/* 313 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setHandle(String handle) {
/* 321 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setHints(Hints hints) {
/* 329 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setMaxFeatures(int maxFeatures) {
/* 337 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setNamespace(URI namespace) {
/* 345 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setPropertyNames(List<String> propNames) {
/* 353 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setPropertyNames(String[] propNames) {
/* 361 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setSortBy(SortBy[] sortBy) {
/* 369 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setStartIndex(Integer startIndex) {
/* 377 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setTypeName(String typeName) {
/* 385 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/* 393 */     new UnsupportedOperationException("Query.FIDS cannot be changed, please just use as a default.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FIDSQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */