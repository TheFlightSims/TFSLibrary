/*    */ package com.ctc.wstx.exc;
/*    */ 
/*    */ import com.ctc.wstx.util.StringUtil;
/*    */ import javax.xml.stream.Location;
/*    */ import org.codehaus.stax2.validation.XMLValidationException;
/*    */ import org.codehaus.stax2.validation.XMLValidationProblem;
/*    */ 
/*    */ public class WstxValidationException extends XMLValidationException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected WstxValidationException(XMLValidationProblem cause, String msg) {
/* 35 */     super(cause, msg);
/*    */   }
/*    */   
/*    */   protected WstxValidationException(XMLValidationProblem cause, String msg, Location loc) {
/* 41 */     super(cause, msg, loc);
/*    */   }
/*    */   
/*    */   public static WstxValidationException create(XMLValidationProblem cause) {
/* 47 */     Location loc = cause.getLocation();
/* 48 */     if (loc == null)
/* 49 */       return new WstxValidationException(cause, cause.getMessage()); 
/* 51 */     return new WstxValidationException(cause, cause.getMessage(), loc);
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 68 */     String locMsg = getLocationDesc();
/* 73 */     if (locMsg == null)
/* 74 */       return super.getMessage(); 
/* 76 */     String msg = getValidationProblem().getMessage();
/* 77 */     StringBuffer sb = new StringBuffer(msg.length() + locMsg.length() + 20);
/* 78 */     sb.append(msg);
/* 79 */     StringUtil.appendLF(sb);
/* 80 */     sb.append(" at ");
/* 81 */     sb.append(locMsg);
/* 82 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 87 */     return getClass().getName() + ": " + getMessage();
/*    */   }
/*    */   
/*    */   protected String getLocationDesc() {
/* 98 */     Location loc = getLocation();
/* 99 */     return (loc == null) ? null : loc.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxValidationException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */