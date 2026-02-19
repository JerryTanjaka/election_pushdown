-- Table des candidats
CREATE TABLE candidate (
                           id SERIAL PRIMARY KEY,
                           name TEXT NOT NULL
);

-- Table des électeurs
CREATE TABLE voter (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL
);

-- Type ENUM pour le type de vote
CREATE TYPE vote_type AS ENUM ('VALID', 'BLANK', 'NULL');

-- Table des votes
CREATE TABLE vote (
                      id SERIAL PRIMARY KEY,
                      candidate_id INT REFERENCES candidate(id),
                      voter_id INT NOT NULL REFERENCES voter(id),
                      vote_type vote_type NOT NULL
);
