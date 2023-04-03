/*     */ package com.google.common.io;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Ascii;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.hash.Funnels;
/*     */ import com.google.common.hash.HashCode;
/*     */ import com.google.common.hash.HashFunction;
/*     */ import com.google.common.hash.Hasher;
/*     */ import com.google.common.hash.PrimitiveSink;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class ByteSource implements InputSupplier<InputStream> {
/*     */   private static final int BUF_SIZE = 4096;
/*     */   
/*     */   public CharSource asCharSource(Charset charset) {
/*  73 */     return new AsCharSource(charset);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final InputStream getInput() throws IOException {
/*  98 */     return openStream();
/*     */   }
/*     */   
/*     */   public InputStream openBufferedStream() throws IOException {
/* 114 */     InputStream in = openStream();
/* 115 */     return (in instanceof BufferedInputStream) ? in : new BufferedInputStream(in);
/*     */   }
/*     */   
/*     */   public ByteSource slice(long offset, long length) {
/* 127 */     return new SlicedByteSource(offset, length);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() throws IOException {
/* 138 */     Closer closer = Closer.create();
/*     */     try {
/* 140 */       InputStream in = closer.<InputStream>register(openStream());
/* 141 */       return (in.read() == -1);
/* 142 */     } catch (Throwable e) {
/* 143 */       throw closer.rethrow(e);
/*     */     } finally {
/* 145 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long size() throws IOException {
/* 165 */     Closer closer = Closer.create();
/*     */     try {
/* 167 */       InputStream in = closer.<InputStream>register(openStream());
/* 168 */       return countBySkipping(in);
/* 169 */     } catch (IOException e) {
/*     */     
/*     */     } finally {
/* 172 */       closer.close();
/*     */     } 
/* 175 */     closer = Closer.create();
/*     */     try {
/* 177 */       InputStream in = closer.<InputStream>register(openStream());
/* 178 */       return countByReading(in);
/* 179 */     } catch (Throwable e) {
/* 180 */       throw closer.rethrow(e);
/*     */     } finally {
/* 182 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private long countBySkipping(InputStream in) throws IOException {
/* 191 */     long count = 0L;
/*     */     while (true) {
/* 195 */       long skipped = in.skip(Math.min(in.available(), 2147483647));
/* 196 */       if (skipped <= 0L) {
/* 197 */         if (in.read() == -1)
/* 198 */           return count; 
/* 199 */         if (count == 0L && in.available() == 0)
/* 202 */           throw new IOException(); 
/* 204 */         count++;
/*     */         continue;
/*     */       } 
/* 206 */       count += skipped;
/*     */     } 
/*     */   }
/*     */   
/* 211 */   private static final byte[] countBuffer = new byte[4096];
/*     */   
/*     */   private long countByReading(InputStream in) throws IOException {
/* 214 */     long count = 0L;
/*     */     long read;
/* 216 */     while ((read = in.read(countBuffer)) != -1L)
/* 217 */       count += read; 
/* 219 */     return count;
/*     */   }
/*     */   
/*     */   public long copyTo(OutputStream output) throws IOException {
/* 230 */     Preconditions.checkNotNull(output);
/* 232 */     Closer closer = Closer.create();
/*     */     try {
/* 234 */       InputStream in = closer.<InputStream>register(openStream());
/* 235 */       return ByteStreams.copy(in, output);
/* 236 */     } catch (Throwable e) {
/* 237 */       throw closer.rethrow(e);
/*     */     } finally {
/* 239 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long copyTo(ByteSink sink) throws IOException {
/* 250 */     Preconditions.checkNotNull(sink);
/* 252 */     Closer closer = Closer.create();
/*     */     try {
/* 254 */       InputStream in = closer.<InputStream>register(openStream());
/* 255 */       OutputStream out = closer.<OutputStream>register(sink.openStream());
/* 256 */       return ByteStreams.copy(in, out);
/* 257 */     } catch (Throwable e) {
/* 258 */       throw closer.rethrow(e);
/*     */     } finally {
/* 260 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] read() throws IOException {
/* 270 */     Closer closer = Closer.create();
/*     */     try {
/* 272 */       InputStream in = closer.<InputStream>register(openStream());
/* 273 */       return ByteStreams.toByteArray(in);
/* 274 */     } catch (Throwable e) {
/* 275 */       throw closer.rethrow(e);
/*     */     } finally {
/* 277 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Beta
/*     */   public <T> T read(ByteProcessor<T> processor) throws IOException {
/* 292 */     Preconditions.checkNotNull(processor);
/* 294 */     Closer closer = Closer.create();
/*     */     try {
/* 296 */       InputStream in = closer.<InputStream>register(openStream());
/* 297 */       return (T)ByteStreams.readBytes(in, (ByteProcessor)processor);
/* 298 */     } catch (Throwable e) {
/* 299 */       throw closer.rethrow(e);
/*     */     } finally {
/* 301 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public HashCode hash(HashFunction hashFunction) throws IOException {
/* 311 */     Hasher hasher = hashFunction.newHasher();
/* 312 */     copyTo(Funnels.asOutputStream((PrimitiveSink)hasher));
/* 313 */     return hasher.hash();
/*     */   }
/*     */   
/*     */   public boolean contentEquals(ByteSource other) throws IOException {
/* 324 */     Preconditions.checkNotNull(other);
/* 326 */     byte[] buf1 = new byte[4096];
/* 327 */     byte[] buf2 = new byte[4096];
/* 329 */     Closer closer = Closer.create();
/*     */     try {
/* 331 */       InputStream in1 = closer.<InputStream>register(openStream());
/* 332 */       InputStream in2 = closer.<InputStream>register(other.openStream());
/*     */       while (true) {
/* 334 */         int read1 = ByteStreams.read(in1, buf1, 0, 4096);
/* 335 */         int read2 = ByteStreams.read(in2, buf2, 0, 4096);
/* 336 */         if (read1 != read2 || !Arrays.equals(buf1, buf2))
/* 337 */           return false; 
/* 338 */         if (read1 != 4096)
/* 339 */           return true; 
/*     */       } 
/* 342 */     } catch (Throwable e) {
/* 343 */       throw closer.rethrow(e);
/*     */     } finally {
/* 345 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ByteSource concat(Iterable<? extends ByteSource> sources) {
/* 361 */     return new ConcatenatedByteSource(sources);
/*     */   }
/*     */   
/*     */   public static ByteSource concat(Iterator<? extends ByteSource> sources) {
/* 383 */     return concat((Iterable<? extends ByteSource>)ImmutableList.copyOf(sources));
/*     */   }
/*     */   
/*     */   public static ByteSource concat(ByteSource... sources) {
/* 399 */     return concat((Iterable<? extends ByteSource>)ImmutableList.copyOf((Object[])sources));
/*     */   }
/*     */   
/*     */   public static ByteSource wrap(byte[] b) {
/* 409 */     return new ByteArrayByteSource(b);
/*     */   }
/*     */   
/*     */   public static ByteSource empty() {
/* 418 */     return EmptyByteSource.INSTANCE;
/*     */   }
/*     */   
/*     */   public abstract InputStream openStream() throws IOException;
/*     */   
/*     */   private final class AsCharSource extends CharSource {
/*     */     private final Charset charset;
/*     */     
/*     */     private AsCharSource(Charset charset) {
/* 430 */       this.charset = (Charset)Preconditions.checkNotNull(charset);
/*     */     }
/*     */     
/*     */     public Reader openStream() throws IOException {
/* 435 */       return new InputStreamReader(ByteSource.this.openStream(), this.charset);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 440 */       return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   private final class SlicedByteSource extends ByteSource {
/*     */     private final long offset;
/*     */     
/*     */     private final long length;
/*     */     
/*     */     private SlicedByteSource(long offset, long length) {
/* 453 */       Preconditions.checkArgument((offset >= 0L), "offset (%s) may not be negative", new Object[] { Long.valueOf(offset) });
/* 454 */       Preconditions.checkArgument((length >= 0L), "length (%s) may not be negative", new Object[] { Long.valueOf(length) });
/* 455 */       this.offset = offset;
/* 456 */       this.length = length;
/*     */     }
/*     */     
/*     */     public InputStream openStream() throws IOException {
/* 461 */       return sliceStream(ByteSource.this.openStream());
/*     */     }
/*     */     
/*     */     public InputStream openBufferedStream() throws IOException {
/* 466 */       return sliceStream(ByteSource.this.openBufferedStream());
/*     */     }
/*     */     
/*     */     private InputStream sliceStream(InputStream in) throws IOException {
/* 470 */       if (this.offset > 0L)
/*     */         try {
/* 472 */           ByteStreams.skipFully(in, this.offset);
/* 473 */         } catch (Throwable e) {
/* 474 */           Closer closer = Closer.create();
/* 475 */           closer.register(in);
/*     */           try {
/* 477 */             throw closer.rethrow(e);
/*     */           } finally {
/* 479 */             closer.close();
/*     */           } 
/*     */         }  
/* 483 */       return ByteStreams.limit(in, this.length);
/*     */     }
/*     */     
/*     */     public ByteSource slice(long offset, long length) {
/* 488 */       Preconditions.checkArgument((offset >= 0L), "offset (%s) may not be negative", new Object[] { Long.valueOf(offset) });
/* 489 */       Preconditions.checkArgument((length >= 0L), "length (%s) may not be negative", new Object[] { Long.valueOf(length) });
/* 490 */       long maxLength = this.length - offset;
/* 491 */       return ByteSource.this.slice(this.offset + offset, Math.min(length, maxLength));
/*     */     }
/*     */     
/*     */     public boolean isEmpty() throws IOException {
/* 496 */       return (this.length == 0L || super.isEmpty());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 501 */       return ByteSource.this.toString() + ".slice(" + this.offset + ", " + this.length + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   private static class ByteArrayByteSource extends ByteSource {
/*     */     protected final byte[] bytes;
/*     */     
/*     */     protected ByteArrayByteSource(byte[] bytes) {
/* 510 */       this.bytes = (byte[])Preconditions.checkNotNull(bytes);
/*     */     }
/*     */     
/*     */     public InputStream openStream() {
/* 515 */       return new ByteArrayInputStream(this.bytes);
/*     */     }
/*     */     
/*     */     public InputStream openBufferedStream() throws IOException {
/* 520 */       return openStream();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 525 */       return (this.bytes.length == 0);
/*     */     }
/*     */     
/*     */     public long size() {
/* 530 */       return this.bytes.length;
/*     */     }
/*     */     
/*     */     public byte[] read() {
/* 535 */       return (byte[])this.bytes.clone();
/*     */     }
/*     */     
/*     */     public long copyTo(OutputStream output) throws IOException {
/* 540 */       output.write(this.bytes);
/* 541 */       return this.bytes.length;
/*     */     }
/*     */     
/*     */     public <T> T read(ByteProcessor<T> processor) throws IOException {
/* 546 */       processor.processBytes(this.bytes, 0, this.bytes.length);
/* 547 */       return processor.getResult();
/*     */     }
/*     */     
/*     */     public HashCode hash(HashFunction hashFunction) throws IOException {
/* 552 */       return hashFunction.hashBytes(this.bytes);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 559 */       return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes), 30, "...") + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class EmptyByteSource extends ByteArrayByteSource {
/* 566 */     private static final EmptyByteSource INSTANCE = new EmptyByteSource();
/*     */     
/*     */     private EmptyByteSource() {
/* 569 */       super(new byte[0]);
/*     */     }
/*     */     
/*     */     public CharSource asCharSource(Charset charset) {
/* 574 */       Preconditions.checkNotNull(charset);
/* 575 */       return CharSource.empty();
/*     */     }
/*     */     
/*     */     public byte[] read() {
/* 580 */       return this.bytes;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 585 */       return "ByteSource.empty()";
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ConcatenatedByteSource extends ByteSource {
/*     */     private final Iterable<? extends ByteSource> sources;
/*     */     
/*     */     ConcatenatedByteSource(Iterable<? extends ByteSource> sources) {
/* 594 */       this.sources = (Iterable<? extends ByteSource>)Preconditions.checkNotNull(sources);
/*     */     }
/*     */     
/*     */     public InputStream openStream() throws IOException {
/* 599 */       return new MultiInputStream(this.sources.iterator());
/*     */     }
/*     */     
/*     */     public boolean isEmpty() throws IOException {
/* 604 */       for (ByteSource source : this.sources) {
/* 605 */         if (!source.isEmpty())
/* 606 */           return false; 
/*     */       } 
/* 609 */       return true;
/*     */     }
/*     */     
/*     */     public long size() throws IOException {
/* 614 */       long result = 0L;
/* 615 */       for (ByteSource source : this.sources)
/* 616 */         result += source.size(); 
/* 618 */       return result;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 623 */       return "ByteSource.concat(" + this.sources + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\io\ByteSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */