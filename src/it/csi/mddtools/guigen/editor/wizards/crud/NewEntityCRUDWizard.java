package it.csi.mddtools.guigen.editor.wizards.crud;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;

import it.csi.mddtools.guigen.presentation.GuigenEditorPlugin;
import it.csi.mddtools.guigen.presentation.GUIGENGenerateModelWizard.GuigenModelWizard.GuigenModelWizardNewFileCreationPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;

import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

import it.csi.mddtools.guigen.editor.wizards.crud.WizardHelper;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "mpe". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class NewEntityCRUDWizard extends Wizard implements INewWizard {
	
	private WizardInfo info;
	
	public WizardInfo getInfo() {
		return info;
	}

	public void setInfo(WizardInfo info) {
		this.info = info;
	}

//	private WizardHelper helper;
	
//	public WizardHelper getHelper() {
//		return helper;
//	}
//
//	public void setHelper(WizardHelper helper) {
//		this.helper = helper;
//	}



	private SceltaModGuiPrimaPagWizard pageSceltaModPrinc;
	private SceltaEntitaSecPagWizard pageSceltaEntita;
	private SceltaPKeyTerzaPagWizard pageSceltaPrKeyEntita;
	private SceltaFiltriEntitaQuartaPagWizard pageFiltriEntita;
	private SceltaCampiTabQuintaPagWizard pageTabEntita;
	 
	
	
	protected IStructuredSelection selection;
	
	public static final List<String> FILE_EXTENSIONS =
			Collections.unmodifiableList(Arrays.asList(GuigenEditorPlugin.INSTANCE.getString("_UI_GuigenEditorFilenameExtensions").split("\\s*,\\s*")));

	/**
	 * Constructor for SampleNewWizard.
	 */
	public NewEntityCRUDWizard() {
		super();
		setNeedsProgressMonitor(true);
		 info = new WizardInfo();
		
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		
		pageSceltaModPrinc = new SceltaModGuiPrimaPagWizard(selection, this);
		pageSceltaEntita = new SceltaEntitaSecPagWizard(selection, this);
		pageSceltaPrKeyEntita = new SceltaPKeyTerzaPagWizard(selection, this);
		pageFiltriEntita = new SceltaFiltriEntitaQuartaPagWizard(selection, this);
		pageTabEntita = new SceltaCampiTabQuintaPagWizard(selection, this);
		addPage(pageSceltaModPrinc);
		addPage(pageSceltaEntita);
		addPage(pageSceltaPrKeyEntita);
		addPage(pageFiltriEntita);
		addPage(pageTabEntita);
		
		

	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = null;//pageSceltaModPrinc.getContainerName();
		final String fileName = pageSceltaModPrinc.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					//doFinish(containerName, fileName, monitor);
					doFinish();
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */
	private void doFinish() throws CoreException {

			try {
				WizardHelper.creaAppMod(info.getNomeEntita());
				WizardHelper.creaAppDataGroup(info.getNomeEntita(), info);
				WizardHelper.creaCPRicercaByAppModule(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
//commento 10-10-2012 sera	private void doFinish(
//		String containerName,
//		String fileName,
//		IProgressMonitor monitor)
//		throws CoreException {
//		// create a sample file
//		monitor.beginTask("Creating " + fileName, 2);
//		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//		IResource resource = root.findMember(new Path(containerName));
//		if (!resource.exists() || !(resource instanceof IContainer)) {
//			throwCoreException("Container \"" + containerName + "\" does not exist.");
//		}
//		IContainer container = (IContainer) resource;
//		final IFile file = container.getFile(new Path(fileName));
//		try {
//			InputStream stream = openContentStream();
//			if (file.exists()) {
//				file.setContents(stream, true, true, monitor);
//			} else {
//				file.create(stream, true, monitor);
//			}
//			stream.close();
//		} catch (IOException e) {
//		}
//		monitor.worked(1);
//		monitor.setTaskName("Opening file for editing...");
//		getShell().getDisplay().asyncExec(new Runnable() {
//			public void run() {
//				IWorkbenchPage page =
//					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//				try {
//					IDE.openEditor(page, file, true);
//				} catch (PartInitException e) {
//				}
//			}
//		});
//		monitor.worked(1);
//	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		String contents =
			"This is the initial file contents for *.mpe file that should be word-sorted in the Preview page of the multi-page editor";
		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "guigen.editor", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;

		
	}
	
	
	
	
}