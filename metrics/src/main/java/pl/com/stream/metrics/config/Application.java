package pl.com.stream.metrics.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.socket.WebSocketHandler;

import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.websocket.DefaultEchoService;
import pl.com.stream.metrics.websocket.EchoWebSocketHandler;

@EnableAutoConfiguration
@Configuration
@EnableAsync
@EnableScheduling
@EnableJpaRepositories(basePackages = "pl.com.stream.metrics.repo")
@ComponentScan(basePackages = "pl.com.stream.metrics")
public class Application extends SpringBootServletInitializer {
	// implements
	// AsyncConfigurer, SchedulingConfigurer

	public static void main(String[] args) throws Throwable {
		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(Application.class, args);
		applicationContext.getBean(AccountRepository.class).findAll();
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean(name = "/echo")
	public WebSocketHandler echoWebSocketHandler() {
		return new EchoWebSocketHandler(new DefaultEchoService("as"));
	}

	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(H2).build();
	}

	@Bean
	@Profile("prod")
	public DataSource dataSourceProd() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.postgresql.Driver.class);
		dataSource.setUsername("metric");
		dataSource.setPassword("metric");
		dataSource.setUrl("jdbc:postgresql://192.168.72.169:5432/metric");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setPersistenceUnitName("app");
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("pl.com.stream.metrics.model");
		return lef;
	}

	@Bean
	@Profile("dev")
	public JpaVendorAdapter h2JPAAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.H2);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	@Profile("prod")
	public JpaVendorAdapter postgresqlJPAAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

}
