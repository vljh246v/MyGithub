import java.net.*;
public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException{
		InetAddress addr1 = InetAddress.getByName("www.naver.co.kr");
		InetAddress addr2 = InetAddress.getByName("220.79.181.253");
		
		System.out.println("==========================");
		System.out.println("addr1 = " + addr1.getHostAddress());
		System.out.println("addr2 = " + addr2.getHostName());
	}

}
