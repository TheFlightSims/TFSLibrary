/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ class ALLQuery extends Query {
/*     */   public final String[] getPropertyNames() {
/*  48 */     return null;
/*     */   }
/*     */   
/*     */   public final boolean retrieveAllProperties() {
/*  52 */     return true;
/*     */   }
/*     */   
/*     */   public final int getMaxFeatures() {
/*  56 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public Integer getStartIndex() {
/*  60 */     return null;
/*     */   }
/*     */   
/*     */   public final Filter getFilter() {
/*  64 */     return (Filter)Filter.INCLUDE;
/*     */   }
/*     */   
/*     */   public final String getTypeName() {
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public URI getNamespace() {
/*  72 */     return NO_NAMESPACE;
/*     */   }
/*     */   
/*     */   public final String getHandle() {
/*  76 */     return "Request All Features";
/*     */   }
/*     */   
/*     */   public final String getVersion() {
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  89 */     String[] n = getPropertyNames();
/*  91 */     return ((n == null) ? -1 : ((n.length == 0) ? 0 : (n.length | n[0].hashCode()))) | getMaxFeatures() | ((getFilter() == null) ? 0 : getFilter().hashCode()) | ((getTypeName() == null) ? 0 : getTypeName().hashCode()) | ((getVersion() == null) ? 0 : getVersion().hashCode()) | ((getCoordinateSystem() == null) ? 0 : getCoordinateSystem().hashCode()) | ((getCoordinateSystemReproject() == null) ? 0 : getCoordinateSystemReproject().hashCode());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 112 */     if (obj == null || !(obj instanceof Query))
/* 113 */       return false; 
/* 116 */     if (this == obj)
/* 117 */       return true; 
/* 120 */     Query other = (Query)obj;
/* 122 */     return (Arrays.equals((Object[])getPropertyNames(), (Object[])other.getPropertyNames()) && retrieveAllProperties() == other.retrieveAllProperties() && getMaxFeatures() == other.getMaxFeatures() && ((getFilter() == null) ? (other.getFilter() == null) : getFilter().equals(other.getFilter())) && ((getTypeName() == null) ? (other.getTypeName() == null) : getTypeName().equals(other.getTypeName())) && ((getVersion() == null) ? (other.getVersion() == null) : getVersion().equals(other.getVersion())) && ((getCoordinateSystem() == null) ? (other.getCoordinateSystem() == null) : getCoordinateSystem().equals(other.getCoordinateSystem())) && ((getCoordinateSystemReproject() == null) ? (other.getCoordinateSystemReproject() == null) : getCoordinateSystemReproject().equals(other.getCoordinateSystemReproject())));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 141 */     return "Query.ALL";
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateSystem() {
/* 152 */     return null;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCoordinateSystemReproject() {
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public SortBy[] getSortBy() {
/* 170 */     return SortBy.UNSORTED;
/*     */   }
/*     */   
/*     */   public Hints getHints() {
/* 177 */     return new Hints();
/*     */   }
/*     */   
/*     */   public void setCoordinateSystem(CoordinateReferenceSystem system) {
/* 185 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setCoordinateSystemReproject(CoordinateReferenceSystem system) {
/* 189 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setFilter(Filter filter) {
/* 193 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setHandle(String handle) {
/* 197 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setHints(Hints hints) {
/* 201 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setMaxFeatures(int maxFeatures) {
/* 205 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setNamespace(URI namespace) {
/* 209 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setPropertyNames(List<String> propNames) {
/* 213 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setPropertyNames(String[] propNames) {
/* 217 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setSortBy(SortBy[] sortBy) {
/* 221 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setStartIndex(Integer startIndex) {
/* 225 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setTypeName(String typeName) {
/* 229 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */   
/*     */   public void setVersion(String version) {
/* 233 */     new UnsupportedOperationException("Query.ALL cannot be changed, please just use as a default.");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ALLQuery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */