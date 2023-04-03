/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public final class ExtensionRegistry extends ExtensionRegistryLite {
/*     */   private final Map<String, ExtensionInfo> extensionsByName;
/*     */   
/*     */   private final Map<DescriptorIntPair, ExtensionInfo> extensionsByNumber;
/*     */   
/*     */   public static ExtensionRegistry newInstance() {
/*  96 */     return new ExtensionRegistry();
/*     */   }
/*     */   
/*     */   public static ExtensionRegistry getEmptyRegistry() {
/* 101 */     return EMPTY;
/*     */   }
/*     */   
/*     */   public ExtensionRegistry getUnmodifiable() {
/* 107 */     return new ExtensionRegistry(this);
/*     */   }
/*     */   
/*     */   public static final class ExtensionInfo {
/*     */     public final Descriptors.FieldDescriptor descriptor;
/*     */     
/*     */     public final Message defaultInstance;
/*     */     
/*     */     private ExtensionInfo(Descriptors.FieldDescriptor descriptor) {
/* 122 */       this.descriptor = descriptor;
/* 123 */       this.defaultInstance = null;
/*     */     }
/*     */     
/*     */     private ExtensionInfo(Descriptors.FieldDescriptor descriptor, Message defaultInstance) {
/* 127 */       this.descriptor = descriptor;
/* 128 */       this.defaultInstance = defaultInstance;
/*     */     }
/*     */   }
/*     */   
/*     */   public ExtensionInfo findExtensionByName(String fullName) {
/* 141 */     return this.extensionsByName.get(fullName);
/*     */   }
/*     */   
/*     */   public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor containingType, int fieldNumber) {
/* 152 */     return this.extensionsByNumber.get(new DescriptorIntPair(containingType, fieldNumber));
/*     */   }
/*     */   
/*     */   public void add(GeneratedMessage.GeneratedExtension<?, ?> extension) {
/* 158 */     if (extension.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
/* 160 */       if (extension.getMessageDefaultInstance() == null)
/* 161 */         throw new IllegalStateException("Registered message-type extension had null default instance: " + extension.getDescriptor().getFullName()); 
/* 165 */       add(new ExtensionInfo(extension.getDescriptor(), extension.getMessageDefaultInstance()));
/*     */     } else {
/* 168 */       add(new ExtensionInfo(extension.getDescriptor(), null));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Descriptors.FieldDescriptor type) {
/* 174 */     if (type.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
/* 175 */       throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension."); 
/* 179 */     add(new ExtensionInfo(type, null));
/*     */   }
/*     */   
/*     */   public void add(Descriptors.FieldDescriptor type, Message defaultInstance) {
/* 184 */     if (type.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
/* 185 */       throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension."); 
/* 189 */     add(new ExtensionInfo(type, defaultInstance));
/*     */   }
/*     */   
/*     */   private ExtensionRegistry() {
/* 196 */     this.extensionsByName = new HashMap<String, ExtensionInfo>();
/* 197 */     this.extensionsByNumber = new HashMap<DescriptorIntPair, ExtensionInfo>();
/*     */   }
/*     */   
/*     */   private ExtensionRegistry(ExtensionRegistry other) {
/* 201 */     super(other);
/* 202 */     this.extensionsByName = Collections.unmodifiableMap(other.extensionsByName);
/* 203 */     this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
/*     */   }
/*     */   
/*     */   private ExtensionRegistry(boolean empty) {
/* 211 */     super(ExtensionRegistryLite.getEmptyRegistry());
/* 212 */     this.extensionsByName = Collections.emptyMap();
/* 213 */     this.extensionsByNumber = Collections.emptyMap();
/*     */   }
/*     */   
/* 216 */   private static final ExtensionRegistry EMPTY = new ExtensionRegistry(true);
/*     */   
/*     */   private void add(ExtensionInfo extension) {
/* 219 */     if (!extension.descriptor.isExtension())
/* 220 */       throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field."); 
/* 225 */     this.extensionsByName.put(extension.descriptor.getFullName(), extension);
/* 226 */     this.extensionsByNumber.put(new DescriptorIntPair(extension.descriptor.getContainingType(), extension.descriptor.getNumber()), extension);
/* 231 */     Descriptors.FieldDescriptor field = extension.descriptor;
/* 232 */     if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType())
/* 239 */       this.extensionsByName.put(field.getMessageType().getFullName(), extension); 
/*     */   }
/*     */   
/*     */   private static final class DescriptorIntPair {
/*     */     private final Descriptors.Descriptor descriptor;
/*     */     
/*     */     private final int number;
/*     */     
/*     */     DescriptorIntPair(Descriptors.Descriptor descriptor, int number) {
/* 249 */       this.descriptor = descriptor;
/* 250 */       this.number = number;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 255 */       return this.descriptor.hashCode() * 65535 + this.number;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 259 */       if (!(obj instanceof DescriptorIntPair))
/* 260 */         return false; 
/* 262 */       DescriptorIntPair other = (DescriptorIntPair)obj;
/* 263 */       return (this.descriptor == other.descriptor && this.number == other.number);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\ExtensionRegistry.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */