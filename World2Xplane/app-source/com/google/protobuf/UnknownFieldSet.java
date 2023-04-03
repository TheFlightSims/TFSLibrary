/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public final class UnknownFieldSet implements MessageLite {
/*     */   private UnknownFieldSet() {}
/*     */   
/*     */   public static Builder newBuilder() {
/*  64 */     return Builder.create();
/*     */   }
/*     */   
/*     */   public static Builder newBuilder(UnknownFieldSet copyFrom) {
/*  72 */     return newBuilder().mergeFrom(copyFrom);
/*     */   }
/*     */   
/*     */   public static UnknownFieldSet getDefaultInstance() {
/*  77 */     return defaultInstance;
/*     */   }
/*     */   
/*     */   public UnknownFieldSet getDefaultInstanceForType() {
/*  80 */     return defaultInstance;
/*     */   }
/*     */   
/*  82 */   private static final UnknownFieldSet defaultInstance = new UnknownFieldSet(Collections.emptyMap());
/*     */   
/*     */   private Map<Integer, Field> fields;
/*     */   
/*     */   private UnknownFieldSet(Map<Integer, Field> fields) {
/*  90 */     this.fields = fields;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*  96 */     if (this == other)
/*  97 */       return true; 
/*  99 */     return (other instanceof UnknownFieldSet && this.fields.equals(((UnknownFieldSet)other).fields));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 105 */     return this.fields.hashCode();
/*     */   }
/*     */   
/*     */   public Map<Integer, Field> asMap() {
/* 110 */     return this.fields;
/*     */   }
/*     */   
/*     */   public boolean hasField(int number) {
/* 115 */     return this.fields.containsKey(Integer.valueOf(number));
/*     */   }
/*     */   
/*     */   public Field getField(int number) {
/* 123 */     Field result = this.fields.get(Integer.valueOf(number));
/* 124 */     return (result == null) ? Field.getDefaultInstance() : result;
/*     */   }
/*     */   
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 129 */     for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
/* 130 */       ((Field)entry.getValue()).writeTo(((Integer)entry.getKey()).intValue(), output); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 141 */     return TextFormat.printToString(this);
/*     */   }
/*     */   
/*     */   public ByteString toByteString() {
/*     */     try {
/* 150 */       ByteString.CodedBuilder out = ByteString.newCodedBuilder(getSerializedSize());
/* 152 */       writeTo(out.getCodedOutput());
/* 153 */       return out.build();
/* 154 */     } catch (IOException e) {
/* 155 */       throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] toByteArray() {
/*     */     try {
/* 167 */       byte[] result = new byte[getSerializedSize()];
/* 168 */       CodedOutputStream output = CodedOutputStream.newInstance(result);
/* 169 */       writeTo(output);
/* 170 */       output.checkNoSpaceLeft();
/* 171 */       return result;
/* 172 */     } catch (IOException e) {
/* 173 */       throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeTo(OutputStream output) throws IOException {
/* 184 */     CodedOutputStream codedOutput = CodedOutputStream.newInstance(output);
/* 185 */     writeTo(codedOutput);
/* 186 */     codedOutput.flush();
/*     */   }
/*     */   
/*     */   public void writeDelimitedTo(OutputStream output) throws IOException {
/* 190 */     CodedOutputStream codedOutput = CodedOutputStream.newInstance(output);
/* 191 */     codedOutput.writeRawVarint32(getSerializedSize());
/* 192 */     writeTo(codedOutput);
/* 193 */     codedOutput.flush();
/*     */   }
/*     */   
/*     */   public int getSerializedSize() {
/* 198 */     int result = 0;
/* 199 */     for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
/* 200 */       result += ((Field)entry.getValue()).getSerializedSize(((Integer)entry.getKey()).intValue()); 
/* 202 */     return result;
/*     */   }
/*     */   
/*     */   public void writeAsMessageSetTo(CodedOutputStream output) throws IOException {
/* 211 */     for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
/* 212 */       ((Field)entry.getValue()).writeAsMessageSetExtensionTo(((Integer)entry.getKey()).intValue(), output); 
/*     */   }
/*     */   
/*     */   public int getSerializedSizeAsMessageSet() {
/* 222 */     int result = 0;
/* 223 */     for (Map.Entry<Integer, Field> entry : this.fields.entrySet())
/* 224 */       result += ((Field)entry.getValue()).getSerializedSizeAsMessageSetExtension(((Integer)entry.getKey()).intValue()); 
/* 227 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isInitialized() {
/* 233 */     return true;
/*     */   }
/*     */   
/*     */   public static UnknownFieldSet parseFrom(CodedInputStream input) throws IOException {
/* 239 */     return newBuilder().mergeFrom(input).build();
/*     */   }
/*     */   
/*     */   public static UnknownFieldSet parseFrom(ByteString data) throws InvalidProtocolBufferException {
/* 245 */     return newBuilder().mergeFrom(data).build();
/*     */   }
/*     */   
/*     */   public static UnknownFieldSet parseFrom(byte[] data) throws InvalidProtocolBufferException {
/* 251 */     return newBuilder().mergeFrom(data).build();
/*     */   }
/*     */   
/*     */   public static UnknownFieldSet parseFrom(InputStream input) throws IOException {
/* 257 */     return newBuilder().mergeFrom(input).build();
/*     */   }
/*     */   
/*     */   public Builder newBuilderForType() {
/* 261 */     return newBuilder();
/*     */   }
/*     */   
/*     */   public Builder toBuilder() {
/* 265 */     return newBuilder().mergeFrom(this);
/*     */   }
/*     */   
/*     */   public static final class Builder implements MessageLite.Builder {
/*     */     private Map<Integer, UnknownFieldSet.Field> fields;
/*     */     
/*     */     private int lastFieldNumber;
/*     */     
/*     */     private UnknownFieldSet.Field.Builder lastField;
/*     */     
/*     */     private static Builder create() {
/* 293 */       Builder builder = new Builder();
/* 294 */       builder.reinitialize();
/* 295 */       return builder;
/*     */     }
/*     */     
/*     */     private UnknownFieldSet.Field.Builder getFieldBuilder(int number) {
/* 303 */       if (this.lastField != null) {
/* 304 */         if (number == this.lastFieldNumber)
/* 305 */           return this.lastField; 
/* 308 */         addField(this.lastFieldNumber, this.lastField.build());
/*     */       } 
/* 310 */       if (number == 0)
/* 311 */         return null; 
/* 313 */       UnknownFieldSet.Field existing = this.fields.get(Integer.valueOf(number));
/* 314 */       this.lastFieldNumber = number;
/* 315 */       this.lastField = UnknownFieldSet.Field.newBuilder();
/* 316 */       if (existing != null)
/* 317 */         this.lastField.mergeFrom(existing); 
/* 319 */       return this.lastField;
/*     */     }
/*     */     
/*     */     public UnknownFieldSet build() {
/*     */       UnknownFieldSet result;
/* 332 */       getFieldBuilder(0);
/* 334 */       if (this.fields.isEmpty()) {
/* 335 */         result = UnknownFieldSet.getDefaultInstance();
/*     */       } else {
/* 337 */         result = new UnknownFieldSet(Collections.unmodifiableMap(this.fields));
/*     */       } 
/* 339 */       this.fields = null;
/* 340 */       return result;
/*     */     }
/*     */     
/*     */     public UnknownFieldSet buildPartial() {
/* 345 */       return build();
/*     */     }
/*     */     
/*     */     public Builder clone() {
/* 350 */       getFieldBuilder(0);
/* 351 */       return UnknownFieldSet.newBuilder().mergeFrom(new UnknownFieldSet(this.fields));
/*     */     }
/*     */     
/*     */     public UnknownFieldSet getDefaultInstanceForType() {
/* 356 */       return UnknownFieldSet.getDefaultInstance();
/*     */     }
/*     */     
/*     */     private void reinitialize() {
/* 360 */       this.fields = Collections.emptyMap();
/* 361 */       this.lastFieldNumber = 0;
/* 362 */       this.lastField = null;
/*     */     }
/*     */     
/*     */     public Builder clear() {
/* 367 */       reinitialize();
/* 368 */       return this;
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(UnknownFieldSet other) {
/* 377 */       if (other != UnknownFieldSet.getDefaultInstance())
/* 378 */         for (Map.Entry<Integer, UnknownFieldSet.Field> entry : (Iterable<Map.Entry<Integer, UnknownFieldSet.Field>>)other.fields.entrySet())
/* 379 */           mergeField(((Integer)entry.getKey()).intValue(), entry.getValue());  
/* 382 */       return this;
/*     */     }
/*     */     
/*     */     public Builder mergeField(int number, UnknownFieldSet.Field field) {
/* 390 */       if (number == 0)
/* 391 */         throw new IllegalArgumentException("Zero is not a valid field number."); 
/* 393 */       if (hasField(number)) {
/* 394 */         getFieldBuilder(number).mergeFrom(field);
/*     */       } else {
/* 399 */         addField(number, field);
/*     */       } 
/* 401 */       return this;
/*     */     }
/*     */     
/*     */     public Builder mergeVarintField(int number, int value) {
/* 410 */       if (number == 0)
/* 411 */         throw new IllegalArgumentException("Zero is not a valid field number."); 
/* 413 */       getFieldBuilder(number).addVarint(value);
/* 414 */       return this;
/*     */     }
/*     */     
/*     */     public boolean hasField(int number) {
/* 419 */       if (number == 0)
/* 420 */         throw new IllegalArgumentException("Zero is not a valid field number."); 
/* 422 */       return (number == this.lastFieldNumber || this.fields.containsKey(Integer.valueOf(number)));
/*     */     }
/*     */     
/*     */     public Builder addField(int number, UnknownFieldSet.Field field) {
/* 430 */       if (number == 0)
/* 431 */         throw new IllegalArgumentException("Zero is not a valid field number."); 
/* 433 */       if (this.lastField != null && this.lastFieldNumber == number) {
/* 435 */         this.lastField = null;
/* 436 */         this.lastFieldNumber = 0;
/*     */       } 
/* 438 */       if (this.fields.isEmpty())
/* 439 */         this.fields = new TreeMap<Integer, UnknownFieldSet.Field>(); 
/* 441 */       this.fields.put(Integer.valueOf(number), field);
/* 442 */       return this;
/*     */     }
/*     */     
/*     */     public Map<Integer, UnknownFieldSet.Field> asMap() {
/* 450 */       getFieldBuilder(0);
/* 451 */       return Collections.unmodifiableMap(this.fields);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input) throws IOException {
/*     */       int tag;
/*     */       do {
/* 460 */         tag = input.readTag();
/* 461 */       } while (tag != 0 && mergeFieldFrom(tag, input));
/* 465 */       return this;
/*     */     }
/*     */     
/*     */     public boolean mergeFieldFrom(int tag, CodedInputStream input) throws IOException {
/*     */       Builder subBuilder;
/* 475 */       int number = WireFormat.getTagFieldNumber(tag);
/* 476 */       switch (WireFormat.getTagWireType(tag)) {
/*     */         case 0:
/* 478 */           getFieldBuilder(number).addVarint(input.readInt64());
/* 479 */           return true;
/*     */         case 1:
/* 481 */           getFieldBuilder(number).addFixed64(input.readFixed64());
/* 482 */           return true;
/*     */         case 2:
/* 484 */           getFieldBuilder(number).addLengthDelimited(input.readBytes());
/* 485 */           return true;
/*     */         case 3:
/* 487 */           subBuilder = UnknownFieldSet.newBuilder();
/* 488 */           input.readGroup(number, subBuilder, ExtensionRegistry.getEmptyRegistry());
/* 490 */           getFieldBuilder(number).addGroup(subBuilder.build());
/* 491 */           return true;
/*     */         case 4:
/* 493 */           return false;
/*     */         case 5:
/* 495 */           getFieldBuilder(number).addFixed32(input.readFixed32());
/* 496 */           return true;
/*     */       } 
/* 498 */       throw InvalidProtocolBufferException.invalidWireType();
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(ByteString data) throws InvalidProtocolBufferException {
/*     */       try {
/* 510 */         CodedInputStream input = data.newCodedInput();
/* 511 */         mergeFrom(input);
/* 512 */         input.checkLastTagWas(0);
/* 513 */         return this;
/* 514 */       } catch (InvalidProtocolBufferException e) {
/* 515 */         throw e;
/* 516 */       } catch (IOException e) {
/* 517 */         throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(byte[] data) throws InvalidProtocolBufferException {
/*     */       try {
/* 531 */         CodedInputStream input = CodedInputStream.newInstance(data);
/* 532 */         mergeFrom(input);
/* 533 */         input.checkLastTagWas(0);
/* 534 */         return this;
/* 535 */       } catch (InvalidProtocolBufferException e) {
/* 536 */         throw e;
/* 537 */       } catch (IOException e) {
/* 538 */         throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(InputStream input) throws IOException {
/* 550 */       CodedInputStream codedInput = CodedInputStream.newInstance(input);
/* 551 */       mergeFrom(codedInput);
/* 552 */       codedInput.checkLastTagWas(0);
/* 553 */       return this;
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input) throws IOException {
/* 558 */       int firstByte = input.read();
/* 559 */       if (firstByte == -1)
/* 560 */         return false; 
/* 562 */       int size = CodedInputStream.readRawVarint32(firstByte, input);
/* 563 */       InputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input, size);
/* 564 */       mergeFrom(limitedInput);
/* 565 */       return true;
/*     */     }
/*     */     
/*     */     public boolean mergeDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 572 */       return mergeDelimitedFrom(input);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 579 */       return mergeFrom(input);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 587 */       return mergeFrom(data);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
/*     */       try {
/* 593 */         CodedInputStream input = CodedInputStream.newInstance(data, off, len);
/* 595 */         mergeFrom(input);
/* 596 */         input.checkLastTagWas(0);
/* 597 */         return this;
/* 598 */       } catch (InvalidProtocolBufferException e) {
/* 599 */         throw e;
/* 600 */       } catch (IOException e) {
/* 601 */         throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 612 */       return mergeFrom(data);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
/* 620 */       return mergeFrom(data, off, len);
/*     */     }
/*     */     
/*     */     public Builder mergeFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
/* 627 */       return mergeFrom(input);
/*     */     }
/*     */     
/*     */     public boolean isInitialized() {
/* 633 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class Field {
/*     */     private Field() {}
/*     */     
/*     */     public static Builder newBuilder() {
/* 661 */       return Builder.create();
/*     */     }
/*     */     
/*     */     public static Builder newBuilder(Field copyFrom) {
/* 669 */       return newBuilder().mergeFrom(copyFrom);
/*     */     }
/*     */     
/*     */     public static Field getDefaultInstance() {
/* 674 */       return fieldDefaultInstance;
/*     */     }
/*     */     
/* 676 */     private static final Field fieldDefaultInstance = newBuilder().build();
/*     */     
/*     */     private List<Long> varint;
/*     */     
/*     */     private List<Integer> fixed32;
/*     */     
/*     */     private List<Long> fixed64;
/*     */     
/*     */     private List<ByteString> lengthDelimited;
/*     */     
/*     */     private List<UnknownFieldSet> group;
/*     */     
/*     */     public List<Long> getVarintList() {
/* 679 */       return this.varint;
/*     */     }
/*     */     
/*     */     public List<Integer> getFixed32List() {
/* 682 */       return this.fixed32;
/*     */     }
/*     */     
/*     */     public List<Long> getFixed64List() {
/* 685 */       return this.fixed64;
/*     */     }
/*     */     
/*     */     public List<ByteString> getLengthDelimitedList() {
/* 688 */       return this.lengthDelimited;
/*     */     }
/*     */     
/*     */     public List<UnknownFieldSet> getGroupList() {
/* 695 */       return this.group;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 699 */       if (this == other)
/* 700 */         return true; 
/* 702 */       if (!(other instanceof Field))
/* 703 */         return false; 
/* 705 */       return Arrays.equals(getIdentityArray(), ((Field)other).getIdentityArray());
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 711 */       return Arrays.hashCode(getIdentityArray());
/*     */     }
/*     */     
/*     */     private Object[] getIdentityArray() {
/* 719 */       return new Object[] { this.varint, this.fixed32, this.fixed64, this.lengthDelimited, this.group };
/*     */     }
/*     */     
/*     */     public void writeTo(int fieldNumber, CodedOutputStream output) throws IOException {
/* 733 */       for (Iterator<Long> iterator1 = this.varint.iterator(); iterator1.hasNext(); ) {
/* 733 */         long value = ((Long)iterator1.next()).longValue();
/* 734 */         output.writeUInt64(fieldNumber, value);
/*     */       } 
/* 736 */       for (Iterator<Integer> iterator = this.fixed32.iterator(); iterator.hasNext(); ) {
/* 736 */         int value = ((Integer)iterator.next()).intValue();
/* 737 */         output.writeFixed32(fieldNumber, value);
/*     */       } 
/* 739 */       for (Iterator<Long> i$ = this.fixed64.iterator(); i$.hasNext(); ) {
/* 739 */         long value = ((Long)i$.next()).longValue();
/* 740 */         output.writeFixed64(fieldNumber, value);
/*     */       } 
/* 742 */       for (ByteString value : this.lengthDelimited)
/* 743 */         output.writeBytes(fieldNumber, value); 
/* 745 */       for (UnknownFieldSet value : this.group)
/* 746 */         output.writeGroup(fieldNumber, value); 
/*     */     }
/*     */     
/*     */     public int getSerializedSize(int fieldNumber) {
/* 755 */       int result = 0;
/* 756 */       for (Iterator<Long> iterator1 = this.varint.iterator(); iterator1.hasNext(); ) {
/* 756 */         long value = ((Long)iterator1.next()).longValue();
/* 757 */         result += CodedOutputStream.computeUInt64Size(fieldNumber, value);
/*     */       } 
/* 759 */       for (Iterator<Integer> iterator = this.fixed32.iterator(); iterator.hasNext(); ) {
/* 759 */         int value = ((Integer)iterator.next()).intValue();
/* 760 */         result += CodedOutputStream.computeFixed32Size(fieldNumber, value);
/*     */       } 
/* 762 */       for (Iterator<Long> i$ = this.fixed64.iterator(); i$.hasNext(); ) {
/* 762 */         long value = ((Long)i$.next()).longValue();
/* 763 */         result += CodedOutputStream.computeFixed64Size(fieldNumber, value);
/*     */       } 
/* 765 */       for (ByteString value : this.lengthDelimited)
/* 766 */         result += CodedOutputStream.computeBytesSize(fieldNumber, value); 
/* 768 */       for (UnknownFieldSet value : this.group)
/* 769 */         result += CodedOutputStream.computeGroupSize(fieldNumber, value); 
/* 771 */       return result;
/*     */     }
/*     */     
/*     */     public void writeAsMessageSetExtensionTo(int fieldNumber, CodedOutputStream output) throws IOException {
/* 782 */       for (ByteString value : this.lengthDelimited)
/* 783 */         output.writeRawMessageSetExtension(fieldNumber, value); 
/*     */     }
/*     */     
/*     */     public int getSerializedSizeAsMessageSetExtension(int fieldNumber) {
/* 792 */       int result = 0;
/* 793 */       for (ByteString value : this.lengthDelimited)
/* 794 */         result += CodedOutputStream.computeRawMessageSetExtensionSize(fieldNumber, value); 
/* 797 */       return result;
/*     */     }
/*     */     
/*     */     public static final class Builder {
/*     */       private UnknownFieldSet.Field result;
/*     */       
/*     */       private static Builder create() {
/* 816 */         Builder builder = new Builder();
/* 817 */         builder.result = new UnknownFieldSet.Field();
/* 818 */         return builder;
/*     */       }
/*     */       
/*     */       public UnknownFieldSet.Field build() {
/* 830 */         if (this.result.varint == null) {
/* 831 */           this.result.varint = Collections.emptyList();
/*     */         } else {
/* 833 */           this.result.varint = Collections.unmodifiableList(this.result.varint);
/*     */         } 
/* 835 */         if (this.result.fixed32 == null) {
/* 836 */           this.result.fixed32 = Collections.emptyList();
/*     */         } else {
/* 838 */           this.result.fixed32 = Collections.unmodifiableList(this.result.fixed32);
/*     */         } 
/* 840 */         if (this.result.fixed64 == null) {
/* 841 */           this.result.fixed64 = Collections.emptyList();
/*     */         } else {
/* 843 */           this.result.fixed64 = Collections.unmodifiableList(this.result.fixed64);
/*     */         } 
/* 845 */         if (this.result.lengthDelimited == null) {
/* 846 */           this.result.lengthDelimited = Collections.emptyList();
/*     */         } else {
/* 848 */           this.result.lengthDelimited = Collections.unmodifiableList(this.result.lengthDelimited);
/*     */         } 
/* 851 */         if (this.result.group == null) {
/* 852 */           this.result.group = Collections.emptyList();
/*     */         } else {
/* 854 */           this.result.group = Collections.unmodifiableList(this.result.group);
/*     */         } 
/* 857 */         UnknownFieldSet.Field returnMe = this.result;
/* 858 */         this.result = null;
/* 859 */         return returnMe;
/*     */       }
/*     */       
/*     */       public Builder clear() {
/* 864 */         this.result = new UnknownFieldSet.Field();
/* 865 */         return this;
/*     */       }
/*     */       
/*     */       public Builder mergeFrom(UnknownFieldSet.Field other) {
/* 874 */         if (!other.varint.isEmpty()) {
/* 875 */           if (this.result.varint == null)
/* 876 */             this.result.varint = new ArrayList(); 
/* 878 */           this.result.varint.addAll(other.varint);
/*     */         } 
/* 880 */         if (!other.fixed32.isEmpty()) {
/* 881 */           if (this.result.fixed32 == null)
/* 882 */             this.result.fixed32 = new ArrayList(); 
/* 884 */           this.result.fixed32.addAll(other.fixed32);
/*     */         } 
/* 886 */         if (!other.fixed64.isEmpty()) {
/* 887 */           if (this.result.fixed64 == null)
/* 888 */             this.result.fixed64 = new ArrayList(); 
/* 890 */           this.result.fixed64.addAll(other.fixed64);
/*     */         } 
/* 892 */         if (!other.lengthDelimited.isEmpty()) {
/* 893 */           if (this.result.lengthDelimited == null)
/* 894 */             this.result.lengthDelimited = new ArrayList(); 
/* 896 */           this.result.lengthDelimited.addAll(other.lengthDelimited);
/*     */         } 
/* 898 */         if (!other.group.isEmpty()) {
/* 899 */           if (this.result.group == null)
/* 900 */             this.result.group = new ArrayList(); 
/* 902 */           this.result.group.addAll(other.group);
/*     */         } 
/* 904 */         return this;
/*     */       }
/*     */       
/*     */       public Builder addVarint(long value) {
/* 909 */         if (this.result.varint == null)
/* 910 */           this.result.varint = new ArrayList(); 
/* 912 */         this.result.varint.add(Long.valueOf(value));
/* 913 */         return this;
/*     */       }
/*     */       
/*     */       public Builder addFixed32(int value) {
/* 918 */         if (this.result.fixed32 == null)
/* 919 */           this.result.fixed32 = new ArrayList(); 
/* 921 */         this.result.fixed32.add(Integer.valueOf(value));
/* 922 */         return this;
/*     */       }
/*     */       
/*     */       public Builder addFixed64(long value) {
/* 927 */         if (this.result.fixed64 == null)
/* 928 */           this.result.fixed64 = new ArrayList(); 
/* 930 */         this.result.fixed64.add(Long.valueOf(value));
/* 931 */         return this;
/*     */       }
/*     */       
/*     */       public Builder addLengthDelimited(ByteString value) {
/* 936 */         if (this.result.lengthDelimited == null)
/* 937 */           this.result.lengthDelimited = new ArrayList(); 
/* 939 */         this.result.lengthDelimited.add(value);
/* 940 */         return this;
/*     */       }
/*     */       
/*     */       public Builder addGroup(UnknownFieldSet value) {
/* 945 */         if (this.result.group == null)
/* 946 */           this.result.group = new ArrayList(); 
/* 948 */         this.result.group.add(value);
/* 949 */         return this;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\UnknownFieldSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */