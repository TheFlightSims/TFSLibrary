/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class DefaultRepository implements Repository {
/*  59 */   protected Map<Name, DataAccess<?, ?>> repository = new HashMap<Name, DataAccess<?, ?>>();
/*     */   
/*     */   public DataAccess<?, ?> access(String name) {
/*  65 */     return access((Name)new NameImpl(name));
/*     */   }
/*     */   
/*     */   public DataAccess<?, ?> access(Name name) {
/*  69 */     return this.repository.get(name);
/*     */   }
/*     */   
/*     */   public DataStore dataStore(String name) {
/*  73 */     return (DataStore)access(name);
/*     */   }
/*     */   
/*     */   public DataStore dataStore(Name name) {
/*  77 */     return (DataStore)access(name);
/*     */   }
/*     */   
/*     */   public void load(File propertiesFile) throws Exception {
/*  97 */     Properties properties = new Properties();
/*  98 */     properties.load(new FileInputStream(propertiesFile));
/* 100 */     for (Iterator<Map.Entry<Object, Object>> i = properties.entrySet().iterator(); i.hasNext(); ) {
/* 101 */       Map.Entry<String, String> entry = (Map.Entry<String, String>)i.next();
/* 102 */       String name = entry.getKey();
/* 103 */       String definition = entry.getValue();
/* 104 */       Map<String, Serializable> params = definition(definition);
/* 106 */       DataStore dataStore = DataStoreFinder.getDataStore(params);
/* 108 */       register(name, dataStore);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean lockExists(String lockID) {
/* 118 */     if (lockID == null)
/* 119 */       return false; 
/* 122 */     for (DataAccess<?, ?> access : this.repository.values()) {
/* 123 */       DataStore store = (DataStore)access;
/* 124 */       LockingManager lockManager = store.getLockingManager();
/* 125 */       if (lockManager == null)
/*     */         continue; 
/* 128 */       if (lockManager.exists(lockID))
/* 129 */         return true; 
/*     */     } 
/* 132 */     return false;
/*     */   }
/*     */   
/*     */   public boolean lockRefresh(String lockID, Transaction transaction) throws IOException {
/* 159 */     if (lockID == null)
/* 160 */       throw new IllegalArgumentException("lockID required"); 
/* 162 */     if (transaction == null || transaction == Transaction.AUTO_COMMIT)
/* 163 */       throw new IllegalArgumentException("Tansaction required (with authorization for " + lockID + ")"); 
/* 168 */     boolean refresh = false;
/* 169 */     for (DataAccess<?, ?> access : this.repository.values()) {
/* 170 */       DataStore store = (DataStore)access;
/* 171 */       LockingManager lockManager = store.getLockingManager();
/* 172 */       if (lockManager == null)
/*     */         continue; 
/* 175 */       if (lockManager.release(lockID, transaction))
/* 176 */         refresh = true; 
/*     */     } 
/* 179 */     return refresh;
/*     */   }
/*     */   
/*     */   public boolean lockRelease(String lockID, Transaction transaction) throws IOException {
/* 202 */     if (lockID == null)
/* 203 */       throw new IllegalArgumentException("lockID required"); 
/* 205 */     if (transaction == null || transaction == Transaction.AUTO_COMMIT)
/* 206 */       throw new IllegalArgumentException("Tansaction required (with authorization for " + lockID + ")"); 
/* 212 */     boolean release = false;
/* 213 */     for (DataAccess<?, ?> access : this.repository.values()) {
/* 214 */       DataStore store = (DataStore)access;
/* 215 */       LockingManager lockManager = store.getLockingManager();
/* 216 */       if (lockManager == null)
/*     */         continue; 
/* 219 */       if (lockManager.release(lockID, transaction))
/* 220 */         release = true; 
/*     */     } 
/* 223 */     return release;
/*     */   }
/*     */   
/*     */   public void register(String name, DataAccess<?, ?> dataStore) throws IOException {
/* 239 */     register((Name)new NameImpl(name), dataStore);
/*     */   }
/*     */   
/*     */   public void register(Name name, DataAccess<?, ?> dataStore) throws IOException {
/* 243 */     if (this.repository.containsKey(name))
/* 244 */       throw new IOException("Name " + name + " already registered"); 
/* 246 */     if (this.repository.containsValue(dataStore))
/* 247 */       throw new IOException("The dataStore already registered"); 
/* 249 */     this.repository.put(name, dataStore);
/*     */   }
/*     */   
/*     */   public DataStore datastore(String id) {
/* 253 */     return dataStore((Name)new NameImpl(id));
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource source(String dataStoreId, String typeName) throws IOException {
/* 258 */     DataStore ds = datastore(dataStoreId);
/* 259 */     return ds.getFeatureSource(typeName);
/*     */   }
/*     */   
/*     */   private static final Map<String, Serializable> definition(String definition) throws ParseException {
/* 267 */     Map<String, Serializable> map = new HashMap<String, Serializable>();
/* 269 */     String[] params = definition.split(",");
/* 270 */     int offset = 0;
/* 271 */     for (int i = 0; i < params.length; i++) {
/* 272 */       String[] vals = params[i].split("=");
/* 273 */       if (vals.length == 2) {
/* 274 */         map.put(vals[0].trim(), vals[1].trim());
/*     */       } else {
/* 276 */         throw new ParseException("Could not interpret " + params[i], offset);
/*     */       } 
/* 278 */       offset += params[i].length();
/*     */     } 
/* 280 */     return map;
/*     */   }
/*     */   
/*     */   public Set<Name> getNames() {
/* 285 */     Set<Name> names = new HashSet(this.repository.keySet());
/* 286 */     return names;
/*     */   }
/*     */   
/*     */   public List<DataStore> getDataStores() {
/* 290 */     List<DataStore> list = new ArrayList(this.repository.values());
/* 291 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DefaultRepository.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */