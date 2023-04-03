/*    */ package org.geotools.styling;
/*    */ 
/*    */ import org.opengis.style.GraphicalSymbol;
/*    */ 
/*    */ public interface Symbol extends GraphicalSymbol {
/* 36 */   public static final Symbol[] SYMBOLS_EMPTY = new Symbol[0];
/*    */   
/*    */   void accept(StyleVisitor paramStyleVisitor);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */