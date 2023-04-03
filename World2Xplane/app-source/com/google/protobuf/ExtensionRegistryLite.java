/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ExtensionRegistryLite {
/*     */   private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;
/*     */   
/*     */   public static ExtensionRegistryLite newInstance() {
/*  76 */     return new ExtensionRegistryLite();
/*     */   }
/*     */   
/*     */   public static ExtensionRegistryLite getEmptyRegistry() {
/*  81 */     return EMPTY;
/*     */   }
/*     */   
/*     */   public ExtensionRegistryLite getUnmodifiable() {
/*  86 */     return new ExtensionRegistryLite(this);
/*     */   }
/*     */   
/*     */   public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingTypeDefaultInstance, int fieldNumber) {
/* 101 */     return (GeneratedMessageLite.GeneratedExtension<ContainingType, ?>)this.extensionsByNumber.get(new ObjectIntPair(containingTypeDefaultInstance, fieldNumber));
/*     */   }
/*     */   
/*     */   public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> extension) {
/* 109 */     this.extensionsByNumber.put(new ObjectIntPair(extension.getContainingTypeDefaultInstance(), extension.getNumber()), extension);
/*     */   }
/*     */   
/*     */   ExtensionRegistryLite() {
/* 122 */     this.extensionsByNumber = new HashMap<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>>();
/*     */   }
/*     */   
/*     */   ExtensionRegistryLite(ExtensionRegistryLite other) {
/* 128 */     if (other == EMPTY) {
/* 129 */       this.extensionsByNumber = Collections.emptyMap();
/*     */     } else {
/* 131 */       this.extensionsByNumber = Collections.unmodifiableMap(other.extensionsByNumber);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ExtensionRegistryLite(boolean empty) {
/* 141 */     this.extensionsByNumber = Collections.emptyMap();
/*     */   }
/*     */   
/* 143 */   private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);
/*     */   
/*     */   private static final class ObjectIntPair {
/*     */     private final Object object;
/*     */     
/*     */     private final int number;
/*     */     
/*     */     ObjectIntPair(Object object, int number) {
/* 152 */       this.object = object;
/* 153 */       this.number = number;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 158 */       return System.identityHashCode(this.object) * 65535 + this.number;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 162 */       if (!(obj instanceof ObjectIntPair))
/* 163 */         return false; 
/* 165 */       ObjectIntPair other = (ObjectIntPair)obj;
/* 166 */       return (this.object == other.object && this.number == other.number);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\ExtensionRegistryLite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */