/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ import java.util.NavigableSet;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DBMaker<DBMakerT extends DBMaker<DBMakerT>> {
/*  34 */   protected final String TRUE = "true";
/*     */   
/*  95 */   protected Properties props = new Properties();
/*     */   
/*     */   protected DBMaker() {}
/*     */   
/*     */   protected DBMaker(File file) {
/* 101 */     this.props.setProperty("file", file.getPath());
/*     */   }
/*     */   
/*     */   public static DBMaker newHeapDB() {
/* 109 */     return (new DBMaker<DBMaker>())._newHeapDB();
/*     */   }
/*     */   
/*     */   public DBMakerT _newHeapDB() {
/* 113 */     this.props.setProperty("store", "heap");
/* 114 */     return getThis();
/*     */   }
/*     */   
/*     */   public static DBMaker newMemoryDB() {
/* 123 */     return (new DBMaker<DBMaker>())._newMemoryDB();
/*     */   }
/*     */   
/*     */   public DBMakerT _newMemoryDB() {
/* 127 */     this.props.setProperty("volume", "byteBuffer");
/* 128 */     return getThis();
/*     */   }
/*     */   
/*     */   public static DBMaker newMemoryDirectDB() {
/* 136 */     return (new DBMaker<DBMaker>())._newMemoryDirectDB();
/*     */   }
/*     */   
/*     */   public DBMakerT _newMemoryDirectDB() {
/* 140 */     this.props.setProperty("volume", "directByteBuffer");
/* 141 */     return getThis();
/*     */   }
/*     */   
/*     */   protected static DBMaker newAppendFileDB(File file) {
/* 155 */     return (new DBMaker<DBMaker>())._newAppendFileDB(file);
/*     */   }
/*     */   
/*     */   protected DBMakerT _newAppendFileDB(File file) {
/* 159 */     this.props.setProperty("file", file.getPath());
/* 160 */     this.props.setProperty("store", "append");
/* 161 */     return getThis();
/*     */   }
/*     */   
/*     */   public static <K, V> BTreeMap<K, V> newTempTreeMap() {
/* 172 */     return newTempFileDB().deleteFilesAfterClose().closeOnJvmShutdown().transactionDisable().make().getTreeMap("temp");
/*     */   }
/*     */   
/*     */   public static <K, V> HTreeMap<K, V> newTempHashMap() {
/* 187 */     return newTempFileDB().deleteFilesAfterClose().closeOnJvmShutdown().transactionDisable().make().getHashMap("temp");
/*     */   }
/*     */   
/*     */   public static <K> NavigableSet<K> newTempTreeSet() {
/* 202 */     return newTempFileDB().deleteFilesAfterClose().closeOnJvmShutdown().transactionDisable().make().getTreeSet("temp");
/*     */   }
/*     */   
/*     */   public static <K> Set<K> newTempHashSet() {
/* 217 */     return newTempFileDB().deleteFilesAfterClose().closeOnJvmShutdown().transactionDisable().make().getHashSet("temp");
/*     */   }
/*     */   
/*     */   public static DBMaker newTempFileDB() {
/*     */     try {
/* 230 */       return newFileDB(File.createTempFile("mapdb-temp", "db"));
/* 231 */     } catch (IOException e) {
/* 232 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <K, V> HTreeMap<K, V> newCacheDirect(double size) {
/* 247 */     return newMemoryDirectDB().transactionDisable().make().createHashMap("cache").expireStoreSize(size).counterEnable().make();
/*     */   }
/*     */   
/*     */   public static <K, V> HTreeMap<K, V> newCache(double size) {
/* 269 */     return newMemoryDB().transactionDisable().make().createHashMap("cache").expireStoreSize(size).counterEnable().make();
/*     */   }
/*     */   
/*     */   public static DBMaker newFileDB(File file) {
/* 282 */     return new DBMaker<DBMaker>(file);
/*     */   }
/*     */   
/*     */   public DBMakerT _newFileDB(File file) {
/* 286 */     this.props.setProperty("file", file.getPath());
/* 287 */     return getThis();
/*     */   }
/*     */   
/*     */   protected DBMakerT getThis() {
/* 292 */     return (DBMakerT)this;
/*     */   }
/*     */   
/*     */   public DBMakerT transactionDisable() {
/* 309 */     this.props.put("transactionDisable", "true");
/* 310 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheDisable() {
/* 324 */     this.props.put("cache", "disable");
/* 325 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheHardRefEnable() {
/* 339 */     this.props.put("cache", "hardRef");
/* 340 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheWeakRefEnable() {
/* 351 */     this.props.put("cache", "weakRef");
/* 352 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheSoftRefEnable() {
/* 362 */     this.props.put("cache", "softRef");
/* 363 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheLRUEnable() {
/* 372 */     this.props.put("cache", "lru");
/* 373 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT mmapFileEnable() {
/* 383 */     assertNotInMemoryVolume();
/* 384 */     this.props.setProperty("volume", "mmapf");
/* 385 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT mmapFileEnablePartial() {
/* 402 */     assertNotInMemoryVolume();
/* 403 */     this.props.setProperty("volume", "mmapfPartial");
/* 404 */     return getThis();
/*     */   }
/*     */   
/*     */   private void assertNotInMemoryVolume() {
/* 408 */     if ("byteBuffer".equals(this.props.getProperty("volume")) || "directByteBuffer".equals(this.props.getProperty("volume")))
/* 410 */       throw new IllegalArgumentException("Can not enable mmap file for in-memory store"); 
/*     */   }
/*     */   
/*     */   public DBMakerT mmapFileEnableIfSupported() {
/* 417 */     assertNotInMemoryVolume();
/* 418 */     this.props.setProperty("volume", "mmapfIfSupported");
/* 419 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT cacheSize(int cacheSize) {
/* 434 */     this.props.setProperty("cacheSize", "" + cacheSize);
/* 435 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT snapshotEnable() {
/* 445 */     this.props.setProperty("snapshots", "true");
/* 446 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT asyncWriteEnable() {
/* 460 */     this.props.setProperty("asyncWrite", "true");
/* 461 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT asyncWriteFlushDelay(int delay) {
/* 482 */     this.props.setProperty("asyncWriteFlushDelay", "" + delay);
/* 483 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT asyncWriteQueueSize(int queueSize) {
/* 495 */     this.props.setProperty("asyncWriteQueueSize", "" + queueSize);
/* 496 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT deleteFilesAfterClose() {
/* 507 */     this.props.setProperty("deleteFilesAfterClose", "true");
/* 508 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT closeOnJvmShutdown() {
/* 517 */     this.props.setProperty("closeOnJvmShutdown", "true");
/* 518 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT compressionEnable() {
/* 529 */     this.props.setProperty("compression", "lzf");
/* 530 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT encryptionEnable(String password) {
/* 546 */     return encryptionEnable(password.getBytes(Charset.forName("UTF8")));
/*     */   }
/*     */   
/*     */   public DBMakerT encryptionEnable(byte[] password) {
/* 563 */     this.props.setProperty("encryption", "xtea");
/* 564 */     this.props.setProperty("encryptionKey", toHexa(password));
/* 565 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT checksumEnable() {
/* 578 */     this.props.setProperty("checksum", "true");
/* 579 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT strictDBGet() {
/* 593 */     this.props.setProperty("strictDBGet", "true");
/* 594 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT readOnly() {
/* 607 */     this.props.setProperty("readOnly", "true");
/* 608 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT freeSpaceReclaimQ(int q) {
/* 624 */     if (q < 0 || q > 10)
/* 624 */       throw new IllegalArgumentException("wrong Q"); 
/* 625 */     this.props.setProperty("freeSpaceReclaimQ", "" + q);
/* 626 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT commitFileSyncDisable() {
/* 639 */     this.props.setProperty("commitFileSyncDisable", "true");
/* 640 */     return getThis();
/*     */   }
/*     */   
/*     */   public DBMakerT sizeLimit(double maxSize) {
/* 654 */     long size = (long)(maxSize * 1024.0D * 1024.0D * 1024.0D);
/* 655 */     this.props.setProperty("sizeLimit", "" + size);
/* 656 */     return getThis();
/*     */   }
/*     */   
/*     */   public DB make() {
/* 664 */     boolean strictGet = propsGetBool("strictDBGet");
/* 665 */     Engine engine = makeEngine();
/* 666 */     boolean dbCreated = false;
/*     */     try {
/* 668 */       DB db = new DB(engine, strictGet, false);
/* 669 */       dbCreated = true;
/* 670 */       return db;
/*     */     } finally {
/* 673 */       if (!dbCreated)
/* 674 */         engine.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public TxMaker makeTxMaker() {
/* 680 */     this.props.setProperty("fullTx", "true");
/* 681 */     snapshotEnable();
/* 682 */     Engine e = makeEngine();
/* 684 */     DB db = new DB(e);
/* 685 */     db.commit();
/* 686 */     return new TxMaker(e, propsGetBool("strictDBGet"), propsGetBool("snapshots"));
/*     */   }
/*     */   
/*     */   public Engine makeEngine() {
/* 692 */     boolean readOnly = propsGetBool("readOnly");
/* 693 */     File file = this.props.containsKey("file") ? new File(this.props.getProperty("file")) : null;
/* 694 */     String volume = this.props.getProperty("volume");
/* 695 */     String store = this.props.getProperty("store");
/* 697 */     if (readOnly && file == null)
/* 698 */       throw new UnsupportedOperationException("Can not open in-memory DB in read-only mode."); 
/* 700 */     if (readOnly && !file.exists() && !"append".equals(store))
/* 701 */       throw new UnsupportedOperationException("Can not open non-existing file in read-only mode."); 
/* 704 */     if (propsGetLong("sizeLimit", 0L) > 0L && "append".equals(store))
/* 705 */       throw new UnsupportedOperationException("Append-Only store does not support Size Limit"); 
/* 707 */     extendArgumentCheck();
/* 711 */     if ("heap".equals(store)) {
/* 712 */       engine = extendHeapStore();
/* 714 */     } else if ("append".equals(store)) {
/* 715 */       if ("byteBuffer".equals(volume) || "directByteBuffer".equals(volume))
/* 716 */         throw new UnsupportedOperationException("Append Storage format is not supported with in-memory dbs"); 
/* 717 */       engine = extendStoreAppend();
/*     */     } else {
/* 720 */       Volume.Factory folFac = extendStoreVolumeFactory();
/* 722 */       engine = propsGetBool("transactionDisable") ? extendStoreDirect(folFac) : extendStoreWAL(folFac);
/*     */     } 
/* 727 */     Engine engine = extendWrapStore(engine);
/* 729 */     if (propsGetBool("asyncWrite") && !readOnly)
/* 730 */       engine = extendAsyncWriteEngine(engine); 
/* 733 */     String cache = this.props.getProperty("cache", "hashTable");
/* 735 */     if (!"disable".equals(cache))
/* 737 */       if ("hashTable".equals(cache)) {
/* 738 */         engine = extendCacheHashTable(engine);
/* 739 */       } else if ("hardRef".equals(cache)) {
/* 740 */         engine = extendCacheHardRef(engine);
/* 741 */       } else if ("weakRef".equals(cache)) {
/* 742 */         engine = extendCacheWeakRef(engine);
/* 743 */       } else if ("softRef".equals(cache)) {
/* 744 */         engine = extendCacheSoftRef(engine);
/* 745 */       } else if ("lru".equals(cache)) {
/* 746 */         engine = extendCacheLRU(engine);
/*     */       } else {
/* 748 */         throw new IllegalArgumentException("unknown cache type: " + cache);
/*     */       }  
/* 751 */     engine = extendWrapCache(engine);
/* 754 */     if (propsGetBool("snapshots"))
/* 755 */       engine = extendSnapshotEngine(engine); 
/* 757 */     engine = extendWrapSnapshotEngine(engine);
/* 759 */     if (readOnly)
/* 760 */       engine = new EngineWrapper.ReadOnlyEngine(engine); 
/* 763 */     if (propsGetBool("closeOnJvmShutdown"))
/* 764 */       engine = new EngineWrapper.CloseOnJVMShutdown(engine); 
/* 769 */     Fun.Tuple2<Integer, byte[]> check = null;
/*     */     try {
/* 771 */       check = (Fun.Tuple2<Integer, byte[]>)engine.<Object>get(3L, Serializer.BASIC);
/* 772 */       if (check != null && (
/* 773 */         (Integer)check.a).intValue() != Arrays.hashCode((byte[])check.b))
/* 774 */         throw new RuntimeException("invalid checksum"); 
/* 776 */     } catch (Throwable e) {
/* 777 */       throw new IllegalArgumentException("Error while opening store. Make sure you have right password, compression or encryption is well configured.", e);
/*     */     } 
/* 779 */     if (check == null && !engine.isReadOnly()) {
/* 781 */       byte[] b = new byte[127];
/* 782 */       (new Random()).nextBytes(b);
/* 783 */       check = Fun.t2(Integer.valueOf(Arrays.hashCode(b)), b);
/* 784 */       engine.update(3L, check, Serializer.BASIC);
/* 785 */       engine.commit();
/*     */     } 
/* 789 */     return engine;
/*     */   }
/*     */   
/*     */   protected int propsGetInt(String key, int defValue) {
/* 795 */     String ret = this.props.getProperty(key);
/* 796 */     if (ret == null)
/* 796 */       return defValue; 
/* 797 */     return Integer.valueOf(ret).intValue();
/*     */   }
/*     */   
/*     */   protected long propsGetLong(String key, long defValue) {
/* 801 */     String ret = this.props.getProperty(key);
/* 802 */     if (ret == null)
/* 802 */       return defValue; 
/* 803 */     return Long.valueOf(ret).longValue();
/*     */   }
/*     */   
/*     */   protected boolean propsGetBool(String key) {
/* 808 */     String ret = this.props.getProperty(key);
/* 809 */     return (ret != null && ret.equals("true"));
/*     */   }
/*     */   
/*     */   protected byte[] propsGetXteaEncKey() {
/* 813 */     if (!"xtea".equals(this.props.getProperty("encryption")))
/* 814 */       return null; 
/* 815 */     return fromHexa(this.props.getProperty("encryptionKey"));
/*     */   }
/*     */   
/*     */   protected static boolean JVMSupportsLargeMappedFiles() {
/* 825 */     String prop = System.getProperty("os.arch");
/* 826 */     if (prop != null && prop.contains("64"))
/* 826 */       return true; 
/* 828 */     return false;
/*     */   }
/*     */   
/*     */   protected int propsGetRafMode() {
/* 833 */     String volume = this.props.getProperty("volume");
/* 834 */     if (volume == null || "raf".equals(volume))
/* 835 */       return 2; 
/* 836 */     if ("mmapfIfSupported".equals(volume))
/* 837 */       return JVMSupportsLargeMappedFiles() ? 0 : 2; 
/* 838 */     if ("mmapfPartial".equals(volume))
/* 839 */       return 1; 
/* 840 */     if ("mmapf".equals(volume))
/* 841 */       return 0; 
/* 843 */     return 2;
/*     */   }
/*     */   
/*     */   protected Engine extendSnapshotEngine(Engine engine) {
/* 848 */     return new TxEngine(engine, propsGetBool("fullTx"));
/*     */   }
/*     */   
/*     */   protected Engine extendCacheLRU(Engine engine) {
/* 852 */     int cacheSize = propsGetInt("cacheSize", 32768);
/* 853 */     return new Caches.LRU(engine, cacheSize, false);
/*     */   }
/*     */   
/*     */   protected Engine extendCacheWeakRef(Engine engine) {
/* 857 */     return new Caches.WeakSoftRef(engine, true, false);
/*     */   }
/*     */   
/*     */   protected Engine extendCacheSoftRef(Engine engine) {
/* 861 */     return new Caches.WeakSoftRef(engine, false, false);
/*     */   }
/*     */   
/*     */   protected Engine extendCacheHardRef(Engine engine) {
/* 867 */     int cacheSize = propsGetInt("cacheSize", 32768);
/* 868 */     return new Caches.HardRef(engine, cacheSize, false);
/*     */   }
/*     */   
/*     */   protected Engine extendCacheHashTable(Engine engine) {
/* 872 */     int cacheSize = propsGetInt("cacheSize", 32768);
/* 873 */     return new Caches.HashTable(engine, cacheSize, false);
/*     */   }
/*     */   
/*     */   protected Engine extendAsyncWriteEngine(Engine engine) {
/* 877 */     return new AsyncWriteEngine(engine, propsGetInt("asyncWriteFlushDelay", 100), propsGetInt("asyncWriteQueueSize", 32000), null);
/*     */   }
/*     */   
/*     */   protected void extendArgumentCheck() {}
/*     */   
/*     */   protected Engine extendWrapStore(Engine engine) {
/* 888 */     return engine;
/*     */   }
/*     */   
/*     */   protected Engine extendWrapCache(Engine engine) {
/* 893 */     return engine;
/*     */   }
/*     */   
/*     */   protected Engine extendWrapSnapshotEngine(Engine engine) {
/* 897 */     return engine;
/*     */   }
/*     */   
/*     */   protected Engine extendHeapStore() {
/* 902 */     return new StoreHeap();
/*     */   }
/*     */   
/*     */   protected Engine extendStoreAppend() {
/* 906 */     File file = this.props.containsKey("file") ? new File(this.props.getProperty("file")) : null;
/* 907 */     boolean compressionEnabled = "lzf".equals(this.props.getProperty("compression"));
/* 908 */     return new StoreAppend(file, (propsGetRafMode() > 0), propsGetBool("readOnly"), propsGetBool("transactionDisable"), propsGetBool("deleteFilesAfterClose"), propsGetBool("commitFileSyncDisable"), propsGetBool("checksum"), compressionEnabled, propsGetXteaEncKey(), false);
/*     */   }
/*     */   
/*     */   protected Engine extendStoreDirect(Volume.Factory folFac) {
/* 917 */     boolean compressionEnabled = "lzf".equals(this.props.getProperty("compression"));
/* 918 */     return new StoreDirect(folFac, propsGetBool("readOnly"), propsGetBool("deleteFilesAfterClose"), propsGetInt("freeSpaceReclaimQ", 5), propsGetBool("commitFileSyncDisable"), propsGetLong("sizeLimit", 0L), propsGetBool("checksum"), compressionEnabled, propsGetXteaEncKey(), false, 0);
/*     */   }
/*     */   
/*     */   protected Engine extendStoreWAL(Volume.Factory folFac) {
/* 927 */     boolean compressionEnabled = "lzf".equals(this.props.getProperty("compression"));
/* 928 */     return new StoreWAL(folFac, propsGetBool("readOnly"), propsGetBool("deleteFilesAfterClose"), propsGetInt("freeSpaceReclaimQ", 5), propsGetBool("commitFileSyncDisable"), propsGetLong("sizeLimit", -1L), propsGetBool("checksum"), compressionEnabled, propsGetXteaEncKey(), false, 0);
/*     */   }
/*     */   
/*     */   protected Volume.Factory extendStoreVolumeFactory() {
/* 937 */     long sizeLimit = propsGetLong("sizeLimit", 0L);
/* 938 */     String volume = this.props.getProperty("volume");
/* 939 */     if ("byteBuffer".equals(volume))
/* 940 */       return Volume.memoryFactory(false, sizeLimit, 20); 
/* 941 */     if ("directByteBuffer".equals(volume))
/* 942 */       return Volume.memoryFactory(true, sizeLimit, 20); 
/* 944 */     File indexFile = new File(this.props.getProperty("file"));
/* 946 */     File dataFile = new File(indexFile.getPath() + ".p");
/* 947 */     File logFile = new File(indexFile.getPath() + ".t");
/* 949 */     return Volume.fileFactory(indexFile, propsGetRafMode(), propsGetBool("readOnly"), sizeLimit, 20, 0, dataFile, logFile, propsGetBool("asyncWrite"));
/*     */   }
/*     */   
/*     */   protected static String toHexa(byte[] bb) {
/* 960 */     char[] HEXA_CHARS = { 
/* 960 */         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/* 960 */         'A', 'B', 'C', 'D', 'E', 'F' };
/* 961 */     char[] ret = new char[bb.length * 2];
/* 962 */     for (int i = 0; i < bb.length; i++) {
/* 963 */       ret[i * 2] = HEXA_CHARS[(bb[i] & 0xF0) >> 4];
/* 964 */       ret[i * 2 + 1] = HEXA_CHARS[bb[i] & 0xF];
/*     */     } 
/* 966 */     return new String(ret);
/*     */   }
/*     */   
/*     */   protected static byte[] fromHexa(String s) {
/* 970 */     byte[] ret = new byte[s.length() / 2];
/* 971 */     for (int i = 0; i < ret.length; i++)
/* 972 */       ret[i] = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16); 
/* 974 */     return ret;
/*     */   }
/*     */   
/*     */   protected static interface Keys {
/*     */     public static final String cache = "cache";
/*     */     
/*     */     public static final String cacheSize = "cacheSize";
/*     */     
/*     */     public static final String cache_disable = "disable";
/*     */     
/*     */     public static final String cache_hashTable = "hashTable";
/*     */     
/*     */     public static final String cache_hardRef = "hardRef";
/*     */     
/*     */     public static final String cache_softRef = "softRef";
/*     */     
/*     */     public static final String cache_weakRef = "weakRef";
/*     */     
/*     */     public static final String cache_lru = "lru";
/*     */     
/*     */     public static final String file = "file";
/*     */     
/*     */     public static final String volume = "volume";
/*     */     
/*     */     public static final String volume_raf = "raf";
/*     */     
/*     */     public static final String volume_mmapfPartial = "mmapfPartial";
/*     */     
/*     */     public static final String volume_mmapfIfSupported = "mmapfIfSupported";
/*     */     
/*     */     public static final String volume_mmapf = "mmapf";
/*     */     
/*     */     public static final String volume_byteBuffer = "byteBuffer";
/*     */     
/*     */     public static final String volume_directByteBuffer = "directByteBuffer";
/*     */     
/*     */     public static final String store = "store";
/*     */     
/*     */     public static final String store_direct = "direct";
/*     */     
/*     */     public static final String store_wal = "wal";
/*     */     
/*     */     public static final String store_append = "append";
/*     */     
/*     */     public static final String store_heap = "heap";
/*     */     
/*     */     public static final String transactionDisable = "transactionDisable";
/*     */     
/*     */     public static final String asyncWrite = "asyncWrite";
/*     */     
/*     */     public static final String asyncWriteFlushDelay = "asyncWriteFlushDelay";
/*     */     
/*     */     public static final String asyncWriteQueueSize = "asyncWriteQueueSize";
/*     */     
/*     */     public static final String deleteFilesAfterClose = "deleteFilesAfterClose";
/*     */     
/*     */     public static final String closeOnJvmShutdown = "closeOnJvmShutdown";
/*     */     
/*     */     public static final String readOnly = "readOnly";
/*     */     
/*     */     public static final String compression = "compression";
/*     */     
/*     */     public static final String compression_lzf = "lzf";
/*     */     
/*     */     public static final String encryptionKey = "encryptionKey";
/*     */     
/*     */     public static final String encryption = "encryption";
/*     */     
/*     */     public static final String encryption_xtea = "xtea";
/*     */     
/*     */     public static final String checksum = "checksum";
/*     */     
/*     */     public static final String freeSpaceReclaimQ = "freeSpaceReclaimQ";
/*     */     
/*     */     public static final String commitFileSyncDisable = "commitFileSyncDisable";
/*     */     
/*     */     public static final String snapshots = "snapshots";
/*     */     
/*     */     public static final String strictDBGet = "strictDBGet";
/*     */     
/*     */     public static final String sizeLimit = "sizeLimit";
/*     */     
/*     */     public static final String fullTx = "fullTx";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\DBMaker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */