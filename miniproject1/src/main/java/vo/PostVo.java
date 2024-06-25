package vo;

import dao.PostDao;

public class PostVo {
		
		private int no;
		private int p_idx;
		private String p_cate;
		private String p_title;
		private String p_content;
		private String p_rdate;
		private String p_mdate;
		private int p_type;
		private int p_hit;
		private int m_idx;
		private String m_name;
		
		public int getLike() { // 게시글 별 좋아요 수
			int res = PostDao.getInstance().getPostLikeCount(p_idx);
			
			return res; 
		}
		
		public int getScrap() { // 게시글 별 스크랩 수
			int res = PostDao.getInstance().getPostScrapCount(p_idx);
			
			return res; 
		}
		
		
		public PostVo() {
			// TODO Auto-generated constructor stub
		}
		
		public PostVo(String p_title, String p_content, String p_cate, int p_type, int m_idx, String m_name) {
			super();
			this.p_title = p_title;
			this.p_content = p_content;
			this.p_cate = p_cate;
			this.p_type = p_type;
			this.m_idx = m_idx;
			this.setM_name(m_name);
		}

		
		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public int getP_idx() {
			return p_idx;
		}
		
		public void setP_idx(int p_idx) {
			this.p_idx = p_idx;
		}
		
		public String getP_cate() {
			return p_cate;
		}
		
		public void setP_cate(String p_cate) {
			this.p_cate = p_cate;
		}
		
		public String getP_title() {
			return p_title;
		}
		
		public void setP_title(String p_title) {
			this.p_title = p_title;
		}
		
		public String getP_content() {
			return p_content;
		}
		
		public void setP_content(String p_content) {
			this.p_content = p_content;
		}
		
		public String getP_rdate() {
			return p_rdate;
		}
		
		public void setP_rdate(String p_rdate) {
			this.p_rdate = p_rdate;
		}
		
		public String getP_mdate() {
			return p_mdate;
		}
		
		public void setP_mdate(String p_mdate) {
			this.p_mdate = p_mdate;
		}
		
		public int getP_type() {
			return p_type;
		}
		
		public void setP_type(int p_type) {
			this.p_type = p_type;
		}
		
		public int getP_hit() {
			return p_hit;
		}
		
		public void setP_hit(int p_hit) {
			this.p_hit = p_hit;
		}
		
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
		
	}

