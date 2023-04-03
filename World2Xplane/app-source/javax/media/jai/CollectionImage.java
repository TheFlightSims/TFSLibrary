/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyUtil;
/*     */ import java.awt.Image;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class CollectionImage implements ImageJAI, Collection {
/*     */   protected Collection imageCollection;
/*     */   
/*     */   protected CollectionImageFactory imageFactory;
/*     */   
/*  56 */   private Boolean isFactorySet = Boolean.FALSE;
/*     */   
/*  63 */   protected PropertyChangeSupportJAI eventManager = null;
/*     */   
/*  70 */   protected WritablePropertySourceImpl properties = null;
/*     */   
/*     */   protected Set sinks;
/*     */   
/*     */   protected CollectionImage() {
/*  90 */     this.eventManager = new PropertyChangeSupportJAI(this);
/*  91 */     this.properties = new WritablePropertySourceImpl(null, null, this.eventManager);
/*     */   }
/*     */   
/*     */   public CollectionImage(Collection collection) {
/* 104 */     this();
/* 107 */     if (collection == null)
/* 108 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 111 */     this.imageCollection = collection;
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 133 */     if (index < 0 || index >= this.imageCollection.size())
/* 134 */       throw new IndexOutOfBoundsException(); 
/* 137 */     if (this.imageCollection instanceof List)
/* 138 */       return ((List)this.imageCollection).get(index); 
/* 140 */     return this.imageCollection.toArray((T[])null)[index];
/*     */   }
/*     */   
/*     */   public void setImageFactory(CollectionImageFactory imageFactory) {
/* 160 */     synchronized (this.isFactorySet) {
/* 161 */       if (this.isFactorySet.booleanValue())
/* 162 */         throw new IllegalStateException(); 
/* 164 */       this.imageFactory = imageFactory;
/* 165 */       this.isFactorySet = Boolean.TRUE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CollectionImageFactory getImageFactory() {
/* 177 */     synchronized (this.isFactorySet) {
/* 178 */       return this.imageFactory;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized boolean addSink(Object sink) {
/* 190 */     if (sink == null)
/* 191 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 194 */     if (this.sinks == null)
/* 195 */       this.sinks = new HashSet(); 
/* 198 */     return this.sinks.add(new WeakReference(sink));
/*     */   }
/*     */   
/*     */   public synchronized boolean removeSink(Object sink) {
/* 210 */     if (sink == null)
/* 211 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 214 */     if (this.sinks == null)
/* 215 */       return false; 
/* 218 */     boolean result = false;
/* 219 */     Iterator it = this.sinks.iterator();
/* 220 */     while (it.hasNext()) {
/* 221 */       Object referent = ((WeakReference)it.next()).get();
/* 222 */       if (referent == sink) {
/* 224 */         it.remove();
/* 225 */         result = true;
/*     */         continue;
/*     */       } 
/* 227 */       if (referent == null)
/* 229 */         it.remove(); 
/*     */     } 
/* 233 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized Set getSinks() {
/* 244 */     Set v = null;
/* 246 */     if (this.sinks != null && this.sinks.size() > 0) {
/* 247 */       v = new HashSet(this.sinks.size());
/* 249 */       Iterator it = this.sinks.iterator();
/* 250 */       while (it.hasNext()) {
/* 251 */         Object o = ((WeakReference)it.next()).get();
/* 253 */         if (o != null)
/* 254 */           v.add(o); 
/*     */       } 
/* 258 */       if (v.size() == 0)
/* 259 */         v = null; 
/*     */     } 
/* 263 */     return v;
/*     */   }
/*     */   
/*     */   public synchronized void removeSinks() {
/* 272 */     this.sinks = null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 286 */     return this.properties.getPropertyNames();
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 307 */     return PropertyUtil.getPropertyNames(getPropertyNames(), prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String name) {
/* 324 */     return this.properties.getPropertyClass(name);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 335 */     return this.properties.getProperty(name);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Collection collection) {
/* 347 */     return Image.UndefinedProperty;
/*     */   }
/*     */   
/*     */   public void setProperty(String name, Object value) {
/* 364 */     this.properties.setProperty(name, value);
/*     */   }
/*     */   
/*     */   public void removeProperty(String name) {
/* 375 */     this.properties.removeProperty(name);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 387 */     this.eventManager.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 400 */     this.eventManager.addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 411 */     this.eventManager.removePropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 422 */     this.eventManager.removePropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public int size() {
/* 429 */     return this.imageCollection.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 437 */     return this.imageCollection.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean contains(Object o) {
/* 445 */     return this.imageCollection.contains(o);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 453 */     return this.imageCollection.iterator();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 461 */     return this.imageCollection.toArray();
/*     */   }
/*     */   
/*     */   public Object[] toArray(Object[] a) {
/* 473 */     return this.imageCollection.toArray(a);
/*     */   }
/*     */   
/*     */   public boolean add(Object o) {
/* 483 */     return this.imageCollection.add(o);
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 493 */     return this.imageCollection.remove(o);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection c) {
/* 501 */     return this.imageCollection.containsAll(c);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection c) {
/* 512 */     return this.imageCollection.addAll(c);
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection c) {
/* 523 */     return this.imageCollection.removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection c) {
/* 534 */     return this.imageCollection.retainAll(c);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 539 */     this.imageCollection.clear();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\CollectionImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */