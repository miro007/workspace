/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.com.stream.metrics.lib;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
public class EmFlusher implements Ordered {
	@PersistenceContext(unitName = "app")
	private EntityManager entityManager;

	@Pointcut(value = "execution(public * *(..))")
	public void anyPublicMethod() {
	}

	@Around(value = "anyPublicMethod() && @within(service)", argNames = "joinPoint,service")
	public Object logAround(ProceedingJoinPoint joinPoint, Service service)
			throws Throwable {
		Object proceed = joinPoint.proceed();
		try {
			entityManager.flush();
			entityManager.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return proceed;
	}

	@Override
	public int getOrder() {
		return 100;
	}

}
