/**
 * 
 */
package main.utils;

/**
 * @author ddiaz
 *
 */
public class Version {
	
	
	private String versionCode;	
	private String dateVersion;
	
	private static final String VERSION = "0.0.1-SNAPSHOT";
	private static final String DATE = "19/11/2015";
	
	public Version() {
		super();
		this.versionCode = VERSION;
		this.dateVersion = DATE;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(String dateVersion) {
		this.dateVersion = dateVersion;
	}
	
	
	
	
	
	

}
