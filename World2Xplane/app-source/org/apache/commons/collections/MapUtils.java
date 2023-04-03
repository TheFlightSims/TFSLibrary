/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.text.ParseException;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.SortedMap;
/*      */ import java.util.TreeMap;
/*      */ import org.apache.commons.collections.map.FixedSizeMap;
/*      */ import org.apache.commons.collections.map.FixedSizeSortedMap;
/*      */ import org.apache.commons.collections.map.LazyMap;
/*      */ import org.apache.commons.collections.map.LazySortedMap;
/*      */ import org.apache.commons.collections.map.ListOrderedMap;
/*      */ import org.apache.commons.collections.map.MultiValueMap;
/*      */ import org.apache.commons.collections.map.PredicatedMap;
/*      */ import org.apache.commons.collections.map.PredicatedSortedMap;
/*      */ import org.apache.commons.collections.map.TransformedMap;
/*      */ import org.apache.commons.collections.map.TransformedSortedMap;
/*      */ import org.apache.commons.collections.map.TypedMap;
/*      */ import org.apache.commons.collections.map.TypedSortedMap;
/*      */ import org.apache.commons.collections.map.UnmodifiableMap;
/*      */ import org.apache.commons.collections.map.UnmodifiableSortedMap;
/*      */ 
/*      */ public class MapUtils {
/*   96 */   public static final Map EMPTY_MAP = UnmodifiableMap.decorate(new HashMap(1));
/*      */   
/*  101 */   public static final SortedMap EMPTY_SORTED_MAP = UnmodifiableSortedMap.decorate(new TreeMap());
/*      */   
/*      */   private static final String INDENT_STRING = "    ";
/*      */   
/*      */   public static Object getObject(Map map, Object key) {
/*  123 */     if (map != null)
/*  124 */       return map.get(key); 
/*  126 */     return null;
/*      */   }
/*      */   
/*      */   public static String getString(Map map, Object key) {
/*  139 */     if (map != null) {
/*  140 */       Object answer = map.get(key);
/*  141 */       if (answer != null)
/*  142 */         return answer.toString(); 
/*      */     } 
/*  145 */     return null;
/*      */   }
/*      */   
/*      */   public static Boolean getBoolean(Map map, Object key) {
/*  163 */     if (map != null) {
/*  164 */       Object answer = map.get(key);
/*  165 */       if (answer != null) {
/*  166 */         if (answer instanceof Boolean)
/*  167 */           return (Boolean)answer; 
/*  169 */         if (answer instanceof String)
/*  170 */           return new Boolean((String)answer); 
/*  172 */         if (answer instanceof Number) {
/*  173 */           Number n = (Number)answer;
/*  174 */           return (n.intValue() != 0) ? Boolean.TRUE : Boolean.FALSE;
/*      */         } 
/*      */       } 
/*      */     } 
/*  178 */     return null;
/*      */   }
/*      */   
/*      */   public static Number getNumber(Map map, Object key) {
/*  195 */     if (map != null) {
/*  196 */       Object answer = map.get(key);
/*  197 */       if (answer != null) {
/*  198 */         if (answer instanceof Number)
/*  199 */           return (Number)answer; 
/*  201 */         if (answer instanceof String)
/*      */           try {
/*  203 */             String text = (String)answer;
/*  204 */             return NumberFormat.getInstance().parse(text);
/*  206 */           } catch (ParseException e) {
/*  207 */             logInfo(e);
/*      */           }  
/*      */       } 
/*      */     } 
/*  212 */     return null;
/*      */   }
/*      */   
/*      */   public static Byte getByte(Map map, Object key) {
/*  225 */     Number answer = getNumber(map, key);
/*  226 */     if (answer == null)
/*  227 */       return null; 
/*  228 */     if (answer instanceof Byte)
/*  229 */       return (Byte)answer; 
/*  231 */     return new Byte(answer.byteValue());
/*      */   }
/*      */   
/*      */   public static Short getShort(Map map, Object key) {
/*  244 */     Number answer = getNumber(map, key);
/*  245 */     if (answer == null)
/*  246 */       return null; 
/*  247 */     if (answer instanceof Short)
/*  248 */       return (Short)answer; 
/*  250 */     return new Short(answer.shortValue());
/*      */   }
/*      */   
/*      */   public static Integer getInteger(Map map, Object key) {
/*  263 */     Number answer = getNumber(map, key);
/*  264 */     if (answer == null)
/*  265 */       return null; 
/*  266 */     if (answer instanceof Integer)
/*  267 */       return (Integer)answer; 
/*  269 */     return new Integer(answer.intValue());
/*      */   }
/*      */   
/*      */   public static Long getLong(Map map, Object key) {
/*  282 */     Number answer = getNumber(map, key);
/*  283 */     if (answer == null)
/*  284 */       return null; 
/*  285 */     if (answer instanceof Long)
/*  286 */       return (Long)answer; 
/*  288 */     return new Long(answer.longValue());
/*      */   }
/*      */   
/*      */   public static Float getFloat(Map map, Object key) {
/*  301 */     Number answer = getNumber(map, key);
/*  302 */     if (answer == null)
/*  303 */       return null; 
/*  304 */     if (answer instanceof Float)
/*  305 */       return (Float)answer; 
/*  307 */     return new Float(answer.floatValue());
/*      */   }
/*      */   
/*      */   public static Double getDouble(Map map, Object key) {
/*  320 */     Number answer = getNumber(map, key);
/*  321 */     if (answer == null)
/*  322 */       return null; 
/*  323 */     if (answer instanceof Double)
/*  324 */       return (Double)answer; 
/*  326 */     return new Double(answer.doubleValue());
/*      */   }
/*      */   
/*      */   public static Map getMap(Map map, Object key) {
/*  340 */     if (map != null) {
/*  341 */       Object answer = map.get(key);
/*  342 */       if (answer != null && answer instanceof Map)
/*  343 */         return (Map)answer; 
/*      */     } 
/*  346 */     return null;
/*      */   }
/*      */   
/*      */   public static Object getObject(Map map, Object key, Object defaultValue) {
/*  362 */     if (map != null) {
/*  363 */       Object answer = map.get(key);
/*  364 */       if (answer != null)
/*  365 */         return answer; 
/*      */     } 
/*  368 */     return defaultValue;
/*      */   }
/*      */   
/*      */   public static String getString(Map map, Object key, String defaultValue) {
/*  384 */     String answer = getString(map, key);
/*  385 */     if (answer == null)
/*  386 */       answer = defaultValue; 
/*  388 */     return answer;
/*      */   }
/*      */   
/*      */   public static Boolean getBoolean(Map map, Object key, Boolean defaultValue) {
/*  404 */     Boolean answer = getBoolean(map, key);
/*  405 */     if (answer == null)
/*  406 */       answer = defaultValue; 
/*  408 */     return answer;
/*      */   }
/*      */   
/*      */   public static Number getNumber(Map map, Object key, Number defaultValue) {
/*  424 */     Number answer = getNumber(map, key);
/*  425 */     if (answer == null)
/*  426 */       answer = defaultValue; 
/*  428 */     return answer;
/*      */   }
/*      */   
/*      */   public static Byte getByte(Map map, Object key, Byte defaultValue) {
/*  444 */     Byte answer = getByte(map, key);
/*  445 */     if (answer == null)
/*  446 */       answer = defaultValue; 
/*  448 */     return answer;
/*      */   }
/*      */   
/*      */   public static Short getShort(Map map, Object key, Short defaultValue) {
/*  464 */     Short answer = getShort(map, key);
/*  465 */     if (answer == null)
/*  466 */       answer = defaultValue; 
/*  468 */     return answer;
/*      */   }
/*      */   
/*      */   public static Integer getInteger(Map map, Object key, Integer defaultValue) {
/*  484 */     Integer answer = getInteger(map, key);
/*  485 */     if (answer == null)
/*  486 */       answer = defaultValue; 
/*  488 */     return answer;
/*      */   }
/*      */   
/*      */   public static Long getLong(Map map, Object key, Long defaultValue) {
/*  504 */     Long answer = getLong(map, key);
/*  505 */     if (answer == null)
/*  506 */       answer = defaultValue; 
/*  508 */     return answer;
/*      */   }
/*      */   
/*      */   public static Float getFloat(Map map, Object key, Float defaultValue) {
/*  524 */     Float answer = getFloat(map, key);
/*  525 */     if (answer == null)
/*  526 */       answer = defaultValue; 
/*  528 */     return answer;
/*      */   }
/*      */   
/*      */   public static Double getDouble(Map map, Object key, Double defaultValue) {
/*  544 */     Double answer = getDouble(map, key);
/*  545 */     if (answer == null)
/*  546 */       answer = defaultValue; 
/*  548 */     return answer;
/*      */   }
/*      */   
/*      */   public static Map getMap(Map map, Object key, Map defaultValue) {
/*  564 */     Map answer = getMap(map, key);
/*  565 */     if (answer == null)
/*  566 */       answer = defaultValue; 
/*  568 */     return answer;
/*      */   }
/*      */   
/*      */   public static boolean getBooleanValue(Map map, Object key) {
/*  589 */     Boolean booleanObject = getBoolean(map, key);
/*  590 */     if (booleanObject == null)
/*  591 */       return false; 
/*  593 */     return booleanObject.booleanValue();
/*      */   }
/*      */   
/*      */   public static byte getByteValue(Map map, Object key) {
/*  606 */     Byte byteObject = getByte(map, key);
/*  607 */     if (byteObject == null)
/*  608 */       return 0; 
/*  610 */     return byteObject.byteValue();
/*      */   }
/*      */   
/*      */   public static short getShortValue(Map map, Object key) {
/*  623 */     Short shortObject = getShort(map, key);
/*  624 */     if (shortObject == null)
/*  625 */       return 0; 
/*  627 */     return shortObject.shortValue();
/*      */   }
/*      */   
/*      */   public static int getIntValue(Map map, Object key) {
/*  640 */     Integer integerObject = getInteger(map, key);
/*  641 */     if (integerObject == null)
/*  642 */       return 0; 
/*  644 */     return integerObject.intValue();
/*      */   }
/*      */   
/*      */   public static long getLongValue(Map map, Object key) {
/*  657 */     Long longObject = getLong(map, key);
/*  658 */     if (longObject == null)
/*  659 */       return 0L; 
/*  661 */     return longObject.longValue();
/*      */   }
/*      */   
/*      */   public static float getFloatValue(Map map, Object key) {
/*  674 */     Float floatObject = getFloat(map, key);
/*  675 */     if (floatObject == null)
/*  676 */       return 0.0F; 
/*  678 */     return floatObject.floatValue();
/*      */   }
/*      */   
/*      */   public static double getDoubleValue(Map map, Object key) {
/*  691 */     Double doubleObject = getDouble(map, key);
/*  692 */     if (doubleObject == null)
/*  693 */       return 0.0D; 
/*  695 */     return doubleObject.doubleValue();
/*      */   }
/*      */   
/*      */   public static boolean getBooleanValue(Map map, Object key, boolean defaultValue) {
/*  718 */     Boolean booleanObject = getBoolean(map, key);
/*  719 */     if (booleanObject == null)
/*  720 */       return defaultValue; 
/*  722 */     return booleanObject.booleanValue();
/*      */   }
/*      */   
/*      */   public static byte getByteValue(Map map, Object key, byte defaultValue) {
/*  738 */     Byte byteObject = getByte(map, key);
/*  739 */     if (byteObject == null)
/*  740 */       return defaultValue; 
/*  742 */     return byteObject.byteValue();
/*      */   }
/*      */   
/*      */   public static short getShortValue(Map map, Object key, short defaultValue) {
/*  758 */     Short shortObject = getShort(map, key);
/*  759 */     if (shortObject == null)
/*  760 */       return defaultValue; 
/*  762 */     return shortObject.shortValue();
/*      */   }
/*      */   
/*      */   public static int getIntValue(Map map, Object key, int defaultValue) {
/*  778 */     Integer integerObject = getInteger(map, key);
/*  779 */     if (integerObject == null)
/*  780 */       return defaultValue; 
/*  782 */     return integerObject.intValue();
/*      */   }
/*      */   
/*      */   public static long getLongValue(Map map, Object key, long defaultValue) {
/*  798 */     Long longObject = getLong(map, key);
/*  799 */     if (longObject == null)
/*  800 */       return defaultValue; 
/*  802 */     return longObject.longValue();
/*      */   }
/*      */   
/*      */   public static float getFloatValue(Map map, Object key, float defaultValue) {
/*  818 */     Float floatObject = getFloat(map, key);
/*  819 */     if (floatObject == null)
/*  820 */       return defaultValue; 
/*  822 */     return floatObject.floatValue();
/*      */   }
/*      */   
/*      */   public static double getDoubleValue(Map map, Object key, double defaultValue) {
/*  838 */     Double doubleObject = getDouble(map, key);
/*  839 */     if (doubleObject == null)
/*  840 */       return defaultValue; 
/*  842 */     return doubleObject.doubleValue();
/*      */   }
/*      */   
/*      */   public static Properties toProperties(Map map) {
/*  855 */     Properties answer = new Properties();
/*  856 */     if (map != null)
/*  857 */       for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
/*  858 */         Map.Entry entry = iter.next();
/*  859 */         Object key = entry.getKey();
/*  860 */         Object value = entry.getValue();
/*  861 */         answer.put(key, value);
/*      */       }  
/*  864 */     return answer;
/*      */   }
/*      */   
/*      */   public static Map toMap(ResourceBundle resourceBundle) {
/*  875 */     Enumeration enumeration = resourceBundle.getKeys();
/*  876 */     Map map = new HashMap();
/*  878 */     while (enumeration.hasMoreElements()) {
/*  879 */       String key = enumeration.nextElement();
/*  880 */       Object value = resourceBundle.getObject(key);
/*  881 */       map.put(key, value);
/*      */     } 
/*  884 */     return map;
/*      */   }
/*      */   
/*      */   public static void verbosePrint(PrintStream out, Object label, Map map) {
/*  912 */     verbosePrintInternal(out, label, map, new ArrayStack(), false);
/*      */   }
/*      */   
/*      */   public static void debugPrint(PrintStream out, Object label, Map map) {
/*  938 */     verbosePrintInternal(out, label, map, new ArrayStack(), true);
/*      */   }
/*      */   
/*      */   protected static void logInfo(Exception ex) {
/*  951 */     System.out.println("INFO: Exception: " + ex);
/*      */   }
/*      */   
/*      */   private static void verbosePrintInternal(PrintStream out, Object label, Map map, ArrayStack lineage, boolean debug) {
/*  984 */     printIndent(out, lineage.size());
/*  986 */     if (map == null) {
/*  987 */       if (label != null) {
/*  988 */         out.print(label);
/*  989 */         out.print(" = ");
/*      */       } 
/*  991 */       out.println("null");
/*      */       return;
/*      */     } 
/*  994 */     if (label != null) {
/*  995 */       out.print(label);
/*  996 */       out.println(" = ");
/*      */     } 
/*  999 */     printIndent(out, lineage.size());
/* 1000 */     out.println("{");
/* 1002 */     lineage.push(map);
/* 1004 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 1005 */       Map.Entry entry = it.next();
/* 1006 */       Object childKey = entry.getKey();
/* 1007 */       Object childValue = entry.getValue();
/* 1008 */       if (childValue instanceof Map && !lineage.contains(childValue)) {
/* 1009 */         verbosePrintInternal(out, (childKey == null) ? "null" : childKey, (Map)childValue, lineage, debug);
/*      */         continue;
/*      */       } 
/* 1016 */       printIndent(out, lineage.size());
/* 1017 */       out.print(childKey);
/* 1018 */       out.print(" = ");
/* 1020 */       int lineageIndex = lineage.indexOf(childValue);
/* 1021 */       if (lineageIndex == -1) {
/* 1022 */         out.print(childValue);
/* 1023 */       } else if (lineage.size() - 1 == lineageIndex) {
/* 1024 */         out.print("(this Map)");
/*      */       } else {
/* 1026 */         out.print("(ancestor[" + (lineage.size() - 1 - lineageIndex - 1) + "] Map)");
/*      */       } 
/* 1032 */       if (debug && childValue != null) {
/* 1033 */         out.print(' ');
/* 1034 */         out.println(childValue.getClass().getName());
/*      */         continue;
/*      */       } 
/* 1036 */       out.println();
/*      */     } 
/* 1041 */     lineage.pop();
/* 1043 */     printIndent(out, lineage.size());
/* 1044 */     out.println(debug ? ("} " + map.getClass().getName()) : "}");
/*      */   }
/*      */   
/*      */   private static void printIndent(PrintStream out, int indent) {
/* 1053 */     for (int i = 0; i < indent; i++)
/* 1054 */       out.print("    "); 
/*      */   }
/*      */   
/*      */   public static Map invertMap(Map map) {
/* 1074 */     Map out = new HashMap(map.size());
/* 1075 */     for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
/* 1076 */       Map.Entry entry = it.next();
/* 1077 */       out.put(entry.getValue(), entry.getKey());
/*      */     } 
/* 1079 */     return out;
/*      */   }
/*      */   
/*      */   public static void safeAddToMap(Map map, Object key, Object value) throws NullPointerException {
/* 1101 */     if (value == null) {
/* 1102 */       map.put(key, "");
/*      */     } else {
/* 1104 */       map.put(key, value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Map putAll(Map map, Object[] array) {
/* 1157 */     map.size();
/* 1158 */     if (array == null || array.length == 0)
/* 1159 */       return map; 
/* 1161 */     Object obj = array[0];
/* 1162 */     if (obj instanceof Map.Entry) {
/* 1163 */       for (int i = 0; i < array.length; i++) {
/* 1164 */         Map.Entry entry = (Map.Entry)array[i];
/* 1165 */         map.put(entry.getKey(), entry.getValue());
/*      */       } 
/* 1167 */     } else if (obj instanceof KeyValue) {
/* 1168 */       for (int i = 0; i < array.length; i++) {
/* 1169 */         KeyValue keyval = (KeyValue)array[i];
/* 1170 */         map.put(keyval.getKey(), keyval.getValue());
/*      */       } 
/* 1172 */     } else if (obj instanceof Object[]) {
/* 1173 */       for (int i = 0; i < array.length; i++) {
/* 1174 */         Object[] sub = (Object[])array[i];
/* 1175 */         if (sub == null || sub.length < 2)
/* 1176 */           throw new IllegalArgumentException("Invalid array element: " + i); 
/* 1178 */         map.put(sub[0], sub[1]);
/*      */       } 
/*      */     } else {
/* 1181 */       for (int i = 0; i < array.length - 1;)
/* 1182 */         map.put(array[i++], array[i++]); 
/*      */     } 
/* 1185 */     return map;
/*      */   }
/*      */   
/*      */   public static boolean isEmpty(Map map) {
/* 1199 */     return (map == null || map.isEmpty());
/*      */   }
/*      */   
/*      */   public static boolean isNotEmpty(Map map) {
/* 1212 */     return !isEmpty(map);
/*      */   }
/*      */   
/*      */   public static Map synchronizedMap(Map map) {
/* 1241 */     return Collections.synchronizedMap(map);
/*      */   }
/*      */   
/*      */   public static Map unmodifiableMap(Map map) {
/* 1254 */     return UnmodifiableMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static Map predicatedMap(Map map, Predicate keyPred, Predicate valuePred) {
/* 1273 */     return PredicatedMap.decorate(map, keyPred, valuePred);
/*      */   }
/*      */   
/*      */   public static Map typedMap(Map map, Class keyType, Class valueType) {
/* 1288 */     return TypedMap.decorate(map, keyType, valueType);
/*      */   }
/*      */   
/*      */   public static Map transformedMap(Map map, Transformer keyTransformer, Transformer valueTransformer) {
/* 1313 */     return TransformedMap.decorate(map, keyTransformer, valueTransformer);
/*      */   }
/*      */   
/*      */   public static Map fixedSizeMap(Map map) {
/* 1327 */     return FixedSizeMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static Map lazyMap(Map map, Factory factory) {
/* 1359 */     return LazyMap.decorate(map, factory);
/*      */   }
/*      */   
/*      */   public static Map lazyMap(Map map, Transformer transformerFactory) {
/* 1398 */     return LazyMap.decorate(map, transformerFactory);
/*      */   }
/*      */   
/*      */   public static Map orderedMap(Map map) {
/* 1413 */     return ListOrderedMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static Map multiValueMap(Map map) {
/* 1426 */     return (Map)MultiValueMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static Map multiValueMap(Map map, Class collectionClass) {
/* 1441 */     return (Map)MultiValueMap.decorate(map, collectionClass);
/*      */   }
/*      */   
/*      */   public static Map multiValueMap(Map map, Factory collectionFactory) {
/* 1456 */     return (Map)MultiValueMap.decorate(map, collectionFactory);
/*      */   }
/*      */   
/*      */   public static Map synchronizedSortedMap(SortedMap map) {
/* 1485 */     return Collections.synchronizedSortedMap(map);
/*      */   }
/*      */   
/*      */   public static Map unmodifiableSortedMap(SortedMap map) {
/* 1498 */     return UnmodifiableSortedMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static SortedMap predicatedSortedMap(SortedMap map, Predicate keyPred, Predicate valuePred) {
/* 1517 */     return PredicatedSortedMap.decorate(map, keyPred, valuePred);
/*      */   }
/*      */   
/*      */   public static SortedMap typedSortedMap(SortedMap map, Class keyType, Class valueType) {
/* 1531 */     return TypedSortedMap.decorate(map, keyType, valueType);
/*      */   }
/*      */   
/*      */   public static SortedMap transformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
/* 1556 */     return TransformedSortedMap.decorate(map, keyTransformer, valueTransformer);
/*      */   }
/*      */   
/*      */   public static SortedMap fixedSizeSortedMap(SortedMap map) {
/* 1570 */     return FixedSizeSortedMap.decorate(map);
/*      */   }
/*      */   
/*      */   public static SortedMap lazySortedMap(SortedMap map, Factory factory) {
/* 1603 */     return LazySortedMap.decorate(map, factory);
/*      */   }
/*      */   
/*      */   public static SortedMap lazySortedMap(SortedMap map, Transformer transformerFactory) {
/* 1642 */     return LazySortedMap.decorate(map, transformerFactory);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\MapUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */