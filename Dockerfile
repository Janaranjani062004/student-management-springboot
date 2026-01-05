FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# ✅ Give execute permission
RUN chmod +x mvnw

# ✅ Now run Maven
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/*.jar"]

