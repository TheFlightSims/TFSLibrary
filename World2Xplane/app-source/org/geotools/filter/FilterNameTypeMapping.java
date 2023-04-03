/*     */ package org.geotools.filter;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.opengis.filter.expression.Function;
/*     */ 
/*     */ class FilterNameTypeMapping {
/*  32 */   static Map spatialFiltersMap = loadSpatialFiltersMap();
/*     */   
/*  33 */   static Map comparisonsMap = loadComparisonFilterMap();
/*     */   
/*  34 */   static Map filterTypeToFilterCapabilitiesMap = loadFilterTypeToFilterCapabilitiesMap();
/*     */   
/*  35 */   static Map functionNameMap = loadFunctionNameMap();
/*     */   
/*     */   public static Map loadSpatialFiltersMap() {
/*  38 */     spatialFiltersMap = new HashMap<Object, Object>();
/*  39 */     spatialFiltersMap.put("", NO_OP_CAPS);
/*  40 */     spatialFiltersMap.put("BBOX", new FilterCapabilities(1L));
/*  41 */     spatialFiltersMap.put("Equals", new FilterCapabilities(2L));
/*  42 */     spatialFiltersMap.put("Disjoint", new FilterCapabilities(4L));
/*  43 */     spatialFiltersMap.put("Intersect", new FilterCapabilities(8L));
/*  44 */     spatialFiltersMap.put("Touches", new FilterCapabilities(16L));
/*  45 */     spatialFiltersMap.put("Crosses", new FilterCapabilities(32L));
/*  46 */     spatialFiltersMap.put("Within", new FilterCapabilities(64L));
/*  47 */     spatialFiltersMap.put("Contains", new FilterCapabilities(128L));
/*  48 */     spatialFiltersMap.put("Overlaps", new FilterCapabilities(256L));
/*  49 */     spatialFiltersMap.put("Beyond", new FilterCapabilities(512L));
/*  50 */     spatialFiltersMap.put("DWithin", new FilterCapabilities(1024L));
/*  52 */     return spatialFiltersMap;
/*     */   }
/*     */   
/*     */   public static Map loadComparisonFilterMap() {
/*  56 */     comparisonsMap = new HashMap<Object, Object>();
/*  57 */     comparisonsMap.put("", NO_OP_CAPS);
/*  58 */     comparisonsMap.put("Logical", new FilterCapabilities(58720256L));
/*  59 */     comparisonsMap.put("Simple_Comparisons", new FilterCapabilities(4128768L));
/*  60 */     comparisonsMap.put("Like", new FilterCapabilities(2048L));
/*  61 */     comparisonsMap.put("Between", new FilterCapabilities(4096L));
/*  62 */     comparisonsMap.put("NullCheck", new FilterCapabilities(8192L));
/*  63 */     comparisonsMap.put("Simple_Arithmetic", new FilterCapabilities(16384L));
/*  64 */     comparisonsMap.put("Functions", new FilterCapabilities(32768L));
/*  66 */     return comparisonsMap;
/*     */   }
/*     */   
/*     */   public static Map loadFilterTypeToFilterCapabilitiesMap() {
/*  70 */     Map<Object, Object> conversionMap = new HashMap<Object, Object>();
/*  71 */     conversionMap.put(new Short((short)19), new FilterCapabilities(4096L));
/*  72 */     conversionMap.put(new Short((short)14), new FilterCapabilities(65536L));
/*  73 */     conversionMap.put(new Short((short)16), new FilterCapabilities(131072L));
/*  74 */     conversionMap.put(new Short((short)18), new FilterCapabilities(262144L));
/*  75 */     conversionMap.put(new Short((short)15), new FilterCapabilities(524288L));
/*  76 */     conversionMap.put(new Short((short)17), new FilterCapabilities(1048576L));
/*  77 */     conversionMap.put(new Short((short)23), new FilterCapabilities(2097152L));
/*  78 */     conversionMap.put(new Short((short)22), new FilterCapabilities(4194304L));
/*  79 */     conversionMap.put(new Short((short)4), new FilterCapabilities(1L));
/*  80 */     conversionMap.put(new Short((short)13), new FilterCapabilities(512L));
/*  81 */     conversionMap.put(new Short((short)11), new FilterCapabilities(128L));
/*  82 */     conversionMap.put(new Short((short)9), new FilterCapabilities(32L));
/*  83 */     conversionMap.put(new Short((short)6), new FilterCapabilities(4L));
/*  84 */     conversionMap.put(new Short((short)24), new FilterCapabilities(1024L));
/*  85 */     conversionMap.put(new Short((short)5), new FilterCapabilities(2L));
/*  86 */     conversionMap.put(new Short((short)7), new FilterCapabilities(8L));
/*  87 */     conversionMap.put(new Short((short)12), new FilterCapabilities(256L));
/*  88 */     conversionMap.put(new Short((short)8), new FilterCapabilities(16L));
/*  89 */     conversionMap.put(new Short((short)10), new FilterCapabilities(64L));
/*  90 */     conversionMap.put(new Short((short)20), new FilterCapabilities(2048L));
/*  91 */     conversionMap.put(new Short((short)2), new FilterCapabilities(8388608L));
/*  92 */     conversionMap.put(new Short((short)3), new FilterCapabilities(16777216L));
/*  93 */     conversionMap.put(new Short((short)1), new FilterCapabilities(33554432L));
/*  94 */     conversionMap.put(new Short((short)21), new FilterCapabilities(8192L));
/*  95 */     return conversionMap;
/*     */   }
/*     */   
/*     */   public static Map loadFunctionNameMap() {
/* 100 */     functionNameMap = new HashMap<Object, Object>();
/* 101 */     functionNameMap.put("", NO_OP_CAPS);
/* 102 */     Iterator<Function> functions = CommonFactoryFinder.getFunctions(null).iterator();
/* 103 */     while (functions.hasNext()) {
/* 104 */       Function exp = functions.next();
/* 105 */       functionNameMap.put(exp.getName().toLowerCase(), new FilterCapabilities(exp.getClass()));
/*     */     } 
/* 107 */     return functionNameMap;
/*     */   }
/*     */   
/*     */   public static FilterCapabilities findFunction(String name) {
/* 111 */     FilterCapabilities filterCapabilities = (FilterCapabilities)functionNameMap.get(name);
/* 112 */     if (filterCapabilities != null)
/* 113 */       return filterCapabilities; 
/* 115 */     return NO_OP_CAPS;
/*     */   }
/*     */   
/*     */   public static FilterCapabilities findOperation(String s) {
/* 126 */     if (spatialFiltersMap.containsKey(s))
/* 127 */       return (FilterCapabilities)spatialFiltersMap.get(s); 
/* 130 */     if (comparisonsMap.containsKey(s))
/* 131 */       return (FilterCapabilities)comparisonsMap.get(s); 
/* 134 */     return NO_OP_CAPS;
/*     */   }
/*     */   
/*     */   public static String writeSpatialOperation(long i) {
/* 144 */     if (i == 1L)
/* 145 */       return "BBOX"; 
/* 147 */     if (i == 2L)
/* 148 */       return "Equals"; 
/* 150 */     if (i == 4L)
/* 151 */       return "Disjoint"; 
/* 153 */     if (i == 8L)
/* 154 */       return "Intersect"; 
/* 156 */     if (i == 16L)
/* 157 */       return "Touches"; 
/* 159 */     if (i == 32L)
/* 160 */       return "Crosses"; 
/* 162 */     if (i == 64L)
/* 163 */       return "Within"; 
/* 165 */     if (i == 128L)
/* 166 */       return "Contains"; 
/* 168 */     if (i == 256L)
/* 169 */       return "Overlaps"; 
/* 171 */     if (i == 512L)
/* 172 */       return "Beyond"; 
/* 174 */     if (i == 1024L)
/* 175 */       return "DWithin"; 
/* 177 */     return "";
/*     */   }
/*     */   
/*     */   public static String writeScalarOperation(long i) {
/* 187 */     if (i == 58720256L)
/* 188 */       return "Logical"; 
/* 190 */     if (i == 4128768L)
/* 191 */       return "Simple_Comparisons"; 
/* 193 */     if (i == 2048L)
/* 194 */       return "Like"; 
/* 196 */     if (i == 4096L)
/* 197 */       return "Between"; 
/* 199 */     if (i == 8192L)
/* 200 */       return "NullCheck"; 
/* 202 */     if (i == 16384L)
/* 203 */       return "Simple_Arithmetic"; 
/* 205 */     if (i == 32768L)
/* 206 */       return "Functions"; 
/* 208 */     if (i == 4194304L)
/* 209 */       return "FeatureID"; 
/* 211 */     if (i == 65536L)
/* 212 */       return "Compare_Equals"; 
/* 214 */     if (i == 131072L)
/* 215 */       return "Compare_Greater_Than"; 
/* 217 */     if (i == 262144L)
/* 218 */       return "Compare_Greater_Than_Equal"; 
/* 220 */     if (i == 524288L)
/* 221 */       return "Compare_Less_Than"; 
/* 223 */     if (i == 1048576L)
/* 224 */       return "Compare_Less_Than_Equal"; 
/* 226 */     if (i == 2097152L)
/* 227 */       return "Compare_Not_Equals"; 
/* 229 */     return "";
/*     */   }
/*     */   
/* 232 */   static final FilterCapabilities NO_OP_CAPS = new FilterCapabilities(0L);
/*     */   
/* 233 */   public static final FilterCapabilities ALL_CAPS = new FilterCapabilities() {
/*     */       public boolean supports(Class type) {
/* 236 */         return super.supports(type);
/*     */       }
/*     */       
/*     */       public boolean supports(Filter filter) {
/* 240 */         return true;
/*     */       }
/*     */       
/*     */       public boolean supports(FilterCapabilities type) {
/* 244 */         return true;
/*     */       }
/*     */       
/*     */       public boolean supports(long type) {
/* 248 */         return true;
/*     */       }
/*     */       
/*     */       public boolean supports(short type) {
/* 252 */         return true;
/*     */       }
/*     */     };
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\FilterNameTypeMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */