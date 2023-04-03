/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class NIOUtilities {
/*  49 */   static Map<Integer, Queue<Object>> cache = new ConcurrentHashMap<Integer, Queue<Object>>();
/*     */   
/*  51 */   static Map<Class, Method> cleanerMethodCache = (Map)new ConcurrentHashMap<Class<?>, Method>();
/*     */   
/*  57 */   static int maxCacheSize = 2097152;
/*     */   
/*  62 */   static AtomicInteger hardCacheSize = new AtomicInteger(0);
/*     */   
/*  67 */   static final byte[] ZEROES = new byte[4096];
/*     */   
/*     */   private static boolean warned = false;
/*     */   
/*     */   private static boolean directBuffersEnabled = true;
/*     */   
/*     */   static {
/*  80 */     String directBuffers = System.getProperty("geotools.nioutilities.direct", "true");
/*  81 */     directBuffersEnabled = "TRUE".equalsIgnoreCase(directBuffers);
/*     */   }
/*     */   
/*     */   public static boolean isDirectBuffersEnabled() {
/*  89 */     return directBuffersEnabled;
/*     */   }
/*     */   
/*     */   public static void setDirectBuffersEnabled(boolean directBuffersEnabled) {
/* 101 */     NIOUtilities.directBuffersEnabled = directBuffersEnabled;
/*     */   }
/*     */   
/*     */   public static void setMaxCacheSize(int maxCacheSize) {
/* 117 */     NIOUtilities.maxCacheSize = maxCacheSize;
/*     */   }
/*     */   
/*     */   public static ByteBuffer allocate(int size) {
/* 131 */     Queue<Object> buffers = getBuffers(size);
/* 132 */     Object sr = null;
/* 133 */     while ((sr = buffers.poll()) != null) {
/* 134 */       ByteBuffer buffer = null;
/* 136 */       if (sr instanceof BufferSoftReference) {
/* 137 */         buffer = ((BufferSoftReference)sr).get();
/*     */       } else {
/* 140 */         buffer = (ByteBuffer)sr;
/* 141 */         hardCacheSize.addAndGet(-buffer.capacity());
/*     */       } 
/* 144 */       if (buffer != null) {
/* 145 */         buffer.clear();
/* 146 */         return buffer;
/*     */       } 
/*     */     } 
/* 151 */     if (directBuffersEnabled)
/* 152 */       return ByteBuffer.allocateDirect(size); 
/* 154 */     return ByteBuffer.allocate(size);
/*     */   }
/*     */   
/*     */   private static Queue<Object> getBuffers(int size) {
/* 164 */     Queue<Object> result = cache.get(Integer.valueOf(size));
/* 165 */     if (result == null)
/* 169 */       synchronized (cache) {
/* 170 */         result = cache.get(Integer.valueOf(size));
/* 171 */         if (result == null) {
/* 172 */           result = new ConcurrentLinkedQueue();
/* 173 */           cache.put(Integer.valueOf(size), result);
/*     */         } 
/*     */       }  
/* 177 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean clean(ByteBuffer buffer, boolean memoryMapped) {
/* 189 */     if (memoryMapped)
/* 190 */       return clean(buffer); 
/* 192 */     if (returnToCache(buffer))
/* 193 */       return true; 
/* 195 */     return clean(buffer);
/*     */   }
/*     */   
/*     */   public static boolean clean(final ByteBuffer buffer) {
/* 213 */     if (buffer == null || !buffer.isDirect())
/* 214 */       return true; 
/* 217 */     Boolean b = AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>() {
/*     */           public Boolean run() {
/* 219 */             Boolean success = Boolean.FALSE;
/*     */             try {
/* 221 */               Method getCleanerMethod = NIOUtilities.getCleanerMethod(buffer);
/* 222 */               if (getCleanerMethod != null) {
/* 223 */                 Object cleaner = getCleanerMethod.invoke(buffer, (Object[])null);
/* 224 */                 if (cleaner != null) {
/* 225 */                   Method clean = cleaner.getClass().getMethod("clean", (Class[])null);
/* 226 */                   clean.invoke(cleaner, (Object[])null);
/* 227 */                   success = Boolean.TRUE;
/*     */                 } 
/*     */               } 
/* 230 */             } catch (Exception e) {
/* 232 */               if (NIOUtilities.isLoggable())
/* 233 */                 NIOUtilities.log(e, buffer); 
/*     */             } 
/* 236 */             return success;
/*     */           }
/*     */         });
/* 239 */     return b.booleanValue();
/*     */   }
/*     */   
/*     */   static Method getCleanerMethod(ByteBuffer buffer) throws NoSuchMethodException {
/* 243 */     Method result = cleanerMethodCache.get(buffer.getClass());
/* 244 */     if (result == null) {
/* 245 */       result = buffer.getClass().getMethod("cleaner", (Class[])null);
/* 246 */       result.setAccessible(true);
/* 247 */       cleanerMethodCache.put(buffer.getClass(), result);
/*     */     } 
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean returnToCache(ByteBuffer buffer) {
/* 255 */     int capacity = buffer.capacity();
/* 256 */     if (capacity != 100 && capacity != 13 && capacity != 16000) {
/* 257 */       int size = (int)Math.pow(2.0D, Math.ceil(Math.log(capacity) / Math.log(2.0D)));
/* 258 */       if (size != capacity)
/* 259 */         return false; 
/*     */     } 
/* 265 */     buffer.clear();
/* 266 */     buffer.order(ByteOrder.BIG_ENDIAN);
/* 270 */     Queue<Object> buffers = cache.get(Integer.valueOf(capacity));
/* 271 */     if (hardCacheSize.get() > maxCacheSize) {
/* 272 */       buffers.add(new BufferSoftReference(buffer));
/*     */     } else {
/* 274 */       hardCacheSize.addAndGet(capacity);
/* 275 */       buffers.add(buffer);
/*     */     } 
/* 277 */     return true;
/*     */   }
/*     */   
/*     */   private static synchronized boolean isLoggable() {
/*     */     try {
/* 285 */       return (!warned && (Boolean.getBoolean("org.geotools.io.debugBuffer") || System.getProperty("os.name").indexOf("Windows") >= 0));
/* 288 */     } catch (SecurityException exception) {
/* 291 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static synchronized void log(Exception e, ByteBuffer buffer) {
/* 299 */     warned = true;
/* 300 */     String message = "Error attempting to close a mapped byte buffer : " + buffer.getClass().getName() + "\n JVM : " + System.getProperty("java.version") + ' ' + System.getProperty("java.vendor");
/* 303 */     Logging.getLogger("org.geotools.io").log(Level.SEVERE, message, e);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\NIOUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */