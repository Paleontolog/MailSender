DROP TABLE IF EXISTS ADDRESSEES;

CREATE TABLE IF NOT EXISTS ADDRESSEES (
	ID IDENTITY NOT NULL PRIMARY KEY,
	EMAIL VARCHAR(255) NOT NULL
);

insert into ADDRESSEES values
    ( null , 'lezgyan@yandex.ru' ),
    ( null , 'lezgyan.artem@yandex.ru' );
