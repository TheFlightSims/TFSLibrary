/*     */ package org.mapdb;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InvalidClassException;
/*     */ import java.io.NotSerializableException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.ObjectStreamField;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ 
/*     */ public class SerializerPojo extends SerializerBase implements Serializable {
/*  39 */   protected static final Serializer<CopyOnWriteArrayList<ClassInfo>> serializer = new Serializer<CopyOnWriteArrayList<ClassInfo>>() {
/*     */       public void serialize(DataOutput out, CopyOnWriteArrayList<SerializerPojo.ClassInfo> obj) throws IOException {
/*  43 */         DataOutput2.packInt(out, obj.size());
/*  44 */         for (SerializerPojo.ClassInfo ci : obj) {
/*  45 */           out.writeUTF(ci.name);
/*  46 */           out.writeBoolean(ci.isEnum);
/*  47 */           out.writeBoolean(ci.useObjectStream);
/*  48 */           if (ci.useObjectStream)
/*     */             continue; 
/*  50 */           DataOutput2.packInt(out, ci.fields.size());
/*  51 */           for (SerializerPojo.FieldInfo fi : ci.fields) {
/*  52 */             out.writeUTF(fi.name);
/*  53 */             out.writeBoolean(fi.primitive);
/*  54 */             out.writeUTF(fi.type);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public CopyOnWriteArrayList<SerializerPojo.ClassInfo> deserialize(DataInput in, int available) throws IOException {
/*  61 */         if (available == 0)
/*  61 */           return new CopyOnWriteArrayList<SerializerPojo.ClassInfo>(); 
/*  63 */         int size = DataInput2.unpackInt(in);
/*  64 */         ArrayList<SerializerPojo.ClassInfo> ret = new ArrayList<SerializerPojo.ClassInfo>(size);
/*  66 */         for (int i = 0; i < size; i++) {
/*  67 */           String className = in.readUTF();
/*  68 */           boolean isEnum = in.readBoolean();
/*  69 */           boolean isExternalizable = in.readBoolean();
/*  71 */           int fieldsNum = isExternalizable ? 0 : DataInput2.unpackInt(in);
/*  72 */           SerializerPojo.FieldInfo[] fields = new SerializerPojo.FieldInfo[fieldsNum];
/*  73 */           for (int j = 0; j < fieldsNum; j++)
/*  74 */             fields[j] = new SerializerPojo.FieldInfo(in.readUTF(), in.readBoolean(), in.readUTF(), SerializerPojo.classForName(className)); 
/*  76 */           ret.add(new SerializerPojo.ClassInfo(className, fields, isEnum, isExternalizable));
/*     */         } 
/*  78 */         return new CopyOnWriteArrayList<SerializerPojo.ClassInfo>(ret);
/*     */       }
/*     */       
/*     */       public int fixedSize() {
/*  83 */         return -1;
/*     */       }
/*     */     };
/*     */   
/*     */   private static final long serialVersionUID = 3181417366609199703L;
/*     */   
/*  89 */   protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
/*     */   
/*     */   protected DB db;
/*     */   
/*     */   protected CopyOnWriteArrayList<ClassInfo> registered;
/*     */   
/*     */   protected Map<Class<?>, Integer> class2classId;
/*     */   
/*     */   protected Map<Integer, Class<?>> classId2class;
/*     */   
/*     */   protected static Class<?> classForName(String className) {
/*     */     try {
/*  93 */       ClassLoader loader = Thread.currentThread().getContextClassLoader();
/*  94 */       return Class.forName(className, true, loader);
/*  95 */     } catch (ClassNotFoundException e) {
/*  96 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setDb(DB db) {
/* 120 */     this.db = db;
/*     */   }
/*     */   
/*     */   protected static final class ClassInfo {
/*     */     protected final String name;
/*     */     
/* 130 */     protected final List<SerializerPojo.FieldInfo> fields = new ArrayList<SerializerPojo.FieldInfo>();
/*     */     
/* 131 */     protected final Map<String, SerializerPojo.FieldInfo> name2fieldInfo = new HashMap<String, SerializerPojo.FieldInfo>();
/*     */     
/* 132 */     protected final Map<String, Integer> name2fieldId = new HashMap<String, Integer>();
/*     */     
/*     */     protected ObjectStreamField[] objectStreamFields;
/*     */     
/*     */     protected final boolean isEnum;
/*     */     
/*     */     protected final boolean useObjectStream;
/*     */     
/*     */     public ClassInfo(String name, SerializerPojo.FieldInfo[] fields, boolean isEnum, boolean isExternalizable) {
/* 140 */       this.name = name;
/* 141 */       this.isEnum = isEnum;
/* 142 */       this.useObjectStream = isExternalizable;
/* 144 */       for (SerializerPojo.FieldInfo f : fields) {
/* 145 */         this.name2fieldId.put(f.name, Integer.valueOf(this.fields.size()));
/* 146 */         this.fields.add(f);
/* 147 */         this.name2fieldInfo.put(f.name, f);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int getFieldId(String name) {
/* 153 */       Integer fieldId = this.name2fieldId.get(name);
/* 154 */       if (fieldId != null)
/* 155 */         return fieldId.intValue(); 
/* 156 */       return -1;
/*     */     }
/*     */     
/*     */     public int addFieldInfo(SerializerPojo.FieldInfo field) {
/* 161 */       this.name2fieldId.put(field.name, Integer.valueOf(this.fields.size()));
/* 162 */       this.name2fieldInfo.put(field.name, field);
/* 163 */       this.fields.add(field);
/* 164 */       return this.fields.size() - 1;
/*     */     }
/*     */     
/*     */     public ObjectStreamField[] getObjectStreamFields() {
/* 168 */       return this.objectStreamFields;
/*     */     }
/*     */     
/*     */     public void setObjectStreamFields(ObjectStreamField[] objectStreamFields) {
/* 172 */       this.objectStreamFields = objectStreamFields;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 176 */       return super.toString() + "[" + this.name + "]";
/*     */     }
/*     */   }
/*     */   
/*     */   protected static class FieldInfo {
/*     */     protected final String name;
/*     */     
/*     */     protected final boolean primitive;
/*     */     
/*     */     protected final String type;
/*     */     
/*     */     protected Class<?> typeClass;
/*     */     
/*     */     protected final Class<?> clazz;
/*     */     
/*     */     protected Field field;
/*     */     
/*     */     public FieldInfo(String name, boolean primitive, String type, Class<?> clazz) {
/* 196 */       this.name = name;
/* 197 */       this.primitive = primitive;
/* 198 */       this.type = type;
/* 199 */       this.clazz = clazz;
/* 200 */       this.typeClass = primitive ? null : SerializerPojo.classForName(type);
/* 204 */       Class<?> aClazz = clazz;
/*     */       while (true) {
/* 208 */         if (aClazz == Object.class)
/* 208 */           throw new RuntimeException("Could not set field value: " + name + " - " + clazz.toString()); 
/*     */         try {
/* 211 */           Field f = aClazz.getDeclaredField(name);
/* 213 */           if (!f.isAccessible())
/* 214 */             f.setAccessible(true); 
/* 215 */           this.field = f;
/*     */           break;
/* 217 */         } catch (NoSuchFieldException e) {
/* 221 */           aClazz = aClazz.getSuperclass();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public FieldInfo(ObjectStreamField sf, Class<?> clazz) {
/* 229 */       this(sf.getName(), sf.isPrimitive(), sf.getType().getName(), clazz);
/*     */     }
/*     */   }
/*     */   
/*     */   public SerializerPojo(CopyOnWriteArrayList<ClassInfo> registered) {
/* 236 */     this.class2classId = new HashMap<Class<?>, Integer>();
/* 237 */     this.classId2class = new HashMap<Integer, Class<?>>();
/*     */     if (registered == null)
/*     */       registered = new CopyOnWriteArrayList<ClassInfo>(); 
/*     */     this.registered = registered;
/*     */     this.oldSize = registered.size();
/*     */     for (int i = 0; i < registered.size(); i++) {
/*     */       ClassInfo ci = registered.get(i);
/*     */       Class<?> clazz = classForName(ci.name);
/*     */       this.class2classId.put(clazz, Integer.valueOf(i));
/*     */       this.classId2class.put(Integer.valueOf(i), clazz);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void registerClass(Class<?> clazz) throws IOException {
/* 242 */     if (containsClass(clazz))
/*     */       return; 
/* 245 */     assert this.lock.isWriteLockedByCurrentThread();
/* 247 */     boolean advancedSer = usesAdvancedSerialization(clazz);
/* 248 */     ObjectStreamField[] streamFields = advancedSer ? new ObjectStreamField[0] : getFields(clazz);
/* 249 */     FieldInfo[] fields = new FieldInfo[streamFields.length];
/* 250 */     for (int j = 0; j < fields.length; j++) {
/* 251 */       ObjectStreamField sf = streamFields[j];
/* 252 */       fields[j] = new FieldInfo(sf, clazz);
/*     */     } 
/* 255 */     ClassInfo i = new ClassInfo(clazz.getName(), fields, clazz.isEnum(), advancedSer);
/* 256 */     this.class2classId.put(clazz, Integer.valueOf(this.registered.size()));
/* 257 */     this.classId2class.put(Integer.valueOf(this.registered.size()), clazz);
/* 258 */     this.registered.add(i);
/* 260 */     saveClassInfo();
/*     */   }
/*     */   
/*     */   protected boolean usesAdvancedSerialization(Class<?> clazz) {
/* 264 */     if (Externalizable.class.isAssignableFrom(clazz))
/* 264 */       return true; 
/*     */     try {
/* 266 */       if (clazz.getDeclaredMethod("readObject", new Class[] { ObjectInputStream.class }) != null)
/* 266 */         return true; 
/* 267 */     } catch (NoSuchMethodException e) {}
/*     */     try {
/* 270 */       if (clazz.getDeclaredMethod("writeObject", new Class[] { ObjectOutputStream.class }) != null)
/* 270 */         return true; 
/* 271 */     } catch (NoSuchMethodException e) {}
/*     */     try {
/* 276 */       if (clazz.getDeclaredMethod("writeReplace", new Class[0]) != null)
/* 276 */         return true; 
/* 277 */     } catch (NoSuchMethodException e) {}
/* 280 */     return false;
/*     */   }
/*     */   
/*     */   protected void saveClassInfo() {}
/*     */   
/*     */   protected ObjectStreamField[] getFields(Class<?> clazz) {
/* 289 */     ObjectStreamField[] fields = null;
/* 290 */     ClassInfo classInfo = null;
/* 291 */     Integer classId = this.class2classId.get(clazz);
/* 292 */     if (classId != null) {
/* 293 */       classInfo = this.registered.get(classId.intValue());
/* 294 */       fields = classInfo.getObjectStreamFields();
/*     */     } 
/* 296 */     if (fields == null) {
/* 297 */       ObjectStreamClass streamClass = ObjectStreamClass.lookup(clazz);
/* 298 */       SerializerBase.FastArrayList<ObjectStreamField> fieldsList = new SerializerBase.FastArrayList<ObjectStreamField>();
/* 299 */       while (streamClass != null) {
/* 300 */         for (ObjectStreamField f : streamClass.getFields())
/* 301 */           fieldsList.add(f); 
/* 303 */         clazz = clazz.getSuperclass();
/* 304 */         streamClass = ObjectStreamClass.lookup(clazz);
/*     */       } 
/* 306 */       fields = new ObjectStreamField[fieldsList.size];
/* 308 */       System.arraycopy(fieldsList.data, 0, fields, 0, fields.length);
/* 309 */       if (classInfo != null)
/* 310 */         classInfo.setObjectStreamFields(fields); 
/*     */     } 
/* 312 */     return fields;
/*     */   }
/*     */   
/*     */   protected void assertClassSerializable(Class<?> clazz) throws NotSerializableException, InvalidClassException {
/* 316 */     if (containsClass(clazz))
/*     */       return; 
/* 319 */     if (!Serializable.class.isAssignableFrom(clazz))
/* 320 */       throw new NotSerializableException(clazz.getName()); 
/*     */   }
/*     */   
/*     */   public Object getFieldValue(FieldInfo fieldInfo, Object object) {
/* 327 */     if (fieldInfo.field == null)
/* 328 */       throw new NoSuchFieldError(object.getClass() + "." + fieldInfo.name); 
/*     */     try {
/* 333 */       return fieldInfo.field.get(object);
/* 334 */     } catch (IllegalAccessException e) {
/* 335 */       throw new RuntimeException("Could not get value from field", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFieldValue(FieldInfo fieldInfo, Object object, Object value) {
/* 342 */     if (fieldInfo.field == null)
/* 343 */       throw new NoSuchFieldError(object.getClass() + "." + fieldInfo.name); 
/*     */     try {
/* 346 */       fieldInfo.field.set(object, value);
/* 347 */     } catch (IllegalAccessException e) {
/* 348 */       throw new RuntimeException("Could not set field value: ", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsClass(Class<?> clazz) {
/* 354 */     return (this.class2classId.get(clazz) != null);
/*     */   }
/*     */   
/*     */   public int getClassId(Class<?> clazz) {
/* 358 */     Integer classId = this.class2classId.get(clazz);
/* 359 */     if (classId != null)
/* 360 */       return classId.intValue(); 
/* 362 */     throw new AssertionError("Class is not registered: " + clazz);
/*     */   }
/*     */   
/*     */   protected Engine getEngine() {
/* 367 */     return this.db.getEngine();
/*     */   }
/*     */   
/*     */   protected void serializeUnknownObject(DataOutput out, Object obj, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 372 */     if (this.db != null) {
/* 374 */       String name = this.db.getNameForObject(obj);
/* 375 */       if (name != null) {
/* 376 */         out.write(175);
/* 377 */         out.writeUTF(name);
/*     */         return;
/*     */       } 
/*     */     } 
/* 382 */     out.write(173);
/* 383 */     this.lock.writeLock().lock();
/*     */     try {
/* 385 */       Class<?> clazz = obj.getClass();
/* 386 */       if (!clazz.isEnum() && clazz.getSuperclass() != null && clazz.getSuperclass().isEnum())
/* 387 */         clazz = clazz.getSuperclass(); 
/* 389 */       if (clazz != Object.class)
/* 390 */         assertClassSerializable(clazz); 
/* 392 */       registerClass(clazz);
/* 395 */       int classId = getClassId(clazz);
/* 396 */       DataOutput2.packInt(out, classId);
/* 397 */       ClassInfo classInfo = this.registered.get(classId);
/* 399 */       if (classInfo.useObjectStream) {
/* 400 */         ObjectOutputStream2 out2 = new ObjectOutputStream2((OutputStream)out);
/* 401 */         out2.writeObject(obj);
/*     */         return;
/*     */       } 
/* 406 */       if (classInfo.isEnum) {
/* 407 */         int ordinal = ((Enum)obj).ordinal();
/* 408 */         DataOutput2.packInt(out, ordinal);
/*     */       } 
/* 411 */       ObjectStreamField[] fields = getFields(clazz);
/* 412 */       DataOutput2.packInt(out, fields.length);
/* 414 */       for (ObjectStreamField f : fields) {
/* 416 */         int fieldId = classInfo.getFieldId(f.getName());
/* 417 */         if (fieldId == -1) {
/* 420 */           fieldId = classInfo.addFieldInfo(new FieldInfo(f, clazz));
/* 421 */           saveClassInfo();
/*     */         } 
/* 423 */         DataOutput2.packInt(out, fieldId);
/* 425 */         Object fieldValue = getFieldValue(classInfo.fields.get(fieldId), obj);
/* 426 */         serialize(out, fieldValue, objectStack);
/*     */       } 
/*     */     } finally {
/* 429 */       this.lock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Object deserializeUnknownHeader(DataInput in, int head, SerializerBase.FastArrayList<Object> objectStack) throws IOException {
/* 436 */     if (head == 175) {
/* 437 */       String name = in.readUTF();
/* 438 */       Object o = this.db.get(name);
/* 439 */       if (o == null)
/* 439 */         throw new AssertionError("Named object was not found: " + name); 
/* 440 */       objectStack.add(o);
/* 441 */       return o;
/*     */     } 
/* 444 */     if (head != 173)
/* 444 */       throw new AssertionError(); 
/* 446 */     this.lock.readLock().lock();
/*     */     try {
/*     */       Object o;
/* 449 */       int classId = DataInput2.unpackInt(in);
/* 450 */       ClassInfo classInfo = this.registered.get(classId);
/* 452 */       Class<?> clazz = this.classId2class.get(Integer.valueOf(classId));
/* 453 */       if (clazz == null)
/* 454 */         clazz = classForName(classInfo.name); 
/* 455 */       assertClassSerializable(clazz);
/* 459 */       if (classInfo.useObjectStream) {
/* 460 */         ObjectInputStream2 in2 = new ObjectInputStream2((InputStream)in);
/* 461 */         o = in2.readObject();
/* 462 */       } else if (classInfo.isEnum) {
/* 463 */         int ordinal = DataInput2.unpackInt(in);
/* 464 */         o = clazz.getEnumConstants()[ordinal];
/*     */       } else {
/* 467 */         o = createInstanceSkippinkConstructor(clazz);
/*     */       } 
/* 470 */       objectStack.add(o);
/* 472 */       if (!classInfo.useObjectStream) {
/* 473 */         int fieldCount = DataInput2.unpackInt(in);
/* 474 */         for (int i = 0; i < fieldCount; i++) {
/* 475 */           int fieldId = DataInput2.unpackInt(in);
/* 476 */           FieldInfo f = classInfo.fields.get(fieldId);
/* 477 */           Object fieldValue = deserialize(in, objectStack);
/* 478 */           setFieldValue(f, o, fieldValue);
/*     */         } 
/*     */       } 
/* 481 */       return o;
/* 482 */     } catch (Exception e) {
/* 483 */       throw new RuntimeException("Could not instantiate class", e);
/*     */     } finally {
/* 485 */       this.lock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/* 490 */   protected static Method sunConstructor = null;
/*     */   
/* 491 */   protected static Object sunReflFac = null;
/*     */   
/* 492 */   protected static Method androidConstructor = null;
/*     */   
/* 493 */   private static Method androidConstructorGinger = null;
/*     */   
/*     */   private static Object constructorId;
/*     */   
/*     */   static {
/*     */     try {
/* 498 */       Class<?> clazz = classForName("sun.reflect.ReflectionFactory");
/* 499 */       if (clazz != null) {
/* 500 */         Method getReflectionFactory = clazz.getMethod("getReflectionFactory", new Class[0]);
/* 501 */         sunReflFac = getReflectionFactory.invoke(null, new Object[0]);
/* 502 */         sunConstructor = clazz.getMethod("newConstructorForSerialization", new Class[] { Class.class, Constructor.class });
/*     */       } 
/* 505 */     } catch (Exception e) {}
/* 509 */     if (sunConstructor == null)
/*     */       try {
/* 511 */         Method newInstance = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
/* 512 */         newInstance.setAccessible(true);
/* 513 */         androidConstructor = newInstance;
/* 515 */       } catch (Exception e) {} 
/* 522 */     if (sunConstructor == null && androidConstructor == null)
/*     */       try {
/* 524 */         Method getConstructorId = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
/* 525 */         getConstructorId.setAccessible(true);
/* 526 */         constructorId = getConstructorId.invoke(null, new Object[] { Object.class });
/* 528 */         Method newInstance = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[] { Class.class, getConstructorId.getReturnType() });
/* 529 */         newInstance.setAccessible(true);
/* 530 */         androidConstructorGinger = newInstance;
/* 532 */       } catch (Exception e) {} 
/*     */   }
/*     */   
/* 538 */   protected static Map<Class<?>, Constructor<?>> class2constuctor = new ConcurrentHashMap<Class<?>, Constructor<?>>();
/*     */   
/*     */   protected int oldSize;
/*     */   
/*     */   protected CopyOnWriteArrayList<Fun.Function1> serializationTransformsSerialize;
/*     */   
/*     */   protected CopyOnWriteArrayList<Fun.Function1> serializationTransformsDeserialize;
/*     */   
/*     */   protected <T> T createInstanceSkippinkConstructor(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
/* 557 */     if (sunConstructor != null) {
/* 559 */       Constructor<?> intConstr = class2constuctor.get(clazz);
/* 561 */       if (intConstr == null) {
/* 562 */         Constructor<?> objDef = Object.class.getDeclaredConstructor(new Class[0]);
/* 563 */         intConstr = (Constructor)sunConstructor.invoke(sunReflFac, new Object[] { clazz, objDef });
/* 564 */         class2constuctor.put(clazz, intConstr);
/*     */       } 
/* 567 */       return (T)intConstr.newInstance(new Object[0]);
/*     */     } 
/* 568 */     if (androidConstructor != null)
/* 570 */       return (T)androidConstructor.invoke(null, new Object[] { clazz, Object.class }); 
/* 571 */     if (androidConstructorGinger != null)
/* 573 */       return (T)androidConstructorGinger.invoke(null, new Object[] { clazz, constructorId }); 
/* 577 */     Constructor<?> c = class2constuctor.get(clazz);
/* 578 */     if (c == null) {
/* 579 */       c = clazz.getConstructor(new Class[0]);
/* 580 */       if (!c.isAccessible())
/* 580 */         c.setAccessible(true); 
/* 581 */       class2constuctor.put(clazz, c);
/*     */     } 
/* 583 */     return (T)c.newInstance(new Object[0]);
/*     */   }
/*     */   
/*     */   protected final class ObjectOutputStream2 extends ObjectOutputStream {
/*     */     protected ObjectOutputStream2(OutputStream out) throws IOException, SecurityException {
/* 593 */       super(out);
/*     */     }
/*     */     
/*     */     protected void writeClassDescriptor(ObjectStreamClass desc) throws IOException {
/* 598 */       Integer classId = SerializerPojo.this.class2classId.get(desc.forClass());
/* 599 */       if (classId == null) {
/* 600 */         SerializerPojo.this.registerClass(desc.forClass());
/* 601 */         classId = SerializerPojo.this.class2classId.get(desc.forClass());
/*     */       } 
/* 603 */       DataOutput2.packInt(this, classId.intValue());
/*     */     }
/*     */   }
/*     */   
/*     */   protected final class ObjectInputStream2 extends ObjectInputStream {
/*     */     protected ObjectInputStream2(InputStream in) throws IOException, SecurityException {
/* 610 */       super(in);
/*     */     }
/*     */     
/*     */     protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
/* 615 */       Integer classId = Integer.valueOf(DataInput2.unpackInt(this));
/* 616 */       Class<?> clazz = SerializerPojo.this.classId2class.get(classId);
/* 617 */       return ObjectStreamClass.lookup(clazz);
/*     */     }
/*     */     
/*     */     protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
/* 622 */       ClassLoader loader = Thread.currentThread().getContextClassLoader();
/* 623 */       Class<?> clazz = Class.forName(desc.getName(), false, loader);
/* 624 */       if (clazz != null)
/* 625 */         return clazz; 
/* 626 */       return super.resolveClass(desc);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasUnsavedChanges() {
/* 633 */     return (this.oldSize != this.registered.size());
/*     */   }
/*     */   
/*     */   public void save(Engine e) {
/* 637 */     e.update(2L, this.registered, serializer);
/* 638 */     this.oldSize = this.registered.size();
/*     */   }
/*     */   
/*     */   public <A, R> void serializerTransformAdd(Fun.Function1<A, R> beforeSerialization, Fun.Function1<R, A> afterDeserialization) {
/* 651 */     this.lock.writeLock().lock();
/*     */     try {
/* 654 */       if (this.serializationTransformsSerialize == null) {
/* 655 */         this.serializationTransformsSerialize = new CopyOnWriteArrayList<Fun.Function1>();
/* 656 */         this.serializationTransformsDeserialize = new CopyOnWriteArrayList<Fun.Function1>();
/*     */       } 
/* 659 */       this.serializationTransformsSerialize.add(beforeSerialization);
/* 660 */       this.serializationTransformsDeserialize.add(afterDeserialization);
/*     */     } finally {
/* 662 */       this.lock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, R> void serializerTransformRemove(Fun.Function1<A, R> beforeSerialization, Fun.Function1<R, A> afterDeserialization) {
/* 675 */     this.lock.writeLock().lock();
/*     */     try {
/* 678 */       if (this.serializationTransformsSerialize == null)
/*     */         return; 
/* 681 */       this.serializationTransformsSerialize.remove(beforeSerialization);
/* 682 */       this.serializationTransformsDeserialize.remove(afterDeserialization);
/*     */     } finally {
/* 684 */       this.lock.writeLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void serialize(DataOutput out, Object obj) throws IOException {
/* 691 */     if (this.serializationTransformsSerialize != null)
/* 692 */       for (Fun.Function1 f : this.serializationTransformsSerialize)
/* 693 */         obj = f.run(obj);  
/* 696 */     super.serialize(out, obj);
/*     */   }
/*     */   
/*     */   public Object deserialize(DataInput is, int capacity) throws IOException {
/* 701 */     Object obj = super.deserialize(is, capacity);
/* 703 */     if (this.serializationTransformsDeserialize != null)
/* 704 */       for (Fun.Function1 f : this.serializationTransformsDeserialize)
/* 705 */         obj = f.run(obj);  
/* 709 */     return obj;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\mapdb\SerializerPojo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */