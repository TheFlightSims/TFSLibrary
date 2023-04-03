/*    */ package javax.measure.unit.format;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import javax.measure.converter.RationalConverter;
/*    */ import javax.measure.converter.UnitConverter;
/*    */ 
/*    */ enum Prefix {
/* 41 */   YOTTA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(24), BigInteger.ONE)),
/* 42 */   ZETTA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(21), BigInteger.ONE)),
/* 43 */   EXA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(18), BigInteger.ONE)),
/* 44 */   PETA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(15), BigInteger.ONE)),
/* 45 */   TERA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(12), BigInteger.ONE)),
/* 46 */   GIGA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(9), BigInteger.ONE)),
/* 47 */   MEGA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(6), BigInteger.ONE)),
/* 48 */   KILO((UnitConverter)new RationalConverter(BigInteger.TEN.pow(3), BigInteger.ONE)),
/* 49 */   HECTO((UnitConverter)new RationalConverter(BigInteger.TEN.pow(2), BigInteger.ONE)),
/* 50 */   DEKA((UnitConverter)new RationalConverter(BigInteger.TEN.pow(1), BigInteger.ONE)),
/* 51 */   DECI((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(1))),
/* 52 */   CENTI((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(2))),
/* 53 */   MILLI((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(3))),
/* 54 */   MICRO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(6))),
/* 55 */   NANO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(9))),
/* 56 */   PICO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(12))),
/* 57 */   FEMTO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(15))),
/* 58 */   ATTO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(18))),
/* 59 */   ZEPTO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(21))),
/* 60 */   YOCTO((UnitConverter)new RationalConverter(BigInteger.ONE, BigInteger.TEN.pow(24)));
/*    */   
/*    */   private final UnitConverter _converter;
/*    */   
/*    */   Prefix(UnitConverter converter) {
/* 70 */     this._converter = converter;
/*    */   }
/*    */   
/*    */   public UnitConverter getConverter() {
/* 79 */     return this._converter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\Prefix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */