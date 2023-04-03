/*     */ package org.geotools.data.shapefile;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.AbstractDataStoreFactory;
/*     */ import org.geotools.data.DataAccess;
/*     */ import org.geotools.data.DataAccessFactory;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FileDataStore;
/*     */ import org.geotools.data.FileDataStoreFactorySpi;
/*     */ import org.geotools.data.directory.DirectoryDataStore;
/*     */ import org.geotools.data.directory.FileStoreFactory;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.util.KVP;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class ShapefileDataStoreFactory extends AbstractDataStoreFactory implements FileDataStoreFactorySpi {
/*  51 */   static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*  56 */   public static final DataAccessFactory.Param URLP = new DataAccessFactory.Param("url", URL.class, "url to a .shp file", true, null, (Map)new KVP(new Object[] { "ext", "shp" }));
/*     */   
/*  62 */   public static final DataAccessFactory.Param NAMESPACEP = new DataAccessFactory.Param("namespace", URI.class, "uri to a the namespace", false, null, (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*  69 */   public static final DataAccessFactory.Param MEMORY_MAPPED = new DataAccessFactory.Param("memory mapped buffer", Boolean.class, "enable/disable the use of memory-mapped io", false, Boolean.valueOf(false), (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*  76 */   public static final DataAccessFactory.Param CACHE_MEMORY_MAPS = new DataAccessFactory.Param("cache and reuse memory maps", Boolean.class, "only memory map a file one, then cache and reuse the map", false, Boolean.valueOf(true), (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*  83 */   public static final DataAccessFactory.Param FILE_TYPE = new DataAccessFactory.Param("filetype", String.class, "Discriminator for directory stores", false, "shapefile", (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*  90 */   public static final DataAccessFactory.Param CREATE_SPATIAL_INDEX = new DataAccessFactory.Param("create spatial index", Boolean.class, "enable/disable the automatic creation of spatial index", false, Boolean.valueOf(true), (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*  97 */   public static final DataAccessFactory.Param DBFCHARSET = new DataAccessFactory.Param("charset", Charset.class, "character used to decode strings from the DBF file", false, Charset.forName("ISO-8859-1"), (Map)new KVP(new Object[] { "level", "advanced" })) {
/*     */       public Object parse(String text) throws IOException {
/* 106 */         return Charset.forName(text);
/*     */       }
/*     */       
/*     */       public String text(Object value) {
/* 110 */         return ((Charset)value).name();
/*     */       }
/*     */     };
/*     */   
/* 117 */   public static final DataAccessFactory.Param FSTYPE = new DataAccessFactory.Param("fstype", String.class, "Enable using a setting of 'shape'.", false, "shape", (Map)new KVP(new Object[] { "level", "advanced", "options", Arrays.asList(new String[] { "shape-ng", "shape", "index" }) }));
/*     */   
/* 123 */   public static final DataAccessFactory.Param DBFTIMEZONE = new DataAccessFactory.Param("timezone", TimeZone.class, "time zone used to read dates from the DBF file", false, TimeZone.getDefault(), (Map)new KVP(new Object[] { "level", "advanced" })) {
/*     */       public Object parse(String text) throws IOException {
/* 128 */         return TimeZone.getTimeZone(text);
/*     */       }
/*     */       
/*     */       public String text(Object value) {
/* 132 */         return ((TimeZone)value).getID();
/*     */       }
/*     */     };
/*     */   
/* 139 */   public static final DataAccessFactory.Param ENABLE_SPATIAL_INDEX = new DataAccessFactory.Param("enable spatial index", Boolean.class, "enable/disable the use of spatial index for local shapefiles", false, Boolean.valueOf(true), (Map)new KVP(new Object[] { "level", "advanced" }));
/*     */   
/*     */   public String getDisplayName() {
/* 144 */     return "Shapefile";
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 148 */     return "ESRI(tm) Shapefiles (*.shp)";
/*     */   }
/*     */   
/*     */   public DataAccessFactory.Param[] getParametersInfo() {
/* 152 */     return new DataAccessFactory.Param[] { URLP, NAMESPACEP, ENABLE_SPATIAL_INDEX, CREATE_SPATIAL_INDEX, DBFCHARSET, DBFTIMEZONE, MEMORY_MAPPED, CACHE_MEMORY_MAPS, FILE_TYPE, FSTYPE };
/*     */   }
/*     */   
/*     */   public boolean isAvailable() {
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 161 */     return Collections.EMPTY_MAP;
/*     */   }
/*     */   
/*     */   public DataStore createDataStore(Map<String, Serializable> params) throws IOException {
/* 165 */     URL url = lookup(URLP, params, URL.class);
/* 166 */     Boolean isMemoryMapped = lookup(MEMORY_MAPPED, params, Boolean.class);
/* 167 */     Boolean cacheMemoryMaps = lookup(CACHE_MEMORY_MAPS, params, Boolean.class);
/* 168 */     URI namespace = lookup(NAMESPACEP, params, URI.class);
/* 169 */     Charset dbfCharset = lookup(DBFCHARSET, params, Charset.class);
/* 170 */     TimeZone dbfTimeZone = lookup(DBFTIMEZONE, params, TimeZone.class);
/* 171 */     Boolean isCreateSpatialIndex = lookup(CREATE_SPATIAL_INDEX, params, Boolean.class);
/* 172 */     Boolean isEnableSpatialIndex = (Boolean)ENABLE_SPATIAL_INDEX.lookUp(params);
/* 173 */     if (isEnableSpatialIndex == null)
/* 175 */       isEnableSpatialIndex = Boolean.TRUE; 
/* 179 */     File dir = DataUtilities.urlToFile(url);
/* 180 */     if (dir != null && dir.isDirectory())
/* 181 */       return (DataStore)new DirectoryDataStore(DataUtilities.urlToFile(url), new ShpFileStoreFactory(this, params)); 
/* 184 */     ShpFiles shpFiles = new ShpFiles(url);
/* 186 */     boolean isLocal = shpFiles.isLocal();
/* 187 */     boolean useMemoryMappedBuffer = (isLocal && isMemoryMapped.booleanValue());
/* 188 */     boolean enableIndex = (isEnableSpatialIndex.booleanValue() && isLocal);
/* 189 */     boolean createIndex = (isCreateSpatialIndex.booleanValue() && enableIndex);
/* 192 */     ShapefileDataStore store = new ShapefileDataStore(url);
/* 193 */     if (namespace != null)
/* 194 */       store.setNamespaceURI(namespace.toString()); 
/* 196 */     store.setMemoryMapped(useMemoryMappedBuffer);
/* 197 */     store.setBufferCachingEnabled(cacheMemoryMaps.booleanValue());
/* 198 */     store.setCharset(dbfCharset);
/* 199 */     store.setTimeZone(dbfTimeZone);
/* 200 */     store.setIndexed(enableIndex);
/* 201 */     store.setIndexCreationEnabled(createIndex);
/* 202 */     return (DataStore)store;
/*     */   }
/*     */   
/*     */   public DataStore createNewDataStore(Map<String, Serializable> params) throws IOException {
/* 207 */     return createDataStore(params);
/*     */   }
/*     */   
/*     */   <T> T lookup(DataAccessFactory.Param param, Map<String, Serializable> params, Class<T> target) throws IOException {
/* 222 */     T result = (T)param.lookUp(params);
/* 223 */     if (result == null)
/* 224 */       return (T)param.getDefaultValue(); 
/* 226 */     return result;
/*     */   }
/*     */   
/*     */   public boolean canProcess(Map params) {
/* 233 */     if (!super.canProcess(params))
/* 234 */       return false; 
/*     */     try {
/* 237 */       URL url = (URL)URLP.lookUp(params);
/* 238 */       if (canProcess(url))
/* 239 */         return true; 
/* 242 */       Object fileType = FILE_TYPE.lookUp(params);
/* 243 */       File dir = DataUtilities.urlToFile(url);
/* 245 */       return (dir.isDirectory() && (fileType == null || "shapefile".equals(fileType)));
/* 247 */     } catch (IOException e) {
/* 248 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean canProcess(URL f) {
/* 253 */     return (f != null && f.getFile().toUpperCase().endsWith("SHP"));
/*     */   }
/*     */   
/*     */   public static class ShpFileStoreFactory implements FileStoreFactory {
/*     */     ShapefileDataStoreFactory shpFactory;
/*     */     
/*     */     Map originalParams;
/*     */     
/*     */     public ShpFileStoreFactory(ShapefileDataStoreFactory factory, Map originalParams) {
/* 268 */       this.shpFactory = factory;
/* 269 */       this.originalParams = originalParams;
/*     */     }
/*     */     
/*     */     public DataStore getDataStore(File file) throws IOException {
/* 273 */       URL url = DataUtilities.fileToURL(file);
/* 274 */       if (this.shpFactory.canProcess(url)) {
/* 275 */         Map<String, Serializable> params = new HashMap<String, Serializable>(this.originalParams);
/* 276 */         params.put(ShapefileDataStoreFactory.URLP.key, url);
/* 277 */         return this.shpFactory.createDataStore(params);
/*     */       } 
/* 279 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getFileExtensions() {
/* 287 */     return new String[] { ".shp" };
/*     */   }
/*     */   
/*     */   public FileDataStore createDataStore(URL url) throws IOException {
/* 292 */     Map<String, Serializable> params = new HashMap<String, Serializable>();
/* 293 */     params.put(URLP.key, url);
/* 295 */     boolean isLocal = url.getProtocol().equalsIgnoreCase("file");
/* 296 */     File file = DataUtilities.urlToFile(url);
/* 297 */     if (file != null && file.isDirectory())
/* 298 */       return null; 
/* 300 */     if (isLocal && !file.exists())
/* 301 */       return (FileDataStore)createNewDataStore(params); 
/* 303 */     return (FileDataStore)createDataStore(params);
/*     */   }
/*     */   
/*     */   public String getTypeName(URL url) throws IOException {
/* 310 */     FileDataStore fileDataStore = createDataStore(url);
/* 311 */     String[] names = fileDataStore.getTypeNames();
/* 312 */     fileDataStore.dispose();
/* 313 */     return (names == null || names.length == 0) ? null : names[0];
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\ShapefileDataStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */