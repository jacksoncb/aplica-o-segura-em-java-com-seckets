/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		@SuppressWarnings("resource")
		DatagramSocket clientSocket = new DatagramSocket();
		
		InetAddress IPAddress = InetAddress.getByName("localhost");			
		
		byte[] sendData = new byte[1024];
		
		byte[] receiveData = new byte[1024];
		
		while(true){
		
			System.out.println("Cliente preparado para enviar: ");
			
			//Lê entrada do usuário
			String sentence = inFromUser.readLine();
			
			//sendData = sentence.getBytes();
			sendData = CriptografiaUDP.encrypt(sentence);
			
			//Cria pacote udp
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 9876);
			
			//envia ao servidor
			clientSocket.send(sendPacket);
			
			//Recebe resposta do servidor
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			
			clientSocket.receive(receivePacket);
			
//			String modifiedSentence = new String(receivePacket.getData());
			
			String modifiedSentence = CriptografiaUDP.decrypt(receiveData);
			
			System.out.println("Recebido do servidor UDP:" + modifiedSentence);
			//Fecha conexão: clientSocket.close();
		}
	}
}
