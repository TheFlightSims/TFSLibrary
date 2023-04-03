/*     */ package org.apache.commons.lang.builder;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ 
/*     */ public class ReflectionToStringBuilder extends ToStringBuilder {
/*     */   public static String toString(Object object) {
/* 121 */     return toString(object, (ToStringStyle)null, false, false, (Class)null);
/*     */   }
/*     */   
/*     */   public static String toString(Object object, ToStringStyle style) {
/* 153 */     return toString(object, style, false, false, (Class)null);
/*     */   }
/*     */   
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients) {
/* 191 */     return toString(object, style, outputTransients, false, (Class)null);
/*     */   }
/*     */   
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics) {
/* 237 */     return toString(object, style, outputTransients, outputStatics, (Class)null);
/*     */   }
/*     */   
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class reflectUpToClass) {
/* 287 */     return (new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics)).toString();
/*     */   }
/*     */   
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients, Class reflectUpToClass) {
/* 334 */     return (new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients)).toString();
/*     */   }
/*     */   
/*     */   public static String toStringExclude(Object object, String excludeFieldName) {
/* 347 */     return toStringExclude(object, new String[] { excludeFieldName });
/*     */   }
/*     */   
/*     */   public static String toStringExclude(Object object, Collection excludeFieldNames) {
/* 360 */     return toStringExclude(object, toNoNullStringArray(excludeFieldNames));
/*     */   }
/*     */   
/*     */   static String[] toNoNullStringArray(Collection collection) {
/* 373 */     if (collection == null)
/* 374 */       return ArrayUtils.EMPTY_STRING_ARRAY; 
/* 376 */     return toNoNullStringArray(collection.toArray());
/*     */   }
/*     */   
/*     */   static String[] toNoNullStringArray(Object[] array) {
/* 389 */     ArrayList list = new ArrayList(array.length);
/* 390 */     for (int i = 0; i < array.length; i++) {
/* 391 */       Object e = array[i];
/* 392 */       if (e != null)
/* 393 */         list.add(e.toString()); 
/*     */     } 
/* 396 */     return list.<String>toArray(ArrayUtils.EMPTY_STRING_ARRAY);
/*     */   }
/*     */   
/*     */   public static String toStringExclude(Object object, String[] excludeFieldNames) {
/* 410 */     return (new ReflectionToStringBuilder(object)).setExcludeFieldNames(excludeFieldNames).toString();
/*     */   }
/*     */   
/*     */   private boolean appendStatics = false;
/*     */   
/*     */   private boolean appendTransients = false;
/*     */   
/*     */   private String[] excludeFieldNames;
/*     */   
/* 431 */   private Class upToClass = null;
/*     */   
/*     */   public ReflectionToStringBuilder(Object object) {
/* 448 */     super(object);
/*     */   }
/*     */   
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style) {
/* 468 */     super(object, style);
/*     */   }
/*     */   
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
/* 494 */     super(object, style, buffer);
/*     */   }
/*     */   
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients) {
/* 515 */     super(object, style, buffer);
/* 516 */     setUpToClass(reflectUpToClass);
/* 517 */     setAppendTransients(outputTransients);
/*     */   }
/*     */   
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients, boolean outputStatics) {
/* 539 */     super(object, style, buffer);
/* 540 */     setUpToClass(reflectUpToClass);
/* 541 */     setAppendTransients(outputTransients);
/* 542 */     setAppendStatics(outputStatics);
/*     */   }
/*     */   
/*     */   protected boolean accept(Field field) {
/* 558 */     if (field.getName().indexOf('$') != -1)
/* 560 */       return false; 
/* 562 */     if (Modifier.isTransient(field.getModifiers()) && !isAppendTransients())
/* 564 */       return false; 
/* 566 */     if (Modifier.isStatic(field.getModifiers()) && !isAppendStatics())
/* 568 */       return false; 
/* 570 */     if (getExcludeFieldNames() != null && Arrays.binarySearch((Object[])getExcludeFieldNames(), field.getName()) >= 0)
/* 573 */       return false; 
/* 575 */     return true;
/*     */   }
/*     */   
/*     */   protected void appendFieldsIn(Class clazz) {
/* 592 */     if (clazz.isArray()) {
/* 593 */       reflectionAppendArray(getObject());
/*     */       return;
/*     */     } 
/* 596 */     Field[] fields = clazz.getDeclaredFields();
/* 597 */     AccessibleObject.setAccessible((AccessibleObject[])fields, true);
/* 598 */     for (int i = 0; i < fields.length; i++) {
/* 599 */       Field field = fields[i];
/* 600 */       String fieldName = field.getName();
/* 601 */       if (accept(field))
/*     */         try {
/* 605 */           Object fieldValue = getValue(field);
/* 606 */           append(fieldName, fieldValue);
/* 607 */         } catch (IllegalAccessException ex) {
/* 612 */           throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public String[] getExcludeFieldNames() {
/* 622 */     return this.excludeFieldNames;
/*     */   }
/*     */   
/*     */   public Class getUpToClass() {
/* 633 */     return this.upToClass;
/*     */   }
/*     */   
/*     */   protected Object getValue(Field field) throws IllegalArgumentException, IllegalAccessException {
/* 653 */     return field.get(getObject());
/*     */   }
/*     */   
/*     */   public boolean isAppendStatics() {
/* 665 */     return this.appendStatics;
/*     */   }
/*     */   
/*     */   public boolean isAppendTransients() {
/* 676 */     return this.appendTransients;
/*     */   }
/*     */   
/*     */   public ToStringBuilder reflectionAppendArray(Object array) {
/* 689 */     getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, array);
/* 690 */     return this;
/*     */   }
/*     */   
/*     */   public void setAppendStatics(boolean appendStatics) {
/* 703 */     this.appendStatics = appendStatics;
/*     */   }
/*     */   
/*     */   public void setAppendTransients(boolean appendTransients) {
/* 715 */     this.appendTransients = appendTransients;
/*     */   }
/*     */   
/*     */   public ReflectionToStringBuilder setExcludeFieldNames(String[] excludeFieldNamesParam) {
/* 726 */     if (excludeFieldNamesParam == null) {
/* 727 */       this.excludeFieldNames = null;
/*     */     } else {
/* 729 */       this.excludeFieldNames = toNoNullStringArray((Object[])excludeFieldNamesParam);
/* 730 */       Arrays.sort((Object[])this.excludeFieldNames);
/*     */     } 
/* 732 */     return this;
/*     */   }
/*     */   
/*     */   public void setUpToClass(Class clazz) {
/* 744 */     if (clazz != null) {
/* 745 */       Object object = getObject();
/* 746 */       if (object != null && !clazz.isInstance(object))
/* 747 */         throw new IllegalArgumentException("Specified class is not a superclass of the object"); 
/*     */     } 
/* 750 */     this.upToClass = clazz;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 761 */     if (getObject() == null)
/* 762 */       return getStyle().getNullText(); 
/* 764 */     Class clazz = getObject().getClass();
/* 765 */     appendFieldsIn(clazz);
/* 766 */     while (clazz.getSuperclass() != null && clazz != getUpToClass()) {
/* 767 */       clazz = clazz.getSuperclass();
/* 768 */       appendFieldsIn(clazz);
/*     */     } 
/* 770 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\builder\ReflectionToStringBuilder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */