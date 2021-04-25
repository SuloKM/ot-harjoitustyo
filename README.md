#  Musakatalogi

Sovelluksella on tarkoitus pystyä ylläpitämään henkilökohtaista musiikkikokoelmaa.

## Dokumentaatio

[vaatimusmaarittely.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/vaatimusmaarittely.md)

[tuntikirjanpito.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/tuntikirjanpito.md)

[arkkitehtuuri.md](https://github.com/SuloKM/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Ohjelman käyttöönotto

Projektikansio ladataan koneelle ja sijoitetaan haluttuun hakemistoon. Musakatalogi-hakemistossa (sisältää pom.xml-tiedoston) suoritetaan komento

```
mvn compile exec:java -Dexec.mainClass=katalogi.Main
```

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
