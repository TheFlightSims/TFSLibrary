/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.opengis.filter.And;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.Not;
/*     */ import org.opengis.filter.Or;
/*     */ import org.opengis.filter.PropertyIsBetween;
/*     */ import org.opengis.filter.PropertyIsEqualTo;
/*     */ import org.opengis.filter.PropertyIsGreaterThan;
/*     */ import org.opengis.filter.PropertyIsGreaterThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLessThan;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.PropertyIsLike;
/*     */ import org.opengis.filter.PropertyIsNotEqualTo;
/*     */ import org.opengis.filter.PropertyIsNull;
/*     */ import org.opengis.filter.expression.Add;
/*     */ import org.opengis.filter.expression.Divide;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.filter.expression.Function;
/*     */ import org.opengis.filter.expression.Multiply;
/*     */ import org.opengis.filter.expression.Subtract;
/*     */ import org.opengis.filter.spatial.BBOX;
/*     */ import org.opengis.filter.spatial.Beyond;
/*     */ import org.opengis.filter.spatial.Contains;
/*     */ import org.opengis.filter.spatial.Crosses;
/*     */ import org.opengis.filter.spatial.DWithin;
/*     */ import org.opengis.filter.spatial.Disjoint;
/*     */ import org.opengis.filter.spatial.Equals;
/*     */ import org.opengis.filter.spatial.Intersects;
/*     */ import org.opengis.filter.spatial.Overlaps;
/*     */ import org.opengis.filter.spatial.Touches;
/*     */ import org.opengis.filter.spatial.Within;
/*     */ 
/*     */ public class FilterCapabilities {
/*     */   public static final long NO_OP = 0L;
/*     */   
/*     */   public static final long NONE = 1073741824L;
/*     */   
/*     */   public static final long ALL = -2147483648L;
/*     */   
/*     */   public static final long SPATIAL_BBOX = 1L;
/*     */   
/*     */   public static final long SPATIAL_EQUALS = 2L;
/*     */   
/*     */   public static final long SPATIAL_DISJOINT = 4L;
/*     */   
/*     */   public static final long SPATIAL_INTERSECT = 8L;
/*     */   
/*     */   public static final long SPATIAL_TOUCHES = 16L;
/*     */   
/*     */   public static final long SPATIAL_CROSSES = 32L;
/*     */   
/*     */   public static final long SPATIAL_WITHIN = 64L;
/*     */   
/*     */   public static final long SPATIAL_CONTAINS = 128L;
/*     */   
/*     */   public static final long SPATIAL_OVERLAPS = 256L;
/*     */   
/*     */   public static final long SPATIAL_BEYOND = 512L;
/*     */   
/*     */   public static final long SPATIAL_DWITHIN = 1024L;
/*     */   
/*     */   public static final long LIKE = 2048L;
/*     */   
/*     */   public static final long BETWEEN = 4096L;
/*     */   
/*     */   public static final long NULL_CHECK = 8192L;
/*     */   
/*     */   public static final long SIMPLE_ARITHMETIC = 16384L;
/*     */   
/*     */   public static final long FUNCTIONS = 32768L;
/*     */   
/*     */   public static final long COMPARE_EQUALS = 65536L;
/*     */   
/*     */   public static final long COMPARE_GREATER_THAN = 131072L;
/*     */   
/*     */   public static final long COMPARE_GREATER_THAN_EQUAL = 262144L;
/*     */   
/*     */   public static final long COMPARE_LESS_THAN = 524288L;
/*     */   
/*     */   public static final long COMPARE_LESS_THAN_EQUAL = 1048576L;
/*     */   
/*     */   public static final long COMPARE_NOT_EQUALS = 2097152L;
/*     */   
/*     */   public static final long FID = 4194304L;
/*     */   
/*     */   public static final long LOGIC_AND = 8388608L;
/*     */   
/*     */   public static final long LOGIC_NOT = 16777216L;
/*     */   
/*     */   public static final long LOGIC_OR = 33554432L;
/*     */   
/*     */   public static final long LOGICAL = 58720256L;
/*     */   
/*     */   public static final long SIMPLE_COMPARISONS = 4128768L;
/*     */   
/*     */   public static final FilterCapabilities SIMPLE_COMPARISONS_OPENGIS;
/*     */   
/*     */   public static final FilterCapabilities LOGICAL_OPENGIS;
/*     */   
/* 188 */   static final Map intTypeToOpenGisTypeMap = new HashMap<Object, Object>();
/*     */   
/*     */   static {
/* 191 */     SIMPLE_COMPARISONS_OPENGIS = new FilterCapabilities();
/* 192 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsEqualTo.class);
/* 193 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsGreaterThan.class);
/* 194 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsGreaterThanOrEqualTo.class);
/* 195 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsLessThanOrEqualTo.class);
/* 196 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsLessThan.class);
/* 197 */     SIMPLE_COMPARISONS_OPENGIS.addType(PropertyIsNotEqualTo.class);
/* 199 */     LOGICAL_OPENGIS = new FilterCapabilities();
/* 200 */     LOGICAL_OPENGIS.addType(And.class);
/* 201 */     LOGICAL_OPENGIS.addType(Not.class);
/* 202 */     LOGICAL_OPENGIS.addType(Or.class);
/* 204 */     intTypeToOpenGisTypeMap.put(new Long(1L), BBOX.class);
/* 205 */     intTypeToOpenGisTypeMap.put(new Long(2L), Equals.class);
/* 206 */     intTypeToOpenGisTypeMap.put(new Long(4L), Disjoint.class);
/* 207 */     intTypeToOpenGisTypeMap.put(new Long(8L), Intersects.class);
/* 208 */     intTypeToOpenGisTypeMap.put(new Long(16L), Touches.class);
/* 209 */     intTypeToOpenGisTypeMap.put(new Long(32L), Crosses.class);
/* 210 */     intTypeToOpenGisTypeMap.put(new Long(64L), Within.class);
/* 211 */     intTypeToOpenGisTypeMap.put(new Long(128L), Contains.class);
/* 212 */     intTypeToOpenGisTypeMap.put(new Long(256L), Overlaps.class);
/* 213 */     intTypeToOpenGisTypeMap.put(new Long(512L), Beyond.class);
/* 214 */     intTypeToOpenGisTypeMap.put(new Long(1024L), DWithin.class);
/* 215 */     intTypeToOpenGisTypeMap.put(new Long(16384L), new Class[] { Add.class, Subtract.class, Multiply.class, Divide.class });
/* 216 */     intTypeToOpenGisTypeMap.put(new Long(32768L), Function.class);
/* 217 */     intTypeToOpenGisTypeMap.put(new Long(65536L), PropertyIsEqualTo.class);
/* 218 */     intTypeToOpenGisTypeMap.put(new Long(2097152L), PropertyIsNotEqualTo.class);
/* 219 */     intTypeToOpenGisTypeMap.put(new Long(131072L), PropertyIsGreaterThan.class);
/* 220 */     intTypeToOpenGisTypeMap.put(new Long(262144L), PropertyIsGreaterThanOrEqualTo.class);
/* 221 */     intTypeToOpenGisTypeMap.put(new Long(524288L), PropertyIsLessThan.class);
/* 222 */     intTypeToOpenGisTypeMap.put(new Long(1048576L), PropertyIsLessThanOrEqualTo.class);
/* 223 */     intTypeToOpenGisTypeMap.put(new Long(8192L), PropertyIsNull.class);
/* 224 */     intTypeToOpenGisTypeMap.put(new Long(2048L), PropertyIsLike.class);
/* 225 */     intTypeToOpenGisTypeMap.put(new Long(4096L), PropertyIsBetween.class);
/* 226 */     intTypeToOpenGisTypeMap.put(new Long(8388608L), And.class);
/* 227 */     intTypeToOpenGisTypeMap.put(new Long(33554432L), Or.class);
/* 228 */     intTypeToOpenGisTypeMap.put(new Long(16777216L), Not.class);
/*     */   }
/*     */   
/* 231 */   private long ops = 0L;
/*     */   
/* 233 */   private Set functions = new HashSet();
/*     */   
/*     */   public FilterCapabilities(long filterCapabilitiesType) {
/* 236 */     addType(filterCapabilitiesType);
/*     */   }
/*     */   
/*     */   public FilterCapabilities() {
/* 240 */     this(0L);
/*     */   }
/*     */   
/*     */   public FilterCapabilities(Class type) {
/* 244 */     addType(type);
/*     */   }
/*     */   
/*     */   public void addType(long type) {
/* 254 */     if ((this.ops & type) != 0L)
/*     */       return; 
/* 257 */     this.ops |= type;
/* 259 */     for (Iterator<Map.Entry> it = intTypeToOpenGisTypeMap.entrySet().iterator(); it.hasNext(); ) {
/* 260 */       Map.Entry entry = it.next();
/* 261 */       long key = ((Long)entry.getKey()).longValue();
/* 262 */       if ((key & type) != 0L) {
/* 263 */         Object filter = entry.getValue();
/* 264 */         if (filter != null) {
/* 265 */           if (filter instanceof Class[]) {
/* 266 */             Class[] filters = (Class[])filter;
/* 267 */             for (int i = 0; i < filters.length; i++)
/* 268 */               addType(filters[i], false); 
/*     */             continue;
/*     */           } 
/* 271 */           addType((Class)filter, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addType(Class<?> type) {
/* 285 */     if (Filter.class.isAssignableFrom(type) || Expression.class.isAssignableFrom(type)) {
/* 288 */       addType(32768L);
/* 289 */       this.functions.add(type);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addType(Class<?> type, boolean addFunctionType) {
/* 300 */     if (Filter.class.isAssignableFrom(type) || Expression.class.isAssignableFrom(type)) {
/* 303 */       if (addFunctionType)
/* 303 */         addType(32768L); 
/* 304 */       this.functions.add(type);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addAll(FilterCapabilities capabilities) {
/* 314 */     addType(capabilities.ops);
/* 315 */     this.functions.addAll(capabilities.functions);
/*     */   }
/*     */   
/*     */   public void addType(short type) {
/* 327 */     addAll(convertFilterTypeToMask(type));
/*     */   }
/*     */   
/*     */   public FilterCapabilities convertFilterTypeToMask(short type) {
/* 337 */     if (type == -12345)
/* 338 */       return FilterNameTypeMapping.NO_OP_CAPS; 
/* 339 */     if (type == 12345)
/* 340 */       return FilterNameTypeMapping.ALL_CAPS; 
/* 341 */     Object object = FilterNameTypeMapping.filterTypeToFilterCapabilitiesMap.get(new Short(type));
/* 342 */     return (FilterCapabilities)object;
/*     */   }
/*     */   
/*     */   public boolean supports(Filter filter) {
/* 355 */     for (Iterator<Class<?>> ifunc = this.functions.iterator(); ifunc.hasNext();) {
/* 356 */       if (((Class)ifunc.next()).isAssignableFrom(filter.getClass()))
/* 357 */         return true; 
/*     */     } 
/* 360 */     if (this.functions.contains(filter.getClass()))
/* 361 */       return true; 
/* 363 */     short filterType = Filters.getFilterType(filter);
/* 364 */     if (filterType == 0)
/* 366 */       return false; 
/* 368 */     return supports(filterType);
/*     */   }
/*     */   
/*     */   public boolean fullySupports(Filter filter) {
/* 387 */     boolean supports = true;
/* 389 */     if (filter == null)
/* 390 */       throw new IllegalArgumentException("Null filters can not be unpacked, did you mean Filter.INCLUDE?"); 
/* 394 */     short filterType = Filters.getFilterType(filter);
/* 396 */     if (AbstractFilter.isLogicFilter(filterType)) {
/* 397 */       Iterator<Filter> filters = ((LogicFilter)filter).getFilterIterator();
/* 398 */       Filter testFilter = null;
/* 401 */       while (filters.hasNext()) {
/* 402 */         testFilter = filters.next();
/* 404 */         if (!fullySupports(testFilter)) {
/* 405 */           supports = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 410 */       supports = supports(filter);
/*     */     } 
/* 413 */     return supports;
/*     */   }
/*     */   
/*     */   public boolean supports(short type) {
/* 426 */     return supports(convertFilterTypeToMask(type));
/*     */   }
/*     */   
/*     */   public boolean supports(long type) {
/* 430 */     return ((this.ops & type) == type);
/*     */   }
/*     */   
/*     */   public boolean supports(FilterCapabilities type) {
/* 434 */     return ((this.ops & type.ops) == type.ops && this.functions.containsAll(type.functions));
/*     */   }
/*     */   
/*     */   public boolean supports(Class type) {
/* 438 */     return this.functions.contains(type);
/*     */   }
/*     */   
/*     */   public long getScalarOps() {
/* 442 */     return this.ops & 0x3FFF800L;
/*     */   }
/*     */   
/*     */   public long getSpatialOps() {
/* 445 */     return this.ops & 0x7FFL;
/*     */   }
/*     */   
/*     */   public static FilterCapabilities findOperation(String name) {
/* 457 */     return FilterNameTypeMapping.findOperation(name);
/*     */   }
/*     */   
/*     */   public static FilterCapabilities findFunction(String name) {
/* 467 */     return FilterNameTypeMapping.findFunction(name);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */