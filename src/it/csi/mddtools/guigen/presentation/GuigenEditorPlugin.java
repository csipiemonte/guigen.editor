/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package it.csi.mddtools.guigen.presentation;

import it.csi.mddtools.guigen.genutils.MiscUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import mddtools.usagetracking.ProfilingPacketBuilder;
import mddtools.usagetracking.TrackingSender;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.ui.EclipseUIPlugin;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This is the central singleton for the Guigen editor plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class GuigenEditorPlugin extends EMFPlugin {
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final GuigenEditorPlugin INSTANCE = new GuigenEditorPlugin();
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GuigenEditorPlugin() {
		super
			(new ResourceLocator [] {
			});
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}
	
	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}
	
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated 
	 */
	public static class Implementation extends EclipseUIPlugin {
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated not
		 */
		public Implementation() {
			super();
	
			// Remember the static instance.
			//
			plugin = this;
			
			// track usage
			manageTracking();
		}
		
		
		
		/**
		 * @generated not
		 */
		public static void manageTracking(){
			Properties packet = mddtools.usagetracking.ProfilingPacketBuilder.packStartupInfo(getXPluginName(), getXPluginVer());
			packet.list(System.out);
			String whoName = packet.getProperty(ProfilingPacketBuilder.P_WHO_NAME);
			if (whoName == null || whoName.length()==0){
				//ask for registration
				// TODO
				System.out.println("ask for registration");
			}
			else{
				TrackingSender.sendTrackingInfo(packet);
			}
		}
		
		
		public static String getXPluginName(){
			return MiscUtils.getPluginName();
		}
	
		public static String getXPluginVer(){
			return MiscUtils.getPluginVersion();
		}
	}
	
}
