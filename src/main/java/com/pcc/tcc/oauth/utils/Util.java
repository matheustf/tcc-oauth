package com.pcc.tcc.oauth.utils;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.pcc.tcc.oauth.consts.Constants;
import com.pcc.tcc.oauth.exception.OAuthException;

public class Util {
	public static boolean validar(String cep) throws OAuthException {
		if (StringUtils.isBlank(cep) || "00000000".equals(cep) || cep.contains("-") || !cep.matches("\\d{8}")) {
			throw new OAuthException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND);
		}
		return true;
	}

	public static String gerarCodigo(int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(0, qtdCaracteres);
	}

	public static String gerarCodigo(String inicio, int qtdCaracteres) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return inicio + "-" + myRandom.substring(0, qtdCaracteres);
	}

	public static String retirarPrefixo(String codigo) {
		String[] partes = codigo.split("-");

		return partes[1];
	}

	public static String dataNow() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(new Locale("pt", "br"));
		return agora.format(formatador); // 08/04/14 10:02

	}

	public static String criptografarMD5(String senhaOriginal) throws OAuthException {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

			byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();

		} catch (Exception e) {
			throw new OAuthException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.SERVER_ERROR);
		}
	}

}
