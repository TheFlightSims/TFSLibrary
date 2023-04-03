/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.util.ImageUtil;
/*     */ import java.awt.RenderingHints;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.media.jai.remote.RemoteImagingException;
/*     */ import javax.media.jai.remote.SerializableState;
/*     */ import javax.media.jai.remote.Serializer;
/*     */ import javax.media.jai.remote.SerializerFactory;
/*     */ import javax.media.jai.util.ImagingException;
/*     */ import javax.media.jai.util.ImagingListener;
/*     */ 
/*     */ public final class SerializerImpl implements Serializer {
/*     */   private Class theClass;
/*     */   
/*     */   private boolean areSubclassesPermitted;
/*     */   
/*     */   private Constructor ctor;
/*     */   
/*     */   public static final void registerSerializers() {
/*  48 */     registerSerializers(ColorModelState.class);
/*  49 */     registerSerializers(DataBufferState.class);
/*  50 */     registerSerializers(HashSetState.class);
/*  51 */     registerSerializers(HashtableState.class);
/*  52 */     registerSerializers(RasterState.class);
/*  53 */     registerSerializers(RenderedImageState.class);
/*  54 */     registerSerializers(RenderContextState.class);
/*  55 */     registerSerializers(RenderingHintsState.class);
/*  56 */     registerSerializers(RenderingKeyState.class);
/*  57 */     registerSerializers(SampleModelState.class);
/*  58 */     registerSerializers(VectorState.class);
/*  59 */     registerSerializers(ShapeState.class);
/*     */   }
/*     */   
/*     */   private static void registerSerializers(Class ssi) {
/*  63 */     if (ssi == null)
/*  64 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  67 */     if (!SerializableStateImpl.class.isAssignableFrom(ssi))
/*  68 */       throw new IllegalArgumentException(JaiI18N.getString("SerializerImpl0")); 
/*  71 */     ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/*  73 */     Class[] classes = null;
/*     */     try {
/*  75 */       Method m1 = ssi.getMethod("getSupportedClasses", null);
/*  76 */       classes = (Class[])m1.invoke(null, null);
/*  77 */     } catch (NoSuchMethodException e) {
/*  78 */       String message = JaiI18N.getString("SerializerImpl1");
/*  79 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/*  82 */     } catch (IllegalAccessException e) {
/*  83 */       String message = JaiI18N.getString("SerializerImpl1");
/*  84 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/*  87 */     } catch (InvocationTargetException e) {
/*  88 */       String message = JaiI18N.getString("SerializerImpl1");
/*  89 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/*     */     } 
/*  94 */     boolean supportsSubclasses = false;
/*     */     try {
/*  96 */       Method m2 = ssi.getMethod("permitsSubclasses", null);
/*  97 */       Boolean b = (Boolean)m2.invoke(null, null);
/*  98 */       supportsSubclasses = b.booleanValue();
/*  99 */     } catch (NoSuchMethodException e) {
/* 100 */       String message = JaiI18N.getString("SerializerImpl4");
/* 101 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/* 104 */     } catch (IllegalAccessException e) {
/* 105 */       String message = JaiI18N.getString("SerializerImpl4");
/* 106 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/* 109 */     } catch (InvocationTargetException e) {
/* 110 */       String message = JaiI18N.getString("SerializerImpl4");
/* 111 */       listener.errorOccurred(message, (Throwable)new RemoteImagingException(message, e), SerializerImpl.class, false);
/*     */     } 
/* 116 */     int numClasses = classes.length;
/* 117 */     for (int i = 0; i < numClasses; i++) {
/* 118 */       Serializer s = new SerializerImpl(ssi, classes[i], supportsSubclasses);
/* 120 */       SerializerFactory.registerSerializer(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected SerializerImpl(Class ssi, Class c, boolean areSubclassesPermitted) {
/* 135 */     this.theClass = c;
/* 136 */     this.areSubclassesPermitted = areSubclassesPermitted;
/*     */     try {
/* 139 */       Class[] paramTypes = { Class.class, Object.class, RenderingHints.class };
/* 142 */       this.ctor = ssi.getConstructor(paramTypes);
/* 143 */     } catch (NoSuchMethodException e) {
/* 144 */       String message = this.theClass.getName() + ": " + JaiI18N.getString("SerializerImpl2");
/* 146 */       sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/*     */     } 
/*     */   }
/*     */   
/*     */   public SerializableState getState(Object o, RenderingHints h) {
/* 157 */     Object state = null;
/*     */     try {
/* 159 */       state = this.ctor.newInstance(new Object[] { this.theClass, o, h });
/* 160 */     } catch (InstantiationException e) {
/* 161 */       String message = this.theClass.getName() + ": " + JaiI18N.getString("SerializerImpl3");
/* 163 */       sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/* 165 */     } catch (IllegalAccessException e) {
/* 166 */       String message = this.theClass.getName() + ": " + JaiI18N.getString("SerializerImpl3");
/* 168 */       sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/* 170 */     } catch (InvocationTargetException e) {
/* 171 */       String message = this.theClass.getName() + ": " + JaiI18N.getString("SerializerImpl3");
/* 173 */       sendExceptionToListener(message, (Exception)new RemoteImagingException(message, e));
/*     */     } 
/* 177 */     return (SerializableState)state;
/*     */   }
/*     */   
/*     */   public Class getSupportedClass() {
/* 181 */     return this.theClass;
/*     */   }
/*     */   
/*     */   public boolean permitsSubclasses() {
/* 185 */     return this.areSubclassesPermitted;
/*     */   }
/*     */   
/*     */   private void sendExceptionToListener(String message, Exception e) {
/* 189 */     ImagingListener listener = ImageUtil.getImagingListener((RenderingHints)null);
/* 191 */     listener.errorOccurred(message, (Throwable)new ImagingException(message, e), this, false);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\SerializerImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */