/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Frame;
/*     */ import java.awt.IllegalComponentStateException;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import java.util.Locale;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.border.Border;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ 
/*     */ public final class SwingUtilities {
/*     */   public static Component toFrame(Component owner, JComponent panel, String title, WindowListener listener) {
/*  99 */     while (owner != null) {
/* 100 */       if (owner == panel)
/* 101 */         throw new IllegalArgumentException(); 
/* 104 */       if (owner instanceof JDesktopPane) {
/* 105 */         JInternalFrame jInternalFrame = new JInternalFrame(title, true, true, true, true);
/* 106 */         jInternalFrame.setDefaultCloseOperation(2);
/* 107 */         jInternalFrame.addInternalFrameListener(InternalWindowListener.wrap(listener));
/* 108 */         ((JDesktopPane)owner).add(jInternalFrame);
/* 109 */         jInternalFrame.getContentPane().add(panel);
/* 110 */         jInternalFrame.pack();
/* 111 */         return jInternalFrame;
/*     */       } 
/* 113 */       if (owner instanceof Frame) {
/* 114 */         JDialog dialog = new JDialog((Frame)owner, title);
/* 115 */         dialog.setDefaultCloseOperation(2);
/* 116 */         dialog.addWindowListener(listener);
/* 117 */         dialog.getContentPane().add(panel);
/* 118 */         dialog.pack();
/* 119 */         return dialog;
/*     */       } 
/* 121 */       if (owner instanceof Dialog) {
/* 122 */         JDialog dialog = new JDialog((Dialog)owner, title);
/* 123 */         dialog.setDefaultCloseOperation(2);
/* 124 */         dialog.addWindowListener(listener);
/* 125 */         dialog.getContentPane().add(panel);
/* 126 */         dialog.pack();
/* 127 */         return dialog;
/*     */       } 
/* 129 */       owner = owner.getParent();
/*     */     } 
/* 135 */     JFrame frame = new JFrame(title);
/* 136 */     frame.setDefaultCloseOperation(2);
/* 137 */     frame.addWindowListener(listener);
/* 138 */     frame.getContentPane().add(panel);
/* 139 */     frame.pack();
/* 140 */     return frame;
/*     */   }
/*     */   
/*     */   public static void setTitle(Component component, String title) {
/* 147 */     while (component != null) {
/* 148 */       if (component instanceof JInternalFrame)
/* 149 */         ((JInternalFrame)component).setTitle(title); 
/* 151 */       if (component instanceof Frame) {
/* 152 */         ((Frame)component).setTitle(title);
/*     */         return;
/*     */       } 
/* 155 */       if (component instanceof Dialog) {
/* 156 */         ((Dialog)component).setTitle(title);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean showOptionDialog(Component owner, Object dialog, String title) {
/* 175 */     return showOptionDialog(owner, dialog, title, null);
/*     */   }
/*     */   
/*     */   public static boolean showOptionDialog(final Component owner, final Object dialog, final String title, final ActionListener reset) {
/*     */     int choice;
/* 197 */     if (!EventQueue.isDispatchThread()) {
/* 198 */       final boolean[] result = new boolean[1];
/* 199 */       invokeAndWait(new Runnable() {
/*     */             public void run() {
/* 201 */               result[0] = SwingUtilities.showOptionDialog(owner, dialog, title, reset);
/*     */             }
/*     */           });
/* 204 */       return result[0];
/*     */     } 
/* 207 */     Object[] options = null;
/* 208 */     Object initialValue = null;
/* 209 */     int okChoice = 0;
/* 210 */     if (reset != null) {
/*     */       JButton button;
/* 211 */       Vocabulary resources = Vocabulary.getResources((owner != null) ? owner.getLocale() : null);
/* 213 */       if (reset instanceof Action) {
/* 214 */         button = new JButton((Action)reset);
/*     */       } else {
/* 216 */         button = new JButton(resources.getString(182));
/* 217 */         button.addActionListener(reset);
/*     */       } 
/* 219 */       options = new Object[] { resources.getString(155), resources.getString(13), button };
/* 224 */       initialValue = options[okChoice = 0];
/*     */     } 
/* 229 */     if (JOptionPane.getDesktopPaneForComponent(owner) != null) {
/* 230 */       choice = JOptionPane.showInternalOptionDialog(owner, dialog, title, 2, -1, null, options, initialValue);
/*     */     } else {
/* 240 */       choice = JOptionPane.showOptionDialog(owner, dialog, title, 2, -1, null, options, initialValue);
/*     */     } 
/* 250 */     return (choice == okChoice);
/*     */   }
/*     */   
/*     */   public static void showMessageDialog(final Component owner, final Object message, final String title, final int type) {
/* 272 */     if (!EventQueue.isDispatchThread()) {
/* 273 */       invokeAndWait(new Runnable() {
/*     */             public void run() {
/* 275 */               SwingUtilities.showMessageDialog(owner, message, title, type);
/*     */             }
/*     */           });
/*     */       return;
/*     */     } 
/* 280 */     if (JOptionPane.getDesktopPaneForComponent(owner) != null) {
/* 281 */       JOptionPane.showInternalMessageDialog(owner, message, title, type);
/*     */     } else {
/* 287 */       JOptionPane.showMessageDialog(owner, message, title, type);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean showConfirmDialog(final Component owner, final Object message, final String title, final int type) {
/*     */     int choice;
/* 315 */     if (!EventQueue.isDispatchThread()) {
/* 316 */       final boolean[] result = new boolean[1];
/* 317 */       invokeAndWait(new Runnable() {
/*     */             public void run() {
/* 319 */               result[0] = SwingUtilities.showConfirmDialog(owner, message, title, type);
/*     */             }
/*     */           });
/* 322 */       return result[0];
/*     */     } 
/* 325 */     if (JOptionPane.getDesktopPaneForComponent(owner) != null) {
/* 326 */       choice = JOptionPane.showInternalConfirmDialog(owner, message, title, 0, type);
/*     */     } else {
/* 333 */       choice = JOptionPane.showConfirmDialog(owner, message, title, 0, type);
/*     */     } 
/* 340 */     return (choice == 0);
/*     */   }
/*     */   
/*     */   public static JComponent getMultilineLabelFor(JComponent owner, String text) {
/* 353 */     JTextArea label = new JTextArea(text);
/* 354 */     Dimension size = owner.getPreferredSize();
/* 355 */     size.height = (label.getMaximumSize()).height;
/* 356 */     label.setMaximumSize(size);
/* 357 */     label.setWrapStyleWord(true);
/* 358 */     label.setLineWrap(true);
/* 359 */     label.setEditable(false);
/* 360 */     label.setFocusable(false);
/* 361 */     label.setOpaque(false);
/* 362 */     label.setBorder((Border)null);
/* 363 */     LookAndFeel.installColorsAndFont(label, "Label.background", "Label.foreground", "Label.font");
/* 366 */     return label;
/*     */   }
/*     */   
/*     */   public static Locale getLocale(Component component) {
/* 374 */     if (component != null)
/*     */       try {
/* 375 */         return component.getLocale();
/* 376 */       } catch (IllegalComponentStateException ignore) {} 
/* 379 */     return JComponent.getDefaultLocale();
/*     */   }
/*     */   
/*     */   public static void invokeAndWait(Runnable runnable) {
/* 388 */     if (EventQueue.isDispatchThread()) {
/* 389 */       runnable.run();
/*     */     } else {
/*     */       try {
/* 392 */         EventQueue.invokeAndWait(runnable);
/* 393 */       } catch (InterruptedException exception) {
/*     */       
/* 395 */       } catch (InvocationTargetException target) {
/* 396 */         Throwable exception = target.getTargetException();
/* 397 */         if (exception instanceof RuntimeException)
/* 398 */           throw (RuntimeException)exception; 
/* 400 */         if (exception instanceof Error)
/* 401 */           throw (Error)exception; 
/* 404 */         throw new UndeclaredThrowableException(exception, exception.getLocalizedMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\SwingUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */