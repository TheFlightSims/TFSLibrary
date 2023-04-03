/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ public class RepeatedFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
/*     */   private GeneratedMessage.BuilderParent parent;
/*     */   
/*     */   private List<MType> messages;
/*     */   
/*     */   private boolean isMessagesListMutable;
/*     */   
/*     */   private List<SingleFieldBuilder<MType, BType, IType>> builders;
/*     */   
/*     */   private boolean isClean;
/*     */   
/*     */   private MessageExternalList<MType, BType, IType> externalMessageList;
/*     */   
/*     */   private BuilderExternalList<MType, BType, IType> externalBuilderList;
/*     */   
/*     */   private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
/*     */   
/*     */   public RepeatedFieldBuilder(List<MType> messages, boolean isMessagesListMutable, GeneratedMessage.BuilderParent parent, boolean isClean) {
/* 137 */     this.messages = messages;
/* 138 */     this.isMessagesListMutable = isMessagesListMutable;
/* 139 */     this.parent = parent;
/* 140 */     this.isClean = isClean;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 145 */     this.parent = null;
/*     */   }
/*     */   
/*     */   private void ensureMutableMessageList() {
/* 153 */     if (!this.isMessagesListMutable) {
/* 154 */       this.messages = new ArrayList<MType>(this.messages);
/* 155 */       this.isMessagesListMutable = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void ensureBuilders() {
/* 165 */     if (this.builders == null) {
/* 166 */       this.builders = new ArrayList<SingleFieldBuilder<MType, BType, IType>>(this.messages.size());
/* 169 */       for (int i = 0; i < this.messages.size(); i++)
/* 170 */         this.builders.add(null); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 181 */     return this.messages.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 190 */     return this.messages.isEmpty();
/*     */   }
/*     */   
/*     */   public MType getMessage(int index) {
/* 202 */     return getMessage(index, false);
/*     */   }
/*     */   
/*     */   private MType getMessage(int index, boolean forBuild) {
/* 216 */     if (this.builders == null)
/* 220 */       return this.messages.get(index); 
/* 223 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 224 */     if (builder == null)
/* 228 */       return this.messages.get(index); 
/* 231 */     return forBuild ? builder.build() : builder.getMessage();
/*     */   }
/*     */   
/*     */   public BType getBuilder(int index) {
/* 244 */     ensureBuilders();
/* 245 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 246 */     if (builder == null) {
/* 247 */       GeneratedMessage generatedMessage = (GeneratedMessage)this.messages.get(index);
/* 248 */       builder = new SingleFieldBuilder<MType, BType, IType>((MType)generatedMessage, this, this.isClean);
/* 250 */       this.builders.set(index, builder);
/*     */     } 
/* 252 */     return builder.getBuilder();
/*     */   }
/*     */   
/*     */   public IType getMessageOrBuilder(int index) {
/* 264 */     if (this.builders == null)
/* 268 */       return (IType)this.messages.get(index); 
/* 271 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 272 */     if (builder == null)
/* 276 */       return (IType)this.messages.get(index); 
/* 279 */     return builder.getMessageOrBuilder();
/*     */   }
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> setMessage(int index, MType message) {
/* 293 */     if (message == null)
/* 294 */       throw new NullPointerException(); 
/* 296 */     ensureMutableMessageList();
/* 297 */     this.messages.set(index, message);
/* 298 */     if (this.builders != null) {
/* 299 */       SingleFieldBuilder<MType, BType, IType> entry = this.builders.set(index, null);
/* 301 */       if (entry != null)
/* 302 */         entry.dispose(); 
/*     */     } 
/* 305 */     onChanged();
/* 306 */     incrementModCounts();
/* 307 */     return this;
/*     */   }
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addMessage(MType message) {
/* 318 */     if (message == null)
/* 319 */       throw new NullPointerException(); 
/* 321 */     ensureMutableMessageList();
/* 322 */     this.messages.add(message);
/* 323 */     if (this.builders != null)
/* 324 */       this.builders.add(null); 
/* 326 */     onChanged();
/* 327 */     incrementModCounts();
/* 328 */     return this;
/*     */   }
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addMessage(int index, MType message) {
/* 342 */     if (message == null)
/* 343 */       throw new NullPointerException(); 
/* 345 */     ensureMutableMessageList();
/* 346 */     this.messages.add(index, message);
/* 347 */     if (this.builders != null)
/* 348 */       this.builders.add(index, null); 
/* 350 */     onChanged();
/* 351 */     incrementModCounts();
/* 352 */     return this;
/*     */   }
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addAllMessages(Iterable<? extends MType> values) {
/* 365 */     for (GeneratedMessage generatedMessage : values) {
/* 366 */       if (generatedMessage == null)
/* 367 */         throw new NullPointerException(); 
/*     */     } 
/* 370 */     if (values instanceof Collection) {
/* 372 */       Collection<MType> collection = (Collection)values;
/* 373 */       if (collection.size() == 0)
/* 374 */         return this; 
/* 376 */       ensureMutableMessageList();
/* 377 */       for (GeneratedMessage generatedMessage : values)
/* 378 */         addMessage((MType)generatedMessage); 
/*     */     } else {
/* 381 */       ensureMutableMessageList();
/* 382 */       for (GeneratedMessage generatedMessage : values)
/* 383 */         addMessage((MType)generatedMessage); 
/*     */     } 
/* 386 */     onChanged();
/* 387 */     incrementModCounts();
/* 388 */     return this;
/*     */   }
/*     */   
/*     */   public BType addBuilder(MType message) {
/* 398 */     ensureMutableMessageList();
/* 399 */     ensureBuilders();
/* 400 */     SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder<MType, BType, IType>(message, this, this.isClean);
/* 403 */     this.messages.add(null);
/* 404 */     this.builders.add(builder);
/* 405 */     onChanged();
/* 406 */     incrementModCounts();
/* 407 */     return builder.getBuilder();
/*     */   }
/*     */   
/*     */   public BType addBuilder(int index, MType message) {
/* 420 */     ensureMutableMessageList();
/* 421 */     ensureBuilders();
/* 422 */     SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder<MType, BType, IType>(message, this, this.isClean);
/* 425 */     this.messages.add(index, null);
/* 426 */     this.builders.add(index, builder);
/* 427 */     onChanged();
/* 428 */     incrementModCounts();
/* 429 */     return builder.getBuilder();
/*     */   }
/*     */   
/*     */   public void remove(int index) {
/* 440 */     ensureMutableMessageList();
/* 441 */     this.messages.remove(index);
/* 442 */     if (this.builders != null) {
/* 443 */       SingleFieldBuilder<MType, BType, IType> entry = this.builders.remove(index);
/* 445 */       if (entry != null)
/* 446 */         entry.dispose(); 
/*     */     } 
/* 449 */     onChanged();
/* 450 */     incrementModCounts();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 458 */     this.messages = Collections.emptyList();
/* 459 */     this.isMessagesListMutable = false;
/* 460 */     if (this.builders != null) {
/* 462 */       for (SingleFieldBuilder<MType, BType, IType> entry : this.builders) {
/* 463 */         if (entry != null)
/* 464 */           entry.dispose(); 
/*     */       } 
/* 467 */       this.builders = null;
/*     */     } 
/* 469 */     onChanged();
/* 470 */     incrementModCounts();
/*     */   }
/*     */   
/*     */   public List<MType> build() {
/* 481 */     this.isClean = true;
/* 483 */     if (!this.isMessagesListMutable && this.builders == null)
/* 485 */       return this.messages; 
/* 488 */     boolean allMessagesInSync = true;
/* 489 */     if (!this.isMessagesListMutable) {
/* 492 */       for (int j = 0; j < this.messages.size(); j++) {
/* 493 */         Message message = (Message)this.messages.get(j);
/* 494 */         SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(j);
/* 495 */         if (builder != null && 
/* 496 */           builder.build() != message) {
/* 497 */           allMessagesInSync = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 502 */       if (allMessagesInSync)
/* 504 */         return this.messages; 
/*     */     } 
/* 509 */     ensureMutableMessageList();
/* 510 */     for (int i = 0; i < this.messages.size(); i++)
/* 511 */       this.messages.set(i, getMessage(i, true)); 
/* 516 */     this.messages = Collections.unmodifiableList(this.messages);
/* 517 */     this.isMessagesListMutable = false;
/* 518 */     return this.messages;
/*     */   }
/*     */   
/*     */   public List<MType> getMessageList() {
/* 528 */     if (this.externalMessageList == null)
/* 529 */       this.externalMessageList = new MessageExternalList<MType, BType, IType>(this); 
/* 532 */     return this.externalMessageList;
/*     */   }
/*     */   
/*     */   public List<BType> getBuilderList() {
/* 542 */     if (this.externalBuilderList == null)
/* 543 */       this.externalBuilderList = new BuilderExternalList<MType, BType, IType>(this); 
/* 546 */     return this.externalBuilderList;
/*     */   }
/*     */   
/*     */   public List<IType> getMessageOrBuilderList() {
/* 556 */     if (this.externalMessageOrBuilderList == null)
/* 557 */       this.externalMessageOrBuilderList = new MessageOrBuilderExternalList<MType, BType, IType>(this); 
/* 560 */     return this.externalMessageOrBuilderList;
/*     */   }
/*     */   
/*     */   private void onChanged() {
/* 568 */     if (this.isClean && this.parent != null) {
/* 569 */       this.parent.markDirty();
/* 572 */       this.isClean = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void markDirty() {
/* 578 */     onChanged();
/*     */   }
/*     */   
/*     */   private void incrementModCounts() {
/* 587 */     if (this.externalMessageList != null)
/* 588 */       this.externalMessageList.incrementModCount(); 
/* 590 */     if (this.externalBuilderList != null)
/* 591 */       this.externalBuilderList.incrementModCount(); 
/* 593 */     if (this.externalMessageOrBuilderList != null)
/* 594 */       this.externalMessageOrBuilderList.incrementModCount(); 
/*     */   }
/*     */   
/*     */   private static class MessageExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<MType> implements List<MType> {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */     
/*     */     MessageExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 615 */       this.builder = builder;
/*     */     }
/*     */     
/*     */     public int size() {
/* 619 */       return this.builder.getCount();
/*     */     }
/*     */     
/*     */     public MType get(int index) {
/* 623 */       return this.builder.getMessage(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 627 */       this.modCount++;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class BuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<BType> implements List<BType> {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */     
/*     */     BuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 648 */       this.builder = builder;
/*     */     }
/*     */     
/*     */     public int size() {
/* 652 */       return this.builder.getCount();
/*     */     }
/*     */     
/*     */     public BType get(int index) {
/* 656 */       return this.builder.getBuilder(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 660 */       this.modCount++;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class MessageOrBuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> extends AbstractList<IType> implements List<IType> {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */     
/*     */     MessageOrBuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 681 */       this.builder = builder;
/*     */     }
/*     */     
/*     */     public int size() {
/* 685 */       return this.builder.getCount();
/*     */     }
/*     */     
/*     */     public IType get(int index) {
/* 689 */       return this.builder.getMessageOrBuilder(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 693 */       this.modCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\RepeatedFieldBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */