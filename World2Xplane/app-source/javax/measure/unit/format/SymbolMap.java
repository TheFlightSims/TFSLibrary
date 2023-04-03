/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.unit.Unit;
/*     */ 
/*     */ public class SymbolMap {
/*  75 */   private Map<String, Unit<?>> _symbolToUnit = new HashMap<String, Unit<?>>();
/*     */   
/*  76 */   private Map<Unit<?>, String> _unitToSymbol = new HashMap<Unit<?>, String>();
/*     */   
/*  77 */   private Map<String, Object> _symbolToPrefix = new HashMap<String, Object>();
/*     */   
/*  78 */   private Map<Object, String> _prefixToSymbol = new HashMap<Object, String>();
/*     */   
/*  79 */   private Map<UnitConverter, Prefix> _converterToPrefix = new HashMap<UnitConverter, Prefix>();
/*     */   
/*     */   public SymbolMap() {}
/*     */   
/*     */   public SymbolMap(ResourceBundle rb) {
/*  88 */     this();
/*  89 */     for (Enumeration<String> i = rb.getKeys(); i.hasMoreElements(); ) {
/*  90 */       String fqn = i.nextElement();
/*  91 */       String symbol = rb.getString(fqn);
/*  92 */       boolean isAlias = false;
/*  93 */       int lastDot = fqn.lastIndexOf('.');
/*  94 */       String className = fqn.substring(0, lastDot);
/*  95 */       String fieldName = fqn.substring(lastDot + 1, fqn.length());
/*  96 */       if (Character.isDigit(fieldName.charAt(0))) {
/*  97 */         isAlias = true;
/*  98 */         fqn = className;
/*  99 */         lastDot = fqn.lastIndexOf('.');
/* 100 */         className = fqn.substring(0, lastDot);
/* 101 */         fieldName = fqn.substring(lastDot + 1, fqn.length());
/*     */       } 
/*     */       try {
/* 104 */         Class<?> c = Class.forName(className);
/* 105 */         Field field = c.getField(fieldName);
/* 106 */         Object value = field.get(null);
/* 107 */         if (value instanceof Unit) {
/* 108 */           if (isAlias) {
/* 109 */             alias((Unit)value, symbol);
/*     */             continue;
/*     */           } 
/* 111 */           label((Unit)value, symbol);
/*     */           continue;
/*     */         } 
/* 113 */         if (value instanceof Prefix) {
/* 114 */           label((Prefix)value, symbol);
/*     */           continue;
/*     */         } 
/* 116 */         throw new ClassCastException("unable to cast " + value + " to Unit or Prefix");
/* 118 */       } catch (Exception e) {
/* 119 */         System.err.println("ERROR reading Unit names: " + e.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void label(Unit<?> unit, String symbol) {
/* 134 */     this._symbolToUnit.put(symbol, unit);
/* 135 */     this._unitToSymbol.put(unit, symbol);
/*     */   }
/*     */   
/*     */   public void alias(Unit<?> unit, String symbol) {
/* 152 */     this._symbolToUnit.put(symbol, unit);
/*     */   }
/*     */   
/*     */   public void label(Prefix prefix, String symbol) {
/* 162 */     this._symbolToPrefix.put(symbol, prefix);
/* 163 */     this._prefixToSymbol.put(prefix, symbol);
/* 164 */     this._converterToPrefix.put(prefix.getConverter(), prefix);
/*     */   }
/*     */   
/*     */   public Unit<?> getUnit(String symbol) {
/* 174 */     return this._symbolToUnit.get(symbol);
/*     */   }
/*     */   
/*     */   public String getSymbol(Unit<?> unit) {
/* 184 */     return this._unitToSymbol.get(unit);
/*     */   }
/*     */   
/*     */   public Prefix getPrefix(String symbol) {
/* 194 */     for (Iterator<String> i = this._symbolToPrefix.keySet().iterator(); i.hasNext(); ) {
/* 195 */       String pfSymbol = i.next();
/* 196 */       if (symbol.startsWith(pfSymbol))
/* 197 */         return (Prefix)this._symbolToPrefix.get(pfSymbol); 
/*     */     } 
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public Prefix getPrefix(UnitConverter converter) {
/* 210 */     return this._converterToPrefix.get(converter);
/*     */   }
/*     */   
/*     */   public String getSymbol(Prefix prefix) {
/* 220 */     return this._prefixToSymbol.get(prefix);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\SymbolMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */