-- Database: `Bank` DENNE MÅ VÆRE OPPRETTET FØR SCRIPTET KJØRES !!!
--

-- --------------------------------------------------------
--
-- Tabellstruktur for tabell `Konto`
--

CREATE TABLE IF NOT EXISTS `Konto` (
                                       `Kontonummer` varchar(20) NOT NULL,
                                       `Personnummer` varchar(11) NOT NULL,
                                       `Saldo` float NOT NULL,
                                       `Type` varchar(20) NOT NULL,
                                       `Valuta` varchar(3) NOT NULL,
                                       PRIMARY KEY (`Kontonummer`)
);

--
-- Tabellstruktur for tabell `Kunde`
--

CREATE TABLE IF NOT EXISTS `Kunde` (
                                       `Personnummer` varchar(11) NOT NULL,
                                       `Fornavn` varchar(30) NOT NULL,
                                       `Etternavn` varchar(30) NOT NULL,
                                       `Adresse` varchar(50) NOT NULL,
                                       `Postnr` varchar(4) NOT NULL,
                                       `Telefonnr` varchar(8) NOT NULL,
                                       `Passord` varchar(500) NOT NULL,
                                       PRIMARY KEY (`Personnummer`)
);

--
-- Tabellstruktur for tabell `Poststed`
--

CREATE TABLE IF NOT EXISTS `Poststed` (
                                          `Postnr` varchar(4) NOT NULL,
                                          `Poststed` varchar(30) NOT NULL,
                                          PRIMARY KEY (`Postnr`)
);

--
-- Tabellstruktur for tabell `Transaksjon`
--

CREATE TABLE IF NOT EXISTS `Transaksjon` (
                                             `TxID` int(11) NOT NULL AUTO_INCREMENT,
                                             `FraTilKontonummer` varchar(20) NOT NULL,
                                             `Belop` float NOT NULL,
                                             `Dato` date NOT NULL,
                                             `Melding` varchar(100) NOT NULL,
                                             `Kontonummer` varchar(20) NOT NULL,
                                             `Avventer` tinyint(1) NOT NULL,
                                             PRIMARY KEY (`TxID`)
);