/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ public abstract class GeneratedMessage extends AbstractMessage implements Serializable {
/*      */   private static final long serialVersionUID = 1L;
/*      */   
/*      */   private final UnknownFieldSet unknownFields;
/*      */   
/*      */   protected static boolean alwaysUseFieldBuilders = false;
/*      */   
/*      */   protected GeneratedMessage() {
/*   71 */     this.unknownFields = UnknownFieldSet.getDefaultInstance();
/*      */   }
/*      */   
/*      */   protected GeneratedMessage(Builder<?> builder) {
/*   75 */     this.unknownFields = builder.getUnknownFields();
/*      */   }
/*      */   
/*      */   static void enableAlwaysUseFieldBuildersForTesting() {
/*   85 */     alwaysUseFieldBuilders = true;
/*      */   }
/*      */   
/*      */   public Descriptors.Descriptor getDescriptorForType() {
/*   97 */     return (internalGetFieldAccessorTable()).descriptor;
/*      */   }
/*      */   
/*      */   private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
/*  102 */     TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<Descriptors.FieldDescriptor, Object>();
/*  104 */     Descriptors.Descriptor descriptor = (internalGetFieldAccessorTable()).descriptor;
/*  105 */     for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
/*  106 */       if (field.isRepeated()) {
/*  107 */         List<?> value = (List)getField(field);
/*  108 */         if (!value.isEmpty())
/*  109 */           result.put(field, value); 
/*      */         continue;
/*      */       } 
/*  112 */       if (hasField(field))
/*  113 */         result.put(field, getField(field)); 
/*      */     } 
/*  117 */     return result;
/*      */   }
/*      */   
/*      */   public boolean isInitialized() {
/*  122 */     for (Descriptors.FieldDescriptor field : getDescriptorForType().getFields()) {
/*  124 */       if (field.isRequired() && 
/*  125 */         !hasField(field))
/*  126 */         return false; 
/*  130 */       if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  131 */         if (field.isRepeated()) {
/*  133 */           List<Message> messageList = (List<Message>)getField(field);
/*  134 */           for (Message element : messageList) {
/*  135 */             if (!element.isInitialized())
/*  136 */               return false; 
/*      */           } 
/*      */           continue;
/*      */         } 
/*  140 */         if (hasField(field) && !((Message)getField(field)).isInitialized())
/*  141 */           return false; 
/*      */       } 
/*      */     } 
/*  147 */     return true;
/*      */   }
/*      */   
/*      */   public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  152 */     return Collections.unmodifiableMap(getAllFieldsMutable());
/*      */   }
/*      */   
/*      */   public boolean hasField(Descriptors.FieldDescriptor field) {
/*  157 */     return internalGetFieldAccessorTable().getField(field).has(this);
/*      */   }
/*      */   
/*      */   public Object getField(Descriptors.FieldDescriptor field) {
/*  162 */     return internalGetFieldAccessorTable().getField(field).get(this);
/*      */   }
/*      */   
/*      */   public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/*  167 */     return internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
/*      */   }
/*      */   
/*      */   public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/*  173 */     return internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
/*      */   }
/*      */   
/*      */   public final UnknownFieldSet getUnknownFields() {
/*  179 */     return this.unknownFields;
/*      */   }
/*      */   
/*      */   public static abstract class Builder<BuilderType extends Builder> extends AbstractMessage.Builder<BuilderType> {
/*      */     private GeneratedMessage.BuilderParent builderParent;
/*      */     
/*      */     private BuilderParentImpl meAsParent;
/*      */     
/*      */     private boolean isClean;
/*      */     
/*  219 */     private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
/*      */     
/*      */     protected Builder() {
/*  223 */       this(null);
/*      */     }
/*      */     
/*      */     protected Builder(GeneratedMessage.BuilderParent builderParent) {
/*  227 */       this.builderParent = builderParent;
/*      */     }
/*      */     
/*      */     void dispose() {
/*  231 */       this.builderParent = null;
/*      */     }
/*      */     
/*      */     protected void onBuilt() {
/*  238 */       if (this.builderParent != null)
/*  239 */         markClean(); 
/*      */     }
/*      */     
/*      */     protected void markClean() {
/*  248 */       this.isClean = true;
/*      */     }
/*      */     
/*      */     protected boolean isClean() {
/*  257 */       return this.isClean;
/*      */     }
/*      */     
/*      */     public BuilderType clone() {
/*  265 */       throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
/*      */     }
/*      */     
/*      */     public BuilderType clear() {
/*  274 */       this.unknownFields = UnknownFieldSet.getDefaultInstance();
/*  275 */       onChanged();
/*  276 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public Descriptors.Descriptor getDescriptorForType() {
/*  288 */       return (internalGetFieldAccessorTable()).descriptor;
/*      */     }
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  293 */       return Collections.unmodifiableMap(getAllFieldsMutable());
/*      */     }
/*      */     
/*      */     private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable() {
/*  298 */       TreeMap<Descriptors.FieldDescriptor, Object> result = new TreeMap<Descriptors.FieldDescriptor, Object>();
/*  300 */       Descriptors.Descriptor descriptor = (internalGetFieldAccessorTable()).descriptor;
/*  301 */       for (Descriptors.FieldDescriptor field : descriptor.getFields()) {
/*  302 */         if (field.isRepeated()) {
/*  303 */           List value = (List)getField(field);
/*  304 */           if (!value.isEmpty())
/*  305 */             result.put(field, value); 
/*      */           continue;
/*      */         } 
/*  308 */         if (hasField(field))
/*  309 */           result.put(field, getField(field)); 
/*      */       } 
/*  313 */       return result;
/*      */     }
/*      */     
/*      */     public Message.Builder newBuilderForField(Descriptors.FieldDescriptor field) {
/*  318 */       return internalGetFieldAccessorTable().getField(field).newBuilder();
/*      */     }
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/*  323 */       return internalGetFieldAccessorTable().getField(field).has(this);
/*      */     }
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/*  328 */       Object object = internalGetFieldAccessorTable().getField(field).get(this);
/*  329 */       if (field.isRepeated())
/*  332 */         return Collections.unmodifiableList((List)object); 
/*  334 */       return object;
/*      */     }
/*      */     
/*      */     public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
/*  340 */       internalGetFieldAccessorTable().getField(field).set(this, value);
/*  341 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public BuilderType clearField(Descriptors.FieldDescriptor field) {
/*  346 */       internalGetFieldAccessorTable().getField(field).clear(this);
/*  347 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/*  352 */       return internalGetFieldAccessorTable().getField(field).getRepeatedCount(this);
/*      */     }
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/*  359 */       return internalGetFieldAccessorTable().getField(field).getRepeated(this, index);
/*      */     }
/*      */     
/*      */     public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/*  365 */       internalGetFieldAccessorTable().getField(field).setRepeated(this, index, value);
/*  367 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/*  372 */       internalGetFieldAccessorTable().getField(field).addRepeated(this, value);
/*  373 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public final BuilderType setUnknownFields(UnknownFieldSet unknownFields) {
/*  378 */       this.unknownFields = unknownFields;
/*  379 */       onChanged();
/*  380 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public final BuilderType mergeUnknownFields(UnknownFieldSet unknownFields) {
/*  386 */       this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFields).build();
/*  390 */       onChanged();
/*  391 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public boolean isInitialized() {
/*  396 */       for (Descriptors.FieldDescriptor field : getDescriptorForType().getFields()) {
/*  398 */         if (field.isRequired() && 
/*  399 */           !hasField(field))
/*  400 */           return false; 
/*  404 */         if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/*  405 */           if (field.isRepeated()) {
/*  407 */             List<Message> messageList = (List<Message>)getField(field);
/*  408 */             for (Message element : messageList) {
/*  409 */               if (!element.isInitialized())
/*  410 */                 return false; 
/*      */             } 
/*      */             continue;
/*      */           } 
/*  414 */           if (hasField(field) && !((Message)getField(field)).isInitialized())
/*  416 */             return false; 
/*      */         } 
/*      */       } 
/*  421 */       return true;
/*      */     }
/*      */     
/*      */     public final UnknownFieldSet getUnknownFields() {
/*  426 */       return this.unknownFields;
/*      */     }
/*      */     
/*      */     protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  438 */       return unknownFields.mergeFieldFrom(tag, input);
/*      */     }
/*      */     
/*      */     private class BuilderParentImpl implements GeneratedMessage.BuilderParent {
/*      */       private BuilderParentImpl() {}
/*      */       
/*      */       public void markDirty() {
/*  450 */         GeneratedMessage.Builder.this.onChanged();
/*      */       }
/*      */     }
/*      */     
/*      */     protected GeneratedMessage.BuilderParent getParentForChildren() {
/*  459 */       if (this.meAsParent == null)
/*  460 */         this.meAsParent = new BuilderParentImpl(); 
/*  462 */       return this.meAsParent;
/*      */     }
/*      */     
/*      */     protected final void onChanged() {
/*  470 */       if (this.isClean && this.builderParent != null) {
/*  471 */         this.builderParent.markDirty();
/*  474 */         this.isClean = false;
/*      */       } 
/*      */     }
/*      */     
/*      */     protected abstract GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable();
/*      */   }
/*      */   
/*      */   public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage implements ExtendableMessageOrBuilder<MessageType> {
/*      */     private final FieldSet<Descriptors.FieldDescriptor> extensions;
/*      */     
/*      */     protected ExtendableMessage() {
/*  545 */       this.extensions = FieldSet.newFieldSet();
/*      */     }
/*      */     
/*      */     protected ExtendableMessage(GeneratedMessage.ExtendableBuilder<MessageType, ?> builder) {
/*  550 */       super(builder);
/*  551 */       this.extensions = builder.buildExtensions();
/*      */     }
/*      */     
/*      */     private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension<MessageType, ?> extension) {
/*  556 */       if (extension.getDescriptor().getContainingType() != getDescriptorForType())
/*  559 */         throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\"."); 
/*      */     }
/*      */     
/*      */     public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/*  571 */       verifyExtensionContainingType(extension);
/*  572 */       return this.extensions.hasField(extension.getDescriptor());
/*      */     }
/*      */     
/*      */     public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension) {
/*  579 */       verifyExtensionContainingType(extension);
/*  580 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  581 */       return this.extensions.getRepeatedFieldCount(descriptor);
/*      */     }
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/*  589 */       verifyExtensionContainingType(extension);
/*  590 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  591 */       Object value = this.extensions.getField(descriptor);
/*  592 */       if (value == null) {
/*  593 */         if (descriptor.isRepeated())
/*  594 */           return (Type)Collections.emptyList(); 
/*  595 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*  597 */           return (Type)extension.getMessageDefaultInstance(); 
/*  599 */         return (Type)extension.fromReflectionType(descriptor.getDefaultValue());
/*      */       } 
/*  603 */       return (Type)extension.fromReflectionType(value);
/*      */     }
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/*  613 */       verifyExtensionContainingType(extension);
/*  614 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  615 */       return (Type)extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
/*      */     }
/*      */     
/*      */     protected boolean extensionsAreInitialized() {
/*  621 */       return this.extensions.isInitialized();
/*      */     }
/*      */     
/*      */     public boolean isInitialized() {
/*  626 */       return (super.isInitialized() && extensionsAreInitialized());
/*      */     }
/*      */     
/*      */     protected class ExtensionWriter {
/*  639 */       private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
/*      */       
/*      */       private Map.Entry<Descriptors.FieldDescriptor, Object> next;
/*      */       
/*      */       private final boolean messageSetWireFormat;
/*      */       
/*      */       private ExtensionWriter(boolean messageSetWireFormat) {
/*  645 */         if (this.iter.hasNext())
/*  646 */           this.next = this.iter.next(); 
/*  648 */         this.messageSetWireFormat = messageSetWireFormat;
/*      */       }
/*      */       
/*      */       public void writeUntil(int end, CodedOutputStream output) throws IOException {
/*  653 */         while (this.next != null && ((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < end) {
/*  654 */           Descriptors.FieldDescriptor descriptor = this.next.getKey();
/*  655 */           if (this.messageSetWireFormat && descriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !descriptor.isRepeated()) {
/*  658 */             output.writeMessageSetExtension(descriptor.getNumber(), (Message)this.next.getValue());
/*      */           } else {
/*  661 */             FieldSet.writeField(descriptor, this.next.getValue(), output);
/*      */           } 
/*  663 */           if (this.iter.hasNext()) {
/*  664 */             this.next = this.iter.next();
/*      */             continue;
/*      */           } 
/*  666 */           this.next = null;
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/*      */     protected ExtensionWriter newExtensionWriter() {
/*  673 */       return new ExtensionWriter(false);
/*      */     }
/*      */     
/*      */     protected ExtensionWriter newMessageSetExtensionWriter() {
/*  676 */       return new ExtensionWriter(true);
/*      */     }
/*      */     
/*      */     protected int extensionsSerializedSize() {
/*  681 */       return this.extensions.getSerializedSize();
/*      */     }
/*      */     
/*      */     protected int extensionsSerializedSizeAsMessageSet() {
/*  684 */       return this.extensions.getMessageSetSerializedSize();
/*      */     }
/*      */     
/*      */     protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields() {
/*  691 */       return this.extensions.getAllFields();
/*      */     }
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  696 */       Map<Descriptors.FieldDescriptor, Object> result = getAllFieldsMutable();
/*  697 */       result.putAll(getExtensionFields());
/*  698 */       return Collections.unmodifiableMap(result);
/*      */     }
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/*  703 */       if (field.isExtension()) {
/*  704 */         verifyContainingType(field);
/*  705 */         return this.extensions.hasField(field);
/*      */       } 
/*  707 */       return super.hasField(field);
/*      */     }
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/*  713 */       if (field.isExtension()) {
/*  714 */         verifyContainingType(field);
/*  715 */         Object value = this.extensions.getField(field);
/*  716 */         if (value == null) {
/*  717 */           if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*  720 */             return DynamicMessage.getDefaultInstance(field.getMessageType()); 
/*  722 */           return field.getDefaultValue();
/*      */         } 
/*  725 */         return value;
/*      */       } 
/*  728 */       return super.getField(field);
/*      */     }
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/*  734 */       if (field.isExtension()) {
/*  735 */         verifyContainingType(field);
/*  736 */         return this.extensions.getRepeatedFieldCount(field);
/*      */       } 
/*  738 */       return super.getRepeatedFieldCount(field);
/*      */     }
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/*  745 */       if (field.isExtension()) {
/*  746 */         verifyContainingType(field);
/*  747 */         return this.extensions.getRepeatedField(field, index);
/*      */       } 
/*  749 */       return super.getRepeatedField(field, index);
/*      */     }
/*      */     
/*      */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/*  754 */       if (field.getContainingType() != getDescriptorForType())
/*  755 */         throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder> extends Builder<BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
/*  805 */     private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();
/*      */     
/*      */     protected ExtendableBuilder(GeneratedMessage.BuilderParent parent) {
/*  811 */       super(parent);
/*      */     }
/*      */     
/*      */     public BuilderType clear() {
/*  816 */       this.extensions = FieldSet.emptySet();
/*  817 */       return super.clear();
/*      */     }
/*      */     
/*      */     public BuilderType clone() {
/*  825 */       throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
/*      */     }
/*      */     
/*      */     private void ensureExtensionsIsMutable() {
/*  830 */       if (this.extensions.isImmutable())
/*  831 */         this.extensions = this.extensions.clone(); 
/*      */     }
/*      */     
/*      */     private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension<MessageType, ?> extension) {
/*  837 */       if (extension.getDescriptor().getContainingType() != getDescriptorForType())
/*  840 */         throw new IllegalArgumentException("Extension is for type \"" + extension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\"."); 
/*      */     }
/*      */     
/*      */     public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/*  852 */       verifyExtensionContainingType(extension);
/*  853 */       return this.extensions.hasField(extension.getDescriptor());
/*      */     }
/*      */     
/*      */     public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension) {
/*  860 */       verifyExtensionContainingType(extension);
/*  861 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  862 */       return this.extensions.getRepeatedFieldCount(descriptor);
/*      */     }
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension) {
/*  869 */       verifyExtensionContainingType(extension);
/*  870 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  871 */       Object value = this.extensions.getField(descriptor);
/*  872 */       if (value == null) {
/*  873 */         if (descriptor.isRepeated())
/*  874 */           return (Type)Collections.emptyList(); 
/*  875 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*  877 */           return (Type)extension.getMessageDefaultInstance(); 
/*  879 */         return (Type)extension.fromReflectionType(descriptor.getDefaultValue());
/*      */       } 
/*  883 */       return (Type)extension.fromReflectionType(value);
/*      */     }
/*      */     
/*      */     public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index) {
/*  892 */       verifyExtensionContainingType(extension);
/*  893 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  894 */       return (Type)extension.singularFromReflectionType(this.extensions.getRepeatedField(descriptor, index));
/*      */     }
/*      */     
/*      */     public final <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> extension, Type value) {
/*  902 */       verifyExtensionContainingType(extension);
/*  903 */       ensureExtensionsIsMutable();
/*  904 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  905 */       this.extensions.setField(descriptor, extension.toReflectionType(value));
/*  906 */       onChanged();
/*  907 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public final <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, int index, Type value) {
/*  914 */       verifyExtensionContainingType(extension);
/*  915 */       ensureExtensionsIsMutable();
/*  916 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  917 */       this.extensions.setRepeatedField(descriptor, index, extension.singularToReflectionType(value));
/*  920 */       onChanged();
/*  921 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public final <Type> BuilderType addExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> extension, Type value) {
/*  928 */       verifyExtensionContainingType(extension);
/*  929 */       ensureExtensionsIsMutable();
/*  930 */       Descriptors.FieldDescriptor descriptor = extension.getDescriptor();
/*  931 */       this.extensions.addRepeatedField(descriptor, extension.singularToReflectionType(value));
/*  933 */       onChanged();
/*  934 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     public final <Type> BuilderType clearExtension(GeneratedMessage.GeneratedExtension<MessageType, ?> extension) {
/*  940 */       verifyExtensionContainingType(extension);
/*  941 */       ensureExtensionsIsMutable();
/*  942 */       this.extensions.clearField(extension.getDescriptor());
/*  943 */       onChanged();
/*  944 */       return (BuilderType)this;
/*      */     }
/*      */     
/*      */     protected boolean extensionsAreInitialized() {
/*  949 */       return this.extensions.isInitialized();
/*      */     }
/*      */     
/*      */     private FieldSet<Descriptors.FieldDescriptor> buildExtensions() {
/*  957 */       this.extensions.makeImmutable();
/*  958 */       return this.extensions;
/*      */     }
/*      */     
/*      */     public boolean isInitialized() {
/*  963 */       return (super.isInitialized() && extensionsAreInitialized());
/*      */     }
/*      */     
/*      */     protected boolean parseUnknownField(CodedInputStream input, UnknownFieldSet.Builder unknownFields, ExtensionRegistryLite extensionRegistry, int tag) throws IOException {
/*  976 */       return AbstractMessage.Builder.mergeFieldFrom(input, unknownFields, extensionRegistry, this, tag);
/*      */     }
/*      */     
/*      */     public Map<Descriptors.FieldDescriptor, Object> getAllFields() {
/*  985 */       Map<Descriptors.FieldDescriptor, Object> result = getAllFieldsMutable();
/*  986 */       result.putAll(this.extensions.getAllFields());
/*  987 */       return Collections.unmodifiableMap(result);
/*      */     }
/*      */     
/*      */     public Object getField(Descriptors.FieldDescriptor field) {
/*  992 */       if (field.isExtension()) {
/*  993 */         verifyContainingType(field);
/*  994 */         Object value = this.extensions.getField(field);
/*  995 */         if (value == null) {
/*  996 */           if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/*  999 */             return DynamicMessage.getDefaultInstance(field.getMessageType()); 
/* 1001 */           return field.getDefaultValue();
/*      */         } 
/* 1004 */         return value;
/*      */       } 
/* 1007 */       return super.getField(field);
/*      */     }
/*      */     
/*      */     public int getRepeatedFieldCount(Descriptors.FieldDescriptor field) {
/* 1013 */       if (field.isExtension()) {
/* 1014 */         verifyContainingType(field);
/* 1015 */         return this.extensions.getRepeatedFieldCount(field);
/*      */       } 
/* 1017 */       return super.getRepeatedFieldCount(field);
/*      */     }
/*      */     
/*      */     public Object getRepeatedField(Descriptors.FieldDescriptor field, int index) {
/* 1024 */       if (field.isExtension()) {
/* 1025 */         verifyContainingType(field);
/* 1026 */         return this.extensions.getRepeatedField(field, index);
/*      */       } 
/* 1028 */       return super.getRepeatedField(field, index);
/*      */     }
/*      */     
/*      */     public boolean hasField(Descriptors.FieldDescriptor field) {
/* 1034 */       if (field.isExtension()) {
/* 1035 */         verifyContainingType(field);
/* 1036 */         return this.extensions.hasField(field);
/*      */       } 
/* 1038 */       return super.hasField(field);
/*      */     }
/*      */     
/*      */     public BuilderType setField(Descriptors.FieldDescriptor field, Object value) {
/* 1045 */       if (field.isExtension()) {
/* 1046 */         verifyContainingType(field);
/* 1047 */         ensureExtensionsIsMutable();
/* 1048 */         this.extensions.setField(field, value);
/* 1049 */         onChanged();
/* 1050 */         return (BuilderType)this;
/*      */       } 
/* 1052 */       return super.setField(field, value);
/*      */     }
/*      */     
/*      */     public BuilderType clearField(Descriptors.FieldDescriptor field) {
/* 1058 */       if (field.isExtension()) {
/* 1059 */         verifyContainingType(field);
/* 1060 */         ensureExtensionsIsMutable();
/* 1061 */         this.extensions.clearField(field);
/* 1062 */         onChanged();
/* 1063 */         return (BuilderType)this;
/*      */       } 
/* 1065 */       return super.clearField(field);
/*      */     }
/*      */     
/*      */     public BuilderType setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
/* 1072 */       if (field.isExtension()) {
/* 1073 */         verifyContainingType(field);
/* 1074 */         ensureExtensionsIsMutable();
/* 1075 */         this.extensions.setRepeatedField(field, index, value);
/* 1076 */         onChanged();
/* 1077 */         return (BuilderType)this;
/*      */       } 
/* 1079 */       return super.setRepeatedField(field, index, value);
/*      */     }
/*      */     
/*      */     public BuilderType addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
/* 1086 */       if (field.isExtension()) {
/* 1087 */         verifyContainingType(field);
/* 1088 */         ensureExtensionsIsMutable();
/* 1089 */         this.extensions.addRepeatedField(field, value);
/* 1090 */         onChanged();
/* 1091 */         return (BuilderType)this;
/*      */       } 
/* 1093 */       return super.addRepeatedField(field, value);
/*      */     }
/*      */     
/*      */     protected final void mergeExtensionFields(GeneratedMessage.ExtendableMessage other) {
/* 1098 */       ensureExtensionsIsMutable();
/* 1099 */       this.extensions.mergeFrom(other.extensions);
/* 1100 */       onChanged();
/*      */     }
/*      */     
/*      */     private void verifyContainingType(Descriptors.FieldDescriptor field) {
/* 1104 */       if (field.getContainingType() != getDescriptorForType())
/* 1105 */         throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
/*      */     }
/*      */     
/*      */     protected ExtendableBuilder() {}
/*      */   }
/*      */   
/*      */   public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(final Message scope, final int descriptorIndex, Class singularType, Message defaultInstance) {
/* 1131 */     return new GeneratedExtension<ContainingType, Type>(new ExtensionDescriptorRetriever() {
/*      */           public Descriptors.FieldDescriptor getDescriptor() {
/* 1135 */             return scope.getDescriptorForType().getExtensions().get(descriptorIndex);
/*      */           }
/*      */         },  singularType, defaultInstance);
/*      */   }
/*      */   
/*      */   public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class singularType, Message defaultInstance) {
/* 1151 */     return new GeneratedExtension<ContainingType, Type>(null, singularType, defaultInstance);
/*      */   }
/*      */   
/*      */   public static final class GeneratedExtension<ContainingType extends Message, Type> {
/*      */     private GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever;
/*      */     
/*      */     private final Class singularType;
/*      */     
/*      */     private final Message messageDefaultInstance;
/*      */     
/*      */     private final Method enumValueOf;
/*      */     
/*      */     private final Method enumGetValueDescriptor;
/*      */     
/*      */     private GeneratedExtension(GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever, Class<?> singularType, Message messageDefaultInstance) {
/* 1202 */       if (Message.class.isAssignableFrom(singularType) && !singularType.isInstance(messageDefaultInstance))
/* 1204 */         throw new IllegalArgumentException("Bad messageDefaultInstance for " + singularType.getName()); 
/* 1207 */       this.descriptorRetriever = descriptorRetriever;
/* 1208 */       this.singularType = singularType;
/* 1209 */       this.messageDefaultInstance = messageDefaultInstance;
/* 1211 */       if (ProtocolMessageEnum.class.isAssignableFrom(singularType)) {
/* 1212 */         this.enumValueOf = GeneratedMessage.getMethodOrDie(singularType, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
/* 1214 */         this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(singularType, "getValueDescriptor", new Class[0]);
/*      */       } else {
/* 1217 */         this.enumValueOf = null;
/* 1218 */         this.enumGetValueDescriptor = null;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void internalInit(final Descriptors.FieldDescriptor descriptor) {
/* 1224 */       if (this.descriptorRetriever != null)
/* 1225 */         throw new IllegalStateException("Already initialized."); 
/* 1227 */       this.descriptorRetriever = new GeneratedMessage.ExtensionDescriptorRetriever() {
/*      */           public Descriptors.FieldDescriptor getDescriptor() {
/* 1230 */             return descriptor;
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*      */     public Descriptors.FieldDescriptor getDescriptor() {
/* 1242 */       if (this.descriptorRetriever == null)
/* 1243 */         throw new IllegalStateException("getDescriptor() called before internalInit()"); 
/* 1246 */       return this.descriptorRetriever.getDescriptor();
/*      */     }
/*      */     
/*      */     public Message getMessageDefaultInstance() {
/* 1254 */       return this.messageDefaultInstance;
/*      */     }
/*      */     
/*      */     private Object fromReflectionType(Object value) {
/* 1265 */       Descriptors.FieldDescriptor descriptor = getDescriptor();
/* 1266 */       if (descriptor.isRepeated()) {
/* 1267 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE || descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1270 */           List<Object> result = new ArrayList();
/* 1271 */           for (Object element : value)
/* 1272 */             result.add(singularFromReflectionType(element)); 
/* 1274 */           return result;
/*      */         } 
/* 1276 */         return value;
/*      */       } 
/* 1279 */       return singularFromReflectionType(value);
/*      */     }
/*      */     
/*      */     private Object singularFromReflectionType(Object value) {
/* 1288 */       Descriptors.FieldDescriptor descriptor = getDescriptor();
/* 1289 */       switch (descriptor.getJavaType()) {
/*      */         case MESSAGE:
/* 1291 */           if (this.singularType.isInstance(value))
/* 1292 */             return value; 
/* 1300 */           return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)value).build();
/*      */         case ENUM:
/* 1304 */           return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[] { value });
/*      */       } 
/* 1306 */       return value;
/*      */     }
/*      */     
/*      */     private Object toReflectionType(Object value) {
/* 1318 */       Descriptors.FieldDescriptor descriptor = getDescriptor();
/* 1319 */       if (descriptor.isRepeated()) {
/* 1320 */         if (descriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1322 */           List<Object> result = new ArrayList();
/* 1323 */           for (Object element : value)
/* 1324 */             result.add(singularToReflectionType(element)); 
/* 1326 */           return result;
/*      */         } 
/* 1328 */         return value;
/*      */       } 
/* 1331 */       return singularToReflectionType(value);
/*      */     }
/*      */     
/*      */     private Object singularToReflectionType(Object value) {
/* 1340 */       Descriptors.FieldDescriptor descriptor = getDescriptor();
/* 1341 */       switch (descriptor.getJavaType()) {
/*      */         case ENUM:
/* 1343 */           return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, value, new Object[0]);
/*      */       } 
/* 1345 */       return value;
/*      */     }
/*      */   }
/*      */   
/*      */   private static Method getMethodOrDie(Class clazz, String name, Class... params) {
/*      */     try {
/* 1357 */       return clazz.getMethod(name, params);
/* 1358 */     } catch (NoSuchMethodException e) {
/* 1359 */       throw new RuntimeException("Generated message class \"" + clazz.getName() + "\" missing method \"" + name + "\".", e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Object invokeOrDie(Method method, Object object, Object... params) {
/*      */     try {
/* 1369 */       return method.invoke(object, params);
/* 1370 */     } catch (IllegalAccessException e) {
/* 1371 */       throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
/* 1374 */     } catch (InvocationTargetException e) {
/* 1375 */       Throwable cause = e.getCause();
/* 1376 */       if (cause instanceof RuntimeException)
/* 1377 */         throw (RuntimeException)cause; 
/* 1378 */       if (cause instanceof Error)
/* 1379 */         throw (Error)cause; 
/* 1381 */       throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final class FieldAccessorTable {
/*      */     private final Descriptors.Descriptor descriptor;
/*      */     
/*      */     private final FieldAccessor[] fields;
/*      */     
/*      */     public FieldAccessorTable(Descriptors.Descriptor descriptor, String[] camelCaseNames, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1408 */       this.descriptor = descriptor;
/* 1409 */       this.fields = new FieldAccessor[descriptor.getFields().size()];
/* 1411 */       for (int i = 0; i < this.fields.length; i++) {
/* 1412 */         Descriptors.FieldDescriptor field = descriptor.getFields().get(i);
/* 1413 */         if (field.isRepeated()) {
/* 1414 */           if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1415 */             this.fields[i] = new RepeatedMessageFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/* 1417 */           } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1418 */             this.fields[i] = new RepeatedEnumFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/*      */           } else {
/* 1421 */             this.fields[i] = new RepeatedFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/*      */           } 
/* 1425 */         } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 1426 */           this.fields[i] = new SingularMessageFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/* 1428 */         } else if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
/* 1429 */           this.fields[i] = new SingularEnumFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/*      */         } else {
/* 1432 */           this.fields[i] = new SingularFieldAccessor(field, camelCaseNames[i], messageClass, builderClass);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private FieldAccessor getField(Descriptors.FieldDescriptor field) {
/* 1444 */       if (field.getContainingType() != this.descriptor)
/* 1445 */         throw new IllegalArgumentException("FieldDescriptor does not match message type."); 
/* 1447 */       if (field.isExtension())
/* 1450 */         throw new IllegalArgumentException("This type does not have extensions."); 
/* 1453 */       return this.fields[field.getIndex()];
/*      */     }
/*      */     
/*      */     private static interface FieldAccessor {
/*      */       Object get(GeneratedMessage param2GeneratedMessage);
/*      */       
/*      */       Object get(GeneratedMessage.Builder param2Builder);
/*      */       
/*      */       void set(GeneratedMessage.Builder param2Builder, Object param2Object);
/*      */       
/*      */       Object getRepeated(GeneratedMessage param2GeneratedMessage, int param2Int);
/*      */       
/*      */       Object getRepeated(GeneratedMessage.Builder param2Builder, int param2Int);
/*      */       
/*      */       void setRepeated(GeneratedMessage.Builder param2Builder, int param2Int, Object param2Object);
/*      */       
/*      */       void addRepeated(GeneratedMessage.Builder param2Builder, Object param2Object);
/*      */       
/*      */       boolean has(GeneratedMessage param2GeneratedMessage);
/*      */       
/*      */       boolean has(GeneratedMessage.Builder param2Builder);
/*      */       
/*      */       int getRepeatedCount(GeneratedMessage param2GeneratedMessage);
/*      */       
/*      */       int getRepeatedCount(GeneratedMessage.Builder param2Builder);
/*      */       
/*      */       void clear(GeneratedMessage.Builder param2Builder);
/*      */       
/*      */       Message.Builder newBuilder();
/*      */     }
/*      */     
/*      */     private static class SingularFieldAccessor implements FieldAccessor {
/*      */       protected final Class<?> type;
/*      */       
/*      */       protected final Method getMethod;
/*      */       
/*      */       protected final Method getMethodBuilder;
/*      */       
/*      */       protected final Method setMethod;
/*      */       
/*      */       protected final Method hasMethod;
/*      */       
/*      */       protected final Method hasMethodBuilder;
/*      */       
/*      */       protected final Method clearMethod;
/*      */       
/*      */       SingularFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1484 */         this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[0]);
/* 1485 */         this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[0]);
/* 1486 */         this.type = this.getMethod.getReturnType();
/* 1487 */         this.setMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, new Class[] { this.type });
/* 1488 */         this.hasMethod = GeneratedMessage.getMethodOrDie(messageClass, "has" + camelCaseName, new Class[0]);
/* 1490 */         this.hasMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "has" + camelCaseName, new Class[0]);
/* 1492 */         this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage message) {
/* 1507 */         return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage.Builder builder) {
/* 1510 */         return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
/*      */       }
/*      */       
/*      */       public void set(GeneratedMessage.Builder builder, Object value) {
/* 1513 */         GeneratedMessage.invokeOrDie(this.setMethod, builder, new Object[] { value });
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage message, int index) {
/* 1517 */         throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage.Builder builder, int index) {
/* 1521 */         throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public void setRepeated(GeneratedMessage.Builder builder, int index, Object value) {
/* 1526 */         throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public void addRepeated(GeneratedMessage.Builder builder, Object value) {
/* 1530 */         throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessage message) {
/* 1534 */         return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, message, new Object[0])).booleanValue();
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessage.Builder builder) {
/* 1537 */         return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessage message) {
/* 1540 */         throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessage.Builder builder) {
/* 1544 */         throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
/*      */       }
/*      */       
/*      */       public void clear(GeneratedMessage.Builder builder) {
/* 1548 */         GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 1551 */         throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
/*      */       }
/*      */     }
/*      */     
/*      */     private static class RepeatedFieldAccessor implements FieldAccessor {
/*      */       protected final Class type;
/*      */       
/*      */       protected final Method getMethod;
/*      */       
/*      */       protected final Method getMethodBuilder;
/*      */       
/*      */       protected final Method getRepeatedMethod;
/*      */       
/*      */       protected final Method getRepeatedMethodBuilder;
/*      */       
/*      */       protected final Method setRepeatedMethod;
/*      */       
/*      */       protected final Method addRepeatedMethod;
/*      */       
/*      */       protected final Method getCountMethod;
/*      */       
/*      */       protected final Method getCountMethodBuilder;
/*      */       
/*      */       protected final Method clearMethod;
/*      */       
/*      */       RepeatedFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1572 */         this.getMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "List", new Class[0]);
/* 1574 */         this.getMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "List", new Class[0]);
/* 1578 */         this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName, new Class[] { int.class });
/* 1580 */         this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName, new Class[] { int.class });
/* 1582 */         this.type = this.getRepeatedMethod.getReturnType();
/* 1583 */         this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "set" + camelCaseName, new Class[] { int.class, this.type });
/* 1586 */         this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(builderClass, "add" + camelCaseName, new Class[] { this.type });
/* 1588 */         this.getCountMethod = GeneratedMessage.getMethodOrDie(messageClass, "get" + camelCaseName + "Count", new Class[0]);
/* 1590 */         this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(builderClass, "get" + camelCaseName + "Count", new Class[0]);
/* 1593 */         this.clearMethod = GeneratedMessage.getMethodOrDie(builderClass, "clear" + camelCaseName, new Class[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage message) {
/* 1597 */         return GeneratedMessage.invokeOrDie(this.getMethod, message, new Object[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage.Builder builder) {
/* 1600 */         return GeneratedMessage.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
/*      */       }
/*      */       
/*      */       public void set(GeneratedMessage.Builder builder, Object value) {
/* 1607 */         clear(builder);
/* 1608 */         for (Object element : value)
/* 1609 */           addRepeated(builder, element); 
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage message, int index) {
/* 1614 */         return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, message, new Object[] { Integer.valueOf(index) });
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage.Builder builder, int index) {
/* 1617 */         return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, builder, new Object[] { Integer.valueOf(index) });
/*      */       }
/*      */       
/*      */       public void setRepeated(GeneratedMessage.Builder builder, int index, Object value) {
/* 1621 */         GeneratedMessage.invokeOrDie(this.setRepeatedMethod, builder, new Object[] { Integer.valueOf(index), value });
/*      */       }
/*      */       
/*      */       public void addRepeated(GeneratedMessage.Builder builder, Object value) {
/* 1624 */         GeneratedMessage.invokeOrDie(this.addRepeatedMethod, builder, new Object[] { value });
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessage message) {
/* 1627 */         throw new UnsupportedOperationException("hasField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public boolean has(GeneratedMessage.Builder builder) {
/* 1631 */         throw new UnsupportedOperationException("hasField() called on a singular field.");
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessage message) {
/* 1635 */         return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, message, new Object[0])).intValue();
/*      */       }
/*      */       
/*      */       public int getRepeatedCount(GeneratedMessage.Builder builder) {
/* 1638 */         return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
/*      */       }
/*      */       
/*      */       public void clear(GeneratedMessage.Builder builder) {
/* 1641 */         GeneratedMessage.invokeOrDie(this.clearMethod, builder, new Object[0]);
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 1644 */         throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
/*      */       }
/*      */     }
/*      */     
/*      */     private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
/*      */       private Method valueOfMethod;
/*      */       
/*      */       private Method getValueDescriptorMethod;
/*      */       
/*      */       SingularEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1657 */         super(descriptor, camelCaseName, messageClass, builderClass);
/* 1659 */         this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
/* 1661 */         this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage message) {
/* 1670 */         return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(message), new Object[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage.Builder builder) {
/* 1675 */         return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
/*      */       }
/*      */       
/*      */       public void set(GeneratedMessage.Builder builder, Object value) {
/* 1680 */         super.set(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       }
/*      */     }
/*      */     
/*      */     private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
/*      */       private final Method valueOfMethod;
/*      */       
/*      */       private final Method getValueDescriptorMethod;
/*      */       
/*      */       RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1690 */         super(descriptor, camelCaseName, messageClass, builderClass);
/* 1692 */         this.valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
/* 1694 */         this.getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage message) {
/* 1704 */         List<Object> newList = new ArrayList();
/* 1705 */         for (Object element : super.get(message))
/* 1706 */           newList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, element, new Object[0])); 
/* 1708 */         return Collections.unmodifiableList(newList);
/*      */       }
/*      */       
/*      */       public Object get(GeneratedMessage.Builder builder) {
/* 1714 */         List<Object> newList = new ArrayList();
/* 1715 */         for (Object element : super.get(builder))
/* 1716 */           newList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, element, new Object[0])); 
/* 1718 */         return Collections.unmodifiableList(newList);
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage message, int index) {
/* 1724 */         return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(message, index), new Object[0]);
/*      */       }
/*      */       
/*      */       public Object getRepeated(GeneratedMessage.Builder builder, int index) {
/* 1730 */         return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, index), new Object[0]);
/*      */       }
/*      */       
/*      */       public void setRepeated(GeneratedMessage.Builder builder, int index, Object value) {
/* 1736 */         super.setRepeated(builder, index, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       }
/*      */       
/*      */       public void addRepeated(GeneratedMessage.Builder builder, Object value) {
/* 1741 */         super.addRepeated(builder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { value }));
/*      */       }
/*      */     }
/*      */     
/*      */     private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
/*      */       private final Method newBuilderMethod;
/*      */       
/*      */       SingularMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1753 */         super(descriptor, camelCaseName, messageClass, builderClass);
/* 1755 */         this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
/*      */       }
/*      */       
/*      */       private Object coerceType(Object value) {
/* 1761 */         if (this.type.isInstance(value))
/* 1762 */           return value; 
/* 1768 */         return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)value).build();
/*      */       }
/*      */       
/*      */       public void set(GeneratedMessage.Builder builder, Object value) {
/* 1775 */         super.set(builder, coerceType(value));
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 1779 */         return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
/*      */       }
/*      */     }
/*      */     
/*      */     private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
/*      */       private final Method newBuilderMethod;
/*      */       
/*      */       RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor descriptor, String camelCaseName, Class<? extends GeneratedMessage> messageClass, Class<? extends GeneratedMessage.Builder> builderClass) {
/* 1789 */         super(descriptor, camelCaseName, messageClass, builderClass);
/* 1791 */         this.newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);
/*      */       }
/*      */       
/*      */       private Object coerceType(Object value) {
/* 1797 */         if (this.type.isInstance(value))
/* 1798 */           return value; 
/* 1804 */         return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)value).build();
/*      */       }
/*      */       
/*      */       public void setRepeated(GeneratedMessage.Builder builder, int index, Object value) {
/* 1812 */         super.setRepeated(builder, index, coerceType(value));
/*      */       }
/*      */       
/*      */       public void addRepeated(GeneratedMessage.Builder builder, Object value) {
/* 1816 */         super.addRepeated(builder, coerceType(value));
/*      */       }
/*      */       
/*      */       public Message.Builder newBuilder() {
/* 1820 */         return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected Object writeReplace() throws ObjectStreamException {
/* 1832 */     return new GeneratedMessageLite.SerializedForm(this);
/*      */   }
/*      */   
/*      */   protected abstract FieldAccessorTable internalGetFieldAccessorTable();
/*      */   
/*      */   protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);
/*      */   
/*      */   private static interface ExtensionDescriptorRetriever {
/*      */     Descriptors.FieldDescriptor getDescriptor();
/*      */   }
/*      */   
/*      */   public static interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageOrBuilder {
/*      */     <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*      */     
/*      */     <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension);
/*      */     
/*      */     <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> param1GeneratedExtension);
/*      */     
/*      */     <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> param1GeneratedExtension, int param1Int);
/*      */   }
/*      */   
/*      */   protected static interface BuilderParent {
/*      */     void markDirty();
/*      */   }
/*      */   
/*      */   private static interface FieldAccessor {
/*      */     Object get(GeneratedMessage param1GeneratedMessage);
/*      */     
/*      */     Object get(GeneratedMessage.Builder param1Builder);
/*      */     
/*      */     void set(GeneratedMessage.Builder param1Builder, Object param1Object);
/*      */     
/*      */     Object getRepeated(GeneratedMessage param1GeneratedMessage, int param1Int);
/*      */     
/*      */     Object getRepeated(GeneratedMessage.Builder param1Builder, int param1Int);
/*      */     
/*      */     void setRepeated(GeneratedMessage.Builder param1Builder, int param1Int, Object param1Object);
/*      */     
/*      */     void addRepeated(GeneratedMessage.Builder param1Builder, Object param1Object);
/*      */     
/*      */     boolean has(GeneratedMessage param1GeneratedMessage);
/*      */     
/*      */     boolean has(GeneratedMessage.Builder param1Builder);
/*      */     
/*      */     int getRepeatedCount(GeneratedMessage param1GeneratedMessage);
/*      */     
/*      */     int getRepeatedCount(GeneratedMessage.Builder param1Builder);
/*      */     
/*      */     void clear(GeneratedMessage.Builder param1Builder);
/*      */     
/*      */     Message.Builder newBuilder();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\GeneratedMessage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */