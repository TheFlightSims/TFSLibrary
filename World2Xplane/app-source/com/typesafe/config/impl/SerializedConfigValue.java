/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigList;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ class SerializedConfigValue extends AbstractConfigValue implements Externalizable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   private ConfigValue value;
/*     */   
/*     */   private boolean wasConfig;
/*     */   
/*     */   enum SerializedField {
/*  47 */     UNKNOWN, END_MARKER, ROOT_VALUE, ROOT_WAS_CONFIG, VALUE_DATA, VALUE_ORIGIN, ORIGIN_DESCRIPTION, ORIGIN_LINE_NUMBER, ORIGIN_END_LINE_NUMBER, ORIGIN_TYPE, ORIGIN_URL, ORIGIN_COMMENTS, ORIGIN_NULL_URL, ORIGIN_NULL_COMMENTS;
/*     */     
/*     */     static SerializedField forInt(int b) {
/*  71 */       if (b < (values()).length)
/*  72 */         return values()[b]; 
/*  74 */       return UNKNOWN;
/*     */     }
/*     */   }
/*     */   
/*     */   private enum SerializedValueType {
/*  80 */     NULL((String)ConfigValueType.NULL),
/*  81 */     BOOLEAN((String)ConfigValueType.BOOLEAN),
/*  82 */     INT((String)ConfigValueType.NUMBER),
/*  83 */     LONG((String)ConfigValueType.NUMBER),
/*  84 */     DOUBLE((String)ConfigValueType.NUMBER),
/*  85 */     STRING((String)ConfigValueType.STRING),
/*  86 */     LIST((String)ConfigValueType.LIST),
/*  87 */     OBJECT((String)ConfigValueType.OBJECT);
/*     */     
/*     */     ConfigValueType configType;
/*     */     
/*     */     SerializedValueType(ConfigValueType configType) {
/*  92 */       this.configType = configType;
/*     */     }
/*     */     
/*     */     static SerializedValueType forInt(int b) {
/*  96 */       if (b < (values()).length)
/*  97 */         return values()[b]; 
/*  99 */       return null;
/*     */     }
/*     */     
/*     */     static SerializedValueType forValue(ConfigValue value) {
/* 103 */       ConfigValueType t = value.valueType();
/* 104 */       if (t == ConfigValueType.NUMBER) {
/* 105 */         if (value instanceof ConfigInt)
/* 106 */           return INT; 
/* 107 */         if (value instanceof ConfigLong)
/* 108 */           return LONG; 
/* 109 */         if (value instanceof ConfigDouble)
/* 110 */           return DOUBLE; 
/*     */       } else {
/* 112 */         for (SerializedValueType st : values()) {
/* 113 */           if (st.configType == t)
/* 114 */             return st; 
/*     */         } 
/*     */       } 
/* 118 */       throw new ConfigException.BugOrBroken("don't know how to serialize " + value);
/*     */     }
/*     */   }
/*     */   
/*     */   public SerializedConfigValue() {
/* 127 */     super(null);
/*     */   }
/*     */   
/*     */   SerializedConfigValue(ConfigValue value) {
/* 131 */     this();
/* 132 */     this.value = value;
/* 133 */     this.wasConfig = false;
/*     */   }
/*     */   
/*     */   SerializedConfigValue(Config conf) {
/* 137 */     this((ConfigValue)conf.root());
/* 138 */     this.wasConfig = true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 144 */     if (this.wasConfig)
/* 145 */       return ((ConfigObject)this.value).toConfig(); 
/* 147 */     return this.value;
/*     */   }
/*     */   
/*     */   private static class FieldOut {
/*     */     final SerializedConfigValue.SerializedField code;
/*     */     
/*     */     final ByteArrayOutputStream bytes;
/*     */     
/*     */     final DataOutput data;
/*     */     
/*     */     FieldOut(SerializedConfigValue.SerializedField code) {
/* 156 */       this.code = code;
/* 157 */       this.bytes = new ByteArrayOutputStream();
/* 158 */       this.data = new DataOutputStream(this.bytes);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void writeOriginField(DataOutput out, SerializedField code, Object v) throws IOException {
/*     */     List<String> list;
/*     */     int size;
/* 166 */     switch (code) {
/*     */       case BOOLEAN:
/* 168 */         out.writeUTF((String)v);
/*     */       case NULL:
/* 171 */         out.writeInt(((Integer)v).intValue());
/*     */       case INT:
/* 174 */         out.writeInt(((Integer)v).intValue());
/*     */       case LONG:
/* 177 */         out.writeByte(((Integer)v).intValue());
/*     */       case DOUBLE:
/* 180 */         out.writeUTF((String)v);
/*     */       case STRING:
/* 184 */         list = (List<String>)v;
/* 185 */         size = list.size();
/* 186 */         out.writeInt(size);
/* 187 */         for (String s : list)
/* 188 */           out.writeUTF(s); 
/*     */       case LIST:
/*     */       case OBJECT:
/*     */         return;
/*     */     } 
/* 196 */     throw new IOException("Unhandled field from origin: " + code);
/*     */   }
/*     */   
/*     */   static void writeOrigin(DataOutput out, SimpleConfigOrigin origin, SimpleConfigOrigin baseOrigin) throws IOException {
/*     */     Map<SerializedField, Object> m;
/* 205 */     if (origin != null) {
/* 206 */       m = origin.toFieldsDelta(baseOrigin);
/*     */     } else {
/* 208 */       m = Collections.emptyMap();
/*     */     } 
/* 209 */     for (Map.Entry<SerializedField, Object> e : m.entrySet()) {
/* 210 */       FieldOut field = new FieldOut(e.getKey());
/* 211 */       Object v = e.getValue();
/* 212 */       writeOriginField(field.data, field.code, v);
/* 213 */       writeField(out, field);
/*     */     } 
/* 215 */     writeEndMarker(out);
/*     */   }
/*     */   
/*     */   static SimpleConfigOrigin readOrigin(DataInput in, SimpleConfigOrigin baseOrigin) throws IOException {
/* 221 */     Map<SerializedField, Object> m = new EnumMap<SerializedField, Object>(SerializedField.class);
/*     */     while (true) {
/*     */       int size;
/*     */       List<String> list;
/*     */       int i;
/* 223 */       Object<String> v = null;
/* 224 */       SerializedField field = readCode(in);
/* 225 */       switch (field) {
/*     */         case null:
/* 227 */           return SimpleConfigOrigin.fromBase(baseOrigin, m);
/*     */         case BOOLEAN:
/* 229 */           in.readInt();
/* 230 */           v = (Object<String>)in.readUTF();
/*     */           break;
/*     */         case NULL:
/* 233 */           in.readInt();
/* 234 */           v = (Object<String>)Integer.valueOf(in.readInt());
/*     */           break;
/*     */         case INT:
/* 237 */           in.readInt();
/* 238 */           v = (Object<String>)Integer.valueOf(in.readInt());
/*     */           break;
/*     */         case LONG:
/* 241 */           in.readInt();
/* 242 */           v = (Object<String>)Integer.valueOf(in.readUnsignedByte());
/*     */           break;
/*     */         case DOUBLE:
/* 245 */           in.readInt();
/* 246 */           v = (Object<String>)in.readUTF();
/*     */           break;
/*     */         case STRING:
/* 249 */           in.readInt();
/* 250 */           size = in.readInt();
/* 251 */           list = new ArrayList<String>(size);
/* 252 */           for (i = 0; i < size; i++)
/* 253 */             list.add(in.readUTF()); 
/* 255 */           v = (Object<String>)list;
/*     */           break;
/*     */         case LIST:
/*     */         case OBJECT:
/* 260 */           in.readInt();
/* 261 */           v = (Object<String>)"";
/*     */           break;
/*     */         case null:
/*     */         case null:
/*     */         case null:
/*     */         case null:
/* 267 */           throw new IOException("Not expecting this field here: " + field);
/*     */         case null:
/* 270 */           skipField(in);
/*     */           break;
/*     */       } 
/* 273 */       if (v != null)
/* 274 */         m.put(field, v); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeValueData(DataOutput out, ConfigValue value) throws IOException {
/*     */     ConfigList list;
/*     */     ConfigObject obj;
/* 279 */     SerializedValueType st = SerializedValueType.forValue(value);
/* 280 */     out.writeByte(st.ordinal());
/* 281 */     switch (st) {
/*     */       case BOOLEAN:
/* 283 */         out.writeBoolean(((ConfigBoolean)value).unwrapped().booleanValue());
/*     */         break;
/*     */       case INT:
/* 289 */         out.writeInt(((ConfigInt)value).unwrapped().intValue());
/* 290 */         out.writeUTF(((ConfigNumber)value).transformToString());
/*     */         break;
/*     */       case LONG:
/* 293 */         out.writeLong(((ConfigLong)value).unwrapped().longValue());
/* 294 */         out.writeUTF(((ConfigNumber)value).transformToString());
/*     */         break;
/*     */       case DOUBLE:
/* 297 */         out.writeDouble(((ConfigDouble)value).unwrapped().doubleValue());
/* 298 */         out.writeUTF(((ConfigNumber)value).transformToString());
/*     */         break;
/*     */       case STRING:
/* 301 */         out.writeUTF(((ConfigString)value).unwrapped());
/*     */         break;
/*     */       case LIST:
/* 304 */         list = (ConfigList)value;
/* 305 */         out.writeInt(list.size());
/* 306 */         for (ConfigValue v : list)
/* 307 */           writeValue(out, v, (SimpleConfigOrigin)list.origin()); 
/*     */         break;
/*     */       case OBJECT:
/* 311 */         obj = (ConfigObject)value;
/* 312 */         out.writeInt(obj.size());
/* 313 */         for (Map.Entry<String, ConfigValue> e : (Iterable<Map.Entry<String, ConfigValue>>)obj.entrySet()) {
/* 314 */           out.writeUTF(e.getKey());
/* 315 */           writeValue(out, e.getValue(), (SimpleConfigOrigin)obj.origin());
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue readValueData(DataInput in, SimpleConfigOrigin origin) throws IOException {
/*     */     int vi;
/*     */     String si;
/*     */     long vl;
/*     */     String sl;
/*     */     double vd;
/*     */     String sd;
/*     */     int listSize;
/*     */     List<AbstractConfigValue> list;
/*     */     int i, mapSize;
/*     */     Map<String, AbstractConfigValue> map;
/* 323 */     int j, stb = in.readUnsignedByte();
/* 324 */     SerializedValueType st = SerializedValueType.forInt(stb);
/* 325 */     if (st == null)
/* 326 */       throw new IOException("Unknown serialized value type: " + stb); 
/* 327 */     switch (st) {
/*     */       case BOOLEAN:
/* 329 */         return new ConfigBoolean(origin, in.readBoolean());
/*     */       case NULL:
/* 331 */         return new ConfigNull(origin);
/*     */       case INT:
/* 333 */         vi = in.readInt();
/* 334 */         si = in.readUTF();
/* 335 */         return new ConfigInt(origin, vi, si);
/*     */       case LONG:
/* 337 */         vl = in.readLong();
/* 338 */         sl = in.readUTF();
/* 339 */         return new ConfigLong(origin, vl, sl);
/*     */       case DOUBLE:
/* 341 */         vd = in.readDouble();
/* 342 */         sd = in.readUTF();
/* 343 */         return new ConfigDouble(origin, vd, sd);
/*     */       case STRING:
/* 345 */         return new ConfigString(origin, in.readUTF());
/*     */       case LIST:
/* 347 */         listSize = in.readInt();
/* 348 */         list = new ArrayList<AbstractConfigValue>(listSize);
/* 349 */         for (i = 0; i < listSize; i++) {
/* 350 */           AbstractConfigValue v = readValue(in, origin);
/* 351 */           list.add(v);
/*     */         } 
/* 353 */         return new SimpleConfigList(origin, list);
/*     */       case OBJECT:
/* 355 */         mapSize = in.readInt();
/* 356 */         map = new HashMap<String, AbstractConfigValue>(mapSize);
/* 357 */         for (j = 0; j < mapSize; j++) {
/* 358 */           String key = in.readUTF();
/* 359 */           AbstractConfigValue v = readValue(in, origin);
/* 360 */           map.put(key, v);
/*     */         } 
/* 362 */         return new SimpleConfigObject(origin, map);
/*     */     } 
/* 364 */     throw new IOException("Unhandled serialized value type: " + st);
/*     */   }
/*     */   
/*     */   private static void writeValue(DataOutput out, ConfigValue value, SimpleConfigOrigin baseOrigin) throws IOException {
/* 369 */     FieldOut origin = new FieldOut(SerializedField.VALUE_ORIGIN);
/* 370 */     writeOrigin(origin.data, (SimpleConfigOrigin)value.origin(), baseOrigin);
/* 372 */     writeField(out, origin);
/* 374 */     FieldOut data = new FieldOut(SerializedField.VALUE_DATA);
/* 375 */     writeValueData(data.data, value);
/* 376 */     writeField(out, data);
/* 378 */     writeEndMarker(out);
/*     */   }
/*     */   
/*     */   private static AbstractConfigValue readValue(DataInput in, SimpleConfigOrigin baseOrigin) throws IOException {
/* 383 */     AbstractConfigValue value = null;
/* 384 */     SimpleConfigOrigin origin = null;
/*     */     while (true) {
/* 386 */       SerializedField code = readCode(in);
/* 387 */       if (code == SerializedField.END_MARKER) {
/* 388 */         if (value == null)
/* 389 */           throw new IOException("No value data found in serialization of value"); 
/* 390 */         return value;
/*     */       } 
/* 391 */       if (code == SerializedField.VALUE_DATA) {
/* 392 */         if (origin == null)
/* 393 */           throw new IOException("Origin must be stored before value data"); 
/* 394 */         in.readInt();
/* 395 */         value = readValueData(in, origin);
/*     */         continue;
/*     */       } 
/* 396 */       if (code == SerializedField.VALUE_ORIGIN) {
/* 397 */         in.readInt();
/* 398 */         origin = readOrigin(in, baseOrigin);
/*     */         continue;
/*     */       } 
/* 401 */       skipField(in);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeField(DataOutput out, FieldOut field) throws IOException {
/* 407 */     byte[] bytes = field.bytes.toByteArray();
/* 408 */     out.writeByte(field.code.ordinal());
/* 409 */     out.writeInt(bytes.length);
/* 410 */     out.write(bytes);
/*     */   }
/*     */   
/*     */   private static void writeEndMarker(DataOutput out) throws IOException {
/* 414 */     out.writeByte(SerializedField.END_MARKER.ordinal());
/*     */   }
/*     */   
/*     */   private static SerializedField readCode(DataInput in) throws IOException {
/* 418 */     int c = in.readUnsignedByte();
/* 419 */     if (c == SerializedField.UNKNOWN.ordinal())
/* 420 */       throw new IOException("field code " + c + " is not supposed to be on the wire"); 
/* 421 */     return SerializedField.forInt(c);
/*     */   }
/*     */   
/*     */   private static void skipField(DataInput in) throws IOException {
/* 425 */     int len = in.readInt();
/* 427 */     int skipped = in.skipBytes(len);
/* 428 */     if (skipped < len) {
/* 430 */       byte[] bytes = new byte[len - skipped];
/* 431 */       in.readFully(bytes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 437 */     if (((AbstractConfigValue)this.value).resolveStatus() != ResolveStatus.RESOLVED)
/* 438 */       throw new NotSerializableException("tried to serialize a value with unresolved substitutions, need to Config#resolve() first, see API docs"); 
/* 440 */     FieldOut field = new FieldOut(SerializedField.ROOT_VALUE);
/* 441 */     writeValue(field.data, this.value, null);
/* 442 */     writeField(out, field);
/* 444 */     field = new FieldOut(SerializedField.ROOT_WAS_CONFIG);
/* 445 */     field.data.writeBoolean(this.wasConfig);
/* 446 */     writeField(out, field);
/* 448 */     writeEndMarker(out);
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/*     */     while (true) {
/* 454 */       SerializedField code = readCode(in);
/* 455 */       if (code == SerializedField.END_MARKER)
/*     */         return; 
/* 457 */       if (code == SerializedField.ROOT_VALUE) {
/* 458 */         in.readInt();
/* 459 */         this.value = readValue(in, null);
/*     */         continue;
/*     */       } 
/* 460 */       if (code == SerializedField.ROOT_WAS_CONFIG) {
/* 461 */         in.readInt();
/* 462 */         this.wasConfig = in.readBoolean();
/*     */         continue;
/*     */       } 
/* 465 */       skipField(in);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ConfigException shouldNotBeUsed() {
/* 471 */     return (ConfigException)new ConfigException.BugOrBroken(SerializedConfigValue.class.getName() + " should not exist outside of serialization");
/*     */   }
/*     */   
/*     */   public ConfigValueType valueType() {
/* 477 */     throw shouldNotBeUsed();
/*     */   }
/*     */   
/*     */   public Object unwrapped() {
/* 482 */     throw shouldNotBeUsed();
/*     */   }
/*     */   
/*     */   protected SerializedConfigValue newCopy(ConfigOrigin origin) {
/* 487 */     throw shouldNotBeUsed();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SerializedConfigValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */