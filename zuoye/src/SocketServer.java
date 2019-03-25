import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		 ServerSocket server = new ServerSocket(8088);
		 String line = "";
		 String leiming = "";
		 String method = "";
		 while(true) {
			 Socket socket = server.accept();
			 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 int i=0;
			 while((line = br.readLine())!=null) {
				 if(i==0) {
					String[] str = line.split(" ");
					String s = str[1].substring(1);
					String[] s1 = s.split("-");
					leiming = s1[0];
					method = s1[1];
					System.out.println("类名:"+leiming+"\n"+"方法:"+method);
				 }
				 i++;
			 }
			 Object animal = Class.forName(leiming).newInstance();
			 Method m = animal.getClass().getDeclaredMethod(method,null);
			 m.invoke(animal, null);
			 br.close();
		 }
	}

}
