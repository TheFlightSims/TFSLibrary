/*     */ package org.jdom;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class AttributeList extends AbstractList implements List, Serializable {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: AttributeList.java,v $ $Revision: 1.24 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*     */   
/*     */   private static final int INITIAL_ARRAY_SIZE = 5;
/*     */   
/*     */   private Attribute[] elementData;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private Element parent;
/*     */   
/*     */   private AttributeList() {}
/*     */   
/*     */   AttributeList(Element parent) {
/* 102 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   final void uncheckedAddAttribute(Attribute a) {
/* 112 */     a.parent = this.parent;
/* 113 */     ensureCapacity(this.size + 1);
/* 114 */     this.elementData[this.size++] = a;
/* 115 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public boolean add(Object obj) {
/* 127 */     if (obj instanceof Attribute) {
/* 128 */       Attribute attribute = (Attribute)obj;
/* 129 */       int duplicate = indexOfDuplicate(attribute);
/* 130 */       if (duplicate < 0) {
/* 131 */         add(size(), attribute);
/*     */       } else {
/* 134 */         set(duplicate, attribute);
/*     */       } 
/*     */     } else {
/* 137 */       if (obj == null)
/* 138 */         throw new IllegalAddException("Cannot add null attribute"); 
/* 141 */       throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
/*     */     } 
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public void add(int index, Object obj) {
/* 158 */     if (obj instanceof Attribute) {
/* 159 */       Attribute attribute = (Attribute)obj;
/* 160 */       int duplicate = indexOfDuplicate(attribute);
/* 161 */       if (duplicate >= 0)
/* 162 */         throw new IllegalAddException("Cannot add duplicate attribute"); 
/* 164 */       add(index, attribute);
/*     */     } else {
/* 166 */       if (obj == null)
/* 167 */         throw new IllegalAddException("Cannot add null attribute"); 
/* 170 */       throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
/*     */     } 
/* 174 */     this.modCount++;
/*     */   }
/*     */   
/*     */   void add(int index, Attribute attribute) {
/* 186 */     if (attribute.getParent() != null)
/* 187 */       throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\""); 
/* 192 */     String reason = Verifier.checkNamespaceCollision(attribute, this.parent);
/* 193 */     if (reason != null)
/* 194 */       throw new IllegalAddException(this.parent, attribute, reason); 
/* 197 */     if (index < 0 || index > this.size)
/* 198 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 202 */     attribute.setParent(this.parent);
/* 204 */     ensureCapacity(this.size + 1);
/* 205 */     if (index == this.size) {
/* 206 */       this.elementData[this.size++] = attribute;
/*     */     } else {
/* 208 */       System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
/* 209 */       this.elementData[index] = attribute;
/* 210 */       this.size++;
/*     */     } 
/* 212 */     this.modCount++;
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection collection) {
/* 223 */     return addAll(size(), collection);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection collection) {
/* 238 */     if (index < 0 || index > this.size)
/* 239 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 243 */     if (collection == null || collection.size() == 0)
/* 244 */       return false; 
/* 246 */     ensureCapacity(size() + collection.size());
/* 248 */     int count = 0;
/*     */     try {
/* 251 */       Iterator i = collection.iterator();
/* 252 */       while (i.hasNext()) {
/* 253 */         Object obj = i.next();
/* 254 */         add(index + count, obj);
/* 255 */         count++;
/*     */       } 
/* 258 */     } catch (RuntimeException exception) {
/* 259 */       for (int i = 0; i < count; i++)
/* 260 */         remove(index); 
/* 262 */       throw exception;
/*     */     } 
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 272 */     if (this.elementData != null) {
/* 273 */       for (int i = 0; i < this.size; i++) {
/* 274 */         Attribute attribute = this.elementData[i];
/* 275 */         attribute.setParent(null);
/*     */       } 
/* 277 */       this.elementData = null;
/* 278 */       this.size = 0;
/*     */     } 
/* 280 */     this.modCount++;
/*     */   }
/*     */   
/*     */   void clearAndSet(Collection collection) {
/* 291 */     Attribute[] old = this.elementData;
/* 292 */     int oldSize = this.size;
/* 294 */     this.elementData = null;
/* 295 */     this.size = 0;
/* 297 */     if (collection != null && collection.size() != 0) {
/* 298 */       ensureCapacity(collection.size());
/*     */       try {
/* 300 */         addAll(0, collection);
/* 302 */       } catch (RuntimeException exception) {
/* 303 */         this.elementData = old;
/* 304 */         this.size = oldSize;
/* 305 */         throw exception;
/*     */       } 
/*     */     } 
/* 309 */     if (old != null)
/* 310 */       for (int i = 0; i < oldSize; i++) {
/* 311 */         Attribute attribute = old[i];
/* 312 */         attribute.setParent(null);
/*     */       }  
/* 315 */     this.modCount++;
/*     */   }
/*     */   
/*     */   private void ensureCapacity(int minCapacity) {
/* 326 */     if (this.elementData == null) {
/* 327 */       this.elementData = new Attribute[Math.max(minCapacity, 5)];
/*     */     } else {
/* 330 */       int oldCapacity = this.elementData.length;
/* 331 */       if (minCapacity > oldCapacity) {
/* 332 */         Attribute[] oldData = this.elementData;
/* 333 */         int newCapacity = oldCapacity * 3 / 2 + 1;
/* 334 */         if (newCapacity < minCapacity)
/* 335 */           newCapacity = minCapacity; 
/* 336 */         this.elementData = new Attribute[newCapacity];
/* 337 */         System.arraycopy(oldData, 0, this.elementData, 0, this.size);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 349 */     if (index < 0 || index >= this.size)
/* 350 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 354 */     return this.elementData[index];
/*     */   }
/*     */   
/*     */   Object get(String name, Namespace namespace) {
/* 366 */     int index = indexOf(name, namespace);
/* 367 */     if (index < 0)
/* 368 */       return null; 
/* 370 */     return this.elementData[index];
/*     */   }
/*     */   
/*     */   int indexOf(String name, Namespace namespace) {
/* 378 */     String uri = namespace.getURI();
/* 379 */     if (this.elementData != null)
/* 380 */       for (int i = 0; i < this.size; i++) {
/* 381 */         Attribute old = this.elementData[i];
/* 382 */         String oldURI = old.getNamespaceURI();
/* 383 */         String oldName = old.getName();
/* 384 */         if (oldURI.equals(uri) && oldName.equals(name))
/* 385 */           return i; 
/*     */       }  
/* 389 */     return -1;
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 399 */     if (index < 0 || index >= this.size)
/* 400 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 403 */     Attribute old = this.elementData[index];
/* 404 */     old.setParent(null);
/* 405 */     int numMoved = this.size - index - 1;
/* 406 */     if (numMoved > 0)
/* 407 */       System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved); 
/* 408 */     this.elementData[--this.size] = null;
/* 409 */     this.modCount++;
/* 410 */     return old;
/*     */   }
/*     */   
/*     */   boolean remove(String name, Namespace namespace) {
/* 422 */     int index = indexOf(name, namespace);
/* 423 */     if (index < 0)
/* 424 */       return false; 
/* 426 */     remove(index);
/* 427 */     return true;
/*     */   }
/*     */   
/*     */   public Object set(int index, Object obj) {
/* 440 */     if (obj instanceof Attribute) {
/* 441 */       Attribute attribute = (Attribute)obj;
/* 442 */       int duplicate = indexOfDuplicate(attribute);
/* 443 */       if (duplicate >= 0 && duplicate != index)
/* 444 */         throw new IllegalAddException("Cannot set duplicate attribute"); 
/* 446 */       return set(index, attribute);
/*     */     } 
/* 448 */     if (obj == null)
/* 449 */       throw new IllegalAddException("Cannot add null attribute"); 
/* 452 */     throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
/*     */   }
/*     */   
/*     */   Object set(int index, Attribute attribute) {
/* 468 */     if (index < 0 || index >= this.size)
/* 469 */       throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size()); 
/* 472 */     if (attribute.getParent() != null)
/* 473 */       throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\""); 
/* 478 */     String reason = Verifier.checkNamespaceCollision(attribute, this.parent);
/* 479 */     if (reason != null)
/* 480 */       throw new IllegalAddException(this.parent, attribute, reason); 
/* 483 */     Attribute old = this.elementData[index];
/* 484 */     old.setParent(null);
/* 486 */     this.elementData[index] = attribute;
/* 487 */     attribute.setParent(this.parent);
/* 488 */     return old;
/*     */   }
/*     */   
/*     */   private int indexOfDuplicate(Attribute attribute) {
/* 496 */     int duplicate = -1;
/* 497 */     String name = attribute.getName();
/* 498 */     Namespace namespace = attribute.getNamespace();
/* 499 */     duplicate = indexOf(name, namespace);
/* 500 */     return duplicate;
/*     */   }
/*     */   
/*     */   public int size() {
/* 509 */     return this.size;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 516 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\AttributeList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */