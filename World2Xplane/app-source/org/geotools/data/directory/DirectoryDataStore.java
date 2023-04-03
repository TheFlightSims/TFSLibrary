/*     */ package org.geotools.data.directory;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataStoreFinder;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.DefaultServiceInfo;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.LockingManager;
/*     */ import org.geotools.data.Query;
/*     */ import org.geotools.data.ServiceInfo;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.simple.SimpleFeatureLocking;
/*     */ import org.geotools.data.simple.SimpleFeatureSource;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.feature.FeatureTypes;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class DirectoryDataStore implements DataStore {
/*     */   DirectoryTypeCache cache;
/*     */   
/*     */   DirectoryLockingManager lm;
/*     */   
/*     */   public DirectoryDataStore(File directory, FileStoreFactory dialect) throws IOException {
/*  63 */     this.cache = new DirectoryTypeCache(directory, dialect);
/*     */   }
/*     */   
/*     */   public FeatureReader<SimpleFeatureType, SimpleFeature> getFeatureReader(Query query, Transaction transaction) throws IOException {
/*  68 */     String typeName = query.getTypeName();
/*  69 */     return getDataStore(typeName).getFeatureReader(query, transaction);
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource(String typeName) throws IOException {
/*  74 */     SimpleFeatureSource fs = getDataStore(typeName).getFeatureSource(typeName);
/*  75 */     if (fs instanceof SimpleFeatureLocking)
/*  76 */       return new DirectoryFeatureLocking((SimpleFeatureLocking)fs); 
/*  77 */     if (fs instanceof org.geotools.data.FeatureStore)
/*  78 */       return new DirectoryFeatureStore((SimpleFeatureStore)fs); 
/*  80 */     return new DirectoryFeatureSource(fs);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Filter filter, Transaction transaction) throws IOException {
/*  87 */     return getDataStore(typeName).getFeatureWriter(typeName, filter, transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriter(String typeName, Transaction transaction) throws IOException {
/*  92 */     return getDataStore(typeName).getFeatureWriter(typeName, transaction);
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> getFeatureWriterAppend(String typeName, Transaction transaction) throws IOException {
/*  97 */     return getDataStore(typeName).getFeatureWriterAppend(typeName, transaction);
/*     */   }
/*     */   
/*     */   public LockingManager getLockingManager() {
/* 101 */     if (this.lm == null)
/* 102 */       this.lm = new DirectoryLockingManager(this.cache); 
/* 104 */     return this.lm;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(String typeName) throws IOException {
/* 108 */     return getDataStore(typeName).getSchema(typeName);
/*     */   }
/*     */   
/*     */   public String[] getTypeNames() throws IOException {
/* 112 */     Set<String> typeNames = this.cache.getTypeNames();
/* 113 */     return typeNames.<String>toArray(new String[typeNames.size()]);
/*     */   }
/*     */   
/*     */   public void updateSchema(String typeName, SimpleFeatureType featureType) throws IOException {
/* 118 */     getDataStore(typeName).updateSchema(typeName, featureType);
/*     */   }
/*     */   
/*     */   public void createSchema(SimpleFeatureType featureType) throws IOException {
/* 122 */     File f = new File(this.cache.directory, featureType.getTypeName() + ".shp");
/* 124 */     Map<String, Serializable> params = new HashMap<String, Serializable>();
/* 125 */     params.put("url", DataUtilities.fileToURL(f));
/* 126 */     params.put("filetype", "shapefile");
/* 127 */     DataStore ds = null;
/*     */     try {
/* 129 */       ds = DataStoreFinder.getDataStore(params);
/* 130 */       if (ds != null) {
/* 131 */         ds.createSchema((FeatureType)featureType);
/* 132 */         ds.dispose();
/* 133 */         this.cache.refreshCacheContents();
/*     */       } 
/* 135 */     } catch (Exception e) {
/* 136 */       throw (IOException)(new IOException("Error creating new data store")).initCause(e);
/*     */     } 
/* 138 */     if (ds == null)
/* 139 */       throw new IOException("Could not find the shapefile data store in the classpath"); 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 144 */     this.cache.dispose();
/*     */   }
/*     */   
/*     */   public SimpleFeatureSource getFeatureSource(Name typeName) throws IOException {
/* 149 */     return getFeatureSource(typeName.getLocalPart());
/*     */   }
/*     */   
/*     */   public ServiceInfo getInfo() {
/* 153 */     DefaultServiceInfo info = new DefaultServiceInfo();
/* 154 */     info.setDescription("Features from Directory " + this.cache.directory);
/* 155 */     info.setSchema(FeatureTypes.DEFAULT_NAMESPACE);
/* 156 */     info.setSource(this.cache.directory.toURI());
/*     */     try {
/* 158 */       info.setPublisher(new URI(System.getProperty("user.name")));
/* 159 */     } catch (URISyntaxException e) {}
/* 161 */     return (ServiceInfo)info;
/*     */   }
/*     */   
/*     */   public List<Name> getNames() throws IOException {
/* 165 */     String[] typeNames = getTypeNames();
/* 166 */     List<Name> names = new ArrayList<Name>(typeNames.length);
/* 167 */     for (String typeName : typeNames)
/* 168 */       names.add(new NameImpl(typeName)); 
/* 170 */     return names;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getSchema(Name name) throws IOException {
/* 174 */     return getSchema(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public void updateSchema(Name typeName, SimpleFeatureType featureType) throws IOException {
/* 179 */     updateSchema(typeName.getLocalPart(), featureType);
/*     */   }
/*     */   
/*     */   public DataStore getDataStore(String typeName) throws IOException {
/* 190 */     DataStore store = this.cache.getDataStore(typeName, true);
/* 191 */     if (store == null)
/* 192 */       throw new IOException("Feature type " + typeName + " is unknown"); 
/* 193 */     return store;
/*     */   }
/*     */   
/*     */   public void removeSchema(Name name) throws IOException {
/* 198 */     removeSchema(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public void removeSchema(String name) throws IOException {
/* 204 */     getDataStore(name).removeSchema(name);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryDataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */