/*     */ package org.jfree.ui.action;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.KeyStroke;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public class ActionRadioButton extends JRadioButton {
/*     */   private Action action;
/*     */   
/*     */   private ActionEnablePropertyChangeHandler propertyChangeHandler;
/*     */   
/*     */   private class ActionEnablePropertyChangeHandler implements PropertyChangeListener {
/*     */     private final ActionRadioButton this$0;
/*     */     
/*     */     private ActionEnablePropertyChangeHandler(ActionRadioButton this$0) {
/*  76 */       ActionRadioButton.this = ActionRadioButton.this;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent event) {
/*     */       try {
/*  87 */         if (event.getPropertyName().equals("enabled")) {
/*  89 */           ActionRadioButton.this.setEnabled(ActionRadioButton.this.getAction().isEnabled());
/*  91 */         } else if (event.getPropertyName().equals("SmallIcon")) {
/*  93 */           ActionRadioButton.this.setIcon((Icon)ActionRadioButton.this.getAction().getValue("SmallIcon"));
/*  95 */         } else if (event.getPropertyName().equals("Name")) {
/*  97 */           ActionRadioButton.this.setText((String)ActionRadioButton.this.getAction().getValue("Name"));
/* 100 */         } else if (event.getPropertyName().equals("ShortDescription")) {
/* 102 */           ActionRadioButton.this.setToolTipText((String)ActionRadioButton.this.getAction().getValue("ShortDescription"));
/*     */         } 
/* 106 */         Action ac = ActionRadioButton.this.getAction();
/* 107 */         if (event.getPropertyName().equals("AcceleratorKey")) {
/* 109 */           KeyStroke oldVal = (KeyStroke)event.getOldValue();
/* 110 */           if (oldVal != null)
/* 112 */             ActionRadioButton.this.unregisterKeyboardAction(oldVal); 
/* 115 */           Object o = ac.getValue("AcceleratorKey");
/* 116 */           if (o instanceof KeyStroke && o != null) {
/* 118 */             KeyStroke k = (KeyStroke)o;
/* 119 */             ActionRadioButton.this.registerKeyboardAction(ac, k, 2);
/*     */           } 
/* 122 */         } else if (event.getPropertyName().equals("MnemonicKey")) {
/* 124 */           Object o = ac.getValue("MnemonicKey");
/* 125 */           if (o != null)
/* 127 */             if (o instanceof Character) {
/* 129 */               Character c = (Character)o;
/* 130 */               ActionRadioButton.this.setMnemonic(c.charValue());
/* 132 */             } else if (o instanceof Integer) {
/* 134 */               Integer c = (Integer)o;
/* 135 */               ActionRadioButton.this.setMnemonic(c.intValue());
/*     */             }  
/*     */         } 
/* 140 */       } catch (Exception e) {
/* 142 */         Log.warn("Error on PropertyChange in ActionButton: ", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ActionRadioButton() {}
/*     */   
/*     */   public ActionRadioButton(String text) {
/* 162 */     super(text);
/*     */   }
/*     */   
/*     */   public ActionRadioButton(String text, Icon icon) {
/* 173 */     super(text, icon);
/*     */   }
/*     */   
/*     */   public ActionRadioButton(Icon icon) {
/* 184 */     super(icon);
/*     */   }
/*     */   
/*     */   public ActionRadioButton(Action action) {
/* 194 */     setAction(action);
/*     */   }
/*     */   
/*     */   public Action getAction() {
/* 204 */     return this.action;
/*     */   }
/*     */   
/*     */   private ActionEnablePropertyChangeHandler getPropertyChangeHandler() {
/* 216 */     if (this.propertyChangeHandler == null)
/* 218 */       this.propertyChangeHandler = new ActionEnablePropertyChangeHandler(); 
/* 220 */     return this.propertyChangeHandler;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean b) {
/* 231 */     super.setEnabled(b);
/* 232 */     if (getAction() != null)
/* 234 */       getAction().setEnabled(b); 
/*     */   }
/*     */   
/*     */   public void setAction(Action newAction) {
/* 253 */     Action oldAction = getAction();
/* 254 */     if (oldAction != null) {
/* 256 */       removeActionListener(oldAction);
/* 257 */       oldAction.removePropertyChangeListener(getPropertyChangeHandler());
/* 259 */       Object o = oldAction.getValue("AcceleratorKey");
/* 260 */       if (o instanceof KeyStroke && o != null) {
/* 262 */         KeyStroke k = (KeyStroke)o;
/* 263 */         unregisterKeyboardAction(k);
/*     */       } 
/*     */     } 
/* 266 */     this.action = newAction;
/* 267 */     if (this.action != null) {
/* 269 */       addActionListener(newAction);
/* 270 */       newAction.addPropertyChangeListener(getPropertyChangeHandler());
/* 272 */       setText((String)newAction.getValue("Name"));
/* 273 */       setToolTipText((String)newAction.getValue("ShortDescription"));
/* 274 */       setIcon((Icon)newAction.getValue("SmallIcon"));
/* 275 */       setEnabled(this.action.isEnabled());
/* 277 */       Object o = newAction.getValue("MnemonicKey");
/* 278 */       if (o != null)
/* 280 */         if (o instanceof Character) {
/* 282 */           Character c = (Character)o;
/* 283 */           setMnemonic(c.charValue());
/* 285 */         } else if (o instanceof Integer) {
/* 287 */           Integer c = (Integer)o;
/* 288 */           setMnemonic(c.intValue());
/*     */         }  
/* 291 */       o = newAction.getValue("AcceleratorKey");
/* 292 */       if (o instanceof KeyStroke && o != null) {
/* 294 */         KeyStroke k = (KeyStroke)o;
/* 295 */         registerKeyboardAction(newAction, k, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\action\ActionRadioButton.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */