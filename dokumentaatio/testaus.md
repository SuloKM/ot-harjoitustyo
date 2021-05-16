# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla että manuaalisesti järjestelmätasolla.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoidut testit kattavat sovelluslogiikan (_katalogi.domain_) ja pysyväistallennuksen (_katalogi.dao_).

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 95% ja haaraumakattavuus 86%.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/testauskattavuus.png" width="300">

## Järjestelmätestaus

Suoritettu manuaalisesti.

### Asennus ja konfigurointi

Testattu toimivaksi käyttöohjeen mukaisesti Linux-ympäristössä. Tosin tietokantayhteyden toiminnassa on havaittu ongelmia tietyissä hakemistoissa.

### Toiminnallisuus

Määrittelydokumentin listaamat toiminnallisuudet todettu toimiviksi.

## Sovellukseen jääneet heikkoudet

OSX-kehitysympäristössä havaittu ajonaikaisia ongelmia liittyen pom.xml-tiedoston määrityksiin.
