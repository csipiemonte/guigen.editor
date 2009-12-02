package it.csi.mddtools.guigen.presentation.GUIModelNormalizerWizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (guigen).
 */

public class GUIModelNormalizerWizardPage extends WizardPage {
	private Text commonFilesContainerText;
	private String commonFilesContainerName;

	private Text modelFileText;
	private String modelFileName;
	
	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public GUIModelNormalizerWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Selezione percorsi");
		setDescription("Selezionare il file del modello GUIModel e la directory contenente i file comuni "+
				"(commonTNS.guigen, commonAppdata.guigen)");
		this.selection = selection;
		System.out.println("SEL:"+selection.toString());
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = null;
		
		/// nome del modello guigen
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");
		modelFileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		modelFileText.setLayoutData(gd);
		modelFileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleModelFileBrowse();
			}
		});
		
		/// folder dei common files
		label = new Label(container, SWT.NULL);
		label.setText("&Folder contenente le risorse comuni:");

		commonFilesContainerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		commonFilesContainerText.setLayoutData(gd);
		commonFilesContainerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleCommonContainerBrowse();
			}
		});
		
//		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
//		gd = new GridData(GridData.FILL_HORIZONTAL);
//		fileText.setLayoutData(gd);
//		fileText.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				dialogChanged();
//			}
//		});
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				modelFileText.setText(((IResource)obj).getFullPath().toString());
				modelFileName=modelFileText.getText();
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				commonFilesContainerText.setText(container.getFullPath().toString());
				commonFilesContainerName=commonFilesContainerText.getText();
			}
		}
		//modelFileText.setText("mymodel.guigen");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleCommonContainerBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				commonFilesContainerText.setText(((Path) result[0]).toString());
			}
		}
	}

	
	private void handleModelFileBrowse() {
//		org.eclipse.swt.widgets.FileDialog dialog = new org.eclipse.swt.widgets.FileDialog(
//				getShell());
		ResourceSelectionDialog dialog = new ResourceSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), "Scegliere file del modello");
		if (dialog.open() == ResourceSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length >0 && result[0] instanceof IFile) {
				String modelFileSelected = ((IFile) result[0]).getFullPath().toString(); 
				modelFileText.setText(modelFileSelected);
			}
		}
		//modelFileText.setText(dialog.open());
	}
	
	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
//		IResource container = ResourcesPlugin.getWorkspace().getRoot()
//				.findMember(new Path(getCommonFilesContainerName()));
		modelFileName=modelFileText.getText();
		commonFilesContainerName = commonFilesContainerText.getText();
		IResource commonFolder = getCommonFilesContainer();
		String fileName = getModelFileName();

		/// verifiche sul folder dei common files
		if (getCommonFilesContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (commonFolder == null
				|| (commonFolder.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		IResource commonTNSRes = ResourcesPlugin.getWorkspace().getRoot()
			.findMember(new Path(getCommonFilesContainerName()+"/"+"commonTNS.guigen"));
		IResource commonAppdataRes = ResourcesPlugin.getWorkspace().getRoot()
		.findMember(new Path(getCommonFilesContainerName()+"/"+"commonAppdata.guigen"));
		
		if (commonTNSRes==null || commonAppdataRes==null || !commonTNSRes.exists() || !commonAppdataRes.exists()){
			updateStatus("La cartella specificata deve contenere i file [commonTNS.guigen] e [commonAppdata.guigen]");
			return;
		}
//		if (!commonFolder.isAccessible()) {
//			updateStatus("Project must be writable");
//			return;
//		}
		
		//// controlli sul model file
		if (fileName.length() == 0) {
			updateStatus("Occorre specificare il percorso del file che contiene il modello GUIModel, che dovrà essere normalizzato");
			return;
		}
		if (!fileName.endsWith(".guigen")){
			updateStatus("Il file che contiene il modello da normalizzare deve avere l'estensione 'guigen'");
			return;
		}
		IResource modelFile = ResourcesPlugin.getWorkspace().getRoot()
			.findMember(new Path(getModelFileName()));
		if (!modelFile.exists()){
			updateStatus("Il file che contiene il modello da normalizzare deve esistere");
			return;
		}
		
//		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
//			updateStatus("File name must be valid");
//			return;
//		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("guigen") == false) {
				updateStatus("File extension must be \"guigen\"");
				return;
			}
		}
		
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getCommonFilesContainerName() {
		if (commonFilesContainerName==null)
			return "";
		
		if (commonFilesContainerName.endsWith("/"))
			return commonFilesContainerName.substring(0, commonFilesContainerName.length()-2);
		else
			return commonFilesContainerName;
	}

	public IContainer getCommonFilesContainer() {
		return (IContainer)ResourcesPlugin.getWorkspace().getRoot()
		.findMember(new Path(getCommonFilesContainerName()));
	}
	
	public String getModelFileName() {
		return modelFileName;
	}
}