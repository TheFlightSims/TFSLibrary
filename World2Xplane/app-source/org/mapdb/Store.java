/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.CRC32;
/*     */ 
/*     */ public abstract class Store implements Engine {
/*  39 */   protected static final Logger LOG = Logger.getLogger(Store.class.getName());
/*     */   
/*     */   protected final boolean checksum;
/*     */   
/*     */   protected final boolean compress;
/*     */   
/*     */   protected final boolean encrypt;
/*     */   
/*     */   protected final byte[] password;
/*     */   
/*     */   protected final EncryptionXTEA encryptionXTEA;
/*     */   
/*     */   protected static final int CHECKSUM_FLAG_MASK = 1;
/*     */   
/*     */   protected static final int COMPRESS_FLAG_MASK = 4;
/*     */   
/*     */   protected static final int ENCRYPT_FLAG_MASK = 8;
/*     */   
/*     */   protected static final int CHUNK_SIZE = 1048576;
/*     */   
/*     */   protected static final int CHUNK_SIZE_MOD_MASK = 1048575;
/*     */   
/*     */   protected SerializerPojo serializerPojo;
/*     */   
/*     */   protected final ThreadLocal<CompressLZF> LZF;
/*     */   
/*     */   public void printStatistics() {
/*  98 */     System.out.println(calculateStatistics());
/*     */   }
/*     */   
/* 101 */   protected Lock serializerPojoInitLock = new ReentrantLock(false);
/*     */   
/*     */   public SerializerPojo getSerializerPojo() {
/* 107 */     Lock pojoLock = this.serializerPojoInitLock;
/* 108 */     if (pojoLock != null) {
/* 109 */       pojoLock.lock();
/*     */       try {
/* 111 */         if (this.serializerPojo == null) {
/* 112 */           CopyOnWriteArrayList<SerializerPojo.ClassInfo> classInfos = get(2L, SerializerPojo.serializer);
/* 113 */           this.serializerPojo = new SerializerPojo(classInfos);
/* 114 */           this.serializerPojoInitLock = null;
/*     */         } 
/*     */       } finally {
/* 117 */         pojoLock.unlock();
/*     */       } 
/*     */     } 
/* 121 */     return this.serializerPojo;
/*     */   }
/*     */   
/* 125 */   protected final ReentrantLock structuralLock = new ReentrantLock(false);
/*     */   
/* 126 */   protected final ReentrantReadWriteLock newRecidLock = new ReentrantReadWriteLock(false);
/*     */   
/* 127 */   protected final ReentrantReadWriteLock[] locks = new ReentrantReadWriteLock[128];
/*     */   
/*     */   protected final Queue<DataOutput2> recycledDataOuts;
/*     */   
/*     */   private static final int LOCK_MASK = 127;
/*     */   
/*     */   List<Runnable> closeListeners;
/*     */   
/*     */   protected Store(boolean checksum, boolean compress, byte[] password, boolean disableLocks) {
/* 129 */     for (int i = 0; i < this.locks.length; ) {
/* 129 */       this.locks[i] = new ReentrantReadWriteLock(false);
/* 129 */       i++;
/*     */     } 
/* 147 */     this.recycledDataOuts = new ArrayBlockingQueue<DataOutput2>(128);
/* 346 */     this.closeListeners = new CopyOnWriteArrayList<Runnable>();
/*     */     this.checksum = checksum;
/*     */     this.compress = compress;
/*     */     this.encrypt = (password != null);
/*     */     this.password = password;
/*     */     this.encryptionXTEA = !this.encrypt ? null : new EncryptionXTEA(password);
/*     */     this.LZF = !compress ? null : new ThreadLocal<CompressLZF>() {
/*     */         protected CompressLZF initialValue() {
/*     */           return new CompressLZF();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   protected void lockAllWrite() {
/*     */     this.newRecidLock.writeLock().lock();
/*     */     for (ReentrantReadWriteLock l : this.locks)
/*     */       l.writeLock().lock(); 
/*     */     this.structuralLock.lock();
/*     */   }
/*     */   
/*     */   protected void unlockAllWrite() {
/*     */     this.structuralLock.unlock();
/*     */     for (ReentrantReadWriteLock l : this.locks)
/*     */       l.writeLock().unlock(); 
/*     */     this.newRecidLock.writeLock().unlock();
/*     */   }
/*     */   
/*     */   protected <A> DataOutput2 serialize(A value, Serializer<A> serializer) {
/*     */     try {
/*     */       DataOutput2 out = newDataOut2();
/*     */       serializer.serialize(out, value);
/*     */       if (out.pos > 0) {
/*     */         if (this.compress) {
/*     */           byte b;
/*     */           DataOutput2 tmp = newDataOut2();
/*     */           tmp.ensureAvail(out.pos + 40);
/*     */           CompressLZF lzf = this.LZF.get();
/*     */           try {
/*     */             b = lzf.compress(out.buf, out.pos, tmp.buf, 0);
/*     */           } catch (IndexOutOfBoundsException e) {
/*     */             b = 0;
/*     */           } 
/*     */           if (b >= out.pos)
/*     */             b = 0; 
/*     */           if (b == 0) {
/*     */             this.recycledDataOuts.offer(tmp);
/*     */             out.ensureAvail(out.pos + 1);
/*     */             System.arraycopy(out.buf, 0, out.buf, 1, out.pos);
/*     */             out.pos++;
/*     */             out.buf[0] = 0;
/*     */           } else {
/*     */             int decompSize = out.pos;
/*     */             out.pos = 0;
/*     */             DataOutput2.packInt(out, decompSize);
/*     */             out.write(tmp.buf, 0, b);
/*     */             this.recycledDataOuts.offer(tmp);
/*     */           } 
/*     */         } 
/*     */         if (this.encrypt) {
/*     */           int size = out.pos;
/*     */           if (size % 16 != 0)
/*     */             size += 16 - size % 16; 
/*     */           int sizeDif = size - out.pos;
/*     */           out.ensureAvail(sizeDif + 1);
/*     */           this.encryptionXTEA.encrypt(out.buf, 0, size);
/*     */           out.pos = size;
/*     */           out.writeByte(sizeDif);
/*     */         } 
/*     */         if (this.checksum) {
/*     */           CRC32 crc = new CRC32();
/*     */           crc.update(out.buf, 0, out.pos);
/*     */           out.writeInt((int)crc.getValue());
/*     */         } 
/*     */       } 
/*     */       return out;
/*     */     } catch (IOException e) {
/*     */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected DataOutput2 newDataOut2() {
/*     */     DataOutput2 tmp = this.recycledDataOuts.poll();
/*     */     if (tmp == null) {
/*     */       tmp = new DataOutput2();
/*     */     } else {
/*     */       tmp.pos = 0;
/*     */     } 
/*     */     return tmp;
/*     */   }
/*     */   
/*     */   protected <A> A deserialize(Serializer<A> serializer, int size, DataInput input) throws IOException {
/*     */     DataInput2 di = (DataInput2)input;
/*     */     if (size > 0) {
/*     */       if (this.checksum) {
/*     */         size -= 4;
/*     */         DataOutput2 tmp = newDataOut2();
/*     */         tmp.ensureAvail(size);
/*     */         int oldPos = di.pos;
/*     */         di.read(tmp.buf, 0, size);
/*     */         di.pos = oldPos;
/*     */         CRC32 crc = new CRC32();
/*     */         crc.update(tmp.buf, 0, size);
/*     */         this.recycledDataOuts.offer(tmp);
/*     */         int check = (int)crc.getValue();
/*     */         int checkExpected = di.buf.getInt(di.pos + size);
/*     */         if (check != checkExpected)
/*     */           throw new IOException("Checksum does not match, data broken"); 
/*     */       } 
/*     */       if (this.encrypt) {
/*     */         DataOutput2 tmp = newDataOut2();
/*     */         size--;
/*     */         tmp.ensureAvail(size);
/*     */         di.read(tmp.buf, 0, size);
/*     */         this.encryptionXTEA.decrypt(tmp.buf, 0, size);
/*     */         int cut = di.readUnsignedByte();
/*     */         di = new DataInput2(tmp.buf);
/*     */         size -= cut;
/*     */       } 
/*     */       if (this.compress) {
/*     */         int decompSize = DataInput2.unpackInt(di);
/*     */         if (decompSize == 0) {
/*     */           size--;
/*     */         } else {
/*     */           DataOutput2 out = newDataOut2();
/*     */           out.ensureAvail(decompSize);
/*     */           CompressLZF lzf = this.LZF.get();
/*     */           lzf.expand(di.buf, di.pos, out.buf, 0, decompSize);
/*     */           di = new DataInput2(out.buf);
/*     */           size = decompSize;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     int start = di.pos;
/*     */     A ret = serializer.deserialize(di, size);
/*     */     if (size + start > di.pos)
/*     */       throw new AssertionError("data were not fully read, check your serializer "); 
/*     */     if (size + start < di.pos)
/*     */       throw new AssertionError("data were read beyond record size, check your serializer"); 
/*     */     return ret;
/*     */   }
/*     */   
/*     */   public static Store forDB(DB db) {
/*     */     return forEngine(db.engine);
/*     */   }
/*     */   
/*     */   public static Store forEngine(Engine e) {
/*     */     if (e instanceof EngineWrapper)
/*     */       return forEngine(((EngineWrapper)e).getWrappedEngine()); 
/*     */     if (e instanceof TxEngine.Tx)
/*     */       return forEngine(((TxEngine.Tx)e).getWrappedEngine()); 
/*     */     return (Store)e;
/*     */   }
/*     */   
/*     */   protected int expectedMasks() {
/*     */     return (this.encrypt ? 8 : 0) | (this.checksum ? 1 : 0) | (this.compress ? 4 : 0);
/*     */   }
/*     */   
/*     */   protected static int lockPos(long key) {
/*     */     int h = (int)(key ^ key >>> 32L);
/*     */     h ^= h >>> 20 ^ h >>> 12;
/*     */     h ^= h >>> 7 ^ h >>> 4;
/*     */     return h & 0x7F;
/*     */   }
/*     */   
/*     */   public boolean canSnapshot() {
/*     */     return false;
/*     */   }
/*     */   
/*     */   public Engine snapshot() throws UnsupportedOperationException {
/*     */     throw new UnsupportedOperationException("Snapshots are not supported");
/*     */   }
/*     */   
/*     */   public void closeListenerRegister(Runnable closeListener) {
/* 350 */     this.closeListeners.add(closeListener);
/*     */   }
/*     */   
/*     */   public void closeListenerUnregister(Runnable closeListener) {
/* 355 */     this.closeListeners.remove(closeListener);
/*     */   }
/*     */   
/*     */   public abstract long getMaxRecid();
/*     */   
/*     */   public abstract ByteBuffer getRaw(long paramLong);
/*     */   
/*     */   public abstract Iterator<Long> getFreeRecids();
/*     */   
/*     */   public abstract void updateRaw(long paramLong, ByteBuffer paramByteBuffer);
/*     */   
/*     */   public abstract long getSizeLimit();
/*     */   
/*     */   public abstract long getCurrSize();
/*     */   
/*     */   public abstract long getFreeSize();
/*     */   
/*     */   public abstract String calculateStatistics();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Store.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */