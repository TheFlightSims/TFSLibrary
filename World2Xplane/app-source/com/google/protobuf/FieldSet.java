/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class FieldSet<FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> {
/*     */   private final SmallSortedMap<FieldDescriptorType, Object> fields;
/*     */   
/*     */   private boolean isImmutable;
/*     */   
/*     */   private FieldSet() {
/*  74 */     this.fields = SmallSortedMap.newFieldMap(16);
/*     */   }
/*     */   
/*     */   private FieldSet(boolean dummy) {
/*  82 */     this.fields = SmallSortedMap.newFieldMap(0);
/*  83 */     makeImmutable();
/*     */   }
/*     */   
/*     */   public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet() {
/*  89 */     return new FieldSet<T>();
/*     */   }
/*     */   
/*     */   public static <T extends FieldDescriptorLite<T>> FieldSet<T> emptySet() {
/*  96 */     return DEFAULT_INSTANCE;
/*     */   }
/*     */   
/*  99 */   private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);
/*     */   
/*     */   public void makeImmutable() {
/* 104 */     if (this.isImmutable)
/*     */       return; 
/* 107 */     this.fields.makeImmutable();
/* 108 */     this.isImmutable = true;
/*     */   }
/*     */   
/*     */   public boolean isImmutable() {
/* 118 */     return this.isImmutable;
/*     */   }
/*     */   
/*     */   public FieldSet<FieldDescriptorType> clone() {
/* 131 */     FieldSet<FieldDescriptorType> clone = newFieldSet();
/* 132 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
/* 133 */       Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(i);
/* 134 */       FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 135 */       clone.setField((FieldDescriptorType)fieldDescriptorLite, entry.getValue());
/*     */     } 
/* 138 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries()) {
/* 139 */       FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 140 */       clone.setField((FieldDescriptorType)fieldDescriptorLite, entry.getValue());
/*     */     } 
/* 142 */     return clone;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 149 */     this.fields.clear();
/*     */   }
/*     */   
/*     */   public Map<FieldDescriptorType, Object> getAllFields() {
/* 156 */     return this.fields.isImmutable() ? this.fields : Collections.<FieldDescriptorType, Object>unmodifiableMap(this.fields);
/*     */   }
/*     */   
/*     */   public Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
/* 165 */     return this.fields.entrySet().iterator();
/*     */   }
/*     */   
/*     */   public boolean hasField(FieldDescriptorType descriptor) {
/* 173 */     if (descriptor.isRepeated())
/* 174 */       throw new IllegalArgumentException("hasField() can only be called on non-repeated fields."); 
/* 178 */     return (this.fields.get(descriptor) != null);
/*     */   }
/*     */   
/*     */   public Object getField(FieldDescriptorType descriptor) {
/* 188 */     return this.fields.get(descriptor);
/*     */   }
/*     */   
/*     */   public void setField(FieldDescriptorType descriptor, Object value) {
/* 198 */     if (descriptor.isRepeated()) {
/* 199 */       if (!(value instanceof List))
/* 200 */         throw new IllegalArgumentException("Wrong object type used with protocol message reflection."); 
/* 206 */       List newList = new ArrayList();
/* 207 */       newList.addAll((List)value);
/* 208 */       for (Object element : newList)
/* 209 */         verifyType(descriptor.getLiteType(), element); 
/* 211 */       value = newList;
/*     */     } else {
/* 213 */       verifyType(descriptor.getLiteType(), value);
/*     */     } 
/* 216 */     this.fields.put(descriptor, value);
/*     */   }
/*     */   
/*     */   public void clearField(FieldDescriptorType descriptor) {
/* 224 */     this.fields.remove(descriptor);
/*     */   }
/*     */   
/*     */   public int getRepeatedFieldCount(FieldDescriptorType descriptor) {
/* 232 */     if (!descriptor.isRepeated())
/* 233 */       throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
/* 237 */     Object value = this.fields.get(descriptor);
/* 238 */     if (value == null)
/* 239 */       return 0; 
/* 241 */     return ((List)value).size();
/*     */   }
/*     */   
/*     */   public Object getRepeatedField(FieldDescriptorType descriptor, int index) {
/* 251 */     if (!descriptor.isRepeated())
/* 252 */       throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
/* 256 */     Object value = this.fields.get(descriptor);
/* 258 */     if (value == null)
/* 259 */       throw new IndexOutOfBoundsException(); 
/* 261 */     return ((List)value).get(index);
/*     */   }
/*     */   
/*     */   public void setRepeatedField(FieldDescriptorType descriptor, int index, Object value) {
/* 273 */     if (!descriptor.isRepeated())
/* 274 */       throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields."); 
/* 278 */     Object list = this.fields.get(descriptor);
/* 279 */     if (list == null)
/* 280 */       throw new IndexOutOfBoundsException(); 
/* 283 */     verifyType(descriptor.getLiteType(), value);
/* 284 */     ((List<Object>)list).set(index, value);
/*     */   }
/*     */   
/*     */   public void addRepeatedField(FieldDescriptorType descriptor, Object value) {
/*     */     List<Object> list;
/* 294 */     if (!descriptor.isRepeated())
/* 295 */       throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields."); 
/* 299 */     verifyType(descriptor.getLiteType(), value);
/* 301 */     Object existingValue = this.fields.get(descriptor);
/* 303 */     if (existingValue == null) {
/* 304 */       list = new ArrayList();
/* 305 */       this.fields.put(descriptor, list);
/*     */     } else {
/* 307 */       list = (List)existingValue;
/*     */     } 
/* 310 */     list.add(value);
/*     */   }
/*     */   
/*     */   private static void verifyType(WireFormat.FieldType type, Object value) {
/* 322 */     if (value == null)
/* 323 */       throw new NullPointerException(); 
/* 326 */     boolean isValid = false;
/* 327 */     switch (type.getJavaType()) {
/*     */       case DOUBLE:
/* 328 */         isValid = value instanceof Integer;
/*     */         break;
/*     */       case FLOAT:
/* 329 */         isValid = value instanceof Long;
/*     */         break;
/*     */       case INT64:
/* 330 */         isValid = value instanceof Float;
/*     */         break;
/*     */       case UINT64:
/* 331 */         isValid = value instanceof Double;
/*     */         break;
/*     */       case INT32:
/* 332 */         isValid = value instanceof Boolean;
/*     */         break;
/*     */       case FIXED64:
/* 333 */         isValid = value instanceof String;
/*     */         break;
/*     */       case FIXED32:
/* 334 */         isValid = value instanceof ByteString;
/*     */         break;
/*     */       case BOOL:
/* 337 */         isValid = value instanceof Internal.EnumLite;
/*     */         break;
/*     */       case STRING:
/* 341 */         isValid = value instanceof MessageLite;
/*     */         break;
/*     */     } 
/* 345 */     if (!isValid)
/* 353 */       throw new IllegalArgumentException("Wrong object type used with protocol message reflection."); 
/*     */   }
/*     */   
/*     */   public boolean isInitialized() {
/* 368 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
/* 369 */       if (!isInitialized(this.fields.getArrayEntryAt(i)))
/* 370 */         return false; 
/*     */     } 
/* 374 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries()) {
/* 375 */       if (!isInitialized(entry))
/* 376 */         return false; 
/*     */     } 
/* 379 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isInitialized(Map.Entry<FieldDescriptorType, Object> entry) {
/* 385 */     FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 386 */     if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
/* 387 */       if (fieldDescriptorLite.isRepeated()) {
/* 389 */         for (MessageLite element : entry.getValue()) {
/* 390 */           if (!element.isInitialized())
/* 391 */             return false; 
/*     */         } 
/* 395 */       } else if (!((MessageLite)entry.getValue()).isInitialized()) {
/* 396 */         return false;
/*     */       }  
/* 400 */     return true;
/*     */   }
/*     */   
/*     */   static int getWireFormatForFieldType(WireFormat.FieldType type, boolean isPacked) {
/* 411 */     if (isPacked)
/* 412 */       return 2; 
/* 414 */     return type.getWireType();
/*     */   }
/*     */   
/*     */   public void mergeFrom(FieldSet<FieldDescriptorType> other) {
/* 422 */     for (int i = 0; i < other.fields.getNumArrayEntries(); i++)
/* 423 */       mergeFromField(other.fields.getArrayEntryAt(i)); 
/* 426 */     for (Map.Entry<FieldDescriptorType, Object> entry : other.fields.getOverflowEntries())
/* 427 */       mergeFromField(entry); 
/*     */   }
/*     */   
/*     */   private void mergeFromField(Map.Entry<FieldDescriptorType, Object> entry) {
/* 434 */     FieldDescriptorLite fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 435 */     Object otherValue = entry.getValue();
/* 437 */     if (fieldDescriptorLite.isRepeated()) {
/* 438 */       Object value = this.fields.get(fieldDescriptorLite);
/* 439 */       if (value == null) {
/* 443 */         this.fields.put((FieldDescriptorType)fieldDescriptorLite, new ArrayList((List)otherValue));
/*     */       } else {
/* 446 */         ((List)value).addAll((List)otherValue);
/*     */       } 
/* 448 */     } else if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
/* 449 */       Object value = this.fields.get(fieldDescriptorLite);
/* 450 */       if (value == null) {
/* 451 */         this.fields.put((FieldDescriptorType)fieldDescriptorLite, otherValue);
/*     */       } else {
/* 454 */         this.fields.put((FieldDescriptorType)fieldDescriptorLite, fieldDescriptorLite.internalMergeFrom(((MessageLite)value).toBuilder(), (MessageLite)otherValue).build());
/*     */       } 
/*     */     } else {
/* 462 */       this.fields.put((FieldDescriptorType)fieldDescriptorLite, otherValue);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object readPrimitiveField(CodedInputStream input, WireFormat.FieldType type) throws IOException {
/* 483 */     switch (type) {
/*     */       case DOUBLE:
/* 484 */         return Double.valueOf(input.readDouble());
/*     */       case FLOAT:
/* 485 */         return Float.valueOf(input.readFloat());
/*     */       case INT64:
/* 486 */         return Long.valueOf(input.readInt64());
/*     */       case UINT64:
/* 487 */         return Long.valueOf(input.readUInt64());
/*     */       case INT32:
/* 488 */         return Integer.valueOf(input.readInt32());
/*     */       case FIXED64:
/* 489 */         return Long.valueOf(input.readFixed64());
/*     */       case FIXED32:
/* 490 */         return Integer.valueOf(input.readFixed32());
/*     */       case BOOL:
/* 491 */         return Boolean.valueOf(input.readBool());
/*     */       case STRING:
/* 492 */         return input.readString();
/*     */       case BYTES:
/* 493 */         return input.readBytes();
/*     */       case UINT32:
/* 494 */         return Integer.valueOf(input.readUInt32());
/*     */       case SFIXED32:
/* 495 */         return Integer.valueOf(input.readSFixed32());
/*     */       case SFIXED64:
/* 496 */         return Long.valueOf(input.readSFixed64());
/*     */       case SINT32:
/* 497 */         return Integer.valueOf(input.readSInt32());
/*     */       case SINT64:
/* 498 */         return Long.valueOf(input.readSInt64());
/*     */       case GROUP:
/* 501 */         throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
/*     */       case MESSAGE:
/* 504 */         throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
/*     */       case ENUM:
/* 509 */         throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
/*     */     } 
/* 513 */     throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
/*     */   }
/*     */   
/*     */   public void writeTo(CodedOutputStream output) throws IOException {
/* 520 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
/* 521 */       Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(i);
/* 523 */       writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), output);
/*     */     } 
/* 526 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
/* 527 */       writeField((FieldDescriptorLite)entry.getKey(), entry.getValue(), output); 
/*     */   }
/*     */   
/*     */   public void writeMessageSetTo(CodedOutputStream output) throws IOException {
/* 536 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
/* 537 */       writeMessageSetTo(this.fields.getArrayEntryAt(i), output); 
/* 540 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
/* 541 */       writeMessageSetTo(entry, output); 
/*     */   }
/*     */   
/*     */   private void writeMessageSetTo(Map.Entry<FieldDescriptorType, Object> entry, CodedOutputStream output) throws IOException {
/* 548 */     FieldDescriptorLite<?> fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 549 */     if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptorLite.isRepeated() && !fieldDescriptorLite.isPacked()) {
/* 551 */       output.writeMessageSetExtension(((FieldDescriptorLite)entry.getKey()).getNumber(), (MessageLite)entry.getValue());
/*     */     } else {
/* 554 */       writeField(fieldDescriptorLite, entry.getValue(), output);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeElement(CodedOutputStream output, WireFormat.FieldType type, int number, Object value) throws IOException {
/* 575 */     if (type == WireFormat.FieldType.GROUP) {
/* 576 */       output.writeGroup(number, (MessageLite)value);
/*     */     } else {
/* 578 */       output.writeTag(number, getWireFormatForFieldType(type, false));
/* 579 */       writeElementNoTag(output, type, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeElementNoTag(CodedOutputStream output, WireFormat.FieldType type, Object value) throws IOException {
/* 597 */     switch (type) {
/*     */       case DOUBLE:
/* 598 */         output.writeDoubleNoTag(((Double)value).doubleValue());
/*     */         break;
/*     */       case FLOAT:
/* 599 */         output.writeFloatNoTag(((Float)value).floatValue());
/*     */         break;
/*     */       case INT64:
/* 600 */         output.writeInt64NoTag(((Long)value).longValue());
/*     */         break;
/*     */       case UINT64:
/* 601 */         output.writeUInt64NoTag(((Long)value).longValue());
/*     */         break;
/*     */       case INT32:
/* 602 */         output.writeInt32NoTag(((Integer)value).intValue());
/*     */         break;
/*     */       case FIXED64:
/* 603 */         output.writeFixed64NoTag(((Long)value).longValue());
/*     */         break;
/*     */       case FIXED32:
/* 604 */         output.writeFixed32NoTag(((Integer)value).intValue());
/*     */         break;
/*     */       case BOOL:
/* 605 */         output.writeBoolNoTag(((Boolean)value).booleanValue());
/*     */         break;
/*     */       case STRING:
/* 606 */         output.writeStringNoTag((String)value);
/*     */         break;
/*     */       case GROUP:
/* 607 */         output.writeGroupNoTag((MessageLite)value);
/*     */         break;
/*     */       case MESSAGE:
/* 608 */         output.writeMessageNoTag((MessageLite)value);
/*     */         break;
/*     */       case BYTES:
/* 609 */         output.writeBytesNoTag((ByteString)value);
/*     */         break;
/*     */       case UINT32:
/* 610 */         output.writeUInt32NoTag(((Integer)value).intValue());
/*     */         break;
/*     */       case SFIXED32:
/* 611 */         output.writeSFixed32NoTag(((Integer)value).intValue());
/*     */         break;
/*     */       case SFIXED64:
/* 612 */         output.writeSFixed64NoTag(((Long)value).longValue());
/*     */         break;
/*     */       case SINT32:
/* 613 */         output.writeSInt32NoTag(((Integer)value).intValue());
/*     */         break;
/*     */       case SINT64:
/* 614 */         output.writeSInt64NoTag(((Long)value).longValue());
/*     */         break;
/*     */       case ENUM:
/* 617 */         output.writeEnumNoTag(((Internal.EnumLite)value).getNumber());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeField(FieldDescriptorLite<?> descriptor, Object value, CodedOutputStream output) throws IOException {
/* 627 */     WireFormat.FieldType type = descriptor.getLiteType();
/* 628 */     int number = descriptor.getNumber();
/* 629 */     if (descriptor.isRepeated()) {
/* 630 */       List<?> valueList = (List)value;
/* 631 */       if (descriptor.isPacked()) {
/* 632 */         output.writeTag(number, 2);
/* 634 */         int dataSize = 0;
/* 635 */         for (Object element : valueList)
/* 636 */           dataSize += computeElementSizeNoTag(type, element); 
/* 638 */         output.writeRawVarint32(dataSize);
/* 640 */         for (Object element : valueList)
/* 641 */           writeElementNoTag(output, type, element); 
/*     */       } else {
/* 644 */         for (Object element : valueList)
/* 645 */           writeElement(output, type, number, element); 
/*     */       } 
/*     */     } else {
/* 649 */       writeElement(output, type, number, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSerializedSize() {
/* 658 */     int size = 0;
/* 659 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
/* 660 */       Map.Entry<FieldDescriptorType, Object> entry = this.fields.getArrayEntryAt(i);
/* 662 */       size += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue());
/*     */     } 
/* 665 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
/* 666 */       size += computeFieldSize((FieldDescriptorLite)entry.getKey(), entry.getValue()); 
/* 668 */     return size;
/*     */   }
/*     */   
/*     */   public int getMessageSetSerializedSize() {
/* 675 */     int size = 0;
/* 676 */     for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
/* 677 */       size += getMessageSetSerializedSize(this.fields.getArrayEntryAt(i)); 
/* 680 */     for (Map.Entry<FieldDescriptorType, Object> entry : this.fields.getOverflowEntries())
/* 681 */       size += getMessageSetSerializedSize(entry); 
/* 683 */     return size;
/*     */   }
/*     */   
/*     */   private int getMessageSetSerializedSize(Map.Entry<FieldDescriptorType, Object> entry) {
/* 688 */     FieldDescriptorLite<?> fieldDescriptorLite = (FieldDescriptorLite)entry.getKey();
/* 689 */     if (fieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !fieldDescriptorLite.isRepeated() && !fieldDescriptorLite.isPacked())
/* 691 */       return CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)entry.getKey()).getNumber(), (MessageLite)entry.getValue()); 
/* 694 */     return computeFieldSize(fieldDescriptorLite, entry.getValue());
/*     */   }
/*     */   
/*     */   private static int computeElementSize(WireFormat.FieldType type, int number, Object value) {
/* 712 */     int tagSize = CodedOutputStream.computeTagSize(number);
/* 713 */     if (type == WireFormat.FieldType.GROUP)
/* 714 */       tagSize *= 2; 
/* 716 */     return tagSize + computeElementSizeNoTag(type, value);
/*     */   }
/*     */   
/*     */   private static int computeElementSizeNoTag(WireFormat.FieldType type, Object value) {
/* 731 */     switch (type) {
/*     */       case DOUBLE:
/* 734 */         return CodedOutputStream.computeDoubleSizeNoTag(((Double)value).doubleValue());
/*     */       case FLOAT:
/* 735 */         return CodedOutputStream.computeFloatSizeNoTag(((Float)value).floatValue());
/*     */       case INT64:
/* 736 */         return CodedOutputStream.computeInt64SizeNoTag(((Long)value).longValue());
/*     */       case UINT64:
/* 737 */         return CodedOutputStream.computeUInt64SizeNoTag(((Long)value).longValue());
/*     */       case INT32:
/* 738 */         return CodedOutputStream.computeInt32SizeNoTag(((Integer)value).intValue());
/*     */       case FIXED64:
/* 739 */         return CodedOutputStream.computeFixed64SizeNoTag(((Long)value).longValue());
/*     */       case FIXED32:
/* 740 */         return CodedOutputStream.computeFixed32SizeNoTag(((Integer)value).intValue());
/*     */       case BOOL:
/* 741 */         return CodedOutputStream.computeBoolSizeNoTag(((Boolean)value).booleanValue());
/*     */       case STRING:
/* 742 */         return CodedOutputStream.computeStringSizeNoTag((String)value);
/*     */       case GROUP:
/* 743 */         return CodedOutputStream.computeGroupSizeNoTag((MessageLite)value);
/*     */       case MESSAGE:
/* 744 */         return CodedOutputStream.computeMessageSizeNoTag((MessageLite)value);
/*     */       case BYTES:
/* 745 */         return CodedOutputStream.computeBytesSizeNoTag((ByteString)value);
/*     */       case UINT32:
/* 746 */         return CodedOutputStream.computeUInt32SizeNoTag(((Integer)value).intValue());
/*     */       case SFIXED32:
/* 747 */         return CodedOutputStream.computeSFixed32SizeNoTag(((Integer)value).intValue());
/*     */       case SFIXED64:
/* 748 */         return CodedOutputStream.computeSFixed64SizeNoTag(((Long)value).longValue());
/*     */       case SINT32:
/* 749 */         return CodedOutputStream.computeSInt32SizeNoTag(((Integer)value).intValue());
/*     */       case SINT64:
/* 750 */         return CodedOutputStream.computeSInt64SizeNoTag(((Long)value).longValue());
/*     */       case ENUM:
/* 753 */         return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)value).getNumber());
/*     */     } 
/* 757 */     throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
/*     */   }
/*     */   
/*     */   public static int computeFieldSize(FieldDescriptorLite<?> descriptor, Object value) {
/* 766 */     WireFormat.FieldType type = descriptor.getLiteType();
/* 767 */     int number = descriptor.getNumber();
/* 768 */     if (descriptor.isRepeated()) {
/* 769 */       if (descriptor.isPacked()) {
/* 770 */         int dataSize = 0;
/* 771 */         for (Object element : value)
/* 772 */           dataSize += computeElementSizeNoTag(type, element); 
/* 774 */         return dataSize + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeRawVarint32Size(dataSize);
/*     */       } 
/* 778 */       int size = 0;
/* 779 */       for (Object element : value)
/* 780 */         size += computeElementSize(type, number, element); 
/* 782 */       return size;
/*     */     } 
/* 785 */     return computeElementSize(type, number, value);
/*     */   }
/*     */   
/*     */   public static interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T> {
/*     */     int getNumber();
/*     */     
/*     */     WireFormat.FieldType getLiteType();
/*     */     
/*     */     WireFormat.JavaType getLiteJavaType();
/*     */     
/*     */     boolean isRepeated();
/*     */     
/*     */     boolean isPacked();
/*     */     
/*     */     Internal.EnumLiteMap<?> getEnumType();
/*     */     
/*     */     MessageLite.Builder internalMergeFrom(MessageLite.Builder param1Builder, MessageLite param1MessageLite);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\FieldSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */