/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.CheckedCollection;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ final class PropertyAccessor {
/*  60 */   private static final Locale LOCALE = Locale.US;
/*     */   
/*     */   private static final String IS = "is";
/*     */   
/*     */   private static final String GET = "get";
/*     */   
/*     */   private static final String SET = "set";
/*     */   
/*  84 */   private static final String[] EXCLUDES = new String[] { "clone", "finalize", "getClass", "hashCode", "notify", "notifyAll", "toString", "wait" };
/*     */   
/*  92 */   private static final Map<Class<?>, Method[]> SHARED_GETTERS = (Map)new HashMap<Class<?>, Method>();
/*     */   
/*     */   final Class<?> type;
/*     */   
/*     */   final Class<?> implementation;
/*     */   
/*     */   private final Method[] getters;
/*     */   
/*     */   private final Method[] setters;
/*     */   
/*     */   private final Map<String, Integer> mapping;
/*     */   
/*     */   PropertyAccessor(Class<?> implementation, Class<?> type) {
/* 135 */     this.implementation = implementation;
/* 136 */     this.type = type;
/* 137 */     assert type.isAssignableFrom(implementation) : implementation;
/* 138 */     this.getters = getGetters(type);
/* 139 */     this.mapping = new HashMap<String, Integer>(this.getters.length + (this.getters.length + 3) / 4);
/* 140 */     Method[] setters = null;
/* 141 */     Class<?>[] arguments = new Class[1];
/* 142 */     for (int i = 0; i < this.getters.length; i++) {
/*     */       Method method1;
/* 147 */       Integer index = Integer.valueOf(i);
/* 148 */       Method getter = this.getters[i];
/* 149 */       String name = getter.getName();
/* 150 */       int base = prefix(name).length();
/* 151 */       addMapping(name.substring(base), index);
/* 152 */       UML annotation = getter.<UML>getAnnotation(UML.class);
/* 153 */       if (annotation != null)
/* 154 */         addMapping(annotation.identifier(), index); 
/* 160 */       Class<?> returnType = getter.getReturnType();
/* 161 */       arguments[0] = returnType;
/* 162 */       if (name.length() > base) {
/* 163 */         char lo = name.charAt(base);
/* 164 */         char up = Character.toUpperCase(lo);
/* 165 */         if (lo != up) {
/* 166 */           name = "set" + up + name.substring(base + 1);
/*     */         } else {
/* 168 */           name = "set" + name.substring(base);
/*     */         } 
/*     */       } 
/*     */       try {
/* 173 */         method1 = implementation.getMethod(name, arguments);
/* 174 */       } catch (NoSuchMethodException e) {
/*     */         try {
/* 182 */           getter = implementation.getMethod(getter.getName(), (Class[])null);
/* 183 */         } catch (NoSuchMethodException error) {
/* 186 */           throw new AssertionError(error);
/*     */         } 
/* 188 */         if (returnType.equals(returnType = getter.getReturnType()))
/*     */           continue; 
/* 191 */         arguments[0] = returnType;
/*     */         try {
/* 193 */           method1 = implementation.getMethod(name, arguments);
/* 194 */         } catch (NoSuchMethodException ignore) {}
/*     */       } 
/* 198 */       if (setters == null)
/* 199 */         setters = new Method[this.getters.length]; 
/* 201 */       setters[i] = method1;
/*     */       continue;
/*     */     } 
/* 203 */     this.setters = setters;
/*     */   }
/*     */   
/*     */   private void addMapping(String name, Integer index) throws IllegalArgumentException {
/* 211 */     name = name.trim();
/* 212 */     if (name.length() != 0) {
/* 213 */       String lower = name.toLowerCase(LOCALE);
/* 214 */       Integer old = this.mapping.put(lower, index);
/* 215 */       if (old != null && !old.equals(index))
/* 216 */         throw new IllegalArgumentException(Errors.format(154, name, index, lower, old)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Class<?> getType(Class<?> implementation, String interfacePackage) {
/* 231 */     if (implementation != null && !implementation.isInterface()) {
/* 236 */       Set<Class<?>> interfaces = new LinkedHashSet<Class<?>>();
/*     */       do {
/* 238 */         getInterfaces(implementation, interfacePackage, interfaces);
/* 239 */         implementation = implementation.getSuperclass();
/* 240 */       } while (implementation != null);
/*     */       Iterator<Class<?>> it;
/* 245 */       for (it = interfaces.iterator(); it.hasNext(); ) {
/* 246 */         Class<?> candidate = it.next();
/* 247 */         for (Class<?> child : interfaces) {
/* 248 */           if (candidate != child && candidate.isAssignableFrom(child))
/* 249 */             it.remove(); 
/*     */         } 
/*     */       } 
/* 254 */       it = interfaces.iterator();
/* 255 */       if (it.hasNext()) {
/* 256 */         Class<?> candidate = it.next();
/* 257 */         if (!it.hasNext())
/* 258 */           return candidate; 
/*     */       } 
/*     */     } 
/* 264 */     return null;
/*     */   }
/*     */   
/*     */   private static void getInterfaces(Class<?> type, String interfacePackage, Collection<Class<?>> interfaces) {
/* 274 */     for (Class<?> candidate : type.getInterfaces()) {
/* 275 */       if (candidate.getName().startsWith(interfacePackage))
/* 276 */         interfaces.add(candidate); 
/* 278 */       getInterfaces(candidate, interfacePackage, interfaces);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Method[] getGetters(Class<?> type) {
/* 290 */     synchronized (SHARED_GETTERS) {
/* 291 */       Method[] getters = SHARED_GETTERS.get(type);
/* 292 */       if (getters == null) {
/* 293 */         getters = type.getMethods();
/* 294 */         int count = 0;
/* 295 */         for (int i = 0; i < getters.length; i++) {
/* 296 */           Method candidate = getters[i];
/* 297 */           if (candidate.getAnnotation(Deprecated.class) == null)
/* 301 */             if (!candidate.getReturnType().equals(void.class) && (candidate.getParameterTypes()).length == 0) {
/* 314 */               String name = candidate.getName();
/* 315 */               if (!name.startsWith("set") && !isExcluded(name))
/* 316 */                 getters[count++] = candidate; 
/*     */             }  
/*     */         } 
/* 320 */         getters = (Method[])XArray.resize((Object[])getters, count);
/* 321 */         SHARED_GETTERS.put(type, getters);
/*     */       } 
/* 323 */       return getters;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isExcluded(String name) {
/* 331 */     for (int i = 0; i < EXCLUDES.length; i++) {
/* 332 */       if (name.equals(EXCLUDES[i]))
/* 333 */         return true; 
/*     */     } 
/* 336 */     return false;
/*     */   }
/*     */   
/*     */   private static String prefix(String name) {
/* 345 */     if (name.startsWith("get"))
/* 346 */       return "get"; 
/* 348 */     if (name.startsWith("is"))
/* 349 */       return "is"; 
/* 351 */     if (name.startsWith("set"))
/* 352 */       return "set"; 
/* 354 */     return "";
/*     */   }
/*     */   
/*     */   final int count() {
/* 361 */     return this.getters.length;
/*     */   }
/*     */   
/*     */   final int indexOf(String key) {
/* 372 */     key = key.trim().toLowerCase(LOCALE);
/* 373 */     Integer index = this.mapping.get(key);
/* 374 */     return (index != null) ? index.intValue() : -1;
/*     */   }
/*     */   
/*     */   final int requiredIndexOf(String key) throws IllegalArgumentException {
/* 386 */     key = key.trim();
/* 387 */     Integer index = this.mapping.get(key.toLowerCase(LOCALE));
/* 388 */     if (index != null)
/* 389 */       return index.intValue(); 
/* 391 */     throw new IllegalArgumentException(Errors.format(185, key));
/*     */   }
/*     */   
/*     */   private static boolean isAcronym(String name, int offset) {
/* 400 */     int length = name.length();
/* 401 */     while (offset < length) {
/* 402 */       if (Character.isLowerCase(name.charAt(offset++)))
/* 403 */         return false; 
/*     */     } 
/* 406 */     return true;
/*     */   }
/*     */   
/*     */   final String name(int index) {
/* 413 */     if (index >= 0 && index < this.getters.length) {
/* 414 */       String name = this.getters[index].getName();
/* 415 */       int base = prefix(name).length();
/* 422 */       if (name.length() > base)
/* 423 */         if (isAcronym(name, base)) {
/* 424 */           name = name.substring(base);
/*     */         } else {
/* 426 */           char up = name.charAt(base);
/* 427 */           char lo = Character.toLowerCase(up);
/* 428 */           if (up != lo) {
/* 429 */             name = lo + name.substring(base + 1);
/*     */           } else {
/* 431 */             name = name.substring(base);
/*     */           } 
/*     */         }  
/* 435 */       return name;
/*     */     } 
/* 437 */     return null;
/*     */   }
/*     */   
/*     */   final Class<?> type(int index) {
/* 444 */     if (index >= 0 && index < this.getters.length)
/* 445 */       return this.getters[index].getReturnType(); 
/* 447 */     return null;
/*     */   }
/*     */   
/*     */   final boolean isWritable(int index) {
/* 454 */     return (index >= 0 && index < this.getters.length && this.setters != null && this.setters[index] != null);
/*     */   }
/*     */   
/*     */   final Object get(int index, Object metadata) {
/* 461 */     return (index >= 0 && index < this.getters.length) ? get(this.getters[index], metadata) : null;
/*     */   }
/*     */   
/*     */   private static Object get(Method method, Object metadata) {
/* 472 */     assert !method.getReturnType().equals(void.class) : method;
/*     */     try {
/* 474 */       return method.invoke(metadata, (Object[])null);
/* 475 */     } catch (IllegalAccessException e) {
/* 477 */       throw new AssertionError(e);
/* 478 */     } catch (InvocationTargetException e) {
/* 479 */       Throwable cause = e.getTargetException();
/* 480 */       if (cause instanceof RuntimeException)
/* 481 */         throw (RuntimeException)cause; 
/* 483 */       if (cause instanceof Error)
/* 484 */         throw (Error)cause; 
/* 486 */       throw new UndeclaredThrowableException(cause);
/*     */     } 
/*     */   }
/*     */   
/*     */   final Object set(int index, Object metadata, Object value) throws IllegalArgumentException, ClassCastException {
/*     */     String key;
/* 504 */     if (index >= 0 && index < this.getters.length && this.setters != null) {
/* 505 */       Method getter = this.getters[index];
/* 506 */       Method setter = this.setters[index];
/* 507 */       if (setter != null) {
/* 508 */         Object old = get(getter, metadata);
/* 509 */         set(getter, setter, metadata, new Object[] { value });
/* 510 */         return old;
/*     */       } 
/* 512 */       key = getter.getName();
/* 513 */       key = key.substring(prefix(key).length());
/*     */     } else {
/* 516 */       key = String.valueOf(index);
/*     */     } 
/* 518 */     throw new IllegalArgumentException(Errors.format(57, key));
/*     */   }
/*     */   
/*     */   private static void set(Method getter, Method setter, Object metadata, Object[] arguments) throws ClassCastException {
/* 536 */     Class<?>[] paramTypes = setter.getParameterTypes();
/* 537 */     for (int i = 0; i < paramTypes.length; i++) {
/* 538 */       Object<?> argument = (Object<?>)arguments[i];
/* 539 */       if (argument != null) {
/* 542 */         Class<?> paramType = paramTypes[i];
/* 543 */         if (!Classes.primitiveToWrapper(paramType).isInstance(argument)) {
/*     */           Collection<?> addTo;
/*     */           Class<?> elementType;
/* 566 */           if (Collection.class.isAssignableFrom(paramType) && !(argument instanceof Collection)) {
/* 568 */             addTo = (Collection)get(getter, metadata);
/* 569 */             if (addTo instanceof CheckedCollection) {
/* 570 */               elementType = ((CheckedCollection)addTo).getElementType();
/*     */             } else {
/* 572 */               Class<?> c = Classes.boundOfParameterizedAttribute(setter);
/* 573 */               if (c == null) {
/* 574 */                 c = Classes.boundOfParameterizedAttribute(getter);
/* 575 */                 if (c == null)
/* 576 */                   c = Object.class; 
/*     */               } 
/* 579 */               elementType = c;
/*     */             } 
/*     */           } else {
/* 582 */             addTo = null;
/* 583 */             elementType = paramType;
/*     */           } 
/* 590 */           Object<?> parsed = null;
/* 591 */           Exception failure = null;
/* 592 */           if (elementType.isInstance(argument)) {
/* 593 */             parsed = argument;
/* 594 */           } else if (argument instanceof CharSequence) {
/* 595 */             String text = argument.toString();
/* 596 */             if (InternationalString.class.isAssignableFrom(elementType)) {
/* 597 */               parsed = (Object<?>)new SimpleInternationalString(text);
/* 598 */             } else if (File.class.isAssignableFrom(elementType)) {
/* 599 */               parsed = (Object<?>)new File(text);
/* 600 */             } else if (URL.class.isAssignableFrom(elementType)) {
/*     */               try {
/* 601 */                 parsed = (Object<?>)new URL(text);
/* 602 */               } catch (MalformedURLException e) {
/* 603 */                 failure = e;
/*     */               } 
/* 604 */             } else if (URI.class.isAssignableFrom(elementType)) {
/*     */               try {
/* 605 */                 parsed = (Object<?>)new URI(text);
/* 606 */               } catch (URISyntaxException e) {
/* 607 */                 failure = e;
/*     */               } 
/*     */             } else {
/*     */               try {
/* 609 */                 parsed = (Object<?>)Classes.valueOf(elementType, text);
/* 610 */               } catch (RuntimeException e) {
/* 612 */                 failure = e;
/*     */               } 
/*     */             } 
/*     */           } 
/* 620 */           if (parsed == null) {
/* 621 */             ClassCastException e = new ClassCastException(Errors.format(61, argument.getClass(), elementType));
/* 623 */             e.initCause(failure);
/* 624 */             throw e;
/*     */           } 
/* 634 */           if (addTo != null) {
/* 635 */             addUnsafe(addTo, parsed);
/* 636 */             parsed = (Object<?>)addTo;
/*     */           } 
/* 638 */           arguments[i] = parsed;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     try {
/* 641 */       setter.invoke(metadata, arguments);
/* 642 */     } catch (IllegalAccessException e) {
/* 644 */       throw new AssertionError(e);
/* 645 */     } catch (InvocationTargetException e) {
/* 646 */       Throwable cause = e.getTargetException();
/* 647 */       if (cause instanceof RuntimeException)
/* 648 */         throw (RuntimeException)cause; 
/* 650 */       if (cause instanceof Error)
/* 651 */         throw (Error)cause; 
/* 653 */       throw new UndeclaredThrowableException(cause);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void addUnsafe(Collection<?> addTo, Object element) {
/* 664 */     addTo.add(element);
/*     */   }
/*     */   
/*     */   public boolean shallowEquals(Object metadata1, Object metadata2, boolean skipNulls) {
/* 681 */     assert this.type.isInstance(metadata1) : metadata1;
/* 682 */     assert this.type.isInstance(metadata2) : metadata2;
/* 683 */     for (int i = 0; i < this.getters.length; i++) {
/* 684 */       Method method = this.getters[i];
/* 685 */       Object value1 = get(method, metadata1);
/* 686 */       Object value2 = get(method, metadata2);
/* 687 */       boolean empty1 = isEmpty(value1);
/* 688 */       boolean empty2 = isEmpty(value2);
/* 689 */       if (!empty1 || !empty2)
/* 692 */         if (!Utilities.equals(value1, value2) && (
/* 693 */           !skipNulls || (!empty1 && !empty2)))
/* 694 */           return false;  
/*     */     } 
/* 698 */     return true;
/*     */   }
/*     */   
/*     */   public boolean shallowCopy(Object source, Object target, boolean skipNulls) throws UnmodifiableMetadataException {
/* 716 */     boolean success = true;
/* 717 */     assert this.type.isInstance(source) : source;
/* 718 */     assert this.implementation.isInstance(target) : target;
/* 719 */     Object[] arguments = new Object[1];
/* 720 */     for (int i = 0; i < this.getters.length; i++) {
/* 721 */       Method getter = this.getters[i];
/* 722 */       arguments[0] = get(getter, source);
/* 723 */       if (!skipNulls || !isEmpty(arguments[0])) {
/* 724 */         if (this.setters == null)
/* 725 */           return false; 
/* 727 */         Method setter = this.setters[i];
/* 728 */         if (setter != null) {
/* 729 */           set(getter, setter, target, arguments);
/*     */         } else {
/* 731 */           success = false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 735 */     return success;
/*     */   }
/*     */   
/*     */   final void freeze(Object metadata) {
/* 743 */     assert this.implementation.isInstance(metadata) : metadata;
/* 744 */     if (this.setters != null) {
/* 745 */       Object[] arguments = new Object[1];
/* 746 */       for (int i = 0; i < this.getters.length; i++) {
/* 747 */         Method setter = this.setters[i];
/* 748 */         if (setter != null) {
/* 749 */           Method getter = this.getters[i];
/* 750 */           Object source = get(getter, metadata);
/* 751 */           Object target = ModifiableMetadata.unmodifiable(source);
/* 752 */           if (source != target) {
/* 753 */             arguments[0] = target;
/* 754 */             set(getter, setter, metadata, arguments);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   final boolean isModifiable() {
/* 766 */     if (this.setters != null)
/* 767 */       return true; 
/* 769 */     for (int i = 0; i < this.getters.length; i++) {
/* 772 */       if (Cloneable.class.isAssignableFrom(this.getters[i].getReturnType()))
/* 773 */         return true; 
/*     */     } 
/* 776 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode(Object metadata) {
/* 786 */     assert this.type.isInstance(metadata) : metadata;
/* 787 */     int code = 0;
/* 788 */     for (int i = 0; i < this.getters.length; i++) {
/* 789 */       Object value = get(this.getters[i], metadata);
/* 790 */       if (!isEmpty(value))
/* 791 */         code += value.hashCode(); 
/*     */     } 
/* 794 */     return code;
/*     */   }
/*     */   
/*     */   public int count(Object metadata, int max) {
/* 801 */     assert this.type.isInstance(metadata) : metadata;
/* 802 */     int count = 0;
/* 803 */     for (int i = 0; i < this.getters.length && (
/* 804 */       isEmpty(get(this.getters[i], metadata)) || 
/* 805 */       ++count < max); i++);
/* 810 */     return count;
/*     */   }
/*     */   
/*     */   static boolean isEmpty(Object value) {
/* 818 */     return (value == null || (value instanceof Collection && ((Collection)value).isEmpty()) || (value instanceof CharSequence && value.toString().trim().length() == 0) || (value.getClass().isArray() && Array.getLength(value) == 0));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\PropertyAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */