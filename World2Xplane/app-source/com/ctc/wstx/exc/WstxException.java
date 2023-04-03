/*     */ package com.ctc.wstx.exc;
/*     */ 
/*     */ import com.ctc.wstx.util.ExceptionUtil;
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public class WstxException extends XMLStreamException {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   protected final String mMsg;
/*     */   
/*     */   public WstxException(String msg) {
/*  39 */     super(msg);
/*  40 */     this.mMsg = msg;
/*     */   }
/*     */   
/*     */   public WstxException(Throwable th) {
/*  44 */     super(th.getMessage(), th);
/*  45 */     this.mMsg = th.getMessage();
/*  48 */     ExceptionUtil.setInitCause(this, th);
/*     */   }
/*     */   
/*     */   public WstxException(String msg, Location loc) {
/*  52 */     super(msg, loc);
/*  53 */     this.mMsg = msg;
/*     */   }
/*     */   
/*     */   public WstxException(String msg, Location loc, Throwable th) {
/*  57 */     super(msg, loc, th);
/*  58 */     this.mMsg = msg;
/*  61 */     ExceptionUtil.setInitCause(this, th);
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  72 */     String locMsg = getLocationDesc();
/*  77 */     if (locMsg == null)
/*  78 */       return super.getMessage(); 
/*  80 */     StringBuffer sb = new StringBuffer(this.mMsg.length() + locMsg.length() + 20);
/*  81 */     sb.append(this.mMsg);
/*  82 */     StringUtil.appendLF(sb);
/*  83 */     sb.append(" at ");
/*  84 */     sb.append(locMsg);
/*  85 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/*  90 */     return getClass().getName() + ": " + getMessage();
/*     */   }
/*     */   
/*     */   protected String getLocationDesc() {
/* 101 */     Location loc = getLocation();
/* 102 */     return (loc == null) ? null : loc.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\exc\WstxException.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */