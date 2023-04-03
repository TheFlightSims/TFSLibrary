/*     */ package com.google.common.reflect;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.AnnotatedElement;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ @Beta
/*     */ public final class Parameter implements AnnotatedElement {
/*     */   private final Invokable<?, ?> declaration;
/*     */   
/*     */   private final int position;
/*     */   
/*     */   private final TypeToken<?> type;
/*     */   
/*     */   private final ImmutableList<Annotation> annotations;
/*     */   
/*     */   Parameter(Invokable<?, ?> declaration, int position, TypeToken<?> type, Annotation[] annotations) {
/*  48 */     this.declaration = declaration;
/*  49 */     this.position = position;
/*  50 */     this.type = type;
/*  51 */     this.annotations = ImmutableList.copyOf((Object[])annotations);
/*     */   }
/*     */   
/*     */   public TypeToken<?> getType() {
/*  56 */     return this.type;
/*     */   }
/*     */   
/*     */   public Invokable<?, ?> getDeclaringInvokable() {
/*  61 */     return this.declaration;
/*     */   }
/*     */   
/*     */   public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
/*  65 */     return (getAnnotation(annotationType) != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
/*  71 */     Preconditions.checkNotNull(annotationType);
/*  72 */     for (Annotation annotation : this.annotations) {
/*  73 */       if (annotationType.isInstance(annotation))
/*  74 */         return annotationType.cast(annotation); 
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */   
/*     */   public Annotation[] getAnnotations() {
/*  81 */     return getDeclaredAnnotations();
/*     */   }
/*     */   
/*     */   public Annotation[] getDeclaredAnnotations() {
/*  85 */     return (Annotation[])this.annotations.toArray((Object[])new Annotation[this.annotations.size()]);
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object obj) {
/*  89 */     if (obj instanceof Parameter) {
/*  90 */       Parameter that = (Parameter)obj;
/*  91 */       return (this.position == that.position && this.declaration.equals(that.declaration));
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  97 */     return this.position;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 101 */     return this.type + " arg" + this.position;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\reflect\Parameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */