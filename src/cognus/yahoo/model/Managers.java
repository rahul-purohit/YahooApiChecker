package cognus.yahoo.model;

public class Managers {
	public static final String KEY_MANAGERS = "managers";
	public static final String KEY_MANAGER = "manager";
	public final static String KEY_COUNT = "count";
	private User user;
	private String is_commissioner;
	private String nickname;
	private String is_current_login;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIs_commissioner() {
		return is_commissioner;
	}

	public void setIs_commissioner(String is_commissioner) {
		this.is_commissioner = is_commissioner;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIs_current_login() {
		return is_current_login;
	}

	public void setIs_current_login(String is_current_login) {
		this.is_current_login = is_current_login;
	}

}
