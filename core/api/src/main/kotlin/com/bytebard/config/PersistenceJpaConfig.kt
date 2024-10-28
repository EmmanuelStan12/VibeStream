package com.bytebard.config

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource
import kotlin.time.Duration.Companion.minutes


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.bytebard.repository"])
@PropertySource("classpath:persistence.properties")
class PersistenceJpaConfig {

    private companion object {
        private const val DATABASE_URL = "database.url"
        private const val DATABASE_URL_PARAM = "database.url.parameter"
        private const val DATABASE_USE_SSL = "database.use.ssl"
        private const val DATABASE_DRIVER = "database.driver"
        private const val DATABASE_USERNAME = "database.username"
        private const val DATABASE_PASSWORD = "database.password"
        private const val DATABASE_DATASOURCE = "database.datasource"
        private const val HIBERNATE_DIALECT = "hibernate.dialect"
        private const val HIBERNATE_DDL_AUTO = "hibernate.hbm2ddl.auto"
        private const val HIBERNATE_SHOW_SQL = "hibernate.show_sql"
        private const val MAX_POOL_SIZE = 20
        private val MAX_LIFE_TIME = 10.minutes
    }

    @Bean
    fun dataSource(env: Environment): DataSource {
        return HikariDataSource().apply {
            jdbcUrl = "${env[DATABASE_URL]}"
            username = env.getProperty(DATABASE_USERNAME)
            password = env.getProperty(DATABASE_PASSWORD)
            driverClassName = env.getProperty(DATABASE_DRIVER)
            maximumPoolSize = MAX_POOL_SIZE
            maxLifetime = MAX_LIFE_TIME.inWholeMilliseconds
        }
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource, env: Environment): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.setPackagesToScan("com.bytebard.entity") // Adjust to your entity package
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        em.setJpaProperties(hibernateProperties(env))
        return em
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(emf)
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    private fun hibernateProperties(env: Environment): Properties {
        return Properties().apply {
            put(HIBERNATE_DIALECT, env[HIBERNATE_DIALECT])
            put(HIBERNATE_DDL_AUTO, env[HIBERNATE_DDL_AUTO])
            put(HIBERNATE_SHOW_SQL, env[HIBERNATE_SHOW_SQL])
        }
    }

    @Bean
    fun propertySourcesPlaceholderConfigurer(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}