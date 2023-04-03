/*      */ package org.geotools.data;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryCollection;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.LinearRing;
/*      */ import com.vividsolutions.jts.geom.MultiLineString;
/*      */ import com.vividsolutions.jts.geom.MultiPoint;
/*      */ import com.vividsolutions.jts.geom.MultiPolygon;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.Polygon;
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.net.URL;
/*      */ import java.net.URLDecoder;
/*      */ import java.sql.Date;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.UUID;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.data.collection.CollectionDataStore;
/*      */ import org.geotools.data.collection.CollectionFeatureSource;
/*      */ import org.geotools.data.collection.ListFeatureCollection;
/*      */ import org.geotools.data.collection.SpatialIndexFeatureCollection;
/*      */ import org.geotools.data.collection.SpatialIndexFeatureSource;
/*      */ import org.geotools.data.collection.TreeSetFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureCollection;
/*      */ import org.geotools.data.simple.SimpleFeatureIterator;
/*      */ import org.geotools.data.simple.SimpleFeatureLocking;
/*      */ import org.geotools.data.simple.SimpleFeatureReader;
/*      */ import org.geotools.data.simple.SimpleFeatureSource;
/*      */ import org.geotools.data.simple.SimpleFeatureStore;
/*      */ import org.geotools.data.store.ArrayDataStore;
/*      */ import org.geotools.data.view.DefaultView;
/*      */ import org.geotools.factory.CommonFactoryFinder;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.feature.AttributeImpl;
/*      */ import org.geotools.feature.AttributeTypeBuilder;
/*      */ import org.geotools.feature.DefaultFeatureCollection;
/*      */ import org.geotools.feature.FeatureCollection;
/*      */ import org.geotools.feature.FeatureIterator;
/*      */ import org.geotools.feature.FeatureTypes;
/*      */ import org.geotools.feature.GeometryAttributeImpl;
/*      */ import org.geotools.feature.NameImpl;
/*      */ import org.geotools.feature.SchemaException;
/*      */ import org.geotools.feature.collection.BridgeIterator;
/*      */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*      */ import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
/*      */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*      */ import org.geotools.feature.type.AttributeTypeImpl;
/*      */ import org.geotools.feature.type.GeometryDescriptorImpl;
/*      */ import org.geotools.feature.type.GeometryTypeImpl;
/*      */ import org.geotools.filter.FilterAttributeExtractor;
/*      */ import org.geotools.filter.visitor.PropertyNameResolvingVisitor;
/*      */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*      */ import org.geotools.metadata.iso.citation.Citations;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.geotools.util.Converters;
/*      */ import org.geotools.util.NullProgressListener;
/*      */ import org.geotools.util.Utilities;
/*      */ import org.opengis.feature.Feature;
/*      */ import org.opengis.feature.FeatureFactory;
/*      */ import org.opengis.feature.FeatureVisitor;
/*      */ import org.opengis.feature.IllegalAttributeException;
/*      */ import org.opengis.feature.Property;
/*      */ import org.opengis.feature.simple.SimpleFeature;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.AttributeDescriptor;
/*      */ import org.opengis.feature.type.AttributeType;
/*      */ import org.opengis.feature.type.FeatureType;
/*      */ import org.opengis.feature.type.GeometryDescriptor;
/*      */ import org.opengis.feature.type.GeometryType;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.feature.type.PropertyDescriptor;
/*      */ import org.opengis.filter.And;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.filter.FilterFactory2;
/*      */ import org.opengis.filter.FilterVisitor;
/*      */ import org.opengis.filter.expression.Expression;
/*      */ import org.opengis.filter.expression.ExpressionVisitor;
/*      */ import org.opengis.filter.expression.PropertyName;
/*      */ import org.opengis.filter.sort.SortBy;
/*      */ import org.opengis.geometry.BoundingBox;
/*      */ import org.opengis.metadata.citation.Citation;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.util.ProgressListener;
/*      */ 
/*      */ public class DataUtilities {
/*  195 */   static Map<String, Class> typeMap = (Map)new HashMap<String, Class<?>>();
/*      */   
/*  198 */   static Map<Class, String> typeEncode = (Map)new HashMap<Class<?>, String>();
/*      */   
/*  200 */   static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
/*      */   
/*      */   static final boolean IS_WINDOWS_OS;
/*      */   
/*      */   static {
/*  205 */     typeEncode.put(String.class, "String");
/*  206 */     typeMap.put("String", String.class);
/*  207 */     typeMap.put("string", String.class);
/*  208 */     typeMap.put("\"\"", String.class);
/*  210 */     typeEncode.put(Integer.class, "Integer");
/*  211 */     typeMap.put("Integer", Integer.class);
/*  212 */     typeMap.put("int", Integer.class);
/*  213 */     typeMap.put("0", Integer.class);
/*  215 */     typeEncode.put(Double.class, "Double");
/*  216 */     typeMap.put("Double", Double.class);
/*  217 */     typeMap.put("double", Double.class);
/*  218 */     typeMap.put("0.0", Double.class);
/*  220 */     typeEncode.put(Float.class, "Float");
/*  221 */     typeMap.put("Float", Float.class);
/*  222 */     typeMap.put("float", Float.class);
/*  223 */     typeMap.put("0.0f", Float.class);
/*  225 */     typeEncode.put(Boolean.class, "Boolean");
/*  226 */     typeMap.put("Boolean", Boolean.class);
/*  227 */     typeMap.put("true", Boolean.class);
/*  228 */     typeMap.put("false", Boolean.class);
/*  230 */     typeEncode.put(UUID.class, "UUID");
/*  231 */     typeMap.put("UUID", UUID.class);
/*  233 */     typeEncode.put(Geometry.class, "Geometry");
/*  234 */     typeMap.put("Geometry", Geometry.class);
/*  236 */     typeEncode.put(Point.class, "Point");
/*  237 */     typeMap.put("Point", Point.class);
/*  239 */     typeEncode.put(LineString.class, "LineString");
/*  240 */     typeMap.put("LineString", LineString.class);
/*  242 */     typeEncode.put(Polygon.class, "Polygon");
/*  243 */     typeMap.put("Polygon", Polygon.class);
/*  245 */     typeEncode.put(MultiPoint.class, "MultiPoint");
/*  246 */     typeMap.put("MultiPoint", MultiPoint.class);
/*  248 */     typeEncode.put(MultiLineString.class, "MultiLineString");
/*  249 */     typeMap.put("MultiLineString", MultiLineString.class);
/*  251 */     typeEncode.put(MultiPolygon.class, "MultiPolygon");
/*  252 */     typeMap.put("MultiPolygon", MultiPolygon.class);
/*  254 */     typeEncode.put(GeometryCollection.class, "GeometryCollection");
/*  255 */     typeMap.put("GeometryCollection", GeometryCollection.class);
/*  257 */     typeEncode.put(Date.class, "Date");
/*  258 */     typeMap.put("Date", Date.class);
/*  261 */     String os = System.getProperty("os.name");
/*  262 */     IS_WINDOWS_OS = os.toUpperCase().contains("WINDOWS");
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(SimpleFeatureType featureType) {
/*  272 */     String[] names = new String[featureType.getAttributeCount()];
/*  273 */     int count = featureType.getAttributeCount();
/*  274 */     for (int i = 0; i < count; i++)
/*  275 */       names[i] = featureType.getDescriptor(i).getLocalName(); 
/*  277 */     return names;
/*      */   }
/*      */   
/*      */   public static URL fileToURL(File file) {
/*      */     try {
/*  296 */       URL url = file.toURI().toURL();
/*  297 */       String string = url.toExternalForm();
/*  298 */       if (string.contains("+"))
/*  302 */         string = string.replace("+", "%2B"); 
/*  304 */       if (string.contains(" "))
/*  308 */         string = string.replace(" ", "%20"); 
/*  310 */       return new URL(string);
/*  311 */     } catch (MalformedURLException e) {
/*  312 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static File urlToFile(URL url) {
/*      */     String path3;
/*  333 */     if (!"file".equals(url.getProtocol()))
/*  334 */       return null; 
/*  336 */     String string = url.toExternalForm();
/*  337 */     if (string.contains("+"))
/*  341 */       string = string.replace("+", "%2B"); 
/*      */     try {
/*  344 */       string = URLDecoder.decode(string, "UTF-8");
/*  345 */     } catch (UnsupportedEncodingException e) {
/*  346 */       throw new RuntimeException("Could not decode the URL to UTF-8 format", e);
/*      */     } 
/*  351 */     String simplePrefix = "file:/";
/*  352 */     String standardPrefix = "file://";
/*  354 */     if (IS_WINDOWS_OS && string.startsWith(standardPrefix)) {
/*  356 */       path3 = string.substring(standardPrefix.length() - 2);
/*  357 */     } else if (string.startsWith(standardPrefix)) {
/*  358 */       path3 = string.substring(standardPrefix.length());
/*  359 */     } else if (string.startsWith(simplePrefix)) {
/*  360 */       path3 = string.substring(simplePrefix.length() - 1);
/*      */     } else {
/*  362 */       String auth = url.getAuthority();
/*  363 */       String path2 = url.getPath().replace("%20", " ");
/*  364 */       if (auth != null && !auth.equals("")) {
/*  365 */         path3 = "//" + auth + path2;
/*      */       } else {
/*  367 */         path3 = path2;
/*      */       } 
/*      */     } 
/*  371 */     return new File(path3);
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(Filter filter, SimpleFeatureType featureType) {
/*  382 */     if (filter == null)
/*  383 */       return new String[0]; 
/*  385 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  386 */     filter.accept((FilterVisitor)attExtractor, null);
/*  387 */     String[] attributeNames = attExtractor.getAttributeNames();
/*  388 */     return attributeNames;
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Filter filter, SimpleFeatureType featureType) {
/*  399 */     if (filter == null)
/*  400 */       return Collections.emptySet(); 
/*  402 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  403 */     filter.accept((FilterVisitor)attExtractor, null);
/*  404 */     Set<PropertyName> propertyNames = attExtractor.getPropertyNameSet();
/*  405 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(Filter filter) {
/*  412 */     return attributeNames(filter, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Filter filter) {
/*  419 */     return propertyNames(filter, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(Expression expression, SimpleFeatureType featureType) {
/*  430 */     if (expression == null)
/*  431 */       return new String[0]; 
/*  433 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  434 */     expression.accept((ExpressionVisitor)attExtractor, null);
/*  435 */     String[] attributeNames = attExtractor.getAttributeNames();
/*  436 */     return attributeNames;
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Expression expression, SimpleFeatureType featureType) {
/*  448 */     if (expression == null)
/*  449 */       return Collections.emptySet(); 
/*  451 */     FilterAttributeExtractor attExtractor = new FilterAttributeExtractor(featureType);
/*  452 */     expression.accept((ExpressionVisitor)attExtractor, null);
/*  453 */     Set<PropertyName> propertyNames = attExtractor.getPropertyNameSet();
/*  454 */     return propertyNames;
/*      */   }
/*      */   
/*      */   public static String[] attributeNames(Expression expression) {
/*  461 */     return attributeNames(expression, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   public static Set<PropertyName> propertyNames(Expression expression) {
/*  468 */     return propertyNames(expression, (SimpleFeatureType)null);
/*      */   }
/*      */   
/*      */   public static int compare(SimpleFeatureType typeA, SimpleFeatureType typeB) {
/*  503 */     if (typeA == typeB)
/*  504 */       return 0; 
/*  507 */     if (typeA == null)
/*  508 */       return -1; 
/*  511 */     if (typeB == null)
/*  512 */       return -1; 
/*  515 */     int countA = typeA.getAttributeCount();
/*  516 */     int countB = typeB.getAttributeCount();
/*  518 */     if (countA > countB)
/*  519 */       return -1; 
/*  524 */     int match = 0;
/*  526 */     for (int i = 0; i < countA; i++) {
/*  527 */       AttributeDescriptor a = typeA.getDescriptor(i);
/*  529 */       if (isMatch(a, typeB.getDescriptor(i))) {
/*  530 */         match++;
/*  531 */       } else if (!isMatch(a, typeB.getDescriptor(a.getLocalName()))) {
/*  535 */         return -1;
/*      */       } 
/*      */     } 
/*  539 */     if (countA == countB && match == countA)
/*  542 */       return 0; 
/*  545 */     return 1;
/*      */   }
/*      */   
/*      */   public static boolean isMatch(AttributeDescriptor a, AttributeDescriptor b) {
/*  562 */     if (a == b)
/*  563 */       return true; 
/*  566 */     if (b == null)
/*  567 */       return false; 
/*  570 */     if (a == null)
/*  571 */       return false; 
/*  574 */     if (a.equals(b))
/*  575 */       return true; 
/*  578 */     if (a.getLocalName().equals(b.getLocalName()) && a.getClass().equals(b.getClass()))
/*  579 */       return true; 
/*  582 */     return false;
/*      */   }
/*      */   
/*      */   public static SimpleFeature reType(SimpleFeatureType featureType, SimpleFeature feature) throws IllegalAttributeException {
/*  603 */     SimpleFeatureType origional = feature.getFeatureType();
/*  605 */     if (featureType.equals(origional))
/*  606 */       return SimpleFeatureBuilder.copy(feature); 
/*  609 */     String id = feature.getID();
/*  610 */     int numAtts = featureType.getAttributeCount();
/*  611 */     Object[] attributes = new Object[numAtts];
/*  614 */     for (int i = 0; i < numAtts; i++) {
/*  615 */       AttributeDescriptor curAttType = featureType.getDescriptor(i);
/*  616 */       String xpath = curAttType.getLocalName();
/*  617 */       attributes[i] = duplicate(feature.getAttribute(xpath));
/*      */     } 
/*  620 */     return SimpleFeatureBuilder.build(featureType, attributes, id);
/*      */   }
/*      */   
/*      */   public static SimpleFeature reType(SimpleFeatureType featureType, SimpleFeature feature, boolean duplicate) throws IllegalAttributeException {
/*  645 */     if (duplicate)
/*  646 */       return reType(featureType, feature); 
/*  649 */     SimpleFeatureType simpleFeatureType = feature.getFeatureType();
/*  650 */     if (featureType.equals(simpleFeatureType))
/*  651 */       return feature; 
/*  653 */     String id = feature.getID();
/*  654 */     int numAtts = featureType.getAttributeCount();
/*  655 */     Object[] attributes = new Object[numAtts];
/*  658 */     for (int i = 0; i < numAtts; i++) {
/*  659 */       AttributeDescriptor curAttType = featureType.getDescriptor(i);
/*  660 */       attributes[i] = feature.getAttribute(curAttType.getLocalName());
/*      */     } 
/*  662 */     return SimpleFeatureBuilder.build(featureType, attributes, id);
/*      */   }
/*      */   
/*      */   public static Object duplicate(Object src) {
/*  684 */     if (src == null)
/*  685 */       return null; 
/*  692 */     if (src instanceof String || src instanceof Integer || src instanceof Double || src instanceof Float || src instanceof Byte || src instanceof Boolean || src instanceof Short || src instanceof Long || src instanceof Character || src instanceof Number)
/*  696 */       return src; 
/*  699 */     if (src instanceof Date)
/*  700 */       return new Date(((Date)src).getTime()); 
/*  703 */     if (src instanceof URL || src instanceof URI)
/*  704 */       return src; 
/*  707 */     if (src instanceof Object[]) {
/*  708 */       Object[] array = (Object[])src;
/*  709 */       Object[] copy = new Object[array.length];
/*  711 */       for (int i = 0; i < array.length; i++)
/*  712 */         copy[i] = duplicate(array[i]); 
/*  715 */       return copy;
/*      */     } 
/*  718 */     if (src instanceof Geometry) {
/*  719 */       Geometry geometry = (Geometry)src;
/*  721 */       return geometry.clone();
/*      */     } 
/*  724 */     if (src instanceof SimpleFeature) {
/*  725 */       SimpleFeature feature = (SimpleFeature)src;
/*  726 */       return SimpleFeatureBuilder.copy(feature);
/*      */     } 
/*  734 */     Class<? extends Object> type = (Class)src.getClass();
/*  736 */     if (type.isArray() && type.getComponentType().isPrimitive()) {
/*  737 */       int length = Array.getLength(src);
/*  738 */       Object copy = Array.newInstance(type.getComponentType(), length);
/*  739 */       System.arraycopy(src, 0, copy, 0, length);
/*  741 */       return copy;
/*      */     } 
/*  744 */     if (type.isArray()) {
/*  745 */       int length = Array.getLength(src);
/*  746 */       Object copy = Array.newInstance(type.getComponentType(), length);
/*  748 */       for (int i = 0; i < length; i++)
/*  749 */         Array.set(copy, i, duplicate(Array.get(src, i))); 
/*  752 */       return copy;
/*      */     } 
/*  755 */     if (src instanceof List) {
/*  756 */       List list = (List)src;
/*  757 */       List<Object> copy = new ArrayList(list.size());
/*  759 */       for (Iterator i = list.iterator(); i.hasNext();)
/*  760 */         copy.add(duplicate(i.next())); 
/*  763 */       return Collections.unmodifiableList(copy);
/*      */     } 
/*  766 */     if (src instanceof Map) {
/*  767 */       Map map = (Map)src;
/*  768 */       Map<Object, Object> copy = new HashMap<Object, Object>(map.size());
/*  770 */       for (Iterator<Map.Entry> i = map.entrySet().iterator(); i.hasNext(); ) {
/*  771 */         Map.Entry entry = i.next();
/*  772 */         copy.put(entry.getKey(), duplicate(entry.getValue()));
/*      */       } 
/*  775 */       return Collections.unmodifiableMap(copy);
/*      */     } 
/*  778 */     if (src instanceof org.opengis.coverage.grid.GridCoverage)
/*  779 */       return src; 
/*  787 */     throw new IllegalAttributeException(null, "Do not know how to deep copy " + type.getName());
/*      */   }
/*      */   
/*      */   public static SimpleFeature template(SimpleFeatureType featureType) throws IllegalAttributeException {
/*  804 */     return SimpleFeatureBuilder.build(featureType, defaultValues(featureType), null);
/*      */   }
/*      */   
/*      */   public static SimpleFeature template(SimpleFeatureType featureType, String featureID) {
/*  818 */     return SimpleFeatureBuilder.build(featureType, defaultValues(featureType), featureID);
/*      */   }
/*      */   
/*      */   public static Object[] defaultValues(SimpleFeatureType featureType) {
/*  828 */     return defaultValues(featureType, null);
/*      */   }
/*      */   
/*      */   public static SimpleFeature template(SimpleFeatureType featureType, Object[] providedValues) {
/*  841 */     return SimpleFeatureBuilder.build(featureType, defaultValues(featureType, providedValues), null);
/*      */   }
/*      */   
/*      */   public static SimpleFeature template(SimpleFeatureType featureType, String featureID, Object[] providedValues) {
/*  858 */     return SimpleFeatureBuilder.build(featureType, defaultValues(featureType, providedValues), featureID);
/*      */   }
/*      */   
/*      */   public static Object[] defaultValues(SimpleFeatureType featureType, Object[] values) {
/*  871 */     if (values == null) {
/*  872 */       values = new Object[featureType.getAttributeCount()];
/*  873 */     } else if (values.length != featureType.getAttributeCount()) {
/*  874 */       throw new ArrayIndexOutOfBoundsException("values");
/*      */     } 
/*  877 */     for (int i = 0; i < featureType.getAttributeCount(); i++) {
/*  878 */       AttributeDescriptor descriptor = featureType.getDescriptor(i);
/*  879 */       values[i] = descriptor.getDefaultValue();
/*      */     } 
/*  882 */     return values;
/*      */   }
/*      */   
/*      */   public static Object defaultValue(AttributeDescriptor attributeType) throws IllegalAttributeException {
/*  898 */     Object value = attributeType.getDefaultValue();
/*  900 */     if (value == null && !attributeType.isNillable())
/*  901 */       return null; 
/*  903 */     return value;
/*      */   }
/*      */   
/*      */   public static Object defaultValue(Class<String> type) {
/*  934 */     if (type == String.class || type == Object.class)
/*  935 */       return ""; 
/*  937 */     if (type == Integer.class)
/*  938 */       return new Integer(0); 
/*  940 */     if (type == Double.class)
/*  941 */       return new Double(0.0D); 
/*  943 */     if (type == Long.class)
/*  944 */       return new Long(0L); 
/*  946 */     if (type == Short.class)
/*  947 */       return new Short((short)0); 
/*  949 */     if (type == Float.class)
/*  950 */       return new Float(0.0F); 
/*  952 */     if (type == BigDecimal.class)
/*  953 */       return BigDecimal.valueOf(0L); 
/*  955 */     if (type == BigInteger.class)
/*  956 */       return BigInteger.valueOf(0L); 
/*  958 */     if (type == Character.class)
/*  959 */       return new Character(' '); 
/*  961 */     if (type == Boolean.class)
/*  962 */       return Boolean.FALSE; 
/*  964 */     if (type == UUID.class)
/*  965 */       return UUID.fromString("00000000-0000-0000-0000-000000000000"); 
/*  967 */     if (type == Timestamp.class)
/*  968 */       return new Timestamp(0L); 
/*  969 */     if (type == Date.class)
/*  970 */       return new Date(0L); 
/*  971 */     if (type == Time.class)
/*  972 */       return new Time(0L); 
/*  973 */     if (type == Date.class)
/*  974 */       return new Date(0L); 
/*  976 */     GeometryFactory fac = new GeometryFactory();
/*  977 */     Coordinate coordinate = new Coordinate(0.0D, 0.0D);
/*  978 */     Point point = fac.createPoint(coordinate);
/*  980 */     if (type == Point.class)
/*  981 */       return point; 
/*  983 */     if (type == MultiPoint.class)
/*  984 */       return fac.createMultiPoint(new Point[] { point }); 
/*  986 */     LineString lineString = fac.createLineString(new Coordinate[] { new Coordinate(0.0D, 0.0D), new Coordinate(0.0D, 1.0D) });
/*  987 */     if (type == LineString.class)
/*  988 */       return lineString; 
/*  990 */     LinearRing linearRing = fac.createLinearRing(new Coordinate[] { new Coordinate(0.0D, 0.0D), new Coordinate(0.0D, 1.0D), new Coordinate(1.0D, 1.0D), new Coordinate(1.0D, 0.0D), new Coordinate(0.0D, 0.0D) });
/*  992 */     if (type == LinearRing.class)
/*  993 */       return linearRing; 
/*  995 */     if (type == MultiLineString.class)
/*  996 */       return fac.createMultiLineString(new LineString[] { lineString }); 
/*  998 */     Polygon polygon = fac.createPolygon(linearRing, new LinearRing[0]);
/*  999 */     if (type == Polygon.class)
/* 1000 */       return polygon; 
/* 1002 */     if (type == MultiPolygon.class)
/* 1003 */       return fac.createMultiPolygon(new Polygon[] { polygon }); 
/* 1006 */     throw new IllegalArgumentException(type + " is not supported by this method");
/*      */   }
/*      */   
/*      */   public static FeatureReader<SimpleFeatureType, SimpleFeature> reader(final SimpleFeature[] features) throws IOException {
/* 1024 */     if (features == null || features.length == 0)
/* 1025 */       throw new IOException("Provided features where empty"); 
/* 1028 */     return new FeatureReader<SimpleFeatureType, SimpleFeature>() {
/* 1029 */         SimpleFeature[] array = features;
/*      */         
/* 1031 */         int offset = -1;
/*      */         
/*      */         public SimpleFeatureType getFeatureType() {
/* 1034 */           return features[0].getFeatureType();
/*      */         }
/*      */         
/*      */         public SimpleFeature next() {
/* 1038 */           if (!hasNext())
/* 1039 */             throw new NoSuchElementException("No more features"); 
/* 1042 */           return this.array[++this.offset];
/*      */         }
/*      */         
/*      */         public boolean hasNext() {
/* 1046 */           return (this.array != null && this.offset < this.array.length - 1);
/*      */         }
/*      */         
/*      */         public void close() {
/* 1050 */           this.array = null;
/* 1051 */           this.offset = -1;
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public static SimpleFeatureSource source(SimpleFeature[] featureArray) {
/*      */     SimpleFeatureType featureType;
/* 1069 */     if (featureArray == null || featureArray.length == 0) {
/* 1070 */       featureType = FeatureTypes.EMPTY;
/*      */     } else {
/* 1072 */       featureType = featureArray[0].getFeatureType();
/*      */     } 
/* 1075 */     ArrayDataStore arrayDataStore = new ArrayDataStore(featureArray);
/* 1077 */     String typeName = featureType.getTypeName();
/*      */     try {
/* 1079 */       return arrayDataStore.getFeatureSource(typeName);
/* 1080 */     } catch (IOException e) {
/* 1081 */       throw new IllegalStateException("Unable to find " + typeName + " ArrayDataStore in an inconsistent" + "state", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SimpleFeatureSource source(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) {
/* 1099 */     if (collection == null)
/* 1100 */       throw new NullPointerException("No content provided"); 
/* 1102 */     if (collection instanceof ListFeatureCollection) {
/* 1103 */       ListFeatureCollection list = (ListFeatureCollection)collection;
/* 1104 */       CollectionFeatureSource source = new CollectionFeatureSource((SimpleFeatureCollection)list);
/* 1106 */       return (SimpleFeatureSource)source;
/*      */     } 
/* 1108 */     if (collection instanceof SpatialIndexFeatureCollection) {
/* 1109 */       SpatialIndexFeatureCollection indexed = (SpatialIndexFeatureCollection)collection;
/* 1110 */       SpatialIndexFeatureSource source = new SpatialIndexFeatureSource(indexed);
/* 1112 */       return (SimpleFeatureSource)source;
/*      */     } 
/* 1114 */     if (collection instanceof TreeSetFeatureCollection) {
/* 1115 */       TreeSetFeatureCollection tree = (TreeSetFeatureCollection)collection;
/* 1116 */       CollectionFeatureSource source = new CollectionFeatureSource((SimpleFeatureCollection)tree);
/* 1118 */       return (SimpleFeatureSource)source;
/*      */     } 
/* 1127 */     CollectionDataStore store = new CollectionDataStore(collection);
/* 1128 */     String typeName = store.getTypeNames()[0];
/*      */     try {
/* 1130 */       return store.getFeatureSource(typeName);
/* 1131 */     } catch (IOException e) {
/* 1132 */       throw new IllegalArgumentException("CollectionDataStore inconsistent,  ensure type name " + typeName + " is the same for all features", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SimpleFeatureSource createView(DataStore store, Query query) throws IOException, SchemaException {
/* 1154 */     return (SimpleFeatureSource)new DefaultView(store.getFeatureSource(query.getTypeName()), query);
/*      */   }
/*      */   
/*      */   public static FeatureReader<SimpleFeatureType, SimpleFeature> reader(Collection<SimpleFeature> collection) throws IOException {
/* 1169 */     return reader(collection.<SimpleFeature>toArray(new SimpleFeature[collection.size()]));
/*      */   }
/*      */   
/*      */   public static FeatureReader<SimpleFeatureType, SimpleFeature> reader(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) throws IOException {
/* 1184 */     return reader((SimpleFeature[])collection.toArray((Object[])new SimpleFeature[collection.size()]));
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection collection(SimpleFeature[] features) {
/* 1204 */     DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/* 1205 */     int length = features.length;
/* 1206 */     for (int i = 0; i < length; i++)
/* 1207 */       collection.add(features[i]); 
/* 1209 */     return (SimpleFeatureCollection)collection;
/*      */   }
/*      */   
/*      */   public static DefaultFeatureCollection collection(FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection) {
/* 1223 */     return new DefaultFeatureCollection(featureCollection);
/*      */   }
/*      */   
/*      */   public static void close(Iterator<?> iterator) {
/* 1232 */     if (iterator != null && iterator instanceof Closeable)
/*      */       try {
/* 1234 */         ((Closeable)iterator).close();
/* 1235 */       } catch (IOException e) {
/* 1236 */         String name = iterator.getClass().getPackage().toString();
/* 1237 */         Logger log = Logger.getLogger(name);
/* 1238 */         log.log(Level.FINE, e.getMessage(), e);
/*      */       }  
/*      */   }
/*      */   
/*      */   public static <F extends Feature> F first(FeatureCollection<?, F> featureCollection) {
/* 1250 */     if (featureCollection == null)
/* 1251 */       return null; 
/* 1253 */     FeatureIterator<F> iter = featureCollection.features();
/*      */     try {
/* 1255 */       while (iter.hasNext()) {
/* 1256 */         Feature feature = iter.next();
/* 1257 */         if (feature != null)
/* 1258 */           return (F)feature; 
/*      */       } 
/* 1261 */       return null;
/*      */     } finally {
/* 1263 */       iter.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection simple(FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection) {
/* 1290 */     if (featureCollection instanceof SimpleFeatureCollection)
/* 1291 */       return (SimpleFeatureCollection)featureCollection; 
/* 1293 */     if (featureCollection.getSchema() instanceof SimpleFeatureType)
/* 1294 */       return new SimpleFeatureCollectionBridge(featureCollection); 
/* 1296 */     throw new IllegalArgumentException("The provided feature collection contains complex features, cannot be bridged to a simple one");
/*      */   }
/*      */   
/*      */   public static SimpleFeatureReader simple(FeatureReader<SimpleFeatureType, SimpleFeature> reader) {
/* 1301 */     if (reader instanceof SimpleFeatureReader)
/* 1302 */       return (SimpleFeatureReader)reader; 
/* 1304 */     return new SimpleFeatureReaderBrige(reader);
/*      */   }
/*      */   
/*      */   public static SimpleFeatureSource simple(FeatureSource<SimpleFeatureType, SimpleFeature> source) {
/* 1318 */     if (source instanceof FeatureLocking)
/* 1319 */       return (SimpleFeatureSource)simple((FeatureLocking)source); 
/* 1320 */     if (source instanceof FeatureStore)
/* 1321 */       return (SimpleFeatureSource)simple((FeatureStore)source); 
/* 1324 */     if (source instanceof SimpleFeatureSource)
/* 1325 */       return (SimpleFeatureSource)source; 
/* 1327 */     if (source.getSchema() instanceof SimpleFeatureType)
/* 1328 */       return new SimpleFeatureSourceBridge(source); 
/* 1330 */     throw new IllegalArgumentException("The provided feature source contains complex features, cannot be bridged to a simple one");
/*      */   }
/*      */   
/*      */   public static SimpleFeatureStore simple(FeatureStore<SimpleFeatureType, SimpleFeature> store) {
/* 1344 */     if (store instanceof FeatureLocking)
/* 1345 */       return (SimpleFeatureStore)simple((FeatureLocking)store); 
/* 1348 */     if (store instanceof SimpleFeatureStore)
/* 1349 */       return (SimpleFeatureStore)store; 
/* 1351 */     if (store.getSchema() instanceof SimpleFeatureType)
/* 1352 */       return new SimpleFeatureStoreBridge(store); 
/* 1354 */     throw new IllegalArgumentException("The provided feature store contains complex features, cannot be bridged to a simple one");
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType simple(FeatureType featureType) throws DataSourceException {
/*      */     SimpleFeatureType simpleFeatureType;
/* 1370 */     if (featureType == null)
/* 1371 */       return null; 
/* 1376 */     Collection<PropertyDescriptor> descriptors = featureType.getDescriptors();
/* 1377 */     List<PropertyDescriptor> attributes = new ArrayList<PropertyDescriptor>(descriptors);
/* 1379 */     List<String> simpleProperties = new ArrayList<String>();
/* 1380 */     List<AttributeDescriptor> simpleAttributes = new ArrayList<AttributeDescriptor>();
/* 1384 */     List<String> ignoreList = Arrays.asList(new String[] { "location", "metaDataProperty", "description", "name", "boundedBy" });
/* 1387 */     for (Iterator<PropertyDescriptor> it = attributes.iterator(); it.hasNext(); ) {
/* 1388 */       PropertyDescriptor property = it.next();
/* 1389 */       if (!(property instanceof AttributeDescriptor))
/*      */         continue; 
/* 1392 */       AttributeDescriptor descriptor = (AttributeDescriptor)property;
/* 1393 */       Name name = descriptor.getName();
/* 1395 */       if (ignoreList.contains(name.getLocalPart()))
/* 1396 */         it.remove(); 
/*      */     } 
/* 1401 */     for (PropertyDescriptor descriptor : attributes) {
/* 1402 */       Class<?> binding = descriptor.getType().getBinding();
/* 1403 */       int maxOccurs = descriptor.getMaxOccurs();
/* 1404 */       Name name = descriptor.getName();
/* 1405 */       if (Object.class.equals(binding))
/*      */         continue; 
/* 1408 */       if (maxOccurs > 1)
/*      */         continue; 
/* 1411 */       if ("http://www.opengis.net/gml".equals(name.getNamespaceURI()))
/*      */         continue; 
/* 1414 */       if (descriptor instanceof AttributeDescriptor) {
/* 1415 */         AttributeDescriptor attribute = (AttributeDescriptor)descriptor;
/* 1417 */         simpleAttributes.add(attribute);
/* 1418 */         simpleProperties.add(attribute.getLocalName());
/*      */       } 
/*      */     } 
/* 1422 */     String[] properties = simpleProperties.<String>toArray(new String[simpleProperties.size()]);
/*      */     try {
/* 1425 */       if (featureType instanceof SimpleFeature) {
/* 1426 */         simpleFeatureType = createSubType((SimpleFeatureType)featureType, properties);
/*      */       } else {
/* 1429 */         SimpleFeatureTypeBuilder build = new SimpleFeatureTypeBuilder();
/* 1430 */         build.setName(featureType.getName());
/* 1431 */         build.setAttributes(simpleAttributes);
/* 1432 */         build.setDefaultGeometry(featureType.getGeometryDescriptor().getLocalName());
/* 1434 */         simpleFeatureType = build.buildFeatureType();
/*      */       } 
/* 1436 */     } catch (SchemaException e) {
/* 1437 */       throw new DataSourceException(e);
/*      */     } 
/* 1439 */     return simpleFeatureType;
/*      */   }
/*      */   
/*      */   public static SimpleFeatureLocking simple(FeatureLocking<SimpleFeatureType, SimpleFeature> locking) {
/* 1452 */     if (locking instanceof SimpleFeatureLocking)
/* 1453 */       return (SimpleFeatureLocking)locking; 
/* 1455 */     if (locking.getSchema() instanceof SimpleFeatureType)
/* 1456 */       return new SimpleFeatureLockingBridge(locking); 
/* 1458 */     throw new IllegalArgumentException("The provided feature store contains complex features, cannot be bridged to a simple one");
/*      */   }
/*      */   
/*      */   public static <F extends Feature> List<F> list(FeatureCollection<?, F> featureCollection) {
/* 1471 */     ArrayList<F> list = new ArrayList<F>();
/* 1472 */     FeatureIterator<F> iter = featureCollection.features();
/*      */     try {
/* 1474 */       while (iter.hasNext()) {
/* 1475 */         Feature feature = iter.next();
/* 1476 */         list.add((F)feature);
/*      */       } 
/*      */     } finally {
/* 1480 */       iter.close();
/*      */     } 
/* 1482 */     return list;
/*      */   }
/*      */   
/*      */   public static <F extends Feature> List<F> list(FeatureCollection<?, F> featureCollection, int maxFeatures) {
/* 1492 */     ArrayList<F> list = new ArrayList<F>();
/* 1493 */     FeatureIterator<F> iter = featureCollection.features();
/*      */     try {
/* 1495 */       for (int count = 0; iter.hasNext() && count < maxFeatures; count++) {
/* 1496 */         Feature feature = iter.next();
/* 1497 */         list.add((F)feature);
/*      */       } 
/*      */     } finally {
/* 1501 */       iter.close();
/*      */     } 
/* 1503 */     return list;
/*      */   }
/*      */   
/*      */   public static <F extends Feature> Iterator<F> iterator(FeatureIterator<F> featureIterator) {
/* 1513 */     return (Iterator<F>)new BridgeIterator(featureIterator);
/*      */   }
/*      */   
/*      */   public static Set<String> fidSet(FeatureCollection<?, ?> featureCollection) {
/* 1525 */     final HashSet<String> fids = new HashSet<String>();
/*      */     try {
/* 1527 */       featureCollection.accepts(new FeatureVisitor() {
/*      */             public void visit(Feature feature) {
/* 1529 */               fids.add(feature.getIdentifier().getID());
/*      */             }
/*      */           },  null);
/* 1532 */     } catch (IOException ignore) {}
/* 1534 */     return fids;
/*      */   }
/*      */   
/*      */   public static <F extends Feature> Collection<F> collectionCast(FeatureCollection<?, F> featureCollection) {
/* 1548 */     if (featureCollection instanceof Collection)
/* 1549 */       return (Collection)featureCollection; 
/* 1551 */     throw new IllegalArgumentException("Require access to SimpleFeatureCollection implementing Collecion.add");
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection collection(List<SimpleFeature> list) {
/* 1568 */     DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/* 1569 */     for (SimpleFeature feature : list)
/* 1570 */       collection.add(feature); 
/* 1572 */     return (SimpleFeatureCollection)collection;
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection collection(SimpleFeature feature) {
/* 1591 */     DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/* 1592 */     collection.add(feature);
/* 1593 */     return (SimpleFeatureCollection)collection;
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection collection(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 1611 */     DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/*      */     try {
/* 1613 */       while (reader.hasNext()) {
/*      */         try {
/* 1615 */           collection.add(reader.next());
/* 1616 */         } catch (NoSuchElementException e) {
/* 1617 */           throw (IOException)(new IOException("EOF")).initCause(e);
/* 1618 */         } catch (IllegalAttributeException e) {
/* 1619 */           throw (IOException)(new IOException()).initCause(e);
/*      */         } 
/*      */       } 
/*      */     } finally {
/* 1623 */       reader.close();
/*      */     } 
/* 1625 */     return (SimpleFeatureCollection)collection;
/*      */   }
/*      */   
/*      */   public static SimpleFeatureCollection collection(SimpleFeatureIterator reader) throws IOException {
/* 1643 */     DefaultFeatureCollection collection = new DefaultFeatureCollection(null, null);
/*      */     try {
/* 1645 */       while (reader.hasNext()) {
/*      */         try {
/* 1647 */           collection.add((SimpleFeature)reader.next());
/* 1648 */         } catch (NoSuchElementException e) {
/* 1649 */           throw (IOException)(new IOException("EOF")).initCause(e);
/*      */         } 
/*      */       } 
/*      */     } finally {
/* 1653 */       reader.close();
/*      */     } 
/* 1655 */     return (SimpleFeatureCollection)collection;
/*      */   }
/*      */   
/*      */   public static boolean attributesEqual(Object att, Object otherAtt) {
/* 1681 */     if (att == null) {
/* 1682 */       if (otherAtt != null)
/* 1683 */         return false; 
/* 1688 */     } else if (!att.equals(otherAtt)) {
/* 1689 */       return false;
/*      */     } 
/* 1693 */     return true;
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType createSubType(SimpleFeatureType featureType, String[] properties, CoordinateReferenceSystem override) throws SchemaException {
/* 1715 */     URI namespaceURI = null;
/* 1716 */     if (featureType.getName().getNamespaceURI() != null)
/*      */       try {
/* 1718 */         namespaceURI = new URI(featureType.getName().getNamespaceURI());
/* 1719 */       } catch (URISyntaxException e) {
/* 1720 */         throw new RuntimeException(e);
/*      */       }  
/* 1724 */     return createSubType(featureType, properties, override, featureType.getTypeName(), namespaceURI);
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType createSubType(SimpleFeatureType featureType, String[] properties, CoordinateReferenceSystem override, String typeName, URI namespace) throws SchemaException {
/* 1733 */     if (properties == null && override == null)
/* 1734 */       return featureType; 
/* 1737 */     if (properties == null) {
/* 1738 */       properties = new String[featureType.getAttributeCount()];
/* 1739 */       for (int k = 0; k < properties.length; k++)
/* 1740 */         properties[k] = featureType.getDescriptor(k).getLocalName(); 
/*      */     } 
/* 1744 */     String namespaceURI = (namespace != null) ? namespace.toString() : null;
/* 1745 */     boolean same = (featureType.getAttributeCount() == properties.length && featureType.getTypeName().equals(typeName) && Utilities.equals(featureType.getName().getNamespaceURI(), namespaceURI));
/* 1749 */     for (int i = 0; i < featureType.getAttributeCount() && same; i++) {
/* 1750 */       AttributeDescriptor type = featureType.getDescriptor(i);
/* 1751 */       same = (type.getLocalName().equals(properties[i]) && (override == null || !(type instanceof GeometryDescriptor) || assertEquals(override, ((GeometryDescriptor)type).getCoordinateReferenceSystem())));
/*      */     } 
/* 1757 */     if (same)
/* 1758 */       return featureType; 
/* 1761 */     AttributeDescriptor[] types = new AttributeDescriptor[properties.length];
/* 1763 */     for (int j = 0; j < properties.length; j++) {
/* 1764 */       types[j] = featureType.getDescriptor(properties[j]);
/* 1766 */       if (override != null && types[j] instanceof GeometryDescriptor) {
/* 1767 */         AttributeTypeBuilder ab = new AttributeTypeBuilder();
/* 1768 */         ab.init(types[j]);
/* 1769 */         ab.setCRS(override);
/* 1770 */         types[j] = (AttributeDescriptor)ab.buildDescriptor(types[j].getLocalName(), ab.buildGeometryType());
/*      */       } 
/*      */     } 
/* 1774 */     if (typeName == null)
/* 1775 */       typeName = featureType.getTypeName(); 
/* 1776 */     if (namespace == null && featureType.getName().getNamespaceURI() != null)
/*      */       try {
/* 1778 */         namespace = new URI(featureType.getName().getNamespaceURI());
/* 1779 */       } catch (URISyntaxException e) {
/* 1780 */         throw new RuntimeException(e);
/*      */       }  
/* 1783 */     SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 1784 */     tb.setName(typeName);
/* 1785 */     tb.setNamespaceURI(namespace);
/* 1786 */     tb.setCRS(null);
/* 1787 */     tb.addAll(types);
/* 1788 */     setDefaultGeometry(tb, properties, featureType);
/* 1789 */     return tb.buildFeatureType();
/*      */   }
/*      */   
/*      */   private static boolean assertEquals(Object o1, Object o2) {
/* 1793 */     return (o1 == null && o2 == null) ? true : ((o1 != null) ? o1.equals(o2) : false);
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType createSubType(SimpleFeatureType featureType, String[] properties) throws SchemaException {
/* 1808 */     if (properties == null)
/* 1809 */       return featureType; 
/* 1812 */     boolean same = (featureType.getAttributeCount() == properties.length);
/* 1814 */     for (int i = 0; i < featureType.getAttributeCount() && same; i++)
/* 1815 */       same = featureType.getDescriptor(i).getLocalName().equals(properties[i]); 
/* 1818 */     if (same)
/* 1819 */       return featureType; 
/* 1822 */     SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
/* 1823 */     tb.setName(featureType.getName());
/* 1824 */     tb.setCRS(null);
/* 1825 */     for (int j = 0; j < properties.length; j++)
/* 1826 */       tb.add(featureType.getDescriptor(properties[j])); 
/* 1828 */     setDefaultGeometry(tb, properties, featureType);
/* 1829 */     return tb.buildFeatureType();
/*      */   }
/*      */   
/*      */   private static void setDefaultGeometry(SimpleFeatureTypeBuilder typeBuilder, String[] properties, SimpleFeatureType featureType) {
/* 1834 */     GeometryDescriptor geometryDescriptor = featureType.getGeometryDescriptor();
/* 1835 */     if (geometryDescriptor != null) {
/* 1836 */       String propertyName = geometryDescriptor.getLocalName();
/* 1837 */       if (Arrays.<String>asList(properties).contains(propertyName))
/* 1838 */         typeBuilder.setDefaultGeometry(propertyName); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType createType(String typeName, String typeSpec) throws SchemaException {
/* 1897 */     int split = typeName.lastIndexOf('.');
/* 1898 */     String namespace = (split == -1) ? null : typeName.substring(0, split);
/* 1899 */     String name = (split == -1) ? typeName : typeName.substring(split + 1);
/* 1901 */     return createType(namespace, name, typeSpec);
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType createType(String namespace, String name, String typeSpec) throws SchemaException {
/* 1914 */     SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
/* 1915 */     builder.setName(name);
/* 1916 */     builder.setNamespaceURI(namespace);
/* 1918 */     String[] types = typeSpec.split(",");
/* 1921 */     builder.setCRS(null);
/* 1922 */     for (int i = 0; i < types.length; i++) {
/* 1923 */       boolean defaultGeometry = types[i].startsWith("*");
/* 1924 */       if (types[i].startsWith("*"))
/* 1925 */         types[i] = types[i].substring(1); 
/* 1928 */       AttributeDescriptor attributeType = createAttribute(types[i]);
/* 1929 */       builder.add(attributeType);
/* 1931 */       if (defaultGeometry)
/* 1932 */         builder.setDefaultGeometry(attributeType.getLocalName()); 
/*      */     } 
/* 1936 */     return builder.buildFeatureType();
/*      */   }
/*      */   
/*      */   public static String encodeType(SimpleFeatureType featureType) {
/* 1956 */     Collection<PropertyDescriptor> types = featureType.getDescriptors();
/* 1957 */     StringBuffer buf = new StringBuffer();
/* 1959 */     for (PropertyDescriptor type : types) {
/* 1960 */       buf.append(type.getName().getLocalPart());
/* 1961 */       buf.append(":");
/* 1962 */       buf.append(typeMap(type.getType().getBinding()));
/* 1963 */       if (type instanceof GeometryDescriptor) {
/* 1964 */         GeometryDescriptor gd = (GeometryDescriptor)type;
/* 1965 */         int srid = toSRID(gd.getCoordinateReferenceSystem());
/* 1966 */         if (srid != -1)
/* 1967 */           buf.append(":srid=" + srid); 
/*      */       } 
/* 1970 */       buf.append(",");
/*      */     } 
/* 1972 */     buf.delete(buf.length() - 1, buf.length());
/* 1973 */     return buf.toString();
/*      */   }
/*      */   
/*      */   private static int toSRID(CoordinateReferenceSystem crs) {
/* 1983 */     if (crs == null || crs.getIdentifiers() == null)
/* 1984 */       return -1; 
/* 1986 */     Iterator<ReferenceIdentifier> it = crs.getIdentifiers().iterator();
/* 1987 */     while (it.hasNext()) {
/* 1988 */       ReferenceIdentifier id = it.next();
/* 1989 */       Citation authority = id.getAuthority();
/* 1990 */       if (authority != null && authority.getTitle().equals(Citations.EPSG.getTitle()))
/*      */         try {
/* 1993 */           return Integer.parseInt(id.getCode());
/* 1995 */         } catch (NumberFormatException nanException) {} 
/*      */     } 
/* 2000 */     return -1;
/*      */   }
/*      */   
/*      */   public static String spec(FeatureType featureType) {
/* 2016 */     Collection<PropertyDescriptor> types = featureType.getDescriptors();
/* 2017 */     StringBuffer buf = new StringBuffer();
/* 2019 */     for (PropertyDescriptor type : types) {
/* 2020 */       buf.append(type.getName().getLocalPart());
/* 2021 */       buf.append(":");
/* 2022 */       buf.append(typeMap(type.getType().getBinding()));
/* 2023 */       if (type instanceof GeometryDescriptor) {
/* 2024 */         GeometryDescriptor gd = (GeometryDescriptor)type;
/* 2025 */         if (gd.getCoordinateReferenceSystem() != null && gd.getCoordinateReferenceSystem().getIdentifiers() != null) {
/* 2027 */           Iterator<ReferenceIdentifier> it = gd.getCoordinateReferenceSystem().getIdentifiers().iterator();
/* 2028 */           while (it.hasNext()) {
/* 2029 */             ReferenceIdentifier id = it.next();
/* 2031 */             if (id.getAuthority() != null && id.getAuthority().getTitle().equals(Citations.EPSG.getTitle())) {
/* 2033 */               buf.append(":srid=" + id.getCode());
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2040 */       buf.append(",");
/*      */     } 
/* 2042 */     buf.delete(buf.length() - 1, buf.length());
/* 2043 */     return buf.toString();
/*      */   }
/*      */   
/*      */   public static SimpleFeature createFeature(SimpleFeatureType featureType, String line) {
/*      */     String fid;
/* 2071 */     int fidSplit = line.indexOf('=');
/* 2072 */     int barSplit = line.indexOf('|');
/* 2073 */     if (fidSplit == -1 || (barSplit != -1 && barSplit < fidSplit)) {
/* 2074 */       fid = null;
/*      */     } else {
/* 2077 */       fid = line.substring(0, fidSplit);
/*      */     } 
/* 2079 */     String data = line.substring(fidSplit + 1);
/* 2080 */     String[] text = splitIntoText(data, featureType);
/* 2081 */     Object[] values = new Object[text.length];
/* 2082 */     for (int i = 0; i < text.length; i++) {
/* 2083 */       AttributeDescriptor descriptor = featureType.getDescriptor(i);
/* 2084 */       Object value = createValue(descriptor, text[i]);
/* 2085 */       values[i] = value;
/*      */     } 
/* 2087 */     SimpleFeature feature = SimpleFeatureBuilder.build(featureType, values, fid);
/* 2089 */     return feature;
/*      */   }
/*      */   
/*      */   private static String[] splitIntoText(String data, SimpleFeatureType type) {
/* 2110 */     String[] text = new String[type.getAttributeCount()];
/* 2111 */     int i = 0;
/* 2112 */     StringBuilder item = new StringBuilder();
/* 2113 */     for (String str : data.split("\\|", -1)) {
/* 2114 */       if (i == type.getAttributeCount())
/* 2116 */         throw new IllegalArgumentException("format error: expected " + text.length + " attributes, stopped after finding " + i + ". [" + data + "] split into " + Arrays.asList(text)); 
/* 2120 */       if (str.endsWith("\\")) {
/* 2121 */         item.append(str);
/* 2122 */         item.append("|");
/*      */       } else {
/* 2124 */         item.append(str);
/* 2125 */         text[i] = item.toString();
/* 2126 */         i++;
/* 2127 */         item = new StringBuilder();
/*      */       } 
/*      */     } 
/* 2130 */     if (i < type.getAttributeCount())
/* 2131 */       throw new IllegalArgumentException("createFeature format error: expected " + type.getAttributeCount() + " attributes, but only found " + i + ". [" + data + "] split into " + Arrays.asList(text)); 
/* 2135 */     return text;
/*      */   }
/*      */   
/*      */   private static Object createValue(AttributeDescriptor descriptor, String rawText) {
/* 2150 */     String stringValue = null;
/*      */     try {
/* 2153 */       stringValue = decodeEscapedCharacters(rawText);
/* 2154 */     } catch (RuntimeException huh) {
/* 2155 */       huh.printStackTrace();
/* 2156 */       stringValue = null;
/*      */     } 
/* 2159 */     if ("<null>".equals(stringValue))
/* 2160 */       stringValue = null; 
/* 2162 */     if (stringValue == null && 
/* 2163 */       descriptor.isNillable())
/* 2164 */       return null; 
/* 2168 */     Object value = Converters.convert(stringValue, descriptor.getType().getBinding());
/* 2170 */     if (descriptor.getType() instanceof GeometryType) {
/* 2172 */       CoordinateReferenceSystem crs = ((GeometryType)descriptor.getType()).getCoordinateReferenceSystem();
/* 2174 */       if (crs != null)
/* 2176 */         if (value != null && value instanceof Geometry)
/* 2177 */           ((Geometry)value).setUserData(crs);  
/*      */     } 
/* 2181 */     return value;
/*      */   }
/*      */   
/*      */   public static String encodeFeature(SimpleFeature feature) {
/* 2191 */     return encodeFeature(feature, true);
/*      */   }
/*      */   
/*      */   public static String encodeFeature(SimpleFeature feature, boolean includeFid) {
/* 2202 */     StringBuilder build = new StringBuilder();
/* 2203 */     if (includeFid) {
/* 2204 */       String fid = feature.getID();
/* 2205 */       if (feature.getUserData().containsKey(Hints.PROVIDED_FID))
/* 2206 */         fid = (String)feature.getUserData().get(Hints.PROVIDED_FID); 
/* 2208 */       build.append(fid);
/* 2209 */       build.append("=");
/*      */     } 
/* 2211 */     for (int i = 0; i < feature.getAttributeCount(); i++) {
/* 2212 */       Object attribute = feature.getAttribute(i);
/* 2213 */       if (i != 0)
/* 2214 */         build.append("|"); 
/* 2216 */       if (attribute == null) {
/* 2217 */         build.append("<null>");
/* 2218 */       } else if (attribute instanceof String) {
/* 2219 */         String txt = encodeString((String)attribute);
/* 2220 */         build.append(txt);
/* 2221 */       } else if (attribute instanceof Geometry) {
/* 2222 */         Geometry geometry = (Geometry)attribute;
/* 2223 */         String txt = geometry.toText();
/* 2225 */         txt = encodeString(txt);
/* 2226 */         build.append(txt);
/*      */       } else {
/* 2228 */         String txt = (String)Converters.convert(attribute, String.class);
/* 2229 */         if (txt == null)
/* 2230 */           txt = attribute.toString(); 
/* 2232 */         txt = encodeString(txt);
/* 2233 */         build.append(txt);
/*      */       } 
/*      */     } 
/* 2236 */     return build.toString();
/*      */   }
/*      */   
/*      */   public static SimpleFeature parse(SimpleFeatureType type, String fid, String[] text) throws IllegalAttributeException {
/* 2254 */     Object[] attributes = new Object[text.length];
/* 2256 */     for (int i = 0; i < text.length; i++) {
/* 2257 */       AttributeType attType = type.getDescriptor(i).getType();
/* 2258 */       attributes[i] = Converters.convert(text[i], attType.getBinding());
/*      */     } 
/* 2260 */     return SimpleFeatureBuilder.build(type, attributes, fid);
/*      */   }
/*      */   
/*      */   private static String decodeEscapedCharacters(String txt) {
/* 2275 */     txt = txt.replace("\\n", "\n");
/* 2276 */     txt = txt.replaceAll("\\r", "\r");
/* 2279 */     txt = txt.replace("\\|", "|");
/* 2282 */     return txt;
/*      */   }
/*      */   
/*      */   private static String encodeString(String txt) {
/* 2295 */     txt = txt.replace("|", "\\|");
/* 2298 */     txt = txt.replace("\n", "\\n");
/* 2299 */     txt = txt.replace("\r", "\\r");
/* 2301 */     return txt;
/*      */   }
/*      */   
/*      */   static Class<?> type(String typeName) throws ClassNotFoundException {
/* 2308 */     if (typeMap.containsKey(typeName))
/* 2309 */       return typeMap.get(typeName); 
/* 2311 */     return Class.forName(typeName);
/*      */   }
/*      */   
/*      */   static String typeMap(Class<?> type) {
/* 2318 */     if (typeEncode.containsKey(type))
/* 2319 */       return typeEncode.get(type); 
/* 2321 */     return type.getName();
/*      */   }
/*      */   
/*      */   public static Comparator<SimpleFeature> sortComparator(SortBy sortBy) {
/* 2338 */     if (sortBy == null)
/* 2339 */       sortBy = SortBy.NATURAL_ORDER; 
/* 2341 */     if (sortBy == SortBy.NATURAL_ORDER)
/* 2342 */       return new Comparator<SimpleFeature>() {
/*      */           public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 2344 */             return f1.getID().compareTo(f2.getID());
/*      */           }
/*      */         }; 
/* 2347 */     if (sortBy == SortBy.REVERSE_ORDER)
/* 2348 */       return new Comparator<SimpleFeature>() {
/*      */           public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 2350 */             int compare = f1.getID().compareTo(f2.getID());
/* 2351 */             return -compare;
/*      */           }
/*      */         }; 
/* 2355 */     final PropertyName PROPERTY = sortBy.getPropertyName();
/* 2356 */     return new Comparator<SimpleFeature>() {
/*      */         public int compare(SimpleFeature f1, SimpleFeature f2) {
/* 2359 */           Object value1 = PROPERTY.evaluate(f1, Comparable.class);
/* 2360 */           Object value2 = PROPERTY.evaluate(f2, Comparable.class);
/* 2361 */           if (value1 == null || value2 == null)
/* 2362 */             return 0; 
/* 2364 */           if (value1 instanceof Comparable && value1.getClass().isInstance(value2))
/* 2365 */             return ((Comparable<Object>)value1).compareTo(value2); 
/* 2367 */           return 0;
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public static Query mixQueries(Query firstQuery, Query secondQuery, String handle) {
/*      */     String version;
/*      */     And and;
/* 2419 */     if (firstQuery == null && secondQuery == null)
/* 2421 */       return Query.ALL; 
/* 2423 */     if (firstQuery == null || firstQuery.equals(Query.ALL))
/* 2424 */       return secondQuery; 
/* 2425 */     if (secondQuery == null || secondQuery.equals(Query.ALL))
/* 2426 */       return firstQuery; 
/* 2428 */     if (firstQuery.getTypeName() != null && secondQuery.getTypeName() != null && 
/* 2429 */       !firstQuery.getTypeName().equals(secondQuery.getTypeName())) {
/* 2430 */       String msg = "Type names do not match: " + firstQuery.getTypeName() + " != " + secondQuery.getTypeName();
/* 2432 */       throw new IllegalArgumentException(msg);
/*      */     } 
/* 2438 */     if (firstQuery.getVersion() != null) {
/* 2439 */       if (secondQuery.getVersion() != null && !secondQuery.getVersion().equals(firstQuery.getVersion()))
/* 2441 */         throw new IllegalArgumentException("First and second query refer different versions"); 
/* 2443 */       version = firstQuery.getVersion();
/*      */     } else {
/* 2445 */       version = secondQuery.getVersion();
/*      */     } 
/* 2450 */     int maxFeatures = Math.min(firstQuery.getMaxFeatures(), secondQuery.getMaxFeatures());
/* 2453 */     List<PropertyName> propNames = joinAttributes(firstQuery.getProperties(), secondQuery.getProperties());
/* 2457 */     Filter filter = firstQuery.getFilter();
/* 2458 */     Filter filter2 = secondQuery.getFilter();
/* 2460 */     if (filter == null || filter.equals(Filter.INCLUDE)) {
/* 2461 */       filter = filter2;
/* 2462 */     } else if (filter2 != null && !filter2.equals(Filter.INCLUDE)) {
/* 2463 */       and = ff.and(filter, filter2);
/*      */     } 
/* 2465 */     Integer start = Integer.valueOf(0);
/* 2466 */     if (firstQuery.getStartIndex() != null)
/* 2467 */       start = firstQuery.getStartIndex(); 
/* 2469 */     if (secondQuery.getStartIndex() != null)
/* 2470 */       start = Integer.valueOf(start.intValue() + secondQuery.getStartIndex().intValue()); 
/* 2473 */     Hints hints = new Hints();
/* 2474 */     if (firstQuery.getHints() != null)
/* 2475 */       hints.putAll((Map)firstQuery.getHints()); 
/* 2477 */     if (secondQuery.getHints() != null)
/* 2478 */       hints.putAll((Map)secondQuery.getHints()); 
/* 2481 */     String typeName = (firstQuery.getTypeName() != null) ? firstQuery.getTypeName() : secondQuery.getTypeName();
/* 2484 */     Query mixed = new Query(typeName, (Filter)and, maxFeatures, propNames, handle);
/* 2485 */     mixed.setVersion(version);
/* 2486 */     mixed.setHints(hints);
/* 2487 */     if (start.intValue() != 0)
/* 2488 */       mixed.setStartIndex(start); 
/* 2490 */     return mixed;
/*      */   }
/*      */   
/*      */   public static Query resolvePropertyNames(Query query, SimpleFeatureType schema) {
/* 2502 */     Filter resolved = resolvePropertyNames(query.getFilter(), schema);
/* 2503 */     if (resolved == query.getFilter())
/* 2504 */       return query; 
/* 2506 */     Query newQuery = new Query(query);
/* 2507 */     newQuery.setFilter(resolved);
/* 2508 */     return newQuery;
/*      */   }
/*      */   
/*      */   public static Filter resolvePropertyNames(Filter filter, SimpleFeatureType schema) {
/* 2513 */     if (filter == null || filter == Filter.INCLUDE || filter == Filter.EXCLUDE)
/* 2514 */       return filter; 
/* 2516 */     return (Filter)filter.accept((FilterVisitor)new PropertyNameResolvingVisitor(schema), null);
/*      */   }
/*      */   
/*      */   private static List<PropertyName> joinAttributes(List<PropertyName> atts1, List<PropertyName> atts2) {
/* 2537 */     if (atts1 == null && atts2 == null)
/* 2538 */       return null; 
/* 2541 */     List<PropertyName> atts = new LinkedList<PropertyName>();
/* 2543 */     if (atts1 != null)
/* 2544 */       atts.addAll(atts1); 
/* 2547 */     if (atts2 != null)
/* 2548 */       for (int i = 0; i < atts2.size(); i++) {
/* 2549 */         if (!atts.contains(atts2.get(i)))
/* 2550 */           atts.add(atts2.get(i)); 
/*      */       }  
/* 2554 */     return atts;
/*      */   }
/*      */   
/*      */   public static List<PropertyName> addMandatoryProperties(SimpleFeatureType type, List<PropertyName> oldProps) {
/* 2570 */     Iterator<PropertyDescriptor> ii = type.getDescriptors().iterator();
/* 2572 */     List<PropertyName> properties = new ArrayList<PropertyName>();
/* 2574 */     while (ii.hasNext()) {
/* 2576 */       PropertyDescriptor descr = ii.next();
/* 2577 */       PropertyName propName = ff.property(descr.getName());
/* 2579 */       if (oldProps != null && oldProps.contains(propName)) {
/* 2580 */         properties.add(propName);
/*      */         continue;
/*      */       } 
/* 2581 */       if (descr.getMinOccurs() > 0 && descr.getMaxOccurs() != 0)
/* 2583 */         properties.add(propName); 
/*      */     } 
/* 2587 */     return properties;
/*      */   }
/*      */   
/*      */   static AttributeDescriptor createAttribute(String typeSpec) throws SchemaException {
/*      */     String name, type;
/* 2603 */     int split = typeSpec.indexOf(":");
/* 2607 */     String hint = null;
/* 2609 */     if (split == -1) {
/* 2610 */       name = typeSpec;
/* 2611 */       type = "String";
/*      */     } else {
/* 2613 */       name = typeSpec.substring(0, split);
/* 2615 */       int split2 = typeSpec.indexOf(":", split + 1);
/* 2617 */       if (split2 == -1) {
/* 2618 */         type = typeSpec.substring(split + 1);
/*      */       } else {
/* 2620 */         type = typeSpec.substring(split + 1, split2);
/* 2621 */         hint = typeSpec.substring(split2 + 1);
/*      */       } 
/*      */     } 
/*      */     try {
/* 2626 */       boolean nillable = true;
/* 2627 */       CoordinateReferenceSystem crs = null;
/* 2629 */       if (hint != null) {
/* 2630 */         StringTokenizer st = new StringTokenizer(hint, ";");
/* 2631 */         while (st.hasMoreTokens()) {
/* 2632 */           String h = st.nextToken();
/* 2633 */           h = h.trim();
/* 2638 */           if (h.equals("nillable"))
/* 2639 */             nillable = true; 
/* 2642 */           if (h.startsWith("srid=")) {
/* 2643 */             String srid = h.split("=")[1];
/* 2644 */             Integer.parseInt(srid);
/*      */             try {
/* 2646 */               crs = CRS.decode("EPSG:" + srid);
/* 2647 */             } catch (Exception e) {
/* 2648 */               String msg = "Error decoding srs: " + srid;
/* 2649 */               throw new SchemaException(msg, e);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 2655 */       Class<?> clazz = type(type);
/* 2656 */       if (Geometry.class.isAssignableFrom(clazz)) {
/* 2657 */         GeometryTypeImpl geometryTypeImpl = new GeometryTypeImpl((Name)new NameImpl(name), clazz, crs, false, false, Collections.EMPTY_LIST, null, null);
/* 2659 */         return (AttributeDescriptor)new GeometryDescriptorImpl((GeometryType)geometryTypeImpl, (Name)new NameImpl(name), 0, 1, nillable, null);
/*      */       } 
/* 2661 */       AttributeTypeImpl attributeTypeImpl = new AttributeTypeImpl((Name)new NameImpl(name), clazz, false, false, Collections.EMPTY_LIST, null, null);
/* 2663 */       return (AttributeDescriptor)new AttributeDescriptorImpl((AttributeType)attributeTypeImpl, (Name)new NameImpl(name), 0, 1, nillable, null);
/* 2665 */     } catch (ClassNotFoundException e) {
/* 2666 */       throw new SchemaException("Could not type " + name + " as:" + type, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int count(FeatureIterator<?> iterator) {
/* 2678 */     int count = 0;
/* 2679 */     if (iterator != null)
/*      */       try {
/* 2681 */         while (iterator.hasNext()) {
/* 2683 */           Feature feature = iterator.next();
/* 2684 */           count++;
/*      */         } 
/* 2686 */         return count;
/*      */       } finally {
/* 2689 */         iterator.close();
/*      */       }  
/* 2692 */     return count;
/*      */   }
/*      */   
/*      */   public static int count(FeatureCollection<? extends FeatureType, ? extends Feature> collection) {
/* 2704 */     int count = 0;
/* 2705 */     FeatureIterator<? extends Feature> i = collection.features();
/*      */     try {
/* 2707 */       while (i.hasNext()) {
/* 2709 */         Feature feature = i.next();
/* 2710 */         count++;
/*      */       } 
/* 2712 */       return count;
/*      */     } finally {
/* 2715 */       if (i != null)
/* 2716 */         i.close(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope bounds(FeatureIterator<?> iterator) {
/* 2729 */     if (iterator == null)
/* 2730 */       return null; 
/*      */     try {
/* 2733 */       ReferencedEnvelope bounds = null;
/* 2734 */       while (iterator.hasNext()) {
/* 2735 */         Feature feature = iterator.next();
/* 2736 */         ReferencedEnvelope featureEnvelope = null;
/* 2737 */         if (feature != null && feature.getBounds() != null)
/* 2738 */           featureEnvelope = ReferencedEnvelope.reference(feature.getBounds()); 
/* 2741 */         if (featureEnvelope != null) {
/* 2742 */           if (bounds == null) {
/* 2743 */             bounds = new ReferencedEnvelope(featureEnvelope);
/*      */             continue;
/*      */           } 
/* 2745 */           bounds.expandToInclude((Envelope)featureEnvelope);
/*      */         } 
/*      */       } 
/* 2749 */       return bounds;
/*      */     } finally {
/* 2751 */       iterator.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope bounds(FeatureCollection<? extends FeatureType, ? extends Feature> collection) {
/* 2765 */     FeatureIterator<? extends Feature> i = collection.features();
/*      */     try {
/* 2768 */       CoordinateReferenceSystem crs = collection.getSchema().getCoordinateReferenceSystem();
/* 2769 */       ReferencedEnvelope bounds = new ReferencedEnvelope(crs);
/* 2771 */       while (i.hasNext()) {
/* 2772 */         Feature feature = i.next();
/* 2773 */         if (feature == null)
/*      */           continue; 
/* 2775 */         BoundingBox geomBounds = feature.getBounds();
/* 2779 */         if (geomBounds != null && !geomBounds.isEmpty())
/* 2780 */           bounds.include(geomBounds); 
/*      */       } 
/* 2783 */       return bounds;
/*      */     } finally {
/* 2786 */       if (i != null)
/* 2787 */         i.close(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void visit(FeatureCollection<?, ?> collection, FeatureVisitor visitor, ProgressListener progress) throws IOException {
/*      */     NullProgressListener nullProgressListener;
/* 2803 */     FeatureIterator<?> iterator = null;
/* 2804 */     float size = (progress != null) ? collection.size() : 0.0F;
/* 2805 */     if (progress == null)
/* 2806 */       nullProgressListener = new NullProgressListener(); 
/*      */     try {
/* 2809 */       float position = 0.0F;
/* 2810 */       nullProgressListener.started();
/* 2811 */       iterator = collection.features();
/* 2812 */       while (!nullProgressListener.isCanceled() && iterator.hasNext()) {
/* 2813 */         Feature feature = null;
/*      */         try {
/* 2815 */           feature = iterator.next();
/* 2816 */           visitor.visit(feature);
/* 2817 */           if (size > 0.0F)
/* 2818 */             nullProgressListener.progress(position++ / size); 
/* 2820 */         } catch (Exception erp) {
/* 2821 */           nullProgressListener.exceptionOccurred(erp);
/* 2822 */           String fid = (feature == null) ? "feature" : feature.getIdentifier().toString();
/* 2823 */           throw new IOException("Problem with " + collection.getID() + " visiting " + fid + ":" + erp, erp);
/*      */         } 
/*      */       } 
/*      */     } finally {
/* 2828 */       nullProgressListener.complete();
/* 2829 */       if (iterator != null)
/* 2830 */         iterator.close(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static URL changeUrlExt(URL url, String postfix) throws IllegalArgumentException {
/* 2850 */     String a = url.toExternalForm();
/* 2851 */     int lastDotPos = a.lastIndexOf('.');
/* 2852 */     if (lastDotPos >= 0)
/* 2853 */       a = a.substring(0, lastDotPos); 
/* 2854 */     a = a + "." + postfix;
/*      */     try {
/* 2856 */       return new URL(a);
/* 2857 */     } catch (MalformedURLException e) {
/* 2858 */       throw new IllegalArgumentException("can't create a new URL for " + url + " with new extension " + postfix, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static URL getParentUrl(URL url) throws MalformedURLException {
/* 2870 */     String a = url.toExternalForm();
/* 2871 */     int lastDotPos = a.lastIndexOf('/');
/* 2872 */     if (lastDotPos >= 0)
/* 2873 */       a = a.substring(0, lastDotPos); 
/* 2878 */     if (a.endsWith("!"))
/* 2879 */       a = a + "/"; 
/* 2881 */     return new URL(a);
/*      */   }
/*      */   
/*      */   public static URL extendURL(URL base, String extension) throws MalformedURLException {
/* 2897 */     if (base == null)
/* 2898 */       throw new NullPointerException(Errors.format(143, "base")); 
/* 2899 */     if (extension == null)
/* 2900 */       throw new NullPointerException(Errors.format(143, "extension")); 
/* 2901 */     String a = base.toExternalForm();
/* 2902 */     if (!a.endsWith("/"))
/* 2903 */       a = a + "/"; 
/* 2904 */     a = a + extension;
/* 2905 */     return new URL(a);
/*      */   }
/*      */   
/*      */   public static boolean checkFileReadable(File file, Logger logger) {
/* 2921 */     if (logger != null && logger.isLoggable(Level.FINE)) {
/* 2922 */       StringBuilder builder = (new StringBuilder("Checking file:")).append(file.getAbsolutePath()).append("\n").append("canRead:").append(file.canRead()).append("\n").append("isHidden:").append(file.isHidden()).append("\n").append("isFile").append(file.isFile()).append("\n").append("canWrite").append(file.canWrite()).append("\n");
/* 2927 */       logger.fine(builder.toString());
/*      */     } 
/* 2929 */     if (!file.exists() || !file.canRead() || !file.isFile())
/* 2930 */       return false; 
/* 2931 */     return true;
/*      */   }
/*      */   
/*      */   public static File checkDirectory(File file) throws IllegalArgumentException {
/* 2947 */     String directoryPath = file.getPath();
/* 2948 */     File inDir = file;
/* 2949 */     if (!inDir.isDirectory())
/* 2950 */       throw new IllegalArgumentException("Not a directory: " + directoryPath); 
/* 2952 */     if (!inDir.canRead())
/* 2953 */       throw new IllegalArgumentException("Not a writable directory: " + directoryPath); 
/*      */     try {
/* 2956 */       directoryPath = inDir.getCanonicalPath();
/* 2957 */     } catch (IOException e) {
/* 2958 */       throw new IllegalArgumentException(e);
/*      */     } 
/* 2966 */     inDir = new File(directoryPath);
/* 2967 */     if (!inDir.isDirectory())
/* 2968 */       throw new IllegalArgumentException("Not a directory: " + directoryPath); 
/* 2970 */     if (!inDir.canRead())
/* 2971 */       throw new IllegalArgumentException("Not a writable directory: " + directoryPath); 
/* 2973 */     return new File(directoryPath);
/*      */   }
/*      */   
/*      */   public static FilenameFilter excludeFilters(final FilenameFilter inputFilter, FilenameFilter... filters) {
/* 2989 */     return new FilenameFilter() {
/*      */         public boolean accept(File dir, String name) {
/* 2991 */           if (inputFilter.accept(dir, name)) {
/* 2992 */             for (FilenameFilter exclude : filters) {
/* 2993 */               if (exclude.accept(dir, name))
/* 2994 */                 return false; 
/*      */             } 
/* 2997 */             return true;
/*      */           } 
/* 2999 */           return false;
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public static FilenameFilter includeFilters(final FilenameFilter inputFilter, FilenameFilter... filters) {
/* 3017 */     return new FilenameFilter() {
/*      */         public boolean accept(File dir, String name) {
/* 3019 */           if (inputFilter.accept(dir, name))
/* 3020 */             return true; 
/* 3022 */           for (FilenameFilter include : filters) {
/* 3023 */             if (include.accept(dir, name))
/* 3024 */               return true; 
/*      */           } 
/* 3027 */           return false;
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public static Feature templateFeature(FeatureType schema) {
/* 3040 */     FeatureFactory ff = CommonFactoryFinder.getFeatureFactory(null);
/* 3041 */     Collection<Property> value = new ArrayList<Property>();
/* 3043 */     for (PropertyDescriptor pd : schema.getDescriptors()) {
/* 3044 */       if (pd instanceof AttributeDescriptor) {
/* 3045 */         if (pd instanceof GeometryDescriptor) {
/* 3046 */           value.add(new GeometryAttributeImpl(((AttributeDescriptor)pd).getDefaultValue(), (GeometryDescriptor)pd, null));
/*      */           continue;
/*      */         } 
/* 3048 */         value.add(new AttributeImpl(((AttributeDescriptor)pd).getDefaultValue(), (AttributeDescriptor)pd, null));
/*      */       } 
/*      */     } 
/* 3053 */     return ff.createFeature(value, schema, SimpleFeatureBuilder.createDefaultFeatureId());
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */