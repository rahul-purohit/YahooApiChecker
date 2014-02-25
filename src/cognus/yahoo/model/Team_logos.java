package cognus.yahoo.model;

public class Team_logos {
	public static final String KEY_TEAM_LOGOS = "team_logos";
	public static final String KEY_TEAM_LOGO = "team_logo";
	public final static String KEY_COUNT = "count";
	
	
	
	private String team_logo;
	private String size;
	
	
	public String getTeam_logo() {
		return team_logo;
	}
	public void setTeam_logo(String team_logo) {
		this.team_logo = team_logo;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
