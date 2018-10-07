# Resolución punto 2 | Atix Labs

## Pre-requisitos
Contar con la siguiente configuración:
* java 8
* maven 3
* git

## Instalación & ejecución
1) Clonamos el repositorio git clone https://github.com/Cheppak/sensorium.git
2) Dirigirnos a la carpeta root del proyecto, donde se encuentra el archivo pom.xml, y ejecutar mvn clean package -DskipTests. Se generará la carpeta target con el jar del proyecto.
3) Ejecutar el jar. java -jar target/sensorium-0.0.1-SNAPSHOT.jar [M] [S]
M y S son numeros números enteros positivos. De todas formas estan hechas las validaciones pertinentes.
