--
-- Dataark for tabell `Konto`
--

INSERT INTO `Konto` (`Kontonummer`, `Personnummer`, `Saldo`, `Type`, `Valuta`) VALUES
('105010123456', '01010110523', 720, 'Lonnskonto', 'NOK'),
('105020123456', '01010110523', 100500, 'Sparekonto', 'NOK'),
('22334412345', '01010110523', 10234.5, 'Brukskonto', 'NOK');

--
-- Dataark for tabell `Kunde`
--

INSERT INTO `Kunde` (`Personnummer`, `Fornavn`, `Etternavn`, `Adresse`, `Postnr`, `Telefonnr`, `Passord`) VALUES
('01010110523', 'Lene', 'Jensen', 'Askerveien 22', '3270', '22224444', 'HeiHei'),
('12345678901', 'Per', 'Hansen', 'Osloveien 82', '1234', '12345678', 'HeiHei');

--
-- Dataark for tabell `Poststed`
--

INSERT INTO `Poststed` (`Postnr`, `Poststed`) VALUES
('1234', 'Oslo'),
('3270', 'Asker');

--
-- Dataark for tabell `Transaksjon`
--

INSERT INTO `Transaksjon` (`TxID`, `FraTilKontonummer`, `Belop`, `Dato`, `Melding`, `Kontonummer`, `Avventer`) VALUES
(1, '20102012345', 100.5, '2015-03-15', 'Fjordkraft', '105010123456', 1),
(2, '20102012345', 400.4, '2015-03-20', 'Skagen', '105010123456', 1),
(3, '20102012345', -1400.7, '2015-03-13', 'Innbetaling', '55551166677', 1),
(4, '20102012347', 5000.5, '2015-03-30', 'Husleie', '105010123456', 1),
(5, '20102012345', 345.56, '2015-03-13', 'Test', '55551166677', 0),
(6, '12312345', 1234, '2012-12-12', 'Melding', '234567', 1),
(7, '345678908', 3000, '2012-12-12', '', '105010123456', 0),
(8, '234534678', 15, '2012-12-12', 'Hei', '105010123456', 0),
(9, '1234254365', 125, '2012-12-12', 'Hopp', '105010123456', 0);