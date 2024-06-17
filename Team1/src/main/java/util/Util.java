package util;

public class Util {
	
//	public final static String base_url = "http://localhost:8080/miniproject1";
		
	// 입력된 문자를 MD5형식의 해시 코드로 변환, 보안성 높이는 용도
    // MD5형식은 보안이 다른 것보다 취약하나 미니프로젝트이기때문에 암호화했다는 것만 담기 위해 사용
    // 대체로 보안성 좋은 SHA3-?을 사용하거나 랜덤으로 salt를 붙여 사용하거나 pepper를 사용하거나 bcrypt,argon2를 사용가능(뒤로 갈수록 보안성 높음) 
	public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
	
    // HTML 이스케이프 처리
    public static String escapeHtml(String input) {
        if (input == null) return null;
        
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
    }

}
