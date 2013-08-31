
package pl.com.stream.rdp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Context implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		Context.applicationContext = applicationContext;

	}

	public static <I> I getService(final Class<I> clazz) {
		return applicationContext.getBean(clazz);
	}

}
