/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ class FactoryCache {
/*     */   final String modeName;
/*     */   
/*     */   final RegistryMode mode;
/*     */   
/*     */   final Class factoryClass;
/*     */   
/*     */   final Method factoryMethod;
/*     */   
/*     */   final boolean arePreferencesSupported;
/*     */   
/*     */   private Hashtable instances;
/*     */   
/*     */   private Hashtable instancesByName;
/*     */   
/*  71 */   private int count = 0;
/*     */   
/*     */   private Hashtable prefs;
/*     */   
/*     */   FactoryCache(String modeName) {
/*  90 */     this.modeName = modeName;
/*  92 */     this.mode = RegistryMode.getMode(modeName);
/*  93 */     this.factoryClass = this.mode.getFactoryClass();
/*  94 */     this.factoryMethod = this.mode.getFactoryMethod();
/*  95 */     this.arePreferencesSupported = this.mode.arePreferencesSupported();
/*  97 */     this.instances = new Hashtable();
/*  99 */     if (this.arePreferencesSupported) {
/* 100 */       this.instancesByName = new Hashtable();
/* 101 */       this.prefs = new Hashtable();
/*     */     } 
/*     */   }
/*     */   
/*     */   Object invoke(Object factoryInstance, Object[] parameterValues) throws InvocationTargetException, IllegalAccessException {
/* 122 */     return this.factoryMethod.invoke(factoryInstance, parameterValues);
/*     */   }
/*     */   
/*     */   void addFactory(String descriptorName, String productName, Object factoryInstance) {
/* 139 */     checkInstance(factoryInstance);
/* 141 */     if (this.arePreferencesSupported) {
/* 143 */       if (productName == null)
/* 144 */         throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 148 */       Vector v = new Vector();
/* 150 */       v.add(factoryInstance.getClass().getName());
/* 151 */       v.add(productName);
/* 152 */       v.add(descriptorName);
/* 154 */       CaselessStringKey fileName = new CaselessStringKey(this.modeName + this.count);
/* 157 */       this.instancesByName.put(factoryInstance, fileName);
/* 158 */       this.instances.put(fileName, v);
/* 159 */       this.count++;
/*     */     } else {
/* 162 */       this.instances.put(new CaselessStringKey(descriptorName), factoryInstance);
/*     */     } 
/*     */   }
/*     */   
/*     */   void removeFactory(String descriptorName, String productName, Object factoryInstance) {
/* 177 */     checkInstance(factoryInstance);
/* 178 */     checkRegistered(descriptorName, productName, factoryInstance);
/* 180 */     if (this.arePreferencesSupported) {
/* 184 */       CaselessStringKey fileName = (CaselessStringKey)this.instancesByName.get(factoryInstance);
/* 187 */       this.instancesByName.remove(factoryInstance);
/* 188 */       this.instances.remove(fileName);
/* 189 */       this.count--;
/*     */     } else {
/* 191 */       this.instances.remove(new CaselessStringKey(descriptorName));
/*     */     } 
/*     */   }
/*     */   
/*     */   void setPreference(String descriptorName, String productName, Object preferredOp, Object otherOp) {
/* 209 */     if (!this.arePreferencesSupported)
/* 210 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache1", new Object[] { this.modeName })); 
/* 215 */     if (preferredOp == null || otherOp == null)
/* 216 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 220 */     checkRegistered(descriptorName, productName, preferredOp);
/* 221 */     checkRegistered(descriptorName, productName, otherOp);
/* 223 */     if (preferredOp == otherOp)
/*     */       return; 
/* 226 */     checkInstance(preferredOp);
/* 227 */     checkInstance(otherOp);
/* 229 */     CaselessStringKey dn = new CaselessStringKey(descriptorName);
/* 230 */     CaselessStringKey pn = new CaselessStringKey(productName);
/* 232 */     Hashtable dht = (Hashtable)this.prefs.get(dn);
/* 234 */     if (dht == null)
/* 235 */       this.prefs.put(dn, dht = new Hashtable()); 
/* 238 */     Vector pv = (Vector)dht.get(pn);
/* 240 */     if (pv == null)
/* 241 */       dht.put(pn, pv = new Vector()); 
/* 244 */     pv.addElement(new Object[] { preferredOp, otherOp });
/*     */   }
/*     */   
/*     */   void unsetPreference(String descriptorName, String productName, Object preferredOp, Object otherOp) {
/* 261 */     if (!this.arePreferencesSupported)
/* 262 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache1", new Object[] { this.modeName })); 
/* 267 */     if (preferredOp == null || otherOp == null)
/* 268 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 272 */     checkRegistered(descriptorName, productName, preferredOp);
/* 273 */     checkRegistered(descriptorName, productName, otherOp);
/* 275 */     if (preferredOp == otherOp)
/*     */       return; 
/* 278 */     checkInstance(preferredOp);
/* 279 */     checkInstance(otherOp);
/* 282 */     Hashtable dht = (Hashtable)this.prefs.get(new CaselessStringKey(descriptorName));
/* 285 */     boolean found = false;
/* 287 */     if (dht != null) {
/* 289 */       Vector pv = (Vector)dht.get(new CaselessStringKey(productName));
/* 292 */       if (pv != null) {
/* 293 */         Iterator it = pv.iterator();
/* 295 */         while (it.hasNext()) {
/* 296 */           Object[] objs = it.next();
/* 298 */           if (objs[0] == preferredOp && objs[1] == otherOp) {
/* 301 */             it.remove();
/* 302 */             found = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 309 */     if (!found)
/* 310 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache2", new Object[] { preferredOp.getClass().getName(), otherOp.getClass().getName(), this.modeName, descriptorName, productName })); 
/*     */   }
/*     */   
/*     */   Object[][] getPreferences(String descriptorName, String productName) {
/* 328 */     if (!this.arePreferencesSupported)
/* 329 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache1", new Object[] { this.modeName })); 
/* 334 */     if (descriptorName == null || productName == null)
/* 335 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 338 */     Hashtable dht = (Hashtable)this.prefs.get(new CaselessStringKey(descriptorName));
/* 341 */     if (dht != null) {
/* 343 */       Vector pv = (Vector)dht.get(new CaselessStringKey(productName));
/* 346 */       if (pv != null)
/* 347 */         return (Object[][])pv.toArray((Object[])new Object[0][]); 
/*     */     } 
/* 351 */     return (Object[][])null;
/*     */   }
/*     */   
/*     */   void clearPreferences(String descriptorName, String productName) {
/* 364 */     if (!this.arePreferencesSupported)
/* 365 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache1", new Object[] { this.modeName })); 
/* 371 */     Hashtable dht = (Hashtable)this.prefs.get(new CaselessStringKey(descriptorName));
/* 374 */     if (dht != null)
/* 375 */       dht.remove(new CaselessStringKey(productName)); 
/*     */   }
/*     */   
/*     */   List getFactoryList(String descriptorName, String productName) {
/* 384 */     if (this.arePreferencesSupported) {
/* 386 */       ArrayList arrayList = new ArrayList();
/* 388 */       Enumeration keys = this.instancesByName.keys();
/* 390 */       while (keys.hasMoreElements()) {
/* 391 */         Object instance = keys.nextElement();
/* 392 */         CaselessStringKey fileName = (CaselessStringKey)this.instancesByName.get(instance);
/* 395 */         Vector v = (Vector)this.instances.get(fileName);
/* 397 */         String dn = v.get(2);
/* 398 */         String pn = v.get(1);
/* 400 */         if (descriptorName.equalsIgnoreCase(dn) && productName.equalsIgnoreCase(pn))
/* 402 */           arrayList.add(instance); 
/*     */       } 
/* 405 */       return arrayList;
/*     */     } 
/* 408 */     Object obj = this.instances.get(new CaselessStringKey(descriptorName));
/* 411 */     ArrayList list = new ArrayList(1);
/* 413 */     list.add(obj);
/* 414 */     return list;
/*     */   }
/*     */   
/*     */   String getLocalName(Object factoryInstance) {
/* 422 */     CaselessStringKey fileName = (CaselessStringKey)this.instancesByName.get(factoryInstance);
/* 425 */     if (fileName != null)
/* 426 */       return fileName.getName(); 
/* 428 */     return null;
/*     */   }
/*     */   
/*     */   private boolean checkInstance(Object factoryInstance) {
/* 437 */     if (!this.factoryClass.isInstance(factoryInstance))
/* 438 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache0", new Object[] { factoryInstance.getClass().getName(), this.modeName, this.factoryClass.getName() })); 
/* 446 */     return true;
/*     */   }
/*     */   
/*     */   private void checkRegistered(String descriptorName, String productName, Object factoryInstance) {
/* 456 */     if (this.arePreferencesSupported) {
/* 458 */       if (productName == null)
/* 459 */         throw new IllegalArgumentException("productName : " + JaiI18N.getString("Generic0")); 
/* 462 */       CaselessStringKey fileName = (CaselessStringKey)this.instancesByName.get(factoryInstance);
/* 465 */       if (fileName != null) {
/* 467 */         Vector v = (Vector)this.instances.get(fileName);
/* 469 */         String pn = v.get(1);
/* 470 */         String dn = v.get(2);
/* 472 */         if (dn != null && dn.equalsIgnoreCase(descriptorName) && pn != null && pn.equalsIgnoreCase(productName))
/*     */           return; 
/*     */       } 
/* 478 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache3", new Object[] { factoryInstance.getClass().getName(), descriptorName, productName }));
/*     */     } 
/* 484 */     CaselessStringKey key = new CaselessStringKey(descriptorName);
/* 486 */     if (factoryInstance != this.instances.get(key))
/* 487 */       throw new IllegalArgumentException(JaiI18N.formatMsg("FactoryCache4", new Object[] { factoryInstance.getClass().getName(), descriptorName })); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\FactoryCache.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */