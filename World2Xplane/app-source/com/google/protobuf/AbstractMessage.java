/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class AbstractMessage extends AbstractMessageLite implements Message {
/*     */   public boolean isInitialized() {
/*  54 */     for (Descriptors.FieldDescriptor field : getDescriptorForType().getFields()) {
/*  55 */       if (field.isRequired() && 
/*  56 */         !hasField(field))
/*  57 */         return false; 
/*     */     } 
/*  64 */     for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
/*  65 */       Descriptors.FieldDescriptor field = entry.getKey();
/*  66 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  67 */         if (field.isRepeated()) {
/*  68 */           for (Message element : entry.getValue()) {
/*  69 */             if (!element.isInitialized())
/*  70 */               return false; 
/*     */           } 
/*     */           continue;
/*     */         } 
/*  74 */         if (!((Message)entry.getValue()).isInitialized())
/*  75 */           return false; 
/*     */       } 
/*     */     } 
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   public final String toString() {
/*  86 */     return TextFormat.printToString(this);
/*     */   }
/*     */   
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/*  90 */     boolean isMessageSet = getDescriptorForType().getOptions().getMessageSetWireFormat();
/*  94 */     for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
/*  95 */       Descriptors.FieldDescriptor field = entry.getKey();
/*  96 */       Object value = entry.getValue();
/*  97 */       if (isMessageSet && field.isExtension() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !field.isRepeated()) {
/* 100 */         output.writeMessageSetExtension(field.getNumber(), (Message)value);
/*     */         continue;
/*     */       } 
/* 102 */       FieldSet.writeField(field, value, output);
/*     */     } 
/* 106 */     UnknownFieldSet unknownFields = getUnknownFields();
/* 107 */     if (isMessageSet) {
/* 108 */       unknownFields.writeAsMessageSetTo(output);
/*     */     } else {
/* 110 */       unknownFields.writeTo(output);
/*     */     } 
/*     */   }
/*     */   
/* 114 */   private int memoizedSize = -1;
/*     */   
/*     */   public int getSerializedSize() {
/* 117 */     int size = this.memoizedSize;
/* 118 */     if (size != -1)
/* 119 */       return size; 
/* 122 */     size = 0;
/* 123 */     boolean isMessageSet = getDescriptorForType().getOptions().getMessageSetWireFormat();
/* 127 */     for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet()) {
/* 128 */       Descriptors.FieldDescriptor field = entry.getKey();
/* 129 */       Object value = entry.getValue();
/* 130 */       if (isMessageSet && field.isExtension() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && !field.isRepeated()) {
/* 133 */         size += CodedOutputStream.computeMessageSetExtensionSize(field.getNumber(), (Message)value);
/*     */         continue;
/*     */       } 
/* 136 */       size += FieldSet.computeFieldSize(field, value);
/*     */     } 
/* 140 */     UnknownFieldSet unknownFields = getUnknownFields();
/* 141 */     if (isMessageSet) {
/* 142 */       size += unknownFields.getSerializedSizeAsMessageSet();
/*     */     } else {
/* 144 */       size += unknownFields.getSerializedSize();
/*     */     } 
/* 147 */     this.memoizedSize = size;
/* 148 */     return size;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 153 */     if (other == this)
/* 154 */       return true; 
/* 156 */     if (!(other instanceof Message))
/* 157 */       return false; 
/* 159 */     Message otherMessage = (Message)other;
/* 160 */     if (getDescriptorForType() != otherMessage.getDescriptorForType())
/* 161 */       return false; 
/* 163 */     return (getAllFields().equals(otherMessage.getAllFields()) && getUnknownFields().equals(otherMessage.getUnknownFields()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 169 */     int hash = 41;
/* 170 */     hash = 19 * hash + getDescriptorForType().hashCode();
/* 171 */     hash = hashFields(hash, getAllFields());
/* 172 */     hash = 29 * hash + getUnknownFields().hashCode();
/* 173 */     return hash;
/*     */   }
/*     */   
/*     */   protected int hashFields(int hash, Map<Descriptors.FieldDescriptor, Object> map) {
/* 179 */     for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
/* 180 */       Descriptors.FieldDescriptor field = entry.getKey();
/* 181 */       Object value = entry.getValue();
/* 182 */       hash = 37 * hash + field.getNumber();
/* 183 */       if (field.getType() != Descriptors.FieldDescriptor.Type.ENUM) {
/* 184 */         hash = 53 * hash + value.hashCode();
/*     */         continue;
/*     */       } 
/* 185 */       if (field.isRepeated()) {
/* 186 */         List<? extends Internal.EnumLite> list = (List<? extends Internal.EnumLite>)value;
/* 187 */         hash = 53 * hash + hashEnumList(list);
/*     */         continue;
/*     */       } 
/* 189 */       hash = 53 * hash + hashEnum((Internal.EnumLite)value);
/*     */     } 
/* 192 */     return hash;
/*     */   }
/*     */   
/*     */   protected static int hashLong(long n) {
/* 200 */     return (int)(n ^ n >>> 32L);
/*     */   }
/*     */   
/*     */   protected static int hashBoolean(boolean b) {
/* 208 */     return b ? 1231 : 1237;
/*     */   }
/*     */   
/*     */   protected static int hashEnum(Internal.EnumLite e) {
/* 219 */     return e.getNumber();
/*     */   }
/*     */   
/*     */   protected static int hashEnumList(List<? extends Internal.EnumLite> list) {
/* 224 */     int hash = 1;
/* 225 */     for (Internal.EnumLite e : list)
/* 226 */       hash = 31 * hash + hashEnum(e); 
/* 228 */     return hash;
/*     */   }
/*     */   
/*     */   public static abstract class Builder<BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> implements Message.Builder {
/*     */     public BuilderType clear() {
/* 248 */       for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : getAllFields().entrySet())
/* 249 */         clearField(entry.getKey()); 
/* 251 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(Message other) {
/* 255 */       if (other.getDescriptorForType() != getDescriptorForType())
/* 256 */         throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type."); 
/* 270 */       for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : other.getAllFields().entrySet()) {
/* 271 */         Descriptors.FieldDescriptor field = entry.getKey();
/* 272 */         if (field.isRepeated()) {
/* 273 */           for (Object element : entry.getValue())
/* 274 */             addRepeatedField(field, element); 
/*     */           continue;
/*     */         } 
/* 276 */         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 277 */           Message existingValue = (Message)getField(field);
/* 278 */           if (existingValue == existingValue.getDefaultInstanceForType()) {
/* 279 */             setField(field, entry.getValue());
/*     */             continue;
/*     */           } 
/* 281 */           setField(field, existingValue.newBuilderForType().mergeFrom(existingValue).mergeFrom((Message)entry.getValue()).build());
/*     */           continue;
/*     */         } 
/* 288 */         setField(field, entry.getValue());
/*     */       } 
/* 292 */       mergeUnknownFields(other.getUnknownFields());
/* 294 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(CodedInputStream input) throws IOException {
/* 300 */       return mergeFrom(input, ExtensionRegistry.getEmptyRegistry());
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/*     */       int tag;
/* 308 */       UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder(getUnknownFields());
/*     */       do {
/* 311 */         tag = input.readTag();
/* 312 */         if (tag == 0)
/*     */           break; 
/* 316 */       } while (mergeFieldFrom(input, unknownFields, extensionRegistry, this, tag));
/* 322 */       setUnknownFields(unknownFields.build());
/* 323 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     static boolean mergeFieldFrom(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, Message.Builder builder, int tag) throws IOException {
/*     */       Descriptors.FieldDescriptor field;
/* 339 */       Descriptors.Descriptor type = builder.getDescriptorForType();
/* 341 */       if (type.getOptions().getMessageSetWireFormat() && tag == WireFormat.MESSAGE_SET_ITEM_TAG) {
/* 343 */         mergeMessageSetExtensionFromCodedStream(input, unknownFields, extensionRegistry, builder);
/* 345 */         return true;
/*     */       } 
/* 348 */       int wireType = WireFormat.getTagWireType(tag);
/* 349 */       int fieldNumber = WireFormat.getTagFieldNumber(tag);
/* 352 */       Message defaultInstance = null;
/* 354 */       if (type.isExtensionNumber(fieldNumber)) {
/* 360 */         if (extensionRegistry instanceof ExtensionRegistry) {
/* 361 */           ExtensionRegistry.ExtensionInfo extension = ((ExtensionRegistry)extensionRegistry).findExtensionByNumber(type, fieldNumber);
/* 364 */           if (extension == null) {
/* 365 */             field = null;
/*     */           } else {
/* 367 */             field = extension.descriptor;
/* 368 */             defaultInstance = extension.defaultInstance;
/* 369 */             if (defaultInstance == null && field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/* 371 */               throw new IllegalStateException("Message-typed extension lacked default instance: " + field.getFullName()); 
/*     */           } 
/*     */         } else {
/* 377 */           field = null;
/*     */         } 
/*     */       } else {
/* 380 */         field = type.findFieldByNumber(fieldNumber);
/*     */       } 
/* 383 */       boolean unknown = false;
/* 384 */       boolean packed = false;
/* 385 */       if (field == null) {
/* 386 */         unknown = true;
/* 387 */       } else if (wireType == FieldSet.getWireFormatForFieldType(field.getLiteType(), false)) {
/* 390 */         packed = false;
/* 391 */       } else if (field.isPackable() && wireType == FieldSet.getWireFormatForFieldType(field.getLiteType(), true)) {
/* 395 */         packed = true;
/*     */       } else {
/* 397 */         unknown = true;
/*     */       } 
/* 400 */       if (unknown)
/* 401 */         return unknownFields.mergeFieldFrom(tag, input); 
/* 404 */       if (packed) {
/* 405 */         int length = input.readRawVarint32();
/* 406 */         int limit = input.pushLimit(length);
/* 407 */         if (field.getLiteType() == WireFormat.FieldType.ENUM) {
/* 408 */           while (input.getBytesUntilLimit() > 0) {
/* 409 */             int rawValue = input.readEnum();
/* 410 */             Object value = field.getEnumType().findValueByNumber(rawValue);
/* 411 */             if (value == null)
/* 414 */               return true; 
/* 416 */             builder.addRepeatedField(field, value);
/*     */           } 
/*     */         } else {
/* 419 */           while (input.getBytesUntilLimit() > 0) {
/* 420 */             Object value = FieldSet.readPrimitiveField(input, field.getLiteType());
/* 422 */             builder.addRepeatedField(field, value);
/*     */           } 
/*     */         } 
/* 425 */         input.popLimit(limit);
/*     */       } else {
/*     */         Object value;
/*     */         Message.Builder subBuilder;
/*     */         int rawValue;
/* 428 */         switch (field.getType()) {
/*     */           case GROUP:
/* 431 */             if (defaultInstance != null) {
/* 432 */               subBuilder = defaultInstance.newBuilderForType();
/*     */             } else {
/* 434 */               subBuilder = builder.newBuilderForField(field);
/*     */             } 
/* 436 */             if (!field.isRepeated())
/* 437 */               subBuilder.mergeFrom((Message)builder.getField(field)); 
/* 439 */             input.readGroup(field.getNumber(), subBuilder, extensionRegistry);
/* 440 */             value = subBuilder.build();
/*     */             break;
/*     */           case MESSAGE:
/* 445 */             if (defaultInstance != null) {
/* 446 */               subBuilder = defaultInstance.newBuilderForType();
/*     */             } else {
/* 448 */               subBuilder = builder.newBuilderForField(field);
/*     */             } 
/* 450 */             if (!field.isRepeated())
/* 451 */               subBuilder.mergeFrom((Message)builder.getField(field)); 
/* 453 */             input.readMessage(subBuilder, extensionRegistry);
/* 454 */             value = subBuilder.build();
/*     */             break;
/*     */           case ENUM:
/* 458 */             rawValue = input.readEnum();
/* 459 */             value = field.getEnumType().findValueByNumber(rawValue);
/* 462 */             if (value == null) {
/* 463 */               unknownFields.mergeVarintField(fieldNumber, rawValue);
/* 464 */               return true;
/*     */             } 
/*     */             break;
/*     */           default:
/* 468 */             value = FieldSet.readPrimitiveField(input, field.getLiteType());
/*     */             break;
/*     */         } 
/* 472 */         if (field.isRepeated()) {
/* 473 */           builder.addRepeatedField(field, value);
/*     */         } else {
/* 475 */           builder.setField(field, value);
/*     */         } 
/*     */       } 
/* 479 */       return true;
/*     */     }
/*     */     
/*     */     private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, Message.Builder builder) throws IOException {
/* 488 */       Descriptors.Descriptor type = builder.getDescriptorForType();
/* 506 */       int typeId = 0;
/* 507 */       ByteString rawBytes = null;
/* 508 */       Message.Builder subBuilder = null;
/* 509 */       Descriptors.FieldDescriptor field = null;
/*     */       while (true) {
/* 512 */         int tag = input.readTag();
/* 513 */         if (tag == 0)
/*     */           break; 
/* 517 */         if (tag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
/* 518 */           typeId = input.readUInt32();
/* 520 */           if (typeId != 0) {
/*     */             ExtensionRegistry.ExtensionInfo extension;
/* 528 */             if (extensionRegistry instanceof ExtensionRegistry) {
/* 529 */               extension = ((ExtensionRegistry)extensionRegistry).findExtensionByNumber(type, typeId);
/*     */             } else {
/* 532 */               extension = null;
/*     */             } 
/* 535 */             if (extension != null) {
/* 536 */               field = extension.descriptor;
/* 537 */               subBuilder = extension.defaultInstance.newBuilderForType();
/* 538 */               Message originalMessage = (Message)builder.getField(field);
/* 539 */               if (originalMessage != null)
/* 540 */                 subBuilder.mergeFrom(originalMessage); 
/* 542 */               if (rawBytes != null) {
/* 544 */                 subBuilder.mergeFrom(CodedInputStream.newInstance(rawBytes.newInput()));
/* 546 */                 rawBytes = null;
/*     */               } 
/*     */               continue;
/*     */             } 
/* 551 */             if (rawBytes != null) {
/* 552 */               unknownFields.mergeField(typeId, UnknownFieldSet.Field.newBuilder().addLengthDelimited(rawBytes).build());
/* 556 */               rawBytes = null;
/*     */             } 
/*     */           } 
/*     */           continue;
/*     */         } 
/* 560 */         if (tag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
/* 561 */           if (typeId == 0) {
/* 564 */             rawBytes = input.readBytes();
/*     */             continue;
/*     */           } 
/* 565 */           if (subBuilder == null) {
/* 567 */             unknownFields.mergeField(typeId, UnknownFieldSet.Field.newBuilder().addLengthDelimited(input.readBytes()).build());
/*     */             continue;
/*     */           } 
/* 574 */           input.readMessage(subBuilder, extensionRegistry);
/*     */           continue;
/*     */         } 
/* 578 */         if (!input.skipField(tag))
/*     */           break; 
/*     */       } 
/* 584 */       input.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
/* 586 */       if (subBuilder != null)
/* 587 */         builder.setField(field, subBuilder.build()); 
/*     */     }
/*     */     
/*     */     public BuilderType mergeUnknownFields(UnknownFieldSet unknownFields) {
/* 592 */       setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFields).build());
/* 596 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     protected static UninitializedMessageException newUninitializedMessageException(Message message) {
/* 605 */       return new UninitializedMessageException(findMissingFields(message));
/*     */     }
/*     */     
/*     */     private static List<String> findMissingFields(Message message) {
/* 613 */       List<String> results = new ArrayList<String>();
/* 614 */       findMissingFields(message, "", results);
/* 615 */       return results;
/*     */     }
/*     */     
/*     */     private static void findMissingFields(Message message, String prefix, List<String> results) {
/* 623 */       for (Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
/* 624 */         if (field.isRequired() && !message.hasField(field))
/* 625 */           results.add(prefix + field.getName()); 
/*     */       } 
/* 630 */       for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
/* 631 */         Descriptors.FieldDescriptor field = entry.getKey();
/* 632 */         Object value = entry.getValue();
/* 634 */         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 635 */           if (field.isRepeated()) {
/* 636 */             int i = 0;
/* 637 */             for (Object element : value)
/* 638 */               findMissingFields((Message)element, subMessagePrefix(prefix, field, i++), results); 
/*     */             continue;
/*     */           } 
/* 643 */           if (message.hasField(field))
/* 644 */             findMissingFields((Message)value, subMessagePrefix(prefix, field, -1), results); 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private static String subMessagePrefix(String prefix, Descriptors.FieldDescriptor field, int index) {
/* 656 */       StringBuilder result = new StringBuilder(prefix);
/* 657 */       if (field.isExtension()) {
/* 658 */         result.append('(').append(field.getFullName()).append(')');
/*     */       } else {
/* 662 */         result.append(field.getName());
/*     */       } 
/* 664 */       if (index != -1)
/* 665 */         result.append('[').append(index).append(']'); 
/* 669 */       result.append('.');
/* 670 */       return result.toString();
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(ByteString data) throws InvalidProtocolBufferException {
/* 695 */       return super.mergeFrom(data);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 703 */       return super.mergeFrom(data, extensionRegistry);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data) throws InvalidProtocolBufferException {
/* 709 */       return super.mergeFrom(data);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
/* 716 */       return super.mergeFrom(data, off, len);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 724 */       return super.mergeFrom(data, extensionRegistry);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 732 */       return super.mergeFrom(data, off, len, extensionRegistry);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(InputStream input) throws IOException {
/* 738 */       return super.mergeFrom(input);
/*     */     }
/*     */     
/*     */     public BuilderType mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 746 */       return super.mergeFrom(input, extensionRegistry);
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input) throws IOException {
/* 752 */       return super.mergeDelimitedFrom(input);
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 760 */       return super.mergeDelimitedFrom(input, extensionRegistry);
/*     */     }
/*     */     
/*     */     public abstract BuilderType clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\AbstractMessage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */