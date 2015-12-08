/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	
	private static ServerSocket welcomeSocket;

	public static void main(String argv[]) throws Exception {
	
		CriptografiaTCP cript_tcp = new CriptografiaTCP();
		
		String clientSentence;
		@SuppressWarnings("unused")
		String capitalizedSentence;
		
		welcomeSocket = new ServerSocket(6789);
		
		while (true) {
		
			System.out.println("Servidor TCP ouvindo...");
			
			//Aceitando conexões de clientes.
			Socket connectionSocket = welcomeSocket.accept();
			
			//Lendo dados recebidos.
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
			//Abrindo canal de comunicação para escrita no socket.
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			//Recebe mensagem digitada pelo cliente
			clientSentence = inFromClient.readLine();

			System.out.println("Recebido do Cliente (Criptografdo): " + clientSentence);
			
			//descriptografa a mensagem recebida
			String clientSentenceDecript = cript_tcp.decrypt(clientSentence);
			
			System.out.println("Recebido do Cliente (Descriptografado): " + clientSentenceDecript);
			
			//A resposta será a mesma mensagem, porém captalizada e criptografada.
			capitalizedSentence = cript_tcp.encrypt(clientSentenceDecript.toUpperCase()) + '\n';								 				
			
			//Envia para o cliente
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}



