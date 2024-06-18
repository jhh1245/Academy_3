package action;

public class PersonVo {
	String name;
	int age;
	String addr;
	
	public PersonVo() {
		
	}
	
	public PersonVo(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	// el에서 쓰기 위해서 get, set은 필수이다.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	} 
	

	
	
}
