/*     */ package org.openstreetmap.osmosis.osmbinary;
/*     */ 
/*     */ import com.google.protobuf.AbstractMessageLite;
/*     */ import com.google.protobuf.ByteString;
/*     */ import com.google.protobuf.CodedInputStream;
/*     */ import com.google.protobuf.CodedOutputStream;
/*     */ import com.google.protobuf.ExtensionRegistryLite;
/*     */ import com.google.protobuf.GeneratedMessageLite;
/*     */ import com.google.protobuf.Internal;
/*     */ import com.google.protobuf.InvalidProtocolBufferException;
/*     */ import com.google.protobuf.MessageLite;
/*     */ import com.google.protobuf.MessageLiteOrBuilder;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectStreamException;
/*     */ 
/*     */ public final class Fileformat {
/*     */   public static void registerAllExtensions(ExtensionRegistryLite registry) {}
/*     */   
/*     */   public static interface BlobOrBuilder extends MessageLiteOrBuilder {
/*     */     boolean hasRaw();
/*     */     
/*     */     ByteString getRaw();
/*     */     
/*     */     boolean hasRawSize();
/*     */     
/*     */     int getRawSize();
/*     */     
/*     */     boolean hasZlibData();
/*     */     
/*     */     ByteString getZlibData();
/*     */     
/*     */     boolean hasLzmaData();
/*     */     
/*     */     ByteString getLzmaData();
/*     */     
/*     */     @Deprecated
/*     */     boolean hasOBSOLETEBzip2Data();
/*     */     
/*     */     @Deprecated
/*     */     ByteString getOBSOLETEBzip2Data();
/*     */   }
/*     */   
/*     */   public static final class Blob extends GeneratedMessageLite implements BlobOrBuilder {
/*     */     private Blob(Builder builder) {
/*  39 */       super(builder);
/* 110 */       this.memoizedIsInitialized = -1;
/* 139 */       this.memoizedSerializedSize = -1;
/*     */     }
/*     */     
/*     */     private Blob(boolean noInit) {
/*     */       this.memoizedIsInitialized = -1;
/* 139 */       this.memoizedSerializedSize = -1;
/*     */     }
/*     */     
/*     */     public static Blob getDefaultInstance() {
/*     */       return defaultInstance;
/*     */     }
/*     */     
/*     */     public Blob getDefaultInstanceForType() {
/*     */       return defaultInstance;
/*     */     }
/*     */     
/*     */     public boolean hasRaw() {
/*     */       return ((this.bitField0_ & 0x1) == 1);
/*     */     }
/*     */     
/*     */     public ByteString getRaw() {
/*     */       return this.raw_;
/*     */     }
/*     */     
/*     */     public boolean hasRawSize() {
/*     */       return ((this.bitField0_ & 0x2) == 2);
/*     */     }
/*     */     
/*     */     public int getRawSize() {
/*     */       return this.rawSize_;
/*     */     }
/*     */     
/*     */     public boolean hasZlibData() {
/*     */       return ((this.bitField0_ & 0x4) == 4);
/*     */     }
/*     */     
/*     */     public ByteString getZlibData() {
/*     */       return this.zlibData_;
/*     */     }
/*     */     
/*     */     public boolean hasLzmaData() {
/*     */       return ((this.bitField0_ & 0x8) == 8);
/*     */     }
/*     */     
/*     */     public ByteString getLzmaData() {
/*     */       return this.lzmaData_;
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public boolean hasOBSOLETEBzip2Data() {
/*     */       return ((this.bitField0_ & 0x10) == 16);
/*     */     }
/*     */     
/*     */     @Deprecated
/*     */     public ByteString getOBSOLETEBzip2Data() {
/*     */       return this.oBSOLETEBzip2Data_;
/*     */     }
/*     */     
/*     */     private void initFields() {
/*     */       this.raw_ = ByteString.EMPTY;
/*     */       this.rawSize_ = 0;
/*     */       this.zlibData_ = ByteString.EMPTY;
/*     */       this.lzmaData_ = ByteString.EMPTY;
/*     */       this.oBSOLETEBzip2Data_ = ByteString.EMPTY;
/*     */     }
/*     */     
/*     */     public final boolean isInitialized() {
/*     */       byte isInitialized = this.memoizedIsInitialized;
/*     */       if (isInitialized != -1)
/*     */         return (isInitialized == 1); 
/*     */       this.memoizedIsInitialized = 1;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public void writeTo(CodedOutputStream output) throws IOException {
/*     */       getSerializedSize();
/*     */       if ((this.bitField0_ & 0x1) == 1)
/*     */         output.writeBytes(1, this.raw_); 
/*     */       if ((this.bitField0_ & 0x2) == 2)
/*     */         output.writeInt32(2, this.rawSize_); 
/*     */       if ((this.bitField0_ & 0x4) == 4)
/*     */         output.writeBytes(3, this.zlibData_); 
/*     */       if ((this.bitField0_ & 0x8) == 8)
/*     */         output.writeBytes(4, this.lzmaData_); 
/*     */       if ((this.bitField0_ & 0x10) == 16)
/*     */         output.writeBytes(5, this.oBSOLETEBzip2Data_); 
/*     */     }
/*     */     
/*     */     public int getSerializedSize() {
/* 141 */       int size = this.memoizedSerializedSize;
/* 142 */       if (size != -1)
/* 142 */         return size; 
/* 144 */       size = 0;
/* 145 */       if ((this.bitField0_ & 0x1) == 1)
/* 146 */         size += CodedOutputStream.computeBytesSize(1, this.raw_); 
/* 149 */       if ((this.bitField0_ & 0x2) == 2)
/* 150 */         size += CodedOutputStream.computeInt32Size(2, this.rawSize_); 
/* 153 */       if ((this.bitField0_ & 0x4) == 4)
/* 154 */         size += CodedOutputStream.computeBytesSize(3, this.zlibData_); 
/* 157 */       if ((this.bitField0_ & 0x8) == 8)
/* 158 */         size += CodedOutputStream.computeBytesSize(4, this.lzmaData_); 
/* 161 */       if ((this.bitField0_ & 0x10) == 16)
/* 162 */         size += CodedOutputStream.computeBytesSize(5, this.oBSOLETEBzip2Data_); 
/* 165 */       this.memoizedSerializedSize = size;
/* 166 */       return size;
/*     */     }
/*     */     
/*     */     protected Object writeReplace() throws ObjectStreamException {
/* 173 */       return super.writeReplace();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 179 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 185 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 190 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 196 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(InputStream input) throws IOException {
/* 201 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 207 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseDelimitedFrom(InputStream input) throws IOException {
/* 212 */       Builder builder = newBuilder();
/* 213 */       if (builder.mergeDelimitedFrom(input))
/* 214 */         return builder.buildParsed(); 
/* 216 */       return null;
/*     */     }
/*     */     
/*     */     public static Blob parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 223 */       Builder builder = newBuilder();
/* 224 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 225 */         return builder.buildParsed(); 
/* 227 */       return null;
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(CodedInputStream input) throws IOException {
/* 233 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*     */     }
/*     */     
/*     */     public static Blob parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 239 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*     */     }
/*     */     
/*     */     public static Builder newBuilder() {
/* 243 */       return Builder.create();
/*     */     }
/*     */     
/*     */     public Builder newBuilderForType() {
/* 244 */       return newBuilder();
/*     */     }
/*     */     
/*     */     public static Builder newBuilder(Blob prototype) {
/* 246 */       return newBuilder().mergeFrom(prototype);
/*     */     }
/*     */     
/*     */     public Builder toBuilder() {
/* 248 */       return newBuilder(this);
/*     */     }
/*     */     
/*     */     public static final class Builder extends GeneratedMessageLite.Builder<Blob, Builder> implements Fileformat.BlobOrBuilder {
/*     */       private int bitField0_;
/*     */       
/*     */       private ByteString raw_;
/*     */       
/*     */       private int rawSize_;
/*     */       
/*     */       private ByteString zlibData_;
/*     */       
/*     */       private ByteString lzmaData_;
/*     */       
/*     */       private ByteString oBSOLETEBzip2Data_;
/*     */       
/*     */       private Builder() {
/* 407 */         this.raw_ = ByteString.EMPTY;
/* 452 */         this.zlibData_ = ByteString.EMPTY;
/* 476 */         this.lzmaData_ = ByteString.EMPTY;
/* 500 */         this.oBSOLETEBzip2Data_ = ByteString.EMPTY;
/*     */         maybeForceBuilderInitialization();
/*     */       }
/*     */       
/*     */       private void maybeForceBuilderInitialization() {}
/*     */       
/*     */       private static Builder create() {
/*     */         return new Builder();
/*     */       }
/*     */       
/*     */       public Builder clear() {
/*     */         super.clear();
/*     */         this.raw_ = ByteString.EMPTY;
/*     */         this.bitField0_ &= 0xFFFFFFFE;
/*     */         this.rawSize_ = 0;
/*     */         this.bitField0_ &= 0xFFFFFFFD;
/*     */         this.zlibData_ = ByteString.EMPTY;
/*     */         this.bitField0_ &= 0xFFFFFFFB;
/*     */         this.lzmaData_ = ByteString.EMPTY;
/*     */         this.bitField0_ &= 0xFFFFFFF7;
/*     */         this.oBSOLETEBzip2Data_ = ByteString.EMPTY;
/*     */         this.bitField0_ &= 0xFFFFFFEF;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clone() {
/*     */         return create().mergeFrom(buildPartial());
/*     */       }
/*     */       
/*     */       public Fileformat.Blob getDefaultInstanceForType() {
/*     */         return Fileformat.Blob.getDefaultInstance();
/*     */       }
/*     */       
/*     */       public Fileformat.Blob build() {
/*     */         Fileformat.Blob result = buildPartial();
/*     */         if (!result.isInitialized())
/*     */           throw newUninitializedMessageException(result); 
/*     */         return result;
/*     */       }
/*     */       
/*     */       private Fileformat.Blob buildParsed() throws InvalidProtocolBufferException {
/*     */         Fileformat.Blob result = buildPartial();
/*     */         if (!result.isInitialized())
/*     */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*     */         return result;
/*     */       }
/*     */       
/*     */       public Fileformat.Blob buildPartial() {
/*     */         Fileformat.Blob result = new Fileformat.Blob(this);
/*     */         int from_bitField0_ = this.bitField0_;
/*     */         int to_bitField0_ = 0;
/*     */         if ((from_bitField0_ & 0x1) == 1)
/*     */           to_bitField0_ |= 0x1; 
/*     */         result.raw_ = this.raw_;
/*     */         if ((from_bitField0_ & 0x2) == 2)
/*     */           to_bitField0_ |= 0x2; 
/*     */         result.rawSize_ = this.rawSize_;
/*     */         if ((from_bitField0_ & 0x4) == 4)
/*     */           to_bitField0_ |= 0x4; 
/*     */         result.zlibData_ = this.zlibData_;
/*     */         if ((from_bitField0_ & 0x8) == 8)
/*     */           to_bitField0_ |= 0x8; 
/*     */         result.lzmaData_ = this.lzmaData_;
/*     */         if ((from_bitField0_ & 0x10) == 16)
/*     */           to_bitField0_ |= 0x10; 
/*     */         result.oBSOLETEBzip2Data_ = this.oBSOLETEBzip2Data_;
/*     */         result.bitField0_ = to_bitField0_;
/*     */         return result;
/*     */       }
/*     */       
/*     */       public Builder mergeFrom(Fileformat.Blob other) {
/*     */         if (other == Fileformat.Blob.getDefaultInstance())
/*     */           return this; 
/*     */         if (other.hasRaw())
/*     */           setRaw(other.getRaw()); 
/*     */         if (other.hasRawSize())
/*     */           setRawSize(other.getRawSize()); 
/*     */         if (other.hasZlibData())
/*     */           setZlibData(other.getZlibData()); 
/*     */         if (other.hasLzmaData())
/*     */           setLzmaData(other.getLzmaData()); 
/*     */         if (other.hasOBSOLETEBzip2Data())
/*     */           setOBSOLETEBzip2Data(other.getOBSOLETEBzip2Data()); 
/*     */         return this;
/*     */       }
/*     */       
/*     */       public final boolean isInitialized() {
/*     */         return true;
/*     */       }
/*     */       
/*     */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*     */         while (true) {
/*     */           int tag = input.readTag();
/*     */           switch (tag) {
/*     */             case 0:
/*     */               return this;
/*     */             case 10:
/*     */               this.bitField0_ |= 0x1;
/*     */               this.raw_ = input.readBytes();
/*     */               break;
/*     */             case 16:
/*     */               this.bitField0_ |= 0x2;
/*     */               this.rawSize_ = input.readInt32();
/*     */               break;
/*     */             case 26:
/*     */               this.bitField0_ |= 0x4;
/*     */               this.zlibData_ = input.readBytes();
/*     */               break;
/*     */             case 34:
/*     */               this.bitField0_ |= 0x8;
/*     */               this.lzmaData_ = input.readBytes();
/*     */               break;
/*     */             case 42:
/*     */               this.bitField0_ |= 0x10;
/*     */               this.oBSOLETEBzip2Data_ = input.readBytes();
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public boolean hasRaw() {
/*     */         return ((this.bitField0_ & 0x1) == 1);
/*     */       }
/*     */       
/*     */       public ByteString getRaw() {
/*     */         return this.raw_;
/*     */       }
/*     */       
/*     */       public Builder setRaw(ByteString value) {
/*     */         if (value == null)
/*     */           throw new NullPointerException(); 
/*     */         this.bitField0_ |= 0x1;
/*     */         this.raw_ = value;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearRaw() {
/*     */         this.bitField0_ &= 0xFFFFFFFE;
/*     */         this.raw_ = Fileformat.Blob.getDefaultInstance().getRaw();
/*     */         return this;
/*     */       }
/*     */       
/*     */       public boolean hasRawSize() {
/*     */         return ((this.bitField0_ & 0x2) == 2);
/*     */       }
/*     */       
/*     */       public int getRawSize() {
/*     */         return this.rawSize_;
/*     */       }
/*     */       
/*     */       public Builder setRawSize(int value) {
/*     */         this.bitField0_ |= 0x2;
/*     */         this.rawSize_ = value;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearRawSize() {
/*     */         this.bitField0_ &= 0xFFFFFFFD;
/*     */         this.rawSize_ = 0;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public boolean hasZlibData() {
/*     */         return ((this.bitField0_ & 0x4) == 4);
/*     */       }
/*     */       
/*     */       public ByteString getZlibData() {
/*     */         return this.zlibData_;
/*     */       }
/*     */       
/*     */       public Builder setZlibData(ByteString value) {
/*     */         if (value == null)
/*     */           throw new NullPointerException(); 
/*     */         this.bitField0_ |= 0x4;
/*     */         this.zlibData_ = value;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearZlibData() {
/*     */         this.bitField0_ &= 0xFFFFFFFB;
/*     */         this.zlibData_ = Fileformat.Blob.getDefaultInstance().getZlibData();
/*     */         return this;
/*     */       }
/*     */       
/*     */       public boolean hasLzmaData() {
/*     */         return ((this.bitField0_ & 0x8) == 8);
/*     */       }
/*     */       
/*     */       public ByteString getLzmaData() {
/*     */         return this.lzmaData_;
/*     */       }
/*     */       
/*     */       public Builder setLzmaData(ByteString value) {
/*     */         if (value == null)
/*     */           throw new NullPointerException(); 
/*     */         this.bitField0_ |= 0x8;
/*     */         this.lzmaData_ = value;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearLzmaData() {
/*     */         this.bitField0_ &= 0xFFFFFFF7;
/*     */         this.lzmaData_ = Fileformat.Blob.getDefaultInstance().getLzmaData();
/*     */         return this;
/*     */       }
/*     */       
/*     */       @Deprecated
/*     */       public boolean hasOBSOLETEBzip2Data() {
/* 502 */         return ((this.bitField0_ & 0x10) == 16);
/*     */       }
/*     */       
/*     */       @Deprecated
/*     */       public ByteString getOBSOLETEBzip2Data() {
/* 505 */         return this.oBSOLETEBzip2Data_;
/*     */       }
/*     */       
/*     */       @Deprecated
/*     */       public Builder setOBSOLETEBzip2Data(ByteString value) {
/* 508 */         if (value == null)
/* 509 */           throw new NullPointerException(); 
/* 511 */         this.bitField0_ |= 0x10;
/* 512 */         this.oBSOLETEBzip2Data_ = value;
/* 514 */         return this;
/*     */       }
/*     */       
/*     */       @Deprecated
/*     */       public Builder clearOBSOLETEBzip2Data() {
/* 517 */         this.bitField0_ &= 0xFFFFFFEF;
/* 518 */         this.oBSOLETEBzip2Data_ = Fileformat.Blob.getDefaultInstance().getOBSOLETEBzip2Data();
/* 520 */         return this;
/*     */       }
/*     */     }
/*     */     
/* 527 */     private static final Blob defaultInstance = new Blob(true);
/*     */     
/*     */     private int bitField0_;
/*     */     
/*     */     public static final int RAW_FIELD_NUMBER = 1;
/*     */     
/*     */     private ByteString raw_;
/*     */     
/*     */     public static final int RAW_SIZE_FIELD_NUMBER = 2;
/*     */     
/*     */     private int rawSize_;
/*     */     
/*     */     public static final int ZLIB_DATA_FIELD_NUMBER = 3;
/*     */     
/*     */     private ByteString zlibData_;
/*     */     
/*     */     public static final int LZMA_DATA_FIELD_NUMBER = 4;
/*     */     
/*     */     private ByteString lzmaData_;
/*     */     
/*     */     public static final int OBSOLETE_BZIP2_DATA_FIELD_NUMBER = 5;
/*     */     
/*     */     private ByteString oBSOLETEBzip2Data_;
/*     */     
/*     */     private byte memoizedIsInitialized;
/*     */     
/*     */     private int memoizedSerializedSize;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     static {
/* 528 */       defaultInstance.initFields();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface BlobHeaderOrBuilder extends MessageLiteOrBuilder {
/*     */     boolean hasType();
/*     */     
/*     */     String getType();
/*     */     
/*     */     boolean hasIndexdata();
/*     */     
/*     */     ByteString getIndexdata();
/*     */     
/*     */     boolean hasDatasize();
/*     */     
/*     */     int getDatasize();
/*     */   }
/*     */   
/*     */   public static final class BlobHeader extends GeneratedMessageLite implements BlobHeaderOrBuilder {
/*     */     private BlobHeader(Builder builder) {
/* 554 */       super(builder);
/* 625 */       this.memoizedIsInitialized = -1;
/* 656 */       this.memoizedSerializedSize = -1;
/*     */     }
/*     */     
/*     */     private BlobHeader(boolean noInit) {
/*     */       this.memoizedIsInitialized = -1;
/* 656 */       this.memoizedSerializedSize = -1;
/*     */     }
/*     */     
/*     */     public static BlobHeader getDefaultInstance() {
/*     */       return defaultInstance;
/*     */     }
/*     */     
/*     */     public BlobHeader getDefaultInstanceForType() {
/*     */       return defaultInstance;
/*     */     }
/*     */     
/*     */     public boolean hasType() {
/*     */       return ((this.bitField0_ & 0x1) == 1);
/*     */     }
/*     */     
/*     */     public String getType() {
/*     */       Object ref = this.type_;
/*     */       if (ref instanceof String)
/*     */         return (String)ref; 
/*     */       ByteString bs = (ByteString)ref;
/*     */       String s = bs.toStringUtf8();
/*     */       if (Internal.isValidUtf8(bs))
/*     */         this.type_ = s; 
/*     */       return s;
/*     */     }
/*     */     
/*     */     private ByteString getTypeBytes() {
/*     */       Object ref = this.type_;
/*     */       if (ref instanceof String) {
/*     */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*     */         this.type_ = b;
/*     */         return b;
/*     */       } 
/*     */       return (ByteString)ref;
/*     */     }
/*     */     
/*     */     public boolean hasIndexdata() {
/*     */       return ((this.bitField0_ & 0x2) == 2);
/*     */     }
/*     */     
/*     */     public ByteString getIndexdata() {
/*     */       return this.indexdata_;
/*     */     }
/*     */     
/*     */     public boolean hasDatasize() {
/*     */       return ((this.bitField0_ & 0x4) == 4);
/*     */     }
/*     */     
/*     */     public int getDatasize() {
/*     */       return this.datasize_;
/*     */     }
/*     */     
/*     */     private void initFields() {
/*     */       this.type_ = "";
/*     */       this.indexdata_ = ByteString.EMPTY;
/*     */       this.datasize_ = 0;
/*     */     }
/*     */     
/*     */     public final boolean isInitialized() {
/*     */       byte isInitialized = this.memoizedIsInitialized;
/*     */       if (isInitialized != -1)
/*     */         return (isInitialized == 1); 
/*     */       if (!hasType()) {
/*     */         this.memoizedIsInitialized = 0;
/*     */         return false;
/*     */       } 
/*     */       if (!hasDatasize()) {
/*     */         this.memoizedIsInitialized = 0;
/*     */         return false;
/*     */       } 
/*     */       this.memoizedIsInitialized = 1;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public void writeTo(CodedOutputStream output) throws IOException {
/*     */       getSerializedSize();
/*     */       if ((this.bitField0_ & 0x1) == 1)
/*     */         output.writeBytes(1, getTypeBytes()); 
/*     */       if ((this.bitField0_ & 0x2) == 2)
/*     */         output.writeBytes(2, this.indexdata_); 
/*     */       if ((this.bitField0_ & 0x4) == 4)
/*     */         output.writeInt32(3, this.datasize_); 
/*     */     }
/*     */     
/*     */     public int getSerializedSize() {
/* 658 */       int size = this.memoizedSerializedSize;
/* 659 */       if (size != -1)
/* 659 */         return size; 
/* 661 */       size = 0;
/* 662 */       if ((this.bitField0_ & 0x1) == 1)
/* 663 */         size += CodedOutputStream.computeBytesSize(1, getTypeBytes()); 
/* 666 */       if ((this.bitField0_ & 0x2) == 2)
/* 667 */         size += CodedOutputStream.computeBytesSize(2, this.indexdata_); 
/* 670 */       if ((this.bitField0_ & 0x4) == 4)
/* 671 */         size += CodedOutputStream.computeInt32Size(3, this.datasize_); 
/* 674 */       this.memoizedSerializedSize = size;
/* 675 */       return size;
/*     */     }
/*     */     
/*     */     protected Object writeReplace() throws ObjectStreamException {
/* 682 */       return super.writeReplace();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 688 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 694 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 699 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 705 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(InputStream input) throws IOException {
/* 710 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 716 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseDelimitedFrom(InputStream input) throws IOException {
/* 721 */       Builder builder = newBuilder();
/* 722 */       if (builder.mergeDelimitedFrom(input))
/* 723 */         return builder.buildParsed(); 
/* 725 */       return null;
/*     */     }
/*     */     
/*     */     public static BlobHeader parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 732 */       Builder builder = newBuilder();
/* 733 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 734 */         return builder.buildParsed(); 
/* 736 */       return null;
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(CodedInputStream input) throws IOException {
/* 742 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*     */     }
/*     */     
/*     */     public static BlobHeader parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 748 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*     */     }
/*     */     
/*     */     public static Builder newBuilder() {
/* 752 */       return Builder.create();
/*     */     }
/*     */     
/*     */     public Builder newBuilderForType() {
/* 753 */       return newBuilder();
/*     */     }
/*     */     
/*     */     public static Builder newBuilder(BlobHeader prototype) {
/* 755 */       return newBuilder().mergeFrom(prototype);
/*     */     }
/*     */     
/*     */     public Builder toBuilder() {
/* 757 */       return newBuilder(this);
/*     */     }
/*     */     
/*     */     public static final class Builder extends GeneratedMessageLite.Builder<BlobHeader, Builder> implements Fileformat.BlobHeaderOrBuilder {
/*     */       private int bitField0_;
/*     */       
/*     */       private Object type_;
/*     */       
/*     */       private ByteString indexdata_;
/*     */       
/*     */       private int datasize_;
/*     */       
/*     */       private Builder() {
/* 896 */         this.type_ = "";
/* 932 */         this.indexdata_ = ByteString.EMPTY;
/*     */         maybeForceBuilderInitialization();
/*     */       }
/*     */       
/*     */       private void maybeForceBuilderInitialization() {}
/*     */       
/*     */       private static Builder create() {
/*     */         return new Builder();
/*     */       }
/*     */       
/*     */       public Builder clear() {
/*     */         super.clear();
/*     */         this.type_ = "";
/*     */         this.bitField0_ &= 0xFFFFFFFE;
/*     */         this.indexdata_ = ByteString.EMPTY;
/*     */         this.bitField0_ &= 0xFFFFFFFD;
/*     */         this.datasize_ = 0;
/*     */         this.bitField0_ &= 0xFFFFFFFB;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clone() {
/*     */         return create().mergeFrom(buildPartial());
/*     */       }
/*     */       
/*     */       public Fileformat.BlobHeader getDefaultInstanceForType() {
/*     */         return Fileformat.BlobHeader.getDefaultInstance();
/*     */       }
/*     */       
/*     */       public Fileformat.BlobHeader build() {
/*     */         Fileformat.BlobHeader result = buildPartial();
/*     */         if (!result.isInitialized())
/*     */           throw newUninitializedMessageException(result); 
/*     */         return result;
/*     */       }
/*     */       
/*     */       private Fileformat.BlobHeader buildParsed() throws InvalidProtocolBufferException {
/*     */         Fileformat.BlobHeader result = buildPartial();
/*     */         if (!result.isInitialized())
/*     */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*     */         return result;
/*     */       }
/*     */       
/*     */       public Fileformat.BlobHeader buildPartial() {
/*     */         Fileformat.BlobHeader result = new Fileformat.BlobHeader(this);
/*     */         int from_bitField0_ = this.bitField0_;
/*     */         int to_bitField0_ = 0;
/*     */         if ((from_bitField0_ & 0x1) == 1)
/*     */           to_bitField0_ |= 0x1; 
/*     */         result.type_ = this.type_;
/*     */         if ((from_bitField0_ & 0x2) == 2)
/*     */           to_bitField0_ |= 0x2; 
/*     */         result.indexdata_ = this.indexdata_;
/*     */         if ((from_bitField0_ & 0x4) == 4)
/*     */           to_bitField0_ |= 0x4; 
/*     */         result.datasize_ = this.datasize_;
/*     */         result.bitField0_ = to_bitField0_;
/*     */         return result;
/*     */       }
/*     */       
/*     */       public Builder mergeFrom(Fileformat.BlobHeader other) {
/*     */         if (other == Fileformat.BlobHeader.getDefaultInstance())
/*     */           return this; 
/*     */         if (other.hasType())
/*     */           setType(other.getType()); 
/*     */         if (other.hasIndexdata())
/*     */           setIndexdata(other.getIndexdata()); 
/*     */         if (other.hasDatasize())
/*     */           setDatasize(other.getDatasize()); 
/*     */         return this;
/*     */       }
/*     */       
/*     */       public final boolean isInitialized() {
/*     */         if (!hasType())
/*     */           return false; 
/*     */         if (!hasDatasize())
/*     */           return false; 
/*     */         return true;
/*     */       }
/*     */       
/*     */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*     */         while (true) {
/*     */           int tag = input.readTag();
/*     */           switch (tag) {
/*     */             case 0:
/*     */               return this;
/*     */             case 10:
/*     */               this.bitField0_ |= 0x1;
/*     */               this.type_ = input.readBytes();
/*     */               break;
/*     */             case 18:
/*     */               this.bitField0_ |= 0x2;
/*     */               this.indexdata_ = input.readBytes();
/*     */               break;
/*     */             case 24:
/*     */               this.bitField0_ |= 0x4;
/*     */               this.datasize_ = input.readInt32();
/*     */               break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public boolean hasType() {
/*     */         return ((this.bitField0_ & 0x1) == 1);
/*     */       }
/*     */       
/*     */       public String getType() {
/*     */         Object ref = this.type_;
/*     */         if (!(ref instanceof String)) {
/*     */           String s = ((ByteString)ref).toStringUtf8();
/*     */           this.type_ = s;
/*     */           return s;
/*     */         } 
/*     */         return (String)ref;
/*     */       }
/*     */       
/*     */       public Builder setType(String value) {
/*     */         if (value == null)
/*     */           throw new NullPointerException(); 
/*     */         this.bitField0_ |= 0x1;
/*     */         this.type_ = value;
/*     */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearType() {
/*     */         this.bitField0_ &= 0xFFFFFFFE;
/*     */         this.type_ = Fileformat.BlobHeader.getDefaultInstance().getType();
/*     */         return this;
/*     */       }
/*     */       
/*     */       void setType(ByteString value) {
/*     */         this.bitField0_ |= 0x1;
/*     */         this.type_ = value;
/*     */       }
/*     */       
/*     */       public boolean hasIndexdata() {
/* 934 */         return ((this.bitField0_ & 0x2) == 2);
/*     */       }
/*     */       
/*     */       public ByteString getIndexdata() {
/* 937 */         return this.indexdata_;
/*     */       }
/*     */       
/*     */       public Builder setIndexdata(ByteString value) {
/* 940 */         if (value == null)
/* 941 */           throw new NullPointerException(); 
/* 943 */         this.bitField0_ |= 0x2;
/* 944 */         this.indexdata_ = value;
/* 946 */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearIndexdata() {
/* 949 */         this.bitField0_ &= 0xFFFFFFFD;
/* 950 */         this.indexdata_ = Fileformat.BlobHeader.getDefaultInstance().getIndexdata();
/* 952 */         return this;
/*     */       }
/*     */       
/*     */       public boolean hasDatasize() {
/* 958 */         return ((this.bitField0_ & 0x4) == 4);
/*     */       }
/*     */       
/*     */       public int getDatasize() {
/* 961 */         return this.datasize_;
/*     */       }
/*     */       
/*     */       public Builder setDatasize(int value) {
/* 964 */         this.bitField0_ |= 0x4;
/* 965 */         this.datasize_ = value;
/* 967 */         return this;
/*     */       }
/*     */       
/*     */       public Builder clearDatasize() {
/* 970 */         this.bitField0_ &= 0xFFFFFFFB;
/* 971 */         this.datasize_ = 0;
/* 973 */         return this;
/*     */       }
/*     */     }
/*     */     
/* 980 */     private static final BlobHeader defaultInstance = new BlobHeader(true);
/*     */     
/*     */     private int bitField0_;
/*     */     
/*     */     public static final int TYPE_FIELD_NUMBER = 1;
/*     */     
/*     */     private Object type_;
/*     */     
/*     */     public static final int INDEXDATA_FIELD_NUMBER = 2;
/*     */     
/*     */     private ByteString indexdata_;
/*     */     
/*     */     public static final int DATASIZE_FIELD_NUMBER = 3;
/*     */     
/*     */     private int datasize_;
/*     */     
/*     */     private byte memoizedIsInitialized;
/*     */     
/*     */     private int memoizedSerializedSize;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     static {
/* 981 */       defaultInstance.initFields();
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\Fileformat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */