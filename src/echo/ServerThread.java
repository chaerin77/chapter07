package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{

	//필드  -필요하면 유연하게 넣어 줄 수 있어야 함
	private Socket socket;//자료형, 내가정한이름 여기에 전화번호저장되는곳. 생성자 정우성꺼면 여기소켓에 정우성전화번호, 생성자이효리꺼면 소켓에 이효리전화번호저장..
	//생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	//메소드 g/s

	
	//메소드 일반
	@Override
	public void run() {//부모꺼 맘에 안들어서 재정의
		
		try {
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
			
			br.close();//try catch지나면 br사라져서 close이안에넣음
			bw.close();
			socket.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}

		
	}
	

}
