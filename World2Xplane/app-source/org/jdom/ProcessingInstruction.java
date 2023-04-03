/*     */ package org.jdom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.jdom.output.XMLOutputter;
/*     */ 
/*     */ public class ProcessingInstruction extends Content {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: ProcessingInstruction.java,v $ $Revision: 1.47 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   protected String target;
/*     */   
/*     */   protected String rawData;
/*     */   
/*     */   protected Map mapData;
/*     */   
/*     */   protected ProcessingInstruction() {}
/*     */   
/*     */   public ProcessingInstruction(String target, Map data) {
/* 104 */     setTarget(target);
/* 105 */     setData(data);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction(String target, String data) {
/* 118 */     setTarget(target);
/* 119 */     setData(data);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction setTarget(String newTarget) {
/*     */     String reason;
/* 130 */     if ((reason = Verifier.checkProcessingInstructionTarget(newTarget)) != null)
/* 132 */       throw new IllegalTargetException(newTarget, reason); 
/* 135 */     this.target = newTarget;
/* 136 */     return this;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 146 */     return this.rawData;
/*     */   }
/*     */   
/*     */   public String getTarget() {
/* 156 */     return this.target;
/*     */   }
/*     */   
/*     */   public String getData() {
/* 165 */     return this.rawData;
/*     */   }
/*     */   
/*     */   public List getPseudoAttributeNames() {
/* 176 */     Set mapDataSet = this.mapData.entrySet();
/* 177 */     List nameList = new ArrayList();
/* 178 */     for (Iterator i = mapDataSet.iterator(); i.hasNext(); ) {
/* 179 */       String wholeSet = i.next().toString();
/* 180 */       String attrName = wholeSet.substring(0, wholeSet.indexOf("="));
/* 181 */       nameList.add(attrName);
/*     */     } 
/* 183 */     return nameList;
/*     */   }
/*     */   
/*     */   public ProcessingInstruction setData(String data) {
/* 193 */     String reason = Verifier.checkProcessingInstructionData(data);
/* 194 */     if (reason != null)
/* 195 */       throw new IllegalDataException(data, reason); 
/* 198 */     this.rawData = data;
/* 199 */     this.mapData = parseData(data);
/* 200 */     return this;
/*     */   }
/*     */   
/*     */   public ProcessingInstruction setData(Map data) {
/* 213 */     String temp = toString(data);
/* 215 */     String reason = Verifier.checkProcessingInstructionData(temp);
/* 216 */     if (reason != null)
/* 217 */       throw new IllegalDataException(temp, reason); 
/* 220 */     this.rawData = temp;
/* 222 */     this.mapData = new HashMap(data);
/* 223 */     return this;
/*     */   }
/*     */   
/*     */   public String getPseudoAttributeValue(String name) {
/* 237 */     return (String)this.mapData.get(name);
/*     */   }
/*     */   
/*     */   public ProcessingInstruction setPseudoAttribute(String name, String value) {
/* 250 */     String reason = Verifier.checkProcessingInstructionData(name);
/* 251 */     if (reason != null)
/* 252 */       throw new IllegalDataException(name, reason); 
/* 255 */     reason = Verifier.checkProcessingInstructionData(value);
/* 256 */     if (reason != null)
/* 257 */       throw new IllegalDataException(value, reason); 
/* 260 */     this.mapData.put(name, value);
/* 261 */     this.rawData = toString(this.mapData);
/* 262 */     return this;
/*     */   }
/*     */   
/*     */   public boolean removePseudoAttribute(String name) {
/* 274 */     if (this.mapData.remove(name) != null) {
/* 275 */       this.rawData = toString(this.mapData);
/* 276 */       return true;
/*     */     } 
/* 279 */     return false;
/*     */   }
/*     */   
/*     */   private String toString(Map mapData) {
/* 289 */     StringBuffer rawData = new StringBuffer();
/* 291 */     Iterator i = mapData.keySet().iterator();
/* 292 */     while (i.hasNext()) {
/* 293 */       String name = i.next();
/* 294 */       String value = (String)mapData.get(name);
/* 295 */       rawData.append(name).append("=\"").append(value).append("\" ");
/*     */     } 
/* 301 */     if (rawData.length() > 0)
/* 302 */       rawData.setLength(rawData.length() - 1); 
/* 305 */     return rawData.toString();
/*     */   }
/*     */   
/*     */   private Map parseData(String rawData) {
/* 324 */     Map data = new HashMap();
/* 329 */     String inputData = rawData.trim();
/* 332 */     while (!inputData.trim().equals("")) {
/* 336 */       String name = "";
/* 337 */       String value = "";
/* 338 */       int startName = 0;
/* 339 */       char previousChar = inputData.charAt(startName);
/* 340 */       int pos = 1;
/* 341 */       for (; pos < inputData.length(); pos++) {
/* 342 */         char currentChar = inputData.charAt(pos);
/* 343 */         if (currentChar == '=') {
/* 344 */           name = inputData.substring(startName, pos).trim();
/* 347 */           int[] bounds = extractQuotedString(inputData.substring(pos + 1));
/* 350 */           if (bounds == null)
/* 351 */             return new HashMap(); 
/* 353 */           value = inputData.substring(bounds[0] + pos + 1, bounds[1] + pos + 1);
/* 355 */           pos += bounds[1] + 1;
/*     */           break;
/*     */         } 
/* 358 */         if (Character.isWhitespace(previousChar) && !Character.isWhitespace(currentChar))
/* 360 */           startName = pos; 
/* 363 */         previousChar = currentChar;
/*     */       } 
/* 367 */       inputData = inputData.substring(pos);
/* 374 */       if (name.length() > 0 && value != null)
/* 380 */         data.put(name, value); 
/*     */     } 
/* 385 */     return data;
/*     */   }
/*     */   
/*     */   private static int[] extractQuotedString(String rawData) {
/* 405 */     boolean inQuotes = false;
/* 408 */     char quoteChar = '"';
/* 412 */     int start = 0;
/* 416 */     for (int pos = 0; pos < rawData.length(); pos++) {
/* 417 */       char currentChar = rawData.charAt(pos);
/* 418 */       if (currentChar == '"' || currentChar == '\'')
/* 419 */         if (!inQuotes) {
/* 421 */           quoteChar = currentChar;
/* 422 */           inQuotes = true;
/* 423 */           start = pos + 1;
/* 425 */         } else if (quoteChar == currentChar) {
/* 427 */           inQuotes = false;
/* 428 */           return new int[] { start, pos };
/*     */         }  
/*     */     } 
/* 435 */     return null;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 449 */     return "[ProcessingInstruction: " + (new XMLOutputter()).outputString(this) + "]";
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 463 */     ProcessingInstruction pi = (ProcessingInstruction)super.clone();
/* 469 */     if (this.mapData != null)
/* 470 */       pi.mapData = parseData(this.rawData); 
/* 472 */     return pi;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\ProcessingInstruction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */