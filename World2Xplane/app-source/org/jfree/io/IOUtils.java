/*     */ package org.jfree.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class IOUtils {
/*     */   private static IOUtils instance;
/*     */   
/*     */   public static IOUtils getInstance() {
/*  83 */     if (instance == null)
/*  84 */       instance = new IOUtils(); 
/*  86 */     return instance;
/*     */   }
/*     */   
/*     */   private boolean isFileStyleProtocol(URL url) {
/*  96 */     if (url.getProtocol().equals("http"))
/*  97 */       return true; 
/*  99 */     if (url.getProtocol().equals("https"))
/* 100 */       return true; 
/* 102 */     if (url.getProtocol().equals("ftp"))
/* 103 */       return true; 
/* 105 */     if (url.getProtocol().equals("file"))
/* 106 */       return true; 
/* 108 */     if (url.getProtocol().equals("jar"))
/* 109 */       return true; 
/* 111 */     return false;
/*     */   }
/*     */   
/*     */   private List parseName(String name) {
/* 121 */     ArrayList list = new ArrayList();
/* 122 */     StringTokenizer strTok = new StringTokenizer(name, "/");
/* 123 */     while (strTok.hasMoreElements()) {
/* 124 */       String s = (String)strTok.nextElement();
/* 125 */       if (s.length() != 0)
/* 126 */         list.add(s); 
/*     */     } 
/* 129 */     return list;
/*     */   }
/*     */   
/*     */   private String formatName(List name, String query) {
/* 140 */     StringBuffer b = new StringBuffer();
/* 141 */     Iterator it = name.iterator();
/* 142 */     while (it.hasNext()) {
/* 143 */       b.append(it.next());
/* 144 */       if (it.hasNext())
/* 145 */         b.append("/"); 
/*     */     } 
/* 148 */     if (query != null) {
/* 149 */       b.append('?');
/* 150 */       b.append(query);
/*     */     } 
/* 152 */     return b.toString();
/*     */   }
/*     */   
/*     */   private int startsWithUntil(List baseName, List urlName) {
/* 164 */     int minIdx = Math.min(urlName.size(), baseName.size());
/* 165 */     for (int i = 0; i < minIdx; i++) {
/* 166 */       String baseToken = baseName.get(i);
/* 167 */       String urlToken = urlName.get(i);
/* 168 */       if (!baseToken.equals(urlToken))
/* 169 */         return i; 
/*     */     } 
/* 172 */     return minIdx;
/*     */   }
/*     */   
/*     */   private boolean isSameService(URL url, URL baseUrl) {
/* 185 */     if (!url.getProtocol().equals(baseUrl.getProtocol()))
/* 186 */       return false; 
/* 188 */     if (!url.getHost().equals(baseUrl.getHost()))
/* 189 */       return false; 
/* 191 */     if (url.getPort() != baseUrl.getPort())
/* 192 */       return false; 
/* 194 */     return true;
/*     */   }
/*     */   
/*     */   public String createRelativeURL(URL url, URL baseURL) {
/* 207 */     if (url == null)
/* 208 */       throw new NullPointerException("content url must not be null."); 
/* 210 */     if (baseURL == null)
/* 211 */       throw new NullPointerException("baseURL must not be null."); 
/* 213 */     if (isFileStyleProtocol(url) && isSameService(url, baseURL)) {
/* 217 */       List urlName = parseName(getPath(url));
/* 218 */       List baseName = parseName(getPath(baseURL));
/* 219 */       String query = getQuery(url);
/* 221 */       if (!isPath(baseURL))
/* 222 */         baseName.remove(baseName.size() - 1); 
/* 226 */       if (url.equals(baseURL))
/* 227 */         return urlName.get(urlName.size() - 1); 
/* 230 */       int commonIndex = startsWithUntil(urlName, baseName);
/* 231 */       if (commonIndex == 0)
/* 232 */         return url.toExternalForm(); 
/* 235 */       if (commonIndex == urlName.size())
/* 245 */         commonIndex--; 
/* 248 */       ArrayList retval = new ArrayList();
/* 249 */       if (baseName.size() >= urlName.size()) {
/* 250 */         int levels = baseName.size() - commonIndex;
/* 251 */         for (int i = 0; i < levels; i++)
/* 252 */           retval.add(".."); 
/*     */       } 
/* 256 */       retval.addAll(urlName.subList(commonIndex, urlName.size()));
/* 257 */       return formatName(retval, query);
/*     */     } 
/* 259 */     return url.toExternalForm();
/*     */   }
/*     */   
/*     */   private boolean isPath(URL baseURL) {
/* 271 */     if (getPath(baseURL).endsWith("/"))
/* 272 */       return true; 
/* 274 */     if (baseURL.getProtocol().equals("file")) {
/* 275 */       File f = new File(getPath(baseURL));
/*     */       try {
/* 277 */         if (f.isDirectory())
/* 278 */           return true; 
/* 281 */       } catch (SecurityException se) {}
/*     */     } 
/* 285 */     return false;
/*     */   }
/*     */   
/*     */   private String getQuery(URL url) {
/* 296 */     String file = url.getFile();
/* 297 */     int queryIndex = file.indexOf('?');
/* 298 */     if (queryIndex == -1)
/* 299 */       return null; 
/* 301 */     return file.substring(queryIndex + 1);
/*     */   }
/*     */   
/*     */   private String getPath(URL url) {
/* 312 */     String file = url.getFile();
/* 313 */     int queryIndex = file.indexOf('?');
/* 314 */     if (queryIndex == -1)
/* 315 */       return file; 
/* 317 */     return file.substring(0, queryIndex);
/*     */   }
/*     */   
/*     */   public void copyStreams(InputStream in, OutputStream out) throws IOException {
/* 330 */     copyStreams(in, out, 4096);
/*     */   }
/*     */   
/*     */   public void copyStreams(InputStream in, OutputStream out, int buffersize) throws IOException {
/* 345 */     byte[] bytes = new byte[buffersize];
/* 349 */     int bytesRead = in.read(bytes);
/* 350 */     while (bytesRead > -1) {
/* 351 */       out.write(bytes, 0, bytesRead);
/* 352 */       bytesRead = in.read(bytes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void copyWriter(Reader in, Writer out) throws IOException {
/* 366 */     copyWriter(in, out, 4096);
/*     */   }
/*     */   
/*     */   public void copyWriter(Reader in, Writer out, int buffersize) throws IOException {
/* 383 */     char[] bytes = new char[buffersize];
/* 387 */     int bytesRead = in.read(bytes);
/* 388 */     while (bytesRead > -1) {
/* 389 */       out.write(bytes, 0, bytesRead);
/* 390 */       bytesRead = in.read(bytes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getFileName(URL url) {
/* 401 */     String file = url.getFile();
/* 402 */     int last = file.lastIndexOf("/");
/* 403 */     if (last < 0)
/* 404 */       return file; 
/* 406 */     return file.substring(last);
/*     */   }
/*     */   
/*     */   public String stripFileExtension(String file) {
/* 416 */     int idx = file.lastIndexOf(".");
/* 418 */     if (idx < 1)
/* 419 */       return file; 
/* 421 */     return file.substring(0, idx);
/*     */   }
/*     */   
/*     */   public String getFileExtension(String file) {
/* 432 */     int idx = file.lastIndexOf(".");
/* 434 */     if (idx < 1)
/* 435 */       return ""; 
/* 437 */     return file.substring(idx);
/*     */   }
/*     */   
/*     */   public boolean isSubDirectory(File base, File child) throws IOException {
/* 451 */     base = base.getCanonicalFile();
/* 452 */     child = child.getCanonicalFile();
/* 454 */     File parentFile = child;
/* 455 */     while (parentFile != null) {
/* 456 */       if (base.equals(parentFile))
/* 457 */         return true; 
/* 459 */       parentFile = parentFile.getParentFile();
/*     */     } 
/* 461 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\io\IOUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */