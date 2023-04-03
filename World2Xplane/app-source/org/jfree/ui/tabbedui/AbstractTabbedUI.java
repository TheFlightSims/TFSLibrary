/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public abstract class AbstractTabbedUI extends JComponent {
/*     */   public static final String JMENUBAR_PROPERTY = "jMenuBar";
/*     */   
/*     */   public static final String GLOBAL_MENU_PROPERTY = "globalMenu";
/*     */   
/*     */   private ArrayList rootEditors;
/*     */   
/*     */   private JTabbedPane tabbedPane;
/*     */   
/*     */   private int selectedRootEditor;
/*     */   
/*     */   private JComponent currentToolbar;
/*     */   
/*     */   private JPanel toolbarContainer;
/*     */   
/*     */   private Action closeAction;
/*     */   
/*     */   private JMenuBar jMenuBar;
/*     */   
/*     */   private boolean globalMenu;
/*     */   
/*     */   protected class ExitAction extends AbstractAction {
/*     */     private final AbstractTabbedUI this$0;
/*     */     
/*     */     public ExitAction(AbstractTabbedUI this$0) {
/*  89 */       this.this$0 = this$0;
/*  90 */       putValue("Name", "Exit");
/*     */     }
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/*  99 */       this.this$0.attempExit();
/*     */     }
/*     */   }
/*     */   
/*     */   private class TabChangeHandler implements ChangeListener {
/*     */     private final JTabbedPane pane;
/*     */     
/*     */     private final AbstractTabbedUI this$0;
/*     */     
/*     */     public TabChangeHandler(AbstractTabbedUI this$0, JTabbedPane pane) {
/* 117 */       this.this$0 = this$0;
/* 118 */       this.pane = pane;
/*     */     }
/*     */     
/*     */     public void stateChanged(ChangeEvent e) {
/* 127 */       this.this$0.setSelectedEditor(this.pane.getSelectedIndex());
/*     */     }
/*     */   }
/*     */   
/*     */   private class TabEnableChangeListener implements PropertyChangeListener {
/*     */     private final AbstractTabbedUI this$0;
/*     */     
/*     */     public TabEnableChangeListener(AbstractTabbedUI this$0) {
/* 139 */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/* 149 */       if (!evt.getPropertyName().equals("enabled")) {
/* 150 */         Log.debug("PropertyName");
/*     */         return;
/*     */       } 
/* 153 */       if (!(evt.getSource() instanceof RootEditor)) {
/* 154 */         Log.debug("Source");
/*     */         return;
/*     */       } 
/* 157 */       RootEditor editor = (RootEditor)evt.getSource();
/* 158 */       this.this$0.updateRootEditorEnabled(editor);
/*     */     }
/*     */   }
/*     */   
/*     */   public AbstractTabbedUI() {
/* 183 */     this.selectedRootEditor = -1;
/* 185 */     this.toolbarContainer = new JPanel();
/* 186 */     this.toolbarContainer.setLayout(new BorderLayout());
/* 188 */     this.tabbedPane = new JTabbedPane(3);
/* 189 */     this.tabbedPane.addChangeListener(new TabChangeHandler(this, this.tabbedPane));
/* 191 */     this.rootEditors = new ArrayList();
/* 193 */     setLayout(new BorderLayout());
/* 194 */     add(this.toolbarContainer, "North");
/* 195 */     add(this.tabbedPane, "Center");
/* 197 */     this.closeAction = createCloseAction();
/*     */   }
/*     */   
/*     */   protected JTabbedPane getTabbedPane() {
/* 206 */     return this.tabbedPane;
/*     */   }
/*     */   
/*     */   public boolean isGlobalMenu() {
/* 220 */     return this.globalMenu;
/*     */   }
/*     */   
/*     */   public void setGlobalMenu(boolean globalMenu) {
/* 229 */     this.globalMenu = globalMenu;
/* 230 */     if (isGlobalMenu()) {
/* 231 */       setJMenuBar(updateGlobalMenubar());
/* 234 */     } else if (getRootEditorCount() > 0) {
/* 235 */       setJMenuBar(createEditorMenubar(getRootEditor(getSelectedEditor())));
/*     */     } 
/*     */   }
/*     */   
/*     */   public JMenuBar getJMenuBar() {
/* 246 */     return this.jMenuBar;
/*     */   }
/*     */   
/*     */   protected void setJMenuBar(JMenuBar menuBar) {
/* 255 */     JMenuBar oldMenuBar = this.jMenuBar;
/* 256 */     this.jMenuBar = menuBar;
/* 257 */     firePropertyChange("jMenuBar", oldMenuBar, menuBar);
/*     */   }
/*     */   
/*     */   protected Action createCloseAction() {
/* 266 */     return new ExitAction(this);
/*     */   }
/*     */   
/*     */   public Action getCloseAction() {
/* 275 */     return this.closeAction;
/*     */   }
/*     */   
/*     */   protected abstract JMenu[] getPrefixMenus();
/*     */   
/*     */   protected abstract JMenu[] getPostfixMenus();
/*     */   
/*     */   private void addMenus(JMenuBar menuBar, JMenu[] customMenus) {
/* 299 */     for (int i = 0; i < customMenus.length; i++)
/* 300 */       menuBar.add(customMenus[i]); 
/*     */   }
/*     */   
/*     */   private JMenuBar updateGlobalMenubar() {
/* 309 */     JMenuBar menuBar = getJMenuBar();
/* 310 */     if (menuBar == null) {
/* 311 */       menuBar = new JMenuBar();
/*     */     } else {
/* 314 */       menuBar.removeAll();
/*     */     } 
/* 317 */     addMenus(menuBar, getPrefixMenus());
/* 318 */     for (int i = 0; i < this.rootEditors.size(); i++) {
/* 320 */       RootEditor editor = this.rootEditors.get(i);
/* 321 */       addMenus(menuBar, editor.getMenus());
/*     */     } 
/* 323 */     addMenus(menuBar, getPostfixMenus());
/* 324 */     return menuBar;
/*     */   }
/*     */   
/*     */   private JMenuBar createEditorMenubar(RootEditor root) {
/* 335 */     JMenuBar menuBar = getJMenuBar();
/* 336 */     if (menuBar == null) {
/* 337 */       menuBar = new JMenuBar();
/*     */     } else {
/* 340 */       menuBar.removeAll();
/*     */     } 
/* 343 */     addMenus(menuBar, getPrefixMenus());
/* 344 */     if (isGlobalMenu()) {
/* 346 */       for (int i = 0; i < this.rootEditors.size(); i++) {
/* 348 */         RootEditor editor = this.rootEditors.get(i);
/* 349 */         addMenus(menuBar, editor.getMenus());
/*     */       } 
/*     */     } else {
/* 354 */       addMenus(menuBar, root.getMenus());
/*     */     } 
/* 356 */     addMenus(menuBar, getPostfixMenus());
/* 357 */     return menuBar;
/*     */   }
/*     */   
/*     */   public void addRootEditor(RootEditor rootPanel) {
/* 366 */     this.rootEditors.add(rootPanel);
/* 367 */     this.tabbedPane.add(rootPanel.getEditorName(), rootPanel.getMainPanel());
/* 368 */     rootPanel.addPropertyChangeListener("enabled", new TabEnableChangeListener(this));
/* 369 */     updateRootEditorEnabled(rootPanel);
/* 370 */     if (getRootEditorCount() == 1) {
/* 371 */       setSelectedEditor(0);
/* 373 */     } else if (isGlobalMenu()) {
/* 374 */       setJMenuBar(updateGlobalMenubar());
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getRootEditorCount() {
/* 384 */     return this.rootEditors.size();
/*     */   }
/*     */   
/*     */   public RootEditor getRootEditor(int pos) {
/* 395 */     return this.rootEditors.get(pos);
/*     */   }
/*     */   
/*     */   public int getSelectedEditor() {
/* 404 */     return this.selectedRootEditor;
/*     */   }
/*     */   
/*     */   public void setSelectedEditor(int selectedEditor) {
/* 413 */     int oldEditor = this.selectedRootEditor;
/* 414 */     if (oldEditor == selectedEditor)
/*     */       return; 
/* 418 */     this.selectedRootEditor = selectedEditor;
/*     */     int i;
/* 423 */     for (i = 0; i < this.rootEditors.size(); i++) {
/* 424 */       boolean shouldBeActive = (i == selectedEditor);
/* 425 */       RootEditor container = this.rootEditors.get(i);
/* 427 */       if (container.isActive() && !shouldBeActive)
/* 428 */         container.setActive(false); 
/*     */     } 
/* 432 */     if (this.currentToolbar != null) {
/* 433 */       closeToolbar();
/* 434 */       this.toolbarContainer.removeAll();
/* 435 */       this.currentToolbar = null;
/*     */     } 
/* 438 */     for (i = 0; i < this.rootEditors.size(); i++) {
/* 439 */       boolean shouldBeActive = (i == selectedEditor);
/* 440 */       RootEditor container = this.rootEditors.get(i);
/* 442 */       if (!container.isActive() && shouldBeActive == true) {
/* 443 */         container.setActive(true);
/* 444 */         setJMenuBar(createEditorMenubar(container));
/* 445 */         this.currentToolbar = container.getToolbar();
/* 446 */         if (this.currentToolbar != null) {
/* 447 */           this.toolbarContainer.add(this.currentToolbar, "Center");
/* 449 */           this.toolbarContainer.setVisible(true);
/* 450 */           this.currentToolbar.setVisible(true);
/*     */         } else {
/* 453 */           this.toolbarContainer.setVisible(false);
/*     */         } 
/* 456 */         getJMenuBar().repaint();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void closeToolbar() {
/* 465 */     if (this.currentToolbar != null) {
/* 466 */       if (this.currentToolbar.getParent() != this.toolbarContainer) {
/* 469 */         Window w = SwingUtilities.windowForComponent(this.currentToolbar);
/* 470 */         if (w != null) {
/* 471 */           w.setVisible(false);
/* 472 */           w.dispose();
/*     */         } 
/*     */       } 
/* 475 */       this.currentToolbar.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void attempExit();
/*     */   
/*     */   protected void updateRootEditorEnabled(RootEditor editor) {
/* 491 */     boolean enabled = editor.isEnabled();
/* 492 */     for (int i = 0; i < this.tabbedPane.getTabCount(); i++) {
/* 493 */       Component tab = this.tabbedPane.getComponentAt(i);
/* 494 */       if (tab == editor.getMainPanel()) {
/* 495 */         this.tabbedPane.setEnabledAt(i, enabled);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\tabbedui\AbstractTabbedUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */