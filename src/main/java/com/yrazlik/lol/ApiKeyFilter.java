package com.yrazlik.lol;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yrazlik.lol.util.PlatformConstants;
import com.yrazlik.lol.util.UrlUtil;

public class ApiKeyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String language = req.getHeader(PlatformConstants.HEADER_LANGUAGE);
        if(!UrlUtil.API_KEY_ENABLED) {
        		throw new RuntimeException(getMessageByLanguage(language));
        } else {
        		chain.doFilter(request, response);
        }
	}
	
	private String getMessageByLanguage(String language) {
		if(language != null) {
			if(language.startsWith("tr")) {
				return "Bu özellik birkaç hafta içinde kullanıma sunulacak. Anlayışınız için teşekkürler.";
			} else if(language.startsWith("es")) {
				return "Esta función estará disponible en unas semanas. Gracias por su paciencia.";
			} else if(language.startsWith("pt")) {
				return "Este recurso estará disponível em algumas semanas. Obrigado pela sua paciência.";
			}
		}
		return "This feature will be available in a few weeks. Thank you for your patience.";
	}

}
