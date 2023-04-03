/*      */ package org.apache.commons.lang.builder;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Collection;
/*      */ import java.util.Map;
/*      */ import java.util.WeakHashMap;
/*      */ import org.apache.commons.lang.ClassUtils;
/*      */ import org.apache.commons.lang.ObjectUtils;
/*      */ import org.apache.commons.lang.SystemUtils;
/*      */ 
/*      */ public abstract class ToStringStyle implements Serializable {
/*   80 */   public static final ToStringStyle DEFAULT_STYLE = new DefaultToStringStyle();
/*      */   
/*   94 */   public static final ToStringStyle MULTI_LINE_STYLE = new MultiLineToStringStyle();
/*      */   
/*  105 */   public static final ToStringStyle NO_FIELD_NAMES_STYLE = new NoFieldNameToStringStyle();
/*      */   
/*  117 */   public static final ToStringStyle SHORT_PREFIX_STYLE = new ShortPrefixToStringStyle();
/*      */   
/*  127 */   public static final ToStringStyle SIMPLE_STYLE = new SimpleToStringStyle();
/*      */   
/*  135 */   private static final ThreadLocal REGISTRY = new ThreadLocal();
/*      */   
/*      */   static Map getRegistry() {
/*  146 */     return REGISTRY.get();
/*      */   }
/*      */   
/*      */   static boolean isRegistered(Object value) {
/*  161 */     Map m = getRegistry();
/*  162 */     return (m != null && m.containsKey(value));
/*      */   }
/*      */   
/*      */   static void register(Object value) {
/*  175 */     if (value != null) {
/*  176 */       Map m = getRegistry();
/*  177 */       if (m == null) {
/*  178 */         m = new WeakHashMap();
/*  179 */         REGISTRY.set(m);
/*      */       } 
/*  181 */       m.put(value, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   static void unregister(Object value) {
/*  198 */     if (value != null) {
/*  199 */       Map m = getRegistry();
/*  200 */       if (m != null) {
/*  201 */         m.remove(value);
/*  202 */         if (m.isEmpty())
/*  203 */           REGISTRY.set(null); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean useFieldNames = true;
/*      */   
/*      */   private boolean useClassName = true;
/*      */   
/*      */   private boolean useShortClassName = false;
/*      */   
/*      */   private boolean useIdentityHashCode = true;
/*      */   
/*  232 */   private String contentStart = "[";
/*      */   
/*  237 */   private String contentEnd = "]";
/*      */   
/*  242 */   private String fieldNameValueSeparator = "=";
/*      */   
/*      */   private boolean fieldSeparatorAtStart = false;
/*      */   
/*      */   private boolean fieldSeparatorAtEnd = false;
/*      */   
/*  257 */   private String fieldSeparator = ",";
/*      */   
/*  262 */   private String arrayStart = "{";
/*      */   
/*  267 */   private String arraySeparator = ",";
/*      */   
/*      */   private boolean arrayContentDetail = true;
/*      */   
/*  277 */   private String arrayEnd = "}";
/*      */   
/*      */   private boolean defaultFullDetail = true;
/*      */   
/*  288 */   private String nullText = "<null>";
/*      */   
/*  293 */   private String sizeStartText = "<size=";
/*      */   
/*  298 */   private String sizeEndText = ">";
/*      */   
/*  303 */   private String summaryObjectStartText = "<";
/*      */   
/*  308 */   private String summaryObjectEndText = ">";
/*      */   
/*      */   public void appendSuper(StringBuffer buffer, String superToString) {
/*  332 */     appendToString(buffer, superToString);
/*      */   }
/*      */   
/*      */   public void appendToString(StringBuffer buffer, String toString) {
/*  346 */     if (toString != null) {
/*  347 */       int pos1 = toString.indexOf(this.contentStart) + this.contentStart.length();
/*  348 */       int pos2 = toString.lastIndexOf(this.contentEnd);
/*  349 */       if (pos1 != pos2 && pos1 >= 0 && pos2 >= 0) {
/*  350 */         String data = toString.substring(pos1, pos2);
/*  351 */         if (this.fieldSeparatorAtStart)
/*  352 */           removeLastFieldSeparator(buffer); 
/*  354 */         buffer.append(data);
/*  355 */         appendFieldSeparator(buffer);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void appendStart(StringBuffer buffer, Object object) {
/*  367 */     if (object != null) {
/*  368 */       appendClassName(buffer, object);
/*  369 */       appendIdentityHashCode(buffer, object);
/*  370 */       appendContentStart(buffer);
/*  371 */       if (this.fieldSeparatorAtStart)
/*  372 */         appendFieldSeparator(buffer); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void appendEnd(StringBuffer buffer, Object object) {
/*  385 */     if (!this.fieldSeparatorAtEnd)
/*  386 */       removeLastFieldSeparator(buffer); 
/*  388 */     appendContentEnd(buffer);
/*  389 */     unregister(object);
/*      */   }
/*      */   
/*      */   protected void removeLastFieldSeparator(StringBuffer buffer) {
/*  399 */     int len = buffer.length();
/*  400 */     int sepLen = this.fieldSeparator.length();
/*  401 */     if (len > 0 && sepLen > 0 && len >= sepLen) {
/*  402 */       boolean match = true;
/*  403 */       for (int i = 0; i < sepLen; i++) {
/*  404 */         if (buffer.charAt(len - 1 - i) != this.fieldSeparator.charAt(sepLen - 1 - i)) {
/*  405 */           match = false;
/*      */           break;
/*      */         } 
/*      */       } 
/*  409 */       if (match)
/*  410 */         buffer.setLength(len - sepLen); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
/*  429 */     appendFieldStart(buffer, fieldName);
/*  431 */     if (value == null) {
/*  432 */       appendNullText(buffer, fieldName);
/*      */     } else {
/*  435 */       appendInternal(buffer, fieldName, value, isFullDetail(fullDetail));
/*      */     } 
/*  438 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail) {
/*  461 */     if (isRegistered(value) && !(value instanceof Number) && !(value instanceof Boolean) && !(value instanceof Character)) {
/*  463 */       appendCyclicObject(buffer, fieldName, value);
/*      */       return;
/*      */     } 
/*  467 */     register(value);
/*      */     try {
/*  470 */       if (value instanceof Collection) {
/*  471 */         if (detail) {
/*  472 */           appendDetail(buffer, fieldName, (Collection)value);
/*      */         } else {
/*  474 */           appendSummarySize(buffer, fieldName, ((Collection)value).size());
/*      */         } 
/*  477 */       } else if (value instanceof Map) {
/*  478 */         if (detail) {
/*  479 */           appendDetail(buffer, fieldName, (Map)value);
/*      */         } else {
/*  481 */           appendSummarySize(buffer, fieldName, ((Map)value).size());
/*      */         } 
/*  484 */       } else if (value instanceof long[]) {
/*  485 */         if (detail) {
/*  486 */           appendDetail(buffer, fieldName, (long[])value);
/*      */         } else {
/*  488 */           appendSummary(buffer, fieldName, (long[])value);
/*      */         } 
/*  491 */       } else if (value instanceof int[]) {
/*  492 */         if (detail) {
/*  493 */           appendDetail(buffer, fieldName, (int[])value);
/*      */         } else {
/*  495 */           appendSummary(buffer, fieldName, (int[])value);
/*      */         } 
/*  498 */       } else if (value instanceof short[]) {
/*  499 */         if (detail) {
/*  500 */           appendDetail(buffer, fieldName, (short[])value);
/*      */         } else {
/*  502 */           appendSummary(buffer, fieldName, (short[])value);
/*      */         } 
/*  505 */       } else if (value instanceof byte[]) {
/*  506 */         if (detail) {
/*  507 */           appendDetail(buffer, fieldName, (byte[])value);
/*      */         } else {
/*  509 */           appendSummary(buffer, fieldName, (byte[])value);
/*      */         } 
/*  512 */       } else if (value instanceof char[]) {
/*  513 */         if (detail) {
/*  514 */           appendDetail(buffer, fieldName, (char[])value);
/*      */         } else {
/*  516 */           appendSummary(buffer, fieldName, (char[])value);
/*      */         } 
/*  519 */       } else if (value instanceof double[]) {
/*  520 */         if (detail) {
/*  521 */           appendDetail(buffer, fieldName, (double[])value);
/*      */         } else {
/*  523 */           appendSummary(buffer, fieldName, (double[])value);
/*      */         } 
/*  526 */       } else if (value instanceof float[]) {
/*  527 */         if (detail) {
/*  528 */           appendDetail(buffer, fieldName, (float[])value);
/*      */         } else {
/*  530 */           appendSummary(buffer, fieldName, (float[])value);
/*      */         } 
/*  533 */       } else if (value instanceof boolean[]) {
/*  534 */         if (detail) {
/*  535 */           appendDetail(buffer, fieldName, (boolean[])value);
/*      */         } else {
/*  537 */           appendSummary(buffer, fieldName, (boolean[])value);
/*      */         } 
/*  540 */       } else if (value.getClass().isArray()) {
/*  541 */         if (detail) {
/*  542 */           appendDetail(buffer, fieldName, (Object[])value);
/*      */         } else {
/*  544 */           appendSummary(buffer, fieldName, (Object[])value);
/*      */         } 
/*  548 */       } else if (detail) {
/*  549 */         appendDetail(buffer, fieldName, value);
/*      */       } else {
/*  551 */         appendSummary(buffer, fieldName, value);
/*      */       } 
/*      */     } finally {
/*  555 */       unregister(value);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void appendCyclicObject(StringBuffer buffer, String fieldName, Object value) {
/*  572 */     ObjectUtils.identityToString(buffer, value);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
/*  585 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Collection coll) {
/*  597 */     buffer.append(coll);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Map map) {
/*  609 */     buffer.append(map);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, Object value) {
/*  622 */     buffer.append(this.summaryObjectStartText);
/*  623 */     buffer.append(getShortClassName(value.getClass()));
/*  624 */     buffer.append(this.summaryObjectEndText);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, long value) {
/*  638 */     appendFieldStart(buffer, fieldName);
/*  639 */     appendDetail(buffer, fieldName, value);
/*  640 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, long value) {
/*  652 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, int value) {
/*  666 */     appendFieldStart(buffer, fieldName);
/*  667 */     appendDetail(buffer, fieldName, value);
/*  668 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, int value) {
/*  680 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, short value) {
/*  694 */     appendFieldStart(buffer, fieldName);
/*  695 */     appendDetail(buffer, fieldName, value);
/*  696 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, short value) {
/*  708 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, byte value) {
/*  722 */     appendFieldStart(buffer, fieldName);
/*  723 */     appendDetail(buffer, fieldName, value);
/*  724 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, byte value) {
/*  736 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, char value) {
/*  750 */     appendFieldStart(buffer, fieldName);
/*  751 */     appendDetail(buffer, fieldName, value);
/*  752 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, char value) {
/*  764 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, double value) {
/*  778 */     appendFieldStart(buffer, fieldName);
/*  779 */     appendDetail(buffer, fieldName, value);
/*  780 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, double value) {
/*  792 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, float value) {
/*  806 */     appendFieldStart(buffer, fieldName);
/*  807 */     appendDetail(buffer, fieldName, value);
/*  808 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, float value) {
/*  820 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, boolean value) {
/*  834 */     appendFieldStart(buffer, fieldName);
/*  835 */     appendDetail(buffer, fieldName, value);
/*  836 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, boolean value) {
/*  848 */     buffer.append(value);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
/*  862 */     appendFieldStart(buffer, fieldName);
/*  864 */     if (array == null) {
/*  865 */       appendNullText(buffer, fieldName);
/*  867 */     } else if (isFullDetail(fullDetail)) {
/*  868 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/*  871 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/*  874 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array) {
/*  889 */     buffer.append(this.arrayStart);
/*  890 */     for (int i = 0; i < array.length; i++) {
/*  891 */       Object item = array[i];
/*  892 */       if (i > 0)
/*  893 */         buffer.append(this.arraySeparator); 
/*  895 */       if (item == null) {
/*  896 */         appendNullText(buffer, fieldName);
/*      */       } else {
/*  899 */         appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*      */       } 
/*      */     } 
/*  902 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void reflectionAppendArrayDetail(StringBuffer buffer, String fieldName, Object array) {
/*  915 */     buffer.append(this.arrayStart);
/*  916 */     int length = Array.getLength(array);
/*  917 */     for (int i = 0; i < length; i++) {
/*  918 */       Object item = Array.get(array, i);
/*  919 */       if (i > 0)
/*  920 */         buffer.append(this.arraySeparator); 
/*  922 */       if (item == null) {
/*  923 */         appendNullText(buffer, fieldName);
/*      */       } else {
/*  926 */         appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*      */       } 
/*      */     } 
/*  929 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, Object[] array) {
/*  942 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail) {
/*  958 */     appendFieldStart(buffer, fieldName);
/*  960 */     if (array == null) {
/*  961 */       appendNullText(buffer, fieldName);
/*  963 */     } else if (isFullDetail(fullDetail)) {
/*  964 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/*  967 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/*  970 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, long[] array) {
/*  983 */     buffer.append(this.arrayStart);
/*  984 */     for (int i = 0; i < array.length; i++) {
/*  985 */       if (i > 0)
/*  986 */         buffer.append(this.arraySeparator); 
/*  988 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/*  990 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, long[] array) {
/* 1003 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail) {
/* 1019 */     appendFieldStart(buffer, fieldName);
/* 1021 */     if (array == null) {
/* 1022 */       appendNullText(buffer, fieldName);
/* 1024 */     } else if (isFullDetail(fullDetail)) {
/* 1025 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1028 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1031 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, int[] array) {
/* 1044 */     buffer.append(this.arrayStart);
/* 1045 */     for (int i = 0; i < array.length; i++) {
/* 1046 */       if (i > 0)
/* 1047 */         buffer.append(this.arraySeparator); 
/* 1049 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1051 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, int[] array) {
/* 1064 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail) {
/* 1080 */     appendFieldStart(buffer, fieldName);
/* 1082 */     if (array == null) {
/* 1083 */       appendNullText(buffer, fieldName);
/* 1085 */     } else if (isFullDetail(fullDetail)) {
/* 1086 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1089 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1092 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, short[] array) {
/* 1105 */     buffer.append(this.arrayStart);
/* 1106 */     for (int i = 0; i < array.length; i++) {
/* 1107 */       if (i > 0)
/* 1108 */         buffer.append(this.arraySeparator); 
/* 1110 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1112 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, short[] array) {
/* 1125 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail) {
/* 1141 */     appendFieldStart(buffer, fieldName);
/* 1143 */     if (array == null) {
/* 1144 */       appendNullText(buffer, fieldName);
/* 1146 */     } else if (isFullDetail(fullDetail)) {
/* 1147 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1150 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1153 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, byte[] array) {
/* 1166 */     buffer.append(this.arrayStart);
/* 1167 */     for (int i = 0; i < array.length; i++) {
/* 1168 */       if (i > 0)
/* 1169 */         buffer.append(this.arraySeparator); 
/* 1171 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1173 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, byte[] array) {
/* 1186 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail) {
/* 1202 */     appendFieldStart(buffer, fieldName);
/* 1204 */     if (array == null) {
/* 1205 */       appendNullText(buffer, fieldName);
/* 1207 */     } else if (isFullDetail(fullDetail)) {
/* 1208 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1211 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1214 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, char[] array) {
/* 1227 */     buffer.append(this.arrayStart);
/* 1228 */     for (int i = 0; i < array.length; i++) {
/* 1229 */       if (i > 0)
/* 1230 */         buffer.append(this.arraySeparator); 
/* 1232 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1234 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, char[] array) {
/* 1247 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail) {
/* 1263 */     appendFieldStart(buffer, fieldName);
/* 1265 */     if (array == null) {
/* 1266 */       appendNullText(buffer, fieldName);
/* 1268 */     } else if (isFullDetail(fullDetail)) {
/* 1269 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1272 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1275 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, double[] array) {
/* 1288 */     buffer.append(this.arrayStart);
/* 1289 */     for (int i = 0; i < array.length; i++) {
/* 1290 */       if (i > 0)
/* 1291 */         buffer.append(this.arraySeparator); 
/* 1293 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1295 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, double[] array) {
/* 1308 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail) {
/* 1324 */     appendFieldStart(buffer, fieldName);
/* 1326 */     if (array == null) {
/* 1327 */       appendNullText(buffer, fieldName);
/* 1329 */     } else if (isFullDetail(fullDetail)) {
/* 1330 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1333 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1336 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, float[] array) {
/* 1349 */     buffer.append(this.arrayStart);
/* 1350 */     for (int i = 0; i < array.length; i++) {
/* 1351 */       if (i > 0)
/* 1352 */         buffer.append(this.arraySeparator); 
/* 1354 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1356 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, float[] array) {
/* 1369 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail) {
/* 1385 */     appendFieldStart(buffer, fieldName);
/* 1387 */     if (array == null) {
/* 1388 */       appendNullText(buffer, fieldName);
/* 1390 */     } else if (isFullDetail(fullDetail)) {
/* 1391 */       appendDetail(buffer, fieldName, array);
/*      */     } else {
/* 1394 */       appendSummary(buffer, fieldName, array);
/*      */     } 
/* 1397 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */   
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, boolean[] array) {
/* 1410 */     buffer.append(this.arrayStart);
/* 1411 */     for (int i = 0; i < array.length; i++) {
/* 1412 */       if (i > 0)
/* 1413 */         buffer.append(this.arraySeparator); 
/* 1415 */       appendDetail(buffer, fieldName, array[i]);
/*      */     } 
/* 1417 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */   
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, boolean[] array) {
/* 1430 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */   
/*      */   protected void appendClassName(StringBuffer buffer, Object object) {
/* 1442 */     if (this.useClassName && object != null) {
/* 1443 */       register(object);
/* 1444 */       if (this.useShortClassName) {
/* 1445 */         buffer.append(getShortClassName(object.getClass()));
/*      */       } else {
/* 1447 */         buffer.append(object.getClass().getName());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void appendIdentityHashCode(StringBuffer buffer, Object object) {
/* 1459 */     if (isUseIdentityHashCode() && object != null) {
/* 1460 */       register(object);
/* 1461 */       buffer.append('@');
/* 1462 */       buffer.append(Integer.toHexString(System.identityHashCode(object)));
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void appendContentStart(StringBuffer buffer) {
/* 1472 */     buffer.append(this.contentStart);
/*      */   }
/*      */   
/*      */   protected void appendContentEnd(StringBuffer buffer) {
/* 1481 */     buffer.append(this.contentEnd);
/*      */   }
/*      */   
/*      */   protected void appendNullText(StringBuffer buffer, String fieldName) {
/* 1493 */     buffer.append(this.nullText);
/*      */   }
/*      */   
/*      */   protected void appendFieldSeparator(StringBuffer buffer) {
/* 1502 */     buffer.append(this.fieldSeparator);
/*      */   }
/*      */   
/*      */   protected void appendFieldStart(StringBuffer buffer, String fieldName) {
/* 1512 */     if (this.useFieldNames && fieldName != null) {
/* 1513 */       buffer.append(fieldName);
/* 1514 */       buffer.append(this.fieldNameValueSeparator);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void appendFieldEnd(StringBuffer buffer, String fieldName) {
/* 1525 */     appendFieldSeparator(buffer);
/*      */   }
/*      */   
/*      */   protected void appendSummarySize(StringBuffer buffer, String fieldName, int size) {
/* 1544 */     buffer.append(this.sizeStartText);
/* 1545 */     buffer.append(size);
/* 1546 */     buffer.append(this.sizeEndText);
/*      */   }
/*      */   
/*      */   protected boolean isFullDetail(Boolean fullDetailRequest) {
/* 1564 */     if (fullDetailRequest == null)
/* 1565 */       return this.defaultFullDetail; 
/* 1567 */     return fullDetailRequest.booleanValue();
/*      */   }
/*      */   
/*      */   protected String getShortClassName(Class cls) {
/* 1580 */     return ClassUtils.getShortClassName(cls);
/*      */   }
/*      */   
/*      */   protected boolean isUseClassName() {
/* 1594 */     return this.useClassName;
/*      */   }
/*      */   
/*      */   protected void setUseClassName(boolean useClassName) {
/* 1603 */     this.useClassName = useClassName;
/*      */   }
/*      */   
/*      */   protected boolean isUseShortClassName() {
/* 1615 */     return this.useShortClassName;
/*      */   }
/*      */   
/*      */   protected boolean isShortClassName() {
/* 1626 */     return this.useShortClassName;
/*      */   }
/*      */   
/*      */   protected void setUseShortClassName(boolean useShortClassName) {
/* 1636 */     this.useShortClassName = useShortClassName;
/*      */   }
/*      */   
/*      */   protected void setShortClassName(boolean shortClassName) {
/* 1647 */     this.useShortClassName = shortClassName;
/*      */   }
/*      */   
/*      */   protected boolean isUseIdentityHashCode() {
/* 1658 */     return this.useIdentityHashCode;
/*      */   }
/*      */   
/*      */   protected void setUseIdentityHashCode(boolean useIdentityHashCode) {
/* 1667 */     this.useIdentityHashCode = useIdentityHashCode;
/*      */   }
/*      */   
/*      */   protected boolean isUseFieldNames() {
/* 1678 */     return this.useFieldNames;
/*      */   }
/*      */   
/*      */   protected void setUseFieldNames(boolean useFieldNames) {
/* 1687 */     this.useFieldNames = useFieldNames;
/*      */   }
/*      */   
/*      */   protected boolean isDefaultFullDetail() {
/* 1699 */     return this.defaultFullDetail;
/*      */   }
/*      */   
/*      */   protected void setDefaultFullDetail(boolean defaultFullDetail) {
/* 1709 */     this.defaultFullDetail = defaultFullDetail;
/*      */   }
/*      */   
/*      */   protected boolean isArrayContentDetail() {
/* 1720 */     return this.arrayContentDetail;
/*      */   }
/*      */   
/*      */   protected void setArrayContentDetail(boolean arrayContentDetail) {
/* 1729 */     this.arrayContentDetail = arrayContentDetail;
/*      */   }
/*      */   
/*      */   protected String getArrayStart() {
/* 1740 */     return this.arrayStart;
/*      */   }
/*      */   
/*      */   protected void setArrayStart(String arrayStart) {
/* 1752 */     if (arrayStart == null)
/* 1753 */       arrayStart = ""; 
/* 1755 */     this.arrayStart = arrayStart;
/*      */   }
/*      */   
/*      */   protected String getArrayEnd() {
/* 1766 */     return this.arrayEnd;
/*      */   }
/*      */   
/*      */   protected void setArrayEnd(String arrayEnd) {
/* 1778 */     if (arrayEnd == null)
/* 1779 */       arrayEnd = ""; 
/* 1781 */     this.arrayEnd = arrayEnd;
/*      */   }
/*      */   
/*      */   protected String getArraySeparator() {
/* 1792 */     return this.arraySeparator;
/*      */   }
/*      */   
/*      */   protected void setArraySeparator(String arraySeparator) {
/* 1804 */     if (arraySeparator == null)
/* 1805 */       arraySeparator = ""; 
/* 1807 */     this.arraySeparator = arraySeparator;
/*      */   }
/*      */   
/*      */   protected String getContentStart() {
/* 1818 */     return this.contentStart;
/*      */   }
/*      */   
/*      */   protected void setContentStart(String contentStart) {
/* 1830 */     if (contentStart == null)
/* 1831 */       contentStart = ""; 
/* 1833 */     this.contentStart = contentStart;
/*      */   }
/*      */   
/*      */   protected String getContentEnd() {
/* 1844 */     return this.contentEnd;
/*      */   }
/*      */   
/*      */   protected void setContentEnd(String contentEnd) {
/* 1856 */     if (contentEnd == null)
/* 1857 */       contentEnd = ""; 
/* 1859 */     this.contentEnd = contentEnd;
/*      */   }
/*      */   
/*      */   protected String getFieldNameValueSeparator() {
/* 1870 */     return this.fieldNameValueSeparator;
/*      */   }
/*      */   
/*      */   protected void setFieldNameValueSeparator(String fieldNameValueSeparator) {
/* 1882 */     if (fieldNameValueSeparator == null)
/* 1883 */       fieldNameValueSeparator = ""; 
/* 1885 */     this.fieldNameValueSeparator = fieldNameValueSeparator;
/*      */   }
/*      */   
/*      */   protected String getFieldSeparator() {
/* 1896 */     return this.fieldSeparator;
/*      */   }
/*      */   
/*      */   protected void setFieldSeparator(String fieldSeparator) {
/* 1908 */     if (fieldSeparator == null)
/* 1909 */       fieldSeparator = ""; 
/* 1911 */     this.fieldSeparator = fieldSeparator;
/*      */   }
/*      */   
/*      */   protected boolean isFieldSeparatorAtStart() {
/* 1924 */     return this.fieldSeparatorAtStart;
/*      */   }
/*      */   
/*      */   protected void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart) {
/* 1935 */     this.fieldSeparatorAtStart = fieldSeparatorAtStart;
/*      */   }
/*      */   
/*      */   protected boolean isFieldSeparatorAtEnd() {
/* 1948 */     return this.fieldSeparatorAtEnd;
/*      */   }
/*      */   
/*      */   protected void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd) {
/* 1959 */     this.fieldSeparatorAtEnd = fieldSeparatorAtEnd;
/*      */   }
/*      */   
/*      */   protected String getNullText() {
/* 1970 */     return this.nullText;
/*      */   }
/*      */   
/*      */   protected void setNullText(String nullText) {
/* 1982 */     if (nullText == null)
/* 1983 */       nullText = ""; 
/* 1985 */     this.nullText = nullText;
/*      */   }
/*      */   
/*      */   protected String getSizeStartText() {
/* 1999 */     return this.sizeStartText;
/*      */   }
/*      */   
/*      */   protected void setSizeStartText(String sizeStartText) {
/* 2014 */     if (sizeStartText == null)
/* 2015 */       sizeStartText = ""; 
/* 2017 */     this.sizeStartText = sizeStartText;
/*      */   }
/*      */   
/*      */   protected String getSizeEndText() {
/* 2031 */     return this.sizeEndText;
/*      */   }
/*      */   
/*      */   protected void setSizeEndText(String sizeEndText) {
/* 2046 */     if (sizeEndText == null)
/* 2047 */       sizeEndText = ""; 
/* 2049 */     this.sizeEndText = sizeEndText;
/*      */   }
/*      */   
/*      */   protected String getSummaryObjectStartText() {
/* 2063 */     return this.summaryObjectStartText;
/*      */   }
/*      */   
/*      */   protected void setSummaryObjectStartText(String summaryObjectStartText) {
/* 2078 */     if (summaryObjectStartText == null)
/* 2079 */       summaryObjectStartText = ""; 
/* 2081 */     this.summaryObjectStartText = summaryObjectStartText;
/*      */   }
/*      */   
/*      */   protected String getSummaryObjectEndText() {
/* 2095 */     return this.summaryObjectEndText;
/*      */   }
/*      */   
/*      */   protected void setSummaryObjectEndText(String summaryObjectEndText) {
/* 2110 */     if (summaryObjectEndText == null)
/* 2111 */       summaryObjectEndText = ""; 
/* 2113 */     this.summaryObjectEndText = summaryObjectEndText;
/*      */   }
/*      */   
/*      */   private static final class DefaultToStringStyle extends ToStringStyle {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     private Object readResolve() {
/* 2148 */       return ToStringStyle.DEFAULT_STYLE;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class NoFieldNameToStringStyle extends ToStringStyle {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     NoFieldNameToStringStyle() {
/* 2173 */       setUseFieldNames(false);
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 2182 */       return ToStringStyle.NO_FIELD_NAMES_STYLE;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class ShortPrefixToStringStyle extends ToStringStyle {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     ShortPrefixToStringStyle() {
/* 2207 */       setUseShortClassName(true);
/* 2208 */       setUseIdentityHashCode(false);
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 2216 */       return ToStringStyle.SHORT_PREFIX_STYLE;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class SimpleToStringStyle extends ToStringStyle {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     SimpleToStringStyle() {
/* 2239 */       setUseClassName(false);
/* 2240 */       setUseIdentityHashCode(false);
/* 2241 */       setUseFieldNames(false);
/* 2242 */       setContentStart("");
/* 2243 */       setContentEnd("");
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 2251 */       return ToStringStyle.SIMPLE_STYLE;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class MultiLineToStringStyle extends ToStringStyle {
/*      */     private static final long serialVersionUID = 1L;
/*      */     
/*      */     MultiLineToStringStyle() {
/* 2275 */       setContentStart("[");
/* 2276 */       setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
/* 2277 */       setFieldSeparatorAtStart(true);
/* 2278 */       setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
/*      */     }
/*      */     
/*      */     private Object readResolve() {
/* 2287 */       return ToStringStyle.MULTI_LINE_STYLE;
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\ToStringStyle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */