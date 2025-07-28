package app.service;

public class EmailService {
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        // Simulação de envio
        System.out.println("Enviando email para: " + destinatario);
        System.out.println("Assunto: " + assunto);
        System.out.println("Mensagem: " + mensagem);
    }
}
