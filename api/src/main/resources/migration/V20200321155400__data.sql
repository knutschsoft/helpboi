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
	(1, 'Shain', 'Toad', 'DIVERS', '1213321123', '1945-03-28 00:00:01.0', '01231', 'Totenstadt', 'Am Friedhof 1', 'UNKNOWN', 'scheintot');