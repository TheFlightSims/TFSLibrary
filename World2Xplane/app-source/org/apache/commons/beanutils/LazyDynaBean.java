/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class LazyDynaBean implements DynaBean, Serializable {
/* 119 */   private transient Log logger = LogFactory.getLog(LazyDynaBean.class);
/*     */   
/* 122 */   protected static final BigInteger BigInteger_ZERO = new BigInteger("0");
/*     */   
/* 124 */   protected static final BigDecimal BigDecimal_ZERO = new BigDecimal("0");
/*     */   
/* 126 */   protected static final Character Character_SPACE = new Character(' ');
/*     */   
/* 128 */   protected static final Byte Byte_ZERO = new Byte((byte)0);
/*     */   
/* 130 */   protected static final Short Short_ZERO = new Short((short)0);
/*     */   
/* 132 */   protected static final Integer Integer_ZERO = new Integer(0);
/*     */   
/* 134 */   protected static final Long Long_ZERO = new Long(0L);
/*     */   
/* 136 */   protected static final Float Float_ZERO = new Float(0.0F);
/*     */   
/* 138 */   protected static final Double Double_ZERO = new Double(0.0D);
/*     */   
/*     */   protected Map values;
/*     */   
/*     */   private transient Map mapDecorator;
/*     */   
/*     */   protected MutableDynaClass dynaClass;
/*     */   
/*     */   public LazyDynaBean() {
/* 162 */     this(new LazyDynaClass());
/*     */   }
/*     */   
/*     */   public LazyDynaBean(String name) {
/* 171 */     this(new LazyDynaClass(name));
/*     */   }
/*     */   
/*     */   public LazyDynaBean(DynaClass dynaClass) {
/* 183 */     this.values = newMap();
/* 185 */     if (dynaClass instanceof MutableDynaClass) {
/* 186 */       this.dynaClass = (MutableDynaClass)dynaClass;
/*     */     } else {
/* 188 */       this.dynaClass = new LazyDynaClass(dynaClass.getName(), dynaClass.getDynaProperties());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map getMap() {
/* 207 */     if (this.mapDecorator == null)
/* 208 */       this.mapDecorator = new DynaBeanMapDecorator(this); 
/* 210 */     return this.mapDecorator;
/*     */   }
/*     */   
/*     */   public int size(String name) {
/* 222 */     if (name == null)
/* 223 */       throw new IllegalArgumentException("No property name specified"); 
/* 226 */     Object value = this.values.get(name);
/* 227 */     if (value == null)
/* 228 */       return 0; 
/* 231 */     if (value instanceof Map)
/* 232 */       return ((Map)value).size(); 
/* 235 */     if (value instanceof List)
/* 236 */       return ((List)value).size(); 
/* 239 */     if (value.getClass().isArray())
/* 240 */       return Array.getLength(value); 
/* 243 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean contains(String name, String key) {
/* 262 */     if (name == null)
/* 263 */       throw new IllegalArgumentException("No property name specified"); 
/* 266 */     Object value = this.values.get(name);
/* 267 */     if (value == null)
/* 268 */       return false; 
/* 271 */     if (value instanceof Map)
/* 272 */       return ((Map)value).containsKey(key); 
/* 275 */     return false;
/*     */   }
/*     */   
/*     */   public Object get(String name) {
/* 291 */     if (name == null)
/* 292 */       throw new IllegalArgumentException("No property name specified"); 
/* 296 */     Object value = this.values.get(name);
/* 297 */     if (value != null)
/* 298 */       return value; 
/* 302 */     if (!isDynaProperty(name))
/* 303 */       return null; 
/* 307 */     value = createProperty(name, this.dynaClass.getDynaProperty(name).getType());
/* 309 */     if (value != null)
/* 310 */       set(name, value); 
/* 313 */     return value;
/*     */   }
/*     */   
/*     */   public Object get(String name, int index) {
/* 335 */     if (!isDynaProperty(name))
/* 336 */       set(name, defaultIndexedProperty(name)); 
/* 340 */     Object indexedProperty = get(name);
/* 343 */     if (!this.dynaClass.getDynaProperty(name).isIndexed())
/* 344 */       throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]' " + this.dynaClass.getDynaProperty(name).getName()); 
/* 350 */     indexedProperty = growIndexedProperty(name, indexedProperty, index);
/* 353 */     if (indexedProperty.getClass().isArray())
/* 354 */       return Array.get(indexedProperty, index); 
/* 355 */     if (indexedProperty instanceof List)
/* 356 */       return ((List)indexedProperty).get(index); 
/* 358 */     throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]' " + indexedProperty.getClass().getName());
/*     */   }
/*     */   
/*     */   public Object get(String name, String key) {
/* 381 */     if (!isDynaProperty(name))
/* 382 */       set(name, defaultMappedProperty(name)); 
/* 386 */     Object mappedProperty = get(name);
/* 389 */     if (!this.dynaClass.getDynaProperty(name).isMapped())
/* 390 */       throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")' " + this.dynaClass.getDynaProperty(name).getType().getName()); 
/* 396 */     if (mappedProperty instanceof Map)
/* 397 */       return ((Map)mappedProperty).get(key); 
/* 399 */     throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'" + mappedProperty.getClass().getName());
/*     */   }
/*     */   
/*     */   public DynaClass getDynaClass() {
/* 414 */     return this.dynaClass;
/*     */   }
/*     */   
/*     */   public void remove(String name, String key) {
/* 430 */     if (name == null)
/* 431 */       throw new IllegalArgumentException("No property name specified"); 
/* 434 */     Object value = this.values.get(name);
/* 435 */     if (value == null)
/*     */       return; 
/* 439 */     if (value instanceof Map) {
/* 440 */       ((Map)value).remove(key);
/*     */     } else {
/* 442 */       throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'" + value.getClass().getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/* 465 */     if (!isDynaProperty(name)) {
/* 467 */       if (this.dynaClass.isRestricted())
/* 468 */         throw new IllegalArgumentException("Invalid property name '" + name + "' (DynaClass is restricted)"); 
/* 471 */       if (value == null) {
/* 472 */         this.dynaClass.add(name);
/*     */       } else {
/* 474 */         this.dynaClass.add(name, value.getClass());
/*     */       } 
/*     */     } 
/* 479 */     DynaProperty descriptor = this.dynaClass.getDynaProperty(name);
/* 481 */     if (value == null) {
/* 482 */       if (descriptor.getType().isPrimitive())
/* 483 */         throw new NullPointerException("Primitive value for '" + name + "'"); 
/* 486 */     } else if (!isAssignable(descriptor.getType(), value.getClass())) {
/* 487 */       throw new ConversionException("Cannot assign value of type '" + value.getClass().getName() + "' to property '" + name + "' of type '" + descriptor.getType().getName() + "'");
/*     */     } 
/* 495 */     this.values.put(name, value);
/*     */   }
/*     */   
/*     */   public void set(String name, int index, Object value) {
/* 518 */     if (!isDynaProperty(name))
/* 519 */       set(name, defaultIndexedProperty(name)); 
/* 523 */     Object indexedProperty = get(name);
/* 526 */     if (!this.dynaClass.getDynaProperty(name).isIndexed())
/* 527 */       throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]'" + this.dynaClass.getDynaProperty(name).getType().getName()); 
/* 533 */     indexedProperty = growIndexedProperty(name, indexedProperty, index);
/* 536 */     if (indexedProperty.getClass().isArray()) {
/* 537 */       Array.set(indexedProperty, index, value);
/* 538 */     } else if (indexedProperty instanceof List) {
/* 539 */       ((List)indexedProperty).set(index, value);
/*     */     } else {
/* 541 */       throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]' " + indexedProperty.getClass().getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(String name, String key, Object value) {
/* 565 */     if (!isDynaProperty(name))
/* 566 */       set(name, defaultMappedProperty(name)); 
/* 570 */     Object mappedProperty = get(name);
/* 573 */     if (!this.dynaClass.getDynaProperty(name).isMapped())
/* 574 */       throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'" + this.dynaClass.getDynaProperty(name).getType().getName()); 
/* 580 */     ((Map)mappedProperty).put(key, value);
/*     */   }
/*     */   
/*     */   protected Object growIndexedProperty(String name, Object indexedProperty, int index) {
/* 597 */     if (indexedProperty instanceof List) {
/* 599 */       List list = (List)indexedProperty;
/* 600 */       while (index >= list.size()) {
/* 601 */         Class contentType = getDynaClass().getDynaProperty(name).getContentType();
/* 602 */         Object value = null;
/* 603 */         if (contentType != null)
/* 604 */           value = createProperty(name + "[" + list.size() + "]", contentType); 
/* 606 */         list.add(value);
/*     */       } 
/*     */     } 
/* 612 */     if (indexedProperty.getClass().isArray()) {
/* 614 */       int length = Array.getLength(indexedProperty);
/* 615 */       if (index >= length) {
/* 616 */         Class componentType = indexedProperty.getClass().getComponentType();
/* 617 */         Object newArray = Array.newInstance(componentType, index + 1);
/* 618 */         System.arraycopy(indexedProperty, 0, newArray, 0, length);
/* 619 */         indexedProperty = newArray;
/* 620 */         set(name, indexedProperty);
/* 621 */         int newLength = Array.getLength(indexedProperty);
/* 622 */         for (int i = length; i < newLength; i++)
/* 623 */           Array.set(indexedProperty, i, createProperty(name + "[" + i + "]", componentType)); 
/*     */       } 
/*     */     } 
/* 628 */     return indexedProperty;
/*     */   }
/*     */   
/*     */   protected Object createProperty(String name, Class type) {
/* 639 */     if (type == null)
/* 640 */       return null; 
/* 644 */     if (type.isArray() || List.class.isAssignableFrom(type))
/* 645 */       return createIndexedProperty(name, type); 
/* 648 */     if (Map.class.isAssignableFrom(type))
/* 649 */       return createMappedProperty(name, type); 
/* 652 */     if (DynaBean.class.isAssignableFrom(type))
/* 653 */       return createDynaBeanProperty(name, type); 
/* 656 */     if (type.isPrimitive())
/* 657 */       return createPrimitiveProperty(name, type); 
/* 660 */     if (Number.class.isAssignableFrom(type))
/* 661 */       return createNumberProperty(name, type); 
/* 664 */     return createOtherProperty(name, type);
/*     */   }
/*     */   
/*     */   protected Object createIndexedProperty(String name, Class type) {
/* 677 */     Object indexedProperty = null;
/* 679 */     if (type == null) {
/* 681 */       indexedProperty = defaultIndexedProperty(name);
/* 683 */     } else if (type.isArray()) {
/* 685 */       indexedProperty = Array.newInstance(type.getComponentType(), 0);
/* 687 */     } else if (List.class.isAssignableFrom(type)) {
/* 688 */       if (type.isInterface()) {
/* 689 */         indexedProperty = defaultIndexedProperty(name);
/*     */       } else {
/*     */         try {
/* 692 */           indexedProperty = type.newInstance();
/* 694 */         } catch (Exception ex) {
/* 695 */           throw new IllegalArgumentException("Error instantiating indexed property of type '" + type.getName() + "' for '" + name + "' " + ex);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 702 */       throw new IllegalArgumentException("Non-indexed property of type '" + type.getName() + "' for '" + name + "'");
/*     */     } 
/* 706 */     return indexedProperty;
/*     */   }
/*     */   
/*     */   protected Object createMappedProperty(String name, Class type) {
/* 719 */     Object mappedProperty = null;
/* 721 */     if (type == null) {
/* 723 */       mappedProperty = defaultMappedProperty(name);
/* 725 */     } else if (type.isInterface()) {
/* 727 */       mappedProperty = defaultMappedProperty(name);
/* 729 */     } else if (Map.class.isAssignableFrom(type)) {
/*     */       try {
/* 731 */         mappedProperty = type.newInstance();
/* 733 */       } catch (Exception ex) {
/* 734 */         throw new IllegalArgumentException("Error instantiating mapped property of type '" + type.getName() + "' for '" + name + "' " + ex);
/*     */       } 
/*     */     } else {
/* 740 */       throw new IllegalArgumentException("Non-mapped property of type '" + type.getName() + "' for '" + name + "'");
/*     */     } 
/* 744 */     return mappedProperty;
/*     */   }
/*     */   
/*     */   protected Object createDynaBeanProperty(String name, Class type) {
/*     */     try {
/* 756 */       return type.newInstance();
/* 758 */     } catch (Exception ex) {
/* 759 */       if (logger().isWarnEnabled())
/* 760 */         logger().warn("Error instantiating DynaBean property of type '" + type.getName() + "' for '" + name + "' " + ex); 
/* 763 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object createPrimitiveProperty(String name, Class type) {
/* 775 */     if (type == boolean.class)
/* 776 */       return Boolean.FALSE; 
/* 777 */     if (type == int.class)
/* 778 */       return Integer_ZERO; 
/* 779 */     if (type == long.class)
/* 780 */       return Long_ZERO; 
/* 781 */     if (type == double.class)
/* 782 */       return Double_ZERO; 
/* 783 */     if (type == float.class)
/* 784 */       return Float_ZERO; 
/* 785 */     if (type == byte.class)
/* 786 */       return Byte_ZERO; 
/* 787 */     if (type == short.class)
/* 788 */       return Short_ZERO; 
/* 789 */     if (type == char.class)
/* 790 */       return Character_SPACE; 
/* 792 */     return null;
/*     */   }
/*     */   
/*     */   protected Object createNumberProperty(String name, Class type) {
/* 805 */     return null;
/*     */   }
/*     */   
/*     */   protected Object createOtherProperty(String name, Class type) {
/* 817 */     if (type == Object.class || type == String.class || type == Boolean.class || type == Character.class || Date.class.isAssignableFrom(type))
/* 823 */       return null; 
/*     */     try {
/* 828 */       return type.newInstance();
/* 830 */     } catch (Exception ex) {
/* 831 */       if (logger().isWarnEnabled())
/* 832 */         logger().warn("Error instantiating property of type '" + type.getName() + "' for '" + name + "' " + ex); 
/* 834 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object defaultIndexedProperty(String name) {
/* 849 */     return new ArrayList();
/*     */   }
/*     */   
/*     */   protected Map defaultMappedProperty(String name) {
/* 863 */     return new HashMap();
/*     */   }
/*     */   
/*     */   protected boolean isDynaProperty(String name) {
/* 874 */     if (name == null)
/* 875 */       throw new IllegalArgumentException("No property name specified"); 
/* 879 */     if (this.dynaClass instanceof LazyDynaClass)
/* 880 */       return ((LazyDynaClass)this.dynaClass).isDynaProperty(name); 
/* 884 */     return !(this.dynaClass.getDynaProperty(name) == null);
/*     */   }
/*     */   
/*     */   protected boolean isAssignable(Class dest, Class source) {
/* 898 */     if (dest.isAssignableFrom(source) || (dest == boolean.class && source == Boolean.class) || (dest == byte.class && source == Byte.class) || (dest == char.class && source == Character.class) || (dest == double.class && source == Double.class) || (dest == float.class && source == Float.class) || (dest == int.class && source == Integer.class) || (dest == long.class && source == Long.class) || (dest == short.class && source == Short.class))
/* 907 */       return true; 
/* 909 */     return false;
/*     */   }
/*     */   
/*     */   protected Map newMap() {
/* 919 */     return new HashMap();
/*     */   }
/*     */   
/*     */   private Log logger() {
/* 926 */     if (this.logger == null)
/* 927 */       this.logger = LogFactory.getLog(LazyDynaBean.class); 
/* 929 */     return this.logger;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\LazyDynaBean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */