INSERT INTO `organisation` (`name`, `zipcode`, `city`, `address`) VALUES
    ('Testorga', '00123', 'Berlin', 'Musterstrasse');

INSERT INTO `user` (`email`, `password`, `firstname`, `lastname`, `phone`, `organisation_id`, `is_admin`, `is_verified`) VALUES
	('root@root.com', '$2a$10$uuT5ef4CsSdtmQ8A17afr.nj7EPvUbpk3zFRlszmMTrfWzoWxHeYm', 'Ruth', 'Juza', '012832873', 1, true, true);

INSERT INTO `symptom` (`name`) VALUES
	('Fieber >37.5°C'),
	('Trockener Husten'),
	('Unwohlsein und Ermüdung'),
	('Erhöhte Speichelproduktion'),
	('Kurzatmigkeit'),
	('Muskel- und Gelenkschmerzen'),
	('Halsschmerzen'),
	('Kopfschmerzen'),
	('Schüttelfrost'),
	('Übelkeit / Erbrechen'),
	('Schnupfen'),
	('Durchfall'),
	('Bluthusten (Hämoptyse)'),
	('Schwellung der Bindehaut');
	
INSERT INTO `patient` (`organisation_id`, `firstname`, `lastname`, `gender`, `phone`, `date_of_birth`, `zipcode`, `city`, `address`, `status`, `notes`) VALUES
	(1, 'Shain', 'Toad', 'DIVERS', '01213321123', '1945-03-28 00:00:01.0', '01231', 'Totenstadt', 'Am Friedhof 0', 'UNKNOWN', 'Kurz vor 12'),
	(1, 'Anna', 'Lena', 'FEMALE', '019371937', '1960-07-12 00:00:01.0', '12345', 'Beispieldorf', 'Zum-Beispiel-Hier-Strasse 10', 'UNKNOWN', ''),
	(1, 'Maria', 'Magdalena', 'FEMALE', '0913031812', '1983-06-22 00:00:01.0', '87343', 'Mustertal', 'Musterallee 43', 'UNKNOWN', ''),
	(1, 'Jorge', 'Taun', 'MALE', '04144143442', '1953-12-28 00:00:01.0', '13923', 'Lang am Weilig', 'Wohinstrasse 2', 'UNKNOWN', 'Weiss nicht wohin mit sich'),
	(1, 'Mars', 'El', 'MALE', '0982098237', '1999-11-02 00:00:01.0', '91322', 'Karantehne', 'Zuhause-Weg 9', 'UNKNOWN', 'Kann nichts machen'),
	(1, 'Ali', 'Na', 'MALE', '0924982473', '2002-01-1 00:00:01.0', '12398', 'Frohdorf', 'Glückstrasse 13', 'UNKNOWN', 'Mental stabil');
	
INSERT INTO `task` (`patient_id`, `agent_id`, `content`, `status`, `created_at`, `active_to`) VALUES
	(1, 1, 'Patient anrufen', 'OPEN', '2020-03-21 10:00:00.0', '2020-03-25 12:00:00.0');