/*     */ package org.geotools.referencing.factory.gridshift;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.Channels;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.referencing.factory.ReferencingFactory;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.SoftValueHashMap;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ 
/*     */ public class NADCONGridShiftFactory extends ReferencingFactory implements BufferedFactory {
/*     */   private static final int GRID_CACHE_HARD_REFERENCES = 10;
/*     */   
/*     */   static final class NADCONKey {
/*     */     String latFile;
/*     */     
/*     */     String longFile;
/*     */     
/*     */     public NADCONKey(String latFile, String longFile) {
/*  57 */       this.latFile = latFile;
/*  58 */       this.longFile = longFile;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  63 */       int prime = 31;
/*  64 */       int result = 1;
/*  65 */       result = 31 * result + ((this.latFile == null) ? 0 : this.latFile.hashCode());
/*  66 */       result = 31 * result + ((this.longFile == null) ? 0 : this.longFile.hashCode());
/*  67 */       return result;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/*  72 */       if (this == obj)
/*  73 */         return true; 
/*  74 */       if (obj == null)
/*  75 */         return false; 
/*  76 */       if (getClass() != obj.getClass())
/*  77 */         return false; 
/*  78 */       NADCONKey other = (NADCONKey)obj;
/*  79 */       if (this.latFile == null) {
/*  80 */         if (other.latFile != null)
/*  81 */           return false; 
/*  82 */       } else if (!this.latFile.equals(other.latFile)) {
/*  83 */         return false;
/*     */       } 
/*  84 */       if (this.longFile == null) {
/*  85 */         if (other.longFile != null)
/*  86 */           return false; 
/*  87 */       } else if (!this.longFile.equals(other.longFile)) {
/*  88 */         return false;
/*     */       } 
/*  89 */       return true;
/*     */     }
/*     */   }
/*     */   
/* 102 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.referencing");
/*     */   
/*     */   private SoftValueHashMap<NADCONKey, NADConGridShift> gridCache;
/*     */   
/*     */   public NADCONGridShiftFactory() {
/* 113 */     this.gridCache = new SoftValueHashMap(10);
/*     */   }
/*     */   
/*     */   public NADConGridShift loadGridShift(URL latGridURL, URL longGridURL) throws FactoryException {
/* 117 */     NADCONKey key = new NADCONKey(latGridURL.toExternalForm(), longGridURL.toExternalForm());
/* 118 */     synchronized (this.gridCache) {
/* 119 */       NADConGridShift grid = (NADConGridShift)this.gridCache.get(key);
/* 120 */       if (grid != null)
/* 121 */         return grid; 
/* 123 */       grid = loadGridShiftInternal(latGridURL, longGridURL);
/* 124 */       if (grid != null) {
/* 125 */         this.gridCache.put(key, grid);
/* 126 */         return grid;
/*     */       } 
/* 129 */       throw new FactoryException("NTv2 Grid " + latGridURL + ", " + longGridURL + " could not be created.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private NADConGridShift loadGridShiftInternal(URL latGridURL, URL longGridURL) throws FactoryException {
/* 137 */     String latGridName = DataUtilities.urlToFile(latGridURL).getPath();
/* 138 */     String longGridName = DataUtilities.urlToFile(longGridURL).getPath();
/*     */     try {
/* 140 */       if ((latGridName.endsWith(".las") && longGridName.endsWith(".los")) || (latGridName.endsWith(".LAS") && longGridName.endsWith(".LOS")))
/* 142 */         return loadBinaryGrid(latGridURL, longGridURL); 
/* 143 */       if ((latGridName.endsWith(".laa") && longGridName.endsWith(".loa")) || (latGridName.endsWith(".LAA") && longGridName.endsWith(".LOA")))
/* 145 */         return loadTextGrid(latGridURL, longGridURL); 
/* 147 */       throw new FactoryException(Errors.format(200, latGridName.substring(latGridName.lastIndexOf('.') + 1), longGridName.substring(longGridName.lastIndexOf('.') + 1)));
/* 153 */     } catch (IOException exception) {
/* 154 */       Throwable cause = exception.getCause();
/* 155 */       if (cause instanceof FactoryException)
/* 156 */         throw (FactoryException)cause; 
/* 158 */       throw new FactoryException(exception.getLocalizedMessage(), exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private NADConGridShift loadBinaryGrid(URL latGridUrl, URL longGridUrl) throws IOException, FactoryException {
/* 180 */     int HEADER_BYTES = 96;
/* 181 */     int SEPARATOR_BYTES = 4;
/* 182 */     int DESCRIPTION_LENGTH = 64;
/* 183 */     ReadableByteChannel latChannel = null;
/* 184 */     ReadableByteChannel longChannel = null;
/* 185 */     NADConGridShift gridShift = null;
/*     */     try {
/* 193 */       latChannel = getReadChannel(latGridUrl);
/* 194 */       ByteBuffer latBuffer = fillBuffer(latChannel, 96);
/* 196 */       longChannel = getReadChannel(longGridUrl);
/* 197 */       ByteBuffer longBuffer = fillBuffer(longChannel, 96);
/* 203 */       latBuffer.position(latBuffer.position() + 64);
/* 205 */       int nc = latBuffer.getInt();
/* 206 */       int nr = latBuffer.getInt();
/* 207 */       int nz = latBuffer.getInt();
/* 209 */       float xmin = latBuffer.getFloat();
/* 210 */       float dx = latBuffer.getFloat();
/* 211 */       float ymin = latBuffer.getFloat();
/* 212 */       float dy = latBuffer.getFloat();
/* 214 */       float angle = latBuffer.getFloat();
/* 215 */       float xmax = xmin + (nc - 1) * dx;
/* 216 */       float ymax = ymin + (nr - 1) * dy;
/* 219 */       longBuffer.position(longBuffer.position() + 64);
/* 222 */       if (nc != longBuffer.getInt() || nr != longBuffer.getInt() || nz != longBuffer.getInt() || xmin != longBuffer.getFloat() || dx != longBuffer.getFloat() || ymin != longBuffer.getFloat() || dy != longBuffer.getFloat() || angle != longBuffer.getFloat())
/* 226 */         throw new FactoryException(Errors.format(53)); 
/* 232 */       int RECORD_LENGTH = nc * 4 + 4;
/* 233 */       int NUM_BYTES_LEFT = (nr + 1) * RECORD_LENGTH - 96;
/* 234 */       int START_OF_DATA = RECORD_LENGTH - 96;
/* 236 */       latBuffer = fillBuffer(latChannel, NUM_BYTES_LEFT);
/* 237 */       latBuffer.position(START_OF_DATA);
/* 239 */       longBuffer = fillBuffer(longChannel, NUM_BYTES_LEFT);
/* 240 */       longBuffer.position(START_OF_DATA);
/* 242 */       gridShift = new NADConGridShift(xmin, ymin, xmax, ymax, dx, dy, nc, nr);
/* 244 */       int i = 0;
/* 245 */       int j = 0;
/* 246 */       for (i = 0; i < nr; i++) {
/* 247 */         latBuffer.position(latBuffer.position() + 4);
/* 248 */         longBuffer.position(longBuffer.position() + 4);
/* 250 */         for (j = 0; j < nc; j++)
/* 251 */           gridShift.setLocalizationPoint(j, i, longBuffer.getFloat(), latBuffer.getFloat()); 
/*     */       } 
/* 255 */       assert i == nr : i;
/* 256 */       assert j == nc : j;
/*     */     } finally {
/* 258 */       if (latChannel != null)
/* 259 */         latChannel.close(); 
/* 261 */       if (longChannel != null)
/* 262 */         longChannel.close(); 
/*     */     } 
/* 267 */     return gridShift;
/*     */   }
/*     */   
/*     */   private ByteBuffer fillBuffer(ReadableByteChannel channel, int numBytes) throws IOException {
/* 281 */     ByteBuffer buf = ByteBuffer.allocate(numBytes);
/* 283 */     if (fill(buf, channel) == -1)
/* 284 */       throw new EOFException(Errors.format(48)); 
/* 287 */     buf.flip();
/* 288 */     buf.order(ByteOrder.LITTLE_ENDIAN);
/* 290 */     return buf;
/*     */   }
/*     */   
/*     */   private int fill(ByteBuffer buffer, ReadableByteChannel channel) throws IOException {
/* 302 */     int r = buffer.remaining();
/* 306 */     while (buffer.remaining() > 0 && r != -1)
/* 307 */       r = channel.read(buffer); 
/* 310 */     if (r == -1)
/* 311 */       buffer.limit(buffer.position()); 
/* 314 */     return r;
/*     */   }
/*     */   
/*     */   private ReadableByteChannel getReadChannel(URL url) throws IOException {
/* 327 */     ReadableByteChannel channel = null;
/* 329 */     if (url.getProtocol().equals("file")) {
/* 330 */       File file = DataUtilities.urlToFile(url);
/* 332 */       if (!file.exists() || !file.canRead())
/* 333 */         throw new IOException(Errors.format(50, file)); 
/* 336 */       FileInputStream in = new FileInputStream(file);
/* 337 */       channel = in.getChannel();
/*     */     } else {
/* 339 */       InputStream in = url.openConnection().getInputStream();
/* 340 */       channel = Channels.newChannel(in);
/*     */     } 
/* 343 */     return channel;
/*     */   }
/*     */   
/*     */   private NADConGridShift loadTextGrid(URL latGridUrl, URL longGridUrl) throws IOException, FactoryException {
/* 369 */     InputStreamReader latIsr = new InputStreamReader(latGridUrl.openStream());
/* 370 */     BufferedReader latBr = new BufferedReader(latIsr);
/* 372 */     InputStreamReader longIsr = new InputStreamReader(longGridUrl.openStream());
/* 373 */     BufferedReader longBr = new BufferedReader(longIsr);
/* 378 */     String latLine = latBr.readLine();
/* 379 */     latLine = latBr.readLine();
/* 380 */     StringTokenizer latSt = new StringTokenizer(latLine, " ");
/* 382 */     if (latSt.countTokens() != 8)
/* 383 */       throw new FactoryException(Errors.format(54, String.valueOf(latSt.countTokens()))); 
/* 387 */     int nc = Integer.parseInt(latSt.nextToken());
/* 388 */     int nr = Integer.parseInt(latSt.nextToken());
/* 389 */     int nz = Integer.parseInt(latSt.nextToken());
/* 391 */     float xmin = Float.parseFloat(latSt.nextToken());
/* 392 */     float dx = Float.parseFloat(latSt.nextToken());
/* 393 */     float ymin = Float.parseFloat(latSt.nextToken());
/* 394 */     float dy = Float.parseFloat(latSt.nextToken());
/* 396 */     float angle = Float.parseFloat(latSt.nextToken());
/* 397 */     float xmax = xmin + (nc - 1) * dx;
/* 398 */     float ymax = ymin + (nr - 1) * dy;
/* 401 */     String longLine = longBr.readLine();
/* 402 */     longLine = longBr.readLine();
/* 403 */     StringTokenizer longSt = new StringTokenizer(longLine, " ");
/* 405 */     if (longSt.countTokens() != 8)
/* 406 */       throw new FactoryException(Errors.format(54, String.valueOf(longSt.countTokens()))); 
/* 411 */     if (nc != Integer.parseInt(longSt.nextToken()) || nr != Integer.parseInt(longSt.nextToken()) || nz != Integer.parseInt(longSt.nextToken()) || xmin != Float.parseFloat(longSt.nextToken()) || dx != Float.parseFloat(longSt.nextToken()) || ymin != Float.parseFloat(longSt.nextToken()) || dy != Float.parseFloat(longSt.nextToken()) || angle != Float.parseFloat(longSt.nextToken()))
/* 419 */       throw new FactoryException(Errors.format(53)); 
/* 425 */     NADConGridShift gridShift = new NADConGridShift(xmin, ymin, xmax, ymax, dx, dy, nc, nr);
/* 427 */     int i = 0;
/* 428 */     int j = 0;
/* 429 */     for (i = 0; i < nr; i++) {
/* 430 */       for (j = 0; j < nc; ) {
/* 431 */         latLine = latBr.readLine();
/* 432 */         latSt = new StringTokenizer(latLine, " ");
/* 433 */         longLine = longBr.readLine();
/* 434 */         longSt = new StringTokenizer(longLine, " ");
/* 436 */         while (latSt.hasMoreTokens() && longSt.hasMoreTokens()) {
/* 437 */           gridShift.setLocalizationPoint(j, i, Float.parseFloat(longSt.nextToken()), Float.parseFloat(latSt.nextToken()));
/* 440 */           j++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 445 */     assert i == nr : i;
/* 446 */     assert j == nc : j;
/* 448 */     return gridShift;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\NADCONGridShiftFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */