/*      */ package org.geotools.data;
/*      */ 
/*      */ import java.net.URI;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.filter.identity.ResourceId;
/*      */ import org.opengis.filter.identity.Version;
/*      */ import org.opengis.filter.sort.SortBy;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ 
/*      */ public class Query {
/*  122 */   public static Hints.Key INCLUDE_MANDATORY_PROPS = new Hints.Key(Boolean.class);
/*      */   
/*  127 */   public static final URI NO_NAMESPACE = null;
/*      */   
/*      */   public static final int DEFAULT_MAX = 2147483647;
/*      */   
/*  137 */   public static final Query ALL = new ALLQuery();
/*      */   
/*  144 */   public static final Query FIDS = new FIDSQuery();
/*      */   
/*  154 */   public static final String[] NO_NAMES = new String[0];
/*      */   
/*  161 */   public static final String[] ALL_NAMES = null;
/*      */   
/*  171 */   public static final List<PropertyName> NO_PROPERTIES = Collections.emptyList();
/*      */   
/*  178 */   public static final List<PropertyName> ALL_PROPERTIES = null;
/*      */   
/*      */   protected List<PropertyName> properties;
/*      */   
/*  184 */   protected int maxFeatures = Integer.MAX_VALUE;
/*      */   
/*  187 */   protected Integer startIndex = null;
/*      */   
/*  190 */   protected Filter filter = (Filter)Filter.INCLUDE;
/*      */   
/*      */   protected String typeName;
/*      */   
/*      */   protected String alias;
/*      */   
/*  199 */   protected URI namespace = NO_NAMESPACE;
/*      */   
/*      */   protected String handle;
/*      */   
/*      */   protected CoordinateReferenceSystem coordinateSystem;
/*      */   
/*      */   protected CoordinateReferenceSystem coordinateSystemReproject;
/*      */   
/*      */   protected SortBy[] sortBy;
/*      */   
/*      */   protected String version;
/*      */   
/*      */   protected Hints hints;
/*      */   
/*  220 */   protected List<Join> joins = new ArrayList<Join>();
/*      */   
/*      */   public Query() {}
/*      */   
/*      */   public Query(String typeName) {
/*  236 */     this(typeName, (Filter)Filter.INCLUDE);
/*      */   }
/*      */   
/*      */   public Query(String typeName, Filter filter) {
/*  246 */     this(typeName, filter, ALL_NAMES);
/*      */   }
/*      */   
/*      */   public Query(String typeName, Filter filter, String[] properties) {
/*  257 */     this(typeName, (URI)null, filter, 2147483647, properties, (String)null);
/*      */   }
/*      */   
/*      */   public Query(String typeName, Filter filter, List<PropertyName> properties) {
/*  268 */     this(typeName, (URI)null, filter, 2147483647, properties, (String)null);
/*      */   }
/*      */   
/*      */   public Query(String typeName, Filter filter, int maxFeatures, String[] propNames, String handle) {
/*  282 */     this(typeName, (URI)null, filter, maxFeatures, propNames, handle);
/*      */   }
/*      */   
/*      */   public Query(String typeName, Filter filter, int maxFeatures, List<PropertyName> properties, String handle) {
/*  296 */     this(typeName, (URI)null, filter, maxFeatures, properties, handle);
/*      */   }
/*      */   
/*      */   public Query(String typeName, URI namespace, Filter filter, int maxFeatures, String[] propNames, String handle) {
/*  311 */     this.typeName = typeName;
/*  312 */     this.filter = filter;
/*  313 */     this.namespace = namespace;
/*  314 */     this.maxFeatures = maxFeatures;
/*  315 */     this.handle = handle;
/*  316 */     setPropertyNames(propNames);
/*      */   }
/*      */   
/*      */   public Query(String typeName, URI namespace, Filter filter, int maxFeatures, List<PropertyName> propNames, String handle) {
/*  331 */     this.typeName = typeName;
/*  332 */     this.filter = filter;
/*  333 */     this.namespace = namespace;
/*  334 */     this.maxFeatures = maxFeatures;
/*  335 */     this.handle = handle;
/*  336 */     this.properties = (propNames == null) ? null : new ArrayList<PropertyName>(propNames);
/*      */   }
/*      */   
/*      */   public Query(Query query) {
/*  345 */     this(query.getTypeName(), query.getNamespace(), query.getFilter(), query.getMaxFeatures(), query.getProperties(), query.getHandle());
/*  347 */     this.sortBy = query.getSortBy();
/*  348 */     this.coordinateSystem = query.getCoordinateSystem();
/*  349 */     this.coordinateSystemReproject = query.getCoordinateSystemReproject();
/*  350 */     this.version = query.getVersion();
/*  351 */     this.hints = query.getHints();
/*  352 */     this.startIndex = query.getStartIndex();
/*  353 */     this.alias = query.getAlias();
/*  354 */     this.joins = new ArrayList<Join>();
/*  355 */     for (Join j : query.getJoins())
/*  356 */       this.joins.add(new Join(j)); 
/*      */   }
/*      */   
/*      */   public String[] getPropertyNames() {
/*  373 */     if (this.properties == null)
/*  374 */       return null; 
/*  377 */     String[] propertyNames = new String[this.properties.size()];
/*  378 */     for (int i = 0; i < this.properties.size(); i++) {
/*  379 */       PropertyName propertyName = this.properties.get(i);
/*  380 */       if (propertyName != null) {
/*  381 */         String xpath = propertyName.getPropertyName();
/*  382 */         propertyNames[i] = xpath;
/*      */       } 
/*      */     } 
/*  385 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public void setPropertyNames(String[] propNames) {
/*  408 */     if (propNames == null) {
/*  409 */       this.properties = ALL_PROPERTIES;
/*      */       return;
/*      */     } 
/*  413 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*  414 */     this.properties = new ArrayList<PropertyName>(propNames.length);
/*  415 */     for (int i = 0; i < propNames.length; i++) {
/*  416 */       String xpath = propNames[i];
/*  417 */       if (xpath != null)
/*  418 */         this.properties.add(ff.property(xpath)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public List<PropertyName> getProperties() {
/*  432 */     if (this.properties == ALL_PROPERTIES)
/*  433 */       return ALL_PROPERTIES; 
/*  435 */     return Collections.unmodifiableList(this.properties);
/*      */   }
/*      */   
/*      */   public void setProperties(List<PropertyName> propNames) {
/*  458 */     this.properties = (propNames == ALL_PROPERTIES) ? ALL_PROPERTIES : new ArrayList<PropertyName>(propNames);
/*      */   }
/*      */   
/*      */   public void setPropertyNames(List<String> propNames) {
/*  478 */     if (propNames == null) {
/*  479 */       this.properties = ALL_PROPERTIES;
/*      */       return;
/*      */     } 
/*  483 */     FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*  485 */     this.properties = new ArrayList<PropertyName>(propNames.size());
/*  486 */     for (int i = 0; i < propNames.size(); i++) {
/*  487 */       String xpath = propNames.get(i);
/*  488 */       if (xpath != null)
/*  489 */         this.properties.add(ff.property(xpath)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean retrieveAllProperties() {
/*  504 */     return (this.properties == null);
/*      */   }
/*      */   
/*      */   public int getMaxFeatures() {
/*  524 */     return this.maxFeatures;
/*      */   }
/*      */   
/*      */   public boolean isMaxFeaturesUnlimited() {
/*  533 */     return (this.maxFeatures < 0 || this.maxFeatures == Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */   public void setMaxFeatures(int maxFeatures) {
/*  543 */     this.maxFeatures = maxFeatures;
/*      */   }
/*      */   
/*      */   public Integer getStartIndex() {
/*  553 */     return this.startIndex;
/*      */   }
/*      */   
/*      */   public void setStartIndex(Integer startIndex) {
/*  567 */     if (startIndex != null && startIndex.intValue() < 0)
/*  568 */       throw new IllegalArgumentException("startIndex shall be a positive integer: " + startIndex); 
/*  570 */     this.startIndex = startIndex;
/*      */   }
/*      */   
/*      */   public Filter getFilter() {
/*  580 */     return this.filter;
/*      */   }
/*      */   
/*      */   public void setFilter(Filter filter) {
/*  595 */     this.filter = filter;
/*      */   }
/*      */   
/*      */   public String getTypeName() {
/*  604 */     return this.typeName;
/*      */   }
/*      */   
/*      */   public void setTypeName(String typeName) {
/*  615 */     this.typeName = typeName;
/*      */   }
/*      */   
/*      */   public String getAlias() {
/*  627 */     return this.alias;
/*      */   }
/*      */   
/*      */   public void setAlias(String alias) {
/*  637 */     this.alias = alias;
/*      */   }
/*      */   
/*      */   public URI getNamespace() {
/*  647 */     return this.namespace;
/*      */   }
/*      */   
/*      */   public void setNamespace(URI namespace) {
/*  657 */     this.namespace = namespace;
/*      */   }
/*      */   
/*      */   public String getHandle() {
/*  667 */     return this.handle;
/*      */   }
/*      */   
/*      */   public void setHandle(String handle) {
/*  677 */     this.handle = handle;
/*      */   }
/*      */   
/*      */   public String getVersion() {
/*  704 */     return this.version;
/*      */   }
/*      */   
/*      */   public void setVersion(int index) {
/*  707 */     this.version = String.valueOf(index);
/*      */   }
/*      */   
/*      */   public void setVersion(Date date) {
/*  710 */     this.version = (date == null) ? null : ("date:" + date);
/*      */   }
/*      */   
/*      */   public void setVersion(Version.Action action) {
/*  713 */     this.version = (action == null) ? null : action.name();
/*      */   }
/*      */   
/*      */   public void setVersion(Date startTime, Date endTime) {
/*  716 */     if (startTime == null || endTime == null) {
/*  717 */       this.version = null;
/*      */     } else {
/*  720 */       this.version = "start:" + startTime + " end:" + endTime;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setVersion(ResourceId history) {
/*  724 */     if (history.getStartTime() != null && history.getEndTime() != null) {
/*  725 */       setVersion(history.getStartTime(), history.getEndTime());
/*  727 */     } else if (history.getVersion() != null) {
/*  728 */       Version ver = history.getVersion();
/*  729 */       if (ver.isVersionAction()) {
/*  730 */         setVersion(ver.getVersionAction());
/*  732 */       } else if (ver.isDateTime()) {
/*  733 */         setVersion(ver.getDateTime());
/*  735 */       } else if (ver.isIndex()) {
/*  736 */         setVersion(ver.getIndex().intValue());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setVersion(String version) {
/*  748 */     this.version = version;
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem getCoordinateSystem() {
/*  760 */     return this.coordinateSystem;
/*      */   }
/*      */   
/*      */   public void setCoordinateSystem(CoordinateReferenceSystem system) {
/*  781 */     this.coordinateSystem = system;
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem getCoordinateSystemReproject() {
/*  793 */     return this.coordinateSystemReproject;
/*      */   }
/*      */   
/*      */   public void setCoordinateSystemReproject(CoordinateReferenceSystem system) {
/*  807 */     this.coordinateSystemReproject = system;
/*      */   }
/*      */   
/*      */   public SortBy[] getSortBy() {
/*  843 */     return this.sortBy;
/*      */   }
/*      */   
/*      */   public void setSortBy(SortBy[] sortBy) {
/*  851 */     this.sortBy = sortBy;
/*      */   }
/*      */   
/*      */   public Hints getHints() {
/*  862 */     if (this.hints == null)
/*  863 */       this.hints = new Hints(); 
/*  865 */     return this.hints;
/*      */   }
/*      */   
/*      */   public void setHints(Hints hints) {
/*  894 */     this.hints = hints;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  904 */     String[] n = getPropertyNames();
/*  906 */     return ((n == null) ? -1 : ((n.length == 0) ? 0 : (n.length | n[0].hashCode()))) | getMaxFeatures() | ((getFilter() == null) ? 0 : getFilter().hashCode()) | ((getTypeName() == null) ? 0 : getTypeName().hashCode()) | ((getVersion() == null) ? 0 : getVersion().hashCode()) | ((getCoordinateSystem() == null) ? 0 : getCoordinateSystem().hashCode()) | ((getCoordinateSystemReproject() == null) ? 0 : getCoordinateSystemReproject().hashCode()) | getStartIndex().intValue();
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/*  926 */     if (obj == null || !(obj instanceof Query))
/*  927 */       return false; 
/*  929 */     if (this == obj)
/*  929 */       return true; 
/*  930 */     Query other = (Query)obj;
/*  932 */     return (Arrays.equals((Object[])getPropertyNames(), (Object[])other.getPropertyNames()) && retrieveAllProperties() == other.retrieveAllProperties() && getMaxFeatures() == other.getMaxFeatures() && ((getFilter() == null) ? (other.getFilter() == null) : getFilter().equals(other.getFilter())) && ((getTypeName() == null) ? (other.getTypeName() == null) : getTypeName().equals(other.getTypeName())) && ((getVersion() == null) ? (other.getVersion() == null) : getVersion().equals(other.getVersion())) && ((getCoordinateSystem() == null) ? (other.getCoordinateSystem() == null) : getCoordinateSystem().equals(other.getCoordinateSystem())) && ((getCoordinateSystemReproject() == null) ? (other.getCoordinateSystemReproject() == null) : getCoordinateSystemReproject().equals(other.getCoordinateSystemReproject())) && getStartIndex() == other.getStartIndex() && ((getHints() == null) ? (other.getHints() == null) : getHints().equals(other.getHints())));
/*      */   }
/*      */   
/*      */   public String toString() {
/*  956 */     StringBuffer returnString = new StringBuffer("Query:");
/*  958 */     if (this.handle != null)
/*  959 */       returnString.append(" [" + this.handle + "]"); 
/*  962 */     returnString.append("\n   feature type: " + this.typeName);
/*  964 */     if (this.filter != null)
/*  965 */       returnString.append("\n   filter: " + this.filter.toString()); 
/*  968 */     returnString.append("\n   [properties: ");
/*  970 */     if (this.properties == null || this.properties.size() == 0) {
/*  971 */       returnString.append(" ALL ]");
/*      */     } else {
/*  973 */       for (int i = 0; i < this.properties.size(); i++) {
/*  974 */         returnString.append(this.properties.get(i));
/*  976 */         if (i < this.properties.size() - 1)
/*  977 */           returnString.append(", "); 
/*      */       } 
/*  981 */       returnString.append("]");
/*      */     } 
/*  984 */     if (this.sortBy != null && this.sortBy.length > 0) {
/*  985 */       returnString.append("\n   [sort by: ");
/*  986 */       for (int i = 0; i < this.sortBy.length; i++) {
/*  987 */         SortBy sb = this.sortBy[i];
/*  988 */         if (sb == SortBy.NATURAL_ORDER) {
/*  989 */           returnString.append("NATURAL");
/*  990 */         } else if (sb == SortBy.REVERSE_ORDER) {
/*  991 */           returnString.append("REVERSE");
/*      */         } else {
/*  993 */           returnString.append(sb.getPropertyName().getPropertyName());
/*  994 */           returnString.append(" ");
/*  995 */           returnString.append(sb.getSortOrder().name());
/*      */         } 
/*  998 */         if (i < this.sortBy.length - 1)
/*  999 */           returnString.append(", "); 
/*      */       } 
/* 1003 */       returnString.append("]");
/*      */     } 
/* 1006 */     return returnString.toString();
/*      */   }
/*      */   
/*      */   public List<Join> getJoins() {
/* 1018 */     return this.joins;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */