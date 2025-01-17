# Utilizar una imagen base de OpenJDK
FROM openjdk:11-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo WAR generado por Maven al contenedor
COPY target/jwt.war /app/jwt.war

# Exponer el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "jwt.war"]
