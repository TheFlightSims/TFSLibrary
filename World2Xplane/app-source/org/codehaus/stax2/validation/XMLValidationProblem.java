/*     */ package org.codehaus.stax2.validation;
/*     */ 
/*     */ import javax.xml.stream.Location;
/*     */ 
/*     */ public class XMLValidationProblem {
/*     */   public static final int SEVERITY_WARNING = 1;
/*     */   
/*     */   public static final int SEVERITY_ERROR = 2;
/*     */   
/*     */   public static final int SEVERITY_FATAL = 3;
/*     */   
/*     */   protected Location mLocation;
/*     */   
/*     */   protected final String mMessage;
/*     */   
/*     */   protected final int mSeverity;
/*     */   
/*     */   protected String mType;
/*     */   
/*     */   protected XMLValidator mReporter;
/*     */   
/*     */   public XMLValidationProblem(Location loc, String msg) {
/*  42 */     this(loc, msg, 2);
/*     */   }
/*     */   
/*     */   public XMLValidationProblem(Location loc, String msg, int severity) {
/*  47 */     this(loc, msg, severity, null);
/*     */   }
/*     */   
/*     */   public XMLValidationProblem(Location loc, String msg, int severity, String type) {
/*  53 */     this.mLocation = loc;
/*  54 */     this.mMessage = msg;
/*  55 */     this.mSeverity = severity;
/*  56 */     this.mType = type;
/*     */   }
/*     */   
/*     */   public XMLValidationException toException() {
/*  71 */     return XMLValidationException.createException(this);
/*     */   }
/*     */   
/*     */   public void setType(String t) {
/*  77 */     this.mType = t;
/*     */   }
/*     */   
/*     */   public void setLocation(Location l) {
/*  82 */     this.mLocation = l;
/*     */   }
/*     */   
/*     */   public void setReporter(XMLValidator v) {
/*  89 */     this.mReporter = v;
/*     */   }
/*     */   
/*     */   public Location getLocation() {
/*  94 */     return this.mLocation;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*  99 */     return this.mMessage;
/*     */   }
/*     */   
/*     */   public int getSeverity() {
/* 105 */     return this.mSeverity;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 113 */     return this.mType;
/*     */   }
/*     */   
/*     */   public XMLValidator getReporter() {
/* 122 */     return this.mReporter;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\validation\XMLValidationProblem.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */