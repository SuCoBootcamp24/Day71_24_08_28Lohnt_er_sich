# ✅ Aufgabenstellung

Seit dem du Webentwickler bist lernst du immer neue Freunde kennen. Nun bist du aber auch ein Geschäftsmensch. Du willst also gleich sehen wie gut du an deinen neuen Freunden verdienen kannst und ob du sogar schon was für die gemacht hast.

Schreibe eine API mit der du deine Freunde verwalten kannst.

Also standard CRUD operationen für Freunde Entitäten.

Deine Freunde sollen folgende Properties haben.

```mermaid
classDiagram
	class Freund{
	Vorname
	Nachname
	Geburtstag
	Handynummer
	Email
	Beruf
	Verdienst
	IstSelbständig
	WarSchonmalKunde
	}
```

Bitte entscheide selber welche Datentypen die passenden sind.

Neben den standard Queries wollen wir noch ein paar spezifische Queries haben:

1. Nur Freunde mit einen Verdienst über xxx
2. Nur Freunde die Selbständig sind.
3. Nur Freunde die älter als xxx sind.
4. Nur Freunde die schonmal Kunde waren und über xxx Verdienen
5. Alle Freunde sortiert nach dem Verdienst

Vergiss nicht das Freundschaften nicht ewig halten, also solltest du auch Freunde entfernen können.

Genauso entwickeln sich alle Menschen weiter, schaue wie du deine Entitäten updaten kannst.