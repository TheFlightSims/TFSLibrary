/*     */ package org.jfree.ui.action;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.KeyStroke;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public class ActionButton extends JButton {
/*     */   private Action action;
/*     */   
/*     */   private ActionEnablePropertyChangeHandler propertyChangeHandler;
/*     */   
/*     */   private class ActionEnablePropertyChangeHandler implements PropertyChangeListener {
/*     */     private final ActionButton this$0;
/*     */     
/*     */     public ActionEnablePropertyChangeHandler(ActionButton this$0) {
/*  85 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent event) {
/*     */       try {
/*  95 */         if (event.getPropertyName().equals("enabled")) {
/*  96 */           this.this$0.setEnabled(this.this$0.getAction().isEnabled());
/*  98 */         } else if (event.getPropertyName().equals("SmallIcon")) {
/*  99 */           this.this$0.setIcon((Icon)this.this$0.getAction().getValue("SmallIcon"));
/* 101 */         } else if (event.getPropertyName().equals("Name")) {
/* 102 */           this.this$0.setText((String)this.this$0.getAction().getValue("Name"));
/* 105 */         } else if (event.getPropertyName().equals("ShortDescription")) {
/* 106 */           this.this$0.setToolTipText((String)this.this$0.getAction().getValue("ShortDescription"));
/*     */         } 
/* 110 */         Action ac = this.this$0.getAction();
/* 111 */         if (event.getPropertyName().equals("AcceleratorKey")) {
/* 112 */           KeyStroke oldVal = (KeyStroke)event.getOldValue();
/* 113 */           if (oldVal != null)
/* 114 */             this.this$0.unregisterKeyboardAction(oldVal); 
/* 117 */           Object o = ac.getValue("AcceleratorKey");
/* 118 */           if (o instanceof KeyStroke) {
/* 119 */             KeyStroke k = (KeyStroke)o;
/* 120 */             this.this$0.registerKeyboardAction(ac, k, 2);
/*     */           } 
/* 123 */         } else if (event.getPropertyName().equals("MnemonicKey")) {
/* 124 */           Object o = ac.getValue("MnemonicKey");
/* 125 */           if (o != null)
/* 126 */             if (o instanceof Character) {
/* 127 */               Character c = (Character)o;
/* 128 */               this.this$0.setMnemonic(c.charValue());
/* 130 */             } else if (o instanceof Integer) {
/* 131 */               Integer c = (Integer)o;
/* 132 */               this.this$0.setMnemonic(c.intValue());
/*     */             }  
/*     */         } 
/* 137 */       } catch (Exception e) {
/* 138 */         Log.warn("Error on PropertyChange in ActionButton: ", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ActionButton() {}
/*     */   
/*     */   public ActionButton(String text) {
/* 156 */     super(text);
/*     */   }
/*     */   
/*     */   public ActionButton(String text, Icon icon) {
/* 166 */     super(text, icon);
/*     */   }
/*     */   
/*     */   public ActionButton(Icon icon) {
/* 176 */     super(icon);
/*     */   }
/*     */   
/*     */   public ActionButton(Action action) {
/* 185 */     setAction(action);
/*     */   }
/*     */   
/*     */   public Action getAction() {
/* 194 */     return this.action;
/*     */   }
/*     */   
/*     */   private ActionEnablePropertyChangeHandler getPropertyChangeHandler() {
/* 205 */     if (this.propertyChangeHandler == null)
/* 206 */       this.propertyChangeHandler = new ActionEnablePropertyChangeHandler(this); 
/* 208 */     return this.propertyChangeHandler;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean b) {
/* 218 */     super.setEnabled(b);
/* 219 */     if (getAction() != null)
/* 220 */       getAction().setEnabled(b); 
/*     */   }
/*     */   
/*     */   public void setAction(Action newAction) {
/* 238 */     Action oldAction = getAction();
/* 239 */     if (oldAction != null) {
/* 240 */       removeActionListener(oldAction);
/* 241 */       oldAction.removePropertyChangeListener(getPropertyChangeHandler());
/* 243 */       Object o = oldAction.getValue("AcceleratorKey");
/* 244 */       if (o instanceof KeyStroke) {
/* 245 */         KeyStroke k = (KeyStroke)o;
/* 246 */         unregisterKeyboardAction(k);
/*     */       } 
/*     */     } 
/* 249 */     this.action = newAction;
/* 250 */     if (this.action != null) {
/* 251 */       addActionListener(newAction);
/* 252 */       newAction.addPropertyChangeListener(getPropertyChangeHandler());
/* 254 */       setText((String)newAction.getValue("Name"));
/* 255 */       setToolTipText((String)newAction.getValue("ShortDescription"));
/* 256 */       setIcon((Icon)newAction.getValue("SmallIcon"));
/* 257 */       setEnabled(this.action.isEnabled());
/* 259 */       Object o = newAction.getValue("MnemonicKey");
/* 260 */       if (o != null)
/* 261 */         if (o instanceof Character) {
/* 262 */           Character c = (Character)o;
/* 263 */           setMnemonic(c.charValue());
/* 265 */         } else if (o instanceof Integer) {
/* 266 */           Integer c = (Integer)o;
/* 267 */           setMnemonic(c.intValue());
/*     */         }  
/* 270 */       o = newAction.getValue("AcceleratorKey");
/* 271 */       if (o instanceof KeyStroke) {
/* 272 */         KeyStroke k = (KeyStroke)o;
/* 273 */         registerKeyboardAction(newAction, k, 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\action\ActionButton.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */