/*     */ package ch.qos.logback.core.joran.spi;
/*     */ 
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.spi.ContextAwareImpl;
/*     */ import org.xml.sax.Locator;
/*     */ 
/*     */ class CAI_WithLocatorSupport extends ContextAwareImpl {
/*     */   CAI_WithLocatorSupport(Context context, Interpreter interpreter) {
/* 344 */     super(context, interpreter);
/*     */   }
/*     */   
/*     */   protected Object getOrigin() {
/* 349 */     Interpreter i = (Interpreter)super.getOrigin();
/* 350 */     Locator locator = i.locator;
/* 351 */     if (locator != null)
/* 352 */       return Interpreter.class.getName() + "@" + locator.getLineNumber() + ":" + locator.getColumnNumber(); 
/* 355 */     return Interpreter.class.getName() + "@NA:NA";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\spi\CAI_WithLocatorSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */