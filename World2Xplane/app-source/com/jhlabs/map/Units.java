/*    */ package com.jhlabs.map;
/*    */ 
/*    */ public class Units {
/* 25 */   public static final Unit DEGREES = new DegreeUnit();
/*    */   
/* 26 */   public static final Unit RADIANS = new Unit("radian", "radians", "rad", Math.toDegrees(1.0D));
/*    */   
/* 27 */   public static final Unit ARC_MINUTES = new Unit("arc minute", "arc minutes", "min", 0.016666666666666666D);
/*    */   
/* 28 */   public static final Unit ARC_SECONDS = new Unit("arc second", "arc seconds", "sec", 2.777777777777778E-4D);
/*    */   
/* 33 */   public static final Unit KILOMETRES = new Unit("kilometre", "kilometres", "km", 1000.0D);
/*    */   
/* 34 */   public static final Unit METRES = new Unit("metre", "metres", "m", 1.0D);
/*    */   
/* 35 */   public static final Unit DECIMETRES = new Unit("decimetre", "decimetres", "dm", 0.1D);
/*    */   
/* 36 */   public static final Unit CENTIMETRES = new Unit("centimetre", "centimetres", "cm", 0.01D);
/*    */   
/* 37 */   public static final Unit MILLIMETRES = new Unit("millimetre", "millimetres", "mm", 0.001D);
/*    */   
/* 40 */   public static final Unit NAUTICAL_MILES = new Unit("nautical mile", "nautical miles", "kmi", 1852.0D);
/*    */   
/* 41 */   public static final Unit MILES = new Unit("mile", "miles", "mi", 1609.344D);
/*    */   
/* 42 */   public static final Unit CHAINS = new Unit("chain", "chains", "ch", 20.1168D);
/*    */   
/* 43 */   public static final Unit YARDS = new Unit("yard", "yards", "yd", 0.9144D);
/*    */   
/* 44 */   public static final Unit FEET = new Unit("foot", "feet", "ft", 0.3048D);
/*    */   
/* 45 */   public static final Unit INCHES = new Unit("inch", "inches", "in", 0.0254D);
/*    */   
/* 48 */   public static final Unit US_MILES = new Unit("U.S. mile", "U.S. miles", "us-mi", 1609.347218694437D);
/*    */   
/* 49 */   public static final Unit US_CHAINS = new Unit("U.S. chain", "U.S. chains", "us-ch", 20.11684023368047D);
/*    */   
/* 50 */   public static final Unit US_YARDS = new Unit("U.S. yard", "U.S. yards", "us-yd", 0.914401828803658D);
/*    */   
/* 51 */   public static final Unit US_FEET = new Unit("U.S. foot", "U.S. feet", "us-ft", 0.304800609601219D);
/*    */   
/* 52 */   public static final Unit US_INCHES = new Unit("U.S. inch", "U.S. inches", "us-in", 0.025400050800101603D);
/*    */   
/* 55 */   public static final Unit FATHOMS = new Unit("fathom", "fathoms", "fath", 1.8288D);
/*    */   
/* 56 */   public static final Unit LINKS = new Unit("link", "links", "link", 0.201168D);
/*    */   
/* 58 */   public static final Unit POINTS = new Unit("point", "points", "point", 3.5145980351459805E-4D);
/*    */   
/* 60 */   public static Unit[] units = new Unit[] { 
/* 60 */       DEGREES, KILOMETRES, METRES, DECIMETRES, CENTIMETRES, MILLIMETRES, MILES, YARDS, FEET, INCHES, 
/* 60 */       US_MILES, US_YARDS, US_FEET, US_INCHES, NAUTICAL_MILES };
/*    */   
/*    */   public static Unit findUnits(String name) {
/* 69 */     for (int i = 0; i < units.length; i++) {
/* 70 */       if (name.equals((units[i]).name) || name.equals((units[i]).plural) || name.equals((units[i]).abbreviation))
/* 71 */         return units[i]; 
/*    */     } 
/* 73 */     return METRES;
/*    */   }
/*    */   
/*    */   public static double convert(double value, Unit from, Unit to) {
/* 77 */     if (from == to)
/* 78 */       return value; 
/* 79 */     return to.fromBase(from.toBase(value));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\Units.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */