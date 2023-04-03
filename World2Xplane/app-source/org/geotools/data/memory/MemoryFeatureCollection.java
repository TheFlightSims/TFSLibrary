/*     */ package org.geotools.data.memory;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.collection.AbstractFeatureCollection;
/*     */ import org.geotools.feature.collection.RandomFeatureAccess;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ 
/*     */ public class MemoryFeatureCollection extends AbstractFeatureCollection implements RandomFeatureAccess, Collection<SimpleFeature> {
/*  53 */   TreeMap<String, SimpleFeature> contents = new TreeMap<String, SimpleFeature>();
/*     */   
/*     */   public MemoryFeatureCollection(SimpleFeatureType schema) {
/*  56 */     super(schema);
/*     */   }
/*     */   
/*     */   public boolean add(SimpleFeature o) {
/*  60 */     SimpleFeature feature = o;
/*  61 */     this.contents.put(feature.getID(), feature);
/*  62 */     return true;
/*     */   }
/*     */   
/*     */   public int size() {
/*  66 */     return this.contents.size();
/*     */   }
/*     */   
/*     */   public MemoryIterator openIterator() {
/*  70 */     return new MemoryIterator(this.contents.values().iterator());
/*     */   }
/*     */   
/*     */   class MemoryIterator implements Iterator<SimpleFeature>, SimpleFeatureIterator {
/*     */     Iterator<SimpleFeature> it;
/*     */     
/*     */     MemoryIterator(Iterator<SimpleFeature> iterator) {
/*  77 */       this.it = iterator;
/*     */     }
/*     */     
/*     */     public void close() {
/*  81 */       this.it = null;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  85 */       if (this.it == null)
/*  86 */         throw new IllegalStateException(); 
/*  88 */       return this.it.hasNext();
/*     */     }
/*     */     
/*     */     public SimpleFeature next() {
/*  92 */       if (this.it == null)
/*  93 */         throw new IllegalStateException(); 
/*  95 */       return this.it.next();
/*     */     }
/*     */     
/*     */     public void remove() {
/*  99 */       this.it.remove();
/*     */     }
/*     */   }
/*     */   
/*     */   public SimpleFeature getFeatureMember(String id) throws NoSuchElementException {
/* 107 */     if (this.contents.containsKey(id))
/* 108 */       return this.contents.get(id); 
/* 110 */     throw new NoSuchElementException(id);
/*     */   }
/*     */   
/*     */   public SimpleFeature removeFeatureMember(String id) {
/* 114 */     if (this.contents.containsKey(id)) {
/* 115 */       SimpleFeature old = this.contents.get(id);
/* 116 */       this.contents.remove(id);
/* 117 */       return old;
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public ReferencedEnvelope getBounds() {
/* 127 */     return DataUtilities.bounds((FeatureIterator)features());
/*     */   }
/*     */   
/*     */   public boolean remove(Object o) {
/* 132 */     return this.contents.values().remove(o);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends SimpleFeature> c) {
/* 137 */     boolean changed = false;
/* 138 */     for (SimpleFeature feature : c) {
/* 139 */       boolean added = add(feature);
/* 140 */       if (!changed && added)
/* 141 */         changed = true; 
/*     */     } 
/* 144 */     return changed;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 149 */     return this.contents.values().removeAll(c);
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 154 */     return this.contents.values().retainAll(c);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 159 */     this.contents.clear();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\memory\MemoryFeatureCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */