/*     */ package javax.media.jai.remote;
/*     */ 
/*     */ import com.sun.media.jai.rmi.InterfaceState;
/*     */ import com.sun.media.jai.rmi.SerializerImpl;
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public final class SerializerFactory {
/* 108 */   private static Hashtable repository = new Hashtable();
/*     */   
/* 114 */   private static Serializer serializableSerializer = new SerSerializer();
/*     */   
/* 116 */   static final SerializableState NULL_STATE = new SerializableState() {
/*     */       public Class getObjectClass() {
/* 119 */         return (SerializerFactory.class$java$lang$Object == null) ? (SerializerFactory.class$java$lang$Object = SerializerFactory.class$("java.lang.Object")) : SerializerFactory.class$java$lang$Object;
/*     */       }
/*     */       
/*     */       public Object getObject() {
/* 123 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   static {
/* 129 */     SerializerImpl.registerSerializers();
/*     */   }
/*     */   
/*     */   public static synchronized void registerSerializer(Serializer s) {
/* 142 */     if (s == null)
/* 143 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 146 */     Class c = s.getSupportedClass();
/* 148 */     if (repository.containsKey(c)) {
/* 149 */       Object value = repository.get(c);
/* 150 */       if (value instanceof Vector) {
/* 151 */         ((Vector)value).add(0, s);
/*     */       } else {
/* 153 */         Vector v = new Vector(2);
/* 154 */         v.add(0, s);
/* 155 */         v.add(1, value);
/* 156 */         repository.put(c, v);
/*     */       } 
/*     */     } else {
/* 159 */       repository.put(c, s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static synchronized void unregisterSerializer(Serializer s) {
/* 171 */     if (s == null)
/* 172 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 175 */     Class c = s.getSupportedClass();
/* 176 */     Object value = repository.get(c);
/* 177 */     if (value != null)
/* 178 */       if (value instanceof Vector) {
/* 179 */         Vector v = (Vector)value;
/* 180 */         v.remove(s);
/* 181 */         if (v.size() == 1)
/* 182 */           repository.put(c, v.get(0)); 
/*     */       } else {
/* 185 */         repository.remove(c);
/*     */       }  
/*     */   }
/*     */   
/*     */   public static synchronized Serializer[] getSerializers(Class c) {
/* 203 */     if (c == null)
/* 204 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 206 */     Object value = repository.get(c);
/* 207 */     Serializer[] result = null;
/* 208 */     if (value == null && Serializable.class.isAssignableFrom(c)) {
/* 209 */       result = new Serializer[] { serializableSerializer };
/* 210 */     } else if (value instanceof Vector) {
/* 211 */       result = (Serializer[])((Vector)value).toArray((Object[])new Serializer[0]);
/* 212 */     } else if (value != null) {
/* 213 */       result = new Serializer[] { (Serializer)value };
/*     */     } 
/* 215 */     return result;
/*     */   }
/*     */   
/*     */   public static synchronized Serializer getSerializer(Class c) {
/* 240 */     if (c == null)
/* 241 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 245 */     Object value = repository.get(c);
/* 248 */     if (value == null) {
/* 249 */       Class theClass = c;
/* 250 */       while (theClass != Object.class) {
/* 251 */         Class theSuperclass = theClass.getSuperclass();
/* 252 */         if (isSupportedClass(theSuperclass)) {
/* 253 */           Serializer s = getSerializer(theSuperclass);
/* 254 */           if (s.permitsSubclasses()) {
/* 255 */             value = s;
/*     */             break;
/*     */           } 
/*     */         } 
/* 259 */         Class clazz1 = theSuperclass;
/*     */       } 
/*     */     } 
/* 263 */     if (value == null && Serializable.class.isAssignableFrom(c))
/* 264 */       value = serializableSerializer; 
/* 268 */     return (value instanceof Vector) ? ((Vector)value).get(0) : (Serializer)value;
/*     */   }
/*     */   
/*     */   public static boolean isSupportedClass(Class c) {
/* 284 */     if (c == null)
/* 285 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 286 */     if (Serializable.class.isAssignableFrom(c))
/* 287 */       return true; 
/* 289 */     return repository.containsKey(c);
/*     */   }
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/* 300 */     Class[] classes = new Class[repository.size() + 1];
/* 301 */     repository.keySet().toArray((Object[])classes);
/* 302 */     classes[classes.length - 1] = Serializable.class;
/* 303 */     return classes;
/*     */   }
/*     */   
/*     */   public static Class getDeserializedClass(Class c) {
/* 326 */     if (c == null)
/* 327 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 330 */     Class clazz = null;
/* 333 */     if (isSupportedClass(c)) {
/* 334 */       clazz = c;
/*     */     } else {
/* 336 */       Class theClass = c;
/* 337 */       while (theClass != Object.class) {
/* 338 */         Class theSuperclass = theClass.getSuperclass();
/* 339 */         if (isSupportedClass(theSuperclass)) {
/* 340 */           Serializer s = getSerializer(theSuperclass);
/* 341 */           if (s.permitsSubclasses()) {
/* 342 */             clazz = theSuperclass;
/*     */             break;
/*     */           } 
/*     */         } 
/* 346 */         Class clazz1 = theSuperclass;
/*     */       } 
/*     */     } 
/* 350 */     return clazz;
/*     */   }
/*     */   
/*     */   public static SerializableState getState(Object o, RenderingHints h) {
/*     */     InterfaceState interfaceState;
/* 385 */     if (o == null)
/* 386 */       return NULL_STATE; 
/* 389 */     Class c = o.getClass();
/* 390 */     SerializableState state = null;
/* 391 */     if (isSupportedClass(c)) {
/* 393 */       Serializer s = getSerializer(c);
/* 394 */       state = s.getState(o, h);
/*     */     } else {
/* 397 */       Class theClass = c;
/* 398 */       while (theClass != Object.class) {
/* 399 */         Class theSuperclass = theClass.getSuperclass();
/* 400 */         if (isSupportedClass(theSuperclass)) {
/* 401 */           Serializer s = getSerializer(theSuperclass);
/* 402 */           if (s.permitsSubclasses()) {
/* 403 */             state = s.getState(o, h);
/*     */             break;
/*     */           } 
/*     */         } 
/* 407 */         theClass = theSuperclass;
/*     */       } 
/* 410 */       if (state == null) {
/* 413 */         Class[] interfaces = getInterfaces(c);
/* 414 */         Vector serializers = null;
/* 415 */         int numInterfaces = (interfaces == null) ? 0 : interfaces.length;
/* 416 */         for (int i = 0; i < numInterfaces; i++) {
/* 417 */           Class iface = interfaces[i];
/* 418 */           if (isSupportedClass(iface)) {
/* 419 */             if (serializers == null)
/* 420 */               serializers = new Vector(); 
/* 422 */             serializers.add(getSerializer(iface));
/*     */           } 
/*     */         } 
/* 426 */         int numSupportedInterfaces = (serializers == null) ? 0 : serializers.size();
/* 428 */         if (numSupportedInterfaces == 0)
/* 429 */           throw new IllegalArgumentException(JaiI18N.getString("SerializerFactory1")); 
/* 431 */         if (numSupportedInterfaces == 1) {
/* 432 */           state = ((Serializer)serializers.get(0)).getState(o, h);
/*     */         } else {
/* 434 */           Serializer[] sArray = serializers.<Serializer>toArray(new Serializer[0]);
/* 436 */           interfaceState = new InterfaceState(o, sArray, h);
/*     */         } 
/*     */       } 
/*     */     } 
/* 441 */     return (SerializableState)interfaceState;
/*     */   }
/*     */   
/*     */   private static Class[] getInterfaces(Class c) {
/* 449 */     if (c == null)
/* 450 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 453 */     ArrayList interfaces = new ArrayList();
/* 454 */     Class laClasse = c;
/* 455 */     while (laClasse != Object.class) {
/* 456 */       Class[] iFaces = laClasse.getInterfaces();
/* 457 */       if (iFaces != null)
/* 458 */         for (int i = 0; i < iFaces.length; i++)
/* 459 */           interfaces.add(iFaces[i]);  
/* 462 */       laClasse = (Class)laClasse.getSuperclass();
/*     */     } 
/* 465 */     return (interfaces.size() == 0) ? null : (Class[])interfaces.<Class[]>toArray((Class[][])new Class[interfaces.size()]);
/*     */   }
/*     */   
/*     */   public static final SerializableState getState(Object o) {
/* 476 */     return getState(o, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\SerializerFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */