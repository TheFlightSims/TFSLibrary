/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ 
/*     */ public class IdentifiedObjectSet extends AbstractSet implements Serializable {
/*     */   private static final long serialVersionUID = -4221260663706882719L;
/*     */   
/*  91 */   private final Map objects = new LinkedHashMap<Object, Object>();
/*     */   
/*     */   protected final AuthorityFactory factory;
/*     */   
/*     */   public IdentifiedObjectSet(AuthorityFactory factory) {
/* 106 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 114 */     this.objects.clear();
/*     */   }
/*     */   
/*     */   public int size() {
/* 123 */     return this.objects.size();
/*     */   }
/*     */   
/*     */   public boolean addAuthorityCode(String code) {
/* 133 */     boolean already = this.objects.containsKey(code);
/* 134 */     IdentifiedObject old = this.objects.put(code, null);
/* 135 */     if (old != null) {
/* 137 */       this.objects.put(code, old);
/* 138 */       return false;
/*     */     } 
/* 140 */     return !already;
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 151 */     String code = getAuthorityCode((IdentifiedObject)object);
/* 152 */     return !Utilities.equals(this.objects.put(code, object), object);
/*     */   }
/*     */   
/*     */   private IdentifiedObject get(String code) throws BackingStoreException {
/* 162 */     IdentifiedObject object = (IdentifiedObject)this.objects.get(code);
/* 163 */     if (object == null && this.objects.containsKey(code))
/*     */       try {
/* 165 */         object = createObject(code);
/* 166 */         this.objects.put(code, object);
/* 167 */       } catch (FactoryException exception) {
/* 168 */         if (!isRecoverableFailure(exception))
/* 169 */           throw new BackingStoreException(exception); 
/* 171 */         log(exception, code);
/* 172 */         this.objects.remove(code);
/*     */       }  
/* 175 */     return object;
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 182 */     String code = getAuthorityCode((IdentifiedObject)object);
/* 183 */     IdentifiedObject current = get(code);
/* 184 */     return object.equals(current);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 192 */     String code = getAuthorityCode((IdentifiedObject)object);
/* 193 */     IdentifiedObject current = get(code);
/* 194 */     if (object.equals(current)) {
/* 195 */       this.objects.remove(code);
/* 196 */       return true;
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection collection) {
/* 206 */     boolean modified = false;
/* 207 */     for (Iterator it = collection.iterator(); it.hasNext();) {
/* 208 */       if (remove(it.next()))
/* 209 */         modified = true; 
/*     */     } 
/* 212 */     return modified;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 221 */     return new Iter(this.objects.entrySet().iterator());
/*     */   }
/*     */   
/*     */   public void resolve(int n) throws FactoryException {
/* 238 */     if (n > 0)
/*     */       try {
/* 239 */         for (Iterator it = iterator(); it.hasNext(); ) {
/* 240 */           it.next();
/* 241 */           if (--n == 0)
/*     */             break; 
/*     */         } 
/* 245 */       } catch (BackingStoreException exception) {
/* 246 */         Throwable cause = exception.getCause();
/* 247 */         if (cause instanceof FactoryException)
/* 248 */           throw (FactoryException)cause; 
/* 250 */         throw exception;
/*     */       }  
/*     */   }
/*     */   
/*     */   public String[] getAuthorityCodes() {
/* 263 */     Set codes = this.objects.keySet();
/* 264 */     return (String[])codes.toArray((Object[])new String[codes.size()]);
/*     */   }
/*     */   
/*     */   public void setAuthorityCodes(String[] codes) {
/* 281 */     Map<Object, Object> copy = new HashMap<Object, Object>(this.objects);
/* 282 */     this.objects.clear();
/* 283 */     for (int i = 0; i < codes.length; i++) {
/* 284 */       String code = codes[i];
/* 285 */       this.objects.put(code, (IdentifiedObject)copy.get(code));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getAuthorityCode(IdentifiedObject object) {
/*     */     ReferenceIdentifier referenceIdentifier;
/* 297 */     Set<Identifier> identifiers = object.getIdentifiers();
/* 298 */     if (identifiers != null && !identifiers.isEmpty()) {
/* 299 */       Identifier id = identifiers.iterator().next();
/*     */     } else {
/* 301 */       referenceIdentifier = object.getName();
/*     */     } 
/* 303 */     return referenceIdentifier.getCode();
/*     */   }
/*     */   
/*     */   protected IdentifiedObject createObject(String code) throws FactoryException {
/* 313 */     return this.factory.createObject(code);
/*     */   }
/*     */   
/*     */   protected boolean isRecoverableFailure(FactoryException exception) {
/* 327 */     return exception instanceof org.opengis.referencing.NoSuchIdentifierException;
/*     */   }
/*     */   
/*     */   static void log(FactoryException exception, String code) {
/* 336 */     LogRecord record = new LogRecord(Level.FINE, "Failed to create an object for code \"" + code + "\".");
/* 338 */     record.setSourceClassName(IdentifiedObjectSet.class.getName());
/* 339 */     record.setSourceMethodName("createObject");
/* 340 */     record.setThrown((Throwable)exception);
/* 341 */     Logger logger = AbstractAuthorityFactory.LOGGER;
/* 342 */     record.setLoggerName(logger.getName());
/* 343 */     logger.log(record);
/*     */   }
/*     */   
/*     */   protected Object writeReplace() throws ObjectStreamException {
/* 352 */     return new LinkedHashSet(this);
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator {
/*     */     private final Iterator iterator;
/*     */     
/*     */     private IdentifiedObject element;
/*     */     
/*     */     public Iter(Iterator iterator) {
/* 377 */       this.iterator = iterator;
/* 378 */       toNext();
/*     */     }
/*     */     
/*     */     private void toNext() throws BackingStoreException {
/* 388 */       while (this.iterator.hasNext()) {
/* 389 */         Map.Entry entry = this.iterator.next();
/* 390 */         this.element = (IdentifiedObject)entry.getValue();
/* 391 */         if (this.element == null) {
/* 392 */           String code = (String)entry.getKey();
/*     */           try {
/* 394 */             this.element = IdentifiedObjectSet.this.createObject(code);
/* 395 */           } catch (FactoryException exception) {
/* 396 */             if (!IdentifiedObjectSet.this.isRecoverableFailure(exception))
/* 397 */               throw new BackingStoreException(exception); 
/* 399 */             IdentifiedObjectSet.log(exception, code);
/* 400 */             this.iterator.remove();
/*     */             continue;
/*     */           } 
/* 403 */           entry.setValue(this.element);
/*     */         } 
/*     */         return;
/*     */       } 
/* 407 */       this.element = null;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 414 */       return (this.element != null);
/*     */     }
/*     */     
/*     */     public Object next() throws NoSuchElementException {
/* 423 */       IdentifiedObject next = this.element;
/* 424 */       if (next == null)
/* 425 */         throw new NoSuchElementException(); 
/* 427 */       toNext();
/* 428 */       return next;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 435 */       this.iterator.remove();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\IdentifiedObjectSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */