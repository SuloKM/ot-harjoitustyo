#  Musakatalogi

Sovelluksella on tarkoitus pystyä ylläpitämään henkilökohtaista musiikkikokoelmaa.

## Dokumentaatio

[vaatimusmaarittely.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/vaatimusmaarittely.md)

[kayttoohje.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/kayttoohje.md)

[tuntikirjanpito.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/tuntikirjanpito.md)

[arkkitehtuuri.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/SuloKM/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/SuloKM/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/SuloKM/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Ohjelman käyttöönotto

### _! HUOM ! Sovelluksen kehitysvaiheessa esiintyi Cubbli-ympäristössä ajonaikaisia ongelmia SQLite-tietokantayhteydessä tietyissä hakemistoissa ajettaessa._

Projektikansio ladataan koneelle ja sijoitetaan haluttuun hakemistoon. Musakatalogi-hakemistossa (sisältää pom.xml-tiedoston) suoritetaan komento

```
mvn compile exec:java -Dexec.mainClass=katalogi.Main
```

### Suoritettava jar-paketti

Komento

```
mvn package
```
generoi target-hakemistoon ajettavan _Musakatalogi-1.0-SNAPSHOT.jar_ -paketin


### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraportti: _/target/site/jacoco/index.html_

### Checkstyle

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
