/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.ExceptionContextProvider;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class MathParseException extends MathIllegalStateException implements ExceptionContextProvider {
/*    */   private static final long serialVersionUID = -6024911025449780478L;
/*    */   
/*    */   public MathParseException(String wrong, int position, Class<?> type) {
/* 43 */     getContext().addMessage((Localizable)LocalizedFormats.CANNOT_PARSE_AS_TYPE, new Object[] { wrong, Integer.valueOf(position), type.getName() });
/*    */   }
/*    */   
/*    */   public MathParseException(String wrong, int position) {
/* 54 */     getContext().addMessage((Localizable)LocalizedFormats.CANNOT_PARSE, new Object[] { wrong, Integer.valueOf(position) });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MathParseException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */