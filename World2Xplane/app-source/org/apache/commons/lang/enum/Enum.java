/*     */ package org.apache.commons.lang.enum;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import org.apache.commons.lang.ClassUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public abstract class Enum implements Comparable, Serializable {
/*     */   private static final long serialVersionUID = -487045951170455942L;
/*     */   
/* 254 */   private static final Map EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));
/*     */   
/* 259 */   private static Map cEnumClasses = new WeakHashMap();
/*     */   
/*     */   private final String iName;
/*     */   
/*     */   private final transient int iHashCode;
/*     */   
/* 279 */   protected transient String iToString = null;
/*     */   
/*     */   private static class Entry {
/* 288 */     final Map map = new HashMap();
/*     */     
/* 292 */     final Map unmodifiableMap = Collections.unmodifiableMap(this.map);
/*     */     
/* 296 */     final List list = new ArrayList(25);
/*     */     
/* 300 */     final List unmodifiableList = Collections.unmodifiableList(this.list);
/*     */   }
/*     */   
/*     */   protected Enum(String name) {
/* 322 */     init(name);
/* 323 */     this.iName = name;
/* 324 */     this.iHashCode = 7 + getEnumClass().hashCode() + 3 * name.hashCode();
/*     */   }
/*     */   
/*     */   private void init(String name) {
/*     */     Entry entry;
/* 336 */     if (StringUtils.isEmpty(name))
/* 337 */       throw new IllegalArgumentException("The Enum name must not be empty or null"); 
/* 340 */     Class enumClass = getEnumClass();
/* 341 */     if (enumClass == null)
/* 342 */       throw new IllegalArgumentException("getEnumClass() must not be null"); 
/* 344 */     Class cls = getClass();
/* 345 */     boolean ok = false;
/* 346 */     while (cls != null && cls != Enum.class && cls != ValuedEnum.class) {
/* 347 */       if (cls == enumClass) {
/* 348 */         ok = true;
/*     */         break;
/*     */       } 
/* 351 */       cls = cls.getSuperclass();
/*     */     } 
/* 353 */     if (!ok)
/* 354 */       throw new IllegalArgumentException("getEnumClass() must return a superclass of this class"); 
/* 358 */     synchronized (Enum.class) {
/* 360 */       entry = (Entry)cEnumClasses.get(enumClass);
/* 361 */       if (entry == null) {
/* 362 */         entry = createEntry(enumClass);
/* 363 */         Map myMap = new WeakHashMap();
/* 364 */         myMap.putAll(cEnumClasses);
/* 365 */         myMap.put(enumClass, entry);
/* 366 */         cEnumClasses = myMap;
/*     */       } 
/*     */     } 
/* 369 */     if (entry.map.containsKey(name))
/* 370 */       throw new IllegalArgumentException("The Enum name must be unique, '" + name + "' has already been added"); 
/* 372 */     entry.map.put(name, this);
/* 373 */     entry.list.add(this);
/*     */   }
/*     */   
/*     */   protected Object readResolve() {
/* 383 */     Entry entry = (Entry)cEnumClasses.get(getEnumClass());
/* 384 */     if (entry == null)
/* 385 */       return null; 
/* 387 */     return entry.map.get(getName());
/*     */   }
/*     */   
/*     */   protected static Enum getEnum(Class enumClass, String name) {
/* 404 */     Entry entry = getEntry(enumClass);
/* 405 */     if (entry == null)
/* 406 */       return null; 
/* 408 */     return (Enum)entry.map.get(name);
/*     */   }
/*     */   
/*     */   protected static Map getEnumMap(Class enumClass) {
/* 425 */     Entry entry = getEntry(enumClass);
/* 426 */     if (entry == null)
/* 427 */       return EMPTY_MAP; 
/* 429 */     return entry.unmodifiableMap;
/*     */   }
/*     */   
/*     */   protected static List getEnumList(Class enumClass) {
/* 447 */     Entry entry = getEntry(enumClass);
/* 448 */     if (entry == null)
/* 449 */       return Collections.EMPTY_LIST; 
/* 451 */     return entry.unmodifiableList;
/*     */   }
/*     */   
/*     */   protected static Iterator iterator(Class enumClass) {
/* 469 */     return getEnumList(enumClass).iterator();
/*     */   }
/*     */   
/*     */   private static Entry getEntry(Class enumClass) {
/* 480 */     if (enumClass == null)
/* 481 */       throw new IllegalArgumentException("The Enum Class must not be null"); 
/* 483 */     if (!Enum.class.isAssignableFrom(enumClass))
/* 484 */       throw new IllegalArgumentException("The Class must be a subclass of Enum"); 
/* 486 */     Entry entry = (Entry)cEnumClasses.get(enumClass);
/* 488 */     if (entry == null)
/*     */       try {
/* 491 */         Class.forName(enumClass.getName(), true, enumClass.getClassLoader());
/* 492 */         entry = (Entry)cEnumClasses.get(enumClass);
/* 493 */       } catch (Exception e) {} 
/* 498 */     return entry;
/*     */   }
/*     */   
/*     */   private static Entry createEntry(Class enumClass) {
/* 510 */     Entry entry = new Entry();
/* 511 */     Class cls = enumClass.getSuperclass();
/* 512 */     while (cls != null && cls != Enum.class && cls != ValuedEnum.class) {
/* 513 */       Entry loopEntry = (Entry)cEnumClasses.get(cls);
/* 514 */       if (loopEntry != null) {
/* 515 */         entry.list.addAll(loopEntry.list);
/* 516 */         entry.map.putAll(loopEntry.map);
/*     */         break;
/*     */       } 
/* 519 */       cls = (Class)cls.getSuperclass();
/*     */     } 
/* 521 */     return entry;
/*     */   }
/*     */   
/*     */   public final String getName() {
/* 531 */     return this.iName;
/*     */   }
/*     */   
/*     */   public Class getEnumClass() {
/* 545 */     return getClass();
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 562 */     if (other == this)
/* 563 */       return true; 
/* 564 */     if (other == null)
/* 565 */       return false; 
/* 566 */     if (other.getClass() == getClass())
/* 570 */       return this.iName.equals(((Enum)other).iName); 
/* 573 */     if (!other.getClass().getName().equals(getClass().getName()))
/* 574 */       return false; 
/* 576 */     return this.iName.equals(getNameInOtherClassLoader(other));
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 586 */     return this.iHashCode;
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/* 606 */     if (other == this)
/* 607 */       return 0; 
/* 609 */     if (other.getClass() != getClass()) {
/* 610 */       if (other.getClass().getName().equals(getClass().getName()))
/* 611 */         return this.iName.compareTo(getNameInOtherClassLoader(other)); 
/* 613 */       throw new ClassCastException("Different enum class '" + ClassUtils.getShortClassName(other.getClass()) + "'");
/*     */     } 
/* 616 */     return this.iName.compareTo(((Enum)other).iName);
/*     */   }
/*     */   
/*     */   private String getNameInOtherClassLoader(Object other) {
/*     */     try {
/* 627 */       Method mth = other.getClass().getMethod("getName", null);
/* 628 */       String name = (String)mth.invoke(other, null);
/* 629 */       return name;
/* 630 */     } catch (NoSuchMethodException e) {
/*     */     
/* 632 */     } catch (IllegalAccessException e) {
/*     */     
/* 634 */     } catch (InvocationTargetException e) {}
/* 637 */     throw new IllegalStateException("This should not happen");
/*     */   }
/*     */   
/*     */   public String toString() {
/* 648 */     if (this.iToString == null) {
/* 649 */       String shortName = ClassUtils.getShortClassName(getEnumClass());
/* 650 */       this.iToString = shortName + "[" + getName() + "]";
/*     */     } 
/* 652 */     return this.iToString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enum\Enum.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */