package db.vo;

// DB 변수명 = VO 속성명 = form parameter명
public class DeptVo {
	int    deptno;
	String dname;
	String loc;
	
	// get, set 필수 EL 출력할 때 속성명 -> getter 호출하기 때문에
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
