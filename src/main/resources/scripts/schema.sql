CREATE TABLE gobelet (
    gobelet_id SERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    description TEXT,
    image VARCHAR(255),
    type VARCHAR(20) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO gobelet (nom, description, image, type)
VALUES
    ('Gecko', 'Petit lézard agile, souvent coloré, vivant dans les régions chaudes.', 'gecko.jpg', 'REPTILE'),
    ('Tortue', 'Reptile lent, protégé par une carapace dure.', 'tortue.jpg', 'REPTILE'),
    ('Raie', 'Poisson plat aux larges nageoires, vivant dans les fonds marins.', 'raie.jpg', 'POISSON'),
    ('Aigle', 'Grand rapace puissant, aux ailes larges et au regard perçant.', 'aigle.jpg', 'OISEAU'),
    ('Serpent', 'Reptile rampant, sans pattes, au corps allongé et couvert d’écailles.', 'serpent.jpg', 'REPTILE'),
    ('Suricate', 'Petit mammifère curieux, agile, dressé sur ses pattes pour surveiller.', 'suricate.jpg', 'MAMMIFERE'),
    ('Michael Jackson', 'Michael Jackson méconnaissable, visage déformé, sourire inquiétant, ambiance cauchemardesque.', 'mj.jpg', 'REPTILE');