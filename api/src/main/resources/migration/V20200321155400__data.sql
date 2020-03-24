INSERT INTO `organisation` (`name`, `zipcode`, `city`, `address`) VALUES
    ('Testorga', '00123', 'Berlin', 'Musterstrasse');

INSERT INTO `user` (`email`, `password`, `firstname`, `lastname`, `phone`, `organisation_id`, `is_admin`, `is_verified`) VALUES
	('admin@root.com', '$2a$10$uuT5ef4CsSdtmQ8A17afr.nj7EPvUbpk3zFRlszmMTrfWzoWxHeYm', 'Ruth', 'Admin', '012832873', 1, true, true);
	
INSERT INTO `user` (`email`, `password`, `firstname`, `lastname`, `phone`, `organisation_id`, `is_admin`, `is_verified`) VALUES
	('user@root.com', '$2a$10$uuT5ef4CsSdtmQ8A17afr.nj7EPvUbpk3zFRlszmMTrfWzoWxHeYm', 'Norm-Al', 'User', '012832873', 1, false, true);

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
	
INSERT INTO `patient` (`organisation_id`, `firstname`, `lastname`, `gender`, `phone`, `email`, `date_of_birth`, `zipcode`, `city`, `address`, `status`, `notes`) VALUES
	(1, 'Shain', 'Toad', 'DIVERS', '01213321123', 'shaintoad@gelebt.hat', '1945-03-28 00:00:01.0', '01231', 'Totenstadt', 'Am Friedhof 0', 'TEST_POSITIVE_IN_QUARANTINE', 'Kurz vor 12' ),
	(1, 'Anna', 'Lena', 'FEMALE', '019371937', null, '1960-07-12 00:00:01.0', '12345', 'Beispieldorf', 'Zum-Beispiel-Hier-Strasse 10', 'REGISTERED', ''),
	(1, 'Maria', 'Magdalena', 'FEMALE', '0913031812', null, '1983-06-22 00:00:01.0', '87343', 'Mustertal', 'Musterallee 43', 'VOLUNTARY_QUARANTINE', ''),
	(1, 'Jorge', 'Taun', 'MALE', '04144143442', 'jt123@gehtdichnichtsan.de', '1953-12-28 00:00:01.0', '13923', 'Lang am Weilig', 'Wohinstrasse 2', 'TEST_IN_QUARANTINE', 'Weiss nicht wohin mit sich'),
	(1, 'Mars', 'El', 'MALE', '0982098237', 'mars.el@was.tun', '1999-11-02 00:00:01.0', '91322', 'Karantehne', 'Zuhause-Weg 9', 'VOLUNTARY_QUARANTINE', 'Kann nichts machen'),
	(1, 'Ali', 'Na', 'MALE', '0924982473', null, '2002-01-1 00:00:01.0', '12398', 'Frohdorf', 'Glückstrasse 13', 'TEST_VISIT_HOSPITAL', 'Mental stabil');
	
INSERT INTO `patient_symptom` (`patient_id`, `symptom_id`) VALUES
	(1, 1),	(1, 2),
	(3, 3), (3, 7), (3,12),
	(4, 2), (4, 5), (4, 3),
	(5, 10), (5, 3),
	(6,5), (6, 7);
	
INSERT INTO `history` (`patient_id`, `creator_id`, `created_at`, `modified_at`, `type`, `content`) VALUES
	(1, 1, '2020-02-01 10:32:17.4', '2020-02-01 10:32:17.4', 'INFO', 'erster Anruf, besorgt, Aufnahme der Symptome'),
	(1, 1, '2020-02-01 10:41:01.4', '2020-02-01 10:41:01.4', 'TASK', 'Rückruf in 2 Tagen'),
	(1, 1, '2020-02-03 09:00:00.0', '2020-02-03 09:00:00.0', 'INFO', 'Symptome: Fieber >37.5 °C, Trockener Husten'),
	(1, 1, '2020-02-03 09:00:00.0', '2020-02-03 09:00:00.0', 'ACTION', 'Test angeordnet und vorläufig in Quarantäne'),
	(1, 1, '2020-02-03 09:41:01.4', '2020-02-03 09:41:01.4', 'TASK', 'Rückruf in 2 Tagen'),
	(1, 1, '2020-02-05 12:02:34.0', '2020-02-05 12:02:34.0', 'ACTION', 'Test positiv, strenge Quarantäne');
	
INSERT INTO `task` (`patient_id`, `agent_id`, `content`, `status`, `created_at`, `active_to`) VALUES
	(1, 1, 'Patient anrufen', 'CLOSED', '2020-02-01 10:41:01.4', '2020-02-03 10:41:01.4'),
	(1, 2, 'Patient anrufen', 'CLOSED', '2020-02-03 09:41:01.4', '2020-02-05 09:41:01.4');