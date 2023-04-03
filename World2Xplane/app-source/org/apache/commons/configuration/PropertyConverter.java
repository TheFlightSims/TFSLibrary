/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.collections.IteratorUtils;
/*     */ import org.apache.commons.collections.iterators.IteratorChain;
/*     */ import org.apache.commons.collections.iterators.SingletonIterator;
/*     */ import org.apache.commons.lang.BooleanUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.SystemUtils;
/*     */ 
/*     */ public final class PropertyConverter {
/*     */   static final char LIST_ESC_CHAR = '\\';
/*     */   
/*  61 */   static final String LIST_ESCAPE = String.valueOf('\\');
/*     */   
/*     */   private static final String HEX_PREFIX = "0x";
/*     */   
/*     */   private static final int HEX_RADIX = 16;
/*     */   
/*     */   private static final float JAVA_VERSION_1_5 = 1.5F;
/*     */   
/*  73 */   private static final Class[] CONSTR_ARGS = new Class[] { String.class };
/*     */   
/*     */   private static final String INTERNET_ADDRESS_CLASSNAME = "javax.mail.internet.InternetAddress";
/*     */   
/*     */   static Object to(Class cls, Object value, Object[] params) throws ConversionException {
/* 101 */     if (Boolean.class.equals(cls) || boolean.class.equals(cls))
/* 103 */       return toBoolean(value); 
/* 105 */     if (Number.class.isAssignableFrom(cls) || cls.isPrimitive()) {
/* 107 */       if (Integer.class.equals(cls) || int.class.equals(cls))
/* 109 */         return toInteger(value); 
/* 111 */       if (Long.class.equals(cls) || long.class.equals(cls))
/* 113 */         return toLong(value); 
/* 115 */       if (Byte.class.equals(cls) || byte.class.equals(cls))
/* 117 */         return toByte(value); 
/* 119 */       if (Short.class.equals(cls) || short.class.equals(cls))
/* 121 */         return toShort(value); 
/* 123 */       if (Float.class.equals(cls) || float.class.equals(cls))
/* 125 */         return toFloat(value); 
/* 127 */       if (Double.class.equals(cls) || double.class.equals(cls))
/* 129 */         return toDouble(value); 
/* 131 */       if (BigInteger.class.equals(cls))
/* 133 */         return toBigInteger(value); 
/* 135 */       if (BigDecimal.class.equals(cls))
/* 137 */         return toBigDecimal(value); 
/*     */     } else {
/* 140 */       if (Date.class.equals(cls))
/* 142 */         return toDate(value, (String)params[0]); 
/* 144 */       if (Calendar.class.equals(cls))
/* 146 */         return toCalendar(value, (String)params[0]); 
/* 148 */       if (URL.class.equals(cls))
/* 150 */         return toURL(value); 
/* 152 */       if (Locale.class.equals(cls))
/* 154 */         return toLocale(value); 
/* 156 */       if (isEnum(cls))
/* 158 */         return toEnum(value, cls); 
/* 160 */       if (Color.class.equals(cls))
/* 162 */         return toColor(value); 
/* 164 */       if (cls.getName().equals("javax.mail.internet.InternetAddress"))
/* 166 */         return toInternetAddress(value); 
/* 168 */       if (InetAddress.class.isAssignableFrom(cls))
/* 170 */         return toInetAddress(value); 
/*     */     } 
/* 173 */     throw new ConversionException("The value '" + value + "' (" + value.getClass() + ")" + " can't be converted to a " + cls.getName() + " object");
/*     */   }
/*     */   
/*     */   public static Boolean toBoolean(Object value) throws ConversionException {
/* 192 */     if (value instanceof Boolean)
/* 194 */       return (Boolean)value; 
/* 196 */     if (value instanceof String) {
/* 198 */       Boolean b = BooleanUtils.toBooleanObject((String)value);
/* 199 */       if (b == null)
/* 201 */         throw new ConversionException("The value " + value + " can't be converted to a Boolean object"); 
/* 203 */       return b;
/*     */     } 
/* 207 */     throw new ConversionException("The value " + value + " can't be converted to a Boolean object");
/*     */   }
/*     */   
/*     */   public static Byte toByte(Object value) throws ConversionException {
/* 220 */     Number n = toNumber(value, Byte.class);
/* 221 */     if (n instanceof Byte)
/* 223 */       return (Byte)n; 
/* 227 */     return new Byte(n.byteValue());
/*     */   }
/*     */   
/*     */   public static Short toShort(Object value) throws ConversionException {
/* 240 */     Number n = toNumber(value, Short.class);
/* 241 */     if (n instanceof Short)
/* 243 */       return (Short)n; 
/* 247 */     return new Short(n.shortValue());
/*     */   }
/*     */   
/*     */   public static Integer toInteger(Object value) throws ConversionException {
/* 260 */     Number n = toNumber(value, Integer.class);
/* 261 */     if (n instanceof Integer)
/* 263 */       return (Integer)n; 
/* 267 */     return new Integer(n.intValue());
/*     */   }
/*     */   
/*     */   public static Long toLong(Object value) throws ConversionException {
/* 280 */     Number n = toNumber(value, Long.class);
/* 281 */     if (n instanceof Long)
/* 283 */       return (Long)n; 
/* 287 */     return new Long(n.longValue());
/*     */   }
/*     */   
/*     */   public static Float toFloat(Object value) throws ConversionException {
/* 300 */     Number n = toNumber(value, Float.class);
/* 301 */     if (n instanceof Float)
/* 303 */       return (Float)n; 
/* 307 */     return new Float(n.floatValue());
/*     */   }
/*     */   
/*     */   public static Double toDouble(Object value) throws ConversionException {
/* 320 */     Number n = toNumber(value, Double.class);
/* 321 */     if (n instanceof Double)
/* 323 */       return (Double)n; 
/* 327 */     return new Double(n.doubleValue());
/*     */   }
/*     */   
/*     */   public static BigInteger toBigInteger(Object value) throws ConversionException {
/* 340 */     Number n = toNumber(value, BigInteger.class);
/* 341 */     if (n instanceof BigInteger)
/* 343 */       return (BigInteger)n; 
/* 347 */     return BigInteger.valueOf(n.longValue());
/*     */   }
/*     */   
/*     */   public static BigDecimal toBigDecimal(Object value) throws ConversionException {
/* 360 */     Number n = toNumber(value, BigDecimal.class);
/* 361 */     if (n instanceof BigDecimal)
/* 363 */       return (BigDecimal)n; 
/* 367 */     return new BigDecimal(n.doubleValue());
/*     */   }
/*     */   
/*     */   static Number toNumber(Object value, Class targetClass) throws ConversionException {
/* 385 */     if (value instanceof Number)
/* 387 */       return (Number)value; 
/* 391 */     String str = value.toString();
/* 392 */     if (str.startsWith("0x"))
/*     */       try {
/* 396 */         return new BigInteger(str.substring("0x".length()), 16);
/* 398 */       } catch (NumberFormatException nex) {
/* 400 */         throw new ConversionException("Could not convert " + str + " to " + targetClass.getName() + "! Invalid hex number.", nex);
/*     */       }  
/*     */     try {
/* 408 */       Constructor constr = targetClass.getConstructor(CONSTR_ARGS);
/* 409 */       return constr.newInstance(new Object[] { str });
/* 411 */     } catch (InvocationTargetException itex) {
/* 413 */       throw new ConversionException("Could not convert " + str + " to " + targetClass.getName(), itex.getTargetException());
/* 417 */     } catch (Exception ex) {
/* 420 */       throw new ConversionException("Conversion error when trying to convert " + str + " to " + targetClass.getName(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL toURL(Object value) throws ConversionException {
/* 436 */     if (value instanceof URL)
/* 438 */       return (URL)value; 
/* 440 */     if (value instanceof String)
/*     */       try {
/* 444 */         return new URL((String)value);
/* 446 */       } catch (MalformedURLException e) {
/* 448 */         throw new ConversionException("The value " + value + " can't be converted to an URL", e);
/*     */       }  
/* 453 */     throw new ConversionException("The value " + value + " can't be converted to an URL");
/*     */   }
/*     */   
/*     */   public static Locale toLocale(Object value) throws ConversionException {
/* 466 */     if (value instanceof Locale)
/* 468 */       return (Locale)value; 
/* 470 */     if (value instanceof String) {
/* 472 */       List elements = split((String)value, '_');
/* 473 */       int size = elements.size();
/* 475 */       if (size >= 1 && (((String)elements.get(0)).length() == 2 || ((String)elements.get(0)).length() == 0)) {
/* 477 */         String language = elements.get(0);
/* 478 */         String country = (size >= 2) ? elements.get(1) : "";
/* 479 */         String variant = (size >= 3) ? elements.get(2) : "";
/* 481 */         return new Locale(language, country, variant);
/*     */       } 
/* 485 */       throw new ConversionException("The value " + value + " can't be converted to a Locale");
/*     */     } 
/* 490 */     throw new ConversionException("The value " + value + " can't be converted to a Locale");
/*     */   }
/*     */   
/*     */   public static List split(String s, char delimiter, boolean trim) {
/* 507 */     if (s == null)
/* 509 */       return new ArrayList(); 
/* 512 */     List list = new ArrayList();
/* 514 */     StringBuffer token = new StringBuffer();
/* 515 */     int begin = 0;
/* 516 */     boolean inEscape = false;
/* 518 */     while (begin < s.length()) {
/* 520 */       char c = s.charAt(begin);
/* 521 */       if (inEscape) {
/* 525 */         if (c != delimiter && c != '\\')
/* 528 */           token.append('\\'); 
/* 530 */         token.append(c);
/* 531 */         inEscape = false;
/* 536 */       } else if (c == delimiter) {
/* 539 */         String str = token.toString();
/* 540 */         if (trim)
/* 542 */           str = str.trim(); 
/* 544 */         list.add(str);
/* 545 */         token = new StringBuffer();
/* 547 */       } else if (c == '\\') {
/* 550 */         inEscape = true;
/*     */       } else {
/* 554 */         token.append(c);
/*     */       } 
/* 558 */       begin++;
/*     */     } 
/* 562 */     if (inEscape)
/* 564 */       token.append('\\'); 
/* 567 */     String t = token.toString();
/* 568 */     if (trim)
/* 570 */       t = t.trim(); 
/* 572 */     list.add(t);
/* 574 */     return list;
/*     */   }
/*     */   
/*     */   public static List split(String s, char delimiter) {
/* 587 */     return split(s, delimiter, true);
/*     */   }
/*     */   
/*     */   public static String escapeDelimiters(String s, char delimiter) {
/* 601 */     String s1 = StringUtils.replace(s, LIST_ESCAPE, LIST_ESCAPE + LIST_ESCAPE);
/* 602 */     return escapeListDelimiter(s1, delimiter);
/*     */   }
/*     */   
/*     */   public static String escapeListDelimiter(String s, char delimiter) {
/* 619 */     return StringUtils.replace(s, String.valueOf(delimiter), LIST_ESCAPE + delimiter);
/*     */   }
/*     */   
/*     */   public static Color toColor(Object value) throws ConversionException {
/* 639 */     if (value instanceof Color)
/* 641 */       return (Color)value; 
/* 643 */     if (value instanceof String && !StringUtils.isBlank((String)value)) {
/* 645 */       String color = ((String)value).trim();
/* 647 */       int[] components = new int[3];
/* 650 */       int minlength = components.length * 2;
/* 651 */       if (color.length() < minlength)
/* 653 */         throw new ConversionException("The value " + value + " can't be converted to a Color"); 
/* 657 */       if (color.startsWith("#"))
/* 659 */         color = color.substring(1); 
/*     */       try {
/*     */         int alpha;
/* 665 */         for (int i = 0; i < components.length; i++)
/* 667 */           components[i] = Integer.parseInt(color.substring(2 * i, 2 * i + 2), 16); 
/* 672 */         if (color.length() >= minlength + 2) {
/* 674 */           alpha = Integer.parseInt(color.substring(minlength, minlength + 2), 16);
/*     */         } else {
/* 678 */           alpha = Color.black.getAlpha();
/*     */         } 
/* 681 */         return new Color(components[0], components[1], components[2], alpha);
/* 683 */       } catch (Exception e) {
/* 685 */         throw new ConversionException("The value " + value + " can't be converted to a Color", e);
/*     */       } 
/*     */     } 
/* 690 */     throw new ConversionException("The value " + value + " can't be converted to a Color");
/*     */   }
/*     */   
/*     */   static InetAddress toInetAddress(Object value) throws ConversionException {
/* 705 */     if (value instanceof InetAddress)
/* 707 */       return (InetAddress)value; 
/* 709 */     if (value instanceof String)
/*     */       try {
/* 713 */         return InetAddress.getByName((String)value);
/* 715 */       } catch (UnknownHostException e) {
/* 717 */         throw new ConversionException("The value " + value + " can't be converted to a InetAddress", e);
/*     */       }  
/* 722 */     throw new ConversionException("The value " + value + " can't be converted to a InetAddress");
/*     */   }
/*     */   
/*     */   static Object toInternetAddress(Object value) throws ConversionException {
/* 737 */     if (value.getClass().getName().equals("javax.mail.internet.InternetAddress"))
/* 739 */       return value; 
/* 741 */     if (value instanceof String)
/*     */       try {
/* 745 */         Constructor ctor = Class.forName("javax.mail.internet.InternetAddress").getConstructor(new Class[] { String.class });
/* 746 */         return ctor.newInstance(new Object[] { value });
/* 748 */       } catch (Exception e) {
/* 750 */         throw new ConversionException("The value " + value + " can't be converted to a InternetAddress", e);
/*     */       }  
/* 755 */     throw new ConversionException("The value " + value + " can't be converted to a InternetAddress");
/*     */   }
/*     */   
/*     */   static boolean isEnum(Class cls) {
/* 764 */     if (!SystemUtils.isJavaVersionAtLeast(1.5F))
/* 766 */       return false; 
/*     */     try {
/* 771 */       Method isEnumMethod = Class.class.getMethod("isEnum", new Class[0]);
/* 772 */       return ((Boolean)isEnumMethod.invoke(cls, new Object[0])).booleanValue();
/* 774 */     } catch (Exception e) {
/* 777 */       throw new RuntimeException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   static Object toEnum(Object value, Class cls) throws ConversionException {
/* 793 */     if (value.getClass().equals(cls))
/* 795 */       return value; 
/* 797 */     if (value instanceof String)
/*     */       try {
/* 801 */         Method valueOfMethod = cls.getMethod("valueOf", new Class[] { String.class });
/* 802 */         return valueOfMethod.invoke(null, new Object[] { value });
/* 804 */       } catch (Exception e) {
/* 806 */         throw new ConversionException("The value " + value + " can't be converted to a " + cls.getName());
/*     */       }  
/* 809 */     if (value instanceof Number)
/*     */       try {
/* 813 */         Method valuesMethod = cls.getMethod("values", new Class[0]);
/* 814 */         Object valuesArray = valuesMethod.invoke(null, new Object[0]);
/* 816 */         return Array.get(valuesArray, ((Number)value).intValue());
/* 818 */       } catch (Exception e) {
/* 820 */         throw new ConversionException("The value " + value + " can't be converted to a " + cls.getName());
/*     */       }  
/* 825 */     throw new ConversionException("The value " + value + " can't be converted to a " + cls.getName());
/*     */   }
/*     */   
/*     */   public static Date toDate(Object value, String format) throws ConversionException {
/* 839 */     if (value instanceof Date)
/* 841 */       return (Date)value; 
/* 843 */     if (value instanceof Calendar)
/* 845 */       return ((Calendar)value).getTime(); 
/* 847 */     if (value instanceof String)
/*     */       try {
/* 851 */         return (new SimpleDateFormat(format)).parse((String)value);
/* 853 */       } catch (ParseException e) {
/* 855 */         throw new ConversionException("The value " + value + " can't be converted to a Date", e);
/*     */       }  
/* 860 */     throw new ConversionException("The value " + value + " can't be converted to a Date");
/*     */   }
/*     */   
/*     */   public static Calendar toCalendar(Object value, String format) throws ConversionException {
/* 874 */     if (value instanceof Calendar)
/* 876 */       return (Calendar)value; 
/* 878 */     if (value instanceof Date) {
/* 880 */       Calendar calendar = Calendar.getInstance();
/* 881 */       calendar.setTime((Date)value);
/* 882 */       return calendar;
/*     */     } 
/* 884 */     if (value instanceof String)
/*     */       try {
/* 888 */         Calendar calendar = Calendar.getInstance();
/* 889 */         calendar.setTime((new SimpleDateFormat(format)).parse((String)value));
/* 890 */         return calendar;
/* 892 */       } catch (ParseException e) {
/* 894 */         throw new ConversionException("The value " + value + " can't be converted to a Calendar", e);
/*     */       }  
/* 899 */     throw new ConversionException("The value " + value + " can't be converted to a Calendar");
/*     */   }
/*     */   
/*     */   public static Iterator toIterator(Object value, char delimiter) {
/* 920 */     if (value == null)
/* 922 */       return (Iterator)IteratorUtils.emptyIterator(); 
/* 924 */     if (value instanceof String) {
/* 926 */       String s = (String)value;
/* 927 */       if (s.indexOf(delimiter) > 0)
/* 929 */         return split((String)value, delimiter).iterator(); 
/* 933 */       return (Iterator)new SingletonIterator(value);
/*     */     } 
/* 936 */     if (value instanceof Collection)
/* 938 */       return toIterator(((Collection)value).iterator(), delimiter); 
/* 940 */     if (value.getClass().isArray())
/* 942 */       return toIterator(IteratorUtils.arrayIterator(value), delimiter); 
/* 944 */     if (value instanceof Iterator) {
/* 946 */       Iterator iterator = (Iterator)value;
/* 947 */       IteratorChain chain = new IteratorChain();
/* 948 */       while (iterator.hasNext())
/* 950 */         chain.addIterator(toIterator(iterator.next(), delimiter)); 
/* 952 */       return (Iterator)chain;
/*     */     } 
/* 956 */     return (Iterator)new SingletonIterator(value);
/*     */   }
/*     */   
/*     */   public static Object interpolate(Object value, AbstractConfiguration config) {
/* 972 */     if (value instanceof String)
/* 974 */       return config.getSubstitutor().replace((String)value); 
/* 978 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\PropertyConverter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */