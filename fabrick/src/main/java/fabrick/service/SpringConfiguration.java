package fabrick.service;

import org.springframework.context.annotation.Bean;
import java.io.Serializable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import fabrick.service.impl.FabrickServiceBean;
@Configuration
public class SpringConfiguration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6722861037087609269L;
	public SpringConfiguration() {
		super();
	}
	@Bean
	public FabrickServiceBean fabrickServiceBean() {
		FabrickServiceBean fabrickServiceBean= new FabrickServiceBean();
		return fabrickServiceBean;
		
	
	}
	@Bean
	public FabrickService fabrickService(FabrickServiceBean fabrickServiceBean,RestTemplate restTemplate) {
		FabrickService fabrickService=new FabrickService();
		fabrickService.setFabrickServiceBean(fabrickServiceBean);
		fabrickServiceBean.setRestTemplate(restTemplate);
		return fabrickService;
	
	}
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}
}
