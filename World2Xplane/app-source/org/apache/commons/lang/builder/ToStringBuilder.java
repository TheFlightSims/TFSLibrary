/*      */ package org.apache.commons.lang.builder;
/*      */ 
/*      */ import org.apache.commons.lang.BooleanUtils;
/*      */ import org.apache.commons.lang.ObjectUtils;
/*      */ 
/*      */ public class ToStringBuilder {
/*   98 */   private static volatile ToStringStyle defaultStyle = ToStringStyle.DEFAULT_STYLE;
/*      */   
/*      */   private final StringBuffer buffer;
/*      */   
/*      */   private final Object object;
/*      */   
/*      */   private final ToStringStyle style;
/*      */   
/*      */   public static ToStringStyle getDefaultStyle() {
/*  121 */     return defaultStyle;
/*      */   }
/*      */   
/*      */   public static void setDefaultStyle(ToStringStyle style) {
/*  140 */     if (style == null)
/*  141 */       throw new IllegalArgumentException("The style must not be null"); 
/*  143 */     defaultStyle = style;
/*      */   }
/*      */   
/*      */   public static String reflectionToString(Object object) {
/*  156 */     return ReflectionToStringBuilder.toString(object);
/*      */   }
/*      */   
/*      */   public static String reflectionToString(Object object, ToStringStyle style) {
/*  169 */     return ReflectionToStringBuilder.toString(object, style);
/*      */   }
/*      */   
/*      */   public static String reflectionToString(Object object, ToStringStyle style, boolean outputTransients) {
/*  183 */     return ReflectionToStringBuilder.toString(object, style, outputTransients, false, null);
/*      */   }
/*      */   
/*      */   public static String reflectionToString(Object object, ToStringStyle style, boolean outputTransients, Class reflectUpToClass) {
/*  203 */     return ReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
/*      */   }
/*      */   
/*      */   public ToStringBuilder(Object object) {
/*  229 */     this(object, null, null);
/*      */   }
/*      */   
/*      */   public ToStringBuilder(Object object, ToStringStyle style) {
/*  241 */     this(object, style, null);
/*      */   }
/*      */   
/*      */   public ToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
/*  256 */     if (style == null)
/*  257 */       style = getDefaultStyle(); 
/*  259 */     if (buffer == null)
/*  260 */       buffer = new StringBuffer(512); 
/*  262 */     this.buffer = buffer;
/*  263 */     this.style = style;
/*  264 */     this.object = object;
/*  266 */     style.appendStart(buffer, object);
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(boolean value) {
/*  279 */     this.style.append(this.buffer, (String)null, value);
/*  280 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(boolean[] array) {
/*  293 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  294 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(byte value) {
/*  307 */     this.style.append(this.buffer, (String)null, value);
/*  308 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(byte[] array) {
/*  321 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  322 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(char value) {
/*  335 */     this.style.append(this.buffer, (String)null, value);
/*  336 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(char[] array) {
/*  349 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  350 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(double value) {
/*  363 */     this.style.append(this.buffer, (String)null, value);
/*  364 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(double[] array) {
/*  377 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  378 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(float value) {
/*  391 */     this.style.append(this.buffer, (String)null, value);
/*  392 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(float[] array) {
/*  405 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  406 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(int value) {
/*  419 */     this.style.append(this.buffer, (String)null, value);
/*  420 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(int[] array) {
/*  433 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  434 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(long value) {
/*  447 */     this.style.append(this.buffer, (String)null, value);
/*  448 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(long[] array) {
/*  461 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  462 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(Object obj) {
/*  475 */     this.style.append(this.buffer, (String)null, obj, (Boolean)null);
/*  476 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(Object[] array) {
/*  489 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  490 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(short value) {
/*  503 */     this.style.append(this.buffer, (String)null, value);
/*  504 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(short[] array) {
/*  517 */     this.style.append(this.buffer, (String)null, array, (Boolean)null);
/*  518 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, boolean value) {
/*  530 */     this.style.append(this.buffer, fieldName, value);
/*  531 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, boolean[] array) {
/*  543 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  544 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, boolean[] array, boolean fullDetail) {
/*  563 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  564 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, byte value) {
/*  576 */     this.style.append(this.buffer, fieldName, value);
/*  577 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, byte[] array) {
/*  588 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  589 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, byte[] array, boolean fullDetail) {
/*  608 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  609 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, char value) {
/*  621 */     this.style.append(this.buffer, fieldName, value);
/*  622 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, char[] array) {
/*  634 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  635 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, char[] array, boolean fullDetail) {
/*  654 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  655 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, double value) {
/*  667 */     this.style.append(this.buffer, fieldName, value);
/*  668 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, double[] array) {
/*  680 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  681 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, double[] array, boolean fullDetail) {
/*  700 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  701 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, float value) {
/*  713 */     this.style.append(this.buffer, fieldName, value);
/*  714 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, float[] array) {
/*  726 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  727 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, float[] array, boolean fullDetail) {
/*  746 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  747 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, int value) {
/*  759 */     this.style.append(this.buffer, fieldName, value);
/*  760 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, int[] array) {
/*  772 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  773 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, int[] array, boolean fullDetail) {
/*  792 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  793 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, long value) {
/*  805 */     this.style.append(this.buffer, fieldName, value);
/*  806 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, long[] array) {
/*  818 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  819 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, long[] array, boolean fullDetail) {
/*  838 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  839 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, Object obj) {
/*  851 */     this.style.append(this.buffer, fieldName, obj, (Boolean)null);
/*  852 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, Object obj, boolean fullDetail) {
/*  866 */     this.style.append(this.buffer, fieldName, obj, BooleanUtils.toBooleanObject(fullDetail));
/*  867 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, Object[] array) {
/*  879 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  880 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, Object[] array, boolean fullDetail) {
/*  899 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  900 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, short value) {
/*  912 */     this.style.append(this.buffer, fieldName, value);
/*  913 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, short[] array) {
/*  925 */     this.style.append(this.buffer, fieldName, array, (Boolean)null);
/*  926 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder append(String fieldName, short[] array, boolean fullDetail) {
/*  945 */     this.style.append(this.buffer, fieldName, array, BooleanUtils.toBooleanObject(fullDetail));
/*  946 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder appendAsObjectToString(Object object) {
/*  959 */     ObjectUtils.identityToString(getStringBuffer(), object);
/*  960 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder appendSuper(String superToString) {
/*  978 */     if (superToString != null)
/*  979 */       this.style.appendSuper(this.buffer, superToString); 
/*  981 */     return this;
/*      */   }
/*      */   
/*      */   public ToStringBuilder appendToString(String toString) {
/* 1012 */     if (toString != null)
/* 1013 */       this.style.appendToString(this.buffer, toString); 
/* 1015 */     return this;
/*      */   }
/*      */   
/*      */   public Object getObject() {
/* 1025 */     return this.object;
/*      */   }
/*      */   
/*      */   public StringBuffer getStringBuffer() {
/* 1034 */     return this.buffer;
/*      */   }
/*      */   
/*      */   public ToStringStyle getStyle() {
/* 1046 */     return this.style;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1060 */     if (getObject() == null) {
/* 1061 */       getStringBuffer().append(getStyle().getNullText());
/*      */     } else {
/* 1063 */       this.style.appendEnd(getStringBuffer(), getObject());
/*      */     } 
/* 1065 */     return getStringBuffer().toString();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\ToStringBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */