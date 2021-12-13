package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("192.168.0.203",10001));//( )안에 객체 생성해서 넣어야함
		//172.30.1.43(본인컴 ip)   10001--포트번호는 개발하는입장에서 정해주는거. 숫자임
	
		System.out.println("<서버시작>");
		System.out.println("===================================");
		System.out.println("[연결을 기다리고 있습니다.]");
		//누가 오기를 기다리는중
		
		while(true) {
			Socket socket = serverSocket.accept();//누가 와서 계속 생기게됨 
			
			Thread thread = new ServerThread(socket);
			thread.start(); //하는일 출장나가고,[선보강,메세지 주고받기], 출장종료
			/* 
			if() {
				break;
			}
			*/
		}
		
		//소켓이 생기면 출장보내야해 정우성이오면 여기는 정우성의번호를 갖고있어야함 유재석은유재석으로..socket은 다른번호들을 갖게됨
		//근데 얘네가 몇번올지 몰라서 while문 만들고 탈출코드만들것임
		//소켓에게 정보를 주고 나가~~해야함
		//System.out.println("[클라이언트가 연결되었습니다.]");
		
		
		/*
		//여기 밑에있는애들은 출장(thread 상속받기)가서 출장나가는거 성공하면 세팅되어야할 일들
		//메세지 받기용 스트림1
		InputStream is = socket.getInputStream();//socket이 inputStream갖고있어서 new안해도됨
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//메세지 보내기용 스트림2
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		while(true) {
			//메세지 받기1
			String msg = br.readLine();
			
			if(msg == null) {//문자열 주소없는값이오면
				System.out.println("클라이언트 접속키 입력");
				break;
			}
			
			System.out.println("받은메세지:" +msg);
			
			//메세지 보내기2
			bw.write(msg);//bw의메소드사용
			bw.newLine();
			bw.flush();//공간이 꽉 차지 않아도 보내는것
		}
		*/
		/*
		System.out.println("========================");
		System.out.println("<서버종료>");
		socket.close();
		serverSocket.close();
		*/
	}

}
