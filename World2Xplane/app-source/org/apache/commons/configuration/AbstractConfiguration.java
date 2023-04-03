/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Properties;
/*      */ import org.apache.commons.collections.Predicate;
/*      */ import org.apache.commons.collections.iterators.FilterIterator;
/*      */ import org.apache.commons.configuration.event.ConfigurationErrorEvent;
/*      */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*      */ import org.apache.commons.configuration.event.EventSource;
/*      */ import org.apache.commons.configuration.interpol.ConfigurationInterpolator;
/*      */ import org.apache.commons.lang.BooleanUtils;
/*      */ import org.apache.commons.lang.ClassUtils;
/*      */ import org.apache.commons.lang.text.StrLookup;
/*      */ import org.apache.commons.lang.text.StrSubstitutor;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.impl.NoOpLog;
/*      */ 
/*      */ public abstract class AbstractConfiguration extends EventSource implements Configuration {
/*      */   public static final int EVENT_ADD_PROPERTY = 1;
/*      */   
/*      */   public static final int EVENT_CLEAR_PROPERTY = 2;
/*      */   
/*      */   public static final int EVENT_SET_PROPERTY = 3;
/*      */   
/*      */   public static final int EVENT_CLEAR = 4;
/*      */   
/*      */   public static final int EVENT_READ_PROPERTY = 5;
/*      */   
/*      */   protected static final String START_TOKEN = "${";
/*      */   
/*      */   protected static final String END_TOKEN = "}";
/*      */   
/*      */   private static final char DISABLED_DELIMITER = '\000';
/*      */   
/*  130 */   private static char defaultListDelimiter = ',';
/*      */   
/*  133 */   private char listDelimiter = defaultListDelimiter;
/*      */   
/*      */   private boolean delimiterParsingDisabled;
/*      */   
/*      */   private boolean throwExceptionOnMissing;
/*      */   
/*      */   private StrSubstitutor substitutor;
/*      */   
/*      */   private Log log;
/*      */   
/*      */   public AbstractConfiguration() {
/*  158 */     setLogger((Log)null);
/*      */   }
/*      */   
/*      */   public static void setDefaultListDelimiter(char delimiter) {
/*  171 */     defaultListDelimiter = delimiter;
/*      */   }
/*      */   
/*      */   public static void setDelimiter(char delimiter) {
/*  183 */     setDefaultListDelimiter(delimiter);
/*      */   }
/*      */   
/*      */   public static char getDefaultListDelimiter() {
/*  193 */     return defaultListDelimiter;
/*      */   }
/*      */   
/*      */   public static char getDelimiter() {
/*  204 */     return getDefaultListDelimiter();
/*      */   }
/*      */   
/*      */   public void setListDelimiter(char listDelimiter) {
/*  218 */     this.listDelimiter = listDelimiter;
/*      */   }
/*      */   
/*      */   public char getListDelimiter() {
/*  229 */     return this.listDelimiter;
/*      */   }
/*      */   
/*      */   public boolean isDelimiterParsingDisabled() {
/*  239 */     return this.delimiterParsingDisabled;
/*      */   }
/*      */   
/*      */   public void setDelimiterParsingDisabled(boolean delimiterParsingDisabled) {
/*  255 */     this.delimiterParsingDisabled = delimiterParsingDisabled;
/*      */   }
/*      */   
/*      */   public void setThrowExceptionOnMissing(boolean throwExceptionOnMissing) {
/*  271 */     this.throwExceptionOnMissing = throwExceptionOnMissing;
/*      */   }
/*      */   
/*      */   public boolean isThrowExceptionOnMissing() {
/*  281 */     return this.throwExceptionOnMissing;
/*      */   }
/*      */   
/*      */   public synchronized StrSubstitutor getSubstitutor() {
/*  292 */     if (this.substitutor == null)
/*  294 */       this.substitutor = new StrSubstitutor((StrLookup)createInterpolator()); 
/*  296 */     return this.substitutor;
/*      */   }
/*      */   
/*      */   public ConfigurationInterpolator getInterpolator() {
/*  314 */     return (ConfigurationInterpolator)getSubstitutor().getVariableResolver();
/*      */   }
/*      */   
/*      */   protected ConfigurationInterpolator createInterpolator() {
/*  330 */     ConfigurationInterpolator interpol = new ConfigurationInterpolator();
/*  331 */     interpol.setDefaultLookup(new StrLookup() {
/*      */           private final AbstractConfiguration this$0;
/*      */           
/*      */           public String lookup(String var) {
/*  335 */             Object prop = AbstractConfiguration.this.resolveContainerStore(var);
/*  336 */             return (prop != null) ? prop.toString() : null;
/*      */           }
/*      */         });
/*  339 */     return interpol;
/*      */   }
/*      */   
/*      */   public Log getLogger() {
/*  350 */     return this.log;
/*      */   }
/*      */   
/*      */   public void setLogger(Log log) {
/*  365 */     this.log = (log != null) ? log : (Log)new NoOpLog();
/*      */   }
/*      */   
/*      */   public void addErrorLogListener() {
/*  380 */     addErrorListener(new ConfigurationErrorListener() {
/*      */           private final AbstractConfiguration this$0;
/*      */           
/*      */           public void configurationError(ConfigurationErrorEvent event) {
/*  384 */             AbstractConfiguration.this.getLogger().warn("Internal error", event.getCause());
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public void addProperty(String key, Object value) {
/*  391 */     fireEvent(1, key, value, true);
/*  392 */     addPropertyValues(key, value, isDelimiterParsingDisabled() ? Character.MIN_VALUE : getListDelimiter());
/*  395 */     fireEvent(1, key, value, false);
/*      */   }
/*      */   
/*      */   protected abstract void addPropertyDirect(String paramString, Object paramObject);
/*      */   
/*      */   private void addPropertyValues(String key, Object value, char delimiter) {
/*  419 */     Iterator it = PropertyConverter.toIterator(value, delimiter);
/*  420 */     while (it.hasNext())
/*  422 */       addPropertyDirect(key, it.next()); 
/*      */   }
/*      */   
/*      */   protected String interpolate(String base) {
/*  435 */     Object result = interpolate(base);
/*  436 */     return (result == null) ? null : result.toString();
/*      */   }
/*      */   
/*      */   protected Object interpolate(Object value) {
/*  448 */     return PropertyConverter.interpolate(value, this);
/*      */   }
/*      */   
/*      */   protected String interpolateHelper(String base, List priorVariables) {
/*  469 */     return base;
/*      */   }
/*      */   
/*      */   public Configuration subset(String prefix) {
/*  474 */     return new SubsetConfiguration(this, prefix, ".");
/*      */   }
/*      */   
/*      */   public void setProperty(String key, Object value) {
/*  479 */     fireEvent(3, key, value, true);
/*  480 */     setDetailEvents(false);
/*      */     try {
/*  483 */       clearProperty(key);
/*  484 */       addProperty(key, value);
/*      */     } finally {
/*  488 */       setDetailEvents(true);
/*      */     } 
/*  490 */     fireEvent(3, key, value, false);
/*      */   }
/*      */   
/*      */   public void clearProperty(String key) {
/*  502 */     fireEvent(2, key, null, true);
/*  503 */     clearPropertyDirect(key);
/*  504 */     fireEvent(2, key, null, false);
/*      */   }
/*      */   
/*      */   protected void clearPropertyDirect(String key) {}
/*      */   
/*      */   public void clear() {
/*  522 */     fireEvent(4, null, null, true);
/*  523 */     setDetailEvents(false);
/*  524 */     boolean useIterator = true;
/*      */     try {
/*  527 */       Iterator it = getKeys();
/*  528 */       while (it.hasNext()) {
/*  530 */         String key = it.next();
/*  531 */         if (useIterator)
/*      */           try {
/*  535 */             it.remove();
/*  537 */           } catch (UnsupportedOperationException usoex) {
/*  539 */             useIterator = false;
/*      */           }  
/*  543 */         if (useIterator && containsKey(key))
/*  545 */           useIterator = false; 
/*  548 */         if (!useIterator)
/*  552 */           clearProperty(key); 
/*      */       } 
/*      */     } finally {
/*  558 */       setDetailEvents(true);
/*      */     } 
/*  560 */     fireEvent(4, null, null, false);
/*      */   }
/*      */   
/*      */   public Iterator getKeys(final String prefix) {
/*  572 */     return (Iterator)new FilterIterator(getKeys(), new Predicate() {
/*      */           private final String val$prefix;
/*      */           
/*      */           private final AbstractConfiguration this$0;
/*      */           
/*      */           public boolean evaluate(Object obj) {
/*  576 */             String key = (String)obj;
/*  576 */             return (
/*  577 */               key.startsWith(prefix + ".") || key.equals(prefix));
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public Properties getProperties(String key) {
/*  584 */     return getProperties(key, (Properties)null);
/*      */   }
/*      */   
/*      */   public Properties getProperties(String key, Properties defaults) {
/*  607 */     String[] tokens = getStringArray(key);
/*  612 */     Properties props = (defaults == null) ? new Properties() : new Properties(defaults);
/*  613 */     for (int i = 0; i < tokens.length; i++) {
/*  615 */       String token = tokens[i];
/*  616 */       int equalSign = token.indexOf('=');
/*  617 */       if (equalSign > 0) {
/*  619 */         String pkey = token.substring(0, equalSign).trim();
/*  620 */         String pvalue = token.substring(equalSign + 1).trim();
/*  621 */         props.put(pkey, pvalue);
/*      */       } else {
/*  623 */         if (tokens.length == 1 && "".equals(token))
/*      */           break; 
/*  631 */         throw new IllegalArgumentException('\'' + token + "' does not contain an equals sign");
/*      */       } 
/*      */     } 
/*  634 */     return props;
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String key) {
/*  643 */     Boolean b = getBoolean(key, (Boolean)null);
/*  644 */     if (b != null)
/*  646 */       return b.booleanValue(); 
/*  650 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public boolean getBoolean(String key, boolean defaultValue) {
/*  660 */     return getBoolean(key, BooleanUtils.toBooleanObject(defaultValue)).booleanValue();
/*      */   }
/*      */   
/*      */   public Boolean getBoolean(String key, Boolean defaultValue) {
/*  677 */     Object value = resolveContainerStore(key);
/*  679 */     if (value == null)
/*  681 */       return defaultValue; 
/*      */     try {
/*  687 */       return PropertyConverter.toBoolean(interpolate(value));
/*  689 */     } catch (ConversionException e) {
/*  691 */       throw new ConversionException('\'' + key + "' doesn't map to a Boolean object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public byte getByte(String key) {
/*  698 */     Byte b = getByte(key, (Byte)null);
/*  699 */     if (b != null)
/*  701 */       return b.byteValue(); 
/*  705 */     throw new NoSuchElementException('\'' + key + " doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public byte getByte(String key, byte defaultValue) {
/*  711 */     return getByte(key, new Byte(defaultValue)).byteValue();
/*      */   }
/*      */   
/*      */   public Byte getByte(String key, Byte defaultValue) {
/*  716 */     Object value = resolveContainerStore(key);
/*  718 */     if (value == null)
/*  720 */       return defaultValue; 
/*      */     try {
/*  726 */       return PropertyConverter.toByte(interpolate(value));
/*  728 */     } catch (ConversionException e) {
/*  730 */       throw new ConversionException('\'' + key + "' doesn't map to a Byte object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getDouble(String key) {
/*  737 */     Double d = getDouble(key, (Double)null);
/*  738 */     if (d != null)
/*  740 */       return d.doubleValue(); 
/*  744 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public double getDouble(String key, double defaultValue) {
/*  750 */     return getDouble(key, new Double(defaultValue)).doubleValue();
/*      */   }
/*      */   
/*      */   public Double getDouble(String key, Double defaultValue) {
/*  755 */     Object value = resolveContainerStore(key);
/*  757 */     if (value == null)
/*  759 */       return defaultValue; 
/*      */     try {
/*  765 */       return PropertyConverter.toDouble(interpolate(value));
/*  767 */     } catch (ConversionException e) {
/*  769 */       throw new ConversionException('\'' + key + "' doesn't map to a Double object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getFloat(String key) {
/*  776 */     Float f = getFloat(key, (Float)null);
/*  777 */     if (f != null)
/*  779 */       return f.floatValue(); 
/*  783 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public float getFloat(String key, float defaultValue) {
/*  789 */     return getFloat(key, new Float(defaultValue)).floatValue();
/*      */   }
/*      */   
/*      */   public Float getFloat(String key, Float defaultValue) {
/*  794 */     Object value = resolveContainerStore(key);
/*  796 */     if (value == null)
/*  798 */       return defaultValue; 
/*      */     try {
/*  804 */       return PropertyConverter.toFloat(interpolate(value));
/*  806 */     } catch (ConversionException e) {
/*  808 */       throw new ConversionException('\'' + key + "' doesn't map to a Float object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getInt(String key) {
/*  815 */     Integer i = getInteger(key, (Integer)null);
/*  816 */     if (i != null)
/*  818 */       return i.intValue(); 
/*  822 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public int getInt(String key, int defaultValue) {
/*  828 */     Integer i = getInteger(key, (Integer)null);
/*  830 */     if (i == null)
/*  832 */       return defaultValue; 
/*  835 */     return i.intValue();
/*      */   }
/*      */   
/*      */   public Integer getInteger(String key, Integer defaultValue) {
/*  840 */     Object value = resolveContainerStore(key);
/*  842 */     if (value == null)
/*  844 */       return defaultValue; 
/*      */     try {
/*  850 */       return PropertyConverter.toInteger(interpolate(value));
/*  852 */     } catch (ConversionException e) {
/*  854 */       throw new ConversionException('\'' + key + "' doesn't map to an Integer object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public long getLong(String key) {
/*  861 */     Long l = getLong(key, (Long)null);
/*  862 */     if (l != null)
/*  864 */       return l.longValue(); 
/*  868 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public long getLong(String key, long defaultValue) {
/*  874 */     return getLong(key, new Long(defaultValue)).longValue();
/*      */   }
/*      */   
/*      */   public Long getLong(String key, Long defaultValue) {
/*  879 */     Object value = resolveContainerStore(key);
/*  881 */     if (value == null)
/*  883 */       return defaultValue; 
/*      */     try {
/*  889 */       return PropertyConverter.toLong(interpolate(value));
/*  891 */     } catch (ConversionException e) {
/*  893 */       throw new ConversionException('\'' + key + "' doesn't map to a Long object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public short getShort(String key) {
/*  900 */     Short s = getShort(key, (Short)null);
/*  901 */     if (s != null)
/*  903 */       return s.shortValue(); 
/*  907 */     throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object");
/*      */   }
/*      */   
/*      */   public short getShort(String key, short defaultValue) {
/*  913 */     return getShort(key, new Short(defaultValue)).shortValue();
/*      */   }
/*      */   
/*      */   public Short getShort(String key, Short defaultValue) {
/*  918 */     Object value = resolveContainerStore(key);
/*  920 */     if (value == null)
/*  922 */       return defaultValue; 
/*      */     try {
/*  928 */       return PropertyConverter.toShort(interpolate(value));
/*  930 */     } catch (ConversionException e) {
/*  932 */       throw new ConversionException('\'' + key + "' doesn't map to a Short object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String key) {
/*  943 */     BigDecimal number = getBigDecimal(key, (BigDecimal)null);
/*  944 */     if (number != null)
/*  946 */       return number; 
/*  948 */     if (isThrowExceptionOnMissing())
/*  950 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/*  954 */     return null;
/*      */   }
/*      */   
/*      */   public BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
/*  960 */     Object value = resolveContainerStore(key);
/*  962 */     if (value == null)
/*  964 */       return defaultValue; 
/*      */     try {
/*  970 */       return PropertyConverter.toBigDecimal(interpolate(value));
/*  972 */     } catch (ConversionException e) {
/*  974 */       throw new ConversionException('\'' + key + "' doesn't map to a BigDecimal object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public BigInteger getBigInteger(String key) {
/*  985 */     BigInteger number = getBigInteger(key, (BigInteger)null);
/*  986 */     if (number != null)
/*  988 */       return number; 
/*  990 */     if (isThrowExceptionOnMissing())
/*  992 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/*  996 */     return null;
/*      */   }
/*      */   
/*      */   public BigInteger getBigInteger(String key, BigInteger defaultValue) {
/* 1002 */     Object value = resolveContainerStore(key);
/* 1004 */     if (value == null)
/* 1006 */       return defaultValue; 
/*      */     try {
/* 1012 */       return PropertyConverter.toBigInteger(interpolate(value));
/* 1014 */     } catch (ConversionException e) {
/* 1016 */       throw new ConversionException('\'' + key + "' doesn't map to a BigInteger object", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getString(String key) {
/* 1027 */     String s = getString(key, (String)null);
/* 1028 */     if (s != null)
/* 1030 */       return s; 
/* 1032 */     if (isThrowExceptionOnMissing())
/* 1034 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/* 1038 */     return null;
/*      */   }
/*      */   
/*      */   public String getString(String key, String defaultValue) {
/* 1044 */     Object value = resolveContainerStore(key);
/* 1046 */     if (value instanceof String)
/* 1048 */       return interpolate((String)value); 
/* 1050 */     if (value == null)
/* 1052 */       return interpolate(defaultValue); 
/* 1056 */     throw new ConversionException('\'' + key + "' doesn't map to a String object");
/*      */   }
/*      */   
/*      */   public String[] getStringArray(String key) {
/*      */     String[] array;
/* 1081 */     Object value = getProperty(key);
/* 1085 */     if (value instanceof String) {
/* 1087 */       array = new String[1];
/* 1089 */       array[0] = interpolate((String)value);
/* 1091 */     } else if (value instanceof List) {
/* 1093 */       List list = (List)value;
/* 1094 */       array = new String[list.size()];
/* 1096 */       for (int i = 0; i < array.length; i++)
/* 1098 */         array[i] = interpolate(list.get(i)); 
/* 1101 */     } else if (value == null) {
/* 1103 */       array = new String[0];
/* 1105 */     } else if (isScalarValue(value)) {
/* 1107 */       array = new String[1];
/* 1108 */       array[0] = value.toString();
/*      */     } else {
/* 1112 */       throw new ConversionException('\'' + key + "' doesn't map to a String/List object");
/*      */     } 
/* 1114 */     return array;
/*      */   }
/*      */   
/*      */   public List getList(String key) {
/* 1123 */     return getList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getList(String key, List defaultValue) {
/*      */     List list;
/* 1128 */     Object value = getProperty(key);
/* 1131 */     if (value instanceof String) {
/* 1133 */       list = new ArrayList(1);
/* 1134 */       list.add(interpolate((String)value));
/* 1136 */     } else if (value instanceof List) {
/* 1138 */       list = new ArrayList();
/* 1139 */       List l = (List)value;
/* 1142 */       Iterator it = l.iterator();
/* 1143 */       while (it.hasNext())
/* 1145 */         list.add(interpolate(it.next())); 
/* 1148 */     } else if (value == null) {
/* 1150 */       list = defaultValue;
/*      */     } else {
/* 1152 */       if (value.getClass().isArray())
/* 1154 */         return Arrays.asList((Object[])value); 
/* 1156 */       if (isScalarValue(value))
/* 1158 */         return Collections.singletonList(value.toString()); 
/* 1162 */       throw new ConversionException('\'' + key + "' doesn't map to a List object: " + value + ", a " + value.getClass().getName());
/*      */     } 
/* 1165 */     return list;
/*      */   }
/*      */   
/*      */   protected Object resolveContainerStore(String key) {
/* 1178 */     Object value = getProperty(key);
/* 1179 */     if (value != null)
/* 1181 */       if (value instanceof Collection) {
/* 1183 */         Collection collection = (Collection)value;
/* 1184 */         value = collection.isEmpty() ? null : collection.iterator().next();
/* 1186 */       } else if (value.getClass().isArray() && Array.getLength(value) > 0) {
/* 1188 */         value = Array.get(value, 0);
/*      */       }  
/* 1192 */     return value;
/*      */   }
/*      */   
/*      */   protected boolean isScalarValue(Object value) {
/* 1210 */     return (ClassUtils.wrapperToPrimitive(value.getClass()) != null);
/*      */   }
/*      */   
/*      */   public void copy(Configuration c) {
/* 1230 */     if (c != null)
/* 1232 */       for (Iterator it = c.getKeys(); it.hasNext(); ) {
/* 1234 */         String key = it.next();
/* 1235 */         Object value = c.getProperty(key);
/* 1236 */         fireEvent(3, key, value, true);
/* 1237 */         setDetailEvents(false);
/*      */         try {
/* 1240 */           clearProperty(key);
/* 1241 */           addPropertyValues(key, value, false);
/*      */         } finally {
/* 1245 */           setDetailEvents(true);
/*      */         } 
/* 1247 */         fireEvent(3, key, value, false);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void append(Configuration c) {
/* 1271 */     if (c != null)
/* 1273 */       for (Iterator it = c.getKeys(); it.hasNext(); ) {
/* 1275 */         String key = it.next();
/* 1276 */         Object value = c.getProperty(key);
/* 1277 */         fireEvent(1, key, value, true);
/* 1278 */         addPropertyValues(key, value, false);
/* 1279 */         fireEvent(1, key, value, false);
/*      */       }  
/*      */   }
/*      */   
/*      */   public Configuration interpolatedConfiguration() {
/* 1302 */     AbstractConfiguration c = (AbstractConfiguration)ConfigurationUtils.cloneConfiguration(this);
/* 1306 */     c.setDelimiterParsingDisabled(true);
/* 1307 */     for (Iterator it = getKeys(); it.hasNext(); ) {
/* 1309 */       String key = it.next();
/* 1310 */       c.setProperty(key, getList(key));
/*      */     } 
/* 1313 */     c.setDelimiterParsingDisabled(isDelimiterParsingDisabled());
/* 1314 */     return c;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\AbstractConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */