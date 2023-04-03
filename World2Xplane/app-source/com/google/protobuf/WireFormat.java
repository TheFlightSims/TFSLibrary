/*     */ package com.google.protobuf;
/*     */ 
/*     */ public final class WireFormat {
/*     */   public static final int WIRETYPE_VARINT = 0;
/*     */   
/*     */   public static final int WIRETYPE_FIXED64 = 1;
/*     */   
/*     */   public static final int WIRETYPE_LENGTH_DELIMITED = 2;
/*     */   
/*     */   public static final int WIRETYPE_START_GROUP = 3;
/*     */   
/*     */   public static final int WIRETYPE_END_GROUP = 4;
/*     */   
/*     */   public static final int WIRETYPE_FIXED32 = 5;
/*     */   
/*     */   static final int TAG_TYPE_BITS = 3;
/*     */   
/*     */   static final int TAG_TYPE_MASK = 7;
/*     */   
/*     */   static final int MESSAGE_SET_ITEM = 1;
/*     */   
/*     */   static final int MESSAGE_SET_TYPE_ID = 2;
/*     */   
/*     */   static final int MESSAGE_SET_MESSAGE = 3;
/*     */   
/*     */   static int getTagWireType(int tag) {
/*  60 */     return tag & 0x7;
/*     */   }
/*     */   
/*     */   public static int getTagFieldNumber(int tag) {
/*  65 */     return tag >>> 3;
/*     */   }
/*     */   
/*     */   static int makeTag(int fieldNumber, int wireType) {
/*  70 */     return fieldNumber << 3 | wireType;
/*     */   }
/*     */   
/*     */   public enum JavaType {
/*  78 */     INT((String)Integer.valueOf(0)),
/*  79 */     LONG((String)Long.valueOf(0L)),
/*  80 */     FLOAT((String)Float.valueOf(0.0F)),
/*  81 */     DOUBLE((String)Double.valueOf(0.0D)),
/*  82 */     BOOLEAN((String)Boolean.valueOf(false)),
/*  83 */     STRING(""),
/*  84 */     BYTE_STRING((String)ByteString.EMPTY),
/*  85 */     ENUM(null),
/*  86 */     MESSAGE(null);
/*     */     
/*     */     private final Object defaultDefault;
/*     */     
/*     */     JavaType(Object defaultDefault) {
/*  89 */       this.defaultDefault = defaultDefault;
/*     */     }
/*     */     
/*     */     Object getDefaultDefault() {
/*  97 */       return this.defaultDefault;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum FieldType {
/* 108 */     DOUBLE((String)WireFormat.JavaType.DOUBLE, 1),
/* 109 */     FLOAT((String)WireFormat.JavaType.FLOAT, 5),
/* 110 */     INT64((String)WireFormat.JavaType.LONG, 0),
/* 111 */     UINT64((String)WireFormat.JavaType.LONG, 0),
/* 112 */     INT32((String)WireFormat.JavaType.INT, 0),
/* 113 */     FIXED64((String)WireFormat.JavaType.LONG, 1),
/* 114 */     FIXED32((String)WireFormat.JavaType.INT, 5),
/* 115 */     BOOL((String)WireFormat.JavaType.BOOLEAN, 0),
/* 116 */     STRING((String)WireFormat.JavaType.STRING, 2) {
/*     */       public boolean isPackable() {
/* 117 */         return false;
/*     */       }
/*     */     },
/* 119 */     GROUP((String)WireFormat.JavaType.MESSAGE, 3) {
/*     */       public boolean isPackable() {
/* 120 */         return false;
/*     */       }
/*     */     },
/* 122 */     MESSAGE((String)WireFormat.JavaType.MESSAGE, 2) {
/*     */       public boolean isPackable() {
/* 123 */         return false;
/*     */       }
/*     */     },
/* 125 */     BYTES((String)WireFormat.JavaType.BYTE_STRING, 2) {
/*     */       public boolean isPackable() {
/* 126 */         return false;
/*     */       }
/*     */     },
/* 128 */     UINT32((String)WireFormat.JavaType.INT, 0),
/* 129 */     ENUM((String)WireFormat.JavaType.ENUM, 0),
/* 130 */     SFIXED32((String)WireFormat.JavaType.INT, 5),
/* 131 */     SFIXED64((String)WireFormat.JavaType.LONG, 1),
/* 132 */     SINT32((String)WireFormat.JavaType.INT, 0),
/* 133 */     SINT64((String)WireFormat.JavaType.LONG, 0);
/*     */     
/*     */     private final WireFormat.JavaType javaType;
/*     */     
/*     */     private final int wireType;
/*     */     
/*     */     FieldType(WireFormat.JavaType javaType, int wireType) {
/* 136 */       this.javaType = javaType;
/* 137 */       this.wireType = wireType;
/*     */     }
/*     */     
/*     */     public WireFormat.JavaType getJavaType() {
/* 143 */       return this.javaType;
/*     */     }
/*     */     
/*     */     public int getWireType() {
/* 144 */       return this.wireType;
/*     */     }
/*     */     
/*     */     public boolean isPackable() {
/* 146 */       return true;
/*     */     }
/*     */   }
/*     */   
/* 155 */   static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
/*     */   
/* 157 */   static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
/*     */   
/* 159 */   static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
/*     */   
/* 161 */   static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\protobuf\WireFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */