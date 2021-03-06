/*******************************************************************************
 * Copyright (c) 2009, 2014 Xored Software Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Xored Software Inc - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.rcptt.ui.actions.edit;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Handles the redirection of the global Cut, Copy, Paste, and Select All
 * actions to either the current inline text control or the part's supplied
 * action handler.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * <p>
 * Example usage:
 * 
 * <pre>
 * textActionHandler = new TextActionHandler(this.getViewSite().getActionBars());
 * textActionHandler.addText((Text) textCellEditor1.getControl());
 * textActionHandler.addText((Text) textCellEditor2.getControl());
 * textActionHandler.setSelectAllAction(selectAllAction);
 * </pre>
 * 
 * </p>
 */
public class TextActionHandler extends org.eclipse.ui.actions.TextActionHandler {
	private final DeleteActionHandler textDeleteActionHandler = new DeleteActionHandler();

	private final CutActionHandler textCutAction = new CutActionHandler();

	private final CopyActionHandler textCopyAction = new CopyActionHandler();

	private final PasteActionHandler textPasteAction = new PasteActionHandler();

	private final SelectAllActionHandler textSelectAllAction = new SelectAllActionHandler();

	private IAction deleteAction;

	private IAction cutAction;

	private IAction copyAction;

	private IAction pasteAction;

	private IAction selectAllAction;

	private final IPropertyChangeListener deleteActionListener = new PropertyChangeListener(
			textDeleteActionHandler);

	private final IPropertyChangeListener cutActionListener = new PropertyChangeListener(
			textCutAction);

	private final IPropertyChangeListener copyActionListener = new PropertyChangeListener(
			textCopyAction);

	private final IPropertyChangeListener pasteActionListener = new PropertyChangeListener(
			textPasteAction);

	private final IPropertyChangeListener selectAllActionListener = new PropertyChangeListener(
			textSelectAllAction);

	private final Listener textControlListener = new TextControlListener();

	private Text activeTextControl;

	private final MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseUp(MouseEvent e) {
			updateActionsEnableState();
		}
	};

	private final KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			updateActionsEnableState();
		}
	};

	private final IActionBars actionBars;

	private class TextControlListener implements Listener {
		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.Activate:
				activeTextControl = (Text) event.widget;
				updateActionsEnableState();
				break;
			case SWT.Deactivate:
				activeTextControl = null;
				updateActionsEnableState();
				break;
			default:
				break;
			}
		}
	}

	private class PropertyChangeListener implements IPropertyChangeListener {
		private final IAction actionHandler;

		protected PropertyChangeListener(IAction actionHandler) {
			super();
			this.actionHandler = actionHandler;
		}

		public void propertyChange(PropertyChangeEvent event) {
			if (activeTextControl != null) {
				return;
			}
			if (event.getProperty().equals(IAction.ENABLED)) {
				Boolean bool = (Boolean) event.getNewValue();
				actionHandler.setEnabled(bool.booleanValue());
			}
		}
	}

	private class DeleteActionHandler extends Action {
		protected DeleteActionHandler() {
			super("Delete"); // TODO IDEWorkbenchMessages.Delete); //$NON-NLS-1$
			setId("TextDeleteActionHandler");//$NON-NLS-1$
			setEnabled(false);
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(this, "DeleteHelpId"); //$NON-NLS-1$
			// TODO IIDEHelpContextIds.TEXT_DELETE_ACTION);
		}

		@Override
		public void runWithEvent(Event event) {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				String text = activeTextControl.getText();
				Point selection = activeTextControl.getSelection();
				if (selection.y == selection.x) {
					++selection.y;
				}
				if (selection.y > text.length()) {
					return;
				}
				StringBuffer buf = new StringBuffer(text.substring(0,
						selection.x));
				buf.append(text.substring(selection.y));
				activeTextControl.setText(buf.toString());
				activeTextControl.setSelection(selection.x, selection.x);
				updateActionsEnableState();
				return;
			}
			if (deleteAction != null) {
				deleteAction.runWithEvent(event);
				return;
			}
		}

		/**
		 * Update state.
		 */
		public void updateEnabledState() {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				setEnabled(activeTextControl.getSelectionCount() > 0
						|| activeTextControl.getCaretPosition() < activeTextControl
								.getCharCount());
				return;
			}
			if (deleteAction != null) {
				setEnabled(deleteAction.isEnabled());
				return;
			}
			setEnabled(false);
		}
	}

	private class CutActionHandler extends Action {
		protected CutActionHandler() {
			super("Cut"); // TODO IDEWorkbenchMessages.Cut); //$NON-NLS-1$
			setId("TextCutActionHandler");//$NON-NLS-1$
			setEnabled(false);
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(this, "CutHelpId"); //$NON-NLS-1$
			// TODO IIDEHelpContextIds.TEXT_CUT_ACTION);
		}

		@Override
		public void runWithEvent(Event event) {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				activeTextControl.cut();
				updateActionsEnableState();
				return;
			}
			if (cutAction != null) {
				cutAction.runWithEvent(event);
				return;
			}
		}

		/**
		 * Update state.
		 */
		public void updateEnabledState() {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				setEnabled(activeTextControl.getSelectionCount() > 0);
				return;
			}
			if (cutAction != null) {
				setEnabled(cutAction.isEnabled());
				return;
			}
			setEnabled(false);
		}
	}

	private class CopyActionHandler extends Action {
		protected CopyActionHandler() {
			super("Copy"); // TODO IDEWorkbenchMessages.Copy); //$NON-NLS-1$
			setId("TextCopyActionHandler");//$NON-NLS-1$
			setEnabled(false);
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(this, "CopyHelpId"); //$NON-NLS-1$
			// TODO IIDEHelpContextIds.TEXT_COPY_ACTION);
		}

		@Override
		public void runWithEvent(Event event) {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				activeTextControl.copy();
				updateActionsEnableState();
				return;
			}
			if (copyAction != null) {
				copyAction.runWithEvent(event);
				return;
			}
		}

		/**
		 * Update the state.
		 */
		public void updateEnabledState() {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				setEnabled(activeTextControl.getSelectionCount() > 0);
				return;
			}
			if (copyAction != null) {
				setEnabled(copyAction.isEnabled());
				return;
			}
			setEnabled(false);
		}
	}

	private class PasteActionHandler extends Action {
		protected PasteActionHandler() {
			super("Paste"); // TODO IDEWorkbenchMessages.Paste); //$NON-NLS-1$
			setId("TextPasteActionHandler");//$NON-NLS-1$
			setEnabled(false);
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(this, "PasteHelpId"); //$NON-NLS-1$
			// TODO IIDEHelpContextIds.TEXT_PASTE_ACTION);
		}

		@Override
		public void runWithEvent(Event event) {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				activeTextControl.paste();
				updateActionsEnableState();
				return;
			}
			if (pasteAction != null) {
				pasteAction.runWithEvent(event);
				return;
			}
		}

		/**
		 * Update the state
		 */
		public void updateEnabledState() {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				setEnabled(true);
				return;
			}
			if (pasteAction != null) {
				setEnabled(pasteAction.isEnabled());
				return;
			}
			setEnabled(false);
		}
	}

	private class SelectAllActionHandler extends Action {
		protected SelectAllActionHandler() {
			super("Select All"); // TODO IDEWorkbenchMessages.TextAction_selectAll); //$NON-NLS-1$
			setId("TextSelectAllActionHandler");//$NON-NLS-1$
			setEnabled(false);
			PlatformUI.getWorkbench().getHelpSystem()
					.setHelp(this, "SelectAllHelpId"); //$NON-NLS-1$
			// TODO IIDEHelpContextIds.TEXT_SELECT_ALL_ACTION);
		}

		@Override
		public void runWithEvent(Event event) {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				activeTextControl.selectAll();
				updateActionsEnableState();
				return;
			}
			if (selectAllAction != null) {
				selectAllAction.runWithEvent(event);
				return;
			}
		}

		/**
		 * Update the state.
		 */
		public void updateEnabledState() {
			if (activeTextControl != null && !activeTextControl.isDisposed()) {
				setEnabled(true);
				return;
			}
			if (selectAllAction != null) {
				setEnabled(selectAllAction.isEnabled());
				return;
			}
			setEnabled(false);
		}
	}

	/**
	 * Creates a <code>Text</code> control action handler for the global Cut,
	 * Copy, Paste, Delete, and Select All of the action bar.
	 * 
	 * @param theActionBars
	 *            the action bar to register global action handlers for Cut,
	 *            Copy, Paste, Delete, and Select All
	 */
	public TextActionHandler(IActionBars theActionBars) {
		super(theActionBars);
		actionBars = theActionBars;
		updateActionBars();
	}

	/**
	 * 
	 */
	public void updateActionBars() {
		if (actionBars == null) {
			return;
		}
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(),
				textCutAction);
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(),
				textCopyAction);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
				textPasteAction);
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(),
				textSelectAllAction);
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
				textDeleteActionHandler);
	}

	/**
	 * Add a <code>Text</code> control to the handler so that the Cut, Copy,
	 * Paste, Delete, and Select All actions are redirected to it when active.
	 * 
	 * @param textControl
	 *            the inline <code>Text</code> control
	 */
	@Override
	public void addText(Text textControl) {
		if (textControl == null) {
			return;
		}

		activeTextControl = textControl;
		textControl.addListener(SWT.Activate, textControlListener);
		textControl.addListener(SWT.Deactivate, textControlListener);

		// We really want a selection listener but it is not supported so we
		// use a key listener and a mouse listener to know when selection
		// changes
		// may have occured
		textControl.addKeyListener(keyAdapter);
		textControl.addMouseListener(mouseAdapter);

	}

	/**
	 * Dispose of this action handler
	 */
	@Override
	public void dispose() {
		setCutAction(null);
		setCopyAction(null);
		setPasteAction(null);
		setSelectAllAction(null);
		setDeleteAction(null);
	}

	/**
	 * Removes a <code>Text</code> control from the handler so that the Cut,
	 * Copy, Paste, Delete, and Select All actions are no longer redirected to
	 * it when active.
	 * 
	 * @param textControl
	 *            the inline <code>Text</code> control
	 */
	@Override
	public void removeText(Text textControl) {
		if (textControl == null) {
			return;
		}

		textControl.removeListener(SWT.Activate, textControlListener);
		textControl.removeListener(SWT.Deactivate, textControlListener);

		textControl.removeMouseListener(mouseAdapter);
		textControl.removeKeyListener(keyAdapter);

		activeTextControl = null;
		updateActionsEnableState();
	}

	/**
	 * Set the default <code>IAction</code> handler for the Copy action. This
	 * <code>IAction</code> is run only if no active inline text control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Copy action, or
	 *            <code>null</code> if not interested.
	 */
	@Override
	public void setCopyAction(IAction action) {
		if (copyAction == action) {
			return;
		}

		if (copyAction != null) {
			copyAction.removePropertyChangeListener(copyActionListener);
		}

		copyAction = action;

		if (copyAction != null) {
			copyAction.addPropertyChangeListener(copyActionListener);
		}

		textCopyAction.updateEnabledState();
	}

	/**
	 * Set the default <code>IAction</code> handler for the Cut action. This
	 * <code>IAction</code> is run only if no active inline text control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Cut action, or
	 *            <code>null</code> if not interested.
	 */
	@Override
	public void setCutAction(IAction action) {
		if (cutAction == action) {
			return;
		}

		if (cutAction != null) {
			cutAction.removePropertyChangeListener(cutActionListener);
		}

		cutAction = action;

		if (cutAction != null) {
			cutAction.addPropertyChangeListener(cutActionListener);
		}

		textCutAction.updateEnabledState();
	}

	/**
	 * Set the default <code>IAction</code> handler for the Paste action. This
	 * <code>IAction</code> is run only if no active inline text control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Paste action, or
	 *            <code>null</code> if not interested.
	 */
	@Override
	public void setPasteAction(IAction action) {
		if (pasteAction == action) {
			return;
		}

		if (pasteAction != null) {
			pasteAction.removePropertyChangeListener(pasteActionListener);
		}

		pasteAction = action;

		if (pasteAction != null) {
			pasteAction.addPropertyChangeListener(pasteActionListener);
		}

		textPasteAction.updateEnabledState();
	}

	/**
	 * Set the default <code>IAction</code> handler for the Select All action.
	 * This <code>IAction</code> is run only if no active inline text control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Select All action, or
	 *            <code>null</code> if not interested.
	 */
	@Override
	public void setSelectAllAction(IAction action) {
		if (selectAllAction == action) {
			return;
		}

		if (selectAllAction != null) {
			selectAllAction
					.removePropertyChangeListener(selectAllActionListener);
		}

		selectAllAction = action;

		if (selectAllAction != null) {
			selectAllAction.addPropertyChangeListener(selectAllActionListener);
		}

		textSelectAllAction.updateEnabledState();
	}

	/**
	 * Set the default <code>IAction</code> handler for the Delete action. This
	 * <code>IAction</code> is run only if no active inline text control.
	 * 
	 * @param action
	 *            the <code>IAction</code> to run for the Delete action, or
	 *            <code>null</code> if not interested.
	 */
	@Override
	public void setDeleteAction(IAction action) {
		if (deleteAction == action) {
			return;
		}

		if (deleteAction != null) {
			deleteAction.removePropertyChangeListener(deleteActionListener);
		}

		deleteAction = action;

		if (deleteAction != null) {
			deleteAction.addPropertyChangeListener(deleteActionListener);
		}

		textDeleteActionHandler.updateEnabledState();
	}

	/**
	 * Update the enable state of the Cut, Copy, Paste, Delete, and Select All
	 * action handlers
	 */
	private void updateActionsEnableState() {
		textCutAction.updateEnabledState();
		textCopyAction.updateEnabledState();
		textPasteAction.updateEnabledState();
		textSelectAllAction.updateEnabledState();
		textDeleteActionHandler.updateEnabledState();
	}
}
