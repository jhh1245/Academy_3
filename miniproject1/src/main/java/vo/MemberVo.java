package vo;

public class MemberVo {
	private int m_idx;
	private String m_name;
	private String m_id;
	private String m_pw;
	private String m_email;
	private String m_intro;
	private String m_rdate;
	private String m_mdate;
	private int m_type;
	
	public int getM_idx() {
		return m_idx;
	}
	
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	
	public String getM_name() {
		return m_name;
	}
	
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public String getM_id() {
		return m_id;
	}
	
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	
	public String getM_pw() {
		return m_pw;
	}
	
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	
	public String getM_email() {
		return m_email;
	}
	
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	
	public String getM_intro() {
		return m_intro;
	}
	
	public void setM_intro(String m_intro) {
		this.m_intro = m_intro;
	}
	
	public String getM_rdate() {
		return m_rdate;
	}
	
	public void setM_rdate(String m_rdate) {
		this.m_rdate = m_rdate;
	}
	
	public String getM_mdate() {
		return m_mdate;
	}
	
	public void setM_mdate(String m_mdate) {
		this.m_mdate = m_mdate;
	}
	
	public int getM_type() {
		return m_type;
	}

	public void setM_type(int m_type) {
		this.m_type = m_type;
	}

	
	public MemberVo() {
	}

	public MemberVo(int m_idx, String m_name, String m_id, String m_pw, String m_email, String m_intro, String m_rdate,
			String m_mdate, int m_type) {
		super();
		this.m_idx = m_idx;
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_email = m_email;
		this.m_intro = m_intro;
		this.m_rdate = m_rdate;
		this.m_mdate = m_mdate;
		this.m_type = m_type;
	}

	public MemberVo(String m_name, String m_id, String m_pw, String m_email, String m_intro, int m_type) {
		super();
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_email = m_email;
		this.m_intro = m_intro;
		this.m_type = m_type;
	}

	public MemberVo(int m_idx, String m_name, String m_email, String m_intro) {
		super();
		this.m_idx = m_idx;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_intro = m_intro;
	}
	
	
}
