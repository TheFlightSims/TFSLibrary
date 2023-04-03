/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ public class CompositeConfiguration extends AbstractConfiguration implements Cloneable {
/*  42 */   private List configList = new LinkedList();
/*     */   
/*     */   private Configuration inMemoryConfiguration;
/*     */   
/*     */   public CompositeConfiguration() {
/*  56 */     clear();
/*     */   }
/*     */   
/*     */   public CompositeConfiguration(Configuration inMemoryConfiguration) {
/*  68 */     this.configList.clear();
/*  69 */     this.inMemoryConfiguration = inMemoryConfiguration;
/*  70 */     this.configList.add(inMemoryConfiguration);
/*     */   }
/*     */   
/*     */   public CompositeConfiguration(Collection configurations) {
/*  81 */     this(new BaseConfiguration(), configurations);
/*     */   }
/*     */   
/*     */   public CompositeConfiguration(Configuration inMemoryConfiguration, Collection configurations) {
/*  93 */     this(inMemoryConfiguration);
/*  95 */     if (configurations != null) {
/*  97 */       Iterator it = configurations.iterator();
/*  98 */       while (it.hasNext())
/* 100 */         addConfiguration(it.next()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addConfiguration(Configuration config) {
/* 112 */     if (!this.configList.contains(config)) {
/* 118 */       this.configList.add(this.configList.indexOf(this.inMemoryConfiguration), config);
/* 120 */       if (config instanceof AbstractConfiguration)
/* 122 */         ((AbstractConfiguration)config).setThrowExceptionOnMissing(isThrowExceptionOnMissing()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeConfiguration(Configuration config) {
/* 136 */     if (!config.equals(this.inMemoryConfiguration))
/* 138 */       this.configList.remove(config); 
/*     */   }
/*     */   
/*     */   public int getNumberOfConfigurations() {
/* 149 */     return this.configList.size();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 157 */     this.configList.clear();
/* 159 */     this.inMemoryConfiguration = new BaseConfiguration();
/* 160 */     ((BaseConfiguration)this.inMemoryConfiguration).setThrowExceptionOnMissing(isThrowExceptionOnMissing());
/* 161 */     ((BaseConfiguration)this.inMemoryConfiguration).setListDelimiter(getListDelimiter());
/* 162 */     ((BaseConfiguration)this.inMemoryConfiguration).setDelimiterParsingDisabled(isDelimiterParsingDisabled());
/* 163 */     this.configList.add(this.inMemoryConfiguration);
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object token) {
/* 174 */     this.inMemoryConfiguration.addProperty(key, token);
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 186 */     Configuration firstMatchingConfiguration = null;
/* 187 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 189 */       Configuration config = i.next();
/* 190 */       if (config.containsKey(key)) {
/* 192 */         firstMatchingConfiguration = config;
/*     */         break;
/*     */       } 
/*     */     } 
/* 197 */     if (firstMatchingConfiguration != null)
/* 199 */       return firstMatchingConfiguration.getProperty(key); 
/* 203 */     return null;
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 209 */     List keys = new ArrayList();
/* 210 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 212 */       Configuration config = i.next();
/* 214 */       Iterator j = config.getKeys();
/* 215 */       while (j.hasNext()) {
/* 217 */         String key = j.next();
/* 218 */         if (!keys.contains(key))
/* 220 */           keys.add(key); 
/*     */       } 
/*     */     } 
/* 225 */     return keys.iterator();
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String key) {
/* 230 */     List keys = new ArrayList();
/* 231 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 233 */       Configuration config = i.next();
/* 235 */       Iterator j = config.getKeys(key);
/* 236 */       while (j.hasNext()) {
/* 238 */         String newKey = j.next();
/* 239 */         if (!keys.contains(newKey))
/* 241 */           keys.add(newKey); 
/*     */       } 
/*     */     } 
/* 246 */     return keys.iterator();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 251 */     boolean isEmpty = true;
/* 252 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 254 */       Configuration config = i.next();
/* 255 */       if (!config.isEmpty())
/* 257 */         return false; 
/*     */     } 
/* 261 */     return isEmpty;
/*     */   }
/*     */   
/*     */   protected void clearPropertyDirect(String key) {
/* 266 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 268 */       Configuration config = i.next();
/* 269 */       config.clearProperty(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 275 */     for (Iterator i = this.configList.iterator(); i.hasNext(); ) {
/* 277 */       Configuration config = i.next();
/* 278 */       if (config.containsKey(key))
/* 280 */         return true; 
/*     */     } 
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   public List getList(String key, List defaultValue) {
/* 288 */     List list = new ArrayList();
/* 291 */     Iterator it = this.configList.iterator();
/* 292 */     while (it.hasNext() && list.isEmpty()) {
/* 294 */       Configuration config = it.next();
/* 295 */       if (config != this.inMemoryConfiguration && config.containsKey(key))
/* 297 */         appendListProperty(list, config, key); 
/*     */     } 
/* 302 */     appendListProperty(list, this.inMemoryConfiguration, key);
/* 304 */     if (list.isEmpty())
/* 306 */       return defaultValue; 
/* 309 */     ListIterator lit = list.listIterator();
/* 310 */     while (lit.hasNext())
/* 312 */       lit.set(interpolate(lit.next())); 
/* 315 */     return list;
/*     */   }
/*     */   
/*     */   public String[] getStringArray(String key) {
/* 320 */     List list = getList(key);
/* 323 */     String[] tokens = new String[list.size()];
/* 325 */     for (int i = 0; i < tokens.length; i++)
/* 327 */       tokens[i] = String.valueOf(list.get(i)); 
/* 330 */     return tokens;
/*     */   }
/*     */   
/*     */   public Configuration getConfiguration(int index) {
/* 341 */     return this.configList.get(index);
/*     */   }
/*     */   
/*     */   public Configuration getInMemoryConfiguration() {
/* 352 */     return this.inMemoryConfiguration;
/*     */   }
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 369 */       CompositeConfiguration copy = (CompositeConfiguration)super.clone();
/* 371 */       copy.clearConfigurationListeners();
/* 372 */       copy.configList = new LinkedList();
/* 373 */       copy.inMemoryConfiguration = ConfigurationUtils.cloneConfiguration(getInMemoryConfiguration());
/* 375 */       copy.configList.add(copy.inMemoryConfiguration);
/* 377 */       for (int i = 0; i < getNumberOfConfigurations(); i++) {
/* 379 */         Configuration config = getConfiguration(i);
/* 380 */         if (config != getInMemoryConfiguration())
/* 382 */           copy.addConfiguration(ConfigurationUtils.cloneConfiguration(config)); 
/*     */       } 
/* 387 */       return copy;
/* 389 */     } catch (CloneNotSupportedException cnex) {
/* 392 */       throw new ConfigurationRuntimeException(cnex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDelimiterParsingDisabled(boolean delimiterParsingDisabled) {
/* 406 */     ((BaseConfiguration)getInMemoryConfiguration()).setDelimiterParsingDisabled(delimiterParsingDisabled);
/* 408 */     super.setDelimiterParsingDisabled(delimiterParsingDisabled);
/*     */   }
/*     */   
/*     */   public void setListDelimiter(char listDelimiter) {
/* 420 */     ((BaseConfiguration)getInMemoryConfiguration()).setListDelimiter(listDelimiter);
/* 422 */     super.setListDelimiter(listDelimiter);
/*     */   }
/*     */   
/*     */   public Configuration getSource(String key) {
/* 450 */     if (key == null)
/* 452 */       throw new IllegalArgumentException("Key must not be null!"); 
/* 455 */     Configuration source = null;
/* 456 */     for (Iterator it = this.configList.iterator(); it.hasNext(); ) {
/* 458 */       Configuration conf = it.next();
/* 459 */       if (conf.containsKey(key)) {
/* 461 */         if (source != null)
/* 463 */           throw new IllegalArgumentException("The key " + key + " is defined by multiple sources!"); 
/* 466 */         source = conf;
/*     */       } 
/*     */     } 
/* 470 */     return source;
/*     */   }
/*     */   
/*     */   private static void appendListProperty(List dest, Configuration config, String key) {
/* 485 */     Object value = config.getProperty(key);
/* 486 */     if (value != null)
/* 488 */       if (value instanceof Collection) {
/* 490 */         dest.addAll((Collection)value);
/*     */       } else {
/* 494 */         dest.add(value);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\CompositeConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */