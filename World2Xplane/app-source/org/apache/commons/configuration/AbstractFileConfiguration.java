/*      */ package org.apache.commons.configuration;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.net.URL;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import org.apache.commons.configuration.reloading.InvariantReloadingStrategy;
/*      */ import org.apache.commons.configuration.reloading.ReloadingStrategy;
/*      */ import org.apache.commons.lang.StringUtils;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public abstract class AbstractFileConfiguration extends BaseConfiguration implements FileConfiguration, FileSystemBased {
/*      */   public static final int EVENT_RELOAD = 20;
/*      */   
/*      */   public static final int EVENT_CONFIG_CHANGED = 21;
/*      */   
/*      */   private static final String FILE_SCHEME = "file:";
/*      */   
/*      */   protected String fileName;
/*      */   
/*      */   protected String basePath;
/*      */   
/*      */   protected boolean autoSave;
/*      */   
/*      */   protected ReloadingStrategy strategy;
/*      */   
/*  106 */   protected Object reloadLock = new Lock("AbstractFileConfiguration");
/*      */   
/*      */   private String encoding;
/*      */   
/*      */   private URL sourceURL;
/*      */   
/*      */   private int noReload;
/*      */   
/*  118 */   private FileSystem fileSystem = FileSystem.getDefaultFileSystem();
/*      */   
/*      */   public AbstractFileConfiguration() {
/*  127 */     initReloadingStrategy();
/*  128 */     setLogger(LogFactory.getLog(getClass()));
/*  129 */     addErrorLogListener();
/*      */   }
/*      */   
/*      */   public AbstractFileConfiguration(String fileName) throws ConfigurationException {
/*  143 */     this();
/*  146 */     setFileName(fileName);
/*  149 */     load();
/*      */   }
/*      */   
/*      */   public AbstractFileConfiguration(File file) throws ConfigurationException {
/*  161 */     this();
/*  164 */     setFile(file);
/*  167 */     if (file.exists())
/*  169 */       load(); 
/*      */   }
/*      */   
/*      */   public AbstractFileConfiguration(URL url) throws ConfigurationException {
/*  182 */     this();
/*  185 */     setURL(url);
/*  188 */     load();
/*      */   }
/*      */   
/*      */   public void setFileSystem(FileSystem fileSystem) {
/*  193 */     if (fileSystem == null)
/*  195 */       throw new NullPointerException("A valid FileSystem must be specified"); 
/*  197 */     this.fileSystem = fileSystem;
/*      */   }
/*      */   
/*      */   public void resetFileSystem() {
/*  202 */     this.fileSystem = FileSystem.getDefaultFileSystem();
/*      */   }
/*      */   
/*      */   public FileSystem getFileSystem() {
/*  207 */     return this.fileSystem;
/*      */   }
/*      */   
/*      */   public Object getReloadLock() {
/*  212 */     return this.reloadLock;
/*      */   }
/*      */   
/*      */   public void load() throws ConfigurationException {
/*  223 */     if (this.sourceURL != null) {
/*  225 */       load(this.sourceURL);
/*      */     } else {
/*  229 */       load(getFileName());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void load(String fileName) throws ConfigurationException {
/*      */     try {
/*  245 */       URL url = ConfigurationUtils.locate(this.fileSystem, this.basePath, fileName);
/*  247 */       if (url == null)
/*  249 */         throw new ConfigurationException("Cannot locate configuration source " + fileName); 
/*  251 */       load(url);
/*  253 */     } catch (ConfigurationException e) {
/*  255 */       throw e;
/*  257 */     } catch (Exception e) {
/*  259 */       throw new ConfigurationException("Unable to load the configuration file " + fileName, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void load(File file) throws ConfigurationException {
/*      */     try {
/*  275 */       load(ConfigurationUtils.toURL(file));
/*  277 */     } catch (ConfigurationException e) {
/*  279 */       throw e;
/*  281 */     } catch (Exception e) {
/*  283 */       throw new ConfigurationException("Unable to load the configuration file " + file, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void load(URL url) throws ConfigurationException {
/*  297 */     if (this.sourceURL == null) {
/*  299 */       if (StringUtils.isEmpty(getBasePath()))
/*  302 */         setBasePath(url.toString()); 
/*  304 */       this.sourceURL = url;
/*      */     } 
/*  307 */     InputStream in = null;
/*      */     try {
/*  311 */       in = this.fileSystem.getInputStream(url);
/*  312 */       load(in);
/*  314 */     } catch (ConfigurationException e) {
/*  316 */       throw e;
/*  318 */     } catch (Exception e) {
/*  320 */       throw new ConfigurationException("Unable to load the configuration from the URL " + url, e);
/*      */     } finally {
/*      */       try {
/*  327 */         if (in != null)
/*  329 */           in.close(); 
/*  332 */       } catch (IOException e) {
/*  334 */         getLogger().warn("Could not close input stream", e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void load(InputStream in) throws ConfigurationException {
/*  349 */     load(in, getEncoding());
/*      */   }
/*      */   
/*      */   public void load(InputStream in, String encoding) throws ConfigurationException {
/*  363 */     Reader reader = null;
/*  365 */     if (encoding != null)
/*      */       try {
/*  369 */         reader = new InputStreamReader(in, encoding);
/*  371 */       } catch (UnsupportedEncodingException e) {
/*  373 */         throw new ConfigurationException("The requested encoding is not supported, try the default encoding.", e);
/*      */       }  
/*  378 */     if (reader == null)
/*  380 */       reader = new InputStreamReader(in); 
/*  383 */     load(reader);
/*      */   }
/*      */   
/*      */   public void save() throws ConfigurationException {
/*  395 */     if (getFileName() == null)
/*  397 */       throw new ConfigurationException("No file name has been set!"); 
/*  400 */     if (this.sourceURL != null) {
/*  402 */       save(this.sourceURL);
/*      */     } else {
/*  406 */       save(this.fileName);
/*      */     } 
/*  408 */     this.strategy.init();
/*      */   }
/*      */   
/*      */   public void save(String fileName) throws ConfigurationException {
/*      */     try {
/*  423 */       URL url = this.fileSystem.getURL(this.basePath, fileName);
/*  425 */       if (url == null)
/*  427 */         throw new ConfigurationException("Cannot locate configuration source " + fileName); 
/*  429 */       save(url);
/*  437 */     } catch (ConfigurationException e) {
/*  439 */       throw e;
/*  441 */     } catch (Exception e) {
/*  443 */       throw new ConfigurationException("Unable to save the configuration to the file " + fileName, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(URL url) throws ConfigurationException {
/*  458 */     OutputStream out = null;
/*      */     try {
/*  461 */       out = this.fileSystem.getOutputStream(url);
/*  462 */       save(out);
/*  463 */       if (out instanceof VerifiableOutputStream)
/*  465 */         ((VerifiableOutputStream)out).verify(); 
/*  468 */     } catch (IOException e) {
/*  470 */       throw new ConfigurationException("Could not save to URL " + url, e);
/*      */     } finally {
/*  474 */       closeSilent(out);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(File file) throws ConfigurationException {
/*  489 */     OutputStream out = null;
/*      */     try {
/*  493 */       out = this.fileSystem.getOutputStream(file);
/*  494 */       save(out);
/*      */     } finally {
/*  498 */       closeSilent(out);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void save(OutputStream out) throws ConfigurationException {
/*  512 */     save(out, getEncoding());
/*      */   }
/*      */   
/*      */   public void save(OutputStream out, String encoding) throws ConfigurationException {
/*  525 */     Writer writer = null;
/*  527 */     if (encoding != null)
/*      */       try {
/*  531 */         writer = new OutputStreamWriter(out, encoding);
/*  533 */       } catch (UnsupportedEncodingException e) {
/*  535 */         throw new ConfigurationException("The requested encoding is not supported, try the default encoding.", e);
/*      */       }  
/*  540 */     if (writer == null)
/*  542 */       writer = new OutputStreamWriter(out); 
/*  545 */     save(writer);
/*      */   }
/*      */   
/*      */   public String getFileName() {
/*  555 */     return this.fileName;
/*      */   }
/*      */   
/*      */   public void setFileName(String fileName) {
/*  569 */     if (fileName != null && fileName.startsWith("file:") && !fileName.startsWith("file://"))
/*  571 */       fileName = "file://" + fileName.substring("file:".length()); 
/*  574 */     this.sourceURL = null;
/*  575 */     this.fileName = fileName;
/*  576 */     getLogger().debug("FileName set to " + fileName);
/*      */   }
/*      */   
/*      */   public String getBasePath() {
/*  587 */     return this.basePath;
/*      */   }
/*      */   
/*      */   public void setBasePath(String basePath) {
/*  608 */     if (basePath != null && basePath.startsWith("file:") && !basePath.startsWith("file://"))
/*  610 */       basePath = "file://" + basePath.substring("file:".length()); 
/*  612 */     this.sourceURL = null;
/*  613 */     this.basePath = basePath;
/*  614 */     getLogger().debug("Base path set to " + basePath);
/*      */   }
/*      */   
/*      */   public File getFile() {
/*  627 */     if (getFileName() == null && this.sourceURL == null)
/*  629 */       return null; 
/*  631 */     if (this.sourceURL != null)
/*  633 */       return ConfigurationUtils.fileFromURL(this.sourceURL); 
/*  637 */     return ConfigurationUtils.getFile(getBasePath(), getFileName());
/*      */   }
/*      */   
/*      */   public void setFile(File file) {
/*  650 */     this.sourceURL = null;
/*  651 */     setFileName(file.getName());
/*  652 */     setBasePath((file.getParentFile() != null) ? file.getParentFile().getAbsolutePath() : null);
/*      */   }
/*      */   
/*      */   public String getPath() {
/*  667 */     return this.fileSystem.getPath(getFile(), this.sourceURL, getBasePath(), getFileName());
/*      */   }
/*      */   
/*      */   public void setPath(String path) {
/*  682 */     setFile(new File(path));
/*      */   }
/*      */   
/*      */   URL getSourceURL() {
/*  687 */     return this.sourceURL;
/*      */   }
/*      */   
/*      */   public URL getURL() {
/*  697 */     return (this.sourceURL != null) ? this.sourceURL : ConfigurationUtils.locate(this.fileSystem, getBasePath(), getFileName());
/*      */   }
/*      */   
/*      */   public void setURL(URL url) {
/*  711 */     setBasePath(ConfigurationUtils.getBasePath(url));
/*  712 */     setFileName(ConfigurationUtils.getFileName(url));
/*  713 */     this.sourceURL = url;
/*  714 */     getLogger().debug("URL set to " + url);
/*      */   }
/*      */   
/*      */   public void setAutoSave(boolean autoSave) {
/*  719 */     this.autoSave = autoSave;
/*      */   }
/*      */   
/*      */   public boolean isAutoSave() {
/*  724 */     return this.autoSave;
/*      */   }
/*      */   
/*      */   protected void possiblySave() {
/*  733 */     if (this.autoSave && this.fileName != null)
/*      */       try {
/*  737 */         save();
/*  739 */       } catch (ConfigurationException e) {
/*  741 */         throw new ConfigurationRuntimeException("Failed to auto-save", e);
/*      */       }  
/*      */   }
/*      */   
/*      */   public void addProperty(String key, Object value) {
/*  755 */     synchronized (this.reloadLock) {
/*  757 */       super.addProperty(key, value);
/*  758 */       possiblySave();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setProperty(String key, Object value) {
/*  772 */     synchronized (this.reloadLock) {
/*  774 */       super.setProperty(key, value);
/*  775 */       possiblySave();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void clearProperty(String key) {
/*  781 */     synchronized (this.reloadLock) {
/*  783 */       super.clearProperty(key);
/*  784 */       possiblySave();
/*      */     } 
/*      */   }
/*      */   
/*      */   public ReloadingStrategy getReloadingStrategy() {
/*  790 */     return this.strategy;
/*      */   }
/*      */   
/*      */   public void setReloadingStrategy(ReloadingStrategy strategy) {
/*  795 */     this.strategy = strategy;
/*  796 */     strategy.setConfiguration(this);
/*  797 */     strategy.init();
/*      */   }
/*      */   
/*      */   public void reload() {
/*  812 */     reload(false);
/*      */   }
/*      */   
/*      */   public boolean reload(boolean checkReload) {
/*  817 */     synchronized (this.reloadLock) {
/*  819 */       if (this.noReload == 0)
/*      */         try {
/*  823 */           enterNoReload();
/*  825 */           if (this.strategy.reloadingRequired()) {
/*  827 */             if (getLogger().isInfoEnabled())
/*  829 */               getLogger().info("Reloading configuration. URL is " + getURL()); 
/*  831 */             refresh();
/*  834 */             this.strategy.reloadingPerformed();
/*      */           } 
/*  837 */         } catch (Exception e) {
/*  839 */           fireError(20, null, null, e);
/*  841 */           if (checkReload)
/*  843 */             return false; 
/*      */         } finally {
/*  848 */           exitNoReload();
/*      */         }  
/*      */     } 
/*  852 */     return true;
/*      */   }
/*      */   
/*      */   public void refresh() throws ConfigurationException {
/*  867 */     fireEvent(20, (String)null, getURL(), true);
/*  868 */     setDetailEvents(false);
/*  869 */     boolean autoSaveBak = isAutoSave();
/*  870 */     setAutoSave(false);
/*      */     try {
/*  873 */       clear();
/*  874 */       load();
/*      */     } finally {
/*  878 */       setAutoSave(autoSaveBak);
/*  879 */       setDetailEvents(true);
/*      */     } 
/*  881 */     fireEvent(20, (String)null, getURL(), false);
/*      */   }
/*      */   
/*      */   public void configurationChanged() {
/*  889 */     fireEvent(21, (String)null, getURL(), true);
/*      */   }
/*      */   
/*      */   protected void enterNoReload() {
/*  904 */     synchronized (this.reloadLock) {
/*  906 */       this.noReload++;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void exitNoReload() {
/*  917 */     synchronized (this.reloadLock) {
/*  919 */       if (this.noReload > 0)
/*  921 */         this.noReload--; 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void fireEvent(int type, String propName, Object propValue, boolean before) {
/*  939 */     enterNoReload();
/*      */     try {
/*  942 */       super.fireEvent(type, propName, propValue, before);
/*      */     } finally {
/*  946 */       exitNoReload();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object getProperty(String key) {
/*  952 */     synchronized (this.reloadLock) {
/*  954 */       reload();
/*  955 */       return super.getProperty(key);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  961 */     reload();
/*  962 */     synchronized (this.reloadLock) {
/*  964 */       return super.isEmpty();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean containsKey(String key) {
/*  970 */     reload();
/*  971 */     synchronized (this.reloadLock) {
/*  973 */       return super.containsKey(key);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Iterator getKeys() {
/*  994 */     reload();
/*  995 */     List keyList = new LinkedList();
/*  996 */     enterNoReload();
/*      */     try {
/*      */       Iterator it;
/*  999 */       for (it = super.getKeys(); it.hasNext();)
/* 1001 */         keyList.add(it.next()); 
/* 1004 */       it = keyList.iterator();
/* 1008 */       return it;
/*      */     } finally {
/* 1008 */       exitNoReload();
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getEncoding() {
/* 1014 */     return this.encoding;
/*      */   }
/*      */   
/*      */   public void setEncoding(String encoding) {
/* 1019 */     this.encoding = encoding;
/*      */   }
/*      */   
/*      */   public Object clone() {
/* 1035 */     AbstractFileConfiguration copy = (AbstractFileConfiguration)super.clone();
/* 1036 */     copy.setBasePath((String)null);
/* 1037 */     copy.setFileName((String)null);
/* 1038 */     copy.initReloadingStrategy();
/* 1039 */     return copy;
/*      */   }
/*      */   
/*      */   private void initReloadingStrategy() {
/* 1047 */     setReloadingStrategy((ReloadingStrategy)new InvariantReloadingStrategy());
/*      */   }
/*      */   
/*      */   protected void closeSilent(OutputStream out) {
/*      */     try {
/* 1061 */       if (out != null)
/* 1063 */         out.close(); 
/* 1066 */     } catch (IOException e) {
/* 1068 */       getLogger().warn("Could not close output stream", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\AbstractFileConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */