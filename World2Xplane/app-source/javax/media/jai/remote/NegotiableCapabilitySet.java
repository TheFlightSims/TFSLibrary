/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ public class NegotiableCapabilitySet implements Serializable {
/*  46 */   private Hashtable categories = new Hashtable();
/*     */   
/*     */   private boolean isPreference = false;
/*     */   
/*     */   public NegotiableCapabilitySet(boolean isPreference) {
/*  68 */     this.isPreference = isPreference;
/*     */   }
/*     */   
/*     */   public boolean isPreference() {
/*  77 */     return this.isPreference;
/*     */   }
/*     */   
/*     */   public void add(NegotiableCapability capability) {
/*  98 */     if (capability == null)
/*  99 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet0")); 
/* 103 */     if (this.isPreference != capability.isPreference())
/* 104 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet1")); 
/* 108 */     SequentialMap map = getCategoryMap(capability.getCategory());
/* 109 */     map.put(capability);
/*     */   }
/*     */   
/*     */   public void remove(NegotiableCapability capability) {
/* 125 */     if (capability == null)
/* 126 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet0")); 
/* 130 */     SequentialMap map = getCategoryMap(capability.getCategory());
/* 131 */     map.remove(capability);
/*     */   }
/*     */   
/*     */   public List get(String category, String capabilityName) {
/* 147 */     if (category == null)
/* 148 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet3")); 
/* 152 */     if (capabilityName == null)
/* 153 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet4")); 
/* 157 */     SequentialMap map = getCategoryMap(category);
/* 158 */     return map.getNCList(capabilityName);
/*     */   }
/*     */   
/*     */   public List get(String category) {
/* 172 */     if (category == null)
/* 173 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet3")); 
/* 177 */     SequentialMap map = getCategoryMap(category);
/* 178 */     Vector capNames = map.getCapabilityNames();
/* 180 */     Vector allNC = new Vector();
/* 182 */     for (Iterator e = capNames.iterator(); e.hasNext(); ) {
/* 184 */       Vector curr = (Vector)map.getNCList(e.next());
/* 185 */       for (Iterator i = curr.iterator(); i.hasNext(); ) {
/* 187 */         Object obj = i.next();
/* 190 */         if (!allNC.contains(obj))
/* 191 */           allNC.add(obj); 
/*     */       } 
/*     */     } 
/* 196 */     return allNC;
/*     */   }
/*     */   
/*     */   public List getCategories() {
/* 211 */     Vector v = new Vector();
/* 212 */     for (Enumeration e = this.categories.keys(); e.hasMoreElements(); ) {
/* 213 */       CaselessStringKey key = e.nextElement();
/* 214 */       v.add(key.toString());
/*     */     } 
/* 217 */     return v;
/*     */   }
/*     */   
/*     */   public List getCapabilityNames(String category) {
/* 235 */     if (category == null)
/* 236 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet3")); 
/* 240 */     SequentialMap map = getCategoryMap(category);
/* 241 */     Vector names = map.getCapabilityNames();
/* 242 */     return names;
/*     */   }
/*     */   
/*     */   public NegotiableCapabilitySet negotiate(NegotiableCapabilitySet other) {
/* 275 */     if (other == null)
/* 276 */       return null; 
/* 278 */     NegotiableCapabilitySet negotiated = new NegotiableCapabilitySet(this.isPreference & other.isPreference());
/* 282 */     Vector commonCategories = new Vector(getCategories());
/* 283 */     commonCategories.retainAll(other.getCategories());
/* 289 */     for (Iterator c = commonCategories.iterator(); c.hasNext(); ) {
/* 290 */       String currCategory = c.next();
/* 292 */       List thisCapabilities = get(currCategory);
/* 293 */       List otherCapabilities = other.get(currCategory);
/* 295 */       for (Iterator t = thisCapabilities.iterator(); t.hasNext(); ) {
/* 297 */         NegotiableCapability thisCap = t.next();
/* 299 */         for (Iterator o = otherCapabilities.iterator(); o.hasNext(); ) {
/* 301 */           NegotiableCapability otherCap = o.next();
/* 302 */           NegotiableCapability negCap = thisCap.negotiate(otherCap);
/* 303 */           if (negCap != null)
/* 304 */             negotiated.add(negCap); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 309 */     if (negotiated.isEmpty())
/* 310 */       return null; 
/* 313 */     return negotiated;
/*     */   }
/*     */   
/*     */   public NegotiableCapability getNegotiatedValue(String category) {
/* 330 */     if (category == null)
/* 331 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet3")); 
/* 335 */     List thisCapabilities = get(category);
/* 336 */     if (thisCapabilities.isEmpty())
/* 337 */       return null; 
/* 339 */     return thisCapabilities.get(0);
/*     */   }
/*     */   
/*     */   public NegotiableCapability getNegotiatedValue(NegotiableCapabilitySet other, String category) {
/* 369 */     if (other == null)
/* 370 */       return null; 
/* 372 */     if (category == null)
/* 373 */       throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet3")); 
/* 377 */     List thisCapabilities = get(category);
/* 378 */     List otherCapabilities = other.get(category);
/* 382 */     for (Iterator t = thisCapabilities.iterator(); t.hasNext(); ) {
/* 384 */       NegotiableCapability thisCap = t.next();
/* 386 */       for (Iterator o = otherCapabilities.iterator(); o.hasNext(); ) {
/* 388 */         NegotiableCapability otherCap = o.next();
/* 389 */         NegotiableCapability negCap = thisCap.negotiate(otherCap);
/* 392 */         if (negCap != null)
/* 393 */           return negCap; 
/*     */       } 
/*     */     } 
/* 397 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 405 */     return this.categories.isEmpty();
/*     */   }
/*     */   
/*     */   private SequentialMap getCategoryMap(String category) {
/* 412 */     CaselessStringKey categoryKey = new CaselessStringKey(category);
/* 413 */     SequentialMap map = (SequentialMap)this.categories.get(categoryKey);
/* 415 */     if (map == null) {
/* 416 */       map = new SequentialMap(this);
/* 417 */       this.categories.put(categoryKey, map);
/*     */     } 
/* 420 */     return map;
/*     */   }
/*     */   
/*     */   class SequentialMap implements Serializable {
/*     */     Vector keys;
/*     */     
/*     */     Vector values;
/*     */     
/*     */     private final NegotiableCapabilitySet this$0;
/*     */     
/*     */     SequentialMap(NegotiableCapabilitySet this$0) {
/* 441 */       this.this$0 = this$0;
/* 442 */       this.keys = new Vector();
/* 443 */       this.values = new Vector();
/*     */     }
/*     */     
/*     */     void put(NegotiableCapability capability) {
/* 451 */       CaselessStringKey capNameKey = new CaselessStringKey(capability.getCapabilityName());
/* 454 */       int index = this.keys.indexOf(capNameKey);
/* 457 */       if (index == -1) {
/* 458 */         this.keys.add(capNameKey);
/* 459 */         Vector v = new Vector();
/* 460 */         v.add(capability);
/* 461 */         this.values.add(v);
/*     */       } else {
/* 463 */         Vector v = this.values.elementAt(index);
/* 464 */         if (v == null)
/* 465 */           v = new Vector(); 
/* 466 */         v.add(capability);
/*     */       } 
/*     */     }
/*     */     
/*     */     List getNCList(String capabilityName) {
/* 476 */       CaselessStringKey capNameKey = new CaselessStringKey(capabilityName);
/* 479 */       int index = this.keys.indexOf(capNameKey);
/* 482 */       if (index == -1) {
/* 483 */         Vector vector = new Vector();
/* 484 */         return vector;
/*     */       } 
/* 486 */       Vector v = this.values.elementAt(index);
/* 487 */       return v;
/*     */     }
/*     */     
/*     */     void remove(NegotiableCapability capability) {
/* 496 */       CaselessStringKey capNameKey = new CaselessStringKey(capability.getCapabilityName());
/* 499 */       int index = this.keys.indexOf(capNameKey);
/* 501 */       if (index == -1)
/* 502 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet2")); 
/* 506 */       Vector v = this.values.elementAt(index);
/* 507 */       if (!v.remove(capability))
/* 508 */         throw new IllegalArgumentException(JaiI18N.getString("NegotiableCapabilitySet2")); 
/* 513 */       if (v.isEmpty()) {
/* 514 */         this.keys.remove(capNameKey);
/* 515 */         this.values.remove(index);
/*     */       } 
/* 518 */       if (this.keys.isEmpty())
/* 519 */         this.this$0.categories.remove(new CaselessStringKey(capability.getCategory())); 
/*     */     }
/*     */     
/*     */     Vector getCapabilityNames() {
/* 529 */       Vector v = new Vector();
/* 531 */       for (Iterator i = this.keys.iterator(); i.hasNext(); ) {
/* 532 */         CaselessStringKey name = i.next();
/* 533 */         v.add(name.getName());
/*     */       } 
/* 536 */       return v;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\NegotiableCapabilitySet.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */