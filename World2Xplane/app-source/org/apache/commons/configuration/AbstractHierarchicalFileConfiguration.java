/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*     */ import org.apache.commons.configuration.event.ConfigurationEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationListener;
/*     */ import org.apache.commons.configuration.reloading.Reloadable;
/*     */ import org.apache.commons.configuration.reloading.ReloadingStrategy;
/*     */ 
/*     */ public abstract class AbstractHierarchicalFileConfiguration extends HierarchicalConfiguration implements FileConfiguration, ConfigurationListener, ConfigurationErrorListener, FileSystemBased, Reloadable {
/*     */   private FileConfigurationDelegate delegate;
/*     */   
/*     */   protected AbstractHierarchicalFileConfiguration() {
/*  65 */     initialize();
/*     */   }
/*     */   
/*     */   protected AbstractHierarchicalFileConfiguration(HierarchicalConfiguration c) {
/*  78 */     super(c);
/*  79 */     initialize();
/*     */   }
/*     */   
/*     */   public AbstractHierarchicalFileConfiguration(String fileName) throws ConfigurationException {
/*  90 */     this();
/*  92 */     this.delegate.setFileName(fileName);
/*  95 */     load();
/*     */   }
/*     */   
/*     */   public AbstractHierarchicalFileConfiguration(File file) throws ConfigurationException {
/* 106 */     this();
/* 108 */     setFile(file);
/* 111 */     if (file.exists())
/* 113 */       load(); 
/*     */   }
/*     */   
/*     */   public AbstractHierarchicalFileConfiguration(URL url) throws ConfigurationException {
/* 125 */     this();
/* 127 */     setURL(url);
/* 130 */     load();
/*     */   }
/*     */   
/*     */   private void initialize() {
/* 138 */     this.delegate = createDelegate();
/* 139 */     initDelegate(this.delegate);
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object obj) {
/* 144 */     synchronized (this.delegate.getReloadLock()) {
/* 146 */       super.addPropertyDirect(key, obj);
/* 147 */       this.delegate.possiblySave();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/* 153 */     synchronized (this.delegate.getReloadLock()) {
/* 155 */       super.clearProperty(key);
/* 156 */       this.delegate.possiblySave();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearTree(String key) {
/* 162 */     synchronized (this.delegate.getReloadLock()) {
/* 164 */       super.clearTree(key);
/* 165 */       this.delegate.possiblySave();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setProperty(String key, Object value) {
/* 171 */     synchronized (this.delegate.getReloadLock()) {
/* 173 */       super.setProperty(key, value);
/* 174 */       this.delegate.possiblySave();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void load() throws ConfigurationException {
/* 180 */     this.delegate.load();
/*     */   }
/*     */   
/*     */   public void load(String fileName) throws ConfigurationException {
/* 185 */     this.delegate.load(fileName);
/*     */   }
/*     */   
/*     */   public void load(File file) throws ConfigurationException {
/* 190 */     this.delegate.load(file);
/*     */   }
/*     */   
/*     */   public void load(URL url) throws ConfigurationException {
/* 195 */     this.delegate.load(url);
/*     */   }
/*     */   
/*     */   public void load(InputStream in) throws ConfigurationException {
/* 200 */     this.delegate.load(in);
/*     */   }
/*     */   
/*     */   public void load(InputStream in, String encoding) throws ConfigurationException {
/* 205 */     this.delegate.load(in, encoding);
/*     */   }
/*     */   
/*     */   public void save() throws ConfigurationException {
/* 210 */     this.delegate.save();
/*     */   }
/*     */   
/*     */   public void save(String fileName) throws ConfigurationException {
/* 215 */     this.delegate.save(fileName);
/*     */   }
/*     */   
/*     */   public void save(File file) throws ConfigurationException {
/* 220 */     this.delegate.save(file);
/*     */   }
/*     */   
/*     */   public void save(URL url) throws ConfigurationException {
/* 225 */     this.delegate.save(url);
/*     */   }
/*     */   
/*     */   public void save(OutputStream out) throws ConfigurationException {
/* 230 */     this.delegate.save(out);
/*     */   }
/*     */   
/*     */   public void save(OutputStream out, String encoding) throws ConfigurationException {
/* 235 */     this.delegate.save(out, encoding);
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 240 */     return this.delegate.getFileName();
/*     */   }
/*     */   
/*     */   public void setFileName(String fileName) {
/* 245 */     this.delegate.setFileName(fileName);
/*     */   }
/*     */   
/*     */   public String getBasePath() {
/* 250 */     return this.delegate.getBasePath();
/*     */   }
/*     */   
/*     */   public void setBasePath(String basePath) {
/* 255 */     this.delegate.setBasePath(basePath);
/*     */   }
/*     */   
/*     */   public File getFile() {
/* 260 */     return this.delegate.getFile();
/*     */   }
/*     */   
/*     */   public void setFile(File file) {
/* 265 */     this.delegate.setFile(file);
/*     */   }
/*     */   
/*     */   public URL getURL() {
/* 270 */     return this.delegate.getURL();
/*     */   }
/*     */   
/*     */   public void setURL(URL url) {
/* 275 */     this.delegate.setURL(url);
/*     */   }
/*     */   
/*     */   public void setAutoSave(boolean autoSave) {
/* 280 */     this.delegate.setAutoSave(autoSave);
/*     */   }
/*     */   
/*     */   public boolean isAutoSave() {
/* 285 */     return this.delegate.isAutoSave();
/*     */   }
/*     */   
/*     */   public ReloadingStrategy getReloadingStrategy() {
/* 290 */     return this.delegate.getReloadingStrategy();
/*     */   }
/*     */   
/*     */   public void setReloadingStrategy(ReloadingStrategy strategy) {
/* 295 */     this.delegate.setReloadingStrategy(strategy);
/*     */   }
/*     */   
/*     */   public void reload() {
/* 300 */     reload(false);
/*     */   }
/*     */   
/*     */   private boolean reload(boolean checkReload) {
/* 305 */     synchronized (this.delegate.getReloadLock()) {
/* 307 */       setDetailEvents(false);
/*     */       try {
/* 310 */         return this.delegate.reload(checkReload);
/*     */       } finally {
/* 314 */         setDetailEvents(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void refresh() throws ConfigurationException {
/* 331 */     this.delegate.refresh();
/*     */   }
/*     */   
/*     */   public String getEncoding() {
/* 336 */     return this.delegate.getEncoding();
/*     */   }
/*     */   
/*     */   public void setEncoding(String encoding) {
/* 341 */     this.delegate.setEncoding(encoding);
/*     */   }
/*     */   
/*     */   public Object getReloadLock() {
/* 346 */     return this.delegate.getReloadLock();
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 351 */     reload();
/* 352 */     synchronized (this.delegate.getReloadLock()) {
/* 354 */       return super.containsKey(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 360 */     reload();
/* 361 */     synchronized (this.delegate.getReloadLock()) {
/* 363 */       return super.getKeys();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator getKeys(String prefix) {
/* 369 */     reload();
/* 370 */     synchronized (this.delegate.getReloadLock()) {
/* 372 */       return super.getKeys(prefix);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 378 */     if (reload(true))
/* 381 */       synchronized (this.delegate.getReloadLock()) {
/* 383 */         return super.getProperty(key);
/*     */       }  
/* 386 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 391 */     reload();
/* 392 */     synchronized (this.delegate.getReloadLock()) {
/* 394 */       return super.isEmpty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addNodes(String key, Collection nodes) {
/* 408 */     synchronized (this.delegate.getReloadLock()) {
/* 410 */       super.addNodes(key, nodes);
/* 411 */       this.delegate.possiblySave();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List fetchNodeList(String key) {
/* 424 */     reload();
/* 425 */     synchronized (this.delegate.getReloadLock()) {
/* 427 */       return super.fetchNodeList(key);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void subnodeConfigurationChanged(ConfigurationEvent event) {
/* 440 */     this.delegate.possiblySave();
/* 441 */     super.subnodeConfigurationChanged(event);
/*     */   }
/*     */   
/*     */   protected FileConfigurationDelegate createDelegate() {
/* 455 */     return new FileConfigurationDelegate();
/*     */   }
/*     */   
/*     */   private void initDelegate(FileConfigurationDelegate del) {
/* 465 */     del.addConfigurationListener(this);
/* 466 */     del.addErrorListener(this);
/* 467 */     del.setLogger(getLogger());
/*     */   }
/*     */   
/*     */   public void configurationChanged(ConfigurationEvent event) {
/* 480 */     setDetailEvents(true);
/*     */     try {
/* 483 */       fireEvent(event.getType(), event.getPropertyName(), event.getPropertyValue(), event.isBeforeUpdate());
/*     */     } finally {
/* 488 */       setDetailEvents(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void configurationError(ConfigurationErrorEvent event) {
/* 494 */     fireError(event.getType(), event.getPropertyName(), event.getPropertyValue(), event.getCause());
/*     */   }
/*     */   
/*     */   protected FileConfigurationDelegate getDelegate() {
/* 505 */     return this.delegate;
/*     */   }
/*     */   
/*     */   protected void setDelegate(FileConfigurationDelegate delegate) {
/* 514 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   public void setFileSystem(FileSystem fileSystem) {
/* 523 */     this.delegate.setFileSystem(fileSystem);
/*     */   }
/*     */   
/*     */   public void resetFileSystem() {
/* 531 */     this.delegate.resetFileSystem();
/*     */   }
/*     */   
/*     */   public FileSystem getFileSystem() {
/* 540 */     return this.delegate.getFileSystem();
/*     */   }
/*     */   
/*     */   protected class FileConfigurationDelegate extends AbstractFileConfiguration {
/*     */     private final AbstractHierarchicalFileConfiguration this$0;
/*     */     
/*     */     public void load(Reader in) throws ConfigurationException {
/* 552 */       AbstractHierarchicalFileConfiguration.this.load(in);
/*     */     }
/*     */     
/*     */     public void save(Writer out) throws ConfigurationException {
/* 557 */       AbstractHierarchicalFileConfiguration.this.save(out);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 562 */       AbstractHierarchicalFileConfiguration.this.clear();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\AbstractHierarchicalFileConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */