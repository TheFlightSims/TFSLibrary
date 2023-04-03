/*     */ package org.geotools.metadata;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.CheckedArrayList;
/*     */ import org.geotools.util.CheckedHashSet;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public abstract class ModifiableMetadata extends AbstractMetadata implements Cloneable {
/*     */   private static final class Null extends ModifiableMetadata {
/*     */     private Null() {}
/*     */     
/*     */     public MetadataStandard getStandard() {
/*  85 */       return null;
/*     */     }
/*     */   }
/*     */   
/*  93 */   private static final ModifiableMetadata FREEZING = new Null();
/*     */   
/*     */   private transient ModifiableMetadata unmodifiable;
/*     */   
/*     */   protected ModifiableMetadata() {}
/*     */   
/*     */   protected ModifiableMetadata(Object source) throws ClassCastException, UnmodifiableMetadataException {
/* 124 */     super(source);
/*     */   }
/*     */   
/*     */   public final boolean isModifiable() {
/* 135 */     return (this.unmodifiable != this);
/*     */   }
/*     */   
/*     */   public synchronized AbstractMetadata unmodifiable() {
/* 150 */     if (this.unmodifiable == null) {
/*     */       ModifiableMetadata candidate;
/*     */       try {
/* 157 */         candidate = clone();
/* 158 */       } catch (CloneNotSupportedException exception) {
/* 164 */         Logging.unexpectedException(LOGGER, exception);
/* 165 */         return this;
/*     */       } 
/* 167 */       candidate.freeze();
/* 170 */       this.unmodifiable = candidate;
/*     */     } 
/* 172 */     assert !this.unmodifiable.isModifiable();
/* 173 */     return this.unmodifiable;
/*     */   }
/*     */   
/*     */   static Object unmodifiable(Object object) {
/* 200 */     if (object instanceof ModifiableMetadata)
/* 201 */       return ((ModifiableMetadata)object).unmodifiable(); 
/* 208 */     if (object instanceof Collection) {
/* 209 */       Collection<?> collection = (Collection)object;
/* 210 */       if (collection.isEmpty()) {
/* 211 */         if (collection instanceof List) {
/* 212 */           collection = Collections.EMPTY_LIST;
/*     */         } else {
/* 214 */           collection = Collections.EMPTY_SET;
/*     */         } 
/*     */       } else {
/* 217 */         Object[] array = collection.toArray();
/* 218 */         for (int i = 0; i < array.length; i++)
/* 219 */           array[i] = unmodifiable(array[i]); 
/* 223 */         UnmodifiableArrayList unmodifiableArrayList = UnmodifiableArrayList.wrap(array);
/* 224 */         if (unmodifiableArrayList instanceof Set)
/* 225 */           collection = Collections.unmodifiableSet(new LinkedHashSet((Collection<?>)unmodifiableArrayList)); 
/*     */       } 
/* 231 */       return collection;
/*     */     } 
/* 237 */     if (object instanceof Map) {
/* 238 */       Map<?, ?> map = (Map)object;
/* 239 */       if (map.isEmpty())
/* 240 */         return Collections.EMPTY_MAP; 
/* 242 */       map = new LinkedHashMap<Object, Object>(map);
/* 243 */       for (Iterator<Map.Entry> it = map.entrySet().iterator(); it.hasNext(); ) {
/* 244 */         Map.Entry entry = it.next();
/* 245 */         entry.setValue(unmodifiable(entry.getValue()));
/*     */       } 
/* 247 */       return Collections.unmodifiableMap(map);
/*     */     } 
/* 252 */     if (object instanceof Cloneable)
/* 253 */       return ((Cloneable)object).clone(); 
/* 258 */     return object;
/*     */   }
/*     */   
/*     */   public synchronized void freeze() {
/* 267 */     ModifiableMetadata success = null;
/*     */     try {
/* 269 */       this.unmodifiable = FREEZING;
/* 270 */       getStandard().freeze(this);
/* 271 */       success = this;
/*     */     } finally {
/* 273 */       this.unmodifiable = success;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void checkWritePermission() throws UnmodifiableMetadataException {
/* 285 */     assert Thread.holdsLock(this);
/* 286 */     if (!isModifiable())
/* 287 */       throw new UnmodifiableMetadataException(Errors.format(190)); 
/* 289 */     invalidate();
/*     */   }
/*     */   
/*     */   final void invalidate() {
/* 298 */     super.invalidate();
/* 299 */     this.unmodifiable = null;
/*     */   }
/*     */   
/*     */   private static boolean isModifiable(Collection collection) {
/* 308 */     if (!collection.isEmpty())
/*     */       try {
/* 309 */         collection.clear();
/* 310 */         return true;
/* 311 */       } catch (UnsupportedOperationException e) {} 
/* 314 */     return false;
/*     */   }
/*     */   
/*     */   protected final <E> List<E> copyList(Collection<? extends E> source, List<E> target, Class<E> elementType) throws UnmodifiableMetadataException {
/*     */     MutableList<E> mutableList;
/* 336 */     if (this.unmodifiable == FREEZING) {
/* 341 */       assert !isModifiable(source);
/* 343 */       List<E> unmodifiable = (List)source;
/* 344 */       return unmodifiable;
/*     */     } 
/* 346 */     checkWritePermission();
/* 352 */     if (source != target)
/* 353 */       if (source == null) {
/* 354 */         if (target != null)
/* 355 */           target.clear(); 
/*     */       } else {
/* 358 */         if (target != null) {
/* 359 */           target.clear();
/*     */         } else {
/* 361 */           int capacity = source.size();
/* 362 */           mutableList = new MutableList<E>(elementType, capacity);
/*     */         } 
/* 364 */         mutableList.addAll(source);
/*     */       }  
/* 367 */     return (List<E>)mutableList;
/*     */   }
/*     */   
/*     */   protected final <E> Collection<E> copyCollection(Collection<? extends E> source, Collection<E> target, Class<E> elementType) throws UnmodifiableMetadataException {
/*     */     MutableSet<E> mutableSet;
/* 388 */     if (this.unmodifiable == FREEZING) {
/* 393 */       assert !isModifiable(source);
/* 395 */       return (Collection)source;
/*     */     } 
/* 398 */     checkWritePermission();
/* 404 */     if (source != target)
/* 405 */       if (source == null) {
/* 406 */         if (target != null)
/* 407 */           target.clear(); 
/*     */       } else {
/* 410 */         boolean isList = source instanceof List;
/* 411 */         if (target != null && target instanceof List == isList) {
/* 412 */           target.clear();
/*     */         } else {
/* 414 */           int capacity = source.size();
/* 415 */           if (isList) {
/* 416 */             MutableList<E> mutableList = new MutableList<E>(elementType, capacity);
/*     */           } else {
/* 418 */             capacity = Math.round(capacity / 0.75F) + 1;
/* 419 */             mutableSet = new MutableSet<E>(elementType, capacity);
/*     */           } 
/*     */         } 
/* 422 */         mutableSet.addAll(source);
/*     */       }  
/* 425 */     return (Collection<E>)mutableSet;
/*     */   }
/*     */   
/*     */   protected final <E> Collection<E> nonNullCollection(Collection<E> c, Class<E> elementType) {
/* 441 */     assert Thread.holdsLock(this);
/* 442 */     if (c != null)
/* 443 */       return c; 
/* 445 */     if (isModifiable())
/* 446 */       return (Collection<E>)new MutableSet<E>(elementType); 
/* 448 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   protected final <E> Set<E> nonNullSet(Set<E> c, Class<E> elementType) {
/* 464 */     assert Thread.holdsLock(this);
/* 465 */     if (c != null)
/* 466 */       return c; 
/* 468 */     if (isModifiable())
/* 469 */       return (Set<E>)new MutableSet<E>(elementType); 
/* 471 */     return Collections.emptySet();
/*     */   }
/*     */   
/*     */   protected final <E> List<E> nonNullList(List<E> c, Class<E> elementType) {
/* 485 */     assert Thread.holdsLock(this);
/* 486 */     if (c != null)
/* 487 */       return c; 
/* 489 */     if (isModifiable())
/* 490 */       return (List<E>)new MutableList<E>(elementType); 
/* 492 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   private final class MutableSet<E> extends CheckedHashSet<E> {
/*     */     private static final long serialVersionUID = 2337350768744454264L;
/*     */     
/*     */     public MutableSet(Class<E> type) {
/* 504 */       super(type);
/*     */     }
/*     */     
/*     */     public MutableSet(Class<E> type, int capacity) {
/* 508 */       super(type, capacity);
/*     */     }
/*     */     
/*     */     protected Object getLock() {
/* 513 */       return ModifiableMetadata.this;
/*     */     }
/*     */     
/*     */     protected void checkWritePermission() throws UnsupportedOperationException {
/* 518 */       ModifiableMetadata.this.checkWritePermission();
/*     */     }
/*     */   }
/*     */   
/*     */   private final class MutableList<E> extends CheckedArrayList<E> {
/*     */     private static final long serialVersionUID = -5016778173550153002L;
/*     */     
/*     */     public MutableList(Class<E> type) {
/* 531 */       super(type);
/*     */     }
/*     */     
/*     */     public MutableList(Class<E> type, int capacity) {
/* 535 */       super(type, capacity);
/*     */     }
/*     */     
/*     */     protected Object getLock() {
/* 540 */       return ModifiableMetadata.this;
/*     */     }
/*     */     
/*     */     protected void checkWritePermission() throws UnsupportedOperationException {
/* 545 */       ModifiableMetadata.this.checkWritePermission();
/*     */     }
/*     */   }
/*     */   
/*     */   protected ModifiableMetadata clone() throws CloneNotSupportedException {
/* 563 */     return (ModifiableMetadata)super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\ModifiableMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */