/*     */ package com.google.protobuf;
/*     */ 
/*     */ public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
/*     */   private GeneratedMessage.BuilderParent parent;
/*     */   
/*     */   private BType builder;
/*     */   
/*     */   private MType message;
/*     */   
/*     */   private boolean isClean;
/*     */   
/*     */   public SingleFieldBuilder(MType message, GeneratedMessage.BuilderParent parent, boolean isClean) {
/*  87 */     if (message == null)
/*  88 */       throw new NullPointerException(); 
/*  90 */     this.message = message;
/*  91 */     this.parent = parent;
/*  92 */     this.isClean = isClean;
/*     */   }
/*     */   
/*     */   public void dispose() {
/*  97 */     this.parent = null;
/*     */   }
/*     */   
/*     */   public MType getMessage() {
/* 110 */     if (this.message == null)
/* 112 */       this.message = (MType)this.builder.buildPartial(); 
/* 114 */     return this.message;
/*     */   }
/*     */   
/*     */   public MType build() {
/* 125 */     this.isClean = true;
/* 126 */     return getMessage();
/*     */   }
/*     */   
/*     */   public BType getBuilder() {
/* 137 */     if (this.builder == null) {
/* 142 */       this.builder = (BType)this.message.newBuilderForType(this);
/* 143 */       this.builder.mergeFrom((Message)this.message);
/* 144 */       this.builder.markClean();
/*     */     } 
/* 146 */     return this.builder;
/*     */   }
/*     */   
/*     */   public IType getMessageOrBuilder() {
/* 157 */     if (this.builder != null)
/* 158 */       return (IType)this.builder; 
/* 160 */     return (IType)this.message;
/*     */   }
/*     */   
/*     */   public SingleFieldBuilder<MType, BType, IType> setMessage(MType message) {
/* 172 */     if (message == null)
/* 173 */       throw new NullPointerException(); 
/* 175 */     this.message = message;
/* 176 */     if (this.builder != null) {
/* 177 */       this.builder.dispose();
/* 178 */       this.builder = null;
/*     */     } 
/* 180 */     onChanged();
/* 181 */     return this;
/*     */   }
/*     */   
/*     */   public SingleFieldBuilder<MType, BType, IType> mergeFrom(MType value) {
/* 192 */     if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
/* 193 */       this.message = value;
/*     */     } else {
/* 195 */       getBuilder().mergeFrom((Message)value);
/*     */     } 
/* 197 */     onChanged();
/* 198 */     return this;
/*     */   }
/*     */   
/*     */   public SingleFieldBuilder<MType, BType, IType> clear() {
/* 208 */     this.message = (MType)((this.message != null) ? (GeneratedMessage)this.message.getDefaultInstanceForType() : (GeneratedMessage)this.builder.getDefaultInstanceForType());
/* 211 */     if (this.builder != null) {
/* 212 */       this.builder.dispose();
/* 213 */       this.builder = null;
/*     */     } 
/* 215 */     onChanged();
/* 216 */     return this;
/*     */   }
/*     */   
/*     */   private void onChanged() {
/* 226 */     if (this.builder != null)
/* 227 */       this.message = null; 
/* 229 */     if (this.isClean && this.parent != null) {
/* 230 */       this.parent.markDirty();
/* 233 */       this.isClean = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markDirty() {
/* 239 */     onChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\SingleFieldBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */