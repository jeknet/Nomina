#Nomina
======

Generador de nomina conforme a disposiciones SAT

##Instalación.
clone https://github.com/bigdata-mx/factura-electronica.git
en el root de factura-electronica , mvn install

desde el directorio src/main/resources, ejecutar el siguiente comando
mvn install:install-file -Dfile=conector_java.jar -DgroupId=mx.comercio-digital -DartifactId=timbrado -Dversion=1.0 -Dpackaging=jar
esto instalará en nuestro repositorio local el jar de timbrado.