/*     */ package org.geotools.metadata.sql;
/*     */ 
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.sql.SQLException;
/*     */ 
/*     */ final class MetadataEntity implements InvocationHandler {
/*     */   private final String identifier;
/*     */   
/*     */   private final MetadataSource source;
/*     */   
/*     */   public MetadataEntity(String identifier, MetadataSource source) {
/*  65 */     this.identifier = identifier;
/*  66 */     this.source = source;
/*     */   }
/*     */   
/*     */   public Object invoke(Object proxy, Method method, Object[] args) {
/*  80 */     Class<?> type = method.getDeclaringClass();
/*  81 */     this.source.getClass();
/*  81 */     if (type.getName().startsWith("org.opengis.metadata.")) {
/*  82 */       if (args != null && args.length != 0)
/*  83 */         throw new MetadataException("Unexpected argument."); 
/*     */       try {
/*  91 */         return this.source.getValue(type, method, this.identifier);
/*  92 */       } catch (SQLException e) {
/*  93 */         throw new MetadataException("Failed to query the database.", e);
/*     */       } 
/*     */     } 
/*     */     try {
/* 105 */       return method.invoke(this, args);
/* 106 */     } catch (IllegalAccessException e) {
/* 107 */       throw new MetadataException("Illegal method call.", e);
/* 108 */     } catch (InvocationTargetException e) {
/* 109 */       throw new MetadataException("Illegal method call.", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\sql\MetadataEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */