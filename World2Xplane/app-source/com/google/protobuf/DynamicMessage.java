/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Map;
/*     */ 
/*     */ public final class DynamicMessage extends AbstractMessage {
/*     */   private final Descriptors.Descriptor type;
/*     */   
/*     */   private final FieldSet<Descriptors.FieldDescriptor> fields;
/*     */   
/*     */   private final UnknownFieldSet unknownFields;
/*     */   
/*  50 */   private int memoizedSize = -1;
/*     */   
/*     */   private DynamicMessage(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields, UnknownFieldSet unknownFields) {
/*  57 */     this.type = type;
/*  58 */     this.fields = fields;
/*  59 */     this.unknownFields = unknownFields;
/*     */   }
/*     */   
/*     */   public static DynamicMessage getDefaultInstance(Descriptors.Descriptor type) {
/*  67 */     return new DynamicMessage(type, FieldSet.emptySet(), UnknownFieldSet.getDefaultInstance());
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input) throws IOException {
/*  75 */     return newBuilder(type).mergeFrom(input).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, CodedInputStream input, ExtensionRegistry extensionRegistry) throws IOException {
/*  84 */     return newBuilder(type).mergeFrom(input, extensionRegistry).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data) throws InvalidProtocolBufferException {
/*  90 */     return newBuilder(type).mergeFrom(data).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, ByteString data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
/*  97 */     return newBuilder(type).mergeFrom(data, extensionRegistry).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data) throws InvalidProtocolBufferException {
/* 103 */     return newBuilder(type).mergeFrom(data).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, byte[] data, ExtensionRegistry extensionRegistry) throws InvalidProtocolBufferException {
/* 110 */     return newBuilder(type).mergeFrom(data, extensionRegistry).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input) throws IOException {
/* 116 */     return newBuilder(type).mergeFrom(input).buildParsed();
/*     */   }
/*     */   
/*     */   public static DynamicMessage parseFrom(Descriptors.Descriptor type, InputStream input, ExtensionRegistry extensionRegistry) throws IOException {
/* 123 */     return newBuilder(type).mergeFrom(input, extensionRegistry).buildParsed();
/*     */   }
/*     */   
/*     */   public static Builder newBuilder(Descriptors.Descriptor type) {
/* 128 */     return new Builder(type);
/*     */   }
/*     */   
/*     */   public static Builder newBuilder(Message prototype) {
/* 136 */     return (new Builder(prototype.getDescriptorForType())).mergeFrom(prototype);
/*     */   }
/*     */   
/*     */   public Descriptors.Descriptor getDescriptorForType() {
/* 143 */     return this.type;
/*     */   }
/*     */   
/*     */   public DynamicMessage getDefaultInstanceForType() {
/* 147 */     return getDefaultInstance(this.type);
/*     */   }
/*     */   
/*     */   public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 151 */     return this.fields.getAllFields();
/*     */   }
/*     */   
/*     */   public boolean hasField(Descriptors.FieldDescriptor field) {
/* 155 */     verifyContainingType(field);
/* 156 */     return this.fields.hasField(field);
/*     */   }
/*     */   
/*     */   public Object getField(Descriptors.FieldDescriptor field) {
/* 160 */     verifyContainingType(field);
/* 161 */     Object result = this.fields.getField(field);
/* 162 */     if (result == null)
/* 163 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 164 */         result = getDefaultInstance(field.getMessageType());
/*     */       } else {
/* 166 */         result = field.getDefaultValue();
/*     */       }  
/* 169 */     return result;
/*     */   }
/*     */   
/*     */   public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 173 */     verifyContainingType(field);
/* 174 */     return this.fields.getRepeatedFieldCount(field);
/*     */   }
/*     */   
/*     */   public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 178 */     verifyContainingType(field);
/* 179 */     return this.fields.getRepeatedField(field, index);
/*     */   }
/*     */   
/*     */   public UnknownFieldSet getUnknownFields() {
/* 183 */     return this.unknownFields;
/*     */   }
/*     */   
/*     */   private static boolean isInitialized(Descriptors.Descriptor type, FieldSet<Descriptors.FieldDescriptor> fields) {
/* 189 */     for (Descriptors.FieldDescriptor field : type.getFields()) {
/* 190 */       if (field.isRequired() && 
/* 191 */         !fields.hasField(field))
/* 192 */         return false; 
/*     */     } 
/* 198 */     return fields.isInitialized();
/*     */   }
/*     */   
/*     */   public boolean isInitialized() {
/* 202 */     return isInitialized(this.type, this.fields);
/*     */   }
/*     */   
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 206 */     if (this.type.getOptions().getMessageSetWireFormat()) {
/* 207 */       this.fields.writeMessageSetTo(output);
/* 208 */       this.unknownFields.writeAsMessageSetTo(output);
/*     */     } else {
/* 210 */       this.fields.writeTo(output);
/* 211 */       this.unknownFields.writeTo(output);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSerializedSize() {
/* 216 */     int size = this.memoizedSize;
/* 217 */     if (size != -1)
/* 217 */       return size; 
/* 219 */     if (this.type.getOptions().getMessageSetWireFormat()) {
/* 220 */       size = this.fields.getMessageSetSerializedSize();
/* 221 */       size += this.unknownFields.getSerializedSizeAsMessageSet();
/*     */     } else {
/* 223 */       size = this.fields.getSerializedSize();
/* 224 */       size += this.unknownFields.getSerializedSize();
/*     */     } 
/* 227 */     this.memoizedSize = size;
/* 228 */     return size;
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 232 */     return new Builder(this.type);
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 236 */     return newBuilderForType().mergeFrom(this);
/*     */   }
/*     */   
/*     */   private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 241 */     if (field.getContainingType() != this.type)
/* 242 */       throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
/*     */   }
/*     */   
/*     */   public static final class Builder extends AbstractMessage.Builder<Builder> {
/*     */     private final Descriptors.Descriptor type;
/*     */     
/*     */     private FieldSet<Descriptors.FieldDescriptor> fields;
/*     */     
/*     */     private UnknownFieldSet unknownFields;
/*     */     
/*     */     private Builder(Descriptors.Descriptor type) {
/* 259 */       this.type = type;
/* 260 */       this.fields = FieldSet.newFieldSet();
/* 261 */       this.unknownFields = UnknownFieldSet.getDefaultInstance();
/*     */     }
/*     */     
/*     */     public Builder clear() {
/* 268 */       if (this.fields == null)
/* 269 */         throw new IllegalStateException("Cannot call clear() after build()."); 
/* 271 */       this.fields.clear();
/* 272 */       return this;
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(Message other) {
/* 276 */       if (other instanceof DynamicMessage) {
/* 278 */         DynamicMessage otherDynamicMessage = (DynamicMessage)other;
/* 279 */         if (otherDynamicMessage.type != this.type)
/* 280 */           throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type."); 
/* 283 */         this.fields.mergeFrom(otherDynamicMessage.fields);
/* 284 */         mergeUnknownFields(otherDynamicMessage.unknownFields);
/* 285 */         return this;
/*     */       } 
/* 287 */       return super.mergeFrom(other);
/*     */     }
/*     */     
/*     */     public DynamicMessage build() {
/* 293 */       if (this.fields != null && !isInitialized())
/* 294 */         throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, this.unknownFields)); 
/* 297 */       return buildPartial();
/*     */     }
/*     */     
/*     */     private DynamicMessage buildParsed() throws InvalidProtocolBufferException {
/* 306 */       if (!isInitialized())
/* 307 */         throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, this.unknownFields)).asInvalidProtocolBufferException(); 
/* 311 */       return buildPartial();
/*     */     }
/*     */     
/*     */     public DynamicMessage buildPartial() {
/* 315 */       if (this.fields == null)
/* 316 */         throw new IllegalStateException("build() has already been called on this Builder."); 
/* 319 */       this.fields.makeImmutable();
/* 320 */       DynamicMessage result = new DynamicMessage(this.type, this.fields, this.unknownFields);
/* 322 */       this.fields = null;
/* 323 */       this.unknownFields = null;
/* 324 */       return result;
/*     */     }
/*     */     
/*     */     public Builder clone() {
/* 328 */       Builder result = new Builder(this.type);
/* 329 */       result.fields.mergeFrom(this.fields);
/* 330 */       return result;
/*     */     }
/*     */     
/*     */     public boolean isInitialized() {
/* 334 */       return DynamicMessage.isInitialized(this.type, this.fields);
/*     */     }
/*     */     
/*     */     public Descriptors.Descriptor getDescriptorForType() {
/* 338 */       return this.type;
/*     */     }
/*     */     
/*     */     public DynamicMessage getDefaultInstanceForType() {
/* 342 */       return DynamicMessage.getDefaultInstance(this.type);
/*     */     }
/*     */     
/*     */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/* 346 */       return this.fields.getAllFields();
/*     */     }
/*     */     
/*     */     public Builder newBuilderForField(Descriptors.FieldDescriptor field) {
/* 350 */       verifyContainingType(field);
/* 352 */       if (field.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
/* 353 */         throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type."); 
/* 357 */       return new Builder(field.getMessageType());
/*     */     }
/*     */     
/*     */     public boolean hasField(Descriptors.FieldDescriptor field) {
/* 361 */       verifyContainingType(field);
/* 362 */       return this.fields.hasField(field);
/*     */     }
/*     */     
/*     */     public Object getField(Descriptors.FieldDescriptor field) {
/* 366 */       verifyContainingType(field);
/* 367 */       Object result = this.fields.getField(field);
/* 368 */       if (result == null)
/* 369 */         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 370 */           result = DynamicMessage.getDefaultInstance(field.getMessageType());
/*     */         } else {
/* 372 */           result = field.getDefaultValue();
/*     */         }  
/* 375 */       return result;
/*     */     }
/*     */     
/*     */     public Builder setField(Descriptors.FieldDescriptor field, Object value) {
/* 379 */       verifyContainingType(field);
/* 380 */       this.fields.setField(field, value);
/* 381 */       return this;
/*     */     }
/*     */     
/*     */     public Builder clearField(Descriptors.FieldDescriptor field) {
/* 385 */       verifyContainingType(field);
/* 386 */       this.fields.clearField(field);
/* 387 */       return this;
/*     */     }
/*     */     
/*     */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 391 */       verifyContainingType(field);
/* 392 */       return this.fields.getRepeatedFieldCount(field);
/*     */     }
/*     */     
/*     */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 396 */       verifyContainingType(field);
/* 397 */       return this.fields.getRepeatedField(field, index);
/*     */     }
/*     */     
/*     */     public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 402 */       verifyContainingType(field);
/* 403 */       this.fields.setRepeatedField(field, index, value);
/* 404 */       return this;
/*     */     }
/*     */     
/*     */     public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 408 */       verifyContainingType(field);
/* 409 */       this.fields.addRepeatedField(field, value);
/* 410 */       return this;
/*     */     }
/*     */     
/*     */     public UnknownFieldSet getUnknownFields() {
/* 414 */       return this.unknownFields;
/*     */     }
/*     */     
/*     */     public Builder setUnknownFields(UnknownFieldSet unknownFields) {
/* 418 */       this.unknownFields = unknownFields;
/* 419 */       return this;
/*     */     }
/*     */     
/*     */     public Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 423 */       this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
/* 427 */       return this;
/*     */     }
/*     */     
/*     */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 432 */       if (field.getContainingType() != this.type)
/* 433 */         throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\DynamicMessage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */