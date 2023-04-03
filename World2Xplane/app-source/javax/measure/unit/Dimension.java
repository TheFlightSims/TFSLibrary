/*     */ package javax.measure.unit;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.measure.converter.RationalConverter;
/*     */ import javax.measure.converter.UnitConverter;
/*     */ import javax.measure.quantity.Quantity;
/*     */ 
/*     */ public final class Dimension implements Serializable {
/*  35 */   private static Model CurrentModel = Model.STANDARD;
/*     */   
/*  40 */   public static final Dimension NONE = new Dimension(Unit.ONE);
/*     */   
/*  45 */   public static final Dimension LENGTH = new Dimension('L');
/*     */   
/*  50 */   public static final Dimension MASS = new Dimension('M');
/*     */   
/*  55 */   public static final Dimension TIME = new Dimension('T');
/*     */   
/*  60 */   public static final Dimension ELECTRIC_CURRENT = new Dimension('I');
/*     */   
/*  65 */   public static final Dimension TEMPERATURE = new Dimension('θ');
/*     */   
/*  70 */   public static final Dimension AMOUNT_OF_SUBSTANCE = new Dimension('N');
/*     */   
/*     */   private final Unit<?> _pseudoUnit;
/*     */   
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public Dimension(char symbol) {
/*  83 */     this._pseudoUnit = new BaseUnit("[" + symbol + "]");
/*     */   }
/*     */   
/*     */   private Dimension(Unit<?> pseudoUnit) {
/*  93 */     this._pseudoUnit = pseudoUnit;
/*     */   }
/*     */   
/*     */   public final Dimension times(Dimension that) {
/* 103 */     return new Dimension(this._pseudoUnit.times(that._pseudoUnit));
/*     */   }
/*     */   
/*     */   public final Dimension divide(Dimension that) {
/* 113 */     return new Dimension(this._pseudoUnit.divide(that._pseudoUnit));
/*     */   }
/*     */   
/*     */   public final Dimension pow(int n) {
/* 123 */     return new Dimension(this._pseudoUnit.pow(n));
/*     */   }
/*     */   
/*     */   public final Dimension root(int n) {
/* 134 */     return new Dimension(this._pseudoUnit.root(n));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 143 */     return this._pseudoUnit.toString();
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/* 154 */     if (this == that)
/* 155 */       return true; 
/* 156 */     return (that instanceof Dimension && this._pseudoUnit.equals(((Dimension)that)._pseudoUnit));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 166 */     return this._pseudoUnit.hashCode();
/*     */   }
/*     */   
/*     */   public static void setModel(Model model) {
/* 175 */     CurrentModel = model;
/*     */   }
/*     */   
/*     */   public static Model getModel() {
/* 185 */     return CurrentModel;
/*     */   }
/*     */   
/*     */   public static interface Model {
/* 219 */     public static final Model STANDARD = new Model() {
/*     */         public Dimension getDimension(BaseUnit<?> unit) {
/* 222 */           if (unit.equals(SI.METRE))
/* 222 */             return Dimension.LENGTH; 
/* 223 */           if (unit.equals(SI.KILOGRAM))
/* 223 */             return Dimension.MASS; 
/* 224 */           if (unit.equals(SI.KELVIN))
/* 224 */             return Dimension.TEMPERATURE; 
/* 225 */           if (unit.equals(SI.SECOND))
/* 225 */             return Dimension.TIME; 
/* 226 */           if (unit.equals(SI.AMPERE))
/* 226 */             return Dimension.ELECTRIC_CURRENT; 
/* 227 */           if (unit.equals(SI.MOLE))
/* 227 */             return Dimension.AMOUNT_OF_SUBSTANCE; 
/* 228 */           if (unit.equals(SI.CANDELA))
/* 228 */             return SI.WATT.getDimension(); 
/* 229 */           return new Dimension(new BaseUnit<Quantity>("[" + unit.getSymbol() + "]"));
/*     */         }
/*     */         
/*     */         public UnitConverter getTransform(BaseUnit<?> unit) {
/* 233 */           if (unit.equals(SI.CANDELA))
/* 233 */             return (UnitConverter)new RationalConverter(1L, 683L); 
/* 234 */           return UnitConverter.IDENTITY;
/*     */         }
/*     */       };
/*     */     
/*     */     Dimension getDimension(BaseUnit<?> param1BaseUnit);
/*     */     
/*     */     UnitConverter getTransform(BaseUnit<?> param1BaseUnit);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\Dimension.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */