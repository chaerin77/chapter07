package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException{

		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=================================");
		
		System.out.println("[서버에 연결을 요청합니다.]");
		
		socket.connect(new InetSocketAddress("192.168.0.203",10001));
		                               //내가 접속할 서버의 ip, 포트번호
									   //집 ip172.30.1.43
	
	    System.out.println("[서버에 연결되었습니다.]");
	    
	    //메세지 보내기 스트림 1
	    OutputStream os = socket.getOutputStream();//주스트림
	    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8"); //보조스트림. 보조스트림은 섞어쓰기 잘 안함
	    BufferedWriter bw = new BufferedWriter(osw);
	    
	    //메세지 받기용 스트림2
	    InputStream is = socket.getInputStream();
	    InputStreamReader isr = new InputStreamReader(is,"UTF-8");
	    BufferedReader br = new BufferedReader(isr);
	    
	    //Scanner(키보드 입력용)
	    //Scanner sc = new Scanner(System.in);
	    
	    InputStream in = System.in;//System이라는 클래스,스태틱  in은필드
	    InputStreamReader sisr = new InputStreamReader(in,"UTF-8");
	    BufferedReader sbr = new BufferedReader(sisr);
	    
	    
	    ////반복구간 /q넣어야끝나는
	    while(true){
		    //String str = sc.nextLine();
	    	String str = sbr.readLine();
	    	if("/q".equals(str)) {//"/q"는 문자로 딱 박혀있어서 주소가없는 경우가 생기지 않을테니 아래처럼쓰지말고 "/q"의 equals로 비교해주는게 null포인트가 발생하지 않게 해준다
	    		System.out.println("종료키 입력");
	    		break;
	    	}
	    	/*
		    if(str.equals("/q")) {//str=="/q"는 주소값같은지 비교한것
	    		break;
	    	}
	        */ 
	    	 //메세지 보내기1
		    //String str = sc.nextLine();
		    bw.write(str);
		    bw.newLine();
		    bw.flush();
		    
		    //메세지 받기2
		    String reMsg = br.readLine();
		    System.out.println("server:[" + reMsg + "]");
	    
	    
	    }
	   
	    ////반복구간
	    
		System.out.println("========================");
	    //System.out.println("<클라이언트종료>");
	    OutputStream out = System.out;
	    OutputStreamWriter posr = new OutputStreamWriter(out);
	    BufferedWriter pbw = new BufferedWriter(posr);
	    		
	    pbw.write("<클라이언트종료> 스트림사용구현");
	    pbw.newLine();
	    pbw.flush();
	    
	    //sc.close();
	    bw.close();
	    socket.close();
	}

}
