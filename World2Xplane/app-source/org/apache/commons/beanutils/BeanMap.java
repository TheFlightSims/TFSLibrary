/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.beans.BeanInfo;
/*     */ import java.beans.IntrospectionException;
/*     */ import java.beans.Introspector;
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public class BeanMap extends AbstractMap implements Cloneable {
/*     */   private transient Object bean;
/*     */   
/*  57 */   private transient HashMap readMethods = new HashMap();
/*     */   
/*  58 */   private transient HashMap writeMethods = new HashMap();
/*     */   
/*  59 */   private transient HashMap types = new HashMap();
/*     */   
/*  64 */   public static final Object[] NULL_ARGUMENTS = new Object[0];
/*     */   
/*  72 */   private static final Map typeTransformers = Collections.unmodifiableMap(createTypeTransformers());
/*     */   
/*  81 */   public static HashMap defaultTransformers = new HashMap() {
/*     */       public void clear() {
/*  83 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public boolean containsKey(Object key) {
/*  86 */         return BeanMap.typeTransformers.containsKey(key);
/*     */       }
/*     */       
/*     */       public boolean containsValue(Object value) {
/*  89 */         return BeanMap.typeTransformers.containsValue(value);
/*     */       }
/*     */       
/*     */       public Set entrySet() {
/*  92 */         return BeanMap.typeTransformers.entrySet();
/*     */       }
/*     */       
/*     */       public Object get(Object key) {
/*  95 */         return BeanMap.typeTransformers.get(key);
/*     */       }
/*     */       
/*     */       public boolean isEmpty() {
/*  98 */         return false;
/*     */       }
/*     */       
/*     */       public Set keySet() {
/* 101 */         return BeanMap.typeTransformers.keySet();
/*     */       }
/*     */       
/*     */       public Object put(Object key, Object value) {
/* 104 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public void putAll(Map m) {
/* 107 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public Object remove(Object key) {
/* 110 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public int size() {
/* 113 */         return BeanMap.typeTransformers.size();
/*     */       }
/*     */       
/*     */       public Collection values() {
/* 116 */         return BeanMap.typeTransformers.values();
/*     */       }
/*     */     };
/*     */   
/*     */   private static Map createTypeTransformers() {
/* 121 */     Map defaultTransformers = new HashMap();
/* 122 */     defaultTransformers.put(boolean.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 126 */             return Boolean.valueOf(input.toString());
/*     */           }
/*     */         });
/* 130 */     defaultTransformers.put(char.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 134 */             return new Character(input.toString().charAt(0));
/*     */           }
/*     */         });
/* 138 */     defaultTransformers.put(byte.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 142 */             return Byte.valueOf(input.toString());
/*     */           }
/*     */         });
/* 146 */     defaultTransformers.put(short.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 150 */             return Short.valueOf(input.toString());
/*     */           }
/*     */         });
/* 154 */     defaultTransformers.put(int.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 158 */             return Integer.valueOf(input.toString());
/*     */           }
/*     */         });
/* 162 */     defaultTransformers.put(long.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 166 */             return Long.valueOf(input.toString());
/*     */           }
/*     */         });
/* 170 */     defaultTransformers.put(float.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 174 */             return Float.valueOf(input.toString());
/*     */           }
/*     */         });
/* 178 */     defaultTransformers.put(double.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 182 */             return Double.valueOf(input.toString());
/*     */           }
/*     */         });
/* 186 */     return defaultTransformers;
/*     */   }
/*     */   
/*     */   public BeanMap(Object bean) {
/* 207 */     this.bean = bean;
/* 208 */     initialise();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 219 */     return "BeanMap<" + String.valueOf(this.bean) + ">";
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 250 */     BeanMap newMap = (BeanMap)super.clone();
/* 252 */     if (this.bean == null)
/* 255 */       return newMap; 
/* 258 */     Object newBean = null;
/* 259 */     Class beanClass = this.bean.getClass();
/*     */     try {
/* 261 */       newBean = beanClass.newInstance();
/* 262 */     } catch (Exception e) {
/* 264 */       throw new CloneNotSupportedException("Unable to instantiate the underlying bean \"" + beanClass.getName() + "\": " + e);
/*     */     } 
/*     */     try {
/* 270 */       newMap.setBean(newBean);
/* 271 */     } catch (Exception exception) {
/* 272 */       throw new CloneNotSupportedException("Unable to set bean in the cloned bean map: " + exception);
/*     */     } 
/*     */     try {
/* 281 */       Iterator readableKeys = this.readMethods.keySet().iterator();
/* 282 */       while (readableKeys.hasNext()) {
/* 283 */         Object key = readableKeys.next();
/* 284 */         if (getWriteMethod(key) != null)
/* 285 */           newMap.put(key, get(key)); 
/*     */       } 
/* 288 */     } catch (Exception exception) {
/* 289 */       throw new CloneNotSupportedException("Unable to copy bean values to cloned bean map: " + exception);
/*     */     } 
/* 294 */     return newMap;
/*     */   }
/*     */   
/*     */   public void putAllWriteable(BeanMap map) {
/* 304 */     Iterator readableKeys = map.readMethods.keySet().iterator();
/* 305 */     while (readableKeys.hasNext()) {
/* 306 */       Object key = readableKeys.next();
/* 307 */       if (getWriteMethod(key) != null)
/* 308 */         put(key, map.get(key)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 323 */     if (this.bean == null)
/*     */       return; 
/* 327 */     Class beanClass = null;
/*     */     try {
/* 329 */       beanClass = this.bean.getClass();
/* 330 */       this.bean = beanClass.newInstance();
/* 332 */     } catch (Exception e) {
/* 333 */       throw new UnsupportedOperationException("Could not create new instance of class: " + beanClass);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object name) {
/* 353 */     Method method = getReadMethod(name);
/* 354 */     return (method != null);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 367 */     return super.containsValue(value);
/*     */   }
/*     */   
/*     */   public Object get(Object name) {
/* 386 */     if (this.bean != null) {
/* 387 */       Method method = getReadMethod(name);
/* 388 */       if (method != null)
/*     */         try {
/* 390 */           return method.invoke(this.bean, NULL_ARGUMENTS);
/* 392 */         } catch (IllegalAccessException e) {
/* 393 */           logWarn(e);
/* 395 */         } catch (IllegalArgumentException e) {
/* 396 */           logWarn(e);
/* 398 */         } catch (InvocationTargetException e) {
/* 399 */           logWarn(e);
/* 401 */         } catch (NullPointerException e) {
/* 402 */           logWarn(e);
/*     */         }  
/*     */     } 
/* 406 */     return null;
/*     */   }
/*     */   
/*     */   public Object put(Object name, Object value) throws IllegalArgumentException, ClassCastException {
/* 422 */     if (this.bean != null) {
/* 423 */       Object oldValue = get(name);
/* 424 */       Method method = getWriteMethod(name);
/* 425 */       if (method == null)
/* 426 */         throw new IllegalArgumentException("The bean of type: " + this.bean.getClass().getName() + " has no property called: " + name); 
/*     */       try {
/* 430 */         Object[] arguments = createWriteMethodArguments(method, value);
/* 431 */         method.invoke(this.bean, arguments);
/* 433 */         Object newValue = get(name);
/* 434 */         firePropertyChange(name, oldValue, newValue);
/* 436 */       } catch (InvocationTargetException e) {
/* 437 */         logInfo(e);
/* 438 */         throw new IllegalArgumentException(e.getMessage());
/* 440 */       } catch (IllegalAccessException e) {
/* 441 */         logInfo(e);
/* 442 */         throw new IllegalArgumentException(e.getMessage());
/*     */       } 
/* 444 */       return oldValue;
/*     */     } 
/* 446 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 455 */     return this.readMethods.size();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 470 */     return UnmodifiableSet.decorate(this.readMethods.keySet());
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 481 */     return UnmodifiableSet.decorate(new AbstractSet(this) {
/*     */           private final BeanMap this$0;
/*     */           
/*     */           public Iterator iterator() {
/* 483 */             return this.this$0.entryIterator();
/*     */           }
/*     */           
/*     */           public int size() {
/* 486 */             return this.this$0.readMethods.size();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 498 */     ArrayList answer = new ArrayList(this.readMethods.size());
/* 499 */     for (Iterator iter = valueIterator(); iter.hasNext();)
/* 500 */       answer.add(iter.next()); 
/* 502 */     return UnmodifiableList.decorate(answer);
/*     */   }
/*     */   
/*     */   public Class getType(String name) {
/* 517 */     return (Class)this.types.get(name);
/*     */   }
/*     */   
/*     */   public Iterator keyIterator() {
/* 528 */     return this.readMethods.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Iterator valueIterator() {
/* 537 */     Iterator iter = keyIterator();
/* 538 */     return new Iterator(this, iter) {
/*     */         private final Iterator val$iter;
/*     */         
/*     */         private final BeanMap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 540 */           return this.val$iter.hasNext();
/*     */         }
/*     */         
/*     */         public Object next() {
/* 543 */           Object key = this.val$iter.next();
/* 544 */           return this.this$0.get(key);
/*     */         }
/*     */         
/*     */         public void remove() {
/* 547 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Iterator entryIterator() {
/* 558 */     Iterator iter = keyIterator();
/* 559 */     return new Iterator(this, iter) {
/*     */         private final Iterator val$iter;
/*     */         
/*     */         private final BeanMap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 561 */           return this.val$iter.hasNext();
/*     */         }
/*     */         
/*     */         public Object next() {
/* 564 */           Object key = this.val$iter.next();
/* 565 */           Object value = this.this$0.get(key);
/* 566 */           return new BeanMap.Entry(this.this$0, key, value);
/*     */         }
/*     */         
/*     */         public void remove() {
/* 569 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Object getBean() {
/* 585 */     return this.bean;
/*     */   }
/*     */   
/*     */   public void setBean(Object newBean) {
/* 595 */     this.bean = newBean;
/* 596 */     reinitialise();
/*     */   }
/*     */   
/*     */   public Method getReadMethod(String name) {
/* 606 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */   
/*     */   public Method getWriteMethod(String name) {
/* 616 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */   
/*     */   protected Method getReadMethod(Object name) {
/* 632 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */   
/*     */   protected Method getWriteMethod(Object name) {
/* 644 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */   
/*     */   protected void reinitialise() {
/* 652 */     this.readMethods.clear();
/* 653 */     this.writeMethods.clear();
/* 654 */     this.types.clear();
/* 655 */     initialise();
/*     */   }
/*     */   
/*     */   private void initialise() {
/* 659 */     if (getBean() == null)
/*     */       return; 
/* 663 */     Class beanClass = getBean().getClass();
/*     */     try {
/* 666 */       BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/* 667 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 668 */       if (propertyDescriptors != null)
/* 669 */         for (int i = 0; i < propertyDescriptors.length; i++) {
/* 670 */           PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
/* 671 */           if (propertyDescriptor != null) {
/* 672 */             String name = propertyDescriptor.getName();
/* 673 */             Method readMethod = propertyDescriptor.getReadMethod();
/* 674 */             Method writeMethod = propertyDescriptor.getWriteMethod();
/* 675 */             Class aType = propertyDescriptor.getPropertyType();
/* 677 */             if (readMethod != null)
/* 678 */               this.readMethods.put(name, readMethod); 
/* 680 */             if (writeMethod != null)
/* 681 */               this.writeMethods.put(name, writeMethod); 
/* 683 */             this.types.put(name, aType);
/*     */           } 
/*     */         }  
/* 688 */     } catch (IntrospectionException e) {
/* 689 */       logWarn(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(Object key, Object oldValue, Object newValue) {}
/*     */   
/*     */   protected static class Entry extends AbstractMapEntry {
/*     */     private BeanMap owner;
/*     */     
/*     */     protected Entry(BeanMap owner, Object key, Object value) {
/* 722 */       super(key, value);
/* 723 */       this.owner = owner;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 733 */       Object key = getKey();
/* 734 */       Object oldValue = this.owner.get(key);
/* 736 */       this.owner.put(key, value);
/* 737 */       Object newValue = this.owner.get(key);
/* 738 */       super.setValue(newValue);
/* 739 */       return oldValue;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object[] createWriteMethodArguments(Method method, Object value) throws IllegalAccessException, ClassCastException {
/*     */     try {
/* 761 */       if (value != null) {
/* 762 */         Class[] types = method.getParameterTypes();
/* 763 */         if (types != null && types.length > 0) {
/* 764 */           Class paramType = types[0];
/* 765 */           if (!paramType.isAssignableFrom(value.getClass()))
/* 766 */             value = convertType(paramType, value); 
/*     */         } 
/*     */       } 
/* 770 */       Object[] answer = { value };
/* 771 */       return answer;
/* 773 */     } catch (InvocationTargetException e) {
/* 774 */       logInfo(e);
/* 775 */       throw new IllegalArgumentException(e.getMessage());
/* 777 */     } catch (InstantiationException e) {
/* 778 */       logInfo(e);
/* 779 */       throw new IllegalArgumentException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object convertType(Class newType, Object value) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 818 */     Class[] types = { value.getClass() };
/*     */     try {
/* 820 */       Constructor constructor = newType.getConstructor(types);
/* 821 */       Object[] arguments = { value };
/* 822 */       return constructor.newInstance(arguments);
/* 824 */     } catch (NoSuchMethodException e) {
/* 826 */       Transformer transformer = getTypeTransformer(newType);
/* 827 */       if (transformer != null)
/* 828 */         return transformer.transform(value); 
/* 830 */       return value;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Transformer getTypeTransformer(Class aType) {
/* 842 */     return (Transformer)typeTransformers.get(aType);
/*     */   }
/*     */   
/*     */   protected void logInfo(Exception ex) {
/* 853 */     System.out.println("INFO: Exception: " + ex);
/*     */   }
/*     */   
/*     */   protected void logWarn(Exception ex) {
/* 864 */     System.out.println("WARN: Exception: " + ex);
/* 865 */     ex.printStackTrace();
/*     */   }
/*     */   
/*     */   public BeanMap() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\BeanMap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */