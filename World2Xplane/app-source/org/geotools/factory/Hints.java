/*      */ package org.geotools.factory;
/*      */ 
/*      */ import java.awt.RenderingHints;
/*      */ import java.io.File;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import javax.sql.DataSource;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.geotools.util.logging.Logging;
/*      */ 
/*      */ public class Hints extends RenderingHints {
/*   76 */   private static volatile Map<RenderingHints.Key, Object> GLOBAL = new ConcurrentHashMap<RenderingHints.Key, Object>();
/*      */   
/*   81 */   private static AtomicBoolean needScan = new AtomicBoolean(true);
/*      */   
/*   96 */   public static final ClassKey CRS_AUTHORITY_FACTORY = new ClassKey("org.opengis.referencing.crs.CRSAuthorityFactory");
/*      */   
/*  104 */   public static final ClassKey CS_AUTHORITY_FACTORY = new ClassKey("org.opengis.referencing.cs.CSAuthorityFactory");
/*      */   
/*  112 */   public static final ClassKey DATUM_AUTHORITY_FACTORY = new ClassKey("org.opengis.referencing.datum.DatumAuthorityFactory");
/*      */   
/*  120 */   public static final ClassKey CRS_FACTORY = new ClassKey("org.opengis.referencing.crs.CRSFactory");
/*      */   
/*  128 */   public static final ClassKey CS_FACTORY = new ClassKey("org.opengis.referencing.cs.CSFactory");
/*      */   
/*  136 */   public static final ClassKey DATUM_FACTORY = new ClassKey("org.opengis.referencing.datum.DatumFactory");
/*      */   
/*  144 */   public static final ClassKey COORDINATE_OPERATION_FACTORY = new ClassKey("org.opengis.referencing.operation.CoordinateOperationFactory");
/*      */   
/*  153 */   public static final ClassKey COORDINATE_OPERATION_AUTHORITY_FACTORY = new ClassKey("org.opengis.referencing.operation.CoordinateOperationAuthorityFactory");
/*      */   
/*  161 */   public static final ClassKey MATH_TRANSFORM_FACTORY = new ClassKey("org.opengis.referencing.operation.MathTransformFactory");
/*      */   
/*  171 */   public static final Key DEFAULT_COORDINATE_REFERENCE_SYSTEM = new Key("org.opengis.referencing.crs.CoordinateReferenceSystem");
/*      */   
/*  193 */   public static final FileKey CRS_AUTHORITY_EXTRA_DIRECTORY = new FileKey(false);
/*      */   
/*  214 */   public static final Key EPSG_DATA_SOURCE = new DataSourceKey();
/*      */   
/*  225 */   public static final OptionKey DATUM_SHIFT_METHOD = new OptionKey(new String[] { "Molodenski", "Abridged_Molodenski", "Geocentric", "*" });
/*      */   
/*  246 */   public static final Key LENIENT_DATUM_SHIFT = new Key(Boolean.class);
/*      */   
/*  293 */   public static final Key FORCE_LONGITUDE_FIRST_AXIS_ORDER = new Key(Boolean.class);
/*      */   
/*  319 */   public static final Key FORCE_AXIS_ORDER_HONORING = new Key(String.class);
/*      */   
/*  343 */   public static final Key FORCE_STANDARD_AXIS_DIRECTIONS = new Key(Boolean.class);
/*      */   
/*  364 */   public static final Key FORCE_STANDARD_AXIS_UNITS = new Key(Boolean.class);
/*      */   
/*  374 */   public static final Key VERSION = new Key("org.geotools.util.Version");
/*      */   
/*  395 */   public static final Key USE_PROVIDED_FID = new Key(Boolean.class);
/*      */   
/*  410 */   public static final Key PROVIDED_FID = new Key(String.class);
/*      */   
/*  425 */   public static final Key CRS = new Key("org.opengis.referencing.crs.CoordinateReferenceSystem");
/*      */   
/*  433 */   public static final Key PRECISION = new Key("org.opengis.geometry.Precision");
/*      */   
/*  440 */   public static final Key POSITION_FACTORY = new Key("org.opengis.geometry.PositionFactory");
/*      */   
/*  448 */   public static final Key GEOMETRY_FACTORY = new Key("org.opengis.geometry.coordinate.GeometryFactory");
/*      */   
/*  455 */   public static final Key COMPLEX_FACTORY = new Key("org.opengis.geometry.complex.ComplexFactory");
/*      */   
/*  462 */   public static final Key AGGREGATE_FACTORY = new Key("org.opengis.geometry.aggregate.AggregateFactory");
/*      */   
/*  469 */   public static final Key PRIMITIVE_FACTORY = new Key("org.opengis.geometry.primitive.PrimitiveFactory");
/*      */   
/*  477 */   public static final Key GEOMETRY_VALIDATE = new Key(Boolean.class);
/*      */   
/*  493 */   public static final ClassKey JTS_GEOMETRY_FACTORY = new ClassKey("com.vividsolutions.jts.geom.GeometryFactory");
/*      */   
/*  501 */   public static final ClassKey JTS_COORDINATE_SEQUENCE_FACTORY = new ClassKey("com.vividsolutions.jts.geom.CoordinateSequenceFactory");
/*      */   
/*  510 */   public static final Key JTS_PRECISION_MODEL = new Key("com.vividsolutions.jts.geom.PrecisionModel");
/*      */   
/*  519 */   public static final Key JTS_SRID = new Key(Integer.class);
/*      */   
/*  535 */   public static ClassKey FEATURE_FACTORY = new ClassKey("org.opengis.feature.FeatureFactory");
/*      */   
/*  543 */   public static ClassKey FEATURE_TYPE_FACTORY = new ClassKey("org.opengis.feature.type.FeatureTypeFactory");
/*      */   
/*  553 */   public static final ClassKey FEATURE_LOCK_FACTORY = new ClassKey("org.geotools.data.FeatureLockFactory");
/*      */   
/*  563 */   public static final ClassKey FEATURE_COLLECTIONS = new ClassKey("org.geotools.feature.FeatureCollections");
/*      */   
/*  573 */   public static final Key FEATURE_TYPE_FACTORY_NAME = new Key(String.class);
/*      */   
/*  584 */   public static final Key FEATURE_DETACHED = new Key(Boolean.class);
/*      */   
/*  593 */   public static final Key FEATURE_2D = new Key(Boolean.class);
/*      */   
/*  602 */   public static final Key MAX_MEMORY_SORT = new Key(Integer.class);
/*      */   
/*  611 */   public static final Key GEOMETRY_DISTANCE = new Key(Double.class);
/*      */   
/*  618 */   public static final Key GEOMETRY_GENERALIZATION = new Key(Double.class);
/*      */   
/*  626 */   public static final Key GEOMETRY_SIMPLIFICATION = new Key(Double.class);
/*      */   
/*  631 */   public static final Key SCREENMAP = new ClassKey("org.geotools.renderer.ScreenMap");
/*      */   
/*  637 */   public static final Key COORDINATE_DIMENSION = new Key(Integer.class);
/*      */   
/*  647 */   public static final ClassKey STYLE_FACTORY = new ClassKey("org.geotools.styling.StyleFactory");
/*      */   
/*  657 */   public static final ClassKey ATTRIBUTE_TYPE_FACTORY = new ClassKey("org.geotools.feature.AttributeTypeFactory");
/*      */   
/*  667 */   public static final ClassKey FILTER_FACTORY = new ClassKey("org.opengis.filter.FilterFactory");
/*      */   
/*  677 */   public static final ClassKey VIRTUAL_TABLE_PARAMETERS = new ClassKey("java.util.Map");
/*      */   
/*  693 */   public static final Key MAX_ALLOWED_TILES = new Key(Integer.class);
/*      */   
/*  701 */   public static final Key MOSAIC_LOCATION_ATTRIBUTE = new Key(String.class);
/*      */   
/*  710 */   public static final Key USE_JAI_IMAGEREAD = new Key(Boolean.class);
/*      */   
/*  719 */   public static final Key OVERVIEW_POLICY = new Key("org.geotools.coverage.grid.io.OverviewPolicy");
/*      */   
/*  727 */   public static final Key DECIMATION_POLICY = new Key("org.geotools.coverage.grid.io.DecimationPolicy");
/*      */   
/*  749 */   public static final Key COVERAGE_PROCESSING_VIEW = new Key("org.geotools.coverage.grid.ViewType");
/*      */   
/*  761 */   public static final Key REPLACE_NON_GEOPHYSICS_VIEW = new Key(Boolean.class);
/*      */   
/*  778 */   public static final Key TILE_ENCODING = new Key(String.class);
/*      */   
/*  783 */   public static final Key JAI_INSTANCE = new Key("javax.media.jai.JAI");
/*      */   
/*  788 */   public static final Key SAMPLE_DIMENSION_TYPE = new Key("org.opengis.coverage.SampleDimensionType");
/*      */   
/*  796 */   public static final ClassKey GRID_COVERAGE_FACTORY = new ClassKey("org.geotools.coverage.grid.GridCoverageFactory");
/*      */   
/*  803 */   public static final ClassKey EXECUTOR_SERVICE = new ClassKey("java.util.concurrent.ExecutorService");
/*      */   
/*  810 */   public static final Key RESAMPLE_TOLERANCE = new Key(Double.class);
/*      */   
/*  824 */   public static final Key RESOLVE = new Key("net.opengis.wfs20.ResolveValueType");
/*      */   
/*  831 */   public static final Key RESOLVE_TIMEOUT = new Key(Integer.class);
/*      */   
/*  838 */   public static final Key ASSOCIATION_TRAVERSAL_DEPTH = new Key(Integer.class);
/*      */   
/*  845 */   public static final Key ASSOCIATION_PROPERTY = new Key("org.opengis.filter.expression.PropertyName");
/*      */   
/*  872 */   public static final OptionKey CACHE_POLICY = new OptionKey(new String[] { "weak", "all", "fixed", "none", "default", "soft" });
/*      */   
/*  880 */   public static final IntegerKey CACHE_LIMIT = new IntegerKey(50);
/*      */   
/*  901 */   public static final IntegerKey AUTHORITY_MAX_ACTIVE = new IntegerKey(Runtime.getRuntime().availableProcessors() + 1);
/*      */   
/*  922 */   public static final IntegerKey AUTHORITY_MIN_IDLE = new IntegerKey(1);
/*      */   
/*  943 */   public static final IntegerKey AUTHORITY_MAX_IDLE = new IntegerKey(2);
/*      */   
/*  951 */   public static final IntegerKey AUTHORITY_MIN_EVICT_IDLETIME = new IntegerKey(120000);
/*      */   
/*  960 */   public static final IntegerKey AUTHORITY_SOFTMIN_EVICT_IDLETIME = new IntegerKey(10000);
/*      */   
/*  967 */   public static final IntegerKey AUTHORITY_TIME_BETWEEN_EVICTION_RUNS = new IntegerKey(5000);
/*      */   
/*  978 */   public static final DoubleKey COMPARISON_TOLERANCE = new DoubleKey(0.0D);
/*      */   
/*      */   public Hints() {
/*  986 */     super(null);
/*      */   }
/*      */   
/*      */   public Hints(RenderingHints.Key key, Object value) {
/*  996 */     super(null);
/*  997 */     put(key, value);
/*      */   }
/*      */   
/*      */   public Hints(RenderingHints.Key key1, Object value1, RenderingHints.Key key2, Object value2) {
/* 1013 */     this(key1, value1);
/* 1014 */     put(key2, value2);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public Hints(RenderingHints.Key key1, Object value1, Object[] pairs) {
/* 1031 */     this(key1, value1);
/* 1032 */     fromPairs(pairs);
/*      */   }
/*      */   
/*      */   public Hints(RenderingHints.Key key1, Object value1, RenderingHints.Key key2, Object value2, Object... pairs) {
/* 1050 */     this(key1, value1, key2, value2);
/* 1051 */     fromPairs(pairs);
/*      */   }
/*      */   
/*      */   private void fromPairs(Object[] pairs) throws IllegalArgumentException {
/* 1061 */     if ((pairs.length & 0x1) != 0)
/* 1062 */       throw new IllegalArgumentException(Errors.format(149, Integer.valueOf(pairs.length))); 
/* 1065 */     for (int i = 0; i < pairs.length; i += 2)
/* 1066 */       put(pairs[i], pairs[i + 1]); 
/*      */   }
/*      */   
/*      */   public Hints(Map<? extends RenderingHints.Key, ?> hints) {
/* 1078 */     super(stripNonKeys(hints));
/*      */   }
/*      */   
/*      */   public Hints(RenderingHints hints) {
/* 1091 */     super(stripNonKeys(hints));
/*      */   }
/*      */   
/*      */   static Map<RenderingHints.Key, Object> stripNonKeys(Map<?, ?> hints) {
/* 1103 */     if (hints == null)
/* 1104 */       return null; 
/* 1112 */     Map<?, ?> map = hints;
/* 1113 */     for (Iterator it = hints.keySet().iterator(); it.hasNext(); ) {
/* 1114 */       Object key = it.next();
/* 1115 */       if (!(key instanceof RenderingHints.Key)) {
/* 1116 */         if (map == hints)
/* 1118 */           map = new HashMap<Object, Object>(map); 
/* 1120 */         map.remove(key);
/*      */       } 
/*      */     } 
/* 1123 */     return (Map)map;
/*      */   }
/*      */   
/*      */   public Hints clone() {
/* 1133 */     return (Hints)super.clone();
/*      */   }
/*      */   
/*      */   public static void scanSystemProperties() {
/* 1151 */     needScan.set(true);
/*      */   }
/*      */   
/*      */   private static boolean ensureSystemDefaultLoaded() {
/* 1162 */     if (needScan.get()) {
/* 1163 */       Map<RenderingHints.Key, Object> newGlobal = new ConcurrentHashMap<RenderingHints.Key, Object>(GLOBAL);
/* 1164 */       boolean modified = GeoTools.scanForSystemHints(newGlobal);
/* 1165 */       GLOBAL = newGlobal;
/* 1166 */       needScan.set(false);
/* 1167 */       return modified;
/*      */     } 
/* 1169 */     return false;
/*      */   }
/*      */   
/*      */   static Hints getDefaults(boolean strict) {
/*      */     Hints hints;
/* 1179 */     boolean changed = ensureSystemDefaultLoaded();
/* 1180 */     if (strict) {
/* 1181 */       hints = new StrictHints(GLOBAL);
/*      */     } else {
/* 1183 */       hints = new Hints(GLOBAL);
/*      */     } 
/* 1185 */     if (changed)
/* 1186 */       GeoTools.fireConfigurationChanged(); 
/* 1188 */     return hints;
/*      */   }
/*      */   
/*      */   static void putSystemDefault(RenderingHints hints) {
/* 1196 */     ensureSystemDefaultLoaded();
/* 1197 */     Map<RenderingHints.Key, Object> map = toMap(hints);
/* 1198 */     GLOBAL.putAll(map);
/* 1199 */     GeoTools.fireConfigurationChanged();
/*      */   }
/*      */   
/*      */   private static Map<RenderingHints.Key, Object> toMap(RenderingHints hints) {
/* 1210 */     Map<RenderingHints.Key, Object> result = new HashMap<RenderingHints.Key, Object>();
/* 1211 */     for (Map.Entry<Object, Object> entry : hints.entrySet()) {
/* 1212 */       if (entry.getKey() instanceof RenderingHints.Key)
/* 1213 */         result.put((RenderingHints.Key)entry.getKey(), entry.getValue()); 
/*      */     } 
/* 1217 */     return result;
/*      */   }
/*      */   
/*      */   public static Object getSystemDefault(RenderingHints.Key key) {
/* 1233 */     boolean changed = ensureSystemDefaultLoaded();
/* 1234 */     Object value = GLOBAL.get(key);
/* 1235 */     if (changed)
/* 1236 */       GeoTools.fireConfigurationChanged(); 
/* 1238 */     return value;
/*      */   }
/*      */   
/*      */   public static Object putSystemDefault(RenderingHints.Key key, Object value) {
/* 1258 */     boolean changed = ensureSystemDefaultLoaded();
/* 1259 */     Object old = GLOBAL.put(key, value);
/* 1260 */     if (changed || !Utilities.equals(value, old))
/* 1261 */       GeoTools.fireConfigurationChanged(); 
/* 1263 */     return old;
/*      */   }
/*      */   
/*      */   public static Object removeSystemDefault(RenderingHints.Key key) {
/* 1279 */     boolean changed = ensureSystemDefaultLoaded();
/* 1280 */     Object old = GLOBAL.remove(key);
/* 1281 */     if (changed || old != null)
/* 1282 */       GeoTools.fireConfigurationChanged(); 
/* 1284 */     return old;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1296 */     String lineSeparator = System.getProperty("line.separator", "\n");
/* 1297 */     StringBuilder buffer = new StringBuilder("Hints:");
/* 1298 */     buffer.append(lineSeparator).append(AbstractFactory.toString(this));
/* 1299 */     Map<?, ?> extra = null;
/* 1301 */     boolean changed = ensureSystemDefaultLoaded();
/* 1302 */     if (!GLOBAL.isEmpty())
/* 1303 */       extra = new HashMap<Object, Object>(GLOBAL); 
/* 1305 */     if (changed)
/* 1306 */       GeoTools.fireConfigurationChanged(); 
/* 1308 */     if (extra != null) {
/* 1309 */       extra.keySet().removeAll(keySet());
/* 1310 */       if (!extra.isEmpty())
/* 1311 */         buffer.append("System defaults:").append(lineSeparator).append(AbstractFactory.toString(extra)); 
/*      */     } 
/* 1315 */     return buffer.toString();
/*      */   }
/*      */   
/*      */   static String nameOf(RenderingHints.Key key) {
/* 1322 */     if (key instanceof Key)
/* 1323 */       return key.toString(); 
/* 1325 */     int t = 0;
/*      */     while (true) {
/*      */       Class<?> type;
/* 1328 */       switch (t++) {
/*      */         case 0:
/* 1330 */           type = RenderingHints.class;
/*      */           break;
/*      */         case 1:
/*      */           try {
/* 1335 */             type = Class.forName("javax.media.jai.JAI");
/*      */             break;
/* 1337 */           } catch (ClassNotFoundException e) {
/*      */             continue;
/* 1339 */           } catch (NoClassDefFoundError e) {
/*      */             continue;
/*      */           } 
/*      */         default:
/* 1345 */           return key.toString();
/*      */       } 
/* 1348 */       String name = nameOf(type, key);
/* 1349 */       if (name != null)
/* 1350 */         return name; 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String nameOf(Class<?> type, RenderingHints.Key key) {
/* 1360 */     Field[] fields = type.getFields();
/* 1361 */     for (int i = 0; i < fields.length; i++) {
/* 1362 */       Field f = fields[i];
/* 1363 */       if (Modifier.isStatic(f.getModifiers())) {
/*      */         Object v;
/*      */         try {
/* 1366 */           v = f.get(null);
/* 1367 */         } catch (IllegalAccessException e) {}
/* 1370 */         if (v == key)
/* 1371 */           return f.getName(); 
/*      */       } 
/*      */     } 
/* 1375 */     return null;
/*      */   }
/*      */   
/*      */   public static class Key extends RenderingHints.Key {
/*      */     private static int count;
/*      */     
/*      */     private final String className;
/*      */     
/*      */     private transient Class<?> valueClass;
/*      */     
/*      */     public Key(Class<?> classe) {
/* 1414 */       this(classe.getName());
/* 1415 */       this.valueClass = classe;
/*      */     }
/*      */     
/*      */     Key(String className) {
/* 1426 */       super(count());
/* 1427 */       this.className = className;
/*      */     }
/*      */     
/*      */     private static synchronized int count() {
/* 1436 */       return count++;
/*      */     }
/*      */     
/*      */     public Class<?> getValueClass() {
/* 1445 */       if (this.valueClass == null)
/*      */         try {
/* 1447 */           this.valueClass = Class.forName(this.className);
/* 1448 */         } catch (ClassNotFoundException exception) {
/* 1449 */           Logging.unexpectedException(Key.class, "getValueClass", exception);
/* 1450 */           this.valueClass = Object.class;
/*      */         }  
/* 1453 */       return this.valueClass;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1476 */       return getValueClass().isInstance(value);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1486 */       int t = 0;
/*      */       while (true) {
/*      */         Class<?> type;
/* 1489 */         switch (t++) {
/*      */           case 0:
/* 1490 */             type = Hints.class;
/*      */             break;
/*      */           case 1:
/* 1491 */             type = getValueClass();
/*      */             break;
/*      */           default:
/* 1492 */             return super.toString();
/*      */         } 
/* 1494 */         String name = Hints.nameOf(type, this);
/* 1495 */         if (name != null)
/* 1496 */           return name; 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class ClassKey extends Key {
/*      */     public ClassKey(Class<?> classe) {
/* 1518 */       super(classe);
/*      */     }
/*      */     
/*      */     ClassKey(String className) {
/* 1529 */       super(className);
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1545 */       if (value == null)
/* 1546 */         return false; 
/* 1552 */       if (value instanceof Class[]) {
/* 1553 */         Class<?>[] types = (Class[])value;
/* 1554 */         for (int i = 0; i < types.length; i++) {
/* 1555 */           if (!isCompatibleValue(types[i]))
/* 1556 */             return false; 
/*      */         } 
/* 1559 */         return (types.length != 0);
/*      */       } 
/* 1570 */       if (value instanceof Class) {
/* 1571 */         Class<?> type = (Class)value;
/* 1572 */         Class<?> expected = getValueClass();
/* 1573 */         if (expected.isAssignableFrom(type))
/* 1574 */           return true; 
/* 1576 */         if (expected.isInterface() && !type.isInterface()) {
/* 1577 */           int modifiers = type.getModifiers();
/* 1578 */           if (Modifier.isAbstract(modifiers) && !Modifier.isFinal(modifiers))
/* 1579 */             return true; 
/*      */         } 
/* 1582 */         return false;
/*      */       } 
/* 1584 */       return super.isCompatibleValue(value);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class FileKey extends Key {
/*      */     private final boolean writable;
/*      */     
/*      */     public FileKey(boolean writable) {
/* 1610 */       super(File.class);
/* 1611 */       this.writable = writable;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/*      */       File file;
/* 1620 */       if (value instanceof File) {
/* 1621 */         file = (File)value;
/* 1622 */       } else if (value instanceof String) {
/* 1623 */         file = new File((String)value);
/*      */       } else {
/* 1625 */         return false;
/*      */       } 
/* 1627 */       if (file.exists())
/* 1628 */         return (!this.writable || file.canWrite()); 
/* 1630 */       File parent = file.getParentFile();
/* 1631 */       return (parent != null && parent.canWrite());
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class IntegerKey extends Key {
/*      */     private final int number;
/*      */     
/*      */     public IntegerKey(int number) {
/* 1656 */       super(Integer.class);
/* 1657 */       this.number = number;
/*      */     }
/*      */     
/*      */     public int getDefault() {
/* 1666 */       return this.number;
/*      */     }
/*      */     
/*      */     public int toValue(Hints hints) {
/* 1678 */       if (hints != null) {
/* 1679 */         Object value = hints.get(this);
/* 1680 */         if (value instanceof Number)
/* 1681 */           return ((Number)value).intValue(); 
/* 1682 */         if (value instanceof CharSequence)
/* 1683 */           return Integer.parseInt(value.toString()); 
/*      */       } 
/* 1686 */       return this.number;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1694 */       if (value instanceof Short || value instanceof Integer)
/* 1695 */         return true; 
/* 1697 */       if (value instanceof String || value instanceof org.opengis.util.InternationalString)
/*      */         try {
/* 1699 */           Integer.parseInt(value.toString());
/* 1700 */         } catch (NumberFormatException e) {
/* 1701 */           Logging.getLogger(IntegerKey.class).finer(e.toString());
/*      */         }  
/* 1704 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class DoubleKey extends Key {
/*      */     private final double number;
/*      */     
/*      */     public DoubleKey(double number) {
/* 1729 */       super(Integer.class);
/* 1730 */       this.number = number;
/*      */     }
/*      */     
/*      */     public double getDefault() {
/* 1739 */       return this.number;
/*      */     }
/*      */     
/*      */     public double toValue(Hints hints) {
/* 1751 */       if (hints != null) {
/* 1752 */         Object value = hints.get(this);
/* 1753 */         if (value instanceof Number)
/* 1754 */           return ((Number)value).doubleValue(); 
/* 1755 */         if (value instanceof CharSequence)
/* 1756 */           return Double.parseDouble(value.toString()); 
/*      */       } 
/* 1759 */       return this.number;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1767 */       if (value instanceof Float || value instanceof Double)
/* 1768 */         return true; 
/* 1770 */       if (value instanceof String || value instanceof org.opengis.util.InternationalString)
/*      */         try {
/* 1772 */           Double.parseDouble(value.toString());
/* 1773 */         } catch (NumberFormatException e) {
/* 1774 */           Logging.getLogger(DoubleKey.class).finer(e.toString());
/*      */         }  
/* 1777 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class OptionKey extends Key {
/*      */     private final Set<String> options;
/*      */     
/*      */     private final boolean wildcard;
/*      */     
/*      */     public OptionKey(String... alternatives) {
/* 1808 */       super(String.class);
/* 1809 */       Set<String> options = new TreeSet<String>(Arrays.asList(alternatives));
/* 1810 */       this.wildcard = options.remove("*");
/* 1811 */       this.options = Collections.unmodifiableSet(options);
/*      */     }
/*      */     
/*      */     public Set<String> getOptions() {
/* 1820 */       return this.options;
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1830 */       return this.wildcard ? (value instanceof String) : this.options.contains(value);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class DataSourceKey extends Key {
/*      */     public DataSourceKey() {
/* 1853 */       super(DataSource.class);
/*      */     }
/*      */     
/*      */     public boolean isCompatibleValue(Object value) {
/* 1861 */       return (value instanceof DataSource || value instanceof String || value instanceof javax.naming.Name);
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\Hints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */