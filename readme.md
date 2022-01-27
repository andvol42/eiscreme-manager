Meet the Minds Day Eiscreme-Manager

Installationnsanleitung

Über Docker Compose

- Im Projekt-Root befindet sich eine docker-compose.yml, über die sich das Backend und Frontend einfach starten lässt
- Vor dem Ausführen der compose muss das Backend allerdings über den Maven Wrapper nochmal gebaut werden

1. Execute-Berechtigungen an den Maven Wrapper vergeben, im Project-Root:
         
         chmod +x mvnw
2. Backend bauen: 

         ./mvnw clean package
3. compose ausführen
   
         docker-compose up

Nach dem Start sollte die Applikation unter localhost:80 erreichbar sein

Plan B (falls compose schief läuft) - startup-Skript

- Wurde auf OS X und der Git-Bash getestet 
- JDK-Version muss größer 8 sein, getestet mit 11 und 15 
- Node-Version muss größer 12 sein, getestet mit 14 und 16


1. Repo klonen
2. Execute-Rechte auf das startup und cleanup-Skript und den maven wrapper vergeben: 

        cd eiscreme-manager 
   
        chmod -R +x startup 
   
        chmod +x mvnw
3. Ins startup-Verzeichnis wechseln und startup-Shell-Skript ausführen:

        cd startup
        ./startup.sh

    Warten bis die Anwendung vollständig gebaut und hochgefahren wird, am Ende sollte Browser mit der Erfassungsmaske aufgehen
   

4. Nach dem Beenden das cleanup-Skript ausführen, damit der Backend-Prozess beendet wird 
    
        ./cleanup.sh
