/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class CodedInputStream {
/*     */   private final byte[] buffer;
/*     */   
/*     */   private int bufferSize;
/*     */   
/*     */   private int bufferSizeAfterLimit;
/*     */   
/*     */   private int bufferPos;
/*     */   
/*     */   private final InputStream input;
/*     */   
/*     */   private int lastTag;
/*     */   
/*     */   private int totalBytesRetired;
/*     */   
/*     */   public static CodedInputStream newInstance(InputStream input) {
/*  55 */     return new CodedInputStream(input);
/*     */   }
/*     */   
/*     */   public static CodedInputStream newInstance(byte[] buf) {
/*  62 */     return newInstance(buf, 0, buf.length);
/*     */   }
/*     */   
/*     */   public static CodedInputStream newInstance(byte[] buf, int off, int len) {
/*  70 */     CodedInputStream result = new CodedInputStream(buf, off, len);
/*     */     try {
/*  77 */       result.pushLimit(len);
/*  78 */     } catch (InvalidProtocolBufferException ex) {
/*  86 */       throw new IllegalArgumentException(ex);
/*     */     } 
/*  88 */     return result;
/*     */   }
/*     */   
/*     */   public int readTag() throws IOException {
/*  99 */     if (isAtEnd()) {
/* 100 */       this.lastTag = 0;
/* 101 */       return 0;
/*     */     } 
/* 104 */     this.lastTag = readRawVarint32();
/* 105 */     if (WireFormat.getTagFieldNumber(this.lastTag) == 0)
/* 108 */       throw InvalidProtocolBufferException.invalidTag(); 
/* 110 */     return this.lastTag;
/*     */   }
/*     */   
/*     */   public void checkLastTagWas(int value) throws InvalidProtocolBufferException {
/* 123 */     if (this.lastTag != value)
/* 124 */       throw InvalidProtocolBufferException.invalidEndTag(); 
/*     */   }
/*     */   
/*     */   public boolean skipField(int tag) throws IOException {
/* 135 */     switch (WireFormat.getTagWireType(tag)) {
/*     */       case 0:
/* 137 */         readInt32();
/* 138 */         return true;
/*     */       case 1:
/* 140 */         readRawLittleEndian64();
/* 141 */         return true;
/*     */       case 2:
/* 143 */         skipRawBytes(readRawVarint32());
/* 144 */         return true;
/*     */       case 3:
/* 146 */         skipMessage();
/* 147 */         checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(tag), 4));
/* 150 */         return true;
/*     */       case 4:
/* 152 */         return false;
/*     */       case 5:
/* 154 */         readRawLittleEndian32();
/* 155 */         return true;
/*     */     } 
/* 157 */     throw InvalidProtocolBufferException.invalidWireType();
/*     */   }
/*     */   
/*     */   public void skipMessage() throws IOException {
/*     */     int tag;
/*     */     do {
/* 167 */       tag = readTag();
/* 168 */     } while (tag != 0 && skipField(tag));
/*     */   }
/*     */   
/*     */   public double readDouble() throws IOException {
/* 178 */     return Double.longBitsToDouble(readRawLittleEndian64());
/*     */   }
/*     */   
/*     */   public float readFloat() throws IOException {
/* 183 */     return Float.intBitsToFloat(readRawLittleEndian32());
/*     */   }
/*     */   
/*     */   public long readUInt64() throws IOException {
/* 188 */     return readRawVarint64();
/*     */   }
/*     */   
/*     */   public long readInt64() throws IOException {
/* 193 */     return readRawVarint64();
/*     */   }
/*     */   
/*     */   public int readInt32() throws IOException {
/* 198 */     return readRawVarint32();
/*     */   }
/*     */   
/*     */   public long readFixed64() throws IOException {
/* 203 */     return readRawLittleEndian64();
/*     */   }
/*     */   
/*     */   public int readFixed32() throws IOException {
/* 208 */     return readRawLittleEndian32();
/*     */   }
/*     */   
/*     */   public boolean readBool() throws IOException {
/* 213 */     return (readRawVarint32() != 0);
/*     */   }
/*     */   
/*     */   public String readString() throws IOException {
/* 218 */     int size = readRawVarint32();
/* 219 */     if (size <= this.bufferSize - this.bufferPos && size > 0) {
/* 222 */       String result = new String(this.buffer, this.bufferPos, size, "UTF-8");
/* 223 */       this.bufferPos += size;
/* 224 */       return result;
/*     */     } 
/* 227 */     return new String(readRawBytes(size), "UTF-8");
/*     */   }
/*     */   
/*     */   public void readGroup(int fieldNumber, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 236 */     if (this.recursionDepth >= this.recursionLimit)
/* 237 */       throw InvalidProtocolBufferException.recursionLimitExceeded(); 
/* 239 */     this.recursionDepth++;
/* 240 */     builder.mergeFrom(this, extensionRegistry);
/* 241 */     checkLastTagWas(WireFormat.makeTag(fieldNumber, 4));
/* 243 */     this.recursionDepth--;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void readUnknownGroup(int fieldNumber, MessageLite.Builder builder) throws IOException {
/* 262 */     readGroup(fieldNumber, builder, null);
/*     */   }
/*     */   
/*     */   public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 269 */     int length = readRawVarint32();
/* 270 */     if (this.recursionDepth >= this.recursionLimit)
/* 271 */       throw InvalidProtocolBufferException.recursionLimitExceeded(); 
/* 273 */     int oldLimit = pushLimit(length);
/* 274 */     this.recursionDepth++;
/* 275 */     builder.mergeFrom(this, extensionRegistry);
/* 276 */     checkLastTagWas(0);
/* 277 */     this.recursionDepth--;
/* 278 */     popLimit(oldLimit);
/*     */   }
/*     */   
/*     */   public ByteString readBytes() throws IOException {
/* 283 */     int size = readRawVarint32();
/* 284 */     if (size == 0)
/* 285 */       return ByteString.EMPTY; 
/* 286 */     if (size <= this.bufferSize - this.bufferPos && size > 0) {
/* 289 */       ByteString result = ByteString.copyFrom(this.buffer, this.bufferPos, size);
/* 290 */       this.bufferPos += size;
/* 291 */       return result;
/*     */     } 
/* 294 */     return ByteString.copyFrom(readRawBytes(size));
/*     */   }
/*     */   
/*     */   public int readUInt32() throws IOException {
/* 300 */     return readRawVarint32();
/*     */   }
/*     */   
/*     */   public int readEnum() throws IOException {
/* 308 */     return readRawVarint32();
/*     */   }
/*     */   
/*     */   public int readSFixed32() throws IOException {
/* 313 */     return readRawLittleEndian32();
/*     */   }
/*     */   
/*     */   public long readSFixed64() throws IOException {
/* 318 */     return readRawLittleEndian64();
/*     */   }
/*     */   
/*     */   public int readSInt32() throws IOException {
/* 323 */     return decodeZigZag32(readRawVarint32());
/*     */   }
/*     */   
/*     */   public long readSInt64() throws IOException {
/* 328 */     return decodeZigZag64(readRawVarint64());
/*     */   }
/*     */   
/*     */   public int readRawVarint32() throws IOException {
/* 338 */     byte tmp = readRawByte();
/* 339 */     if (tmp >= 0)
/* 340 */       return tmp; 
/* 342 */     int result = tmp & Byte.MAX_VALUE;
/* 343 */     if ((tmp = readRawByte()) >= 0) {
/* 344 */       result |= tmp << 7;
/*     */     } else {
/* 346 */       result |= (tmp & Byte.MAX_VALUE) << 7;
/* 347 */       if ((tmp = readRawByte()) >= 0) {
/* 348 */         result |= tmp << 14;
/*     */       } else {
/* 350 */         result |= (tmp & Byte.MAX_VALUE) << 14;
/* 351 */         if ((tmp = readRawByte()) >= 0) {
/* 352 */           result |= tmp << 21;
/*     */         } else {
/* 354 */           result |= (tmp & Byte.MAX_VALUE) << 21;
/* 355 */           result |= (tmp = readRawByte()) << 28;
/* 356 */           if (tmp < 0) {
/* 358 */             for (int i = 0; i < 5; i++) {
/* 359 */               if (readRawByte() >= 0)
/* 360 */                 return result; 
/*     */             } 
/* 363 */             throw InvalidProtocolBufferException.malformedVarint();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 368 */     return result;
/*     */   }
/*     */   
/*     */   static int readRawVarint32(InputStream input) throws IOException {
/* 379 */     int firstByte = input.read();
/* 380 */     if (firstByte == -1)
/* 381 */       throw InvalidProtocolBufferException.truncatedMessage(); 
/* 383 */     return readRawVarint32(firstByte, input);
/*     */   }
/*     */   
/*     */   public static int readRawVarint32(int firstByte, InputStream input) throws IOException {
/* 393 */     if ((firstByte & 0x80) == 0)
/* 394 */       return firstByte; 
/* 397 */     int result = firstByte & 0x7F;
/* 398 */     int offset = 7;
/* 399 */     for (; offset < 32; offset += 7) {
/* 400 */       int b = input.read();
/* 401 */       if (b == -1)
/* 402 */         throw InvalidProtocolBufferException.truncatedMessage(); 
/* 404 */       result |= (b & 0x7F) << offset;
/* 405 */       if ((b & 0x80) == 0)
/* 406 */         return result; 
/*     */     } 
/* 410 */     for (; offset < 64; offset += 7) {
/* 411 */       int b = input.read();
/* 412 */       if (b == -1)
/* 413 */         throw InvalidProtocolBufferException.truncatedMessage(); 
/* 415 */       if ((b & 0x80) == 0)
/* 416 */         return result; 
/*     */     } 
/* 419 */     throw InvalidProtocolBufferException.malformedVarint();
/*     */   }
/*     */   
/*     */   public long readRawVarint64() throws IOException {
/* 424 */     int shift = 0;
/* 425 */     long result = 0L;
/* 426 */     while (shift < 64) {
/* 427 */       byte b = readRawByte();
/* 428 */       result |= (b & Byte.MAX_VALUE) << shift;
/* 429 */       if ((b & 0x80) == 0)
/* 430 */         return result; 
/* 432 */       shift += 7;
/*     */     } 
/* 434 */     throw InvalidProtocolBufferException.malformedVarint();
/*     */   }
/*     */   
/*     */   public int readRawLittleEndian32() throws IOException {
/* 439 */     byte b1 = readRawByte();
/* 440 */     byte b2 = readRawByte();
/* 441 */     byte b3 = readRawByte();
/* 442 */     byte b4 = readRawByte();
/* 443 */     return b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16 | (b4 & 0xFF) << 24;
/*     */   }
/*     */   
/*     */   public long readRawLittleEndian64() throws IOException {
/* 451 */     byte b1 = readRawByte();
/* 452 */     byte b2 = readRawByte();
/* 453 */     byte b3 = readRawByte();
/* 454 */     byte b4 = readRawByte();
/* 455 */     byte b5 = readRawByte();
/* 456 */     byte b6 = readRawByte();
/* 457 */     byte b7 = readRawByte();
/* 458 */     byte b8 = readRawByte();
/* 459 */     return b1 & 0xFFL | (b2 & 0xFFL) << 8L | (b3 & 0xFFL) << 16L | (b4 & 0xFFL) << 24L | (b5 & 0xFFL) << 32L | (b6 & 0xFFL) << 40L | (b7 & 0xFFL) << 48L | (b8 & 0xFFL) << 56L;
/*     */   }
/*     */   
/*     */   public static int decodeZigZag32(int n) {
/* 480 */     return n >>> 1 ^ -(n & 0x1);
/*     */   }
/*     */   
/*     */   public static long decodeZigZag64(long n) {
/* 494 */     return n >>> 1L ^ -(n & 0x1L);
/*     */   }
/*     */   
/* 516 */   private int currentLimit = Integer.MAX_VALUE;
/*     */   
/*     */   private int recursionDepth;
/*     */   
/* 520 */   private int recursionLimit = 64;
/*     */   
/* 523 */   private int sizeLimit = 67108864;
/*     */   
/*     */   private static final int DEFAULT_RECURSION_LIMIT = 64;
/*     */   
/*     */   private static final int DEFAULT_SIZE_LIMIT = 67108864;
/*     */   
/*     */   private static final int BUFFER_SIZE = 4096;
/*     */   
/*     */   private CodedInputStream(byte[] buffer, int off, int len) {
/* 530 */     this.buffer = buffer;
/* 531 */     this.bufferSize = off + len;
/* 532 */     this.bufferPos = off;
/* 533 */     this.totalBytesRetired = -off;
/* 534 */     this.input = null;
/*     */   }
/*     */   
/*     */   private CodedInputStream(InputStream input) {
/* 538 */     this.buffer = new byte[4096];
/* 539 */     this.bufferSize = 0;
/* 540 */     this.bufferPos = 0;
/* 541 */     this.totalBytesRetired = 0;
/* 542 */     this.input = input;
/*     */   }
/*     */   
/*     */   public int setRecursionLimit(int limit) {
/* 553 */     if (limit < 0)
/* 554 */       throw new IllegalArgumentException("Recursion limit cannot be negative: " + limit); 
/* 557 */     int oldLimit = this.recursionLimit;
/* 558 */     this.recursionLimit = limit;
/* 559 */     return oldLimit;
/*     */   }
/*     */   
/*     */   public int setSizeLimit(int limit) {
/* 579 */     if (limit < 0)
/* 580 */       throw new IllegalArgumentException("Size limit cannot be negative: " + limit); 
/* 583 */     int oldLimit = this.sizeLimit;
/* 584 */     this.sizeLimit = limit;
/* 585 */     return oldLimit;
/*     */   }
/*     */   
/*     */   public void resetSizeCounter() {
/* 592 */     this.totalBytesRetired = -this.bufferPos;
/*     */   }
/*     */   
/*     */   public int pushLimit(int byteLimit) throws InvalidProtocolBufferException {
/* 610 */     if (byteLimit < 0)
/* 611 */       throw InvalidProtocolBufferException.negativeSize(); 
/* 613 */     byteLimit += this.totalBytesRetired + this.bufferPos;
/* 614 */     int oldLimit = this.currentLimit;
/* 615 */     if (byteLimit > oldLimit)
/* 616 */       throw InvalidProtocolBufferException.truncatedMessage(); 
/* 618 */     this.currentLimit = byteLimit;
/* 620 */     recomputeBufferSizeAfterLimit();
/* 622 */     return oldLimit;
/*     */   }
/*     */   
/*     */   private void recomputeBufferSizeAfterLimit() {
/* 626 */     this.bufferSize += this.bufferSizeAfterLimit;
/* 627 */     int bufferEnd = this.totalBytesRetired + this.bufferSize;
/* 628 */     if (bufferEnd > this.currentLimit) {
/* 630 */       this.bufferSizeAfterLimit = bufferEnd - this.currentLimit;
/* 631 */       this.bufferSize -= this.bufferSizeAfterLimit;
/*     */     } else {
/* 633 */       this.bufferSizeAfterLimit = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void popLimit(int oldLimit) {
/* 643 */     this.currentLimit = oldLimit;
/* 644 */     recomputeBufferSizeAfterLimit();
/*     */   }
/*     */   
/*     */   public int getBytesUntilLimit() {
/* 652 */     if (this.currentLimit == Integer.MAX_VALUE)
/* 653 */       return -1; 
/* 656 */     int currentAbsolutePosition = this.totalBytesRetired + this.bufferPos;
/* 657 */     return this.currentLimit - currentAbsolutePosition;
/*     */   }
/*     */   
/*     */   public boolean isAtEnd() throws IOException {
/* 666 */     return (this.bufferPos == this.bufferSize && !refillBuffer(false));
/*     */   }
/*     */   
/*     */   public int getTotalBytesRead() {
/* 674 */     return this.totalBytesRetired + this.bufferPos;
/*     */   }
/*     */   
/*     */   private boolean refillBuffer(boolean mustSucceed) throws IOException {
/* 685 */     if (this.bufferPos < this.bufferSize)
/* 686 */       throw new IllegalStateException("refillBuffer() called when buffer wasn't empty."); 
/* 690 */     if (this.totalBytesRetired + this.bufferSize == this.currentLimit) {
/* 692 */       if (mustSucceed)
/* 693 */         throw InvalidProtocolBufferException.truncatedMessage(); 
/* 695 */       return false;
/*     */     } 
/* 699 */     this.totalBytesRetired += this.bufferSize;
/* 701 */     this.bufferPos = 0;
/* 702 */     this.bufferSize = (this.input == null) ? -1 : this.input.read(this.buffer);
/* 703 */     if (this.bufferSize == 0 || this.bufferSize < -1)
/* 704 */       throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.bufferSize + "\nThe InputStream implementation is buggy."); 
/* 708 */     if (this.bufferSize == -1) {
/* 709 */       this.bufferSize = 0;
/* 710 */       if (mustSucceed)
/* 711 */         throw InvalidProtocolBufferException.truncatedMessage(); 
/* 713 */       return false;
/*     */     } 
/* 716 */     recomputeBufferSizeAfterLimit();
/* 717 */     int totalBytesRead = this.totalBytesRetired + this.bufferSize + this.bufferSizeAfterLimit;
/* 719 */     if (totalBytesRead > this.sizeLimit || totalBytesRead < 0)
/* 720 */       throw InvalidProtocolBufferException.sizeLimitExceeded(); 
/* 722 */     return true;
/*     */   }
/*     */   
/*     */   public byte readRawByte() throws IOException {
/* 733 */     if (this.bufferPos == this.bufferSize)
/* 734 */       refillBuffer(true); 
/* 736 */     return this.buffer[this.bufferPos++];
/*     */   }
/*     */   
/*     */   public byte[] readRawBytes(int size) throws IOException {
/* 746 */     if (size < 0)
/* 747 */       throw InvalidProtocolBufferException.negativeSize(); 
/* 750 */     if (this.totalBytesRetired + this.bufferPos + size > this.currentLimit) {
/* 752 */       skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
/* 754 */       throw InvalidProtocolBufferException.truncatedMessage();
/*     */     } 
/* 757 */     if (size <= this.bufferSize - this.bufferPos) {
/* 759 */       byte[] arrayOfByte = new byte[size];
/* 760 */       System.arraycopy(this.buffer, this.bufferPos, arrayOfByte, 0, size);
/* 761 */       this.bufferPos += size;
/* 762 */       return arrayOfByte;
/*     */     } 
/* 763 */     if (size < 4096) {
/* 768 */       byte[] arrayOfByte = new byte[size];
/* 769 */       int i = this.bufferSize - this.bufferPos;
/* 770 */       System.arraycopy(this.buffer, this.bufferPos, arrayOfByte, 0, i);
/* 771 */       this.bufferPos = this.bufferSize;
/* 776 */       refillBuffer(true);
/* 778 */       while (size - i > this.bufferSize) {
/* 779 */         System.arraycopy(this.buffer, 0, arrayOfByte, i, this.bufferSize);
/* 780 */         i += this.bufferSize;
/* 781 */         this.bufferPos = this.bufferSize;
/* 782 */         refillBuffer(true);
/*     */       } 
/* 785 */       System.arraycopy(this.buffer, 0, arrayOfByte, i, size - i);
/* 786 */       this.bufferPos = size - i;
/* 788 */       return arrayOfByte;
/*     */     } 
/* 800 */     int originalBufferPos = this.bufferPos;
/* 801 */     int originalBufferSize = this.bufferSize;
/* 804 */     this.totalBytesRetired += this.bufferSize;
/* 805 */     this.bufferPos = 0;
/* 806 */     this.bufferSize = 0;
/* 809 */     int sizeLeft = size - originalBufferSize - originalBufferPos;
/* 810 */     List<byte[]> chunks = (List)new ArrayList<byte>();
/* 812 */     while (sizeLeft > 0) {
/* 813 */       byte[] chunk = new byte[Math.min(sizeLeft, 4096)];
/* 814 */       int i = 0;
/* 815 */       while (i < chunk.length) {
/* 816 */         int n = (this.input == null) ? -1 : this.input.read(chunk, i, chunk.length - i);
/* 818 */         if (n == -1)
/* 819 */           throw InvalidProtocolBufferException.truncatedMessage(); 
/* 821 */         this.totalBytesRetired += n;
/* 822 */         i += n;
/*     */       } 
/* 824 */       sizeLeft -= chunk.length;
/* 825 */       chunks.add(chunk);
/*     */     } 
/* 829 */     byte[] bytes = new byte[size];
/* 832 */     int pos = originalBufferSize - originalBufferPos;
/* 833 */     System.arraycopy(this.buffer, originalBufferPos, bytes, 0, pos);
/* 836 */     for (byte[] chunk : chunks) {
/* 837 */       System.arraycopy(chunk, 0, bytes, pos, chunk.length);
/* 838 */       pos += chunk.length;
/*     */     } 
/* 842 */     return bytes;
/*     */   }
/*     */   
/*     */   public void skipRawBytes(int size) throws IOException {
/* 853 */     if (size < 0)
/* 854 */       throw InvalidProtocolBufferException.negativeSize(); 
/* 857 */     if (this.totalBytesRetired + this.bufferPos + size > this.currentLimit) {
/* 859 */       skipRawBytes(this.currentLimit - this.totalBytesRetired - this.bufferPos);
/* 861 */       throw InvalidProtocolBufferException.truncatedMessage();
/*     */     } 
/* 864 */     if (size <= this.bufferSize - this.bufferPos) {
/* 866 */       this.bufferPos += size;
/*     */     } else {
/* 869 */       int pos = this.bufferSize - this.bufferPos;
/* 870 */       this.bufferPos = this.bufferSize;
/* 875 */       refillBuffer(true);
/* 876 */       while (size - pos > this.bufferSize) {
/* 877 */         pos += this.bufferSize;
/* 878 */         this.bufferPos = this.bufferSize;
/* 879 */         refillBuffer(true);
/*     */       } 
/* 882 */       this.bufferPos = size - pos;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\CodedInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */