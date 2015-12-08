/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
	
	public static void main(String argv[]) throws Exception {
	
		String sentence;
		
		String modifiedSentence;
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		CriptografiaTCP cript_tcp = new CriptografiaTCP();
		
		while(true){
		
			//Abre conexão com destino: local e porta: 6789
			@SuppressWarnings("resource")
			Socket clientSocket = new Socket("localhost", 6789);
			
			System.out.println("Digite algo para enviar ao servidor TCP: ");				
			
			//Lê entrada do usuário.
			sentence = inFromUser.readLine();
			
			//Criptografa a string sentence
			String sentence_cript = cript_tcp.encrypt(sentence);
			System.out.println(sentence+" Criptografado: "+sentence_cript);
			
			//Cria canal de comunicação com o servidor
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			//Enviar a mensagem criptografada ao servidor.
			outToServer.writeBytes(sentence_cript + '\n');
			
			//Lê resposta do servidor.
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			
			modifiedSentence = inFromServer.readLine();
			
			//Descriptografa a mensagem recebida do servidor
			String modifiedSentenceDecript = cript_tcp.decrypt(modifiedSentence);
			
			System.out.println("Recebido do servidor TCP: " + modifiedSentenceDecript);
			
			//clientSocket.close();
		}
	}
}
