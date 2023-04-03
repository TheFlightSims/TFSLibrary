/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.NoSuchElementException;
/*      */ import org.apache.commons.lang.ClassUtils;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ 
/*      */ public class DataConfiguration extends AbstractConfiguration implements Serializable {
/*      */   public static final String DATE_FORMAT_KEY = "org.apache.commons.configuration.format.date";
/*      */   
/*      */   public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
/*      */   
/*      */   private static final long serialVersionUID = -69011336405718640L;
/*      */   
/*      */   protected Configuration configuration;
/*      */   
/*      */   public DataConfiguration(Configuration configuration) {
/*  113 */     this.configuration = configuration;
/*      */   }
/*      */   
/*      */   public Configuration getConfiguration() {
/*  123 */     return this.configuration;
/*      */   }
/*      */   
/*      */   public Object getProperty(String key) {
/*  128 */     return this.configuration.getProperty(key);
/*      */   }
/*      */   
/*      */   protected void addPropertyDirect(String key, Object obj) {
/*  133 */     if (this.configuration instanceof AbstractConfiguration) {
/*  135 */       ((AbstractConfiguration)this.configuration).addPropertyDirect(key, obj);
/*      */     } else {
/*  139 */       this.configuration.addProperty(key, obj);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void addProperty(String key, Object value) {
/*  145 */     getConfiguration().addProperty(key, value);
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  150 */     return this.configuration.isEmpty();
/*      */   }
/*      */   
/*      */   public boolean containsKey(String key) {
/*  155 */     return this.configuration.containsKey(key);
/*      */   }
/*      */   
/*      */   public void clearProperty(String key) {
/*  160 */     this.configuration.clearProperty(key);
/*      */   }
/*      */   
/*      */   public void setProperty(String key, Object value) {
/*  165 */     this.configuration.setProperty(key, value);
/*      */   }
/*      */   
/*      */   public Iterator getKeys() {
/*  170 */     return this.configuration.getKeys();
/*      */   }
/*      */   
/*      */   public Object get(Class cls, String key) {
/*  192 */     Object value = get(cls, key, (Object)null);
/*  193 */     if (value != null)
/*  195 */       return value; 
/*  197 */     if (isThrowExceptionOnMissing())
/*  199 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/*  203 */     return null;
/*      */   }
/*      */   
/*      */   public Object get(Class cls, String key, Object defaultValue) {
/*  224 */     Object value = resolveContainerStore(key);
/*  226 */     if (value == null)
/*  228 */       return defaultValue; 
/*      */     try {
/*  234 */       if (Date.class.equals(cls) || Calendar.class.equals(cls))
/*  236 */         return PropertyConverter.to(cls, interpolate(value), (Object[])new String[] { getDefaultDateFormat() }); 
/*  240 */       return PropertyConverter.to(cls, interpolate(value), null);
/*  243 */     } catch (ConversionException e) {
/*  245 */       throw new ConversionException('\'' + key + "' doesn't map to a " + cls, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public List getList(Class cls, String key) {
/*  265 */     return getList(cls, key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getList(Class cls, String key, List defaultValue) {
/*      */     List list;
/*  285 */     Object value = getProperty(key);
/*  286 */     Class valueClass = (value != null) ? value.getClass() : null;
/*  290 */     if (value == null || (value instanceof String && StringUtils.isEmpty((String)value))) {
/*  293 */       list = defaultValue;
/*      */     } else {
/*  297 */       list = new ArrayList();
/*  299 */       Object[] params = null;
/*  300 */       if (cls.equals(Date.class) || cls.equals(Calendar.class))
/*  302 */         params = new Object[] { getDefaultDateFormat() }; 
/*      */       try {
/*  307 */         if (valueClass.isArray()) {
/*  310 */           Class arrayType = valueClass.getComponentType();
/*  311 */           int length = Array.getLength(value);
/*  313 */           if (arrayType.equals(cls) || (arrayType.isPrimitive() && cls.equals(ClassUtils.primitiveToWrapper(arrayType)))) {
/*  318 */             for (int i = 0; i < length; i++)
/*  320 */               list.add(Array.get(value, i)); 
/*      */           } else {
/*  326 */             for (int i = 0; i < length; i++)
/*  328 */               list.add(PropertyConverter.to(cls, interpolate(Array.get(value, i)), params)); 
/*      */           } 
/*  332 */         } else if (value instanceof Collection) {
/*  334 */           Collection values = (Collection)value;
/*  336 */           Iterator it = values.iterator();
/*  337 */           while (it.hasNext())
/*  339 */             list.add(PropertyConverter.to(cls, interpolate(it.next()), params)); 
/*      */         } else {
/*  345 */           list.add(PropertyConverter.to(cls, interpolate(value), params));
/*      */         } 
/*  348 */       } catch (ConversionException e) {
/*  350 */         throw new ConversionException("'" + key + "' doesn't map to a list of " + cls, e);
/*      */       } 
/*      */     } 
/*  354 */     return list;
/*      */   }
/*      */   
/*      */   public Object getArray(Class cls, String key) {
/*  372 */     return getArray(cls, key, Array.newInstance(cls, 0));
/*      */   }
/*      */   
/*      */   public Object getArray(Class cls, String key, Object defaultValue) {
/*  393 */     if (defaultValue != null && (!defaultValue.getClass().isArray() || !cls.isAssignableFrom(defaultValue.getClass().getComponentType())))
/*  398 */       throw new IllegalArgumentException("The type of the default value (" + defaultValue.getClass() + ")" + " is not an array of the specified class (" + cls + ")"); 
/*  404 */     if (cls.isPrimitive())
/*  406 */       return getPrimitiveArray(cls, key, defaultValue); 
/*  409 */     List list = getList(cls, key);
/*  410 */     if (list.isEmpty())
/*  412 */       return defaultValue; 
/*  416 */     return list.toArray((Object[])Array.newInstance(cls, list.size()));
/*      */   }
/*      */   
/*      */   private Object getPrimitiveArray(Class cls, String key, Object defaultValue) {
/*  436 */     Object array, value = getProperty(key);
/*  437 */     Class valueClass = (value != null) ? value.getClass() : null;
/*  441 */     if (value == null || (value instanceof String && StringUtils.isEmpty((String)value))) {
/*  444 */       array = defaultValue;
/*  448 */     } else if (valueClass.isArray()) {
/*  451 */       Class arrayType = valueClass.getComponentType();
/*  452 */       int length = Array.getLength(value);
/*  454 */       if (arrayType.equals(cls)) {
/*  457 */         array = value;
/*  459 */       } else if (arrayType.equals(ClassUtils.primitiveToWrapper(cls))) {
/*  462 */         array = Array.newInstance(cls, length);
/*  464 */         for (int i = 0; i < length; i++)
/*  466 */           Array.set(array, i, Array.get(value, i)); 
/*      */       } else {
/*  471 */         throw new ConversionException('\'' + key + "' (" + arrayType + ")" + " doesn't map to a compatible array of " + cls);
/*      */       } 
/*  475 */     } else if (value instanceof Collection) {
/*  477 */       Collection values = (Collection)value;
/*  479 */       array = Array.newInstance(cls, values.size());
/*  481 */       Iterator it = values.iterator();
/*  482 */       int i = 0;
/*  483 */       while (it.hasNext())
/*  485 */         Array.set(array, i++, PropertyConverter.to(cls, interpolate(it.next()), null)); 
/*      */     } else {
/*      */       try {
/*  493 */         Object convertedValue = PropertyConverter.to(cls, interpolate(value), null);
/*  496 */         array = Array.newInstance(cls, 1);
/*  497 */         Array.set(array, 0, convertedValue);
/*  499 */       } catch (ConversionException e) {
/*  501 */         throw new ConversionException('\'' + key + "' doesn't map to an array of " + cls, e);
/*      */       } 
/*      */     } 
/*  506 */     return array;
/*      */   }
/*      */   
/*      */   public List getBooleanList(String key) {
/*  522 */     return getBooleanList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getBooleanList(String key, List defaultValue) {
/*  539 */     return getList(Boolean.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public boolean[] getBooleanArray(String key) {
/*  555 */     return (boolean[])getArray(boolean.class, key);
/*      */   }
/*      */   
/*      */   public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
/*  572 */     return (boolean[])getArray(boolean.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getByteList(String key) {
/*  587 */     return getByteList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getByteList(String key, List defaultValue) {
/*  604 */     return getList(Byte.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public byte[] getByteArray(String key) {
/*  620 */     return getByteArray(key, new byte[0]);
/*      */   }
/*      */   
/*      */   public byte[] getByteArray(String key, byte[] defaultValue) {
/*  637 */     return (byte[])getArray(byte.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getShortList(String key) {
/*  652 */     return getShortList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getShortList(String key, List defaultValue) {
/*  669 */     return getList(Short.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public short[] getShortArray(String key) {
/*  685 */     return getShortArray(key, new short[0]);
/*      */   }
/*      */   
/*      */   public short[] getShortArray(String key, short[] defaultValue) {
/*  702 */     return (short[])getArray(short.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getIntegerList(String key) {
/*  718 */     return getIntegerList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getIntegerList(String key, List defaultValue) {
/*  735 */     return getList(Integer.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public int[] getIntArray(String key) {
/*  751 */     return getIntArray(key, new int[0]);
/*      */   }
/*      */   
/*      */   public int[] getIntArray(String key, int[] defaultValue) {
/*  768 */     return (int[])getArray(int.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getLongList(String key) {
/*  783 */     return getLongList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getLongList(String key, List defaultValue) {
/*  800 */     return getList(Long.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public long[] getLongArray(String key) {
/*  816 */     return getLongArray(key, new long[0]);
/*      */   }
/*      */   
/*      */   public long[] getLongArray(String key, long[] defaultValue) {
/*  833 */     return (long[])getArray(long.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getFloatList(String key) {
/*  848 */     return getFloatList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getFloatList(String key, List defaultValue) {
/*  865 */     return getList(Float.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public float[] getFloatArray(String key) {
/*  881 */     return getFloatArray(key, new float[0]);
/*      */   }
/*      */   
/*      */   public float[] getFloatArray(String key, float[] defaultValue) {
/*  898 */     return (float[])getArray(float.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getDoubleList(String key) {
/*  914 */     return getDoubleList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getDoubleList(String key, List defaultValue) {
/*  931 */     return getList(Double.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public double[] getDoubleArray(String key) {
/*  947 */     return getDoubleArray(key, new double[0]);
/*      */   }
/*      */   
/*      */   public double[] getDoubleArray(String key, double[] defaultValue) {
/*  964 */     return (double[])getArray(double.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getBigIntegerList(String key) {
/*  979 */     return getBigIntegerList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getBigIntegerList(String key, List defaultValue) {
/*  996 */     return getList(BigInteger.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public BigInteger[] getBigIntegerArray(String key) {
/* 1012 */     return getBigIntegerArray(key, new BigInteger[0]);
/*      */   }
/*      */   
/*      */   public BigInteger[] getBigIntegerArray(String key, BigInteger[] defaultValue) {
/* 1029 */     return (BigInteger[])getArray(BigInteger.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getBigDecimalList(String key) {
/* 1044 */     return getBigDecimalList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getBigDecimalList(String key, List defaultValue) {
/* 1061 */     return getList(BigDecimal.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public BigDecimal[] getBigDecimalArray(String key) {
/* 1077 */     return getBigDecimalArray(key, new BigDecimal[0]);
/*      */   }
/*      */   
/*      */   public BigDecimal[] getBigDecimalArray(String key, BigDecimal[] defaultValue) {
/* 1094 */     return (BigDecimal[])getArray(BigDecimal.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public URL getURL(String key) {
/* 1108 */     return (URL)get(URL.class, key);
/*      */   }
/*      */   
/*      */   public URL getURL(String key, URL defaultValue) {
/* 1125 */     return (URL)get(URL.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getURLList(String key) {
/* 1140 */     return getURLList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getURLList(String key, List defaultValue) {
/* 1157 */     return getList(URL.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public URL[] getURLArray(String key) {
/* 1172 */     return getURLArray(key, new URL[0]);
/*      */   }
/*      */   
/*      */   public URL[] getURLArray(String key, URL[] defaultValue) {
/* 1188 */     return (URL[])getArray(URL.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public Date getDate(String key) {
/* 1205 */     return (Date)get(Date.class, key);
/*      */   }
/*      */   
/*      */   public Date getDate(String key, String format) {
/* 1221 */     Date value = getDate(key, (Date)null, format);
/* 1222 */     if (value != null)
/* 1224 */       return value; 
/* 1226 */     if (isThrowExceptionOnMissing())
/* 1228 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/* 1232 */     return null;
/*      */   }
/*      */   
/*      */   public Date getDate(String key, Date defaultValue) {
/* 1252 */     return getDate(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public Date getDate(String key, Date defaultValue, String format) {
/* 1271 */     Object value = resolveContainerStore(key);
/* 1273 */     if (value == null)
/* 1275 */       return defaultValue; 
/*      */     try {
/* 1281 */       return PropertyConverter.toDate(interpolate(value), format);
/* 1283 */     } catch (ConversionException e) {
/* 1285 */       throw new ConversionException('\'' + key + "' doesn't map to a Date", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public List getDateList(String key) {
/* 1305 */     return getDateList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getDateList(String key, String format) {
/* 1323 */     return getDateList(key, new ArrayList(), format);
/*      */   }
/*      */   
/*      */   public List getDateList(String key, List defaultValue) {
/* 1343 */     return getDateList(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public List getDateList(String key, List defaultValue, String format) {
/*      */     List list;
/* 1362 */     Object value = getProperty(key);
/* 1366 */     if (value == null || (value instanceof String && StringUtils.isEmpty((String)value))) {
/* 1368 */       list = defaultValue;
/* 1370 */     } else if (value.getClass().isArray()) {
/* 1372 */       list = new ArrayList();
/* 1373 */       int length = Array.getLength(value);
/* 1374 */       for (int i = 0; i < length; i++)
/* 1376 */         list.add(PropertyConverter.toDate(interpolate(Array.get(value, i)), format)); 
/* 1379 */     } else if (value instanceof Collection) {
/* 1381 */       Collection values = (Collection)value;
/* 1382 */       list = new ArrayList();
/* 1384 */       Iterator it = values.iterator();
/* 1385 */       while (it.hasNext())
/* 1387 */         list.add(PropertyConverter.toDate(interpolate(it.next()), format)); 
/*      */     } else {
/*      */       try {
/* 1395 */         list = new ArrayList();
/* 1396 */         list.add(PropertyConverter.toDate(interpolate(value), format));
/* 1398 */       } catch (ConversionException e) {
/* 1400 */         throw new ConversionException('\'' + key + "' doesn't map to a list of Dates", e);
/*      */       } 
/*      */     } 
/* 1404 */     return list;
/*      */   }
/*      */   
/*      */   public Date[] getDateArray(String key) {
/* 1422 */     return getDateArray(key, new Date[0]);
/*      */   }
/*      */   
/*      */   public Date[] getDateArray(String key, String format) {
/* 1440 */     return getDateArray(key, new Date[0], format);
/*      */   }
/*      */   
/*      */   public Date[] getDateArray(String key, Date[] defaultValue) {
/* 1459 */     return getDateArray(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public Date[] getDateArray(String key, Date[] defaultValue, String format) {
/* 1478 */     List list = getDateList(key, format);
/* 1479 */     if (list.isEmpty())
/* 1481 */       return defaultValue; 
/* 1485 */     return (Date[])list.toArray((Object[])new Date[list.size()]);
/*      */   }
/*      */   
/*      */   public Calendar getCalendar(String key) {
/* 1503 */     return (Calendar)get(Calendar.class, key);
/*      */   }
/*      */   
/*      */   public Calendar getCalendar(String key, String format) {
/* 1520 */     Calendar value = getCalendar(key, (Calendar)null, format);
/* 1521 */     if (value != null)
/* 1523 */       return value; 
/* 1525 */     if (isThrowExceptionOnMissing())
/* 1527 */       throw new NoSuchElementException('\'' + key + "' doesn't map to an existing object"); 
/* 1531 */     return null;
/*      */   }
/*      */   
/*      */   public Calendar getCalendar(String key, Calendar defaultValue) {
/* 1551 */     return getCalendar(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public Calendar getCalendar(String key, Calendar defaultValue, String format) {
/* 1570 */     Object value = resolveContainerStore(key);
/* 1572 */     if (value == null)
/* 1574 */       return defaultValue; 
/*      */     try {
/* 1580 */       return PropertyConverter.toCalendar(interpolate(value), format);
/* 1582 */     } catch (ConversionException e) {
/* 1584 */       throw new ConversionException('\'' + key + "' doesn't map to a Calendar", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public List getCalendarList(String key) {
/* 1604 */     return getCalendarList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getCalendarList(String key, String format) {
/* 1622 */     return getCalendarList(key, new ArrayList(), format);
/*      */   }
/*      */   
/*      */   public List getCalendarList(String key, List defaultValue) {
/* 1642 */     return getCalendarList(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public List getCalendarList(String key, List defaultValue, String format) {
/*      */     List list;
/* 1661 */     Object value = getProperty(key);
/* 1665 */     if (value == null || (value instanceof String && StringUtils.isEmpty((String)value))) {
/* 1667 */       list = defaultValue;
/* 1669 */     } else if (value.getClass().isArray()) {
/* 1671 */       list = new ArrayList();
/* 1672 */       int length = Array.getLength(value);
/* 1673 */       for (int i = 0; i < length; i++)
/* 1675 */         list.add(PropertyConverter.toCalendar(interpolate(Array.get(value, i)), format)); 
/* 1678 */     } else if (value instanceof Collection) {
/* 1680 */       Collection values = (Collection)value;
/* 1681 */       list = new ArrayList();
/* 1683 */       Iterator it = values.iterator();
/* 1684 */       while (it.hasNext())
/* 1686 */         list.add(PropertyConverter.toCalendar(interpolate(it.next()), format)); 
/*      */     } else {
/*      */       try {
/* 1694 */         list = new ArrayList();
/* 1695 */         list.add(PropertyConverter.toCalendar(interpolate(value), format));
/* 1697 */       } catch (ConversionException e) {
/* 1699 */         throw new ConversionException('\'' + key + "' doesn't map to a list of Calendars", e);
/*      */       } 
/*      */     } 
/* 1703 */     return list;
/*      */   }
/*      */   
/*      */   public Calendar[] getCalendarArray(String key) {
/* 1721 */     return getCalendarArray(key, new Calendar[0]);
/*      */   }
/*      */   
/*      */   public Calendar[] getCalendarArray(String key, String format) {
/* 1739 */     return getCalendarArray(key, new Calendar[0], format);
/*      */   }
/*      */   
/*      */   public Calendar[] getCalendarArray(String key, Calendar[] defaultValue) {
/* 1758 */     return getCalendarArray(key, defaultValue, getDefaultDateFormat());
/*      */   }
/*      */   
/*      */   public Calendar[] getCalendarArray(String key, Calendar[] defaultValue, String format) {
/* 1777 */     List list = getCalendarList(key, format);
/* 1778 */     if (list.isEmpty())
/* 1780 */       return defaultValue; 
/* 1784 */     return (Calendar[])list.toArray((Object[])new Calendar[list.size()]);
/*      */   }
/*      */   
/*      */   private String getDefaultDateFormat() {
/* 1796 */     return getString("org.apache.commons.configuration.format.date", "yyyy-MM-dd HH:mm:ss");
/*      */   }
/*      */   
/*      */   public Locale getLocale(String key) {
/* 1810 */     return (Locale)get(Locale.class, key);
/*      */   }
/*      */   
/*      */   public Locale getLocale(String key, Locale defaultValue) {
/* 1827 */     return (Locale)get(Locale.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getLocaleList(String key) {
/* 1842 */     return getLocaleList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getLocaleList(String key, List defaultValue) {
/* 1859 */     return getList(Locale.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public Locale[] getLocaleArray(String key) {
/* 1875 */     return getLocaleArray(key, new Locale[0]);
/*      */   }
/*      */   
/*      */   public Locale[] getLocaleArray(String key, Locale[] defaultValue) {
/* 1892 */     return (Locale[])getArray(Locale.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public Color getColor(String key) {
/* 1906 */     return (Color)get(Color.class, key);
/*      */   }
/*      */   
/*      */   public Color getColor(String key, Color defaultValue) {
/* 1923 */     return (Color)get(Color.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public List getColorList(String key) {
/* 1938 */     return getColorList(key, new ArrayList());
/*      */   }
/*      */   
/*      */   public List getColorList(String key, List defaultValue) {
/* 1955 */     return getList(Color.class, key, defaultValue);
/*      */   }
/*      */   
/*      */   public Color[] getColorArray(String key) {
/* 1971 */     return getColorArray(key, new Color[0]);
/*      */   }
/*      */   
/*      */   public Color[] getColorArray(String key, Color[] defaultValue) {
/* 1988 */     return (Color[])getArray(Color.class, key, defaultValue);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\DataConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */