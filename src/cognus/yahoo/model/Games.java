package cognus.yahoo.model;

public class Games {
	public static final String KEY_GAMES = "games";
	public static final String KEY_GAME = "game";
	public final static String KEY_COUNT = "count";

	private String game_key;
	private String code;
	private String session;
	private String type;
	private String name;
	private String game_id;
	private String guid;

	public Games(String guid) {
		this.setGuid(guid);
	}

	public String getGame_key() {
		return game_key;
	}

	public void setGame_key(String game_key) {
		this.game_key = game_key;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

}
