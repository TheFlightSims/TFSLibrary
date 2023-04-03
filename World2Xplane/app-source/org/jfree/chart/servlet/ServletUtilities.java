/*     */ package org.jfree.chart.servlet;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.ChartUtilities;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ 
/*     */ public class ServletUtilities {
/*  82 */   private static String tempFilePrefix = "jfreechart-";
/*     */   
/*  85 */   private static String tempOneTimeFilePrefix = "jfreechart-onetime-";
/*     */   
/*     */   public static String getTempFilePrefix() {
/*  93 */     return tempFilePrefix;
/*     */   }
/*     */   
/*     */   public static void setTempFilePrefix(String prefix) {
/* 102 */     if (prefix == null)
/* 103 */       throw new IllegalArgumentException("Null 'prefix' argument."); 
/* 105 */     tempFilePrefix = prefix;
/*     */   }
/*     */   
/*     */   public static String getTempOneTimeFilePrefix() {
/* 115 */     return tempOneTimeFilePrefix;
/*     */   }
/*     */   
/*     */   public static void setTempOneTimeFilePrefix(String prefix) {
/* 125 */     if (prefix == null)
/* 126 */       throw new IllegalArgumentException("Null 'prefix' argument."); 
/* 128 */     tempOneTimeFilePrefix = prefix;
/*     */   }
/*     */   
/*     */   public static String saveChartAsPNG(JFreeChart chart, int width, int height, HttpSession session) throws IOException {
/* 150 */     return saveChartAsPNG(chart, width, height, null, session);
/*     */   }
/*     */   
/*     */   public static String saveChartAsPNG(JFreeChart chart, int width, int height, ChartRenderingInfo info, HttpSession session) throws IOException {
/* 180 */     if (chart == null)
/* 181 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 183 */     createTempDir();
/* 184 */     String prefix = tempFilePrefix;
/* 185 */     if (session == null)
/* 186 */       prefix = tempOneTimeFilePrefix; 
/* 188 */     File tempFile = File.createTempFile(prefix, ".png", new File(System.getProperty("java.io.tmpdir")));
/* 191 */     ChartUtilities.saveChartAsPNG(tempFile, chart, width, height, info);
/* 192 */     if (session != null)
/* 193 */       registerChartForDeletion(tempFile, session); 
/* 195 */     return tempFile.getName();
/*     */   }
/*     */   
/*     */   public static String saveChartAsJPEG(JFreeChart chart, int width, int height, HttpSession session) throws IOException {
/* 218 */     return saveChartAsJPEG(chart, width, height, null, session);
/*     */   }
/*     */   
/*     */   public static String saveChartAsJPEG(JFreeChart chart, int width, int height, ChartRenderingInfo info, HttpSession session) throws IOException {
/* 247 */     if (chart == null)
/* 248 */       throw new IllegalArgumentException("Null 'chart' argument."); 
/* 251 */     createTempDir();
/* 252 */     String prefix = tempFilePrefix;
/* 253 */     if (session == null)
/* 254 */       prefix = tempOneTimeFilePrefix; 
/* 256 */     File tempFile = File.createTempFile(prefix, ".jpeg", new File(System.getProperty("java.io.tmpdir")));
/* 259 */     ChartUtilities.saveChartAsJPEG(tempFile, chart, width, height, info);
/* 260 */     if (session != null)
/* 261 */       registerChartForDeletion(tempFile, session); 
/* 263 */     return tempFile.getName();
/*     */   }
/*     */   
/*     */   protected static void createTempDir() {
/* 277 */     String tempDirName = System.getProperty("java.io.tmpdir");
/* 278 */     if (tempDirName == null)
/* 279 */       throw new RuntimeException("Temporary directory system property (java.io.tmpdir) is null."); 
/* 285 */     File tempDir = new File(tempDirName);
/* 286 */     if (!tempDir.exists())
/* 287 */       tempDir.mkdirs(); 
/*     */   }
/*     */   
/*     */   protected static void registerChartForDeletion(File tempFile, HttpSession session) {
/* 303 */     if (session != null) {
/* 304 */       ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute("JFreeChart_Deleter");
/* 306 */       if (chartDeleter == null) {
/* 307 */         chartDeleter = new ChartDeleter();
/* 308 */         session.setAttribute("JFreeChart_Deleter", chartDeleter);
/*     */       } 
/* 310 */       chartDeleter.addChart(tempFile.getName());
/*     */     } else {
/* 313 */       System.out.println("Session is null - chart will not be deleted");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendTempFile(String filename, HttpServletResponse response) throws IOException {
/* 330 */     File file = new File(System.getProperty("java.io.tmpdir"), filename);
/* 331 */     sendTempFile(file, response);
/*     */   }
/*     */   
/*     */   public static void sendTempFile(File file, HttpServletResponse response) throws IOException {
/* 345 */     String mimeType = null;
/* 346 */     String filename = file.getName();
/* 347 */     if (filename.length() > 5)
/* 348 */       if (filename.substring(filename.length() - 5, filename.length()).equals(".jpeg")) {
/* 350 */         mimeType = "image/jpeg";
/* 352 */       } else if (filename.substring(filename.length() - 4, filename.length()).equals(".png")) {
/* 354 */         mimeType = "image/png";
/*     */       }  
/* 357 */     sendTempFile(file, response, mimeType);
/*     */   }
/*     */   
/*     */   public static void sendTempFile(File file, HttpServletResponse response, String mimeType) throws IOException {
/* 372 */     if (file.exists()) {
/* 373 */       BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
/* 378 */       if (mimeType != null)
/* 379 */         response.setHeader("Content-Type", mimeType); 
/* 381 */       response.setHeader("Content-Length", String.valueOf(file.length()));
/* 382 */       SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
/* 385 */       sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
/* 386 */       response.setHeader("Last-Modified", sdf.format(new Date(file.lastModified())));
/* 390 */       BufferedOutputStream bos = new BufferedOutputStream((OutputStream)response.getOutputStream());
/* 393 */       byte[] input = new byte[1024];
/* 394 */       boolean eof = false;
/* 395 */       while (!eof) {
/* 396 */         int length = bis.read(input);
/* 397 */         if (length == -1) {
/* 398 */           eof = true;
/*     */           continue;
/*     */         } 
/* 401 */         bos.write(input, 0, length);
/*     */       } 
/* 404 */       bos.flush();
/* 405 */       bis.close();
/* 406 */       bos.close();
/*     */     } else {
/* 409 */       throw new FileNotFoundException(file.getAbsolutePath());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String searchReplace(String inputString, String searchString, String replaceString) {
/* 428 */     int i = inputString.indexOf(searchString);
/* 429 */     if (i == -1)
/* 430 */       return inputString; 
/* 433 */     String r = "";
/* 434 */     r = r + inputString.substring(0, i) + replaceString;
/* 435 */     if (i + searchString.length() < inputString.length())
/* 436 */       r = r + searchReplace(inputString.substring(i + searchString.length()), searchString, replaceString); 
/* 442 */     return r;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\servlet\ServletUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */