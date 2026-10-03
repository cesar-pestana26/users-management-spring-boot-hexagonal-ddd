package com.jcaa.usersmanagement.infrastructure.adapter.persistence.config;

public record DatabaseConfig(
    String type,
    String host,
    int port,
    String databaseName,
    String username,
    String password,
    String sslMode) {
  private static final String MYSQL_URL_TEMPLATE = "jdbc:mysql://%s:%d/%s?sslMode=%s&serverTimezone=UTC&allowPublicKeyRetrieval=true";
  private static final String POSTGRESQL_URL_TEMPLATE = "jdbc:postgresql://%s:%d/%s?sslmode=%s";

  public DatabaseConfig(
      final String host,
      final int port,
      final String databaseName,
      final String username,
      final String password,
      final String sslMode) {
    this("mysql", host, port, databaseName, username, password, sslMode);
  }

  public String buildJdbcUrl() {
    final String template = "postgresql".equalsIgnoreCase(type) ? POSTGRESQL_URL_TEMPLATE : MYSQL_URL_TEMPLATE;
    return String.format(template, host, port, databaseName, sslMode);
  }
}