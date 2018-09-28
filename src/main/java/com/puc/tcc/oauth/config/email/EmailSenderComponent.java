package com.puc.tcc.oauth.config.email;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderComponent {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailSenderComponent(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public EmailSenderComponent() {
		// TODO Auto-generated constructor stub
	}

	public void emailBoasVindas(String nomeDoCliente, String emailCliente)
			throws MailException, MessagingException, IOException {
		StringBuilder titulo = new StringBuilder();
		titulo.append("Seja Bem Vindo(a) " + nomeDoCliente);

		Email email = Email.builder().remetente("salesstoreofficial@gmail.com").destinatario(emailCliente)
				.titulo(titulo.toString()).nomeUsuario(nomeDoCliente).build();

		javaMailSender.send(emailToSimpleMailMessage(email));
	}

	private MimeMessage emailToSimpleMailMessage(Email email) throws MessagingException, IOException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setSubject(email.getTitulo());
		helper.setTo(email.getDestinatario());
		helper.setFrom(email.getRemetente());

		String contentFile = readEmail("boasvindas.html");
		System.out.println("Content File Boas Vindas: ");
		System.out.println(contentFile);
		System.out.println("Content File Usuario: " + email.getNomeUsuario());
		contentFile = contentFile.replace("**usuario**", email.getNomeUsuario());

		helper.setText(contentFile, true);

		return message;
	}

	public String readEmail(String name) {
		try {
			String fileName = "email/" + name;
			ClassLoader classLoader = new EmailSenderComponent().getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			if(file == null) {
				System.out.println("Arquivo nao encontrado");
			}
			String contentFile = new String(Files.readAllBytes(file.toPath()));
			System.out.println("Content File Method: ");
			System.out.println(contentFile);
			return contentFile;
		} catch (IOException e) {
			System.out.println("Error file email");
			return null;
		}
	}

}
