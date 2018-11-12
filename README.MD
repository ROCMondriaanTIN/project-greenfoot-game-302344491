# Project Greenfoot game
Voor dit project krijg je een kleine platformer engine. Daarnaast ga je ook werken met git om je 
werk op te slaan.

Git is een version control system waarmee veel gewerkt wordt in de programmeer wereld. Het houdt
de verschillen bij tussen stukken code wat je maakt en commit. Hiermee zou je helemaal terug kunnen naar de start van het project 
mocht alles in de soep lopen. Het is hiervoor wel belanrijk de je regelmatig een commit maakt. Een commit is een soort savepoint 
waarnaar je terug zou kunnen als je iets verkeerds doet.

Zorg ervoor dat je einde van de dag je werkt goed opslaat. Dit doe je door je werk te committen
en te pushen naar de server. Zo staat het online en kan je er gemakkelijk overal op de wereld bij (mits de website van github offline is natuurlijk).  

## Git Global setup

Instellen van je config user naam en e-mail

```
git config --global user.name "Voornaam achternaam"
git config --global user.email "email@rocmondriaan.nl"
```

## Git clone
Om een repository te clonen naar je lokale PC kun je het volgende doen:

0. installeer git op https://git-scm.com/download/
1. Open een map waar je de repository naar toe wil clonen.
2. In de verkenner klik je rechter muis klik 'git bash here'.
3. type in het volgende in:
```
git clone <url van de repository>
```

ps. als je de URL niet kan kopieren via de sneltoetsen ctrl+v. probeer dan Ctrl+shift+insert.
Simpel he ;)


## Git toevoegen van files
Om in een repository files te kunnen toevoegen zijn een aantal kleine stappen nodig. Als eerste moet je een een repository lokaal op je PC hebben staan. Vervolgens kan je in die map een nieuwe file aan maken. Vervolgens kan je content toevoegen aan die file. Nu weet je computer dat je een nieuwe file hebt aangemaakt en wat daar instaat, maar `git` weet dat nog niet. We moeten nog tegen git vertellen dat we een nieuwe file hebben gemaakt en dat we die willen toevoegen. Dit kan je doen met de volgende commando:
```
git add <naam van de file + extentie>
```
Voorbeeld:
```
git add nieuwefile.txt
```
Zo nu weet git ook dat je een file heb aangemaakt. Nu moeten we een commit voorbereiden zodat dit in de change tree van git wordt toegevoegd. Dit kan met de volgende commando:
```
git commit -m"We hebben een nieuwe file toegevoegd!"
```
De `-m` in deze commando staat voor message. We geven een kleine beschrijving van wat alle veranderingen zijn. Veel commits zijn vaak kleine features die af zijn. Hierbij is het woord AF van groots belang. Je wilt niet iets commiten wat niet werkt. Dit kan ernamelijk voor zorgen dat als het op de server staat niemand van je team meer kan werken.

Zo nu staat onze file in de git tree. Om er voor te zorgen dat ook andere mensen deze file kunnen zien in de git tree moeten wij de changes/commits uploaden naar de server, dit wordt ook wel een push genoemt. Een commande van een push kan er als volgd uitzien:
```
git push origin master
```
Deze commando zorgt ervoor dat we van ons lokale origin de huidige brache de changes/commits gaan push'e naar de `master` op de server. Als er nog geen branche bestaat van de `master` op de server dan maakt hij deze aan.
