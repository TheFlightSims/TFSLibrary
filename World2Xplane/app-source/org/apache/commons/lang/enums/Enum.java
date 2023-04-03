/*     */ package org.apache.commons.lang.enums;
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
/* 300 */   private static final Map EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));
/*     */   
/* 305 */   private static Map cEnumClasses = new WeakHashMap();
/*     */   
/*     */   private final String iName;
/*     */   
/*     */   private final transient int iHashCode;
/*     */   
/* 325 */   protected transient String iToString = null;
/*     */   
/*     */   private static class Entry {
/* 334 */     final Map map = new HashMap();
/*     */     
/* 338 */     final Map unmodifiableMap = Collections.unmodifiableMap(this.map);
/*     */     
/* 342 */     final List list = new ArrayList(25);
/*     */     
/* 346 */     final List unmodifiableList = Collections.unmodifiableList(this.list);
/*     */   }
/*     */   
/*     */   protected Enum(String name) {
/* 368 */     init(name);
/* 369 */     this.iName = name;
/* 370 */     this.iHashCode = 7 + getEnumClass().hashCode() + 3 * name.hashCode();
/*     */   }
/*     */   
/*     */   private void init(String name) {
/*     */     Entry entry;
/* 382 */     if (StringUtils.isEmpty(name))
/* 383 */       throw new IllegalArgumentException("The Enum name must not be empty or null"); 
/* 386 */     Class enumClass = getEnumClass();
/* 387 */     if (enumClass == null)
/* 388 */       throw new IllegalArgumentException("getEnumClass() must not be null"); 
/* 390 */     Class cls = getClass();
/* 391 */     boolean ok = false;
/* 392 */     while (cls != null && cls != Enum.class && cls != ValuedEnum.class) {
/* 393 */       if (cls == enumClass) {
/* 394 */         ok = true;
/*     */         break;
/*     */       } 
/* 397 */       cls = cls.getSuperclass();
/*     */     } 
/* 399 */     if (!ok)
/* 400 */       throw new IllegalArgumentException("getEnumClass() must return a superclass of this class"); 
/* 404 */     synchronized (Enum.class) {
/* 406 */       entry = (Entry)cEnumClasses.get(enumClass);
/* 407 */       if (entry == null) {
/* 408 */         entry = createEntry(enumClass);
/* 409 */         Map myMap = new WeakHashMap();
/* 410 */         myMap.putAll(cEnumClasses);
/* 411 */         myMap.put(enumClass, entry);
/* 412 */         cEnumClasses = myMap;
/*     */       } 
/*     */     } 
/* 415 */     if (entry.map.containsKey(name))
/* 416 */       throw new IllegalArgumentException("The Enum name must be unique, '" + name + "' has already been added"); 
/* 418 */     entry.map.put(name, this);
/* 419 */     entry.list.add(this);
/*     */   }
/*     */   
/*     */   protected Object readResolve() {
/* 429 */     Entry entry = (Entry)cEnumClasses.get(getEnumClass());
/* 430 */     if (entry == null)
/* 431 */       return null; 
/* 433 */     return entry.map.get(getName());
/*     */   }
/*     */   
/*     */   protected static Enum getEnum(Class enumClass, String name) {
/* 450 */     Entry entry = getEntry(enumClass);
/* 451 */     if (entry == null)
/* 452 */       return null; 
/* 454 */     return (Enum)entry.map.get(name);
/*     */   }
/*     */   
/*     */   protected static Map getEnumMap(Class enumClass) {
/* 471 */     Entry entry = getEntry(enumClass);
/* 472 */     if (entry == null)
/* 473 */       return EMPTY_MAP; 
/* 475 */     return entry.unmodifiableMap;
/*     */   }
/*     */   
/*     */   protected static List getEnumList(Class enumClass) {
/* 493 */     Entry entry = getEntry(enumClass);
/* 494 */     if (entry == null)
/* 495 */       return Collections.EMPTY_LIST; 
/* 497 */     return entry.unmodifiableList;
/*     */   }
/*     */   
/*     */   protected static Iterator iterator(Class enumClass) {
/* 515 */     return getEnumList(enumClass).iterator();
/*     */   }
/*     */   
/*     */   private static Entry getEntry(Class enumClass) {
/* 526 */     if (enumClass == null)
/* 527 */       throw new IllegalArgumentException("The Enum Class must not be null"); 
/* 529 */     if (!Enum.class.isAssignableFrom(enumClass))
/* 530 */       throw new IllegalArgumentException("The Class must be a subclass of Enum"); 
/* 532 */     Entry entry = (Entry)cEnumClasses.get(enumClass);
/* 534 */     if (entry == null)
/*     */       try {
/* 537 */         Class.forName(enumClass.getName(), true, enumClass.getClassLoader());
/* 538 */         entry = (Entry)cEnumClasses.get(enumClass);
/* 539 */       } catch (Exception e) {} 
/* 544 */     return entry;
/*     */   }
/*     */   
/*     */   private static Entry createEntry(Class enumClass) {
/* 556 */     Entry entry = new Entry();
/* 557 */     Class cls = enumClass.getSuperclass();
/* 558 */     while (cls != null && cls != Enum.class && cls != ValuedEnum.class) {
/* 559 */       Entry loopEntry = (Entry)cEnumClasses.get(cls);
/* 560 */       if (loopEntry != null) {
/* 561 */         entry.list.addAll(loopEntry.list);
/* 562 */         entry.map.putAll(loopEntry.map);
/*     */         break;
/*     */       } 
/* 565 */       cls = (Class)cls.getSuperclass();
/*     */     } 
/* 567 */     return entry;
/*     */   }
/*     */   
/*     */   public final String getName() {
/* 577 */     return this.iName;
/*     */   }
/*     */   
/*     */   public Class getEnumClass() {
/* 591 */     return getClass();
/*     */   }
/*     */   
/*     */   public final boolean equals(Object other) {
/* 608 */     if (other == this)
/* 609 */       return true; 
/* 610 */     if (other == null)
/* 611 */       return false; 
/* 612 */     if (other.getClass() == getClass())
/* 616 */       return this.iName.equals(((Enum)other).iName); 
/* 619 */     if (!other.getClass().getName().equals(getClass().getName()))
/* 620 */       return false; 
/* 622 */     return this.iName.equals(getNameInOtherClassLoader(other));
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 632 */     return this.iHashCode;
/*     */   }
/*     */   
/*     */   public int compareTo(Object other) {
/* 652 */     if (other == this)
/* 653 */       return 0; 
/* 655 */     if (other.getClass() != getClass()) {
/* 656 */       if (other.getClass().getName().equals(getClass().getName()))
/* 657 */         return this.iName.compareTo(getNameInOtherClassLoader(other)); 
/* 659 */       throw new ClassCastException("Different enum class '" + ClassUtils.getShortClassName(other.getClass()) + "'");
/*     */     } 
/* 662 */     return this.iName.compareTo(((Enum)other).iName);
/*     */   }
/*     */   
/*     */   private String getNameInOtherClassLoader(Object other) {
/*     */     try {
/* 673 */       Method mth = other.getClass().getMethod("getName", null);
/* 674 */       String name = (String)mth.invoke(other, null);
/* 675 */       return name;
/* 676 */     } catch (NoSuchMethodException e) {
/*     */     
/* 678 */     } catch (IllegalAccessException e) {
/*     */     
/* 680 */     } catch (InvocationTargetException e) {}
/* 683 */     throw new IllegalStateException("This should not happen");
/*     */   }
/*     */   
/*     */   public String toString() {
/* 694 */     if (this.iToString == null) {
/* 695 */       String shortName = ClassUtils.getShortClassName(getEnumClass());
/* 696 */       this.iToString = shortName + "[" + getName() + "]";
/*     */     } 
/* 698 */     return this.iToString;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\enums\Enum.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */