/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.vfs2.FileContent;
/*     */ import org.apache.commons.vfs2.FileName;
/*     */ import org.apache.commons.vfs2.FileObject;
/*     */ import org.apache.commons.vfs2.FileSystemConfigBuilder;
/*     */ import org.apache.commons.vfs2.FileSystemException;
/*     */ import org.apache.commons.vfs2.FileSystemManager;
/*     */ import org.apache.commons.vfs2.FileSystemOptions;
/*     */ import org.apache.commons.vfs2.FileType;
/*     */ import org.apache.commons.vfs2.VFS;
/*     */ import org.apache.commons.vfs2.provider.UriParser;
/*     */ 
/*     */ public class VFSFileSystem extends DefaultFileSystem {
/*     */   public InputStream getInputStream(String basePath, String fileName) throws ConfigurationException {
/*     */     try {
/*     */       FileName path;
/*  59 */       FileSystemManager manager = VFS.getManager();
/*  61 */       if (basePath != null) {
/*  63 */         FileName base = manager.resolveURI(basePath);
/*  64 */         path = manager.resolveName(base, fileName);
/*     */       } else {
/*  68 */         FileName fileName1 = manager.resolveURI(fileName);
/*  69 */         FileName base = fileName1.getParent();
/*  70 */         path = manager.resolveName(base, fileName1.getBaseName());
/*     */       } 
/*  72 */       FileSystemOptions opts = getOptions(path.getScheme());
/*  73 */       FileObject file = (opts == null) ? manager.resolveFile(path.getURI()) : manager.resolveFile(path.getURI(), opts);
/*  75 */       FileContent content = file.getContent();
/*  76 */       if (content == null) {
/*  78 */         String msg = "Cannot access content of " + file.getName().getFriendlyURI();
/*  79 */         throw new ConfigurationException(msg);
/*     */       } 
/*  81 */       return content.getInputStream();
/*  83 */     } catch (ConfigurationException e) {
/*  85 */       throw e;
/*  87 */     } catch (Exception e) {
/*  89 */       throw new ConfigurationException("Unable to load the configuration file " + fileName, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(URL url) throws ConfigurationException {
/*     */     try {
/*  98 */       FileSystemOptions opts = getOptions(url.getProtocol());
/*  99 */       FileObject file = (opts == null) ? VFS.getManager().resolveFile(url.toString()) : VFS.getManager().resolveFile(url.toString(), opts);
/* 101 */       if (file.getType() != FileType.FILE)
/* 103 */         throw new ConfigurationException("Cannot load a configuration from a directory"); 
/* 105 */       FileContent content = file.getContent();
/* 106 */       if (content == null) {
/* 108 */         String msg = "Cannot access content of " + file.getName().getFriendlyURI();
/* 109 */         throw new ConfigurationException(msg);
/*     */       } 
/* 111 */       return content.getInputStream();
/* 113 */     } catch (FileSystemException fse) {
/* 115 */       String msg = "Unable to access " + url.toString();
/* 116 */       throw new ConfigurationException(msg, fse);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream(URL url) throws ConfigurationException {
/*     */     try {
/* 124 */       FileSystemOptions opts = getOptions(url.getProtocol());
/* 125 */       FileSystemManager fsManager = VFS.getManager();
/* 126 */       FileObject file = (opts == null) ? fsManager.resolveFile(url.toString()) : fsManager.resolveFile(url.toString(), opts);
/* 129 */       if (file == null || file.getType() == FileType.FOLDER)
/* 131 */         throw new ConfigurationException("Cannot save a configuration to a directory"); 
/* 133 */       FileContent content = file.getContent();
/* 135 */       if (content == null)
/* 137 */         throw new ConfigurationException("Cannot access content of " + url); 
/* 139 */       return content.getOutputStream();
/* 141 */     } catch (FileSystemException fse) {
/* 143 */       throw new ConfigurationException("Unable to access " + url, fse);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPath(File file, URL url, String basePath, String fileName) {
/* 149 */     if (file != null)
/* 151 */       return super.getPath(file, url, basePath, fileName); 
/*     */     try {
/* 155 */       FileSystemManager fsManager = VFS.getManager();
/* 156 */       if (url != null) {
/* 158 */         FileName fileName1 = fsManager.resolveURI(url.toString());
/* 159 */         if (fileName1 != null)
/* 161 */           return fileName1.toString(); 
/*     */       } 
/* 165 */       if (UriParser.extractScheme(fileName) != null)
/* 167 */         return fileName; 
/* 169 */       if (basePath != null) {
/* 171 */         FileName fileName1 = fsManager.resolveURI(basePath);
/* 172 */         return fsManager.resolveName(fileName1, fileName).getURI();
/*     */       } 
/* 176 */       FileName name = fsManager.resolveURI(fileName);
/* 177 */       FileName base = name.getParent();
/* 178 */       return fsManager.resolveName(base, name.getBaseName()).getURI();
/* 181 */     } catch (FileSystemException fse) {
/* 183 */       fse.printStackTrace();
/* 184 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getBasePath(String path) {
/* 190 */     if (UriParser.extractScheme(path) == null)
/* 192 */       return super.getBasePath(path); 
/*     */     try {
/* 196 */       FileSystemManager fsManager = VFS.getManager();
/* 197 */       FileName name = fsManager.resolveURI(path);
/* 198 */       return name.getParent().getURI();
/* 200 */     } catch (FileSystemException fse) {
/* 202 */       fse.printStackTrace();
/* 203 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getFileName(String path) {
/* 209 */     if (UriParser.extractScheme(path) == null)
/* 211 */       return super.getFileName(path); 
/*     */     try {
/* 215 */       FileSystemManager fsManager = VFS.getManager();
/* 216 */       FileName name = fsManager.resolveURI(path);
/* 217 */       return name.getBaseName();
/* 219 */     } catch (FileSystemException fse) {
/* 221 */       fse.printStackTrace();
/* 222 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL getURL(String basePath, String file) throws MalformedURLException {
/* 228 */     if ((basePath != null && UriParser.extractScheme(basePath) == null) || (basePath == null && UriParser.extractScheme(file) == null))
/* 231 */       return super.getURL(basePath, file); 
/*     */     try {
/*     */       FileName path;
/* 235 */       FileSystemManager fsManager = VFS.getManager();
/* 238 */       if (basePath != null && UriParser.extractScheme(file) == null) {
/* 240 */         FileName base = fsManager.resolveURI(basePath);
/* 241 */         path = fsManager.resolveName(base, file);
/*     */       } else {
/* 245 */         path = fsManager.resolveURI(file);
/*     */       } 
/* 248 */       URLStreamHandler handler = new VFSURLStreamHandler(path);
/* 249 */       return new URL(null, path.getURI(), handler);
/* 251 */     } catch (FileSystemException fse) {
/* 253 */       throw new ConfigurationRuntimeException("Could not parse basePath: " + basePath + " and fileName: " + file, fse);
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL locateFromURL(String basePath, String fileName) {
/* 260 */     String fileScheme = UriParser.extractScheme(fileName);
/* 263 */     if ((basePath == null || UriParser.extractScheme(basePath) == null) && fileScheme == null)
/* 265 */       return super.locateFromURL(basePath, fileName); 
/*     */     try {
/*     */       FileObject file;
/* 269 */       FileSystemManager fsManager = VFS.getManager();
/* 273 */       if (basePath != null && fileScheme == null) {
/* 275 */         String scheme = UriParser.extractScheme(basePath);
/* 276 */         FileSystemOptions opts = (scheme != null) ? getOptions(scheme) : null;
/* 277 */         FileObject base = (opts == null) ? fsManager.resolveFile(basePath) : fsManager.resolveFile(basePath, opts);
/* 279 */         if (base.getType() == FileType.FILE)
/* 281 */           base = base.getParent(); 
/* 284 */         file = fsManager.resolveFile(base, fileName);
/*     */       } else {
/* 288 */         FileSystemOptions opts = (fileScheme != null) ? getOptions(fileScheme) : null;
/* 289 */         file = (opts == null) ? fsManager.resolveFile(fileName) : fsManager.resolveFile(fileName, opts);
/*     */       } 
/* 293 */       if (!file.exists())
/* 295 */         return null; 
/* 297 */       FileName path = file.getName();
/* 298 */       URLStreamHandler handler = new VFSURLStreamHandler(path);
/* 299 */       return new URL(null, path.getURI(), handler);
/* 301 */     } catch (FileSystemException fse) {
/* 303 */       return null;
/* 305 */     } catch (MalformedURLException ex) {
/* 307 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private FileSystemOptions getOptions(String scheme) {
/*     */     FileSystemConfigBuilder builder;
/* 313 */     FileSystemOptions opts = new FileSystemOptions();
/*     */     try {
/* 317 */       builder = VFS.getManager().getFileSystemConfigBuilder(scheme);
/* 319 */     } catch (Exception ex) {
/* 321 */       return null;
/*     */     } 
/* 323 */     FileOptionsProvider provider = getFileOptionsProvider();
/* 324 */     if (provider != null) {
/* 326 */       Map map = provider.getOptions();
/* 327 */       if (map == null)
/* 329 */         return null; 
/* 331 */       Iterator iter = map.entrySet().iterator();
/* 332 */       int count = 0;
/* 333 */       while (iter.hasNext()) {
/* 335 */         Map.Entry entry = iter.next();
/*     */         try {
/* 338 */           String key = (String)entry.getKey();
/* 339 */           if ("currentUser".equals(key))
/* 341 */             key = "creatorName"; 
/* 343 */           setProperty(builder, opts, key, entry.getValue());
/* 344 */           count++;
/* 346 */         } catch (Exception ex) {}
/*     */       } 
/* 352 */       if (count > 0)
/* 354 */         return opts; 
/*     */     } 
/* 357 */     return null;
/*     */   }
/*     */   
/*     */   private void setProperty(FileSystemConfigBuilder builder, FileSystemOptions options, String key, Object value) {
/* 364 */     String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
/* 365 */     Class[] paramTypes = new Class[2];
/* 366 */     paramTypes[0] = FileSystemOptions.class;
/* 367 */     paramTypes[1] = value.getClass();
/*     */     try {
/* 371 */       Method method = builder.getClass().getMethod(methodName, paramTypes);
/* 372 */       Object[] params = new Object[2];
/* 373 */       params[0] = options;
/* 374 */       params[1] = value;
/* 375 */       method.invoke(builder, params);
/* 377 */     } catch (Exception ex) {
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class VFSURLStreamHandler extends URLStreamHandler {
/*     */     private final String protocol;
/*     */     
/*     */     public VFSURLStreamHandler(FileName file) {
/* 394 */       this.protocol = file.getScheme();
/*     */     }
/*     */     
/*     */     protected URLConnection openConnection(URL url) throws IOException {
/* 399 */       throw new IOException("VFS URLs can only be used with VFS APIs");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\VFSFileSystem.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */