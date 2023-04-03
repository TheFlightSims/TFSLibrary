/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Array;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.URL;
/*     */ import java.sql.Date;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.beanutils.converters.ArrayConverter;
/*     */ import org.apache.commons.beanutils.converters.BigDecimalConverter;
/*     */ import org.apache.commons.beanutils.converters.BigIntegerConverter;
/*     */ import org.apache.commons.beanutils.converters.BooleanConverter;
/*     */ import org.apache.commons.beanutils.converters.ByteConverter;
/*     */ import org.apache.commons.beanutils.converters.CalendarConverter;
/*     */ import org.apache.commons.beanutils.converters.CharacterConverter;
/*     */ import org.apache.commons.beanutils.converters.ClassConverter;
/*     */ import org.apache.commons.beanutils.converters.ConverterFacade;
/*     */ import org.apache.commons.beanutils.converters.DateConverter;
/*     */ import org.apache.commons.beanutils.converters.DoubleConverter;
/*     */ import org.apache.commons.beanutils.converters.FileConverter;
/*     */ import org.apache.commons.beanutils.converters.FloatConverter;
/*     */ import org.apache.commons.beanutils.converters.IntegerConverter;
/*     */ import org.apache.commons.beanutils.converters.LongConverter;
/*     */ import org.apache.commons.beanutils.converters.ShortConverter;
/*     */ import org.apache.commons.beanutils.converters.SqlDateConverter;
/*     */ import org.apache.commons.beanutils.converters.SqlTimeConverter;
/*     */ import org.apache.commons.beanutils.converters.SqlTimestampConverter;
/*     */ import org.apache.commons.beanutils.converters.StringConverter;
/*     */ import org.apache.commons.beanutils.converters.URLConverter;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ConvertUtilsBean {
/* 133 */   private static final Integer ZERO = new Integer(0);
/*     */   
/* 134 */   private static final Character SPACE = new Character(' ');
/*     */   
/*     */   protected static ConvertUtilsBean getInstance() {
/* 142 */     return BeanUtilsBean.getInstance().getConvertUtils();
/*     */   }
/*     */   
/* 152 */   private WeakFastHashMap converters = new WeakFastHashMap();
/*     */   
/* 157 */   private Log log = LogFactory.getLog(ConvertUtils.class);
/*     */   
/*     */   private Boolean defaultBoolean;
/*     */   
/*     */   private Byte defaultByte;
/*     */   
/*     */   private Character defaultCharacter;
/*     */   
/*     */   private Double defaultDouble;
/*     */   
/*     */   private Float defaultFloat;
/*     */   
/*     */   private Integer defaultInteger;
/*     */   
/*     */   private Long defaultLong;
/*     */   
/*     */   public ConvertUtilsBean() {
/* 175 */     this.defaultBoolean = Boolean.FALSE;
/* 205 */     this.defaultByte = new Byte((byte)0);
/* 235 */     this.defaultCharacter = new Character(' ');
/* 267 */     this.defaultDouble = new Double(0.0D);
/* 297 */     this.defaultFloat = new Float(0.0F);
/* 327 */     this.defaultInteger = new Integer(0);
/* 357 */     this.defaultLong = new Long(0L);
/*     */     this.converters.setFast(false);
/*     */     deregister();
/*     */     this.converters.setFast(true);
/*     */   }
/*     */   
/*     */   public boolean getDefaultBoolean() {
/*     */     return this.defaultBoolean.booleanValue();
/*     */   }
/*     */   
/*     */   public void setDefaultBoolean(boolean newDefaultBoolean) {
/*     */     this.defaultBoolean = newDefaultBoolean ? Boolean.TRUE : Boolean.FALSE;
/*     */     register((Converter)new BooleanConverter(this.defaultBoolean), boolean.class);
/*     */     register((Converter)new BooleanConverter(this.defaultBoolean), Boolean.class);
/*     */   }
/*     */   
/*     */   public byte getDefaultByte() {
/*     */     return this.defaultByte.byteValue();
/*     */   }
/*     */   
/*     */   public void setDefaultByte(byte newDefaultByte) {
/*     */     this.defaultByte = new Byte(newDefaultByte);
/*     */     register((Converter)new ByteConverter(this.defaultByte), byte.class);
/*     */     register((Converter)new ByteConverter(this.defaultByte), Byte.class);
/*     */   }
/*     */   
/*     */   public char getDefaultCharacter() {
/*     */     return this.defaultCharacter.charValue();
/*     */   }
/*     */   
/*     */   public void setDefaultCharacter(char newDefaultCharacter) {
/*     */     this.defaultCharacter = new Character(newDefaultCharacter);
/*     */     register((Converter)new CharacterConverter(this.defaultCharacter), char.class);
/*     */     register((Converter)new CharacterConverter(this.defaultCharacter), Character.class);
/*     */   }
/*     */   
/*     */   public double getDefaultDouble() {
/*     */     return this.defaultDouble.doubleValue();
/*     */   }
/*     */   
/*     */   public void setDefaultDouble(double newDefaultDouble) {
/*     */     this.defaultDouble = new Double(newDefaultDouble);
/*     */     register((Converter)new DoubleConverter(this.defaultDouble), double.class);
/*     */     register((Converter)new DoubleConverter(this.defaultDouble), Double.class);
/*     */   }
/*     */   
/*     */   public float getDefaultFloat() {
/*     */     return this.defaultFloat.floatValue();
/*     */   }
/*     */   
/*     */   public void setDefaultFloat(float newDefaultFloat) {
/*     */     this.defaultFloat = new Float(newDefaultFloat);
/*     */     register((Converter)new FloatConverter(this.defaultFloat), float.class);
/*     */     register((Converter)new FloatConverter(this.defaultFloat), Float.class);
/*     */   }
/*     */   
/*     */   public int getDefaultInteger() {
/*     */     return this.defaultInteger.intValue();
/*     */   }
/*     */   
/*     */   public void setDefaultInteger(int newDefaultInteger) {
/*     */     this.defaultInteger = new Integer(newDefaultInteger);
/*     */     register((Converter)new IntegerConverter(this.defaultInteger), int.class);
/*     */     register((Converter)new IntegerConverter(this.defaultInteger), Integer.class);
/*     */   }
/*     */   
/*     */   public long getDefaultLong() {
/* 366 */     return this.defaultLong.longValue();
/*     */   }
/*     */   
/*     */   public void setDefaultLong(long newDefaultLong) {
/* 376 */     this.defaultLong = new Long(newDefaultLong);
/* 377 */     register((Converter)new LongConverter(this.defaultLong), long.class);
/* 378 */     register((Converter)new LongConverter(this.defaultLong), Long.class);
/*     */   }
/*     */   
/* 387 */   private static Short defaultShort = new Short((short)0);
/*     */   
/*     */   static Class array$Ljava$lang$String;
/*     */   
/*     */   public short getDefaultShort() {
/* 396 */     return defaultShort.shortValue();
/*     */   }
/*     */   
/*     */   public void setDefaultShort(short newDefaultShort) {
/* 406 */     defaultShort = new Short(newDefaultShort);
/* 407 */     register((Converter)new ShortConverter(defaultShort), short.class);
/* 408 */     register((Converter)new ShortConverter(defaultShort), Short.class);
/*     */   }
/*     */   
/*     */   public String convert(Object value) {
/* 426 */     if (value == null)
/* 427 */       return null; 
/* 428 */     if (value.getClass().isArray()) {
/* 429 */       if (Array.getLength(value) < 1)
/* 430 */         return null; 
/* 432 */       value = Array.get(value, 0);
/* 433 */       if (value == null)
/* 434 */         return null; 
/* 436 */       Converter converter1 = lookup(String.class);
/* 437 */       return (String)converter1.convert(String.class, value);
/*     */     } 
/* 440 */     Converter converter = lookup(String.class);
/* 441 */     return (String)converter.convert(String.class, value);
/*     */   }
/*     */   
/*     */   public Object convert(String value, Class clazz) {
/* 459 */     if (this.log.isDebugEnabled())
/* 460 */       this.log.debug("Convert string '" + value + "' to class '" + clazz.getName() + "'"); 
/* 463 */     Converter converter = lookup(clazz);
/* 464 */     if (converter == null)
/* 465 */       converter = lookup(String.class); 
/* 467 */     if (this.log.isTraceEnabled())
/* 468 */       this.log.trace("  Using converter " + converter); 
/* 470 */     return converter.convert(clazz, value);
/*     */   }
/*     */   
/*     */   public Object convert(String[] values, Class clazz) {
/* 490 */     Class type = clazz;
/* 491 */     if (clazz.isArray())
/* 492 */       type = clazz.getComponentType(); 
/* 494 */     if (this.log.isDebugEnabled())
/* 495 */       this.log.debug("Convert String[" + values.length + "] to class '" + type.getName() + "[]'"); 
/* 498 */     Converter converter = lookup(type);
/* 499 */     if (converter == null)
/* 500 */       converter = lookup(String.class); 
/* 502 */     if (this.log.isTraceEnabled())
/* 503 */       this.log.trace("  Using converter " + converter); 
/* 505 */     Object array = Array.newInstance(type, values.length);
/* 506 */     for (int i = 0; i < values.length; i++)
/* 507 */       Array.set(array, i, converter.convert(type, values[i])); 
/* 509 */     return array;
/*     */   }
/*     */   
/*     */   public Object convert(Object value, Class targetType) {
/* 526 */     Class sourceType = (value == null) ? null : value.getClass();
/* 528 */     if (this.log.isDebugEnabled())
/* 529 */       if (value == null) {
/* 530 */         this.log.debug("Convert null value to type '" + targetType.getName() + "'");
/*     */       } else {
/* 533 */         this.log.debug("Convert type '" + sourceType.getName() + "' value '" + value + "' to type '" + targetType.getName() + "'");
/*     */       }  
/* 538 */     Object converted = value;
/* 539 */     Converter converter = lookup(sourceType, targetType);
/* 540 */     if (converter != null) {
/* 541 */       if (this.log.isTraceEnabled())
/* 542 */         this.log.trace("  Using converter " + converter); 
/* 544 */       converted = converter.convert(targetType, value);
/*     */     } 
/* 546 */     if (targetType == String.class && converted != null && !(converted instanceof String)) {
/* 552 */       converter = lookup(String.class);
/* 553 */       if (converter != null) {
/* 554 */         if (this.log.isTraceEnabled())
/* 555 */           this.log.trace("  Using converter " + converter); 
/* 557 */         converted = converter.convert(String.class, converted);
/*     */       } 
/* 561 */       if (converted != null && !(converted instanceof String))
/* 562 */         converted = converted.toString(); 
/*     */     } 
/* 566 */     return converted;
/*     */   }
/*     */   
/*     */   public void deregister() {
/* 576 */     this.converters.clear();
/* 578 */     registerPrimitives(false);
/* 579 */     registerStandard(false, false);
/* 580 */     registerOther(true);
/* 581 */     registerArrays(false, 0);
/* 582 */     register(BigDecimal.class, (Converter)new BigDecimalConverter());
/* 583 */     register(BigInteger.class, (Converter)new BigIntegerConverter());
/*     */   }
/*     */   
/*     */   public void register(boolean throwException, boolean defaultNull, int defaultArraySize) {
/* 602 */     registerPrimitives(throwException);
/* 603 */     registerStandard(throwException, defaultNull);
/* 604 */     registerOther(throwException);
/* 605 */     registerArrays(throwException, defaultArraySize);
/*     */   }
/*     */   
/*     */   private void registerPrimitives(boolean throwException) {
/* 627 */     register(boolean.class, throwException ? (Converter)new BooleanConverter() : (Converter)new BooleanConverter(Boolean.FALSE));
/* 628 */     register(byte.class, throwException ? (Converter)new ByteConverter() : (Converter)new ByteConverter(ZERO));
/* 629 */     register(char.class, throwException ? (Converter)new CharacterConverter() : (Converter)new CharacterConverter(SPACE));
/* 630 */     register(double.class, throwException ? (Converter)new DoubleConverter() : (Converter)new DoubleConverter(ZERO));
/* 631 */     register(float.class, throwException ? (Converter)new FloatConverter() : (Converter)new FloatConverter(ZERO));
/* 632 */     register(int.class, throwException ? (Converter)new IntegerConverter() : (Converter)new IntegerConverter(ZERO));
/* 633 */     register(long.class, throwException ? (Converter)new LongConverter() : (Converter)new LongConverter(ZERO));
/* 634 */     register(short.class, throwException ? (Converter)new ShortConverter() : (Converter)new ShortConverter(ZERO));
/*     */   }
/*     */   
/*     */   private void registerStandard(boolean throwException, boolean defaultNull) {
/* 664 */     Number defaultNumber = defaultNull ? null : ZERO;
/* 665 */     BigDecimal bigDecDeflt = defaultNull ? null : new BigDecimal("0.0");
/* 666 */     BigInteger bigIntDeflt = defaultNull ? null : new BigInteger("0");
/* 667 */     Boolean booleanDefault = defaultNull ? null : Boolean.FALSE;
/* 668 */     Character charDefault = defaultNull ? null : SPACE;
/* 669 */     String stringDefault = defaultNull ? null : "";
/* 671 */     register(BigDecimal.class, throwException ? (Converter)new BigDecimalConverter() : (Converter)new BigDecimalConverter(bigDecDeflt));
/* 672 */     register(BigInteger.class, throwException ? (Converter)new BigIntegerConverter() : (Converter)new BigIntegerConverter(bigIntDeflt));
/* 673 */     register(Boolean.class, throwException ? (Converter)new BooleanConverter() : (Converter)new BooleanConverter(booleanDefault));
/* 674 */     register(Byte.class, throwException ? (Converter)new ByteConverter() : (Converter)new ByteConverter(defaultNumber));
/* 675 */     register(Character.class, throwException ? (Converter)new CharacterConverter() : (Converter)new CharacterConverter(charDefault));
/* 676 */     register(Double.class, throwException ? (Converter)new DoubleConverter() : (Converter)new DoubleConverter(defaultNumber));
/* 677 */     register(Float.class, throwException ? (Converter)new FloatConverter() : (Converter)new FloatConverter(defaultNumber));
/* 678 */     register(Integer.class, throwException ? (Converter)new IntegerConverter() : (Converter)new IntegerConverter(defaultNumber));
/* 679 */     register(Long.class, throwException ? (Converter)new LongConverter() : (Converter)new LongConverter(defaultNumber));
/* 680 */     register(Short.class, throwException ? (Converter)new ShortConverter() : (Converter)new ShortConverter(defaultNumber));
/* 681 */     register(String.class, throwException ? (Converter)new StringConverter() : (Converter)new StringConverter(stringDefault));
/*     */   }
/*     */   
/*     */   private void registerOther(boolean throwException) {
/* 704 */     register(Class.class, throwException ? (Converter)new ClassConverter() : (Converter)new ClassConverter(null));
/* 705 */     register(Date.class, throwException ? (Converter)new DateConverter() : (Converter)new DateConverter(null));
/* 706 */     register(Calendar.class, throwException ? (Converter)new CalendarConverter() : (Converter)new CalendarConverter(null));
/* 707 */     register(File.class, throwException ? (Converter)new FileConverter() : (Converter)new FileConverter(null));
/* 708 */     register(Date.class, throwException ? (Converter)new SqlDateConverter() : (Converter)new SqlDateConverter(null));
/* 709 */     register(Time.class, throwException ? (Converter)new SqlTimeConverter() : (Converter)new SqlTimeConverter(null));
/* 710 */     register(Timestamp.class, throwException ? (Converter)new SqlTimestampConverter() : (Converter)new SqlTimestampConverter(null));
/* 711 */     register(URL.class, throwException ? (Converter)new URLConverter() : (Converter)new URLConverter(null));
/*     */   }
/*     */   
/*     */   private void registerArrays(boolean throwException, int defaultArraySize) {
/* 728 */     registerArrayConverter(boolean.class, (Converter)new BooleanConverter(), throwException, defaultArraySize);
/* 729 */     registerArrayConverter(byte.class, (Converter)new ByteConverter(), throwException, defaultArraySize);
/* 730 */     registerArrayConverter(char.class, (Converter)new CharacterConverter(), throwException, defaultArraySize);
/* 731 */     registerArrayConverter(double.class, (Converter)new DoubleConverter(), throwException, defaultArraySize);
/* 732 */     registerArrayConverter(float.class, (Converter)new FloatConverter(), throwException, defaultArraySize);
/* 733 */     registerArrayConverter(int.class, (Converter)new IntegerConverter(), throwException, defaultArraySize);
/* 734 */     registerArrayConverter(long.class, (Converter)new LongConverter(), throwException, defaultArraySize);
/* 735 */     registerArrayConverter(short.class, (Converter)new ShortConverter(), throwException, defaultArraySize);
/* 738 */     registerArrayConverter(BigDecimal.class, (Converter)new BigDecimalConverter(), throwException, defaultArraySize);
/* 739 */     registerArrayConverter(BigInteger.class, (Converter)new BigIntegerConverter(), throwException, defaultArraySize);
/* 740 */     registerArrayConverter(Boolean.class, (Converter)new BooleanConverter(), throwException, defaultArraySize);
/* 741 */     registerArrayConverter(Byte.class, (Converter)new ByteConverter(), throwException, defaultArraySize);
/* 742 */     registerArrayConverter(Character.class, (Converter)new CharacterConverter(), throwException, defaultArraySize);
/* 743 */     registerArrayConverter(Double.class, (Converter)new DoubleConverter(), throwException, defaultArraySize);
/* 744 */     registerArrayConverter(Float.class, (Converter)new FloatConverter(), throwException, defaultArraySize);
/* 745 */     registerArrayConverter(Integer.class, (Converter)new IntegerConverter(), throwException, defaultArraySize);
/* 746 */     registerArrayConverter(Long.class, (Converter)new LongConverter(), throwException, defaultArraySize);
/* 747 */     registerArrayConverter(Short.class, (Converter)new ShortConverter(), throwException, defaultArraySize);
/* 748 */     registerArrayConverter(String.class, (Converter)new StringConverter(), throwException, defaultArraySize);
/* 751 */     registerArrayConverter(Class.class, (Converter)new ClassConverter(), throwException, defaultArraySize);
/* 752 */     registerArrayConverter(Date.class, (Converter)new DateConverter(), throwException, defaultArraySize);
/* 753 */     registerArrayConverter(Calendar.class, (Converter)new DateConverter(), throwException, defaultArraySize);
/* 754 */     registerArrayConverter(File.class, (Converter)new FileConverter(), throwException, defaultArraySize);
/* 755 */     registerArrayConverter(Date.class, (Converter)new SqlDateConverter(), throwException, defaultArraySize);
/* 756 */     registerArrayConverter(Time.class, (Converter)new SqlTimeConverter(), throwException, defaultArraySize);
/* 757 */     registerArrayConverter(Timestamp.class, (Converter)new SqlTimestampConverter(), throwException, defaultArraySize);
/* 758 */     registerArrayConverter(URL.class, (Converter)new URLConverter(), throwException, defaultArraySize);
/*     */   }
/*     */   
/*     */   private void registerArrayConverter(Class componentType, Converter componentConverter, boolean throwException, int defaultArraySize) {
/*     */     ArrayConverter arrayConverter1;
/* 774 */     Class arrayType = Array.newInstance(componentType, 0).getClass();
/* 775 */     Converter arrayConverter = null;
/* 776 */     if (throwException) {
/* 777 */       arrayConverter1 = new ArrayConverter(arrayType, componentConverter);
/*     */     } else {
/* 779 */       arrayConverter1 = new ArrayConverter(arrayType, componentConverter, defaultArraySize);
/*     */     } 
/* 781 */     register(arrayType, (Converter)arrayConverter1);
/*     */   }
/*     */   
/*     */   private void register(Class clazz, Converter converter) {
/* 786 */     register((Converter)new ConverterFacade(converter), clazz);
/*     */   }
/*     */   
/*     */   public void deregister(Class clazz) {
/* 797 */     this.converters.remove(clazz);
/*     */   }
/*     */   
/*     */   public Converter lookup(Class clazz) {
/* 812 */     return (Converter)this.converters.get(clazz);
/*     */   }
/*     */   
/*     */   public Converter lookup(Class sourceType, Class targetType) {
/* 827 */     if (targetType == null)
/* 828 */       throw new IllegalArgumentException("Target type is missing"); 
/* 830 */     if (sourceType == null)
/* 831 */       return lookup(targetType); 
/* 834 */     Converter converter = null;
/* 836 */     if (targetType == String.class) {
/* 837 */       converter = lookup(sourceType);
/* 838 */       if (converter == null && (sourceType.isArray() || Collection.class.isAssignableFrom(sourceType)))
/* 840 */         converter = lookup((array$Ljava$lang$String == null) ? (array$Ljava$lang$String = class$("[Ljava.lang.String;")) : array$Ljava$lang$String); 
/* 842 */       if (converter == null)
/* 843 */         converter = lookup(String.class); 
/* 845 */       return converter;
/*     */     } 
/* 849 */     if (targetType == ((array$Ljava$lang$String == null) ? (array$Ljava$lang$String = class$("[Ljava.lang.String;")) : array$Ljava$lang$String)) {
/* 850 */       if (sourceType.isArray() || Collection.class.isAssignableFrom(sourceType))
/* 851 */         converter = lookup(sourceType); 
/* 853 */       if (converter == null)
/* 854 */         converter = lookup((array$Ljava$lang$String == null) ? (array$Ljava$lang$String = class$("[Ljava.lang.String;")) : array$Ljava$lang$String); 
/* 856 */       return converter;
/*     */     } 
/* 859 */     return lookup(targetType);
/*     */   }
/*     */   
/*     */   public void register(Converter converter, Class clazz) {
/* 873 */     this.converters.put(clazz, converter);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\ConvertUtilsBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */