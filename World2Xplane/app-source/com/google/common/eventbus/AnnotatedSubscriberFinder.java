/*     */ package com.google.common.eventbus;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.reflect.TypeToken;
/*     */ import com.google.common.util.concurrent.UncheckedExecutionException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ class AnnotatedSubscriberFinder implements SubscriberFindingStrategy {
/*  53 */   private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableList<Method>>() {
/*     */         public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
/*  59 */           return AnnotatedSubscriberFinder.getAnnotatedMethodsInternal(concreteClass);
/*     */         }
/*     */       });
/*     */   
/*     */   public Multimap<Class<?>, EventSubscriber> findAllSubscribers(Object listener) {
/*  70 */     HashMultimap hashMultimap = HashMultimap.create();
/*  71 */     Class<?> clazz = listener.getClass();
/*  72 */     for (Method method : getAnnotatedMethods(clazz)) {
/*  73 */       Class<?>[] parameterTypes = method.getParameterTypes();
/*  74 */       Class<?> eventType = parameterTypes[0];
/*  75 */       EventSubscriber subscriber = makeSubscriber(listener, method);
/*  76 */       hashMultimap.put(eventType, subscriber);
/*     */     } 
/*  78 */     return (Multimap<Class<?>, EventSubscriber>)hashMultimap;
/*     */   }
/*     */   
/*     */   private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
/*     */     try {
/*  83 */       return (ImmutableList<Method>)subscriberMethodsCache.getUnchecked(clazz);
/*  84 */     } catch (UncheckedExecutionException e) {
/*  85 */       throw Throwables.propagate(e.getCause());
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final class MethodIdentifier {
/*     */     private final String name;
/*     */     
/*     */     private final List<Class<?>> parameterTypes;
/*     */     
/*     */     MethodIdentifier(Method method) {
/*  94 */       this.name = method.getName();
/*  95 */       this.parameterTypes = Arrays.asList(method.getParameterTypes());
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 100 */       return Objects.hashCode(new Object[] { this.name, this.parameterTypes });
/*     */     }
/*     */     
/*     */     public boolean equals(@Nullable Object o) {
/* 105 */       if (o instanceof MethodIdentifier) {
/* 106 */         MethodIdentifier ident = (MethodIdentifier)o;
/* 107 */         return (this.name.equals(ident.name) && this.parameterTypes.equals(ident.parameterTypes));
/*     */       } 
/* 109 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private static ImmutableList<Method> getAnnotatedMethodsInternal(Class<?> clazz) {
/* 114 */     Set<? extends Class<?>> supers = TypeToken.of(clazz).getTypes().rawTypes();
/* 115 */     Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
/* 116 */     for (Class<?> superClazz : supers) {
/* 117 */       for (Method superClazzMethod : superClazz.getMethods()) {
/* 118 */         if (superClazzMethod.isAnnotationPresent((Class)Subscribe.class)) {
/* 119 */           Class<?>[] parameterTypes = superClazzMethod.getParameterTypes();
/* 120 */           if (parameterTypes.length != 1)
/* 121 */             throw new IllegalArgumentException("Method " + superClazzMethod + " has @Subscribe annotation, but requires " + parameterTypes.length + " arguments.  Event subscriber methods must require a single argument."); 
/* 126 */           MethodIdentifier ident = new MethodIdentifier(superClazzMethod);
/* 127 */           if (!identifiers.containsKey(ident))
/* 128 */             identifiers.put(ident, superClazzMethod); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 133 */     return ImmutableList.copyOf(identifiers.values());
/*     */   }
/*     */   
/*     */   private static EventSubscriber makeSubscriber(Object listener, Method method) {
/*     */     EventSubscriber wrapper;
/* 149 */     if (methodIsDeclaredThreadSafe(method)) {
/* 150 */       wrapper = new EventSubscriber(listener, method);
/*     */     } else {
/* 152 */       wrapper = new SynchronizedEventSubscriber(listener, method);
/*     */     } 
/* 154 */     return wrapper;
/*     */   }
/*     */   
/*     */   private static boolean methodIsDeclaredThreadSafe(Method method) {
/* 166 */     return (method.getAnnotation(AllowConcurrentEvents.class) != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\eventbus\AnnotatedSubscriberFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */