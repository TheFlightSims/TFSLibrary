/*     */ package org.opengis.util;
/*     */ 
/*     */ import java.io.InvalidObjectException;
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.opengis.annotation.UML;
/*     */ 
/*     */ public abstract class CodeList<E extends CodeList<E>> implements Comparable<E>, Serializable {
/*     */   private static final long serialVersionUID = 5655809691319522885L;
/*     */   
/*  48 */   private static final Map<Class<? extends CodeList>, Collection<? extends CodeList>> VALUES = new HashMap<Class<? extends CodeList>, Collection<? extends CodeList>>();
/*     */   
/*  55 */   private static final Class<String>[] CONSTRUCTOR_PARAMETERS = new Class[] { String.class };
/*     */   
/*     */   private final transient int ordinal;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private transient String identifier;
/*     */   
/*     */   protected CodeList(String name, Collection<E> values) {
/*  87 */     this.name = name = name.trim();
/*  88 */     synchronized (values) {
/*  89 */       this.ordinal = values.size();
/*  90 */       if (!values.add((E)this))
/*  91 */         throw new IllegalArgumentException("Duplicated value: " + name); 
/*     */     } 
/*  94 */     Class<? extends CodeList> codeType = (Class)getClass();
/*  95 */     synchronized (VALUES) {
/*  96 */       Collection<? extends CodeList> previous = (Collection<? extends CodeList>)VALUES.put(codeType, values);
/*  97 */       if (previous != null && previous != values) {
/*  98 */         VALUES.put(codeType, previous);
/*  99 */         throw new IllegalArgumentException("List already exists: " + values);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <T extends CodeList> T valueOf(Class<T> codeType, String name) {
/*     */     Collection<? extends CodeList> values;
/* 117 */     if (name == null)
/* 118 */       return null; 
/* 120 */     name = name.trim();
/* 122 */     synchronized (VALUES) {
/* 123 */       values = VALUES.get(codeType);
/* 124 */       if (values == null) {
/* 125 */         if (codeType == null)
/* 126 */           throw new IllegalArgumentException("Code type is null"); 
/* 128 */         throw new IllegalStateException("No collection of " + codeType.getSimpleName());
/*     */       } 
/*     */     } 
/* 132 */     synchronized (values) {
/* 133 */       for (CodeList code : values) {
/* 134 */         if (code.matches(name))
/* 135 */           return codeType.cast(code); 
/*     */       } 
/*     */       try {
/* 139 */         Constructor<T> constructor = codeType.getDeclaredConstructor((Class<?>[])CONSTRUCTOR_PARAMETERS);
/* 140 */         constructor.setAccessible(true);
/* 141 */         return constructor.newInstance(new Object[] { name });
/* 142 */       } catch (Exception exception) {
/* 143 */         throw new IllegalArgumentException("Can't create code of type " + codeType.getSimpleName(), exception);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final int ordinal() {
/* 155 */     return this.ordinal;
/*     */   }
/*     */   
/*     */   public String identifier() {
/* 170 */     String identifier = this.identifier;
/* 171 */     if (identifier == null) {
/*     */       Field field;
/* 172 */       Class<? extends CodeList> codeType = (Class)getClass();
/*     */       try {
/* 175 */         field = codeType.getField(this.name);
/* 176 */       } catch (NoSuchFieldException e) {
/* 179 */         field = null;
/*     */       } 
/* 181 */       if (field != null && Modifier.isStatic(field.getModifiers())) {
/*     */         Object value;
/*     */         try {
/* 184 */           value = field.get(null);
/* 185 */         } catch (IllegalAccessException e) {
/* 187 */           throw new AssertionError(e);
/*     */         } 
/* 189 */         if (equals(value)) {
/* 190 */           UML annotation = field.<UML>getAnnotation(UML.class);
/* 191 */           if (annotation != null)
/* 192 */             identifier = annotation.identifier(); 
/*     */         } 
/*     */       } 
/* 196 */       if (identifier == null)
/* 197 */         identifier = ""; 
/* 199 */       this.identifier = identifier;
/*     */     } 
/* 201 */     return (identifier.length() != 0) ? identifier : null;
/*     */   }
/*     */   
/*     */   public final String name() {
/* 210 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean matches(String name) {
/* 224 */     if (name == null)
/* 225 */       return false; 
/* 227 */     if (name.equalsIgnoreCase(this.name))
/* 228 */       return true; 
/* 230 */     String identifier = identifier();
/* 231 */     return (identifier != null && name.equalsIgnoreCase(identifier));
/*     */   }
/*     */   
/*     */   public final int compareTo(E other) {
/* 254 */     Class<? extends CodeList> ct = (Class)getClass();
/* 255 */     Class<? extends CodeList> co = (Class)other.getClass();
/* 256 */     if (!ct.equals(co))
/* 257 */       throw new ClassCastException("Can't compare " + ct.getSimpleName() + " to " + co.getSimpleName()); 
/* 259 */     return this.ordinal - ((CodeList)other).ordinal;
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/* 274 */     if (object != null && object.getClass().equals(getClass()))
/* 275 */       return (this.ordinal == ((CodeList)object).ordinal); 
/* 277 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 285 */     return getClass().getSimpleName() + '[' + this.name + ']';
/*     */   }
/*     */   
/*     */   protected Object readResolve() throws ObjectStreamException {
/*     */     Collection<? extends CodeList> values;
/* 296 */     Class<? extends CodeList> codeType = (Class)getClass();
/* 298 */     synchronized (VALUES) {
/* 299 */       values = VALUES.get(codeType);
/*     */     } 
/* 301 */     if (values != null)
/* 302 */       synchronized (values) {
/* 303 */         for (CodeList code : values) {
/* 304 */           if (!codeType.isInstance(code))
/* 308 */             return this; 
/* 310 */           if (code.matches(this.name))
/* 311 */             return code; 
/*     */         } 
/* 317 */         Collection<? extends CodeList> collection = values;
/* 318 */         if (!collection.add(this))
/* 320 */           throw new InvalidObjectException(this.name); 
/*     */       }  
/* 324 */     return this;
/*     */   }
/*     */   
/*     */   public abstract E[] family();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\CodeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */