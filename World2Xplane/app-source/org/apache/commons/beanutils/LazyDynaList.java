/*     */ package org.apache.commons.beanutils;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class LazyDynaList extends ArrayList {
/*     */   private DynaClass elementDynaClass;
/*     */   
/*     */   private transient WrapDynaClass wrapDynaClass;
/*     */   
/*     */   private Class elementType;
/*     */   
/*     */   private Class elementDynaBeanType;
/*     */   
/*     */   public LazyDynaList() {}
/*     */   
/*     */   public LazyDynaList(int capacity) {
/* 207 */     super(capacity);
/*     */   }
/*     */   
/*     */   public LazyDynaList(DynaClass elementDynaClass) {
/* 219 */     setElementDynaClass(elementDynaClass);
/*     */   }
/*     */   
/*     */   public LazyDynaList(Class elementType) {
/* 230 */     setElementType(elementType);
/*     */   }
/*     */   
/*     */   public LazyDynaList(Collection collection) {
/* 240 */     super(collection.size());
/* 241 */     addAll(collection);
/*     */   }
/*     */   
/*     */   public LazyDynaList(Object[] array) {
/* 251 */     super(array.length);
/* 252 */     for (int i = 0; i < array.length; i++)
/* 253 */       add(array[i]); 
/*     */   }
/*     */   
/*     */   public void add(int index, Object element) {
/* 272 */     DynaBean dynaBean = transform(element);
/* 274 */     growList(index);
/* 276 */     super.add(index, dynaBean);
/*     */   }
/*     */   
/*     */   public boolean add(Object element) {
/* 288 */     DynaBean dynaBean = transform(element);
/* 290 */     return super.add(dynaBean);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection collection) {
/* 302 */     if (collection == null || collection.size() == 0)
/* 303 */       return false; 
/* 306 */     ensureCapacity(size() + collection.size());
/* 308 */     Iterator iterator = collection.iterator();
/* 309 */     while (iterator.hasNext())
/* 310 */       add(iterator.next()); 
/* 313 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection collection) {
/* 331 */     if (collection == null || collection.size() == 0)
/* 332 */       return false; 
/* 335 */     ensureCapacity(((index > size()) ? index : size()) + collection.size());
/* 340 */     if (size() == 0)
/* 341 */       transform(collection.iterator().next()); 
/* 344 */     growList(index);
/* 346 */     Iterator iterator = collection.iterator();
/* 347 */     while (iterator.hasNext())
/* 348 */       add(index++, iterator.next()); 
/* 351 */     return true;
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 367 */     growList(index + 1);
/* 369 */     return super.get(index);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object element) {
/* 386 */     DynaBean dynaBean = transform(element);
/* 388 */     growList(index + 1);
/* 390 */     return super.set(index, dynaBean);
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 411 */     if (size() == 0 && this.elementType == null)
/* 412 */       return (Object[])new LazyDynaBean[0]; 
/* 415 */     Object[] array = (Object[])Array.newInstance(this.elementType, size());
/* 416 */     for (int i = 0; i < size(); i++) {
/* 417 */       if (Map.class.isAssignableFrom(this.elementType)) {
/* 418 */         array[i] = ((LazyDynaMap)get(i)).getMap();
/* 419 */       } else if (DynaBean.class.isAssignableFrom(this.elementType)) {
/* 420 */         array[i] = get(i);
/*     */       } else {
/* 422 */         array[i] = ((WrapDynaBean)get(i)).getInstance();
/*     */       } 
/*     */     } 
/* 425 */     return array;
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] model) {
/* 438 */     Class arrayType = model.getClass().getComponentType();
/* 439 */     Object[] array = (Object[])Array.newInstance(arrayType, size());
/* 441 */     if (size() == 0 && this.elementType == null)
/* 442 */       return (Object[])new LazyDynaBean[0]; 
/* 445 */     if (DynaBean.class.isAssignableFrom(arrayType)) {
/* 446 */       for (int i = 0; i < size(); i++)
/* 447 */         array[i] = get(i); 
/* 449 */       return array;
/*     */     } 
/* 452 */     if (arrayType.isAssignableFrom(this.elementType)) {
/* 453 */       for (int i = 0; i < size(); i++) {
/* 454 */         if (Map.class.isAssignableFrom(this.elementType)) {
/* 455 */           array[i] = ((LazyDynaMap)get(i)).getMap();
/* 456 */         } else if (DynaBean.class.isAssignableFrom(this.elementType)) {
/* 457 */           array[i] = get(i);
/*     */         } else {
/* 459 */           array[i] = ((WrapDynaBean)get(i)).getInstance();
/*     */         } 
/*     */       } 
/* 462 */       return array;
/*     */     } 
/* 465 */     throw new IllegalArgumentException("Invalid array type: " + arrayType.getName() + " - not compatible with '" + this.elementType.getName());
/*     */   }
/*     */   
/*     */   public DynaBean[] toDynaBeanArray() {
/* 481 */     if (size() == 0 && this.elementDynaBeanType == null)
/* 482 */       return (DynaBean[])new LazyDynaBean[0]; 
/* 485 */     DynaBean[] array = (DynaBean[])Array.newInstance(this.elementDynaBeanType, size());
/* 486 */     for (int i = 0; i < size(); i++)
/* 487 */       array[i] = (DynaBean)get(i); 
/* 489 */     return array;
/*     */   }
/*     */   
/*     */   public void setElementType(Class elementType) {
/* 502 */     if (elementType == null)
/* 503 */       throw new IllegalArgumentException("Element Type is missing"); 
/* 506 */     boolean changeType = (this.elementType != null && !this.elementType.equals(elementType));
/* 507 */     if (changeType && size() > 0)
/* 508 */       throw new IllegalStateException("Element Type cannot be reset"); 
/* 511 */     this.elementType = elementType;
/* 514 */     Object object = null;
/*     */     try {
/* 516 */       object = elementType.newInstance();
/* 517 */     } catch (Exception e) {
/* 518 */       throw new IllegalArgumentException("Error creating type: " + elementType.getName() + " - " + e);
/*     */     } 
/* 523 */     DynaBean dynaBean = null;
/* 524 */     if (Map.class.isAssignableFrom(elementType)) {
/* 525 */       dynaBean = new LazyDynaMap((Map)object);
/* 526 */       this.elementDynaClass = dynaBean.getDynaClass();
/* 527 */     } else if (DynaBean.class.isAssignableFrom(elementType)) {
/* 528 */       dynaBean = (DynaBean)object;
/* 529 */       this.elementDynaClass = dynaBean.getDynaClass();
/*     */     } else {
/* 531 */       dynaBean = new WrapDynaBean(object);
/* 532 */       this.wrapDynaClass = (WrapDynaClass)dynaBean.getDynaClass();
/*     */     } 
/* 535 */     this.elementDynaBeanType = dynaBean.getClass();
/* 538 */     if (WrapDynaBean.class.isAssignableFrom(this.elementDynaBeanType)) {
/* 539 */       this.elementType = ((WrapDynaBean)dynaBean).getInstance().getClass();
/* 540 */     } else if (LazyDynaMap.class.isAssignableFrom(this.elementDynaBeanType)) {
/* 541 */       this.elementType = ((LazyDynaMap)dynaBean).getMap().getClass();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setElementDynaClass(DynaClass elementDynaClass) {
/* 555 */     if (elementDynaClass == null)
/* 556 */       throw new IllegalArgumentException("Element DynaClass is missing"); 
/* 559 */     if (size() > 0)
/* 560 */       throw new IllegalStateException("Element DynaClass cannot be reset"); 
/*     */     try {
/* 565 */       DynaBean dynaBean = elementDynaClass.newInstance();
/* 566 */       this.elementDynaBeanType = dynaBean.getClass();
/* 567 */       if (WrapDynaBean.class.isAssignableFrom(this.elementDynaBeanType)) {
/* 568 */         this.elementType = ((WrapDynaBean)dynaBean).getInstance().getClass();
/* 569 */         this.wrapDynaClass = (WrapDynaClass)elementDynaClass;
/* 570 */       } else if (LazyDynaMap.class.isAssignableFrom(this.elementDynaBeanType)) {
/* 571 */         this.elementType = ((LazyDynaMap)dynaBean).getMap().getClass();
/* 572 */         this.elementDynaClass = elementDynaClass;
/*     */       } else {
/* 574 */         this.elementType = dynaBean.getClass();
/* 575 */         this.elementDynaClass = elementDynaClass;
/*     */       } 
/* 577 */     } catch (Exception e) {
/* 578 */       throw new IllegalArgumentException("Error creating DynaBean from " + elementDynaClass.getClass().getName() + " - " + e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void growList(int requiredSize) {
/* 597 */     if (requiredSize < size())
/*     */       return; 
/* 601 */     ensureCapacity(requiredSize + 1);
/* 603 */     for (int i = size(); i < requiredSize; i++) {
/* 604 */       DynaBean dynaBean = transform((Object)null);
/* 605 */       super.add(dynaBean);
/*     */     } 
/*     */   }
/*     */   
/*     */   private DynaBean transform(Object element) {
/* 624 */     DynaBean dynaBean = null;
/* 625 */     Class newDynaBeanType = null;
/* 626 */     Class newElementType = null;
/* 629 */     if (element == null) {
/* 633 */       if (this.elementType == null)
/* 634 */         setElementDynaClass(new LazyDynaClass()); 
/* 638 */       if (getDynaClass() == null)
/* 639 */         setElementType(this.elementType); 
/*     */       try {
/* 644 */         dynaBean = getDynaClass().newInstance();
/* 645 */         newDynaBeanType = dynaBean.getClass();
/* 646 */       } catch (Exception e) {
/* 647 */         throw new IllegalArgumentException("Error creating DynaBean: " + getDynaClass().getClass().getName() + " - " + e);
/*     */       } 
/*     */     } else {
/* 655 */       newElementType = element.getClass();
/* 656 */       if (Map.class.isAssignableFrom(element.getClass())) {
/* 657 */         dynaBean = new LazyDynaMap((Map)element);
/* 658 */       } else if (DynaBean.class.isAssignableFrom(element.getClass())) {
/* 659 */         dynaBean = (DynaBean)element;
/*     */       } else {
/* 661 */         dynaBean = new WrapDynaBean(element);
/*     */       } 
/* 664 */       newDynaBeanType = dynaBean.getClass();
/*     */     } 
/* 669 */     newElementType = dynaBean.getClass();
/* 670 */     if (WrapDynaBean.class.isAssignableFrom(newDynaBeanType)) {
/* 671 */       newElementType = ((WrapDynaBean)dynaBean).getInstance().getClass();
/* 672 */     } else if (LazyDynaMap.class.isAssignableFrom(newDynaBeanType)) {
/* 673 */       newElementType = ((LazyDynaMap)dynaBean).getMap().getClass();
/*     */     } 
/* 678 */     if (this.elementType != null && !newElementType.equals(this.elementType))
/* 679 */       throw new IllegalArgumentException("Element Type " + newElementType + " doesn't match other elements " + this.elementType); 
/* 683 */     return dynaBean;
/*     */   }
/*     */   
/*     */   private DynaClass getDynaClass() {
/* 691 */     return (this.elementDynaClass == null) ? this.wrapDynaClass : this.elementDynaClass;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\LazyDynaList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */