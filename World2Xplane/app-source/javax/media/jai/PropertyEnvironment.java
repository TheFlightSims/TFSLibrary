/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.CaselessStringKeyHashtable;
/*     */ import com.sun.media.jai.util.PropertyUtil;
/*     */ import java.awt.Image;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import javax.media.jai.util.CaselessStringKey;
/*     */ 
/*     */ class PropertyEnvironment implements PropertySource {
/*     */   Vector pg;
/*     */   
/*     */   Vector sources;
/*     */   
/*  44 */   private static final Object PRESENT = new Object();
/*     */   
/*     */   CaselessStringKeyHashtable suppressed;
/*     */   
/*     */   CaselessStringKeyHashtable sourceForProp;
/*     */   
/*     */   private Object op;
/*     */   
/*     */   private CaselessStringKeyHashtable propNames;
/*     */   
/*  72 */   private PropertySource defaultPropertySource = null;
/*     */   
/*     */   private boolean areDefaultsMapped = true;
/*     */   
/*     */   public PropertyEnvironment(Vector sources, Vector generators, Vector suppressed, Hashtable sourceForProp, Object op) {
/*  95 */     this.sources = sources;
/*  96 */     this.pg = (generators == null) ? null : (Vector)generators.clone();
/* 100 */     this.suppressed = new CaselessStringKeyHashtable();
/* 102 */     if (suppressed != null) {
/* 103 */       Enumeration e = suppressed.elements();
/* 105 */       while (e.hasMoreElements())
/* 106 */         this.suppressed.put(e.nextElement(), PRESENT); 
/*     */     } 
/* 110 */     this.sourceForProp = (sourceForProp == null) ? null : new CaselessStringKeyHashtable(sourceForProp);
/* 113 */     this.op = op;
/* 115 */     hashNames();
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 125 */     mapDefaults();
/* 127 */     int count = 0;
/* 128 */     String[] names = new String[this.propNames.size()];
/* 129 */     for (Enumeration e = this.propNames.keys(); e.hasMoreElements();)
/* 130 */       names[count++] = ((CaselessStringKey)e.nextElement()).getName(); 
/* 133 */     return names;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames(String prefix) {
/* 150 */     String[] propertyNames = getPropertyNames();
/* 151 */     return PropertyUtil.getPropertyNames(propertyNames, prefix);
/*     */   }
/*     */   
/*     */   public Class getPropertyClass(String propertyName) {
/* 166 */     if (propertyName == null)
/* 167 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 169 */     return null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 179 */     if (name == null)
/* 180 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 183 */     mapDefaults();
/* 185 */     Object o = this.propNames.get(name);
/* 187 */     Object property = null;
/* 188 */     if (o == null)
/* 189 */       return Image.UndefinedProperty; 
/* 190 */     if (o instanceof PropertyGenerator) {
/* 191 */       property = ((PropertyGenerator)o).getProperty(name, this.op);
/* 192 */     } else if (o instanceof Integer) {
/* 193 */       int srcIndex = ((Integer)o).intValue();
/* 194 */       PropertySource src = this.sources.elementAt(srcIndex);
/* 195 */       property = src.getProperty(name);
/* 196 */     } else if (o instanceof PropertySource) {
/* 197 */       property = ((PropertySource)o).getProperty(name);
/*     */     } 
/* 200 */     return property;
/*     */   }
/*     */   
/*     */   public void copyPropertyFromSource(String propertyName, int sourceIndex) {
/* 206 */     PropertySource propertySource = this.sources.elementAt(sourceIndex);
/* 208 */     this.propNames.put(propertyName, propertySource);
/* 209 */     this.suppressed.remove(propertyName);
/*     */   }
/*     */   
/*     */   public void suppressProperty(String propertyName) {
/* 213 */     this.suppressed.put(propertyName, PRESENT);
/* 214 */     hashNames();
/*     */   }
/*     */   
/*     */   public void addPropertyGenerator(PropertyGenerator generator) {
/* 218 */     if (this.pg == null)
/* 219 */       this.pg = new Vector(); 
/* 221 */     this.pg.addElement(generator);
/* 225 */     removeSuppressedProps(generator);
/* 226 */     hashNames();
/*     */   }
/*     */   
/*     */   public void setDefaultPropertySource(PropertySource ps) {
/* 241 */     if (ps == this.defaultPropertySource)
/*     */       return; 
/* 245 */     if (this.defaultPropertySource != null)
/* 247 */       hashNames(); 
/* 251 */     this.areDefaultsMapped = false;
/* 254 */     this.defaultPropertySource = ps;
/*     */   }
/*     */   
/*     */   private void mapDefaults() {
/* 264 */     if (!this.areDefaultsMapped) {
/* 266 */       this.areDefaultsMapped = true;
/* 269 */       if (this.defaultPropertySource != null) {
/* 270 */         String[] names = this.defaultPropertySource.getPropertyNames();
/* 271 */         if (names != null) {
/* 272 */           int length = names.length;
/* 273 */           for (int i = 0; i < length; i++) {
/* 274 */             if (!this.suppressed.containsKey(names[i])) {
/* 275 */               Object o = this.propNames.get(names[i]);
/* 276 */               if (o == null || o instanceof Integer)
/* 280 */                 this.propNames.put(names[i], this.defaultPropertySource); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeSuppressedProps(PropertyGenerator generator) {
/* 290 */     String[] names = generator.getPropertyNames();
/* 291 */     for (int i = 0; i < names.length; i++)
/* 292 */       this.suppressed.remove(names[i]); 
/*     */   }
/*     */   
/*     */   private void hashNames() {
/* 297 */     this.propNames = new CaselessStringKeyHashtable();
/* 302 */     if (this.sources != null)
/* 304 */       for (int i = this.sources.size() - 1; i >= 0; i--) {
/* 305 */         Object o = this.sources.elementAt(i);
/* 307 */         if (o instanceof PropertySource) {
/* 308 */           PropertySource source = (PropertySource)o;
/* 309 */           String[] propertyNames = source.getPropertyNames();
/* 311 */           if (propertyNames != null)
/* 312 */             for (int j = 0; j < propertyNames.length; j++) {
/* 313 */               String name = propertyNames[j];
/* 314 */               if (!this.suppressed.containsKey(name))
/* 315 */                 this.propNames.put(name, new Integer(i)); 
/*     */             }  
/*     */         } 
/*     */       }  
/* 325 */     if (this.pg != null)
/* 328 */       for (Iterator it = this.pg.iterator(); it.hasNext(); ) {
/* 329 */         PropertyGenerator generator = it.next();
/* 330 */         if (generator.canGenerateProperties(this.op)) {
/* 331 */           String[] propertyNames = generator.getPropertyNames();
/* 332 */           if (propertyNames != null)
/* 333 */             for (int i = 0; i < propertyNames.length; i++) {
/* 334 */               String name = propertyNames[i];
/* 335 */               if (!this.suppressed.containsKey(name))
/* 336 */                 this.propNames.put(name, generator); 
/*     */             }  
/*     */         } 
/*     */       }  
/* 346 */     if (this.sourceForProp != null)
/* 347 */       for (Enumeration e = this.sourceForProp.keys(); e.hasMoreElements(); ) {
/* 348 */         CaselessStringKey name = e.nextElement();
/* 350 */         if (!this.suppressed.containsKey(name)) {
/* 351 */           Integer i = (Integer)this.sourceForProp.get(name);
/* 352 */           PropertySource propertySource = this.sources.elementAt(i.intValue());
/* 354 */           this.propNames.put(name, propertySource);
/*     */         } 
/*     */       }  
/* 360 */     this.areDefaultsMapped = false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyEnvironment.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */