/*     */ package org.apache.commons.collections;
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
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ 
/*     */ public class BeanMap extends AbstractMap implements Cloneable {
/*     */   private transient Object bean;
/*     */   
/*  59 */   private transient HashMap readMethods = new HashMap();
/*     */   
/*  60 */   private transient HashMap writeMethods = new HashMap();
/*     */   
/*  61 */   private transient HashMap types = new HashMap();
/*     */   
/*  66 */   public static final Object[] NULL_ARGUMENTS = new Object[0];
/*     */   
/*  72 */   public static HashMap defaultTransformers = new HashMap();
/*     */   
/*     */   static {
/*  75 */     defaultTransformers.put(boolean.class, new Transformer() {
/*     */           public Object transform(Object input) {
/*  79 */             return Boolean.valueOf(input.toString());
/*     */           }
/*     */         });
/*  83 */     defaultTransformers.put(char.class, new Transformer() {
/*     */           public Object transform(Object input) {
/*  87 */             return new Character(input.toString().charAt(0));
/*     */           }
/*     */         });
/*  91 */     defaultTransformers.put(byte.class, new Transformer() {
/*     */           public Object transform(Object input) {
/*  95 */             return Byte.valueOf(input.toString());
/*     */           }
/*     */         });
/*  99 */     defaultTransformers.put(short.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 103 */             return Short.valueOf(input.toString());
/*     */           }
/*     */         });
/* 107 */     defaultTransformers.put(int.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 111 */             return Integer.valueOf(input.toString());
/*     */           }
/*     */         });
/* 115 */     defaultTransformers.put(long.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 119 */             return Long.valueOf(input.toString());
/*     */           }
/*     */         });
/* 123 */     defaultTransformers.put(float.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 127 */             return Float.valueOf(input.toString());
/*     */           }
/*     */         });
/* 131 */     defaultTransformers.put(double.class, new Transformer() {
/*     */           public Object transform(Object input) {
/* 135 */             return Double.valueOf(input.toString());
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public BeanMap(Object bean) {
/* 159 */     this.bean = bean;
/* 160 */     initialise();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 167 */     return "BeanMap<" + String.valueOf(this.bean) + ">";
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 194 */     BeanMap newMap = (BeanMap)super.clone();
/* 196 */     if (this.bean == null)
/* 199 */       return newMap; 
/* 202 */     Object newBean = null;
/* 203 */     Class beanClass = null;
/*     */     try {
/* 205 */       beanClass = this.bean.getClass();
/* 206 */       newBean = beanClass.newInstance();
/* 207 */     } catch (Exception e) {
/* 209 */       throw new CloneNotSupportedException("Unable to instantiate the underlying bean \"" + beanClass.getName() + "\": " + e);
/*     */     } 
/*     */     try {
/* 215 */       newMap.setBean(newBean);
/* 216 */     } catch (Exception exception) {
/* 217 */       throw new CloneNotSupportedException("Unable to set bean in the cloned bean map: " + exception);
/*     */     } 
/*     */     try {
/* 226 */       Iterator readableKeys = this.readMethods.keySet().iterator();
/* 227 */       while (readableKeys.hasNext()) {
/* 228 */         Object key = readableKeys.next();
/* 229 */         if (getWriteMethod(key) != null)
/* 230 */           newMap.put(key, get(key)); 
/*     */       } 
/* 233 */     } catch (Exception exception) {
/* 234 */       throw new CloneNotSupportedException("Unable to copy bean values to cloned bean map: " + exception);
/*     */     } 
/* 239 */     return newMap;
/*     */   }
/*     */   
/*     */   public void putAllWriteable(BeanMap map) {
/* 249 */     Iterator readableKeys = map.readMethods.keySet().iterator();
/* 250 */     while (readableKeys.hasNext()) {
/* 251 */       Object key = readableKeys.next();
/* 252 */       if (getWriteMethod(key) != null)
/* 253 */         put(key, map.get(key)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 268 */     if (this.bean == null)
/*     */       return; 
/* 270 */     Class beanClass = null;
/*     */     try {
/* 272 */       beanClass = this.bean.getClass();
/* 273 */       this.bean = beanClass.newInstance();
/* 275 */     } catch (Exception e) {
/* 276 */       throw new UnsupportedOperationException("Could not create new instance of class: " + beanClass);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object name) {
/* 296 */     Method method = getReadMethod(name);
/* 297 */     return (method != null);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 310 */     return super.containsValue(value);
/*     */   }
/*     */   
/*     */   public Object get(Object name) {
/* 329 */     if (this.bean != null) {
/* 330 */       Method method = getReadMethod(name);
/* 331 */       if (method != null)
/*     */         try {
/* 333 */           return method.invoke(this.bean, NULL_ARGUMENTS);
/* 335 */         } catch (IllegalAccessException e) {
/* 336 */           logWarn(e);
/* 338 */         } catch (IllegalArgumentException e) {
/* 339 */           logWarn(e);
/* 341 */         } catch (InvocationTargetException e) {
/* 342 */           logWarn(e);
/* 344 */         } catch (NullPointerException e) {
/* 345 */           logWarn(e);
/*     */         }  
/*     */     } 
/* 349 */     return null;
/*     */   }
/*     */   
/*     */   public Object put(Object name, Object value) throws IllegalArgumentException, ClassCastException {
/* 364 */     if (this.bean != null) {
/* 365 */       Object oldValue = get(name);
/* 366 */       Method method = getWriteMethod(name);
/* 367 */       if (method == null)
/* 368 */         throw new IllegalArgumentException("The bean of type: " + this.bean.getClass().getName() + " has no property called: " + name); 
/*     */       try {
/* 371 */         Object[] arguments = createWriteMethodArguments(method, value);
/* 372 */         method.invoke(this.bean, arguments);
/* 374 */         Object newValue = get(name);
/* 375 */         firePropertyChange(name, oldValue, newValue);
/* 377 */       } catch (InvocationTargetException e) {
/* 378 */         logInfo(e);
/* 379 */         throw new IllegalArgumentException(e.getMessage());
/* 381 */       } catch (IllegalAccessException e) {
/* 382 */         logInfo(e);
/* 383 */         throw new IllegalArgumentException(e.getMessage());
/*     */       } 
/* 385 */       return oldValue;
/*     */     } 
/* 387 */     return null;
/*     */   }
/*     */   
/*     */   public int size() {
/* 396 */     return this.readMethods.size();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 411 */     return UnmodifiableSet.decorate(this.readMethods.keySet());
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 422 */     return UnmodifiableSet.decorate(new AbstractSet(this) {
/*     */           private final BeanMap this$0;
/*     */           
/*     */           public Iterator iterator() {
/* 424 */             return this.this$0.entryIterator();
/*     */           }
/*     */           
/*     */           public int size() {
/* 427 */             return this.this$0.readMethods.size();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 439 */     ArrayList answer = new ArrayList(this.readMethods.size());
/* 440 */     for (Iterator iter = valueIterator(); iter.hasNext();)
/* 441 */       answer.add(iter.next()); 
/* 443 */     return UnmodifiableList.decorate(answer);
/*     */   }
/*     */   
/*     */   public Class getType(String name) {
/* 458 */     return (Class)this.types.get(name);
/*     */   }
/*     */   
/*     */   public Iterator keyIterator() {
/* 469 */     return this.readMethods.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Iterator valueIterator() {
/* 478 */     Iterator iter = keyIterator();
/* 479 */     return new Iterator(this, iter) {
/*     */         private final Iterator val$iter;
/*     */         
/*     */         private final BeanMap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 481 */           return this.val$iter.hasNext();
/*     */         }
/*     */         
/*     */         public Object next() {
/* 484 */           Object key = this.val$iter.next();
/* 485 */           return this.this$0.get(key);
/*     */         }
/*     */         
/*     */         public void remove() {
/* 488 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Iterator entryIterator() {
/* 499 */     Iterator iter = keyIterator();
/* 500 */     return new Iterator(this, iter) {
/*     */         private final Iterator val$iter;
/*     */         
/*     */         private final BeanMap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 502 */           return this.val$iter.hasNext();
/*     */         }
/*     */         
/*     */         public Object next() {
/* 505 */           Object key = this.val$iter.next();
/* 506 */           Object value = this.this$0.get(key);
/* 507 */           return new BeanMap.MyMapEntry(this.this$0, key, value);
/*     */         }
/*     */         
/*     */         public void remove() {
/* 510 */           throw new UnsupportedOperationException("remove() not supported for BeanMap");
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Object getBean() {
/* 526 */     return this.bean;
/*     */   }
/*     */   
/*     */   public void setBean(Object newBean) {
/* 536 */     this.bean = newBean;
/* 537 */     reinitialise();
/*     */   }
/*     */   
/*     */   public Method getReadMethod(String name) {
/* 547 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */   
/*     */   public Method getWriteMethod(String name) {
/* 557 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */   
/*     */   protected Method getReadMethod(Object name) {
/* 573 */     return (Method)this.readMethods.get(name);
/*     */   }
/*     */   
/*     */   protected Method getWriteMethod(Object name) {
/* 585 */     return (Method)this.writeMethods.get(name);
/*     */   }
/*     */   
/*     */   protected void reinitialise() {
/* 593 */     this.readMethods.clear();
/* 594 */     this.writeMethods.clear();
/* 595 */     this.types.clear();
/* 596 */     initialise();
/*     */   }
/*     */   
/*     */   private void initialise() {
/* 600 */     if (getBean() == null)
/*     */       return; 
/* 602 */     Class beanClass = getBean().getClass();
/*     */     try {
/* 605 */       BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
/* 606 */       PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
/* 607 */       if (propertyDescriptors != null)
/* 608 */         for (int i = 0; i < propertyDescriptors.length; i++) {
/* 609 */           PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
/* 610 */           if (propertyDescriptor != null) {
/* 611 */             String name = propertyDescriptor.getName();
/* 612 */             Method readMethod = propertyDescriptor.getReadMethod();
/* 613 */             Method writeMethod = propertyDescriptor.getWriteMethod();
/* 614 */             Class aType = propertyDescriptor.getPropertyType();
/* 616 */             if (readMethod != null)
/* 617 */               this.readMethods.put(name, readMethod); 
/* 619 */             if (writeMethod != null)
/* 620 */               this.writeMethods.put(name, writeMethod); 
/* 622 */             this.types.put(name, aType);
/*     */           } 
/*     */         }  
/* 627 */     } catch (IntrospectionException e) {
/* 628 */       logWarn(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(Object key, Object oldValue, Object newValue) {}
/*     */   
/*     */   protected static class MyMapEntry extends AbstractMapEntry {
/*     */     private BeanMap owner;
/*     */     
/*     */     protected MyMapEntry(BeanMap owner, Object key, Object value) {
/* 661 */       super(key, value);
/* 662 */       this.owner = owner;
/*     */     }
/*     */     
/*     */     public Object setValue(Object value) {
/* 672 */       Object key = getKey();
/* 673 */       Object oldValue = this.owner.get(key);
/* 675 */       this.owner.put(key, value);
/* 676 */       Object newValue = this.owner.get(key);
/* 677 */       super.setValue(newValue);
/* 678 */       return oldValue;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object[] createWriteMethodArguments(Method method, Object value) throws IllegalAccessException, ClassCastException {
/*     */     try {
/* 698 */       if (value != null) {
/* 699 */         Class[] types = method.getParameterTypes();
/* 700 */         if (types != null && types.length > 0) {
/* 701 */           Class paramType = types[0];
/* 702 */           if (!paramType.isAssignableFrom(value.getClass()))
/* 703 */             value = convertType(paramType, value); 
/*     */         } 
/*     */       } 
/* 707 */       Object[] answer = { value };
/* 708 */       return answer;
/* 710 */     } catch (InvocationTargetException e) {
/* 711 */       logInfo(e);
/* 712 */       throw new IllegalArgumentException(e.getMessage());
/* 714 */     } catch (InstantiationException e) {
/* 715 */       logInfo(e);
/* 716 */       throw new IllegalArgumentException(e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object convertType(Class newType, Object value) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/* 755 */     Class[] types = { value.getClass() };
/*     */     try {
/* 757 */       Constructor constructor = newType.getConstructor(types);
/* 758 */       Object[] arguments = { value };
/* 759 */       return constructor.newInstance(arguments);
/* 761 */     } catch (NoSuchMethodException e) {
/* 763 */       Transformer transformer = getTypeTransformer(newType);
/* 764 */       if (transformer != null)
/* 765 */         return transformer.transform(value); 
/* 767 */       return value;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Transformer getTypeTransformer(Class aType) {
/* 779 */     return (Transformer)defaultTransformers.get(aType);
/*     */   }
/*     */   
/*     */   protected void logInfo(Exception ex) {
/* 790 */     System.out.println("INFO: Exception: " + ex);
/*     */   }
/*     */   
/*     */   protected void logWarn(Exception ex) {
/* 801 */     System.out.println("WARN: Exception: " + ex);
/* 802 */     ex.printStackTrace();
/*     */   }
/*     */   
/*     */   public BeanMap() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\BeanMap.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */