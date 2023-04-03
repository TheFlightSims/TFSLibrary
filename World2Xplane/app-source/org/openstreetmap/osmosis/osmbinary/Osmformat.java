/*      */ package org.openstreetmap.osmosis.osmbinary;
/*      */ 
/*      */ import com.google.protobuf.AbstractMessageLite;
/*      */ import com.google.protobuf.ByteString;
/*      */ import com.google.protobuf.CodedInputStream;
/*      */ import com.google.protobuf.CodedOutputStream;
/*      */ import com.google.protobuf.ExtensionRegistryLite;
/*      */ import com.google.protobuf.GeneratedMessageLite;
/*      */ import com.google.protobuf.Internal;
/*      */ import com.google.protobuf.InvalidProtocolBufferException;
/*      */ import com.google.protobuf.LazyStringArrayList;
/*      */ import com.google.protobuf.LazyStringList;
/*      */ import com.google.protobuf.MessageLite;
/*      */ import com.google.protobuf.MessageLiteOrBuilder;
/*      */ import com.google.protobuf.UnmodifiableLazyStringList;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ 
/*      */ public final class Osmformat {
/*      */   public static void registerAllExtensions(ExtensionRegistryLite registry) {}
/*      */   
/*      */   public static interface HeaderBlockOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasBbox();
/*      */     
/*      */     Osmformat.HeaderBBox getBbox();
/*      */     
/*      */     List<String> getRequiredFeaturesList();
/*      */     
/*      */     int getRequiredFeaturesCount();
/*      */     
/*      */     String getRequiredFeatures(int param1Int);
/*      */     
/*      */     List<String> getOptionalFeaturesList();
/*      */     
/*      */     int getOptionalFeaturesCount();
/*      */     
/*      */     String getOptionalFeatures(int param1Int);
/*      */     
/*      */     boolean hasWritingprogram();
/*      */     
/*      */     String getWritingprogram();
/*      */     
/*      */     boolean hasSource();
/*      */     
/*      */     String getSource();
/*      */     
/*      */     boolean hasOsmosisReplicationTimestamp();
/*      */     
/*      */     long getOsmosisReplicationTimestamp();
/*      */     
/*      */     boolean hasOsmosisReplicationSequenceNumber();
/*      */     
/*      */     long getOsmosisReplicationSequenceNumber();
/*      */     
/*      */     boolean hasOsmosisReplicationBaseUrl();
/*      */     
/*      */     String getOsmosisReplicationBaseUrl();
/*      */   }
/*      */   
/*      */   public static final class HeaderBlock extends GeneratedMessageLite implements HeaderBlockOrBuilder {
/*      */     private HeaderBlock(Builder builder) {
/*   53 */       super(builder);
/*  231 */       this.memoizedIsInitialized = -1;
/*  275 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private HeaderBlock(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/*  275 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static HeaderBlock getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public HeaderBlock getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasBbox() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public Osmformat.HeaderBBox getBbox() {
/*      */       return this.bbox_;
/*      */     }
/*      */     
/*      */     public List<String> getRequiredFeaturesList() {
/*      */       return (List<String>)this.requiredFeatures_;
/*      */     }
/*      */     
/*      */     public int getRequiredFeaturesCount() {
/*      */       return this.requiredFeatures_.size();
/*      */     }
/*      */     
/*      */     public String getRequiredFeatures(int index) {
/*      */       return (String)this.requiredFeatures_.get(index);
/*      */     }
/*      */     
/*      */     public List<String> getOptionalFeaturesList() {
/*      */       return (List<String>)this.optionalFeatures_;
/*      */     }
/*      */     
/*      */     public int getOptionalFeaturesCount() {
/*      */       return this.optionalFeatures_.size();
/*      */     }
/*      */     
/*      */     public String getOptionalFeatures(int index) {
/*      */       return (String)this.optionalFeatures_.get(index);
/*      */     }
/*      */     
/*      */     public boolean hasWritingprogram() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public String getWritingprogram() {
/*      */       Object ref = this.writingprogram_;
/*      */       if (ref instanceof String)
/*      */         return (String)ref; 
/*      */       ByteString bs = (ByteString)ref;
/*      */       String s = bs.toStringUtf8();
/*      */       if (Internal.isValidUtf8(bs))
/*      */         this.writingprogram_ = s; 
/*      */       return s;
/*      */     }
/*      */     
/*      */     private ByteString getWritingprogramBytes() {
/*      */       Object ref = this.writingprogram_;
/*      */       if (ref instanceof String) {
/*      */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         this.writingprogram_ = b;
/*      */         return b;
/*      */       } 
/*      */       return (ByteString)ref;
/*      */     }
/*      */     
/*      */     public boolean hasSource() {
/*      */       return ((this.bitField0_ & 0x4) == 4);
/*      */     }
/*      */     
/*      */     public String getSource() {
/*      */       Object ref = this.source_;
/*      */       if (ref instanceof String)
/*      */         return (String)ref; 
/*      */       ByteString bs = (ByteString)ref;
/*      */       String s = bs.toStringUtf8();
/*      */       if (Internal.isValidUtf8(bs))
/*      */         this.source_ = s; 
/*      */       return s;
/*      */     }
/*      */     
/*      */     private ByteString getSourceBytes() {
/*      */       Object ref = this.source_;
/*      */       if (ref instanceof String) {
/*      */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         this.source_ = b;
/*      */         return b;
/*      */       } 
/*      */       return (ByteString)ref;
/*      */     }
/*      */     
/*      */     public boolean hasOsmosisReplicationTimestamp() {
/*      */       return ((this.bitField0_ & 0x8) == 8);
/*      */     }
/*      */     
/*      */     public long getOsmosisReplicationTimestamp() {
/*      */       return this.osmosisReplicationTimestamp_;
/*      */     }
/*      */     
/*      */     public boolean hasOsmosisReplicationSequenceNumber() {
/*      */       return ((this.bitField0_ & 0x10) == 16);
/*      */     }
/*      */     
/*      */     public long getOsmosisReplicationSequenceNumber() {
/*      */       return this.osmosisReplicationSequenceNumber_;
/*      */     }
/*      */     
/*      */     public boolean hasOsmosisReplicationBaseUrl() {
/*      */       return ((this.bitField0_ & 0x20) == 32);
/*      */     }
/*      */     
/*      */     public String getOsmosisReplicationBaseUrl() {
/*      */       Object ref = this.osmosisReplicationBaseUrl_;
/*      */       if (ref instanceof String)
/*      */         return (String)ref; 
/*      */       ByteString bs = (ByteString)ref;
/*      */       String s = bs.toStringUtf8();
/*      */       if (Internal.isValidUtf8(bs))
/*      */         this.osmosisReplicationBaseUrl_ = s; 
/*      */       return s;
/*      */     }
/*      */     
/*      */     private ByteString getOsmosisReplicationBaseUrlBytes() {
/*      */       Object ref = this.osmosisReplicationBaseUrl_;
/*      */       if (ref instanceof String) {
/*      */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*      */         this.osmosisReplicationBaseUrl_ = b;
/*      */         return b;
/*      */       } 
/*      */       return (ByteString)ref;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.bbox_ = Osmformat.HeaderBBox.getDefaultInstance();
/*      */       this.requiredFeatures_ = LazyStringArrayList.EMPTY;
/*      */       this.optionalFeatures_ = LazyStringArrayList.EMPTY;
/*      */       this.writingprogram_ = "";
/*      */       this.source_ = "";
/*      */       this.osmosisReplicationTimestamp_ = 0L;
/*      */       this.osmosisReplicationSequenceNumber_ = 0L;
/*      */       this.osmosisReplicationBaseUrl_ = "";
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (hasBbox() && !getBbox().isInitialized()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeMessage(1, (MessageLite)this.bbox_); 
/*      */       int i;
/*      */       for (i = 0; i < this.requiredFeatures_.size(); i++)
/*      */         output.writeBytes(4, this.requiredFeatures_.getByteString(i)); 
/*      */       for (i = 0; i < this.optionalFeatures_.size(); i++)
/*      */         output.writeBytes(5, this.optionalFeatures_.getByteString(i)); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeBytes(16, getWritingprogramBytes()); 
/*      */       if ((this.bitField0_ & 0x4) == 4)
/*      */         output.writeBytes(17, getSourceBytes()); 
/*      */       if ((this.bitField0_ & 0x8) == 8)
/*      */         output.writeInt64(32, this.osmosisReplicationTimestamp_); 
/*      */       if ((this.bitField0_ & 0x10) == 16)
/*      */         output.writeInt64(33, this.osmosisReplicationSequenceNumber_); 
/*      */       if ((this.bitField0_ & 0x20) == 32)
/*      */         output.writeBytes(34, getOsmosisReplicationBaseUrlBytes()); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/*  277 */       int size = this.memoizedSerializedSize;
/*  278 */       if (size != -1)
/*  278 */         return size; 
/*  280 */       size = 0;
/*  281 */       if ((this.bitField0_ & 0x1) == 1)
/*  282 */         size += CodedOutputStream.computeMessageSize(1, (MessageLite)this.bbox_); 
/*  286 */       int dataSize = 0;
/*      */       int i;
/*  287 */       for (i = 0; i < this.requiredFeatures_.size(); i++)
/*  288 */         dataSize += CodedOutputStream.computeBytesSizeNoTag(this.requiredFeatures_.getByteString(i)); 
/*  291 */       size += dataSize;
/*  292 */       size += 1 * getRequiredFeaturesList().size();
/*  295 */       dataSize = 0;
/*  296 */       for (i = 0; i < this.optionalFeatures_.size(); i++)
/*  297 */         dataSize += CodedOutputStream.computeBytesSizeNoTag(this.optionalFeatures_.getByteString(i)); 
/*  300 */       size += dataSize;
/*  301 */       size += 1 * getOptionalFeaturesList().size();
/*  303 */       if ((this.bitField0_ & 0x2) == 2)
/*  304 */         size += CodedOutputStream.computeBytesSize(16, getWritingprogramBytes()); 
/*  307 */       if ((this.bitField0_ & 0x4) == 4)
/*  308 */         size += CodedOutputStream.computeBytesSize(17, getSourceBytes()); 
/*  311 */       if ((this.bitField0_ & 0x8) == 8)
/*  312 */         size += CodedOutputStream.computeInt64Size(32, this.osmosisReplicationTimestamp_); 
/*  315 */       if ((this.bitField0_ & 0x10) == 16)
/*  316 */         size += CodedOutputStream.computeInt64Size(33, this.osmosisReplicationSequenceNumber_); 
/*  319 */       if ((this.bitField0_ & 0x20) == 32)
/*  320 */         size += CodedOutputStream.computeBytesSize(34, getOsmosisReplicationBaseUrlBytes()); 
/*  323 */       this.memoizedSerializedSize = size;
/*  324 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/*  331 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  337 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  343 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  348 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  354 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(InputStream input) throws IOException {
/*  359 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  365 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseDelimitedFrom(InputStream input) throws IOException {
/*  370 */       Builder builder = newBuilder();
/*  371 */       if (builder.mergeDelimitedFrom(input))
/*  372 */         return builder.buildParsed(); 
/*  374 */       return null;
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  381 */       Builder builder = newBuilder();
/*  382 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  383 */         return builder.buildParsed(); 
/*  385 */       return null;
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(CodedInputStream input) throws IOException {
/*  391 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBlock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  397 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/*  401 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/*  402 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(HeaderBlock prototype) {
/*  404 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/*  406 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<HeaderBlock, Builder> implements Osmformat.HeaderBlockOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private Osmformat.HeaderBBox bbox_;
/*      */       
/*      */       private LazyStringList requiredFeatures_;
/*      */       
/*      */       private LazyStringList optionalFeatures_;
/*      */       
/*      */       private Object writingprogram_;
/*      */       
/*      */       private Object source_;
/*      */       
/*      */       private long osmosisReplicationTimestamp_;
/*      */       
/*      */       private long osmosisReplicationSequenceNumber_;
/*      */       
/*      */       private Object osmosisReplicationBaseUrl_;
/*      */       
/*      */       private Builder() {
/*  635 */         this.bbox_ = Osmformat.HeaderBBox.getDefaultInstance();
/*  678 */         this.requiredFeatures_ = LazyStringArrayList.EMPTY;
/*  734 */         this.optionalFeatures_ = LazyStringArrayList.EMPTY;
/*  790 */         this.writingprogram_ = "";
/*  826 */         this.source_ = "";
/*  904 */         this.osmosisReplicationBaseUrl_ = "";
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.bbox_ = Osmformat.HeaderBBox.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.requiredFeatures_ = LazyStringArrayList.EMPTY;
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.optionalFeatures_ = LazyStringArrayList.EMPTY;
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.writingprogram_ = "";
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.source_ = "";
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.osmosisReplicationTimestamp_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         this.osmosisReplicationSequenceNumber_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFBF;
/*      */         this.osmosisReplicationBaseUrl_ = "";
/*      */         this.bitField0_ &= 0xFFFFFF7F;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBlock getDefaultInstanceForType() {
/*      */         return Osmformat.HeaderBlock.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBlock build() {
/*      */         Osmformat.HeaderBlock result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.HeaderBlock buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.HeaderBlock result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBlock buildPartial() {
/*      */         Osmformat.HeaderBlock result = new Osmformat.HeaderBlock(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.bbox_ = this.bbox_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.requiredFeatures_ = (LazyStringList)new UnmodifiableLazyStringList(this.requiredFeatures_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.requiredFeatures_ = this.requiredFeatures_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.optionalFeatures_ = (LazyStringList)new UnmodifiableLazyStringList(this.optionalFeatures_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.optionalFeatures_ = this.optionalFeatures_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.writingprogram_ = this.writingprogram_;
/*      */         if ((from_bitField0_ & 0x10) == 16)
/*      */           to_bitField0_ |= 0x4; 
/*      */         result.source_ = this.source_;
/*      */         if ((from_bitField0_ & 0x20) == 32)
/*      */           to_bitField0_ |= 0x8; 
/*      */         result.osmosisReplicationTimestamp_ = this.osmosisReplicationTimestamp_;
/*      */         if ((from_bitField0_ & 0x40) == 64)
/*      */           to_bitField0_ |= 0x10; 
/*      */         result.osmosisReplicationSequenceNumber_ = this.osmosisReplicationSequenceNumber_;
/*      */         if ((from_bitField0_ & 0x80) == 128)
/*      */           to_bitField0_ |= 0x20; 
/*      */         result.osmosisReplicationBaseUrl_ = this.osmosisReplicationBaseUrl_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.HeaderBlock other) {
/*      */         if (other == Osmformat.HeaderBlock.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasBbox())
/*      */           mergeBbox(other.getBbox()); 
/*      */         if (!other.requiredFeatures_.isEmpty())
/*      */           if (this.requiredFeatures_.isEmpty()) {
/*      */             this.requiredFeatures_ = other.requiredFeatures_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensureRequiredFeaturesIsMutable();
/*      */             this.requiredFeatures_.addAll((Collection)other.requiredFeatures_);
/*      */           }  
/*      */         if (!other.optionalFeatures_.isEmpty())
/*      */           if (this.optionalFeatures_.isEmpty()) {
/*      */             this.optionalFeatures_ = other.optionalFeatures_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureOptionalFeaturesIsMutable();
/*      */             this.optionalFeatures_.addAll((Collection)other.optionalFeatures_);
/*      */           }  
/*      */         if (other.hasWritingprogram())
/*      */           setWritingprogram(other.getWritingprogram()); 
/*      */         if (other.hasSource())
/*      */           setSource(other.getSource()); 
/*      */         if (other.hasOsmosisReplicationTimestamp())
/*      */           setOsmosisReplicationTimestamp(other.getOsmosisReplicationTimestamp()); 
/*      */         if (other.hasOsmosisReplicationSequenceNumber())
/*      */           setOsmosisReplicationSequenceNumber(other.getOsmosisReplicationSequenceNumber()); 
/*      */         if (other.hasOsmosisReplicationBaseUrl())
/*      */           setOsmosisReplicationBaseUrl(other.getOsmosisReplicationBaseUrl()); 
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         if (hasBbox() && !getBbox().isInitialized())
/*      */           return false; 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           Osmformat.HeaderBBox.Builder subBuilder;
/*      */           int tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 10:
/*      */               subBuilder = Osmformat.HeaderBBox.newBuilder();
/*      */               if (hasBbox())
/*      */                 subBuilder.mergeFrom(getBbox()); 
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               setBbox(subBuilder.buildPartial());
/*      */               break;
/*      */             case 34:
/*      */               ensureRequiredFeaturesIsMutable();
/*      */               this.requiredFeatures_.add(input.readBytes());
/*      */               break;
/*      */             case 42:
/*      */               ensureOptionalFeaturesIsMutable();
/*      */               this.optionalFeatures_.add(input.readBytes());
/*      */               break;
/*      */             case 130:
/*      */               this.bitField0_ |= 0x8;
/*      */               this.writingprogram_ = input.readBytes();
/*      */               break;
/*      */             case 138:
/*      */               this.bitField0_ |= 0x10;
/*      */               this.source_ = input.readBytes();
/*      */               break;
/*      */             case 256:
/*      */               this.bitField0_ |= 0x20;
/*      */               this.osmosisReplicationTimestamp_ = input.readInt64();
/*      */               break;
/*      */             case 264:
/*      */               this.bitField0_ |= 0x40;
/*      */               this.osmosisReplicationSequenceNumber_ = input.readInt64();
/*      */               break;
/*      */             case 274:
/*      */               this.bitField0_ |= 0x80;
/*      */               this.osmosisReplicationBaseUrl_ = input.readBytes();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasBbox() {
/*      */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBBox getBbox() {
/*      */         return this.bbox_;
/*      */       }
/*      */       
/*      */       public Builder setBbox(Osmformat.HeaderBBox value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.bbox_ = value;
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setBbox(Osmformat.HeaderBBox.Builder builderForValue) {
/*      */         this.bbox_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeBbox(Osmformat.HeaderBBox value) {
/*      */         if ((this.bitField0_ & 0x1) == 1 && this.bbox_ != Osmformat.HeaderBBox.getDefaultInstance()) {
/*      */           this.bbox_ = Osmformat.HeaderBBox.newBuilder(this.bbox_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.bbox_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearBbox() {
/*      */         this.bbox_ = Osmformat.HeaderBBox.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureRequiredFeaturesIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.requiredFeatures_ = (LazyStringList)new LazyStringArrayList((List)this.requiredFeatures_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<String> getRequiredFeaturesList() {
/*      */         return Collections.unmodifiableList((List<? extends String>)this.requiredFeatures_);
/*      */       }
/*      */       
/*      */       public int getRequiredFeaturesCount() {
/*      */         return this.requiredFeatures_.size();
/*      */       }
/*      */       
/*      */       public String getRequiredFeatures(int index) {
/*      */         return (String)this.requiredFeatures_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setRequiredFeatures(int index, String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureRequiredFeaturesIsMutable();
/*      */         this.requiredFeatures_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRequiredFeatures(String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureRequiredFeaturesIsMutable();
/*      */         this.requiredFeatures_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllRequiredFeatures(Iterable<String> values) {
/*      */         ensureRequiredFeaturesIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, (Collection)this.requiredFeatures_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearRequiredFeatures() {
/*      */         this.requiredFeatures_ = LazyStringArrayList.EMPTY;
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       void addRequiredFeatures(ByteString value) {
/*      */         ensureRequiredFeaturesIsMutable();
/*      */         this.requiredFeatures_.add(value);
/*      */       }
/*      */       
/*      */       private void ensureOptionalFeaturesIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.optionalFeatures_ = (LazyStringList)new LazyStringArrayList((List)this.optionalFeatures_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<String> getOptionalFeaturesList() {
/*      */         return Collections.unmodifiableList((List<? extends String>)this.optionalFeatures_);
/*      */       }
/*      */       
/*      */       public int getOptionalFeaturesCount() {
/*      */         return this.optionalFeatures_.size();
/*      */       }
/*      */       
/*      */       public String getOptionalFeatures(int index) {
/*      */         return (String)this.optionalFeatures_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setOptionalFeatures(int index, String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureOptionalFeaturesIsMutable();
/*      */         this.optionalFeatures_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addOptionalFeatures(String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureOptionalFeaturesIsMutable();
/*      */         this.optionalFeatures_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllOptionalFeatures(Iterable<String> values) {
/*      */         ensureOptionalFeaturesIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, (Collection)this.optionalFeatures_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearOptionalFeatures() {
/*      */         this.optionalFeatures_ = LazyStringArrayList.EMPTY;
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       void addOptionalFeatures(ByteString value) {
/*      */         ensureOptionalFeaturesIsMutable();
/*      */         this.optionalFeatures_.add(value);
/*      */       }
/*      */       
/*      */       public boolean hasWritingprogram() {
/*      */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public String getWritingprogram() {
/*      */         Object ref = this.writingprogram_;
/*      */         if (!(ref instanceof String)) {
/*      */           String s = ((ByteString)ref).toStringUtf8();
/*      */           this.writingprogram_ = s;
/*      */           return s;
/*      */         } 
/*      */         return (String)ref;
/*      */       }
/*      */       
/*      */       public Builder setWritingprogram(String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.bitField0_ |= 0x8;
/*      */         this.writingprogram_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearWritingprogram() {
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.writingprogram_ = Osmformat.HeaderBlock.getDefaultInstance().getWritingprogram();
/*      */         return this;
/*      */       }
/*      */       
/*      */       void setWritingprogram(ByteString value) {
/*      */         this.bitField0_ |= 0x8;
/*      */         this.writingprogram_ = value;
/*      */       }
/*      */       
/*      */       public boolean hasSource() {
/*      */         return ((this.bitField0_ & 0x10) == 16);
/*      */       }
/*      */       
/*      */       public String getSource() {
/*      */         Object ref = this.source_;
/*      */         if (!(ref instanceof String)) {
/*      */           String s = ((ByteString)ref).toStringUtf8();
/*      */           this.source_ = s;
/*      */           return s;
/*      */         } 
/*      */         return (String)ref;
/*      */       }
/*      */       
/*      */       public Builder setSource(String value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.bitField0_ |= 0x10;
/*      */         this.source_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearSource() {
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.source_ = Osmformat.HeaderBlock.getDefaultInstance().getSource();
/*      */         return this;
/*      */       }
/*      */       
/*      */       void setSource(ByteString value) {
/*      */         this.bitField0_ |= 0x10;
/*      */         this.source_ = value;
/*      */       }
/*      */       
/*      */       public boolean hasOsmosisReplicationTimestamp() {
/*      */         return ((this.bitField0_ & 0x20) == 32);
/*      */       }
/*      */       
/*      */       public long getOsmosisReplicationTimestamp() {
/*      */         return this.osmosisReplicationTimestamp_;
/*      */       }
/*      */       
/*      */       public Builder setOsmosisReplicationTimestamp(long value) {
/*      */         this.bitField0_ |= 0x20;
/*      */         this.osmosisReplicationTimestamp_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearOsmosisReplicationTimestamp() {
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         this.osmosisReplicationTimestamp_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasOsmosisReplicationSequenceNumber() {
/*      */         return ((this.bitField0_ & 0x40) == 64);
/*      */       }
/*      */       
/*      */       public long getOsmosisReplicationSequenceNumber() {
/*      */         return this.osmosisReplicationSequenceNumber_;
/*      */       }
/*      */       
/*      */       public Builder setOsmosisReplicationSequenceNumber(long value) {
/*      */         this.bitField0_ |= 0x40;
/*      */         this.osmosisReplicationSequenceNumber_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearOsmosisReplicationSequenceNumber() {
/*      */         this.bitField0_ &= 0xFFFFFFBF;
/*      */         this.osmosisReplicationSequenceNumber_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasOsmosisReplicationBaseUrl() {
/*  906 */         return ((this.bitField0_ & 0x80) == 128);
/*      */       }
/*      */       
/*      */       public String getOsmosisReplicationBaseUrl() {
/*  909 */         Object ref = this.osmosisReplicationBaseUrl_;
/*  910 */         if (!(ref instanceof String)) {
/*  911 */           String s = ((ByteString)ref).toStringUtf8();
/*  912 */           this.osmosisReplicationBaseUrl_ = s;
/*  913 */           return s;
/*      */         } 
/*  915 */         return (String)ref;
/*      */       }
/*      */       
/*      */       public Builder setOsmosisReplicationBaseUrl(String value) {
/*  919 */         if (value == null)
/*  920 */           throw new NullPointerException(); 
/*  922 */         this.bitField0_ |= 0x80;
/*  923 */         this.osmosisReplicationBaseUrl_ = value;
/*  925 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearOsmosisReplicationBaseUrl() {
/*  928 */         this.bitField0_ &= 0xFFFFFF7F;
/*  929 */         this.osmosisReplicationBaseUrl_ = Osmformat.HeaderBlock.getDefaultInstance().getOsmosisReplicationBaseUrl();
/*  931 */         return this;
/*      */       }
/*      */       
/*      */       void setOsmosisReplicationBaseUrl(ByteString value) {
/*  934 */         this.bitField0_ |= 0x80;
/*  935 */         this.osmosisReplicationBaseUrl_ = value;
/*      */       }
/*      */     }
/*      */     
/*  943 */     private static final HeaderBlock defaultInstance = new HeaderBlock(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int BBOX_FIELD_NUMBER = 1;
/*      */     
/*      */     private Osmformat.HeaderBBox bbox_;
/*      */     
/*      */     public static final int REQUIRED_FEATURES_FIELD_NUMBER = 4;
/*      */     
/*      */     private LazyStringList requiredFeatures_;
/*      */     
/*      */     public static final int OPTIONAL_FEATURES_FIELD_NUMBER = 5;
/*      */     
/*      */     private LazyStringList optionalFeatures_;
/*      */     
/*      */     public static final int WRITINGPROGRAM_FIELD_NUMBER = 16;
/*      */     
/*      */     private Object writingprogram_;
/*      */     
/*      */     public static final int SOURCE_FIELD_NUMBER = 17;
/*      */     
/*      */     private Object source_;
/*      */     
/*      */     public static final int OSMOSIS_REPLICATION_TIMESTAMP_FIELD_NUMBER = 32;
/*      */     
/*      */     private long osmosisReplicationTimestamp_;
/*      */     
/*      */     public static final int OSMOSIS_REPLICATION_SEQUENCE_NUMBER_FIELD_NUMBER = 33;
/*      */     
/*      */     private long osmosisReplicationSequenceNumber_;
/*      */     
/*      */     public static final int OSMOSIS_REPLICATION_BASE_URL_FIELD_NUMBER = 34;
/*      */     
/*      */     private Object osmosisReplicationBaseUrl_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/*  944 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface HeaderBBoxOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasLeft();
/*      */     
/*      */     long getLeft();
/*      */     
/*      */     boolean hasRight();
/*      */     
/*      */     long getRight();
/*      */     
/*      */     boolean hasTop();
/*      */     
/*      */     long getTop();
/*      */     
/*      */     boolean hasBottom();
/*      */     
/*      */     long getBottom();
/*      */   }
/*      */   
/*      */   public static final class HeaderBBox extends GeneratedMessageLite implements HeaderBBoxOrBuilder {
/*      */     private HeaderBBox(Builder builder) {
/*  974 */       super(builder);
/* 1034 */       this.memoizedIsInitialized = -1;
/* 1076 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private HeaderBBox(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 1076 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static HeaderBBox getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public HeaderBBox getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasLeft() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public long getLeft() {
/*      */       return this.left_;
/*      */     }
/*      */     
/*      */     public boolean hasRight() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public long getRight() {
/*      */       return this.right_;
/*      */     }
/*      */     
/*      */     public boolean hasTop() {
/*      */       return ((this.bitField0_ & 0x4) == 4);
/*      */     }
/*      */     
/*      */     public long getTop() {
/*      */       return this.top_;
/*      */     }
/*      */     
/*      */     public boolean hasBottom() {
/*      */       return ((this.bitField0_ & 0x8) == 8);
/*      */     }
/*      */     
/*      */     public long getBottom() {
/*      */       return this.bottom_;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.left_ = 0L;
/*      */       this.right_ = 0L;
/*      */       this.top_ = 0L;
/*      */       this.bottom_ = 0L;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasLeft()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       if (!hasRight()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       if (!hasTop()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       if (!hasBottom()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeSInt64(1, this.left_); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeSInt64(2, this.right_); 
/*      */       if ((this.bitField0_ & 0x4) == 4)
/*      */         output.writeSInt64(3, this.top_); 
/*      */       if ((this.bitField0_ & 0x8) == 8)
/*      */         output.writeSInt64(4, this.bottom_); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 1078 */       int size = this.memoizedSerializedSize;
/* 1079 */       if (size != -1)
/* 1079 */         return size; 
/* 1081 */       size = 0;
/* 1082 */       if ((this.bitField0_ & 0x1) == 1)
/* 1083 */         size += CodedOutputStream.computeSInt64Size(1, this.left_); 
/* 1086 */       if ((this.bitField0_ & 0x2) == 2)
/* 1087 */         size += CodedOutputStream.computeSInt64Size(2, this.right_); 
/* 1090 */       if ((this.bitField0_ & 0x4) == 4)
/* 1091 */         size += CodedOutputStream.computeSInt64Size(3, this.top_); 
/* 1094 */       if ((this.bitField0_ & 0x8) == 8)
/* 1095 */         size += CodedOutputStream.computeSInt64Size(4, this.bottom_); 
/* 1098 */       this.memoizedSerializedSize = size;
/* 1099 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 1106 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 1112 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1118 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 1123 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1129 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(InputStream input) throws IOException {
/* 1134 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1140 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseDelimitedFrom(InputStream input) throws IOException {
/* 1145 */       Builder builder = newBuilder();
/* 1146 */       if (builder.mergeDelimitedFrom(input))
/* 1147 */         return builder.buildParsed(); 
/* 1149 */       return null;
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1156 */       Builder builder = newBuilder();
/* 1157 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 1158 */         return builder.buildParsed(); 
/* 1160 */       return null;
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(CodedInputStream input) throws IOException {
/* 1166 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static HeaderBBox parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1172 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 1176 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 1177 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(HeaderBBox prototype) {
/* 1179 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 1181 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<HeaderBBox, Builder> implements Osmformat.HeaderBBoxOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private long left_;
/*      */       
/*      */       private long right_;
/*      */       
/*      */       private long top_;
/*      */       
/*      */       private long bottom_;
/*      */       
/*      */       private Builder() {
/* 1189 */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/* 1195 */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/* 1199 */         super.clear();
/* 1200 */         this.left_ = 0L;
/* 1201 */         this.bitField0_ &= 0xFFFFFFFE;
/* 1202 */         this.right_ = 0L;
/* 1203 */         this.bitField0_ &= 0xFFFFFFFD;
/* 1204 */         this.top_ = 0L;
/* 1205 */         this.bitField0_ &= 0xFFFFFFFB;
/* 1206 */         this.bottom_ = 0L;
/* 1207 */         this.bitField0_ &= 0xFFFFFFF7;
/* 1208 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/* 1212 */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBBox getDefaultInstanceForType() {
/* 1216 */         return Osmformat.HeaderBBox.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBBox build() {
/* 1220 */         Osmformat.HeaderBBox result = buildPartial();
/* 1221 */         if (!result.isInitialized())
/* 1222 */           throw newUninitializedMessageException(result); 
/* 1224 */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.HeaderBBox buildParsed() throws InvalidProtocolBufferException {
/* 1229 */         Osmformat.HeaderBBox result = buildPartial();
/* 1230 */         if (!result.isInitialized())
/* 1231 */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/* 1234 */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.HeaderBBox buildPartial() {
/* 1238 */         Osmformat.HeaderBBox result = new Osmformat.HeaderBBox(this);
/* 1239 */         int from_bitField0_ = this.bitField0_;
/* 1240 */         int to_bitField0_ = 0;
/* 1241 */         if ((from_bitField0_ & 0x1) == 1)
/* 1242 */           to_bitField0_ |= 0x1; 
/* 1244 */         result.left_ = this.left_;
/* 1245 */         if ((from_bitField0_ & 0x2) == 2)
/* 1246 */           to_bitField0_ |= 0x2; 
/* 1248 */         result.right_ = this.right_;
/* 1249 */         if ((from_bitField0_ & 0x4) == 4)
/* 1250 */           to_bitField0_ |= 0x4; 
/* 1252 */         result.top_ = this.top_;
/* 1253 */         if ((from_bitField0_ & 0x8) == 8)
/* 1254 */           to_bitField0_ |= 0x8; 
/* 1256 */         result.bottom_ = this.bottom_;
/* 1257 */         result.bitField0_ = to_bitField0_;
/* 1258 */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.HeaderBBox other) {
/* 1262 */         if (other == Osmformat.HeaderBBox.getDefaultInstance())
/* 1262 */           return this; 
/* 1263 */         if (other.hasLeft())
/* 1264 */           setLeft(other.getLeft()); 
/* 1266 */         if (other.hasRight())
/* 1267 */           setRight(other.getRight()); 
/* 1269 */         if (other.hasTop())
/* 1270 */           setTop(other.getTop()); 
/* 1272 */         if (other.hasBottom())
/* 1273 */           setBottom(other.getBottom()); 
/* 1275 */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/* 1279 */         if (!hasLeft())
/* 1281 */           return false; 
/* 1283 */         if (!hasRight())
/* 1285 */           return false; 
/* 1287 */         if (!hasTop())
/* 1289 */           return false; 
/* 1291 */         if (!hasBottom())
/* 1293 */           return false; 
/* 1295 */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/* 1303 */           int tag = input.readTag();
/* 1304 */           switch (tag) {
/*      */             case 0:
/* 1307 */               return this;
/*      */             case 8:
/* 1316 */               this.bitField0_ |= 0x1;
/* 1317 */               this.left_ = input.readSInt64();
/*      */               break;
/*      */             case 16:
/* 1321 */               this.bitField0_ |= 0x2;
/* 1322 */               this.right_ = input.readSInt64();
/*      */               break;
/*      */             case 24:
/* 1326 */               this.bitField0_ |= 0x4;
/* 1327 */               this.top_ = input.readSInt64();
/*      */               break;
/*      */             case 32:
/* 1331 */               this.bitField0_ |= 0x8;
/* 1332 */               this.bottom_ = input.readSInt64();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasLeft() {
/* 1344 */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public long getLeft() {
/* 1347 */         return this.left_;
/*      */       }
/*      */       
/*      */       public Builder setLeft(long value) {
/* 1350 */         this.bitField0_ |= 0x1;
/* 1351 */         this.left_ = value;
/* 1353 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLeft() {
/* 1356 */         this.bitField0_ &= 0xFFFFFFFE;
/* 1357 */         this.left_ = 0L;
/* 1359 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasRight() {
/* 1365 */         return ((this.bitField0_ & 0x2) == 2);
/*      */       }
/*      */       
/*      */       public long getRight() {
/* 1368 */         return this.right_;
/*      */       }
/*      */       
/*      */       public Builder setRight(long value) {
/* 1371 */         this.bitField0_ |= 0x2;
/* 1372 */         this.right_ = value;
/* 1374 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearRight() {
/* 1377 */         this.bitField0_ &= 0xFFFFFFFD;
/* 1378 */         this.right_ = 0L;
/* 1380 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasTop() {
/* 1386 */         return ((this.bitField0_ & 0x4) == 4);
/*      */       }
/*      */       
/*      */       public long getTop() {
/* 1389 */         return this.top_;
/*      */       }
/*      */       
/*      */       public Builder setTop(long value) {
/* 1392 */         this.bitField0_ |= 0x4;
/* 1393 */         this.top_ = value;
/* 1395 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearTop() {
/* 1398 */         this.bitField0_ &= 0xFFFFFFFB;
/* 1399 */         this.top_ = 0L;
/* 1401 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasBottom() {
/* 1407 */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public long getBottom() {
/* 1410 */         return this.bottom_;
/*      */       }
/*      */       
/*      */       public Builder setBottom(long value) {
/* 1413 */         this.bitField0_ |= 0x8;
/* 1414 */         this.bottom_ = value;
/* 1416 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearBottom() {
/* 1419 */         this.bitField0_ &= 0xFFFFFFF7;
/* 1420 */         this.bottom_ = 0L;
/* 1422 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 1429 */     private static final HeaderBBox defaultInstance = new HeaderBBox(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int LEFT_FIELD_NUMBER = 1;
/*      */     
/*      */     private long left_;
/*      */     
/*      */     public static final int RIGHT_FIELD_NUMBER = 2;
/*      */     
/*      */     private long right_;
/*      */     
/*      */     public static final int TOP_FIELD_NUMBER = 3;
/*      */     
/*      */     private long top_;
/*      */     
/*      */     public static final int BOTTOM_FIELD_NUMBER = 4;
/*      */     
/*      */     private long bottom_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 1430 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface PrimitiveBlockOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasStringtable();
/*      */     
/*      */     Osmformat.StringTable getStringtable();
/*      */     
/*      */     List<Osmformat.PrimitiveGroup> getPrimitivegroupList();
/*      */     
/*      */     Osmformat.PrimitiveGroup getPrimitivegroup(int param1Int);
/*      */     
/*      */     int getPrimitivegroupCount();
/*      */     
/*      */     boolean hasGranularity();
/*      */     
/*      */     int getGranularity();
/*      */     
/*      */     boolean hasLatOffset();
/*      */     
/*      */     long getLatOffset();
/*      */     
/*      */     boolean hasLonOffset();
/*      */     
/*      */     long getLonOffset();
/*      */     
/*      */     boolean hasDateGranularity();
/*      */     
/*      */     int getDateGranularity();
/*      */   }
/*      */   
/*      */   public static final class PrimitiveBlock extends GeneratedMessageLite implements PrimitiveBlockOrBuilder {
/*      */     private PrimitiveBlock(Builder builder) {
/* 1470 */       super(builder);
/* 1563 */       this.memoizedIsInitialized = -1;
/* 1605 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private PrimitiveBlock(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 1605 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public PrimitiveBlock getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasStringtable() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public Osmformat.StringTable getStringtable() {
/*      */       return this.stringtable_;
/*      */     }
/*      */     
/*      */     public List<Osmformat.PrimitiveGroup> getPrimitivegroupList() {
/*      */       return this.primitivegroup_;
/*      */     }
/*      */     
/*      */     public List<? extends Osmformat.PrimitiveGroupOrBuilder> getPrimitivegroupOrBuilderList() {
/*      */       return (List)this.primitivegroup_;
/*      */     }
/*      */     
/*      */     public int getPrimitivegroupCount() {
/*      */       return this.primitivegroup_.size();
/*      */     }
/*      */     
/*      */     public Osmformat.PrimitiveGroup getPrimitivegroup(int index) {
/*      */       return this.primitivegroup_.get(index);
/*      */     }
/*      */     
/*      */     public Osmformat.PrimitiveGroupOrBuilder getPrimitivegroupOrBuilder(int index) {
/*      */       return this.primitivegroup_.get(index);
/*      */     }
/*      */     
/*      */     public boolean hasGranularity() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public int getGranularity() {
/*      */       return this.granularity_;
/*      */     }
/*      */     
/*      */     public boolean hasLatOffset() {
/*      */       return ((this.bitField0_ & 0x4) == 4);
/*      */     }
/*      */     
/*      */     public long getLatOffset() {
/*      */       return this.latOffset_;
/*      */     }
/*      */     
/*      */     public boolean hasLonOffset() {
/*      */       return ((this.bitField0_ & 0x8) == 8);
/*      */     }
/*      */     
/*      */     public long getLonOffset() {
/*      */       return this.lonOffset_;
/*      */     }
/*      */     
/*      */     public boolean hasDateGranularity() {
/*      */       return ((this.bitField0_ & 0x10) == 16);
/*      */     }
/*      */     
/*      */     public int getDateGranularity() {
/*      */       return this.dateGranularity_;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.stringtable_ = Osmformat.StringTable.getDefaultInstance();
/*      */       this.primitivegroup_ = Collections.emptyList();
/*      */       this.granularity_ = 100;
/*      */       this.latOffset_ = 0L;
/*      */       this.lonOffset_ = 0L;
/*      */       this.dateGranularity_ = 1000;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasStringtable()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       for (int i = 0; i < getPrimitivegroupCount(); i++) {
/*      */         if (!getPrimitivegroup(i).isInitialized()) {
/*      */           this.memoizedIsInitialized = 0;
/*      */           return false;
/*      */         } 
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeMessage(1, (MessageLite)this.stringtable_); 
/*      */       for (int i = 0; i < this.primitivegroup_.size(); i++)
/*      */         output.writeMessage(2, (MessageLite)this.primitivegroup_.get(i)); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeInt32(17, this.granularity_); 
/*      */       if ((this.bitField0_ & 0x10) == 16)
/*      */         output.writeInt32(18, this.dateGranularity_); 
/*      */       if ((this.bitField0_ & 0x4) == 4)
/*      */         output.writeInt64(19, this.latOffset_); 
/*      */       if ((this.bitField0_ & 0x8) == 8)
/*      */         output.writeInt64(20, this.lonOffset_); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 1607 */       int size = this.memoizedSerializedSize;
/* 1608 */       if (size != -1)
/* 1608 */         return size; 
/* 1610 */       size = 0;
/* 1611 */       if ((this.bitField0_ & 0x1) == 1)
/* 1612 */         size += CodedOutputStream.computeMessageSize(1, (MessageLite)this.stringtable_); 
/* 1615 */       for (int i = 0; i < this.primitivegroup_.size(); i++)
/* 1616 */         size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.primitivegroup_.get(i)); 
/* 1619 */       if ((this.bitField0_ & 0x2) == 2)
/* 1620 */         size += CodedOutputStream.computeInt32Size(17, this.granularity_); 
/* 1623 */       if ((this.bitField0_ & 0x10) == 16)
/* 1624 */         size += CodedOutputStream.computeInt32Size(18, this.dateGranularity_); 
/* 1627 */       if ((this.bitField0_ & 0x4) == 4)
/* 1628 */         size += CodedOutputStream.computeInt64Size(19, this.latOffset_); 
/* 1631 */       if ((this.bitField0_ & 0x8) == 8)
/* 1632 */         size += CodedOutputStream.computeInt64Size(20, this.lonOffset_); 
/* 1635 */       this.memoizedSerializedSize = size;
/* 1636 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 1643 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 1649 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1655 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 1660 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 1666 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(InputStream input) throws IOException {
/* 1671 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1677 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseDelimitedFrom(InputStream input) throws IOException {
/* 1682 */       Builder builder = newBuilder();
/* 1683 */       if (builder.mergeDelimitedFrom(input))
/* 1684 */         return builder.buildParsed(); 
/* 1686 */       return null;
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1693 */       Builder builder = newBuilder();
/* 1694 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 1695 */         return builder.buildParsed(); 
/* 1697 */       return null;
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(CodedInputStream input) throws IOException {
/* 1703 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveBlock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 1709 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 1713 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 1714 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(PrimitiveBlock prototype) {
/* 1716 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 1718 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<PrimitiveBlock, Builder> implements Osmformat.PrimitiveBlockOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private Osmformat.StringTable stringtable_;
/*      */       
/*      */       private List<Osmformat.PrimitiveGroup> primitivegroup_;
/*      */       
/*      */       private int granularity_;
/*      */       
/*      */       private long latOffset_;
/*      */       
/*      */       private long lonOffset_;
/*      */       
/*      */       private int dateGranularity_;
/*      */       
/*      */       private Builder() {
/* 1914 */         this.stringtable_ = Osmformat.StringTable.getDefaultInstance();
/* 1957 */         this.primitivegroup_ = Collections.emptyList();
/* 2046 */         this.granularity_ = 100;
/* 2109 */         this.dateGranularity_ = 1000;
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.stringtable_ = Osmformat.StringTable.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.primitivegroup_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.granularity_ = 100;
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.latOffset_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.lonOffset_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.dateGranularity_ = 1000;
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveBlock getDefaultInstanceForType() {
/*      */         return Osmformat.PrimitiveBlock.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveBlock build() {
/*      */         Osmformat.PrimitiveBlock result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.PrimitiveBlock buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.PrimitiveBlock result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveBlock buildPartial() {
/*      */         Osmformat.PrimitiveBlock result = new Osmformat.PrimitiveBlock(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.stringtable_ = this.stringtable_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.primitivegroup_ = Collections.unmodifiableList(this.primitivegroup_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.primitivegroup_ = this.primitivegroup_;
/*      */         if ((from_bitField0_ & 0x4) == 4)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.granularity_ = this.granularity_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x4; 
/*      */         result.latOffset_ = this.latOffset_;
/*      */         if ((from_bitField0_ & 0x10) == 16)
/*      */           to_bitField0_ |= 0x8; 
/*      */         result.lonOffset_ = this.lonOffset_;
/*      */         if ((from_bitField0_ & 0x20) == 32)
/*      */           to_bitField0_ |= 0x10; 
/*      */         result.dateGranularity_ = this.dateGranularity_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.PrimitiveBlock other) {
/*      */         if (other == Osmformat.PrimitiveBlock.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasStringtable())
/*      */           mergeStringtable(other.getStringtable()); 
/*      */         if (!other.primitivegroup_.isEmpty())
/*      */           if (this.primitivegroup_.isEmpty()) {
/*      */             this.primitivegroup_ = other.primitivegroup_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensurePrimitivegroupIsMutable();
/*      */             this.primitivegroup_.addAll(other.primitivegroup_);
/*      */           }  
/*      */         if (other.hasGranularity())
/*      */           setGranularity(other.getGranularity()); 
/*      */         if (other.hasLatOffset())
/*      */           setLatOffset(other.getLatOffset()); 
/*      */         if (other.hasLonOffset())
/*      */           setLonOffset(other.getLonOffset()); 
/*      */         if (other.hasDateGranularity())
/*      */           setDateGranularity(other.getDateGranularity()); 
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         if (!hasStringtable())
/*      */           return false; 
/*      */         for (int i = 0; i < getPrimitivegroupCount(); i++) {
/*      */           if (!getPrimitivegroup(i).isInitialized())
/*      */             return false; 
/*      */         } 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           Osmformat.StringTable.Builder builder;
/*      */           Osmformat.PrimitiveGroup.Builder subBuilder;
/*      */           int tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 10:
/*      */               builder = Osmformat.StringTable.newBuilder();
/*      */               if (hasStringtable())
/*      */                 builder.mergeFrom(getStringtable()); 
/*      */               input.readMessage((MessageLite.Builder)builder, extensionRegistry);
/*      */               setStringtable(builder.buildPartial());
/*      */               break;
/*      */             case 18:
/*      */               subBuilder = Osmformat.PrimitiveGroup.newBuilder();
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               addPrimitivegroup(subBuilder.buildPartial());
/*      */               break;
/*      */             case 136:
/*      */               this.bitField0_ |= 0x4;
/*      */               this.granularity_ = input.readInt32();
/*      */               break;
/*      */             case 144:
/*      */               this.bitField0_ |= 0x20;
/*      */               this.dateGranularity_ = input.readInt32();
/*      */               break;
/*      */             case 152:
/*      */               this.bitField0_ |= 0x8;
/*      */               this.latOffset_ = input.readInt64();
/*      */               break;
/*      */             case 160:
/*      */               this.bitField0_ |= 0x10;
/*      */               this.lonOffset_ = input.readInt64();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasStringtable() {
/*      */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public Osmformat.StringTable getStringtable() {
/*      */         return this.stringtable_;
/*      */       }
/*      */       
/*      */       public Builder setStringtable(Osmformat.StringTable value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.stringtable_ = value;
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setStringtable(Osmformat.StringTable.Builder builderForValue) {
/*      */         this.stringtable_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeStringtable(Osmformat.StringTable value) {
/*      */         if ((this.bitField0_ & 0x1) == 1 && this.stringtable_ != Osmformat.StringTable.getDefaultInstance()) {
/*      */           this.stringtable_ = Osmformat.StringTable.newBuilder(this.stringtable_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.stringtable_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x1;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearStringtable() {
/*      */         this.stringtable_ = Osmformat.StringTable.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensurePrimitivegroupIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.primitivegroup_ = new ArrayList<Osmformat.PrimitiveGroup>(this.primitivegroup_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.PrimitiveGroup> getPrimitivegroupList() {
/*      */         return Collections.unmodifiableList(this.primitivegroup_);
/*      */       }
/*      */       
/*      */       public int getPrimitivegroupCount() {
/*      */         return this.primitivegroup_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveGroup getPrimitivegroup(int index) {
/*      */         return this.primitivegroup_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setPrimitivegroup(int index, Osmformat.PrimitiveGroup value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setPrimitivegroup(int index, Osmformat.PrimitiveGroup.Builder builderForValue) {
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.set(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addPrimitivegroup(Osmformat.PrimitiveGroup value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addPrimitivegroup(int index, Osmformat.PrimitiveGroup value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.add(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addPrimitivegroup(Osmformat.PrimitiveGroup.Builder builderForValue) {
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.add(builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addPrimitivegroup(int index, Osmformat.PrimitiveGroup.Builder builderForValue) {
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.add(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllPrimitivegroup(Iterable<? extends Osmformat.PrimitiveGroup> values) {
/*      */         ensurePrimitivegroupIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.primitivegroup_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearPrimitivegroup() {
/*      */         this.primitivegroup_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder removePrimitivegroup(int index) {
/*      */         ensurePrimitivegroupIsMutable();
/*      */         this.primitivegroup_.remove(index);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasGranularity() {
/*      */         return ((this.bitField0_ & 0x4) == 4);
/*      */       }
/*      */       
/*      */       public int getGranularity() {
/*      */         return this.granularity_;
/*      */       }
/*      */       
/*      */       public Builder setGranularity(int value) {
/*      */         this.bitField0_ |= 0x4;
/*      */         this.granularity_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearGranularity() {
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.granularity_ = 100;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasLatOffset() {
/*      */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public long getLatOffset() {
/*      */         return this.latOffset_;
/*      */       }
/*      */       
/*      */       public Builder setLatOffset(long value) {
/*      */         this.bitField0_ |= 0x8;
/*      */         this.latOffset_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLatOffset() {
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.latOffset_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasLonOffset() {
/*      */         return ((this.bitField0_ & 0x10) == 16);
/*      */       }
/*      */       
/*      */       public long getLonOffset() {
/*      */         return this.lonOffset_;
/*      */       }
/*      */       
/*      */       public Builder setLonOffset(long value) {
/*      */         this.bitField0_ |= 0x10;
/*      */         this.lonOffset_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLonOffset() {
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.lonOffset_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasDateGranularity() {
/* 2111 */         return ((this.bitField0_ & 0x20) == 32);
/*      */       }
/*      */       
/*      */       public int getDateGranularity() {
/* 2114 */         return this.dateGranularity_;
/*      */       }
/*      */       
/*      */       public Builder setDateGranularity(int value) {
/* 2117 */         this.bitField0_ |= 0x20;
/* 2118 */         this.dateGranularity_ = value;
/* 2120 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearDateGranularity() {
/* 2123 */         this.bitField0_ &= 0xFFFFFFDF;
/* 2124 */         this.dateGranularity_ = 1000;
/* 2126 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 2133 */     private static final PrimitiveBlock defaultInstance = new PrimitiveBlock(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int STRINGTABLE_FIELD_NUMBER = 1;
/*      */     
/*      */     private Osmformat.StringTable stringtable_;
/*      */     
/*      */     public static final int PRIMITIVEGROUP_FIELD_NUMBER = 2;
/*      */     
/*      */     private List<Osmformat.PrimitiveGroup> primitivegroup_;
/*      */     
/*      */     public static final int GRANULARITY_FIELD_NUMBER = 17;
/*      */     
/*      */     private int granularity_;
/*      */     
/*      */     public static final int LAT_OFFSET_FIELD_NUMBER = 19;
/*      */     
/*      */     private long latOffset_;
/*      */     
/*      */     public static final int LON_OFFSET_FIELD_NUMBER = 20;
/*      */     
/*      */     private long lonOffset_;
/*      */     
/*      */     public static final int DATE_GRANULARITY_FIELD_NUMBER = 18;
/*      */     
/*      */     private int dateGranularity_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 2134 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface PrimitiveGroupOrBuilder extends MessageLiteOrBuilder {
/*      */     List<Osmformat.Node> getNodesList();
/*      */     
/*      */     Osmformat.Node getNodes(int param1Int);
/*      */     
/*      */     int getNodesCount();
/*      */     
/*      */     boolean hasDense();
/*      */     
/*      */     Osmformat.DenseNodes getDense();
/*      */     
/*      */     List<Osmformat.Way> getWaysList();
/*      */     
/*      */     Osmformat.Way getWays(int param1Int);
/*      */     
/*      */     int getWaysCount();
/*      */     
/*      */     List<Osmformat.Relation> getRelationsList();
/*      */     
/*      */     Osmformat.Relation getRelations(int param1Int);
/*      */     
/*      */     int getRelationsCount();
/*      */     
/*      */     List<Osmformat.ChangeSet> getChangesetsList();
/*      */     
/*      */     Osmformat.ChangeSet getChangesets(int param1Int);
/*      */     
/*      */     int getChangesetsCount();
/*      */   }
/*      */   
/*      */   public static final class PrimitiveGroup extends GeneratedMessageLite implements PrimitiveGroupOrBuilder {
/*      */     private PrimitiveGroup(Builder builder) {
/* 2176 */       super(builder);
/* 2291 */       this.memoizedIsInitialized = -1;
/* 2344 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private PrimitiveGroup(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 2344 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public PrimitiveGroup getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public List<Osmformat.Node> getNodesList() {
/*      */       return this.nodes_;
/*      */     }
/*      */     
/*      */     public List<? extends Osmformat.NodeOrBuilder> getNodesOrBuilderList() {
/*      */       return (List)this.nodes_;
/*      */     }
/*      */     
/*      */     public int getNodesCount() {
/*      */       return this.nodes_.size();
/*      */     }
/*      */     
/*      */     public Osmformat.Node getNodes(int index) {
/*      */       return this.nodes_.get(index);
/*      */     }
/*      */     
/*      */     public Osmformat.NodeOrBuilder getNodesOrBuilder(int index) {
/*      */       return this.nodes_.get(index);
/*      */     }
/*      */     
/*      */     public boolean hasDense() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public Osmformat.DenseNodes getDense() {
/*      */       return this.dense_;
/*      */     }
/*      */     
/*      */     public List<Osmformat.Way> getWaysList() {
/*      */       return this.ways_;
/*      */     }
/*      */     
/*      */     public List<? extends Osmformat.WayOrBuilder> getWaysOrBuilderList() {
/*      */       return (List)this.ways_;
/*      */     }
/*      */     
/*      */     public int getWaysCount() {
/*      */       return this.ways_.size();
/*      */     }
/*      */     
/*      */     public Osmformat.Way getWays(int index) {
/*      */       return this.ways_.get(index);
/*      */     }
/*      */     
/*      */     public Osmformat.WayOrBuilder getWaysOrBuilder(int index) {
/*      */       return this.ways_.get(index);
/*      */     }
/*      */     
/*      */     public List<Osmformat.Relation> getRelationsList() {
/*      */       return this.relations_;
/*      */     }
/*      */     
/*      */     public List<? extends Osmformat.RelationOrBuilder> getRelationsOrBuilderList() {
/*      */       return (List)this.relations_;
/*      */     }
/*      */     
/*      */     public int getRelationsCount() {
/*      */       return this.relations_.size();
/*      */     }
/*      */     
/*      */     public Osmformat.Relation getRelations(int index) {
/*      */       return this.relations_.get(index);
/*      */     }
/*      */     
/*      */     public Osmformat.RelationOrBuilder getRelationsOrBuilder(int index) {
/*      */       return this.relations_.get(index);
/*      */     }
/*      */     
/*      */     public List<Osmformat.ChangeSet> getChangesetsList() {
/*      */       return this.changesets_;
/*      */     }
/*      */     
/*      */     public List<? extends Osmformat.ChangeSetOrBuilder> getChangesetsOrBuilderList() {
/*      */       return (List)this.changesets_;
/*      */     }
/*      */     
/*      */     public int getChangesetsCount() {
/*      */       return this.changesets_.size();
/*      */     }
/*      */     
/*      */     public Osmformat.ChangeSet getChangesets(int index) {
/*      */       return this.changesets_.get(index);
/*      */     }
/*      */     
/*      */     public Osmformat.ChangeSetOrBuilder getChangesetsOrBuilder(int index) {
/*      */       return this.changesets_.get(index);
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.nodes_ = Collections.emptyList();
/*      */       this.dense_ = Osmformat.DenseNodes.getDefaultInstance();
/*      */       this.ways_ = Collections.emptyList();
/*      */       this.relations_ = Collections.emptyList();
/*      */       this.changesets_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       int i;
/*      */       for (i = 0; i < getNodesCount(); i++) {
/*      */         if (!getNodes(i).isInitialized()) {
/*      */           this.memoizedIsInitialized = 0;
/*      */           return false;
/*      */         } 
/*      */       } 
/*      */       for (i = 0; i < getWaysCount(); i++) {
/*      */         if (!getWays(i).isInitialized()) {
/*      */           this.memoizedIsInitialized = 0;
/*      */           return false;
/*      */         } 
/*      */       } 
/*      */       for (i = 0; i < getRelationsCount(); i++) {
/*      */         if (!getRelations(i).isInitialized()) {
/*      */           this.memoizedIsInitialized = 0;
/*      */           return false;
/*      */         } 
/*      */       } 
/*      */       for (i = 0; i < getChangesetsCount(); i++) {
/*      */         if (!getChangesets(i).isInitialized()) {
/*      */           this.memoizedIsInitialized = 0;
/*      */           return false;
/*      */         } 
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       int i;
/*      */       for (i = 0; i < this.nodes_.size(); i++)
/*      */         output.writeMessage(1, (MessageLite)this.nodes_.get(i)); 
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeMessage(2, (MessageLite)this.dense_); 
/*      */       for (i = 0; i < this.ways_.size(); i++)
/*      */         output.writeMessage(3, (MessageLite)this.ways_.get(i)); 
/*      */       for (i = 0; i < this.relations_.size(); i++)
/*      */         output.writeMessage(4, (MessageLite)this.relations_.get(i)); 
/*      */       for (i = 0; i < this.changesets_.size(); i++)
/*      */         output.writeMessage(5, (MessageLite)this.changesets_.get(i)); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 2346 */       int size = this.memoizedSerializedSize;
/* 2347 */       if (size != -1)
/* 2347 */         return size; 
/* 2349 */       size = 0;
/*      */       int i;
/* 2350 */       for (i = 0; i < this.nodes_.size(); i++)
/* 2351 */         size += CodedOutputStream.computeMessageSize(1, (MessageLite)this.nodes_.get(i)); 
/* 2354 */       if ((this.bitField0_ & 0x1) == 1)
/* 2355 */         size += CodedOutputStream.computeMessageSize(2, (MessageLite)this.dense_); 
/* 2358 */       for (i = 0; i < this.ways_.size(); i++)
/* 2359 */         size += CodedOutputStream.computeMessageSize(3, (MessageLite)this.ways_.get(i)); 
/* 2362 */       for (i = 0; i < this.relations_.size(); i++)
/* 2363 */         size += CodedOutputStream.computeMessageSize(4, (MessageLite)this.relations_.get(i)); 
/* 2366 */       for (i = 0; i < this.changesets_.size(); i++)
/* 2367 */         size += CodedOutputStream.computeMessageSize(5, (MessageLite)this.changesets_.get(i)); 
/* 2370 */       this.memoizedSerializedSize = size;
/* 2371 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 2378 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 2384 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 2390 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 2395 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 2401 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(InputStream input) throws IOException {
/* 2406 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2412 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseDelimitedFrom(InputStream input) throws IOException {
/* 2417 */       Builder builder = newBuilder();
/* 2418 */       if (builder.mergeDelimitedFrom(input))
/* 2419 */         return builder.buildParsed(); 
/* 2421 */       return null;
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2428 */       Builder builder = newBuilder();
/* 2429 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 2430 */         return builder.buildParsed(); 
/* 2432 */       return null;
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(CodedInputStream input) throws IOException {
/* 2438 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static PrimitiveGroup parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 2444 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 2448 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 2449 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(PrimitiveGroup prototype) {
/* 2451 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 2453 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<PrimitiveGroup, Builder> implements Osmformat.PrimitiveGroupOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private List<Osmformat.Node> nodes_;
/*      */       
/*      */       private Osmformat.DenseNodes dense_;
/*      */       
/*      */       private List<Osmformat.Way> ways_;
/*      */       
/*      */       private List<Osmformat.Relation> relations_;
/*      */       
/*      */       private List<Osmformat.ChangeSet> changesets_;
/*      */       
/*      */       private Builder() {
/* 2676 */         this.nodes_ = Collections.emptyList();
/* 2765 */         this.dense_ = Osmformat.DenseNodes.getDefaultInstance();
/* 2808 */         this.ways_ = Collections.emptyList();
/* 2897 */         this.relations_ = Collections.emptyList();
/* 2986 */         this.changesets_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.nodes_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.dense_ = Osmformat.DenseNodes.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.ways_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.relations_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.changesets_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveGroup getDefaultInstanceForType() {
/*      */         return Osmformat.PrimitiveGroup.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveGroup build() {
/*      */         Osmformat.PrimitiveGroup result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.PrimitiveGroup buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.PrimitiveGroup result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.PrimitiveGroup buildPartial() {
/*      */         Osmformat.PrimitiveGroup result = new Osmformat.PrimitiveGroup(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((this.bitField0_ & 0x1) == 1) {
/*      */           this.nodes_ = Collections.unmodifiableList(this.nodes_);
/*      */           this.bitField0_ &= 0xFFFFFFFE;
/*      */         } 
/*      */         result.nodes_ = this.nodes_;
/*      */         if ((from_bitField0_ & 0x2) == 2)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.dense_ = this.dense_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.ways_ = Collections.unmodifiableList(this.ways_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.ways_ = this.ways_;
/*      */         if ((this.bitField0_ & 0x8) == 8) {
/*      */           this.relations_ = Collections.unmodifiableList(this.relations_);
/*      */           this.bitField0_ &= 0xFFFFFFF7;
/*      */         } 
/*      */         result.relations_ = this.relations_;
/*      */         if ((this.bitField0_ & 0x10) == 16) {
/*      */           this.changesets_ = Collections.unmodifiableList(this.changesets_);
/*      */           this.bitField0_ &= 0xFFFFFFEF;
/*      */         } 
/*      */         result.changesets_ = this.changesets_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.PrimitiveGroup other) {
/*      */         if (other == Osmformat.PrimitiveGroup.getDefaultInstance())
/*      */           return this; 
/*      */         if (!other.nodes_.isEmpty())
/*      */           if (this.nodes_.isEmpty()) {
/*      */             this.nodes_ = other.nodes_;
/*      */             this.bitField0_ &= 0xFFFFFFFE;
/*      */           } else {
/*      */             ensureNodesIsMutable();
/*      */             this.nodes_.addAll(other.nodes_);
/*      */           }  
/*      */         if (other.hasDense())
/*      */           mergeDense(other.getDense()); 
/*      */         if (!other.ways_.isEmpty())
/*      */           if (this.ways_.isEmpty()) {
/*      */             this.ways_ = other.ways_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureWaysIsMutable();
/*      */             this.ways_.addAll(other.ways_);
/*      */           }  
/*      */         if (!other.relations_.isEmpty())
/*      */           if (this.relations_.isEmpty()) {
/*      */             this.relations_ = other.relations_;
/*      */             this.bitField0_ &= 0xFFFFFFF7;
/*      */           } else {
/*      */             ensureRelationsIsMutable();
/*      */             this.relations_.addAll(other.relations_);
/*      */           }  
/*      */         if (!other.changesets_.isEmpty())
/*      */           if (this.changesets_.isEmpty()) {
/*      */             this.changesets_ = other.changesets_;
/*      */             this.bitField0_ &= 0xFFFFFFEF;
/*      */           } else {
/*      */             ensureChangesetsIsMutable();
/*      */             this.changesets_.addAll(other.changesets_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         int i;
/*      */         for (i = 0; i < getNodesCount(); i++) {
/*      */           if (!getNodes(i).isInitialized())
/*      */             return false; 
/*      */         } 
/*      */         for (i = 0; i < getWaysCount(); i++) {
/*      */           if (!getWays(i).isInitialized())
/*      */             return false; 
/*      */         } 
/*      */         for (i = 0; i < getRelationsCount(); i++) {
/*      */           if (!getRelations(i).isInitialized())
/*      */             return false; 
/*      */         } 
/*      */         for (i = 0; i < getChangesetsCount(); i++) {
/*      */           if (!getChangesets(i).isInitialized())
/*      */             return false; 
/*      */         } 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           Osmformat.Node.Builder builder3;
/*      */           Osmformat.DenseNodes.Builder builder2;
/*      */           Osmformat.Way.Builder builder1;
/*      */           Osmformat.Relation.Builder builder;
/*      */           Osmformat.ChangeSet.Builder subBuilder;
/*      */           int tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 10:
/*      */               builder3 = Osmformat.Node.newBuilder();
/*      */               input.readMessage((MessageLite.Builder)builder3, extensionRegistry);
/*      */               addNodes(builder3.buildPartial());
/*      */               break;
/*      */             case 18:
/*      */               builder2 = Osmformat.DenseNodes.newBuilder();
/*      */               if (hasDense())
/*      */                 builder2.mergeFrom(getDense()); 
/*      */               input.readMessage((MessageLite.Builder)builder2, extensionRegistry);
/*      */               setDense(builder2.buildPartial());
/*      */               break;
/*      */             case 26:
/*      */               builder1 = Osmformat.Way.newBuilder();
/*      */               input.readMessage((MessageLite.Builder)builder1, extensionRegistry);
/*      */               addWays(builder1.buildPartial());
/*      */               break;
/*      */             case 34:
/*      */               builder = Osmformat.Relation.newBuilder();
/*      */               input.readMessage((MessageLite.Builder)builder, extensionRegistry);
/*      */               addRelations(builder.buildPartial());
/*      */               break;
/*      */             case 42:
/*      */               subBuilder = Osmformat.ChangeSet.newBuilder();
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               addChangesets(subBuilder.buildPartial());
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       private void ensureNodesIsMutable() {
/*      */         if ((this.bitField0_ & 0x1) != 1) {
/*      */           this.nodes_ = new ArrayList<Osmformat.Node>(this.nodes_);
/*      */           this.bitField0_ |= 0x1;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.Node> getNodesList() {
/*      */         return Collections.unmodifiableList(this.nodes_);
/*      */       }
/*      */       
/*      */       public int getNodesCount() {
/*      */         return this.nodes_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.Node getNodes(int index) {
/*      */         return this.nodes_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setNodes(int index, Osmformat.Node value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setNodes(int index, Osmformat.Node.Builder builderForValue) {
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.set(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addNodes(Osmformat.Node value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addNodes(int index, Osmformat.Node value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.add(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addNodes(Osmformat.Node.Builder builderForValue) {
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.add(builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addNodes(int index, Osmformat.Node.Builder builderForValue) {
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.add(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllNodes(Iterable<? extends Osmformat.Node> values) {
/*      */         ensureNodesIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.nodes_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearNodes() {
/*      */         this.nodes_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder removeNodes(int index) {
/*      */         ensureNodesIsMutable();
/*      */         this.nodes_.remove(index);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasDense() {
/*      */         return ((this.bitField0_ & 0x2) == 2);
/*      */       }
/*      */       
/*      */       public Osmformat.DenseNodes getDense() {
/*      */         return this.dense_;
/*      */       }
/*      */       
/*      */       public Builder setDense(Osmformat.DenseNodes value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.dense_ = value;
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setDense(Osmformat.DenseNodes.Builder builderForValue) {
/*      */         this.dense_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeDense(Osmformat.DenseNodes value) {
/*      */         if ((this.bitField0_ & 0x2) == 2 && this.dense_ != Osmformat.DenseNodes.getDefaultInstance()) {
/*      */           this.dense_ = Osmformat.DenseNodes.newBuilder(this.dense_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.dense_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearDense() {
/*      */         this.dense_ = Osmformat.DenseNodes.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureWaysIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.ways_ = new ArrayList<Osmformat.Way>(this.ways_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.Way> getWaysList() {
/*      */         return Collections.unmodifiableList(this.ways_);
/*      */       }
/*      */       
/*      */       public int getWaysCount() {
/*      */         return this.ways_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.Way getWays(int index) {
/*      */         return this.ways_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setWays(int index, Osmformat.Way value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setWays(int index, Osmformat.Way.Builder builderForValue) {
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.set(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addWays(Osmformat.Way value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addWays(int index, Osmformat.Way value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.add(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addWays(Osmformat.Way.Builder builderForValue) {
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.add(builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addWays(int index, Osmformat.Way.Builder builderForValue) {
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.add(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllWays(Iterable<? extends Osmformat.Way> values) {
/*      */         ensureWaysIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.ways_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearWays() {
/*      */         this.ways_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder removeWays(int index) {
/*      */         ensureWaysIsMutable();
/*      */         this.ways_.remove(index);
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureRelationsIsMutable() {
/*      */         if ((this.bitField0_ & 0x8) != 8) {
/*      */           this.relations_ = new ArrayList<Osmformat.Relation>(this.relations_);
/*      */           this.bitField0_ |= 0x8;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.Relation> getRelationsList() {
/*      */         return Collections.unmodifiableList(this.relations_);
/*      */       }
/*      */       
/*      */       public int getRelationsCount() {
/*      */         return this.relations_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.Relation getRelations(int index) {
/*      */         return this.relations_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setRelations(int index, Osmformat.Relation value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.set(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setRelations(int index, Osmformat.Relation.Builder builderForValue) {
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.set(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRelations(Osmformat.Relation value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.add(value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRelations(int index, Osmformat.Relation value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.add(index, value);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRelations(Osmformat.Relation.Builder builderForValue) {
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.add(builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRelations(int index, Osmformat.Relation.Builder builderForValue) {
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.add(index, builderForValue.build());
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllRelations(Iterable<? extends Osmformat.Relation> values) {
/*      */         ensureRelationsIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.relations_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearRelations() {
/*      */         this.relations_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder removeRelations(int index) {
/*      */         ensureRelationsIsMutable();
/*      */         this.relations_.remove(index);
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureChangesetsIsMutable() {
/* 2989 */         if ((this.bitField0_ & 0x10) != 16) {
/* 2990 */           this.changesets_ = new ArrayList<Osmformat.ChangeSet>(this.changesets_);
/* 2991 */           this.bitField0_ |= 0x10;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.ChangeSet> getChangesetsList() {
/* 2996 */         return Collections.unmodifiableList(this.changesets_);
/*      */       }
/*      */       
/*      */       public int getChangesetsCount() {
/* 2999 */         return this.changesets_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.ChangeSet getChangesets(int index) {
/* 3002 */         return this.changesets_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setChangesets(int index, Osmformat.ChangeSet value) {
/* 3006 */         if (value == null)
/* 3007 */           throw new NullPointerException(); 
/* 3009 */         ensureChangesetsIsMutable();
/* 3010 */         this.changesets_.set(index, value);
/* 3012 */         return this;
/*      */       }
/*      */       
/*      */       public Builder setChangesets(int index, Osmformat.ChangeSet.Builder builderForValue) {
/* 3016 */         ensureChangesetsIsMutable();
/* 3017 */         this.changesets_.set(index, builderForValue.build());
/* 3019 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addChangesets(Osmformat.ChangeSet value) {
/* 3022 */         if (value == null)
/* 3023 */           throw new NullPointerException(); 
/* 3025 */         ensureChangesetsIsMutable();
/* 3026 */         this.changesets_.add(value);
/* 3028 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addChangesets(int index, Osmformat.ChangeSet value) {
/* 3032 */         if (value == null)
/* 3033 */           throw new NullPointerException(); 
/* 3035 */         ensureChangesetsIsMutable();
/* 3036 */         this.changesets_.add(index, value);
/* 3038 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addChangesets(Osmformat.ChangeSet.Builder builderForValue) {
/* 3042 */         ensureChangesetsIsMutable();
/* 3043 */         this.changesets_.add(builderForValue.build());
/* 3045 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addChangesets(int index, Osmformat.ChangeSet.Builder builderForValue) {
/* 3049 */         ensureChangesetsIsMutable();
/* 3050 */         this.changesets_.add(index, builderForValue.build());
/* 3052 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllChangesets(Iterable<? extends Osmformat.ChangeSet> values) {
/* 3056 */         ensureChangesetsIsMutable();
/* 3057 */         GeneratedMessageLite.Builder.addAll(values, this.changesets_);
/* 3059 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearChangesets() {
/* 3062 */         this.changesets_ = Collections.emptyList();
/* 3063 */         this.bitField0_ &= 0xFFFFFFEF;
/* 3065 */         return this;
/*      */       }
/*      */       
/*      */       public Builder removeChangesets(int index) {
/* 3068 */         ensureChangesetsIsMutable();
/* 3069 */         this.changesets_.remove(index);
/* 3071 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 3078 */     private static final PrimitiveGroup defaultInstance = new PrimitiveGroup(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int NODES_FIELD_NUMBER = 1;
/*      */     
/*      */     private List<Osmformat.Node> nodes_;
/*      */     
/*      */     public static final int DENSE_FIELD_NUMBER = 2;
/*      */     
/*      */     private Osmformat.DenseNodes dense_;
/*      */     
/*      */     public static final int WAYS_FIELD_NUMBER = 3;
/*      */     
/*      */     private List<Osmformat.Way> ways_;
/*      */     
/*      */     public static final int RELATIONS_FIELD_NUMBER = 4;
/*      */     
/*      */     private List<Osmformat.Relation> relations_;
/*      */     
/*      */     public static final int CHANGESETS_FIELD_NUMBER = 5;
/*      */     
/*      */     private List<Osmformat.ChangeSet> changesets_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 3079 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface StringTableOrBuilder extends MessageLiteOrBuilder {
/*      */     List<ByteString> getSList();
/*      */     
/*      */     int getSCount();
/*      */     
/*      */     ByteString getS(int param1Int);
/*      */   }
/*      */   
/*      */   public static final class StringTable extends GeneratedMessageLite implements StringTableOrBuilder {
/*      */     private StringTable(Builder builder) {
/* 3098 */       super(builder);
/* 3128 */       this.memoizedIsInitialized = -1;
/* 3145 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private StringTable(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 3145 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static StringTable getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public StringTable getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public List<ByteString> getSList() {
/*      */       return this.s_;
/*      */     }
/*      */     
/*      */     public int getSCount() {
/*      */       return this.s_.size();
/*      */     }
/*      */     
/*      */     public ByteString getS(int index) {
/*      */       return this.s_.get(index);
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.s_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       for (int i = 0; i < this.s_.size(); i++)
/*      */         output.writeBytes(1, this.s_.get(i)); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 3147 */       int size = this.memoizedSerializedSize;
/* 3148 */       if (size != -1)
/* 3148 */         return size; 
/* 3150 */       size = 0;
/* 3152 */       int dataSize = 0;
/* 3153 */       for (int i = 0; i < this.s_.size(); i++)
/* 3154 */         dataSize += CodedOutputStream.computeBytesSizeNoTag(this.s_.get(i)); 
/* 3157 */       size += dataSize;
/* 3158 */       size += 1 * getSList().size();
/* 3160 */       this.memoizedSerializedSize = size;
/* 3161 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 3168 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 3174 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 3180 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 3185 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 3191 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(InputStream input) throws IOException {
/* 3196 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3202 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseDelimitedFrom(InputStream input) throws IOException {
/* 3207 */       Builder builder = newBuilder();
/* 3208 */       if (builder.mergeDelimitedFrom(input))
/* 3209 */         return builder.buildParsed(); 
/* 3211 */       return null;
/*      */     }
/*      */     
/*      */     public static StringTable parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3218 */       Builder builder = newBuilder();
/* 3219 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 3220 */         return builder.buildParsed(); 
/* 3222 */       return null;
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(CodedInputStream input) throws IOException {
/* 3228 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static StringTable parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3234 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 3238 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 3239 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(StringTable prototype) {
/* 3241 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 3243 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<StringTable, Builder> implements Osmformat.StringTableOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private List<ByteString> s_;
/*      */       
/*      */       private Builder() {
/* 3352 */         this.s_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.s_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.StringTable getDefaultInstanceForType() {
/*      */         return Osmformat.StringTable.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.StringTable build() {
/*      */         Osmformat.StringTable result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.StringTable buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.StringTable result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.StringTable buildPartial() {
/*      */         Osmformat.StringTable result = new Osmformat.StringTable(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         if ((this.bitField0_ & 0x1) == 1) {
/*      */           this.s_ = Collections.unmodifiableList(this.s_);
/*      */           this.bitField0_ &= 0xFFFFFFFE;
/*      */         } 
/*      */         result.s_ = this.s_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.StringTable other) {
/*      */         if (other == Osmformat.StringTable.getDefaultInstance())
/*      */           return this; 
/*      */         if (!other.s_.isEmpty())
/*      */           if (this.s_.isEmpty()) {
/*      */             this.s_ = other.s_;
/*      */             this.bitField0_ &= 0xFFFFFFFE;
/*      */           } else {
/*      */             ensureSIsMutable();
/*      */             this.s_.addAll(other.s_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 10:
/*      */               ensureSIsMutable();
/*      */               this.s_.add(input.readBytes());
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       private void ensureSIsMutable() {
/* 3354 */         if ((this.bitField0_ & 0x1) != 1) {
/* 3355 */           this.s_ = new ArrayList<ByteString>(this.s_);
/* 3356 */           this.bitField0_ |= 0x1;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<ByteString> getSList() {
/* 3361 */         return Collections.unmodifiableList(this.s_);
/*      */       }
/*      */       
/*      */       public int getSCount() {
/* 3364 */         return this.s_.size();
/*      */       }
/*      */       
/*      */       public ByteString getS(int index) {
/* 3367 */         return this.s_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setS(int index, ByteString value) {
/* 3371 */         if (value == null)
/* 3372 */           throw new NullPointerException(); 
/* 3374 */         ensureSIsMutable();
/* 3375 */         this.s_.set(index, value);
/* 3377 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addS(ByteString value) {
/* 3380 */         if (value == null)
/* 3381 */           throw new NullPointerException(); 
/* 3383 */         ensureSIsMutable();
/* 3384 */         this.s_.add(value);
/* 3386 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllS(Iterable<? extends ByteString> values) {
/* 3390 */         ensureSIsMutable();
/* 3391 */         GeneratedMessageLite.Builder.addAll(values, this.s_);
/* 3393 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearS() {
/* 3396 */         this.s_ = Collections.emptyList();
/* 3397 */         this.bitField0_ &= 0xFFFFFFFE;
/* 3399 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 3406 */     private static final StringTable defaultInstance = new StringTable(true);
/*      */     
/*      */     public static final int S_FIELD_NUMBER = 1;
/*      */     
/*      */     private List<ByteString> s_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 3407 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface InfoOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasVersion();
/*      */     
/*      */     int getVersion();
/*      */     
/*      */     boolean hasTimestamp();
/*      */     
/*      */     long getTimestamp();
/*      */     
/*      */     boolean hasChangeset();
/*      */     
/*      */     long getChangeset();
/*      */     
/*      */     boolean hasUid();
/*      */     
/*      */     int getUid();
/*      */     
/*      */     boolean hasUserSid();
/*      */     
/*      */     int getUserSid();
/*      */     
/*      */     boolean hasVisible();
/*      */     
/*      */     boolean getVisible();
/*      */   }
/*      */   
/*      */   public static final class Info extends GeneratedMessageLite implements InfoOrBuilder {
/*      */     private Info(Builder builder) {
/* 3445 */       super(builder);
/* 3527 */       this.memoizedIsInitialized = -1;
/* 3559 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private Info(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 3559 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static Info getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public Info getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasVersion() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public int getVersion() {
/*      */       return this.version_;
/*      */     }
/*      */     
/*      */     public boolean hasTimestamp() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public long getTimestamp() {
/*      */       return this.timestamp_;
/*      */     }
/*      */     
/*      */     public boolean hasChangeset() {
/*      */       return ((this.bitField0_ & 0x4) == 4);
/*      */     }
/*      */     
/*      */     public long getChangeset() {
/*      */       return this.changeset_;
/*      */     }
/*      */     
/*      */     public boolean hasUid() {
/*      */       return ((this.bitField0_ & 0x8) == 8);
/*      */     }
/*      */     
/*      */     public int getUid() {
/*      */       return this.uid_;
/*      */     }
/*      */     
/*      */     public boolean hasUserSid() {
/*      */       return ((this.bitField0_ & 0x10) == 16);
/*      */     }
/*      */     
/*      */     public int getUserSid() {
/*      */       return this.userSid_;
/*      */     }
/*      */     
/*      */     public boolean hasVisible() {
/*      */       return ((this.bitField0_ & 0x20) == 32);
/*      */     }
/*      */     
/*      */     public boolean getVisible() {
/*      */       return this.visible_;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.version_ = -1;
/*      */       this.timestamp_ = 0L;
/*      */       this.changeset_ = 0L;
/*      */       this.uid_ = 0;
/*      */       this.userSid_ = 0;
/*      */       this.visible_ = false;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeInt32(1, this.version_); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeInt64(2, this.timestamp_); 
/*      */       if ((this.bitField0_ & 0x4) == 4)
/*      */         output.writeInt64(3, this.changeset_); 
/*      */       if ((this.bitField0_ & 0x8) == 8)
/*      */         output.writeInt32(4, this.uid_); 
/*      */       if ((this.bitField0_ & 0x10) == 16)
/*      */         output.writeUInt32(5, this.userSid_); 
/*      */       if ((this.bitField0_ & 0x20) == 32)
/*      */         output.writeBool(6, this.visible_); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 3561 */       int size = this.memoizedSerializedSize;
/* 3562 */       if (size != -1)
/* 3562 */         return size; 
/* 3564 */       size = 0;
/* 3565 */       if ((this.bitField0_ & 0x1) == 1)
/* 3566 */         size += CodedOutputStream.computeInt32Size(1, this.version_); 
/* 3569 */       if ((this.bitField0_ & 0x2) == 2)
/* 3570 */         size += CodedOutputStream.computeInt64Size(2, this.timestamp_); 
/* 3573 */       if ((this.bitField0_ & 0x4) == 4)
/* 3574 */         size += CodedOutputStream.computeInt64Size(3, this.changeset_); 
/* 3577 */       if ((this.bitField0_ & 0x8) == 8)
/* 3578 */         size += CodedOutputStream.computeInt32Size(4, this.uid_); 
/* 3581 */       if ((this.bitField0_ & 0x10) == 16)
/* 3582 */         size += CodedOutputStream.computeUInt32Size(5, this.userSid_); 
/* 3585 */       if ((this.bitField0_ & 0x20) == 32)
/* 3586 */         size += CodedOutputStream.computeBoolSize(6, this.visible_); 
/* 3589 */       this.memoizedSerializedSize = size;
/* 3590 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 3597 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 3603 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 3609 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 3614 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 3620 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(InputStream input) throws IOException {
/* 3625 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3631 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseDelimitedFrom(InputStream input) throws IOException {
/* 3636 */       Builder builder = newBuilder();
/* 3637 */       if (builder.mergeDelimitedFrom(input))
/* 3638 */         return builder.buildParsed(); 
/* 3640 */       return null;
/*      */     }
/*      */     
/*      */     public static Info parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3647 */       Builder builder = newBuilder();
/* 3648 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 3649 */         return builder.buildParsed(); 
/* 3651 */       return null;
/*      */     }
/*      */     
/*      */     public static Info parseFrom(CodedInputStream input) throws IOException {
/* 3657 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Info parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 3663 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 3667 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 3668 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(Info prototype) {
/* 3670 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 3672 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<Info, Builder> implements Osmformat.InfoOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private int version_;
/*      */       
/*      */       private long timestamp_;
/*      */       
/*      */       private long changeset_;
/*      */       
/*      */       private int uid_;
/*      */       
/*      */       private int userSid_;
/*      */       
/*      */       private boolean visible_;
/*      */       
/*      */       private Builder() {
/* 3845 */         this.version_ = -1;
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.version_ = -1;
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.timestamp_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.changeset_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.uid_ = 0;
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.userSid_ = 0;
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.visible_ = false;
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.Info getDefaultInstanceForType() {
/*      */         return Osmformat.Info.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.Info build() {
/*      */         Osmformat.Info result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.Info buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.Info result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.Info buildPartial() {
/*      */         Osmformat.Info result = new Osmformat.Info(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.version_ = this.version_;
/*      */         if ((from_bitField0_ & 0x2) == 2)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.timestamp_ = this.timestamp_;
/*      */         if ((from_bitField0_ & 0x4) == 4)
/*      */           to_bitField0_ |= 0x4; 
/*      */         result.changeset_ = this.changeset_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x8; 
/*      */         result.uid_ = this.uid_;
/*      */         if ((from_bitField0_ & 0x10) == 16)
/*      */           to_bitField0_ |= 0x10; 
/*      */         result.userSid_ = this.userSid_;
/*      */         if ((from_bitField0_ & 0x20) == 32)
/*      */           to_bitField0_ |= 0x20; 
/*      */         result.visible_ = this.visible_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.Info other) {
/*      */         if (other == Osmformat.Info.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasVersion())
/*      */           setVersion(other.getVersion()); 
/*      */         if (other.hasTimestamp())
/*      */           setTimestamp(other.getTimestamp()); 
/*      */         if (other.hasChangeset())
/*      */           setChangeset(other.getChangeset()); 
/*      */         if (other.hasUid())
/*      */           setUid(other.getUid()); 
/*      */         if (other.hasUserSid())
/*      */           setUserSid(other.getUserSid()); 
/*      */         if (other.hasVisible())
/*      */           setVisible(other.getVisible()); 
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               this.bitField0_ |= 0x1;
/*      */               this.version_ = input.readInt32();
/*      */               break;
/*      */             case 16:
/*      */               this.bitField0_ |= 0x2;
/*      */               this.timestamp_ = input.readInt64();
/*      */               break;
/*      */             case 24:
/*      */               this.bitField0_ |= 0x4;
/*      */               this.changeset_ = input.readInt64();
/*      */               break;
/*      */             case 32:
/*      */               this.bitField0_ |= 0x8;
/*      */               this.uid_ = input.readInt32();
/*      */               break;
/*      */             case 40:
/*      */               this.bitField0_ |= 0x10;
/*      */               this.userSid_ = input.readUInt32();
/*      */               break;
/*      */             case 48:
/*      */               this.bitField0_ |= 0x20;
/*      */               this.visible_ = input.readBool();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasVersion() {
/* 3847 */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public int getVersion() {
/* 3850 */         return this.version_;
/*      */       }
/*      */       
/*      */       public Builder setVersion(int value) {
/* 3853 */         this.bitField0_ |= 0x1;
/* 3854 */         this.version_ = value;
/* 3856 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVersion() {
/* 3859 */         this.bitField0_ &= 0xFFFFFFFE;
/* 3860 */         this.version_ = -1;
/* 3862 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasTimestamp() {
/* 3868 */         return ((this.bitField0_ & 0x2) == 2);
/*      */       }
/*      */       
/*      */       public long getTimestamp() {
/* 3871 */         return this.timestamp_;
/*      */       }
/*      */       
/*      */       public Builder setTimestamp(long value) {
/* 3874 */         this.bitField0_ |= 0x2;
/* 3875 */         this.timestamp_ = value;
/* 3877 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearTimestamp() {
/* 3880 */         this.bitField0_ &= 0xFFFFFFFD;
/* 3881 */         this.timestamp_ = 0L;
/* 3883 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasChangeset() {
/* 3889 */         return ((this.bitField0_ & 0x4) == 4);
/*      */       }
/*      */       
/*      */       public long getChangeset() {
/* 3892 */         return this.changeset_;
/*      */       }
/*      */       
/*      */       public Builder setChangeset(long value) {
/* 3895 */         this.bitField0_ |= 0x4;
/* 3896 */         this.changeset_ = value;
/* 3898 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearChangeset() {
/* 3901 */         this.bitField0_ &= 0xFFFFFFFB;
/* 3902 */         this.changeset_ = 0L;
/* 3904 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasUid() {
/* 3910 */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public int getUid() {
/* 3913 */         return this.uid_;
/*      */       }
/*      */       
/*      */       public Builder setUid(int value) {
/* 3916 */         this.bitField0_ |= 0x8;
/* 3917 */         this.uid_ = value;
/* 3919 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearUid() {
/* 3922 */         this.bitField0_ &= 0xFFFFFFF7;
/* 3923 */         this.uid_ = 0;
/* 3925 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasUserSid() {
/* 3931 */         return ((this.bitField0_ & 0x10) == 16);
/*      */       }
/*      */       
/*      */       public int getUserSid() {
/* 3934 */         return this.userSid_;
/*      */       }
/*      */       
/*      */       public Builder setUserSid(int value) {
/* 3937 */         this.bitField0_ |= 0x10;
/* 3938 */         this.userSid_ = value;
/* 3940 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearUserSid() {
/* 3943 */         this.bitField0_ &= 0xFFFFFFEF;
/* 3944 */         this.userSid_ = 0;
/* 3946 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasVisible() {
/* 3952 */         return ((this.bitField0_ & 0x20) == 32);
/*      */       }
/*      */       
/*      */       public boolean getVisible() {
/* 3955 */         return this.visible_;
/*      */       }
/*      */       
/*      */       public Builder setVisible(boolean value) {
/* 3958 */         this.bitField0_ |= 0x20;
/* 3959 */         this.visible_ = value;
/* 3961 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVisible() {
/* 3964 */         this.bitField0_ &= 0xFFFFFFDF;
/* 3965 */         this.visible_ = false;
/* 3967 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 3974 */     private static final Info defaultInstance = new Info(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int VERSION_FIELD_NUMBER = 1;
/*      */     
/*      */     private int version_;
/*      */     
/*      */     public static final int TIMESTAMP_FIELD_NUMBER = 2;
/*      */     
/*      */     private long timestamp_;
/*      */     
/*      */     public static final int CHANGESET_FIELD_NUMBER = 3;
/*      */     
/*      */     private long changeset_;
/*      */     
/*      */     public static final int UID_FIELD_NUMBER = 4;
/*      */     
/*      */     private int uid_;
/*      */     
/*      */     public static final int USER_SID_FIELD_NUMBER = 5;
/*      */     
/*      */     private int userSid_;
/*      */     
/*      */     public static final int VISIBLE_FIELD_NUMBER = 6;
/*      */     
/*      */     private boolean visible_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 3975 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface DenseInfoOrBuilder extends MessageLiteOrBuilder {
/*      */     List<Integer> getVersionList();
/*      */     
/*      */     int getVersionCount();
/*      */     
/*      */     int getVersion(int param1Int);
/*      */     
/*      */     List<Long> getTimestampList();
/*      */     
/*      */     int getTimestampCount();
/*      */     
/*      */     long getTimestamp(int param1Int);
/*      */     
/*      */     List<Long> getChangesetList();
/*      */     
/*      */     int getChangesetCount();
/*      */     
/*      */     long getChangeset(int param1Int);
/*      */     
/*      */     List<Integer> getUidList();
/*      */     
/*      */     int getUidCount();
/*      */     
/*      */     int getUid(int param1Int);
/*      */     
/*      */     List<Integer> getUserSidList();
/*      */     
/*      */     int getUserSidCount();
/*      */     
/*      */     int getUserSid(int param1Int);
/*      */     
/*      */     List<Boolean> getVisibleList();
/*      */     
/*      */     int getVisibleCount();
/*      */     
/*      */     boolean getVisible(int param1Int);
/*      */   }
/*      */   
/*      */   public static final class DenseInfo extends GeneratedMessageLite implements DenseInfoOrBuilder {
/*      */     private DenseInfo(Builder builder) {
/* 4019 */       super(builder);
/* 4045 */       this.versionMemoizedSerializedSize = -1;
/* 4060 */       this.timestampMemoizedSerializedSize = -1;
/* 4075 */       this.changesetMemoizedSerializedSize = -1;
/* 4090 */       this.uidMemoizedSerializedSize = -1;
/* 4105 */       this.userSidMemoizedSerializedSize = -1;
/* 4120 */       this.visibleMemoizedSerializedSize = -1;
/* 4130 */       this.memoizedIsInitialized = -1;
/* 4186 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private DenseInfo(boolean noInit) {
/*      */       this.versionMemoizedSerializedSize = -1;
/*      */       this.timestampMemoizedSerializedSize = -1;
/*      */       this.changesetMemoizedSerializedSize = -1;
/*      */       this.uidMemoizedSerializedSize = -1;
/*      */       this.userSidMemoizedSerializedSize = -1;
/*      */       this.visibleMemoizedSerializedSize = -1;
/*      */       this.memoizedIsInitialized = -1;
/* 4186 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static DenseInfo getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public DenseInfo getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public List<Integer> getVersionList() {
/*      */       return this.version_;
/*      */     }
/*      */     
/*      */     public int getVersionCount() {
/*      */       return this.version_.size();
/*      */     }
/*      */     
/*      */     public int getVersion(int index) {
/*      */       return ((Integer)this.version_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Long> getTimestampList() {
/*      */       return this.timestamp_;
/*      */     }
/*      */     
/*      */     public int getTimestampCount() {
/*      */       return this.timestamp_.size();
/*      */     }
/*      */     
/*      */     public long getTimestamp(int index) {
/*      */       return ((Long)this.timestamp_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public List<Long> getChangesetList() {
/*      */       return this.changeset_;
/*      */     }
/*      */     
/*      */     public int getChangesetCount() {
/*      */       return this.changeset_.size();
/*      */     }
/*      */     
/*      */     public long getChangeset(int index) {
/*      */       return ((Long)this.changeset_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getUidList() {
/*      */       return this.uid_;
/*      */     }
/*      */     
/*      */     public int getUidCount() {
/*      */       return this.uid_.size();
/*      */     }
/*      */     
/*      */     public int getUid(int index) {
/*      */       return ((Integer)this.uid_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getUserSidList() {
/*      */       return this.userSid_;
/*      */     }
/*      */     
/*      */     public int getUserSidCount() {
/*      */       return this.userSid_.size();
/*      */     }
/*      */     
/*      */     public int getUserSid(int index) {
/*      */       return ((Integer)this.userSid_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Boolean> getVisibleList() {
/*      */       return this.visible_;
/*      */     }
/*      */     
/*      */     public int getVisibleCount() {
/*      */       return this.visible_.size();
/*      */     }
/*      */     
/*      */     public boolean getVisible(int index) {
/*      */       return ((Boolean)this.visible_.get(index)).booleanValue();
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.version_ = Collections.emptyList();
/*      */       this.timestamp_ = Collections.emptyList();
/*      */       this.changeset_ = Collections.emptyList();
/*      */       this.uid_ = Collections.emptyList();
/*      */       this.userSid_ = Collections.emptyList();
/*      */       this.visible_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if (getVersionList().size() > 0) {
/*      */         output.writeRawVarint32(10);
/*      */         output.writeRawVarint32(this.versionMemoizedSerializedSize);
/*      */       } 
/*      */       int i;
/*      */       for (i = 0; i < this.version_.size(); i++)
/*      */         output.writeInt32NoTag(((Integer)this.version_.get(i)).intValue()); 
/*      */       if (getTimestampList().size() > 0) {
/*      */         output.writeRawVarint32(18);
/*      */         output.writeRawVarint32(this.timestampMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.timestamp_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.timestamp_.get(i)).longValue()); 
/*      */       if (getChangesetList().size() > 0) {
/*      */         output.writeRawVarint32(26);
/*      */         output.writeRawVarint32(this.changesetMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.changeset_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.changeset_.get(i)).longValue()); 
/*      */       if (getUidList().size() > 0) {
/*      */         output.writeRawVarint32(34);
/*      */         output.writeRawVarint32(this.uidMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.uid_.size(); i++)
/*      */         output.writeSInt32NoTag(((Integer)this.uid_.get(i)).intValue()); 
/*      */       if (getUserSidList().size() > 0) {
/*      */         output.writeRawVarint32(42);
/*      */         output.writeRawVarint32(this.userSidMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.userSid_.size(); i++)
/*      */         output.writeSInt32NoTag(((Integer)this.userSid_.get(i)).intValue()); 
/*      */       if (getVisibleList().size() > 0) {
/*      */         output.writeRawVarint32(50);
/*      */         output.writeRawVarint32(this.visibleMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.visible_.size(); i++)
/*      */         output.writeBoolNoTag(((Boolean)this.visible_.get(i)).booleanValue()); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 4188 */       int size = this.memoizedSerializedSize;
/* 4189 */       if (size != -1)
/* 4189 */         return size; 
/* 4191 */       size = 0;
/* 4193 */       int dataSize = 0;
/*      */       int i;
/* 4194 */       for (i = 0; i < this.version_.size(); i++)
/* 4195 */         dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.version_.get(i)).intValue()); 
/* 4198 */       size += dataSize;
/* 4199 */       if (!getVersionList().isEmpty()) {
/* 4200 */         size++;
/* 4201 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4204 */       this.versionMemoizedSerializedSize = dataSize;
/* 4207 */       dataSize = 0;
/* 4208 */       for (i = 0; i < this.timestamp_.size(); i++)
/* 4209 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.timestamp_.get(i)).longValue()); 
/* 4212 */       size += dataSize;
/* 4213 */       if (!getTimestampList().isEmpty()) {
/* 4214 */         size++;
/* 4215 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4218 */       this.timestampMemoizedSerializedSize = dataSize;
/* 4221 */       dataSize = 0;
/* 4222 */       for (i = 0; i < this.changeset_.size(); i++)
/* 4223 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.changeset_.get(i)).longValue()); 
/* 4226 */       size += dataSize;
/* 4227 */       if (!getChangesetList().isEmpty()) {
/* 4228 */         size++;
/* 4229 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4232 */       this.changesetMemoizedSerializedSize = dataSize;
/* 4235 */       dataSize = 0;
/* 4236 */       for (i = 0; i < this.uid_.size(); i++)
/* 4237 */         dataSize += CodedOutputStream.computeSInt32SizeNoTag(((Integer)this.uid_.get(i)).intValue()); 
/* 4240 */       size += dataSize;
/* 4241 */       if (!getUidList().isEmpty()) {
/* 4242 */         size++;
/* 4243 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4246 */       this.uidMemoizedSerializedSize = dataSize;
/* 4249 */       dataSize = 0;
/* 4250 */       for (i = 0; i < this.userSid_.size(); i++)
/* 4251 */         dataSize += CodedOutputStream.computeSInt32SizeNoTag(((Integer)this.userSid_.get(i)).intValue()); 
/* 4254 */       size += dataSize;
/* 4255 */       if (!getUserSidList().isEmpty()) {
/* 4256 */         size++;
/* 4257 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4260 */       this.userSidMemoizedSerializedSize = dataSize;
/* 4263 */       dataSize = 0;
/* 4264 */       dataSize = 1 * getVisibleList().size();
/* 4265 */       size += dataSize;
/* 4266 */       if (!getVisibleList().isEmpty()) {
/* 4267 */         size++;
/* 4268 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 4271 */       this.visibleMemoizedSerializedSize = dataSize;
/* 4273 */       this.memoizedSerializedSize = size;
/* 4274 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 4281 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 4287 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 4293 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 4298 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 4304 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(InputStream input) throws IOException {
/* 4309 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 4315 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseDelimitedFrom(InputStream input) throws IOException {
/* 4320 */       Builder builder = newBuilder();
/* 4321 */       if (builder.mergeDelimitedFrom(input))
/* 4322 */         return builder.buildParsed(); 
/* 4324 */       return null;
/*      */     }
/*      */     
/*      */     public static DenseInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 4331 */       Builder builder = newBuilder();
/* 4332 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 4333 */         return builder.buildParsed(); 
/* 4335 */       return null;
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(CodedInputStream input) throws IOException {
/* 4341 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 4347 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 4351 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 4352 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(DenseInfo prototype) {
/* 4354 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 4356 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<DenseInfo, Builder> implements Osmformat.DenseInfoOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private List<Integer> version_;
/*      */       
/*      */       private List<Long> timestamp_;
/*      */       
/*      */       private List<Long> changeset_;
/*      */       
/*      */       private List<Integer> uid_;
/*      */       
/*      */       private List<Integer> userSid_;
/*      */       
/*      */       private List<Boolean> visible_;
/*      */       
/*      */       private Builder() {
/* 4629 */         this.version_ = Collections.emptyList();
/* 4674 */         this.timestamp_ = Collections.emptyList();
/* 4719 */         this.changeset_ = Collections.emptyList();
/* 4764 */         this.uid_ = Collections.emptyList();
/* 4809 */         this.userSid_ = Collections.emptyList();
/* 4854 */         this.visible_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.version_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.timestamp_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.changeset_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.uid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.userSid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.visible_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.DenseInfo getDefaultInstanceForType() {
/*      */         return Osmformat.DenseInfo.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.DenseInfo build() {
/*      */         Osmformat.DenseInfo result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.DenseInfo buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.DenseInfo result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.DenseInfo buildPartial() {
/*      */         Osmformat.DenseInfo result = new Osmformat.DenseInfo(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         if ((this.bitField0_ & 0x1) == 1) {
/*      */           this.version_ = Collections.unmodifiableList(this.version_);
/*      */           this.bitField0_ &= 0xFFFFFFFE;
/*      */         } 
/*      */         result.version_ = this.version_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.timestamp_ = Collections.unmodifiableList(this.timestamp_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.timestamp_ = this.timestamp_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.changeset_ = Collections.unmodifiableList(this.changeset_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.changeset_ = this.changeset_;
/*      */         if ((this.bitField0_ & 0x8) == 8) {
/*      */           this.uid_ = Collections.unmodifiableList(this.uid_);
/*      */           this.bitField0_ &= 0xFFFFFFF7;
/*      */         } 
/*      */         result.uid_ = this.uid_;
/*      */         if ((this.bitField0_ & 0x10) == 16) {
/*      */           this.userSid_ = Collections.unmodifiableList(this.userSid_);
/*      */           this.bitField0_ &= 0xFFFFFFEF;
/*      */         } 
/*      */         result.userSid_ = this.userSid_;
/*      */         if ((this.bitField0_ & 0x20) == 32) {
/*      */           this.visible_ = Collections.unmodifiableList(this.visible_);
/*      */           this.bitField0_ &= 0xFFFFFFDF;
/*      */         } 
/*      */         result.visible_ = this.visible_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.DenseInfo other) {
/*      */         if (other == Osmformat.DenseInfo.getDefaultInstance())
/*      */           return this; 
/*      */         if (!other.version_.isEmpty())
/*      */           if (this.version_.isEmpty()) {
/*      */             this.version_ = other.version_;
/*      */             this.bitField0_ &= 0xFFFFFFFE;
/*      */           } else {
/*      */             ensureVersionIsMutable();
/*      */             this.version_.addAll(other.version_);
/*      */           }  
/*      */         if (!other.timestamp_.isEmpty())
/*      */           if (this.timestamp_.isEmpty()) {
/*      */             this.timestamp_ = other.timestamp_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensureTimestampIsMutable();
/*      */             this.timestamp_.addAll(other.timestamp_);
/*      */           }  
/*      */         if (!other.changeset_.isEmpty())
/*      */           if (this.changeset_.isEmpty()) {
/*      */             this.changeset_ = other.changeset_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureChangesetIsMutable();
/*      */             this.changeset_.addAll(other.changeset_);
/*      */           }  
/*      */         if (!other.uid_.isEmpty())
/*      */           if (this.uid_.isEmpty()) {
/*      */             this.uid_ = other.uid_;
/*      */             this.bitField0_ &= 0xFFFFFFF7;
/*      */           } else {
/*      */             ensureUidIsMutable();
/*      */             this.uid_.addAll(other.uid_);
/*      */           }  
/*      */         if (!other.userSid_.isEmpty())
/*      */           if (this.userSid_.isEmpty()) {
/*      */             this.userSid_ = other.userSid_;
/*      */             this.bitField0_ &= 0xFFFFFFEF;
/*      */           } else {
/*      */             ensureUserSidIsMutable();
/*      */             this.userSid_.addAll(other.userSid_);
/*      */           }  
/*      */         if (!other.visible_.isEmpty())
/*      */           if (this.visible_.isEmpty()) {
/*      */             this.visible_ = other.visible_;
/*      */             this.bitField0_ &= 0xFFFFFFDF;
/*      */           } else {
/*      */             ensureVisibleIsMutable();
/*      */             this.visible_.addAll(other.visible_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int length, limit, tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               ensureVersionIsMutable();
/*      */               this.version_.add(Integer.valueOf(input.readInt32()));
/*      */               break;
/*      */             case 10:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addVersion(input.readInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 16:
/*      */               ensureTimestampIsMutable();
/*      */               this.timestamp_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 18:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addTimestamp(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 24:
/*      */               ensureChangesetIsMutable();
/*      */               this.changeset_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 26:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addChangeset(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 32:
/*      */               ensureUidIsMutable();
/*      */               this.uid_.add(Integer.valueOf(input.readSInt32()));
/*      */               break;
/*      */             case 34:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addUid(input.readSInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 40:
/*      */               ensureUserSidIsMutable();
/*      */               this.userSid_.add(Integer.valueOf(input.readSInt32()));
/*      */               break;
/*      */             case 42:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addUserSid(input.readSInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 48:
/*      */               ensureVisibleIsMutable();
/*      */               this.visible_.add(Boolean.valueOf(input.readBool()));
/*      */               break;
/*      */             case 50:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addVisible(input.readBool()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       private void ensureVersionIsMutable() {
/*      */         if ((this.bitField0_ & 0x1) != 1) {
/*      */           this.version_ = new ArrayList<Integer>(this.version_);
/*      */           this.bitField0_ |= 0x1;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getVersionList() {
/*      */         return Collections.unmodifiableList(this.version_);
/*      */       }
/*      */       
/*      */       public int getVersionCount() {
/*      */         return this.version_.size();
/*      */       }
/*      */       
/*      */       public int getVersion(int index) {
/*      */         return ((Integer)this.version_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setVersion(int index, int value) {
/*      */         ensureVersionIsMutable();
/*      */         this.version_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addVersion(int value) {
/*      */         ensureVersionIsMutable();
/*      */         this.version_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllVersion(Iterable<? extends Integer> values) {
/*      */         ensureVersionIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.version_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVersion() {
/*      */         this.version_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureTimestampIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.timestamp_ = new ArrayList<Long>(this.timestamp_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getTimestampList() {
/*      */         return Collections.unmodifiableList(this.timestamp_);
/*      */       }
/*      */       
/*      */       public int getTimestampCount() {
/*      */         return this.timestamp_.size();
/*      */       }
/*      */       
/*      */       public long getTimestamp(int index) {
/*      */         return ((Long)this.timestamp_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setTimestamp(int index, long value) {
/*      */         ensureTimestampIsMutable();
/*      */         this.timestamp_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addTimestamp(long value) {
/*      */         ensureTimestampIsMutable();
/*      */         this.timestamp_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllTimestamp(Iterable<? extends Long> values) {
/*      */         ensureTimestampIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.timestamp_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearTimestamp() {
/*      */         this.timestamp_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureChangesetIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.changeset_ = new ArrayList<Long>(this.changeset_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getChangesetList() {
/*      */         return Collections.unmodifiableList(this.changeset_);
/*      */       }
/*      */       
/*      */       public int getChangesetCount() {
/*      */         return this.changeset_.size();
/*      */       }
/*      */       
/*      */       public long getChangeset(int index) {
/*      */         return ((Long)this.changeset_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setChangeset(int index, long value) {
/*      */         ensureChangesetIsMutable();
/*      */         this.changeset_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addChangeset(long value) {
/*      */         ensureChangesetIsMutable();
/*      */         this.changeset_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllChangeset(Iterable<? extends Long> values) {
/*      */         ensureChangesetIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.changeset_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearChangeset() {
/*      */         this.changeset_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureUidIsMutable() {
/*      */         if ((this.bitField0_ & 0x8) != 8) {
/*      */           this.uid_ = new ArrayList<Integer>(this.uid_);
/*      */           this.bitField0_ |= 0x8;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getUidList() {
/*      */         return Collections.unmodifiableList(this.uid_);
/*      */       }
/*      */       
/*      */       public int getUidCount() {
/*      */         return this.uid_.size();
/*      */       }
/*      */       
/*      */       public int getUid(int index) {
/*      */         return ((Integer)this.uid_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setUid(int index, int value) {
/*      */         ensureUidIsMutable();
/*      */         this.uid_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addUid(int value) {
/*      */         ensureUidIsMutable();
/*      */         this.uid_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllUid(Iterable<? extends Integer> values) {
/*      */         ensureUidIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.uid_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearUid() {
/*      */         this.uid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureUserSidIsMutable() {
/*      */         if ((this.bitField0_ & 0x10) != 16) {
/*      */           this.userSid_ = new ArrayList<Integer>(this.userSid_);
/*      */           this.bitField0_ |= 0x10;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getUserSidList() {
/*      */         return Collections.unmodifiableList(this.userSid_);
/*      */       }
/*      */       
/*      */       public int getUserSidCount() {
/*      */         return this.userSid_.size();
/*      */       }
/*      */       
/*      */       public int getUserSid(int index) {
/*      */         return ((Integer)this.userSid_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setUserSid(int index, int value) {
/*      */         ensureUserSidIsMutable();
/*      */         this.userSid_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addUserSid(int value) {
/*      */         ensureUserSidIsMutable();
/*      */         this.userSid_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllUserSid(Iterable<? extends Integer> values) {
/*      */         ensureUserSidIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.userSid_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearUserSid() {
/*      */         this.userSid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureVisibleIsMutable() {
/* 4856 */         if ((this.bitField0_ & 0x20) != 32) {
/* 4857 */           this.visible_ = new ArrayList<Boolean>(this.visible_);
/* 4858 */           this.bitField0_ |= 0x20;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Boolean> getVisibleList() {
/* 4863 */         return Collections.unmodifiableList(this.visible_);
/*      */       }
/*      */       
/*      */       public int getVisibleCount() {
/* 4866 */         return this.visible_.size();
/*      */       }
/*      */       
/*      */       public boolean getVisible(int index) {
/* 4869 */         return ((Boolean)this.visible_.get(index)).booleanValue();
/*      */       }
/*      */       
/*      */       public Builder setVisible(int index, boolean value) {
/* 4873 */         ensureVisibleIsMutable();
/* 4874 */         this.visible_.set(index, Boolean.valueOf(value));
/* 4876 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addVisible(boolean value) {
/* 4879 */         ensureVisibleIsMutable();
/* 4880 */         this.visible_.add(Boolean.valueOf(value));
/* 4882 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllVisible(Iterable<? extends Boolean> values) {
/* 4886 */         ensureVisibleIsMutable();
/* 4887 */         GeneratedMessageLite.Builder.addAll(values, this.visible_);
/* 4889 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVisible() {
/* 4892 */         this.visible_ = Collections.emptyList();
/* 4893 */         this.bitField0_ &= 0xFFFFFFDF;
/* 4895 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 4902 */     private static final DenseInfo defaultInstance = new DenseInfo(true);
/*      */     
/*      */     public static final int VERSION_FIELD_NUMBER = 1;
/*      */     
/*      */     private List<Integer> version_;
/*      */     
/*      */     private int versionMemoizedSerializedSize;
/*      */     
/*      */     public static final int TIMESTAMP_FIELD_NUMBER = 2;
/*      */     
/*      */     private List<Long> timestamp_;
/*      */     
/*      */     private int timestampMemoizedSerializedSize;
/*      */     
/*      */     public static final int CHANGESET_FIELD_NUMBER = 3;
/*      */     
/*      */     private List<Long> changeset_;
/*      */     
/*      */     private int changesetMemoizedSerializedSize;
/*      */     
/*      */     public static final int UID_FIELD_NUMBER = 4;
/*      */     
/*      */     private List<Integer> uid_;
/*      */     
/*      */     private int uidMemoizedSerializedSize;
/*      */     
/*      */     public static final int USER_SID_FIELD_NUMBER = 5;
/*      */     
/*      */     private List<Integer> userSid_;
/*      */     
/*      */     private int userSidMemoizedSerializedSize;
/*      */     
/*      */     public static final int VISIBLE_FIELD_NUMBER = 6;
/*      */     
/*      */     private List<Boolean> visible_;
/*      */     
/*      */     private int visibleMemoizedSerializedSize;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 4903 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface ChangeSetOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasId();
/*      */     
/*      */     long getId();
/*      */   }
/*      */   
/*      */   public static final class ChangeSet extends GeneratedMessageLite implements ChangeSetOrBuilder {
/*      */     private ChangeSet(Builder builder) {
/* 4921 */       super(builder);
/* 4948 */       this.memoizedIsInitialized = -1;
/* 4969 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private ChangeSet(boolean noInit) {
/*      */       this.memoizedIsInitialized = -1;
/* 4969 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static ChangeSet getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public ChangeSet getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasId() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public long getId() {
/*      */       return this.id_;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.id_ = 0L;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasId()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeInt64(1, this.id_); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 4971 */       int size = this.memoizedSerializedSize;
/* 4972 */       if (size != -1)
/* 4972 */         return size; 
/* 4974 */       size = 0;
/* 4975 */       if ((this.bitField0_ & 0x1) == 1)
/* 4976 */         size += CodedOutputStream.computeInt64Size(1, this.id_); 
/* 4979 */       this.memoizedSerializedSize = size;
/* 4980 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 4987 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 4993 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 4999 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 5004 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5010 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(InputStream input) throws IOException {
/* 5015 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5021 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseDelimitedFrom(InputStream input) throws IOException {
/* 5026 */       Builder builder = newBuilder();
/* 5027 */       if (builder.mergeDelimitedFrom(input))
/* 5028 */         return builder.buildParsed(); 
/* 5030 */       return null;
/*      */     }
/*      */     
/*      */     public static ChangeSet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5037 */       Builder builder = newBuilder();
/* 5038 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 5039 */         return builder.buildParsed(); 
/* 5041 */       return null;
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(CodedInputStream input) throws IOException {
/* 5047 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static ChangeSet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5053 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 5057 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 5058 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(ChangeSet prototype) {
/* 5060 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 5062 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<ChangeSet, Builder> implements Osmformat.ChangeSetOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private long id_;
/*      */       
/*      */       private Builder() {
/* 5070 */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/* 5076 */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/* 5080 */         super.clear();
/* 5081 */         this.id_ = 0L;
/* 5082 */         this.bitField0_ &= 0xFFFFFFFE;
/* 5083 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/* 5087 */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.ChangeSet getDefaultInstanceForType() {
/* 5091 */         return Osmformat.ChangeSet.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.ChangeSet build() {
/* 5095 */         Osmformat.ChangeSet result = buildPartial();
/* 5096 */         if (!result.isInitialized())
/* 5097 */           throw newUninitializedMessageException(result); 
/* 5099 */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.ChangeSet buildParsed() throws InvalidProtocolBufferException {
/* 5104 */         Osmformat.ChangeSet result = buildPartial();
/* 5105 */         if (!result.isInitialized())
/* 5106 */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/* 5109 */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.ChangeSet buildPartial() {
/* 5113 */         Osmformat.ChangeSet result = new Osmformat.ChangeSet(this);
/* 5114 */         int from_bitField0_ = this.bitField0_;
/* 5115 */         int to_bitField0_ = 0;
/* 5116 */         if ((from_bitField0_ & 0x1) == 1)
/* 5117 */           to_bitField0_ |= 0x1; 
/* 5119 */         result.id_ = this.id_;
/* 5120 */         result.bitField0_ = to_bitField0_;
/* 5121 */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.ChangeSet other) {
/* 5125 */         if (other == Osmformat.ChangeSet.getDefaultInstance())
/* 5125 */           return this; 
/* 5126 */         if (other.hasId())
/* 5127 */           setId(other.getId()); 
/* 5129 */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/* 5133 */         if (!hasId())
/* 5135 */           return false; 
/* 5137 */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/* 5145 */           int tag = input.readTag();
/* 5146 */           switch (tag) {
/*      */             case 0:
/* 5149 */               return this;
/*      */             case 8:
/* 5158 */               this.bitField0_ |= 0x1;
/* 5159 */               this.id_ = input.readInt64();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasId() {
/* 5171 */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public long getId() {
/* 5174 */         return this.id_;
/*      */       }
/*      */       
/*      */       public Builder setId(long value) {
/* 5177 */         this.bitField0_ |= 0x1;
/* 5178 */         this.id_ = value;
/* 5180 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearId() {
/* 5183 */         this.bitField0_ &= 0xFFFFFFFE;
/* 5184 */         this.id_ = 0L;
/* 5186 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 5193 */     private static final ChangeSet defaultInstance = new ChangeSet(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int ID_FIELD_NUMBER = 1;
/*      */     
/*      */     private long id_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 5194 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface NodeOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasId();
/*      */     
/*      */     long getId();
/*      */     
/*      */     List<Integer> getKeysList();
/*      */     
/*      */     int getKeysCount();
/*      */     
/*      */     int getKeys(int param1Int);
/*      */     
/*      */     List<Integer> getValsList();
/*      */     
/*      */     int getValsCount();
/*      */     
/*      */     int getVals(int param1Int);
/*      */     
/*      */     boolean hasInfo();
/*      */     
/*      */     Osmformat.Info getInfo();
/*      */     
/*      */     boolean hasLat();
/*      */     
/*      */     long getLat();
/*      */     
/*      */     boolean hasLon();
/*      */     
/*      */     long getLon();
/*      */   }
/*      */   
/*      */   public static final class Node extends GeneratedMessageLite implements NodeOrBuilder {
/*      */     private Node(Builder builder) {
/* 5234 */       super(builder);
/* 5271 */       this.keysMemoizedSerializedSize = -1;
/* 5286 */       this.valsMemoizedSerializedSize = -1;
/* 5326 */       this.memoizedIsInitialized = -1;
/* 5378 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private Node(boolean noInit) {
/*      */       this.keysMemoizedSerializedSize = -1;
/*      */       this.valsMemoizedSerializedSize = -1;
/*      */       this.memoizedIsInitialized = -1;
/* 5378 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static Node getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public Node getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasId() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public long getId() {
/*      */       return this.id_;
/*      */     }
/*      */     
/*      */     public List<Integer> getKeysList() {
/*      */       return this.keys_;
/*      */     }
/*      */     
/*      */     public int getKeysCount() {
/*      */       return this.keys_.size();
/*      */     }
/*      */     
/*      */     public int getKeys(int index) {
/*      */       return ((Integer)this.keys_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getValsList() {
/*      */       return this.vals_;
/*      */     }
/*      */     
/*      */     public int getValsCount() {
/*      */       return this.vals_.size();
/*      */     }
/*      */     
/*      */     public int getVals(int index) {
/*      */       return ((Integer)this.vals_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public boolean hasInfo() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public Osmformat.Info getInfo() {
/*      */       return this.info_;
/*      */     }
/*      */     
/*      */     public boolean hasLat() {
/*      */       return ((this.bitField0_ & 0x4) == 4);
/*      */     }
/*      */     
/*      */     public long getLat() {
/*      */       return this.lat_;
/*      */     }
/*      */     
/*      */     public boolean hasLon() {
/*      */       return ((this.bitField0_ & 0x8) == 8);
/*      */     }
/*      */     
/*      */     public long getLon() {
/*      */       return this.lon_;
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.id_ = 0L;
/*      */       this.keys_ = Collections.emptyList();
/*      */       this.vals_ = Collections.emptyList();
/*      */       this.info_ = Osmformat.Info.getDefaultInstance();
/*      */       this.lat_ = 0L;
/*      */       this.lon_ = 0L;
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasId()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       if (!hasLat()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       if (!hasLon()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeSInt64(1, this.id_); 
/*      */       if (getKeysList().size() > 0) {
/*      */         output.writeRawVarint32(18);
/*      */         output.writeRawVarint32(this.keysMemoizedSerializedSize);
/*      */       } 
/*      */       int i;
/*      */       for (i = 0; i < this.keys_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.keys_.get(i)).intValue()); 
/*      */       if (getValsList().size() > 0) {
/*      */         output.writeRawVarint32(26);
/*      */         output.writeRawVarint32(this.valsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.vals_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.vals_.get(i)).intValue()); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeMessage(4, (MessageLite)this.info_); 
/*      */       if ((this.bitField0_ & 0x4) == 4)
/*      */         output.writeSInt64(8, this.lat_); 
/*      */       if ((this.bitField0_ & 0x8) == 8)
/*      */         output.writeSInt64(9, this.lon_); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 5380 */       int size = this.memoizedSerializedSize;
/* 5381 */       if (size != -1)
/* 5381 */         return size; 
/* 5383 */       size = 0;
/* 5384 */       if ((this.bitField0_ & 0x1) == 1)
/* 5385 */         size += CodedOutputStream.computeSInt64Size(1, this.id_); 
/* 5389 */       int dataSize = 0;
/*      */       int i;
/* 5390 */       for (i = 0; i < this.keys_.size(); i++)
/* 5391 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.keys_.get(i)).intValue()); 
/* 5394 */       size += dataSize;
/* 5395 */       if (!getKeysList().isEmpty()) {
/* 5396 */         size++;
/* 5397 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 5400 */       this.keysMemoizedSerializedSize = dataSize;
/* 5403 */       dataSize = 0;
/* 5404 */       for (i = 0; i < this.vals_.size(); i++)
/* 5405 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.vals_.get(i)).intValue()); 
/* 5408 */       size += dataSize;
/* 5409 */       if (!getValsList().isEmpty()) {
/* 5410 */         size++;
/* 5411 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 5414 */       this.valsMemoizedSerializedSize = dataSize;
/* 5416 */       if ((this.bitField0_ & 0x2) == 2)
/* 5417 */         size += CodedOutputStream.computeMessageSize(4, (MessageLite)this.info_); 
/* 5420 */       if ((this.bitField0_ & 0x4) == 4)
/* 5421 */         size += CodedOutputStream.computeSInt64Size(8, this.lat_); 
/* 5424 */       if ((this.bitField0_ & 0x8) == 8)
/* 5425 */         size += CodedOutputStream.computeSInt64Size(9, this.lon_); 
/* 5428 */       this.memoizedSerializedSize = size;
/* 5429 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 5436 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 5442 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5448 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 5453 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 5459 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(InputStream input) throws IOException {
/* 5464 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5470 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseDelimitedFrom(InputStream input) throws IOException {
/* 5475 */       Builder builder = newBuilder();
/* 5476 */       if (builder.mergeDelimitedFrom(input))
/* 5477 */         return builder.buildParsed(); 
/* 5479 */       return null;
/*      */     }
/*      */     
/*      */     public static Node parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5486 */       Builder builder = newBuilder();
/* 5487 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 5488 */         return builder.buildParsed(); 
/* 5490 */       return null;
/*      */     }
/*      */     
/*      */     public static Node parseFrom(CodedInputStream input) throws IOException {
/* 5496 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Node parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 5502 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 5506 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 5507 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(Node prototype) {
/* 5509 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 5511 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<Node, Builder> implements Osmformat.NodeOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private long id_;
/*      */       
/*      */       private List<Integer> keys_;
/*      */       
/*      */       private List<Integer> vals_;
/*      */       
/*      */       private Osmformat.Info info_;
/*      */       
/*      */       private long lat_;
/*      */       
/*      */       private long lon_;
/*      */       
/*      */       private Builder() {
/* 5755 */         this.keys_ = Collections.emptyList();
/* 5800 */         this.vals_ = Collections.emptyList();
/* 5845 */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.id_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.lat_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.lon_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.Node getDefaultInstanceForType() {
/*      */         return Osmformat.Node.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.Node build() {
/*      */         Osmformat.Node result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.Node buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.Node result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.Node buildPartial() {
/*      */         Osmformat.Node result = new Osmformat.Node(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.id_ = this.id_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.keys_ = Collections.unmodifiableList(this.keys_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.keys_ = this.keys_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.vals_ = Collections.unmodifiableList(this.vals_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.vals_ = this.vals_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.info_ = this.info_;
/*      */         if ((from_bitField0_ & 0x10) == 16)
/*      */           to_bitField0_ |= 0x4; 
/*      */         result.lat_ = this.lat_;
/*      */         if ((from_bitField0_ & 0x20) == 32)
/*      */           to_bitField0_ |= 0x8; 
/*      */         result.lon_ = this.lon_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.Node other) {
/*      */         if (other == Osmformat.Node.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasId())
/*      */           setId(other.getId()); 
/*      */         if (!other.keys_.isEmpty())
/*      */           if (this.keys_.isEmpty()) {
/*      */             this.keys_ = other.keys_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensureKeysIsMutable();
/*      */             this.keys_.addAll(other.keys_);
/*      */           }  
/*      */         if (!other.vals_.isEmpty())
/*      */           if (this.vals_.isEmpty()) {
/*      */             this.vals_ = other.vals_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureValsIsMutable();
/*      */             this.vals_.addAll(other.vals_);
/*      */           }  
/*      */         if (other.hasInfo())
/*      */           mergeInfo(other.getInfo()); 
/*      */         if (other.hasLat())
/*      */           setLat(other.getLat()); 
/*      */         if (other.hasLon())
/*      */           setLon(other.getLon()); 
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         if (!hasId())
/*      */           return false; 
/*      */         if (!hasLat())
/*      */           return false; 
/*      */         if (!hasLon())
/*      */           return false; 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int length;
/*      */           Osmformat.Info.Builder subBuilder;
/*      */           int limit, tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               this.bitField0_ |= 0x1;
/*      */               this.id_ = input.readSInt64();
/*      */               break;
/*      */             case 16:
/*      */               ensureKeysIsMutable();
/*      */               this.keys_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 18:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addKeys(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 24:
/*      */               ensureValsIsMutable();
/*      */               this.vals_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 26:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addVals(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 34:
/*      */               subBuilder = Osmformat.Info.newBuilder();
/*      */               if (hasInfo())
/*      */                 subBuilder.mergeFrom(getInfo()); 
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               setInfo(subBuilder.buildPartial());
/*      */               break;
/*      */             case 64:
/*      */               this.bitField0_ |= 0x10;
/*      */               this.lat_ = input.readSInt64();
/*      */               break;
/*      */             case 72:
/*      */               this.bitField0_ |= 0x20;
/*      */               this.lon_ = input.readSInt64();
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasId() {
/*      */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public long getId() {
/*      */         return this.id_;
/*      */       }
/*      */       
/*      */       public Builder setId(long value) {
/*      */         this.bitField0_ |= 0x1;
/*      */         this.id_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearId() {
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.id_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureKeysIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.keys_ = new ArrayList<Integer>(this.keys_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getKeysList() {
/*      */         return Collections.unmodifiableList(this.keys_);
/*      */       }
/*      */       
/*      */       public int getKeysCount() {
/*      */         return this.keys_.size();
/*      */       }
/*      */       
/*      */       public int getKeys(int index) {
/*      */         return ((Integer)this.keys_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setKeys(int index, int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addKeys(int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllKeys(Iterable<? extends Integer> values) {
/*      */         ensureKeysIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.keys_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearKeys() {
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureValsIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.vals_ = new ArrayList<Integer>(this.vals_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getValsList() {
/*      */         return Collections.unmodifiableList(this.vals_);
/*      */       }
/*      */       
/*      */       public int getValsCount() {
/*      */         return this.vals_.size();
/*      */       }
/*      */       
/*      */       public int getVals(int index) {
/*      */         return ((Integer)this.vals_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setVals(int index, int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addVals(int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllVals(Iterable<? extends Integer> values) {
/*      */         ensureValsIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.vals_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVals() {
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasInfo() {
/* 5847 */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public Osmformat.Info getInfo() {
/* 5850 */         return this.info_;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info value) {
/* 5853 */         if (value == null)
/* 5854 */           throw new NullPointerException(); 
/* 5856 */         this.info_ = value;
/* 5858 */         this.bitField0_ |= 0x8;
/* 5859 */         return this;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info.Builder builderForValue) {
/* 5863 */         this.info_ = builderForValue.build();
/* 5865 */         this.bitField0_ |= 0x8;
/* 5866 */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeInfo(Osmformat.Info value) {
/* 5869 */         if ((this.bitField0_ & 0x8) == 8 && this.info_ != Osmformat.Info.getDefaultInstance()) {
/* 5871 */           this.info_ = Osmformat.Info.newBuilder(this.info_).mergeFrom(value).buildPartial();
/*      */         } else {
/* 5874 */           this.info_ = value;
/*      */         } 
/* 5877 */         this.bitField0_ |= 0x8;
/* 5878 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearInfo() {
/* 5881 */         this.info_ = Osmformat.Info.getDefaultInstance();
/* 5883 */         this.bitField0_ &= 0xFFFFFFF7;
/* 5884 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasLat() {
/* 5890 */         return ((this.bitField0_ & 0x10) == 16);
/*      */       }
/*      */       
/*      */       public long getLat() {
/* 5893 */         return this.lat_;
/*      */       }
/*      */       
/*      */       public Builder setLat(long value) {
/* 5896 */         this.bitField0_ |= 0x10;
/* 5897 */         this.lat_ = value;
/* 5899 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLat() {
/* 5902 */         this.bitField0_ &= 0xFFFFFFEF;
/* 5903 */         this.lat_ = 0L;
/* 5905 */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasLon() {
/* 5911 */         return ((this.bitField0_ & 0x20) == 32);
/*      */       }
/*      */       
/*      */       public long getLon() {
/* 5914 */         return this.lon_;
/*      */       }
/*      */       
/*      */       public Builder setLon(long value) {
/* 5917 */         this.bitField0_ |= 0x20;
/* 5918 */         this.lon_ = value;
/* 5920 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLon() {
/* 5923 */         this.bitField0_ &= 0xFFFFFFDF;
/* 5924 */         this.lon_ = 0L;
/* 5926 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 5933 */     private static final Node defaultInstance = new Node(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int ID_FIELD_NUMBER = 1;
/*      */     
/*      */     private long id_;
/*      */     
/*      */     public static final int KEYS_FIELD_NUMBER = 2;
/*      */     
/*      */     private List<Integer> keys_;
/*      */     
/*      */     private int keysMemoizedSerializedSize;
/*      */     
/*      */     public static final int VALS_FIELD_NUMBER = 3;
/*      */     
/*      */     private List<Integer> vals_;
/*      */     
/*      */     private int valsMemoizedSerializedSize;
/*      */     
/*      */     public static final int INFO_FIELD_NUMBER = 4;
/*      */     
/*      */     private Osmformat.Info info_;
/*      */     
/*      */     public static final int LAT_FIELD_NUMBER = 8;
/*      */     
/*      */     private long lat_;
/*      */     
/*      */     public static final int LON_FIELD_NUMBER = 9;
/*      */     
/*      */     private long lon_;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 5934 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface DenseNodesOrBuilder extends MessageLiteOrBuilder {
/*      */     List<Long> getIdList();
/*      */     
/*      */     int getIdCount();
/*      */     
/*      */     long getId(int param1Int);
/*      */     
/*      */     boolean hasDenseinfo();
/*      */     
/*      */     Osmformat.DenseInfo getDenseinfo();
/*      */     
/*      */     List<Long> getLatList();
/*      */     
/*      */     int getLatCount();
/*      */     
/*      */     long getLat(int param1Int);
/*      */     
/*      */     List<Long> getLonList();
/*      */     
/*      */     int getLonCount();
/*      */     
/*      */     long getLon(int param1Int);
/*      */     
/*      */     List<Integer> getKeysValsList();
/*      */     
/*      */     int getKeysValsCount();
/*      */     
/*      */     int getKeysVals(int param1Int);
/*      */   }
/*      */   
/*      */   public static final class DenseNodes extends GeneratedMessageLite implements DenseNodesOrBuilder {
/*      */     private DenseNodes(Builder builder) {
/* 5972 */       super(builder);
/* 5999 */       this.idMemoizedSerializedSize = -1;
/* 6024 */       this.latMemoizedSerializedSize = -1;
/* 6039 */       this.lonMemoizedSerializedSize = -1;
/* 6054 */       this.keysValsMemoizedSerializedSize = -1;
/* 6063 */       this.memoizedIsInitialized = -1;
/* 6108 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private DenseNodes(boolean noInit) {
/*      */       this.idMemoizedSerializedSize = -1;
/*      */       this.latMemoizedSerializedSize = -1;
/*      */       this.lonMemoizedSerializedSize = -1;
/*      */       this.keysValsMemoizedSerializedSize = -1;
/*      */       this.memoizedIsInitialized = -1;
/* 6108 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static DenseNodes getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public DenseNodes getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public List<Long> getIdList() {
/*      */       return this.id_;
/*      */     }
/*      */     
/*      */     public int getIdCount() {
/*      */       return this.id_.size();
/*      */     }
/*      */     
/*      */     public long getId(int index) {
/*      */       return ((Long)this.id_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public boolean hasDenseinfo() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public Osmformat.DenseInfo getDenseinfo() {
/*      */       return this.denseinfo_;
/*      */     }
/*      */     
/*      */     public List<Long> getLatList() {
/*      */       return this.lat_;
/*      */     }
/*      */     
/*      */     public int getLatCount() {
/*      */       return this.lat_.size();
/*      */     }
/*      */     
/*      */     public long getLat(int index) {
/*      */       return ((Long)this.lat_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public List<Long> getLonList() {
/*      */       return this.lon_;
/*      */     }
/*      */     
/*      */     public int getLonCount() {
/*      */       return this.lon_.size();
/*      */     }
/*      */     
/*      */     public long getLon(int index) {
/*      */       return ((Long)this.lon_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getKeysValsList() {
/*      */       return this.keysVals_;
/*      */     }
/*      */     
/*      */     public int getKeysValsCount() {
/*      */       return this.keysVals_.size();
/*      */     }
/*      */     
/*      */     public int getKeysVals(int index) {
/*      */       return ((Integer)this.keysVals_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.id_ = Collections.emptyList();
/*      */       this.denseinfo_ = Osmformat.DenseInfo.getDefaultInstance();
/*      */       this.lat_ = Collections.emptyList();
/*      */       this.lon_ = Collections.emptyList();
/*      */       this.keysVals_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if (getIdList().size() > 0) {
/*      */         output.writeRawVarint32(10);
/*      */         output.writeRawVarint32(this.idMemoizedSerializedSize);
/*      */       } 
/*      */       int i;
/*      */       for (i = 0; i < this.id_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.id_.get(i)).longValue()); 
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeMessage(5, (MessageLite)this.denseinfo_); 
/*      */       if (getLatList().size() > 0) {
/*      */         output.writeRawVarint32(66);
/*      */         output.writeRawVarint32(this.latMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.lat_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.lat_.get(i)).longValue()); 
/*      */       if (getLonList().size() > 0) {
/*      */         output.writeRawVarint32(74);
/*      */         output.writeRawVarint32(this.lonMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.lon_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.lon_.get(i)).longValue()); 
/*      */       if (getKeysValsList().size() > 0) {
/*      */         output.writeRawVarint32(82);
/*      */         output.writeRawVarint32(this.keysValsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.keysVals_.size(); i++)
/*      */         output.writeInt32NoTag(((Integer)this.keysVals_.get(i)).intValue()); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 6110 */       int size = this.memoizedSerializedSize;
/* 6111 */       if (size != -1)
/* 6111 */         return size; 
/* 6113 */       size = 0;
/* 6115 */       int dataSize = 0;
/*      */       int i;
/* 6116 */       for (i = 0; i < this.id_.size(); i++)
/* 6117 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.id_.get(i)).longValue()); 
/* 6120 */       size += dataSize;
/* 6121 */       if (!getIdList().isEmpty()) {
/* 6122 */         size++;
/* 6123 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6126 */       this.idMemoizedSerializedSize = dataSize;
/* 6128 */       if ((this.bitField0_ & 0x1) == 1)
/* 6129 */         size += CodedOutputStream.computeMessageSize(5, (MessageLite)this.denseinfo_); 
/* 6133 */       dataSize = 0;
/* 6134 */       for (i = 0; i < this.lat_.size(); i++)
/* 6135 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.lat_.get(i)).longValue()); 
/* 6138 */       size += dataSize;
/* 6139 */       if (!getLatList().isEmpty()) {
/* 6140 */         size++;
/* 6141 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6144 */       this.latMemoizedSerializedSize = dataSize;
/* 6147 */       dataSize = 0;
/* 6148 */       for (i = 0; i < this.lon_.size(); i++)
/* 6149 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.lon_.get(i)).longValue()); 
/* 6152 */       size += dataSize;
/* 6153 */       if (!getLonList().isEmpty()) {
/* 6154 */         size++;
/* 6155 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6158 */       this.lonMemoizedSerializedSize = dataSize;
/* 6161 */       dataSize = 0;
/* 6162 */       for (i = 0; i < this.keysVals_.size(); i++)
/* 6163 */         dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.keysVals_.get(i)).intValue()); 
/* 6166 */       size += dataSize;
/* 6167 */       if (!getKeysValsList().isEmpty()) {
/* 6168 */         size++;
/* 6169 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6172 */       this.keysValsMemoizedSerializedSize = dataSize;
/* 6174 */       this.memoizedSerializedSize = size;
/* 6175 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 6182 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 6188 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 6194 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 6199 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 6205 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(InputStream input) throws IOException {
/* 6210 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 6216 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseDelimitedFrom(InputStream input) throws IOException {
/* 6221 */       Builder builder = newBuilder();
/* 6222 */       if (builder.mergeDelimitedFrom(input))
/* 6223 */         return builder.buildParsed(); 
/* 6225 */       return null;
/*      */     }
/*      */     
/*      */     public static DenseNodes parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 6232 */       Builder builder = newBuilder();
/* 6233 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 6234 */         return builder.buildParsed(); 
/* 6236 */       return null;
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(CodedInputStream input) throws IOException {
/* 6242 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static DenseNodes parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 6248 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 6252 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 6253 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(DenseNodes prototype) {
/* 6255 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 6257 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<DenseNodes, Builder> implements Osmformat.DenseNodesOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private List<Long> id_;
/*      */       
/*      */       private Osmformat.DenseInfo denseinfo_;
/*      */       
/*      */       private List<Long> lat_;
/*      */       
/*      */       private List<Long> lon_;
/*      */       
/*      */       private List<Integer> keysVals_;
/*      */       
/*      */       private Builder() {
/* 6488 */         this.id_ = Collections.emptyList();
/* 6533 */         this.denseinfo_ = Osmformat.DenseInfo.getDefaultInstance();
/* 6576 */         this.lat_ = Collections.emptyList();
/* 6621 */         this.lon_ = Collections.emptyList();
/* 6666 */         this.keysVals_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.id_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.denseinfo_ = Osmformat.DenseInfo.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.lat_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.lon_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.keysVals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.DenseNodes getDefaultInstanceForType() {
/*      */         return Osmformat.DenseNodes.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.DenseNodes build() {
/*      */         Osmformat.DenseNodes result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.DenseNodes buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.DenseNodes result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.DenseNodes buildPartial() {
/*      */         Osmformat.DenseNodes result = new Osmformat.DenseNodes(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((this.bitField0_ & 0x1) == 1) {
/*      */           this.id_ = Collections.unmodifiableList(this.id_);
/*      */           this.bitField0_ &= 0xFFFFFFFE;
/*      */         } 
/*      */         result.id_ = this.id_;
/*      */         if ((from_bitField0_ & 0x2) == 2)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.denseinfo_ = this.denseinfo_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.lat_ = Collections.unmodifiableList(this.lat_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.lat_ = this.lat_;
/*      */         if ((this.bitField0_ & 0x8) == 8) {
/*      */           this.lon_ = Collections.unmodifiableList(this.lon_);
/*      */           this.bitField0_ &= 0xFFFFFFF7;
/*      */         } 
/*      */         result.lon_ = this.lon_;
/*      */         if ((this.bitField0_ & 0x10) == 16) {
/*      */           this.keysVals_ = Collections.unmodifiableList(this.keysVals_);
/*      */           this.bitField0_ &= 0xFFFFFFEF;
/*      */         } 
/*      */         result.keysVals_ = this.keysVals_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.DenseNodes other) {
/*      */         if (other == Osmformat.DenseNodes.getDefaultInstance())
/*      */           return this; 
/*      */         if (!other.id_.isEmpty())
/*      */           if (this.id_.isEmpty()) {
/*      */             this.id_ = other.id_;
/*      */             this.bitField0_ &= 0xFFFFFFFE;
/*      */           } else {
/*      */             ensureIdIsMutable();
/*      */             this.id_.addAll(other.id_);
/*      */           }  
/*      */         if (other.hasDenseinfo())
/*      */           mergeDenseinfo(other.getDenseinfo()); 
/*      */         if (!other.lat_.isEmpty())
/*      */           if (this.lat_.isEmpty()) {
/*      */             this.lat_ = other.lat_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureLatIsMutable();
/*      */             this.lat_.addAll(other.lat_);
/*      */           }  
/*      */         if (!other.lon_.isEmpty())
/*      */           if (this.lon_.isEmpty()) {
/*      */             this.lon_ = other.lon_;
/*      */             this.bitField0_ &= 0xFFFFFFF7;
/*      */           } else {
/*      */             ensureLonIsMutable();
/*      */             this.lon_.addAll(other.lon_);
/*      */           }  
/*      */         if (!other.keysVals_.isEmpty())
/*      */           if (this.keysVals_.isEmpty()) {
/*      */             this.keysVals_ = other.keysVals_;
/*      */             this.bitField0_ &= 0xFFFFFFEF;
/*      */           } else {
/*      */             ensureKeysValsIsMutable();
/*      */             this.keysVals_.addAll(other.keysVals_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int i;
/*      */           Osmformat.DenseInfo.Builder subBuilder;
/*      */           int length, limit, tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               ensureIdIsMutable();
/*      */               this.id_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 10:
/*      */               i = input.readRawVarint32();
/*      */               limit = input.pushLimit(i);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addId(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 42:
/*      */               subBuilder = Osmformat.DenseInfo.newBuilder();
/*      */               if (hasDenseinfo())
/*      */                 subBuilder.mergeFrom(getDenseinfo()); 
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               setDenseinfo(subBuilder.buildPartial());
/*      */               break;
/*      */             case 64:
/*      */               ensureLatIsMutable();
/*      */               this.lat_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 66:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addLat(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 72:
/*      */               ensureLonIsMutable();
/*      */               this.lon_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 74:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addLon(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 80:
/*      */               ensureKeysValsIsMutable();
/*      */               this.keysVals_.add(Integer.valueOf(input.readInt32()));
/*      */               break;
/*      */             case 82:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addKeysVals(input.readInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       private void ensureIdIsMutable() {
/*      */         if ((this.bitField0_ & 0x1) != 1) {
/*      */           this.id_ = new ArrayList<Long>(this.id_);
/*      */           this.bitField0_ |= 0x1;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getIdList() {
/*      */         return Collections.unmodifiableList(this.id_);
/*      */       }
/*      */       
/*      */       public int getIdCount() {
/*      */         return this.id_.size();
/*      */       }
/*      */       
/*      */       public long getId(int index) {
/*      */         return ((Long)this.id_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setId(int index, long value) {
/*      */         ensureIdIsMutable();
/*      */         this.id_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addId(long value) {
/*      */         ensureIdIsMutable();
/*      */         this.id_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllId(Iterable<? extends Long> values) {
/*      */         ensureIdIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.id_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearId() {
/*      */         this.id_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasDenseinfo() {
/*      */         return ((this.bitField0_ & 0x2) == 2);
/*      */       }
/*      */       
/*      */       public Osmformat.DenseInfo getDenseinfo() {
/*      */         return this.denseinfo_;
/*      */       }
/*      */       
/*      */       public Builder setDenseinfo(Osmformat.DenseInfo value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.denseinfo_ = value;
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setDenseinfo(Osmformat.DenseInfo.Builder builderForValue) {
/*      */         this.denseinfo_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeDenseinfo(Osmformat.DenseInfo value) {
/*      */         if ((this.bitField0_ & 0x2) == 2 && this.denseinfo_ != Osmformat.DenseInfo.getDefaultInstance()) {
/*      */           this.denseinfo_ = Osmformat.DenseInfo.newBuilder(this.denseinfo_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.denseinfo_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x2;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearDenseinfo() {
/*      */         this.denseinfo_ = Osmformat.DenseInfo.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureLatIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.lat_ = new ArrayList<Long>(this.lat_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getLatList() {
/*      */         return Collections.unmodifiableList(this.lat_);
/*      */       }
/*      */       
/*      */       public int getLatCount() {
/*      */         return this.lat_.size();
/*      */       }
/*      */       
/*      */       public long getLat(int index) {
/*      */         return ((Long)this.lat_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setLat(int index, long value) {
/*      */         ensureLatIsMutable();
/*      */         this.lat_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addLat(long value) {
/*      */         ensureLatIsMutable();
/*      */         this.lat_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllLat(Iterable<? extends Long> values) {
/*      */         ensureLatIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.lat_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLat() {
/*      */         this.lat_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureLonIsMutable() {
/*      */         if ((this.bitField0_ & 0x8) != 8) {
/*      */           this.lon_ = new ArrayList<Long>(this.lon_);
/*      */           this.bitField0_ |= 0x8;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getLonList() {
/*      */         return Collections.unmodifiableList(this.lon_);
/*      */       }
/*      */       
/*      */       public int getLonCount() {
/*      */         return this.lon_.size();
/*      */       }
/*      */       
/*      */       public long getLon(int index) {
/*      */         return ((Long)this.lon_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setLon(int index, long value) {
/*      */         ensureLonIsMutable();
/*      */         this.lon_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addLon(long value) {
/*      */         ensureLonIsMutable();
/*      */         this.lon_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllLon(Iterable<? extends Long> values) {
/*      */         ensureLonIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.lon_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearLon() {
/*      */         this.lon_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureKeysValsIsMutable() {
/* 6668 */         if ((this.bitField0_ & 0x10) != 16) {
/* 6669 */           this.keysVals_ = new ArrayList<Integer>(this.keysVals_);
/* 6670 */           this.bitField0_ |= 0x10;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getKeysValsList() {
/* 6675 */         return Collections.unmodifiableList(this.keysVals_);
/*      */       }
/*      */       
/*      */       public int getKeysValsCount() {
/* 6678 */         return this.keysVals_.size();
/*      */       }
/*      */       
/*      */       public int getKeysVals(int index) {
/* 6681 */         return ((Integer)this.keysVals_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setKeysVals(int index, int value) {
/* 6685 */         ensureKeysValsIsMutable();
/* 6686 */         this.keysVals_.set(index, Integer.valueOf(value));
/* 6688 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addKeysVals(int value) {
/* 6691 */         ensureKeysValsIsMutable();
/* 6692 */         this.keysVals_.add(Integer.valueOf(value));
/* 6694 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllKeysVals(Iterable<? extends Integer> values) {
/* 6698 */         ensureKeysValsIsMutable();
/* 6699 */         GeneratedMessageLite.Builder.addAll(values, this.keysVals_);
/* 6701 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearKeysVals() {
/* 6704 */         this.keysVals_ = Collections.emptyList();
/* 6705 */         this.bitField0_ &= 0xFFFFFFEF;
/* 6707 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 6714 */     private static final DenseNodes defaultInstance = new DenseNodes(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int ID_FIELD_NUMBER = 1;
/*      */     
/*      */     private List<Long> id_;
/*      */     
/*      */     private int idMemoizedSerializedSize;
/*      */     
/*      */     public static final int DENSEINFO_FIELD_NUMBER = 5;
/*      */     
/*      */     private Osmformat.DenseInfo denseinfo_;
/*      */     
/*      */     public static final int LAT_FIELD_NUMBER = 8;
/*      */     
/*      */     private List<Long> lat_;
/*      */     
/*      */     private int latMemoizedSerializedSize;
/*      */     
/*      */     public static final int LON_FIELD_NUMBER = 9;
/*      */     
/*      */     private List<Long> lon_;
/*      */     
/*      */     private int lonMemoizedSerializedSize;
/*      */     
/*      */     public static final int KEYS_VALS_FIELD_NUMBER = 10;
/*      */     
/*      */     private List<Integer> keysVals_;
/*      */     
/*      */     private int keysValsMemoizedSerializedSize;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 6715 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface WayOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasId();
/*      */     
/*      */     long getId();
/*      */     
/*      */     List<Integer> getKeysList();
/*      */     
/*      */     int getKeysCount();
/*      */     
/*      */     int getKeys(int param1Int);
/*      */     
/*      */     List<Integer> getValsList();
/*      */     
/*      */     int getValsCount();
/*      */     
/*      */     int getVals(int param1Int);
/*      */     
/*      */     boolean hasInfo();
/*      */     
/*      */     Osmformat.Info getInfo();
/*      */     
/*      */     List<Long> getRefsList();
/*      */     
/*      */     int getRefsCount();
/*      */     
/*      */     long getRefs(int param1Int);
/*      */   }
/*      */   
/*      */   public static final class Way extends GeneratedMessageLite implements WayOrBuilder {
/*      */     private Way(Builder builder) {
/* 6752 */       super(builder);
/* 6789 */       this.keysMemoizedSerializedSize = -1;
/* 6804 */       this.valsMemoizedSerializedSize = -1;
/* 6829 */       this.refsMemoizedSerializedSize = -1;
/* 6838 */       this.memoizedIsInitialized = -1;
/* 6883 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private Way(boolean noInit) {
/*      */       this.keysMemoizedSerializedSize = -1;
/*      */       this.valsMemoizedSerializedSize = -1;
/*      */       this.refsMemoizedSerializedSize = -1;
/*      */       this.memoizedIsInitialized = -1;
/* 6883 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static Way getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public Way getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public boolean hasId() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public long getId() {
/*      */       return this.id_;
/*      */     }
/*      */     
/*      */     public List<Integer> getKeysList() {
/*      */       return this.keys_;
/*      */     }
/*      */     
/*      */     public int getKeysCount() {
/*      */       return this.keys_.size();
/*      */     }
/*      */     
/*      */     public int getKeys(int index) {
/*      */       return ((Integer)this.keys_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getValsList() {
/*      */       return this.vals_;
/*      */     }
/*      */     
/*      */     public int getValsCount() {
/*      */       return this.vals_.size();
/*      */     }
/*      */     
/*      */     public int getVals(int index) {
/*      */       return ((Integer)this.vals_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public boolean hasInfo() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public Osmformat.Info getInfo() {
/*      */       return this.info_;
/*      */     }
/*      */     
/*      */     public List<Long> getRefsList() {
/*      */       return this.refs_;
/*      */     }
/*      */     
/*      */     public int getRefsCount() {
/*      */       return this.refs_.size();
/*      */     }
/*      */     
/*      */     public long getRefs(int index) {
/*      */       return ((Long)this.refs_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.id_ = 0L;
/*      */       this.keys_ = Collections.emptyList();
/*      */       this.vals_ = Collections.emptyList();
/*      */       this.info_ = Osmformat.Info.getDefaultInstance();
/*      */       this.refs_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasId()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeInt64(1, this.id_); 
/*      */       if (getKeysList().size() > 0) {
/*      */         output.writeRawVarint32(18);
/*      */         output.writeRawVarint32(this.keysMemoizedSerializedSize);
/*      */       } 
/*      */       int i;
/*      */       for (i = 0; i < this.keys_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.keys_.get(i)).intValue()); 
/*      */       if (getValsList().size() > 0) {
/*      */         output.writeRawVarint32(26);
/*      */         output.writeRawVarint32(this.valsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.vals_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.vals_.get(i)).intValue()); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeMessage(4, (MessageLite)this.info_); 
/*      */       if (getRefsList().size() > 0) {
/*      */         output.writeRawVarint32(66);
/*      */         output.writeRawVarint32(this.refsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.refs_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.refs_.get(i)).longValue()); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 6885 */       int size = this.memoizedSerializedSize;
/* 6886 */       if (size != -1)
/* 6886 */         return size; 
/* 6888 */       size = 0;
/* 6889 */       if ((this.bitField0_ & 0x1) == 1)
/* 6890 */         size += CodedOutputStream.computeInt64Size(1, this.id_); 
/* 6894 */       int dataSize = 0;
/*      */       int i;
/* 6895 */       for (i = 0; i < this.keys_.size(); i++)
/* 6896 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.keys_.get(i)).intValue()); 
/* 6899 */       size += dataSize;
/* 6900 */       if (!getKeysList().isEmpty()) {
/* 6901 */         size++;
/* 6902 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6905 */       this.keysMemoizedSerializedSize = dataSize;
/* 6908 */       dataSize = 0;
/* 6909 */       for (i = 0; i < this.vals_.size(); i++)
/* 6910 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.vals_.get(i)).intValue()); 
/* 6913 */       size += dataSize;
/* 6914 */       if (!getValsList().isEmpty()) {
/* 6915 */         size++;
/* 6916 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6919 */       this.valsMemoizedSerializedSize = dataSize;
/* 6921 */       if ((this.bitField0_ & 0x2) == 2)
/* 6922 */         size += CodedOutputStream.computeMessageSize(4, (MessageLite)this.info_); 
/* 6926 */       dataSize = 0;
/* 6927 */       for (i = 0; i < this.refs_.size(); i++)
/* 6928 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.refs_.get(i)).longValue()); 
/* 6931 */       size += dataSize;
/* 6932 */       if (!getRefsList().isEmpty()) {
/* 6933 */         size++;
/* 6934 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 6937 */       this.refsMemoizedSerializedSize = dataSize;
/* 6939 */       this.memoizedSerializedSize = size;
/* 6940 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 6947 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 6953 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 6959 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 6964 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 6970 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(InputStream input) throws IOException {
/* 6975 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 6981 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseDelimitedFrom(InputStream input) throws IOException {
/* 6986 */       Builder builder = newBuilder();
/* 6987 */       if (builder.mergeDelimitedFrom(input))
/* 6988 */         return builder.buildParsed(); 
/* 6990 */       return null;
/*      */     }
/*      */     
/*      */     public static Way parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 6997 */       Builder builder = newBuilder();
/* 6998 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 6999 */         return builder.buildParsed(); 
/* 7001 */       return null;
/*      */     }
/*      */     
/*      */     public static Way parseFrom(CodedInputStream input) throws IOException {
/* 7007 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Way parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 7013 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 7017 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 7018 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(Way prototype) {
/* 7020 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 7022 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<Way, Builder> implements Osmformat.WayOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private long id_;
/*      */       
/*      */       private List<Integer> keys_;
/*      */       
/*      */       private List<Integer> vals_;
/*      */       
/*      */       private Osmformat.Info info_;
/*      */       
/*      */       private List<Long> refs_;
/*      */       
/*      */       private Builder() {
/* 7261 */         this.keys_ = Collections.emptyList();
/* 7306 */         this.vals_ = Collections.emptyList();
/* 7351 */         this.info_ = Osmformat.Info.getDefaultInstance();
/* 7394 */         this.refs_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.id_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.refs_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.Way getDefaultInstanceForType() {
/*      */         return Osmformat.Way.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.Way build() {
/*      */         Osmformat.Way result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.Way buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.Way result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.Way buildPartial() {
/*      */         Osmformat.Way result = new Osmformat.Way(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.id_ = this.id_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.keys_ = Collections.unmodifiableList(this.keys_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.keys_ = this.keys_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.vals_ = Collections.unmodifiableList(this.vals_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.vals_ = this.vals_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.info_ = this.info_;
/*      */         if ((this.bitField0_ & 0x10) == 16) {
/*      */           this.refs_ = Collections.unmodifiableList(this.refs_);
/*      */           this.bitField0_ &= 0xFFFFFFEF;
/*      */         } 
/*      */         result.refs_ = this.refs_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.Way other) {
/*      */         if (other == Osmformat.Way.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasId())
/*      */           setId(other.getId()); 
/*      */         if (!other.keys_.isEmpty())
/*      */           if (this.keys_.isEmpty()) {
/*      */             this.keys_ = other.keys_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensureKeysIsMutable();
/*      */             this.keys_.addAll(other.keys_);
/*      */           }  
/*      */         if (!other.vals_.isEmpty())
/*      */           if (this.vals_.isEmpty()) {
/*      */             this.vals_ = other.vals_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureValsIsMutable();
/*      */             this.vals_.addAll(other.vals_);
/*      */           }  
/*      */         if (other.hasInfo())
/*      */           mergeInfo(other.getInfo()); 
/*      */         if (!other.refs_.isEmpty())
/*      */           if (this.refs_.isEmpty()) {
/*      */             this.refs_ = other.refs_;
/*      */             this.bitField0_ &= 0xFFFFFFEF;
/*      */           } else {
/*      */             ensureRefsIsMutable();
/*      */             this.refs_.addAll(other.refs_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         if (!hasId())
/*      */           return false; 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int i;
/*      */           Osmformat.Info.Builder subBuilder;
/*      */           int length, limit, tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               this.bitField0_ |= 0x1;
/*      */               this.id_ = input.readInt64();
/*      */               break;
/*      */             case 16:
/*      */               ensureKeysIsMutable();
/*      */               this.keys_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 18:
/*      */               i = input.readRawVarint32();
/*      */               limit = input.pushLimit(i);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addKeys(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 24:
/*      */               ensureValsIsMutable();
/*      */               this.vals_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 26:
/*      */               i = input.readRawVarint32();
/*      */               limit = input.pushLimit(i);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addVals(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 34:
/*      */               subBuilder = Osmformat.Info.newBuilder();
/*      */               if (hasInfo())
/*      */                 subBuilder.mergeFrom(getInfo()); 
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               setInfo(subBuilder.buildPartial());
/*      */               break;
/*      */             case 64:
/*      */               ensureRefsIsMutable();
/*      */               this.refs_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 66:
/*      */               length = input.readRawVarint32();
/*      */               limit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addRefs(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasId() {
/*      */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public long getId() {
/*      */         return this.id_;
/*      */       }
/*      */       
/*      */       public Builder setId(long value) {
/*      */         this.bitField0_ |= 0x1;
/*      */         this.id_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearId() {
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.id_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureKeysIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.keys_ = new ArrayList<Integer>(this.keys_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getKeysList() {
/*      */         return Collections.unmodifiableList(this.keys_);
/*      */       }
/*      */       
/*      */       public int getKeysCount() {
/*      */         return this.keys_.size();
/*      */       }
/*      */       
/*      */       public int getKeys(int index) {
/*      */         return ((Integer)this.keys_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setKeys(int index, int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addKeys(int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllKeys(Iterable<? extends Integer> values) {
/*      */         ensureKeysIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.keys_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearKeys() {
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureValsIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.vals_ = new ArrayList<Integer>(this.vals_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getValsList() {
/*      */         return Collections.unmodifiableList(this.vals_);
/*      */       }
/*      */       
/*      */       public int getValsCount() {
/*      */         return this.vals_.size();
/*      */       }
/*      */       
/*      */       public int getVals(int index) {
/*      */         return ((Integer)this.vals_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setVals(int index, int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addVals(int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllVals(Iterable<? extends Integer> values) {
/*      */         ensureValsIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.vals_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVals() {
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasInfo() {
/*      */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public Osmformat.Info getInfo() {
/*      */         return this.info_;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.info_ = value;
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info.Builder builderForValue) {
/*      */         this.info_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeInfo(Osmformat.Info value) {
/*      */         if ((this.bitField0_ & 0x8) == 8 && this.info_ != Osmformat.Info.getDefaultInstance()) {
/*      */           this.info_ = Osmformat.Info.newBuilder(this.info_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.info_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearInfo() {
/*      */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureRefsIsMutable() {
/* 7396 */         if ((this.bitField0_ & 0x10) != 16) {
/* 7397 */           this.refs_ = new ArrayList<Long>(this.refs_);
/* 7398 */           this.bitField0_ |= 0x10;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getRefsList() {
/* 7403 */         return Collections.unmodifiableList(this.refs_);
/*      */       }
/*      */       
/*      */       public int getRefsCount() {
/* 7406 */         return this.refs_.size();
/*      */       }
/*      */       
/*      */       public long getRefs(int index) {
/* 7409 */         return ((Long)this.refs_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setRefs(int index, long value) {
/* 7413 */         ensureRefsIsMutable();
/* 7414 */         this.refs_.set(index, Long.valueOf(value));
/* 7416 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRefs(long value) {
/* 7419 */         ensureRefsIsMutable();
/* 7420 */         this.refs_.add(Long.valueOf(value));
/* 7422 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllRefs(Iterable<? extends Long> values) {
/* 7426 */         ensureRefsIsMutable();
/* 7427 */         GeneratedMessageLite.Builder.addAll(values, this.refs_);
/* 7429 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearRefs() {
/* 7432 */         this.refs_ = Collections.emptyList();
/* 7433 */         this.bitField0_ &= 0xFFFFFFEF;
/* 7435 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 7442 */     private static final Way defaultInstance = new Way(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int ID_FIELD_NUMBER = 1;
/*      */     
/*      */     private long id_;
/*      */     
/*      */     public static final int KEYS_FIELD_NUMBER = 2;
/*      */     
/*      */     private List<Integer> keys_;
/*      */     
/*      */     private int keysMemoizedSerializedSize;
/*      */     
/*      */     public static final int VALS_FIELD_NUMBER = 3;
/*      */     
/*      */     private List<Integer> vals_;
/*      */     
/*      */     private int valsMemoizedSerializedSize;
/*      */     
/*      */     public static final int INFO_FIELD_NUMBER = 4;
/*      */     
/*      */     private Osmformat.Info info_;
/*      */     
/*      */     public static final int REFS_FIELD_NUMBER = 8;
/*      */     
/*      */     private List<Long> refs_;
/*      */     
/*      */     private int refsMemoizedSerializedSize;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 7443 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface RelationOrBuilder extends MessageLiteOrBuilder {
/*      */     boolean hasId();
/*      */     
/*      */     long getId();
/*      */     
/*      */     List<Integer> getKeysList();
/*      */     
/*      */     int getKeysCount();
/*      */     
/*      */     int getKeys(int param1Int);
/*      */     
/*      */     List<Integer> getValsList();
/*      */     
/*      */     int getValsCount();
/*      */     
/*      */     int getVals(int param1Int);
/*      */     
/*      */     boolean hasInfo();
/*      */     
/*      */     Osmformat.Info getInfo();
/*      */     
/*      */     List<Integer> getRolesSidList();
/*      */     
/*      */     int getRolesSidCount();
/*      */     
/*      */     int getRolesSid(int param1Int);
/*      */     
/*      */     List<Long> getMemidsList();
/*      */     
/*      */     int getMemidsCount();
/*      */     
/*      */     long getMemids(int param1Int);
/*      */     
/*      */     List<Osmformat.Relation.MemberType> getTypesList();
/*      */     
/*      */     int getTypesCount();
/*      */     
/*      */     Osmformat.Relation.MemberType getTypes(int param1Int);
/*      */   }
/*      */   
/*      */   public static final class Relation extends GeneratedMessageLite implements RelationOrBuilder {
/*      */     private Relation(Builder builder) {
/* 7490 */       super(builder);
/* 7571 */       this.keysMemoizedSerializedSize = -1;
/* 7586 */       this.valsMemoizedSerializedSize = -1;
/* 7611 */       this.rolesSidMemoizedSerializedSize = -1;
/* 7626 */       this.memidsMemoizedSerializedSize = -1;
/* 7651 */       this.memoizedIsInitialized = -1;
/* 7710 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     private Relation(boolean noInit) {
/*      */       this.keysMemoizedSerializedSize = -1;
/*      */       this.valsMemoizedSerializedSize = -1;
/*      */       this.rolesSidMemoizedSerializedSize = -1;
/*      */       this.memidsMemoizedSerializedSize = -1;
/*      */       this.memoizedIsInitialized = -1;
/* 7710 */       this.memoizedSerializedSize = -1;
/*      */     }
/*      */     
/*      */     public static Relation getDefaultInstance() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public Relation getDefaultInstanceForType() {
/*      */       return defaultInstance;
/*      */     }
/*      */     
/*      */     public enum MemberType implements Internal.EnumLite {
/*      */       NODE(0, 0),
/*      */       WAY(1, 1),
/*      */       RELATION(2, 2);
/*      */       
/*      */       public static final int NODE_VALUE = 0;
/*      */       
/*      */       public static final int WAY_VALUE = 1;
/*      */       
/*      */       public static final int RELATION_VALUE = 2;
/*      */       
/*      */       private static Internal.EnumLiteMap<MemberType> internalValueMap = new Internal.EnumLiteMap<MemberType>() {
/*      */           public Osmformat.Relation.MemberType findValueByNumber(int number) {
/*      */             return Osmformat.Relation.MemberType.valueOf(number);
/*      */           }
/*      */         };
/*      */       
/*      */       private final int value;
/*      */       
/*      */       public final int getNumber() {
/*      */         return this.value;
/*      */       }
/*      */       
/*      */       public static Internal.EnumLiteMap<MemberType> internalGetValueMap() {
/*      */         return internalValueMap;
/*      */       }
/*      */       
/*      */       static {
/*      */       
/*      */       }
/*      */       
/*      */       MemberType(int index, int value) {
/*      */         this.value = value;
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean hasId() {
/*      */       return ((this.bitField0_ & 0x1) == 1);
/*      */     }
/*      */     
/*      */     public long getId() {
/*      */       return this.id_;
/*      */     }
/*      */     
/*      */     public List<Integer> getKeysList() {
/*      */       return this.keys_;
/*      */     }
/*      */     
/*      */     public int getKeysCount() {
/*      */       return this.keys_.size();
/*      */     }
/*      */     
/*      */     public int getKeys(int index) {
/*      */       return ((Integer)this.keys_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Integer> getValsList() {
/*      */       return this.vals_;
/*      */     }
/*      */     
/*      */     public int getValsCount() {
/*      */       return this.vals_.size();
/*      */     }
/*      */     
/*      */     public int getVals(int index) {
/*      */       return ((Integer)this.vals_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public boolean hasInfo() {
/*      */       return ((this.bitField0_ & 0x2) == 2);
/*      */     }
/*      */     
/*      */     public Osmformat.Info getInfo() {
/*      */       return this.info_;
/*      */     }
/*      */     
/*      */     public List<Integer> getRolesSidList() {
/*      */       return this.rolesSid_;
/*      */     }
/*      */     
/*      */     public int getRolesSidCount() {
/*      */       return this.rolesSid_.size();
/*      */     }
/*      */     
/*      */     public int getRolesSid(int index) {
/*      */       return ((Integer)this.rolesSid_.get(index)).intValue();
/*      */     }
/*      */     
/*      */     public List<Long> getMemidsList() {
/*      */       return this.memids_;
/*      */     }
/*      */     
/*      */     public int getMemidsCount() {
/*      */       return this.memids_.size();
/*      */     }
/*      */     
/*      */     public long getMemids(int index) {
/*      */       return ((Long)this.memids_.get(index)).longValue();
/*      */     }
/*      */     
/*      */     public List<MemberType> getTypesList() {
/*      */       return this.types_;
/*      */     }
/*      */     
/*      */     public int getTypesCount() {
/*      */       return this.types_.size();
/*      */     }
/*      */     
/*      */     public MemberType getTypes(int index) {
/*      */       return this.types_.get(index);
/*      */     }
/*      */     
/*      */     private void initFields() {
/*      */       this.id_ = 0L;
/*      */       this.keys_ = Collections.emptyList();
/*      */       this.vals_ = Collections.emptyList();
/*      */       this.info_ = Osmformat.Info.getDefaultInstance();
/*      */       this.rolesSid_ = Collections.emptyList();
/*      */       this.memids_ = Collections.emptyList();
/*      */       this.types_ = Collections.emptyList();
/*      */     }
/*      */     
/*      */     public final boolean isInitialized() {
/*      */       byte isInitialized = this.memoizedIsInitialized;
/*      */       if (isInitialized != -1)
/*      */         return (isInitialized == 1); 
/*      */       if (!hasId()) {
/*      */         this.memoizedIsInitialized = 0;
/*      */         return false;
/*      */       } 
/*      */       this.memoizedIsInitialized = 1;
/*      */       return true;
/*      */     }
/*      */     
/*      */     public void writeTo(CodedOutputStream output) throws IOException {
/*      */       getSerializedSize();
/*      */       if ((this.bitField0_ & 0x1) == 1)
/*      */         output.writeInt64(1, this.id_); 
/*      */       if (getKeysList().size() > 0) {
/*      */         output.writeRawVarint32(18);
/*      */         output.writeRawVarint32(this.keysMemoizedSerializedSize);
/*      */       } 
/*      */       int i;
/*      */       for (i = 0; i < this.keys_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.keys_.get(i)).intValue()); 
/*      */       if (getValsList().size() > 0) {
/*      */         output.writeRawVarint32(26);
/*      */         output.writeRawVarint32(this.valsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.vals_.size(); i++)
/*      */         output.writeUInt32NoTag(((Integer)this.vals_.get(i)).intValue()); 
/*      */       if ((this.bitField0_ & 0x2) == 2)
/*      */         output.writeMessage(4, (MessageLite)this.info_); 
/*      */       if (getRolesSidList().size() > 0) {
/*      */         output.writeRawVarint32(66);
/*      */         output.writeRawVarint32(this.rolesSidMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.rolesSid_.size(); i++)
/*      */         output.writeInt32NoTag(((Integer)this.rolesSid_.get(i)).intValue()); 
/*      */       if (getMemidsList().size() > 0) {
/*      */         output.writeRawVarint32(74);
/*      */         output.writeRawVarint32(this.memidsMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.memids_.size(); i++)
/*      */         output.writeSInt64NoTag(((Long)this.memids_.get(i)).longValue()); 
/*      */       if (getTypesList().size() > 0) {
/*      */         output.writeRawVarint32(82);
/*      */         output.writeRawVarint32(this.typesMemoizedSerializedSize);
/*      */       } 
/*      */       for (i = 0; i < this.types_.size(); i++)
/*      */         output.writeEnumNoTag(((MemberType)this.types_.get(i)).getNumber()); 
/*      */     }
/*      */     
/*      */     public int getSerializedSize() {
/* 7712 */       int size = this.memoizedSerializedSize;
/* 7713 */       if (size != -1)
/* 7713 */         return size; 
/* 7715 */       size = 0;
/* 7716 */       if ((this.bitField0_ & 0x1) == 1)
/* 7717 */         size += CodedOutputStream.computeInt64Size(1, this.id_); 
/* 7721 */       int dataSize = 0;
/*      */       int i;
/* 7722 */       for (i = 0; i < this.keys_.size(); i++)
/* 7723 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.keys_.get(i)).intValue()); 
/* 7726 */       size += dataSize;
/* 7727 */       if (!getKeysList().isEmpty()) {
/* 7728 */         size++;
/* 7729 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 7732 */       this.keysMemoizedSerializedSize = dataSize;
/* 7735 */       dataSize = 0;
/* 7736 */       for (i = 0; i < this.vals_.size(); i++)
/* 7737 */         dataSize += CodedOutputStream.computeUInt32SizeNoTag(((Integer)this.vals_.get(i)).intValue()); 
/* 7740 */       size += dataSize;
/* 7741 */       if (!getValsList().isEmpty()) {
/* 7742 */         size++;
/* 7743 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 7746 */       this.valsMemoizedSerializedSize = dataSize;
/* 7748 */       if ((this.bitField0_ & 0x2) == 2)
/* 7749 */         size += CodedOutputStream.computeMessageSize(4, (MessageLite)this.info_); 
/* 7753 */       dataSize = 0;
/* 7754 */       for (i = 0; i < this.rolesSid_.size(); i++)
/* 7755 */         dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.rolesSid_.get(i)).intValue()); 
/* 7758 */       size += dataSize;
/* 7759 */       if (!getRolesSidList().isEmpty()) {
/* 7760 */         size++;
/* 7761 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 7764 */       this.rolesSidMemoizedSerializedSize = dataSize;
/* 7767 */       dataSize = 0;
/* 7768 */       for (i = 0; i < this.memids_.size(); i++)
/* 7769 */         dataSize += CodedOutputStream.computeSInt64SizeNoTag(((Long)this.memids_.get(i)).longValue()); 
/* 7772 */       size += dataSize;
/* 7773 */       if (!getMemidsList().isEmpty()) {
/* 7774 */         size++;
/* 7775 */         size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*      */       } 
/* 7778 */       this.memidsMemoizedSerializedSize = dataSize;
/* 7781 */       dataSize = 0;
/* 7782 */       for (i = 0; i < this.types_.size(); i++)
/* 7783 */         dataSize += CodedOutputStream.computeEnumSizeNoTag(((MemberType)this.types_.get(i)).getNumber()); 
/* 7786 */       size += dataSize;
/* 7787 */       if (!getTypesList().isEmpty()) {
/* 7787 */         size++;
/* 7788 */         size += CodedOutputStream.computeRawVarint32Size(dataSize);
/*      */       } 
/* 7790 */       this.typesMemoizedSerializedSize = dataSize;
/* 7792 */       this.memoizedSerializedSize = size;
/* 7793 */       return size;
/*      */     }
/*      */     
/*      */     protected Object writeReplace() throws ObjectStreamException {
/* 7800 */       return super.writeReplace();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 7806 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 7812 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 7817 */       return ((Builder)newBuilder().mergeFrom(data)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 7823 */       return ((Builder)newBuilder().mergeFrom(data, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(InputStream input) throws IOException {
/* 7828 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 7834 */       return ((Builder)newBuilder().mergeFrom(input, extensionRegistry)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseDelimitedFrom(InputStream input) throws IOException {
/* 7839 */       Builder builder = newBuilder();
/* 7840 */       if (builder.mergeDelimitedFrom(input))
/* 7841 */         return builder.buildParsed(); 
/* 7843 */       return null;
/*      */     }
/*      */     
/*      */     public static Relation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 7850 */       Builder builder = newBuilder();
/* 7851 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 7852 */         return builder.buildParsed(); 
/* 7854 */       return null;
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(CodedInputStream input) throws IOException {
/* 7860 */       return ((Builder)newBuilder().mergeFrom(input)).buildParsed();
/*      */     }
/*      */     
/*      */     public static Relation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 7866 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder() {
/* 7870 */       return Builder.create();
/*      */     }
/*      */     
/*      */     public Builder newBuilderForType() {
/* 7871 */       return newBuilder();
/*      */     }
/*      */     
/*      */     public static Builder newBuilder(Relation prototype) {
/* 7873 */       return newBuilder().mergeFrom(prototype);
/*      */     }
/*      */     
/*      */     public Builder toBuilder() {
/* 7875 */       return newBuilder(this);
/*      */     }
/*      */     
/*      */     public static final class Builder extends GeneratedMessageLite.Builder<Relation, Builder> implements Osmformat.RelationOrBuilder {
/*      */       private int bitField0_;
/*      */       
/*      */       private long id_;
/*      */       
/*      */       private List<Integer> keys_;
/*      */       
/*      */       private List<Integer> vals_;
/*      */       
/*      */       private Osmformat.Info info_;
/*      */       
/*      */       private List<Integer> rolesSid_;
/*      */       
/*      */       private List<Long> memids_;
/*      */       
/*      */       private List<Osmformat.Relation.MemberType> types_;
/*      */       
/*      */       private Builder() {
/* 8183 */         this.keys_ = Collections.emptyList();
/* 8228 */         this.vals_ = Collections.emptyList();
/* 8273 */         this.info_ = Osmformat.Info.getDefaultInstance();
/* 8316 */         this.rolesSid_ = Collections.emptyList();
/* 8361 */         this.memids_ = Collections.emptyList();
/* 8406 */         this.types_ = Collections.emptyList();
/*      */         maybeForceBuilderInitialization();
/*      */       }
/*      */       
/*      */       private void maybeForceBuilderInitialization() {}
/*      */       
/*      */       private static Builder create() {
/*      */         return new Builder();
/*      */       }
/*      */       
/*      */       public Builder clear() {
/*      */         super.clear();
/*      */         this.id_ = 0L;
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         this.rolesSid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         this.memids_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         this.types_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFBF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clone() {
/*      */         return create().mergeFrom(buildPartial());
/*      */       }
/*      */       
/*      */       public Osmformat.Relation getDefaultInstanceForType() {
/*      */         return Osmformat.Relation.getDefaultInstance();
/*      */       }
/*      */       
/*      */       public Osmformat.Relation build() {
/*      */         Osmformat.Relation result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       private Osmformat.Relation buildParsed() throws InvalidProtocolBufferException {
/*      */         Osmformat.Relation result = buildPartial();
/*      */         if (!result.isInitialized())
/*      */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Osmformat.Relation buildPartial() {
/*      */         Osmformat.Relation result = new Osmformat.Relation(this);
/*      */         int from_bitField0_ = this.bitField0_;
/*      */         int to_bitField0_ = 0;
/*      */         if ((from_bitField0_ & 0x1) == 1)
/*      */           to_bitField0_ |= 0x1; 
/*      */         result.id_ = this.id_;
/*      */         if ((this.bitField0_ & 0x2) == 2) {
/*      */           this.keys_ = Collections.unmodifiableList(this.keys_);
/*      */           this.bitField0_ &= 0xFFFFFFFD;
/*      */         } 
/*      */         result.keys_ = this.keys_;
/*      */         if ((this.bitField0_ & 0x4) == 4) {
/*      */           this.vals_ = Collections.unmodifiableList(this.vals_);
/*      */           this.bitField0_ &= 0xFFFFFFFB;
/*      */         } 
/*      */         result.vals_ = this.vals_;
/*      */         if ((from_bitField0_ & 0x8) == 8)
/*      */           to_bitField0_ |= 0x2; 
/*      */         result.info_ = this.info_;
/*      */         if ((this.bitField0_ & 0x10) == 16) {
/*      */           this.rolesSid_ = Collections.unmodifiableList(this.rolesSid_);
/*      */           this.bitField0_ &= 0xFFFFFFEF;
/*      */         } 
/*      */         result.rolesSid_ = this.rolesSid_;
/*      */         if ((this.bitField0_ & 0x20) == 32) {
/*      */           this.memids_ = Collections.unmodifiableList(this.memids_);
/*      */           this.bitField0_ &= 0xFFFFFFDF;
/*      */         } 
/*      */         result.memids_ = this.memids_;
/*      */         if ((this.bitField0_ & 0x40) == 64) {
/*      */           this.types_ = Collections.unmodifiableList(this.types_);
/*      */           this.bitField0_ &= 0xFFFFFFBF;
/*      */         } 
/*      */         result.types_ = this.types_;
/*      */         result.bitField0_ = to_bitField0_;
/*      */         return result;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(Osmformat.Relation other) {
/*      */         if (other == Osmformat.Relation.getDefaultInstance())
/*      */           return this; 
/*      */         if (other.hasId())
/*      */           setId(other.getId()); 
/*      */         if (!other.keys_.isEmpty())
/*      */           if (this.keys_.isEmpty()) {
/*      */             this.keys_ = other.keys_;
/*      */             this.bitField0_ &= 0xFFFFFFFD;
/*      */           } else {
/*      */             ensureKeysIsMutable();
/*      */             this.keys_.addAll(other.keys_);
/*      */           }  
/*      */         if (!other.vals_.isEmpty())
/*      */           if (this.vals_.isEmpty()) {
/*      */             this.vals_ = other.vals_;
/*      */             this.bitField0_ &= 0xFFFFFFFB;
/*      */           } else {
/*      */             ensureValsIsMutable();
/*      */             this.vals_.addAll(other.vals_);
/*      */           }  
/*      */         if (other.hasInfo())
/*      */           mergeInfo(other.getInfo()); 
/*      */         if (!other.rolesSid_.isEmpty())
/*      */           if (this.rolesSid_.isEmpty()) {
/*      */             this.rolesSid_ = other.rolesSid_;
/*      */             this.bitField0_ &= 0xFFFFFFEF;
/*      */           } else {
/*      */             ensureRolesSidIsMutable();
/*      */             this.rolesSid_.addAll(other.rolesSid_);
/*      */           }  
/*      */         if (!other.memids_.isEmpty())
/*      */           if (this.memids_.isEmpty()) {
/*      */             this.memids_ = other.memids_;
/*      */             this.bitField0_ &= 0xFFFFFFDF;
/*      */           } else {
/*      */             ensureMemidsIsMutable();
/*      */             this.memids_.addAll(other.memids_);
/*      */           }  
/*      */         if (!other.types_.isEmpty())
/*      */           if (this.types_.isEmpty()) {
/*      */             this.types_ = other.types_;
/*      */             this.bitField0_ &= 0xFFFFFFBF;
/*      */           } else {
/*      */             ensureTypesIsMutable();
/*      */             this.types_.addAll(other.types_);
/*      */           }  
/*      */         return this;
/*      */       }
/*      */       
/*      */       public final boolean isInitialized() {
/*      */         if (!hasId())
/*      */           return false; 
/*      */         return true;
/*      */       }
/*      */       
/*      */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*      */         while (true) {
/*      */           int j;
/*      */           Osmformat.Info.Builder subBuilder;
/*      */           int i, rawValue, length, limit;
/*      */           Osmformat.Relation.MemberType value;
/*      */           int oldLimit, tag = input.readTag();
/*      */           switch (tag) {
/*      */             case 0:
/*      */               return this;
/*      */             case 8:
/*      */               this.bitField0_ |= 0x1;
/*      */               this.id_ = input.readInt64();
/*      */               break;
/*      */             case 16:
/*      */               ensureKeysIsMutable();
/*      */               this.keys_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 18:
/*      */               j = input.readRawVarint32();
/*      */               limit = input.pushLimit(j);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addKeys(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 24:
/*      */               ensureValsIsMutable();
/*      */               this.vals_.add(Integer.valueOf(input.readUInt32()));
/*      */               break;
/*      */             case 26:
/*      */               j = input.readRawVarint32();
/*      */               limit = input.pushLimit(j);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addVals(input.readUInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 34:
/*      */               subBuilder = Osmformat.Info.newBuilder();
/*      */               if (hasInfo())
/*      */                 subBuilder.mergeFrom(getInfo()); 
/*      */               input.readMessage((MessageLite.Builder)subBuilder, extensionRegistry);
/*      */               setInfo(subBuilder.buildPartial());
/*      */               break;
/*      */             case 64:
/*      */               ensureRolesSidIsMutable();
/*      */               this.rolesSid_.add(Integer.valueOf(input.readInt32()));
/*      */               break;
/*      */             case 66:
/*      */               i = input.readRawVarint32();
/*      */               limit = input.pushLimit(i);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addRolesSid(input.readInt32()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 72:
/*      */               ensureMemidsIsMutable();
/*      */               this.memids_.add(Long.valueOf(input.readSInt64()));
/*      */               break;
/*      */             case 74:
/*      */               i = input.readRawVarint32();
/*      */               limit = input.pushLimit(i);
/*      */               while (input.getBytesUntilLimit() > 0)
/*      */                 addMemids(input.readSInt64()); 
/*      */               input.popLimit(limit);
/*      */               break;
/*      */             case 80:
/*      */               rawValue = input.readEnum();
/*      */               value = Osmformat.Relation.MemberType.valueOf(rawValue);
/*      */               if (value != null)
/*      */                 addTypes(value); 
/*      */               break;
/*      */             case 82:
/*      */               length = input.readRawVarint32();
/*      */               oldLimit = input.pushLimit(length);
/*      */               while (input.getBytesUntilLimit() > 0) {
/*      */                 int k = input.readEnum();
/*      */                 Osmformat.Relation.MemberType memberType = Osmformat.Relation.MemberType.valueOf(k);
/*      */                 if (memberType != null)
/*      */                   addTypes(memberType); 
/*      */               } 
/*      */               input.popLimit(oldLimit);
/*      */               break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public boolean hasId() {
/*      */         return ((this.bitField0_ & 0x1) == 1);
/*      */       }
/*      */       
/*      */       public long getId() {
/*      */         return this.id_;
/*      */       }
/*      */       
/*      */       public Builder setId(long value) {
/*      */         this.bitField0_ |= 0x1;
/*      */         this.id_ = value;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearId() {
/*      */         this.bitField0_ &= 0xFFFFFFFE;
/*      */         this.id_ = 0L;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureKeysIsMutable() {
/*      */         if ((this.bitField0_ & 0x2) != 2) {
/*      */           this.keys_ = new ArrayList<Integer>(this.keys_);
/*      */           this.bitField0_ |= 0x2;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getKeysList() {
/*      */         return Collections.unmodifiableList(this.keys_);
/*      */       }
/*      */       
/*      */       public int getKeysCount() {
/*      */         return this.keys_.size();
/*      */       }
/*      */       
/*      */       public int getKeys(int index) {
/*      */         return ((Integer)this.keys_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setKeys(int index, int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addKeys(int value) {
/*      */         ensureKeysIsMutable();
/*      */         this.keys_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllKeys(Iterable<? extends Integer> values) {
/*      */         ensureKeysIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.keys_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearKeys() {
/*      */         this.keys_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFD;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureValsIsMutable() {
/*      */         if ((this.bitField0_ & 0x4) != 4) {
/*      */           this.vals_ = new ArrayList<Integer>(this.vals_);
/*      */           this.bitField0_ |= 0x4;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getValsList() {
/*      */         return Collections.unmodifiableList(this.vals_);
/*      */       }
/*      */       
/*      */       public int getValsCount() {
/*      */         return this.vals_.size();
/*      */       }
/*      */       
/*      */       public int getVals(int index) {
/*      */         return ((Integer)this.vals_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setVals(int index, int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addVals(int value) {
/*      */         ensureValsIsMutable();
/*      */         this.vals_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllVals(Iterable<? extends Integer> values) {
/*      */         ensureValsIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.vals_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearVals() {
/*      */         this.vals_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFFB;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public boolean hasInfo() {
/*      */         return ((this.bitField0_ & 0x8) == 8);
/*      */       }
/*      */       
/*      */       public Osmformat.Info getInfo() {
/*      */         return this.info_;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info value) {
/*      */         if (value == null)
/*      */           throw new NullPointerException(); 
/*      */         this.info_ = value;
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder setInfo(Osmformat.Info.Builder builderForValue) {
/*      */         this.info_ = builderForValue.build();
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder mergeInfo(Osmformat.Info value) {
/*      */         if ((this.bitField0_ & 0x8) == 8 && this.info_ != Osmformat.Info.getDefaultInstance()) {
/*      */           this.info_ = Osmformat.Info.newBuilder(this.info_).mergeFrom(value).buildPartial();
/*      */         } else {
/*      */           this.info_ = value;
/*      */         } 
/*      */         this.bitField0_ |= 0x8;
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearInfo() {
/*      */         this.info_ = Osmformat.Info.getDefaultInstance();
/*      */         this.bitField0_ &= 0xFFFFFFF7;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureRolesSidIsMutable() {
/*      */         if ((this.bitField0_ & 0x10) != 16) {
/*      */           this.rolesSid_ = new ArrayList<Integer>(this.rolesSid_);
/*      */           this.bitField0_ |= 0x10;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Integer> getRolesSidList() {
/*      */         return Collections.unmodifiableList(this.rolesSid_);
/*      */       }
/*      */       
/*      */       public int getRolesSidCount() {
/*      */         return this.rolesSid_.size();
/*      */       }
/*      */       
/*      */       public int getRolesSid(int index) {
/*      */         return ((Integer)this.rolesSid_.get(index)).intValue();
/*      */       }
/*      */       
/*      */       public Builder setRolesSid(int index, int value) {
/*      */         ensureRolesSidIsMutable();
/*      */         this.rolesSid_.set(index, Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addRolesSid(int value) {
/*      */         ensureRolesSidIsMutable();
/*      */         this.rolesSid_.add(Integer.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllRolesSid(Iterable<? extends Integer> values) {
/*      */         ensureRolesSidIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.rolesSid_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearRolesSid() {
/*      */         this.rolesSid_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFEF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureMemidsIsMutable() {
/*      */         if ((this.bitField0_ & 0x20) != 32) {
/*      */           this.memids_ = new ArrayList<Long>(this.memids_);
/*      */           this.bitField0_ |= 0x20;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Long> getMemidsList() {
/*      */         return Collections.unmodifiableList(this.memids_);
/*      */       }
/*      */       
/*      */       public int getMemidsCount() {
/*      */         return this.memids_.size();
/*      */       }
/*      */       
/*      */       public long getMemids(int index) {
/*      */         return ((Long)this.memids_.get(index)).longValue();
/*      */       }
/*      */       
/*      */       public Builder setMemids(int index, long value) {
/*      */         ensureMemidsIsMutable();
/*      */         this.memids_.set(index, Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addMemids(long value) {
/*      */         ensureMemidsIsMutable();
/*      */         this.memids_.add(Long.valueOf(value));
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllMemids(Iterable<? extends Long> values) {
/*      */         ensureMemidsIsMutable();
/*      */         GeneratedMessageLite.Builder.addAll(values, this.memids_);
/*      */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearMemids() {
/*      */         this.memids_ = Collections.emptyList();
/*      */         this.bitField0_ &= 0xFFFFFFDF;
/*      */         return this;
/*      */       }
/*      */       
/*      */       private void ensureTypesIsMutable() {
/* 8409 */         if ((this.bitField0_ & 0x40) != 64) {
/* 8410 */           this.types_ = new ArrayList<Osmformat.Relation.MemberType>(this.types_);
/* 8411 */           this.bitField0_ |= 0x40;
/*      */         } 
/*      */       }
/*      */       
/*      */       public List<Osmformat.Relation.MemberType> getTypesList() {
/* 8415 */         return Collections.unmodifiableList(this.types_);
/*      */       }
/*      */       
/*      */       public int getTypesCount() {
/* 8418 */         return this.types_.size();
/*      */       }
/*      */       
/*      */       public Osmformat.Relation.MemberType getTypes(int index) {
/* 8421 */         return this.types_.get(index);
/*      */       }
/*      */       
/*      */       public Builder setTypes(int index, Osmformat.Relation.MemberType value) {
/* 8425 */         if (value == null)
/* 8426 */           throw new NullPointerException(); 
/* 8428 */         ensureTypesIsMutable();
/* 8429 */         this.types_.set(index, value);
/* 8431 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addTypes(Osmformat.Relation.MemberType value) {
/* 8434 */         if (value == null)
/* 8435 */           throw new NullPointerException(); 
/* 8437 */         ensureTypesIsMutable();
/* 8438 */         this.types_.add(value);
/* 8440 */         return this;
/*      */       }
/*      */       
/*      */       public Builder addAllTypes(Iterable<? extends Osmformat.Relation.MemberType> values) {
/* 8444 */         ensureTypesIsMutable();
/* 8445 */         GeneratedMessageLite.Builder.addAll(values, this.types_);
/* 8447 */         return this;
/*      */       }
/*      */       
/*      */       public Builder clearTypes() {
/* 8450 */         this.types_ = Collections.emptyList();
/* 8451 */         this.bitField0_ &= 0xFFFFFFBF;
/* 8453 */         return this;
/*      */       }
/*      */     }
/*      */     
/* 8460 */     private static final Relation defaultInstance = new Relation(true);
/*      */     
/*      */     private int bitField0_;
/*      */     
/*      */     public static final int ID_FIELD_NUMBER = 1;
/*      */     
/*      */     private long id_;
/*      */     
/*      */     public static final int KEYS_FIELD_NUMBER = 2;
/*      */     
/*      */     private List<Integer> keys_;
/*      */     
/*      */     private int keysMemoizedSerializedSize;
/*      */     
/*      */     public static final int VALS_FIELD_NUMBER = 3;
/*      */     
/*      */     private List<Integer> vals_;
/*      */     
/*      */     private int valsMemoizedSerializedSize;
/*      */     
/*      */     public static final int INFO_FIELD_NUMBER = 4;
/*      */     
/*      */     private Osmformat.Info info_;
/*      */     
/*      */     public static final int ROLES_SID_FIELD_NUMBER = 8;
/*      */     
/*      */     private List<Integer> rolesSid_;
/*      */     
/*      */     private int rolesSidMemoizedSerializedSize;
/*      */     
/*      */     public static final int MEMIDS_FIELD_NUMBER = 9;
/*      */     
/*      */     private List<Long> memids_;
/*      */     
/*      */     private int memidsMemoizedSerializedSize;
/*      */     
/*      */     public static final int TYPES_FIELD_NUMBER = 10;
/*      */     
/*      */     private List<MemberType> types_;
/*      */     
/*      */     private int typesMemoizedSerializedSize;
/*      */     
/*      */     private byte memoizedIsInitialized;
/*      */     
/*      */     private int memoizedSerializedSize;
/*      */     
/*      */     private static final long serialVersionUID = 0L;
/*      */     
/*      */     static {
/* 8461 */       defaultInstance.initFields();
/*      */     }
/*      */   }
/*      */   
/*      */   static {
/*      */   
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\osmbinary\Osmformat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */