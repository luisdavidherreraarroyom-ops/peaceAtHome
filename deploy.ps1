$env:JAVA_HOME     = "C:\Program Files\Java\jdk-25.0.2"
$env:CATALINA_HOME = "C:\apache-tomcat-9.0.121"

$projectWebapp = "src\main\webapp"
$targetTomcat  = "$env:CATALINA_HOME\webapps\Peace_at_Home"
$tomcatBin     = "$env:CATALINA_HOME\bin"

Write-Host "1. Deteniendo Tomcat..." -ForegroundColor Yellow
& "$tomcatBin\shutdown.bat" | Out-Null
Start-Sleep -Seconds 2

Write-Host "2. Compilando clases Java..." -ForegroundColor Cyan
if (!(Test-Path "src\main\webapp\WEB-INF\classes")) {
    New-Item -ItemType Directory -Path "src\main\webapp\WEB-INF\classes" -Force | Out-Null
}

javac -cp "$env:CATALINA_HOME\lib\servlet-api.jar;src\main\webapp\WEB-INF\lib\*" -d "src\main\webapp\WEB-INF\classes" src\main\java\com\peaceathome\util\ConexionBD.java src\main\java\com\peaceathome\servlet\LoginServlet.java

Write-Host "3. Limpiando despliegue anterior..." -ForegroundColor Yellow
if (Test-Path $targetTomcat) { Remove-Item -Path $targetTomcat -Recurse -Force }

Write-Host "4. Copiando archivos del proyecto a Tomcat..." -ForegroundColor Cyan
Copy-Item -Path $projectWebapp -Destination $targetTomcat -Recurse -Force

Write-Host "5. Iniciando Tomcat..." -ForegroundColor Green
& "$tomcatBin\startup.bat"

Write-Host "Despliegue completado con exito." -ForegroundColor Green