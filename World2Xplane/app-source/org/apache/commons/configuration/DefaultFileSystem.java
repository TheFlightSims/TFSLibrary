/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class DefaultFileSystem extends FileSystem {
/*  44 */   private Log log = LogFactory.getLog(DefaultFileSystem.class);
/*     */   
/*     */   public InputStream getInputStream(String basePath, String fileName) throws ConfigurationException {
/*     */     try {
/*  51 */       URL url = ConfigurationUtils.locate(this, basePath, fileName);
/*  53 */       if (url == null)
/*  55 */         throw new ConfigurationException("Cannot locate configuration source " + fileName); 
/*  57 */       return getInputStream(url);
/*  59 */     } catch (ConfigurationException e) {
/*  61 */       throw e;
/*  63 */     } catch (Exception e) {
/*  65 */       throw new ConfigurationException("Unable to load the configuration file " + fileName, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(URL url) throws ConfigurationException {
/*  72 */     File file = ConfigurationUtils.fileFromURL(url);
/*  73 */     if (file != null && file.isDirectory())
/*  75 */       throw new ConfigurationException("Cannot load a configuration from a directory"); 
/*     */     try {
/*  80 */       return url.openStream();
/*  82 */     } catch (Exception e) {
/*  84 */       throw new ConfigurationException("Unable to load the configuration from the URL " + url, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream(URL url) throws ConfigurationException {
/*  92 */     File file = ConfigurationUtils.fileFromURL(url);
/*  93 */     if (file != null)
/*  95 */       return getOutputStream(file); 
/*     */     try {
/* 103 */       URLConnection connection = url.openConnection();
/* 104 */       connection.setDoOutput(true);
/* 107 */       if (connection instanceof HttpURLConnection) {
/* 109 */         HttpURLConnection conn = (HttpURLConnection)connection;
/* 110 */         conn.setRequestMethod("PUT");
/*     */       } 
/* 113 */       OutputStream out = connection.getOutputStream();
/* 116 */       if (connection instanceof HttpURLConnection)
/* 118 */         out = new HttpOutputStream(out, (HttpURLConnection)connection); 
/* 120 */       return out;
/* 122 */     } catch (IOException e) {
/* 124 */       throw new ConfigurationException("Could not save to URL " + url, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream(File file) throws ConfigurationException {
/*     */     try {
/* 134 */       createPath(file);
/* 135 */       return new FileOutputStream(file);
/* 137 */     } catch (FileNotFoundException e) {
/* 139 */       throw new ConfigurationException("Unable to save to file " + file, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPath(File file, URL url, String basePath, String fileName) {
/* 145 */     String path = null;
/* 147 */     if (file != null)
/* 149 */       path = file.getAbsolutePath(); 
/* 153 */     if (path == null)
/* 155 */       if (url != null) {
/* 157 */         path = url.getPath();
/*     */       } else {
/*     */         try {
/* 163 */           path = getURL(basePath, fileName).getPath();
/* 165 */         } catch (Exception e) {}
/*     */       }  
/* 173 */     return path;
/*     */   }
/*     */   
/*     */   public String getBasePath(String path) {
/*     */     try {
/* 181 */       URL url = getURL(null, path);
/* 182 */       return ConfigurationUtils.getBasePath(url);
/* 184 */     } catch (Exception e) {
/* 186 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getFileName(String path) {
/*     */     try {
/* 195 */       URL url = getURL(null, path);
/* 196 */       return ConfigurationUtils.getFileName(url);
/* 198 */     } catch (Exception e) {
/* 200 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL getURL(String basePath, String file) throws MalformedURLException {
/* 207 */     File f = new File(file);
/* 208 */     if (f.isAbsolute())
/* 210 */       return ConfigurationUtils.toURL(f); 
/*     */     try {
/* 215 */       if (basePath == null)
/* 217 */         return new URL(file); 
/* 221 */       URL base = new URL(basePath);
/* 222 */       return new URL(base, file);
/* 225 */     } catch (MalformedURLException uex) {
/* 227 */       return ConfigurationUtils.toURL(ConfigurationUtils.constructFile(basePath, file));
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL locateFromURL(String basePath, String fileName) {
/*     */     try {
/* 237 */       if (basePath == null)
/* 239 */         return new URL(fileName); 
/* 244 */       URL baseURL = new URL(basePath);
/* 245 */       URL url = new URL(baseURL, fileName);
/* 248 */       InputStream in = null;
/*     */       try {
/* 251 */         in = url.openStream();
/*     */       } finally {
/* 255 */         if (in != null)
/* 257 */           in.close(); 
/*     */       } 
/* 260 */       return url;
/* 263 */     } catch (IOException e) {
/* 265 */       if (this.log.isDebugEnabled())
/* 267 */         this.log.debug("Could not locate file " + fileName + " at " + basePath + ": " + e.getMessage()); 
/* 269 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createPath(File file) {
/* 280 */     if (file != null)
/* 283 */       if (!file.exists()) {
/* 285 */         File parent = file.getParentFile();
/* 286 */         if (parent != null && !parent.exists())
/* 288 */           parent.mkdirs(); 
/*     */       }  
/*     */   }
/*     */   
/*     */   private static class HttpOutputStream extends VerifiableOutputStream {
/*     */     private final OutputStream stream;
/*     */     
/*     */     private final HttpURLConnection connection;
/*     */     
/*     */     public HttpOutputStream(OutputStream stream, HttpURLConnection connection) {
/* 309 */       this.stream = stream;
/* 310 */       this.connection = connection;
/*     */     }
/*     */     
/*     */     public void write(byte[] bytes) throws IOException {
/* 315 */       this.stream.write(bytes);
/*     */     }
/*     */     
/*     */     public void write(byte[] bytes, int i, int i1) throws IOException {
/* 320 */       this.stream.write(bytes, i, i1);
/*     */     }
/*     */     
/*     */     public void flush() throws IOException {
/* 325 */       this.stream.flush();
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 330 */       this.stream.close();
/*     */     }
/*     */     
/*     */     public void write(int i) throws IOException {
/* 335 */       this.stream.write(i);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 340 */       return this.stream.toString();
/*     */     }
/*     */     
/*     */     public void verify() throws IOException {
/* 345 */       if (this.connection.getResponseCode() >= 400)
/* 347 */         throw new IOException("HTTP Error " + this.connection.getResponseCode() + " " + this.connection.getResponseMessage()); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\DefaultFileSystem.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */