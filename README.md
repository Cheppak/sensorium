# Resolución punto 2 | Atix Labs

## Requisitos
Contar con la siguiente configuración:
* java 8
* maven 3
* git
* cliente rest a gusto (opcional)

## Instalación & ejecución
1) Clonamos el repositorio "git clone https://github.com/Cheppak/sensorium.git"
2) Dirigirnos a la carpeta root del proyecto, donde se encuentra el archivo pom.xml, y ejecutar "mvn clean package -DskipTests". Se generará la carpeta target con el jar del proyecto.
3) Ejecutar el jar. "java -jar target/sensorium-0.0.1-SNAPSHOT.jar [M] [S]"
M y S son numeros números enteros positivos. De todas formas estan hechas las validaciones pertinentes.

## Sensores http
Es posible ingresar un valor al monitor via "rest" mediante post http. Para ello:
1) Como requisito necesitamos levantar la aplicacion como asi lo indica en el apartado "Instalación y ejecución".
2) Con su cliente Rest favorito puede invocar al monitor mediante POST /sensor.
* En el header tenemos que configurar Content-type: application/json.
* Como Body tenemos que ingresar el numero que deseemos. Ej "{ 1231 }".
* El servidor escucha en el puerto 8080.
