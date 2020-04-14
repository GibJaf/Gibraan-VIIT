
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class RSAServer {

	public static void main(String[] args) throws Exception {
		System.out.println("==============Server Side(ALICE)====================");
		ServerSocket server = new ServerSocket(8088);
		System.out.println("waitinng for connection on port  " + server.getLocalPort());
		Socket soc = server.accept();
		System.out.println("Accepted connection from " + soc.getRemoteSocketAddress());
		DataInputStream dis = new DataInputStream(soc.getInputStream());
		DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

		Scanner sc = new Scanner(System.in);
		int n = dis.readInt();
		int e = dis.readInt();

		System.out.println(" Public Key of Bob(Used for encryption): ");
		System.out.println("Public key {e,n} = { " + e + ", " + n + " }");

		System.out.println("ENter Plain Text (integer) to be encypted: PT: ");
		int pt = sc.nextInt();

		BigInteger CT = BigInteger.valueOf(pt).pow(e).mod(BigInteger.valueOf(n));
		System.out.println("Generated Cipher Text CT: " + CT);

		dos.writeInt(CT.intValue());

		soc.close();
		server.close();
		sc.close();
	}
}
