/*      */ package com.google.common.io;
/*      */ 
/*      */ import com.google.common.annotations.Beta;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.Iterables;
/*      */ import com.google.common.hash.HashCode;
/*      */ import com.google.common.hash.HashFunction;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.Closeable;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutput;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.FilterInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.channels.ReadableByteChannel;
/*      */ import java.nio.channels.WritableByteChannel;
/*      */ import java.util.Arrays;
/*      */ 
/*      */ @Beta
/*      */ public final class ByteStreams {
/*      */   private static final int BUF_SIZE = 4096;
/*      */   
/*      */   @Deprecated
/*      */   public static InputSupplier<ByteArrayInputStream> newInputStreamSupplier(byte[] b) {
/*   70 */     return asInputSupplier(ByteSource.wrap(b));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static InputSupplier<ByteArrayInputStream> newInputStreamSupplier(byte[] b, int off, int len) {
/*   87 */     return asInputSupplier(ByteSource.wrap(b).slice(off, len));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static void write(byte[] from, OutputSupplier<? extends OutputStream> to) throws IOException {
/*  102 */     asByteSink(to).write(from);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static long copy(InputSupplier<? extends InputStream> from, OutputSupplier<? extends OutputStream> to) throws IOException {
/*  119 */     return asByteSource(from).copyTo(asByteSink(to));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static long copy(InputSupplier<? extends InputStream> from, OutputStream to) throws IOException {
/*  137 */     return asByteSource(from).copyTo(to);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static long copy(InputStream from, OutputSupplier<? extends OutputStream> to) throws IOException {
/*  156 */     return asByteSink(to).writeFrom(from);
/*      */   }
/*      */   
/*      */   public static long copy(InputStream from, OutputStream to) throws IOException {
/*  170 */     Preconditions.checkNotNull(from);
/*  171 */     Preconditions.checkNotNull(to);
/*  172 */     byte[] buf = new byte[4096];
/*  173 */     long total = 0L;
/*      */     while (true) {
/*  175 */       int r = from.read(buf);
/*  176 */       if (r == -1)
/*      */         break; 
/*  179 */       to.write(buf, 0, r);
/*  180 */       total += r;
/*      */     } 
/*  182 */     return total;
/*      */   }
/*      */   
/*      */   public static long copy(ReadableByteChannel from, WritableByteChannel to) throws IOException {
/*  196 */     Preconditions.checkNotNull(from);
/*  197 */     Preconditions.checkNotNull(to);
/*  198 */     ByteBuffer buf = ByteBuffer.allocate(4096);
/*  199 */     long total = 0L;
/*  200 */     while (from.read(buf) != -1) {
/*  201 */       buf.flip();
/*  202 */       while (buf.hasRemaining())
/*  203 */         total += to.write(buf); 
/*  205 */       buf.clear();
/*      */     } 
/*  207 */     return total;
/*      */   }
/*      */   
/*      */   public static byte[] toByteArray(InputStream in) throws IOException {
/*  219 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*  220 */     copy(in, out);
/*  221 */     return out.toByteArray();
/*      */   }
/*      */   
/*      */   static byte[] toByteArray(InputStream in, int expectedSize) throws IOException {
/*  232 */     byte[] bytes = new byte[expectedSize];
/*  233 */     int remaining = expectedSize;
/*  235 */     while (remaining > 0) {
/*  236 */       int off = expectedSize - remaining;
/*  237 */       int read = in.read(bytes, off, remaining);
/*  238 */       if (read == -1)
/*  241 */         return Arrays.copyOf(bytes, off); 
/*  243 */       remaining -= read;
/*      */     } 
/*  247 */     int b = in.read();
/*  248 */     if (b == -1)
/*  249 */       return bytes; 
/*  253 */     FastByteArrayOutputStream out = new FastByteArrayOutputStream();
/*  254 */     out.write(b);
/*  255 */     copy(in, out);
/*  257 */     byte[] result = new byte[bytes.length + out.size()];
/*  258 */     System.arraycopy(bytes, 0, result, 0, bytes.length);
/*  259 */     out.writeTo(result, bytes.length);
/*  260 */     return result;
/*      */   }
/*      */   
/*      */   private static final class FastByteArrayOutputStream extends ByteArrayOutputStream {
/*      */     private FastByteArrayOutputStream() {}
/*      */     
/*      */     void writeTo(byte[] b, int off) {
/*  273 */       System.arraycopy(this.buf, 0, b, off, this.count);
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static byte[] toByteArray(InputSupplier<? extends InputStream> supplier) throws IOException {
/*  288 */     return asByteSource(supplier).read();
/*      */   }
/*      */   
/*      */   public static ByteArrayDataInput newDataInput(byte[] bytes) {
/*  296 */     return newDataInput(new ByteArrayInputStream(bytes));
/*      */   }
/*      */   
/*      */   public static ByteArrayDataInput newDataInput(byte[] bytes, int start) {
/*  307 */     Preconditions.checkPositionIndex(start, bytes.length);
/*  308 */     return newDataInput(new ByteArrayInputStream(bytes, start, bytes.length - start));
/*      */   }
/*      */   
/*      */   public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
/*  321 */     return new ByteArrayDataInputStream((ByteArrayInputStream)Preconditions.checkNotNull(byteArrayInputStream));
/*      */   }
/*      */   
/*      */   private static class ByteArrayDataInputStream implements ByteArrayDataInput {
/*      */     final DataInput input;
/*      */     
/*      */     ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
/*  328 */       this.input = new DataInputStream(byteArrayInputStream);
/*      */     }
/*      */     
/*      */     public void readFully(byte[] b) {
/*      */       try {
/*  333 */         this.input.readFully(b);
/*  334 */       } catch (IOException e) {
/*  335 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void readFully(byte[] b, int off, int len) {
/*      */       try {
/*  341 */         this.input.readFully(b, off, len);
/*  342 */       } catch (IOException e) {
/*  343 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int skipBytes(int n) {
/*      */       try {
/*  349 */         return this.input.skipBytes(n);
/*  350 */       } catch (IOException e) {
/*  351 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean readBoolean() {
/*      */       try {
/*  357 */         return this.input.readBoolean();
/*  358 */       } catch (IOException e) {
/*  359 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public byte readByte() {
/*      */       try {
/*  365 */         return this.input.readByte();
/*  366 */       } catch (EOFException e) {
/*  367 */         throw new IllegalStateException(e);
/*  368 */       } catch (IOException impossible) {
/*  369 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int readUnsignedByte() {
/*      */       try {
/*  375 */         return this.input.readUnsignedByte();
/*  376 */       } catch (IOException e) {
/*  377 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public short readShort() {
/*      */       try {
/*  383 */         return this.input.readShort();
/*  384 */       } catch (IOException e) {
/*  385 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int readUnsignedShort() {
/*      */       try {
/*  391 */         return this.input.readUnsignedShort();
/*  392 */       } catch (IOException e) {
/*  393 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public char readChar() {
/*      */       try {
/*  399 */         return this.input.readChar();
/*  400 */       } catch (IOException e) {
/*  401 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public int readInt() {
/*      */       try {
/*  407 */         return this.input.readInt();
/*  408 */       } catch (IOException e) {
/*  409 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public long readLong() {
/*      */       try {
/*  415 */         return this.input.readLong();
/*  416 */       } catch (IOException e) {
/*  417 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public float readFloat() {
/*      */       try {
/*  423 */         return this.input.readFloat();
/*  424 */       } catch (IOException e) {
/*  425 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public double readDouble() {
/*      */       try {
/*  431 */         return this.input.readDouble();
/*  432 */       } catch (IOException e) {
/*  433 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String readLine() {
/*      */       try {
/*  439 */         return this.input.readLine();
/*  440 */       } catch (IOException e) {
/*  441 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */     
/*      */     public String readUTF() {
/*      */       try {
/*  447 */         return this.input.readUTF();
/*  448 */       } catch (IOException e) {
/*  449 */         throw new IllegalStateException(e);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static ByteArrayDataOutput newDataOutput() {
/*  458 */     return newDataOutput(new ByteArrayOutputStream());
/*      */   }
/*      */   
/*      */   public static ByteArrayDataOutput newDataOutput(int size) {
/*  468 */     Preconditions.checkArgument((size >= 0), "Invalid size: %s", new Object[] { Integer.valueOf(size) });
/*  469 */     return newDataOutput(new ByteArrayOutputStream(size));
/*      */   }
/*      */   
/*      */   public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputSteam) {
/*  488 */     return new ByteArrayDataOutputStream((ByteArrayOutputStream)Preconditions.checkNotNull(byteArrayOutputSteam));
/*      */   }
/*      */   
/*      */   private static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
/*      */     final DataOutput output;
/*      */     
/*      */     final ByteArrayOutputStream byteArrayOutputSteam;
/*      */     
/*      */     ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputSteam) {
/*  499 */       this.byteArrayOutputSteam = byteArrayOutputSteam;
/*  500 */       this.output = new DataOutputStream(byteArrayOutputSteam);
/*      */     }
/*      */     
/*      */     public void write(int b) {
/*      */       try {
/*  505 */         this.output.write(b);
/*  506 */       } catch (IOException impossible) {
/*  507 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void write(byte[] b) {
/*      */       try {
/*  513 */         this.output.write(b);
/*  514 */       } catch (IOException impossible) {
/*  515 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void write(byte[] b, int off, int len) {
/*      */       try {
/*  521 */         this.output.write(b, off, len);
/*  522 */       } catch (IOException impossible) {
/*  523 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeBoolean(boolean v) {
/*      */       try {
/*  529 */         this.output.writeBoolean(v);
/*  530 */       } catch (IOException impossible) {
/*  531 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeByte(int v) {
/*      */       try {
/*  537 */         this.output.writeByte(v);
/*  538 */       } catch (IOException impossible) {
/*  539 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeBytes(String s) {
/*      */       try {
/*  545 */         this.output.writeBytes(s);
/*  546 */       } catch (IOException impossible) {
/*  547 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeChar(int v) {
/*      */       try {
/*  553 */         this.output.writeChar(v);
/*  554 */       } catch (IOException impossible) {
/*  555 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeChars(String s) {
/*      */       try {
/*  561 */         this.output.writeChars(s);
/*  562 */       } catch (IOException impossible) {
/*  563 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeDouble(double v) {
/*      */       try {
/*  569 */         this.output.writeDouble(v);
/*  570 */       } catch (IOException impossible) {
/*  571 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeFloat(float v) {
/*      */       try {
/*  577 */         this.output.writeFloat(v);
/*  578 */       } catch (IOException impossible) {
/*  579 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeInt(int v) {
/*      */       try {
/*  585 */         this.output.writeInt(v);
/*  586 */       } catch (IOException impossible) {
/*  587 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeLong(long v) {
/*      */       try {
/*  593 */         this.output.writeLong(v);
/*  594 */       } catch (IOException impossible) {
/*  595 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeShort(int v) {
/*      */       try {
/*  601 */         this.output.writeShort(v);
/*  602 */       } catch (IOException impossible) {
/*  603 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void writeUTF(String s) {
/*      */       try {
/*  609 */         this.output.writeUTF(s);
/*  610 */       } catch (IOException impossible) {
/*  611 */         throw new AssertionError(impossible);
/*      */       } 
/*      */     }
/*      */     
/*      */     public byte[] toByteArray() {
/*  616 */       return this.byteArrayOutputSteam.toByteArray();
/*      */     }
/*      */   }
/*      */   
/*  620 */   private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {
/*      */       public void write(int b) {}
/*      */       
/*      */       public void write(byte[] b) {
/*  627 */         Preconditions.checkNotNull(b);
/*      */       }
/*      */       
/*      */       public void write(byte[] b, int off, int len) {
/*  631 */         Preconditions.checkNotNull(b);
/*      */       }
/*      */       
/*      */       public String toString() {
/*  636 */         return "ByteStreams.nullOutputStream()";
/*      */       }
/*      */     };
/*      */   
/*      */   public static OutputStream nullOutputStream() {
/*  646 */     return NULL_OUTPUT_STREAM;
/*      */   }
/*      */   
/*      */   public static InputStream limit(InputStream in, long limit) {
/*  659 */     return new LimitedInputStream(in, limit);
/*      */   }
/*      */   
/*      */   private static final class LimitedInputStream extends FilterInputStream {
/*      */     private long left;
/*      */     
/*  665 */     private long mark = -1L;
/*      */     
/*      */     LimitedInputStream(InputStream in, long limit) {
/*  668 */       super(in);
/*  669 */       Preconditions.checkNotNull(in);
/*  670 */       Preconditions.checkArgument((limit >= 0L), "limit must be non-negative");
/*  671 */       this.left = limit;
/*      */     }
/*      */     
/*      */     public int available() throws IOException {
/*  675 */       return (int)Math.min(this.in.available(), this.left);
/*      */     }
/*      */     
/*      */     public synchronized void mark(int readLimit) {
/*  680 */       this.in.mark(readLimit);
/*  681 */       this.mark = this.left;
/*      */     }
/*      */     
/*      */     public int read() throws IOException {
/*  685 */       if (this.left == 0L)
/*  686 */         return -1; 
/*  689 */       int result = this.in.read();
/*  690 */       if (result != -1)
/*  691 */         this.left--; 
/*  693 */       return result;
/*      */     }
/*      */     
/*      */     public int read(byte[] b, int off, int len) throws IOException {
/*  697 */       if (this.left == 0L)
/*  698 */         return -1; 
/*  701 */       len = (int)Math.min(len, this.left);
/*  702 */       int result = this.in.read(b, off, len);
/*  703 */       if (result != -1)
/*  704 */         this.left -= result; 
/*  706 */       return result;
/*      */     }
/*      */     
/*      */     public synchronized void reset() throws IOException {
/*  710 */       if (!this.in.markSupported())
/*  711 */         throw new IOException("Mark not supported"); 
/*  713 */       if (this.mark == -1L)
/*  714 */         throw new IOException("Mark not set"); 
/*  717 */       this.in.reset();
/*  718 */       this.left = this.mark;
/*      */     }
/*      */     
/*      */     public long skip(long n) throws IOException {
/*  722 */       n = Math.min(n, this.left);
/*  723 */       long skipped = this.in.skip(n);
/*  724 */       this.left -= skipped;
/*  725 */       return skipped;
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static long length(InputSupplier<? extends InputStream> supplier) throws IOException {
/*  738 */     return asByteSource(supplier).size();
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static boolean equal(InputSupplier<? extends InputStream> supplier1, InputSupplier<? extends InputStream> supplier2) throws IOException {
/*  751 */     return asByteSource(supplier1).contentEquals(asByteSource(supplier2));
/*      */   }
/*      */   
/*      */   public static void readFully(InputStream in, byte[] b) throws IOException {
/*  766 */     readFully(in, b, 0, b.length);
/*      */   }
/*      */   
/*      */   public static void readFully(InputStream in, byte[] b, int off, int len) throws IOException {
/*  785 */     int read = read(in, b, off, len);
/*  786 */     if (read != len)
/*  787 */       throw new EOFException("reached end of stream after reading " + read + " bytes; " + len + " bytes expected"); 
/*      */   }
/*      */   
/*      */   public static void skipFully(InputStream in, long n) throws IOException {
/*  805 */     long toSkip = n;
/*  806 */     while (n > 0L) {
/*  807 */       long amt = in.skip(n);
/*  808 */       if (amt == 0L) {
/*  810 */         if (in.read() == -1) {
/*  811 */           long skipped = toSkip - n;
/*  812 */           throw new EOFException("reached end of stream after skipping " + skipped + " bytes; " + toSkip + " bytes expected");
/*      */         } 
/*  815 */         n--;
/*      */         continue;
/*      */       } 
/*  817 */       n -= amt;
/*      */     } 
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static <T> T readBytes(InputSupplier<? extends InputStream> supplier, ByteProcessor<T> processor) throws IOException {
/*  836 */     Preconditions.checkNotNull(supplier);
/*  837 */     Preconditions.checkNotNull(processor);
/*  839 */     Closer closer = Closer.create();
/*      */     try {
/*  841 */       InputStream in = (InputStream)closer.<Closeable>register(supplier.getInput());
/*  842 */       return (T)readBytes(in, (ByteProcessor)processor);
/*  843 */     } catch (Throwable e) {
/*  844 */       throw closer.rethrow(e);
/*      */     } finally {
/*  846 */       closer.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static <T> T readBytes(InputStream input, ByteProcessor<T> processor) throws IOException {
/*      */     int read;
/*  861 */     Preconditions.checkNotNull(input);
/*  862 */     Preconditions.checkNotNull(processor);
/*  864 */     byte[] buf = new byte[4096];
/*      */     do {
/*  867 */       read = input.read(buf);
/*  868 */     } while (read != -1 && processor.processBytes(buf, 0, read));
/*  869 */     return processor.getResult();
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static HashCode hash(InputSupplier<? extends InputStream> supplier, HashFunction hashFunction) throws IOException {
/*  888 */     return asByteSource(supplier).hash(hashFunction);
/*      */   }
/*      */   
/*      */   public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
/*  917 */     Preconditions.checkNotNull(in);
/*  918 */     Preconditions.checkNotNull(b);
/*  919 */     if (len < 0)
/*  920 */       throw new IndexOutOfBoundsException("len is negative"); 
/*  922 */     int total = 0;
/*  923 */     while (total < len) {
/*  924 */       int result = in.read(b, off + total, len - total);
/*  925 */       if (result == -1)
/*      */         break; 
/*  928 */       total += result;
/*      */     } 
/*  930 */     return total;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static InputSupplier<InputStream> slice(InputSupplier<? extends InputStream> supplier, long offset, long length) {
/*  951 */     return asInputSupplier(asByteSource(supplier).slice(offset, length));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static InputSupplier<InputStream> join(Iterable<? extends InputSupplier<? extends InputStream>> suppliers) {
/*  974 */     Preconditions.checkNotNull(suppliers);
/*  975 */     Iterable<ByteSource> sources = Iterables.transform(suppliers, new Function<InputSupplier<? extends InputStream>, ByteSource>() {
/*      */           public ByteSource apply(InputSupplier<? extends InputStream> input) {
/*  979 */             return ByteStreams.asByteSource(input);
/*      */           }
/*      */         });
/*  982 */     return asInputSupplier(ByteSource.concat(sources));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static InputSupplier<InputStream> join(InputSupplier<? extends InputStream>... suppliers) {
/*  995 */     return join(Arrays.asList(suppliers));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static ByteSource asByteSource(final InputSupplier<? extends InputStream> supplier) {
/* 1016 */     Preconditions.checkNotNull(supplier);
/* 1017 */     return new ByteSource() {
/*      */         public InputStream openStream() throws IOException {
/* 1020 */           return supplier.getInput();
/*      */         }
/*      */         
/*      */         public String toString() {
/* 1025 */           return "ByteStreams.asByteSource(" + supplier + ")";
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static ByteSink asByteSink(final OutputSupplier<? extends OutputStream> supplier) {
/* 1046 */     Preconditions.checkNotNull(supplier);
/* 1047 */     return new ByteSink() {
/*      */         public OutputStream openStream() throws IOException {
/* 1050 */           return supplier.getOutput();
/*      */         }
/*      */         
/*      */         public String toString() {
/* 1055 */           return "ByteStreams.asByteSink(" + supplier + ")";
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   static <S extends InputStream> InputSupplier<S> asInputSupplier(ByteSource source) {
/* 1063 */     return (InputSupplier<S>)Preconditions.checkNotNull(source);
/*      */   }
/*      */   
/*      */   static <S extends OutputStream> OutputSupplier<S> asOutputSupplier(ByteSink sink) {
/* 1069 */     return (OutputSupplier<S>)Preconditions.checkNotNull(sink);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\io\ByteStreams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */