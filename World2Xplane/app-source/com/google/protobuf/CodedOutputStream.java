/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ 
/*      */ public final class CodedOutputStream {
/*      */   private final byte[] buffer;
/*      */   
/*      */   private final int limit;
/*      */   
/*      */   private int position;
/*      */   
/*      */   private final OutputStream output;
/*      */   
/*      */   public static final int DEFAULT_BUFFER_SIZE = 4096;
/*      */   
/*      */   public static final int LITTLE_ENDIAN_32_SIZE = 4;
/*      */   
/*      */   public static final int LITTLE_ENDIAN_64_SIZE = 8;
/*      */   
/*      */   static int computePreferredBufferSize(int dataLength) {
/*   72 */     if (dataLength > 4096)
/*   72 */       return 4096; 
/*   73 */     return dataLength;
/*      */   }
/*      */   
/*      */   private CodedOutputStream(byte[] buffer, int offset, int length) {
/*   78 */     this.output = null;
/*   79 */     this.buffer = buffer;
/*   80 */     this.position = offset;
/*   81 */     this.limit = offset + length;
/*      */   }
/*      */   
/*      */   private CodedOutputStream(OutputStream output, byte[] buffer) {
/*   85 */     this.output = output;
/*   86 */     this.buffer = buffer;
/*   87 */     this.position = 0;
/*   88 */     this.limit = buffer.length;
/*      */   }
/*      */   
/*      */   public static CodedOutputStream newInstance(OutputStream output) {
/*   96 */     return newInstance(output, 4096);
/*      */   }
/*      */   
/*      */   public static CodedOutputStream newInstance(OutputStream output, int bufferSize) {
/*  105 */     return new CodedOutputStream(output, new byte[bufferSize]);
/*      */   }
/*      */   
/*      */   public static CodedOutputStream newInstance(byte[] flatArray) {
/*  116 */     return newInstance(flatArray, 0, flatArray.length);
/*      */   }
/*      */   
/*      */   public static CodedOutputStream newInstance(byte[] flatArray, int offset, int length) {
/*  129 */     return new CodedOutputStream(flatArray, offset, length);
/*      */   }
/*      */   
/*      */   public void writeDouble(int fieldNumber, double value) throws IOException {
/*  137 */     writeTag(fieldNumber, 1);
/*  138 */     writeDoubleNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeFloat(int fieldNumber, float value) throws IOException {
/*  144 */     writeTag(fieldNumber, 5);
/*  145 */     writeFloatNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeUInt64(int fieldNumber, long value) throws IOException {
/*  151 */     writeTag(fieldNumber, 0);
/*  152 */     writeUInt64NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeInt64(int fieldNumber, long value) throws IOException {
/*  158 */     writeTag(fieldNumber, 0);
/*  159 */     writeInt64NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeInt32(int fieldNumber, int value) throws IOException {
/*  165 */     writeTag(fieldNumber, 0);
/*  166 */     writeInt32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeFixed64(int fieldNumber, long value) throws IOException {
/*  172 */     writeTag(fieldNumber, 1);
/*  173 */     writeFixed64NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeFixed32(int fieldNumber, int value) throws IOException {
/*  179 */     writeTag(fieldNumber, 5);
/*  180 */     writeFixed32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeBool(int fieldNumber, boolean value) throws IOException {
/*  186 */     writeTag(fieldNumber, 0);
/*  187 */     writeBoolNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeString(int fieldNumber, String value) throws IOException {
/*  193 */     writeTag(fieldNumber, 2);
/*  194 */     writeStringNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeGroup(int fieldNumber, MessageLite value) throws IOException {
/*  200 */     writeTag(fieldNumber, 3);
/*  201 */     writeGroupNoTag(value);
/*  202 */     writeTag(fieldNumber, 4);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void writeUnknownGroup(int fieldNumber, MessageLite value) throws IOException {
/*  215 */     writeGroup(fieldNumber, value);
/*      */   }
/*      */   
/*      */   public void writeMessage(int fieldNumber, MessageLite value) throws IOException {
/*  221 */     writeTag(fieldNumber, 2);
/*  222 */     writeMessageNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeBytes(int fieldNumber, ByteString value) throws IOException {
/*  228 */     writeTag(fieldNumber, 2);
/*  229 */     writeBytesNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeUInt32(int fieldNumber, int value) throws IOException {
/*  235 */     writeTag(fieldNumber, 0);
/*  236 */     writeUInt32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeEnum(int fieldNumber, int value) throws IOException {
/*  245 */     writeTag(fieldNumber, 0);
/*  246 */     writeEnumNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeSFixed32(int fieldNumber, int value) throws IOException {
/*  252 */     writeTag(fieldNumber, 5);
/*  253 */     writeSFixed32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeSFixed64(int fieldNumber, long value) throws IOException {
/*  259 */     writeTag(fieldNumber, 1);
/*  260 */     writeSFixed64NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeSInt32(int fieldNumber, int value) throws IOException {
/*  266 */     writeTag(fieldNumber, 0);
/*  267 */     writeSInt32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeSInt64(int fieldNumber, long value) throws IOException {
/*  273 */     writeTag(fieldNumber, 0);
/*  274 */     writeSInt64NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeMessageSetExtension(int fieldNumber, MessageLite value) throws IOException {
/*  284 */     writeTag(1, 3);
/*  285 */     writeUInt32(2, fieldNumber);
/*  286 */     writeMessage(3, value);
/*  287 */     writeTag(1, 4);
/*      */   }
/*      */   
/*      */   public void writeRawMessageSetExtension(int fieldNumber, ByteString value) throws IOException {
/*  297 */     writeTag(1, 3);
/*  298 */     writeUInt32(2, fieldNumber);
/*  299 */     writeBytes(3, value);
/*  300 */     writeTag(1, 4);
/*      */   }
/*      */   
/*      */   public void writeDoubleNoTag(double value) throws IOException {
/*  307 */     writeRawLittleEndian64(Double.doubleToRawLongBits(value));
/*      */   }
/*      */   
/*      */   public void writeFloatNoTag(float value) throws IOException {
/*  312 */     writeRawLittleEndian32(Float.floatToRawIntBits(value));
/*      */   }
/*      */   
/*      */   public void writeUInt64NoTag(long value) throws IOException {
/*  317 */     writeRawVarint64(value);
/*      */   }
/*      */   
/*      */   public void writeInt64NoTag(long value) throws IOException {
/*  322 */     writeRawVarint64(value);
/*      */   }
/*      */   
/*      */   public void writeInt32NoTag(int value) throws IOException {
/*  327 */     if (value >= 0) {
/*  328 */       writeRawVarint32(value);
/*      */     } else {
/*  331 */       writeRawVarint64(value);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeFixed64NoTag(long value) throws IOException {
/*  337 */     writeRawLittleEndian64(value);
/*      */   }
/*      */   
/*      */   public void writeFixed32NoTag(int value) throws IOException {
/*  342 */     writeRawLittleEndian32(value);
/*      */   }
/*      */   
/*      */   public void writeBoolNoTag(boolean value) throws IOException {
/*  347 */     writeRawByte(value ? 1 : 0);
/*      */   }
/*      */   
/*      */   public void writeStringNoTag(String value) throws IOException {
/*  355 */     byte[] bytes = value.getBytes("UTF-8");
/*  356 */     writeRawVarint32(bytes.length);
/*  357 */     writeRawBytes(bytes);
/*      */   }
/*      */   
/*      */   public void writeGroupNoTag(MessageLite value) throws IOException {
/*  362 */     value.writeTo(this);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void writeUnknownGroupNoTag(MessageLite value) throws IOException {
/*  374 */     writeGroupNoTag(value);
/*      */   }
/*      */   
/*      */   public void writeMessageNoTag(MessageLite value) throws IOException {
/*  379 */     writeRawVarint32(value.getSerializedSize());
/*  380 */     value.writeTo(this);
/*      */   }
/*      */   
/*      */   public void writeBytesNoTag(ByteString value) throws IOException {
/*  385 */     writeRawVarint32(value.size());
/*  386 */     writeRawBytes(value);
/*      */   }
/*      */   
/*      */   public void writeUInt32NoTag(int value) throws IOException {
/*  391 */     writeRawVarint32(value);
/*      */   }
/*      */   
/*      */   public void writeEnumNoTag(int value) throws IOException {
/*  399 */     writeInt32NoTag(value);
/*      */   }
/*      */   
/*      */   public void writeSFixed32NoTag(int value) throws IOException {
/*  404 */     writeRawLittleEndian32(value);
/*      */   }
/*      */   
/*      */   public void writeSFixed64NoTag(long value) throws IOException {
/*  409 */     writeRawLittleEndian64(value);
/*      */   }
/*      */   
/*      */   public void writeSInt32NoTag(int value) throws IOException {
/*  414 */     writeRawVarint32(encodeZigZag32(value));
/*      */   }
/*      */   
/*      */   public void writeSInt64NoTag(long value) throws IOException {
/*  419 */     writeRawVarint64(encodeZigZag64(value));
/*      */   }
/*      */   
/*      */   public static int computeDoubleSize(int fieldNumber, double value) {
/*  430 */     return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeFloatSize(int fieldNumber, float value) {
/*  438 */     return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeUInt64Size(int fieldNumber, long value) {
/*  446 */     return computeTagSize(fieldNumber) + computeUInt64SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeInt64Size(int fieldNumber, long value) {
/*  454 */     return computeTagSize(fieldNumber) + computeInt64SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeInt32Size(int fieldNumber, int value) {
/*  462 */     return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeFixed64Size(int fieldNumber, long value) {
/*  471 */     return computeTagSize(fieldNumber) + computeFixed64SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeFixed32Size(int fieldNumber, int value) {
/*  480 */     return computeTagSize(fieldNumber) + computeFixed32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeBoolSize(int fieldNumber, boolean value) {
/*  489 */     return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeStringSize(int fieldNumber, String value) {
/*  498 */     return computeTagSize(fieldNumber) + computeStringSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeGroupSize(int fieldNumber, MessageLite value) {
/*  507 */     return computeTagSize(fieldNumber) * 2 + computeGroupSizeNoTag(value);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static int computeUnknownGroupSize(int fieldNumber, MessageLite value) {
/*  521 */     return computeGroupSize(fieldNumber, value);
/*      */   }
/*      */   
/*      */   public static int computeMessageSize(int fieldNumber, MessageLite value) {
/*  530 */     return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeBytesSize(int fieldNumber, ByteString value) {
/*  539 */     return computeTagSize(fieldNumber) + computeBytesSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeUInt32Size(int fieldNumber, int value) {
/*  547 */     return computeTagSize(fieldNumber) + computeUInt32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeEnumSize(int fieldNumber, int value) {
/*  556 */     return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeSFixed32Size(int fieldNumber, int value) {
/*  565 */     return computeTagSize(fieldNumber) + computeSFixed32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeSFixed64Size(int fieldNumber, long value) {
/*  574 */     return computeTagSize(fieldNumber) + computeSFixed64SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeSInt32Size(int fieldNumber, int value) {
/*  582 */     return computeTagSize(fieldNumber) + computeSInt32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeSInt64Size(int fieldNumber, long value) {
/*  590 */     return computeTagSize(fieldNumber) + computeSInt64SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeMessageSetExtensionSize(int fieldNumber, MessageLite value) {
/*  600 */     return computeTagSize(1) * 2 + computeUInt32Size(2, fieldNumber) + computeMessageSize(3, value);
/*      */   }
/*      */   
/*      */   public static int computeRawMessageSetExtensionSize(int fieldNumber, ByteString value) {
/*  612 */     return computeTagSize(1) * 2 + computeUInt32Size(2, fieldNumber) + computeBytesSize(3, value);
/*      */   }
/*      */   
/*      */   public static int computeDoubleSizeNoTag(double value) {
/*  624 */     return 8;
/*      */   }
/*      */   
/*      */   public static int computeFloatSizeNoTag(float value) {
/*  632 */     return 4;
/*      */   }
/*      */   
/*      */   public static int computeUInt64SizeNoTag(long value) {
/*  640 */     return computeRawVarint64Size(value);
/*      */   }
/*      */   
/*      */   public static int computeInt64SizeNoTag(long value) {
/*  648 */     return computeRawVarint64Size(value);
/*      */   }
/*      */   
/*      */   public static int computeInt32SizeNoTag(int value) {
/*  656 */     if (value >= 0)
/*  657 */       return computeRawVarint32Size(value); 
/*  660 */     return 10;
/*      */   }
/*      */   
/*      */   public static int computeFixed64SizeNoTag(long value) {
/*  669 */     return 8;
/*      */   }
/*      */   
/*      */   public static int computeFixed32SizeNoTag(int value) {
/*  677 */     return 4;
/*      */   }
/*      */   
/*      */   public static int computeBoolSizeNoTag(boolean value) {
/*  685 */     return 1;
/*      */   }
/*      */   
/*      */   public static int computeStringSizeNoTag(String value) {
/*      */     try {
/*  694 */       byte[] bytes = value.getBytes("UTF-8");
/*  695 */       return computeRawVarint32Size(bytes.length) + bytes.length;
/*  697 */     } catch (UnsupportedEncodingException e) {
/*  698 */       throw new RuntimeException("UTF-8 not supported.", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int computeGroupSizeNoTag(MessageLite value) {
/*  707 */     return value.getSerializedSize();
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static int computeUnknownGroupSizeNoTag(MessageLite value) {
/*  720 */     return computeGroupSizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeMessageSizeNoTag(MessageLite value) {
/*  728 */     int size = value.getSerializedSize();
/*  729 */     return computeRawVarint32Size(size) + size;
/*      */   }
/*      */   
/*      */   public static int computeBytesSizeNoTag(ByteString value) {
/*  737 */     return computeRawVarint32Size(value.size()) + value.size();
/*      */   }
/*      */   
/*      */   public static int computeUInt32SizeNoTag(int value) {
/*  746 */     return computeRawVarint32Size(value);
/*      */   }
/*      */   
/*      */   public static int computeEnumSizeNoTag(int value) {
/*  754 */     return computeInt32SizeNoTag(value);
/*      */   }
/*      */   
/*      */   public static int computeSFixed32SizeNoTag(int value) {
/*  762 */     return 4;
/*      */   }
/*      */   
/*      */   public static int computeSFixed64SizeNoTag(long value) {
/*  770 */     return 8;
/*      */   }
/*      */   
/*      */   public static int computeSInt32SizeNoTag(int value) {
/*  778 */     return computeRawVarint32Size(encodeZigZag32(value));
/*      */   }
/*      */   
/*      */   public static int computeSInt64SizeNoTag(long value) {
/*  786 */     return computeRawVarint64Size(encodeZigZag64(value));
/*      */   }
/*      */   
/*      */   private void refreshBuffer() throws IOException {
/*  796 */     if (this.output == null)
/*  798 */       throw new OutOfSpaceException(); 
/*  803 */     this.output.write(this.buffer, 0, this.position);
/*  804 */     this.position = 0;
/*      */   }
/*      */   
/*      */   public void flush() throws IOException {
/*  812 */     if (this.output != null)
/*  813 */       refreshBuffer(); 
/*      */   }
/*      */   
/*      */   public int spaceLeft() {
/*  822 */     if (this.output == null)
/*  823 */       return this.limit - this.position; 
/*  825 */     throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
/*      */   }
/*      */   
/*      */   public void checkNoSpaceLeft() {
/*  839 */     if (spaceLeft() != 0)
/*  840 */       throw new IllegalStateException("Did not write as much data as expected."); 
/*      */   }
/*      */   
/*      */   public static class OutOfSpaceException extends IOException {
/*      */     private static final long serialVersionUID = -6947486886997889499L;
/*      */     
/*      */     OutOfSpaceException() {
/*  854 */       super("CodedOutputStream was writing to a flat byte array and ran out of space.");
/*      */     }
/*      */   }
/*      */   
/*      */   public void writeRawByte(byte value) throws IOException {
/*  861 */     if (this.position == this.limit)
/*  862 */       refreshBuffer(); 
/*  865 */     this.buffer[this.position++] = value;
/*      */   }
/*      */   
/*      */   public void writeRawByte(int value) throws IOException {
/*  870 */     writeRawByte((byte)value);
/*      */   }
/*      */   
/*      */   public void writeRawBytes(ByteString value) throws IOException {
/*  875 */     writeRawBytes(value, 0, value.size());
/*      */   }
/*      */   
/*      */   public void writeRawBytes(byte[] value) throws IOException {
/*  880 */     writeRawBytes(value, 0, value.length);
/*      */   }
/*      */   
/*      */   public void writeRawBytes(byte[] value, int offset, int length) throws IOException {
/*  886 */     if (this.limit - this.position >= length) {
/*  888 */       System.arraycopy(value, offset, this.buffer, this.position, length);
/*  889 */       this.position += length;
/*      */     } else {
/*  893 */       int bytesWritten = this.limit - this.position;
/*  894 */       System.arraycopy(value, offset, this.buffer, this.position, bytesWritten);
/*  895 */       offset += bytesWritten;
/*  896 */       length -= bytesWritten;
/*  897 */       this.position = this.limit;
/*  898 */       refreshBuffer();
/*  903 */       if (length <= this.limit) {
/*  905 */         System.arraycopy(value, offset, this.buffer, 0, length);
/*  906 */         this.position = length;
/*      */       } else {
/*  909 */         this.output.write(value, offset, length);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeRawBytes(ByteString value, int offset, int length) throws IOException {
/*  917 */     if (this.limit - this.position >= length) {
/*  919 */       value.copyTo(this.buffer, offset, this.position, length);
/*  920 */       this.position += length;
/*      */     } else {
/*  924 */       int bytesWritten = this.limit - this.position;
/*  925 */       value.copyTo(this.buffer, offset, this.position, bytesWritten);
/*  926 */       offset += bytesWritten;
/*  927 */       length -= bytesWritten;
/*  928 */       this.position = this.limit;
/*  929 */       refreshBuffer();
/*  934 */       if (length <= this.limit) {
/*  936 */         value.copyTo(this.buffer, offset, 0, length);
/*  937 */         this.position = length;
/*      */       } else {
/*  943 */         InputStream inputStreamFrom = value.newInput();
/*  944 */         if (offset != inputStreamFrom.skip(offset))
/*  945 */           throw new IllegalStateException("Skip failed? Should never happen."); 
/*  948 */         while (length > 0) {
/*  949 */           int bytesToRead = Math.min(length, this.limit);
/*  950 */           int bytesRead = inputStreamFrom.read(this.buffer, 0, bytesToRead);
/*  951 */           if (bytesRead != bytesToRead)
/*  952 */             throw new IllegalStateException("Read failed? Should never happen"); 
/*  954 */           this.output.write(this.buffer, 0, bytesRead);
/*  955 */           length -= bytesRead;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void writeTag(int fieldNumber, int wireType) throws IOException {
/*  964 */     writeRawVarint32(WireFormat.makeTag(fieldNumber, wireType));
/*      */   }
/*      */   
/*      */   public static int computeTagSize(int fieldNumber) {
/*  969 */     return computeRawVarint32Size(WireFormat.makeTag(fieldNumber, 0));
/*      */   }
/*      */   
/*      */   public void writeRawVarint32(int value) throws IOException {
/*      */     while (true) {
/*  978 */       if ((value & 0xFFFFFF80) == 0) {
/*  979 */         writeRawByte(value);
/*      */         return;
/*      */       } 
/*  982 */       writeRawByte(value & 0x7F | 0x80);
/*  983 */       value >>>= 7;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int computeRawVarint32Size(int value) {
/*  994 */     if ((value & 0xFFFFFF80) == 0)
/*  994 */       return 1; 
/*  995 */     if ((value & 0xFFFFC000) == 0)
/*  995 */       return 2; 
/*  996 */     if ((value & 0xFFE00000) == 0)
/*  996 */       return 3; 
/*  997 */     if ((value & 0xF0000000) == 0)
/*  997 */       return 4; 
/*  998 */     return 5;
/*      */   }
/*      */   
/*      */   public void writeRawVarint64(long value) throws IOException {
/*      */     while (true) {
/* 1004 */       if ((value & 0xFFFFFFFFFFFFFF80L) == 0L) {
/* 1005 */         writeRawByte((int)value);
/*      */         return;
/*      */       } 
/* 1008 */       writeRawByte((int)value & 0x7F | 0x80);
/* 1009 */       value >>>= 7L;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static int computeRawVarint64Size(long value) {
/* 1016 */     if ((value & 0xFFFFFFFFFFFFFF80L) == 0L)
/* 1016 */       return 1; 
/* 1017 */     if ((value & 0xFFFFFFFFFFFFC000L) == 0L)
/* 1017 */       return 2; 
/* 1018 */     if ((value & 0xFFFFFFFFFFE00000L) == 0L)
/* 1018 */       return 3; 
/* 1019 */     if ((value & 0xFFFFFFFFF0000000L) == 0L)
/* 1019 */       return 4; 
/* 1020 */     if ((value & 0xFFFFFFF800000000L) == 0L)
/* 1020 */       return 5; 
/* 1021 */     if ((value & 0xFFFFFC0000000000L) == 0L)
/* 1021 */       return 6; 
/* 1022 */     if ((value & 0xFFFE000000000000L) == 0L)
/* 1022 */       return 7; 
/* 1023 */     if ((value & 0xFF00000000000000L) == 0L)
/* 1023 */       return 8; 
/* 1024 */     if ((value & Long.MIN_VALUE) == 0L)
/* 1024 */       return 9; 
/* 1025 */     return 10;
/*      */   }
/*      */   
/*      */   public void writeRawLittleEndian32(int value) throws IOException {
/* 1030 */     writeRawByte(value & 0xFF);
/* 1031 */     writeRawByte(value >> 8 & 0xFF);
/* 1032 */     writeRawByte(value >> 16 & 0xFF);
/* 1033 */     writeRawByte(value >> 24 & 0xFF);
/*      */   }
/*      */   
/*      */   public void writeRawLittleEndian64(long value) throws IOException {
/* 1040 */     writeRawByte((int)value & 0xFF);
/* 1041 */     writeRawByte((int)(value >> 8L) & 0xFF);
/* 1042 */     writeRawByte((int)(value >> 16L) & 0xFF);
/* 1043 */     writeRawByte((int)(value >> 24L) & 0xFF);
/* 1044 */     writeRawByte((int)(value >> 32L) & 0xFF);
/* 1045 */     writeRawByte((int)(value >> 40L) & 0xFF);
/* 1046 */     writeRawByte((int)(value >> 48L) & 0xFF);
/* 1047 */     writeRawByte((int)(value >> 56L) & 0xFF);
/*      */   }
/*      */   
/*      */   public static int encodeZigZag32(int n) {
/* 1064 */     return n << 1 ^ n >> 31;
/*      */   }
/*      */   
/*      */   public static long encodeZigZag64(long n) {
/* 1079 */     return n << 1L ^ n >> 63L;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\CodedOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */