package com.microservice.microservicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Microservicio2 {

	public static void crearBaseDeDatosAutomaticamente() {
		String rootUrl = "jdbc:postgresql://localhost:5432/postgres";
		String targetDatabaseName = "database2";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(rootUrl, "postgres", "1234");
			statement = connection.createStatement();


			resultSet = statement
					.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + targetDatabaseName + "'");

			if (!resultSet.next()) {
				// Crear la base de datos si no existe
				statement.executeUpdate("CREATE DATABASE " + targetDatabaseName);
				System.out.println("Database " + targetDatabaseName + " creada exitosamente.");
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println("Error al crear la base de datos automaticamente: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		Microservicio2.crearBaseDeDatosAutomaticamente();
		SpringApplication.run(Microservicio2.class, args);
	}

}
