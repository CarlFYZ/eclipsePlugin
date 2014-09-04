package popupmenu.popup.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewAction implements IObjectActionDelegate {

	private Shell shell;
	private IPath projectName;
	private boolean isProject = false;
	
	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		

		String path  = root.getLocation() + projectName.toOSString();
		MessageDialog.openInformation(
			shell,
			"Popupmenu Plug-in",
			"New Action was executed." + path);
        
		try {
            
    	    // run the Unix "ps -ef" command
			File dir = new File(path);
                //Process p = Runtime.getRuntime().exec("cd " + path + ";  cvs -q diff -l -R");
			Process p = Runtime.getRuntime().exec("cvs -q diff -l -R", null, dir);
                BufferedReader stdInput = new BufferedReader(new 
                     InputStreamReader(p.getInputStream()));

                BufferedReader stdError = new BufferedReader(new 
                     InputStreamReader(p.getErrorStream()));

                // read the output from the command
                String s = null;
                System.out.println("Here is the standard output of the command:\n");
                while (( s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }
                
                // read any errors from the attempted command

                System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }
		}
		catch (Exception e)
		{

            System.out.println("=====" + e);
		}

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
	        if (selection instanceof IStructuredSelection) {
	        	List list = ((IStructuredSelection) selection).toList();
	        	if (list!= null && list.size() > 0 )
	        	{
	        		Object object = list.get(0);
	        		if ( object instanceof IProject )
	        		{
	        			projectName  = ((IProject) object).getFullPath();
//	    	        	MessageDialog.openInformation(shell,
//	    	        			"Popupmenu Plug-in",
//	    	        			"Selection executed: " + ((IProject) object).getFullPath());
	    	        	isProject  = true;
	    	        	return;
	        		}
	        	}
	        	
	            //doSomething(((IStructuredSelection) selection).toList());


	        }
	        isProject  = false;

	}

}
