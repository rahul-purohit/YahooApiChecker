package cognus.yahoo.model;

public class Teams {
	public static final String KEY_TEAMS = "teams";
	public static final String KEY_TEAM = "team";
	public final static String KEY_COUNT = "count";

	private String team_key;
	private String team_id;
	private String name;
	private String is_owned_by_current_login;
	private String url;

	private String waiver_priority;
	private String number_of_moves;
	private String number_of_trades;
	private String roster_adds;
	private String value;
	private String coverage_value;
	private String coverage_type;
	private String managers;

	public String getTeam_key() {
		return team_key;
	}

	public void setTeam_key(String team_key) {
		this.team_key = team_key;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_owned_by_current_login() {
		return is_owned_by_current_login;
	}

	public void setIs_owned_by_current_login(String is_owned_by_current_login) {
		this.is_owned_by_current_login = is_owned_by_current_login;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWaiver_priority() {
		return waiver_priority;
	}

	public void setWaiver_priority(String waiver_priority) {
		this.waiver_priority = waiver_priority;
	}

	public String getNumber_of_moves() {
		return number_of_moves;
	}

	public void setNumber_of_moves(String number_of_moves) {
		this.number_of_moves = number_of_moves;
	}

	public String getNumber_of_trades() {
		return number_of_trades;
	}

	public void setNumber_of_trades(String number_of_trades) {
		this.number_of_trades = number_of_trades;
	}

	public String getRoster_adds() {
		return roster_adds;
	}

	public void setRoster_adds(String roster_adds) {
		this.roster_adds = roster_adds;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCoverage_value() {
		return coverage_value;
	}

	public void setCoverage_value(String coverage_value) {
		this.coverage_value = coverage_value;
	}

	public String getCoverage_type() {
		return coverage_type;
	}

	public void setCoverage_type(String coverage_type) {
		this.coverage_type = coverage_type;
	}

	public String getManagers() {
		return managers;
	}

	public void setManagers(String managers) {
		this.managers = managers;
	}

}
