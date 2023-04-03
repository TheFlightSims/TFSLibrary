/*       */ package com.google.protobuf;
/*       */ 
/*       */ import java.io.IOException;
/*       */ import java.io.InputStream;
/*       */ import java.io.ObjectStreamException;
/*       */ import java.util.ArrayList;
/*       */ import java.util.Collections;
/*       */ import java.util.List;
/*       */ 
/*       */ public final class DescriptorProtos {
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorSet_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_FileDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_FieldDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_EnumDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_MethodDescriptorProto_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_FileOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FileOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_MessageOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_FieldOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_EnumOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_EnumValueOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_ServiceOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_MethodOptions_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.Descriptor internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
/*       */   
/*       */   private static GeneratedMessage.FieldAccessorTable internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
/*       */   
/*       */   private static Descriptors.FileDescriptor descriptor;
/*       */   
/*       */   public static void registerAllExtensions(ExtensionRegistry registry) {}
/*       */   
/*       */   public static final class FileDescriptorSet extends GeneratedMessage implements FileDescriptorSetOrBuilder {
/*       */     private FileDescriptorSet(Builder builder) {
/*    29 */       super(builder);
/*    76 */       this.memoizedIsInitialized = -1;
/*   100 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private FileDescriptorSet(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*   100 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public FileDescriptorSet getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.FileDescriptorProto> getFileList() {
/*       */       return this.file_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
/*       */       return (List)this.file_;
/*       */     }
/*       */     
/*       */     public int getFileCount() {
/*       */       return this.file_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FileDescriptorProto getFile(int index) {
/*       */       return this.file_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int index) {
/*       */       return this.file_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.file_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getFileCount(); i++) {
/*       */         if (!getFile(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       for (int i = 0; i < this.file_.size(); i++)
/*       */         output.writeMessage(1, this.file_.get(i)); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*   102 */       int size = this.memoizedSerializedSize;
/*   103 */       if (size != -1)
/*   103 */         return size; 
/*   105 */       size = 0;
/*   106 */       for (int i = 0; i < this.file_.size(); i++)
/*   107 */         size += CodedOutputStream.computeMessageSize(1, this.file_.get(i)); 
/*   110 */       size += getUnknownFields().getSerializedSize();
/*   111 */       this.memoizedSerializedSize = size;
/*   112 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*   119 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*   125 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*   131 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*   136 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*   142 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(InputStream input) throws IOException {
/*   147 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*   153 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseDelimitedFrom(InputStream input) throws IOException {
/*   158 */       Builder builder = newBuilder();
/*   159 */       if (builder.mergeDelimitedFrom(input))
/*   160 */         return builder.buildParsed(); 
/*   162 */       return null;
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*   169 */       Builder builder = newBuilder();
/*   170 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*   171 */         return builder.buildParsed(); 
/*   173 */       return null;
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(CodedInputStream input) throws IOException {
/*   179 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorSet parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*   185 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*   189 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*   190 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(FileDescriptorSet prototype) {
/*   192 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*   194 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*   199 */       Builder builder = new Builder(parent);
/*   200 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.FileDescriptorSetOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.FileDescriptorProto> file_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> fileBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*   207 */         return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*   212 */         return DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*   378 */         this.file_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*   378 */         this.file_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getFileFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.fileBuilder_ == null) {
/*       */           this.file_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.fileBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.FileDescriptorSet.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorSet getDefaultInstanceForType() {
/*       */         return DescriptorProtos.FileDescriptorSet.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorSet build() {
/*       */         DescriptorProtos.FileDescriptorSet result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.FileDescriptorSet buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.FileDescriptorSet result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorSet buildPartial() {
/*       */         DescriptorProtos.FileDescriptorSet result = new DescriptorProtos.FileDescriptorSet(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.fileBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.file_ = Collections.unmodifiableList(this.file_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.file_ = this.file_;
/*       */         } else {
/*       */           result.file_ = this.fileBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.FileDescriptorSet)
/*       */           return mergeFrom((DescriptorProtos.FileDescriptorSet)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.FileDescriptorSet other) {
/*       */         if (other == DescriptorProtos.FileDescriptorSet.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.fileBuilder_ == null) {
/*       */           if (!other.file_.isEmpty()) {
/*       */             if (this.file_.isEmpty()) {
/*       */               this.file_ = other.file_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureFileIsMutable();
/*       */               this.file_.addAll(other.file_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.file_.isEmpty()) {
/*       */           if (this.fileBuilder_.isEmpty()) {
/*       */             this.fileBuilder_.dispose();
/*       */             this.fileBuilder_ = null;
/*       */             this.file_ = other.file_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.fileBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getFileFieldBuilder() : null;
/*       */           } else {
/*       */             this.fileBuilder_.addAllMessages(other.file_);
/*       */           } 
/*       */         } 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getFileCount(); i++) {
/*       */           if (!getFile(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.FileDescriptorProto.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               subBuilder = DescriptorProtos.FileDescriptorProto.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addFile(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureFileIsMutable() {
/*   381 */         if ((this.bitField0_ & 0x1) != 1) {
/*   382 */           this.file_ = new ArrayList<DescriptorProtos.FileDescriptorProto>(this.file_);
/*   383 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FileDescriptorProto> getFileList() {
/*   391 */         if (this.fileBuilder_ == null)
/*   392 */           return Collections.unmodifiableList(this.file_); 
/*   394 */         return this.fileBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getFileCount() {
/*   398 */         if (this.fileBuilder_ == null)
/*   399 */           return this.file_.size(); 
/*   401 */         return this.fileBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto getFile(int index) {
/*   405 */         if (this.fileBuilder_ == null)
/*   406 */           return this.file_.get(index); 
/*   408 */         return this.fileBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setFile(int index, DescriptorProtos.FileDescriptorProto value) {
/*   413 */         if (this.fileBuilder_ == null) {
/*   414 */           if (value == null)
/*   415 */             throw new NullPointerException(); 
/*   417 */           ensureFileIsMutable();
/*   418 */           this.file_.set(index, value);
/*   419 */           onChanged();
/*       */         } else {
/*   421 */           this.fileBuilder_.setMessage(index, value);
/*       */         } 
/*   423 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/*   427 */         if (this.fileBuilder_ == null) {
/*   428 */           ensureFileIsMutable();
/*   429 */           this.file_.set(index, builderForValue.build());
/*   430 */           onChanged();
/*       */         } else {
/*   432 */           this.fileBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*   434 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addFile(DescriptorProtos.FileDescriptorProto value) {
/*   437 */         if (this.fileBuilder_ == null) {
/*   438 */           if (value == null)
/*   439 */             throw new NullPointerException(); 
/*   441 */           ensureFileIsMutable();
/*   442 */           this.file_.add(value);
/*   443 */           onChanged();
/*       */         } else {
/*   445 */           this.fileBuilder_.addMessage(value);
/*       */         } 
/*   447 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addFile(int index, DescriptorProtos.FileDescriptorProto value) {
/*   451 */         if (this.fileBuilder_ == null) {
/*   452 */           if (value == null)
/*   453 */             throw new NullPointerException(); 
/*   455 */           ensureFileIsMutable();
/*   456 */           this.file_.add(index, value);
/*   457 */           onChanged();
/*       */         } else {
/*   459 */           this.fileBuilder_.addMessage(index, value);
/*       */         } 
/*   461 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addFile(DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/*   465 */         if (this.fileBuilder_ == null) {
/*   466 */           ensureFileIsMutable();
/*   467 */           this.file_.add(builderForValue.build());
/*   468 */           onChanged();
/*       */         } else {
/*   470 */           this.fileBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*   472 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addFile(int index, DescriptorProtos.FileDescriptorProto.Builder builderForValue) {
/*   476 */         if (this.fileBuilder_ == null) {
/*   477 */           ensureFileIsMutable();
/*   478 */           this.file_.add(index, builderForValue.build());
/*   479 */           onChanged();
/*       */         } else {
/*   481 */           this.fileBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*   483 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllFile(Iterable<? extends DescriptorProtos.FileDescriptorProto> values) {
/*   487 */         if (this.fileBuilder_ == null) {
/*   488 */           ensureFileIsMutable();
/*   489 */           GeneratedMessage.Builder.addAll(values, this.file_);
/*   490 */           onChanged();
/*       */         } else {
/*   492 */           this.fileBuilder_.addAllMessages(values);
/*       */         } 
/*   494 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearFile() {
/*   497 */         if (this.fileBuilder_ == null) {
/*   498 */           this.file_ = Collections.emptyList();
/*   499 */           this.bitField0_ &= 0xFFFFFFFE;
/*   500 */           onChanged();
/*       */         } else {
/*   502 */           this.fileBuilder_.clear();
/*       */         } 
/*   504 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeFile(int index) {
/*   507 */         if (this.fileBuilder_ == null) {
/*   508 */           ensureFileIsMutable();
/*   509 */           this.file_.remove(index);
/*   510 */           onChanged();
/*       */         } else {
/*   512 */           this.fileBuilder_.remove(index);
/*       */         } 
/*   514 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto.Builder getFileBuilder(int index) {
/*   518 */         return getFileFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int index) {
/*   522 */         if (this.fileBuilder_ == null)
/*   523 */           return this.file_.get(index); 
/*   524 */         return this.fileBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getFileOrBuilderList() {
/*   529 */         if (this.fileBuilder_ != null)
/*   530 */           return this.fileBuilder_.getMessageOrBuilderList(); 
/*   532 */         return Collections.unmodifiableList((List)this.file_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto.Builder addFileBuilder() {
/*   536 */         return getFileFieldBuilder().addBuilder(DescriptorProtos.FileDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto.Builder addFileBuilder(int index) {
/*   541 */         return getFileFieldBuilder().addBuilder(index, DescriptorProtos.FileDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FileDescriptorProto.Builder> getFileBuilderList() {
/*   546 */         return getFileFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder> getFileFieldBuilder() {
/*   551 */         if (this.fileBuilder_ == null) {
/*   552 */           this.fileBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.FileDescriptorProto, DescriptorProtos.FileDescriptorProto.Builder, DescriptorProtos.FileDescriptorProtoOrBuilder>(this.file_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/*   558 */           this.file_ = null;
/*       */         } 
/*   560 */         return this.fileBuilder_;
/*       */       }
/*       */     }
/*       */     
/*   567 */     private static final FileDescriptorSet defaultInstance = new FileDescriptorSet(true);
/*       */     
/*       */     public static final int FILE_FIELD_NUMBER = 1;
/*       */     
/*       */     private List<DescriptorProtos.FileDescriptorProto> file_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*   568 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class FileDescriptorProto extends GeneratedMessage implements FileDescriptorProtoOrBuilder {
/*       */     private FileDescriptorProto(Builder builder) {
/*   645 */       super(builder);
/*   868 */       this.memoizedIsInitialized = -1;
/*   940 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private FileDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*   940 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public FileDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasPackage() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public String getPackage() {
/*       */       Object ref = this.package_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.package_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getPackageBytes() {
/*       */       Object ref = this.package_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.package_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public List<String> getDependencyList() {
/*       */       return this.dependency_;
/*       */     }
/*       */     
/*       */     public int getDependencyCount() {
/*       */       return this.dependency_.size();
/*       */     }
/*       */     
/*       */     public String getDependency(int index) {
/*       */       return this.dependency_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.DescriptorProto> getMessageTypeList() {
/*       */       return this.messageType_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
/*       */       return (List)this.messageType_;
/*       */     }
/*       */     
/*       */     public int getMessageTypeCount() {
/*       */       return this.messageType_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.DescriptorProto getMessageType(int index) {
/*       */       return this.messageType_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int index) {
/*       */       return this.messageType_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList() {
/*       */       return this.enumType_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
/*       */       return (List)this.enumType_;
/*       */     }
/*       */     
/*       */     public int getEnumTypeCount() {
/*       */       return this.enumType_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumDescriptorProto getEnumType(int index) {
/*       */       return this.enumType_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
/*       */       return this.enumType_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.ServiceDescriptorProto> getServiceList() {
/*       */       return this.service_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
/*       */       return (List)this.service_;
/*       */     }
/*       */     
/*       */     public int getServiceCount() {
/*       */       return this.service_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.ServiceDescriptorProto getService(int index) {
/*       */       return this.service_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int index) {
/*       */       return this.service_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.FieldDescriptorProto> getExtensionList() {
/*       */       return this.extension_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
/*       */       return (List)this.extension_;
/*       */     }
/*       */     
/*       */     public int getExtensionCount() {
/*       */       return this.extension_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProto getExtension(int index) {
/*       */       return this.extension_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
/*       */       return this.extension_.get(index);
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FileOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public boolean hasSourceCodeInfo() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.SourceCodeInfo getSourceCodeInfo() {
/*       */       return this.sourceCodeInfo_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
/*       */       return this.sourceCodeInfo_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.package_ = "";
/*       */       this.dependency_ = LazyStringArrayList.EMPTY;
/*       */       this.messageType_ = Collections.emptyList();
/*       */       this.enumType_ = Collections.emptyList();
/*       */       this.service_ = Collections.emptyList();
/*       */       this.extension_ = Collections.emptyList();
/*       */       this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
/*       */       this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       int i;
/*       */       for (i = 0; i < getMessageTypeCount(); i++) {
/*       */         if (!getMessageType(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getEnumTypeCount(); i++) {
/*       */         if (!getEnumType(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getServiceCount(); i++) {
/*       */         if (!getService(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getExtensionCount(); i++) {
/*       */         if (!getExtension(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeBytes(2, getPackageBytes()); 
/*       */       int i;
/*       */       for (i = 0; i < this.dependency_.size(); i++)
/*       */         output.writeBytes(3, this.dependency_.getByteString(i)); 
/*       */       for (i = 0; i < this.messageType_.size(); i++)
/*       */         output.writeMessage(4, this.messageType_.get(i)); 
/*       */       for (i = 0; i < this.enumType_.size(); i++)
/*       */         output.writeMessage(5, this.enumType_.get(i)); 
/*       */       for (i = 0; i < this.service_.size(); i++)
/*       */         output.writeMessage(6, this.service_.get(i)); 
/*       */       for (i = 0; i < this.extension_.size(); i++)
/*       */         output.writeMessage(7, this.extension_.get(i)); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeMessage(8, this.options_); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeMessage(9, this.sourceCodeInfo_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*   942 */       int size = this.memoizedSerializedSize;
/*   943 */       if (size != -1)
/*   943 */         return size; 
/*   945 */       size = 0;
/*   946 */       if ((this.bitField0_ & 0x1) == 1)
/*   947 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*   950 */       if ((this.bitField0_ & 0x2) == 2)
/*   951 */         size += CodedOutputStream.computeBytesSize(2, getPackageBytes()); 
/*   955 */       int dataSize = 0;
/*   956 */       for (int j = 0; j < this.dependency_.size(); j++)
/*   957 */         dataSize += CodedOutputStream.computeBytesSizeNoTag(this.dependency_.getByteString(j)); 
/*   960 */       size += dataSize;
/*   961 */       size += 1 * getDependencyList().size();
/*       */       int i;
/*   963 */       for (i = 0; i < this.messageType_.size(); i++)
/*   964 */         size += CodedOutputStream.computeMessageSize(4, this.messageType_.get(i)); 
/*   967 */       for (i = 0; i < this.enumType_.size(); i++)
/*   968 */         size += CodedOutputStream.computeMessageSize(5, this.enumType_.get(i)); 
/*   971 */       for (i = 0; i < this.service_.size(); i++)
/*   972 */         size += CodedOutputStream.computeMessageSize(6, this.service_.get(i)); 
/*   975 */       for (i = 0; i < this.extension_.size(); i++)
/*   976 */         size += CodedOutputStream.computeMessageSize(7, this.extension_.get(i)); 
/*   979 */       if ((this.bitField0_ & 0x4) == 4)
/*   980 */         size += CodedOutputStream.computeMessageSize(8, this.options_); 
/*   983 */       if ((this.bitField0_ & 0x8) == 8)
/*   984 */         size += CodedOutputStream.computeMessageSize(9, this.sourceCodeInfo_); 
/*   987 */       size += getUnknownFields().getSerializedSize();
/*   988 */       this.memoizedSerializedSize = size;
/*   989 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*   996 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  1002 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  1008 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  1013 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  1019 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(InputStream input) throws IOException {
/*  1024 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  1030 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  1035 */       Builder builder = newBuilder();
/*  1036 */       if (builder.mergeDelimitedFrom(input))
/*  1037 */         return builder.buildParsed(); 
/*  1039 */       return null;
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  1046 */       Builder builder = newBuilder();
/*  1047 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  1048 */         return builder.buildParsed(); 
/*  1050 */       return null;
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  1056 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  1062 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  1066 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  1067 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(FileDescriptorProto prototype) {
/*  1069 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  1071 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  1076 */       Builder builder = new Builder(parent);
/*  1077 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.FileDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private Object package_;
/*       */       
/*       */       private LazyStringList dependency_;
/*       */       
/*       */       private List<DescriptorProtos.DescriptorProto> messageType_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, DescriptorProtos.DescriptorProto.Builder, DescriptorProtos.DescriptorProtoOrBuilder> messageTypeBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.EnumDescriptorProto> enumType_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.ServiceDescriptorProto> service_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.ServiceDescriptorProto, DescriptorProtos.ServiceDescriptorProto.Builder, DescriptorProtos.ServiceDescriptorProtoOrBuilder> serviceBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.FieldDescriptorProto> extension_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> extensionBuilder_;
/*       */       
/*       */       private DescriptorProtos.FileOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.FileOptions, DescriptorProtos.FileOptions.Builder, DescriptorProtos.FileOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       private DescriptorProtos.SourceCodeInfo sourceCodeInfo_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.SourceCodeInfo, DescriptorProtos.SourceCodeInfo.Builder, DescriptorProtos.SourceCodeInfoOrBuilder> sourceCodeInfoBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  1084 */         return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  1089 */         return DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  1530 */         this.name_ = "";
/*  1566 */         this.package_ = "";
/*  1602 */         this.dependency_ = LazyStringArrayList.EMPTY;
/*  1658 */         this.messageType_ = Collections.emptyList();
/*  1844 */         this.enumType_ = Collections.emptyList();
/*  2030 */         this.service_ = Collections.emptyList();
/*  2216 */         this.extension_ = Collections.emptyList();
/*  2402 */         this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
/*  2492 */         this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.package_ = "";
/*       */         this.dependency_ = LazyStringArrayList.EMPTY;
/*       */         this.messageType_ = Collections.emptyList();
/*       */         this.enumType_ = Collections.emptyList();
/*       */         this.service_ = Collections.emptyList();
/*       */         this.extension_ = Collections.emptyList();
/*       */         this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
/*  2492 */         this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders) {
/*       */           getMessageTypeFieldBuilder();
/*       */           getEnumTypeFieldBuilder();
/*       */           getServiceFieldBuilder();
/*       */           getExtensionFieldBuilder();
/*       */           getOptionsFieldBuilder();
/*       */           getSourceCodeInfoFieldBuilder();
/*       */         } 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.package_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.dependency_ = LazyStringArrayList.EMPTY;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           this.messageType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFF7;
/*       */         } else {
/*       */           this.messageTypeBuilder_.clear();
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFEF;
/*       */         } else {
/*       */           this.enumTypeBuilder_.clear();
/*       */         } 
/*       */         if (this.serviceBuilder_ == null) {
/*       */           this.service_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFDF;
/*       */         } else {
/*       */           this.serviceBuilder_.clear();
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extension_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFBF;
/*       */         } else {
/*       */           this.extensionBuilder_.clear();
/*       */         } 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFF7F;
/*       */         if (this.sourceCodeInfoBuilder_ == null) {
/*       */           this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*       */         } else {
/*       */           this.sourceCodeInfoBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFEFF;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.FileDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.FileDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto build() {
/*       */         DescriptorProtos.FileDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.FileDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.FileDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileDescriptorProto buildPartial() {
/*       */         DescriptorProtos.FileDescriptorProto result = new DescriptorProtos.FileDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.package_ = this.package_;
/*       */         if ((this.bitField0_ & 0x4) == 4) {
/*       */           this.dependency_ = new UnmodifiableLazyStringList(this.dependency_);
/*       */           this.bitField0_ &= 0xFFFFFFFB;
/*       */         } 
/*       */         result.dependency_ = this.dependency_;
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x8) == 8) {
/*       */             this.messageType_ = Collections.unmodifiableList(this.messageType_);
/*       */             this.bitField0_ &= 0xFFFFFFF7;
/*       */           } 
/*       */           result.messageType_ = this.messageType_;
/*       */         } else {
/*       */           result.messageType_ = this.messageTypeBuilder_.build();
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x10) == 16) {
/*       */             this.enumType_ = Collections.unmodifiableList(this.enumType_);
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */           } 
/*       */           result.enumType_ = this.enumType_;
/*       */         } else {
/*       */           result.enumType_ = this.enumTypeBuilder_.build();
/*       */         } 
/*       */         if (this.serviceBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x20) == 32) {
/*       */             this.service_ = Collections.unmodifiableList(this.service_);
/*       */             this.bitField0_ &= 0xFFFFFFDF;
/*       */           } 
/*       */           result.service_ = this.service_;
/*       */         } else {
/*       */           result.service_ = this.serviceBuilder_.build();
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x40) == 64) {
/*       */             this.extension_ = Collections.unmodifiableList(this.extension_);
/*       */             this.bitField0_ &= 0xFFFFFFBF;
/*       */           } 
/*       */           result.extension_ = this.extension_;
/*       */         } else {
/*       */           result.extension_ = this.extensionBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x80) == 128)
/*       */           to_bitField0_ |= 0x4; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x100) == 256)
/*       */           to_bitField0_ |= 0x8; 
/*       */         if (this.sourceCodeInfoBuilder_ == null) {
/*       */           result.sourceCodeInfo_ = this.sourceCodeInfo_;
/*       */         } else {
/*       */           result.sourceCodeInfo_ = this.sourceCodeInfoBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.FileDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.FileDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.FileDescriptorProto other) {
/*       */         if (other == DescriptorProtos.FileDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (other.hasPackage())
/*       */           setPackage(other.getPackage()); 
/*       */         if (!other.dependency_.isEmpty()) {
/*       */           if (this.dependency_.isEmpty()) {
/*       */             this.dependency_ = other.dependency_;
/*       */             this.bitField0_ &= 0xFFFFFFFB;
/*       */           } else {
/*       */             ensureDependencyIsMutable();
/*       */             this.dependency_.addAll(other.dependency_);
/*       */           } 
/*       */           onChanged();
/*       */         } 
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           if (!other.messageType_.isEmpty()) {
/*       */             if (this.messageType_.isEmpty()) {
/*       */               this.messageType_ = other.messageType_;
/*       */               this.bitField0_ &= 0xFFFFFFF7;
/*       */             } else {
/*       */               ensureMessageTypeIsMutable();
/*       */               this.messageType_.addAll(other.messageType_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.messageType_.isEmpty()) {
/*       */           if (this.messageTypeBuilder_.isEmpty()) {
/*       */             this.messageTypeBuilder_.dispose();
/*       */             this.messageTypeBuilder_ = null;
/*       */             this.messageType_ = other.messageType_;
/*       */             this.bitField0_ &= 0xFFFFFFF7;
/*       */             this.messageTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getMessageTypeFieldBuilder() : null;
/*       */           } else {
/*       */             this.messageTypeBuilder_.addAllMessages(other.messageType_);
/*       */           } 
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (!other.enumType_.isEmpty()) {
/*       */             if (this.enumType_.isEmpty()) {
/*       */               this.enumType_ = other.enumType_;
/*       */               this.bitField0_ &= 0xFFFFFFEF;
/*       */             } else {
/*       */               ensureEnumTypeIsMutable();
/*       */               this.enumType_.addAll(other.enumType_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.enumType_.isEmpty()) {
/*       */           if (this.enumTypeBuilder_.isEmpty()) {
/*       */             this.enumTypeBuilder_.dispose();
/*       */             this.enumTypeBuilder_ = null;
/*       */             this.enumType_ = other.enumType_;
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */             this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
/*       */           } else {
/*       */             this.enumTypeBuilder_.addAllMessages(other.enumType_);
/*       */           } 
/*       */         } 
/*       */         if (this.serviceBuilder_ == null) {
/*       */           if (!other.service_.isEmpty()) {
/*       */             if (this.service_.isEmpty()) {
/*       */               this.service_ = other.service_;
/*       */               this.bitField0_ &= 0xFFFFFFDF;
/*       */             } else {
/*       */               ensureServiceIsMutable();
/*       */               this.service_.addAll(other.service_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.service_.isEmpty()) {
/*       */           if (this.serviceBuilder_.isEmpty()) {
/*       */             this.serviceBuilder_.dispose();
/*       */             this.serviceBuilder_ = null;
/*       */             this.service_ = other.service_;
/*       */             this.bitField0_ &= 0xFFFFFFDF;
/*       */             this.serviceBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getServiceFieldBuilder() : null;
/*       */           } else {
/*       */             this.serviceBuilder_.addAllMessages(other.service_);
/*       */           } 
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (!other.extension_.isEmpty()) {
/*       */             if (this.extension_.isEmpty()) {
/*       */               this.extension_ = other.extension_;
/*       */               this.bitField0_ &= 0xFFFFFFBF;
/*       */             } else {
/*       */               ensureExtensionIsMutable();
/*       */               this.extension_.addAll(other.extension_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.extension_.isEmpty()) {
/*       */           if (this.extensionBuilder_.isEmpty()) {
/*       */             this.extensionBuilder_.dispose();
/*       */             this.extensionBuilder_ = null;
/*       */             this.extension_ = other.extension_;
/*       */             this.bitField0_ &= 0xFFFFFFBF;
/*       */             this.extensionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
/*       */           } else {
/*       */             this.extensionBuilder_.addAllMessages(other.extension_);
/*       */           } 
/*       */         } 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         if (other.hasSourceCodeInfo())
/*       */           mergeSourceCodeInfo(other.getSourceCodeInfo()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         int i;
/*       */         for (i = 0; i < getMessageTypeCount(); i++) {
/*       */           if (!getMessageType(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getEnumTypeCount(); i++) {
/*       */           if (!getEnumType(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getServiceCount(); i++) {
/*       */           if (!getService(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getExtensionCount(); i++) {
/*       */           if (!getExtension(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.DescriptorProto.Builder builder4;
/*       */           DescriptorProtos.EnumDescriptorProto.Builder builder3;
/*       */           DescriptorProtos.ServiceDescriptorProto.Builder builder2;
/*       */           DescriptorProtos.FieldDescriptorProto.Builder builder1;
/*       */           DescriptorProtos.FileOptions.Builder builder;
/*       */           DescriptorProtos.SourceCodeInfo.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.package_ = input.readBytes();
/*       */               break;
/*       */             case 26:
/*       */               ensureDependencyIsMutable();
/*       */               this.dependency_.add(input.readBytes());
/*       */               break;
/*       */             case 34:
/*       */               builder4 = DescriptorProtos.DescriptorProto.newBuilder();
/*       */               input.readMessage(builder4, extensionRegistry);
/*       */               addMessageType(builder4.buildPartial());
/*       */               break;
/*       */             case 42:
/*       */               builder3 = DescriptorProtos.EnumDescriptorProto.newBuilder();
/*       */               input.readMessage(builder3, extensionRegistry);
/*       */               addEnumType(builder3.buildPartial());
/*       */               break;
/*       */             case 50:
/*       */               builder2 = DescriptorProtos.ServiceDescriptorProto.newBuilder();
/*       */               input.readMessage(builder2, extensionRegistry);
/*       */               addService(builder2.buildPartial());
/*       */               break;
/*       */             case 58:
/*       */               builder1 = DescriptorProtos.FieldDescriptorProto.newBuilder();
/*       */               input.readMessage(builder1, extensionRegistry);
/*       */               addExtension(builder1.buildPartial());
/*       */               break;
/*       */             case 66:
/*       */               builder = DescriptorProtos.FileOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 builder.mergeFrom(getOptions()); 
/*       */               input.readMessage(builder, extensionRegistry);
/*       */               setOptions(builder.buildPartial());
/*       */               break;
/*       */             case 74:
/*       */               subBuilder = DescriptorProtos.SourceCodeInfo.newBuilder();
/*       */               if (hasSourceCodeInfo())
/*       */                 subBuilder.mergeFrom(getSourceCodeInfo()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setSourceCodeInfo(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.FileDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasPackage() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public String getPackage() {
/*       */         Object ref = this.package_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.package_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setPackage(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x2;
/*       */         this.package_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearPackage() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.package_ = DescriptorProtos.FileDescriptorProto.getDefaultInstance().getPackage();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setPackage(ByteString value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.package_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureDependencyIsMutable() {
/*       */         if ((this.bitField0_ & 0x4) != 4) {
/*       */           this.dependency_ = new LazyStringArrayList(this.dependency_);
/*       */           this.bitField0_ |= 0x4;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<String> getDependencyList() {
/*       */         return Collections.unmodifiableList(this.dependency_);
/*       */       }
/*       */       
/*       */       public int getDependencyCount() {
/*       */         return this.dependency_.size();
/*       */       }
/*       */       
/*       */       public String getDependency(int index) {
/*       */         return this.dependency_.get(index);
/*       */       }
/*       */       
/*       */       public Builder setDependency(int index, String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         ensureDependencyIsMutable();
/*       */         this.dependency_.set(index, value);
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addDependency(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         ensureDependencyIsMutable();
/*       */         this.dependency_.add(value);
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllDependency(Iterable<String> values) {
/*       */         ensureDependencyIsMutable();
/*       */         GeneratedMessage.Builder.addAll(values, this.dependency_);
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearDependency() {
/*       */         this.dependency_ = LazyStringArrayList.EMPTY;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void addDependency(ByteString value) {
/*       */         ensureDependencyIsMutable();
/*       */         this.dependency_.add(value);
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureMessageTypeIsMutable() {
/*       */         if ((this.bitField0_ & 0x8) != 8) {
/*       */           this.messageType_ = new ArrayList<DescriptorProtos.DescriptorProto>(this.messageType_);
/*       */           this.bitField0_ |= 0x8;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.DescriptorProto> getMessageTypeList() {
/*       */         if (this.messageTypeBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.messageType_); 
/*       */         return this.messageTypeBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getMessageTypeCount() {
/*       */         if (this.messageTypeBuilder_ == null)
/*       */           return this.messageType_.size(); 
/*       */         return this.messageTypeBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto getMessageType(int index) {
/*       */         if (this.messageTypeBuilder_ == null)
/*       */           return this.messageType_.get(index); 
/*       */         return this.messageTypeBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setMessageType(int index, DescriptorProtos.DescriptorProto value) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setMessageType(int index, DescriptorProtos.DescriptorProto.Builder builderForValue) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMessageType(DescriptorProtos.DescriptorProto value) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMessageType(int index, DescriptorProtos.DescriptorProto value) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMessageType(DescriptorProtos.DescriptorProto.Builder builderForValue) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMessageType(int index, DescriptorProtos.DescriptorProto.Builder builderForValue) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllMessageType(Iterable<? extends DescriptorProtos.DescriptorProto> values) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           ensureMessageTypeIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.messageType_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearMessageType() {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           this.messageType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFF7;
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeMessageType(int index) {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           ensureMessageTypeIsMutable();
/*       */           this.messageType_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.messageTypeBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.Builder getMessageTypeBuilder(int index) {
/*       */         return getMessageTypeFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int index) {
/*       */         if (this.messageTypeBuilder_ == null)
/*       */           return this.messageType_.get(index); 
/*       */         return this.messageTypeBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.DescriptorProtoOrBuilder> getMessageTypeOrBuilderList() {
/*       */         if (this.messageTypeBuilder_ != null)
/*       */           return this.messageTypeBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.messageType_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.Builder addMessageTypeBuilder() {
/*       */         return getMessageTypeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.Builder addMessageTypeBuilder(int index) {
/*       */         return getMessageTypeFieldBuilder().addBuilder(index, DescriptorProtos.DescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.DescriptorProto.Builder> getMessageTypeBuilderList() {
/*       */         return getMessageTypeFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, DescriptorProtos.DescriptorProto.Builder, DescriptorProtos.DescriptorProtoOrBuilder> getMessageTypeFieldBuilder() {
/*       */         if (this.messageTypeBuilder_ == null) {
/*       */           this.messageTypeBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, DescriptorProtos.DescriptorProto.Builder, DescriptorProtos.DescriptorProtoOrBuilder>(this.messageType_, ((this.bitField0_ & 0x8) == 8), getParentForChildren(), isClean());
/*       */           this.messageType_ = null;
/*       */         } 
/*       */         return this.messageTypeBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureEnumTypeIsMutable() {
/*       */         if ((this.bitField0_ & 0x10) != 16) {
/*       */           this.enumType_ = new ArrayList<DescriptorProtos.EnumDescriptorProto>(this.enumType_);
/*       */           this.bitField0_ |= 0x10;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList() {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.enumType_); 
/*       */         return this.enumTypeBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getEnumTypeCount() {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.size(); 
/*       */         return this.enumTypeBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto getEnumType(int index) {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.get(index); 
/*       */         return this.enumTypeBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setEnumType(int index, DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setEnumType(int index, DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(int index, DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(int index, DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllEnumType(Iterable<? extends DescriptorProtos.EnumDescriptorProto> values) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.enumType_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearEnumType() {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFEF;
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeEnumType(int index) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder getEnumTypeBuilder(int index) {
/*       */         return getEnumTypeFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.get(index); 
/*       */         return this.enumTypeBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
/*       */         if (this.enumTypeBuilder_ != null)
/*       */           return this.enumTypeBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.enumType_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder() {
/*       */         return getEnumTypeFieldBuilder().addBuilder(DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder(int index) {
/*       */         return getEnumTypeFieldBuilder().addBuilder(index, DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumDescriptorProto.Builder> getEnumTypeBuilderList() {
/*       */         return getEnumTypeFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumTypeBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder>(this.enumType_, ((this.bitField0_ & 0x10) == 16), getParentForChildren(), isClean());
/*       */           this.enumType_ = null;
/*       */         } 
/*       */         return this.enumTypeBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureServiceIsMutable() {
/*       */         if ((this.bitField0_ & 0x20) != 32) {
/*       */           this.service_ = new ArrayList<DescriptorProtos.ServiceDescriptorProto>(this.service_);
/*       */           this.bitField0_ |= 0x20;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.ServiceDescriptorProto> getServiceList() {
/*       */         if (this.serviceBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.service_); 
/*       */         return this.serviceBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getServiceCount() {
/*       */         if (this.serviceBuilder_ == null)
/*       */           return this.service_.size(); 
/*       */         return this.serviceBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto getService(int index) {
/*       */         if (this.serviceBuilder_ == null)
/*       */           return this.service_.get(index); 
/*       */         return this.serviceBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setService(int index, DescriptorProtos.ServiceDescriptorProto value) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureServiceIsMutable();
/*       */           this.service_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setService(int index, DescriptorProtos.ServiceDescriptorProto.Builder builderForValue) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           ensureServiceIsMutable();
/*       */           this.service_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addService(DescriptorProtos.ServiceDescriptorProto value) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureServiceIsMutable();
/*       */           this.service_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addService(int index, DescriptorProtos.ServiceDescriptorProto value) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureServiceIsMutable();
/*       */           this.service_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addService(DescriptorProtos.ServiceDescriptorProto.Builder builderForValue) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           ensureServiceIsMutable();
/*       */           this.service_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addService(int index, DescriptorProtos.ServiceDescriptorProto.Builder builderForValue) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           ensureServiceIsMutable();
/*       */           this.service_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllService(Iterable<? extends DescriptorProtos.ServiceDescriptorProto> values) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           ensureServiceIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.service_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearService() {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           this.service_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFDF;
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeService(int index) {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           ensureServiceIsMutable();
/*       */           this.service_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.serviceBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto.Builder getServiceBuilder(int index) {
/*       */         return getServiceFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int index) {
/*       */         if (this.serviceBuilder_ == null)
/*       */           return this.service_.get(index); 
/*       */         return this.serviceBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList() {
/*       */         if (this.serviceBuilder_ != null)
/*       */           return this.serviceBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.service_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto.Builder addServiceBuilder() {
/*       */         return getServiceFieldBuilder().addBuilder(DescriptorProtos.ServiceDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto.Builder addServiceBuilder(int index) {
/*       */         return getServiceFieldBuilder().addBuilder(index, DescriptorProtos.ServiceDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.ServiceDescriptorProto.Builder> getServiceBuilderList() {
/*       */         return getServiceFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.ServiceDescriptorProto, DescriptorProtos.ServiceDescriptorProto.Builder, DescriptorProtos.ServiceDescriptorProtoOrBuilder> getServiceFieldBuilder() {
/*       */         if (this.serviceBuilder_ == null) {
/*       */           this.serviceBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.ServiceDescriptorProto, DescriptorProtos.ServiceDescriptorProto.Builder, DescriptorProtos.ServiceDescriptorProtoOrBuilder>(this.service_, ((this.bitField0_ & 0x20) == 32), getParentForChildren(), isClean());
/*       */           this.service_ = null;
/*       */         } 
/*       */         return this.serviceBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureExtensionIsMutable() {
/*       */         if ((this.bitField0_ & 0x40) != 64) {
/*       */           this.extension_ = new ArrayList<DescriptorProtos.FieldDescriptorProto>(this.extension_);
/*       */           this.bitField0_ |= 0x40;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto> getExtensionList() {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.extension_); 
/*       */         return this.extensionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getExtensionCount() {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.size(); 
/*       */         return this.extensionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto getExtension(int index) {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.get(index); 
/*       */         return this.extensionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setExtension(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setExtension(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllExtension(Iterable<? extends DescriptorProtos.FieldDescriptorProto> values) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.extension_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearExtension() {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extension_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFBF;
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeExtension(int index) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder getExtensionBuilder(int index) {
/*       */         return getExtensionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.get(index); 
/*       */         return this.extensionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
/*       */         if (this.extensionBuilder_ != null)
/*       */           return this.extensionBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.extension_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder() {
/*       */         return getExtensionFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder(int index) {
/*       */         return getExtensionFieldBuilder().addBuilder(index, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto.Builder> getExtensionBuilderList() {
/*       */         return getExtensionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extensionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder>(this.extension_, ((this.bitField0_ & 0x40) == 64), getParentForChildren(), isClean());
/*       */           this.extension_ = null;
/*       */         } 
/*       */         return this.extensionBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*       */         return ((this.bitField0_ & 0x80) == 128);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions getOptions() {
/*       */         if (this.optionsBuilder_ == null)
/*       */           return this.options_; 
/*       */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.FileOptions value) {
/*       */         if (this.optionsBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           this.options_ = value;
/*       */           onChanged();
/*       */         } else {
/*       */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*       */         this.bitField0_ |= 0x80;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.FileOptions.Builder builderForValue) {
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = builderForValue.build();
/*       */           onChanged();
/*       */         } else {
/*       */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*       */         this.bitField0_ |= 0x80;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.FileOptions value) {
/*       */         if (this.optionsBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x80) == 128 && this.options_ != DescriptorProtos.FileOptions.getDefaultInstance()) {
/*       */             this.options_ = DescriptorProtos.FileOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*       */             this.options_ = value;
/*       */           } 
/*       */           onChanged();
/*       */         } else {
/*       */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*       */         this.bitField0_ |= 0x80;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.FileOptions.getDefaultInstance();
/*       */           onChanged();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFF7F;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions.Builder getOptionsBuilder() {
/*       */         this.bitField0_ |= 0x80;
/*       */         onChanged();
/*       */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder() {
/*       */         if (this.optionsBuilder_ != null)
/*       */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*       */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.FileOptions, DescriptorProtos.FileOptions.Builder, DescriptorProtos.FileOptionsOrBuilder> getOptionsFieldBuilder() {
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.FileOptions, DescriptorProtos.FileOptions.Builder, DescriptorProtos.FileOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*       */           this.options_ = null;
/*       */         } 
/*       */         return this.optionsBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasSourceCodeInfo() {
/*  2496 */         return ((this.bitField0_ & 0x100) == 256);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo getSourceCodeInfo() {
/*  2499 */         if (this.sourceCodeInfoBuilder_ == null)
/*  2500 */           return this.sourceCodeInfo_; 
/*  2502 */         return this.sourceCodeInfoBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setSourceCodeInfo(DescriptorProtos.SourceCodeInfo value) {
/*  2506 */         if (this.sourceCodeInfoBuilder_ == null) {
/*  2507 */           if (value == null)
/*  2508 */             throw new NullPointerException(); 
/*  2510 */           this.sourceCodeInfo_ = value;
/*  2511 */           onChanged();
/*       */         } else {
/*  2513 */           this.sourceCodeInfoBuilder_.setMessage(value);
/*       */         } 
/*  2515 */         this.bitField0_ |= 0x100;
/*  2516 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setSourceCodeInfo(DescriptorProtos.SourceCodeInfo.Builder builderForValue) {
/*  2520 */         if (this.sourceCodeInfoBuilder_ == null) {
/*  2521 */           this.sourceCodeInfo_ = builderForValue.build();
/*  2522 */           onChanged();
/*       */         } else {
/*  2524 */           this.sourceCodeInfoBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  2526 */         this.bitField0_ |= 0x100;
/*  2527 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeSourceCodeInfo(DescriptorProtos.SourceCodeInfo value) {
/*  2530 */         if (this.sourceCodeInfoBuilder_ == null) {
/*  2531 */           if ((this.bitField0_ & 0x100) == 256 && this.sourceCodeInfo_ != DescriptorProtos.SourceCodeInfo.getDefaultInstance()) {
/*  2533 */             this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.newBuilder(this.sourceCodeInfo_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  2536 */             this.sourceCodeInfo_ = value;
/*       */           } 
/*  2538 */           onChanged();
/*       */         } else {
/*  2540 */           this.sourceCodeInfoBuilder_.mergeFrom(value);
/*       */         } 
/*  2542 */         this.bitField0_ |= 0x100;
/*  2543 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearSourceCodeInfo() {
/*  2546 */         if (this.sourceCodeInfoBuilder_ == null) {
/*  2547 */           this.sourceCodeInfo_ = DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*  2548 */           onChanged();
/*       */         } else {
/*  2550 */           this.sourceCodeInfoBuilder_.clear();
/*       */         } 
/*  2552 */         this.bitField0_ &= 0xFFFFFEFF;
/*  2553 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.Builder getSourceCodeInfoBuilder() {
/*  2556 */         this.bitField0_ |= 0x100;
/*  2557 */         onChanged();
/*  2558 */         return getSourceCodeInfoFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder() {
/*  2561 */         if (this.sourceCodeInfoBuilder_ != null)
/*  2562 */           return this.sourceCodeInfoBuilder_.getMessageOrBuilder(); 
/*  2564 */         return this.sourceCodeInfo_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.SourceCodeInfo, DescriptorProtos.SourceCodeInfo.Builder, DescriptorProtos.SourceCodeInfoOrBuilder> getSourceCodeInfoFieldBuilder() {
/*  2570 */         if (this.sourceCodeInfoBuilder_ == null) {
/*  2571 */           this.sourceCodeInfoBuilder_ = new SingleFieldBuilder<DescriptorProtos.SourceCodeInfo, DescriptorProtos.SourceCodeInfo.Builder, DescriptorProtos.SourceCodeInfoOrBuilder>(this.sourceCodeInfo_, getParentForChildren(), isClean());
/*  2576 */           this.sourceCodeInfo_ = null;
/*       */         } 
/*  2578 */         return this.sourceCodeInfoBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  2585 */     private static final FileDescriptorProto defaultInstance = new FileDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int PACKAGE_FIELD_NUMBER = 2;
/*       */     
/*       */     private Object package_;
/*       */     
/*       */     public static final int DEPENDENCY_FIELD_NUMBER = 3;
/*       */     
/*       */     private LazyStringList dependency_;
/*       */     
/*       */     public static final int MESSAGE_TYPE_FIELD_NUMBER = 4;
/*       */     
/*       */     private List<DescriptorProtos.DescriptorProto> messageType_;
/*       */     
/*       */     public static final int ENUM_TYPE_FIELD_NUMBER = 5;
/*       */     
/*       */     private List<DescriptorProtos.EnumDescriptorProto> enumType_;
/*       */     
/*       */     public static final int SERVICE_FIELD_NUMBER = 6;
/*       */     
/*       */     private List<DescriptorProtos.ServiceDescriptorProto> service_;
/*       */     
/*       */     public static final int EXTENSION_FIELD_NUMBER = 7;
/*       */     
/*       */     private List<DescriptorProtos.FieldDescriptorProto> extension_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 8;
/*       */     
/*       */     private DescriptorProtos.FileOptions options_;
/*       */     
/*       */     public static final int SOURCE_CODE_INFO_FIELD_NUMBER = 9;
/*       */     
/*       */     private DescriptorProtos.SourceCodeInfo sourceCodeInfo_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  2586 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class DescriptorProto extends GeneratedMessage implements DescriptorProtoOrBuilder {
/*       */     private DescriptorProto(Builder builder) {
/*  2659 */       super(builder);
/*  3237 */       this.memoizedIsInitialized = -1;
/*  3303 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private DescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  3303 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static DescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public DescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public static interface ExtensionRangeOrBuilder extends MessageOrBuilder {
/*       */       boolean hasStart();
/*       */       
/*       */       int getStart();
/*       */       
/*       */       boolean hasEnd();
/*       */       
/*       */       int getEnd();
/*       */     }
/*       */     
/*       */     public static final class ExtensionRange extends GeneratedMessage implements ExtensionRangeOrBuilder {
/*       */       private ExtensionRange(Builder builder) {
/*       */         super(builder);
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       private ExtensionRange(boolean noInit) {
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       public static ExtensionRange getDefaultInstance() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public ExtensionRange getDefaultInstanceForType() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       public boolean hasStart() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public int getStart() {
/*       */         return this.start_;
/*       */       }
/*       */       
/*       */       public boolean hasEnd() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public int getEnd() {
/*       */         return this.end_;
/*       */       }
/*       */       
/*       */       private void initFields() {
/*       */         this.start_ = 0;
/*       */         this.end_ = 0;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         byte isInitialized = this.memoizedIsInitialized;
/*       */         if (isInitialized != -1)
/*       */           return (isInitialized == 1); 
/*       */         this.memoizedIsInitialized = 1;
/*       */         return true;
/*       */       }
/*       */       
/*       */       public void writeTo(CodedOutputStream output) throws IOException {
/*       */         getSerializedSize();
/*       */         if ((this.bitField0_ & 0x1) == 1)
/*       */           output.writeInt32(1, this.start_); 
/*       */         if ((this.bitField0_ & 0x2) == 2)
/*       */           output.writeInt32(2, this.end_); 
/*       */         getUnknownFields().writeTo(output);
/*       */       }
/*       */       
/*       */       public int getSerializedSize() {
/*       */         int size = this.memoizedSerializedSize;
/*       */         if (size != -1)
/*       */           return size; 
/*       */         size = 0;
/*       */         if ((this.bitField0_ & 0x1) == 1)
/*       */           size += CodedOutputStream.computeInt32Size(1, this.start_); 
/*       */         if ((this.bitField0_ & 0x2) == 2)
/*       */           size += CodedOutputStream.computeInt32Size(2, this.end_); 
/*       */         size += getUnknownFields().getSerializedSize();
/*       */         this.memoizedSerializedSize = size;
/*       */         return size;
/*       */       }
/*       */       
/*       */       protected Object writeReplace() throws ObjectStreamException {
/*       */         return super.writeReplace();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(InputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseDelimitedFrom(InputStream input) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(CodedInputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static ExtensionRange parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder() {
/*       */         return Builder.create();
/*       */       }
/*       */       
/*       */       public Builder newBuilderForType() {
/*       */         return newBuilder();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder(ExtensionRange prototype) {
/*       */         return newBuilder().mergeFrom(prototype);
/*       */       }
/*       */       
/*       */       public Builder toBuilder() {
/*       */         return newBuilder(this);
/*       */       }
/*       */       
/*       */       protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*       */         Builder builder = new Builder(parent);
/*       */         return builder;
/*       */       }
/*       */       
/*       */       public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder {
/*       */         private int bitField0_;
/*       */         
/*       */         private int start_;
/*       */         
/*       */         private int end_;
/*       */         
/*       */         public static final Descriptors.Descriptor getDescriptor() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor;
/*       */         }
/*       */         
/*       */         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable;
/*       */         }
/*       */         
/*       */         private Builder() {
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private Builder(GeneratedMessage.BuilderParent parent) {
/*       */           super(parent);
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private void maybeForceBuilderInitialization() {
/*       */           if (GeneratedMessage.alwaysUseFieldBuilders);
/*       */         }
/*       */         
/*       */         private static Builder create() {
/*       */           return new Builder();
/*       */         }
/*       */         
/*       */         public Builder clear() {
/*       */           super.clear();
/*       */           this.start_ = 0;
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           this.end_ = 0;
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clone() {
/*       */           return create().mergeFrom(buildPartial());
/*       */         }
/*       */         
/*       */         public Descriptors.Descriptor getDescriptorForType() {
/*       */           return DescriptorProtos.DescriptorProto.ExtensionRange.getDescriptor();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.DescriptorProto.ExtensionRange getDefaultInstanceForType() {
/*       */           return DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.DescriptorProto.ExtensionRange build() {
/*       */           DescriptorProtos.DescriptorProto.ExtensionRange result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         private DescriptorProtos.DescriptorProto.ExtensionRange buildParsed() throws InvalidProtocolBufferException {
/*       */           DescriptorProtos.DescriptorProto.ExtensionRange result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         public DescriptorProtos.DescriptorProto.ExtensionRange buildPartial() {
/*       */           DescriptorProtos.DescriptorProto.ExtensionRange result = new DescriptorProtos.DescriptorProto.ExtensionRange(this);
/*       */           int from_bitField0_ = this.bitField0_;
/*       */           int to_bitField0_ = 0;
/*       */           if ((from_bitField0_ & 0x1) == 1)
/*       */             to_bitField0_ |= 0x1; 
/*       */           result.start_ = this.start_;
/*       */           if ((from_bitField0_ & 0x2) == 2)
/*       */             to_bitField0_ |= 0x2; 
/*       */           result.end_ = this.end_;
/*       */           result.bitField0_ = to_bitField0_;
/*       */           onBuilt();
/*       */           return result;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(Message other) {
/*       */           if (other instanceof DescriptorProtos.DescriptorProto.ExtensionRange)
/*       */             return mergeFrom((DescriptorProtos.DescriptorProto.ExtensionRange)other); 
/*       */           super.mergeFrom(other);
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(DescriptorProtos.DescriptorProto.ExtensionRange other) {
/*       */           if (other == DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance())
/*       */             return this; 
/*       */           if (other.hasStart())
/*       */             setStart(other.getStart()); 
/*       */           if (other.hasEnd())
/*       */             setEnd(other.getEnd()); 
/*       */           mergeUnknownFields(other.getUnknownFields());
/*       */           return this;
/*       */         }
/*       */         
/*       */         public final boolean isInitialized() {
/*       */           return true;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */           UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */           while (true) {
/*       */             int tag = input.readTag();
/*       */             switch (tag) {
/*       */               case 0:
/*       */                 setUnknownFields(unknownFields.build());
/*       */                 onChanged();
/*       */                 return this;
/*       */               case 8:
/*       */                 this.bitField0_ |= 0x1;
/*       */                 this.start_ = input.readInt32();
/*       */                 break;
/*       */               case 16:
/*       */                 this.bitField0_ |= 0x2;
/*       */                 this.end_ = input.readInt32();
/*       */                 break;
/*       */             } 
/*       */           } 
/*       */         }
/*       */         
/*       */         public boolean hasStart() {
/*       */           return ((this.bitField0_ & 0x1) == 1);
/*       */         }
/*       */         
/*       */         public int getStart() {
/*       */           return this.start_;
/*       */         }
/*       */         
/*       */         public Builder setStart(int value) {
/*       */           this.bitField0_ |= 0x1;
/*       */           this.start_ = value;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearStart() {
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           this.start_ = 0;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public boolean hasEnd() {
/*       */           return ((this.bitField0_ & 0x2) == 2);
/*       */         }
/*       */         
/*       */         public int getEnd() {
/*       */           return this.end_;
/*       */         }
/*       */         
/*       */         public Builder setEnd(int value) {
/*       */           this.bitField0_ |= 0x2;
/*       */           this.end_ = value;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearEnd() {
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           this.end_ = 0;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */       }
/*       */       
/*       */       private static final ExtensionRange defaultInstance = new ExtensionRange(true);
/*       */       
/*       */       private int bitField0_;
/*       */       
/*       */       public static final int START_FIELD_NUMBER = 1;
/*       */       
/*       */       private int start_;
/*       */       
/*       */       public static final int END_FIELD_NUMBER = 2;
/*       */       
/*       */       private int end_;
/*       */       
/*       */       private byte memoizedIsInitialized;
/*       */       
/*       */       private int memoizedSerializedSize;
/*       */       
/*       */       private static final long serialVersionUID = 0L;
/*       */       
/*       */       static {
/*       */         defaultInstance.initFields();
/*       */       }
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.FieldDescriptorProto> getFieldList() {
/*       */       return this.field_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
/*       */       return (List)this.field_;
/*       */     }
/*       */     
/*       */     public int getFieldCount() {
/*       */       return this.field_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProto getField(int index) {
/*       */       return this.field_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int index) {
/*       */       return this.field_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.FieldDescriptorProto> getExtensionList() {
/*       */       return this.extension_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
/*       */       return (List)this.extension_;
/*       */     }
/*       */     
/*       */     public int getExtensionCount() {
/*       */       return this.extension_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProto getExtension(int index) {
/*       */       return this.extension_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
/*       */       return this.extension_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProto> getNestedTypeList() {
/*       */       return this.nestedType_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
/*       */       return (List)this.nestedType_;
/*       */     }
/*       */     
/*       */     public int getNestedTypeCount() {
/*       */       return this.nestedType_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProto getNestedType(int index) {
/*       */       return this.nestedType_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.DescriptorProtoOrBuilder getNestedTypeOrBuilder(int index) {
/*       */       return this.nestedType_.get(index);
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList() {
/*       */       return this.enumType_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
/*       */       return (List)this.enumType_;
/*       */     }
/*       */     
/*       */     public int getEnumTypeCount() {
/*       */       return this.enumType_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumDescriptorProto getEnumType(int index) {
/*       */       return this.enumType_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
/*       */       return this.enumType_.get(index);
/*       */     }
/*       */     
/*       */     public List<ExtensionRange> getExtensionRangeList() {
/*       */       return this.extensionRange_;
/*       */     }
/*       */     
/*       */     public List<? extends ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
/*       */       return (List)this.extensionRange_;
/*       */     }
/*       */     
/*       */     public int getExtensionRangeCount() {
/*       */       return this.extensionRange_.size();
/*       */     }
/*       */     
/*       */     public ExtensionRange getExtensionRange(int index) {
/*       */       return this.extensionRange_.get(index);
/*       */     }
/*       */     
/*       */     public ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int index) {
/*       */       return this.extensionRange_.get(index);
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MessageOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.field_ = Collections.emptyList();
/*       */       this.extension_ = Collections.emptyList();
/*       */       this.nestedType_ = Collections.emptyList();
/*       */       this.enumType_ = Collections.emptyList();
/*       */       this.extensionRange_ = Collections.emptyList();
/*       */       this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       int i;
/*       */       for (i = 0; i < getFieldCount(); i++) {
/*       */         if (!getField(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getExtensionCount(); i++) {
/*       */         if (!getExtension(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getNestedTypeCount(); i++) {
/*       */         if (!getNestedType(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       for (i = 0; i < getEnumTypeCount(); i++) {
/*       */         if (!getEnumType(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       int i;
/*       */       for (i = 0; i < this.field_.size(); i++)
/*       */         output.writeMessage(2, this.field_.get(i)); 
/*       */       for (i = 0; i < this.nestedType_.size(); i++)
/*       */         output.writeMessage(3, this.nestedType_.get(i)); 
/*       */       for (i = 0; i < this.enumType_.size(); i++)
/*       */         output.writeMessage(4, this.enumType_.get(i)); 
/*       */       for (i = 0; i < this.extensionRange_.size(); i++)
/*       */         output.writeMessage(5, this.extensionRange_.get(i)); 
/*       */       for (i = 0; i < this.extension_.size(); i++)
/*       */         output.writeMessage(6, this.extension_.get(i)); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeMessage(7, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  3305 */       int size = this.memoizedSerializedSize;
/*  3306 */       if (size != -1)
/*  3306 */         return size; 
/*  3308 */       size = 0;
/*  3309 */       if ((this.bitField0_ & 0x1) == 1)
/*  3310 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*       */       int i;
/*  3313 */       for (i = 0; i < this.field_.size(); i++)
/*  3314 */         size += CodedOutputStream.computeMessageSize(2, this.field_.get(i)); 
/*  3317 */       for (i = 0; i < this.nestedType_.size(); i++)
/*  3318 */         size += CodedOutputStream.computeMessageSize(3, this.nestedType_.get(i)); 
/*  3321 */       for (i = 0; i < this.enumType_.size(); i++)
/*  3322 */         size += CodedOutputStream.computeMessageSize(4, this.enumType_.get(i)); 
/*  3325 */       for (i = 0; i < this.extensionRange_.size(); i++)
/*  3326 */         size += CodedOutputStream.computeMessageSize(5, this.extensionRange_.get(i)); 
/*  3329 */       for (i = 0; i < this.extension_.size(); i++)
/*  3330 */         size += CodedOutputStream.computeMessageSize(6, this.extension_.get(i)); 
/*  3333 */       if ((this.bitField0_ & 0x2) == 2)
/*  3334 */         size += CodedOutputStream.computeMessageSize(7, this.options_); 
/*  3337 */       size += getUnknownFields().getSerializedSize();
/*  3338 */       this.memoizedSerializedSize = size;
/*  3339 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  3346 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  3352 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  3358 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  3363 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  3369 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(InputStream input) throws IOException {
/*  3374 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  3380 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  3385 */       Builder builder = newBuilder();
/*  3386 */       if (builder.mergeDelimitedFrom(input))
/*  3387 */         return builder.buildParsed(); 
/*  3389 */       return null;
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  3396 */       Builder builder = newBuilder();
/*  3397 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  3398 */         return builder.buildParsed(); 
/*  3400 */       return null;
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  3406 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static DescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  3412 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  3416 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  3417 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(DescriptorProto prototype) {
/*  3419 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  3421 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  3426 */       Builder builder = new Builder(parent);
/*  3427 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.DescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private List<DescriptorProtos.FieldDescriptorProto> field_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> fieldBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.FieldDescriptorProto> extension_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> extensionBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.DescriptorProto> nestedType_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, Builder, DescriptorProtos.DescriptorProtoOrBuilder> nestedTypeBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.EnumDescriptorProto> enumType_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder> enumTypeBuilder_;
/*       */       
/*       */       private List<DescriptorProtos.DescriptorProto.ExtensionRange> extensionRange_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto.ExtensionRange, DescriptorProtos.DescriptorProto.ExtensionRange.Builder, DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder> extensionRangeBuilder_;
/*       */       
/*       */       private DescriptorProtos.MessageOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.MessageOptions, DescriptorProtos.MessageOptions.Builder, DescriptorProtos.MessageOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  3434 */         return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  3439 */         return DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  3864 */         this.name_ = "";
/*  3900 */         this.field_ = Collections.emptyList();
/*  4086 */         this.extension_ = Collections.emptyList();
/*  4272 */         this.nestedType_ = Collections.emptyList();
/*  4458 */         this.enumType_ = Collections.emptyList();
/*  4644 */         this.extensionRange_ = Collections.emptyList();
/*  4830 */         this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.field_ = Collections.emptyList();
/*       */         this.extension_ = Collections.emptyList();
/*       */         this.nestedType_ = Collections.emptyList();
/*       */         this.enumType_ = Collections.emptyList();
/*       */         this.extensionRange_ = Collections.emptyList();
/*  4830 */         this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders) {
/*       */           getFieldFieldBuilder();
/*       */           getExtensionFieldBuilder();
/*       */           getNestedTypeFieldBuilder();
/*       */           getEnumTypeFieldBuilder();
/*       */           getExtensionRangeFieldBuilder();
/*       */           getOptionsFieldBuilder();
/*       */         } 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         if (this.fieldBuilder_ == null) {
/*       */           this.field_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */         } else {
/*       */           this.fieldBuilder_.clear();
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extension_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFB;
/*       */         } else {
/*       */           this.extensionBuilder_.clear();
/*       */         } 
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           this.nestedType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFF7;
/*       */         } else {
/*       */           this.nestedTypeBuilder_.clear();
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFEF;
/*       */         } else {
/*       */           this.enumTypeBuilder_.clear();
/*       */         } 
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           this.extensionRange_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFDF;
/*       */         } else {
/*       */           this.extensionRangeBuilder_.clear();
/*       */         } 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.DescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.DescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto build() {
/*       */         DescriptorProtos.DescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.DescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.DescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto buildPartial() {
/*       */         DescriptorProtos.DescriptorProto result = new DescriptorProtos.DescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if (this.fieldBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x2) == 2) {
/*       */             this.field_ = Collections.unmodifiableList(this.field_);
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */           } 
/*       */           result.field_ = this.field_;
/*       */         } else {
/*       */           result.field_ = this.fieldBuilder_.build();
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x4) == 4) {
/*       */             this.extension_ = Collections.unmodifiableList(this.extension_);
/*       */             this.bitField0_ &= 0xFFFFFFFB;
/*       */           } 
/*       */           result.extension_ = this.extension_;
/*       */         } else {
/*       */           result.extension_ = this.extensionBuilder_.build();
/*       */         } 
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x8) == 8) {
/*       */             this.nestedType_ = Collections.unmodifiableList(this.nestedType_);
/*       */             this.bitField0_ &= 0xFFFFFFF7;
/*       */           } 
/*       */           result.nestedType_ = this.nestedType_;
/*       */         } else {
/*       */           result.nestedType_ = this.nestedTypeBuilder_.build();
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x10) == 16) {
/*       */             this.enumType_ = Collections.unmodifiableList(this.enumType_);
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */           } 
/*       */           result.enumType_ = this.enumType_;
/*       */         } else {
/*       */           result.enumType_ = this.enumTypeBuilder_.build();
/*       */         } 
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x20) == 32) {
/*       */             this.extensionRange_ = Collections.unmodifiableList(this.extensionRange_);
/*       */             this.bitField0_ &= 0xFFFFFFDF;
/*       */           } 
/*       */           result.extensionRange_ = this.extensionRange_;
/*       */         } else {
/*       */           result.extensionRange_ = this.extensionRangeBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x40) == 64)
/*       */           to_bitField0_ |= 0x2; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.DescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.DescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.DescriptorProto other) {
/*       */         if (other == DescriptorProtos.DescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (this.fieldBuilder_ == null) {
/*       */           if (!other.field_.isEmpty()) {
/*       */             if (this.field_.isEmpty()) {
/*       */               this.field_ = other.field_;
/*       */               this.bitField0_ &= 0xFFFFFFFD;
/*       */             } else {
/*       */               ensureFieldIsMutable();
/*       */               this.field_.addAll(other.field_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.field_.isEmpty()) {
/*       */           if (this.fieldBuilder_.isEmpty()) {
/*       */             this.fieldBuilder_.dispose();
/*       */             this.fieldBuilder_ = null;
/*       */             this.field_ = other.field_;
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */             this.fieldBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getFieldFieldBuilder() : null;
/*       */           } else {
/*       */             this.fieldBuilder_.addAllMessages(other.field_);
/*       */           } 
/*       */         } 
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (!other.extension_.isEmpty()) {
/*       */             if (this.extension_.isEmpty()) {
/*       */               this.extension_ = other.extension_;
/*       */               this.bitField0_ &= 0xFFFFFFFB;
/*       */             } else {
/*       */               ensureExtensionIsMutable();
/*       */               this.extension_.addAll(other.extension_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.extension_.isEmpty()) {
/*       */           if (this.extensionBuilder_.isEmpty()) {
/*       */             this.extensionBuilder_.dispose();
/*       */             this.extensionBuilder_ = null;
/*       */             this.extension_ = other.extension_;
/*       */             this.bitField0_ &= 0xFFFFFFFB;
/*       */             this.extensionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getExtensionFieldBuilder() : null;
/*       */           } else {
/*       */             this.extensionBuilder_.addAllMessages(other.extension_);
/*       */           } 
/*       */         } 
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           if (!other.nestedType_.isEmpty()) {
/*       */             if (this.nestedType_.isEmpty()) {
/*       */               this.nestedType_ = other.nestedType_;
/*       */               this.bitField0_ &= 0xFFFFFFF7;
/*       */             } else {
/*       */               ensureNestedTypeIsMutable();
/*       */               this.nestedType_.addAll(other.nestedType_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.nestedType_.isEmpty()) {
/*       */           if (this.nestedTypeBuilder_.isEmpty()) {
/*       */             this.nestedTypeBuilder_.dispose();
/*       */             this.nestedTypeBuilder_ = null;
/*       */             this.nestedType_ = other.nestedType_;
/*       */             this.bitField0_ &= 0xFFFFFFF7;
/*       */             this.nestedTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getNestedTypeFieldBuilder() : null;
/*       */           } else {
/*       */             this.nestedTypeBuilder_.addAllMessages(other.nestedType_);
/*       */           } 
/*       */         } 
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (!other.enumType_.isEmpty()) {
/*       */             if (this.enumType_.isEmpty()) {
/*       */               this.enumType_ = other.enumType_;
/*       */               this.bitField0_ &= 0xFFFFFFEF;
/*       */             } else {
/*       */               ensureEnumTypeIsMutable();
/*       */               this.enumType_.addAll(other.enumType_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.enumType_.isEmpty()) {
/*       */           if (this.enumTypeBuilder_.isEmpty()) {
/*       */             this.enumTypeBuilder_.dispose();
/*       */             this.enumTypeBuilder_ = null;
/*       */             this.enumType_ = other.enumType_;
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */             this.enumTypeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getEnumTypeFieldBuilder() : null;
/*       */           } else {
/*       */             this.enumTypeBuilder_.addAllMessages(other.enumType_);
/*       */           } 
/*       */         } 
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           if (!other.extensionRange_.isEmpty()) {
/*       */             if (this.extensionRange_.isEmpty()) {
/*       */               this.extensionRange_ = other.extensionRange_;
/*       */               this.bitField0_ &= 0xFFFFFFDF;
/*       */             } else {
/*       */               ensureExtensionRangeIsMutable();
/*       */               this.extensionRange_.addAll(other.extensionRange_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.extensionRange_.isEmpty()) {
/*       */           if (this.extensionRangeBuilder_.isEmpty()) {
/*       */             this.extensionRangeBuilder_.dispose();
/*       */             this.extensionRangeBuilder_ = null;
/*       */             this.extensionRange_ = other.extensionRange_;
/*       */             this.bitField0_ &= 0xFFFFFFDF;
/*       */             this.extensionRangeBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getExtensionRangeFieldBuilder() : null;
/*       */           } else {
/*       */             this.extensionRangeBuilder_.addAllMessages(other.extensionRange_);
/*       */           } 
/*       */         } 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         int i;
/*       */         for (i = 0; i < getFieldCount(); i++) {
/*       */           if (!getField(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getExtensionCount(); i++) {
/*       */           if (!getExtension(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getNestedTypeCount(); i++) {
/*       */           if (!getNestedType(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         for (i = 0; i < getEnumTypeCount(); i++) {
/*       */           if (!getEnumType(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.FieldDescriptorProto.Builder builder4;
/*       */           Builder builder3;
/*       */           DescriptorProtos.EnumDescriptorProto.Builder builder2;
/*       */           DescriptorProtos.DescriptorProto.ExtensionRange.Builder builder;
/*       */           DescriptorProtos.FieldDescriptorProto.Builder builder1;
/*       */           DescriptorProtos.MessageOptions.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               builder4 = DescriptorProtos.FieldDescriptorProto.newBuilder();
/*       */               input.readMessage(builder4, extensionRegistry);
/*       */               addField(builder4.buildPartial());
/*       */               break;
/*       */             case 26:
/*       */               builder3 = DescriptorProtos.DescriptorProto.newBuilder();
/*       */               input.readMessage(builder3, extensionRegistry);
/*       */               addNestedType(builder3.buildPartial());
/*       */               break;
/*       */             case 34:
/*       */               builder2 = DescriptorProtos.EnumDescriptorProto.newBuilder();
/*       */               input.readMessage(builder2, extensionRegistry);
/*       */               addEnumType(builder2.buildPartial());
/*       */               break;
/*       */             case 42:
/*       */               builder = DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder();
/*       */               input.readMessage(builder, extensionRegistry);
/*       */               addExtensionRange(builder.buildPartial());
/*       */               break;
/*       */             case 50:
/*       */               builder1 = DescriptorProtos.FieldDescriptorProto.newBuilder();
/*       */               input.readMessage(builder1, extensionRegistry);
/*       */               addExtension(builder1.buildPartial());
/*       */               break;
/*       */             case 58:
/*       */               subBuilder = DescriptorProtos.MessageOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.DescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureFieldIsMutable() {
/*       */         if ((this.bitField0_ & 0x2) != 2) {
/*       */           this.field_ = new ArrayList<DescriptorProtos.FieldDescriptorProto>(this.field_);
/*       */           this.bitField0_ |= 0x2;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto> getFieldList() {
/*       */         if (this.fieldBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.field_); 
/*       */         return this.fieldBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getFieldCount() {
/*       */         if (this.fieldBuilder_ == null)
/*       */           return this.field_.size(); 
/*       */         return this.fieldBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto getField(int index) {
/*       */         if (this.fieldBuilder_ == null)
/*       */           return this.field_.get(index); 
/*       */         return this.fieldBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setField(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureFieldIsMutable();
/*       */           this.field_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setField(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           ensureFieldIsMutable();
/*       */           this.field_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addField(DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureFieldIsMutable();
/*       */           this.field_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addField(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureFieldIsMutable();
/*       */           this.field_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addField(DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           ensureFieldIsMutable();
/*       */           this.field_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addField(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           ensureFieldIsMutable();
/*       */           this.field_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllField(Iterable<? extends DescriptorProtos.FieldDescriptorProto> values) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           ensureFieldIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.field_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearField() {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           this.field_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeField(int index) {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           ensureFieldIsMutable();
/*       */           this.field_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.fieldBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder getFieldBuilder(int index) {
/*       */         return getFieldFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int index) {
/*       */         if (this.fieldBuilder_ == null)
/*       */           return this.field_.get(index); 
/*       */         return this.fieldBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getFieldOrBuilderList() {
/*       */         if (this.fieldBuilder_ != null)
/*       */           return this.fieldBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.field_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addFieldBuilder() {
/*       */         return getFieldFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addFieldBuilder(int index) {
/*       */         return getFieldFieldBuilder().addBuilder(index, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto.Builder> getFieldBuilderList() {
/*       */         return getFieldFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> getFieldFieldBuilder() {
/*       */         if (this.fieldBuilder_ == null) {
/*       */           this.fieldBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder>(this.field_, ((this.bitField0_ & 0x2) == 2), getParentForChildren(), isClean());
/*       */           this.field_ = null;
/*       */         } 
/*       */         return this.fieldBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureExtensionIsMutable() {
/*       */         if ((this.bitField0_ & 0x4) != 4) {
/*       */           this.extension_ = new ArrayList<DescriptorProtos.FieldDescriptorProto>(this.extension_);
/*       */           this.bitField0_ |= 0x4;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto> getExtensionList() {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.extension_); 
/*       */         return this.extensionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getExtensionCount() {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.size(); 
/*       */         return this.extensionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto getExtension(int index) {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.get(index); 
/*       */         return this.extensionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setExtension(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setExtension(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(int index, DescriptorProtos.FieldDescriptorProto value) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtension(int index, DescriptorProtos.FieldDescriptorProto.Builder builderForValue) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllExtension(Iterable<? extends DescriptorProtos.FieldDescriptorProto> values) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.extension_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearExtension() {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extension_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFB;
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeExtension(int index) {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           ensureExtensionIsMutable();
/*       */           this.extension_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder getExtensionBuilder(int index) {
/*       */         return getExtensionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int index) {
/*       */         if (this.extensionBuilder_ == null)
/*       */           return this.extension_.get(index); 
/*       */         return this.extensionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList() {
/*       */         if (this.extensionBuilder_ != null)
/*       */           return this.extensionBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.extension_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder() {
/*       */         return getExtensionFieldBuilder().addBuilder(DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Builder addExtensionBuilder(int index) {
/*       */         return getExtensionFieldBuilder().addBuilder(index, DescriptorProtos.FieldDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.FieldDescriptorProto.Builder> getExtensionBuilderList() {
/*       */         return getExtensionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionFieldBuilder() {
/*       */         if (this.extensionBuilder_ == null) {
/*       */           this.extensionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.FieldDescriptorProto, DescriptorProtos.FieldDescriptorProto.Builder, DescriptorProtos.FieldDescriptorProtoOrBuilder>(this.extension_, ((this.bitField0_ & 0x4) == 4), getParentForChildren(), isClean());
/*       */           this.extension_ = null;
/*       */         } 
/*       */         return this.extensionBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureNestedTypeIsMutable() {
/*       */         if ((this.bitField0_ & 0x8) != 8) {
/*       */           this.nestedType_ = new ArrayList<DescriptorProtos.DescriptorProto>(this.nestedType_);
/*       */           this.bitField0_ |= 0x8;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.DescriptorProto> getNestedTypeList() {
/*       */         if (this.nestedTypeBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.nestedType_); 
/*       */         return this.nestedTypeBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getNestedTypeCount() {
/*       */         if (this.nestedTypeBuilder_ == null)
/*       */           return this.nestedType_.size(); 
/*       */         return this.nestedTypeBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto getNestedType(int index) {
/*       */         if (this.nestedTypeBuilder_ == null)
/*       */           return this.nestedType_.get(index); 
/*       */         return this.nestedTypeBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setNestedType(int index, DescriptorProtos.DescriptorProto value) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setNestedType(int index, Builder builderForValue) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addNestedType(DescriptorProtos.DescriptorProto value) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addNestedType(int index, DescriptorProtos.DescriptorProto value) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addNestedType(Builder builderForValue) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addNestedType(int index, Builder builderForValue) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllNestedType(Iterable<? extends DescriptorProtos.DescriptorProto> values) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           ensureNestedTypeIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.nestedType_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearNestedType() {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           this.nestedType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFF7;
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeNestedType(int index) {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           ensureNestedTypeIsMutable();
/*       */           this.nestedType_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nestedTypeBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder getNestedTypeBuilder(int index) {
/*       */         return getNestedTypeFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProtoOrBuilder getNestedTypeOrBuilder(int index) {
/*       */         if (this.nestedTypeBuilder_ == null)
/*       */           return this.nestedType_.get(index); 
/*       */         return this.nestedTypeBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.DescriptorProtoOrBuilder> getNestedTypeOrBuilderList() {
/*       */         if (this.nestedTypeBuilder_ != null)
/*       */           return this.nestedTypeBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.nestedType_);
/*       */       }
/*       */       
/*       */       public Builder addNestedTypeBuilder() {
/*       */         return getNestedTypeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public Builder addNestedTypeBuilder(int index) {
/*       */         return getNestedTypeFieldBuilder().addBuilder(index, DescriptorProtos.DescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<Builder> getNestedTypeBuilderList() {
/*       */         return getNestedTypeFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, Builder, DescriptorProtos.DescriptorProtoOrBuilder> getNestedTypeFieldBuilder() {
/*       */         if (this.nestedTypeBuilder_ == null) {
/*       */           this.nestedTypeBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.DescriptorProto, Builder, DescriptorProtos.DescriptorProtoOrBuilder>(this.nestedType_, ((this.bitField0_ & 0x8) == 8), getParentForChildren(), isClean());
/*       */           this.nestedType_ = null;
/*       */         } 
/*       */         return this.nestedTypeBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureEnumTypeIsMutable() {
/*       */         if ((this.bitField0_ & 0x10) != 16) {
/*       */           this.enumType_ = new ArrayList<DescriptorProtos.EnumDescriptorProto>(this.enumType_);
/*       */           this.bitField0_ |= 0x10;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList() {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.enumType_); 
/*       */         return this.enumTypeBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getEnumTypeCount() {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.size(); 
/*       */         return this.enumTypeBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto getEnumType(int index) {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.get(index); 
/*       */         return this.enumTypeBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setEnumType(int index, DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setEnumType(int index, DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(int index, DescriptorProtos.EnumDescriptorProto value) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addEnumType(int index, DescriptorProtos.EnumDescriptorProto.Builder builderForValue) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllEnumType(Iterable<? extends DescriptorProtos.EnumDescriptorProto> values) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.enumType_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearEnumType() {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumType_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFEF;
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeEnumType(int index) {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           ensureEnumTypeIsMutable();
/*       */           this.enumType_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.enumTypeBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder getEnumTypeBuilder(int index) {
/*       */         return getEnumTypeFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int index) {
/*       */         if (this.enumTypeBuilder_ == null)
/*       */           return this.enumType_.get(index); 
/*       */         return this.enumTypeBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList() {
/*       */         if (this.enumTypeBuilder_ != null)
/*       */           return this.enumTypeBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.enumType_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder() {
/*       */         return getEnumTypeFieldBuilder().addBuilder(DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto.Builder addEnumTypeBuilder(int index) {
/*       */         return getEnumTypeFieldBuilder().addBuilder(index, DescriptorProtos.EnumDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumDescriptorProto.Builder> getEnumTypeBuilderList() {
/*       */         return getEnumTypeFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeFieldBuilder() {
/*       */         if (this.enumTypeBuilder_ == null) {
/*       */           this.enumTypeBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.EnumDescriptorProto, DescriptorProtos.EnumDescriptorProto.Builder, DescriptorProtos.EnumDescriptorProtoOrBuilder>(this.enumType_, ((this.bitField0_ & 0x10) == 16), getParentForChildren(), isClean());
/*       */           this.enumType_ = null;
/*       */         } 
/*       */         return this.enumTypeBuilder_;
/*       */       }
/*       */       
/*       */       private void ensureExtensionRangeIsMutable() {
/*       */         if ((this.bitField0_ & 0x20) != 32) {
/*       */           this.extensionRange_ = new ArrayList<DescriptorProtos.DescriptorProto.ExtensionRange>(this.extensionRange_);
/*       */           this.bitField0_ |= 0x20;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.DescriptorProto.ExtensionRange> getExtensionRangeList() {
/*       */         if (this.extensionRangeBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.extensionRange_); 
/*       */         return this.extensionRangeBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getExtensionRangeCount() {
/*       */         if (this.extensionRangeBuilder_ == null)
/*       */           return this.extensionRange_.size(); 
/*       */         return this.extensionRangeBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.ExtensionRange getExtensionRange(int index) {
/*       */         if (this.extensionRangeBuilder_ == null)
/*       */           return this.extensionRange_.get(index); 
/*       */         return this.extensionRangeBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setExtensionRange(int index, DescriptorProtos.DescriptorProto.ExtensionRange value) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setExtensionRange(int index, DescriptorProtos.DescriptorProto.ExtensionRange.Builder builderForValue) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange value) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtensionRange(int index, DescriptorProtos.DescriptorProto.ExtensionRange value) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.Builder builderForValue) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addExtensionRange(int index, DescriptorProtos.DescriptorProto.ExtensionRange.Builder builderForValue) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllExtensionRange(Iterable<? extends DescriptorProtos.DescriptorProto.ExtensionRange> values) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           ensureExtensionRangeIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.extensionRange_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearExtensionRange() {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           this.extensionRange_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFDF;
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeExtensionRange(int index) {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           ensureExtensionRangeIsMutable();
/*       */           this.extensionRange_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.extensionRangeBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.ExtensionRange.Builder getExtensionRangeBuilder(int index) {
/*       */         return getExtensionRangeFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int index) {
/*       */         if (this.extensionRangeBuilder_ == null)
/*       */           return this.extensionRange_.get(index); 
/*       */         return this.extensionRangeBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList() {
/*       */         if (this.extensionRangeBuilder_ != null)
/*       */           return this.extensionRangeBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.extensionRange_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.ExtensionRange.Builder addExtensionRangeBuilder() {
/*       */         return getExtensionRangeFieldBuilder().addBuilder(DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.DescriptorProto.ExtensionRange.Builder addExtensionRangeBuilder(int index) {
/*       */         return getExtensionRangeFieldBuilder().addBuilder(index, DescriptorProtos.DescriptorProto.ExtensionRange.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.DescriptorProto.ExtensionRange.Builder> getExtensionRangeBuilderList() {
/*       */         return getExtensionRangeFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.DescriptorProto.ExtensionRange, DescriptorProtos.DescriptorProto.ExtensionRange.Builder, DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder> getExtensionRangeFieldBuilder() {
/*       */         if (this.extensionRangeBuilder_ == null) {
/*       */           this.extensionRangeBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.DescriptorProto.ExtensionRange, DescriptorProtos.DescriptorProto.ExtensionRange.Builder, DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder>(this.extensionRange_, ((this.bitField0_ & 0x20) == 32), getParentForChildren(), isClean());
/*       */           this.extensionRange_ = null;
/*       */         } 
/*       */         return this.extensionRangeBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  4834 */         return ((this.bitField0_ & 0x40) == 64);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptions getOptions() {
/*  4837 */         if (this.optionsBuilder_ == null)
/*  4838 */           return this.options_; 
/*  4840 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.MessageOptions value) {
/*  4844 */         if (this.optionsBuilder_ == null) {
/*  4845 */           if (value == null)
/*  4846 */             throw new NullPointerException(); 
/*  4848 */           this.options_ = value;
/*  4849 */           onChanged();
/*       */         } else {
/*  4851 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  4853 */         this.bitField0_ |= 0x40;
/*  4854 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.MessageOptions.Builder builderForValue) {
/*  4858 */         if (this.optionsBuilder_ == null) {
/*  4859 */           this.options_ = builderForValue.build();
/*  4860 */           onChanged();
/*       */         } else {
/*  4862 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  4864 */         this.bitField0_ |= 0x40;
/*  4865 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.MessageOptions value) {
/*  4868 */         if (this.optionsBuilder_ == null) {
/*  4869 */           if ((this.bitField0_ & 0x40) == 64 && this.options_ != DescriptorProtos.MessageOptions.getDefaultInstance()) {
/*  4871 */             this.options_ = DescriptorProtos.MessageOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  4874 */             this.options_ = value;
/*       */           } 
/*  4876 */           onChanged();
/*       */         } else {
/*  4878 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  4880 */         this.bitField0_ |= 0x40;
/*  4881 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  4884 */         if (this.optionsBuilder_ == null) {
/*  4885 */           this.options_ = DescriptorProtos.MessageOptions.getDefaultInstance();
/*  4886 */           onChanged();
/*       */         } else {
/*  4888 */           this.optionsBuilder_.clear();
/*       */         } 
/*  4890 */         this.bitField0_ &= 0xFFFFFFBF;
/*  4891 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptions.Builder getOptionsBuilder() {
/*  4894 */         this.bitField0_ |= 0x40;
/*  4895 */         onChanged();
/*  4896 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder() {
/*  4899 */         if (this.optionsBuilder_ != null)
/*  4900 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  4902 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.MessageOptions, DescriptorProtos.MessageOptions.Builder, DescriptorProtos.MessageOptionsOrBuilder> getOptionsFieldBuilder() {
/*  4908 */         if (this.optionsBuilder_ == null) {
/*  4909 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.MessageOptions, DescriptorProtos.MessageOptions.Builder, DescriptorProtos.MessageOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  4914 */           this.options_ = null;
/*       */         } 
/*  4916 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  4923 */     private static final DescriptorProto defaultInstance = new DescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int FIELD_FIELD_NUMBER = 2;
/*       */     
/*       */     private List<DescriptorProtos.FieldDescriptorProto> field_;
/*       */     
/*       */     public static final int EXTENSION_FIELD_NUMBER = 6;
/*       */     
/*       */     private List<DescriptorProtos.FieldDescriptorProto> extension_;
/*       */     
/*       */     public static final int NESTED_TYPE_FIELD_NUMBER = 3;
/*       */     
/*       */     private List<DescriptorProto> nestedType_;
/*       */     
/*       */     public static final int ENUM_TYPE_FIELD_NUMBER = 4;
/*       */     
/*       */     private List<DescriptorProtos.EnumDescriptorProto> enumType_;
/*       */     
/*       */     public static final int EXTENSION_RANGE_FIELD_NUMBER = 5;
/*       */     
/*       */     private List<ExtensionRange> extensionRange_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 7;
/*       */     
/*       */     private DescriptorProtos.MessageOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  4924 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class FieldDescriptorProto extends GeneratedMessage implements FieldDescriptorProtoOrBuilder {
/*       */     private FieldDescriptorProto(Builder builder) {
/*  4971 */       super(builder);
/*  5365 */       this.memoizedIsInitialized = -1;
/*  5410 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private FieldDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  5410 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public FieldDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public enum Type implements ProtocolMessageEnum {
/*       */       TYPE_DOUBLE(0, 1),
/*       */       TYPE_FLOAT(1, 2),
/*       */       TYPE_INT64(2, 3),
/*       */       TYPE_UINT64(3, 4),
/*       */       TYPE_INT32(4, 5),
/*       */       TYPE_FIXED64(5, 6),
/*       */       TYPE_FIXED32(6, 7),
/*       */       TYPE_BOOL(7, 8),
/*       */       TYPE_STRING(8, 9),
/*       */       TYPE_GROUP(9, 10),
/*       */       TYPE_MESSAGE(10, 11),
/*       */       TYPE_BYTES(11, 12),
/*       */       TYPE_UINT32(12, 13),
/*       */       TYPE_ENUM(13, 14),
/*       */       TYPE_SFIXED32(14, 15),
/*       */       TYPE_SFIXED64(15, 16),
/*       */       TYPE_SINT32(16, 17),
/*       */       TYPE_SINT64(17, 18);
/*       */       
/*       */       public static final int TYPE_DOUBLE_VALUE = 1;
/*       */       
/*       */       public static final int TYPE_FLOAT_VALUE = 2;
/*       */       
/*       */       public static final int TYPE_INT64_VALUE = 3;
/*       */       
/*       */       public static final int TYPE_UINT64_VALUE = 4;
/*       */       
/*       */       public static final int TYPE_INT32_VALUE = 5;
/*       */       
/*       */       public static final int TYPE_FIXED64_VALUE = 6;
/*       */       
/*       */       public static final int TYPE_FIXED32_VALUE = 7;
/*       */       
/*       */       public static final int TYPE_BOOL_VALUE = 8;
/*       */       
/*       */       public static final int TYPE_STRING_VALUE = 9;
/*       */       
/*       */       public static final int TYPE_GROUP_VALUE = 10;
/*       */       
/*       */       public static final int TYPE_MESSAGE_VALUE = 11;
/*       */       
/*       */       public static final int TYPE_BYTES_VALUE = 12;
/*       */       
/*       */       public static final int TYPE_UINT32_VALUE = 13;
/*       */       
/*       */       public static final int TYPE_ENUM_VALUE = 14;
/*       */       
/*       */       public static final int TYPE_SFIXED32_VALUE = 15;
/*       */       
/*       */       public static final int TYPE_SFIXED64_VALUE = 16;
/*       */       
/*       */       public static final int TYPE_SINT32_VALUE = 17;
/*       */       
/*       */       public static final int TYPE_SINT64_VALUE = 18;
/*       */       
/*       */       private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap<Type>() {
/*       */           public DescriptorProtos.FieldDescriptorProto.Type findValueByNumber(int number) {
/*       */             return DescriptorProtos.FieldDescriptorProto.Type.valueOf(number);
/*       */           }
/*       */         };
/*       */       
/*       */       private static final Type[] VALUES = new Type[] { 
/*       */           TYPE_DOUBLE, TYPE_FLOAT, TYPE_INT64, TYPE_UINT64, TYPE_INT32, TYPE_FIXED64, TYPE_FIXED32, TYPE_BOOL, TYPE_STRING, TYPE_GROUP, 
/*       */           TYPE_MESSAGE, TYPE_BYTES, TYPE_UINT32, TYPE_ENUM, TYPE_SFIXED32, TYPE_SFIXED64, TYPE_SINT32, TYPE_SINT64 };
/*       */       
/*       */       private final int index;
/*       */       
/*       */       private final int value;
/*       */       
/*       */       public final int getNumber() {
/*       */         return this.value;
/*       */       }
/*       */       
/*       */       public static Internal.EnumLiteMap<Type> internalGetValueMap() {
/*       */         return internalValueMap;
/*       */       }
/*       */       
/*       */       static {
/*       */       
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumValueDescriptor getValueDescriptor() {
/*       */         return getDescriptor().getValues().get(this.index);
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumDescriptor getDescriptorForType() {
/*       */         return getDescriptor();
/*       */       }
/*       */       
/*       */       public static final Descriptors.EnumDescriptor getDescriptor() {
/*       */         return DescriptorProtos.FieldDescriptorProto.getDescriptor().getEnumTypes().get(0);
/*       */       }
/*       */       
/*       */       Type(int index, int value) {
/*       */         this.index = index;
/*       */         this.value = value;
/*       */       }
/*       */     }
/*       */     
/*       */     public enum Label implements ProtocolMessageEnum {
/*       */       LABEL_OPTIONAL(0, 1),
/*       */       LABEL_REQUIRED(1, 2),
/*       */       LABEL_REPEATED(2, 3);
/*       */       
/*       */       public static final int LABEL_OPTIONAL_VALUE = 1;
/*       */       
/*       */       public static final int LABEL_REQUIRED_VALUE = 2;
/*       */       
/*       */       public static final int LABEL_REPEATED_VALUE = 3;
/*       */       
/*       */       private static Internal.EnumLiteMap<Label> internalValueMap = new Internal.EnumLiteMap<Label>() {
/*       */           public DescriptorProtos.FieldDescriptorProto.Label findValueByNumber(int number) {
/*       */             return DescriptorProtos.FieldDescriptorProto.Label.valueOf(number);
/*       */           }
/*       */         };
/*       */       
/*       */       private static final Label[] VALUES = new Label[] { LABEL_OPTIONAL, LABEL_REQUIRED, LABEL_REPEATED };
/*       */       
/*       */       private final int index;
/*       */       
/*       */       private final int value;
/*       */       
/*       */       public final int getNumber() {
/*       */         return this.value;
/*       */       }
/*       */       
/*       */       public static Internal.EnumLiteMap<Label> internalGetValueMap() {
/*       */         return internalValueMap;
/*       */       }
/*       */       
/*       */       static {
/*       */       
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumValueDescriptor getValueDescriptor() {
/*       */         return getDescriptor().getValues().get(this.index);
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumDescriptor getDescriptorForType() {
/*       */         return getDescriptor();
/*       */       }
/*       */       
/*       */       public static final Descriptors.EnumDescriptor getDescriptor() {
/*       */         return DescriptorProtos.FieldDescriptorProto.getDescriptor().getEnumTypes().get(1);
/*       */       }
/*       */       
/*       */       Label(int index, int value) {
/*       */         this.index = index;
/*       */         this.value = value;
/*       */       }
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasNumber() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public int getNumber() {
/*       */       return this.number_;
/*       */     }
/*       */     
/*       */     public boolean hasLabel() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public Label getLabel() {
/*       */       return this.label_;
/*       */     }
/*       */     
/*       */     public boolean hasType() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public Type getType() {
/*       */       return this.type_;
/*       */     }
/*       */     
/*       */     public boolean hasTypeName() {
/*       */       return ((this.bitField0_ & 0x10) == 16);
/*       */     }
/*       */     
/*       */     public String getTypeName() {
/*       */       Object ref = this.typeName_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.typeName_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getTypeNameBytes() {
/*       */       Object ref = this.typeName_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.typeName_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasExtendee() {
/*       */       return ((this.bitField0_ & 0x20) == 32);
/*       */     }
/*       */     
/*       */     public String getExtendee() {
/*       */       Object ref = this.extendee_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.extendee_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getExtendeeBytes() {
/*       */       Object ref = this.extendee_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.extendee_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasDefaultValue() {
/*       */       return ((this.bitField0_ & 0x40) == 64);
/*       */     }
/*       */     
/*       */     public String getDefaultValue() {
/*       */       Object ref = this.defaultValue_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.defaultValue_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getDefaultValueBytes() {
/*       */       Object ref = this.defaultValue_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.defaultValue_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x80) == 128);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.number_ = 0;
/*       */       this.label_ = Label.LABEL_OPTIONAL;
/*       */       this.type_ = Type.TYPE_DOUBLE;
/*       */       this.typeName_ = "";
/*       */       this.extendee_ = "";
/*       */       this.defaultValue_ = "";
/*       */       this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       if ((this.bitField0_ & 0x20) == 32)
/*       */         output.writeBytes(2, getExtendeeBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeInt32(3, this.number_); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeEnum(4, this.label_.getNumber()); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeEnum(5, this.type_.getNumber()); 
/*       */       if ((this.bitField0_ & 0x10) == 16)
/*       */         output.writeBytes(6, getTypeNameBytes()); 
/*       */       if ((this.bitField0_ & 0x40) == 64)
/*       */         output.writeBytes(7, getDefaultValueBytes()); 
/*       */       if ((this.bitField0_ & 0x80) == 128)
/*       */         output.writeMessage(8, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  5412 */       int size = this.memoizedSerializedSize;
/*  5413 */       if (size != -1)
/*  5413 */         return size; 
/*  5415 */       size = 0;
/*  5416 */       if ((this.bitField0_ & 0x1) == 1)
/*  5417 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*  5420 */       if ((this.bitField0_ & 0x20) == 32)
/*  5421 */         size += CodedOutputStream.computeBytesSize(2, getExtendeeBytes()); 
/*  5424 */       if ((this.bitField0_ & 0x2) == 2)
/*  5425 */         size += CodedOutputStream.computeInt32Size(3, this.number_); 
/*  5428 */       if ((this.bitField0_ & 0x4) == 4)
/*  5429 */         size += CodedOutputStream.computeEnumSize(4, this.label_.getNumber()); 
/*  5432 */       if ((this.bitField0_ & 0x8) == 8)
/*  5433 */         size += CodedOutputStream.computeEnumSize(5, this.type_.getNumber()); 
/*  5436 */       if ((this.bitField0_ & 0x10) == 16)
/*  5437 */         size += CodedOutputStream.computeBytesSize(6, getTypeNameBytes()); 
/*  5440 */       if ((this.bitField0_ & 0x40) == 64)
/*  5441 */         size += CodedOutputStream.computeBytesSize(7, getDefaultValueBytes()); 
/*  5444 */       if ((this.bitField0_ & 0x80) == 128)
/*  5445 */         size += CodedOutputStream.computeMessageSize(8, this.options_); 
/*  5448 */       size += getUnknownFields().getSerializedSize();
/*  5449 */       this.memoizedSerializedSize = size;
/*  5450 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  5457 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  5463 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  5469 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  5474 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  5480 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(InputStream input) throws IOException {
/*  5485 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  5491 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  5496 */       Builder builder = newBuilder();
/*  5497 */       if (builder.mergeDelimitedFrom(input))
/*  5498 */         return builder.buildParsed(); 
/*  5500 */       return null;
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  5507 */       Builder builder = newBuilder();
/*  5508 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  5509 */         return builder.buildParsed(); 
/*  5511 */       return null;
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  5517 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  5523 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  5527 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  5528 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(FieldDescriptorProto prototype) {
/*  5530 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  5532 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  5537 */       Builder builder = new Builder(parent);
/*  5538 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.FieldDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private int number_;
/*       */       
/*       */       private DescriptorProtos.FieldDescriptorProto.Label label_;
/*       */       
/*       */       private DescriptorProtos.FieldDescriptorProto.Type type_;
/*       */       
/*       */       private Object typeName_;
/*       */       
/*       */       private Object extendee_;
/*       */       
/*       */       private Object defaultValue_;
/*       */       
/*       */       private DescriptorProtos.FieldOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.FieldOptions, DescriptorProtos.FieldOptions.Builder, DescriptorProtos.FieldOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  5545 */         return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  5550 */         return DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  5807 */         this.name_ = "";
/*  5864 */         this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
/*  5888 */         this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
/*  5912 */         this.typeName_ = "";
/*  5948 */         this.extendee_ = "";
/*  5984 */         this.defaultValue_ = "";
/*  6020 */         this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
/*       */         this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
/*       */         this.typeName_ = "";
/*       */         this.extendee_ = "";
/*       */         this.defaultValue_ = "";
/*  6020 */         this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getOptionsFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.number_ = 0;
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.typeName_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.extendee_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.defaultValue_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFF7F;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.FieldDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.FieldDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto build() {
/*       */         DescriptorProtos.FieldDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.FieldDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.FieldDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto buildPartial() {
/*       */         DescriptorProtos.FieldDescriptorProto result = new DescriptorProtos.FieldDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.number_ = this.number_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x4; 
/*       */         result.label_ = this.label_;
/*       */         if ((from_bitField0_ & 0x8) == 8)
/*       */           to_bitField0_ |= 0x8; 
/*       */         result.type_ = this.type_;
/*       */         if ((from_bitField0_ & 0x10) == 16)
/*       */           to_bitField0_ |= 0x10; 
/*       */         result.typeName_ = this.typeName_;
/*       */         if ((from_bitField0_ & 0x20) == 32)
/*       */           to_bitField0_ |= 0x20; 
/*       */         result.extendee_ = this.extendee_;
/*       */         if ((from_bitField0_ & 0x40) == 64)
/*       */           to_bitField0_ |= 0x40; 
/*       */         result.defaultValue_ = this.defaultValue_;
/*       */         if ((from_bitField0_ & 0x80) == 128)
/*       */           to_bitField0_ |= 0x80; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.FieldDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.FieldDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.FieldDescriptorProto other) {
/*       */         if (other == DescriptorProtos.FieldDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (other.hasNumber())
/*       */           setNumber(other.getNumber()); 
/*       */         if (other.hasLabel())
/*       */           setLabel(other.getLabel()); 
/*       */         if (other.hasType())
/*       */           setType(other.getType()); 
/*       */         if (other.hasTypeName())
/*       */           setTypeName(other.getTypeName()); 
/*       */         if (other.hasExtendee())
/*       */           setExtendee(other.getExtendee()); 
/*       */         if (other.hasDefaultValue())
/*       */           setDefaultValue(other.getDefaultValue()); 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           int rawValue;
/*       */           DescriptorProtos.FieldOptions.Builder subBuilder;
/*       */           DescriptorProtos.FieldDescriptorProto.Label label;
/*       */           DescriptorProtos.FieldDescriptorProto.Type value;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               this.bitField0_ |= 0x20;
/*       */               this.extendee_ = input.readBytes();
/*       */               break;
/*       */             case 24:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.number_ = input.readInt32();
/*       */               break;
/*       */             case 32:
/*       */               rawValue = input.readEnum();
/*       */               label = DescriptorProtos.FieldDescriptorProto.Label.valueOf(rawValue);
/*       */               if (label == null) {
/*       */                 unknownFields.mergeVarintField(4, rawValue);
/*       */                 break;
/*       */               } 
/*       */               this.bitField0_ |= 0x4;
/*       */               this.label_ = label;
/*       */               break;
/*       */             case 40:
/*       */               rawValue = input.readEnum();
/*       */               value = DescriptorProtos.FieldDescriptorProto.Type.valueOf(rawValue);
/*       */               if (value == null) {
/*       */                 unknownFields.mergeVarintField(5, rawValue);
/*       */                 break;
/*       */               } 
/*       */               this.bitField0_ |= 0x8;
/*       */               this.type_ = value;
/*       */               break;
/*       */             case 50:
/*       */               this.bitField0_ |= 0x10;
/*       */               this.typeName_ = input.readBytes();
/*       */               break;
/*       */             case 58:
/*       */               this.bitField0_ |= 0x40;
/*       */               this.defaultValue_ = input.readBytes();
/*       */               break;
/*       */             case 66:
/*       */               subBuilder = DescriptorProtos.FieldOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasNumber() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public int getNumber() {
/*       */         return this.number_;
/*       */       }
/*       */       
/*       */       public Builder setNumber(int value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.number_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearNumber() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.number_ = 0;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasLabel() {
/*       */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Label getLabel() {
/*       */         return this.label_;
/*       */       }
/*       */       
/*       */       public Builder setLabel(DescriptorProtos.FieldDescriptorProto.Label value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x4;
/*       */         this.label_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearLabel() {
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.label_ = DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasType() {
/*       */         return ((this.bitField0_ & 0x8) == 8);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldDescriptorProto.Type getType() {
/*       */         return this.type_;
/*       */       }
/*       */       
/*       */       public Builder setType(DescriptorProtos.FieldDescriptorProto.Type value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x8;
/*       */         this.type_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearType() {
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.type_ = DescriptorProtos.FieldDescriptorProto.Type.TYPE_DOUBLE;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasTypeName() {
/*       */         return ((this.bitField0_ & 0x10) == 16);
/*       */       }
/*       */       
/*       */       public String getTypeName() {
/*       */         Object ref = this.typeName_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.typeName_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setTypeName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x10;
/*       */         this.typeName_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearTypeName() {
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.typeName_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getTypeName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setTypeName(ByteString value) {
/*       */         this.bitField0_ |= 0x10;
/*       */         this.typeName_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasExtendee() {
/*       */         return ((this.bitField0_ & 0x20) == 32);
/*       */       }
/*       */       
/*       */       public String getExtendee() {
/*       */         Object ref = this.extendee_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.extendee_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setExtendee(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x20;
/*       */         this.extendee_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearExtendee() {
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.extendee_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getExtendee();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setExtendee(ByteString value) {
/*       */         this.bitField0_ |= 0x20;
/*       */         this.extendee_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasDefaultValue() {
/*       */         return ((this.bitField0_ & 0x40) == 64);
/*       */       }
/*       */       
/*       */       public String getDefaultValue() {
/*       */         Object ref = this.defaultValue_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.defaultValue_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setDefaultValue(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x40;
/*       */         this.defaultValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearDefaultValue() {
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         this.defaultValue_ = DescriptorProtos.FieldDescriptorProto.getDefaultInstance().getDefaultValue();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setDefaultValue(ByteString value) {
/*       */         this.bitField0_ |= 0x40;
/*       */         this.defaultValue_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  6024 */         return ((this.bitField0_ & 0x80) == 128);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions getOptions() {
/*  6027 */         if (this.optionsBuilder_ == null)
/*  6028 */           return this.options_; 
/*  6030 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.FieldOptions value) {
/*  6034 */         if (this.optionsBuilder_ == null) {
/*  6035 */           if (value == null)
/*  6036 */             throw new NullPointerException(); 
/*  6038 */           this.options_ = value;
/*  6039 */           onChanged();
/*       */         } else {
/*  6041 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  6043 */         this.bitField0_ |= 0x80;
/*  6044 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.FieldOptions.Builder builderForValue) {
/*  6048 */         if (this.optionsBuilder_ == null) {
/*  6049 */           this.options_ = builderForValue.build();
/*  6050 */           onChanged();
/*       */         } else {
/*  6052 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  6054 */         this.bitField0_ |= 0x80;
/*  6055 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.FieldOptions value) {
/*  6058 */         if (this.optionsBuilder_ == null) {
/*  6059 */           if ((this.bitField0_ & 0x80) == 128 && this.options_ != DescriptorProtos.FieldOptions.getDefaultInstance()) {
/*  6061 */             this.options_ = DescriptorProtos.FieldOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  6064 */             this.options_ = value;
/*       */           } 
/*  6066 */           onChanged();
/*       */         } else {
/*  6068 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  6070 */         this.bitField0_ |= 0x80;
/*  6071 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  6074 */         if (this.optionsBuilder_ == null) {
/*  6075 */           this.options_ = DescriptorProtos.FieldOptions.getDefaultInstance();
/*  6076 */           onChanged();
/*       */         } else {
/*  6078 */           this.optionsBuilder_.clear();
/*       */         } 
/*  6080 */         this.bitField0_ &= 0xFFFFFF7F;
/*  6081 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions.Builder getOptionsBuilder() {
/*  6084 */         this.bitField0_ |= 0x80;
/*  6085 */         onChanged();
/*  6086 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder() {
/*  6089 */         if (this.optionsBuilder_ != null)
/*  6090 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  6092 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.FieldOptions, DescriptorProtos.FieldOptions.Builder, DescriptorProtos.FieldOptionsOrBuilder> getOptionsFieldBuilder() {
/*  6098 */         if (this.optionsBuilder_ == null) {
/*  6099 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.FieldOptions, DescriptorProtos.FieldOptions.Builder, DescriptorProtos.FieldOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  6104 */           this.options_ = null;
/*       */         } 
/*  6106 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  6113 */     private static final FieldDescriptorProto defaultInstance = new FieldDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int NUMBER_FIELD_NUMBER = 3;
/*       */     
/*       */     private int number_;
/*       */     
/*       */     public static final int LABEL_FIELD_NUMBER = 4;
/*       */     
/*       */     private Label label_;
/*       */     
/*       */     public static final int TYPE_FIELD_NUMBER = 5;
/*       */     
/*       */     private Type type_;
/*       */     
/*       */     public static final int TYPE_NAME_FIELD_NUMBER = 6;
/*       */     
/*       */     private Object typeName_;
/*       */     
/*       */     public static final int EXTENDEE_FIELD_NUMBER = 2;
/*       */     
/*       */     private Object extendee_;
/*       */     
/*       */     public static final int DEFAULT_VALUE_FIELD_NUMBER = 7;
/*       */     
/*       */     private Object defaultValue_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 8;
/*       */     
/*       */     private DescriptorProtos.FieldOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  6114 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class EnumDescriptorProto extends GeneratedMessage implements EnumDescriptorProtoOrBuilder {
/*       */     private EnumDescriptorProto(Builder builder) {
/*  6147 */       super(builder);
/*  6242 */       this.memoizedIsInitialized = -1;
/*  6278 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private EnumDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  6278 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public EnumDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.EnumValueDescriptorProto> getValueList() {
/*       */       return this.value_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList() {
/*       */       return (List)this.value_;
/*       */     }
/*       */     
/*       */     public int getValueCount() {
/*       */       return this.value_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumValueDescriptorProto getValue(int index) {
/*       */       return this.value_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int index) {
/*       */       return this.value_.get(index);
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.value_ = Collections.emptyList();
/*       */       this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getValueCount(); i++) {
/*       */         if (!getValue(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       for (int i = 0; i < this.value_.size(); i++)
/*       */         output.writeMessage(2, this.value_.get(i)); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeMessage(3, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  6280 */       int size = this.memoizedSerializedSize;
/*  6281 */       if (size != -1)
/*  6281 */         return size; 
/*  6283 */       size = 0;
/*  6284 */       if ((this.bitField0_ & 0x1) == 1)
/*  6285 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*  6288 */       for (int i = 0; i < this.value_.size(); i++)
/*  6289 */         size += CodedOutputStream.computeMessageSize(2, this.value_.get(i)); 
/*  6292 */       if ((this.bitField0_ & 0x2) == 2)
/*  6293 */         size += CodedOutputStream.computeMessageSize(3, this.options_); 
/*  6296 */       size += getUnknownFields().getSerializedSize();
/*  6297 */       this.memoizedSerializedSize = size;
/*  6298 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  6305 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  6311 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  6317 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  6322 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  6328 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(InputStream input) throws IOException {
/*  6333 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  6339 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  6344 */       Builder builder = newBuilder();
/*  6345 */       if (builder.mergeDelimitedFrom(input))
/*  6346 */         return builder.buildParsed(); 
/*  6348 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  6355 */       Builder builder = newBuilder();
/*  6356 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  6357 */         return builder.buildParsed(); 
/*  6359 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  6365 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  6371 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  6375 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  6376 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(EnumDescriptorProto prototype) {
/*  6378 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  6380 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  6385 */       Builder builder = new Builder(parent);
/*  6386 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.EnumDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private List<DescriptorProtos.EnumValueDescriptorProto> value_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumValueDescriptorProto, DescriptorProtos.EnumValueDescriptorProto.Builder, DescriptorProtos.EnumValueDescriptorProtoOrBuilder> valueBuilder_;
/*       */       
/*       */       private DescriptorProtos.EnumOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.EnumOptions, DescriptorProtos.EnumOptions.Builder, DescriptorProtos.EnumOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  6393 */         return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  6398 */         return DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  6613 */         this.name_ = "";
/*  6649 */         this.value_ = Collections.emptyList();
/*  6835 */         this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.value_ = Collections.emptyList();
/*  6835 */         this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders) {
/*       */           getValueFieldBuilder();
/*       */           getOptionsFieldBuilder();
/*       */         } 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         if (this.valueBuilder_ == null) {
/*       */           this.value_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */         } else {
/*       */           this.valueBuilder_.clear();
/*       */         } 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.EnumDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.EnumDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto build() {
/*       */         DescriptorProtos.EnumDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.EnumDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.EnumDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumDescriptorProto buildPartial() {
/*       */         DescriptorProtos.EnumDescriptorProto result = new DescriptorProtos.EnumDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if (this.valueBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x2) == 2) {
/*       */             this.value_ = Collections.unmodifiableList(this.value_);
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */           } 
/*       */           result.value_ = this.value_;
/*       */         } else {
/*       */           result.value_ = this.valueBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x2; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.EnumDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.EnumDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.EnumDescriptorProto other) {
/*       */         if (other == DescriptorProtos.EnumDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (this.valueBuilder_ == null) {
/*       */           if (!other.value_.isEmpty()) {
/*       */             if (this.value_.isEmpty()) {
/*       */               this.value_ = other.value_;
/*       */               this.bitField0_ &= 0xFFFFFFFD;
/*       */             } else {
/*       */               ensureValueIsMutable();
/*       */               this.value_.addAll(other.value_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.value_.isEmpty()) {
/*       */           if (this.valueBuilder_.isEmpty()) {
/*       */             this.valueBuilder_.dispose();
/*       */             this.valueBuilder_ = null;
/*       */             this.value_ = other.value_;
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */             this.valueBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getValueFieldBuilder() : null;
/*       */           } else {
/*       */             this.valueBuilder_.addAllMessages(other.value_);
/*       */           } 
/*       */         } 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getValueCount(); i++) {
/*       */           if (!getValue(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.EnumValueDescriptorProto.Builder builder;
/*       */           DescriptorProtos.EnumOptions.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               builder = DescriptorProtos.EnumValueDescriptorProto.newBuilder();
/*       */               input.readMessage(builder, extensionRegistry);
/*       */               addValue(builder.buildPartial());
/*       */               break;
/*       */             case 26:
/*       */               subBuilder = DescriptorProtos.EnumOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.EnumDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureValueIsMutable() {
/*       */         if ((this.bitField0_ & 0x2) != 2) {
/*       */           this.value_ = new ArrayList<DescriptorProtos.EnumValueDescriptorProto>(this.value_);
/*       */           this.bitField0_ |= 0x2;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumValueDescriptorProto> getValueList() {
/*       */         if (this.valueBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.value_); 
/*       */         return this.valueBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getValueCount() {
/*       */         if (this.valueBuilder_ == null)
/*       */           return this.value_.size(); 
/*       */         return this.valueBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto getValue(int index) {
/*       */         if (this.valueBuilder_ == null)
/*       */           return this.value_.get(index); 
/*       */         return this.valueBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setValue(int index, DescriptorProtos.EnumValueDescriptorProto value) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureValueIsMutable();
/*       */           this.value_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setValue(int index, DescriptorProtos.EnumValueDescriptorProto.Builder builderForValue) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           ensureValueIsMutable();
/*       */           this.value_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addValue(DescriptorProtos.EnumValueDescriptorProto value) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureValueIsMutable();
/*       */           this.value_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addValue(int index, DescriptorProtos.EnumValueDescriptorProto value) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureValueIsMutable();
/*       */           this.value_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addValue(DescriptorProtos.EnumValueDescriptorProto.Builder builderForValue) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           ensureValueIsMutable();
/*       */           this.value_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addValue(int index, DescriptorProtos.EnumValueDescriptorProto.Builder builderForValue) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           ensureValueIsMutable();
/*       */           this.value_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllValue(Iterable<? extends DescriptorProtos.EnumValueDescriptorProto> values) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           ensureValueIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.value_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearValue() {
/*       */         if (this.valueBuilder_ == null) {
/*       */           this.value_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeValue(int index) {
/*       */         if (this.valueBuilder_ == null) {
/*       */           ensureValueIsMutable();
/*       */           this.value_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.valueBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto.Builder getValueBuilder(int index) {
/*       */         return getValueFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int index) {
/*       */         if (this.valueBuilder_ == null)
/*       */           return this.value_.get(index); 
/*       */         return this.valueBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList() {
/*       */         if (this.valueBuilder_ != null)
/*       */           return this.valueBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.value_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto.Builder addValueBuilder() {
/*       */         return getValueFieldBuilder().addBuilder(DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto.Builder addValueBuilder(int index) {
/*       */         return getValueFieldBuilder().addBuilder(index, DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.EnumValueDescriptorProto.Builder> getValueBuilderList() {
/*       */         return getValueFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.EnumValueDescriptorProto, DescriptorProtos.EnumValueDescriptorProto.Builder, DescriptorProtos.EnumValueDescriptorProtoOrBuilder> getValueFieldBuilder() {
/*       */         if (this.valueBuilder_ == null) {
/*       */           this.valueBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.EnumValueDescriptorProto, DescriptorProtos.EnumValueDescriptorProto.Builder, DescriptorProtos.EnumValueDescriptorProtoOrBuilder>(this.value_, ((this.bitField0_ & 0x2) == 2), getParentForChildren(), isClean());
/*       */           this.value_ = null;
/*       */         } 
/*       */         return this.valueBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  6839 */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptions getOptions() {
/*  6842 */         if (this.optionsBuilder_ == null)
/*  6843 */           return this.options_; 
/*  6845 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.EnumOptions value) {
/*  6849 */         if (this.optionsBuilder_ == null) {
/*  6850 */           if (value == null)
/*  6851 */             throw new NullPointerException(); 
/*  6853 */           this.options_ = value;
/*  6854 */           onChanged();
/*       */         } else {
/*  6856 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  6858 */         this.bitField0_ |= 0x4;
/*  6859 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.EnumOptions.Builder builderForValue) {
/*  6863 */         if (this.optionsBuilder_ == null) {
/*  6864 */           this.options_ = builderForValue.build();
/*  6865 */           onChanged();
/*       */         } else {
/*  6867 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  6869 */         this.bitField0_ |= 0x4;
/*  6870 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.EnumOptions value) {
/*  6873 */         if (this.optionsBuilder_ == null) {
/*  6874 */           if ((this.bitField0_ & 0x4) == 4 && this.options_ != DescriptorProtos.EnumOptions.getDefaultInstance()) {
/*  6876 */             this.options_ = DescriptorProtos.EnumOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  6879 */             this.options_ = value;
/*       */           } 
/*  6881 */           onChanged();
/*       */         } else {
/*  6883 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  6885 */         this.bitField0_ |= 0x4;
/*  6886 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  6889 */         if (this.optionsBuilder_ == null) {
/*  6890 */           this.options_ = DescriptorProtos.EnumOptions.getDefaultInstance();
/*  6891 */           onChanged();
/*       */         } else {
/*  6893 */           this.optionsBuilder_.clear();
/*       */         } 
/*  6895 */         this.bitField0_ &= 0xFFFFFFFB;
/*  6896 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptions.Builder getOptionsBuilder() {
/*  6899 */         this.bitField0_ |= 0x4;
/*  6900 */         onChanged();
/*  6901 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder() {
/*  6904 */         if (this.optionsBuilder_ != null)
/*  6905 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  6907 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.EnumOptions, DescriptorProtos.EnumOptions.Builder, DescriptorProtos.EnumOptionsOrBuilder> getOptionsFieldBuilder() {
/*  6913 */         if (this.optionsBuilder_ == null) {
/*  6914 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.EnumOptions, DescriptorProtos.EnumOptions.Builder, DescriptorProtos.EnumOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  6919 */           this.options_ = null;
/*       */         } 
/*  6921 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  6928 */     private static final EnumDescriptorProto defaultInstance = new EnumDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int VALUE_FIELD_NUMBER = 2;
/*       */     
/*       */     private List<DescriptorProtos.EnumValueDescriptorProto> value_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 3;
/*       */     
/*       */     private DescriptorProtos.EnumOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  6929 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class EnumValueDescriptorProto extends GeneratedMessage implements EnumValueDescriptorProtoOrBuilder {
/*       */     private EnumValueDescriptorProto(Builder builder) {
/*  6956 */       super(builder);
/*  7040 */       this.memoizedIsInitialized = -1;
/*  7070 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private EnumValueDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  7070 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public EnumValueDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasNumber() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public int getNumber() {
/*       */       return this.number_;
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumValueOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.number_ = 0;
/*       */       this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeInt32(2, this.number_); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeMessage(3, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  7072 */       int size = this.memoizedSerializedSize;
/*  7073 */       if (size != -1)
/*  7073 */         return size; 
/*  7075 */       size = 0;
/*  7076 */       if ((this.bitField0_ & 0x1) == 1)
/*  7077 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*  7080 */       if ((this.bitField0_ & 0x2) == 2)
/*  7081 */         size += CodedOutputStream.computeInt32Size(2, this.number_); 
/*  7084 */       if ((this.bitField0_ & 0x4) == 4)
/*  7085 */         size += CodedOutputStream.computeMessageSize(3, this.options_); 
/*  7088 */       size += getUnknownFields().getSerializedSize();
/*  7089 */       this.memoizedSerializedSize = size;
/*  7090 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  7097 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  7103 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  7109 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  7114 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  7120 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(InputStream input) throws IOException {
/*  7125 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7131 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  7136 */       Builder builder = newBuilder();
/*  7137 */       if (builder.mergeDelimitedFrom(input))
/*  7138 */         return builder.buildParsed(); 
/*  7140 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7147 */       Builder builder = newBuilder();
/*  7148 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  7149 */         return builder.buildParsed(); 
/*  7151 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  7157 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7163 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  7167 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  7168 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(EnumValueDescriptorProto prototype) {
/*  7170 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  7172 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  7177 */       Builder builder = new Builder(parent);
/*  7178 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.EnumValueDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private int number_;
/*       */       
/*       */       private DescriptorProtos.EnumValueOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.EnumValueOptions, DescriptorProtos.EnumValueOptions.Builder, DescriptorProtos.EnumValueOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  7185 */         return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  7190 */         return DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  7365 */         this.name_ = "";
/*  7422 */         this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*  7422 */         this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getOptionsFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.number_ = 0;
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.EnumValueDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto build() {
/*       */         DescriptorProtos.EnumValueDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.EnumValueDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.EnumValueDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueDescriptorProto buildPartial() {
/*       */         DescriptorProtos.EnumValueDescriptorProto result = new DescriptorProtos.EnumValueDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.number_ = this.number_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x4; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.EnumValueDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.EnumValueDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.EnumValueDescriptorProto other) {
/*       */         if (other == DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (other.hasNumber())
/*       */           setNumber(other.getNumber()); 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.EnumValueOptions.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 16:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.number_ = input.readInt32();
/*       */               break;
/*       */             case 26:
/*       */               subBuilder = DescriptorProtos.EnumValueOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.EnumValueDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasNumber() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public int getNumber() {
/*       */         return this.number_;
/*       */       }
/*       */       
/*       */       public Builder setNumber(int value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.number_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearNumber() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.number_ = 0;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  7426 */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptions getOptions() {
/*  7429 */         if (this.optionsBuilder_ == null)
/*  7430 */           return this.options_; 
/*  7432 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.EnumValueOptions value) {
/*  7436 */         if (this.optionsBuilder_ == null) {
/*  7437 */           if (value == null)
/*  7438 */             throw new NullPointerException(); 
/*  7440 */           this.options_ = value;
/*  7441 */           onChanged();
/*       */         } else {
/*  7443 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  7445 */         this.bitField0_ |= 0x4;
/*  7446 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.EnumValueOptions.Builder builderForValue) {
/*  7450 */         if (this.optionsBuilder_ == null) {
/*  7451 */           this.options_ = builderForValue.build();
/*  7452 */           onChanged();
/*       */         } else {
/*  7454 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  7456 */         this.bitField0_ |= 0x4;
/*  7457 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.EnumValueOptions value) {
/*  7460 */         if (this.optionsBuilder_ == null) {
/*  7461 */           if ((this.bitField0_ & 0x4) == 4 && this.options_ != DescriptorProtos.EnumValueOptions.getDefaultInstance()) {
/*  7463 */             this.options_ = DescriptorProtos.EnumValueOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  7466 */             this.options_ = value;
/*       */           } 
/*  7468 */           onChanged();
/*       */         } else {
/*  7470 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  7472 */         this.bitField0_ |= 0x4;
/*  7473 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  7476 */         if (this.optionsBuilder_ == null) {
/*  7477 */           this.options_ = DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*  7478 */           onChanged();
/*       */         } else {
/*  7480 */           this.optionsBuilder_.clear();
/*       */         } 
/*  7482 */         this.bitField0_ &= 0xFFFFFFFB;
/*  7483 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptions.Builder getOptionsBuilder() {
/*  7486 */         this.bitField0_ |= 0x4;
/*  7487 */         onChanged();
/*  7488 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder() {
/*  7491 */         if (this.optionsBuilder_ != null)
/*  7492 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  7494 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.EnumValueOptions, DescriptorProtos.EnumValueOptions.Builder, DescriptorProtos.EnumValueOptionsOrBuilder> getOptionsFieldBuilder() {
/*  7500 */         if (this.optionsBuilder_ == null) {
/*  7501 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.EnumValueOptions, DescriptorProtos.EnumValueOptions.Builder, DescriptorProtos.EnumValueOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  7506 */           this.options_ = null;
/*       */         } 
/*  7508 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  7515 */     private static final EnumValueDescriptorProto defaultInstance = new EnumValueDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int NUMBER_FIELD_NUMBER = 2;
/*       */     
/*       */     private int number_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 3;
/*       */     
/*       */     private DescriptorProtos.EnumValueOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  7516 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class ServiceDescriptorProto extends GeneratedMessage implements ServiceDescriptorProtoOrBuilder {
/*       */     private ServiceDescriptorProto(Builder builder) {
/*  7549 */       super(builder);
/*  7644 */       this.memoizedIsInitialized = -1;
/*  7680 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private ServiceDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  7680 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public ServiceDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.MethodDescriptorProto> getMethodList() {
/*       */       return this.method_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
/*       */       return (List)this.method_;
/*       */     }
/*       */     
/*       */     public int getMethodCount() {
/*       */       return this.method_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MethodDescriptorProto getMethod(int index) {
/*       */       return this.method_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int index) {
/*       */       return this.method_.get(index);
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.ServiceOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.method_ = Collections.emptyList();
/*       */       this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getMethodCount(); i++) {
/*       */         if (!getMethod(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       for (int i = 0; i < this.method_.size(); i++)
/*       */         output.writeMessage(2, this.method_.get(i)); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeMessage(3, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  7682 */       int size = this.memoizedSerializedSize;
/*  7683 */       if (size != -1)
/*  7683 */         return size; 
/*  7685 */       size = 0;
/*  7686 */       if ((this.bitField0_ & 0x1) == 1)
/*  7687 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*  7690 */       for (int i = 0; i < this.method_.size(); i++)
/*  7691 */         size += CodedOutputStream.computeMessageSize(2, this.method_.get(i)); 
/*  7694 */       if ((this.bitField0_ & 0x2) == 2)
/*  7695 */         size += CodedOutputStream.computeMessageSize(3, this.options_); 
/*  7698 */       size += getUnknownFields().getSerializedSize();
/*  7699 */       this.memoizedSerializedSize = size;
/*  7700 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  7707 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  7713 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  7719 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  7724 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  7730 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(InputStream input) throws IOException {
/*  7735 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7741 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  7746 */       Builder builder = newBuilder();
/*  7747 */       if (builder.mergeDelimitedFrom(input))
/*  7748 */         return builder.buildParsed(); 
/*  7750 */       return null;
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7757 */       Builder builder = newBuilder();
/*  7758 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  7759 */         return builder.buildParsed(); 
/*  7761 */       return null;
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  7767 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  7773 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  7777 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  7778 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(ServiceDescriptorProto prototype) {
/*  7780 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  7782 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  7787 */       Builder builder = new Builder(parent);
/*  7788 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.ServiceDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private List<DescriptorProtos.MethodDescriptorProto> method_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.MethodDescriptorProto, DescriptorProtos.MethodDescriptorProto.Builder, DescriptorProtos.MethodDescriptorProtoOrBuilder> methodBuilder_;
/*       */       
/*       */       private DescriptorProtos.ServiceOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.ServiceOptions, DescriptorProtos.ServiceOptions.Builder, DescriptorProtos.ServiceOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  7795 */         return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  7800 */         return DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  8015 */         this.name_ = "";
/*  8051 */         this.method_ = Collections.emptyList();
/*  8237 */         this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.method_ = Collections.emptyList();
/*  8237 */         this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders) {
/*       */           getMethodFieldBuilder();
/*       */           getOptionsFieldBuilder();
/*       */         } 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         if (this.methodBuilder_ == null) {
/*       */           this.method_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */         } else {
/*       */           this.methodBuilder_.clear();
/*       */         } 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.ServiceDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.ServiceDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto build() {
/*       */         DescriptorProtos.ServiceDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.ServiceDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.ServiceDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceDescriptorProto buildPartial() {
/*       */         DescriptorProtos.ServiceDescriptorProto result = new DescriptorProtos.ServiceDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if (this.methodBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x2) == 2) {
/*       */             this.method_ = Collections.unmodifiableList(this.method_);
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */           } 
/*       */           result.method_ = this.method_;
/*       */         } else {
/*       */           result.method_ = this.methodBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x2; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.ServiceDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.ServiceDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.ServiceDescriptorProto other) {
/*       */         if (other == DescriptorProtos.ServiceDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (this.methodBuilder_ == null) {
/*       */           if (!other.method_.isEmpty()) {
/*       */             if (this.method_.isEmpty()) {
/*       */               this.method_ = other.method_;
/*       */               this.bitField0_ &= 0xFFFFFFFD;
/*       */             } else {
/*       */               ensureMethodIsMutable();
/*       */               this.method_.addAll(other.method_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.method_.isEmpty()) {
/*       */           if (this.methodBuilder_.isEmpty()) {
/*       */             this.methodBuilder_.dispose();
/*       */             this.methodBuilder_ = null;
/*       */             this.method_ = other.method_;
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */             this.methodBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getMethodFieldBuilder() : null;
/*       */           } else {
/*       */             this.methodBuilder_.addAllMessages(other.method_);
/*       */           } 
/*       */         } 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getMethodCount(); i++) {
/*       */           if (!getMethod(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.MethodDescriptorProto.Builder builder;
/*       */           DescriptorProtos.ServiceOptions.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               builder = DescriptorProtos.MethodDescriptorProto.newBuilder();
/*       */               input.readMessage(builder, extensionRegistry);
/*       */               addMethod(builder.buildPartial());
/*       */               break;
/*       */             case 26:
/*       */               subBuilder = DescriptorProtos.ServiceOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.ServiceDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureMethodIsMutable() {
/*       */         if ((this.bitField0_ & 0x2) != 2) {
/*       */           this.method_ = new ArrayList<DescriptorProtos.MethodDescriptorProto>(this.method_);
/*       */           this.bitField0_ |= 0x2;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.MethodDescriptorProto> getMethodList() {
/*       */         if (this.methodBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.method_); 
/*       */         return this.methodBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getMethodCount() {
/*       */         if (this.methodBuilder_ == null)
/*       */           return this.method_.size(); 
/*       */         return this.methodBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto getMethod(int index) {
/*       */         if (this.methodBuilder_ == null)
/*       */           return this.method_.get(index); 
/*       */         return this.methodBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setMethod(int index, DescriptorProtos.MethodDescriptorProto value) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMethodIsMutable();
/*       */           this.method_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setMethod(int index, DescriptorProtos.MethodDescriptorProto.Builder builderForValue) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           ensureMethodIsMutable();
/*       */           this.method_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMethod(DescriptorProtos.MethodDescriptorProto value) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMethodIsMutable();
/*       */           this.method_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMethod(int index, DescriptorProtos.MethodDescriptorProto value) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureMethodIsMutable();
/*       */           this.method_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMethod(DescriptorProtos.MethodDescriptorProto.Builder builderForValue) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           ensureMethodIsMutable();
/*       */           this.method_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addMethod(int index, DescriptorProtos.MethodDescriptorProto.Builder builderForValue) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           ensureMethodIsMutable();
/*       */           this.method_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllMethod(Iterable<? extends DescriptorProtos.MethodDescriptorProto> values) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           ensureMethodIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.method_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearMethod() {
/*       */         if (this.methodBuilder_ == null) {
/*       */           this.method_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeMethod(int index) {
/*       */         if (this.methodBuilder_ == null) {
/*       */           ensureMethodIsMutable();
/*       */           this.method_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.methodBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto.Builder getMethodBuilder(int index) {
/*       */         return getMethodFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int index) {
/*       */         if (this.methodBuilder_ == null)
/*       */           return this.method_.get(index); 
/*       */         return this.methodBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.MethodDescriptorProtoOrBuilder> getMethodOrBuilderList() {
/*       */         if (this.methodBuilder_ != null)
/*       */           return this.methodBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.method_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto.Builder addMethodBuilder() {
/*       */         return getMethodFieldBuilder().addBuilder(DescriptorProtos.MethodDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto.Builder addMethodBuilder(int index) {
/*       */         return getMethodFieldBuilder().addBuilder(index, DescriptorProtos.MethodDescriptorProto.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.MethodDescriptorProto.Builder> getMethodBuilderList() {
/*       */         return getMethodFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.MethodDescriptorProto, DescriptorProtos.MethodDescriptorProto.Builder, DescriptorProtos.MethodDescriptorProtoOrBuilder> getMethodFieldBuilder() {
/*       */         if (this.methodBuilder_ == null) {
/*       */           this.methodBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.MethodDescriptorProto, DescriptorProtos.MethodDescriptorProto.Builder, DescriptorProtos.MethodDescriptorProtoOrBuilder>(this.method_, ((this.bitField0_ & 0x2) == 2), getParentForChildren(), isClean());
/*       */           this.method_ = null;
/*       */         } 
/*       */         return this.methodBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  8241 */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptions getOptions() {
/*  8244 */         if (this.optionsBuilder_ == null)
/*  8245 */           return this.options_; 
/*  8247 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.ServiceOptions value) {
/*  8251 */         if (this.optionsBuilder_ == null) {
/*  8252 */           if (value == null)
/*  8253 */             throw new NullPointerException(); 
/*  8255 */           this.options_ = value;
/*  8256 */           onChanged();
/*       */         } else {
/*  8258 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  8260 */         this.bitField0_ |= 0x4;
/*  8261 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.ServiceOptions.Builder builderForValue) {
/*  8265 */         if (this.optionsBuilder_ == null) {
/*  8266 */           this.options_ = builderForValue.build();
/*  8267 */           onChanged();
/*       */         } else {
/*  8269 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  8271 */         this.bitField0_ |= 0x4;
/*  8272 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.ServiceOptions value) {
/*  8275 */         if (this.optionsBuilder_ == null) {
/*  8276 */           if ((this.bitField0_ & 0x4) == 4 && this.options_ != DescriptorProtos.ServiceOptions.getDefaultInstance()) {
/*  8278 */             this.options_ = DescriptorProtos.ServiceOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  8281 */             this.options_ = value;
/*       */           } 
/*  8283 */           onChanged();
/*       */         } else {
/*  8285 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  8287 */         this.bitField0_ |= 0x4;
/*  8288 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  8291 */         if (this.optionsBuilder_ == null) {
/*  8292 */           this.options_ = DescriptorProtos.ServiceOptions.getDefaultInstance();
/*  8293 */           onChanged();
/*       */         } else {
/*  8295 */           this.optionsBuilder_.clear();
/*       */         } 
/*  8297 */         this.bitField0_ &= 0xFFFFFFFB;
/*  8298 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptions.Builder getOptionsBuilder() {
/*  8301 */         this.bitField0_ |= 0x4;
/*  8302 */         onChanged();
/*  8303 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder() {
/*  8306 */         if (this.optionsBuilder_ != null)
/*  8307 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  8309 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.ServiceOptions, DescriptorProtos.ServiceOptions.Builder, DescriptorProtos.ServiceOptionsOrBuilder> getOptionsFieldBuilder() {
/*  8315 */         if (this.optionsBuilder_ == null) {
/*  8316 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.ServiceOptions, DescriptorProtos.ServiceOptions.Builder, DescriptorProtos.ServiceOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  8321 */           this.options_ = null;
/*       */         } 
/*  8323 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  8330 */     private static final ServiceDescriptorProto defaultInstance = new ServiceDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int METHOD_FIELD_NUMBER = 2;
/*       */     
/*       */     private List<DescriptorProtos.MethodDescriptorProto> method_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 3;
/*       */     
/*       */     private DescriptorProtos.ServiceOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  8331 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class MethodDescriptorProto extends GeneratedMessage implements MethodDescriptorProtoOrBuilder {
/*       */     private MethodDescriptorProto(Builder builder) {
/*  8362 */       super(builder);
/*  8501 */       this.memoizedIsInitialized = -1;
/*  8534 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private MethodDescriptorProto(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  8534 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public MethodDescriptorProto getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasName() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getName() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.name_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getNameBytes() {
/*       */       Object ref = this.name_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.name_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasInputType() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public String getInputType() {
/*       */       Object ref = this.inputType_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.inputType_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getInputTypeBytes() {
/*       */       Object ref = this.inputType_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.inputType_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasOutputType() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public String getOutputType() {
/*       */       Object ref = this.outputType_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.outputType_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getOutputTypeBytes() {
/*       */       Object ref = this.outputType_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.outputType_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasOptions() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MethodOptions getOptions() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     public DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder() {
/*       */       return this.options_;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = "";
/*       */       this.inputType_ = "";
/*       */       this.outputType_ = "";
/*       */       this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       if (hasOptions() && !getOptions().isInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getNameBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeBytes(2, getInputTypeBytes()); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeBytes(3, getOutputTypeBytes()); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeMessage(4, this.options_); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  8536 */       int size = this.memoizedSerializedSize;
/*  8537 */       if (size != -1)
/*  8537 */         return size; 
/*  8539 */       size = 0;
/*  8540 */       if ((this.bitField0_ & 0x1) == 1)
/*  8541 */         size += CodedOutputStream.computeBytesSize(1, getNameBytes()); 
/*  8544 */       if ((this.bitField0_ & 0x2) == 2)
/*  8545 */         size += CodedOutputStream.computeBytesSize(2, getInputTypeBytes()); 
/*  8548 */       if ((this.bitField0_ & 0x4) == 4)
/*  8549 */         size += CodedOutputStream.computeBytesSize(3, getOutputTypeBytes()); 
/*  8552 */       if ((this.bitField0_ & 0x8) == 8)
/*  8553 */         size += CodedOutputStream.computeMessageSize(4, this.options_); 
/*  8556 */       size += getUnknownFields().getSerializedSize();
/*  8557 */       this.memoizedSerializedSize = size;
/*  8558 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  8565 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  8571 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  8577 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  8582 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  8588 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(InputStream input) throws IOException {
/*  8593 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  8599 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseDelimitedFrom(InputStream input) throws IOException {
/*  8604 */       Builder builder = newBuilder();
/*  8605 */       if (builder.mergeDelimitedFrom(input))
/*  8606 */         return builder.buildParsed(); 
/*  8608 */       return null;
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  8615 */       Builder builder = newBuilder();
/*  8616 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  8617 */         return builder.buildParsed(); 
/*  8619 */       return null;
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(CodedInputStream input) throws IOException {
/*  8625 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodDescriptorProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  8631 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  8635 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  8636 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(MethodDescriptorProto prototype) {
/*  8638 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  8640 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  8645 */       Builder builder = new Builder(parent);
/*  8646 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.MethodDescriptorProtoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object name_;
/*       */       
/*       */       private Object inputType_;
/*       */       
/*       */       private Object outputType_;
/*       */       
/*       */       private DescriptorProtos.MethodOptions options_;
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.MethodOptions, DescriptorProtos.MethodOptions.Builder, DescriptorProtos.MethodOptionsOrBuilder> optionsBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  8653 */         return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  8658 */         return DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  8847 */         this.name_ = "";
/*  8883 */         this.inputType_ = "";
/*  8919 */         this.outputType_ = "";
/*  8955 */         this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = "";
/*       */         this.inputType_ = "";
/*       */         this.outputType_ = "";
/*  8955 */         this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getOptionsFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.name_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.inputType_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.outputType_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         if (this.optionsBuilder_ == null) {
/*       */           this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
/*       */         } else {
/*       */           this.optionsBuilder_.clear();
/*       */         } 
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.MethodDescriptorProto.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto getDefaultInstanceForType() {
/*       */         return DescriptorProtos.MethodDescriptorProto.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto build() {
/*       */         DescriptorProtos.MethodDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.MethodDescriptorProto buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.MethodDescriptorProto result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodDescriptorProto buildPartial() {
/*       */         DescriptorProtos.MethodDescriptorProto result = new DescriptorProtos.MethodDescriptorProto(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.name_ = this.name_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.inputType_ = this.inputType_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x4; 
/*       */         result.outputType_ = this.outputType_;
/*       */         if ((from_bitField0_ & 0x8) == 8)
/*       */           to_bitField0_ |= 0x8; 
/*       */         if (this.optionsBuilder_ == null) {
/*       */           result.options_ = this.options_;
/*       */         } else {
/*       */           result.options_ = this.optionsBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.MethodDescriptorProto)
/*       */           return mergeFrom((DescriptorProtos.MethodDescriptorProto)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.MethodDescriptorProto other) {
/*       */         if (other == DescriptorProtos.MethodDescriptorProto.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasName())
/*       */           setName(other.getName()); 
/*       */         if (other.hasInputType())
/*       */           setInputType(other.getInputType()); 
/*       */         if (other.hasOutputType())
/*       */           setOutputType(other.getOutputType()); 
/*       */         if (other.hasOptions())
/*       */           mergeOptions(other.getOptions()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         if (hasOptions() && !getOptions().isInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.MethodOptions.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.name_ = input.readBytes();
/*       */               break;
/*       */             case 18:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.inputType_ = input.readBytes();
/*       */               break;
/*       */             case 26:
/*       */               this.bitField0_ |= 0x4;
/*       */               this.outputType_ = input.readBytes();
/*       */               break;
/*       */             case 34:
/*       */               subBuilder = DescriptorProtos.MethodOptions.newBuilder();
/*       */               if (hasOptions())
/*       */                 subBuilder.mergeFrom(getOptions()); 
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               setOptions(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasName() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getName() {
/*       */         Object ref = this.name_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.name_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setName(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.name_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getName();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setName(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.name_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasInputType() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public String getInputType() {
/*       */         Object ref = this.inputType_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.inputType_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setInputType(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x2;
/*       */         this.inputType_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearInputType() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.inputType_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getInputType();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setInputType(ByteString value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.inputType_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasOutputType() {
/*       */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public String getOutputType() {
/*       */         Object ref = this.outputType_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.outputType_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setOutputType(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x4;
/*       */         this.outputType_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOutputType() {
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.outputType_ = DescriptorProtos.MethodDescriptorProto.getDefaultInstance().getOutputType();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setOutputType(ByteString value) {
/*       */         this.bitField0_ |= 0x4;
/*       */         this.outputType_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasOptions() {
/*  8959 */         return ((this.bitField0_ & 0x8) == 8);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptions getOptions() {
/*  8962 */         if (this.optionsBuilder_ == null)
/*  8963 */           return this.options_; 
/*  8965 */         return this.optionsBuilder_.getMessage();
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.MethodOptions value) {
/*  8969 */         if (this.optionsBuilder_ == null) {
/*  8970 */           if (value == null)
/*  8971 */             throw new NullPointerException(); 
/*  8973 */           this.options_ = value;
/*  8974 */           onChanged();
/*       */         } else {
/*  8976 */           this.optionsBuilder_.setMessage(value);
/*       */         } 
/*  8978 */         this.bitField0_ |= 0x8;
/*  8979 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setOptions(DescriptorProtos.MethodOptions.Builder builderForValue) {
/*  8983 */         if (this.optionsBuilder_ == null) {
/*  8984 */           this.options_ = builderForValue.build();
/*  8985 */           onChanged();
/*       */         } else {
/*  8987 */           this.optionsBuilder_.setMessage(builderForValue.build());
/*       */         } 
/*  8989 */         this.bitField0_ |= 0x8;
/*  8990 */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeOptions(DescriptorProtos.MethodOptions value) {
/*  8993 */         if (this.optionsBuilder_ == null) {
/*  8994 */           if ((this.bitField0_ & 0x8) == 8 && this.options_ != DescriptorProtos.MethodOptions.getDefaultInstance()) {
/*  8996 */             this.options_ = DescriptorProtos.MethodOptions.newBuilder(this.options_).mergeFrom(value).buildPartial();
/*       */           } else {
/*  8999 */             this.options_ = value;
/*       */           } 
/*  9001 */           onChanged();
/*       */         } else {
/*  9003 */           this.optionsBuilder_.mergeFrom(value);
/*       */         } 
/*  9005 */         this.bitField0_ |= 0x8;
/*  9006 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptions() {
/*  9009 */         if (this.optionsBuilder_ == null) {
/*  9010 */           this.options_ = DescriptorProtos.MethodOptions.getDefaultInstance();
/*  9011 */           onChanged();
/*       */         } else {
/*  9013 */           this.optionsBuilder_.clear();
/*       */         } 
/*  9015 */         this.bitField0_ &= 0xFFFFFFF7;
/*  9016 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptions.Builder getOptionsBuilder() {
/*  9019 */         this.bitField0_ |= 0x8;
/*  9020 */         onChanged();
/*  9021 */         return getOptionsFieldBuilder().getBuilder();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder() {
/*  9024 */         if (this.optionsBuilder_ != null)
/*  9025 */           return this.optionsBuilder_.getMessageOrBuilder(); 
/*  9027 */         return this.options_;
/*       */       }
/*       */       
/*       */       private SingleFieldBuilder<DescriptorProtos.MethodOptions, DescriptorProtos.MethodOptions.Builder, DescriptorProtos.MethodOptionsOrBuilder> getOptionsFieldBuilder() {
/*  9033 */         if (this.optionsBuilder_ == null) {
/*  9034 */           this.optionsBuilder_ = new SingleFieldBuilder<DescriptorProtos.MethodOptions, DescriptorProtos.MethodOptions.Builder, DescriptorProtos.MethodOptionsOrBuilder>(this.options_, getParentForChildren(), isClean());
/*  9039 */           this.options_ = null;
/*       */         } 
/*  9041 */         return this.optionsBuilder_;
/*       */       }
/*       */     }
/*       */     
/*  9048 */     private static final MethodDescriptorProto defaultInstance = new MethodDescriptorProto(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object name_;
/*       */     
/*       */     public static final int INPUT_TYPE_FIELD_NUMBER = 2;
/*       */     
/*       */     private Object inputType_;
/*       */     
/*       */     public static final int OUTPUT_TYPE_FIELD_NUMBER = 3;
/*       */     
/*       */     private Object outputType_;
/*       */     
/*       */     public static final int OPTIONS_FIELD_NUMBER = 4;
/*       */     
/*       */     private DescriptorProtos.MethodOptions options_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/*  9049 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class FileOptions extends GeneratedMessage.ExtendableMessage<FileOptions> implements FileOptionsOrBuilder {
/*       */     private FileOptions(Builder builder) {
/*  9106 */       super(builder);
/*  9358 */       this.memoizedIsInitialized = -1;
/*  9414 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private FileOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/*  9414 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static FileOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public FileOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public enum OptimizeMode implements ProtocolMessageEnum {
/*       */       SPEED(0, 1),
/*       */       CODE_SIZE(1, 2),
/*       */       LITE_RUNTIME(2, 3);
/*       */       
/*       */       public static final int SPEED_VALUE = 1;
/*       */       
/*       */       public static final int CODE_SIZE_VALUE = 2;
/*       */       
/*       */       public static final int LITE_RUNTIME_VALUE = 3;
/*       */       
/*       */       private static Internal.EnumLiteMap<OptimizeMode> internalValueMap = new Internal.EnumLiteMap<OptimizeMode>() {
/*       */           public DescriptorProtos.FileOptions.OptimizeMode findValueByNumber(int number) {
/*       */             return DescriptorProtos.FileOptions.OptimizeMode.valueOf(number);
/*       */           }
/*       */         };
/*       */       
/*       */       private static final OptimizeMode[] VALUES = new OptimizeMode[] { SPEED, CODE_SIZE, LITE_RUNTIME };
/*       */       
/*       */       private final int index;
/*       */       
/*       */       private final int value;
/*       */       
/*       */       public final int getNumber() {
/*       */         return this.value;
/*       */       }
/*       */       
/*       */       public static Internal.EnumLiteMap<OptimizeMode> internalGetValueMap() {
/*       */         return internalValueMap;
/*       */       }
/*       */       
/*       */       static {
/*       */       
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumValueDescriptor getValueDescriptor() {
/*       */         return getDescriptor().getValues().get(this.index);
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumDescriptor getDescriptorForType() {
/*       */         return getDescriptor();
/*       */       }
/*       */       
/*       */       public static final Descriptors.EnumDescriptor getDescriptor() {
/*       */         return DescriptorProtos.FileOptions.getDescriptor().getEnumTypes().get(0);
/*       */       }
/*       */       
/*       */       OptimizeMode(int index, int value) {
/*       */         this.index = index;
/*       */         this.value = value;
/*       */       }
/*       */     }
/*       */     
/*       */     public boolean hasJavaPackage() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getJavaPackage() {
/*       */       Object ref = this.javaPackage_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.javaPackage_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getJavaPackageBytes() {
/*       */       Object ref = this.javaPackage_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.javaPackage_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasJavaOuterClassname() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public String getJavaOuterClassname() {
/*       */       Object ref = this.javaOuterClassname_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.javaOuterClassname_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getJavaOuterClassnameBytes() {
/*       */       Object ref = this.javaOuterClassname_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.javaOuterClassname_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasJavaMultipleFiles() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public boolean getJavaMultipleFiles() {
/*       */       return this.javaMultipleFiles_;
/*       */     }
/*       */     
/*       */     public boolean hasJavaGenerateEqualsAndHash() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public boolean getJavaGenerateEqualsAndHash() {
/*       */       return this.javaGenerateEqualsAndHash_;
/*       */     }
/*       */     
/*       */     public boolean hasOptimizeFor() {
/*       */       return ((this.bitField0_ & 0x10) == 16);
/*       */     }
/*       */     
/*       */     public OptimizeMode getOptimizeFor() {
/*       */       return this.optimizeFor_;
/*       */     }
/*       */     
/*       */     public boolean hasCcGenericServices() {
/*       */       return ((this.bitField0_ & 0x20) == 32);
/*       */     }
/*       */     
/*       */     public boolean getCcGenericServices() {
/*       */       return this.ccGenericServices_;
/*       */     }
/*       */     
/*       */     public boolean hasJavaGenericServices() {
/*       */       return ((this.bitField0_ & 0x40) == 64);
/*       */     }
/*       */     
/*       */     public boolean getJavaGenericServices() {
/*       */       return this.javaGenericServices_;
/*       */     }
/*       */     
/*       */     public boolean hasPyGenericServices() {
/*       */       return ((this.bitField0_ & 0x80) == 128);
/*       */     }
/*       */     
/*       */     public boolean getPyGenericServices() {
/*       */       return this.pyGenericServices_;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.javaPackage_ = "";
/*       */       this.javaOuterClassname_ = "";
/*       */       this.javaMultipleFiles_ = false;
/*       */       this.javaGenerateEqualsAndHash_ = false;
/*       */       this.optimizeFor_ = OptimizeMode.SPEED;
/*       */       this.ccGenericServices_ = false;
/*       */       this.javaGenericServices_ = false;
/*       */       this.pyGenericServices_ = false;
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<FileOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(1, getJavaPackageBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeBytes(8, getJavaOuterClassnameBytes()); 
/*       */       if ((this.bitField0_ & 0x10) == 16)
/*       */         output.writeEnum(9, this.optimizeFor_.getNumber()); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeBool(10, this.javaMultipleFiles_); 
/*       */       if ((this.bitField0_ & 0x20) == 32)
/*       */         output.writeBool(16, this.ccGenericServices_); 
/*       */       if ((this.bitField0_ & 0x40) == 64)
/*       */         output.writeBool(17, this.javaGenericServices_); 
/*       */       if ((this.bitField0_ & 0x80) == 128)
/*       */         output.writeBool(18, this.pyGenericServices_); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeBool(20, this.javaGenerateEqualsAndHash_); 
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/*  9416 */       int size = this.memoizedSerializedSize;
/*  9417 */       if (size != -1)
/*  9417 */         return size; 
/*  9419 */       size = 0;
/*  9420 */       if ((this.bitField0_ & 0x1) == 1)
/*  9421 */         size += CodedOutputStream.computeBytesSize(1, getJavaPackageBytes()); 
/*  9424 */       if ((this.bitField0_ & 0x2) == 2)
/*  9425 */         size += CodedOutputStream.computeBytesSize(8, getJavaOuterClassnameBytes()); 
/*  9428 */       if ((this.bitField0_ & 0x10) == 16)
/*  9429 */         size += CodedOutputStream.computeEnumSize(9, this.optimizeFor_.getNumber()); 
/*  9432 */       if ((this.bitField0_ & 0x4) == 4)
/*  9433 */         size += CodedOutputStream.computeBoolSize(10, this.javaMultipleFiles_); 
/*  9436 */       if ((this.bitField0_ & 0x20) == 32)
/*  9437 */         size += CodedOutputStream.computeBoolSize(16, this.ccGenericServices_); 
/*  9440 */       if ((this.bitField0_ & 0x40) == 64)
/*  9441 */         size += CodedOutputStream.computeBoolSize(17, this.javaGenericServices_); 
/*  9444 */       if ((this.bitField0_ & 0x80) == 128)
/*  9445 */         size += CodedOutputStream.computeBoolSize(18, this.pyGenericServices_); 
/*  9448 */       if ((this.bitField0_ & 0x8) == 8)
/*  9449 */         size += CodedOutputStream.computeBoolSize(20, this.javaGenerateEqualsAndHash_); 
/*  9452 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*  9453 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/*  9456 */       size += extensionsSerializedSize();
/*  9457 */       size += getUnknownFields().getSerializedSize();
/*  9458 */       this.memoizedSerializedSize = size;
/*  9459 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/*  9466 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*  9472 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  9478 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*  9483 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*  9489 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(InputStream input) throws IOException {
/*  9494 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  9500 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseDelimitedFrom(InputStream input) throws IOException {
/*  9505 */       Builder builder = newBuilder();
/*  9506 */       if (builder.mergeDelimitedFrom(input))
/*  9507 */         return builder.buildParsed(); 
/*  9509 */       return null;
/*       */     }
/*       */     
/*       */     public static FileOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  9516 */       Builder builder = newBuilder();
/*  9517 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*  9518 */         return builder.buildParsed(); 
/*  9520 */       return null;
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(CodedInputStream input) throws IOException {
/*  9526 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FileOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*  9532 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/*  9536 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/*  9537 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(FileOptions prototype) {
/*  9539 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/*  9541 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*  9546 */       Builder builder = new Builder(parent);
/*  9547 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<FileOptions, Builder> implements DescriptorProtos.FileOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private Object javaPackage_;
/*       */       
/*       */       private Object javaOuterClassname_;
/*       */       
/*       */       private boolean javaMultipleFiles_;
/*       */       
/*       */       private boolean javaGenerateEqualsAndHash_;
/*       */       
/*       */       private DescriptorProtos.FileOptions.OptimizeMode optimizeFor_;
/*       */       
/*       */       private boolean ccGenericServices_;
/*       */       
/*       */       private boolean javaGenericServices_;
/*       */       
/*       */       private boolean pyGenericServices_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*  9554 */         return DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*  9559 */         return DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/*  9850 */         this.javaPackage_ = "";
/*  9886 */         this.javaOuterClassname_ = "";
/*  9964 */         this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
/* 10051 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.javaPackage_ = "";
/*       */         this.javaOuterClassname_ = "";
/*       */         this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
/* 10051 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.javaPackage_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.javaOuterClassname_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.javaMultipleFiles_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.javaGenerateEqualsAndHash_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.ccGenericServices_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.javaGenericServices_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         this.pyGenericServices_ = false;
/*       */         this.bitField0_ &= 0xFFFFFF7F;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFEFF;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.FileOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.FileOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions build() {
/*       */         DescriptorProtos.FileOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.FileOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.FileOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions buildPartial() {
/*       */         DescriptorProtos.FileOptions result = new DescriptorProtos.FileOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.javaPackage_ = this.javaPackage_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.javaOuterClassname_ = this.javaOuterClassname_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x4; 
/*       */         result.javaMultipleFiles_ = this.javaMultipleFiles_;
/*       */         if ((from_bitField0_ & 0x8) == 8)
/*       */           to_bitField0_ |= 0x8; 
/*       */         result.javaGenerateEqualsAndHash_ = this.javaGenerateEqualsAndHash_;
/*       */         if ((from_bitField0_ & 0x10) == 16)
/*       */           to_bitField0_ |= 0x10; 
/*       */         result.optimizeFor_ = this.optimizeFor_;
/*       */         if ((from_bitField0_ & 0x20) == 32)
/*       */           to_bitField0_ |= 0x20; 
/*       */         result.ccGenericServices_ = this.ccGenericServices_;
/*       */         if ((from_bitField0_ & 0x40) == 64)
/*       */           to_bitField0_ |= 0x40; 
/*       */         result.javaGenericServices_ = this.javaGenericServices_;
/*       */         if ((from_bitField0_ & 0x80) == 128)
/*       */           to_bitField0_ |= 0x80; 
/*       */         result.pyGenericServices_ = this.pyGenericServices_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x100) == 256) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFEFF;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.FileOptions)
/*       */           return mergeFrom((DescriptorProtos.FileOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.FileOptions other) {
/*       */         if (other == DescriptorProtos.FileOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasJavaPackage())
/*       */           setJavaPackage(other.getJavaPackage()); 
/*       */         if (other.hasJavaOuterClassname())
/*       */           setJavaOuterClassname(other.getJavaOuterClassname()); 
/*       */         if (other.hasJavaMultipleFiles())
/*       */           setJavaMultipleFiles(other.getJavaMultipleFiles()); 
/*       */         if (other.hasJavaGenerateEqualsAndHash())
/*       */           setJavaGenerateEqualsAndHash(other.getJavaGenerateEqualsAndHash()); 
/*       */         if (other.hasOptimizeFor())
/*       */           setOptimizeFor(other.getOptimizeFor()); 
/*       */         if (other.hasCcGenericServices())
/*       */           setCcGenericServices(other.getCcGenericServices()); 
/*       */         if (other.hasJavaGenericServices())
/*       */           setJavaGenericServices(other.getJavaGenericServices()); 
/*       */         if (other.hasPyGenericServices())
/*       */           setPyGenericServices(other.getPyGenericServices()); 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFEFF;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFEFF;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           int rawValue;
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           DescriptorProtos.FileOptions.OptimizeMode value;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.javaPackage_ = input.readBytes();
/*       */               break;
/*       */             case 66:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.javaOuterClassname_ = input.readBytes();
/*       */               break;
/*       */             case 72:
/*       */               rawValue = input.readEnum();
/*       */               value = DescriptorProtos.FileOptions.OptimizeMode.valueOf(rawValue);
/*       */               if (value == null) {
/*       */                 unknownFields.mergeVarintField(9, rawValue);
/*       */                 break;
/*       */               } 
/*       */               this.bitField0_ |= 0x10;
/*       */               this.optimizeFor_ = value;
/*       */               break;
/*       */             case 80:
/*       */               this.bitField0_ |= 0x4;
/*       */               this.javaMultipleFiles_ = input.readBool();
/*       */               break;
/*       */             case 128:
/*       */               this.bitField0_ |= 0x20;
/*       */               this.ccGenericServices_ = input.readBool();
/*       */               break;
/*       */             case 136:
/*       */               this.bitField0_ |= 0x40;
/*       */               this.javaGenericServices_ = input.readBool();
/*       */               break;
/*       */             case 144:
/*       */               this.bitField0_ |= 0x80;
/*       */               this.pyGenericServices_ = input.readBool();
/*       */               break;
/*       */             case 160:
/*       */               this.bitField0_ |= 0x8;
/*       */               this.javaGenerateEqualsAndHash_ = input.readBool();
/*       */               break;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasJavaPackage() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getJavaPackage() {
/*       */         Object ref = this.javaPackage_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.javaPackage_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setJavaPackage(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.javaPackage_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearJavaPackage() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.javaPackage_ = DescriptorProtos.FileOptions.getDefaultInstance().getJavaPackage();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setJavaPackage(ByteString value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.javaPackage_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasJavaOuterClassname() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public String getJavaOuterClassname() {
/*       */         Object ref = this.javaOuterClassname_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.javaOuterClassname_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setJavaOuterClassname(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x2;
/*       */         this.javaOuterClassname_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearJavaOuterClassname() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.javaOuterClassname_ = DescriptorProtos.FileOptions.getDefaultInstance().getJavaOuterClassname();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setJavaOuterClassname(ByteString value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.javaOuterClassname_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasJavaMultipleFiles() {
/*       */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public boolean getJavaMultipleFiles() {
/*       */         return this.javaMultipleFiles_;
/*       */       }
/*       */       
/*       */       public Builder setJavaMultipleFiles(boolean value) {
/*       */         this.bitField0_ |= 0x4;
/*       */         this.javaMultipleFiles_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearJavaMultipleFiles() {
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.javaMultipleFiles_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasJavaGenerateEqualsAndHash() {
/*       */         return ((this.bitField0_ & 0x8) == 8);
/*       */       }
/*       */       
/*       */       public boolean getJavaGenerateEqualsAndHash() {
/*       */         return this.javaGenerateEqualsAndHash_;
/*       */       }
/*       */       
/*       */       public Builder setJavaGenerateEqualsAndHash(boolean value) {
/*       */         this.bitField0_ |= 0x8;
/*       */         this.javaGenerateEqualsAndHash_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearJavaGenerateEqualsAndHash() {
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.javaGenerateEqualsAndHash_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasOptimizeFor() {
/*       */         return ((this.bitField0_ & 0x10) == 16);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FileOptions.OptimizeMode getOptimizeFor() {
/*       */         return this.optimizeFor_;
/*       */       }
/*       */       
/*       */       public Builder setOptimizeFor(DescriptorProtos.FileOptions.OptimizeMode value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x10;
/*       */         this.optimizeFor_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearOptimizeFor() {
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.optimizeFor_ = DescriptorProtos.FileOptions.OptimizeMode.SPEED;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasCcGenericServices() {
/*       */         return ((this.bitField0_ & 0x20) == 32);
/*       */       }
/*       */       
/*       */       public boolean getCcGenericServices() {
/*       */         return this.ccGenericServices_;
/*       */       }
/*       */       
/*       */       public Builder setCcGenericServices(boolean value) {
/*       */         this.bitField0_ |= 0x20;
/*       */         this.ccGenericServices_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearCcGenericServices() {
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.ccGenericServices_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasJavaGenericServices() {
/*       */         return ((this.bitField0_ & 0x40) == 64);
/*       */       }
/*       */       
/*       */       public boolean getJavaGenericServices() {
/*       */         return this.javaGenericServices_;
/*       */       }
/*       */       
/*       */       public Builder setJavaGenericServices(boolean value) {
/*       */         this.bitField0_ |= 0x40;
/*       */         this.javaGenericServices_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearJavaGenericServices() {
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         this.javaGenericServices_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasPyGenericServices() {
/*       */         return ((this.bitField0_ & 0x80) == 128);
/*       */       }
/*       */       
/*       */       public boolean getPyGenericServices() {
/*       */         return this.pyGenericServices_;
/*       */       }
/*       */       
/*       */       public Builder setPyGenericServices(boolean value) {
/*       */         this.bitField0_ |= 0x80;
/*       */         this.pyGenericServices_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearPyGenericServices() {
/*       */         this.bitField0_ &= 0xFFFFFF7F;
/*       */         this.pyGenericServices_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 10054 */         if ((this.bitField0_ & 0x100) != 256) {
/* 10055 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 10056 */           this.bitField0_ |= 0x100;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 10064 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10065 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 10067 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 10071 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10072 */           return this.uninterpretedOption_.size(); 
/* 10074 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 10078 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10079 */           return this.uninterpretedOption_.get(index); 
/* 10081 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 10086 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10087 */           if (value == null)
/* 10088 */             throw new NullPointerException(); 
/* 10090 */           ensureUninterpretedOptionIsMutable();
/* 10091 */           this.uninterpretedOption_.set(index, value);
/* 10092 */           onChanged();
/*       */         } else {
/* 10094 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 10096 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10100 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10101 */           ensureUninterpretedOptionIsMutable();
/* 10102 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 10103 */           onChanged();
/*       */         } else {
/* 10105 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 10107 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 10110 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10111 */           if (value == null)
/* 10112 */             throw new NullPointerException(); 
/* 10114 */           ensureUninterpretedOptionIsMutable();
/* 10115 */           this.uninterpretedOption_.add(value);
/* 10116 */           onChanged();
/*       */         } else {
/* 10118 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 10120 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 10124 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10125 */           if (value == null)
/* 10126 */             throw new NullPointerException(); 
/* 10128 */           ensureUninterpretedOptionIsMutable();
/* 10129 */           this.uninterpretedOption_.add(index, value);
/* 10130 */           onChanged();
/*       */         } else {
/* 10132 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 10134 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10138 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10139 */           ensureUninterpretedOptionIsMutable();
/* 10140 */           this.uninterpretedOption_.add(builderForValue.build());
/* 10141 */           onChanged();
/*       */         } else {
/* 10143 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 10145 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10149 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10150 */           ensureUninterpretedOptionIsMutable();
/* 10151 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 10152 */           onChanged();
/*       */         } else {
/* 10154 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 10156 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 10160 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10161 */           ensureUninterpretedOptionIsMutable();
/* 10162 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 10163 */           onChanged();
/*       */         } else {
/* 10165 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 10167 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 10170 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10171 */           this.uninterpretedOption_ = Collections.emptyList();
/* 10172 */           this.bitField0_ &= 0xFFFFFEFF;
/* 10173 */           onChanged();
/*       */         } else {
/* 10175 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 10177 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 10180 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10181 */           ensureUninterpretedOptionIsMutable();
/* 10182 */           this.uninterpretedOption_.remove(index);
/* 10183 */           onChanged();
/*       */         } else {
/* 10185 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 10187 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 10191 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 10195 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10196 */           return this.uninterpretedOption_.get(index); 
/* 10197 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 10202 */         if (this.uninterpretedOptionBuilder_ != null)
/* 10203 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 10205 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 10209 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 10214 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 10219 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 10224 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10225 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x100) == 256), getParentForChildren(), isClean());
/* 10231 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 10233 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 10240 */     private static final FileOptions defaultInstance = new FileOptions(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int JAVA_PACKAGE_FIELD_NUMBER = 1;
/*       */     
/*       */     private Object javaPackage_;
/*       */     
/*       */     public static final int JAVA_OUTER_CLASSNAME_FIELD_NUMBER = 8;
/*       */     
/*       */     private Object javaOuterClassname_;
/*       */     
/*       */     public static final int JAVA_MULTIPLE_FILES_FIELD_NUMBER = 10;
/*       */     
/*       */     private boolean javaMultipleFiles_;
/*       */     
/*       */     public static final int JAVA_GENERATE_EQUALS_AND_HASH_FIELD_NUMBER = 20;
/*       */     
/*       */     private boolean javaGenerateEqualsAndHash_;
/*       */     
/*       */     public static final int OPTIMIZE_FOR_FIELD_NUMBER = 9;
/*       */     
/*       */     private OptimizeMode optimizeFor_;
/*       */     
/*       */     public static final int CC_GENERIC_SERVICES_FIELD_NUMBER = 16;
/*       */     
/*       */     private boolean ccGenericServices_;
/*       */     
/*       */     public static final int JAVA_GENERIC_SERVICES_FIELD_NUMBER = 17;
/*       */     
/*       */     private boolean javaGenericServices_;
/*       */     
/*       */     public static final int PY_GENERIC_SERVICES_FIELD_NUMBER = 18;
/*       */     
/*       */     private boolean pyGenericServices_;
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 10241 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class MessageOptions extends GeneratedMessage.ExtendableMessage<MessageOptions> implements MessageOptionsOrBuilder {
/*       */     private MessageOptions(Builder builder) {
/* 10274 */       super(builder);
/* 10344 */       this.memoizedIsInitialized = -1;
/* 10382 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private MessageOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 10382 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static MessageOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public MessageOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public boolean hasMessageSetWireFormat() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public boolean getMessageSetWireFormat() {
/*       */       return this.messageSetWireFormat_;
/*       */     }
/*       */     
/*       */     public boolean hasNoStandardDescriptorAccessor() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public boolean getNoStandardDescriptorAccessor() {
/*       */       return this.noStandardDescriptorAccessor_;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.messageSetWireFormat_ = false;
/*       */       this.noStandardDescriptorAccessor_ = false;
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<MessageOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBool(1, this.messageSetWireFormat_); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeBool(2, this.noStandardDescriptorAccessor_); 
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 10384 */       int size = this.memoizedSerializedSize;
/* 10385 */       if (size != -1)
/* 10385 */         return size; 
/* 10387 */       size = 0;
/* 10388 */       if ((this.bitField0_ & 0x1) == 1)
/* 10389 */         size += CodedOutputStream.computeBoolSize(1, this.messageSetWireFormat_); 
/* 10392 */       if ((this.bitField0_ & 0x2) == 2)
/* 10393 */         size += CodedOutputStream.computeBoolSize(2, this.noStandardDescriptorAccessor_); 
/* 10396 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 10397 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 10400 */       size += extensionsSerializedSize();
/* 10401 */       size += getUnknownFields().getSerializedSize();
/* 10402 */       this.memoizedSerializedSize = size;
/* 10403 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 10410 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 10416 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 10422 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 10427 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 10433 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(InputStream input) throws IOException {
/* 10438 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 10444 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 10449 */       Builder builder = newBuilder();
/* 10450 */       if (builder.mergeDelimitedFrom(input))
/* 10451 */         return builder.buildParsed(); 
/* 10453 */       return null;
/*       */     }
/*       */     
/*       */     public static MessageOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 10460 */       Builder builder = newBuilder();
/* 10461 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 10462 */         return builder.buildParsed(); 
/* 10464 */       return null;
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(CodedInputStream input) throws IOException {
/* 10470 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MessageOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 10476 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 10480 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 10481 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(MessageOptions prototype) {
/* 10483 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 10485 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 10490 */       Builder builder = new Builder(parent);
/* 10491 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<MessageOptions, Builder> implements DescriptorProtos.MessageOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private boolean messageSetWireFormat_;
/*       */       
/*       */       private boolean noStandardDescriptorAccessor_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 10498 */         return DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 10503 */         return DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 10746 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 10746 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.messageSetWireFormat_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.noStandardDescriptorAccessor_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFB;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.MessageOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.MessageOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptions build() {
/*       */         DescriptorProtos.MessageOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.MessageOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.MessageOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MessageOptions buildPartial() {
/*       */         DescriptorProtos.MessageOptions result = new DescriptorProtos.MessageOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.messageSetWireFormat_ = this.messageSetWireFormat_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.noStandardDescriptorAccessor_ = this.noStandardDescriptorAccessor_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x4) == 4) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFFB;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.MessageOptions)
/*       */           return mergeFrom((DescriptorProtos.MessageOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.MessageOptions other) {
/*       */         if (other == DescriptorProtos.MessageOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasMessageSetWireFormat())
/*       */           setMessageSetWireFormat(other.getMessageSetWireFormat()); 
/*       */         if (other.hasNoStandardDescriptorAccessor())
/*       */           setNoStandardDescriptorAccessor(other.getNoStandardDescriptorAccessor()); 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFFB;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFFB;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 8:
/*       */               this.bitField0_ |= 0x1;
/*       */               this.messageSetWireFormat_ = input.readBool();
/*       */               break;
/*       */             case 16:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.noStandardDescriptorAccessor_ = input.readBool();
/*       */               break;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasMessageSetWireFormat() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public boolean getMessageSetWireFormat() {
/*       */         return this.messageSetWireFormat_;
/*       */       }
/*       */       
/*       */       public Builder setMessageSetWireFormat(boolean value) {
/*       */         this.bitField0_ |= 0x1;
/*       */         this.messageSetWireFormat_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearMessageSetWireFormat() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.messageSetWireFormat_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasNoStandardDescriptorAccessor() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public boolean getNoStandardDescriptorAccessor() {
/*       */         return this.noStandardDescriptorAccessor_;
/*       */       }
/*       */       
/*       */       public Builder setNoStandardDescriptorAccessor(boolean value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.noStandardDescriptorAccessor_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearNoStandardDescriptorAccessor() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.noStandardDescriptorAccessor_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 10749 */         if ((this.bitField0_ & 0x4) != 4) {
/* 10750 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 10751 */           this.bitField0_ |= 0x4;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 10759 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10760 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 10762 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 10766 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10767 */           return this.uninterpretedOption_.size(); 
/* 10769 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 10773 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10774 */           return this.uninterpretedOption_.get(index); 
/* 10776 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 10781 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10782 */           if (value == null)
/* 10783 */             throw new NullPointerException(); 
/* 10785 */           ensureUninterpretedOptionIsMutable();
/* 10786 */           this.uninterpretedOption_.set(index, value);
/* 10787 */           onChanged();
/*       */         } else {
/* 10789 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 10791 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10795 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10796 */           ensureUninterpretedOptionIsMutable();
/* 10797 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 10798 */           onChanged();
/*       */         } else {
/* 10800 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 10802 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 10805 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10806 */           if (value == null)
/* 10807 */             throw new NullPointerException(); 
/* 10809 */           ensureUninterpretedOptionIsMutable();
/* 10810 */           this.uninterpretedOption_.add(value);
/* 10811 */           onChanged();
/*       */         } else {
/* 10813 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 10815 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 10819 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10820 */           if (value == null)
/* 10821 */             throw new NullPointerException(); 
/* 10823 */           ensureUninterpretedOptionIsMutable();
/* 10824 */           this.uninterpretedOption_.add(index, value);
/* 10825 */           onChanged();
/*       */         } else {
/* 10827 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 10829 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10833 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10834 */           ensureUninterpretedOptionIsMutable();
/* 10835 */           this.uninterpretedOption_.add(builderForValue.build());
/* 10836 */           onChanged();
/*       */         } else {
/* 10838 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 10840 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 10844 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10845 */           ensureUninterpretedOptionIsMutable();
/* 10846 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 10847 */           onChanged();
/*       */         } else {
/* 10849 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 10851 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 10855 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10856 */           ensureUninterpretedOptionIsMutable();
/* 10857 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 10858 */           onChanged();
/*       */         } else {
/* 10860 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 10862 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 10865 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10866 */           this.uninterpretedOption_ = Collections.emptyList();
/* 10867 */           this.bitField0_ &= 0xFFFFFFFB;
/* 10868 */           onChanged();
/*       */         } else {
/* 10870 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 10872 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 10875 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10876 */           ensureUninterpretedOptionIsMutable();
/* 10877 */           this.uninterpretedOption_.remove(index);
/* 10878 */           onChanged();
/*       */         } else {
/* 10880 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 10882 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 10886 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 10890 */         if (this.uninterpretedOptionBuilder_ == null)
/* 10891 */           return this.uninterpretedOption_.get(index); 
/* 10892 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 10897 */         if (this.uninterpretedOptionBuilder_ != null)
/* 10898 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 10900 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 10904 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 10909 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 10914 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 10919 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 10920 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x4) == 4), getParentForChildren(), isClean());
/* 10926 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 10928 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 10935 */     private static final MessageOptions defaultInstance = new MessageOptions(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int MESSAGE_SET_WIRE_FORMAT_FIELD_NUMBER = 1;
/*       */     
/*       */     private boolean messageSetWireFormat_;
/*       */     
/*       */     public static final int NO_STANDARD_DESCRIPTOR_ACCESSOR_FIELD_NUMBER = 2;
/*       */     
/*       */     private boolean noStandardDescriptorAccessor_;
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 10936 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class FieldOptions extends GeneratedMessage.ExtendableMessage<FieldOptions> implements FieldOptionsOrBuilder {
/*       */     private FieldOptions(Builder builder) {
/* 10977 */       super(builder);
/* 11163 */       this.memoizedIsInitialized = -1;
/* 11207 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private FieldOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 11207 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static FieldOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public FieldOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public enum CType implements ProtocolMessageEnum {
/*       */       STRING(0, 0),
/*       */       CORD(1, 1),
/*       */       STRING_PIECE(2, 2);
/*       */       
/*       */       public static final int STRING_VALUE = 0;
/*       */       
/*       */       public static final int CORD_VALUE = 1;
/*       */       
/*       */       public static final int STRING_PIECE_VALUE = 2;
/*       */       
/*       */       private static Internal.EnumLiteMap<CType> internalValueMap = new Internal.EnumLiteMap<CType>() {
/*       */           public DescriptorProtos.FieldOptions.CType findValueByNumber(int number) {
/*       */             return DescriptorProtos.FieldOptions.CType.valueOf(number);
/*       */           }
/*       */         };
/*       */       
/*       */       private static final CType[] VALUES = new CType[] { STRING, CORD, STRING_PIECE };
/*       */       
/*       */       private final int index;
/*       */       
/*       */       private final int value;
/*       */       
/*       */       public final int getNumber() {
/*       */         return this.value;
/*       */       }
/*       */       
/*       */       public static Internal.EnumLiteMap<CType> internalGetValueMap() {
/*       */         return internalValueMap;
/*       */       }
/*       */       
/*       */       static {
/*       */       
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumValueDescriptor getValueDescriptor() {
/*       */         return getDescriptor().getValues().get(this.index);
/*       */       }
/*       */       
/*       */       public final Descriptors.EnumDescriptor getDescriptorForType() {
/*       */         return getDescriptor();
/*       */       }
/*       */       
/*       */       public static final Descriptors.EnumDescriptor getDescriptor() {
/*       */         return DescriptorProtos.FieldOptions.getDescriptor().getEnumTypes().get(0);
/*       */       }
/*       */       
/*       */       CType(int index, int value) {
/*       */         this.index = index;
/*       */         this.value = value;
/*       */       }
/*       */     }
/*       */     
/*       */     public boolean hasCtype() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public CType getCtype() {
/*       */       return this.ctype_;
/*       */     }
/*       */     
/*       */     public boolean hasPacked() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public boolean getPacked() {
/*       */       return this.packed_;
/*       */     }
/*       */     
/*       */     public boolean hasDeprecated() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public boolean getDeprecated() {
/*       */       return this.deprecated_;
/*       */     }
/*       */     
/*       */     public boolean hasExperimentalMapKey() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public String getExperimentalMapKey() {
/*       */       Object ref = this.experimentalMapKey_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.experimentalMapKey_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getExperimentalMapKeyBytes() {
/*       */       Object ref = this.experimentalMapKey_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.experimentalMapKey_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.ctype_ = CType.STRING;
/*       */       this.packed_ = false;
/*       */       this.deprecated_ = false;
/*       */       this.experimentalMapKey_ = "";
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<FieldOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeEnum(1, this.ctype_.getNumber()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeBool(2, this.packed_); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeBool(3, this.deprecated_); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeBytes(9, getExperimentalMapKeyBytes()); 
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 11209 */       int size = this.memoizedSerializedSize;
/* 11210 */       if (size != -1)
/* 11210 */         return size; 
/* 11212 */       size = 0;
/* 11213 */       if ((this.bitField0_ & 0x1) == 1)
/* 11214 */         size += CodedOutputStream.computeEnumSize(1, this.ctype_.getNumber()); 
/* 11217 */       if ((this.bitField0_ & 0x2) == 2)
/* 11218 */         size += CodedOutputStream.computeBoolSize(2, this.packed_); 
/* 11221 */       if ((this.bitField0_ & 0x4) == 4)
/* 11222 */         size += CodedOutputStream.computeBoolSize(3, this.deprecated_); 
/* 11225 */       if ((this.bitField0_ & 0x8) == 8)
/* 11226 */         size += CodedOutputStream.computeBytesSize(9, getExperimentalMapKeyBytes()); 
/* 11229 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 11230 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 11233 */       size += extensionsSerializedSize();
/* 11234 */       size += getUnknownFields().getSerializedSize();
/* 11235 */       this.memoizedSerializedSize = size;
/* 11236 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 11243 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 11249 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 11255 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 11260 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 11266 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(InputStream input) throws IOException {
/* 11271 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 11277 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 11282 */       Builder builder = newBuilder();
/* 11283 */       if (builder.mergeDelimitedFrom(input))
/* 11284 */         return builder.buildParsed(); 
/* 11286 */       return null;
/*       */     }
/*       */     
/*       */     public static FieldOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 11293 */       Builder builder = newBuilder();
/* 11294 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 11295 */         return builder.buildParsed(); 
/* 11297 */       return null;
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(CodedInputStream input) throws IOException {
/* 11303 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static FieldOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 11309 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 11313 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 11314 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(FieldOptions prototype) {
/* 11316 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 11318 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 11323 */       Builder builder = new Builder(parent);
/* 11324 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<FieldOptions, Builder> implements DescriptorProtos.FieldOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private DescriptorProtos.FieldOptions.CType ctype_;
/*       */       
/*       */       private boolean packed_;
/*       */       
/*       */       private boolean deprecated_;
/*       */       
/*       */       private Object experimentalMapKey_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 11331 */         return DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 11336 */         return DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 11571 */         this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
/* 11637 */         this.experimentalMapKey_ = "";
/* 11673 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
/*       */         this.experimentalMapKey_ = "";
/* 11673 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.packed_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.deprecated_ = false;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.experimentalMapKey_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFEF;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.FieldOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.FieldOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions build() {
/*       */         DescriptorProtos.FieldOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.FieldOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.FieldOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions buildPartial() {
/*       */         DescriptorProtos.FieldOptions result = new DescriptorProtos.FieldOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if ((from_bitField0_ & 0x1) == 1)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.ctype_ = this.ctype_;
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.packed_ = this.packed_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x4; 
/*       */         result.deprecated_ = this.deprecated_;
/*       */         if ((from_bitField0_ & 0x8) == 8)
/*       */           to_bitField0_ |= 0x8; 
/*       */         result.experimentalMapKey_ = this.experimentalMapKey_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x10) == 16) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.FieldOptions)
/*       */           return mergeFrom((DescriptorProtos.FieldOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.FieldOptions other) {
/*       */         if (other == DescriptorProtos.FieldOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (other.hasCtype())
/*       */           setCtype(other.getCtype()); 
/*       */         if (other.hasPacked())
/*       */           setPacked(other.getPacked()); 
/*       */         if (other.hasDeprecated())
/*       */           setDeprecated(other.getDeprecated()); 
/*       */         if (other.hasExperimentalMapKey())
/*       */           setExperimentalMapKey(other.getExperimentalMapKey()); 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFEF;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFEF;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           int rawValue;
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           DescriptorProtos.FieldOptions.CType value;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 8:
/*       */               rawValue = input.readEnum();
/*       */               value = DescriptorProtos.FieldOptions.CType.valueOf(rawValue);
/*       */               if (value == null) {
/*       */                 unknownFields.mergeVarintField(1, rawValue);
/*       */                 break;
/*       */               } 
/*       */               this.bitField0_ |= 0x1;
/*       */               this.ctype_ = value;
/*       */               break;
/*       */             case 16:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.packed_ = input.readBool();
/*       */               break;
/*       */             case 24:
/*       */               this.bitField0_ |= 0x4;
/*       */               this.deprecated_ = input.readBool();
/*       */               break;
/*       */             case 74:
/*       */               this.bitField0_ |= 0x8;
/*       */               this.experimentalMapKey_ = input.readBytes();
/*       */               break;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       public boolean hasCtype() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.FieldOptions.CType getCtype() {
/*       */         return this.ctype_;
/*       */       }
/*       */       
/*       */       public Builder setCtype(DescriptorProtos.FieldOptions.CType value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x1;
/*       */         this.ctype_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearCtype() {
/*       */         this.bitField0_ &= 0xFFFFFFFE;
/*       */         this.ctype_ = DescriptorProtos.FieldOptions.CType.STRING;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasPacked() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public boolean getPacked() {
/*       */         return this.packed_;
/*       */       }
/*       */       
/*       */       public Builder setPacked(boolean value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.packed_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearPacked() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.packed_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasDeprecated() {
/*       */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public boolean getDeprecated() {
/*       */         return this.deprecated_;
/*       */       }
/*       */       
/*       */       public Builder setDeprecated(boolean value) {
/*       */         this.bitField0_ |= 0x4;
/*       */         this.deprecated_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearDeprecated() {
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.deprecated_ = false;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasExperimentalMapKey() {
/*       */         return ((this.bitField0_ & 0x8) == 8);
/*       */       }
/*       */       
/*       */       public String getExperimentalMapKey() {
/*       */         Object ref = this.experimentalMapKey_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.experimentalMapKey_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setExperimentalMapKey(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x8;
/*       */         this.experimentalMapKey_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearExperimentalMapKey() {
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.experimentalMapKey_ = DescriptorProtos.FieldOptions.getDefaultInstance().getExperimentalMapKey();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setExperimentalMapKey(ByteString value) {
/*       */         this.bitField0_ |= 0x8;
/*       */         this.experimentalMapKey_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 11676 */         if ((this.bitField0_ & 0x10) != 16) {
/* 11677 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 11678 */           this.bitField0_ |= 0x10;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 11686 */         if (this.uninterpretedOptionBuilder_ == null)
/* 11687 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 11689 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 11693 */         if (this.uninterpretedOptionBuilder_ == null)
/* 11694 */           return this.uninterpretedOption_.size(); 
/* 11696 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 11700 */         if (this.uninterpretedOptionBuilder_ == null)
/* 11701 */           return this.uninterpretedOption_.get(index); 
/* 11703 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 11708 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11709 */           if (value == null)
/* 11710 */             throw new NullPointerException(); 
/* 11712 */           ensureUninterpretedOptionIsMutable();
/* 11713 */           this.uninterpretedOption_.set(index, value);
/* 11714 */           onChanged();
/*       */         } else {
/* 11716 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 11718 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 11722 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11723 */           ensureUninterpretedOptionIsMutable();
/* 11724 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 11725 */           onChanged();
/*       */         } else {
/* 11727 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 11729 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 11732 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11733 */           if (value == null)
/* 11734 */             throw new NullPointerException(); 
/* 11736 */           ensureUninterpretedOptionIsMutable();
/* 11737 */           this.uninterpretedOption_.add(value);
/* 11738 */           onChanged();
/*       */         } else {
/* 11740 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 11742 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 11746 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11747 */           if (value == null)
/* 11748 */             throw new NullPointerException(); 
/* 11750 */           ensureUninterpretedOptionIsMutable();
/* 11751 */           this.uninterpretedOption_.add(index, value);
/* 11752 */           onChanged();
/*       */         } else {
/* 11754 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 11756 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 11760 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11761 */           ensureUninterpretedOptionIsMutable();
/* 11762 */           this.uninterpretedOption_.add(builderForValue.build());
/* 11763 */           onChanged();
/*       */         } else {
/* 11765 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 11767 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 11771 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11772 */           ensureUninterpretedOptionIsMutable();
/* 11773 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 11774 */           onChanged();
/*       */         } else {
/* 11776 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 11778 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 11782 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11783 */           ensureUninterpretedOptionIsMutable();
/* 11784 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 11785 */           onChanged();
/*       */         } else {
/* 11787 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 11789 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 11792 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11793 */           this.uninterpretedOption_ = Collections.emptyList();
/* 11794 */           this.bitField0_ &= 0xFFFFFFEF;
/* 11795 */           onChanged();
/*       */         } else {
/* 11797 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 11799 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 11802 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11803 */           ensureUninterpretedOptionIsMutable();
/* 11804 */           this.uninterpretedOption_.remove(index);
/* 11805 */           onChanged();
/*       */         } else {
/* 11807 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 11809 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 11813 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 11817 */         if (this.uninterpretedOptionBuilder_ == null)
/* 11818 */           return this.uninterpretedOption_.get(index); 
/* 11819 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 11824 */         if (this.uninterpretedOptionBuilder_ != null)
/* 11825 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 11827 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 11831 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 11836 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 11841 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 11846 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 11847 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x10) == 16), getParentForChildren(), isClean());
/* 11853 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 11855 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 11862 */     private static final FieldOptions defaultInstance = new FieldOptions(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int CTYPE_FIELD_NUMBER = 1;
/*       */     
/*       */     private CType ctype_;
/*       */     
/*       */     public static final int PACKED_FIELD_NUMBER = 2;
/*       */     
/*       */     private boolean packed_;
/*       */     
/*       */     public static final int DEPRECATED_FIELD_NUMBER = 3;
/*       */     
/*       */     private boolean deprecated_;
/*       */     
/*       */     public static final int EXPERIMENTAL_MAP_KEY_FIELD_NUMBER = 9;
/*       */     
/*       */     private Object experimentalMapKey_;
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 11863 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class EnumOptions extends GeneratedMessage.ExtendableMessage<EnumOptions> implements EnumOptionsOrBuilder {
/*       */     private EnumOptions(Builder builder) {
/* 11888 */       super(builder);
/* 11935 */       this.memoizedIsInitialized = -1;
/* 11967 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private EnumOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 11967 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static EnumOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public EnumOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<EnumOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 11969 */       int size = this.memoizedSerializedSize;
/* 11970 */       if (size != -1)
/* 11970 */         return size; 
/* 11972 */       size = 0;
/* 11973 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 11974 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 11977 */       size += extensionsSerializedSize();
/* 11978 */       size += getUnknownFields().getSerializedSize();
/* 11979 */       this.memoizedSerializedSize = size;
/* 11980 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 11987 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 11993 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 11999 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 12004 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 12010 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(InputStream input) throws IOException {
/* 12015 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12021 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 12026 */       Builder builder = newBuilder();
/* 12027 */       if (builder.mergeDelimitedFrom(input))
/* 12028 */         return builder.buildParsed(); 
/* 12030 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12037 */       Builder builder = newBuilder();
/* 12038 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 12039 */         return builder.buildParsed(); 
/* 12041 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(CodedInputStream input) throws IOException {
/* 12047 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12053 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 12057 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 12058 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(EnumOptions prototype) {
/* 12060 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 12062 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 12067 */       Builder builder = new Builder(parent);
/* 12068 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<EnumOptions, Builder> implements DescriptorProtos.EnumOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 12075 */         return DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 12080 */         return DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 12251 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 12251 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.EnumOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.EnumOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptions build() {
/*       */         DescriptorProtos.EnumOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.EnumOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.EnumOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumOptions buildPartial() {
/*       */         DescriptorProtos.EnumOptions result = new DescriptorProtos.EnumOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.EnumOptions)
/*       */           return mergeFrom((DescriptorProtos.EnumOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.EnumOptions other) {
/*       */         if (other == DescriptorProtos.EnumOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 12254 */         if ((this.bitField0_ & 0x1) != 1) {
/* 12255 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 12256 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 12264 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12265 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 12267 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 12271 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12272 */           return this.uninterpretedOption_.size(); 
/* 12274 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 12278 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12279 */           return this.uninterpretedOption_.get(index); 
/* 12281 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 12286 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12287 */           if (value == null)
/* 12288 */             throw new NullPointerException(); 
/* 12290 */           ensureUninterpretedOptionIsMutable();
/* 12291 */           this.uninterpretedOption_.set(index, value);
/* 12292 */           onChanged();
/*       */         } else {
/* 12294 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 12296 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12300 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12301 */           ensureUninterpretedOptionIsMutable();
/* 12302 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 12303 */           onChanged();
/*       */         } else {
/* 12305 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 12307 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 12310 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12311 */           if (value == null)
/* 12312 */             throw new NullPointerException(); 
/* 12314 */           ensureUninterpretedOptionIsMutable();
/* 12315 */           this.uninterpretedOption_.add(value);
/* 12316 */           onChanged();
/*       */         } else {
/* 12318 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 12320 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 12324 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12325 */           if (value == null)
/* 12326 */             throw new NullPointerException(); 
/* 12328 */           ensureUninterpretedOptionIsMutable();
/* 12329 */           this.uninterpretedOption_.add(index, value);
/* 12330 */           onChanged();
/*       */         } else {
/* 12332 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 12334 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12338 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12339 */           ensureUninterpretedOptionIsMutable();
/* 12340 */           this.uninterpretedOption_.add(builderForValue.build());
/* 12341 */           onChanged();
/*       */         } else {
/* 12343 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 12345 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12349 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12350 */           ensureUninterpretedOptionIsMutable();
/* 12351 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 12352 */           onChanged();
/*       */         } else {
/* 12354 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 12356 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 12360 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12361 */           ensureUninterpretedOptionIsMutable();
/* 12362 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 12363 */           onChanged();
/*       */         } else {
/* 12365 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 12367 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 12370 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12371 */           this.uninterpretedOption_ = Collections.emptyList();
/* 12372 */           this.bitField0_ &= 0xFFFFFFFE;
/* 12373 */           onChanged();
/*       */         } else {
/* 12375 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 12377 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 12380 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12381 */           ensureUninterpretedOptionIsMutable();
/* 12382 */           this.uninterpretedOption_.remove(index);
/* 12383 */           onChanged();
/*       */         } else {
/* 12385 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 12387 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 12391 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 12395 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12396 */           return this.uninterpretedOption_.get(index); 
/* 12397 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 12402 */         if (this.uninterpretedOptionBuilder_ != null)
/* 12403 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 12405 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 12409 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 12414 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 12419 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 12424 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12425 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/* 12431 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 12433 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 12440 */     private static final EnumOptions defaultInstance = new EnumOptions(true);
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 12441 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class EnumValueOptions extends GeneratedMessage.ExtendableMessage<EnumValueOptions> implements EnumValueOptionsOrBuilder {
/*       */     private EnumValueOptions(Builder builder) {
/* 12466 */       super(builder);
/* 12513 */       this.memoizedIsInitialized = -1;
/* 12545 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private EnumValueOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 12545 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static EnumValueOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public EnumValueOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<EnumValueOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 12547 */       int size = this.memoizedSerializedSize;
/* 12548 */       if (size != -1)
/* 12548 */         return size; 
/* 12550 */       size = 0;
/* 12551 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 12552 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 12555 */       size += extensionsSerializedSize();
/* 12556 */       size += getUnknownFields().getSerializedSize();
/* 12557 */       this.memoizedSerializedSize = size;
/* 12558 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 12565 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 12571 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 12577 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 12582 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 12588 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(InputStream input) throws IOException {
/* 12593 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12599 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 12604 */       Builder builder = newBuilder();
/* 12605 */       if (builder.mergeDelimitedFrom(input))
/* 12606 */         return builder.buildParsed(); 
/* 12608 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12615 */       Builder builder = newBuilder();
/* 12616 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 12617 */         return builder.buildParsed(); 
/* 12619 */       return null;
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(CodedInputStream input) throws IOException {
/* 12625 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static EnumValueOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 12631 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 12635 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 12636 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(EnumValueOptions prototype) {
/* 12638 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 12640 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 12645 */       Builder builder = new Builder(parent);
/* 12646 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<EnumValueOptions, Builder> implements DescriptorProtos.EnumValueOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 12653 */         return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 12658 */         return DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 12829 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 12829 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.EnumValueOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.EnumValueOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptions build() {
/*       */         DescriptorProtos.EnumValueOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.EnumValueOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.EnumValueOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.EnumValueOptions buildPartial() {
/*       */         DescriptorProtos.EnumValueOptions result = new DescriptorProtos.EnumValueOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.EnumValueOptions)
/*       */           return mergeFrom((DescriptorProtos.EnumValueOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.EnumValueOptions other) {
/*       */         if (other == DescriptorProtos.EnumValueOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 12832 */         if ((this.bitField0_ & 0x1) != 1) {
/* 12833 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 12834 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 12842 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12843 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 12845 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 12849 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12850 */           return this.uninterpretedOption_.size(); 
/* 12852 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 12856 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12857 */           return this.uninterpretedOption_.get(index); 
/* 12859 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 12864 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12865 */           if (value == null)
/* 12866 */             throw new NullPointerException(); 
/* 12868 */           ensureUninterpretedOptionIsMutable();
/* 12869 */           this.uninterpretedOption_.set(index, value);
/* 12870 */           onChanged();
/*       */         } else {
/* 12872 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 12874 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12878 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12879 */           ensureUninterpretedOptionIsMutable();
/* 12880 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 12881 */           onChanged();
/*       */         } else {
/* 12883 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 12885 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 12888 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12889 */           if (value == null)
/* 12890 */             throw new NullPointerException(); 
/* 12892 */           ensureUninterpretedOptionIsMutable();
/* 12893 */           this.uninterpretedOption_.add(value);
/* 12894 */           onChanged();
/*       */         } else {
/* 12896 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 12898 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 12902 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12903 */           if (value == null)
/* 12904 */             throw new NullPointerException(); 
/* 12906 */           ensureUninterpretedOptionIsMutable();
/* 12907 */           this.uninterpretedOption_.add(index, value);
/* 12908 */           onChanged();
/*       */         } else {
/* 12910 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 12912 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12916 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12917 */           ensureUninterpretedOptionIsMutable();
/* 12918 */           this.uninterpretedOption_.add(builderForValue.build());
/* 12919 */           onChanged();
/*       */         } else {
/* 12921 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 12923 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 12927 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12928 */           ensureUninterpretedOptionIsMutable();
/* 12929 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 12930 */           onChanged();
/*       */         } else {
/* 12932 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 12934 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 12938 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12939 */           ensureUninterpretedOptionIsMutable();
/* 12940 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 12941 */           onChanged();
/*       */         } else {
/* 12943 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 12945 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 12948 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12949 */           this.uninterpretedOption_ = Collections.emptyList();
/* 12950 */           this.bitField0_ &= 0xFFFFFFFE;
/* 12951 */           onChanged();
/*       */         } else {
/* 12953 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 12955 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 12958 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 12959 */           ensureUninterpretedOptionIsMutable();
/* 12960 */           this.uninterpretedOption_.remove(index);
/* 12961 */           onChanged();
/*       */         } else {
/* 12963 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 12965 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 12969 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 12973 */         if (this.uninterpretedOptionBuilder_ == null)
/* 12974 */           return this.uninterpretedOption_.get(index); 
/* 12975 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 12980 */         if (this.uninterpretedOptionBuilder_ != null)
/* 12981 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 12983 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 12987 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 12992 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 12997 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 13002 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13003 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/* 13009 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 13011 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 13018 */     private static final EnumValueOptions defaultInstance = new EnumValueOptions(true);
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 13019 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class ServiceOptions extends GeneratedMessage.ExtendableMessage<ServiceOptions> implements ServiceOptionsOrBuilder {
/*       */     private ServiceOptions(Builder builder) {
/* 13044 */       super(builder);
/* 13091 */       this.memoizedIsInitialized = -1;
/* 13123 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private ServiceOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 13123 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static ServiceOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public ServiceOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<ServiceOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 13125 */       int size = this.memoizedSerializedSize;
/* 13126 */       if (size != -1)
/* 13126 */         return size; 
/* 13128 */       size = 0;
/* 13129 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 13130 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 13133 */       size += extensionsSerializedSize();
/* 13134 */       size += getUnknownFields().getSerializedSize();
/* 13135 */       this.memoizedSerializedSize = size;
/* 13136 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 13143 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 13149 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 13155 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 13160 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 13166 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(InputStream input) throws IOException {
/* 13171 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13177 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 13182 */       Builder builder = newBuilder();
/* 13183 */       if (builder.mergeDelimitedFrom(input))
/* 13184 */         return builder.buildParsed(); 
/* 13186 */       return null;
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13193 */       Builder builder = newBuilder();
/* 13194 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 13195 */         return builder.buildParsed(); 
/* 13197 */       return null;
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(CodedInputStream input) throws IOException {
/* 13203 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static ServiceOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13209 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 13213 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 13214 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(ServiceOptions prototype) {
/* 13216 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 13218 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 13223 */       Builder builder = new Builder(parent);
/* 13224 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<ServiceOptions, Builder> implements DescriptorProtos.ServiceOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 13231 */         return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 13236 */         return DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 13407 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 13407 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.ServiceOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.ServiceOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptions build() {
/*       */         DescriptorProtos.ServiceOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.ServiceOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.ServiceOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.ServiceOptions buildPartial() {
/*       */         DescriptorProtos.ServiceOptions result = new DescriptorProtos.ServiceOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.ServiceOptions)
/*       */           return mergeFrom((DescriptorProtos.ServiceOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.ServiceOptions other) {
/*       */         if (other == DescriptorProtos.ServiceOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 13410 */         if ((this.bitField0_ & 0x1) != 1) {
/* 13411 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 13412 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 13420 */         if (this.uninterpretedOptionBuilder_ == null)
/* 13421 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 13423 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 13427 */         if (this.uninterpretedOptionBuilder_ == null)
/* 13428 */           return this.uninterpretedOption_.size(); 
/* 13430 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 13434 */         if (this.uninterpretedOptionBuilder_ == null)
/* 13435 */           return this.uninterpretedOption_.get(index); 
/* 13437 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 13442 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13443 */           if (value == null)
/* 13444 */             throw new NullPointerException(); 
/* 13446 */           ensureUninterpretedOptionIsMutable();
/* 13447 */           this.uninterpretedOption_.set(index, value);
/* 13448 */           onChanged();
/*       */         } else {
/* 13450 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 13452 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 13456 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13457 */           ensureUninterpretedOptionIsMutable();
/* 13458 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 13459 */           onChanged();
/*       */         } else {
/* 13461 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 13463 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 13466 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13467 */           if (value == null)
/* 13468 */             throw new NullPointerException(); 
/* 13470 */           ensureUninterpretedOptionIsMutable();
/* 13471 */           this.uninterpretedOption_.add(value);
/* 13472 */           onChanged();
/*       */         } else {
/* 13474 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 13476 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 13480 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13481 */           if (value == null)
/* 13482 */             throw new NullPointerException(); 
/* 13484 */           ensureUninterpretedOptionIsMutable();
/* 13485 */           this.uninterpretedOption_.add(index, value);
/* 13486 */           onChanged();
/*       */         } else {
/* 13488 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 13490 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 13494 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13495 */           ensureUninterpretedOptionIsMutable();
/* 13496 */           this.uninterpretedOption_.add(builderForValue.build());
/* 13497 */           onChanged();
/*       */         } else {
/* 13499 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 13501 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 13505 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13506 */           ensureUninterpretedOptionIsMutable();
/* 13507 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 13508 */           onChanged();
/*       */         } else {
/* 13510 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 13512 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 13516 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13517 */           ensureUninterpretedOptionIsMutable();
/* 13518 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 13519 */           onChanged();
/*       */         } else {
/* 13521 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 13523 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 13526 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13527 */           this.uninterpretedOption_ = Collections.emptyList();
/* 13528 */           this.bitField0_ &= 0xFFFFFFFE;
/* 13529 */           onChanged();
/*       */         } else {
/* 13531 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 13533 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 13536 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13537 */           ensureUninterpretedOptionIsMutable();
/* 13538 */           this.uninterpretedOption_.remove(index);
/* 13539 */           onChanged();
/*       */         } else {
/* 13541 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 13543 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 13547 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 13551 */         if (this.uninterpretedOptionBuilder_ == null)
/* 13552 */           return this.uninterpretedOption_.get(index); 
/* 13553 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 13558 */         if (this.uninterpretedOptionBuilder_ != null)
/* 13559 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 13561 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 13565 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 13570 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 13575 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 13580 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 13581 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/* 13587 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 13589 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 13596 */     private static final ServiceOptions defaultInstance = new ServiceOptions(true);
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 13597 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class MethodOptions extends GeneratedMessage.ExtendableMessage<MethodOptions> implements MethodOptionsOrBuilder {
/*       */     private MethodOptions(Builder builder) {
/* 13622 */       super(builder);
/* 13669 */       this.memoizedIsInitialized = -1;
/* 13701 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private MethodOptions(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 13701 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static MethodOptions getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public MethodOptions getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/*       */       return this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/*       */       return (List)this.uninterpretedOption_;
/*       */     }
/*       */     
/*       */     public int getUninterpretedOptionCount() {
/*       */       return this.uninterpretedOption_.size();
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/*       */       return this.uninterpretedOption_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.uninterpretedOption_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */         if (!getUninterpretedOption(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       if (!extensionsAreInitialized()) {
/*       */         this.memoizedIsInitialized = 0;
/*       */         return false;
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       GeneratedMessage.ExtendableMessage<MethodOptions>.ExtensionWriter extensionWriter = newExtensionWriter();
/*       */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/*       */         output.writeMessage(999, this.uninterpretedOption_.get(i)); 
/*       */       extensionWriter.writeUntil(536870912, output);
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 13703 */       int size = this.memoizedSerializedSize;
/* 13704 */       if (size != -1)
/* 13704 */         return size; 
/* 13706 */       size = 0;
/* 13707 */       for (int i = 0; i < this.uninterpretedOption_.size(); i++)
/* 13708 */         size += CodedOutputStream.computeMessageSize(999, this.uninterpretedOption_.get(i)); 
/* 13711 */       size += extensionsSerializedSize();
/* 13712 */       size += getUnknownFields().getSerializedSize();
/* 13713 */       this.memoizedSerializedSize = size;
/* 13714 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 13721 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 13727 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 13733 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 13738 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 13744 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(InputStream input) throws IOException {
/* 13749 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13755 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseDelimitedFrom(InputStream input) throws IOException {
/* 13760 */       Builder builder = newBuilder();
/* 13761 */       if (builder.mergeDelimitedFrom(input))
/* 13762 */         return builder.buildParsed(); 
/* 13764 */       return null;
/*       */     }
/*       */     
/*       */     public static MethodOptions parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13771 */       Builder builder = newBuilder();
/* 13772 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 13773 */         return builder.buildParsed(); 
/* 13775 */       return null;
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(CodedInputStream input) throws IOException {
/* 13781 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static MethodOptions parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 13787 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 13791 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 13792 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(MethodOptions prototype) {
/* 13794 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 13796 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 13801 */       Builder builder = new Builder(parent);
/* 13802 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.ExtendableBuilder<MethodOptions, Builder> implements DescriptorProtos.MethodOptionsOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> uninterpretedOptionBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 13809 */         return DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 13814 */         return DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 13985 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 13985 */         this.uninterpretedOption_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getUninterpretedOptionFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           this.uninterpretedOption_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.MethodOptions.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptions getDefaultInstanceForType() {
/*       */         return DescriptorProtos.MethodOptions.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptions build() {
/*       */         DescriptorProtos.MethodOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.MethodOptions buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.MethodOptions result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.MethodOptions buildPartial() {
/*       */         DescriptorProtos.MethodOptions result = new DescriptorProtos.MethodOptions(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.uninterpretedOption_ = Collections.unmodifiableList(this.uninterpretedOption_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.uninterpretedOption_ = this.uninterpretedOption_;
/*       */         } else {
/*       */           result.uninterpretedOption_ = this.uninterpretedOptionBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.MethodOptions)
/*       */           return mergeFrom((DescriptorProtos.MethodOptions)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.MethodOptions other) {
/*       */         if (other == DescriptorProtos.MethodOptions.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.uninterpretedOptionBuilder_ == null) {
/*       */           if (!other.uninterpretedOption_.isEmpty()) {
/*       */             if (this.uninterpretedOption_.isEmpty()) {
/*       */               this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureUninterpretedOptionIsMutable();
/*       */               this.uninterpretedOption_.addAll(other.uninterpretedOption_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.uninterpretedOption_.isEmpty()) {
/*       */           if (this.uninterpretedOptionBuilder_.isEmpty()) {
/*       */             this.uninterpretedOptionBuilder_.dispose();
/*       */             this.uninterpretedOptionBuilder_ = null;
/*       */             this.uninterpretedOption_ = other.uninterpretedOption_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.uninterpretedOptionBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getUninterpretedOptionFieldBuilder() : null;
/*       */           } else {
/*       */             this.uninterpretedOptionBuilder_.addAllMessages(other.uninterpretedOption_);
/*       */           } 
/*       */         } 
/*       */         mergeExtensionFields(other);
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getUninterpretedOptionCount(); i++) {
/*       */           if (!getUninterpretedOption(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         if (!extensionsAreInitialized())
/*       */           return false; 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 7994:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addUninterpretedOption(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureUninterpretedOptionIsMutable() {
/* 13988 */         if ((this.bitField0_ & 0x1) != 1) {
/* 13989 */           this.uninterpretedOption_ = new ArrayList<DescriptorProtos.UninterpretedOption>(this.uninterpretedOption_);
/* 13990 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList() {
/* 13998 */         if (this.uninterpretedOptionBuilder_ == null)
/* 13999 */           return Collections.unmodifiableList(this.uninterpretedOption_); 
/* 14001 */         return this.uninterpretedOptionBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getUninterpretedOptionCount() {
/* 14005 */         if (this.uninterpretedOptionBuilder_ == null)
/* 14006 */           return this.uninterpretedOption_.size(); 
/* 14008 */         return this.uninterpretedOptionBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getUninterpretedOption(int index) {
/* 14012 */         if (this.uninterpretedOptionBuilder_ == null)
/* 14013 */           return this.uninterpretedOption_.get(index); 
/* 14015 */         return this.uninterpretedOptionBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 14020 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14021 */           if (value == null)
/* 14022 */             throw new NullPointerException(); 
/* 14024 */           ensureUninterpretedOptionIsMutable();
/* 14025 */           this.uninterpretedOption_.set(index, value);
/* 14026 */           onChanged();
/*       */         } else {
/* 14028 */           this.uninterpretedOptionBuilder_.setMessage(index, value);
/*       */         } 
/* 14030 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 14034 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14035 */           ensureUninterpretedOptionIsMutable();
/* 14036 */           this.uninterpretedOption_.set(index, builderForValue.build());
/* 14037 */           onChanged();
/*       */         } else {
/* 14039 */           this.uninterpretedOptionBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 14041 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption value) {
/* 14044 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14045 */           if (value == null)
/* 14046 */             throw new NullPointerException(); 
/* 14048 */           ensureUninterpretedOptionIsMutable();
/* 14049 */           this.uninterpretedOption_.add(value);
/* 14050 */           onChanged();
/*       */         } else {
/* 14052 */           this.uninterpretedOptionBuilder_.addMessage(value);
/*       */         } 
/* 14054 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption value) {
/* 14058 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14059 */           if (value == null)
/* 14060 */             throw new NullPointerException(); 
/* 14062 */           ensureUninterpretedOptionIsMutable();
/* 14063 */           this.uninterpretedOption_.add(index, value);
/* 14064 */           onChanged();
/*       */         } else {
/* 14066 */           this.uninterpretedOptionBuilder_.addMessage(index, value);
/*       */         } 
/* 14068 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 14072 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14073 */           ensureUninterpretedOptionIsMutable();
/* 14074 */           this.uninterpretedOption_.add(builderForValue.build());
/* 14075 */           onChanged();
/*       */         } else {
/* 14077 */           this.uninterpretedOptionBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 14079 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addUninterpretedOption(int index, DescriptorProtos.UninterpretedOption.Builder builderForValue) {
/* 14083 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14084 */           ensureUninterpretedOptionIsMutable();
/* 14085 */           this.uninterpretedOption_.add(index, builderForValue.build());
/* 14086 */           onChanged();
/*       */         } else {
/* 14088 */           this.uninterpretedOptionBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 14090 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllUninterpretedOption(Iterable<? extends DescriptorProtos.UninterpretedOption> values) {
/* 14094 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14095 */           ensureUninterpretedOptionIsMutable();
/* 14096 */           GeneratedMessage.ExtendableBuilder.addAll(values, this.uninterpretedOption_);
/* 14097 */           onChanged();
/*       */         } else {
/* 14099 */           this.uninterpretedOptionBuilder_.addAllMessages(values);
/*       */         } 
/* 14101 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearUninterpretedOption() {
/* 14104 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14105 */           this.uninterpretedOption_ = Collections.emptyList();
/* 14106 */           this.bitField0_ &= 0xFFFFFFFE;
/* 14107 */           onChanged();
/*       */         } else {
/* 14109 */           this.uninterpretedOptionBuilder_.clear();
/*       */         } 
/* 14111 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeUninterpretedOption(int index) {
/* 14114 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14115 */           ensureUninterpretedOptionIsMutable();
/* 14116 */           this.uninterpretedOption_.remove(index);
/* 14117 */           onChanged();
/*       */         } else {
/* 14119 */           this.uninterpretedOptionBuilder_.remove(index);
/*       */         } 
/* 14121 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder getUninterpretedOptionBuilder(int index) {
/* 14125 */         return getUninterpretedOptionFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int index) {
/* 14129 */         if (this.uninterpretedOptionBuilder_ == null)
/* 14130 */           return this.uninterpretedOption_.get(index); 
/* 14131 */         return this.uninterpretedOptionBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList() {
/* 14136 */         if (this.uninterpretedOptionBuilder_ != null)
/* 14137 */           return this.uninterpretedOptionBuilder_.getMessageOrBuilderList(); 
/* 14139 */         return Collections.unmodifiableList((List)this.uninterpretedOption_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder() {
/* 14143 */         return getUninterpretedOptionFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.Builder addUninterpretedOptionBuilder(int index) {
/* 14148 */         return getUninterpretedOptionFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.Builder> getUninterpretedOptionBuilderList() {
/* 14153 */         return getUninterpretedOptionFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionFieldBuilder() {
/* 14158 */         if (this.uninterpretedOptionBuilder_ == null) {
/* 14159 */           this.uninterpretedOptionBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption, DescriptorProtos.UninterpretedOption.Builder, DescriptorProtos.UninterpretedOptionOrBuilder>(this.uninterpretedOption_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/* 14165 */           this.uninterpretedOption_ = null;
/*       */         } 
/* 14167 */         return this.uninterpretedOptionBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 14174 */     private static final MethodOptions defaultInstance = new MethodOptions(true);
/*       */     
/*       */     public static final int UNINTERPRETED_OPTION_FIELD_NUMBER = 999;
/*       */     
/*       */     private List<DescriptorProtos.UninterpretedOption> uninterpretedOption_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 14175 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class UninterpretedOption extends GeneratedMessage implements UninterpretedOptionOrBuilder {
/*       */     private UninterpretedOption(Builder builder) {
/* 14223 */       super(builder);
/* 14829 */       this.memoizedIsInitialized = -1;
/* 14871 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private UninterpretedOption(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 14871 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static UninterpretedOption getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public UninterpretedOption getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public static interface NamePartOrBuilder extends MessageOrBuilder {
/*       */       boolean hasNamePart();
/*       */       
/*       */       String getNamePart();
/*       */       
/*       */       boolean hasIsExtension();
/*       */       
/*       */       boolean getIsExtension();
/*       */     }
/*       */     
/*       */     public static final class NamePart extends GeneratedMessage implements NamePartOrBuilder {
/*       */       private NamePart(Builder builder) {
/*       */         super(builder);
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       private NamePart(boolean noInit) {
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       public static NamePart getDefaultInstance() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public NamePart getDefaultInstanceForType() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       public boolean hasNamePart() {
/*       */         return ((this.bitField0_ & 0x1) == 1);
/*       */       }
/*       */       
/*       */       public String getNamePart() {
/*       */         Object ref = this.namePart_;
/*       */         if (ref instanceof String)
/*       */           return (String)ref; 
/*       */         ByteString bs = (ByteString)ref;
/*       */         String s = bs.toStringUtf8();
/*       */         if (Internal.isValidUtf8(bs))
/*       */           this.namePart_ = s; 
/*       */         return s;
/*       */       }
/*       */       
/*       */       private ByteString getNamePartBytes() {
/*       */         Object ref = this.namePart_;
/*       */         if (ref instanceof String) {
/*       */           ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */           this.namePart_ = b;
/*       */           return b;
/*       */         } 
/*       */         return (ByteString)ref;
/*       */       }
/*       */       
/*       */       public boolean hasIsExtension() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public boolean getIsExtension() {
/*       */         return this.isExtension_;
/*       */       }
/*       */       
/*       */       private void initFields() {
/*       */         this.namePart_ = "";
/*       */         this.isExtension_ = false;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         byte isInitialized = this.memoizedIsInitialized;
/*       */         if (isInitialized != -1)
/*       */           return (isInitialized == 1); 
/*       */         if (!hasNamePart()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */         if (!hasIsExtension()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */         this.memoizedIsInitialized = 1;
/*       */         return true;
/*       */       }
/*       */       
/*       */       public void writeTo(CodedOutputStream output) throws IOException {
/*       */         getSerializedSize();
/*       */         if ((this.bitField0_ & 0x1) == 1)
/*       */           output.writeBytes(1, getNamePartBytes()); 
/*       */         if ((this.bitField0_ & 0x2) == 2)
/*       */           output.writeBool(2, this.isExtension_); 
/*       */         getUnknownFields().writeTo(output);
/*       */       }
/*       */       
/*       */       public int getSerializedSize() {
/*       */         int size = this.memoizedSerializedSize;
/*       */         if (size != -1)
/*       */           return size; 
/*       */         size = 0;
/*       */         if ((this.bitField0_ & 0x1) == 1)
/*       */           size += CodedOutputStream.computeBytesSize(1, getNamePartBytes()); 
/*       */         if ((this.bitField0_ & 0x2) == 2)
/*       */           size += CodedOutputStream.computeBoolSize(2, this.isExtension_); 
/*       */         size += getUnknownFields().getSerializedSize();
/*       */         this.memoizedSerializedSize = size;
/*       */         return size;
/*       */       }
/*       */       
/*       */       protected Object writeReplace() throws ObjectStreamException {
/*       */         return super.writeReplace();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(InputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseDelimitedFrom(InputStream input) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static NamePart parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(CodedInputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static NamePart parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder() {
/*       */         return Builder.create();
/*       */       }
/*       */       
/*       */       public Builder newBuilderForType() {
/*       */         return newBuilder();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder(NamePart prototype) {
/*       */         return newBuilder().mergeFrom(prototype);
/*       */       }
/*       */       
/*       */       public Builder toBuilder() {
/*       */         return newBuilder(this);
/*       */       }
/*       */       
/*       */       protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*       */         Builder builder = new Builder(parent);
/*       */         return builder;
/*       */       }
/*       */       
/*       */       public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.UninterpretedOption.NamePartOrBuilder {
/*       */         private int bitField0_;
/*       */         
/*       */         private Object namePart_;
/*       */         
/*       */         private boolean isExtension_;
/*       */         
/*       */         public static final Descriptors.Descriptor getDescriptor() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor;
/*       */         }
/*       */         
/*       */         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable;
/*       */         }
/*       */         
/*       */         private Builder() {
/*       */           this.namePart_ = "";
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private Builder(GeneratedMessage.BuilderParent parent) {
/*       */           super(parent);
/*       */           this.namePart_ = "";
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private void maybeForceBuilderInitialization() {
/*       */           if (GeneratedMessage.alwaysUseFieldBuilders);
/*       */         }
/*       */         
/*       */         private static Builder create() {
/*       */           return new Builder();
/*       */         }
/*       */         
/*       */         public Builder clear() {
/*       */           super.clear();
/*       */           this.namePart_ = "";
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           this.isExtension_ = false;
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clone() {
/*       */           return create().mergeFrom(buildPartial());
/*       */         }
/*       */         
/*       */         public Descriptors.Descriptor getDescriptorForType() {
/*       */           return DescriptorProtos.UninterpretedOption.NamePart.getDescriptor();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.UninterpretedOption.NamePart getDefaultInstanceForType() {
/*       */           return DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.UninterpretedOption.NamePart build() {
/*       */           DescriptorProtos.UninterpretedOption.NamePart result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         private DescriptorProtos.UninterpretedOption.NamePart buildParsed() throws InvalidProtocolBufferException {
/*       */           DescriptorProtos.UninterpretedOption.NamePart result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         public DescriptorProtos.UninterpretedOption.NamePart buildPartial() {
/*       */           DescriptorProtos.UninterpretedOption.NamePart result = new DescriptorProtos.UninterpretedOption.NamePart(this);
/*       */           int from_bitField0_ = this.bitField0_;
/*       */           int to_bitField0_ = 0;
/*       */           if ((from_bitField0_ & 0x1) == 1)
/*       */             to_bitField0_ |= 0x1; 
/*       */           result.namePart_ = this.namePart_;
/*       */           if ((from_bitField0_ & 0x2) == 2)
/*       */             to_bitField0_ |= 0x2; 
/*       */           result.isExtension_ = this.isExtension_;
/*       */           result.bitField0_ = to_bitField0_;
/*       */           onBuilt();
/*       */           return result;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(Message other) {
/*       */           if (other instanceof DescriptorProtos.UninterpretedOption.NamePart)
/*       */             return mergeFrom((DescriptorProtos.UninterpretedOption.NamePart)other); 
/*       */           super.mergeFrom(other);
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(DescriptorProtos.UninterpretedOption.NamePart other) {
/*       */           if (other == DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance())
/*       */             return this; 
/*       */           if (other.hasNamePart())
/*       */             setNamePart(other.getNamePart()); 
/*       */           if (other.hasIsExtension())
/*       */             setIsExtension(other.getIsExtension()); 
/*       */           mergeUnknownFields(other.getUnknownFields());
/*       */           return this;
/*       */         }
/*       */         
/*       */         public final boolean isInitialized() {
/*       */           if (!hasNamePart())
/*       */             return false; 
/*       */           if (!hasIsExtension())
/*       */             return false; 
/*       */           return true;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */           UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */           while (true) {
/*       */             int tag = input.readTag();
/*       */             switch (tag) {
/*       */               case 0:
/*       */                 setUnknownFields(unknownFields.build());
/*       */                 onChanged();
/*       */                 return this;
/*       */               case 10:
/*       */                 this.bitField0_ |= 0x1;
/*       */                 this.namePart_ = input.readBytes();
/*       */                 break;
/*       */               case 16:
/*       */                 this.bitField0_ |= 0x2;
/*       */                 this.isExtension_ = input.readBool();
/*       */                 break;
/*       */             } 
/*       */           } 
/*       */         }
/*       */         
/*       */         public boolean hasNamePart() {
/*       */           return ((this.bitField0_ & 0x1) == 1);
/*       */         }
/*       */         
/*       */         public String getNamePart() {
/*       */           Object ref = this.namePart_;
/*       */           if (!(ref instanceof String)) {
/*       */             String s = ((ByteString)ref).toStringUtf8();
/*       */             this.namePart_ = s;
/*       */             return s;
/*       */           } 
/*       */           return (String)ref;
/*       */         }
/*       */         
/*       */         public Builder setNamePart(String value) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           this.bitField0_ |= 0x1;
/*       */           this.namePart_ = value;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearNamePart() {
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           this.namePart_ = DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance().getNamePart();
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         void setNamePart(ByteString value) {
/*       */           this.bitField0_ |= 0x1;
/*       */           this.namePart_ = value;
/*       */           onChanged();
/*       */         }
/*       */         
/*       */         public boolean hasIsExtension() {
/*       */           return ((this.bitField0_ & 0x2) == 2);
/*       */         }
/*       */         
/*       */         public boolean getIsExtension() {
/*       */           return this.isExtension_;
/*       */         }
/*       */         
/*       */         public Builder setIsExtension(boolean value) {
/*       */           this.bitField0_ |= 0x2;
/*       */           this.isExtension_ = value;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearIsExtension() {
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           this.isExtension_ = false;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */       }
/*       */       
/*       */       private static final NamePart defaultInstance = new NamePart(true);
/*       */       
/*       */       private int bitField0_;
/*       */       
/*       */       public static final int NAME_PART_FIELD_NUMBER = 1;
/*       */       
/*       */       private Object namePart_;
/*       */       
/*       */       public static final int IS_EXTENSION_FIELD_NUMBER = 2;
/*       */       
/*       */       private boolean isExtension_;
/*       */       
/*       */       private byte memoizedIsInitialized;
/*       */       
/*       */       private int memoizedSerializedSize;
/*       */       
/*       */       private static final long serialVersionUID = 0L;
/*       */       
/*       */       static {
/*       */         defaultInstance.initFields();
/*       */       }
/*       */     }
/*       */     
/*       */     public List<NamePart> getNameList() {
/*       */       return this.name_;
/*       */     }
/*       */     
/*       */     public List<? extends NamePartOrBuilder> getNameOrBuilderList() {
/*       */       return (List)this.name_;
/*       */     }
/*       */     
/*       */     public int getNameCount() {
/*       */       return this.name_.size();
/*       */     }
/*       */     
/*       */     public NamePart getName(int index) {
/*       */       return this.name_.get(index);
/*       */     }
/*       */     
/*       */     public NamePartOrBuilder getNameOrBuilder(int index) {
/*       */       return this.name_.get(index);
/*       */     }
/*       */     
/*       */     public boolean hasIdentifierValue() {
/*       */       return ((this.bitField0_ & 0x1) == 1);
/*       */     }
/*       */     
/*       */     public String getIdentifierValue() {
/*       */       Object ref = this.identifierValue_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.identifierValue_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getIdentifierValueBytes() {
/*       */       Object ref = this.identifierValue_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.identifierValue_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     public boolean hasPositiveIntValue() {
/*       */       return ((this.bitField0_ & 0x2) == 2);
/*       */     }
/*       */     
/*       */     public long getPositiveIntValue() {
/*       */       return this.positiveIntValue_;
/*       */     }
/*       */     
/*       */     public boolean hasNegativeIntValue() {
/*       */       return ((this.bitField0_ & 0x4) == 4);
/*       */     }
/*       */     
/*       */     public long getNegativeIntValue() {
/*       */       return this.negativeIntValue_;
/*       */     }
/*       */     
/*       */     public boolean hasDoubleValue() {
/*       */       return ((this.bitField0_ & 0x8) == 8);
/*       */     }
/*       */     
/*       */     public double getDoubleValue() {
/*       */       return this.doubleValue_;
/*       */     }
/*       */     
/*       */     public boolean hasStringValue() {
/*       */       return ((this.bitField0_ & 0x10) == 16);
/*       */     }
/*       */     
/*       */     public ByteString getStringValue() {
/*       */       return this.stringValue_;
/*       */     }
/*       */     
/*       */     public boolean hasAggregateValue() {
/*       */       return ((this.bitField0_ & 0x20) == 32);
/*       */     }
/*       */     
/*       */     public String getAggregateValue() {
/*       */       Object ref = this.aggregateValue_;
/*       */       if (ref instanceof String)
/*       */         return (String)ref; 
/*       */       ByteString bs = (ByteString)ref;
/*       */       String s = bs.toStringUtf8();
/*       */       if (Internal.isValidUtf8(bs))
/*       */         this.aggregateValue_ = s; 
/*       */       return s;
/*       */     }
/*       */     
/*       */     private ByteString getAggregateValueBytes() {
/*       */       Object ref = this.aggregateValue_;
/*       */       if (ref instanceof String) {
/*       */         ByteString b = ByteString.copyFromUtf8((String)ref);
/*       */         this.aggregateValue_ = b;
/*       */         return b;
/*       */       } 
/*       */       return (ByteString)ref;
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.name_ = Collections.emptyList();
/*       */       this.identifierValue_ = "";
/*       */       this.positiveIntValue_ = 0L;
/*       */       this.negativeIntValue_ = 0L;
/*       */       this.doubleValue_ = 0.0D;
/*       */       this.stringValue_ = ByteString.EMPTY;
/*       */       this.aggregateValue_ = "";
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       for (int i = 0; i < getNameCount(); i++) {
/*       */         if (!getName(i).isInitialized()) {
/*       */           this.memoizedIsInitialized = 0;
/*       */           return false;
/*       */         } 
/*       */       } 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       for (int i = 0; i < this.name_.size(); i++)
/*       */         output.writeMessage(2, this.name_.get(i)); 
/*       */       if ((this.bitField0_ & 0x1) == 1)
/*       */         output.writeBytes(3, getIdentifierValueBytes()); 
/*       */       if ((this.bitField0_ & 0x2) == 2)
/*       */         output.writeUInt64(4, this.positiveIntValue_); 
/*       */       if ((this.bitField0_ & 0x4) == 4)
/*       */         output.writeInt64(5, this.negativeIntValue_); 
/*       */       if ((this.bitField0_ & 0x8) == 8)
/*       */         output.writeDouble(6, this.doubleValue_); 
/*       */       if ((this.bitField0_ & 0x10) == 16)
/*       */         output.writeBytes(7, this.stringValue_); 
/*       */       if ((this.bitField0_ & 0x20) == 32)
/*       */         output.writeBytes(8, getAggregateValueBytes()); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 14873 */       int size = this.memoizedSerializedSize;
/* 14874 */       if (size != -1)
/* 14874 */         return size; 
/* 14876 */       size = 0;
/* 14877 */       for (int i = 0; i < this.name_.size(); i++)
/* 14878 */         size += CodedOutputStream.computeMessageSize(2, this.name_.get(i)); 
/* 14881 */       if ((this.bitField0_ & 0x1) == 1)
/* 14882 */         size += CodedOutputStream.computeBytesSize(3, getIdentifierValueBytes()); 
/* 14885 */       if ((this.bitField0_ & 0x2) == 2)
/* 14886 */         size += CodedOutputStream.computeUInt64Size(4, this.positiveIntValue_); 
/* 14889 */       if ((this.bitField0_ & 0x4) == 4)
/* 14890 */         size += CodedOutputStream.computeInt64Size(5, this.negativeIntValue_); 
/* 14893 */       if ((this.bitField0_ & 0x8) == 8)
/* 14894 */         size += CodedOutputStream.computeDoubleSize(6, this.doubleValue_); 
/* 14897 */       if ((this.bitField0_ & 0x10) == 16)
/* 14898 */         size += CodedOutputStream.computeBytesSize(7, this.stringValue_); 
/* 14901 */       if ((this.bitField0_ & 0x20) == 32)
/* 14902 */         size += CodedOutputStream.computeBytesSize(8, getAggregateValueBytes()); 
/* 14905 */       size += getUnknownFields().getSerializedSize();
/* 14906 */       this.memoizedSerializedSize = size;
/* 14907 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 14914 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 14920 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 14926 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 14931 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 14937 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(InputStream input) throws IOException {
/* 14942 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 14948 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseDelimitedFrom(InputStream input) throws IOException {
/* 14953 */       Builder builder = newBuilder();
/* 14954 */       if (builder.mergeDelimitedFrom(input))
/* 14955 */         return builder.buildParsed(); 
/* 14957 */       return null;
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 14964 */       Builder builder = newBuilder();
/* 14965 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 14966 */         return builder.buildParsed(); 
/* 14968 */       return null;
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(CodedInputStream input) throws IOException {
/* 14974 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static UninterpretedOption parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 14980 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 14984 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 14985 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(UninterpretedOption prototype) {
/* 14987 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 14989 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 14994 */       Builder builder = new Builder(parent);
/* 14995 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.UninterpretedOptionOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.UninterpretedOption.NamePart> name_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption.NamePart, DescriptorProtos.UninterpretedOption.NamePart.Builder, DescriptorProtos.UninterpretedOption.NamePartOrBuilder> nameBuilder_;
/*       */       
/*       */       private Object identifierValue_;
/*       */       
/*       */       private long positiveIntValue_;
/*       */       
/*       */       private long negativeIntValue_;
/*       */       
/*       */       private double doubleValue_;
/*       */       
/*       */       private ByteString stringValue_;
/*       */       
/*       */       private Object aggregateValue_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 15002 */         return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 15007 */         return DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 15259 */         this.name_ = Collections.emptyList();
/* 15445 */         this.identifierValue_ = "";
/* 15544 */         this.stringValue_ = ByteString.EMPTY;
/* 15568 */         this.aggregateValue_ = "";
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/*       */         this.name_ = Collections.emptyList();
/*       */         this.identifierValue_ = "";
/*       */         this.stringValue_ = ByteString.EMPTY;
/* 15568 */         this.aggregateValue_ = "";
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getNameFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.nameBuilder_ == null) {
/*       */           this.name_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.nameBuilder_.clear();
/*       */         } 
/*       */         this.identifierValue_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.positiveIntValue_ = 0L;
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.negativeIntValue_ = 0L;
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.doubleValue_ = 0.0D;
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.stringValue_ = ByteString.EMPTY;
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.aggregateValue_ = "";
/*       */         this.bitField0_ &= 0xFFFFFFBF;
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.UninterpretedOption.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption getDefaultInstanceForType() {
/*       */         return DescriptorProtos.UninterpretedOption.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption build() {
/*       */         DescriptorProtos.UninterpretedOption result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.UninterpretedOption buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.UninterpretedOption result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption buildPartial() {
/*       */         DescriptorProtos.UninterpretedOption result = new DescriptorProtos.UninterpretedOption(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         int to_bitField0_ = 0;
/*       */         if (this.nameBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.name_ = Collections.unmodifiableList(this.name_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.name_ = this.name_;
/*       */         } else {
/*       */           result.name_ = this.nameBuilder_.build();
/*       */         } 
/*       */         if ((from_bitField0_ & 0x2) == 2)
/*       */           to_bitField0_ |= 0x1; 
/*       */         result.identifierValue_ = this.identifierValue_;
/*       */         if ((from_bitField0_ & 0x4) == 4)
/*       */           to_bitField0_ |= 0x2; 
/*       */         result.positiveIntValue_ = this.positiveIntValue_;
/*       */         if ((from_bitField0_ & 0x8) == 8)
/*       */           to_bitField0_ |= 0x4; 
/*       */         result.negativeIntValue_ = this.negativeIntValue_;
/*       */         if ((from_bitField0_ & 0x10) == 16)
/*       */           to_bitField0_ |= 0x8; 
/*       */         result.doubleValue_ = this.doubleValue_;
/*       */         if ((from_bitField0_ & 0x20) == 32)
/*       */           to_bitField0_ |= 0x10; 
/*       */         result.stringValue_ = this.stringValue_;
/*       */         if ((from_bitField0_ & 0x40) == 64)
/*       */           to_bitField0_ |= 0x20; 
/*       */         result.aggregateValue_ = this.aggregateValue_;
/*       */         result.bitField0_ = to_bitField0_;
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.UninterpretedOption)
/*       */           return mergeFrom((DescriptorProtos.UninterpretedOption)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.UninterpretedOption other) {
/*       */         if (other == DescriptorProtos.UninterpretedOption.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.nameBuilder_ == null) {
/*       */           if (!other.name_.isEmpty()) {
/*       */             if (this.name_.isEmpty()) {
/*       */               this.name_ = other.name_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureNameIsMutable();
/*       */               this.name_.addAll(other.name_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.name_.isEmpty()) {
/*       */           if (this.nameBuilder_.isEmpty()) {
/*       */             this.nameBuilder_.dispose();
/*       */             this.nameBuilder_ = null;
/*       */             this.name_ = other.name_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.nameBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getNameFieldBuilder() : null;
/*       */           } else {
/*       */             this.nameBuilder_.addAllMessages(other.name_);
/*       */           } 
/*       */         } 
/*       */         if (other.hasIdentifierValue())
/*       */           setIdentifierValue(other.getIdentifierValue()); 
/*       */         if (other.hasPositiveIntValue())
/*       */           setPositiveIntValue(other.getPositiveIntValue()); 
/*       */         if (other.hasNegativeIntValue())
/*       */           setNegativeIntValue(other.getNegativeIntValue()); 
/*       */         if (other.hasDoubleValue())
/*       */           setDoubleValue(other.getDoubleValue()); 
/*       */         if (other.hasStringValue())
/*       */           setStringValue(other.getStringValue()); 
/*       */         if (other.hasAggregateValue())
/*       */           setAggregateValue(other.getAggregateValue()); 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         for (int i = 0; i < getNameCount(); i++) {
/*       */           if (!getName(i).isInitialized())
/*       */             return false; 
/*       */         } 
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.UninterpretedOption.NamePart.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 18:
/*       */               subBuilder = DescriptorProtos.UninterpretedOption.NamePart.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addName(subBuilder.buildPartial());
/*       */               break;
/*       */             case 26:
/*       */               this.bitField0_ |= 0x2;
/*       */               this.identifierValue_ = input.readBytes();
/*       */               break;
/*       */             case 32:
/*       */               this.bitField0_ |= 0x4;
/*       */               this.positiveIntValue_ = input.readUInt64();
/*       */               break;
/*       */             case 40:
/*       */               this.bitField0_ |= 0x8;
/*       */               this.negativeIntValue_ = input.readInt64();
/*       */               break;
/*       */             case 49:
/*       */               this.bitField0_ |= 0x10;
/*       */               this.doubleValue_ = input.readDouble();
/*       */               break;
/*       */             case 58:
/*       */               this.bitField0_ |= 0x20;
/*       */               this.stringValue_ = input.readBytes();
/*       */               break;
/*       */             case 66:
/*       */               this.bitField0_ |= 0x40;
/*       */               this.aggregateValue_ = input.readBytes();
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureNameIsMutable() {
/*       */         if ((this.bitField0_ & 0x1) != 1) {
/*       */           this.name_ = new ArrayList<DescriptorProtos.UninterpretedOption.NamePart>(this.name_);
/*       */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.NamePart> getNameList() {
/*       */         if (this.nameBuilder_ == null)
/*       */           return Collections.unmodifiableList(this.name_); 
/*       */         return this.nameBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getNameCount() {
/*       */         if (this.nameBuilder_ == null)
/*       */           return this.name_.size(); 
/*       */         return this.nameBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.NamePart getName(int index) {
/*       */         if (this.nameBuilder_ == null)
/*       */           return this.name_.get(index); 
/*       */         return this.nameBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setName(int index, DescriptorProtos.UninterpretedOption.NamePart value) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNameIsMutable();
/*       */           this.name_.set(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.setMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder setName(int index, DescriptorProtos.UninterpretedOption.NamePart.Builder builderForValue) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           ensureNameIsMutable();
/*       */           this.name_.set(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addName(DescriptorProtos.UninterpretedOption.NamePart value) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNameIsMutable();
/*       */           this.name_.add(value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.addMessage(value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addName(int index, DescriptorProtos.UninterpretedOption.NamePart value) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           if (value == null)
/*       */             throw new NullPointerException(); 
/*       */           ensureNameIsMutable();
/*       */           this.name_.add(index, value);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.addMessage(index, value);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addName(DescriptorProtos.UninterpretedOption.NamePart.Builder builderForValue) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           ensureNameIsMutable();
/*       */           this.name_.add(builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.addMessage(builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addName(int index, DescriptorProtos.UninterpretedOption.NamePart.Builder builderForValue) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           ensureNameIsMutable();
/*       */           this.name_.add(index, builderForValue.build());
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllName(Iterable<? extends DescriptorProtos.UninterpretedOption.NamePart> values) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           ensureNameIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.name_);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.addAllMessages(values);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearName() {
/*       */         if (this.nameBuilder_ == null) {
/*       */           this.name_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeName(int index) {
/*       */         if (this.nameBuilder_ == null) {
/*       */           ensureNameIsMutable();
/*       */           this.name_.remove(index);
/*       */           onChanged();
/*       */         } else {
/*       */           this.nameBuilder_.remove(index);
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.NamePart.Builder getNameBuilder(int index) {
/*       */         return getNameFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.NamePartOrBuilder getNameOrBuilder(int index) {
/*       */         if (this.nameBuilder_ == null)
/*       */           return this.name_.get(index); 
/*       */         return this.nameBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.UninterpretedOption.NamePartOrBuilder> getNameOrBuilderList() {
/*       */         if (this.nameBuilder_ != null)
/*       */           return this.nameBuilder_.getMessageOrBuilderList(); 
/*       */         return Collections.unmodifiableList((List)this.name_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.NamePart.Builder addNameBuilder() {
/*       */         return getNameFieldBuilder().addBuilder(DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.UninterpretedOption.NamePart.Builder addNameBuilder(int index) {
/*       */         return getNameFieldBuilder().addBuilder(index, DescriptorProtos.UninterpretedOption.NamePart.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.UninterpretedOption.NamePart.Builder> getNameBuilderList() {
/*       */         return getNameFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption.NamePart, DescriptorProtos.UninterpretedOption.NamePart.Builder, DescriptorProtos.UninterpretedOption.NamePartOrBuilder> getNameFieldBuilder() {
/*       */         if (this.nameBuilder_ == null) {
/*       */           this.nameBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.UninterpretedOption.NamePart, DescriptorProtos.UninterpretedOption.NamePart.Builder, DescriptorProtos.UninterpretedOption.NamePartOrBuilder>(this.name_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/*       */           this.name_ = null;
/*       */         } 
/*       */         return this.nameBuilder_;
/*       */       }
/*       */       
/*       */       public boolean hasIdentifierValue() {
/*       */         return ((this.bitField0_ & 0x2) == 2);
/*       */       }
/*       */       
/*       */       public String getIdentifierValue() {
/*       */         Object ref = this.identifierValue_;
/*       */         if (!(ref instanceof String)) {
/*       */           String s = ((ByteString)ref).toStringUtf8();
/*       */           this.identifierValue_ = s;
/*       */           return s;
/*       */         } 
/*       */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setIdentifierValue(String value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x2;
/*       */         this.identifierValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearIdentifierValue() {
/*       */         this.bitField0_ &= 0xFFFFFFFD;
/*       */         this.identifierValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getIdentifierValue();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       void setIdentifierValue(ByteString value) {
/*       */         this.bitField0_ |= 0x2;
/*       */         this.identifierValue_ = value;
/*       */         onChanged();
/*       */       }
/*       */       
/*       */       public boolean hasPositiveIntValue() {
/*       */         return ((this.bitField0_ & 0x4) == 4);
/*       */       }
/*       */       
/*       */       public long getPositiveIntValue() {
/*       */         return this.positiveIntValue_;
/*       */       }
/*       */       
/*       */       public Builder setPositiveIntValue(long value) {
/*       */         this.bitField0_ |= 0x4;
/*       */         this.positiveIntValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearPositiveIntValue() {
/*       */         this.bitField0_ &= 0xFFFFFFFB;
/*       */         this.positiveIntValue_ = 0L;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasNegativeIntValue() {
/*       */         return ((this.bitField0_ & 0x8) == 8);
/*       */       }
/*       */       
/*       */       public long getNegativeIntValue() {
/*       */         return this.negativeIntValue_;
/*       */       }
/*       */       
/*       */       public Builder setNegativeIntValue(long value) {
/*       */         this.bitField0_ |= 0x8;
/*       */         this.negativeIntValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearNegativeIntValue() {
/*       */         this.bitField0_ &= 0xFFFFFFF7;
/*       */         this.negativeIntValue_ = 0L;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasDoubleValue() {
/*       */         return ((this.bitField0_ & 0x10) == 16);
/*       */       }
/*       */       
/*       */       public double getDoubleValue() {
/*       */         return this.doubleValue_;
/*       */       }
/*       */       
/*       */       public Builder setDoubleValue(double value) {
/*       */         this.bitField0_ |= 0x10;
/*       */         this.doubleValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearDoubleValue() {
/*       */         this.bitField0_ &= 0xFFFFFFEF;
/*       */         this.doubleValue_ = 0.0D;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasStringValue() {
/*       */         return ((this.bitField0_ & 0x20) == 32);
/*       */       }
/*       */       
/*       */       public ByteString getStringValue() {
/*       */         return this.stringValue_;
/*       */       }
/*       */       
/*       */       public Builder setStringValue(ByteString value) {
/*       */         if (value == null)
/*       */           throw new NullPointerException(); 
/*       */         this.bitField0_ |= 0x20;
/*       */         this.stringValue_ = value;
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearStringValue() {
/*       */         this.bitField0_ &= 0xFFFFFFDF;
/*       */         this.stringValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getStringValue();
/*       */         onChanged();
/*       */         return this;
/*       */       }
/*       */       
/*       */       public boolean hasAggregateValue() {
/* 15570 */         return ((this.bitField0_ & 0x40) == 64);
/*       */       }
/*       */       
/*       */       public String getAggregateValue() {
/* 15573 */         Object ref = this.aggregateValue_;
/* 15574 */         if (!(ref instanceof String)) {
/* 15575 */           String s = ((ByteString)ref).toStringUtf8();
/* 15576 */           this.aggregateValue_ = s;
/* 15577 */           return s;
/*       */         } 
/* 15579 */         return (String)ref;
/*       */       }
/*       */       
/*       */       public Builder setAggregateValue(String value) {
/* 15583 */         if (value == null)
/* 15584 */           throw new NullPointerException(); 
/* 15586 */         this.bitField0_ |= 0x40;
/* 15587 */         this.aggregateValue_ = value;
/* 15588 */         onChanged();
/* 15589 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearAggregateValue() {
/* 15592 */         this.bitField0_ &= 0xFFFFFFBF;
/* 15593 */         this.aggregateValue_ = DescriptorProtos.UninterpretedOption.getDefaultInstance().getAggregateValue();
/* 15594 */         onChanged();
/* 15595 */         return this;
/*       */       }
/*       */       
/*       */       void setAggregateValue(ByteString value) {
/* 15598 */         this.bitField0_ |= 0x40;
/* 15599 */         this.aggregateValue_ = value;
/* 15600 */         onChanged();
/*       */       }
/*       */     }
/*       */     
/* 15607 */     private static final UninterpretedOption defaultInstance = new UninterpretedOption(true);
/*       */     
/*       */     private int bitField0_;
/*       */     
/*       */     public static final int NAME_FIELD_NUMBER = 2;
/*       */     
/*       */     private List<NamePart> name_;
/*       */     
/*       */     public static final int IDENTIFIER_VALUE_FIELD_NUMBER = 3;
/*       */     
/*       */     private Object identifierValue_;
/*       */     
/*       */     public static final int POSITIVE_INT_VALUE_FIELD_NUMBER = 4;
/*       */     
/*       */     private long positiveIntValue_;
/*       */     
/*       */     public static final int NEGATIVE_INT_VALUE_FIELD_NUMBER = 5;
/*       */     
/*       */     private long negativeIntValue_;
/*       */     
/*       */     public static final int DOUBLE_VALUE_FIELD_NUMBER = 6;
/*       */     
/*       */     private double doubleValue_;
/*       */     
/*       */     public static final int STRING_VALUE_FIELD_NUMBER = 7;
/*       */     
/*       */     private ByteString stringValue_;
/*       */     
/*       */     public static final int AGGREGATE_VALUE_FIELD_NUMBER = 8;
/*       */     
/*       */     private Object aggregateValue_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 15608 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static final class SourceCodeInfo extends GeneratedMessage implements SourceCodeInfoOrBuilder {
/*       */     private SourceCodeInfo(Builder builder) {
/* 15632 */       super(builder);
/* 16193 */       this.memoizedIsInitialized = -1;
/* 16211 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     private SourceCodeInfo(boolean noInit) {
/*       */       this.memoizedIsInitialized = -1;
/* 16211 */       this.memoizedSerializedSize = -1;
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo getDefaultInstance() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public SourceCodeInfo getDefaultInstanceForType() {
/*       */       return defaultInstance;
/*       */     }
/*       */     
/*       */     public static final Descriptors.Descriptor getDescriptor() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
/*       */     }
/*       */     
/*       */     protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */       return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
/*       */     }
/*       */     
/*       */     public static interface LocationOrBuilder extends MessageOrBuilder {
/*       */       List<Integer> getPathList();
/*       */       
/*       */       int getPathCount();
/*       */       
/*       */       int getPath(int param2Int);
/*       */       
/*       */       List<Integer> getSpanList();
/*       */       
/*       */       int getSpanCount();
/*       */       
/*       */       int getSpan(int param2Int);
/*       */     }
/*       */     
/*       */     public static final class Location extends GeneratedMessage implements LocationOrBuilder {
/*       */       private Location(Builder builder) {
/*       */         super(builder);
/*       */         this.pathMemoizedSerializedSize = -1;
/*       */         this.spanMemoizedSerializedSize = -1;
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       private Location(boolean noInit) {
/*       */         this.pathMemoizedSerializedSize = -1;
/*       */         this.spanMemoizedSerializedSize = -1;
/*       */         this.memoizedIsInitialized = -1;
/*       */         this.memoizedSerializedSize = -1;
/*       */       }
/*       */       
/*       */       public static Location getDefaultInstance() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public Location getDefaultInstanceForType() {
/*       */         return defaultInstance;
/*       */       }
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */         return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       public List<Integer> getPathList() {
/*       */         return this.path_;
/*       */       }
/*       */       
/*       */       public int getPathCount() {
/*       */         return this.path_.size();
/*       */       }
/*       */       
/*       */       public int getPath(int index) {
/*       */         return ((Integer)this.path_.get(index)).intValue();
/*       */       }
/*       */       
/*       */       public List<Integer> getSpanList() {
/*       */         return this.span_;
/*       */       }
/*       */       
/*       */       public int getSpanCount() {
/*       */         return this.span_.size();
/*       */       }
/*       */       
/*       */       public int getSpan(int index) {
/*       */         return ((Integer)this.span_.get(index)).intValue();
/*       */       }
/*       */       
/*       */       private void initFields() {
/*       */         this.path_ = Collections.emptyList();
/*       */         this.span_ = Collections.emptyList();
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         byte isInitialized = this.memoizedIsInitialized;
/*       */         if (isInitialized != -1)
/*       */           return (isInitialized == 1); 
/*       */         this.memoizedIsInitialized = 1;
/*       */         return true;
/*       */       }
/*       */       
/*       */       public void writeTo(CodedOutputStream output) throws IOException {
/*       */         getSerializedSize();
/*       */         if (getPathList().size() > 0) {
/*       */           output.writeRawVarint32(10);
/*       */           output.writeRawVarint32(this.pathMemoizedSerializedSize);
/*       */         } 
/*       */         int i;
/*       */         for (i = 0; i < this.path_.size(); i++)
/*       */           output.writeInt32NoTag(((Integer)this.path_.get(i)).intValue()); 
/*       */         if (getSpanList().size() > 0) {
/*       */           output.writeRawVarint32(18);
/*       */           output.writeRawVarint32(this.spanMemoizedSerializedSize);
/*       */         } 
/*       */         for (i = 0; i < this.span_.size(); i++)
/*       */           output.writeInt32NoTag(((Integer)this.span_.get(i)).intValue()); 
/*       */         getUnknownFields().writeTo(output);
/*       */       }
/*       */       
/*       */       public int getSerializedSize() {
/*       */         int size = this.memoizedSerializedSize;
/*       */         if (size != -1)
/*       */           return size; 
/*       */         size = 0;
/*       */         int dataSize = 0;
/*       */         int i;
/*       */         for (i = 0; i < this.path_.size(); i++)
/*       */           dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.path_.get(i)).intValue()); 
/*       */         size += dataSize;
/*       */         if (!getPathList().isEmpty()) {
/*       */           size++;
/*       */           size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*       */         } 
/*       */         this.pathMemoizedSerializedSize = dataSize;
/*       */         dataSize = 0;
/*       */         for (i = 0; i < this.span_.size(); i++)
/*       */           dataSize += CodedOutputStream.computeInt32SizeNoTag(((Integer)this.span_.get(i)).intValue()); 
/*       */         size += dataSize;
/*       */         if (!getSpanList().isEmpty()) {
/*       */           size++;
/*       */           size += CodedOutputStream.computeInt32SizeNoTag(dataSize);
/*       */         } 
/*       */         this.spanMemoizedSerializedSize = dataSize;
/*       */         size += getUnknownFields().getSerializedSize();
/*       */         this.memoizedSerializedSize = size;
/*       */         return size;
/*       */       }
/*       */       
/*       */       protected Object writeReplace() throws ObjectStreamException {
/*       */         return super.writeReplace();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(ByteString data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(byte[] data) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/*       */         return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(InputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseDelimitedFrom(InputStream input) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static Location parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         Builder builder = newBuilder();
/*       */         if (builder.mergeDelimitedFrom(input, extensionRegistry))
/*       */           return builder.buildParsed(); 
/*       */         return null;
/*       */       }
/*       */       
/*       */       public static Location parseFrom(CodedInputStream input) throws IOException {
/*       */         return newBuilder().mergeFrom(input).buildParsed();
/*       */       }
/*       */       
/*       */       public static Location parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder() {
/*       */         return Builder.create();
/*       */       }
/*       */       
/*       */       public Builder newBuilderForType() {
/*       */         return newBuilder();
/*       */       }
/*       */       
/*       */       public static Builder newBuilder(Location prototype) {
/*       */         return newBuilder().mergeFrom(prototype);
/*       */       }
/*       */       
/*       */       public Builder toBuilder() {
/*       */         return newBuilder(this);
/*       */       }
/*       */       
/*       */       protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/*       */         Builder builder = new Builder(parent);
/*       */         return builder;
/*       */       }
/*       */       
/*       */       public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.SourceCodeInfo.LocationOrBuilder {
/*       */         private int bitField0_;
/*       */         
/*       */         private List<Integer> path_;
/*       */         
/*       */         private List<Integer> span_;
/*       */         
/*       */         public static final Descriptors.Descriptor getDescriptor() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor;
/*       */         }
/*       */         
/*       */         protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/*       */           return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable;
/*       */         }
/*       */         
/*       */         private Builder() {
/*       */           this.path_ = Collections.emptyList();
/*       */           this.span_ = Collections.emptyList();
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private Builder(GeneratedMessage.BuilderParent parent) {
/*       */           super(parent);
/*       */           this.path_ = Collections.emptyList();
/*       */           this.span_ = Collections.emptyList();
/*       */           maybeForceBuilderInitialization();
/*       */         }
/*       */         
/*       */         private void maybeForceBuilderInitialization() {
/*       */           if (GeneratedMessage.alwaysUseFieldBuilders);
/*       */         }
/*       */         
/*       */         private static Builder create() {
/*       */           return new Builder();
/*       */         }
/*       */         
/*       */         public Builder clear() {
/*       */           super.clear();
/*       */           this.path_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           this.span_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clone() {
/*       */           return create().mergeFrom(buildPartial());
/*       */         }
/*       */         
/*       */         public Descriptors.Descriptor getDescriptorForType() {
/*       */           return DescriptorProtos.SourceCodeInfo.Location.getDescriptor();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.SourceCodeInfo.Location getDefaultInstanceForType() {
/*       */           return DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance();
/*       */         }
/*       */         
/*       */         public DescriptorProtos.SourceCodeInfo.Location build() {
/*       */           DescriptorProtos.SourceCodeInfo.Location result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         private DescriptorProtos.SourceCodeInfo.Location buildParsed() throws InvalidProtocolBufferException {
/*       */           DescriptorProtos.SourceCodeInfo.Location result = buildPartial();
/*       */           if (!result.isInitialized())
/*       */             throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */           return result;
/*       */         }
/*       */         
/*       */         public DescriptorProtos.SourceCodeInfo.Location buildPartial() {
/*       */           DescriptorProtos.SourceCodeInfo.Location result = new DescriptorProtos.SourceCodeInfo.Location(this);
/*       */           int from_bitField0_ = this.bitField0_;
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.path_ = Collections.unmodifiableList(this.path_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.path_ = this.path_;
/*       */           if ((this.bitField0_ & 0x2) == 2) {
/*       */             this.span_ = Collections.unmodifiableList(this.span_);
/*       */             this.bitField0_ &= 0xFFFFFFFD;
/*       */           } 
/*       */           result.span_ = this.span_;
/*       */           onBuilt();
/*       */           return result;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(Message other) {
/*       */           if (other instanceof DescriptorProtos.SourceCodeInfo.Location)
/*       */             return mergeFrom((DescriptorProtos.SourceCodeInfo.Location)other); 
/*       */           super.mergeFrom(other);
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(DescriptorProtos.SourceCodeInfo.Location other) {
/*       */           if (other == DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance())
/*       */             return this; 
/*       */           if (!other.path_.isEmpty()) {
/*       */             if (this.path_.isEmpty()) {
/*       */               this.path_ = other.path_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensurePathIsMutable();
/*       */               this.path_.addAll(other.path_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */           if (!other.span_.isEmpty()) {
/*       */             if (this.span_.isEmpty()) {
/*       */               this.span_ = other.span_;
/*       */               this.bitField0_ &= 0xFFFFFFFD;
/*       */             } else {
/*       */               ensureSpanIsMutable();
/*       */               this.span_.addAll(other.span_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */           mergeUnknownFields(other.getUnknownFields());
/*       */           return this;
/*       */         }
/*       */         
/*       */         public final boolean isInitialized() {
/*       */           return true;
/*       */         }
/*       */         
/*       */         public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */           UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */           while (true) {
/*       */             int length, limit, tag = input.readTag();
/*       */             switch (tag) {
/*       */               case 0:
/*       */                 setUnknownFields(unknownFields.build());
/*       */                 onChanged();
/*       */                 return this;
/*       */               case 8:
/*       */                 ensurePathIsMutable();
/*       */                 this.path_.add(Integer.valueOf(input.readInt32()));
/*       */                 break;
/*       */               case 10:
/*       */                 length = input.readRawVarint32();
/*       */                 limit = input.pushLimit(length);
/*       */                 while (input.getBytesUntilLimit() > 0)
/*       */                   addPath(input.readInt32()); 
/*       */                 input.popLimit(limit);
/*       */                 break;
/*       */               case 16:
/*       */                 ensureSpanIsMutable();
/*       */                 this.span_.add(Integer.valueOf(input.readInt32()));
/*       */                 break;
/*       */               case 18:
/*       */                 length = input.readRawVarint32();
/*       */                 limit = input.pushLimit(length);
/*       */                 while (input.getBytesUntilLimit() > 0)
/*       */                   addSpan(input.readInt32()); 
/*       */                 input.popLimit(limit);
/*       */                 break;
/*       */             } 
/*       */           } 
/*       */         }
/*       */         
/*       */         private void ensurePathIsMutable() {
/*       */           if ((this.bitField0_ & 0x1) != 1) {
/*       */             this.path_ = new ArrayList<Integer>(this.path_);
/*       */             this.bitField0_ |= 0x1;
/*       */           } 
/*       */         }
/*       */         
/*       */         public List<Integer> getPathList() {
/*       */           return Collections.unmodifiableList(this.path_);
/*       */         }
/*       */         
/*       */         public int getPathCount() {
/*       */           return this.path_.size();
/*       */         }
/*       */         
/*       */         public int getPath(int index) {
/*       */           return ((Integer)this.path_.get(index)).intValue();
/*       */         }
/*       */         
/*       */         public Builder setPath(int index, int value) {
/*       */           ensurePathIsMutable();
/*       */           this.path_.set(index, Integer.valueOf(value));
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder addPath(int value) {
/*       */           ensurePathIsMutable();
/*       */           this.path_.add(Integer.valueOf(value));
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder addAllPath(Iterable<? extends Integer> values) {
/*       */           ensurePathIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.path_);
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearPath() {
/*       */           this.path_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         private void ensureSpanIsMutable() {
/*       */           if ((this.bitField0_ & 0x2) != 2) {
/*       */             this.span_ = new ArrayList<Integer>(this.span_);
/*       */             this.bitField0_ |= 0x2;
/*       */           } 
/*       */         }
/*       */         
/*       */         public List<Integer> getSpanList() {
/*       */           return Collections.unmodifiableList(this.span_);
/*       */         }
/*       */         
/*       */         public int getSpanCount() {
/*       */           return this.span_.size();
/*       */         }
/*       */         
/*       */         public int getSpan(int index) {
/*       */           return ((Integer)this.span_.get(index)).intValue();
/*       */         }
/*       */         
/*       */         public Builder setSpan(int index, int value) {
/*       */           ensureSpanIsMutable();
/*       */           this.span_.set(index, Integer.valueOf(value));
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder addSpan(int value) {
/*       */           ensureSpanIsMutable();
/*       */           this.span_.add(Integer.valueOf(value));
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder addAllSpan(Iterable<? extends Integer> values) {
/*       */           ensureSpanIsMutable();
/*       */           GeneratedMessage.Builder.addAll(values, this.span_);
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */         
/*       */         public Builder clearSpan() {
/*       */           this.span_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFD;
/*       */           onChanged();
/*       */           return this;
/*       */         }
/*       */       }
/*       */       
/*       */       private static final Location defaultInstance = new Location(true);
/*       */       
/*       */       public static final int PATH_FIELD_NUMBER = 1;
/*       */       
/*       */       private List<Integer> path_;
/*       */       
/*       */       private int pathMemoizedSerializedSize;
/*       */       
/*       */       public static final int SPAN_FIELD_NUMBER = 2;
/*       */       
/*       */       private List<Integer> span_;
/*       */       
/*       */       private int spanMemoizedSerializedSize;
/*       */       
/*       */       private byte memoizedIsInitialized;
/*       */       
/*       */       private int memoizedSerializedSize;
/*       */       
/*       */       private static final long serialVersionUID = 0L;
/*       */       
/*       */       static {
/*       */         defaultInstance.initFields();
/*       */       }
/*       */     }
/*       */     
/*       */     public List<Location> getLocationList() {
/*       */       return this.location_;
/*       */     }
/*       */     
/*       */     public List<? extends LocationOrBuilder> getLocationOrBuilderList() {
/*       */       return (List)this.location_;
/*       */     }
/*       */     
/*       */     public int getLocationCount() {
/*       */       return this.location_.size();
/*       */     }
/*       */     
/*       */     public Location getLocation(int index) {
/*       */       return this.location_.get(index);
/*       */     }
/*       */     
/*       */     public LocationOrBuilder getLocationOrBuilder(int index) {
/*       */       return this.location_.get(index);
/*       */     }
/*       */     
/*       */     private void initFields() {
/*       */       this.location_ = Collections.emptyList();
/*       */     }
/*       */     
/*       */     public final boolean isInitialized() {
/*       */       byte isInitialized = this.memoizedIsInitialized;
/*       */       if (isInitialized != -1)
/*       */         return (isInitialized == 1); 
/*       */       this.memoizedIsInitialized = 1;
/*       */       return true;
/*       */     }
/*       */     
/*       */     public void writeTo(CodedOutputStream output) throws IOException {
/*       */       getSerializedSize();
/*       */       for (int i = 0; i < this.location_.size(); i++)
/*       */         output.writeMessage(1, this.location_.get(i)); 
/*       */       getUnknownFields().writeTo(output);
/*       */     }
/*       */     
/*       */     public int getSerializedSize() {
/* 16213 */       int size = this.memoizedSerializedSize;
/* 16214 */       if (size != -1)
/* 16214 */         return size; 
/* 16216 */       size = 0;
/* 16217 */       for (int i = 0; i < this.location_.size(); i++)
/* 16218 */         size += CodedOutputStream.computeMessageSize(1, this.location_.get(i)); 
/* 16221 */       size += getUnknownFields().getSerializedSize();
/* 16222 */       this.memoizedSerializedSize = size;
/* 16223 */       return size;
/*       */     }
/*       */     
/*       */     protected Object writeReplace() throws ObjectStreamException {
/* 16230 */       return super.writeReplace();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 16236 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 16242 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 16247 */       return newBuilder().mergeFrom(data).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 16253 */       return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(InputStream input) throws IOException {
/* 16258 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 16264 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseDelimitedFrom(InputStream input) throws IOException {
/* 16269 */       Builder builder = newBuilder();
/* 16270 */       if (builder.mergeDelimitedFrom(input))
/* 16271 */         return builder.buildParsed(); 
/* 16273 */       return null;
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 16280 */       Builder builder = newBuilder();
/* 16281 */       if (builder.mergeDelimitedFrom(input, extensionRegistry))
/* 16282 */         return builder.buildParsed(); 
/* 16284 */       return null;
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(CodedInputStream input) throws IOException {
/* 16290 */       return newBuilder().mergeFrom(input).buildParsed();
/*       */     }
/*       */     
/*       */     public static SourceCodeInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 16296 */       return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder() {
/* 16300 */       return Builder.create();
/*       */     }
/*       */     
/*       */     public Builder newBuilderForType() {
/* 16301 */       return newBuilder();
/*       */     }
/*       */     
/*       */     public static Builder newBuilder(SourceCodeInfo prototype) {
/* 16303 */       return newBuilder().mergeFrom(prototype);
/*       */     }
/*       */     
/*       */     public Builder toBuilder() {
/* 16305 */       return newBuilder(this);
/*       */     }
/*       */     
/*       */     protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
/* 16310 */       Builder builder = new Builder(parent);
/* 16311 */       return builder;
/*       */     }
/*       */     
/*       */     public static final class Builder extends GeneratedMessage.Builder<Builder> implements DescriptorProtos.SourceCodeInfoOrBuilder {
/*       */       private int bitField0_;
/*       */       
/*       */       private List<DescriptorProtos.SourceCodeInfo.Location> location_;
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.SourceCodeInfo.Location, DescriptorProtos.SourceCodeInfo.Location.Builder, DescriptorProtos.SourceCodeInfo.LocationOrBuilder> locationBuilder_;
/*       */       
/*       */       public static final Descriptors.Descriptor getDescriptor() {
/* 16318 */         return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor;
/*       */       }
/*       */       
/*       */       protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
/* 16323 */         return DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable;
/*       */       }
/*       */       
/*       */       private Builder() {
/* 16483 */         this.location_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private Builder(GeneratedMessage.BuilderParent parent) {
/*       */         super(parent);
/* 16483 */         this.location_ = Collections.emptyList();
/*       */         maybeForceBuilderInitialization();
/*       */       }
/*       */       
/*       */       private void maybeForceBuilderInitialization() {
/*       */         if (GeneratedMessage.alwaysUseFieldBuilders)
/*       */           getLocationFieldBuilder(); 
/*       */       }
/*       */       
/*       */       private static Builder create() {
/*       */         return new Builder();
/*       */       }
/*       */       
/*       */       public Builder clear() {
/*       */         super.clear();
/*       */         if (this.locationBuilder_ == null) {
/*       */           this.location_ = Collections.emptyList();
/*       */           this.bitField0_ &= 0xFFFFFFFE;
/*       */         } else {
/*       */           this.locationBuilder_.clear();
/*       */         } 
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder clone() {
/*       */         return create().mergeFrom(buildPartial());
/*       */       }
/*       */       
/*       */       public Descriptors.Descriptor getDescriptorForType() {
/*       */         return DescriptorProtos.SourceCodeInfo.getDescriptor();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo getDefaultInstanceForType() {
/*       */         return DescriptorProtos.SourceCodeInfo.getDefaultInstance();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo build() {
/*       */         DescriptorProtos.SourceCodeInfo result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       private DescriptorProtos.SourceCodeInfo buildParsed() throws InvalidProtocolBufferException {
/*       */         DescriptorProtos.SourceCodeInfo result = buildPartial();
/*       */         if (!result.isInitialized())
/*       */           throw newUninitializedMessageException(result).asInvalidProtocolBufferException(); 
/*       */         return result;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo buildPartial() {
/*       */         DescriptorProtos.SourceCodeInfo result = new DescriptorProtos.SourceCodeInfo(this);
/*       */         int from_bitField0_ = this.bitField0_;
/*       */         if (this.locationBuilder_ == null) {
/*       */           if ((this.bitField0_ & 0x1) == 1) {
/*       */             this.location_ = Collections.unmodifiableList(this.location_);
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */           } 
/*       */           result.location_ = this.location_;
/*       */         } else {
/*       */           result.location_ = this.locationBuilder_.build();
/*       */         } 
/*       */         onBuilt();
/*       */         return result;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(Message other) {
/*       */         if (other instanceof DescriptorProtos.SourceCodeInfo)
/*       */           return mergeFrom((DescriptorProtos.SourceCodeInfo)other); 
/*       */         super.mergeFrom(other);
/*       */         return this;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(DescriptorProtos.SourceCodeInfo other) {
/*       */         if (other == DescriptorProtos.SourceCodeInfo.getDefaultInstance())
/*       */           return this; 
/*       */         if (this.locationBuilder_ == null) {
/*       */           if (!other.location_.isEmpty()) {
/*       */             if (this.location_.isEmpty()) {
/*       */               this.location_ = other.location_;
/*       */               this.bitField0_ &= 0xFFFFFFFE;
/*       */             } else {
/*       */               ensureLocationIsMutable();
/*       */               this.location_.addAll(other.location_);
/*       */             } 
/*       */             onChanged();
/*       */           } 
/*       */         } else if (!other.location_.isEmpty()) {
/*       */           if (this.locationBuilder_.isEmpty()) {
/*       */             this.locationBuilder_.dispose();
/*       */             this.locationBuilder_ = null;
/*       */             this.location_ = other.location_;
/*       */             this.bitField0_ &= 0xFFFFFFFE;
/*       */             this.locationBuilder_ = GeneratedMessage.alwaysUseFieldBuilders ? getLocationFieldBuilder() : null;
/*       */           } else {
/*       */             this.locationBuilder_.addAllMessages(other.location_);
/*       */           } 
/*       */         } 
/*       */         mergeUnknownFields(other.getUnknownFields());
/*       */         return this;
/*       */       }
/*       */       
/*       */       public final boolean isInitialized() {
/*       */         return true;
/*       */       }
/*       */       
/*       */       public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*       */         UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*       */         while (true) {
/*       */           DescriptorProtos.SourceCodeInfo.Location.Builder subBuilder;
/*       */           int tag = input.readTag();
/*       */           switch (tag) {
/*       */             case 0:
/*       */               setUnknownFields(unknownFields.build());
/*       */               onChanged();
/*       */               return this;
/*       */             case 10:
/*       */               subBuilder = DescriptorProtos.SourceCodeInfo.Location.newBuilder();
/*       */               input.readMessage(subBuilder, extensionRegistry);
/*       */               addLocation(subBuilder.buildPartial());
/*       */               break;
/*       */           } 
/*       */         } 
/*       */       }
/*       */       
/*       */       private void ensureLocationIsMutable() {
/* 16486 */         if ((this.bitField0_ & 0x1) != 1) {
/* 16487 */           this.location_ = new ArrayList<DescriptorProtos.SourceCodeInfo.Location>(this.location_);
/* 16488 */           this.bitField0_ |= 0x1;
/*       */         } 
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.SourceCodeInfo.Location> getLocationList() {
/* 16496 */         if (this.locationBuilder_ == null)
/* 16497 */           return Collections.unmodifiableList(this.location_); 
/* 16499 */         return this.locationBuilder_.getMessageList();
/*       */       }
/*       */       
/*       */       public int getLocationCount() {
/* 16503 */         if (this.locationBuilder_ == null)
/* 16504 */           return this.location_.size(); 
/* 16506 */         return this.locationBuilder_.getCount();
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.Location getLocation(int index) {
/* 16510 */         if (this.locationBuilder_ == null)
/* 16511 */           return this.location_.get(index); 
/* 16513 */         return this.locationBuilder_.getMessage(index);
/*       */       }
/*       */       
/*       */       public Builder setLocation(int index, DescriptorProtos.SourceCodeInfo.Location value) {
/* 16518 */         if (this.locationBuilder_ == null) {
/* 16519 */           if (value == null)
/* 16520 */             throw new NullPointerException(); 
/* 16522 */           ensureLocationIsMutable();
/* 16523 */           this.location_.set(index, value);
/* 16524 */           onChanged();
/*       */         } else {
/* 16526 */           this.locationBuilder_.setMessage(index, value);
/*       */         } 
/* 16528 */         return this;
/*       */       }
/*       */       
/*       */       public Builder setLocation(int index, DescriptorProtos.SourceCodeInfo.Location.Builder builderForValue) {
/* 16532 */         if (this.locationBuilder_ == null) {
/* 16533 */           ensureLocationIsMutable();
/* 16534 */           this.location_.set(index, builderForValue.build());
/* 16535 */           onChanged();
/*       */         } else {
/* 16537 */           this.locationBuilder_.setMessage(index, builderForValue.build());
/*       */         } 
/* 16539 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addLocation(DescriptorProtos.SourceCodeInfo.Location value) {
/* 16542 */         if (this.locationBuilder_ == null) {
/* 16543 */           if (value == null)
/* 16544 */             throw new NullPointerException(); 
/* 16546 */           ensureLocationIsMutable();
/* 16547 */           this.location_.add(value);
/* 16548 */           onChanged();
/*       */         } else {
/* 16550 */           this.locationBuilder_.addMessage(value);
/*       */         } 
/* 16552 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addLocation(int index, DescriptorProtos.SourceCodeInfo.Location value) {
/* 16556 */         if (this.locationBuilder_ == null) {
/* 16557 */           if (value == null)
/* 16558 */             throw new NullPointerException(); 
/* 16560 */           ensureLocationIsMutable();
/* 16561 */           this.location_.add(index, value);
/* 16562 */           onChanged();
/*       */         } else {
/* 16564 */           this.locationBuilder_.addMessage(index, value);
/*       */         } 
/* 16566 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addLocation(DescriptorProtos.SourceCodeInfo.Location.Builder builderForValue) {
/* 16570 */         if (this.locationBuilder_ == null) {
/* 16571 */           ensureLocationIsMutable();
/* 16572 */           this.location_.add(builderForValue.build());
/* 16573 */           onChanged();
/*       */         } else {
/* 16575 */           this.locationBuilder_.addMessage(builderForValue.build());
/*       */         } 
/* 16577 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addLocation(int index, DescriptorProtos.SourceCodeInfo.Location.Builder builderForValue) {
/* 16581 */         if (this.locationBuilder_ == null) {
/* 16582 */           ensureLocationIsMutable();
/* 16583 */           this.location_.add(index, builderForValue.build());
/* 16584 */           onChanged();
/*       */         } else {
/* 16586 */           this.locationBuilder_.addMessage(index, builderForValue.build());
/*       */         } 
/* 16588 */         return this;
/*       */       }
/*       */       
/*       */       public Builder addAllLocation(Iterable<? extends DescriptorProtos.SourceCodeInfo.Location> values) {
/* 16592 */         if (this.locationBuilder_ == null) {
/* 16593 */           ensureLocationIsMutable();
/* 16594 */           GeneratedMessage.Builder.addAll(values, this.location_);
/* 16595 */           onChanged();
/*       */         } else {
/* 16597 */           this.locationBuilder_.addAllMessages(values);
/*       */         } 
/* 16599 */         return this;
/*       */       }
/*       */       
/*       */       public Builder clearLocation() {
/* 16602 */         if (this.locationBuilder_ == null) {
/* 16603 */           this.location_ = Collections.emptyList();
/* 16604 */           this.bitField0_ &= 0xFFFFFFFE;
/* 16605 */           onChanged();
/*       */         } else {
/* 16607 */           this.locationBuilder_.clear();
/*       */         } 
/* 16609 */         return this;
/*       */       }
/*       */       
/*       */       public Builder removeLocation(int index) {
/* 16612 */         if (this.locationBuilder_ == null) {
/* 16613 */           ensureLocationIsMutable();
/* 16614 */           this.location_.remove(index);
/* 16615 */           onChanged();
/*       */         } else {
/* 16617 */           this.locationBuilder_.remove(index);
/*       */         } 
/* 16619 */         return this;
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.Location.Builder getLocationBuilder(int index) {
/* 16623 */         return getLocationFieldBuilder().getBuilder(index);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.LocationOrBuilder getLocationOrBuilder(int index) {
/* 16627 */         if (this.locationBuilder_ == null)
/* 16628 */           return this.location_.get(index); 
/* 16629 */         return this.locationBuilder_.getMessageOrBuilder(index);
/*       */       }
/*       */       
/*       */       public List<? extends DescriptorProtos.SourceCodeInfo.LocationOrBuilder> getLocationOrBuilderList() {
/* 16634 */         if (this.locationBuilder_ != null)
/* 16635 */           return this.locationBuilder_.getMessageOrBuilderList(); 
/* 16637 */         return Collections.unmodifiableList((List)this.location_);
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.Location.Builder addLocationBuilder() {
/* 16641 */         return getLocationFieldBuilder().addBuilder(DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public DescriptorProtos.SourceCodeInfo.Location.Builder addLocationBuilder(int index) {
/* 16646 */         return getLocationFieldBuilder().addBuilder(index, DescriptorProtos.SourceCodeInfo.Location.getDefaultInstance());
/*       */       }
/*       */       
/*       */       public List<DescriptorProtos.SourceCodeInfo.Location.Builder> getLocationBuilderList() {
/* 16651 */         return getLocationFieldBuilder().getBuilderList();
/*       */       }
/*       */       
/*       */       private RepeatedFieldBuilder<DescriptorProtos.SourceCodeInfo.Location, DescriptorProtos.SourceCodeInfo.Location.Builder, DescriptorProtos.SourceCodeInfo.LocationOrBuilder> getLocationFieldBuilder() {
/* 16656 */         if (this.locationBuilder_ == null) {
/* 16657 */           this.locationBuilder_ = new RepeatedFieldBuilder<DescriptorProtos.SourceCodeInfo.Location, DescriptorProtos.SourceCodeInfo.Location.Builder, DescriptorProtos.SourceCodeInfo.LocationOrBuilder>(this.location_, ((this.bitField0_ & 0x1) == 1), getParentForChildren(), isClean());
/* 16663 */           this.location_ = null;
/*       */         } 
/* 16665 */         return this.locationBuilder_;
/*       */       }
/*       */     }
/*       */     
/* 16672 */     private static final SourceCodeInfo defaultInstance = new SourceCodeInfo(true);
/*       */     
/*       */     public static final int LOCATION_FIELD_NUMBER = 1;
/*       */     
/*       */     private List<Location> location_;
/*       */     
/*       */     private byte memoizedIsInitialized;
/*       */     
/*       */     private int memoizedSerializedSize;
/*       */     
/*       */     private static final long serialVersionUID = 0L;
/*       */     
/*       */     static {
/* 16673 */       defaultInstance.initFields();
/*       */     }
/*       */   }
/*       */   
/*       */   public static Descriptors.FileDescriptor getDescriptor() {
/* 16782 */     return descriptor;
/*       */   }
/*       */   
/*       */   static {
/* 16787 */     String[] descriptorData = { "\n google/protobuf/descriptor.proto\022\017google.protobuf\"G\n\021FileDescriptorSet\0222\n\004file\030\001 \003(\0132$.google.protobuf.FileDescriptorProto\"\003\n\023FileDescriptorProto\022\f\n\004name\030\001 \001(\t\022\017\n\007package\030\002 \001(\t\022\022\n\ndependency\030\003 \003(\t\0226\n\fmessage_type\030\004 \003(\0132 .google.protobuf.DescriptorProto\0227\n\tenum_type\030\005 \003(\0132$.google.protobuf.EnumDescriptorProto\0228\n\007service\030\006 \003(\0132'.google.protobuf.ServiceDescriptorProto\0228\n\textension\030\007 \003(\0132%.google.p", "rotobuf.FieldDescriptorProto\022-\n\007options\030\b \001(\0132\034.google.protobuf.FileOptions\0229\n\020source_code_info\030\t \001(\0132\037.google.protobuf.SourceCodeInfo\"©\003\n\017DescriptorProto\022\f\n\004name\030\001 \001(\t\0224\n\005field\030\002 \003(\0132%.google.protobuf.FieldDescriptorProto\0228\n\textension\030\006 \003(\0132%.google.protobuf.FieldDescriptorProto\0225\n\013nested_type\030\003 \003(\0132 .google.protobuf.DescriptorProto\0227\n\tenum_type\030\004 \003(\0132$.google.protobuf.EnumDescriptorProto\022H\n\017exte", "nsion_range\030\005 \003(\0132/.google.protobuf.DescriptorProto.ExtensionRange\0220\n\007options\030\007 \001(\0132\037.google.protobuf.MessageOptions\032,\n\016ExtensionRange\022\r\n\005start\030\001 \001(\005\022\013\n\003end\030\002 \001(\005\"\005\n\024FieldDescriptorProto\022\f\n\004name\030\001 \001(\t\022\016\n\006number\030\003 \001(\005\022:\n\005label\030\004 \001(\0162+.google.protobuf.FieldDescriptorProto.Label\0228\n\004type\030\005 \001(\0162*.google.protobuf.FieldDescriptorProto.Type\022\021\n\ttype_name\030\006 \001(\t\022\020\n\bextendee\030\002 \001(\t\022\025\n\rdefault_value\030\007 \001(\t\022.\n\007o", "ptions\030\b \001(\0132\035.google.protobuf.FieldOptions\"¶\002\n\004Type\022\017\n\013TYPE_DOUBLE\020\001\022\016\n\nTYPE_FLOAT\020\002\022\016\n\nTYPE_INT64\020\003\022\017\n\013TYPE_UINT64\020\004\022\016\n\nTYPE_INT32\020\005\022\020\n\fTYPE_FIXED64\020\006\022\020\n\fTYPE_FIXED32\020\007\022\r\n\tTYPE_BOOL\020\b\022\017\n\013TYPE_STRING\020\t\022\016\n\nTYPE_GROUP\020\n\022\020\n\fTYPE_MESSAGE\020\013\022\016\n\nTYPE_BYTES\020\f\022\017\n\013TYPE_UINT32\020\r\022\r\n\tTYPE_ENUM\020\016\022\021\n\rTYPE_SFIXED32\020\017\022\021\n\rTYPE_SFIXED64\020\020\022\017\n\013TYPE_SINT32\020\021\022\017\n\013TYPE_SINT64\020\022\"C\n\005Label\022\022\n\016LABEL_OPTIONAL\020\001\022\022\n\016LABEL_REQUI", "RED\020\002\022\022\n\016LABEL_REPEATED\020\003\"\001\n\023EnumDescriptorProto\022\f\n\004name\030\001 \001(\t\0228\n\005value\030\002 \003(\0132).google.protobuf.EnumValueDescriptorProto\022-\n\007options\030\003 \001(\0132\034.google.protobuf.EnumOptions\"l\n\030EnumValueDescriptorProto\022\f\n\004name\030\001 \001(\t\022\016\n\006number\030\002 \001(\005\0222\n\007options\030\003 \001(\0132!.google.protobuf.EnumValueOptions\"\001\n\026ServiceDescriptorProto\022\f\n\004name\030\001 \001(\t\0226\n\006method\030\002 \003(\0132&.google.protobuf.MethodDescriptorProto\0220\n\007options\030\003 \001(\0132\037.googl", "e.protobuf.ServiceOptions\"\n\025MethodDescriptorProto\022\f\n\004name\030\001 \001(\t\022\022\n\ninput_type\030\002 \001(\t\022\023\n\013output_type\030\003 \001(\t\022/\n\007options\030\004 \001(\0132\036.google.protobuf.MethodOptions\"Õ\003\n\013FileOptions\022\024\n\fjava_package\030\001 \001(\t\022\034\n\024java_outer_classname\030\b \001(\t\022\"\n\023java_multiple_files\030\n \001(\b:\005false\022,\n\035java_generate_equals_and_hash\030\024 \001(\b:\005false\022F\n\foptimize_for\030\t \001(\0162).google.protobuf.FileOptions.OptimizeMode:\005SPEED\022\"\n\023cc_generic_services\030", "\020 \001(\b:\005false\022$\n\025java_generic_services\030\021 \001(\b:\005false\022\"\n\023py_generic_services\030\022 \001(\b:\005false\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption\":\n\fOptimizeMode\022\t\n\005SPEED\020\001\022\r\n\tCODE_SIZE\020\002\022\020\n\fLITE_RUNTIME\020\003*\t\bè\007\020\002\"¸\001\n\016MessageOptions\022&\n\027message_set_wire_format\030\001 \001(\b:\005false\022.\n\037no_standard_descriptor_accessor\030\002 \001(\b:\005false\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOpti", "on*\t\bè\007\020\002\"\002\n\fFieldOptions\022:\n\005ctype\030\001 \001(\0162#.google.protobuf.FieldOptions.CType:\006STRING\022\016\n\006packed\030\002 \001(\b\022\031\n\ndeprecated\030\003 \001(\b:\005false\022\034\n\024experimental_map_key\030\t \001(\t\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption\"/\n\005CType\022\n\n\006STRING\020\000\022\b\n\004CORD\020\001\022\020\n\fSTRING_PIECE\020\002*\t\bè\007\020\002\"]\n\013EnumOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"b\n\020EnumValue", "Options\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"`\n\016ServiceOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"_\n\rMethodOptions\022C\n\024uninterpreted_option\030ç\007 \003(\0132$.google.protobuf.UninterpretedOption*\t\bè\007\020\002\"\002\n\023UninterpretedOption\022;\n\004name\030\002 \003(\0132-.google.protobuf.UninterpretedOption.NamePart\022\030\n\020identifier_value\030\003 \001(\t\022\032\n\022pos", "itive_int_value\030\004 \001(\004\022\032\n\022negative_int_value\030\005 \001(\003\022\024\n\fdouble_value\030\006 \001(\001\022\024\n\fstring_value\030\007 \001(\f\022\027\n\017aggregate_value\030\b \001(\t\0323\n\bNamePart\022\021\n\tname_part\030\001 \002(\t\022\024\n\fis_extension\030\002 \002(\b\"|\n\016SourceCodeInfo\022:\n\blocation\030\001 \003(\0132(.google.protobuf.SourceCodeInfo.Location\032.\n\bLocation\022\020\n\004path\030\001 \003(\005B\002\020\001\022\020\n\004span\030\002 \003(\005B\002\020\001B)\n\023com.google.protobufB\020DescriptorProtosH\001" };
/* 16888 */     Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
/*       */         public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
/* 16892 */           DescriptorProtos.descriptor = root;
/* 16893 */           DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(0);
/* 16895 */           DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileDescriptorSet_descriptor, new String[] { "File" }, (Class)DescriptorProtos.FileDescriptorSet.class, (Class)DescriptorProtos.FileDescriptorSet.Builder.class);
/* 16901 */           DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(1);
/* 16903 */           DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileDescriptorProto_descriptor, new String[] { "Name", "Package", "Dependency", "MessageType", "EnumType", "Service", "Extension", "Options", "SourceCodeInfo" }, (Class)DescriptorProtos.FileDescriptorProto.class, (Class)DescriptorProtos.FileDescriptorProto.Builder.class);
/* 16909 */           DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(2);
/* 16911 */           DescriptorProtos.internal_static_google_protobuf_DescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor, new String[] { "Name", "Field", "Extension", "NestedType", "EnumType", "ExtensionRange", "Options" }, (Class)DescriptorProtos.DescriptorProto.class, (Class)DescriptorProtos.DescriptorProto.Builder.class);
/* 16917 */           DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor = DescriptorProtos.internal_static_google_protobuf_DescriptorProto_descriptor.getNestedTypes().get(0);
/* 16919 */           DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_DescriptorProto_ExtensionRange_descriptor, new String[] { "Start", "End" }, (Class)DescriptorProtos.DescriptorProto.ExtensionRange.class, (Class)DescriptorProtos.DescriptorProto.ExtensionRange.Builder.class);
/* 16925 */           DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(3);
/* 16927 */           DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FieldDescriptorProto_descriptor, new String[] { "Name", "Number", "Label", "Type", "TypeName", "Extendee", "DefaultValue", "Options" }, (Class)DescriptorProtos.FieldDescriptorProto.class, (Class)DescriptorProtos.FieldDescriptorProto.Builder.class);
/* 16933 */           DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(4);
/* 16935 */           DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumDescriptorProto_descriptor, new String[] { "Name", "Value", "Options" }, (Class)DescriptorProtos.EnumDescriptorProto.class, (Class)DescriptorProtos.EnumDescriptorProto.Builder.class);
/* 16941 */           DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(5);
/* 16943 */           DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumValueDescriptorProto_descriptor, new String[] { "Name", "Number", "Options" }, (Class)DescriptorProtos.EnumValueDescriptorProto.class, (Class)DescriptorProtos.EnumValueDescriptorProto.Builder.class);
/* 16949 */           DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(6);
/* 16951 */           DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_ServiceDescriptorProto_descriptor, new String[] { "Name", "Method", "Options" }, (Class)DescriptorProtos.ServiceDescriptorProto.class, (Class)DescriptorProtos.ServiceDescriptorProto.Builder.class);
/* 16957 */           DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(7);
/* 16959 */           DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MethodDescriptorProto_descriptor, new String[] { "Name", "InputType", "OutputType", "Options" }, (Class)DescriptorProtos.MethodDescriptorProto.class, (Class)DescriptorProtos.MethodDescriptorProto.Builder.class);
/* 16965 */           DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(8);
/* 16967 */           DescriptorProtos.internal_static_google_protobuf_FileOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FileOptions_descriptor, new String[] { "JavaPackage", "JavaOuterClassname", "JavaMultipleFiles", "JavaGenerateEqualsAndHash", "OptimizeFor", "CcGenericServices", "JavaGenericServices", "PyGenericServices", "UninterpretedOption" }, (Class)DescriptorProtos.FileOptions.class, (Class)DescriptorProtos.FileOptions.Builder.class);
/* 16973 */           DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(9);
/* 16975 */           DescriptorProtos.internal_static_google_protobuf_MessageOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MessageOptions_descriptor, new String[] { "MessageSetWireFormat", "NoStandardDescriptorAccessor", "UninterpretedOption" }, (Class)DescriptorProtos.MessageOptions.class, (Class)DescriptorProtos.MessageOptions.Builder.class);
/* 16981 */           DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(10);
/* 16983 */           DescriptorProtos.internal_static_google_protobuf_FieldOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_FieldOptions_descriptor, new String[] { "Ctype", "Packed", "Deprecated", "ExperimentalMapKey", "UninterpretedOption" }, (Class)DescriptorProtos.FieldOptions.class, (Class)DescriptorProtos.FieldOptions.Builder.class);
/* 16989 */           DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(11);
/* 16991 */           DescriptorProtos.internal_static_google_protobuf_EnumOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumOptions_descriptor, new String[] { "UninterpretedOption" }, (Class)DescriptorProtos.EnumOptions.class, (Class)DescriptorProtos.EnumOptions.Builder.class);
/* 16997 */           DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(12);
/* 16999 */           DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_EnumValueOptions_descriptor, new String[] { "UninterpretedOption" }, (Class)DescriptorProtos.EnumValueOptions.class, (Class)DescriptorProtos.EnumValueOptions.Builder.class);
/* 17005 */           DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(13);
/* 17007 */           DescriptorProtos.internal_static_google_protobuf_ServiceOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_ServiceOptions_descriptor, new String[] { "UninterpretedOption" }, (Class)DescriptorProtos.ServiceOptions.class, (Class)DescriptorProtos.ServiceOptions.Builder.class);
/* 17013 */           DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(14);
/* 17015 */           DescriptorProtos.internal_static_google_protobuf_MethodOptions_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_MethodOptions_descriptor, new String[] { "UninterpretedOption" }, (Class)DescriptorProtos.MethodOptions.class, (Class)DescriptorProtos.MethodOptions.Builder.class);
/* 17021 */           DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(15);
/* 17023 */           DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor, new String[] { "Name", "IdentifierValue", "PositiveIntValue", "NegativeIntValue", "DoubleValue", "StringValue", "AggregateValue" }, (Class)DescriptorProtos.UninterpretedOption.class, (Class)DescriptorProtos.UninterpretedOption.Builder.class);
/* 17029 */           DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor = DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_descriptor.getNestedTypes().get(0);
/* 17031 */           DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_UninterpretedOption_NamePart_descriptor, new String[] { "NamePart", "IsExtension" }, (Class)DescriptorProtos.UninterpretedOption.NamePart.class, (Class)DescriptorProtos.UninterpretedOption.NamePart.Builder.class);
/* 17037 */           DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor = DescriptorProtos.getDescriptor().getMessageTypes().get(16);
/* 17039 */           DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor, new String[] { "Location" }, (Class)DescriptorProtos.SourceCodeInfo.class, (Class)DescriptorProtos.SourceCodeInfo.Builder.class);
/* 17045 */           DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor = DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_descriptor.getNestedTypes().get(0);
/* 17047 */           DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(DescriptorProtos.internal_static_google_protobuf_SourceCodeInfo_Location_descriptor, new String[] { "Path", "Span" }, (Class)DescriptorProtos.SourceCodeInfo.Location.class, (Class)DescriptorProtos.SourceCodeInfo.Location.Builder.class);
/* 17053 */           return null;
/*       */         }
/*       */       };
/* 17056 */     Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
/*       */   }
/*       */   
/*       */   public static interface SourceCodeInfoOrBuilder extends MessageOrBuilder {
/*       */     List<DescriptorProtos.SourceCodeInfo.Location> getLocationList();
/*       */     
/*       */     DescriptorProtos.SourceCodeInfo.Location getLocation(int param1Int);
/*       */     
/*       */     int getLocationCount();
/*       */     
/*       */     List<? extends DescriptorProtos.SourceCodeInfo.LocationOrBuilder> getLocationOrBuilderList();
/*       */     
/*       */     DescriptorProtos.SourceCodeInfo.LocationOrBuilder getLocationOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface UninterpretedOptionOrBuilder extends MessageOrBuilder {
/*       */     List<DescriptorProtos.UninterpretedOption.NamePart> getNameList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption.NamePart getName(int param1Int);
/*       */     
/*       */     int getNameCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOption.NamePartOrBuilder> getNameOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption.NamePartOrBuilder getNameOrBuilder(int param1Int);
/*       */     
/*       */     boolean hasIdentifierValue();
/*       */     
/*       */     String getIdentifierValue();
/*       */     
/*       */     boolean hasPositiveIntValue();
/*       */     
/*       */     long getPositiveIntValue();
/*       */     
/*       */     boolean hasNegativeIntValue();
/*       */     
/*       */     long getNegativeIntValue();
/*       */     
/*       */     boolean hasDoubleValue();
/*       */     
/*       */     double getDoubleValue();
/*       */     
/*       */     boolean hasStringValue();
/*       */     
/*       */     ByteString getStringValue();
/*       */     
/*       */     boolean hasAggregateValue();
/*       */     
/*       */     String getAggregateValue();
/*       */   }
/*       */   
/*       */   public static interface MethodOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<MethodOptions> {
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface ServiceOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<ServiceOptions> {
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface EnumValueOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<EnumValueOptions> {
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface EnumOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<EnumOptions> {
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface FieldOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<FieldOptions> {
/*       */     boolean hasCtype();
/*       */     
/*       */     DescriptorProtos.FieldOptions.CType getCtype();
/*       */     
/*       */     boolean hasPacked();
/*       */     
/*       */     boolean getPacked();
/*       */     
/*       */     boolean hasDeprecated();
/*       */     
/*       */     boolean getDeprecated();
/*       */     
/*       */     boolean hasExperimentalMapKey();
/*       */     
/*       */     String getExperimentalMapKey();
/*       */     
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface MessageOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<MessageOptions> {
/*       */     boolean hasMessageSetWireFormat();
/*       */     
/*       */     boolean getMessageSetWireFormat();
/*       */     
/*       */     boolean hasNoStandardDescriptorAccessor();
/*       */     
/*       */     boolean getNoStandardDescriptorAccessor();
/*       */     
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface FileOptionsOrBuilder extends GeneratedMessage.ExtendableMessageOrBuilder<FileOptions> {
/*       */     boolean hasJavaPackage();
/*       */     
/*       */     String getJavaPackage();
/*       */     
/*       */     boolean hasJavaOuterClassname();
/*       */     
/*       */     String getJavaOuterClassname();
/*       */     
/*       */     boolean hasJavaMultipleFiles();
/*       */     
/*       */     boolean getJavaMultipleFiles();
/*       */     
/*       */     boolean hasJavaGenerateEqualsAndHash();
/*       */     
/*       */     boolean getJavaGenerateEqualsAndHash();
/*       */     
/*       */     boolean hasOptimizeFor();
/*       */     
/*       */     DescriptorProtos.FileOptions.OptimizeMode getOptimizeFor();
/*       */     
/*       */     boolean hasCcGenericServices();
/*       */     
/*       */     boolean getCcGenericServices();
/*       */     
/*       */     boolean hasJavaGenericServices();
/*       */     
/*       */     boolean getJavaGenericServices();
/*       */     
/*       */     boolean hasPyGenericServices();
/*       */     
/*       */     boolean getPyGenericServices();
/*       */     
/*       */     List<DescriptorProtos.UninterpretedOption> getUninterpretedOptionList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOption getUninterpretedOption(int param1Int);
/*       */     
/*       */     int getUninterpretedOptionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.UninterpretedOptionOrBuilder> getUninterpretedOptionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.UninterpretedOptionOrBuilder getUninterpretedOptionOrBuilder(int param1Int);
/*       */   }
/*       */   
/*       */   public static interface MethodDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     boolean hasInputType();
/*       */     
/*       */     String getInputType();
/*       */     
/*       */     boolean hasOutputType();
/*       */     
/*       */     String getOutputType();
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.MethodOptions getOptions();
/*       */     
/*       */     DescriptorProtos.MethodOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface ServiceDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     List<DescriptorProtos.MethodDescriptorProto> getMethodList();
/*       */     
/*       */     DescriptorProtos.MethodDescriptorProto getMethod(int param1Int);
/*       */     
/*       */     int getMethodCount();
/*       */     
/*       */     List<? extends DescriptorProtos.MethodDescriptorProtoOrBuilder> getMethodOrBuilderList();
/*       */     
/*       */     DescriptorProtos.MethodDescriptorProtoOrBuilder getMethodOrBuilder(int param1Int);
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.ServiceOptions getOptions();
/*       */     
/*       */     DescriptorProtos.ServiceOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface EnumValueDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     boolean hasNumber();
/*       */     
/*       */     int getNumber();
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.EnumValueOptions getOptions();
/*       */     
/*       */     DescriptorProtos.EnumValueOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface EnumDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     List<DescriptorProtos.EnumValueDescriptorProto> getValueList();
/*       */     
/*       */     DescriptorProtos.EnumValueDescriptorProto getValue(int param1Int);
/*       */     
/*       */     int getValueCount();
/*       */     
/*       */     List<? extends DescriptorProtos.EnumValueDescriptorProtoOrBuilder> getValueOrBuilderList();
/*       */     
/*       */     DescriptorProtos.EnumValueDescriptorProtoOrBuilder getValueOrBuilder(int param1Int);
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.EnumOptions getOptions();
/*       */     
/*       */     DescriptorProtos.EnumOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface FieldDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     boolean hasNumber();
/*       */     
/*       */     int getNumber();
/*       */     
/*       */     boolean hasLabel();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProto.Label getLabel();
/*       */     
/*       */     boolean hasType();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProto.Type getType();
/*       */     
/*       */     boolean hasTypeName();
/*       */     
/*       */     String getTypeName();
/*       */     
/*       */     boolean hasExtendee();
/*       */     
/*       */     String getExtendee();
/*       */     
/*       */     boolean hasDefaultValue();
/*       */     
/*       */     String getDefaultValue();
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.FieldOptions getOptions();
/*       */     
/*       */     DescriptorProtos.FieldOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface DescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     List<DescriptorProtos.FieldDescriptorProto> getFieldList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProto getField(int param1Int);
/*       */     
/*       */     int getFieldCount();
/*       */     
/*       */     List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getFieldOrBuilderList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProtoOrBuilder getFieldOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.FieldDescriptorProto> getExtensionList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProto getExtension(int param1Int);
/*       */     
/*       */     int getExtensionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.DescriptorProto> getNestedTypeList();
/*       */     
/*       */     DescriptorProtos.DescriptorProto getNestedType(int param1Int);
/*       */     
/*       */     int getNestedTypeCount();
/*       */     
/*       */     List<? extends DescriptorProtoOrBuilder> getNestedTypeOrBuilderList();
/*       */     
/*       */     DescriptorProtoOrBuilder getNestedTypeOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList();
/*       */     
/*       */     DescriptorProtos.EnumDescriptorProto getEnumType(int param1Int);
/*       */     
/*       */     int getEnumTypeCount();
/*       */     
/*       */     List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList();
/*       */     
/*       */     DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.DescriptorProto.ExtensionRange> getExtensionRangeList();
/*       */     
/*       */     DescriptorProtos.DescriptorProto.ExtensionRange getExtensionRange(int param1Int);
/*       */     
/*       */     int getExtensionRangeCount();
/*       */     
/*       */     List<? extends DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder> getExtensionRangeOrBuilderList();
/*       */     
/*       */     DescriptorProtos.DescriptorProto.ExtensionRangeOrBuilder getExtensionRangeOrBuilder(int param1Int);
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.MessageOptions getOptions();
/*       */     
/*       */     DescriptorProtos.MessageOptionsOrBuilder getOptionsOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface FileDescriptorProtoOrBuilder extends MessageOrBuilder {
/*       */     boolean hasName();
/*       */     
/*       */     String getName();
/*       */     
/*       */     boolean hasPackage();
/*       */     
/*       */     String getPackage();
/*       */     
/*       */     List<String> getDependencyList();
/*       */     
/*       */     int getDependencyCount();
/*       */     
/*       */     String getDependency(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.DescriptorProto> getMessageTypeList();
/*       */     
/*       */     DescriptorProtos.DescriptorProto getMessageType(int param1Int);
/*       */     
/*       */     int getMessageTypeCount();
/*       */     
/*       */     List<? extends DescriptorProtos.DescriptorProtoOrBuilder> getMessageTypeOrBuilderList();
/*       */     
/*       */     DescriptorProtos.DescriptorProtoOrBuilder getMessageTypeOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.EnumDescriptorProto> getEnumTypeList();
/*       */     
/*       */     DescriptorProtos.EnumDescriptorProto getEnumType(int param1Int);
/*       */     
/*       */     int getEnumTypeCount();
/*       */     
/*       */     List<? extends DescriptorProtos.EnumDescriptorProtoOrBuilder> getEnumTypeOrBuilderList();
/*       */     
/*       */     DescriptorProtos.EnumDescriptorProtoOrBuilder getEnumTypeOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.ServiceDescriptorProto> getServiceList();
/*       */     
/*       */     DescriptorProtos.ServiceDescriptorProto getService(int param1Int);
/*       */     
/*       */     int getServiceCount();
/*       */     
/*       */     List<? extends DescriptorProtos.ServiceDescriptorProtoOrBuilder> getServiceOrBuilderList();
/*       */     
/*       */     DescriptorProtos.ServiceDescriptorProtoOrBuilder getServiceOrBuilder(int param1Int);
/*       */     
/*       */     List<DescriptorProtos.FieldDescriptorProto> getExtensionList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProto getExtension(int param1Int);
/*       */     
/*       */     int getExtensionCount();
/*       */     
/*       */     List<? extends DescriptorProtos.FieldDescriptorProtoOrBuilder> getExtensionOrBuilderList();
/*       */     
/*       */     DescriptorProtos.FieldDescriptorProtoOrBuilder getExtensionOrBuilder(int param1Int);
/*       */     
/*       */     boolean hasOptions();
/*       */     
/*       */     DescriptorProtos.FileOptions getOptions();
/*       */     
/*       */     DescriptorProtos.FileOptionsOrBuilder getOptionsOrBuilder();
/*       */     
/*       */     boolean hasSourceCodeInfo();
/*       */     
/*       */     DescriptorProtos.SourceCodeInfo getSourceCodeInfo();
/*       */     
/*       */     DescriptorProtos.SourceCodeInfoOrBuilder getSourceCodeInfoOrBuilder();
/*       */   }
/*       */   
/*       */   public static interface FileDescriptorSetOrBuilder extends MessageOrBuilder {
/*       */     List<DescriptorProtos.FileDescriptorProto> getFileList();
/*       */     
/*       */     DescriptorProtos.FileDescriptorProto getFile(int param1Int);
/*       */     
/*       */     int getFileCount();
/*       */     
/*       */     List<? extends DescriptorProtos.FileDescriptorProtoOrBuilder> getFileOrBuilderList();
/*       */     
/*       */     DescriptorProtos.FileDescriptorProtoOrBuilder getFileOrBuilder(int param1Int);
/*       */   }
/*       */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\DescriptorProtos.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */