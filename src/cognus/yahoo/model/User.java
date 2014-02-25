package cognus.yahoo.model;

public class User {
	public final static String KEY_USERS = "users";
	public final static String KEY_COUNT = "count";

	private String guid;

	public User(String guid) {
		this.setGuid("guid");
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
