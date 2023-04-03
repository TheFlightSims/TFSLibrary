/*     */ package org.jfree.ui.action;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.KeyStroke;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public class ActionMenuItem extends JMenuItem {
/*     */   private Action action;
/*     */   
/*     */   private ActionEnablePropertyChangeHandler propertyChangeHandler;
/*     */   
/*     */   private class ActionEnablePropertyChangeHandler implements PropertyChangeListener {
/*     */     private final ActionMenuItem this$0;
/*     */     
/*     */     public ActionEnablePropertyChangeHandler(ActionMenuItem this$0) {
/*  83 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent event) {
/*     */       try {
/*  95 */         if (event.getPropertyName().equals("enabled")) {
/*  97 */           this.this$0.setEnabled(this.this$0.getAction().isEnabled());
/*  99 */         } else if (event.getPropertyName().equals("SmallIcon")) {
/* 101 */           this.this$0.setIcon((Icon)this.this$0.getAction().getValue("SmallIcon"));
/* 103 */         } else if (event.getPropertyName().equals("Name")) {
/* 105 */           this.this$0.setText((String)this.this$0.getAction().getValue("Name"));
/* 107 */         } else if (event.getPropertyName().equals("ShortDescription")) {
/* 109 */           this.this$0.setToolTipText((String)this.this$0.getAction().getValue("ShortDescription"));
/*     */         } 
/* 113 */         Action ac = this.this$0.getAction();
/* 114 */         if (event.getPropertyName().equals("AcceleratorKey")) {
/* 116 */           this.this$0.setAccelerator((KeyStroke)ac.getValue("AcceleratorKey"));
/* 118 */         } else if (event.getPropertyName().equals("MnemonicKey")) {
/* 120 */           Object o = ac.getValue("MnemonicKey");
/* 121 */           if (o != null) {
/* 123 */             if (o instanceof Character) {
/* 125 */               Character c = (Character)o;
/* 126 */               this.this$0.setMnemonic(c.charValue());
/* 128 */             } else if (o instanceof Integer) {
/* 130 */               Integer c = (Integer)o;
/* 131 */               this.this$0.setMnemonic(c.intValue());
/*     */             } 
/*     */           } else {
/* 136 */             this.this$0.setMnemonic(0);
/*     */           } 
/*     */         } 
/* 140 */       } catch (Exception e) {
/* 142 */         Log.warn("Error on PropertyChange in ActionButton: ", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ActionMenuItem() {}
/*     */   
/*     */   public ActionMenuItem(Icon icon) {
/* 160 */     super(icon);
/*     */   }
/*     */   
/*     */   public ActionMenuItem(String text) {
/* 170 */     super(text);
/*     */   }
/*     */   
/*     */   public ActionMenuItem(String text, Icon icon) {
/* 181 */     super(text, icon);
/*     */   }
/*     */   
/*     */   public ActionMenuItem(String text, int i) {
/* 192 */     super(text, i);
/*     */   }
/*     */   
/*     */   public ActionMenuItem(Action action) {
/* 202 */     setAction(action);
/*     */   }
/*     */   
/*     */   public Action getAction() {
/* 212 */     return this.action;
/*     */   }
/*     */   
/*     */   private ActionEnablePropertyChangeHandler getPropertyChangeHandler() {
/* 224 */     if (this.propertyChangeHandler == null)
/* 226 */       this.propertyChangeHandler = new ActionEnablePropertyChangeHandler(this); 
/* 228 */     return this.propertyChangeHandler;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean b) {
/* 239 */     super.setEnabled(b);
/* 240 */     if (getAction() != null)
/* 242 */       getAction().setEnabled(b); 
/*     */   }
/*     */   
/*     */   public void setAction(Action newAction) {
/* 259 */     Action oldAction = getAction();
/* 260 */     if (oldAction != null) {
/* 262 */       removeActionListener(oldAction);
/* 263 */       oldAction.removePropertyChangeListener(getPropertyChangeHandler());
/* 264 */       setAccelerator((KeyStroke)null);
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
/* 278 */       if (o != null) {
/* 280 */         if (o instanceof Character) {
/* 282 */           Character c = (Character)o;
/* 283 */           setMnemonic(c.charValue());
/* 285 */         } else if (o instanceof Integer) {
/* 287 */           Integer c = (Integer)o;
/* 288 */           setMnemonic(c.intValue());
/*     */         } 
/*     */       } else {
/* 293 */         setMnemonic(0);
/*     */       } 
/* 297 */       o = newAction.getValue("AcceleratorKey");
/* 298 */       if (o instanceof KeyStroke)
/* 300 */         setAccelerator((KeyStroke)o); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\action\ActionMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */