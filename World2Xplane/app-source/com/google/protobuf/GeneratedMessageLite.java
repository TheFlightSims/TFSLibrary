/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class GeneratedMessageLite extends AbstractMessageLite implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected GeneratedMessageLite() {}
/*     */   
/*     */   protected GeneratedMessageLite(Builder builder) {}
/*     */   
/*     */   public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType> {
/*     */     public BuilderType clear() {
/*  66 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public BuilderType clone() {
/*  74 */       throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
/*     */     }
/*     */     
/*     */     protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  92 */       return input.skipField(tag);
/*     */     }
/*     */     
/*     */     public abstract BuilderType mergeFrom(MessageType param1MessageType);
/*     */     
/*     */     public abstract MessageType getDefaultInstanceForType();
/*     */   }
/*     */   
/*     */   public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>> extends GeneratedMessageLite implements ExtendableMessageOrBuilder<MessageType> {
/*     */     private final FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions;
/*     */     
/*     */     protected ExtendableMessage() {
/* 133 */       this.extensions = FieldSet.newFieldSet();
/*     */     }
/*     */     
/*     */     protected ExtendableMessage(GeneratedMessageLite.ExtendableBuilder<MessageType, ?> builder) {
/* 137 */       this.extensions = builder.buildExtensions();
/*     */     }
/*     */     
/*     */     private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> extension) {
/* 142 */       if (extension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
/* 145 */         throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings."); 
/*     */     }
/*     */     
/*     */     public final <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> extension) {
/* 155 */       verifyExtensionContainingType(extension);
/* 156 */       return this.extensions.hasField(extension.descriptor);
/*     */     }
/*     */     
/*     */     public final <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension) {
/* 163 */       verifyExtensionContainingType(extension);
/* 164 */       return this.extensions.getRepeatedFieldCount(extension.descriptor);
/*     */     }
/*     */     
/*     */     public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> extension) {
/* 172 */       verifyExtensionContainingType(extension);
/* 173 */       Object value = this.extensions.getField(extension.descriptor);
/* 174 */       if (value == null)
/* 175 */         return extension.defaultValue; 
/* 177 */       return (Type)value;
/*     */     }
/*     */     
/*     */     public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/* 187 */       verifyExtensionContainingType(extension);
/* 188 */       return (Type)this.extensions.getRepeatedField(extension.descriptor, index);
/*     */     }
/*     */     
/*     */     protected boolean extensionsAreInitialized() {
/* 193 */       return this.extensions.isInitialized();
/*     */     }
/*     */     
/*     */     protected class ExtensionWriter {
/* 206 */       private final Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = GeneratedMessageLite.ExtendableMessage.this.extensions.iterator();
/*     */       
/*     */       private Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next;
/*     */       
/*     */       private final boolean messageSetWireFormat;
/*     */       
/*     */       private ExtensionWriter(boolean messageSetWireFormat) {
/* 212 */         if (this.iter.hasNext())
/* 213 */           this.next = this.iter.next(); 
/* 215 */         this.messageSetWireFormat = messageSetWireFormat;
/*     */       }
/*     */       
/*     */       public void writeUntil(int end, CodedOutputStream output) throws IOException {
/* 220 */         while (this.next != null && ((GeneratedMessageLite.ExtensionDescriptor)this.next.getKey()).getNumber() < end) {
/* 221 */           GeneratedMessageLite.ExtensionDescriptor extension = this.next.getKey();
/* 222 */           if (this.messageSetWireFormat && extension.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extension.isRepeated()) {
/* 225 */             output.writeMessageSetExtension(extension.getNumber(), (MessageLite)this.next.getValue());
/*     */           } else {
/* 228 */             FieldSet.writeField(extension, this.next.getValue(), output);
/*     */           } 
/* 230 */           if (this.iter.hasNext()) {
/* 231 */             this.next = this.iter.next();
/*     */             continue;
/*     */           } 
/* 233 */           this.next = null;
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     protected ExtensionWriter newExtensionWriter() {
/* 240 */       return new ExtensionWriter(false);
/*     */     }
/*     */     
/*     */     protected ExtensionWriter newMessageSetExtensionWriter() {
/* 243 */       return new ExtensionWriter(true);
/*     */     }
/*     */     
/*     */     protected int extensionsSerializedSize() {
/* 248 */       return this.extensions.getSerializedSize();
/*     */     }
/*     */     
/*     */     protected int extensionsSerializedSizeAsMessageSet() {
/* 251 */       return this.extensions.getMessageSetSerializedSize();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
/* 266 */     private FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions = FieldSet.emptySet();
/*     */     
/*     */     private boolean extensionsIsMutable;
/*     */     
/*     */     public BuilderType clear() {
/* 271 */       this.extensions.clear();
/* 272 */       this.extensionsIsMutable = false;
/* 273 */       return super.clear();
/*     */     }
/*     */     
/*     */     private void ensureExtensionsIsMutable() {
/* 277 */       if (!this.extensionsIsMutable) {
/* 278 */         this.extensions = this.extensions.clone();
/* 279 */         this.extensionsIsMutable = true;
/*     */       } 
/*     */     }
/*     */     
/*     */     private FieldSet<GeneratedMessageLite.ExtensionDescriptor> buildExtensions() {
/* 288 */       this.extensions.makeImmutable();
/* 289 */       this.extensionsIsMutable = false;
/* 290 */       return this.extensions;
/*     */     }
/*     */     
/*     */     private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> extension) {
/* 295 */       if (extension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
/* 298 */         throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings."); 
/*     */     }
/*     */     
/*     */     public final <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> extension) {
/* 308 */       verifyExtensionContainingType(extension);
/* 309 */       return this.extensions.hasField(extension.descriptor);
/*     */     }
/*     */     
/*     */     public final <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension) {
/* 316 */       verifyExtensionContainingType(extension);
/* 317 */       return this.extensions.getRepeatedFieldCount(extension.descriptor);
/*     */     }
/*     */     
/*     */     public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> extension) {
/* 325 */       verifyExtensionContainingType(extension);
/* 326 */       Object value = this.extensions.getField(extension.descriptor);
/* 327 */       if (value == null)
/* 328 */         return extension.defaultValue; 
/* 330 */       return (Type)value;
/*     */     }
/*     */     
/*     */     public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/* 340 */       verifyExtensionContainingType(extension);
/* 341 */       return (Type)this.extensions.getRepeatedField(extension.descriptor, index);
/*     */     }
/*     */     
/*     */     public BuilderType clone() {
/* 349 */       throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
/*     */     }
/*     */     
/*     */     public final <Type> BuilderType setExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> extension, Type value) {
/* 357 */       verifyExtensionContainingType(extension);
/* 358 */       ensureExtensionsIsMutable();
/* 359 */       this.extensions.setField(extension.descriptor, value);
/* 360 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public final <Type> BuilderType setExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension, int index, Type value) {
/* 367 */       verifyExtensionContainingType(extension);
/* 368 */       ensureExtensionsIsMutable();
/* 369 */       this.extensions.setRepeatedField(extension.descriptor, index, value);
/* 370 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public final <Type> BuilderType addExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> extension, Type value) {
/* 377 */       verifyExtensionContainingType(extension);
/* 378 */       ensureExtensionsIsMutable();
/* 379 */       this.extensions.addRepeatedField(extension.descriptor, value);
/* 380 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     public final <Type> BuilderType clearExtension(GeneratedMessageLite.GeneratedExtension<MessageType, ?> extension) {
/* 386 */       verifyExtensionContainingType(extension);
/* 387 */       ensureExtensionsIsMutable();
/* 388 */       this.extensions.clearField(extension.descriptor);
/* 389 */       return (BuilderType)this;
/*     */     }
/*     */     
/*     */     protected boolean extensionsAreInitialized() {
/* 394 */       return this.extensions.isInitialized();
/*     */     }
/*     */     
/*     */     protected boolean parseUnknownField(CodedInputStream input, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/* 406 */       int wireType = WireFormat.getTagWireType(tag);
/* 407 */       int fieldNumber = WireFormat.getTagFieldNumber(tag);
/* 409 */       GeneratedMessageLite.GeneratedExtension<MessageType, ?> extension = extensionRegistry.findLiteExtensionByNumber(getDefaultInstanceForType(), fieldNumber);
/* 413 */       boolean unknown = false;
/* 414 */       boolean packed = false;
/* 415 */       if (extension == null) {
/* 416 */         unknown = true;
/* 417 */       } else if (wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), false)) {
/* 420 */         packed = false;
/* 421 */       } else if (extension.descriptor.isRepeated && extension.descriptor.type.isPackable() && wireType == FieldSet.getWireFormatForFieldType(extension.descriptor.getLiteType(), true)) {
/* 426 */         packed = true;
/*     */       } else {
/* 428 */         unknown = true;
/*     */       } 
/* 431 */       if (unknown)
/* 432 */         return input.skipField(tag); 
/* 435 */       if (packed) {
/* 436 */         int length = input.readRawVarint32();
/* 437 */         int limit = input.pushLimit(length);
/* 438 */         if (extension.descriptor.getLiteType() == WireFormat.FieldType.ENUM) {
/* 439 */           while (input.getBytesUntilLimit() > 0) {
/* 440 */             int rawValue = input.readEnum();
/* 441 */             Object value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
/* 443 */             if (value == null)
/* 446 */               return true; 
/* 448 */             ensureExtensionsIsMutable();
/* 449 */             this.extensions.addRepeatedField(extension.descriptor, value);
/*     */           } 
/*     */         } else {
/* 452 */           while (input.getBytesUntilLimit() > 0) {
/* 453 */             Object value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType());
/* 456 */             ensureExtensionsIsMutable();
/* 457 */             this.extensions.addRepeatedField(extension.descriptor, value);
/*     */           } 
/*     */         } 
/* 460 */         input.popLimit(limit);
/*     */       } else {
/*     */         Object value;
/*     */         MessageLite.Builder subBuilder;
/*     */         int rawValue;
/* 463 */         switch (extension.descriptor.getLiteJavaType()) {
/*     */           case MESSAGE:
/* 465 */             subBuilder = null;
/* 466 */             if (!extension.descriptor.isRepeated()) {
/* 467 */               MessageLite existingValue = (MessageLite)this.extensions.getField(extension.descriptor);
/* 469 */               if (existingValue != null)
/* 470 */                 subBuilder = existingValue.toBuilder(); 
/*     */             } 
/* 473 */             if (subBuilder == null)
/* 474 */               subBuilder = extension.messageDefaultInstance.newBuilderForType(); 
/* 476 */             if (extension.descriptor.getLiteType() == WireFormat.FieldType.GROUP) {
/* 478 */               input.readGroup(extension.getNumber(), subBuilder, extensionRegistry);
/*     */             } else {
/* 481 */               input.readMessage(subBuilder, extensionRegistry);
/*     */             } 
/* 483 */             value = subBuilder.build();
/*     */             break;
/*     */           case ENUM:
/* 487 */             rawValue = input.readEnum();
/* 488 */             value = extension.descriptor.getEnumType().findValueByNumber(rawValue);
/* 492 */             if (value == null)
/* 493 */               return true; 
/*     */             break;
/*     */           default:
/* 497 */             value = FieldSet.readPrimitiveField(input, extension.descriptor.getLiteType());
/*     */             break;
/*     */         } 
/* 502 */         if (extension.descriptor.isRepeated()) {
/* 503 */           ensureExtensionsIsMutable();
/* 504 */           this.extensions.addRepeatedField(extension.descriptor, value);
/*     */         } else {
/* 506 */           ensureExtensionsIsMutable();
/* 507 */           this.extensions.setField(extension.descriptor, value);
/*     */         } 
/*     */       } 
/* 511 */       return true;
/*     */     }
/*     */     
/*     */     protected final void mergeExtensionFields(MessageType other) {
/* 515 */       ensureExtensionsIsMutable();
/* 516 */       this.extensions.mergeFrom(((GeneratedMessageLite.ExtendableMessage)other).extensions);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type) {
/* 532 */     return new GeneratedExtension<ContainingType, Type>((MessageLite)containingTypeDefaultInstance, defaultValue, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, false, false));
/*     */   }
/*     */   
/*     */   public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingTypeDefaultInstance, MessageLite messageDefaultInstance, Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isPacked) {
/* 552 */     List<?> list = Collections.emptyList();
/* 553 */     return new GeneratedExtension<ContainingType, Type>((MessageLite)containingTypeDefaultInstance, list, messageDefaultInstance, new ExtensionDescriptor(enumTypeMap, number, type, true, isPacked));
/*     */   }
/*     */   
/*     */   private static final class ExtensionDescriptor implements FieldSet.FieldDescriptorLite<ExtensionDescriptor> {
/*     */     private final Internal.EnumLiteMap<?> enumTypeMap;
/*     */     
/*     */     private final int number;
/*     */     
/*     */     private final WireFormat.FieldType type;
/*     */     
/*     */     private final boolean isRepeated;
/*     */     
/*     */     private final boolean isPacked;
/*     */     
/*     */     private ExtensionDescriptor(Internal.EnumLiteMap<?> enumTypeMap, int number, WireFormat.FieldType type, boolean isRepeated, boolean isPacked) {
/* 570 */       this.enumTypeMap = enumTypeMap;
/* 571 */       this.number = number;
/* 572 */       this.type = type;
/* 573 */       this.isRepeated = isRepeated;
/* 574 */       this.isPacked = isPacked;
/*     */     }
/*     */     
/*     */     public int getNumber() {
/* 584 */       return this.number;
/*     */     }
/*     */     
/*     */     public WireFormat.FieldType getLiteType() {
/* 588 */       return this.type;
/*     */     }
/*     */     
/*     */     public WireFormat.JavaType getLiteJavaType() {
/* 592 */       return this.type.getJavaType();
/*     */     }
/*     */     
/*     */     public boolean isRepeated() {
/* 596 */       return this.isRepeated;
/*     */     }
/*     */     
/*     */     public boolean isPacked() {
/* 600 */       return this.isPacked;
/*     */     }
/*     */     
/*     */     public Internal.EnumLiteMap<?> getEnumType() {
/* 604 */       return this.enumTypeMap;
/*     */     }
/*     */     
/*     */     public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
/* 610 */       return ((GeneratedMessageLite.Builder<GeneratedMessageLite, MessageLite.Builder>)to).mergeFrom((GeneratedMessageLite)from);
/*     */     }
/*     */     
/*     */     public int compareTo(ExtensionDescriptor other) {
/* 614 */       return this.number - other.number;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class GeneratedExtension<ContainingType extends MessageLite, Type> {
/*     */     private final ContainingType containingTypeDefaultInstance;
/*     */     
/*     */     private final Type defaultValue;
/*     */     
/*     */     private final MessageLite messageDefaultInstance;
/*     */     
/*     */     private final GeneratedMessageLite.ExtensionDescriptor descriptor;
/*     */     
/*     */     private GeneratedExtension(ContainingType containingTypeDefaultInstance, Type defaultValue, MessageLite messageDefaultInstance, GeneratedMessageLite.ExtensionDescriptor descriptor) {
/* 634 */       if (containingTypeDefaultInstance == null)
/* 635 */         throw new IllegalArgumentException("Null containingTypeDefaultInstance"); 
/* 638 */       if (descriptor.getLiteType() == WireFormat.FieldType.MESSAGE && messageDefaultInstance == null)
/* 640 */         throw new IllegalArgumentException("Null messageDefaultInstance"); 
/* 643 */       this.containingTypeDefaultInstance = containingTypeDefaultInstance;
/* 644 */       this.defaultValue = defaultValue;
/* 645 */       this.messageDefaultInstance = messageDefaultInstance;
/* 646 */       this.descriptor = descriptor;
/*     */     }
/*     */     
/*     */     public ContainingType getContainingTypeDefaultInstance() {
/* 658 */       return this.containingTypeDefaultInstance;
/*     */     }
/*     */     
/*     */     public int getNumber() {
/* 663 */       return this.descriptor.getNumber();
/*     */     }
/*     */     
/*     */     public MessageLite getMessageDefaultInstance() {
/* 671 */       return this.messageDefaultInstance;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SerializedForm implements Serializable {
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     private String messageClassName;
/*     */     
/*     */     private byte[] asBytes;
/*     */     
/*     */     SerializedForm(MessageLite regularForm) {
/* 690 */       this.messageClassName = regularForm.getClass().getName();
/* 691 */       this.asBytes = regularForm.toByteArray();
/*     */     }
/*     */     
/*     */     protected Object readResolve() throws ObjectStreamException {
/*     */       try {
/* 702 */         Class<?> messageClass = Class.forName(this.messageClassName);
/* 703 */         Method newBuilder = messageClass.getMethod("newBuilder", new Class[0]);
/* 704 */         MessageLite.Builder builder = (MessageLite.Builder)newBuilder.invoke(null, new Object[0]);
/* 706 */         builder.mergeFrom(this.asBytes);
/* 707 */         return builder.buildPartial();
/* 708 */       } catch (ClassNotFoundException e) {
/* 709 */         throw new RuntimeException("Unable to find proto buffer class", e);
/* 710 */       } catch (NoSuchMethodException e) {
/* 711 */         throw new RuntimeException("Unable to find newBuilder method", e);
/* 712 */       } catch (IllegalAccessException e) {
/* 713 */         throw new RuntimeException("Unable to call newBuilder method", e);
/* 714 */       } catch (InvocationTargetException e) {
/* 715 */         throw new RuntimeException("Error calling newBuilder", e.getCause());
/* 716 */       } catch (InvalidProtocolBufferException e) {
/* 717 */         throw new RuntimeException("Unable to understand proto buffer", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object writeReplace() throws ObjectStreamException {
/* 729 */     return new SerializedForm(this);
/*     */   }
/*     */   
/*     */   public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageLiteOrBuilder {
/*     */     <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*     */     
/*     */     <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension);
/*     */     
/*     */     <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*     */     
/*     */     <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\GeneratedMessageLite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */