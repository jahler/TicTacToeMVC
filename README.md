# TicTacToeMVC
Eine Android-TicTacToe-App.


## Aufgabenstellung
Schreibe eine Android App, in der man Tic Tac Toe spielen kann.
Es gibt 2 Spieler, die immer abwechselnd spielen (alles lokal). Ein Spieler ist O und der andere X. Speichere dir ab, wenn ein Spieler gewonnen hat, und wie viele Spiele ein Spieler gewonnen hat. 
Das Spiel soll mit dem MVC Prinzip implementiert werden, das heißt überlege dir genau wie du das ganze Modellieren willst.


## Lösungsansatz
Zu Beginn habe ich versucht mich mit dem MVC-Modell etwas besser vertraut zu machen.
Zunächst habe ich dann versucht eine möglichst benutzerfreundliche Oberfläche (mit den gegebenen Vorgaben) zu gestalten.
Anschließend habe ich mich der Logik des Tic-Tac-Toe-Spiels gewidmet.
Für die verschiedenen Buttons habe ich dann die benötigten Listeners angelegt und die dementsprechend folgenden Aktionen ausprogrammiert.
Am Ende habe ich dann eine Datenbank angelegt, in der die Ergebnisse des Spiels gespeichert werden sollen.

## UI

### GUI
<img src="readmeDocs/ui.png" width="264" height="576">
Die Oberfläche meiner App sieht so aus.
Oben links und rechts in den Ecken wird der Spielstand ausgegeben. Mit dem Button „Reset“ in der Mitte kann man den Spielstand zurücksetzen. Dieser wird dann auch gleich in der Datenbank zurückgesetzt.
Über dem Spielfeld sieht man ein Feld, wo der Status des Spiels (sprich X/O ist dran, X/O hat gewonnen, Unentschieden) angezeigt wird.
Darunter sieht man das Spielfeld, wo man ganz gewöhnlich Tic-Tac-Toe spielen kann.
Mit dem Button „New Game“ kann man das Spielfeld zurücksetzen.
