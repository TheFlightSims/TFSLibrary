/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public interface Serializer<A> {
/*  67 */   public static final Serializer<String> STRING = new Serializer<String>() {
/*     */       public void serialize(DataOutput out, String value) throws IOException {
/*  70 */         out.writeUTF(value);
/*     */       }
/*     */       
/*     */       public String deserialize(DataInput in, int available) throws IOException {
/*  75 */         return in.readUTF();
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/*  80 */         return -1;
/*     */       }
/*     */     };
/*     */   
/*  92 */   public static final Serializer<String> STRING_INTERN = new Serializer<String>() {
/*     */       public void serialize(DataOutput out, String value) throws IOException {
/*  95 */         out.writeUTF(value);
/*     */       }
/*     */       
/*     */       public String deserialize(DataInput in, int available) throws IOException {
/* 100 */         return in.readUTF().intern();
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 105 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 116 */   public static final Serializer<String> STRING_ASCII = new Serializer<String>() {
/*     */       public void serialize(DataOutput out, String value) throws IOException {
/* 119 */         char[] cc = new char[value.length()];
/* 121 */         value.getChars(0, cc.length, cc, 0);
/* 122 */         DataOutput2.packInt(out, cc.length);
/* 123 */         for (char c : cc)
/* 124 */           out.write(c); 
/*     */       }
/*     */       
/*     */       public String deserialize(DataInput in, int available) throws IOException {
/* 130 */         int size = DataInput2.unpackInt(in);
/* 131 */         char[] cc = new char[size];
/* 132 */         for (int i = 0; i < size; i++)
/* 133 */           cc[i] = (char)in.readUnsignedByte(); 
/* 135 */         return new String(cc);
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 140 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 150 */   public static final Serializer<String> STRING_NOSIZE = new Serializer<String>() {
/* 152 */       private final Charset UTF8_CHARSET = Charset.forName("UTF8");
/*     */       
/*     */       public void serialize(DataOutput out, String value) throws IOException {
/* 156 */         byte[] bytes = value.getBytes(this.UTF8_CHARSET);
/* 157 */         out.write(bytes);
/*     */       }
/*     */       
/*     */       public String deserialize(DataInput in, int available) throws IOException {
/* 163 */         if (available == -1)
/* 163 */           throw new IllegalArgumentException("STRING_NOSIZE does not work with collections."); 
/* 164 */         byte[] bytes = new byte[available];
/* 165 */         in.readFully(bytes);
/* 166 */         return new String(bytes, this.UTF8_CHARSET);
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 171 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 183 */   public static final Serializer<Long> LONG = new Serializer<Long>() {
/*     */       public void serialize(DataOutput out, Long value) throws IOException {
/* 186 */         if (value != null)
/* 187 */           out.writeLong(value.longValue()); 
/*     */       }
/*     */       
/*     */       public Long deserialize(DataInput in, int available) throws IOException {
/* 192 */         if (available == 0)
/* 192 */           return null; 
/* 193 */         return Long.valueOf(in.readLong());
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 198 */         return 8;
/*     */       }
/*     */     };
/*     */   
/* 206 */   public static final Serializer<Integer> INTEGER = new Serializer<Integer>() {
/*     */       public void serialize(DataOutput out, Integer value) throws IOException {
/* 209 */         out.writeInt(value.intValue());
/*     */       }
/*     */       
/*     */       public Integer deserialize(DataInput in, int available) throws IOException {
/* 214 */         return Integer.valueOf(in.readInt());
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 219 */         return 4;
/*     */       }
/*     */     };
/*     */   
/* 225 */   public static final Serializer<Boolean> BOOLEAN = new Serializer<Boolean>() {
/*     */       public void serialize(DataOutput out, Boolean value) throws IOException {
/* 228 */         out.writeBoolean(value.booleanValue());
/*     */       }
/*     */       
/*     */       public Boolean deserialize(DataInput in, int available) throws IOException {
/* 233 */         if (available == 0)
/* 233 */           return null; 
/* 234 */         return Boolean.valueOf(in.readBoolean());
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 239 */         return 1;
/*     */       }
/*     */     };
/*     */   
/* 250 */   public static final Serializer<Object> ILLEGAL_ACCESS = new Serializer<Object>() {
/*     */       public void serialize(DataOutput out, Object value) throws IOException {
/* 253 */         throw new IllegalAccessError();
/*     */       }
/*     */       
/*     */       public Object deserialize(DataInput in, int available) throws IOException {
/* 258 */         throw new IllegalAccessError();
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 263 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 274 */   public static final Serializer<Object> BASIC = new SerializerBase();
/*     */   
/* 280 */   public static final Serializer<byte[]> BYTE_ARRAY = new Serializer<byte[]>() {
/*     */       public void serialize(DataOutput out, byte[] value) throws IOException {
/* 284 */         DataOutput2.packInt(out, value.length);
/* 285 */         out.write(value);
/*     */       }
/*     */       
/*     */       public byte[] deserialize(DataInput in, int available) throws IOException {
/* 290 */         int size = DataInput2.unpackInt(in);
/* 291 */         byte[] ret = new byte[size];
/* 292 */         in.readFully(ret);
/* 293 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 298 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 307 */   public static final Serializer<byte[]> BYTE_ARRAY_NOSIZE = new Serializer<byte[]>() {
/*     */       public void serialize(DataOutput out, byte[] value) throws IOException {
/* 311 */         if (value == null || value.length == 0)
/*     */           return; 
/* 312 */         out.write(value);
/*     */       }
/*     */       
/*     */       public byte[] deserialize(DataInput in, int available) throws IOException {
/* 317 */         if (available == -1)
/* 317 */           throw new IllegalArgumentException("BYTE_ARRAY_NOSIZE does not work with collections."); 
/* 318 */         if (available == 0)
/* 318 */           return null; 
/* 319 */         byte[] ret = new byte[available];
/* 320 */         in.readFully(ret);
/* 321 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 326 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 334 */   public static final Serializer<char[]> CHAR_ARRAY = new Serializer<char[]>() {
/*     */       public void serialize(DataOutput out, char[] value) throws IOException {
/* 338 */         DataOutput2.packInt(out, value.length);
/* 339 */         for (char c : value)
/* 340 */           out.writeChar(c); 
/*     */       }
/*     */       
/*     */       public char[] deserialize(DataInput in, int available) throws IOException {
/* 346 */         int size = DataInput2.unpackInt(in);
/* 347 */         char[] ret = new char[size];
/* 348 */         for (int i = 0; i < size; i++)
/* 349 */           ret[i] = in.readChar(); 
/* 351 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 356 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 365 */   public static final Serializer<int[]> INT_ARRAY = new Serializer<int[]>() {
/*     */       public void serialize(DataOutput out, int[] value) throws IOException {
/* 369 */         DataOutput2.packInt(out, value.length);
/* 370 */         for (int c : value)
/* 371 */           out.writeInt(c); 
/*     */       }
/*     */       
/*     */       public int[] deserialize(DataInput in, int available) throws IOException {
/* 377 */         int size = DataInput2.unpackInt(in);
/* 378 */         int[] ret = new int[size];
/* 379 */         for (int i = 0; i < size; i++)
/* 380 */           ret[i] = in.readInt(); 
/* 382 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 387 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 395 */   public static final Serializer<long[]> LONG_ARRAY = new Serializer<long[]>() {
/*     */       public void serialize(DataOutput out, long[] value) throws IOException {
/* 399 */         DataOutput2.packInt(out, value.length);
/* 400 */         for (long c : value)
/* 401 */           out.writeLong(c); 
/*     */       }
/*     */       
/*     */       public long[] deserialize(DataInput in, int available) throws IOException {
/* 407 */         int size = DataInput2.unpackInt(in);
/* 408 */         long[] ret = new long[size];
/* 409 */         for (int i = 0; i < size; i++)
/* 410 */           ret[i] = in.readLong(); 
/* 412 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 417 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 425 */   public static final Serializer<double[]> DOUBLE_ARRAY = new Serializer<double[]>() {
/*     */       public void serialize(DataOutput out, double[] value) throws IOException {
/* 429 */         DataOutput2.packInt(out, value.length);
/* 430 */         for (double c : value)
/* 431 */           out.writeDouble(c); 
/*     */       }
/*     */       
/*     */       public double[] deserialize(DataInput in, int available) throws IOException {
/* 437 */         int size = DataInput2.unpackInt(in);
/* 438 */         double[] ret = new double[size];
/* 439 */         for (int i = 0; i < size; i++)
/* 440 */           ret[i] = in.readDouble(); 
/* 442 */         return ret;
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 447 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 454 */   public static final Serializer<Object> JAVA = new Serializer<Object>() {
/*     */       public void serialize(DataOutput out, Object value) throws IOException {
/* 457 */         ObjectOutputStream out2 = new ObjectOutputStream((OutputStream)out);
/* 458 */         out2.writeObject(value);
/* 459 */         out2.flush();
/*     */       }
/*     */       
/*     */       public Object deserialize(DataInput in, int available) throws IOException {
/*     */         try {
/* 465 */           ObjectInputStream in2 = new ObjectInputStream((InputStream)in);
/* 466 */           return in2.readObject();
/* 467 */         } catch (ClassNotFoundException e) {
/* 468 */           throw new IOException(e);
/*     */         } 
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 474 */         return -1;
/*     */       }
/*     */     };
/*     */   
/* 480 */   public static final Serializer<UUID> UUID = new Serializer<UUID>() {
/*     */       public void serialize(DataOutput out, UUID value) throws IOException {
/* 483 */         out.writeLong(value.getMostSignificantBits());
/* 484 */         out.writeLong(value.getLeastSignificantBits());
/*     */       }
/*     */       
/*     */       public UUID deserialize(DataInput in, int available) throws IOException {
/* 489 */         return new UUID(in.readLong(), in.readLong());
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/* 494 */         return 16;
/*     */       }
/*     */     };
/*     */   
/*     */   void serialize(DataOutput paramDataOutput, A paramA) throws IOException;
/*     */   
/*     */   A deserialize(DataInput paramDataInput, int paramInt) throws IOException;
/*     */   
/*     */   int fixedSize();
/*     */   
/*     */   public static final class CompressionWrapper<E> implements Serializer<E>, Serializable {
/*     */     private static final long serialVersionUID = 4440826457939614346L;
/*     */     
/*     */     protected final Serializer<E> serializer;
/*     */     
/* 504 */     protected final ThreadLocal<CompressLZF> LZF = new ThreadLocal<CompressLZF>() {
/*     */         protected CompressLZF initialValue() {
/* 506 */           return new CompressLZF();
/*     */         }
/*     */       };
/*     */     
/*     */     public CompressionWrapper(Serializer<E> serializer) {
/* 511 */       this.serializer = serializer;
/*     */     }
/*     */     
/*     */     protected CompressionWrapper(SerializerBase serializerBase, DataInput is, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 516 */       objectStack.add(this);
/* 517 */       this.serializer = (Serializer<E>)serializerBase.deserialize(is, objectStack);
/*     */     }
/*     */     
/*     */     public void serialize(DataOutput out, E value) throws IOException {
/*     */       byte b;
/* 523 */       DataOutput2 out2 = new DataOutput2();
/* 524 */       this.serializer.serialize(out2, value);
/* 526 */       byte[] tmp = new byte[out2.pos + 41];
/*     */       try {
/* 529 */         b = ((CompressLZF)this.LZF.get()).compress(out2.buf, out2.pos, tmp, 0);
/* 530 */       } catch (IndexOutOfBoundsException e) {
/* 531 */         b = 0;
/*     */       } 
/* 533 */       if (b >= out2.pos) {
/* 535 */         DataOutput2.packInt(out, 0);
/* 536 */         out.write(out2.buf, 0, out2.pos);
/*     */         return;
/*     */       } 
/* 540 */       DataOutput2.packInt(out, out2.pos + 1);
/* 541 */       out.write(tmp, 0, b);
/*     */     }
/*     */     
/*     */     public E deserialize(DataInput in, int available) throws IOException {
/* 546 */       int unpackedSize = DataInput2.unpackInt(in) - 1;
/* 547 */       if (unpackedSize == -1)
/* 549 */         return this.serializer.deserialize(in, (available > 0) ? (available - 1) : available); 
/* 552 */       byte[] unpacked = new byte[unpackedSize];
/* 553 */       ((CompressLZF)this.LZF.get()).expand(in, unpacked, 0, unpackedSize);
/* 554 */       DataInput2 in2 = new DataInput2(unpacked);
/* 555 */       E ret = this.serializer.deserialize(in2, unpackedSize);
/* 556 */       assert in2.pos == unpackedSize : "data were not fully read";
/* 557 */       return ret;
/*     */     }
/*     */     
/*     */     public boolean equals(Object o) {
/* 562 */       if (this == o)
/* 562 */         return true; 
/* 563 */       if (o == null || getClass() != o.getClass())
/* 563 */         return false; 
/* 565 */       CompressionWrapper that = (CompressionWrapper)o;
/* 566 */       return this.serializer.equals(that.serializer);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 571 */       return this.serializer.hashCode();
/*     */     }
/*     */     
/*     */     public int fixedSize() {
/* 576 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\Serializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */