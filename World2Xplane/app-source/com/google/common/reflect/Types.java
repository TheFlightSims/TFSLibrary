/*     */ package com.google.common.reflect;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.GenericArrayType;
/*     */ import java.lang.reflect.GenericDeclaration;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.TypeVariable;
/*     */ import java.lang.reflect.WildcardType;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ final class Types {
/*  53 */   private static final Function<Type, String> TYPE_TO_STRING = new Function<Type, String>() {
/*     */       public String apply(Type from) {
/*  56 */         return Types.toString(from);
/*     */       }
/*     */     };
/*     */   
/*  60 */   private static final Joiner COMMA_JOINER = Joiner.on(", ").useForNull("null");
/*     */   
/*     */   static Type newArrayType(Type componentType) {
/*  64 */     if (componentType instanceof WildcardType) {
/*  65 */       WildcardType wildcard = (WildcardType)componentType;
/*  66 */       Type[] lowerBounds = wildcard.getLowerBounds();
/*  67 */       Preconditions.checkArgument((lowerBounds.length <= 1), "Wildcard cannot have more than one lower bounds.");
/*  68 */       if (lowerBounds.length == 1)
/*  69 */         return supertypeOf(newArrayType(lowerBounds[0])); 
/*  71 */       Type[] upperBounds = wildcard.getUpperBounds();
/*  72 */       Preconditions.checkArgument((upperBounds.length == 1), "Wildcard should have only one upper bound.");
/*  73 */       return subtypeOf(newArrayType(upperBounds[0]));
/*     */     } 
/*  76 */     return JavaVersion.CURRENT.newArrayType(componentType);
/*     */   }
/*     */   
/*     */   static ParameterizedType newParameterizedTypeWithOwner(@Nullable Type ownerType, Class<?> rawType, Type... arguments) {
/*  85 */     if (ownerType == null)
/*  86 */       return newParameterizedType(rawType, arguments); 
/*  89 */     Preconditions.checkNotNull(arguments);
/*  90 */     Preconditions.checkArgument((rawType.getEnclosingClass() != null), "Owner type for unenclosed %s", new Object[] { rawType });
/*  91 */     return new ParameterizedTypeImpl(ownerType, rawType, arguments);
/*     */   }
/*     */   
/*     */   static ParameterizedType newParameterizedType(Class<?> rawType, Type... arguments) {
/*  99 */     return new ParameterizedTypeImpl(ClassOwnership.JVM_BEHAVIOR.getOwnerType(rawType), rawType, arguments);
/*     */   }
/*     */   
/*     */   private enum ClassOwnership {
/* 106 */     OWNED_BY_ENCLOSING_CLASS {
/*     */       @Nullable
/*     */       Class<?> getOwnerType(Class<?> rawType) {
/* 110 */         return rawType.getEnclosingClass();
/*     */       }
/*     */     },
/* 113 */     LOCAL_CLASS_HAS_NO_OWNER {
/*     */       @Nullable
/*     */       Class<?> getOwnerType(Class<?> rawType) {
/* 117 */         if (rawType.isLocalClass())
/* 118 */           return null; 
/* 120 */         return rawType.getEnclosingClass();
/*     */       }
/*     */     };
/*     */     
/* 127 */     static final ClassOwnership JVM_BEHAVIOR = detectJvmBehavior();
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     private static ClassOwnership detectJvmBehavior() {
/* 131 */       Class<?> subclass = (new LocalClass<String>() {
/*     */         
/* 131 */         }).getClass();
/* 132 */       ParameterizedType parameterizedType = (ParameterizedType)subclass.getGenericSuperclass();
/* 134 */       for (ClassOwnership behavior : values()) {
/* 135 */         if (behavior.getOwnerType(LocalClass.class) == parameterizedType.getOwnerType())
/* 136 */           return behavior; 
/*     */       } 
/* 139 */       throw new AssertionError();
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     abstract Class<?> getOwnerType(Class<?> param1Class);
/*     */   }
/*     */   
/*     */   static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(D declaration, String name, Type... bounds) {
/* 149 */     (new Type[1])[0] = Object.class;
/* 149 */     return new TypeVariableImpl<D>(declaration, name, (bounds.length == 0) ? new Type[1] : bounds);
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static WildcardType subtypeOf(Type upperBound) {
/* 159 */     return new WildcardTypeImpl(new Type[0], new Type[] { upperBound });
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   static WildcardType supertypeOf(Type lowerBound) {
/* 164 */     return new WildcardTypeImpl(new Type[] { lowerBound }, new Type[] { Object.class });
/*     */   }
/*     */   
/*     */   static String toString(Type type) {
/* 177 */     return (type instanceof Class) ? ((Class)type).getName() : type.toString();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   static Type getComponentType(Type type) {
/* 183 */     Preconditions.checkNotNull(type);
/* 184 */     final AtomicReference<Type> result = new AtomicReference<Type>();
/* 185 */     (new TypeVisitor() {
/*     */         void visitTypeVariable(TypeVariable<?> t) {
/* 187 */           result.set(Types.subtypeOfComponentType(t.getBounds()));
/*     */         }
/*     */         
/*     */         void visitWildcardType(WildcardType t) {
/* 190 */           result.set(Types.subtypeOfComponentType(t.getUpperBounds()));
/*     */         }
/*     */         
/*     */         void visitGenericArrayType(GenericArrayType t) {
/* 193 */           result.set(t.getGenericComponentType());
/*     */         }
/*     */         
/*     */         void visitClass(Class<?> t) {
/* 196 */           result.set(t.getComponentType());
/*     */         }
/*     */       }).visit(new Type[] { type });
/* 199 */     return result.get();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Type subtypeOfComponentType(Type[] bounds) {
/* 207 */     for (Type bound : bounds) {
/* 208 */       Type componentType = getComponentType(bound);
/* 209 */       if (componentType != null) {
/* 212 */         if (componentType instanceof Class) {
/* 213 */           Class<?> componentClass = (Class)componentType;
/* 214 */           if (componentClass.isPrimitive())
/* 215 */             return componentClass; 
/*     */         } 
/* 218 */         return subtypeOf(componentType);
/*     */       } 
/*     */     } 
/* 221 */     return null;
/*     */   }
/*     */   
/*     */   private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
/*     */     private final Type componentType;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     GenericArrayTypeImpl(Type componentType) {
/* 230 */       this.componentType = Types.JavaVersion.CURRENT.usedInGenericType(componentType);
/*     */     }
/*     */     
/*     */     public Type getGenericComponentType() {
/* 234 */       return this.componentType;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 238 */       return Types.toString(this.componentType) + "[]";
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 242 */       return this.componentType.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 246 */       if (obj instanceof GenericArrayType) {
/* 247 */         GenericArrayType that = (GenericArrayType)obj;
/* 248 */         return Objects.equal(getGenericComponentType(), that.getGenericComponentType());
/*     */       } 
/* 251 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
/*     */     private final Type ownerType;
/*     */     
/*     */     private final ImmutableList<Type> argumentsList;
/*     */     
/*     */     private final Class<?> rawType;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     ParameterizedTypeImpl(@Nullable Type ownerType, Class<?> rawType, Type[] typeArguments) {
/* 266 */       Preconditions.checkNotNull(rawType);
/* 267 */       Preconditions.checkArgument((typeArguments.length == (rawType.getTypeParameters()).length));
/* 268 */       Types.disallowPrimitiveType(typeArguments, "type parameter");
/* 269 */       this.ownerType = ownerType;
/* 270 */       this.rawType = rawType;
/* 271 */       this.argumentsList = Types.JavaVersion.CURRENT.usedInGenericType(typeArguments);
/*     */     }
/*     */     
/*     */     public Type[] getActualTypeArguments() {
/* 275 */       return Types.toArray((Collection<Type>)this.argumentsList);
/*     */     }
/*     */     
/*     */     public Type getRawType() {
/* 279 */       return this.rawType;
/*     */     }
/*     */     
/*     */     public Type getOwnerType() {
/* 283 */       return this.ownerType;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 287 */       StringBuilder builder = new StringBuilder();
/* 288 */       if (this.ownerType != null)
/* 289 */         builder.append(Types.toString(this.ownerType)).append('.'); 
/* 291 */       builder.append(this.rawType.getName()).append('<').append(Types.COMMA_JOINER.join(Iterables.transform((Iterable)this.argumentsList, Types.TYPE_TO_STRING))).append('>');
/* 295 */       return builder.toString();
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 299 */       return ((this.ownerType == null) ? 0 : this.ownerType.hashCode()) ^ this.argumentsList.hashCode() ^ this.rawType.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 304 */       if (!(other instanceof ParameterizedType))
/* 305 */         return false; 
/* 307 */       ParameterizedType that = (ParameterizedType)other;
/* 308 */       return (getRawType().equals(that.getRawType()) && Objects.equal(getOwnerType(), that.getOwnerType()) && Arrays.equals((Object[])getActualTypeArguments(), (Object[])that.getActualTypeArguments()));
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class TypeVariableImpl<D extends GenericDeclaration> implements TypeVariable<D> {
/*     */     private final D genericDeclaration;
/*     */     
/*     */     private final String name;
/*     */     
/*     */     private final ImmutableList<Type> bounds;
/*     */     
/*     */     TypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
/* 325 */       Types.disallowPrimitiveType(bounds, "bound for type variable");
/* 326 */       this.genericDeclaration = (D)Preconditions.checkNotNull(genericDeclaration);
/* 327 */       this.name = (String)Preconditions.checkNotNull(name);
/* 328 */       this.bounds = ImmutableList.copyOf((Object[])bounds);
/*     */     }
/*     */     
/*     */     public Type[] getBounds() {
/* 332 */       return Types.toArray((Collection<Type>)this.bounds);
/*     */     }
/*     */     
/*     */     public D getGenericDeclaration() {
/* 336 */       return this.genericDeclaration;
/*     */     }
/*     */     
/*     */     public String getName() {
/* 340 */       return this.name;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 344 */       return this.name;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 348 */       return this.genericDeclaration.hashCode() ^ this.name.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 352 */       if (Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
/* 354 */         if (obj instanceof TypeVariableImpl) {
/* 355 */           TypeVariableImpl<?> that = (TypeVariableImpl)obj;
/* 356 */           return (this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()) && this.bounds.equals(that.bounds));
/*     */         } 
/* 360 */         return false;
/*     */       } 
/* 363 */       if (obj instanceof TypeVariable) {
/* 364 */         TypeVariable<?> that = (TypeVariable)obj;
/* 365 */         return (this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()));
/*     */       } 
/* 368 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class WildcardTypeImpl implements WildcardType, Serializable {
/*     */     private final ImmutableList<Type> lowerBounds;
/*     */     
/*     */     private final ImmutableList<Type> upperBounds;
/*     */     
/*     */     private static final long serialVersionUID = 0L;
/*     */     
/*     */     WildcardTypeImpl(Type[] lowerBounds, Type[] upperBounds) {
/* 379 */       Types.disallowPrimitiveType(lowerBounds, "lower bound for wildcard");
/* 380 */       Types.disallowPrimitiveType(upperBounds, "upper bound for wildcard");
/* 381 */       this.lowerBounds = Types.JavaVersion.CURRENT.usedInGenericType(lowerBounds);
/* 382 */       this.upperBounds = Types.JavaVersion.CURRENT.usedInGenericType(upperBounds);
/*     */     }
/*     */     
/*     */     public Type[] getLowerBounds() {
/* 386 */       return Types.toArray((Collection<Type>)this.lowerBounds);
/*     */     }
/*     */     
/*     */     public Type[] getUpperBounds() {
/* 390 */       return Types.toArray((Collection<Type>)this.upperBounds);
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 394 */       if (obj instanceof WildcardType) {
/* 395 */         WildcardType that = (WildcardType)obj;
/* 396 */         return (this.lowerBounds.equals(Arrays.asList(that.getLowerBounds())) && this.upperBounds.equals(Arrays.asList(that.getUpperBounds())));
/*     */       } 
/* 399 */       return false;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 403 */       return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 407 */       StringBuilder builder = new StringBuilder("?");
/* 408 */       for (Type lowerBound : this.lowerBounds)
/* 409 */         builder.append(" super ").append(Types.toString(lowerBound)); 
/* 411 */       for (Type upperBound : Types.filterUpperBounds((Iterable<Type>)this.upperBounds))
/* 412 */         builder.append(" extends ").append(Types.toString(upperBound)); 
/* 414 */       return builder.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   private static Type[] toArray(Collection<Type> types) {
/* 421 */     return types.<Type>toArray(new Type[types.size()]);
/*     */   }
/*     */   
/*     */   private static Iterable<Type> filterUpperBounds(Iterable<Type> bounds) {
/* 425 */     return Iterables.filter(bounds, Predicates.not(Predicates.equalTo(Object.class)));
/*     */   }
/*     */   
/*     */   private static void disallowPrimitiveType(Type[] types, String usedAs) {
/* 430 */     for (Type type : types) {
/* 431 */       if (type instanceof Class) {
/* 432 */         Class<?> cls = (Class)type;
/* 433 */         Preconditions.checkArgument(!cls.isPrimitive(), "Primitive type '%s' used as %s", new Object[] { cls, usedAs });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static Class<?> getArrayClass(Class<?> componentType) {
/* 444 */     return Array.newInstance(componentType, 0).getClass();
/*     */   }
/*     */   
/*     */   enum JavaVersion {
/* 450 */     JAVA6 {
/*     */       GenericArrayType newArrayType(Type componentType) {
/* 452 */         return new Types.GenericArrayTypeImpl(componentType);
/*     */       }
/*     */       
/*     */       Type usedInGenericType(Type type) {
/* 455 */         Preconditions.checkNotNull(type);
/* 456 */         if (type instanceof Class) {
/* 457 */           Class<?> cls = (Class)type;
/* 458 */           if (cls.isArray())
/* 459 */             return new Types.GenericArrayTypeImpl(cls.getComponentType()); 
/*     */         } 
/* 462 */         return type;
/*     */       }
/*     */     },
/* 465 */     JAVA7 {
/*     */       Type newArrayType(Type componentType) {
/* 467 */         if (componentType instanceof Class)
/* 468 */           return Types.getArrayClass((Class)componentType); 
/* 470 */         return new Types.GenericArrayTypeImpl(componentType);
/*     */       }
/*     */       
/*     */       Type usedInGenericType(Type type) {
/* 474 */         return (Type)Preconditions.checkNotNull(type);
/*     */       }
/*     */     };
/*     */     
/* 479 */     static final JavaVersion CURRENT = ((new TypeCapture<int[]>() {
/*     */       
/* 479 */       }).capture() instanceof Class) ? JAVA7 : JAVA6;
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     final ImmutableList<Type> usedInGenericType(Type[] types) {
/* 486 */       ImmutableList.Builder<Type> builder = ImmutableList.builder();
/* 487 */       for (Type type : types)
/* 488 */         builder.add(usedInGenericType(type)); 
/* 490 */       return builder.build();
/*     */     }
/*     */     
/*     */     abstract Type newArrayType(Type param1Type);
/*     */     
/*     */     abstract Type usedInGenericType(Type param1Type);
/*     */   }
/*     */   
/*     */   static final class NativeTypeVariableEquals<X> {
/* 505 */     static final boolean NATIVE_TYPE_VARIABLE_ONLY = !NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(NativeTypeVariableEquals.class, "X", new Type[0]));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\reflect\Types.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */