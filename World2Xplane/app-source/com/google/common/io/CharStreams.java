/*     */ package com.google.common.io;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.io.Closeable;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.Writer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ @Beta
/*     */ public final class CharStreams {
/*     */   private static final int BUF_SIZE = 2048;
/*     */   
/*     */   @Deprecated
/*     */   public static InputSupplier<StringReader> newReaderSupplier(String value) {
/*  76 */     return asInputSupplier(CharSource.wrap(value));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static InputSupplier<InputStreamReader> newReaderSupplier(InputSupplier<? extends InputStream> in, Charset charset) {
/*  93 */     return asInputSupplier(ByteStreams.asByteSource(in).asCharSource(charset));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static OutputSupplier<OutputStreamWriter> newWriterSupplier(OutputSupplier<? extends OutputStream> out, Charset charset) {
/* 111 */     return asOutputSupplier(ByteStreams.asByteSink(out).asCharSink(charset));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <W extends Appendable & Closeable> void write(CharSequence from, OutputSupplier<W> to) throws IOException {
/* 128 */     asCharSink(to).write(from);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable, W extends Appendable & Closeable> long copy(InputSupplier<R> from, OutputSupplier<W> to) throws IOException {
/* 147 */     return asCharSource(from).copyTo(asCharSink(to));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable> long copy(InputSupplier<R> from, Appendable to) throws IOException {
/* 165 */     return asCharSource(from).copyTo(to);
/*     */   }
/*     */   
/*     */   public static long copy(Readable from, Appendable to) throws IOException {
/* 178 */     Preconditions.checkNotNull(from);
/* 179 */     Preconditions.checkNotNull(to);
/* 180 */     CharBuffer buf = CharBuffer.allocate(2048);
/* 181 */     long total = 0L;
/* 182 */     while (from.read(buf) != -1) {
/* 183 */       buf.flip();
/* 184 */       to.append(buf);
/* 185 */       total += buf.remaining();
/* 186 */       buf.clear();
/*     */     } 
/* 188 */     return total;
/*     */   }
/*     */   
/*     */   public static String toString(Readable r) throws IOException {
/* 200 */     return toStringBuilder(r).toString();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable> String toString(InputSupplier<R> supplier) throws IOException {
/* 216 */     return asCharSource(supplier).read();
/*     */   }
/*     */   
/*     */   private static StringBuilder toStringBuilder(Readable r) throws IOException {
/* 228 */     StringBuilder sb = new StringBuilder();
/* 229 */     copy(r, sb);
/* 230 */     return sb;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable> String readFirstLine(InputSupplier<R> supplier) throws IOException {
/* 247 */     return asCharSource(supplier).readFirstLine();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable> List<String> readLines(InputSupplier<R> supplier) throws IOException {
/* 265 */     Closer closer = Closer.create();
/*     */     try {
/* 267 */       Readable readable = (Readable)closer.register((Closeable)supplier.getInput());
/* 268 */       return readLines(readable);
/* 269 */     } catch (Throwable e) {
/* 270 */       throw closer.rethrow(e);
/*     */     } finally {
/* 272 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<String> readLines(Readable r) throws IOException {
/* 290 */     List<String> result = new ArrayList<String>();
/* 291 */     LineReader lineReader = new LineReader(r);
/*     */     String line;
/* 293 */     while ((line = lineReader.readLine()) != null)
/* 294 */       result.add(line); 
/* 296 */     return result;
/*     */   }
/*     */   
/*     */   public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
/* 311 */     Preconditions.checkNotNull(readable);
/* 312 */     Preconditions.checkNotNull(processor);
/* 314 */     LineReader lineReader = new LineReader(readable);
/*     */     String line;
/*     */     do {
/*     */     
/* 316 */     } while ((line = lineReader.readLine()) != null && 
/* 317 */       processor.processLine(line));
/* 321 */     return processor.getResult();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static <R extends Readable & Closeable, T> T readLines(InputSupplier<R> supplier, LineProcessor<T> callback) throws IOException {
/* 339 */     Preconditions.checkNotNull(supplier);
/* 340 */     Preconditions.checkNotNull(callback);
/* 342 */     Closer closer = Closer.create();
/*     */     try {
/* 344 */       Readable readable = (Readable)closer.register((Closeable)supplier.getInput());
/* 345 */       return (T)readLines(readable, (LineProcessor)callback);
/* 346 */     } catch (Throwable e) {
/* 347 */       throw closer.rethrow(e);
/*     */     } finally {
/* 349 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static InputSupplier<Reader> join(Iterable<? extends InputSupplier<? extends Reader>> suppliers) {
/* 373 */     Preconditions.checkNotNull(suppliers);
/* 374 */     Iterable<CharSource> sources = Iterables.transform(suppliers, new Function<InputSupplier<? extends Reader>, CharSource>() {
/*     */           public CharSource apply(InputSupplier<? extends Reader> input) {
/* 378 */             return CharStreams.asCharSource((InputSupplier)input);
/*     */           }
/*     */         });
/* 381 */     return asInputSupplier(CharSource.concat(sources));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static InputSupplier<Reader> join(InputSupplier<? extends Reader>... suppliers) {
/* 394 */     return join(Arrays.asList(suppliers));
/*     */   }
/*     */   
/*     */   public static void skipFully(Reader reader, long n) throws IOException {
/* 409 */     Preconditions.checkNotNull(reader);
/* 410 */     while (n > 0L) {
/* 411 */       long amt = reader.skip(n);
/* 412 */       if (amt == 0L) {
/* 414 */         if (reader.read() == -1)
/* 415 */           throw new EOFException(); 
/* 417 */         n--;
/*     */         continue;
/*     */       } 
/* 419 */       n -= amt;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Writer nullWriter() {
/* 430 */     return NullWriter.INSTANCE;
/*     */   }
/*     */   
/*     */   private static final class NullWriter extends Writer {
/* 435 */     private static final NullWriter INSTANCE = new NullWriter();
/*     */     
/*     */     public void write(int c) {}
/*     */     
/*     */     public void write(char[] cbuf) {
/* 443 */       Preconditions.checkNotNull(cbuf);
/*     */     }
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) {
/* 448 */       Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
/*     */     }
/*     */     
/*     */     public void write(String str) {
/* 453 */       Preconditions.checkNotNull(str);
/*     */     }
/*     */     
/*     */     public void write(String str, int off, int len) {
/* 458 */       Preconditions.checkPositionIndexes(off, off + len, str.length());
/*     */     }
/*     */     
/*     */     public Writer append(CharSequence csq) {
/* 463 */       Preconditions.checkNotNull(csq);
/* 464 */       return this;
/*     */     }
/*     */     
/*     */     public Writer append(CharSequence csq, int start, int end) {
/* 469 */       Preconditions.checkPositionIndexes(start, end, csq.length());
/* 470 */       return this;
/*     */     }
/*     */     
/*     */     public Writer append(char c) {
/* 475 */       return this;
/*     */     }
/*     */     
/*     */     public void flush() {}
/*     */     
/*     */     public void close() {}
/*     */     
/*     */     public String toString() {
/* 488 */       return "CharStreams.nullWriter()";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Writer asWriter(Appendable target) {
/* 503 */     if (target instanceof Writer)
/* 504 */       return (Writer)target; 
/* 506 */     return new AppendableWriter(target);
/*     */   }
/*     */   
/*     */   static Reader asReader(final Readable readable) {
/* 512 */     Preconditions.checkNotNull(readable);
/* 513 */     if (readable instanceof Reader)
/* 514 */       return (Reader)readable; 
/* 516 */     return new Reader() {
/*     */         public int read(char[] cbuf, int off, int len) throws IOException {
/* 519 */           return read(CharBuffer.wrap(cbuf, off, len));
/*     */         }
/*     */         
/*     */         public int read(CharBuffer target) throws IOException {
/* 524 */           return readable.read(target);
/*     */         }
/*     */         
/*     */         public void close() throws IOException {
/* 529 */           if (readable instanceof Closeable)
/* 530 */             ((Closeable)readable).close(); 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CharSource asCharSource(final InputSupplier<? extends Readable> supplier) {
/* 552 */     Preconditions.checkNotNull(supplier);
/* 553 */     return new CharSource() {
/*     */         public Reader openStream() throws IOException {
/* 556 */           return CharStreams.asReader(supplier.getInput());
/*     */         }
/*     */         
/*     */         public String toString() {
/* 561 */           return "CharStreams.asCharSource(" + supplier + ")";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CharSink asCharSink(final OutputSupplier<? extends Appendable> supplier) {
/* 582 */     Preconditions.checkNotNull(supplier);
/* 583 */     return new CharSink() {
/*     */         public Writer openStream() throws IOException {
/* 586 */           return CharStreams.asWriter(supplier.getOutput());
/*     */         }
/*     */         
/*     */         public String toString() {
/* 591 */           return "CharStreams.asCharSink(" + supplier + ")";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   static <R extends Reader> InputSupplier<R> asInputSupplier(CharSource source) {
/* 599 */     return (InputSupplier<R>)Preconditions.checkNotNull(source);
/*     */   }
/*     */   
/*     */   static <W extends Writer> OutputSupplier<W> asOutputSupplier(CharSink sink) {
/* 605 */     return (OutputSupplier<W>)Preconditions.checkNotNull(sink);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\io\CharStreams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */